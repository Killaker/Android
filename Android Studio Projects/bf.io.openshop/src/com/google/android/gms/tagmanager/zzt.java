package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

class zzt extends zzak
{
    private static final String ID;
    private static final String zzbhF;
    private static final String zzbip;
    private final zza zzbiq;
    
    static {
        ID = zzad.zzbJ.toString();
        zzbip = zzae.zzfH.toString();
        zzbhF = zzae.zzdI.toString();
    }
    
    public zzt(final zza zzbiq) {
        super(zzt.ID, new String[] { zzt.zzbip });
        this.zzbiq = zzbiq;
    }
    
    @Override
    public boolean zzFW() {
        return false;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final String zzg = zzdf.zzg(map.get(zzt.zzbip));
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final zzag.zza zza = map.get(zzt.zzbhF);
        if (zza != null) {
            final Object zzl = zzdf.zzl(zza);
            if (!(zzl instanceof Map)) {
                zzbg.zzaK("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzdf.zzHF();
            }
            for (final Map.Entry<Object, V> entry : ((Map<Object, V>)zzl).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzdf.zzR(this.zzbiq.zzc(zzg, hashMap));
        }
        catch (Exception ex) {
            zzbg.zzaK("Custom macro/tag " + zzg + " threw exception " + ex.getMessage());
            return zzdf.zzHF();
        }
    }
    
    public interface zza
    {
        Object zzc(final String p0, final Map<String, Object> p1);
    }
}
