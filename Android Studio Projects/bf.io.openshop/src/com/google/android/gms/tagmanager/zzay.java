package com.google.android.gms.tagmanager;

import java.io.*;
import com.google.android.gms.internal.*;
import java.util.*;

class zzay extends zzak
{
    private static final String ID;
    private static final String zzbiQ;
    private static final String zzbji;
    private static final String zzbjj;
    private static final String zzbjk;
    
    static {
        ID = zzad.zzcd.toString();
        zzbiQ = zzae.zzdV.toString();
        zzbji = zzae.zzfU.toString();
        zzbjj = zzae.zzfY.toString();
        zzbjk = zzae.zzfr.toString();
    }
    
    public zzay() {
        super(zzay.ID, new String[] { zzay.zzbiQ });
    }
    
    private String zza(final String s, final zza zza, final Set<Character> set) {
        switch (zzay$1.zzbjl[zza.ordinal()]) {
            default: {
                return s;
            }
            case 1: {
                try {
                    return zzdj.zzgA(s);
                }
                catch (UnsupportedEncodingException ex) {
                    zzbg.zzb("Joiner: unsupported encoding", ex);
                    return s;
                }
            }
            case 2: {
                final String replace = s.replace("\\", "\\\\");
                final Iterator<Character> iterator = set.iterator();
                String replace2 = replace;
                while (iterator.hasNext()) {
                    final String string = iterator.next().toString();
                    replace2 = replace2.replace(string, "\\" + string);
                }
                return replace2;
            }
        }
    }
    
    private void zza(final StringBuilder sb, final String s, final zza zza, final Set<Character> set) {
        sb.append(this.zza(s, zza, set));
    }
    
    private void zza(final Set<Character> set, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            set.add(s.charAt(i));
        }
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zzay.zzbiQ);
        if (zza == null) {
            return zzdf.zzHF();
        }
        final zzag.zza zza2 = map.get(zzay.zzbji);
        String zzg;
        if (zza2 != null) {
            zzg = zzdf.zzg(zza2);
        }
        else {
            zzg = "";
        }
        final zzag.zza zza3 = map.get(zzay.zzbjj);
        String zzg2;
        if (zza3 != null) {
            zzg2 = zzdf.zzg(zza3);
        }
        else {
            zzg2 = "=";
        }
        final zza zzbjm = zzay.zza.zzbjm;
        final zzag.zza zza4 = map.get(zzay.zzbjk);
        zza zza5;
        Set<Character> set;
        if (zza4 != null) {
            final String zzg3 = zzdf.zzg(zza4);
            if ("url".equals(zzg3)) {
                zza5 = zzay.zza.zzbjn;
                set = null;
            }
            else {
                if (!"backslash".equals(zzg3)) {
                    zzbg.e("Joiner: unsupported escape type: " + zzg3);
                    return zzdf.zzHF();
                }
                zza5 = zzay.zza.zzbjo;
                set = new HashSet<Character>();
                this.zza(set, zzg);
                this.zza(set, zzg2);
                set.remove('\\');
            }
        }
        else {
            zza5 = zzbjm;
            set = null;
        }
        final StringBuilder sb = new StringBuilder();
        switch (zza.type) {
            default: {
                this.zza(sb, zzdf.zzg(zza), zza5, set);
                break;
            }
            case 2: {
                int n = 1;
                final zzag.zza[] zzjy = zza.zzjy;
                for (int length = zzjy.length, i = 0; i < length; ++i, n = 0) {
                    final zzag.zza zza6 = zzjy[i];
                    if (n == 0) {
                        sb.append(zzg);
                    }
                    this.zza(sb, zzdf.zzg(zza6), zza5, set);
                }
                break;
            }
            case 3: {
                for (int j = 0; j < zza.zzjz.length; ++j) {
                    if (j > 0) {
                        sb.append(zzg);
                    }
                    final String zzg4 = zzdf.zzg(zza.zzjz[j]);
                    final String zzg5 = zzdf.zzg(zza.zzjA[j]);
                    this.zza(sb, zzg4, zza5, set);
                    sb.append(zzg2);
                    this.zza(sb, zzg5, zza5, set);
                }
                break;
            }
        }
        return zzdf.zzR(sb.toString());
    }
    
    private enum zza
    {
        zzbjm, 
        zzbjn, 
        zzbjo;
    }
}
