package io.github.woaiyuzi.logdog.utils;

public final class StackTraceUtils {

  private StackTraceUtils() {}

    public static String getInvokeClassName(int skipCount) {
        return StackWalker.getInstance()
            .walk(
                frames ->
                    frames
                        .skip(skipCount)
                        .findFirst()
                        .map(StackWalker.StackFrame::getClassName)
                        .orElse("unknown"));
    }
}
