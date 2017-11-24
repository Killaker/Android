package com.android.volley.toolbox;

import com.android.volley.*;
import java.util.*;
import org.apache.http.impl.cookie.*;

public class HttpHeaderParser
{
    public static Cache.Entry parseCacheHeaders(final NetworkResponse networkResponse) {
        final long currentTimeMillis = System.currentTimeMillis();
        final Map<String, String> headers = networkResponse.headers;
        long dateAsEpoch = 0L;
        long dateAsEpoch2 = 0L;
        long dateAsEpoch3 = 0L;
        long softTtl = 0L;
        long ttl = 0L;
        long long1 = 0L;
        long long2 = 0L;
        final String s = headers.get("Date");
        if (s != null) {
            dateAsEpoch = parseDateAsEpoch(s);
        }
        final String s2 = headers.get("Cache-Control");
        boolean b = false;
        boolean b2 = false;
        Label_0221: {
            if (s2 == null) {
                break Label_0221;
            }
            b = true;
            final String[] split = s2.split(",");
            int n = 0;
        Label_0160_Outer:
            while (true) {
                if (n >= split.length) {
                    break Label_0221;
                }
                final String trim = split[n].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                Label_0166: {
                    if (!trim.startsWith("max-age=")) {
                        break Label_0166;
                    }
                Block_13_Outer:
                    while (true) {
                        try {
                            long1 = Long.parseLong(trim.substring(8));
                            ++n;
                            continue Label_0160_Outer;
                            // iftrue(Label_0195:, !trim.startsWith("stale-while-revalidate="))
                            try {
                                long2 = Long.parseLong(trim.substring(23));
                                continue Block_13_Outer;
                                Label_0370: {
                                    ttl = softTtl + 1000L * long2;
                                }
                                // iftrue(Label_0246:, s3 == null)
                                // iftrue(Label_0160:, !trim.equals((Object)"must-revalidate") && !trim.equals((Object)"proxy-revalidate"))
                                // iftrue(Label_0384:, !b)
                                // iftrue(Label_0271:, s4 == null)
                                // iftrue(Label_0370:, !b2)
                                while (true) {
                                    Block_12: {
                                        Label_0215: {
                                            while (true) {
                                                Label_0246: {
                                                    String etag = null;
                                                    Label_0308: {
                                                        break Label_0308;
                                                        final String s3 = headers.get("Expires");
                                                        dateAsEpoch3 = parseDateAsEpoch(s3);
                                                        break Label_0246;
                                                        Label_0195:
                                                        break Label_0215;
                                                        while (true) {
                                                            etag = headers.get("ETag");
                                                            break Block_12;
                                                            ttl = softTtl;
                                                            break Label_0308;
                                                            final String s4;
                                                            dateAsEpoch2 = parseDateAsEpoch(s4);
                                                            continue Block_13_Outer;
                                                        }
                                                    }
                                                    final Cache.Entry entry = new Cache.Entry();
                                                    entry.data = networkResponse.data;
                                                    entry.etag = etag;
                                                    entry.softTtl = softTtl;
                                                    entry.ttl = ttl;
                                                    entry.serverDate = dateAsEpoch;
                                                    entry.lastModified = dateAsEpoch2;
                                                    entry.responseHeaders = headers;
                                                    return entry;
                                                }
                                                final String s4 = headers.get("Last-Modified");
                                                continue;
                                            }
                                        }
                                        b2 = true;
                                        continue Block_13_Outer;
                                    }
                                    softTtl = currentTimeMillis + 1000L * long1;
                                    continue;
                                }
                                Label_0384:
                                // iftrue(Label_0308:, dateAsEpoch <= 0L || dateAsEpoch3 < dateAsEpoch)
                                softTtl = (ttl = currentTimeMillis + (dateAsEpoch3 - dateAsEpoch));
                            }
                            catch (Exception ex) {}
                        }
                        catch (Exception ex2) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    public static String parseCharset(final Map<String, String> map) {
        return parseCharset(map, "ISO-8859-1");
    }
    
    public static String parseCharset(final Map<String, String> map, String s) {
        final String s2 = map.get("Content-Type");
        if (s2 != null) {
            final String[] split = s2.split(";");
            for (int i = 1; i < split.length; ++i) {
                final String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    s = split2[1];
                    break;
                }
            }
        }
        return s;
    }
    
    public static long parseDateAsEpoch(final String s) {
        try {
            return DateUtils.parseDate(s).getTime();
        }
        catch (DateParseException ex) {
            return 0L;
        }
    }
}
