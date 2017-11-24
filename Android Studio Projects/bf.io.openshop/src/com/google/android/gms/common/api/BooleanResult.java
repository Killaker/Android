package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.*;

public class BooleanResult implements Result
{
    private final Status zzUX;
    private final boolean zzagf;
    
    public BooleanResult(final Status status, final boolean zzagf) {
        this.zzUX = zzx.zzb(status, "Status must not be null");
        this.zzagf = zzagf;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof BooleanResult)) {
                return false;
            }
            final BooleanResult booleanResult = (BooleanResult)o;
            if (!this.zzUX.equals(booleanResult.zzUX) || this.zzagf != booleanResult.zzagf) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Status getStatus() {
        return this.zzUX;
    }
    
    public boolean getValue() {
        return this.zzagf;
    }
    
    @Override
    public final int hashCode() {
        final int n = 31 * (527 + this.zzUX.hashCode());
        int n2;
        if (this.zzagf) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        return n2 + n;
    }
}
