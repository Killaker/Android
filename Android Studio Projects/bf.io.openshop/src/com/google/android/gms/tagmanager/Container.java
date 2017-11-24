package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;

public class Container
{
    private final Context mContext;
    private final String zzbhM;
    private final DataLayer zzbhN;
    private zzcp zzbhO;
    private Map<String, FunctionCallMacroCallback> zzbhP;
    private Map<String, FunctionCallTagCallback> zzbhQ;
    private volatile long zzbhR;
    private volatile String zzbhS;
    
    Container(final Context mContext, final DataLayer zzbhN, final String zzbhM, final long zzbhR, final zzaf.zzj zzj) {
        this.zzbhP = new HashMap<String, FunctionCallMacroCallback>();
        this.zzbhQ = new HashMap<String, FunctionCallTagCallback>();
        this.zzbhS = "";
        this.mContext = mContext;
        this.zzbhN = zzbhN;
        this.zzbhM = zzbhM;
        this.zzbhR = zzbhR;
        this.zza(zzj.zzju);
        if (zzj.zzjt != null) {
            this.zza(zzj.zzjt);
        }
    }
    
    Container(final Context mContext, final DataLayer zzbhN, final String zzbhM, final long zzbhR, final zzrs.zzc zzc) {
        this.zzbhP = new HashMap<String, FunctionCallMacroCallback>();
        this.zzbhQ = new HashMap<String, FunctionCallTagCallback>();
        this.zzbhS = "";
        this.mContext = mContext;
        this.zzbhN = zzbhN;
        this.zzbhM = zzbhM;
        this.zzbhR = zzbhR;
        this.zza(zzc);
    }
    
    private zzcp zzGc() {
        synchronized (this) {
            return this.zzbhO;
        }
    }
    
    private void zza(final zzaf.zzf zzf) {
        if (zzf == null) {
            throw new NullPointerException();
        }
        try {
            this.zza(zzrs.zzb(zzf));
        }
        catch (zzrs.zzg zzg) {
            zzbg.e("Not loading resource: " + zzf + " because it is invalid: " + zzg.toString());
        }
    }
    
    private void zza(final zzrs.zzc zzc) {
        this.zzbhS = zzc.getVersion();
        this.zza(new zzcp(this.mContext, zzc, this.zzbhN, new zza(), new zzb(), this.zzfS(this.zzbhS)));
        if (this.getBoolean("_gtm.loadEventEnabled")) {
            this.zzbhN.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzbhM));
        }
    }
    
    private void zza(final zzcp zzbhO) {
        synchronized (this) {
            this.zzbhO = zzbhO;
        }
    }
    
    private void zza(final zzaf.zzi[] array) {
        final ArrayList<zzaf.zzi> list = new ArrayList<zzaf.zzi>();
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        this.zzGc().zzF(list);
    }
    
    public boolean getBoolean(final String s) {
        final zzcp zzGc = this.zzGc();
        if (zzGc == null) {
            zzbg.e("getBoolean called for closed container.");
            return zzdf.zzHC();
        }
        try {
            return zzdf.zzk(zzGc.zzgn(s).getObject());
        }
        catch (Exception ex) {
            zzbg.e("Calling getBoolean() threw an exception: " + ex.getMessage() + " Returning default value.");
            return zzdf.zzHC();
        }
    }
    
    public String getContainerId() {
        return this.zzbhM;
    }
    
    public double getDouble(final String s) {
        final zzcp zzGc = this.zzGc();
        if (zzGc == null) {
            zzbg.e("getDouble called for closed container.");
            return zzdf.zzHB();
        }
        try {
            return zzdf.zzj(zzGc.zzgn(s).getObject());
        }
        catch (Exception ex) {
            zzbg.e("Calling getDouble() threw an exception: " + ex.getMessage() + " Returning default value.");
            return zzdf.zzHB();
        }
    }
    
    public long getLastRefreshTime() {
        return this.zzbhR;
    }
    
    public long getLong(final String s) {
        final zzcp zzGc = this.zzGc();
        if (zzGc == null) {
            zzbg.e("getLong called for closed container.");
            return zzdf.zzHA();
        }
        try {
            return zzdf.zzi(zzGc.zzgn(s).getObject());
        }
        catch (Exception ex) {
            zzbg.e("Calling getLong() threw an exception: " + ex.getMessage() + " Returning default value.");
            return zzdf.zzHA();
        }
    }
    
    public String getString(final String s) {
        final zzcp zzGc = this.zzGc();
        if (zzGc == null) {
            zzbg.e("getString called for closed container.");
            return zzdf.zzHE();
        }
        try {
            return zzdf.zzg(zzGc.zzgn(s).getObject());
        }
        catch (Exception ex) {
            zzbg.e("Calling getString() threw an exception: " + ex.getMessage() + " Returning default value.");
            return zzdf.zzHE();
        }
    }
    
    public boolean isDefault() {
        return this.getLastRefreshTime() == 0L;
    }
    
    public void registerFunctionCallMacroCallback(final String s, final FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzbhP) {
            this.zzbhP.put(s, functionCallMacroCallback);
        }
    }
    
    public void registerFunctionCallTagCallback(final String s, final FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzbhQ) {
            this.zzbhQ.put(s, functionCallTagCallback);
        }
    }
    
    void release() {
        this.zzbhO = null;
    }
    
    public void unregisterFunctionCallMacroCallback(final String s) {
        synchronized (this.zzbhP) {
            this.zzbhP.remove(s);
        }
    }
    
    public void unregisterFunctionCallTagCallback(final String s) {
        synchronized (this.zzbhQ) {
            this.zzbhQ.remove(s);
        }
    }
    
    public String zzGb() {
        return this.zzbhS;
    }
    
    FunctionCallMacroCallback zzfP(final String s) {
        synchronized (this.zzbhP) {
            return this.zzbhP.get(s);
        }
    }
    
    public FunctionCallTagCallback zzfQ(final String s) {
        synchronized (this.zzbhQ) {
            return this.zzbhQ.get(s);
        }
    }
    
    public void zzfR(final String s) {
        this.zzGc().zzfR(s);
    }
    
    zzah zzfS(final String s) {
        if (zzcb.zzGU().zzGV().equals(zzcb.zza.zzbjW)) {}
        return new zzbo();
    }
    
    public interface FunctionCallMacroCallback
    {
        Object getValue(final String p0, final Map<String, Object> p1);
    }
    
    public interface FunctionCallTagCallback
    {
        void execute(final String p0, final Map<String, Object> p1);
    }
    
    private class zza implements zzt.zza
    {
        @Override
        public Object zzc(final String s, final Map<String, Object> map) {
            final FunctionCallMacroCallback zzfP = Container.this.zzfP(s);
            if (zzfP == null) {
                return null;
            }
            return zzfP.getValue(s, map);
        }
    }
    
    private class zzb implements zzt.zza
    {
        @Override
        public Object zzc(final String s, final Map<String, Object> map) {
            final FunctionCallTagCallback zzfQ = Container.this.zzfQ(s);
            if (zzfQ != null) {
                zzfQ.execute(s, map);
            }
            return zzdf.zzHE();
        }
    }
}
