package com.google.android.gms.common;

import java.util.*;

static class zzb extends zza
{
    private final byte[] zzafH;
    
    zzb(final byte[] zzafH) {
        super(Arrays.copyOfRange(zzafH, 0, 25));
        this.zzafH = zzafH;
    }
    
    @Override
    byte[] getBytes() {
        return this.zzafH;
    }
}
