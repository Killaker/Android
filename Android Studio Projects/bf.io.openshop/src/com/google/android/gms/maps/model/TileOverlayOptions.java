package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.maps.model.internal.*;
import android.os.*;

public final class TileOverlayOptions implements SafeParcelable
{
    public static final zzo CREATOR;
    private final int mVersionCode;
    private zzi zzaTP;
    private TileProvider zzaTQ;
    private boolean zzaTR;
    private float zzaTh;
    private boolean zzaTi;
    
    static {
        CREATOR = new zzo();
    }
    
    public TileOverlayOptions() {
        this.zzaTi = true;
        this.zzaTR = true;
        this.mVersionCode = 1;
    }
    
    TileOverlayOptions(final int mVersionCode, final IBinder binder, final boolean zzaTi, final float zzaTh, final boolean zzaTR) {
        this.zzaTi = true;
        this.zzaTR = true;
        this.mVersionCode = mVersionCode;
        this.zzaTP = zzi.zza.zzdm(binder);
        TileProvider zzaTQ;
        if (this.zzaTP == null) {
            zzaTQ = null;
        }
        else {
            zzaTQ = new TileProvider() {
                private final zzi zzaTS = TileOverlayOptions.this.zzaTP;
                
                @Override
                public Tile getTile(final int n, final int n2, final int n3) {
                    try {
                        return this.zzaTS.getTile(n, n2, n3);
                    }
                    catch (RemoteException ex) {
                        return null;
                    }
                }
            };
        }
        this.zzaTQ = zzaTQ;
        this.zzaTi = zzaTi;
        this.zzaTh = zzaTh;
        this.zzaTR = zzaTR;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public TileOverlayOptions fadeIn(final boolean zzaTR) {
        this.zzaTR = zzaTR;
        return this;
    }
    
    public boolean getFadeIn() {
        return this.zzaTR;
    }
    
    public TileProvider getTileProvider() {
        return this.zzaTQ;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public float getZIndex() {
        return this.zzaTh;
    }
    
    public boolean isVisible() {
        return this.zzaTi;
    }
    
    public TileOverlayOptions tileProvider(final TileProvider zzaTQ) {
        this.zzaTQ = zzaTQ;
        zzi zzaTP;
        if (this.zzaTQ == null) {
            zzaTP = null;
        }
        else {
            zzaTP = new zzi.zza() {
                public Tile getTile(final int n, final int n2, final int n3) {
                    return zzaTQ.getTile(n, n2, n3);
                }
            };
        }
        this.zzaTP = zzaTP;
        return this;
    }
    
    public TileOverlayOptions visible(final boolean zzaTi) {
        this.zzaTi = zzaTi;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzo.zza(this, parcel, n);
    }
    
    public TileOverlayOptions zIndex(final float zzaTh) {
        this.zzaTh = zzaTh;
        return this;
    }
    
    IBinder zzAm() {
        return this.zzaTP.asBinder();
    }
}
