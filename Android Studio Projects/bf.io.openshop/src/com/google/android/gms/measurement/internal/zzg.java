package com.google.android.gms.measurement.internal;

import android.content.*;
import android.os.*;
import java.util.concurrent.*;
import java.util.*;
import com.google.android.gms.internal.*;

public class zzg extends zzz
{
    private long zzaVK;
    private String zzaVL;
    
    zzg(final zzw zzw) {
        super(zzw);
    }
    
    public String zzCA() {
        this.zzjv();
        return this.zzaVL;
    }
    
    public String zzCy() {
        this.zzjv();
        return Build$VERSION.RELEASE;
    }
    
    public long zzCz() {
        this.zzjv();
        return this.zzaVK;
    }
    
    public String zzht() {
        this.zzjv();
        return Build.MODEL;
    }
    
    @Override
    protected void zziJ() {
        final Calendar instance = Calendar.getInstance();
        this.zzaVK = TimeUnit.MINUTES.convert(instance.get(15) + instance.get(16), TimeUnit.MILLISECONDS);
        final Locale default1 = Locale.getDefault();
        this.zzaVL = default1.getLanguage().toLowerCase(Locale.ENGLISH) + "-" + default1.getCountry().toLowerCase(Locale.ENGLISH);
    }
}
