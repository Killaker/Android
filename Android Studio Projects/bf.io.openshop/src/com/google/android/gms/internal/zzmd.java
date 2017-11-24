package com.google.android.gms.internal;

import android.support.v4.util.*;
import android.graphics.drawable.*;
import com.google.android.gms.common.internal.*;

public final class zzmd extends LruCache<zza, Drawable>
{
    public zzmd() {
        super(10);
    }
    
    public static final class zza
    {
        public final int zzakx;
        public final int zzaky;
        
        public zza(final int zzakx, final int zzaky) {
            this.zzakx = zzakx;
            this.zzaky = zzaky;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = true;
            if (!(o instanceof zza)) {
                b = false;
            }
            else if (this != o) {
                final zza zza = (zza)o;
                if (zza.zzakx != this.zzakx || zza.zzaky != this.zzaky) {
                    return false;
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return zzw.hashCode(this.zzakx, this.zzaky);
        }
    }
}
