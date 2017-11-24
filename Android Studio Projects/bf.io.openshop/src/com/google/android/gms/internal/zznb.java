package com.google.android.gms.internal;

import android.text.*;
import java.util.regex.*;
import org.json.*;
import java.util.*;

public final class zznb
{
    private static final Pattern zzaoi;
    private static final Pattern zzaoj;
    
    static {
        zzaoi = Pattern.compile("\\\\.");
        zzaoj = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }
    
    public static String zzcU(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Matcher matcher = zznb.zzaoj.matcher(s);
            StringBuffer sb = null;
            while (matcher.find()) {
                if (sb == null) {
                    sb = new StringBuffer();
                }
                switch (matcher.group().charAt(0)) {
                    default: {
                        continue;
                    }
                    case '\b': {
                        matcher.appendReplacement(sb, "\\\\b");
                        continue;
                    }
                    case '\"': {
                        matcher.appendReplacement(sb, "\\\\\\\"");
                        continue;
                    }
                    case '\\': {
                        matcher.appendReplacement(sb, "\\\\\\\\");
                        continue;
                    }
                    case '/': {
                        matcher.appendReplacement(sb, "\\\\/");
                        continue;
                    }
                    case '\f': {
                        matcher.appendReplacement(sb, "\\\\f");
                        continue;
                    }
                    case '\n': {
                        matcher.appendReplacement(sb, "\\\\n");
                        continue;
                    }
                    case '\r': {
                        matcher.appendReplacement(sb, "\\\\r");
                        continue;
                    }
                    case '\t': {
                        matcher.appendReplacement(sb, "\\\\t");
                        continue;
                    }
                }
            }
            if (sb != null) {
                matcher.appendTail(sb);
                return sb.toString();
            }
        }
        return s;
    }
    
    public static boolean zze(final Object o, final Object o2) {
        boolean b;
        if (o == null && o2 == null) {
            b = true;
        }
        else {
            b = false;
            if (o != null) {
                b = false;
                if (o2 != null) {
                    Label_0146: {
                        if (!(o instanceof JSONObject) || !(o2 instanceof JSONObject)) {
                            break Label_0146;
                        }
                        final JSONObject jsonObject = (JSONObject)o;
                        final JSONObject jsonObject2 = (JSONObject)o2;
                        final int length = jsonObject.length();
                        final int length2 = jsonObject2.length();
                        b = false;
                        if (length != length2) {
                            return b;
                        }
                        final Iterator keys = jsonObject.keys();
                        String s;
                        boolean has;
                        int n;
                        JSONArray jsonArray;
                        JSONArray jsonArray2;
                        int length3;
                        int length4;
                        boolean zze;
                        Block_14_Outer:Label_0196_Outer:
                        while (true) {
                            if (!keys.hasNext()) {
                                return true;
                            }
                            s = keys.next();
                            has = jsonObject2.has(s);
                            b = false;
                            if (!has) {
                                return b;
                            }
                            try {
                                if (!zze(jsonObject.get(s), jsonObject2.get(s))) {
                                    return false;
                                }
                                continue Block_14_Outer;
                                // iftrue(Label_0236:, n >= jsonArray.length())
                                // iftrue(Label_0238:, !o instanceof JSONArray || !o2 instanceof JSONArray)
                                // iftrue(Label_0010:, length3 != length4)
                            Label_0196:
                                while (true) {
                                Block_15:
                                    while (true) {
                                        n = 0;
                                        break Label_0196;
                                        break Block_15;
                                        jsonArray = (JSONArray)o;
                                        jsonArray2 = (JSONArray)o2;
                                        length3 = jsonArray.length();
                                        length4 = jsonArray2.length();
                                        b = false;
                                        continue Label_0196_Outer;
                                    }
                                    try {
                                        zze = zze(jsonArray.get(n), jsonArray2.get(n));
                                        b = false;
                                        if (zze) {
                                            ++n;
                                            continue Label_0196;
                                        }
                                        return b;
                                        Label_0238: {
                                            return o.equals(o2);
                                        }
                                        Label_0236:
                                        return true;
                                    }
                                    catch (JSONException ex) {
                                        return false;
                                    }
                                    break;
                                }
                            }
                            catch (JSONException ex2) {
                                return false;
                            }
                            break;
                        }
                    }
                }
            }
        }
        Label_0010: {
            return b;
        }
    }
}
