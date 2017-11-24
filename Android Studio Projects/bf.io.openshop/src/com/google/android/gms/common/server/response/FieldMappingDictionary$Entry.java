package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;
import java.util.*;

public static class Entry implements SafeParcelable
{
    public static final zzd CREATOR;
    final String className;
    final int versionCode;
    final ArrayList<FieldMapPair> zzamY;
    
    static {
        CREATOR = new zzd();
    }
    
    Entry(final int versionCode, final String className, final ArrayList<FieldMapPair> zzamY) {
        this.versionCode = versionCode;
        this.className = className;
        this.zzamY = zzamY;
    }
    
    Entry(final String className, final Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.versionCode = 1;
        this.className = className;
        this.zzamY = zzM(map);
    }
    
    private static ArrayList<FieldMapPair> zzM(final Map<String, FastJsonResponse.Field<?, ?>> map) {
        if (map == null) {
            return null;
        }
        final ArrayList<FieldMapPair> list = new ArrayList<FieldMapPair>();
        for (final String s : map.keySet()) {
            list.add(new FieldMapPair(s, map.get(s)));
        }
        return list;
    }
    
    public int describeContents() {
        final zzd creator = Entry.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzd creator = Entry.CREATOR;
        zzd.zza(this, parcel, n);
    }
    
    HashMap<String, FastJsonResponse.Field<?, ?>> zzrC() {
        final HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<String, FastJsonResponse.Field<?, ?>>();
        for (int size = this.zzamY.size(), i = 0; i < size; ++i) {
            final FieldMapPair fieldMapPair = this.zzamY.get(i);
            hashMap.put(fieldMapPair.key, fieldMapPair.zzamZ);
        }
        return hashMap;
    }
}
