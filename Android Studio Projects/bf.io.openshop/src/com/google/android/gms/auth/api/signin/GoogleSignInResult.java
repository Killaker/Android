package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.*;
import android.support.annotation.*;

public class GoogleSignInResult implements Result
{
    private Status zzUX;
    private GoogleSignInAccount zzXg;
    
    public GoogleSignInResult(@Nullable final GoogleSignInAccount zzXg, @NonNull final Status zzUX) {
        this.zzXg = zzXg;
        this.zzUX = zzUX;
    }
    
    @Nullable
    public GoogleSignInAccount getSignInAccount() {
        return this.zzXg;
    }
    
    @NonNull
    @Override
    public Status getStatus() {
        return this.zzUX;
    }
    
    public boolean isSuccess() {
        return this.zzUX.isSuccess();
    }
}
