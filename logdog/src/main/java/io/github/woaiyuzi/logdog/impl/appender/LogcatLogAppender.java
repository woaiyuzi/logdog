package io.github.woaiyuzi.logdog.impl.appender;

import android.util.Log;
import io.github.woaiyuzi.logdog.api.LogAppender;
import io.github.woaiyuzi.logdog.model.LogLevel;
import io.github.woaiyuzi.logdog.model.LogMessage;

import static io.github.woaiyuzi.logdog.model.LogLevel.TRACE;
import static io.github.woaiyuzi.logdog.model.LogLevel.DEBUG;
import static io.github.woaiyuzi.logdog.model.LogLevel.INFO;
import static io.github.woaiyuzi.logdog.model.LogLevel.WARN;
import static io.github.woaiyuzi.logdog.model.LogLevel.ERROR;


public final class LogcatLogAppender extends LogAppender {

    public LogcatLogAppender() {
        this(LogLevel.DEBUG);
    }

    public LogcatLogAppender(LogLevel level) {
        super(level);
    }

    @Override
    public void doAppend(LogMessage message) {
        LogLevel level = message.level();
        switch(level) {
            case ERROR:
                Log.e(message.tag(), message.message(), message.throwable());
                break;
            case WARN:
                Log.w(message.tag(), message.message(), message.throwable());
                break;
            case INFO:
                Log.i(message.tag(), message.message(), message.throwable());
                break;
            case DEBUG:
                Log.d(message.tag(), message.message(), message.throwable());
                break;
            case TRACE:
                Log.v(message.tag(), message.message(), message.throwable());
                break;
        }
    }
}
