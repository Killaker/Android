package com.google.android.gms.maps.internal;

import android.os.*;

public final class zzac
{
    public static void zza(final Bundle bundle, final String s, final Parcelable parcelable) {
        bundle.setClassLoader(zzac.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(zzac.class.getClassLoader());
        bundle2.putParcelable(s, parcelable);
        bundle.putBundle("map_state", bundle2);
    }
}
