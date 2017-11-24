package com.google.android.gms.common;

import com.google.android.gms.common.internal.*;
import java.util.*;
import java.io.*;

abstract static class zza
{
    private int zzafG;
    
    protected zza(final byte[] array) {
        zzx.zzb(array.length == 25, (Object)"cert hash data has incorrect length");
        this.zzafG = Arrays.hashCode(array);
    }
    
    protected static byte[] zzcs(final String s) {
        try {
            return s.getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof zza && Arrays.equals(this.getBytes(), ((zza)o).getBytes());
    }
    
    abstract byte[] getBytes();
    
    @Override
    public int hashCode() {
        return this.zzafG;
    }
}
