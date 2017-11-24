package okhttp3.internal.http;

import java.util.*;
import java.util.concurrent.*;
import okhttp3.*;

public final class CacheStrategy
{
    public final Response cacheResponse;
    public final Request networkRequest;
    
    private CacheStrategy(final Request networkRequest, final Response cacheResponse) {
        this.networkRequest = networkRequest;
        this.cacheResponse = cacheResponse;
    }
    
    public static boolean isCacheable(final Response response, final Request request) {
        Label_0162: {
            switch (response.code()) {
                case 302:
                case 307: {
                    if (response.header("Expires") != null || response.cacheControl().maxAgeSeconds() != -1 || response.cacheControl().isPublic() || response.cacheControl().isPrivate()) {
                        break Label_0162;
                    }
                    break;
                }
                case 200:
                case 203:
                case 204:
                case 300:
                case 301:
                case 308:
                case 404:
                case 405:
                case 410:
                case 414:
                case 501: {
                    if (!response.cacheControl().noStore() && !request.cacheControl().noStore()) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public static class Factory
    {
        private int ageSeconds;
        final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        final long nowMillis;
        private long receivedResponseMillis;
        final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;
        
        public Factory(final long nowMillis, final Request request, final Response cacheResponse) {
            this.ageSeconds = -1;
            this.nowMillis = nowMillis;
            this.request = request;
            this.cacheResponse = cacheResponse;
            if (cacheResponse != null) {
                final Headers headers = cacheResponse.headers();
                for (int i = 0; i < headers.size(); ++i) {
                    final String name = headers.name(i);
                    final String value = headers.value(i);
                    if ("Date".equalsIgnoreCase(name)) {
                        this.servedDate = HttpDate.parse(value);
                        this.servedDateString = value;
                    }
                    else if ("Expires".equalsIgnoreCase(name)) {
                        this.expires = HttpDate.parse(value);
                    }
                    else if ("Last-Modified".equalsIgnoreCase(name)) {
                        this.lastModified = HttpDate.parse(value);
                        this.lastModifiedString = value;
                    }
                    else if ("ETag".equalsIgnoreCase(name)) {
                        this.etag = value;
                    }
                    else if ("Age".equalsIgnoreCase(name)) {
                        this.ageSeconds = HeaderParser.parseSeconds(value, -1);
                    }
                    else if (OkHeaders.SENT_MILLIS.equalsIgnoreCase(name)) {
                        this.sentRequestMillis = Long.parseLong(value);
                    }
                    else if (OkHeaders.RECEIVED_MILLIS.equalsIgnoreCase(name)) {
                        this.receivedResponseMillis = Long.parseLong(value);
                    }
                }
            }
        }
        
        private long cacheResponseAge() {
            long max = 0L;
            if (this.servedDate != null) {
                max = Math.max(max, this.receivedResponseMillis - this.servedDate.getTime());
            }
            long max2;
            if (this.ageSeconds != -1) {
                max2 = Math.max(max, TimeUnit.SECONDS.toMillis(this.ageSeconds));
            }
            else {
                max2 = max;
            }
            return this.nowMillis - this.receivedResponseMillis + (max2 + (this.receivedResponseMillis - this.sentRequestMillis));
        }
        
        private long computeFreshnessLifetime() {
            long millis = 0L;
            final CacheControl cacheControl = this.cacheResponse.cacheControl();
            if (cacheControl.maxAgeSeconds() != -1) {
                millis = TimeUnit.SECONDS.toMillis(cacheControl.maxAgeSeconds());
            }
            else {
                if (this.expires != null) {
                    long n;
                    if (this.servedDate != null) {
                        n = this.servedDate.getTime();
                    }
                    else {
                        n = this.receivedResponseMillis;
                    }
                    long n2 = this.expires.getTime() - n;
                    if (n2 <= millis) {
                        n2 = millis;
                    }
                    return n2;
                }
                if (this.lastModified != null && this.cacheResponse.request().url().query() == null) {
                    long n3;
                    if (this.servedDate != null) {
                        n3 = this.servedDate.getTime();
                    }
                    else {
                        n3 = this.sentRequestMillis;
                    }
                    final long n4 = n3 - this.lastModified.getTime();
                    if (n4 > millis) {
                        return n4 / 10L;
                    }
                }
            }
            return millis;
        }
        
        private CacheStrategy getCandidate() {
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, null, null);
            }
            if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, null, null);
            }
            if (!CacheStrategy.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, null, null);
            }
            final CacheControl cacheControl = this.request.cacheControl();
            if (cacheControl.noCache() || hasConditions(this.request)) {
                return new CacheStrategy(this.request, null, null);
            }
            final long cacheResponseAge = this.cacheResponseAge();
            long n = this.computeFreshnessLifetime();
            if (cacheControl.maxAgeSeconds() != -1) {
                n = Math.min(n, TimeUnit.SECONDS.toMillis(cacheControl.maxAgeSeconds()));
            }
            long millis = 0L;
            if (cacheControl.minFreshSeconds() != -1) {
                millis = TimeUnit.SECONDS.toMillis(cacheControl.minFreshSeconds());
            }
            long millis2 = 0L;
            final CacheControl cacheControl2 = this.cacheResponse.cacheControl();
            if (!cacheControl2.mustRevalidate() && cacheControl.maxStaleSeconds() != -1) {
                millis2 = TimeUnit.SECONDS.toMillis(cacheControl.maxStaleSeconds());
            }
            if (!cacheControl2.noCache() && cacheResponseAge + millis < n + millis2) {
                final Response.Builder builder = this.cacheResponse.newBuilder();
                if (cacheResponseAge + millis >= n) {
                    builder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                }
                if (cacheResponseAge > 86400000L && this.isFreshnessLifetimeHeuristic()) {
                    builder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                }
                return new CacheStrategy(null, builder.build(), null);
            }
            final Request.Builder builder2 = this.request.newBuilder();
            if (this.etag != null) {
                builder2.header("If-None-Match", this.etag);
            }
            else if (this.lastModified != null) {
                builder2.header("If-Modified-Since", this.lastModifiedString);
            }
            else if (this.servedDate != null) {
                builder2.header("If-Modified-Since", this.servedDateString);
            }
            final Request build = builder2.build();
            if (hasConditions(build)) {
                return new CacheStrategy(build, this.cacheResponse, null);
            }
            return new CacheStrategy(build, null, null);
        }
        
        private static boolean hasConditions(final Request request) {
            return request.header("If-Modified-Since") != null || request.header("If-None-Match") != null;
        }
        
        private boolean isFreshnessLifetimeHeuristic() {
            return this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }
        
        public CacheStrategy get() {
            CacheStrategy candidate = this.getCandidate();
            if (candidate.networkRequest != null && this.request.cacheControl().onlyIfCached()) {
                candidate = new CacheStrategy(null, null, null);
            }
            return candidate;
        }
    }
}
