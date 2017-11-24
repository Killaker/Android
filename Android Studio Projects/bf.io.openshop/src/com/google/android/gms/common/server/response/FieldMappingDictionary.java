package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.os.*;

public class FieldMappingDictionary implements SafeParcelable
{
    public static final zzc CREATOR;
    private final int mVersionCode;
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzamV;
    private final ArrayList<Entry> zzamW;
    private final String zzamX;
    
    static {
        CREATOR = new zzc();
    }
    
    FieldMappingDictionary(final int mVersionCode, final ArrayList<Entry> list, final String s) {
        this.mVersionCode = mVersionCode;
        this.zzamW = null;
        this.zzamV = zze(list);
        this.zzamX = zzx.zzz(s);
        this.zzry();
    }
    
    public FieldMappingDictionary(final Class<? extends FastJsonResponse> clazz) {
        this.mVersionCode = 1;
        this.zzamW = null;
        this.zzamV = new HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>>();
        this.zzamX = clazz.getCanonicalName();
    }
    
    private static HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zze(final ArrayList<Entry> list) {
        final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> hashMap = new HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final Entry entry = list.get(i);
            hashMap.put(entry.className, entry.zzrC());
        }
        return hashMap;
    }
    
    public int describeContents() {
        final zzc creator = FieldMappingDictionary.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.zzamV.keySet()) {
            sb.append(s).append(":\n");
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.zzamV.get(s);
            for (final String s2 : map.keySet()) {
                sb.append("  ").append(s2).append(": ");
                sb.append(map.get(s2));
            }
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzc creator = FieldMappingDictionary.CREATOR;
        zzc.zza(this, parcel, n);
    }
    
    public void zza(final Class<? extends FastJsonResponse> clazz, final Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.zzamV.put(clazz.getCanonicalName(), map);
    }
    
    public boolean zzb(final Class<? extends FastJsonResponse> clazz) {
        return this.zzamV.containsKey(clazz.getCanonicalName());
    }
    
    public Map<String, FastJsonResponse.Field<?, ?>> zzcR(final String s) {
        return this.zzamV.get(s);
    }
    
    ArrayList<Entry> zzrA() {
        final ArrayList<Entry> list = new ArrayList<Entry>();
        for (final String s : this.zzamV.keySet()) {
            list.add(new Entry(s, this.zzamV.get(s)));
        }
        return list;
    }
    
    public String zzrB() {
        return this.zzamX;
    }
    
    public void zzry() {
        final Iterator<String> iterator = this.zzamV.keySet().iterator();
        while (iterator.hasNext()) {
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.zzamV.get(iterator.next());
            final Iterator<String> iterator2 = map.keySet().iterator();
            while (iterator2.hasNext()) {
                ((FastJsonResponse.Field)map.get(iterator2.next())).zza(this);
            }
        }
    }
    
    public void zzrz() {
        for (final String s : this.zzamV.keySet()) {
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.zzamV.get(s);
            final HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<String, FastJsonResponse.Field<?, ?>>();
            for (final String s2 : map.keySet()) {
                hashMap.put(s2, map.get(s2).zzro());
            }
            this.zzamV.put(s, hashMap);
        }
    }
    
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
    
    public static class FieldMapPair implements SafeParcelable
    {
        public static final zzb CREATOR;
        final String key;
        final int versionCode;
        final FastJsonResponse.Field<?, ?> zzamZ;
        
        static {
            CREATOR = new zzb();
        }
        
        FieldMapPair(final int versionCode, final String key, final FastJsonResponse.Field<?, ?> zzamZ) {
            this.versionCode = versionCode;
            this.key = key;
            this.zzamZ = zzamZ;
        }
        
        FieldMapPair(final String key, final FastJsonResponse.Field<?, ?> zzamZ) {
            this.versionCode = 1;
            this.key = key;
            this.zzamZ = zzamZ;
        }
        
        public int describeContents() {
            final zzb creator = FieldMapPair.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final zzb creator = FieldMapPair.CREATOR;
            zzb.zza(this, parcel, n);
        }
    }
}
