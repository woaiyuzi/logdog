package io.github.woaiyuzi.logdog.api;

import io.github.woaiyuzi.logdog.model.LogLevel;
import io.github.woaiyuzi.logdog.model.LogMessage;

public abstract class LogAppender {

    private LogLevel level;
    
    public LogAppender(LogLevel level) {
        this.level = level;
    }
    
    public abstract void doAppend(LogMessage message);
    
    public LogLevel getLevel() {
        return this.level;
    }
    
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public boolean isLoggable(LogLevel level) {
        return this.level.levelInt() <= level.levelInt();
    }
}
