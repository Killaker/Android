package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

class zzcp$3 implements zza {
    final /* synthetic */ Map zzbkD;
    final /* synthetic */ Map zzbkE;
    final /* synthetic */ Map zzbkF;
    final /* synthetic */ Map zzbkG;
    
    @Override
    public void zza(final zzrs.zze zze, final Set<zzrs.zza> set, final Set<zzrs.zza> set2, final zzck zzck) {
        final List<? extends zzrs.zza> list = this.zzbkD.get(zze);
        final List<String> list2 = this.zzbkE.get(zze);
        if (list != null) {
            set.addAll(list);
            zzck.zzGJ().zzc((List<zzrs.zza>)list, list2);
        }
        final List<? extends zzrs.zza> list3 = this.zzbkF.get(zze);
        final List<String> list4 = this.zzbkG.get(zze);
        if (list3 != null) {
            set2.addAll(list3);
            zzck.zzGK().zzc((List<zzrs.zza>)list3, list4);
        }
    }
}