package com.google.android.gms.tagmanager;

class zza implements zzcx.zza
{
    @Override
    public void zza(final zzaq zzaq) {
        zzby.zza(zzby.this, zzaq.zzGD());
    }
    
    @Override
    public void zzb(final zzaq zzaq) {
        zzby.zza(zzby.this, zzaq.zzGD());
        zzbg.v("Permanent failure dispatching hitId: " + zzaq.zzGD());
    }
    
    @Override
    public void zzc(final zzaq zzaq) {
        final long zzGE = zzaq.zzGE();
        if (zzGE == 0L) {
            zzby.zza(zzby.this, zzaq.zzGD(), zzby.zza(zzby.this).currentTimeMillis());
        }
        else if (zzGE + 14400000L < zzby.zza(zzby.this).currentTimeMillis()) {
            zzby.zza(zzby.this, zzaq.zzGD());
            zzbg.v("Giving up on failed hitId: " + zzaq.zzGD());
        }
    }
}
