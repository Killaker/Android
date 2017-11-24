package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.*;
import android.text.*;
import android.support.v4.util.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import java.util.regex.*;

class zzc extends zzz
{
    zzc(final zzw zzw) {
        super(zzw);
    }
    
    private Boolean zza(final zzpz.zzb zzb, final zzqb.zzb zzb2, final long n) {
        if (zzb.zzaZz != null) {
            final Boolean zzac = new zzs(zzb.zzaZz).zzac(n);
            if (zzac == null) {
                return null;
            }
            if (!zzac) {
                return false;
            }
        }
        final HashSet<String> set = new HashSet<String>();
        for (final zzpz.zzc zzc : zzb.zzaZx) {
            if (TextUtils.isEmpty((CharSequence)zzc.zzaZE)) {
                this.zzAo().zzCF().zzj("null or empty param name in filter. event", zzb2.name);
                return null;
            }
            set.add(zzc.zzaZE);
        }
        final ArrayMap<Object, String> arrayMap = new ArrayMap<Object, String>();
        for (final zzqb.zzc zzc2 : zzb2.zzbae) {
            if (set.contains(zzc2.name)) {
                if (zzc2.zzbai != null) {
                    arrayMap.put(zzc2.name, (String)zzc2.zzbai);
                }
                else if (zzc2.zzaZo != null) {
                    arrayMap.put(zzc2.name, (String)zzc2.zzaZo);
                }
                else {
                    if (zzc2.zzamJ == null) {
                        this.zzAo().zzCF().zze("Unknown value for param. event, param", zzb2.name, zzc2.name);
                        return null;
                    }
                    arrayMap.put(zzc2.name, zzc2.zzamJ);
                }
            }
        }
        for (final zzpz.zzc zzc3 : zzb.zzaZx) {
            final String zzaZE = zzc3.zzaZE;
            if (TextUtils.isEmpty((CharSequence)zzaZE)) {
                this.zzAo().zzCF().zzj("Event has empty param name. event", zzb2.name);
                return null;
            }
            final Object value = arrayMap.get(zzaZE);
            if (value instanceof Long) {
                if (zzc3.zzaZC == null) {
                    this.zzAo().zzCF().zze("No number filter for long param. event, param", zzb2.name, zzaZE);
                    return null;
                }
                final Boolean zzac2 = new zzs(zzc3.zzaZC).zzac((long)value);
                if (zzac2 == null) {
                    return null;
                }
                if (!zzac2) {
                    return false;
                }
            }
            else if (value instanceof Float) {
                if (zzc3.zzaZC == null) {
                    this.zzAo().zzCF().zze("No number filter for float param. event, param", zzb2.name, zzaZE);
                    return null;
                }
                final Boolean zzi = new zzs(zzc3.zzaZC).zzi((float)value);
                if (zzi == null) {
                    return null;
                }
                if (!zzi) {
                    return false;
                }
            }
            else if (value instanceof String) {
                if (zzc3.zzaZB == null) {
                    this.zzAo().zzCF().zze("No string filter for String param. event, param", zzb2.name, zzaZE);
                    return null;
                }
                final Boolean zzfp = new zzae(zzc3.zzaZB).zzfp((String)value);
                if (zzfp == null) {
                    return null;
                }
                if (!zzfp) {
                    return false;
                }
            }
            else {
                if (value == null) {
                    this.zzAo().zzCK().zze("Missing param for filter. event, param", zzb2.name, zzaZE);
                    return false;
                }
                this.zzAo().zzCF().zze("Unknown param type. event, param", zzb2.name, zzaZE);
                return null;
            }
        }
        return true;
    }
    
    private Boolean zza(final zzpz.zze zze, final zzqb.zzg zzg) {
        final zzpz.zzc zzaZM = zze.zzaZM;
        if (zzaZM == null) {
            this.zzAo().zzCF().zzj("Missing property filter. property", zzg.name);
            return null;
        }
        if (zzg.zzbai != null) {
            if (zzaZM.zzaZC == null) {
                this.zzAo().zzCF().zzj("No number filter for long property. property", zzg.name);
                return null;
            }
            return new zzs(zzaZM.zzaZC).zzac(zzg.zzbai);
        }
        else if (zzg.zzaZo != null) {
            if (zzaZM.zzaZC == null) {
                this.zzAo().zzCF().zzj("No number filter for float property. property", zzg.name);
                return null;
            }
            return new zzs(zzaZM.zzaZC).zzi(zzg.zzaZo);
        }
        else {
            if (zzg.zzamJ == null) {
                this.zzAo().zzCF().zzj("User property has no value, property", zzg.name);
                return null;
            }
            if (zzaZM.zzaZB != null) {
                return new zzae(zzaZM.zzaZB).zzfp(zzg.zzamJ);
            }
            if (zzaZM.zzaZC == null) {
                this.zzAo().zzCF().zzj("No string or number filter defined. property", zzg.name);
                return null;
            }
            final zzs zzs = new zzs(zzaZM.zzaZC);
            if (!zzaZM.zzaZC.zzaZG) {
                if (this.zzeQ(zzg.zzamJ)) {
                    try {
                        return zzs.zzac(Long.parseLong(zzg.zzamJ));
                    }
                    catch (NumberFormatException ex) {
                        this.zzAo().zzCF().zze("User property value exceeded Long value range. property, value", zzg.name, zzg.zzamJ);
                        return null;
                    }
                }
                this.zzAo().zzCF().zze("Invalid user property value for Long number filter. property, value", zzg.name, zzg.zzamJ);
                return null;
            }
            if (this.zzeR(zzg.zzamJ)) {
                try {
                    final float float1 = Float.parseFloat(zzg.zzamJ);
                    if (!Float.isInfinite(float1)) {
                        return zzs.zzi(float1);
                    }
                    this.zzAo().zzCF().zze("User property value exceeded Float value range. property, value", zzg.name, zzg.zzamJ);
                    return null;
                }
                catch (NumberFormatException ex2) {
                    this.zzAo().zzCF().zze("User property value exceeded Float value range. property, value", zzg.name, zzg.zzamJ);
                    return null;
                }
            }
            this.zzAo().zzCF().zze("Invalid user property value for Float number filter. property, value", zzg.name, zzg.zzamJ);
            return null;
        }
    }
    
    void zza(final String s, final zzpz.zza[] array) {
        this.zzCj().zzb(s, array);
    }
    
    zzqb.zza[] zza(final String s, final zzqb.zzb[] array, final zzqb.zzg[] array2) {
        zzx.zzcM(s);
        final HashSet<Integer> set = new HashSet<Integer>();
        final ArrayMap<Object, Object> arrayMap = new ArrayMap<Object, Object>();
        final ArrayMap<Integer, Object> arrayMap2 = new ArrayMap<Integer, Object>();
        final ArrayMap<Object, Object> arrayMap3 = new ArrayMap<Object, Object>();
        if (array != null) {
            final ArrayMap<Object, Map<Object, List>> arrayMap4 = new ArrayMap<Object, Map<Object, List>>();
            for (final zzqb.zzb zzb : array) {
                final zzi zzI = this.zzCj().zzI(s, zzb.name);
                zzi zzCB;
                if (zzI == null) {
                    this.zzAo().zzCF().zzj("Event aggregate wasn't created during raw event logging. event", zzb.name);
                    zzCB = new zzi(s, zzb.name, 1L, 1L, zzb.zzbaf);
                }
                else {
                    zzCB = zzI.zzCB();
                }
                this.zzCj().zza(zzCB);
                final long zzaVP = zzCB.zzaVP;
                final Map<Object, List> map = arrayMap4.get(zzb.name);
                Map<Object, List> map2;
                if (map == null) {
                    Object zzL = this.zzCj().zzL(s, zzb.name);
                    if (zzL == null) {
                        zzL = new ArrayMap<Object, List<zzpz.zzb>>();
                    }
                    arrayMap4.put(zzb.name, (Map<Object, List>)zzL);
                    map2 = (Map<Object, List>)zzL;
                }
                else {
                    map2 = map;
                }
                this.zzAo().zzCK().zze("Found audiences. event, audience count", zzb.name, map2.size());
                for (final int intValue : map2.keySet()) {
                    if (set.contains(intValue)) {
                        this.zzAo().zzCK().zzj("Skipping failed audience ID", intValue);
                    }
                    else {
                        final zzqb.zza zza = arrayMap.get(intValue);
                        zzqb.zza zza3;
                        if (zza == null) {
                            final zzqb.zza zza2 = new zzqb.zza();
                            arrayMap.put(intValue, zza2);
                            zza2.zzbac = false;
                            zza3 = zza2;
                        }
                        else {
                            zza3 = zza;
                        }
                        final List<zzpz.zzb> list = map2.get(intValue);
                        BitSet set2 = arrayMap2.get(intValue);
                        BitSet set3 = arrayMap3.get(intValue);
                        if (set2 == null) {
                            set2 = new BitSet();
                            arrayMap2.put(intValue, set2);
                            set3 = new BitSet();
                            arrayMap3.put(intValue, set3);
                        }
                        if (zza3.zzbab == null && !zza3.zzbac) {
                            final zzqb.zzf zzC = this.zzCj().zzC(s, intValue);
                            if (zzC == null) {
                                zza3.zzbac = true;
                            }
                            else {
                                zza3.zzbab = zzC;
                                for (int j = 0; j < 64 * zzC.zzbaH.length; ++j) {
                                    if (zzaj.zza(zzC.zzbaH, j)) {
                                        this.zzAo().zzCK().zze("Event filter already evaluated true. audience ID, filter ID", intValue, j);
                                        set2.set(j);
                                        set3.set(j);
                                    }
                                }
                            }
                        }
                        for (final zzpz.zzb zzb2 : list) {
                            if (this.zzAo().zzQ(2)) {
                                this.zzAo().zzCK().zzd("Evaluating filter. audience, filter, event", intValue, zzb2.zzaZv, zzb2.zzaZw);
                                this.zzAo().zzCK().zzj("Filter definition", zzb2);
                            }
                            if (zzb2.zzaZv > 256) {
                                this.zzAo().zzCF().zzj("Invalid event filter ID > 256. id", zzb2.zzaZv);
                            }
                            else {
                                if (set3.get(zzb2.zzaZv)) {
                                    continue;
                                }
                                final Boolean zza4 = this.zza(zzb2, zzb, zzaVP);
                                this.zzAo().zzCK().zzj("Event filter result", zza4);
                                if (zza4 == null) {
                                    set.add(intValue);
                                }
                                else {
                                    set3.set(zzb2.zzaZv);
                                    if (!zza4) {
                                        continue;
                                    }
                                    set2.set(zzb2.zzaZv);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (array2 != null) {
            final ArrayMap<Object, Object> arrayMap5 = new ArrayMap<Object, Object>();
            for (final zzqb.zzg zzg : array2) {
                final Map<String, List> map3 = arrayMap5.get(zzg.name);
                Object o;
                if (map3 == null) {
                    Map<Integer, List<zzpz.zze>> zzM = this.zzCj().zzM(s, zzg.name);
                    if (zzM == null) {
                        zzM = new ArrayMap<Integer, List<zzpz.zze>>();
                    }
                    arrayMap5.put(zzg.name, zzM);
                    o = zzM;
                }
                else {
                    o = map3;
                }
                this.zzAo().zzCK().zze("Found audiences. property, audience count", zzg.name, ((Map)o).size());
                for (final int intValue2 : ((Map<String, List>)o).keySet()) {
                    if (set.contains(intValue2)) {
                        this.zzAo().zzCK().zzj("Skipping failed audience ID", intValue2);
                    }
                    else {
                        final zzqb.zza zza5 = arrayMap.get(intValue2);
                        zzqb.zza zza7;
                        if (zza5 == null) {
                            final zzqb.zza zza6 = new zzqb.zza();
                            arrayMap.put(intValue2, zza6);
                            zza6.zzbac = false;
                            zza7 = zza6;
                        }
                        else {
                            zza7 = zza5;
                        }
                        final List<zzpz.zze> list2 = ((Map<String, List<zzpz.zze>>)o).get(intValue2);
                        BitSet set4 = arrayMap2.get(intValue2);
                        BitSet set5 = arrayMap3.get(intValue2);
                        if (set4 == null) {
                            set4 = new BitSet();
                            arrayMap2.put(intValue2, set4);
                            set5 = new BitSet();
                            arrayMap3.put(intValue2, set5);
                        }
                        if (zza7.zzbab == null && !zza7.zzbac) {
                            final zzqb.zzf zzC2 = this.zzCj().zzC(s, intValue2);
                            if (zzC2 == null) {
                                zza7.zzbac = true;
                            }
                            else {
                                zza7.zzbab = zzC2;
                                for (int l = 0; l < 64 * zzC2.zzbaH.length; ++l) {
                                    if (zzaj.zza(zzC2.zzbaH, l)) {
                                        set4.set(l);
                                        set5.set(l);
                                    }
                                }
                            }
                        }
                        for (final zzpz.zze zze : list2) {
                            if (this.zzAo().zzQ(2)) {
                                this.zzAo().zzCK().zzd("Evaluating filter. audience, filter, property", intValue2, zze.zzaZv, zze.zzaZL);
                                this.zzAo().zzCK().zzj("Filter definition", zze);
                            }
                            if (zze.zzaZv == null || zze.zzaZv > 256) {
                                this.zzAo().zzCF().zzj("Invalid property filter ID. id", String.valueOf(zze.zzaZv));
                                set.add(intValue2);
                                break;
                            }
                            if (set5.get(zze.zzaZv)) {
                                this.zzAo().zzCK().zze("Property filter already evaluated true. audience ID, filter ID", intValue2, zze.zzaZv);
                            }
                            else {
                                final Boolean zza8 = this.zza(zze, zzg);
                                this.zzAo().zzCK().zzj("Property filter result", zza8);
                                if (zza8 == null) {
                                    set.add(intValue2);
                                }
                                else {
                                    set5.set(zze.zzaZv);
                                    if (!zza8) {
                                        continue;
                                    }
                                    set4.set(zze.zzaZv);
                                }
                            }
                        }
                    }
                }
            }
        }
        final zzqb.zza[] array3 = new zzqb.zza[arrayMap2.size()];
        final Iterator<Integer> iterator5 = arrayMap2.keySet().iterator();
        int n = 0;
        while (iterator5.hasNext()) {
            final int intValue3 = iterator5.next();
            if (!set.contains(intValue3)) {
                final zzqb.zza zza9 = arrayMap.get(intValue3);
                zzqb.zza zza10;
                if (zza9 == null) {
                    zza10 = new zzqb.zza();
                }
                else {
                    zza10 = zza9;
                }
                final int n2 = n + 1;
                array3[n] = zza10;
                zza10.zzaZr = intValue3;
                zza10.zzbaa = new zzqb.zzf();
                zza10.zzbaa.zzbaH = zzaj.zza(arrayMap2.get(intValue3));
                zza10.zzbaa.zzbaG = zzaj.zza(arrayMap3.get(intValue3));
                this.zzCj().zza(s, intValue3, zza10.zzbaa);
                n = n2;
            }
        }
        return Arrays.copyOf(array3, n);
    }
    
    boolean zzeQ(final String s) {
        return Pattern.matches("[+-]?[0-9]+", s);
    }
    
    boolean zzeR(final String s) {
        return Pattern.matches("[+-]?(([0-9]+\\.?)|([0-9]*\\.[0-9]+))", s);
    }
    
    @Override
    protected void zziJ() {
    }
}
