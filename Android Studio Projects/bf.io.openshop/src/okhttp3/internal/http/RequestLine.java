package okhttp3.internal.http;

import java.net.*;
import okhttp3.*;

public final class RequestLine
{
    static String get(final Request request, final Proxy.Type type) {
        final StringBuilder sb = new StringBuilder();
        sb.append(request.method());
        sb.append(' ');
        if (includeAuthorityInRequestLine(request, type)) {
            sb.append(request.url());
        }
        else {
            sb.append(requestPath(request.url()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }
    
    private static boolean includeAuthorityInRequestLine(final Request request, final Proxy.Type type) {
        return !request.isHttps() && type == Proxy.Type.HTTP;
    }
    
    public static String requestPath(final HttpUrl httpUrl) {
        String s = httpUrl.encodedPath();
        final String encodedQuery = httpUrl.encodedQuery();
        if (encodedQuery != null) {
            s = s + '?' + encodedQuery;
        }
        return s;
    }
}
