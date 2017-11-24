package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public final class Tile implements SafeParcelable
{
    public static final zzn CREATOR;
    public final byte[] data;
    public final int height;
    private final int mVersionCode;
    public final int width;
    
    static {
        CREATOR = new zzn();
    }
    
    Tile(final int mVersionCode, final int width, final int height, final byte[] data) {
        this.mVersionCode = mVersionCode;
        this.width = width;
        this.height = height;
        this.data = data;
    }
    
    public Tile(final int n, final int n2, final byte[] array) {
        this(1, n, n2, array);
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzn.zza(this, parcel, n);
    }
}
