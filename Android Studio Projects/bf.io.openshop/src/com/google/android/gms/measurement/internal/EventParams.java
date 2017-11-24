package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.os.*;

public class EventParams implements SafeParcelable, Iterable<String>
{
    public static final zzj CREATOR;
    public final int versionCode;
    private final Bundle zzaVS;
    
    static {
        CREATOR = new zzj();
    }
    
    EventParams(final int versionCode, final Bundle zzaVS) {
        this.versionCode = versionCode;
        this.zzaVS = zzaVS;
    }
    
    EventParams(final Bundle zzaVS) {
        zzx.zzz(zzaVS);
        this.zzaVS = zzaVS;
        this.versionCode = 1;
    }
    
    public int describeContents() {
        return 0;
    }
    
    Object get(final String s) {
        return this.zzaVS.get(s);
    }
    
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            Iterator<String> zzaVT = EventParams.this.zzaVS.keySet().iterator();
            
            @Override
            public boolean hasNext() {
                return this.zzaVT.hasNext();
            }
            
            @Override
            public String next() {
                return this.zzaVT.next();
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not supported");
            }
        };
    }
    
    public int size() {
        return this.zzaVS.size();
    }
    
    @Override
    public String toString() {
        return this.zzaVS.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzj.zza(this, parcel, n);
    }
    
    public Bundle zzCC() {
        return new Bundle(this.zzaVS);
    }
}
