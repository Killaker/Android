package com.google.android.gms.internal;

import java.io.*;

public interface zzrq
{
    public static final class zza extends zzso<zza>
    {
        public long zzbmd;
        public zzaf.zzj zzbme;
        public zzaf.zzf zzju;
        
        public zza() {
            this.zzHG();
        }
        
        public static zza zzy(final byte[] array) throws zzst {
            return zzsu.mergeFrom(new zza(), array);
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zza;
                b = false;
                if (b2) {
                    final zza zza = (zza)o;
                    final long n = lcmp(this.zzbmd, zza.zzbmd);
                    b = false;
                    if (n == 0) {
                        if (this.zzju == null) {
                            final zzaf.zzf zzju = zza.zzju;
                            b = false;
                            if (zzju != null) {
                                return b;
                            }
                        }
                        else if (!this.zzju.equals(zza.zzju)) {
                            return false;
                        }
                        if (this.zzbme == null) {
                            final zzaf.zzj zzbme = zza.zzbme;
                            b = false;
                            if (zzbme != null) {
                                return b;
                            }
                        }
                        else if (!this.zzbme.equals(zza.zzbme)) {
                            return false;
                        }
                        if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                            if (zza.zzbuj != null) {
                                final boolean empty = zza.zzbuj.isEmpty();
                                b = false;
                                if (!empty) {
                                    return b;
                                }
                            }
                            return true;
                        }
                        return this.zzbuj.equals(zza.zzbuj);
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + (int)(this.zzbmd ^ this.zzbmd >>> 32));
            int hashCode;
            if (this.zzju == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzju.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzbme == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzbme.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            final zzsq zzbuj = this.zzbuj;
            int hashCode3 = 0;
            if (zzbuj != null) {
                final boolean empty = this.zzbuj.isEmpty();
                hashCode3 = 0;
                if (!empty) {
                    hashCode3 = this.zzbuj.hashCode();
                }
            }
            return n3 + hashCode3;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            zzsn.zzb(1, this.zzbmd);
            if (this.zzju != null) {
                zzsn.zza(2, this.zzju);
            }
            if (this.zzbme != null) {
                zzsn.zza(3, this.zzbme);
            }
            super.writeTo(zzsn);
        }
        
        public zza zzHG() {
            this.zzbmd = 0L;
            this.zzju = null;
            this.zzbme = null;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zza zzJ(final zzsm zzsm) throws IOException {
        Label_0057:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0057;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0057;
                    }
                    case 8: {
                        this.zzbmd = zzsm.zzJa();
                        continue;
                    }
                    case 18: {
                        if (this.zzju == null) {
                            this.zzju = new zzaf.zzf();
                        }
                        zzsm.zza(this.zzju);
                        continue;
                    }
                    case 26: {
                        if (this.zzbme == null) {
                            this.zzbme = new zzaf.zzj();
                        }
                        zzsm.zza(this.zzbme);
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int n = super.zzz() + zzsn.zzd(1, this.zzbmd);
            if (this.zzju != null) {
                n += zzsn.zzc(2, this.zzju);
            }
            if (this.zzbme != null) {
                n += zzsn.zzc(3, this.zzbme);
            }
            return n;
        }
    }
}
