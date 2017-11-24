package com.google.android.gms.measurement;

import android.text.*;
import java.util.*;

public abstract class zze<T extends zze>
{
    public static String zzF(final Object o) {
        return zza(o, 0);
    }
    
    public static String zzO(final Map map) {
        return zza(map, 1);
    }
    
    private static String zza(final Object o, final int n) {
        if (n > 10) {
            return "ERROR: Recursive toString calls";
        }
        if (o == null) {
            return "";
        }
        if (o instanceof String) {
            if (TextUtils.isEmpty((CharSequence)o)) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Integer) {
            if ((int)o == 0) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Long) {
            if ((long)o == 0L) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Double) {
            if ((double)o == 0.0) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Boolean) {
            if (!(boolean)o) {
                return "";
            }
            return o.toString();
        }
        else {
            if (o instanceof List) {
                final StringBuffer sb = new StringBuffer();
                if (n > 0) {
                    sb.append("[");
                }
                final List list = (List)o;
                final int length = sb.length();
                for (final Object next : list) {
                    if (sb.length() > length) {
                        sb.append(", ");
                    }
                    sb.append(zza(next, n + 1));
                }
                if (n > 0) {
                    sb.append("]");
                }
                return sb.toString();
            }
            if (o instanceof Map) {
                final StringBuffer sb2 = new StringBuffer();
                final Iterator<Map.Entry<Object, Object>> iterator2 = new TreeMap<Object, Object>((Map<?, ?>)o).entrySet().iterator();
                int length2 = 0;
                int n2 = 0;
                while (iterator2.hasNext()) {
                    final Map.Entry<Object, Object> entry = iterator2.next();
                    final String zza = zza(entry.getValue(), n + 1);
                    if (!TextUtils.isEmpty((CharSequence)zza)) {
                        if (n > 0 && n2 == 0) {
                            sb2.append("{");
                            n2 = 1;
                            length2 = sb2.length();
                        }
                        if (sb2.length() > length2) {
                            sb2.append(", ");
                        }
                        sb2.append(entry.getKey());
                        sb2.append('=');
                        sb2.append(zza);
                    }
                }
                if (n2 != 0) {
                    sb2.append("}");
                }
                return sb2.toString();
            }
            return o.toString();
        }
    }
    
    public abstract void zza(final T p0);
}
