package io.github.woaiyuzi.logdog.manager;
import android.app.Application;
import android.content.Context;
import java.io.File;

public final class ApplicationManager {

    private static ApplicationManager INSTANCE;
    
    private Application application;

    private ApplicationManager() {
        
    }

    public static ApplicationManager getInstance() {
    	if(INSTANCE == null) {
            INSTANCE = new ApplicationManager();
        }
        
        return INSTANCE;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getPackageName() {
        if(application != null) {
            return application.getPackageName();
        }
        
        return null;
    }

    public File getExternalFilesDir(String subDir) {
    	if(application != null) {
            return application.getExternalFilesDir(subDir);
        }
        
        return null;
    }
}
