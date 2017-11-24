package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.io.*;
import java.net.*;

class zzdj
{
    private static zzbw<zzag.zza> zza(final zzbw<zzag.zza> zzbw) {
        try {
            return new zzbw<zzag.zza>(zzdf.zzR(zzgA(zzdf.zzg(zzbw.getObject()))), zzbw.zzGP());
        }
        catch (UnsupportedEncodingException ex) {
            zzbg.zzb("Escape URI: unsupported encoding", ex);
            return zzbw;
        }
    }
    
    private static zzbw<zzag.zza> zza(final zzbw<zzag.zza> zzbw, final int n) {
        if (!zzn(zzbw.getObject())) {
            zzbg.e("Escaping can only be applied to strings.");
            return zzbw;
        }
        switch (n) {
            default: {
                zzbg.e("Unsupported Value Escaping: " + n);
                return zzbw;
            }
            case 12: {
                return zza(zzbw);
            }
        }
    }
    
    static zzbw<zzag.zza> zza(zzbw<zzag.zza> zza, final int... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            zza = zza(zza, array[i]);
        }
        return zza;
    }
    
    static String zzgA(final String s) throws UnsupportedEncodingException {
        return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
    }
    
    private static boolean zzn(final zzag.zza zza) {
        return zzdf.zzl(zza) instanceof String;
    }
}
