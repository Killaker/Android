package com.google.android.gms.tagmanager;

import java.util.*;
import com.google.android.gms.internal.*;
import java.util.regex.*;

class zzce extends zzak
{
    private static final String ID;
    private static final String zzbka;
    private static final String zzbkb;
    private static final String zzbkc;
    private static final String zzbkd;
    
    static {
        ID = zzad.zzcf.toString();
        zzbka = zzae.zzdV.toString();
        zzbkb = zzae.zzdW.toString();
        zzbkc = zzae.zzfO.toString();
        zzbkd = zzae.zzfI.toString();
    }
    
    public zzce() {
        super(zzce.ID, new String[] { zzce.zzbka, zzce.zzbkb });
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zzce.zzbka);
        final zzag.zza zza2 = map.get(zzce.zzbkb);
        if (zza == null || zza == zzdf.zzHF() || zza2 == null || zza2 == zzdf.zzHF()) {
            return zzdf.zzHF();
        }
        int n = 64;
        if (zzdf.zzk(map.get(zzce.zzbkc))) {
            n = 66;
        }
        final zzag.zza zza3 = map.get(zzce.zzbkd);
        int intValue;
        if (zza3 != null) {
            final Long zzi = zzdf.zzi(zza3);
            if (zzi == zzdf.zzHA()) {
                return zzdf.zzHF();
            }
            intValue = (int)(Object)zzi;
            if (intValue < 0) {
                return zzdf.zzHF();
            }
        }
        else {
            intValue = 1;
        }
        try {
            final Matcher matcher = Pattern.compile(zzdf.zzg(zza2), n).matcher(zzdf.zzg(zza));
            final boolean find = matcher.find();
            Object group = null;
            if (find) {
                final int groupCount = matcher.groupCount();
                group = null;
                if (groupCount >= intValue) {
                    group = matcher.group(intValue);
                }
            }
            if (group == null) {
                return zzdf.zzHF();
            }
            return zzdf.zzR(group);
        }
        catch (PatternSyntaxException ex) {
            return zzdf.zzHF();
        }
    }
}
