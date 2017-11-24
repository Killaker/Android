package bolts;

import android.net.*;
import java.util.*;

public class AppLink
{
    private Uri sourceUrl;
    private List<Target> targets;
    private Uri webUrl;
    
    public AppLink(final Uri sourceUrl, List<Target> emptyList, final Uri webUrl) {
        this.sourceUrl = sourceUrl;
        if (emptyList == null) {
            emptyList = Collections.emptyList();
        }
        this.targets = emptyList;
        this.webUrl = webUrl;
    }
    
    public Uri getSourceUrl() {
        return this.sourceUrl;
    }
    
    public List<Target> getTargets() {
        return Collections.unmodifiableList((List<? extends Target>)this.targets);
    }
    
    public Uri getWebUrl() {
        return this.webUrl;
    }
    
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
}
