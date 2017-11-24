package com.google.android.gms.auth.api.signin.internal;

import java.util.concurrent.locks.*;
import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.auth.api.signin.*;
import android.text.*;
import org.json.*;

public class zzq
{
    private static final Lock zzYa;
    private static zzq zzYb;
    private final Lock zzYc;
    private final SharedPreferences zzYd;
    
    static {
        zzYa = new ReentrantLock();
    }
    
    zzq(final Context context) {
        this.zzYc = new ReentrantLock();
        this.zzYd = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }
    
    public static zzq zzaf(final Context context) {
        zzx.zzz(context);
        zzq.zzYa.lock();
        try {
            if (zzq.zzYb == null) {
                zzq.zzYb = new zzq(context.getApplicationContext());
            }
            return zzq.zzYb;
        }
        finally {
            zzq.zzYa.unlock();
        }
    }
    
    private String zzs(final String s, final String s2) {
        return s + ":" + s2;
    }
    
    void zza(final GoogleSignInAccount googleSignInAccount, final GoogleSignInOptions googleSignInOptions) {
        zzx.zzz(googleSignInAccount);
        zzx.zzz(googleSignInOptions);
        final String zzmL = googleSignInAccount.zzmL();
        this.zzr(this.zzs("googleSignInAccount", zzmL), googleSignInAccount.zzmM());
        this.zzr(this.zzs("googleSignInOptions", zzmL), googleSignInOptions.zzmI());
    }
    
    void zza(final SignInAccount signInAccount, final SignInConfiguration signInConfiguration) {
        zzx.zzz(signInAccount);
        zzx.zzz(signInConfiguration);
        final String userId = signInAccount.getUserId();
        final SignInAccount zzbP = this.zzbP(userId);
        if (zzbP != null && zzbP.zzmV() != null) {
            this.zzbU(zzbP.zzmV().zzmL());
        }
        this.zzr(this.zzs("signInConfiguration", userId), signInConfiguration.zzmI());
        this.zzr(this.zzs("signInAccount", userId), signInAccount.zzmI());
        if (signInAccount.zzmV() != null) {
            this.zza(signInAccount.zzmV(), signInConfiguration.zznm());
        }
    }
    
    public void zzb(final GoogleSignInAccount googleSignInAccount, final GoogleSignInOptions googleSignInOptions) {
        zzx.zzz(googleSignInAccount);
        zzx.zzz(googleSignInOptions);
        this.zzr("defaultGoogleSignInAccount", googleSignInAccount.zzmL());
        this.zza(googleSignInAccount, googleSignInOptions);
    }
    
    public void zzb(final SignInAccount signInAccount, final SignInConfiguration signInConfiguration) {
        zzx.zzz(signInAccount);
        zzx.zzz(signInConfiguration);
        this.zznq();
        this.zzr("defaultSignInAccount", signInAccount.getUserId());
        if (signInAccount.zzmV() != null) {
            this.zzr("defaultGoogleSignInAccount", signInAccount.zzmV().zzmL());
        }
        this.zza(signInAccount, signInConfiguration);
    }
    
    SignInAccount zzbP(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final String zzbS = this.zzbS(this.zzs("signInAccount", s));
            if (!TextUtils.isEmpty((CharSequence)zzbS)) {
                try {
                    final SignInAccount zzbM = SignInAccount.zzbM(zzbS);
                    if (zzbM.zzmV() != null) {
                        final GoogleSignInAccount zzbQ = this.zzbQ(zzbM.zzmV().zzmL());
                        if (zzbQ != null) {
                            zzbM.zza(zzbQ);
                        }
                    }
                    return zzbM;
                }
                catch (JSONException ex) {
                    return null;
                }
            }
        }
        return null;
    }
    
    GoogleSignInAccount zzbQ(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final String zzbS = this.zzbS(this.zzs("googleSignInAccount", s));
            if (zzbS != null) {
                try {
                    return GoogleSignInAccount.zzbH(zzbS);
                }
                catch (JSONException ex) {
                    return null;
                }
            }
        }
        return null;
    }
    
    GoogleSignInOptions zzbR(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final String zzbS = this.zzbS(this.zzs("googleSignInOptions", s));
            if (zzbS != null) {
                try {
                    return GoogleSignInOptions.zzbJ(zzbS);
                }
                catch (JSONException ex) {
                    return null;
                }
            }
        }
        return null;
    }
    
    protected String zzbS(final String s) {
        this.zzYc.lock();
        try {
            return this.zzYd.getString(s, (String)null);
        }
        finally {
            this.zzYc.unlock();
        }
    }
    
    void zzbT(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final SignInAccount zzbP = this.zzbP(s);
            this.zzbV(this.zzs("signInAccount", s));
            this.zzbV(this.zzs("signInConfiguration", s));
            if (zzbP != null && zzbP.zzmV() != null) {
                this.zzbU(zzbP.zzmV().zzmL());
            }
        }
    }
    
    void zzbU(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.zzbV(this.zzs("googleSignInAccount", s));
        this.zzbV(this.zzs("googleSignInOptions", s));
    }
    
    protected void zzbV(final String s) {
        this.zzYc.lock();
        try {
            this.zzYd.edit().remove(s).apply();
        }
        finally {
            this.zzYc.unlock();
        }
    }
    
    public GoogleSignInAccount zzno() {
        return this.zzbQ(this.zzbS("defaultGoogleSignInAccount"));
    }
    
    public GoogleSignInOptions zznp() {
        return this.zzbR(this.zzbS("defaultGoogleSignInAccount"));
    }
    
    public void zznq() {
        final String zzbS = this.zzbS("defaultSignInAccount");
        this.zzbV("defaultSignInAccount");
        this.zznr();
        this.zzbT(zzbS);
    }
    
    public void zznr() {
        final String zzbS = this.zzbS("defaultGoogleSignInAccount");
        this.zzbV("defaultGoogleSignInAccount");
        this.zzbU(zzbS);
    }
    
    protected void zzr(final String s, final String s2) {
        this.zzYc.lock();
        try {
            this.zzYd.edit().putString(s, s2).apply();
        }
        finally {
            this.zzYc.unlock();
        }
    }
}
