package com.google.android.gms.tagmanager;

import java.util.concurrent.*;
import android.content.res.*;
import android.content.*;
import android.annotation.*;
import java.util.*;
import android.support.annotation.*;
import com.google.android.gms.common.api.*;
import android.os.*;
import android.net.*;

public class TagManager
{
    private static TagManager zzblm;
    private final Context mContext;
    private final DataLayer zzbhN;
    private final zzs zzbkh;
    private final zza zzblj;
    private final zzct zzblk;
    private final ConcurrentMap<zzo, Boolean> zzbll;
    
    TagManager(final Context context, final zza zzblj, final DataLayer zzbhN, final zzct zzblk) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzblk = zzblk;
        this.zzblj = zzblj;
        this.zzbll = new ConcurrentHashMap<zzo, Boolean>();
        (this.zzbhN = zzbhN).zza((DataLayer.zzb)new DataLayer.zzb() {
            @Override
            public void zzQ(final Map<String, Object> map) {
                final Object value = map.get("event");
                if (value != null) {
                    TagManager.this.zzgp(value.toString());
                }
            }
        });
        this.zzbhN.zza((DataLayer.zzb)new zzd(this.mContext));
        this.zzbkh = new zzs();
        this.zzHt();
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public static TagManager getInstance(final Context context) {
        Label_0068: {
            synchronized (TagManager.class) {
                if (TagManager.zzblm != null) {
                    break Label_0068;
                }
                if (context == null) {
                    zzbg.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
            }
            TagManager.zzblm = new TagManager(context, (zza)new zza() {
                @Override
                public zzp zza(final Context context, final TagManager tagManager, final Looper looper, final String s, final int n, final zzs zzs) {
                    return new zzp(context, tagManager, looper, s, n, zzs);
                }
            }, new DataLayer((DataLayer.zzc)new zzw(context)), zzcu.zzHo());
        }
        // monitorexit(TagManager.class)
        return TagManager.zzblm;
    }
    
    @TargetApi(14)
    private void zzHt() {
        if (Build$VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks((ComponentCallbacks)new ComponentCallbacks2() {
                public void onConfigurationChanged(final Configuration configuration) {
                }
                
                public void onLowMemory() {
                }
                
                public void onTrimMemory(final int n) {
                    if (n == 20) {
                        TagManager.this.dispatch();
                    }
                }
            });
        }
    }
    
    private void zzgp(final String s) {
        final Iterator<zzo> iterator = this.zzbll.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().zzfR(s);
        }
    }
    
    public void dispatch() {
        this.zzblk.dispatch();
    }
    
    public DataLayer getDataLayer() {
        return this.zzbhN;
    }
    
    public PendingResult<ContainerHolder> loadContainerDefaultOnly(final String s, @RawRes final int n) {
        final zzp zza = this.zzblj.zza(this.mContext, this, null, s, n, this.zzbkh);
        zza.zzGg();
        return zza;
    }
    
    public PendingResult<ContainerHolder> loadContainerDefaultOnly(final String s, @RawRes final int n, final Handler handler) {
        final zzp zza = this.zzblj.zza(this.mContext, this, handler.getLooper(), s, n, this.zzbkh);
        zza.zzGg();
        return zza;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferFresh(final String s, @RawRes final int n) {
        final zzp zza = this.zzblj.zza(this.mContext, this, null, s, n, this.zzbkh);
        zza.zzGi();
        return zza;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferFresh(final String s, @RawRes final int n, final Handler handler) {
        final zzp zza = this.zzblj.zza(this.mContext, this, handler.getLooper(), s, n, this.zzbkh);
        zza.zzGi();
        return zza;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(final String s, @RawRes final int n) {
        final zzp zza = this.zzblj.zza(this.mContext, this, null, s, n, this.zzbkh);
        zza.zzGh();
        return zza;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(final String s, @RawRes final int n, final Handler handler) {
        final zzp zza = this.zzblj.zza(this.mContext, this, handler.getLooper(), s, n, this.zzbkh);
        zza.zzGh();
        return zza;
    }
    
    public void setVerboseLoggingEnabled(final boolean b) {
        int logLevel;
        if (b) {
            logLevel = 2;
        }
        else {
            logLevel = 5;
        }
        zzbg.setLogLevel(logLevel);
    }
    
    public void zza(final zzo zzo) {
        this.zzbll.put(zzo, true);
    }
    
    public boolean zzb(final zzo zzo) {
        return this.zzbll.remove(zzo) != null;
    }
    
    boolean zzp(final Uri uri) {
    Label_0060_Outer:
        while (true) {
            while (true) {
                final zzcb zzGU;
                final String containerId;
                Label_0138: {
                    synchronized (this) {
                        zzGU = zzcb.zzGU();
                        if (zzGU.zzp(uri)) {
                            containerId = zzGU.getContainerId();
                            switch (TagManager$4.zzblo[zzGU.zzGV().ordinal()]) {
                                case 1: {
                                    for (final zzo zzo : this.zzbll.keySet()) {
                                        if (zzo.getContainerId().equals(containerId)) {
                                            zzo.zzfT(null);
                                            zzo.refresh();
                                        }
                                    }
                                    break;
                                }
                                case 2:
                                case 3: {
                                    break Label_0138;
                                }
                            }
                            return true;
                        }
                        return false;
                    }
                }
                for (final zzo zzo2 : this.zzbll.keySet()) {
                    if (zzo2.getContainerId().equals(containerId)) {
                        zzo2.zzfT(zzGU.zzGW());
                        zzo2.refresh();
                    }
                    else {
                        if (zzo2.zzGd() == null) {
                            continue Label_0060_Outer;
                        }
                        zzo2.zzfT(null);
                        zzo2.refresh();
                    }
                }
                continue;
            }
            return false;
        }
    }
    
    public interface zza
    {
        zzp zza(final Context p0, final TagManager p1, final Looper p2, final String p3, final int p4, final zzs p5);
    }
}
