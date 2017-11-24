package com.google.android.gms.playlog.internal;

import java.util.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

public class zzb
{
    private final ArrayList<zza> zzbdE;
    private int zzbdF;
    
    public zzb() {
        this(100);
    }
    
    public zzb(final int zzbdF) {
        this.zzbdE = new ArrayList<zza>();
        this.zzbdF = zzbdF;
    }
    
    private void zzEV() {
        while (this.getSize() > this.getCapacity()) {
            this.zzbdE.remove(0);
        }
    }
    
    public void clear() {
        this.zzbdE.clear();
    }
    
    public int getCapacity() {
        return this.zzbdF;
    }
    
    public int getSize() {
        return this.zzbdE.size();
    }
    
    public boolean isEmpty() {
        return this.zzbdE.isEmpty();
    }
    
    public ArrayList<zza> zzEU() {
        return this.zzbdE;
    }
    
    public void zza(final PlayLoggerContext playLoggerContext, final LogEvent logEvent) {
        this.zzbdE.add(new zza(playLoggerContext, logEvent));
        this.zzEV();
    }
    
    public static class zza
    {
        public final PlayLoggerContext zzbdG;
        public final LogEvent zzbdH;
        public final zzsz.zzd zzbdI;
        
        private zza(final PlayLoggerContext playLoggerContext, final LogEvent logEvent) {
            this.zzbdG = zzx.zzz(playLoggerContext);
            this.zzbdH = zzx.zzz(logEvent);
            this.zzbdI = null;
        }
    }
}
