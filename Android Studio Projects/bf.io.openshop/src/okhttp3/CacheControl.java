package okhttp3;

import java.util.concurrent.*;
import okhttp3.internal.http.*;
import java.io.*;

public final class CacheControl
{
    public static final CacheControl FORCE_CACHE;
    public static final CacheControl FORCE_NETWORK;
    String headerValue;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;
    
    static {
        FORCE_NETWORK = new Builder().noCache().build();
        FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    }
    
    private CacheControl(final Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
    }
    
    private CacheControl(final boolean noCache, final boolean noStore, final int maxAgeSeconds, final int sMaxAgeSeconds, final boolean isPrivate, final boolean isPublic, final boolean mustRevalidate, final int maxStaleSeconds, final int minFreshSeconds, final boolean onlyIfCached, final boolean noTransform, final String headerValue) {
        this.noCache = noCache;
        this.noStore = noStore;
        this.maxAgeSeconds = maxAgeSeconds;
        this.sMaxAgeSeconds = sMaxAgeSeconds;
        this.isPrivate = isPrivate;
        this.isPublic = isPublic;
        this.mustRevalidate = mustRevalidate;
        this.maxStaleSeconds = maxStaleSeconds;
        this.minFreshSeconds = minFreshSeconds;
        this.onlyIfCached = onlyIfCached;
        this.noTransform = noTransform;
        this.headerValue = headerValue;
    }
    
    private String headerValue() {
        final StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=").append(this.maxAgeSeconds).append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=").append(this.sMaxAgeSeconds).append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=").append(this.maxStaleSeconds).append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=").append(this.minFreshSeconds).append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(-2 + sb.length(), sb.length());
        return sb.toString();
    }
    
    public static CacheControl parse(final Headers headers) {
        boolean b = false;
        boolean b2 = false;
        int seconds = -1;
        int seconds2 = -1;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = false;
        int seconds3 = -1;
        int seconds4 = -1;
        boolean b6 = false;
        boolean b7 = false;
        boolean b8 = true;
        String s = null;
        for (int i = 0; i < headers.size(); ++i) {
            final String name = headers.name(i);
            final String value = headers.value(i);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (s != null) {
                    b8 = false;
                }
                else {
                    s = value;
                }
            }
            else {
                if (!name.equalsIgnoreCase("Pragma")) {
                    continue;
                }
                b8 = false;
            }
            int j = 0;
            while (j < value.length()) {
                final int n = j;
                final int skipUntil = HeaderParser.skipUntil(value, j, "=,;");
                final String trim = value.substring(n, skipUntil).trim();
                String s2;
                if (skipUntil == value.length() || value.charAt(skipUntil) == ',' || value.charAt(skipUntil) == ';') {
                    j = skipUntil + 1;
                    s2 = null;
                }
                else {
                    final int skipWhitespace = HeaderParser.skipWhitespace(value, skipUntil + 1);
                    if (skipWhitespace < value.length() && value.charAt(skipWhitespace) == '\"') {
                        final int n2 = skipWhitespace + 1;
                        final int skipUntil2 = HeaderParser.skipUntil(value, n2, "\"");
                        s2 = value.substring(n2, skipUntil2);
                        j = skipUntil2 + 1;
                    }
                    else {
                        j = HeaderParser.skipUntil(value, skipWhitespace, ",;");
                        s2 = value.substring(skipWhitespace, j).trim();
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    b = true;
                }
                else if ("no-store".equalsIgnoreCase(trim)) {
                    b2 = true;
                }
                else if ("max-age".equalsIgnoreCase(trim)) {
                    seconds = HeaderParser.parseSeconds(s2, -1);
                }
                else if ("s-maxage".equalsIgnoreCase(trim)) {
                    seconds2 = HeaderParser.parseSeconds(s2, -1);
                }
                else if ("private".equalsIgnoreCase(trim)) {
                    b3 = true;
                }
                else if ("public".equalsIgnoreCase(trim)) {
                    b4 = true;
                }
                else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    b5 = true;
                }
                else if ("max-stale".equalsIgnoreCase(trim)) {
                    seconds3 = HeaderParser.parseSeconds(s2, Integer.MAX_VALUE);
                }
                else if ("min-fresh".equalsIgnoreCase(trim)) {
                    seconds4 = HeaderParser.parseSeconds(s2, -1);
                }
                else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    b6 = true;
                }
                else {
                    if (!"no-transform".equalsIgnoreCase(trim)) {
                        continue;
                    }
                    b7 = true;
                }
            }
        }
        if (!b8) {
            s = null;
        }
        return new CacheControl(b, b2, seconds, seconds2, b3, b4, b5, seconds3, seconds4, b6, b7, s);
    }
    
    public boolean isPrivate() {
        return this.isPrivate;
    }
    
    public boolean isPublic() {
        return this.isPublic;
    }
    
    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }
    
    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }
    
    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }
    
    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }
    
    public boolean noCache() {
        return this.noCache;
    }
    
    public boolean noStore() {
        return this.noStore;
    }
    
    public boolean noTransform() {
        return this.noTransform;
    }
    
    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }
    
    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }
    
    @Override
    public String toString() {
        final String headerValue = this.headerValue;
        if (headerValue != null) {
            return headerValue;
        }
        return this.headerValue = this.headerValue();
    }
    
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
}
