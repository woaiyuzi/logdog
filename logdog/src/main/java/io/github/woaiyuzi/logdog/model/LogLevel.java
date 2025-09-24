package io.github.woaiyuzi.logdog.model;
import android.util.Log;

public enum LogLevel {

    TRACE(Log.VERBOSE),
    DEBUG(Log.DEBUG),
    INFO(Log.INFO),
    WARN(Log.WARN),
    ERROR(Log.ERROR);
    
    private final int level;
    
    LogLevel(int level) {
        this.level = level;
    }

    public int levelInt() {
        return level;
    }
}
