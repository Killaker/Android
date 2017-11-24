package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.internal.safeparcel.*;
import android.net.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import android.text.*;
import org.json.*;
import com.google.android.gms.auth.api.signin.internal.*;
import android.os.*;

public class EmailSignInOptions implements SafeParcelable
{
    public static final Parcelable$Creator<EmailSignInOptions> CREATOR;
    final int versionCode;
    private final Uri zzWL;
    private String zzWM;
    private Uri zzWN;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    EmailSignInOptions(final int versionCode, final Uri zzWL, final String zzWM, final Uri zzWN) {
        zzx.zzb(zzWL, "Server widget url cannot be null in order to use email/password sign in.");
        zzx.zzh(zzWL.toString(), "Server widget url cannot be null in order to use email/password sign in.");
        zzx.zzb(Patterns.WEB_URL.matcher(zzWL.toString()).matches(), (Object)"Invalid server widget url");
        this.versionCode = versionCode;
        this.zzWL = zzWL;
        this.zzWM = zzWM;
        this.zzWN = zzWN;
    }
    
    private JSONObject zzmJ() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("serverWidgetUrl", (Object)this.zzWL.toString());
            if (!TextUtils.isEmpty((CharSequence)this.zzWM)) {
                jsonObject.put("modeQueryName", (Object)this.zzWM);
            }
            if (this.zzWN != null) {
                jsonObject.put("tosUrl", (Object)this.zzWN.toString());
            }
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null) {
            try {
                final EmailSignInOptions emailSignInOptions = (EmailSignInOptions)o;
                if (!this.zzWL.equals((Object)emailSignInOptions.zzmF())) {
                    return false;
                }
                if (this.zzWN == null) {
                    if (emailSignInOptions.zzmG() != null) {
                        return false;
                    }
                }
                else if (!this.zzWN.equals((Object)emailSignInOptions.zzmG())) {
                    return false;
                }
                if (TextUtils.isEmpty((CharSequence)this.zzWM)) {
                    if (!TextUtils.isEmpty((CharSequence)emailSignInOptions.zzmH())) {
                        return false;
                    }
                }
                else if (!this.zzWM.equals(emailSignInOptions.zzmH())) {
                    return false;
                }
            }
            catch (ClassCastException ex) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return new zze().zzp(this.zzWL).zzp(this.zzWN).zzp(this.zzWM).zzne();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public Uri zzmF() {
        return this.zzWL;
    }
    
    public Uri zzmG() {
        return this.zzWN;
    }
    
    public String zzmH() {
        return this.zzWM;
    }
    
    public String zzmI() {
        return this.zzmJ().toString();
    }
}
