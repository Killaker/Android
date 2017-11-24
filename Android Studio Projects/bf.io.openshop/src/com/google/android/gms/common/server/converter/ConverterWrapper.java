package com.google.android.gms.common.server.converter;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.server.response.*;
import android.os.*;

public class ConverterWrapper implements SafeParcelable
{
    public static final zza CREATOR;
    private final int mVersionCode;
    private final StringToIntConverter zzamF;
    
    static {
        CREATOR = new zza();
    }
    
    ConverterWrapper(final int mVersionCode, final StringToIntConverter zzamF) {
        this.mVersionCode = mVersionCode;
        this.zzamF = zzamF;
    }
    
    private ConverterWrapper(final StringToIntConverter zzamF) {
        this.mVersionCode = 1;
        this.zzamF = zzamF;
    }
    
    public static ConverterWrapper zza(final FastJsonResponse.zza<?, ?> zza) {
        if (zza instanceof StringToIntConverter) {
            return new ConverterWrapper((StringToIntConverter)zza);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }
    
    public int describeContents() {
        final zza creator = ConverterWrapper.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zza creator = ConverterWrapper.CREATOR;
        zza.zza(this, parcel, n);
    }
    
    StringToIntConverter zzrg() {
        return this.zzamF;
    }
    
    public FastJsonResponse.zza<?, ?> zzrh() {
        if (this.zzamF != null) {
            return this.zzamF;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
