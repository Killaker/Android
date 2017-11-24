package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.safeparcel.*;
import android.net.*;
import org.json.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class WebImage implements SafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    private final int mVersionCode;
    private final Uri zzajZ;
    private final int zzoG;
    private final int zzoH;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    WebImage(final int mVersionCode, final Uri zzajZ, final int zzoG, final int zzoH) {
        this.mVersionCode = mVersionCode;
        this.zzajZ = zzajZ;
        this.zzoG = zzoG;
        this.zzoH = zzoH;
    }
    
    public WebImage(final Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }
    
    public WebImage(final Uri uri, final int n, final int n2) throws IllegalArgumentException {
        this(1, uri, n, n2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (n < 0 || n2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }
    
    public WebImage(final JSONObject jsonObject) throws IllegalArgumentException {
        this(zzj(jsonObject), jsonObject.optInt("width", 0), jsonObject.optInt("height", 0));
    }
    
    private static Uri zzj(final JSONObject jsonObject) {
        final boolean has = jsonObject.has("url");
        Uri parse = null;
        if (!has) {
            return parse;
        }
        try {
            parse = Uri.parse(jsonObject.getString("url"));
            return parse;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || !(o instanceof WebImage)) {
                return false;
            }
            final WebImage webImage = (WebImage)o;
            if (!zzw.equal(this.zzajZ, webImage.zzajZ) || this.zzoG != webImage.zzoG || this.zzoH != webImage.zzoH) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.zzoH;
    }
    
    public Uri getUrl() {
        return this.zzajZ;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public int getWidth() {
        return this.zzoG;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzajZ, this.zzoG, this.zzoH);
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", (Object)this.zzajZ.toString());
            jsonObject.put("width", this.zzoG);
            jsonObject.put("height", this.zzoH);
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    @Override
    public String toString() {
        return String.format("Image %dx%d %s", this.zzoG, this.zzoH, this.zzajZ.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
