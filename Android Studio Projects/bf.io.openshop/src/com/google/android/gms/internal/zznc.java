package com.google.android.gms.internal;

import java.util.*;

public class zznc
{
    public static void zza(final StringBuilder sb, final HashMap<String, String> hashMap) {
        sb.append("{");
        final Iterator<String> iterator = hashMap.keySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            final String s = iterator.next();
            int n2;
            if (n == 0) {
                sb.append(",");
                n2 = n;
            }
            else {
                n2 = 0;
            }
            final String s2 = hashMap.get(s);
            sb.append("\"").append(s).append("\":");
            if (s2 == null) {
                sb.append("null");
            }
            else {
                sb.append("\"").append(s2).append("\"");
            }
            n = n2;
        }
        sb.append("}");
    }
}
