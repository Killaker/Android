package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

public class zzdf
{
    private static final Object zzblE;
    private static Long zzblF;
    private static Double zzblG;
    private static zzde zzblH;
    private static String zzblI;
    private static Boolean zzblJ;
    private static List<Object> zzblK;
    private static Map<Object, Object> zzblL;
    private static zzag.zza zzblM;
    
    static {
        zzblE = null;
        zzdf.zzblF = new Long(0L);
        zzdf.zzblG = new Double(0.0);
        zzdf.zzblH = zzde.zzam(0L);
        zzdf.zzblI = new String("");
        zzdf.zzblJ = new Boolean(false);
        zzdf.zzblK = new ArrayList<Object>(0);
        zzdf.zzblL = new HashMap<Object, Object>();
        zzdf.zzblM = zzR(zzdf.zzblI);
    }
    
    private static double getDouble(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).doubleValue();
        }
        zzbg.e("getDouble received non-Number");
        return 0.0;
    }
    
    public static Long zzHA() {
        return zzdf.zzblF;
    }
    
    public static Double zzHB() {
        return zzdf.zzblG;
    }
    
    public static Boolean zzHC() {
        return zzdf.zzblJ;
    }
    
    public static zzde zzHD() {
        return zzdf.zzblH;
    }
    
    public static String zzHE() {
        return zzdf.zzblI;
    }
    
    public static zzag.zza zzHF() {
        return zzdf.zzblM;
    }
    
    public static Object zzHz() {
        return zzdf.zzblE;
    }
    
    public static String zzM(final Object o) {
        if (o == null) {
            return zzdf.zzblI;
        }
        return o.toString();
    }
    
    public static zzde zzN(final Object o) {
        if (o instanceof zzde) {
            return (zzde)o;
        }
        if (zzT(o)) {
            return zzde.zzam(zzU(o));
        }
        if (zzS(o)) {
            return zzde.zza(Double.valueOf(getDouble(o)));
        }
        return zzgu(zzM(o));
    }
    
    public static Long zzO(final Object o) {
        if (zzT(o)) {
            return zzU(o);
        }
        return zzgv(zzM(o));
    }
    
    public static Double zzP(final Object o) {
        if (zzS(o)) {
            return getDouble(o);
        }
        return zzgw(zzM(o));
    }
    
    public static Boolean zzQ(final Object o) {
        if (o instanceof Boolean) {
            return (Boolean)o;
        }
        return zzgx(zzM(o));
    }
    
    public static zzag.zza zzR(final Object o) {
        boolean zzjH = false;
        final zzag.zza zza = new zzag.zza();
        if (o instanceof zzag.zza) {
            return (zzag.zza)o;
        }
        if (o instanceof String) {
            zza.type = 1;
            zza.zzjx = (String)o;
        }
        else if (o instanceof List) {
            zza.type = 2;
            final List list = (List)o;
            final ArrayList list2 = new ArrayList<zzag.zza>(list.size());
            final Iterator<Object> iterator = list.iterator();
            boolean b = false;
            while (iterator.hasNext()) {
                final zzag.zza zzR = zzR(iterator.next());
                if (zzR == zzdf.zzblM) {
                    return zzdf.zzblM;
                }
                final boolean b2 = b || zzR.zzjH;
                list2.add(zzR);
                b = b2;
            }
            zza.zzjy = list2.toArray(new zzag.zza[0]);
            zzjH = b;
        }
        else if (o instanceof Map) {
            zza.type = 3;
            final Set entrySet = ((Map)o).entrySet();
            final ArrayList list3 = new ArrayList<zzag.zza>(entrySet.size());
            final ArrayList list4 = new ArrayList<zzag.zza>(entrySet.size());
            final Iterator<Map.Entry<Object, V>> iterator2 = entrySet.iterator();
            boolean b3 = false;
            while (iterator2.hasNext()) {
                final Map.Entry<Object, V> entry = iterator2.next();
                final zzag.zza zzR2 = zzR(entry.getKey());
                final zzag.zza zzR3 = zzR(entry.getValue());
                if (zzR2 == zzdf.zzblM || zzR3 == zzdf.zzblM) {
                    return zzdf.zzblM;
                }
                final boolean b4 = b3 || zzR2.zzjH || zzR3.zzjH;
                list3.add(zzR2);
                list4.add(zzR3);
                b3 = b4;
            }
            zza.zzjz = list3.toArray(new zzag.zza[0]);
            zza.zzjA = list4.toArray(new zzag.zza[0]);
            zzjH = b3;
        }
        else if (zzS(o)) {
            zza.type = 1;
            zza.zzjx = o.toString();
            zzjH = false;
        }
        else if (zzT(o)) {
            zza.type = 6;
            zza.zzjD = zzU(o);
            zzjH = false;
        }
        else {
            if (!(o instanceof Boolean)) {
                final StringBuilder append = new StringBuilder().append("Converting to Value from unknown object type: ");
                String string;
                if (o == null) {
                    string = "null";
                }
                else {
                    string = o.getClass().toString();
                }
                zzbg.e(append.append(string).toString());
                return zzdf.zzblM;
            }
            zza.type = 8;
            zza.zzjE = (boolean)o;
            zzjH = false;
        }
        zza.zzjH = zzjH;
        return zza;
    }
    
    private static boolean zzS(final Object o) {
        return o instanceof Double || o instanceof Float || (o instanceof zzde && ((zzde)o).zzHu());
    }
    
    private static boolean zzT(final Object o) {
        return o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long || (o instanceof zzde && ((zzde)o).zzHv());
    }
    
    private static long zzU(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).longValue();
        }
        zzbg.e("getInt64 received non-Number");
        return 0L;
    }
    
    public static String zzg(final zzag.zza zza) {
        return zzM(zzl(zza));
    }
    
    public static zzag.zza zzgt(final String zzjC) {
        final zzag.zza zza = new zzag.zza();
        zza.type = 5;
        zza.zzjC = zzjC;
        return zza;
    }
    
    private static zzde zzgu(final String s) {
        try {
            return zzde.zzgs(s);
        }
        catch (NumberFormatException ex) {
            zzbg.e("Failed to convert '" + s + "' to a number.");
            return zzdf.zzblH;
        }
    }
    
    private static Long zzgv(final String s) {
        final zzde zzgu = zzgu(s);
        if (zzgu == zzdf.zzblH) {
            return zzdf.zzblF;
        }
        return zzgu.longValue();
    }
    
    private static Double zzgw(final String s) {
        final zzde zzgu = zzgu(s);
        if (zzgu == zzdf.zzblH) {
            return zzdf.zzblG;
        }
        return zzgu.doubleValue();
    }
    
    private static Boolean zzgx(final String s) {
        if ("true".equalsIgnoreCase(s)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(s)) {
            return Boolean.FALSE;
        }
        return zzdf.zzblJ;
    }
    
    public static zzde zzh(final zzag.zza zza) {
        return zzN(zzl(zza));
    }
    
    public static Long zzi(final zzag.zza zza) {
        return zzO(zzl(zza));
    }
    
    public static Double zzj(final zzag.zza zza) {
        return zzP(zzl(zza));
    }
    
    public static Boolean zzk(final zzag.zza zza) {
        return zzQ(zzl(zza));
    }
    
    public static Object zzl(final zzag.zza zza) {
        int i = 0;
        if (zza == null) {
            return zzdf.zzblE;
        }
        switch (zza.type) {
            default: {
                zzbg.e("Failed to convert a value of type: " + zza.type);
                return zzdf.zzblE;
            }
            case 1: {
                return zza.zzjx;
            }
            case 2: {
                final ArrayList<Object> list = new ArrayList<Object>(zza.zzjy.length);
                for (zzag.zza[] zzjy = zza.zzjy; i < zzjy.length; ++i) {
                    final Object zzl = zzl(zzjy[i]);
                    if (zzl == zzdf.zzblE) {
                        return zzdf.zzblE;
                    }
                    list.add(zzl);
                }
                return list;
            }
            case 3: {
                if (zza.zzjz.length != zza.zzjA.length) {
                    zzbg.e("Converting an invalid value to object: " + zza.toString());
                    return zzdf.zzblE;
                }
                final HashMap<Object, Object> hashMap = new HashMap<Object, Object>(zza.zzjA.length);
                while (i < zza.zzjz.length) {
                    final Object zzl2 = zzl(zza.zzjz[i]);
                    final Object zzl3 = zzl(zza.zzjA[i]);
                    if (zzl2 == zzdf.zzblE || zzl3 == zzdf.zzblE) {
                        return zzdf.zzblE;
                    }
                    hashMap.put(zzl2, zzl3);
                    ++i;
                }
                return hashMap;
            }
            case 4: {
                zzbg.e("Trying to convert a macro reference to object");
                return zzdf.zzblE;
            }
            case 5: {
                zzbg.e("Trying to convert a function id to object");
                return zzdf.zzblE;
            }
            case 6: {
                return zza.zzjD;
            }
            case 7: {
                final StringBuffer sb = new StringBuffer();
                for (zzag.zza[] zzjF = zza.zzjF; i < zzjF.length; ++i) {
                    final String zzg = zzg(zzjF[i]);
                    if (zzg == zzdf.zzblI) {
                        return zzdf.zzblE;
                    }
                    sb.append(zzg);
                }
                return sb.toString();
            }
            case 8: {
                return zza.zzjE;
            }
        }
    }
}
