package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.content.*;
import android.util.*;
import com.google.android.gms.*;
import android.content.res.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class CameraPosition implements SafeParcelable
{
    public static final zza CREATOR;
    public final float bearing;
    private final int mVersionCode;
    public final LatLng target;
    public final float tilt;
    public final float zoom;
    
    static {
        CREATOR = new zza();
    }
    
    CameraPosition(final int mVersionCode, final LatLng target, final float zoom, final float n, float n2) {
        zzx.zzb(target, "null camera target");
        zzx.zzb(0.0f <= n && n <= 90.0f, "Tilt needs to be between 0 and 90 inclusive: %s", n);
        this.mVersionCode = mVersionCode;
        this.target = target;
        this.zoom = zoom;
        this.tilt = n + 0.0f;
        if (n2 <= 0.0) {
            n2 = 360.0f + n2 % 360.0f;
        }
        this.bearing = n2 % 360.0f;
    }
    
    public CameraPosition(final LatLng latLng, final float n, final float n2, final float n3) {
        this(1, latLng, n, n2, n3);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(final CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }
    
    public static CameraPosition createFromAttributes(final Context context, final AttributeSet set) {
        if (set == null) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R.styleable.MapAttrs);
        float float1;
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraTargetLat)) {
            float1 = obtainAttributes.getFloat(R.styleable.MapAttrs_cameraTargetLat, 0.0f);
        }
        else {
            float1 = 0.0f;
        }
        float float2;
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraTargetLng)) {
            float2 = obtainAttributes.getFloat(R.styleable.MapAttrs_cameraTargetLng, 0.0f);
        }
        else {
            float2 = 0.0f;
        }
        final LatLng latLng = new LatLng(float1, float2);
        final Builder builder = builder();
        builder.target(latLng);
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraZoom)) {
            builder.zoom(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraZoom, 0.0f));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraBearing)) {
            builder.bearing(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraBearing, 0.0f));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraTilt)) {
            builder.tilt(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraTilt, 0.0f));
        }
        return builder.build();
    }
    
    public static final CameraPosition fromLatLngZoom(final LatLng latLng, final float n) {
        return new CameraPosition(latLng, n, 0.0f, 0.0f);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof CameraPosition)) {
                return false;
            }
            final CameraPosition cameraPosition = (CameraPosition)o;
            if (!this.target.equals(cameraPosition.target) || Float.floatToIntBits(this.zoom) != Float.floatToIntBits(cameraPosition.zoom) || Float.floatToIntBits(this.tilt) != Float.floatToIntBits(cameraPosition.tilt) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(cameraPosition.bearing)) {
                return false;
            }
        }
        return true;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.target, this.zoom, this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return zzw.zzy(this).zzg("target", this.target).zzg("zoom", this.zoom).zzg("tilt", this.tilt).zzg("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public static final class Builder
    {
        private LatLng zzaSX;
        private float zzaSY;
        private float zzaSZ;
        private float zzaTa;
        
        public Builder() {
        }
        
        public Builder(final CameraPosition cameraPosition) {
            this.zzaSX = cameraPosition.target;
            this.zzaSY = cameraPosition.zoom;
            this.zzaSZ = cameraPosition.tilt;
            this.zzaTa = cameraPosition.bearing;
        }
        
        public Builder bearing(final float zzaTa) {
            this.zzaTa = zzaTa;
            return this;
        }
        
        public CameraPosition build() {
            return new CameraPosition(this.zzaSX, this.zzaSY, this.zzaSZ, this.zzaTa);
        }
        
        public Builder target(final LatLng zzaSX) {
            this.zzaSX = zzaSX;
            return this;
        }
        
        public Builder tilt(final float zzaSZ) {
            this.zzaSZ = zzaSZ;
            return this;
        }
        
        public Builder zoom(final float zzaSY) {
            this.zzaSY = zzaSY;
            return this;
        }
    }
}
