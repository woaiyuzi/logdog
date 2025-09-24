package io.github.woaiyuzi.logdog;
import android.app.Application;
import io.github.woaiyuzi.logdog.api.LogAppender;
import io.github.woaiyuzi.logdog.manager.ApplicationManager;
import io.github.woaiyuzi.logdog.manager.LogAppenderManager;
import io.github.woaiyuzi.logdog.manager.PackageLevelManager;
import io.github.woaiyuzi.logdog.manager.PackageTagManager;
import io.github.woaiyuzi.logdog.model.LogLevel;

public final class LogDog {

    private static LogDog INSTANCE;

    private LogDog() {}
    
    public static LogDog getInstance(Application application) {
    	if(INSTANCE == null) {
            INSTANCE = new LogDog();
            ApplicationManager.getInstance().setApplication(application);
        }
        
        return INSTANCE;
    }

    public void addLogAppender(LogAppender logAppender) {
    	LogAppenderManager.getInstance().addLogAppender(logAppender);
    }

    public void removeLogAppender(LogAppender logAppender) {
    	LogAppenderManager.getInstance().removeLogAppender(logAppender);
    }

    public void setPackageLevel(String packageName, LogLevel level) {
    	PackageLevelManager.getInstance().setPackageLevel(packageName, level);
    }

    public void setPackageTagPrefix(String packageName, String tagPrefix) {
    	PackageTagManager.getInstance().setPackageTagPrefix(packageName, tagPrefix);
    }
}
