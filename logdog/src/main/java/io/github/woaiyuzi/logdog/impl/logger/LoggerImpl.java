package io.github.woaiyuzi.logdog.impl.logger;

import io.github.woaiyuzi.logdog.api.Logger;
import io.github.woaiyuzi.logdog.manager.LogAppenderManager;
import io.github.woaiyuzi.logdog.manager.PackageLevelManager;
import io.github.woaiyuzi.logdog.model.LogLevel;
import io.github.woaiyuzi.logdog.model.LogMessage;

import static io.github.woaiyuzi.logdog.model.LogLevel.TRACE;
import static io.github.woaiyuzi.logdog.model.LogLevel.DEBUG;
import static io.github.woaiyuzi.logdog.model.LogLevel.INFO;
import static io.github.woaiyuzi.logdog.model.LogLevel.WARN;
import static io.github.woaiyuzi.logdog.model.LogLevel.ERROR;
import io.github.woaiyuzi.logdog.utils.LogMessageFormatter;

public final class LoggerImpl implements Logger {

    private final String name;
    private final String tag;

    public LoggerImpl(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getTag() {
        return this.tag;
    }
    
    @Override
    public void trace(String msg) {
        log(TRACE, msg, null, null);
    }
    
    @Override
    public void trace(String msg, Throwable t) {
        log(TRACE, msg, null, t);
    }
    
    @Override
    public void trace(String format, Object... args) {
        log(TRACE, format, args, null);
    }
    
    @Override
    public void debug(String msg) {
        log(DEBUG, msg, null, null);
    }
    
    @Override
    public void debug(String msg, Throwable t) {
        log(DEBUG, msg, null, t);
    }
    
    @Override
    public void debug(String format, Object... args) {
        log(DEBUG, format, args, null);
    }
    
    @Override
    public void info(String msg) {
        log(INFO, msg, null, null);
    }
    
    @Override
    public void info(String msg, Throwable t) {
        log(INFO, msg, null, t);
    }
    
    @Override
    public void info(String format, Object... args) {
        log(INFO, format, args, null);
    }
    
    @Override
    public void warn(String msg) {
        log(WARN, msg, null, null);
    }
    
    @Override
    public void warn(String msg, Throwable t) {
        log(WARN, msg, null, t);
    }
    
    @Override
    public void warn(String format, Object... args) {
        log(WARN, format, args, null);
    }
    
    @Override
    public void error(String msg) {
        log(ERROR, msg, null, null);
    }
    
    @Override
    public void error(String msg, Throwable t) {
        log(ERROR, msg, null, t);
    }
    
    @Override
    public void error(String format, Object... args) {
        log(ERROR, format, args, null);
    }

    private void log(LogLevel level, String message, Object[] parameters, Throwable throwable) {
        // 检测是否需要输出日志
        if(!isLoggable(level)) {
            return;
        }
        
        //
        long currentTimeMillis = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        
        // 
        if (message == null) {
            message = "null"; // 保证 message 永远不会为 null
        }

        if (parameters != null && parameters.length > 0) {
            int length = parameters.length;
            if (throwable == null && parameters[length - 1] instanceof Throwable) {
                throwable = (Throwable) parameters[length - 1];
                length--; // 去掉 Throwable，不参与格式化
            }
            if (length > 0) { 
                message = LogMessageFormatter.format(message, parameters, length);
            }
        }
        
        LogMessage logMessage = new LogMessage(tag, level, currentTimeMillis, threadName, message, throwable);
        LogAppenderManager.getInstance().appendToAll(logMessage);
    }

    private boolean isLoggable(final LogLevel level) {
        // 1. LogLevel检测
        // 2. Logger name对应的包前缀LogLevel检测
        if(!LogAppenderManager.getInstance().isLoggable(level)) {
            return false;
        }
        
        if(!PackageLevelManager.getInstance().isLoggable(getName(), level)) {
            return false;
        }
        return true;
    }
}
