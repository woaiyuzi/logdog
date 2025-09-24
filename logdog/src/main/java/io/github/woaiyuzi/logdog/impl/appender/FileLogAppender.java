package io.github.woaiyuzi.logdog.impl.appender;

import android.util.Log;
import io.github.woaiyuzi.logdog.api.LogAppender;
import io.github.woaiyuzi.logdog.manager.ApplicationManager;
import io.github.woaiyuzi.logdog.model.LogLevel;
import io.github.woaiyuzi.logdog.model.LogMessage;
import io.github.woaiyuzi.logdog.utils.DateTimeUtils;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileLogAppender extends LogAppender {

    private static final String TAG = "LogDog-FileLogAppender";
    
    private static final char SPILT_CHAR = ' ';
    private static final char BRACKET_START_CHAR = '[';
    private static final char BRACKET_END_CHAR = ']';
    private static final char MSG_TOKEN_CHAR = '-';

    private final File logDir;
    private volatile File currentFile;
    private volatile BufferedWriter writer;
    private String currentDate;
    
    private final ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "LogDog-FileLogAppender-Writer");
        t.setDaemon(true);
        return t;
    });
    
    public FileLogAppender() {
        this(LogLevel.INFO);
    }
    
    public FileLogAppender(LogLevel level) {
        this(ApplicationManager.getInstance().getExternalFilesDir("log"), level);
    }

    public FileLogAppender(File logDir, LogLevel level) {
        super(level);
        this.logDir = logDir;
    }

    @Override
    public void doAppend(final LogMessage message) {
        executor.submit(() -> {
            final String logLine = formatMessage(message);
            try {
                rollFileIfNeeded();
                writer.write(logLine);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                Log.e(TAG, null, e);
            }
        });
    }

    private String formatMessage(LogMessage msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(DateTimeUtils.formatDateTime(msg.currentTimeMillis()));
        sb.append(SPILT_CHAR).append(msg.level().name());
        sb.append(SPILT_CHAR).append(BRACKET_START_CHAR).append(msg.threadName()).append(BRACKET_END_CHAR);
        sb.append(SPILT_CHAR).append(BRACKET_START_CHAR).append(msg.tag()).append(BRACKET_END_CHAR);
        sb.append(SPILT_CHAR).append(MSG_TOKEN_CHAR).append(SPILT_CHAR).append(msg.message());
        if(msg.throwable() != null) {
        	sb.append("\n").append(Log.getStackTraceString(msg.throwable()));
        }
        
        return sb.toString();
    }

    private synchronized void rollFileIfNeeded() throws IOException {
        String today = DateTimeUtils.formatDate(LocalDateTime.now());

        // 新的一天
        if (!today.equals(currentDate)) {
            currentDate = today;
            if(writer != null) {
                writer.close();
            }
            initCurrentFile();
            writer = new BufferedWriter(new FileWriter(currentFile, true));
        }
    }

    private void initCurrentFile() {
        currentFile = new File(logDir, currentDate + ".log");
    }
}