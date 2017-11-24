package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import android.text.*;
import java.util.*;

public class zzab
{
    private final List<Command> zzSJ;
    private final long zzSK;
    private final long zzSL;
    private final int zzSM;
    private final boolean zzSN;
    private final String zzSO;
    private final Map<String, String> zzxA;
    
    public zzab(final zzc zzc, final Map<String, String> map, final long n, final boolean b) {
        this(zzc, map, n, b, 0L, 0, null);
    }
    
    public zzab(final zzc zzc, final Map<String, String> map, final long n, final boolean b, final long n2, final int n3) {
        this(zzc, map, n, b, n2, n3, null);
    }
    
    public zzab(final zzc zzc, final Map<String, String> map, final long zzSL, final boolean zzSN, final long zzSK, final int zzSM, final List<Command> list) {
        zzx.zzz(zzc);
        zzx.zzz(map);
        this.zzSL = zzSL;
        this.zzSN = zzSN;
        this.zzSK = zzSK;
        this.zzSM = zzSM;
        List<Command> empty_LIST;
        if (list != null) {
            empty_LIST = list;
        }
        else {
            empty_LIST = (List<Command>)Collections.EMPTY_LIST;
        }
        this.zzSJ = empty_LIST;
        this.zzSO = zzp(list);
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            if (zzk(entry.getKey())) {
                final String zza = zza(zzc, entry.getKey());
                if (zza == null) {
                    continue;
                }
                hashMap.put(zza, zzb(zzc, entry.getValue()));
            }
        }
        for (final Map.Entry<String, String> entry2 : map.entrySet()) {
            if (!zzk(entry2.getKey())) {
                final String zza2 = zza(zzc, entry2.getKey());
                if (zza2 == null) {
                    continue;
                }
                hashMap.put(zza2, zzb(zzc, entry2.getValue()));
            }
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzSO)) {
            zzam.zzc(hashMap, "_v", this.zzSO);
            if (this.zzSO.equals("ma4.0.0") || this.zzSO.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzxA = (Map<String, String>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public static zzab zza(final zzc zzc, final zzab zzab, final Map<String, String> map) {
        return new zzab(zzc, map, zzab.zzlr(), zzab.zzlt(), zzab.zzlq(), zzab.zzlp(), zzab.zzls());
    }
    
    private static String zza(final zzc zzc, final Object o) {
        String s;
        if (o == null) {
            s = null;
        }
        else {
            s = o.toString();
            if (s.startsWith("&")) {
                s = s.substring(1);
            }
            final int length = s.length();
            if (length > 256) {
                s = s.substring(0, 256);
                zzc.zzc("Hit param name is too long and will be trimmed", length, s);
            }
            if (TextUtils.isEmpty((CharSequence)s)) {
                return null;
            }
        }
        return s;
    }
    
    private static String zzb(final zzc zzc, final Object o) {
        String s;
        if (o == null) {
            s = "";
        }
        else {
            s = o.toString();
        }
        final int length = s.length();
        if (length > 8192) {
            s = s.substring(0, 8192);
            zzc.zzc("Hit param value is too long and will be trimmed", length, s);
        }
        return s;
    }
    
    private static boolean zzk(final Object o) {
        return o != null && o.toString().startsWith("&");
    }
    
    private String zzm(final String s, final String s2) {
        zzx.zzcM(s);
        zzx.zzb(!s.startsWith("&"), (Object)"Short param name required");
        final String s3 = this.zzxA.get(s);
        if (s3 != null) {
            return s3;
        }
        return s2;
    }
    
    private static String zzp(final List<Command> list) {
    Label_0047:
        while (true) {
            if (list != null) {
                for (final Command command : list) {
                    if ("appendVersion".equals(command.getId())) {
                        final String value = command.getValue();
                        break Label_0047;
                    }
                }
            }
            Label_0058: {
                break Label_0058;
                final String value;
                if (TextUtils.isEmpty((CharSequence)value)) {
                    return null;
                }
                return value;
            }
            final String value = null;
            continue Label_0047;
        }
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ht=").append(this.zzSL);
        if (this.zzSK != 0L) {
            sb.append(", dbId=").append(this.zzSK);
        }
        if (this.zzSM != 0L) {
            sb.append(", appUID=").append(this.zzSM);
        }
        final ArrayList<String> list = new ArrayList<String>(this.zzxA.keySet());
        Collections.sort((List<Comparable>)list);
        for (final String s : list) {
            sb.append(", ");
            sb.append(s);
            sb.append("=");
            sb.append(this.zzxA.get(s));
        }
        return sb.toString();
    }
    
    public int zzlp() {
        return this.zzSM;
    }
    
    public long zzlq() {
        return this.zzSK;
    }
    
    public long zzlr() {
        return this.zzSL;
    }
    
    public List<Command> zzls() {
        return this.zzSJ;
    }
    
    public boolean zzlt() {
        return this.zzSN;
    }
    
    public long zzlu() {
        return zzam.zzbt(this.zzm("_s", "0"));
    }
    
    public String zzlv() {
        return this.zzm("_m", "");
    }
    
    public Map<String, String> zzn() {
        return this.zzxA;
    }
}
