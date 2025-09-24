package io.github.woaiyuzi.logdog.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private DateTimeUtils() {}

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    
    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMAT);
    }

    public static String formatTime(LocalDateTime dateTime) {
        return dateTime.format(TIME_FORMAT);
    }

    public static String formatDateTime(long timestampMillis) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestampMillis),
                ZoneId.systemDefault() // 使用系统默认时区
        ).format(DATE_TIME_FORMAT);
    }
}