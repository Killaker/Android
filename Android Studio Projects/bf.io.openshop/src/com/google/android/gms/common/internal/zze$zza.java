package com.google.android.gms.common.internal;

import java.util.*;

private static class zza extends zze
{
    List<zze> zzala;
    
    zza(final List<zze> zzala) {
        this.zzala = zzala;
    }
    
    @Override
    public zze zza(final zze zze) {
        final ArrayList<zze> list = new ArrayList<zze>(this.zzala);
        list.add(zzx.zzz(zze));
        return new zza(list);
    }
    
    @Override
    public boolean zzd(final char c) {
        final Iterator<zze> iterator = this.zzala.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().zzd(c)) {
                return true;
            }
        }
        return false;
    }
}
