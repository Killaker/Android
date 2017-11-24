package com.google.android.gms.common.internal;

import android.os.*;
import android.app.*;
import com.google.android.gms.common.*;

private abstract class zza extends zzc<Boolean>
{
    public final int statusCode;
    public final Bundle zzalK;
    
    protected zza(final int statusCode, final Bundle zzalK) {
        super(true);
        this.statusCode = statusCode;
        this.zzalK = zzalK;
    }
    
    protected void zzc(final Boolean b) {
        if (b == null) {
            zzj.zza(zzj.this, 1, null);
        }
        else {
            switch (this.statusCode) {
                default: {
                    zzj.zza(zzj.this, 1, null);
                    final Bundle zzalK = this.zzalK;
                    PendingIntent pendingIntent = null;
                    if (zzalK != null) {
                        pendingIntent = (PendingIntent)this.zzalK.getParcelable("pendingIntent");
                    }
                    this.zzj(new ConnectionResult(this.statusCode, pendingIntent));
                }
                case 0: {
                    if (!this.zzqL()) {
                        zzj.zza(zzj.this, 1, null);
                        this.zzj(new ConnectionResult(8, null));
                        return;
                    }
                    break;
                }
                case 10: {
                    zzj.zza(zzj.this, 1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                }
            }
        }
    }
    
    protected abstract void zzj(final ConnectionResult p0);
    
    protected abstract boolean zzqL();
    
    @Override
    protected void zzqM() {
    }
}
