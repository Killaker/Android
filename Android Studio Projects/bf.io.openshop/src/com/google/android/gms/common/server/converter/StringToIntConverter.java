package com.google.android.gms.common.server.converter;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.server.response.*;
import java.util.*;
import android.os.*;

public final class StringToIntConverter implements SafeParcelable, zza<String, Integer>
{
    public static final zzb CREATOR;
    private final int mVersionCode;
    private final HashMap<String, Integer> zzamG;
    private final HashMap<Integer, String> zzamH;
    private final ArrayList<Entry> zzamI;
    
    static {
        CREATOR = new zzb();
    }
    
    public StringToIntConverter() {
        this.mVersionCode = 1;
        this.zzamG = new HashMap<String, Integer>();
        this.zzamH = new HashMap<Integer, String>();
        this.zzamI = null;
    }
    
    StringToIntConverter(final int mVersionCode, final ArrayList<Entry> list) {
        this.mVersionCode = mVersionCode;
        this.zzamG = new HashMap<String, Integer>();
        this.zzamH = new HashMap<Integer, String>();
        this.zzamI = null;
        this.zzd(list);
    }
    
    private void zzd(final ArrayList<Entry> list) {
        for (final Entry entry : list) {
            this.zzh(entry.zzamJ, entry.zzamK);
        }
    }
    
    public int describeContents() {
        final zzb creator = StringToIntConverter.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzb creator = StringToIntConverter.CREATOR;
        zzb.zza(this, parcel, n);
    }
    
    public String zzb(final Integer n) {
        String s = this.zzamH.get(n);
        if (s == null && this.zzamG.containsKey("gms_unknown")) {
            s = "gms_unknown";
        }
        return s;
    }
    
    public StringToIntConverter zzh(final String s, final int n) {
        this.zzamG.put(s, n);
        this.zzamH.put(n, s);
        return this;
    }
    
    ArrayList<Entry> zzri() {
        final ArrayList<Entry> list = new ArrayList<Entry>();
        for (final String s : this.zzamG.keySet()) {
            list.add(new Entry(s, this.zzamG.get(s)));
        }
        return list;
    }
    
    @Override
    public int zzrj() {
        return 7;
    }
    
    @Override
    public int zzrk() {
        return 0;
    }
    
    public static final class Entry implements SafeParcelable
    {
        public static final zzc CREATOR;
        final int versionCode;
        final String zzamJ;
        final int zzamK;
        
        static {
            CREATOR = new zzc();
        }
        
        Entry(final int versionCode, final String zzamJ, final int zzamK) {
            this.versionCode = versionCode;
            this.zzamJ = zzamJ;
            this.zzamK = zzamK;
        }
        
        Entry(final String zzamJ, final int zzamK) {
            this.versionCode = 1;
            this.zzamJ = zzamJ;
            this.zzamK = zzamK;
        }
        
        public int describeContents() {
            final zzc creator = Entry.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final zzc creator = Entry.CREATOR;
            zzc.zza(this, parcel, n);
        }
    }
}
