package okhttp3.internal.http;

import okhttp3.*;
import okhttp3.internal.*;
import java.util.*;

public final class OkHeaders
{
    static final String PREFIX;
    public static final String RECEIVED_MILLIS;
    public static final String RESPONSE_SOURCE;
    public static final String SELECTED_PROTOCOL;
    public static final String SENT_MILLIS;
    
    static {
        PREFIX = Platform.get().getPrefix();
        SENT_MILLIS = OkHeaders.PREFIX + "-Sent-Millis";
        RECEIVED_MILLIS = OkHeaders.PREFIX + "-Received-Millis";
        SELECTED_PROTOCOL = OkHeaders.PREFIX + "-Selected-Protocol";
        RESPONSE_SOURCE = OkHeaders.PREFIX + "-Response-Source";
    }
    
    public static long contentLength(final Headers headers) {
        return stringToLong(headers.get("Content-Length"));
    }
    
    public static long contentLength(final Request request) {
        return contentLength(request.headers());
    }
    
    public static long contentLength(final Response response) {
        return contentLength(response.headers());
    }
    
    public static boolean hasVaryAll(final Headers headers) {
        return varyFields(headers).contains("*");
    }
    
    public static boolean hasVaryAll(final Response response) {
        return hasVaryAll(response.headers());
    }
    
    static boolean isEndToEnd(final String s) {
        return !"Connection".equalsIgnoreCase(s) && !"Keep-Alive".equalsIgnoreCase(s) && !"Proxy-Authenticate".equalsIgnoreCase(s) && !"Proxy-Authorization".equalsIgnoreCase(s) && !"TE".equalsIgnoreCase(s) && !"Trailers".equalsIgnoreCase(s) && !"Transfer-Encoding".equalsIgnoreCase(s) && !"Upgrade".equalsIgnoreCase(s);
    }
    
    public static List<Challenge> parseChallenges(final Headers headers, final String s) {
        final ArrayList<Challenge> list = new ArrayList<Challenge>();
        for (int i = 0; i < headers.size(); ++i) {
            if (s.equalsIgnoreCase(headers.name(i))) {
                final String value = headers.value(i);
                int j = 0;
                while (j < value.length()) {
                    final int n = j;
                    final int skipUntil = HeaderParser.skipUntil(value, j, " ");
                    final String trim = value.substring(n, skipUntil).trim();
                    final int skipWhitespace = HeaderParser.skipWhitespace(value, skipUntil);
                    if (!value.regionMatches(true, skipWhitespace, "realm=\"", 0, "realm=\"".length())) {
                        break;
                    }
                    final int n2 = skipWhitespace + "realm=\"".length();
                    final int skipUntil2 = HeaderParser.skipUntil(value, n2, "\"");
                    final String substring = value.substring(n2, skipUntil2);
                    j = HeaderParser.skipWhitespace(value, 1 + HeaderParser.skipUntil(value, skipUntil2 + 1, ","));
                    list.add(new Challenge(trim, substring));
                }
            }
        }
        return list;
    }
    
    private static long stringToLong(final String s) {
        if (s == null) {
            return -1L;
        }
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            return -1L;
        }
    }
    
    public static Set<String> varyFields(final Headers headers) {
        Set<String> emptySet = (Set<String>)Collections.emptySet();
        for (int i = 0; i < headers.size(); ++i) {
            if ("Vary".equalsIgnoreCase(headers.name(i))) {
                final String value = headers.value(i);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
                }
                final String[] split = value.split(",");
                for (int length = split.length, j = 0; j < length; ++j) {
                    emptySet.add(split[j].trim());
                }
            }
        }
        return emptySet;
    }
    
    private static Set<String> varyFields(final Response response) {
        return varyFields(response.headers());
    }
    
    public static Headers varyHeaders(final Headers headers, final Headers headers2) {
        final Set<String> varyFields = varyFields(headers2);
        if (varyFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        final Headers.Builder builder = new Headers.Builder();
        for (int i = 0; i < headers.size(); ++i) {
            final String name = headers.name(i);
            if (varyFields.contains(name)) {
                builder.add(name, headers.value(i));
            }
        }
        return builder.build();
    }
    
    public static Headers varyHeaders(final Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }
    
    public static boolean varyMatches(final Response response, final Headers headers, final Request request) {
        for (final String s : varyFields(response)) {
            if (!Util.equal(headers.values(s), request.headers(s))) {
                return false;
            }
        }
        return true;
    }
}
