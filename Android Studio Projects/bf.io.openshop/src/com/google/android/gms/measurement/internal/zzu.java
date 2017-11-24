package com.google.android.gms.measurement.internal;

import java.util.*;
import android.support.v4.util.*;
import java.io.*;
import android.support.annotation.*;
import com.google.android.gms.common.internal.*;
import android.content.*;
import com.google.android.gms.internal.*;

public class zzu extends zzz
{
    private final Map<String, Map<String, String>> zzaXF;
    private final Map<String, Map<String, Boolean>> zzaXG;
    private final Map<String, zzqa.zzb> zzaXH;
    
    zzu(final zzw zzw) {
        super(zzw);
        this.zzaXF = new ArrayMap<String, Map<String, String>>();
        this.zzaXG = new ArrayMap<String, Map<String, Boolean>>();
        this.zzaXH = new ArrayMap<String, zzqa.zzb>();
    }
    
    private Map<String, String> zza(final zzqa.zzb zzb) {
        final ArrayMap<String, String> arrayMap = new ArrayMap<String, String>();
        if (zzb != null && zzb.zzaZV != null) {
            for (final zzqa.zzc zzc : zzb.zzaZV) {
                if (zzc != null) {
                    arrayMap.put(zzc.key, zzc.value);
                }
            }
        }
        return arrayMap;
    }
    
    private Map<String, Boolean> zzb(final zzqa.zzb zzb) {
        final ArrayMap<String, Boolean> arrayMap = new ArrayMap<String, Boolean>();
        if (zzb != null && zzb.zzaZW != null) {
            for (final zzqa.zza zza : zzb.zzaZW) {
                if (zza != null) {
                    arrayMap.put(zza.name, zza.zzaZS);
                }
            }
        }
        return arrayMap;
    }
    
    @WorkerThread
    private zzqa.zzb zzf(final String s, final byte[] array) {
        if (array == null) {
            return new zzqa.zzb();
        }
        final zzsm zzD = zzsm.zzD(array);
        final zzqa.zzb zzb = new zzqa.zzb();
        try {
            zzb.zzA(zzD);
            this.zzAo().zzCK().zze("Parsed config. version, gmp_app_id", zzb.zzaZT, zzb.zzaVt);
            return zzb;
        }
        catch (IOException ex) {
            this.zzAo().zzCF().zze("Unable to merge remote config", s, ex);
            return null;
        }
    }
    
    @WorkerThread
    private void zzfj(final String s) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        if (!this.zzaXH.containsKey(s)) {
            final byte[] zzfa = this.zzCj().zzfa(s);
            if (zzfa != null) {
                final zzqa.zzb zzf = this.zzf(s, zzfa);
                this.zzaXF.put(s, this.zza(zzf));
                this.zzaXG.put(s, this.zzb(zzf));
                this.zzaXH.put(s, zzf);
                return;
            }
            this.zzaXF.put(s, null);
            this.zzaXG.put(s, null);
            this.zzaXH.put(s, null);
        }
    }
    
    @WorkerThread
    String zzO(final String s, final String s2) {
        this.zzjk();
        this.zzfj(s);
        final Map<String, String> map = this.zzaXF.get(s);
        if (map != null) {
            return map.get(s2);
        }
        return null;
    }
    
    @WorkerThread
    boolean zzP(final String s, final String s2) {
        this.zzjk();
        this.zzfj(s);
        final Map<String, Boolean> map = this.zzaXG.get(s);
        if (map != null) {
            final Boolean b = map.get(s2);
            return b != null && b;
        }
        return false;
    }
    
    @WorkerThread
    protected boolean zze(final String s, byte[] array) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        final zzqa.zzb zzf = this.zzf(s, array);
        if (zzf == null) {
            return false;
        }
        this.zzaXG.put(s, this.zzb(zzf));
        this.zzaXH.put(s, zzf);
        this.zzaXF.put(s, this.zza(zzf));
        this.zzCe().zza(s, zzf.zzaZX);
        while (true) {
            try {
                zzf.zzaZX = null;
                final byte[] array2 = new byte[zzf.getSerializedSize()];
                zzf.writeTo(zzsn.zzE(array2));
                array = array2;
                this.zzCj().zzd(s, array);
                return true;
            }
            catch (IOException ex) {
                this.zzAo().zzCF().zzj("Unable to serialize reduced-size config.  Storing full config instead.", ex);
                continue;
            }
            break;
        }
    }
    
    @WorkerThread
    protected zzqa.zzb zzfk(final String s) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        this.zzfj(s);
        return this.zzaXH.get(s);
    }
    
    @Override
    protected void zziJ() {
    }
}
