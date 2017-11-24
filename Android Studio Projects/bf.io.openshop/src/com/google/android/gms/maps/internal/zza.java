package com.google.android.gms.maps.internal;

public final class zza
{
    public static Boolean zza(final byte b) {
        switch (b) {
            default: {
                return null;
            }
            case 1: {
                return Boolean.TRUE;
            }
            case 0: {
                return Boolean.FALSE;
            }
        }
    }
    
    public static byte zze(final Boolean b) {
        if (b == null) {
            return -1;
        }
        if (b) {
            return 1;
        }
        return 0;
    }
}
