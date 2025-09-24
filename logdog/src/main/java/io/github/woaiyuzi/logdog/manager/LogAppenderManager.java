package io.github.woaiyuzi.logdog.manager;
import io.github.woaiyuzi.logdog.api.LogAppender;
import io.github.woaiyuzi.logdog.api.Logger;
import io.github.woaiyuzi.logdog.model.LogLevel;
import io.github.woaiyuzi.logdog.model.LogMessage;
import java.util.HashSet;
import java.util.Set;

public final class LogAppenderManager {
    private static LogAppenderManager INSTANCE;
    
    private final Set<LogAppender> appenderSet;
    
    private LogAppenderManager() {
        this.appenderSet = new HashSet<>();
    }
    
    public static LogAppenderManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LogAppenderManager();
        }
    	return INSTANCE;
    }

    public void addLogAppender(LogAppender logAppender) {
    	appenderSet.add(logAppender);
    }
    
    public void removeLogAppender(LogAppender logAppender) {
        appenderSet.remove(logAppender);
    }

    public boolean isLoggable(LogLevel level) {
    	for(LogAppender appender : appenderSet) {
            if(appender.isLoggable(level)) {
                return true;
            }
        }
        return false;
    }

    public void appendToAll(LogMessage message) {
    	for(LogAppender appender : appenderSet) {
            if(appender.isLoggable(message.level())) {
                appender.doAppend(message);
            }
        }
    }
}
