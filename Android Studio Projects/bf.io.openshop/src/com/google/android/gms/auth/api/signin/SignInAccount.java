package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.internal.safeparcel.*;
import android.net.*;
import com.google.android.gms.common.internal.*;
import android.text.*;
import org.json.*;
import android.os.*;

public class SignInAccount implements SafeParcelable
{
    public static final Parcelable$Creator<SignInAccount> CREATOR;
    final int versionCode;
    private String zzWP;
    private String zzWQ;
    private Uri zzWR;
    private String zzWk;
    private String zzXj;
    private GoogleSignInAccount zzXm;
    private String zzXn;
    private String zzrG;
    
    static {
        CREATOR = (Parcelable$Creator)new zze();
    }
    
    SignInAccount(final int versionCode, final String zzXj, final String zzWk, final String s, final String zzWQ, final Uri zzWR, final GoogleSignInAccount zzXm, final String s2, final String zzXn) {
        this.versionCode = versionCode;
        this.zzWP = zzx.zzh(s, "Email cannot be empty.");
        this.zzWQ = zzWQ;
        this.zzWR = zzWR;
        this.zzXj = zzXj;
        this.zzWk = zzWk;
        this.zzXm = zzXm;
        this.zzrG = zzx.zzcM(s2);
        this.zzXn = zzXn;
    }
    
    public static SignInAccount zza(final zzd zzd, final String s, final String s2, final String s3, final Uri uri, final String s4, final String s5) {
        String zzmT = null;
        if (zzd != null) {
            zzmT = zzd.zzmT();
        }
        return new SignInAccount(2, zzmT, s, s2, s3, uri, null, s4, s5);
    }
    
    public static SignInAccount zzbM(final String s) throws JSONException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject(s);
        final String optString = jsonObject.optString("photoUrl", (String)null);
        Uri parse;
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            parse = Uri.parse(optString);
        }
        else {
            parse = null;
        }
        return zza(zzd.zzbL(jsonObject.optString("providerId", (String)null)), jsonObject.optString("tokenId", (String)null), jsonObject.getString("email"), jsonObject.optString("displayName", (String)null), parse, jsonObject.getString("localId"), jsonObject.optString("refreshToken")).zza(GoogleSignInAccount.zzbH(jsonObject.optString("googleSignInAccount")));
    }
    
    private JSONObject zzmJ() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", (Object)this.getEmail());
            if (!TextUtils.isEmpty((CharSequence)this.zzWQ)) {
                jsonObject.put("displayName", (Object)this.zzWQ);
            }
            if (this.zzWR != null) {
                jsonObject.put("photoUrl", (Object)this.zzWR.toString());
            }
            if (!TextUtils.isEmpty((CharSequence)this.zzXj)) {
                jsonObject.put("providerId", (Object)this.zzXj);
            }
            if (!TextUtils.isEmpty((CharSequence)this.zzWk)) {
                jsonObject.put("tokenId", (Object)this.zzWk);
            }
            if (this.zzXm != null) {
                jsonObject.put("googleSignInAccount", (Object)this.zzXm.zzmI());
            }
            if (!TextUtils.isEmpty((CharSequence)this.zzXn)) {
                jsonObject.put("refreshToken", (Object)this.zzXn);
            }
            jsonObject.put("localId", (Object)this.getUserId());
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getDisplayName() {
        return this.zzWQ;
    }
    
    public String getEmail() {
        return this.zzWP;
    }
    
    public String getIdToken() {
        return this.zzWk;
    }
    
    public Uri getPhotoUrl() {
        return this.zzWR;
    }
    
    public String getUserId() {
        return this.zzrG;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
    
    public SignInAccount zza(final GoogleSignInAccount zzXm) {
        this.zzXm = zzXm;
        return this;
    }
    
    public String zzmI() {
        return this.zzmJ().toString();
    }
    
    String zzmT() {
        return this.zzXj;
    }
    
    public zzd zzmU() {
        return zzd.zzbL(this.zzXj);
    }
    
    public GoogleSignInAccount zzmV() {
        return this.zzXm;
    }
    
    public String zzmW() {
        return this.zzXn;
    }
}
