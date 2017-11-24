package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

class zzaj
{
    private static void zza(final DataLayer dataLayer, final zzaf.zzd zzd) {
        final zzag.zza[] zziD = zzd.zziD;
        for (int length = zziD.length, i = 0; i < length; ++i) {
            dataLayer.zzfX(zzdf.zzg(zziD[i]));
        }
    }
    
    public static void zza(final DataLayer dataLayer, final zzaf.zzi zzi) {
        if (zzi.zzjs == null) {
            zzbg.zzaK("supplemental missing experimentSupplemental");
            return;
        }
        zza(dataLayer, zzi.zzjs);
        zzb(dataLayer, zzi.zzjs);
        zzc(dataLayer, zzi.zzjs);
    }
    
    private static void zzb(final DataLayer dataLayer, final zzaf.zzd zzd) {
        final zzag.zza[] zziC = zzd.zziC;
        for (int length = zziC.length, i = 0; i < length; ++i) {
            final Map<String, Object> zzc = zzc(zziC[i]);
            if (zzc != null) {
                dataLayer.push(zzc);
            }
        }
    }
    
    private static Map<String, Object> zzc(final zzag.zza zza) {
        final Object zzl = zzdf.zzl(zza);
        if (!(zzl instanceof Map)) {
            zzbg.zzaK("value: " + zzl + " is not a map value, ignored.");
            return null;
        }
        return (Map<String, Object>)zzl;
    }
    
    private static void zzc(final DataLayer dataLayer, final zzaf.zzd zzd) {
        for (final zzaf.zzc zzc : zzd.zziE) {
            Label_0036: {
                if (zzc.key == null) {
                    zzbg.zzaK("GaExperimentRandom: No key");
                }
                else {
                    Object o = dataLayer.get(zzc.key);
                    Long value;
                    if (!(o instanceof Number)) {
                        value = null;
                    }
                    else {
                        value = ((Number)o).longValue();
                    }
                    final long zziy = zzc.zziy;
                    final long zziz = zzc.zziz;
                    if (!zzc.zziA || value == null || value < zziy || value > zziz) {
                        if (zziy > zziz) {
                            zzbg.zzaK("GaExperimentRandom: random range invalid");
                            break Label_0036;
                        }
                        o = Math.round(Math.random() * (zziz - zziy) + zziy);
                    }
                    dataLayer.zzfX(zzc.key);
                    final Map<String, Object> zzn = dataLayer.zzn(zzc.key, o);
                    if (zzc.zziB > 0L) {
                        if (!zzn.containsKey("gtm")) {
                            zzn.put("gtm", DataLayer.mapOf("lifetime", zzc.zziB));
                        }
                        else {
                            final Object value2 = zzn.get("gtm");
                            if (value2 instanceof Map) {
                                ((Map<String, Long>)value2).put("lifetime", zzc.zziB);
                            }
                            else {
                                zzbg.zzaK("GaExperimentRandom: gtm not a map");
                            }
                        }
                    }
                    dataLayer.push(zzn);
                }
            }
        }
    }
}
