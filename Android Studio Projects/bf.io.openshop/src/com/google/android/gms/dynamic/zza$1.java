package com.google.android.gms.dynamic;

import android.os.*;
import java.util.*;

class zza$1 implements zzf<T> {
    @Override
    public void zza(final T t) {
        zza.zza(zza.this, t);
        final Iterator iterator = zza.zza(zza.this).iterator();
        while (iterator.hasNext()) {
            iterator.next().zzb(zza.zzb(zza.this));
        }
        zza.zza(zza.this).clear();
        zza.zza(zza.this, (Bundle)null);
    }
}