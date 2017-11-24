package com.google.android.gms.auth.api.signin;

import android.accounts.*;
import com.google.android.gms.common.api.*;
import android.support.annotation.*;
import com.google.android.gms.common.internal.*;
import java.util.*;

public static final class Builder
{
    private Account zzTI;
    private boolean zzXa;
    private boolean zzXb;
    private boolean zzXc;
    private String zzXd;
    private String zzXe;
    private Set<Scope> zzXf;
    
    public Builder() {
        this.zzXf = new HashSet<Scope>();
    }
    
    public Builder(@NonNull final GoogleSignInOptions googleSignInOptions) {
        this.zzXf = new HashSet<Scope>();
        zzx.zzz(googleSignInOptions);
        this.zzXf = new HashSet<Scope>(GoogleSignInOptions.zzb(googleSignInOptions));
        this.zzXb = GoogleSignInOptions.zzc(googleSignInOptions);
        this.zzXc = GoogleSignInOptions.zzd(googleSignInOptions);
        this.zzXa = GoogleSignInOptions.zze(googleSignInOptions);
        this.zzXd = GoogleSignInOptions.zzf(googleSignInOptions);
        this.zzTI = GoogleSignInOptions.zzg(googleSignInOptions);
        this.zzXe = GoogleSignInOptions.zzh(googleSignInOptions);
    }
    
    private String zzbK(final String s) {
        zzx.zzcM(s);
        zzx.zzb(this.zzXd == null || this.zzXd.equals(s), (Object)"two different server client ids provided");
        return s;
    }
    
    public GoogleSignInOptions build() {
        if (this.zzXa && (this.zzTI == null || !this.zzXf.isEmpty())) {
            this.requestId();
        }
        return new GoogleSignInOptions(this.zzXf, this.zzTI, this.zzXa, this.zzXb, this.zzXc, this.zzXd, this.zzXe, null);
    }
    
    public Builder requestEmail() {
        this.zzXf.add(GoogleSignInOptions.zzWX);
        return this;
    }
    
    public Builder requestId() {
        this.zzXf.add(GoogleSignInOptions.zzWY);
        return this;
    }
    
    public Builder requestIdToken(final String s) {
        this.zzXa = true;
        this.zzXd = this.zzbK(s);
        return this;
    }
    
    public Builder requestProfile() {
        this.zzXf.add(GoogleSignInOptions.zzWW);
        return this;
    }
    
    public Builder requestScopes(final Scope scope, final Scope... array) {
        this.zzXf.add(scope);
        this.zzXf.addAll(Arrays.asList(array));
        return this;
    }
    
    public Builder requestServerAuthCode(final String s) {
        return this.requestServerAuthCode(s, false);
    }
    
    public Builder requestServerAuthCode(final String s, final boolean zzXc) {
        this.zzXb = true;
        this.zzXd = this.zzbK(s);
        this.zzXc = zzXc;
        return this;
    }
    
    public Builder setAccountName(final String s) {
        this.zzTI = new Account(zzx.zzcM(s), "com.google");
        return this;
    }
    
    public Builder setHostedDomain(final String s) {
        this.zzXe = zzx.zzcM(s);
        return this;
    }
}
