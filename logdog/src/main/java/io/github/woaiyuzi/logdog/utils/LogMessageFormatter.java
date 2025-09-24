package io.github.woaiyuzi.logdog.utils;

import java.util.Arrays;

public final class LogMessageFormatter {
    private static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    private LogMessageFormatter() {}

    public static String format(String format, Object[] args, int length) {
        StringBuilder sb = new StringBuilder(format.length() + 50);
        int argIndex = 0;
        int formatIndex = 0;

        while (argIndex < length) {
            int delimIndex = format.indexOf(DELIM_STR, formatIndex);
            if (delimIndex == -1) {
                // 没有更多占位符，追加剩余部分
                sb.append(format, formatIndex, format.length());
                return sb.toString();
            }

            // 如果占位符前面是转义符 '\'
            if (isEscapedDelimiter(format, delimIndex)) {
                if (!isDoubleEscaped(format, delimIndex)) {
                    // 单个转义： \{}  -> 输出 "{}" 字面，不消费参数
                    sb.append(format, formatIndex, delimIndex - 1); // 取到转义符前为止
                    sb.append(DELIM_STR);
                    formatIndex = delimIndex + 2;
                    // 不改变 argIndex
                } else {
                    // 双转义： \\{} -> 视为转义符被转义，实际消费参数，并保留一个 '\' 字符
                    sb.append(format, formatIndex, delimIndex - 1); // 保留前面的一个 '\'
                    deeplyAppendParameter(sb, args[argIndex++]);     // 仅此处消费参数
                    formatIndex = delimIndex + 2;
                }
            } else {
                // 正常替换
                sb.append(format, formatIndex, delimIndex);
                deeplyAppendParameter(sb, args[argIndex++]);
                formatIndex = delimIndex + 2;
            }
        }

        // 参数耗尽但格式串可能还有剩余占位符或其它文本：直接追加剩余文本
        sb.append(format, formatIndex, format.length());
        return sb.toString();
    }

    private static void deeplyAppendParameter(final StringBuilder sb, final Object param) {
        if (param == null) {
            sb.append("null");
            return;
        }

        Class<?> cls = param.getClass();
        if (!cls.isArray()) {
            sb.append(param.toString());
            return;
        }

        // 处理各种基本类型数组
        if (param instanceof boolean[]) {
            sb.append(Arrays.toString((boolean[]) param));
        } else if (param instanceof byte[]) {
            sb.append(Arrays.toString((byte[]) param));
        } else if (param instanceof char[]) {
            sb.append(Arrays.toString((char[]) param));
        } else if (param instanceof short[]) {
            sb.append(Arrays.toString((short[]) param));
        } else if (param instanceof int[]) {
            sb.append(Arrays.toString((int[]) param));
        } else if (param instanceof long[]) {
            sb.append(Arrays.toString((long[]) param));
        } else if (param instanceof float[]) {
            sb.append(Arrays.toString((float[]) param));
        } else if (param instanceof double[]) {
            sb.append(Arrays.toString((double[]) param));
        } else if (param instanceof Object[]) {
            // 对象数组（可能嵌套数组）使用 deepToString
            sb.append(Arrays.deepToString((Object[]) param));
        } else {
            // 兜底
            sb.append(param.toString());
        }
    }

    private static boolean isEscapedDelimiter(final String format, final int delimIndex) {
        return delimIndex > 0 && format.charAt(delimIndex - 1) == ESCAPE_CHAR;
    }

    private static boolean isDoubleEscaped(final String format, final int delimIndex) {
        return delimIndex >= 2 && format.charAt(delimIndex - 2) == ESCAPE_CHAR;
    }
}