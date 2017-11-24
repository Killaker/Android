package com.google.android.gms.tagmanager;

import java.util.*;

private static class zzb
{
    final byte[] zzbiK;
    final String zzvs;
    
    zzb(final String zzvs, final byte[] zzbiK) {
        this.zzvs = zzvs;
        this.zzbiK = zzbiK;
    }
    
    @Override
    public String toString() {
        return "KeyAndSerialized: key = " + this.zzvs + " serialized hash = " + Arrays.hashCode(this.zzbiK);
    }
}
