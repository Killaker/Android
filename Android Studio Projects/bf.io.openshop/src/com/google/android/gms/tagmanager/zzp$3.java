package com.google.android.gms.tagmanager;

class zzp$3 implements zza {
    final /* synthetic */ boolean zzbin;
    
    @Override
    public boolean zzb(final Container container) {
        if (this.zzbin) {
            if (43200000L + container.getLastRefreshTime() < zzp.zzc(zzp.this).currentTimeMillis()) {
                return false;
            }
        }
        else if (container.isDefault()) {
            return false;
        }
        return true;
    }
}