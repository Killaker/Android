package com.google.android.gms.measurement;

import com.google.android.gms.measurement.internal.*;
import com.google.android.gms.common.internal.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;

public class AppMeasurement
{
    private final zzw zzaTV;
    
    public AppMeasurement(final zzw zzaTV) {
        zzx.zzz(zzaTV);
        this.zzaTV = zzaTV;
    }
    
    public static AppMeasurement getInstance(final Context context) {
        return zzw.zzaT(context).zzCV();
    }
    
    public void setMeasurementEnabled(final boolean measurementEnabled) {
        this.zzaTV.zzCf().setMeasurementEnabled(measurementEnabled);
    }
    
    public void zzd(final String s, final String s2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzaTV.zzCf().zze(s, s2, bundle);
    }
    
    public interface zza
    {
        @WorkerThread
        void zza(final String p0, final String p1, final Bundle p2, final long p3);
    }
}
