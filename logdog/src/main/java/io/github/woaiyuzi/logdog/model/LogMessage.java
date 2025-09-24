package io.github.woaiyuzi.logdog.model;

public record LogMessage (
    String tag,
    LogLevel level,
    long currentTimeMillis,
    String threadName,
    String message,
    Throwable throwable
) {}