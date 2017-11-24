package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import android.support.annotation.*;
import android.text.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class TokenData implements SafeParcelable
{
    public static final zze CREATOR;
    final int mVersionCode;
    private final String zzVo;
    private final Long zzVp;
    private final boolean zzVq;
    private final boolean zzVr;
    private final List<String> zzVs;
    
    static {
        CREATOR = new zze();
    }
    
    TokenData(final int mVersionCode, final String s, final Long zzVp, final boolean zzVq, final boolean zzVr, final List<String> zzVs) {
        this.mVersionCode = mVersionCode;
        this.zzVo = zzx.zzcM(s);
        this.zzVp = zzVp;
        this.zzVq = zzVq;
        this.zzVr = zzVr;
        this.zzVs = zzVs;
    }
    
    @Nullable
    public static TokenData zzc(final Bundle bundle, final String s) {
        bundle.setClassLoader(TokenData.class.getClassLoader());
        final Bundle bundle2 = bundle.getBundle(s);
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(TokenData.class.getClassLoader());
        return (TokenData)bundle2.getParcelable("TokenData");
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof TokenData) {
            final TokenData tokenData = (TokenData)o;
            if (TextUtils.equals((CharSequence)this.zzVo, (CharSequence)tokenData.zzVo) && zzw.equal(this.zzVp, tokenData.zzVp) && this.zzVq == tokenData.zzVq && this.zzVr == tokenData.zzVr && zzw.equal(this.zzVs, tokenData.zzVs)) {
                return true;
            }
        }
        return false;
    }
    
    public String getToken() {
        return this.zzVo;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzVo, this.zzVp, this.zzVq, this.zzVr, this.zzVs);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
    
    @Nullable
    public Long zzmn() {
        return this.zzVp;
    }
    
    public boolean zzmo() {
        return this.zzVq;
    }
    
    public boolean zzmp() {
        return this.zzVr;
    }
    
    @Nullable
    public List<String> zzmq() {
        return this.zzVs;
    }
}
