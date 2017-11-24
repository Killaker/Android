package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import java.util.*;

public class zzh
{
    private final String zzPO;
    private final long zzQD;
    private final String zzQE;
    private final boolean zzQF;
    private long zzQG;
    private final Map<String, String> zzxA;
    
    public zzh(final long zzQD, final String zzPO, final String zzQE, final boolean zzQF, final long zzQG, final Map<String, String> map) {
        zzx.zzcM(zzPO);
        zzx.zzcM(zzQE);
        this.zzQD = zzQD;
        this.zzPO = zzPO;
        this.zzQE = zzQE;
        this.zzQF = zzQF;
        this.zzQG = zzQG;
        if (map != null) {
            this.zzxA = new HashMap<String, String>(map);
            return;
        }
        this.zzxA = Collections.emptyMap();
    }
    
    public String getClientId() {
        return this.zzPO;
    }
    
    public long zzjD() {
        return this.zzQD;
    }
    
    public String zzjE() {
        return this.zzQE;
    }
    
    public boolean zzjF() {
        return this.zzQF;
    }
    
    public long zzjG() {
        return this.zzQG;
    }
    
    public Map<String, String> zzn() {
        return this.zzxA;
    }
    
    public void zzn(final long zzQG) {
        this.zzQG = zzQG;
    }
}
