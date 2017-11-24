package com.google.android.gms.common.stats;

import android.support.v4.util.*;
import android.os.*;
import android.util.*;

public class zze
{
    private final long zzanN;
    private final int zzanO;
    private final SimpleArrayMap<String, Long> zzanP;
    
    public zze() {
        this.zzanN = 60000L;
        this.zzanO = 10;
        this.zzanP = new SimpleArrayMap<String, Long>(10);
    }
    
    public zze(final int zzanO, final long zzanN) {
        this.zzanN = zzanN;
        this.zzanO = zzanO;
        this.zzanP = new SimpleArrayMap<String, Long>();
    }
    
    private void zzb(final long n, final long n2) {
        for (int i = -1 + this.zzanP.size(); i >= 0; --i) {
            if (n2 - this.zzanP.valueAt(i) > n) {
                this.zzanP.removeAt(i);
            }
        }
    }
    
    public Long zzcS(final String s) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        long zzanN = this.zzanN;
        synchronized (this) {
            while (this.zzanP.size() >= this.zzanO) {
                this.zzb(zzanN, elapsedRealtime);
                zzanN /= 2L;
                Log.w("ConnectionTracker", "The max capacity " + this.zzanO + " is not enough. Current durationThreshold is: " + zzanN);
            }
        }
        // monitorexit(this)
        return this.zzanP.put(s, elapsedRealtime);
    }
    
    public boolean zzcT(final String s) {
        while (true) {
            synchronized (this) {
                if (this.zzanP.remove(s) != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
