package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T>
{
    private static final String[] zzajg;
    private final Parcelable$Creator<T> zzajh;
    
    static {
        zzajg = new String[] { "data" };
    }
    
    public zzd(final DataHolder dataHolder, final Parcelable$Creator<T> zzajh) {
        super(dataHolder);
        this.zzajh = zzajh;
    }
    
    public T zzbG(final int n) {
        final byte[] zzg = this.zzahi.zzg("data", n, this.zzahi.zzbH(n));
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)this.zzajh.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
}
