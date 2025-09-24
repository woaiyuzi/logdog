package io.github.woaiyuzi.logdog.manager;
import io.github.woaiyuzi.logdog.api.Logger;
import java.util.HashMap;
import java.util.Map;

public final class PackageTagManager {
    
    private static PackageTagManager INSTANCE;

    private final Map<String, String> tagPrefixMap;

    private PackageTagManager() {
        this.tagPrefixMap = new HashMap<>();
    }
    
    public static PackageTagManager getInstance() {
    	if(INSTANCE == null) {
            INSTANCE = new PackageTagManager();
        }
        
        return INSTANCE;
    }

    public void setPackageTagPrefix(String packageName, String tagPrefix) {
    	this.tagPrefixMap.put(packageName, tagPrefix);
    }

    public String getTagPrefix(String loggerName) {
        String lastPkgName = "";
        String tagPrefix = "";
    	for(String pkgName : tagPrefixMap.keySet()) {
            if(loggerName.startsWith(pkgName) && pkgName.length() > lastPkgName.length()) {
                 tagPrefix = tagPrefixMap.get(pkgName);
                 lastPkgName = pkgName;
            }
        }
        
        return tagPrefix;
    }
}
