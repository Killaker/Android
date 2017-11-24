package okhttp3;

import java.util.concurrent.*;

public static final class Builder
{
    int maxAgeSeconds;
    int maxStaleSeconds;
    int minFreshSeconds;
    boolean noCache;
    boolean noStore;
    boolean noTransform;
    boolean onlyIfCached;
    
    public Builder() {
        this.maxAgeSeconds = -1;
        this.maxStaleSeconds = -1;
        this.minFreshSeconds = -1;
    }
    
    public CacheControl build() {
        return new CacheControl(this, null);
    }
    
    public Builder maxAge(final int n, final TimeUnit timeUnit) {
        if (n < 0) {
            throw new IllegalArgumentException("maxAge < 0: " + n);
        }
        final long seconds = timeUnit.toSeconds(n);
        int maxAgeSeconds;
        if (seconds > 2147483647L) {
            maxAgeSeconds = Integer.MAX_VALUE;
        }
        else {
            maxAgeSeconds = (int)seconds;
        }
        this.maxAgeSeconds = maxAgeSeconds;
        return this;
    }
    
    public Builder maxStale(final int n, final TimeUnit timeUnit) {
        if (n < 0) {
            throw new IllegalArgumentException("maxStale < 0: " + n);
        }
        final long seconds = timeUnit.toSeconds(n);
        int maxStaleSeconds;
        if (seconds > 2147483647L) {
            maxStaleSeconds = Integer.MAX_VALUE;
        }
        else {
            maxStaleSeconds = (int)seconds;
        }
        this.maxStaleSeconds = maxStaleSeconds;
        return this;
    }
    
    public Builder minFresh(final int n, final TimeUnit timeUnit) {
        if (n < 0) {
            throw new IllegalArgumentException("minFresh < 0: " + n);
        }
        final long seconds = timeUnit.toSeconds(n);
        int minFreshSeconds;
        if (seconds > 2147483647L) {
            minFreshSeconds = Integer.MAX_VALUE;
        }
        else {
            minFreshSeconds = (int)seconds;
        }
        this.minFreshSeconds = minFreshSeconds;
        return this;
    }
    
    public Builder noCache() {
        this.noCache = true;
        return this;
    }
    
    public Builder noStore() {
        this.noStore = true;
        return this;
    }
    
    public Builder noTransform() {
        this.noTransform = true;
        return this;
    }
    
    public Builder onlyIfCached() {
        this.onlyIfCached = true;
        return this;
    }
}
