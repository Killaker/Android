package com.google.android.gms.measurement.internal;

import android.os.*;
import com.google.android.gms.common.internal.*;
import android.text.*;
import java.util.*;

public class zzh
{
    final String mName;
    final String zzaUa;
    final String zzaVM;
    final long zzaVN;
    final EventParams zzaVO;
    final long zzaez;
    
    zzh(final zzw zzw, String zzaVM, final String zzaUa, final String mName, final long zzaez, final long zzaVN, final Bundle bundle) {
        zzx.zzcM(zzaUa);
        zzx.zzcM(mName);
        this.zzaUa = zzaUa;
        this.mName = mName;
        if (TextUtils.isEmpty((CharSequence)zzaVM)) {
            zzaVM = null;
        }
        this.zzaVM = zzaVM;
        this.zzaez = zzaez;
        this.zzaVN = zzaVN;
        if (this.zzaVN != 0L && this.zzaVN > this.zzaez) {
            zzw.zzAo().zzCF().zzfg("Event created with reverse previous/current timestamps");
        }
        this.zzaVO = this.zza(zzw, bundle);
    }
    
    private zzh(final zzw zzw, String zzaVM, final String zzaUa, final String mName, final long zzaez, final long zzaVN, final EventParams zzaVO) {
        zzx.zzcM(zzaUa);
        zzx.zzcM(mName);
        zzx.zzz(zzaVO);
        this.zzaUa = zzaUa;
        this.mName = mName;
        if (TextUtils.isEmpty((CharSequence)zzaVM)) {
            zzaVM = null;
        }
        this.zzaVM = zzaVM;
        this.zzaez = zzaez;
        this.zzaVN = zzaVN;
        if (this.zzaVN != 0L && this.zzaVN > this.zzaez) {
            zzw.zzAo().zzCF().zzfg("Event created with reverse previous/current timestamps");
        }
        this.zzaVO = zzaVO;
    }
    
    private EventParams zza(final zzw zzw, final Bundle bundle) {
        if (bundle != null && !bundle.isEmpty()) {
            final Bundle bundle2 = new Bundle(bundle);
            final Iterator iterator = bundle2.keySet().iterator();
            while (iterator.hasNext()) {
                final String s = iterator.next();
                if (s == null) {
                    iterator.remove();
                }
                else {
                    final Object zzk = zzw.zzCk().zzk(s, bundle2.get(s));
                    if (zzk == null) {
                        iterator.remove();
                    }
                    else {
                        zzw.zzCk().zza(bundle2, s, zzk);
                    }
                }
            }
            return new EventParams(bundle2);
        }
        return new EventParams(new Bundle());
    }
    
    @Override
    public String toString() {
        return "Event{appId='" + this.zzaUa + '\'' + ", name='" + this.mName + '\'' + ", params=" + this.zzaVO + '}';
    }
    
    zzh zza(final zzw zzw, final long n) {
        return new zzh(zzw, this.zzaVM, this.zzaUa, this.mName, this.zzaez, n, this.zzaVO);
    }
}
