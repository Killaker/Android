package com.google.android.gms.common.images;

import android.net.*;
import com.google.android.gms.common.internal.*;

static final class zza
{
    public final Uri uri;
    
    public zza(final Uri uri) {
        this.uri = uri;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof zza && (this == o || zzw.equal(((zza)o).uri, this.uri));
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.uri);
    }
}
