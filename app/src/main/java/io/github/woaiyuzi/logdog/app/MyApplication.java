package io.github.woaiyuzi.logdog.app;
import android.app.Application;
import io.github.woaiyuzi.logdog.LogDog;
import io.github.woaiyuzi.logdog.impl.appender.FileLogAppender;
import io.github.woaiyuzi.logdog.impl.appender.LogcatLogAppender;
import io.github.woaiyuzi.logdog.model.LogLevel;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLogging();
    }

    private void initLogging() {
        LogDog logDog = LogDog.getInstance(this);
        logDog.addLogAppender(new LogcatLogAppender());
        logDog.addLogAppender(new FileLogAppender());
        
        String taskPackageName = "io.github.woaiyuzi.task";
        logDog.setPackageLevel(taskPackageName, LogLevel.WARN);
        logDog.setPackageTagPrefix(taskPackageName, "Task");
    }
}
