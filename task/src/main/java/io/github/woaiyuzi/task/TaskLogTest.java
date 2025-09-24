package io.github.woaiyuzi.task;
import io.github.woaiyuzi.logdog.api.Logger;
import io.github.woaiyuzi.logdog.factory.LoggerFactory;

public class TaskLogTest {

    private static final Logger log = LoggerFactory.getLogger("TaskLogTest");

    public static void log() {
        
        log.error("Logger name is {}", log.getName());
    	log.trace("Trace message");
        log.debug("Debug message");
        log.info("Info message");
        log.warn("Warn message");
        log.error("Error message");
    }
}
