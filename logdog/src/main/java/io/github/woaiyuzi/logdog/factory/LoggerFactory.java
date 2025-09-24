package io.github.woaiyuzi.logdog.factory;
import io.github.woaiyuzi.logdog.api.Logger;
import io.github.woaiyuzi.logdog.impl.logger.LoggerImpl;
import io.github.woaiyuzi.logdog.manager.PackageTagManager;
import io.github.woaiyuzi.logdog.utils.StackTraceUtils;

public final class LoggerFactory {
    private LoggerFactory() {
        
    }

    public static Logger getLogger(String tag) {
    	String loggerName = getInvokeClassName();
        tag = PackageTagManager.getInstance().getTagPrefix(loggerName) + '-' + tag;
        return new LoggerImpl(loggerName, tag);
    }

    public static Logger getLogger(Class<?> clazz) {
    	return getLogger(clazz.getSimpleName());
    }

    private static String getInvokeClassName() {
        return StackTraceUtils.getInvokeClassName(3);
    }
}
