package bolts;

import android.net.*;

public static class Target
{
    private final String appName;
    private final String className;
    private final String packageName;
    private final Uri url;
    
    public Target(final String packageName, final String className, final Uri url, final String appName) {
        this.packageName = packageName;
        this.className = className;
        this.url = url;
        this.appName = appName;
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getPackageName() {
        return this.packageName;
    }
    
    public Uri getUrl() {
        return this.url;
    }
}
