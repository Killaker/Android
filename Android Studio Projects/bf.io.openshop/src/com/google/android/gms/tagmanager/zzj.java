package com.google.android.gms.tagmanager;

import android.content.*;
import com.google.android.gms.internal.*;
import android.net.*;
import java.util.*;

class zzj extends zzdd
{
    private static final String ID;
    private static final String URL;
    private static final String zzbhF;
    private static final String zzbhG;
    static final String zzbhH;
    private static final Set<String> zzbhI;
    private final Context mContext;
    private final zza zzbhJ;
    
    static {
        ID = zzad.zzcJ.toString();
        URL = zzae.zzhZ.toString();
        zzbhF = zzae.zzdI.toString();
        zzbhG = zzae.zzhY.toString();
        zzbhH = "gtm_" + zzj.ID + "_unrepeatable";
        zzbhI = new HashSet<String>();
    }
    
    public zzj(final Context context) {
        this(context, (zza)new zza() {
            @Override
            public zzar zzFX() {
                return zzz.zzaX(context);
            }
        });
    }
    
    zzj(final Context mContext, final zza zzbhJ) {
        super(zzj.ID, new String[] { zzj.URL });
        this.zzbhJ = zzbhJ;
        this.mContext = mContext;
    }
    
    private boolean zzfL(final String s) {
        while (true) {
            boolean b = true;
            synchronized (this) {
                if (!this.zzfN(s)) {
                    if (!this.zzfM(s)) {
                        return false;
                    }
                    zzj.zzbhI.add(s);
                }
                return b;
            }
            b = false;
            return b;
        }
    }
    
    @Override
    public void zzR(final Map<String, zzag.zza> map) {
        String zzg;
        if (map.get(zzj.zzbhG) != null) {
            zzg = zzdf.zzg(map.get(zzj.zzbhG));
        }
        else {
            zzg = null;
        }
        if (zzg == null || !this.zzfL(zzg)) {
            final Uri$Builder buildUpon = Uri.parse(zzdf.zzg(map.get(zzj.URL))).buildUpon();
            final zzag.zza zza = map.get(zzj.zzbhF);
            if (zza != null) {
                final Object zzl = zzdf.zzl(zza);
                if (!(zzl instanceof List)) {
                    zzbg.e("ArbitraryPixel: additional params not a list: not sending partial hit: " + buildUpon.build().toString());
                    return;
                }
                for (final Map<Object, V> next : (List<Object>)zzl) {
                    if (!(next instanceof Map)) {
                        zzbg.e("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + buildUpon.build().toString());
                        return;
                    }
                    for (final Map.Entry<Object, V> entry : next.entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            final String string = buildUpon.build().toString();
            this.zzbhJ.zzFX().zzgc(string);
            zzbg.v("ArbitraryPixel: url = " + string);
            if (zzg != null) {
                synchronized (zzj.class) {
                    zzj.zzbhI.add(zzg);
                    zzcv.zzb(this.mContext, zzj.zzbhH, zzg, "true");
                }
            }
        }
    }
    
    boolean zzfM(final String s) {
        return this.mContext.getSharedPreferences(zzj.zzbhH, 0).contains(s);
    }
    
    boolean zzfN(final String s) {
        return zzj.zzbhI.contains(s);
    }
    
    public interface zza
    {
        zzar zzFX();
    }
}
