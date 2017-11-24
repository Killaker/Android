package com.google.android.gms.tagmanager;

import java.util.*;

class DataLayer$2 implements zzc.zza {
    @Override
    public void zzB(final List<DataLayer.zza> list) {
        for (final DataLayer.zza zza : list) {
            DataLayer.zza(DataLayer.this, DataLayer.this.zzn(zza.zzvs, zza.zzNc));
        }
        DataLayer.zza(DataLayer.this).countDown();
    }
}