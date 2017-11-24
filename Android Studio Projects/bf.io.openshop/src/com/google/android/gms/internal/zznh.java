package com.google.android.gms.internal;

import com.google.android.gms.common.api.*;
import com.google.android.gms.common.internal.*;
import java.util.*;

public final class zznh
{
    public static String[] zzb(final Scope[] array) {
        zzx.zzb(array, "scopes can't be null.");
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].zzpb();
        }
        return array2;
    }
    
    public static String[] zzc(final Set<Scope> set) {
        zzx.zzb(set, "scopes can't be null.");
        return zzb(set.toArray(new Scope[set.size()]));
    }
}
