package io.github.woaiyuzi.logdog.api;

public interface Logger {

    String getName();

    String getTag();

    void trace(String msg);
    
    void trace(String msg, Throwable t);

    void trace(String format, Object... args);

    void debug(String msg);
    
    void debug(String msg, Throwable t);

    void debug(String format, Object... args);
    
    void info(String msg);
    
    void info(String msg, Throwable t);

    void info(String format, Object... args);
    
    void warn(String msg);
    
    void warn(String msg, Throwable t);

    void warn(String format, Object... args);
    
    void error(String msg);
    
    void error(String msg, Throwable t);

    void error(String format, Object... args);
    
}
