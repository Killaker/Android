package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.internal.*;
import android.text.*;
import org.json.*;
import android.os.*;

public final class SignInConfiguration implements SafeParcelable
{
    public static final Parcelable$Creator<SignInConfiguration> CREATOR;
    final int versionCode;
    private final String zzXL;
    private EmailSignInOptions zzXM;
    private GoogleSignInOptions zzXN;
    private String zzXO;
    private String zzXd;
    
    static {
        CREATOR = (Parcelable$Creator)new zzp();
    }
    
    SignInConfiguration(final int versionCode, final String s, final String zzXd, final EmailSignInOptions zzXM, final GoogleSignInOptions zzXN, final String zzXO) {
        this.versionCode = versionCode;
        this.zzXL = zzx.zzcM(s);
        this.zzXd = zzXd;
        this.zzXM = zzXM;
        this.zzXN = zzXN;
        this.zzXO = zzXO;
    }
    
    public SignInConfiguration(final String s) {
        this(2, s, null, null, null, null);
    }
    
    private JSONObject zzmJ() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("consumerPackageName", (Object)this.zzXL);
            if (!TextUtils.isEmpty((CharSequence)this.zzXd)) {
                jsonObject.put("serverClientId", (Object)this.zzXd);
            }
            if (this.zzXM != null) {
                jsonObject.put("emailSignInOptions", (Object)this.zzXM.zzmI());
            }
            if (this.zzXN != null) {
                jsonObject.put("googleSignInOptions", (Object)this.zzXN.zzmI());
            }
            if (!TextUtils.isEmpty((CharSequence)this.zzXO)) {
                jsonObject.put("apiKey", (Object)this.zzXO);
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
                final SignInConfiguration signInConfiguration = (SignInConfiguration)o;
                if (!this.zzXL.equals(signInConfiguration.zznk())) {
                    return false;
                }
                if (TextUtils.isEmpty((CharSequence)this.zzXd)) {
                    if (!TextUtils.isEmpty((CharSequence)signInConfiguration.zzmR())) {
                        return false;
                    }
                }
                else if (!this.zzXd.equals(signInConfiguration.zzmR())) {
                    return false;
                }
                if (TextUtils.isEmpty((CharSequence)this.zzXO)) {
                    if (!TextUtils.isEmpty((CharSequence)signInConfiguration.zznn())) {
                        return false;
                    }
                }
                else if (!this.zzXO.equals(signInConfiguration.zznn())) {
                    return false;
                }
                if (this.zzXM == null) {
                    if (signInConfiguration.zznl() != null) {
                        return false;
                    }
                }
                else if (!this.zzXM.equals(signInConfiguration.zznl())) {
                    return false;
                }
                if (this.zzXN == null) {
                    if (signInConfiguration.zznm() != null) {
                        return false;
                    }
                }
                else if (!this.zzXN.equals(signInConfiguration.zznm())) {
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
        return new zze().zzp(this.zzXL).zzp(this.zzXd).zzp(this.zzXO).zzp(this.zzXM).zzp(this.zzXN).zzne();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzp.zza(this, parcel, n);
    }
    
    public SignInConfiguration zzj(final GoogleSignInOptions googleSignInOptions) {
        this.zzXN = zzx.zzb(googleSignInOptions, "GoogleSignInOptions cannot be null.");
        return this;
    }
    
    public String zzmI() {
        return this.zzmJ().toString();
    }
    
    public String zzmR() {
        return this.zzXd;
    }
    
    public String zznk() {
        return this.zzXL;
    }
    
    public EmailSignInOptions zznl() {
        return this.zzXM;
    }
    
    public GoogleSignInOptions zznm() {
        return this.zzXN;
    }
    
    public String zznn() {
        return this.zzXO;
    }
}
