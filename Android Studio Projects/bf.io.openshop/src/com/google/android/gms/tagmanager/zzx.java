package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

class zzx extends zzdd
{
    private static final String ID;
    private static final String VALUE;
    private static final String zzbiL;
    private final DataLayer zzbhN;
    
    static {
        ID = zzad.zzcg.toString();
        VALUE = zzae.zzih.toString();
        zzbiL = zzae.zzes.toString();
    }
    
    public zzx(final DataLayer zzbhN) {
        super(zzx.ID, new String[] { zzx.VALUE });
        this.zzbhN = zzbhN;
    }
    
    private void zza(final zzag.zza zza) {
        if (zza != null && zza != zzdf.zzHz()) {
            final String zzg = zzdf.zzg(zza);
            if (zzg != zzdf.zzHE()) {
                this.zzbhN.zzfX(zzg);
            }
        }
    }
    
    private void zzb(final zzag.zza zza) {
        if (zza != null && zza != zzdf.zzHz()) {
            final Object zzl = zzdf.zzl(zza);
            if (zzl instanceof List) {
                for (final Map<String, Object> next : (List<Object>)zzl) {
                    if (next instanceof Map) {
                        this.zzbhN.push(next);
                    }
                }
            }
        }
    }
    
    @Override
    public void zzR(final Map<String, zzag.zza> map) {
        this.zzb(map.get(zzx.VALUE));
        this.zza(map.get(zzx.zzbiL));
    }
}
