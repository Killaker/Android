package com.google.android.gms.internal;

import java.io.*;
import com.google.android.gms.tagmanager.*;
import java.util.*;

public class zzrs
{
    private static zzag.zza zza(final int n, final zzaf.zzf zzf, final zzag.zza[] array, final Set<Integer> set) throws zzg {
        int i = 0;
        if (set.contains(n)) {
            zzgC("Value cycle detected.  Current value reference: " + n + "." + "  Previous value references: " + set + ".");
        }
        final zzag.zza zza = zza(zzf.zziI, n, "values");
        if (array[n] != null) {
            return array[n];
        }
        set.add(n);
        final int type = zza.type;
        zzag.zza zza2 = null;
        switch (type) {
            case 2: {
                final zzaf.zzh zzp = zzp(zza);
                zza2 = zzo(zza);
                zza2.zzjy = new zzag.zza[zzp.zzjj.length];
                final int[] zzjj = zzp.zzjj;
                final int length = zzjj.length;
                int n2 = 0;
                while (i < length) {
                    final int n3 = zzjj[i];
                    final zzag.zza[] zzjy = zza2.zzjy;
                    final int n4 = n2 + 1;
                    zzjy[n2] = zza(n3, zzf, array, set);
                    ++i;
                    n2 = n4;
                }
                break;
            }
            case 3: {
                zza2 = zzo(zza);
                final zzaf.zzh zzp2 = zzp(zza);
                if (zzp2.zzjk.length != zzp2.zzjl.length) {
                    zzgC("Uneven map keys (" + zzp2.zzjk.length + ") and map values (" + zzp2.zzjl.length + ")");
                }
                zza2.zzjz = new zzag.zza[zzp2.zzjk.length];
                zza2.zzjA = new zzag.zza[zzp2.zzjk.length];
                final int[] zzjk = zzp2.zzjk;
                final int length2 = zzjk.length;
                int j = 0;
                int n5 = 0;
                while (j < length2) {
                    final int n6 = zzjk[j];
                    final zzag.zza[] zzjz = zza2.zzjz;
                    final int n7 = n5 + 1;
                    zzjz[n5] = zza(n6, zzf, array, set);
                    ++j;
                    n5 = n7;
                }
                final int[] zzjl = zzp2.zzjl;
                final int length3 = zzjl.length;
                int n8 = 0;
                while (i < length3) {
                    final int n9 = zzjl[i];
                    final zzag.zza[] zzjA = zza2.zzjA;
                    final int n10 = n8 + 1;
                    zzjA[n8] = zza(n9, zzf, array, set);
                    ++i;
                    n8 = n10;
                }
                break;
            }
            case 4: {
                zza2 = zzo(zza);
                zza2.zzjB = zzdf.zzg(zza(zzp(zza).zzjo, zzf, array, set));
                break;
            }
            case 7: {
                zza2 = zzo(zza);
                final zzaf.zzh zzp3 = zzp(zza);
                zza2.zzjF = new zzag.zza[zzp3.zzjn.length];
                final int[] zzjn = zzp3.zzjn;
                final int length4 = zzjn.length;
                int n11 = 0;
                while (i < length4) {
                    final int n12 = zzjn[i];
                    final zzag.zza[] zzjF = zza2.zzjF;
                    final int n13 = n11 + 1;
                    zzjF[n11] = zza(n12, zzf, array, set);
                    ++i;
                    n11 = n13;
                }
                break;
            }
            case 1:
            case 5:
            case 6:
            case 8: {
                zza2 = zza;
                break;
            }
        }
        if (zza2 == null) {
            zzgC("Invalid value: " + zza);
        }
        array[n] = zza2;
        set.remove(n);
        return zza2;
    }
    
    private static zza zza(final zzaf.zzb zzb, final zzaf.zzf zzf, final zzag.zza[] array, final int n) throws zzg {
        final zzb zzHH = zza.zzHH();
        final int[] zzit = zzb.zzit;
        for (int length = zzit.length, i = 0; i < length; ++i) {
            final zzaf.zze zze = zza(zzf.zziJ, Integer.valueOf(zzit[i]), "properties");
            final String s = zza(zzf.zziH, zze.key, "keys");
            final zzag.zza zza = zza(array, zze.value, "values");
            if (zzae.zzgU.toString().equals(s)) {
                zzHH.zzq(zza);
            }
            else {
                zzHH.zzb(s, zza);
            }
        }
        return zzHH.zzHJ();
    }
    
    private static zze zza(final zzaf.zzg zzg, final List<zza> list, final List<zza> list2, final List<zza> list3, final zzaf.zzf zzf) {
        final zzf zzHO = zze.zzHO();
        final int[] zziX = zzg.zziX;
        for (int length = zziX.length, i = 0; i < length; ++i) {
            zzHO.zzd((zza)list3.get(Integer.valueOf(zziX[i])));
        }
        final int[] zziY = zzg.zziY;
        for (int length2 = zziY.length, j = 0; j < length2; ++j) {
            zzHO.zze((zza)list3.get(Integer.valueOf(zziY[j])));
        }
        final int[] zziZ = zzg.zziZ;
        for (int length3 = zziZ.length, k = 0; k < length3; ++k) {
            zzHO.zzf((zza)list.get(Integer.valueOf(zziZ[k])));
        }
        final int[] zzjb = zzg.zzjb;
        for (int length4 = zzjb.length, l = 0; l < length4; ++l) {
            zzHO.zzgE(zzf.zziI[Integer.valueOf(zzjb[l])].zzjx);
        }
        final int[] zzja = zzg.zzja;
        for (int length5 = zzja.length, n = 0; n < length5; ++n) {
            zzHO.zzg((zza)list.get(Integer.valueOf(zzja[n])));
        }
        final int[] zzjc = zzg.zzjc;
        for (int length6 = zzjc.length, n2 = 0; n2 < length6; ++n2) {
            zzHO.zzgF(zzf.zziI[Integer.valueOf(zzjc[n2])].zzjx);
        }
        final int[] zzjd = zzg.zzjd;
        for (int length7 = zzjd.length, n3 = 0; n3 < length7; ++n3) {
            zzHO.zzh((zza)list2.get(Integer.valueOf(zzjd[n3])));
        }
        final int[] zzjf = zzg.zzjf;
        for (int length8 = zzjf.length, n4 = 0; n4 < length8; ++n4) {
            zzHO.zzgG(zzf.zziI[Integer.valueOf(zzjf[n4])].zzjx);
        }
        final int[] zzje = zzg.zzje;
        for (int length9 = zzje.length, n5 = 0; n5 < length9; ++n5) {
            zzHO.zzi((zza)list2.get(Integer.valueOf(zzje[n5])));
        }
        final int[] zzjg = zzg.zzjg;
        for (int length10 = zzjg.length, n6 = 0; n6 < length10; ++n6) {
            zzHO.zzgH(zzf.zziI[Integer.valueOf(zzjg[n6])].zzjx);
        }
        return zzHO.zzHZ();
    }
    
    private static <T> T zza(final T[] array, final int n, final String s) throws zzg {
        if (n < 0 || n >= array.length) {
            zzgC("Index out of bounds detected: " + n + " in " + s);
        }
        return array[n];
    }
    
    public static zzc zzb(final zzaf.zzf zzf) throws zzg {
        int i = 0;
        final zzag.zza[] array = new zzag.zza[zzf.zziI.length];
        for (int j = 0; j < zzf.zziI.length; ++j) {
            zza(j, zzf, array, new HashSet<Integer>(0));
        }
        final zzd zzHK = zzc.zzHK();
        final ArrayList<zza> list = new ArrayList<zza>();
        for (int k = 0; k < zzf.zziL.length; ++k) {
            list.add(zza(zzf.zziL[k], zzf, array, k));
        }
        final ArrayList<zza> list2 = new ArrayList<zza>();
        for (int l = 0; l < zzf.zziM.length; ++l) {
            list2.add(zza(zzf.zziM[l], zzf, array, l));
        }
        final ArrayList<zza> list3 = new ArrayList<zza>();
        for (int n = 0; n < zzf.zziK.length; ++n) {
            final zza zza = zza(zzf.zziK[n], zzf, array, n);
            zzHK.zzc(zza);
            list3.add(zza);
        }
        for (zzaf.zzg[] zziN = zzf.zziN; i < zziN.length; ++i) {
            zzHK.zzb(zza(zziN[i], list, list3, list2, zzf));
        }
        zzHK.zzgD(zzf.version);
        zzHK.zzko(zzf.zziV);
        return zzHK.zzHN();
    }
    
    public static void zzb(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[1024];
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
        }
    }
    
    private static void zzgC(final String s) throws zzg {
        zzbg.e(s);
        throw new zzg(s);
    }
    
    public static zzag.zza zzo(final zzag.zza zza) {
        final zzag.zza zza2 = new zzag.zza();
        zza2.type = zza.type;
        zza2.zzjG = zza.zzjG.clone();
        if (zza.zzjH) {
            zza2.zzjH = zza.zzjH;
        }
        return zza2;
    }
    
    private static zzaf.zzh zzp(final zzag.zza zza) throws zzg {
        if (zza.zza(zzaf.zzh.zzjh) == null) {
            zzgC("Expected a ServingValue and didn't get one. Value is: " + zza);
        }
        return zza.zza(zzaf.zzh.zzjh);
    }
    
    public static class zza
    {
        private final zzag.zza zzbkI;
        private final Map<String, zzag.zza> zzbmi;
        
        private zza(final Map<String, zzag.zza> zzbmi, final zzag.zza zzbkI) {
            this.zzbmi = zzbmi;
            this.zzbkI = zzbkI;
        }
        
        public static zzb zzHH() {
            return new zzb();
        }
        
        @Override
        public String toString() {
            return "Properties: " + this.zzHI() + " pushAfterEvaluate: " + this.zzbkI;
        }
        
        public Map<String, zzag.zza> zzHI() {
            return Collections.unmodifiableMap((Map<? extends String, ? extends zzag.zza>)this.zzbmi);
        }
        
        public zzag.zza zzHh() {
            return this.zzbkI;
        }
        
        public void zza(final String s, final zzag.zza zza) {
            this.zzbmi.put(s, zza);
        }
    }
    
    public static class zzb
    {
        private zzag.zza zzbkI;
        private final Map<String, zzag.zza> zzbmi;
        
        private zzb() {
            this.zzbmi = new HashMap<String, zzag.zza>();
        }
        
        public zza zzHJ() {
            return new zza((Map)this.zzbmi, this.zzbkI);
        }
        
        public zzb zzb(final String s, final zzag.zza zza) {
            this.zzbmi.put(s, zza);
            return this;
        }
        
        public zzb zzq(final zzag.zza zzbkI) {
            this.zzbkI = zzbkI;
            return this;
        }
    }
    
    public static class zzc
    {
        private final String zzadc;
        private final List<zze> zzbmj;
        private final Map<String, List<zza>> zzbmk;
        private final int zzbml;
        
        private zzc(final List<zze> list, final Map<String, List<zza>> map, final String zzadc, final int zzbml) {
            this.zzbmj = Collections.unmodifiableList((List<? extends zze>)list);
            this.zzbmk = Collections.unmodifiableMap((Map<? extends String, ? extends List<zza>>)map);
            this.zzadc = zzadc;
            this.zzbml = zzbml;
        }
        
        public static zzd zzHK() {
            return new zzd();
        }
        
        public String getVersion() {
            return this.zzadc;
        }
        
        @Override
        public String toString() {
            return "Rules: " + this.zzHL() + "  Macros: " + this.zzbmk;
        }
        
        public List<zze> zzHL() {
            return this.zzbmj;
        }
        
        public Map<String, List<zza>> zzHM() {
            return this.zzbmk;
        }
    }
    
    public static class zzd
    {
        private String zzadc;
        private final List<zze> zzbmj;
        private final Map<String, List<zza>> zzbmk;
        private int zzbml;
        
        private zzd() {
            this.zzbmj = new ArrayList<zze>();
            this.zzbmk = new HashMap<String, List<zza>>();
            this.zzadc = "";
            this.zzbml = 0;
        }
        
        public zzc zzHN() {
            return new zzc((List)this.zzbmj, (Map)this.zzbmk, this.zzadc, this.zzbml);
        }
        
        public zzd zzb(final zze zze) {
            this.zzbmj.add(zze);
            return this;
        }
        
        public zzd zzc(final zza zza) {
            final String zzg = zzdf.zzg(zza.zzHI().get(zzae.zzfR.toString()));
            List<zza> list = this.zzbmk.get(zzg);
            if (list == null) {
                list = new ArrayList<zza>();
                this.zzbmk.put(zzg, list);
            }
            list.add(zza);
            return this;
        }
        
        public zzd zzgD(final String zzadc) {
            this.zzadc = zzadc;
            return this;
        }
        
        public zzd zzko(final int zzbml) {
            this.zzbml = zzbml;
            return this;
        }
    }
    
    public static class zze
    {
        private final List<zza> zzbmm;
        private final List<zza> zzbmn;
        private final List<zza> zzbmo;
        private final List<zza> zzbmp;
        private final List<zza> zzbmq;
        private final List<zza> zzbmr;
        private final List<String> zzbms;
        private final List<String> zzbmt;
        private final List<String> zzbmu;
        private final List<String> zzbmv;
        
        private zze(final List<zza> list, final List<zza> list2, final List<zza> list3, final List<zza> list4, final List<zza> list5, final List<zza> list6, final List<String> list7, final List<String> list8, final List<String> list9, final List<String> list10) {
            this.zzbmm = Collections.unmodifiableList((List<? extends zza>)list);
            this.zzbmn = Collections.unmodifiableList((List<? extends zza>)list2);
            this.zzbmo = Collections.unmodifiableList((List<? extends zza>)list3);
            this.zzbmp = Collections.unmodifiableList((List<? extends zza>)list4);
            this.zzbmq = Collections.unmodifiableList((List<? extends zza>)list5);
            this.zzbmr = Collections.unmodifiableList((List<? extends zza>)list6);
            this.zzbms = Collections.unmodifiableList((List<? extends String>)list7);
            this.zzbmt = Collections.unmodifiableList((List<? extends String>)list8);
            this.zzbmu = Collections.unmodifiableList((List<? extends String>)list9);
            this.zzbmv = Collections.unmodifiableList((List<? extends String>)list10);
        }
        
        public static zzf zzHO() {
            return new zzf();
        }
        
        @Override
        public String toString() {
            return "Positive predicates: " + this.zzHP() + "  Negative predicates: " + this.zzHQ() + "  Add tags: " + this.zzHR() + "  Remove tags: " + this.zzHS() + "  Add macros: " + this.zzHT() + "  Remove macros: " + this.zzHY();
        }
        
        public List<zza> zzHP() {
            return this.zzbmm;
        }
        
        public List<zza> zzHQ() {
            return this.zzbmn;
        }
        
        public List<zza> zzHR() {
            return this.zzbmo;
        }
        
        public List<zza> zzHS() {
            return this.zzbmp;
        }
        
        public List<zza> zzHT() {
            return this.zzbmq;
        }
        
        public List<String> zzHU() {
            return this.zzbms;
        }
        
        public List<String> zzHV() {
            return this.zzbmt;
        }
        
        public List<String> zzHW() {
            return this.zzbmu;
        }
        
        public List<String> zzHX() {
            return this.zzbmv;
        }
        
        public List<zza> zzHY() {
            return this.zzbmr;
        }
    }
    
    public static class zzf
    {
        private final List<zza> zzbmm;
        private final List<zza> zzbmn;
        private final List<zza> zzbmo;
        private final List<zza> zzbmp;
        private final List<zza> zzbmq;
        private final List<zza> zzbmr;
        private final List<String> zzbms;
        private final List<String> zzbmt;
        private final List<String> zzbmu;
        private final List<String> zzbmv;
        
        private zzf() {
            this.zzbmm = new ArrayList<zza>();
            this.zzbmn = new ArrayList<zza>();
            this.zzbmo = new ArrayList<zza>();
            this.zzbmp = new ArrayList<zza>();
            this.zzbmq = new ArrayList<zza>();
            this.zzbmr = new ArrayList<zza>();
            this.zzbms = new ArrayList<String>();
            this.zzbmt = new ArrayList<String>();
            this.zzbmu = new ArrayList<String>();
            this.zzbmv = new ArrayList<String>();
        }
        
        public zze zzHZ() {
            return new zze((List)this.zzbmm, (List)this.zzbmn, (List)this.zzbmo, (List)this.zzbmp, (List)this.zzbmq, (List)this.zzbmr, (List)this.zzbms, (List)this.zzbmt, (List)this.zzbmu, (List)this.zzbmv);
        }
        
        public zzf zzd(final zza zza) {
            this.zzbmm.add(zza);
            return this;
        }
        
        public zzf zze(final zza zza) {
            this.zzbmn.add(zza);
            return this;
        }
        
        public zzf zzf(final zza zza) {
            this.zzbmo.add(zza);
            return this;
        }
        
        public zzf zzg(final zza zza) {
            this.zzbmp.add(zza);
            return this;
        }
        
        public zzf zzgE(final String s) {
            this.zzbmu.add(s);
            return this;
        }
        
        public zzf zzgF(final String s) {
            this.zzbmv.add(s);
            return this;
        }
        
        public zzf zzgG(final String s) {
            this.zzbms.add(s);
            return this;
        }
        
        public zzf zzgH(final String s) {
            this.zzbmt.add(s);
            return this;
        }
        
        public zzf zzh(final zza zza) {
            this.zzbmq.add(zza);
            return this;
        }
        
        public zzf zzi(final zza zza) {
            this.zzbmr.add(zza);
            return this;
        }
    }
    
    public static class zzg extends Exception
    {
        public zzg(final String s) {
            super(s);
        }
    }
}
