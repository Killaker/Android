package okhttp3.internal;

import okhttp3.*;
import java.util.*;

public final class RouteDatabase
{
    private final Set<Route> failedRoutes;
    
    public RouteDatabase() {
        this.failedRoutes = new LinkedHashSet<Route>();
    }
    
    public void connected(final Route route) {
        synchronized (this) {
            this.failedRoutes.remove(route);
        }
    }
    
    public void failed(final Route route) {
        synchronized (this) {
            this.failedRoutes.add(route);
        }
    }
    
    public int failedRoutesCount() {
        synchronized (this) {
            return this.failedRoutes.size();
        }
    }
    
    public boolean shouldPostpone(final Route route) {
        synchronized (this) {
            return this.failedRoutes.contains(route);
        }
    }
}
