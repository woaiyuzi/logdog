package io.github.woaiyuzi.logdog.manager;
import io.github.woaiyuzi.logdog.model.LogLevel;
import java.util.HashMap;
import java.util.Map;

public final class PackageLevelManager {

    private LogLevel defaultLevel = LogLevel.TRACE;

    private static PackageLevelManager INSTANCE;
    
    private final Map<String, LogLevel> levelMap;

    private PackageLevelManager() {
        this.levelMap = new HashMap<>();
        String applicationId = ApplicationManager.getInstance().getPackageName();
        if(applicationId != null) {
            levelMap.put(applicationId, LogLevel.TRACE);
            defaultLevel = LogLevel.INFO;
        }
    }

    public static PackageLevelManager getInstance() {
    	if(INSTANCE == null) {
            INSTANCE = new PackageLevelManager();
        }
        
        return INSTANCE;
    }

    public void setPackageLevel(String packageName, LogLevel level) {
        this.levelMap.put(packageName, level);
    }

    public boolean isLoggable(String loggerName, LogLevel level) {
        String lastPkgName = "";
        LogLevel pkgLevel = defaultLevel;
    	for(String pkgName : levelMap.keySet()) {
            if(loggerName.startsWith(pkgName) && pkgName.length() > lastPkgName.length()) {
                pkgLevel = levelMap.get(pkgName);
                lastPkgName = pkgName;
            }
        }
        
        return level.levelInt() >= pkgLevel.levelInt();
    }
}
