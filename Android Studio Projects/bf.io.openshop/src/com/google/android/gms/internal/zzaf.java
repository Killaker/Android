package com.google.android.gms.internal;

import java.io.*;

public interface zzaf
{
    public static final class zza extends zzso<zza>
    {
        public int level;
        public int zziq;
        public int zzir;
        
        public zza() {
            this.zzB();
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
                    final int level = this.level;
                    final int level2 = zza.level;
                    b = false;
                    if (level == level2) {
                        final int zziq = this.zziq;
                        final int zziq2 = zza.zziq;
                        b = false;
                        if (zziq == zziq2) {
                            final int zzir = this.zzir;
                            final int zzir2 = zza.zzir;
                            b = false;
                            if (zzir == zzir2) {
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
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + this.level) + this.zziq) + this.zzir);
            int hashCode;
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzbuj.hashCode();
            }
            return hashCode + n;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.level != 1) {
                zzsn.zzA(1, this.level);
            }
            if (this.zziq != 0) {
                zzsn.zzA(2, this.zziq);
            }
            if (this.zzir != 0) {
                zzsn.zzA(3, this.zzir);
            }
            super.writeTo(zzsn);
        }
        
        public zza zzB() {
            this.level = 1;
            this.zziq = 0;
            this.zzir = 0;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zza zza(final zzsm zzsm) throws IOException {
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
                        final int zzJb = zzsm.zzJb();
                        switch (zzJb) {
                            default: {
                                continue;
                            }
                            case 1:
                            case 2:
                            case 3: {
                                this.level = zzJb;
                                continue;
                            }
                        }
                        break;
                    }
                    case 16: {
                        this.zziq = zzsm.zzJb();
                        continue;
                    }
                    case 24: {
                        this.zzir = zzsm.zzJb();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.level != 1) {
                zzz += zzsn.zzC(1, this.level);
            }
            if (this.zziq != 0) {
                zzz += zzsn.zzC(2, this.zziq);
            }
            if (this.zzir != 0) {
                zzz += zzsn.zzC(3, this.zzir);
            }
            return zzz;
        }
    }
    
    public static final class zzb extends zzso<zzb>
    {
        private static volatile zzb[] zzis;
        public int name;
        public int[] zzit;
        public int zziu;
        public boolean zziv;
        public boolean zziw;
        
        public zzb() {
            this.zzD();
        }
        
        public static zzb[] zzC() {
            Label_0027: {
                if (zzb.zzis != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzb.zzis == null) {
                        zzb.zzis = new zzb[0];
                    }
                    return zzb.zzis;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzb;
                b = false;
                if (b2) {
                    final zzb zzb = (zzb)o;
                    final boolean equals = zzss.equals(this.zzit, zzb.zzit);
                    b = false;
                    if (equals) {
                        final int zziu = this.zziu;
                        final int zziu2 = zzb.zziu;
                        b = false;
                        if (zziu == zziu2) {
                            final int name = this.name;
                            final int name2 = zzb.name;
                            b = false;
                            if (name == name2) {
                                final boolean zziv = this.zziv;
                                final boolean zziv2 = zzb.zziv;
                                b = false;
                                if (zziv == zziv2) {
                                    final boolean zziw = this.zziw;
                                    final boolean zziw2 = zzb.zziw;
                                    b = false;
                                    if (zziw == zziw2) {
                                        if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                            if (zzb.zzbuj != null) {
                                                final boolean empty = zzb.zzbuj.isEmpty();
                                                b = false;
                                                if (!empty) {
                                                    return b;
                                                }
                                            }
                                            return true;
                                        }
                                        return this.zzbuj.equals(zzb.zzbuj);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            int n = 1231;
            final int n2 = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzit)) + this.zziu) + this.name);
            int n3;
            if (this.zziv) {
                n3 = n;
            }
            else {
                n3 = 1237;
            }
            final int n4 = 31 * (n3 + n2);
            if (!this.zziw) {
                n = 1237;
            }
            final int n5 = 31 * (n4 + n);
            int hashCode;
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzbuj.hashCode();
            }
            return hashCode + n5;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zziw) {
                zzsn.zze(1, this.zziw);
            }
            zzsn.zzA(2, this.zziu);
            if (this.zzit != null && this.zzit.length > 0) {
                for (int i = 0; i < this.zzit.length; ++i) {
                    zzsn.zzA(3, this.zzit[i]);
                }
            }
            if (this.name != 0) {
                zzsn.zzA(4, this.name);
            }
            if (this.zziv) {
                zzsn.zze(6, this.zziv);
            }
            super.writeTo(zzsn);
        }
        
        public zzb zzD() {
            this.zzit = zzsx.zzbuw;
            this.zziu = 0;
            this.name = 0;
            this.zziv = false;
            this.zziw = false;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzb zzb(final zzsm zzsm) throws IOException {
        Label_0081:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0081;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0081;
                    }
                    case 8: {
                        this.zziw = zzsm.zzJc();
                        continue;
                    }
                    case 16: {
                        this.zziu = zzsm.zzJb();
                        continue;
                    }
                    case 24: {
                        final int zzc = zzsx.zzc(zzsm, 24);
                        int i;
                        if (this.zzit == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzit.length;
                        }
                        final int[] zzit = new int[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzit, 0, zzit, 0, i);
                        }
                        while (i < -1 + zzit.length) {
                            zzit[i] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++i;
                        }
                        zzit[i] = zzsm.zzJb();
                        this.zzit = zzit;
                        continue;
                    }
                    case 26: {
                        final int zzmq = zzsm.zzmq(zzsm.zzJf());
                        final int position = zzsm.getPosition();
                        int n = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n;
                        }
                        zzsm.zzms(position);
                        int j;
                        if (this.zzit == null) {
                            j = 0;
                        }
                        else {
                            j = this.zzit.length;
                        }
                        final int[] zzit2 = new int[n + j];
                        if (j != 0) {
                            System.arraycopy(this.zzit, 0, zzit2, 0, j);
                        }
                        while (j < zzit2.length) {
                            zzit2[j] = zzsm.zzJb();
                            ++j;
                        }
                        this.zzit = zzit2;
                        zzsm.zzmr(zzmq);
                        continue;
                    }
                    case 32: {
                        this.name = zzsm.zzJb();
                        continue;
                    }
                    case 48: {
                        this.zziv = zzsm.zzJc();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int n = 0;
            int zzz = super.zzz();
            if (this.zziw) {
                zzz += zzsn.zzf(1, this.zziw);
            }
            final int n2 = zzz + zzsn.zzC(2, this.zziu);
            int n3;
            if (this.zzit != null && this.zzit.length > 0) {
                for (int i = 0; i < this.zzit.length; ++i) {
                    n += zzsn.zzmx(this.zzit[i]);
                }
                n3 = n2 + n + 1 * this.zzit.length;
            }
            else {
                n3 = n2;
            }
            if (this.name != 0) {
                n3 += zzsn.zzC(4, this.name);
            }
            if (this.zziv) {
                n3 += zzsn.zzf(6, this.zziv);
            }
            return n3;
        }
    }
    
    public static final class zzc extends zzso<zzc>
    {
        private static volatile zzc[] zzix;
        public String key;
        public boolean zziA;
        public long zziB;
        public long zziy;
        public long zziz;
        
        public zzc() {
            this.zzF();
        }
        
        public static zzc[] zzE() {
            Label_0027: {
                if (zzc.zzix != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzc.zzix == null) {
                        zzc.zzix = new zzc[0];
                    }
                    return zzc.zzix;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzc;
                b = false;
                if (b2) {
                    final zzc zzc = (zzc)o;
                    if (this.key == null) {
                        final String key = zzc.key;
                        b = false;
                        if (key != null) {
                            return b;
                        }
                    }
                    else if (!this.key.equals(zzc.key)) {
                        return false;
                    }
                    final long n = lcmp(this.zziy, zzc.zziy);
                    b = false;
                    if (n == 0) {
                        final long n2 = lcmp(this.zziz, zzc.zziz);
                        b = false;
                        if (n2 == 0) {
                            final boolean zziA = this.zziA;
                            final boolean zziA2 = zzc.zziA;
                            b = false;
                            if (zziA == zziA2) {
                                final long n3 = lcmp(this.zziB, zzc.zziB);
                                b = false;
                                if (n3 == 0) {
                                    if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                        if (zzc.zzbuj != null) {
                                            final boolean empty = zzc.zzbuj.isEmpty();
                                            b = false;
                                            if (!empty) {
                                                return b;
                                            }
                                        }
                                        return true;
                                    }
                                    return this.zzbuj.equals(zzc.zzbuj);
                                }
                            }
                        }
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.key == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.key.hashCode();
            }
            final int n2 = 31 * (31 * (31 * (hashCode + n) + (int)(this.zziy ^ this.zziy >>> 32)) + (int)(this.zziz ^ this.zziz >>> 32));
            int n3;
            if (this.zziA) {
                n3 = 1231;
            }
            else {
                n3 = 1237;
            }
            final int n4 = 31 * (31 * (n3 + n2) + (int)(this.zziB ^ this.zziB >>> 32));
            final zzsq zzbuj = this.zzbuj;
            int hashCode2 = 0;
            if (zzbuj != null) {
                final boolean empty = this.zzbuj.isEmpty();
                hashCode2 = 0;
                if (!empty) {
                    hashCode2 = this.zzbuj.hashCode();
                }
            }
            return n4 + hashCode2;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (!this.key.equals("")) {
                zzsn.zzn(1, this.key);
            }
            if (this.zziy != 0L) {
                zzsn.zzb(2, this.zziy);
            }
            if (this.zziz != 2147483647L) {
                zzsn.zzb(3, this.zziz);
            }
            if (this.zziA) {
                zzsn.zze(4, this.zziA);
            }
            if (this.zziB != 0L) {
                zzsn.zzb(5, this.zziB);
            }
            super.writeTo(zzsn);
        }
        
        public zzc zzF() {
            this.key = "";
            this.zziy = 0L;
            this.zziz = 2147483647L;
            this.zziA = false;
            this.zziB = 0L;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzc zzc(final zzsm zzsm) throws IOException {
        Label_0073:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0073;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0073;
                    }
                    case 10: {
                        this.key = zzsm.readString();
                        continue;
                    }
                    case 16: {
                        this.zziy = zzsm.zzJa();
                        continue;
                    }
                    case 24: {
                        this.zziz = zzsm.zzJa();
                        continue;
                    }
                    case 32: {
                        this.zziA = zzsm.zzJc();
                        continue;
                    }
                    case 40: {
                        this.zziB = zzsm.zzJa();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (!this.key.equals("")) {
                zzz += zzsn.zzo(1, this.key);
            }
            if (this.zziy != 0L) {
                zzz += zzsn.zzd(2, this.zziy);
            }
            if (this.zziz != 2147483647L) {
                zzz += zzsn.zzd(3, this.zziz);
            }
            if (this.zziA) {
                zzz += zzsn.zzf(4, this.zziA);
            }
            if (this.zziB != 0L) {
                zzz += zzsn.zzd(5, this.zziB);
            }
            return zzz;
        }
    }
    
    public static final class zzd extends zzso<zzd>
    {
        public zzag.zza[] zziC;
        public zzag.zza[] zziD;
        public zzc[] zziE;
        
        public zzd() {
            this.zzG();
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzd;
                b = false;
                if (b2) {
                    final zzd zzd = (zzd)o;
                    final boolean equals = zzss.equals(this.zziC, zzd.zziC);
                    b = false;
                    if (equals) {
                        final boolean equals2 = zzss.equals(this.zziD, zzd.zziD);
                        b = false;
                        if (equals2) {
                            final boolean equals3 = zzss.equals(this.zziE, zzd.zziE);
                            b = false;
                            if (equals3) {
                                if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                    if (zzd.zzbuj != null) {
                                        final boolean empty = zzd.zzbuj.isEmpty();
                                        b = false;
                                        if (!empty) {
                                            return b;
                                        }
                                    }
                                    return true;
                                }
                                return this.zzbuj.equals(zzd.zzbuj);
                            }
                        }
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zziC)) + zzss.hashCode(this.zziD)) + zzss.hashCode(this.zziE));
            int hashCode;
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzbuj.hashCode();
            }
            return hashCode + n;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zziC != null && this.zziC.length > 0) {
                for (int i = 0; i < this.zziC.length; ++i) {
                    final zzag.zza zza = this.zziC[i];
                    if (zza != null) {
                        zzsn.zza(1, zza);
                    }
                }
            }
            if (this.zziD != null && this.zziD.length > 0) {
                for (int j = 0; j < this.zziD.length; ++j) {
                    final zzag.zza zza2 = this.zziD[j];
                    if (zza2 != null) {
                        zzsn.zza(2, zza2);
                    }
                }
            }
            if (this.zziE != null) {
                final int length = this.zziE.length;
                int k = 0;
                if (length > 0) {
                    while (k < this.zziE.length) {
                        final zzc zzc = this.zziE[k];
                        if (zzc != null) {
                            zzsn.zza(3, zzc);
                        }
                        ++k;
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zzd zzG() {
            this.zziC = zzag.zza.zzQ();
            this.zziD = zzag.zza.zzQ();
            this.zziE = zzc.zzE();
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzd zzd(final zzsm zzsm) throws IOException {
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
                    case 10: {
                        final int zzc = zzsx.zzc(zzsm, 10);
                        int i;
                        if (this.zziC == null) {
                            i = 0;
                        }
                        else {
                            i = this.zziC.length;
                        }
                        final zzag.zza[] zziC = new zzag.zza[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zziC, 0, zziC, 0, i);
                        }
                        while (i < -1 + zziC.length) {
                            zzsm.zza(zziC[i] = new zzag.zza());
                            zzsm.zzIX();
                            ++i;
                        }
                        zzsm.zza(zziC[i] = new zzag.zza());
                        this.zziC = zziC;
                        continue;
                    }
                    case 18: {
                        final int zzc2 = zzsx.zzc(zzsm, 18);
                        int j;
                        if (this.zziD == null) {
                            j = 0;
                        }
                        else {
                            j = this.zziD.length;
                        }
                        final zzag.zza[] zziD = new zzag.zza[zzc2 + j];
                        if (j != 0) {
                            System.arraycopy(this.zziD, 0, zziD, 0, j);
                        }
                        while (j < -1 + zziD.length) {
                            zzsm.zza(zziD[j] = new zzag.zza());
                            zzsm.zzIX();
                            ++j;
                        }
                        zzsm.zza(zziD[j] = new zzag.zza());
                        this.zziD = zziD;
                        continue;
                    }
                    case 26: {
                        final int zzc3 = zzsx.zzc(zzsm, 26);
                        int k;
                        if (this.zziE == null) {
                            k = 0;
                        }
                        else {
                            k = this.zziE.length;
                        }
                        final zzc[] zziE = new zzc[zzc3 + k];
                        if (k != 0) {
                            System.arraycopy(this.zziE, 0, zziE, 0, k);
                        }
                        while (k < -1 + zziE.length) {
                            zzsm.zza(zziE[k] = new zzc());
                            zzsm.zzIX();
                            ++k;
                        }
                        zzsm.zza(zziE[k] = new zzc());
                        this.zziE = zziE;
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zziC != null && this.zziC.length > 0) {
                int n = zzz;
                for (int i = 0; i < this.zziC.length; ++i) {
                    final zzag.zza zza = this.zziC[i];
                    if (zza != null) {
                        n += zzsn.zzc(1, zza);
                    }
                }
                zzz = n;
            }
            if (this.zziD != null && this.zziD.length > 0) {
                int n2 = zzz;
                for (int j = 0; j < this.zziD.length; ++j) {
                    final zzag.zza zza2 = this.zziD[j];
                    if (zza2 != null) {
                        n2 += zzsn.zzc(2, zza2);
                    }
                }
                zzz = n2;
            }
            if (this.zziE != null) {
                final int length = this.zziE.length;
                int k = 0;
                if (length > 0) {
                    while (k < this.zziE.length) {
                        final zzc zzc = this.zziE[k];
                        if (zzc != null) {
                            zzz += zzsn.zzc(3, zzc);
                        }
                        ++k;
                    }
                }
            }
            return zzz;
        }
    }
    
    public static final class zze extends zzso<zze>
    {
        private static volatile zze[] zziF;
        public int key;
        public int value;
        
        public zze() {
            this.zzI();
        }
        
        public static zze[] zzH() {
            Label_0027: {
                if (zze.zziF != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zze.zziF == null) {
                        zze.zziF = new zze[0];
                    }
                    return zze.zziF;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zze;
                b = false;
                if (b2) {
                    final zze zze = (zze)o;
                    final int key = this.key;
                    final int key2 = zze.key;
                    b = false;
                    if (key == key2) {
                        final int value = this.value;
                        final int value2 = zze.value;
                        b = false;
                        if (value == value2) {
                            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                if (zze.zzbuj != null) {
                                    final boolean empty = zze.zzbuj.isEmpty();
                                    b = false;
                                    if (!empty) {
                                        return b;
                                    }
                                }
                                return true;
                            }
                            return this.zzbuj.equals(zze.zzbuj);
                        }
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + this.key) + this.value);
            int hashCode;
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzbuj.hashCode();
            }
            return hashCode + n;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            zzsn.zzA(1, this.key);
            zzsn.zzA(2, this.value);
            super.writeTo(zzsn);
        }
        
        public zze zzI() {
            this.key = 0;
            this.value = 0;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zze zze(final zzsm zzsm) throws IOException {
        Label_0049:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0049;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0049;
                    }
                    case 8: {
                        this.key = zzsm.zzJb();
                        continue;
                    }
                    case 16: {
                        this.value = zzsm.zzJb();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            return super.zzz() + zzsn.zzC(1, this.key) + zzsn.zzC(2, this.value);
        }
    }
    
    public static final class zzf extends zzso<zzf>
    {
        public String version;
        public String[] zziG;
        public String[] zziH;
        public zzag.zza[] zziI;
        public zze[] zziJ;
        public zzb[] zziK;
        public zzb[] zziL;
        public zzb[] zziM;
        public zzg[] zziN;
        public String zziO;
        public String zziP;
        public String zziQ;
        public zza zziR;
        public float zziS;
        public boolean zziT;
        public String[] zziU;
        public int zziV;
        
        public zzf() {
            this.zzJ();
        }
        
        public static zzf zzc(final byte[] array) throws zzst {
            return zzsu.mergeFrom(new zzf(), array);
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzf;
                b = false;
                if (b2) {
                    final zzf zzf = (zzf)o;
                    final boolean equals = zzss.equals(this.zziG, zzf.zziG);
                    b = false;
                    if (equals) {
                        final boolean equals2 = zzss.equals(this.zziH, zzf.zziH);
                        b = false;
                        if (equals2) {
                            final boolean equals3 = zzss.equals(this.zziI, zzf.zziI);
                            b = false;
                            if (equals3) {
                                final boolean equals4 = zzss.equals(this.zziJ, zzf.zziJ);
                                b = false;
                                if (equals4) {
                                    final boolean equals5 = zzss.equals(this.zziK, zzf.zziK);
                                    b = false;
                                    if (equals5) {
                                        final boolean equals6 = zzss.equals(this.zziL, zzf.zziL);
                                        b = false;
                                        if (equals6) {
                                            final boolean equals7 = zzss.equals(this.zziM, zzf.zziM);
                                            b = false;
                                            if (equals7) {
                                                final boolean equals8 = zzss.equals(this.zziN, zzf.zziN);
                                                b = false;
                                                if (equals8) {
                                                    if (this.zziO == null) {
                                                        final String zziO = zzf.zziO;
                                                        b = false;
                                                        if (zziO != null) {
                                                            return b;
                                                        }
                                                    }
                                                    else if (!this.zziO.equals(zzf.zziO)) {
                                                        return false;
                                                    }
                                                    if (this.zziP == null) {
                                                        final String zziP = zzf.zziP;
                                                        b = false;
                                                        if (zziP != null) {
                                                            return b;
                                                        }
                                                    }
                                                    else if (!this.zziP.equals(zzf.zziP)) {
                                                        return false;
                                                    }
                                                    if (this.zziQ == null) {
                                                        final String zziQ = zzf.zziQ;
                                                        b = false;
                                                        if (zziQ != null) {
                                                            return b;
                                                        }
                                                    }
                                                    else if (!this.zziQ.equals(zzf.zziQ)) {
                                                        return false;
                                                    }
                                                    if (this.version == null) {
                                                        final String version = zzf.version;
                                                        b = false;
                                                        if (version != null) {
                                                            return b;
                                                        }
                                                    }
                                                    else if (!this.version.equals(zzf.version)) {
                                                        return false;
                                                    }
                                                    if (this.zziR == null) {
                                                        final zza zziR = zzf.zziR;
                                                        b = false;
                                                        if (zziR != null) {
                                                            return b;
                                                        }
                                                    }
                                                    else if (!this.zziR.equals(zzf.zziR)) {
                                                        return false;
                                                    }
                                                    final int floatToIntBits = Float.floatToIntBits(this.zziS);
                                                    final int floatToIntBits2 = Float.floatToIntBits(zzf.zziS);
                                                    b = false;
                                                    if (floatToIntBits == floatToIntBits2) {
                                                        final boolean zziT = this.zziT;
                                                        final boolean zziT2 = zzf.zziT;
                                                        b = false;
                                                        if (zziT == zziT2) {
                                                            final boolean equals9 = zzss.equals(this.zziU, zzf.zziU);
                                                            b = false;
                                                            if (equals9) {
                                                                final int zziV = this.zziV;
                                                                final int zziV2 = zzf.zziV;
                                                                b = false;
                                                                if (zziV == zziV2) {
                                                                    if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                                                        if (zzf.zzbuj != null) {
                                                                            final boolean empty = zzf.zzbuj.isEmpty();
                                                                            b = false;
                                                                            if (!empty) {
                                                                                return b;
                                                                            }
                                                                        }
                                                                        return true;
                                                                    }
                                                                    return this.zzbuj.equals(zzf.zzbuj);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zziG)) + zzss.hashCode(this.zziH)) + zzss.hashCode(this.zziI)) + zzss.hashCode(this.zziJ)) + zzss.hashCode(this.zziK)) + zzss.hashCode(this.zziL)) + zzss.hashCode(this.zziM)) + zzss.hashCode(this.zziN));
            int hashCode;
            if (this.zziO == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zziO.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zziP == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zziP.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            int hashCode3;
            if (this.zziQ == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zziQ.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            int hashCode4;
            if (this.version == null) {
                hashCode4 = 0;
            }
            else {
                hashCode4 = this.version.hashCode();
            }
            final int n5 = 31 * (hashCode4 + n4);
            int hashCode5;
            if (this.zziR == null) {
                hashCode5 = 0;
            }
            else {
                hashCode5 = this.zziR.hashCode();
            }
            final int n6 = 31 * (31 * (hashCode5 + n5) + Float.floatToIntBits(this.zziS));
            int n7;
            if (this.zziT) {
                n7 = 1231;
            }
            else {
                n7 = 1237;
            }
            final int n8 = 31 * (31 * (31 * (n7 + n6) + zzss.hashCode(this.zziU)) + this.zziV);
            final zzsq zzbuj = this.zzbuj;
            int hashCode6 = 0;
            if (zzbuj != null) {
                final boolean empty = this.zzbuj.isEmpty();
                hashCode6 = 0;
                if (!empty) {
                    hashCode6 = this.zzbuj.hashCode();
                }
            }
            return n8 + hashCode6;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zziH != null && this.zziH.length > 0) {
                for (int i = 0; i < this.zziH.length; ++i) {
                    final String s = this.zziH[i];
                    if (s != null) {
                        zzsn.zzn(1, s);
                    }
                }
            }
            if (this.zziI != null && this.zziI.length > 0) {
                for (int j = 0; j < this.zziI.length; ++j) {
                    final zzag.zza zza = this.zziI[j];
                    if (zza != null) {
                        zzsn.zza(2, zza);
                    }
                }
            }
            if (this.zziJ != null && this.zziJ.length > 0) {
                for (int k = 0; k < this.zziJ.length; ++k) {
                    final zze zze = this.zziJ[k];
                    if (zze != null) {
                        zzsn.zza(3, zze);
                    }
                }
            }
            if (this.zziK != null && this.zziK.length > 0) {
                for (int l = 0; l < this.zziK.length; ++l) {
                    final zzb zzb = this.zziK[l];
                    if (zzb != null) {
                        zzsn.zza(4, zzb);
                    }
                }
            }
            if (this.zziL != null && this.zziL.length > 0) {
                for (int n = 0; n < this.zziL.length; ++n) {
                    final zzb zzb2 = this.zziL[n];
                    if (zzb2 != null) {
                        zzsn.zza(5, zzb2);
                    }
                }
            }
            if (this.zziM != null && this.zziM.length > 0) {
                for (int n2 = 0; n2 < this.zziM.length; ++n2) {
                    final zzb zzb3 = this.zziM[n2];
                    if (zzb3 != null) {
                        zzsn.zza(6, zzb3);
                    }
                }
            }
            if (this.zziN != null && this.zziN.length > 0) {
                for (int n3 = 0; n3 < this.zziN.length; ++n3) {
                    final zzg zzg = this.zziN[n3];
                    if (zzg != null) {
                        zzsn.zza(7, zzg);
                    }
                }
            }
            if (!this.zziO.equals("")) {
                zzsn.zzn(9, this.zziO);
            }
            if (!this.zziP.equals("")) {
                zzsn.zzn(10, this.zziP);
            }
            if (!this.zziQ.equals("0")) {
                zzsn.zzn(12, this.zziQ);
            }
            if (!this.version.equals("")) {
                zzsn.zzn(13, this.version);
            }
            if (this.zziR != null) {
                zzsn.zza(14, this.zziR);
            }
            if (Float.floatToIntBits(this.zziS) != Float.floatToIntBits(0.0f)) {
                zzsn.zzb(15, this.zziS);
            }
            if (this.zziU != null && this.zziU.length > 0) {
                for (int n4 = 0; n4 < this.zziU.length; ++n4) {
                    final String s2 = this.zziU[n4];
                    if (s2 != null) {
                        zzsn.zzn(16, s2);
                    }
                }
            }
            if (this.zziV != 0) {
                zzsn.zzA(17, this.zziV);
            }
            if (this.zziT) {
                zzsn.zze(18, this.zziT);
            }
            if (this.zziG != null) {
                final int length = this.zziG.length;
                int n5 = 0;
                if (length > 0) {
                    while (n5 < this.zziG.length) {
                        final String s3 = this.zziG[n5];
                        if (s3 != null) {
                            zzsn.zzn(19, s3);
                        }
                        ++n5;
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zzf zzJ() {
            this.zziG = zzsx.zzbuB;
            this.zziH = zzsx.zzbuB;
            this.zziI = zzag.zza.zzQ();
            this.zziJ = zze.zzH();
            this.zziK = zzb.zzC();
            this.zziL = zzb.zzC();
            this.zziM = zzb.zzC();
            this.zziN = zzg.zzK();
            this.zziO = "";
            this.zziP = "";
            this.zziQ = "0";
            this.version = "";
            this.zziR = null;
            this.zziS = 0.0f;
            this.zziT = false;
            this.zziU = zzsx.zzbuB;
            this.zziV = 0;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzf zzf(final zzsm zzsm) throws IOException {
        Label_0169:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0169;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0169;
                    }
                    case 10: {
                        final int zzc = zzsx.zzc(zzsm, 10);
                        int i;
                        if (this.zziH == null) {
                            i = 0;
                        }
                        else {
                            i = this.zziH.length;
                        }
                        final String[] zziH = new String[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zziH, 0, zziH, 0, i);
                        }
                        while (i < -1 + zziH.length) {
                            zziH[i] = zzsm.readString();
                            zzsm.zzIX();
                            ++i;
                        }
                        zziH[i] = zzsm.readString();
                        this.zziH = zziH;
                        continue;
                    }
                    case 18: {
                        final int zzc2 = zzsx.zzc(zzsm, 18);
                        int j;
                        if (this.zziI == null) {
                            j = 0;
                        }
                        else {
                            j = this.zziI.length;
                        }
                        final zzag.zza[] zziI = new zzag.zza[zzc2 + j];
                        if (j != 0) {
                            System.arraycopy(this.zziI, 0, zziI, 0, j);
                        }
                        while (j < -1 + zziI.length) {
                            zzsm.zza(zziI[j] = new zzag.zza());
                            zzsm.zzIX();
                            ++j;
                        }
                        zzsm.zza(zziI[j] = new zzag.zza());
                        this.zziI = zziI;
                        continue;
                    }
                    case 26: {
                        final int zzc3 = zzsx.zzc(zzsm, 26);
                        int k;
                        if (this.zziJ == null) {
                            k = 0;
                        }
                        else {
                            k = this.zziJ.length;
                        }
                        final zze[] zziJ = new zze[zzc3 + k];
                        if (k != 0) {
                            System.arraycopy(this.zziJ, 0, zziJ, 0, k);
                        }
                        while (k < -1 + zziJ.length) {
                            zzsm.zza(zziJ[k] = new zze());
                            zzsm.zzIX();
                            ++k;
                        }
                        zzsm.zza(zziJ[k] = new zze());
                        this.zziJ = zziJ;
                        continue;
                    }
                    case 34: {
                        final int zzc4 = zzsx.zzc(zzsm, 34);
                        int l;
                        if (this.zziK == null) {
                            l = 0;
                        }
                        else {
                            l = this.zziK.length;
                        }
                        final zzb[] zziK = new zzb[zzc4 + l];
                        if (l != 0) {
                            System.arraycopy(this.zziK, 0, zziK, 0, l);
                        }
                        while (l < -1 + zziK.length) {
                            zzsm.zza(zziK[l] = new zzb());
                            zzsm.zzIX();
                            ++l;
                        }
                        zzsm.zza(zziK[l] = new zzb());
                        this.zziK = zziK;
                        continue;
                    }
                    case 42: {
                        final int zzc5 = zzsx.zzc(zzsm, 42);
                        int length;
                        if (this.zziL == null) {
                            length = 0;
                        }
                        else {
                            length = this.zziL.length;
                        }
                        final zzb[] zziL = new zzb[zzc5 + length];
                        if (length != 0) {
                            System.arraycopy(this.zziL, 0, zziL, 0, length);
                        }
                        while (length < -1 + zziL.length) {
                            zzsm.zza(zziL[length] = new zzb());
                            zzsm.zzIX();
                            ++length;
                        }
                        zzsm.zza(zziL[length] = new zzb());
                        this.zziL = zziL;
                        continue;
                    }
                    case 50: {
                        final int zzc6 = zzsx.zzc(zzsm, 50);
                        int length2;
                        if (this.zziM == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.zziM.length;
                        }
                        final zzb[] zziM = new zzb[zzc6 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.zziM, 0, zziM, 0, length2);
                        }
                        while (length2 < -1 + zziM.length) {
                            zzsm.zza(zziM[length2] = new zzb());
                            zzsm.zzIX();
                            ++length2;
                        }
                        zzsm.zza(zziM[length2] = new zzb());
                        this.zziM = zziM;
                        continue;
                    }
                    case 58: {
                        final int zzc7 = zzsx.zzc(zzsm, 58);
                        int length3;
                        if (this.zziN == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.zziN.length;
                        }
                        final zzg[] zziN = new zzg[zzc7 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.zziN, 0, zziN, 0, length3);
                        }
                        while (length3 < -1 + zziN.length) {
                            zzsm.zza(zziN[length3] = new zzg());
                            zzsm.zzIX();
                            ++length3;
                        }
                        zzsm.zza(zziN[length3] = new zzg());
                        this.zziN = zziN;
                        continue;
                    }
                    case 74: {
                        this.zziO = zzsm.readString();
                        continue;
                    }
                    case 82: {
                        this.zziP = zzsm.readString();
                        continue;
                    }
                    case 98: {
                        this.zziQ = zzsm.readString();
                        continue;
                    }
                    case 106: {
                        this.version = zzsm.readString();
                        continue;
                    }
                    case 114: {
                        if (this.zziR == null) {
                            this.zziR = new zza();
                        }
                        zzsm.zza(this.zziR);
                        continue;
                    }
                    case 125: {
                        this.zziS = zzsm.readFloat();
                        continue;
                    }
                    case 130: {
                        final int zzc8 = zzsx.zzc(zzsm, 130);
                        int length4;
                        if (this.zziU == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.zziU.length;
                        }
                        final String[] zziU = new String[zzc8 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.zziU, 0, zziU, 0, length4);
                        }
                        while (length4 < -1 + zziU.length) {
                            zziU[length4] = zzsm.readString();
                            zzsm.zzIX();
                            ++length4;
                        }
                        zziU[length4] = zzsm.readString();
                        this.zziU = zziU;
                        continue;
                    }
                    case 136: {
                        this.zziV = zzsm.zzJb();
                        continue;
                    }
                    case 144: {
                        this.zziT = zzsm.zzJc();
                        continue;
                    }
                    case 154: {
                        final int zzc9 = zzsx.zzc(zzsm, 154);
                        int length5;
                        if (this.zziG == null) {
                            length5 = 0;
                        }
                        else {
                            length5 = this.zziG.length;
                        }
                        final String[] zziG = new String[zzc9 + length5];
                        if (length5 != 0) {
                            System.arraycopy(this.zziG, 0, zziG, 0, length5);
                        }
                        while (length5 < -1 + zziG.length) {
                            zziG[length5] = zzsm.readString();
                            zzsm.zzIX();
                            ++length5;
                        }
                        zziG[length5] = zzsm.readString();
                        this.zziG = zziG;
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int i = 0;
            final int zzz = super.zzz();
            int n3;
            if (this.zziH != null && this.zziH.length > 0) {
                int j = 0;
                int n = 0;
                int n2 = 0;
                while (j < this.zziH.length) {
                    final String s = this.zziH[j];
                    if (s != null) {
                        ++n2;
                        n += zzsn.zzgO(s);
                    }
                    ++j;
                }
                n3 = zzz + n + n2 * 1;
            }
            else {
                n3 = zzz;
            }
            if (this.zziI != null && this.zziI.length > 0) {
                int n4 = n3;
                for (int k = 0; k < this.zziI.length; ++k) {
                    final zzag.zza zza = this.zziI[k];
                    if (zza != null) {
                        n4 += zzsn.zzc(2, zza);
                    }
                }
                n3 = n4;
            }
            if (this.zziJ != null && this.zziJ.length > 0) {
                int n5 = n3;
                for (int l = 0; l < this.zziJ.length; ++l) {
                    final zze zze = this.zziJ[l];
                    if (zze != null) {
                        n5 += zzsn.zzc(3, zze);
                    }
                }
                n3 = n5;
            }
            if (this.zziK != null && this.zziK.length > 0) {
                int n6 = n3;
                for (int n7 = 0; n7 < this.zziK.length; ++n7) {
                    final zzb zzb = this.zziK[n7];
                    if (zzb != null) {
                        n6 += zzsn.zzc(4, zzb);
                    }
                }
                n3 = n6;
            }
            if (this.zziL != null && this.zziL.length > 0) {
                int n8 = n3;
                for (int n9 = 0; n9 < this.zziL.length; ++n9) {
                    final zzb zzb2 = this.zziL[n9];
                    if (zzb2 != null) {
                        n8 += zzsn.zzc(5, zzb2);
                    }
                }
                n3 = n8;
            }
            if (this.zziM != null && this.zziM.length > 0) {
                int n10 = n3;
                for (int n11 = 0; n11 < this.zziM.length; ++n11) {
                    final zzb zzb3 = this.zziM[n11];
                    if (zzb3 != null) {
                        n10 += zzsn.zzc(6, zzb3);
                    }
                }
                n3 = n10;
            }
            if (this.zziN != null && this.zziN.length > 0) {
                int n12 = n3;
                for (int n13 = 0; n13 < this.zziN.length; ++n13) {
                    final zzg zzg = this.zziN[n13];
                    if (zzg != null) {
                        n12 += zzsn.zzc(7, zzg);
                    }
                }
                n3 = n12;
            }
            if (!this.zziO.equals("")) {
                n3 += zzsn.zzo(9, this.zziO);
            }
            if (!this.zziP.equals("")) {
                n3 += zzsn.zzo(10, this.zziP);
            }
            if (!this.zziQ.equals("0")) {
                n3 += zzsn.zzo(12, this.zziQ);
            }
            if (!this.version.equals("")) {
                n3 += zzsn.zzo(13, this.version);
            }
            if (this.zziR != null) {
                n3 += zzsn.zzc(14, this.zziR);
            }
            if (Float.floatToIntBits(this.zziS) != Float.floatToIntBits(0.0f)) {
                n3 += zzsn.zzc(15, this.zziS);
            }
            if (this.zziU != null && this.zziU.length > 0) {
                int n14 = 0;
                int n15 = 0;
                int n16 = 0;
                while (n14 < this.zziU.length) {
                    final String s2 = this.zziU[n14];
                    if (s2 != null) {
                        ++n16;
                        n15 += zzsn.zzgO(s2);
                    }
                    ++n14;
                }
                n3 = n3 + n15 + n16 * 2;
            }
            if (this.zziV != 0) {
                n3 += zzsn.zzC(17, this.zziV);
            }
            if (this.zziT) {
                n3 += zzsn.zzf(18, this.zziT);
            }
            if (this.zziG != null && this.zziG.length > 0) {
                int n17 = 0;
                int n18 = 0;
                while (i < this.zziG.length) {
                    final String s3 = this.zziG[i];
                    if (s3 != null) {
                        ++n18;
                        n17 += zzsn.zzgO(s3);
                    }
                    ++i;
                }
                n3 = n3 + n17 + n18 * 2;
            }
            return n3;
        }
    }
    
    public static final class zzg extends zzso<zzg>
    {
        private static volatile zzg[] zziW;
        public int[] zziX;
        public int[] zziY;
        public int[] zziZ;
        public int[] zzja;
        public int[] zzjb;
        public int[] zzjc;
        public int[] zzjd;
        public int[] zzje;
        public int[] zzjf;
        public int[] zzjg;
        
        public zzg() {
            this.zzL();
        }
        
        public static zzg[] zzK() {
            Label_0027: {
                if (zzg.zziW != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzg.zziW == null) {
                        zzg.zziW = new zzg[0];
                    }
                    return zzg.zziW;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzg;
                b = false;
                if (b2) {
                    final zzg zzg = (zzg)o;
                    final boolean equals = zzss.equals(this.zziX, zzg.zziX);
                    b = false;
                    if (equals) {
                        final boolean equals2 = zzss.equals(this.zziY, zzg.zziY);
                        b = false;
                        if (equals2) {
                            final boolean equals3 = zzss.equals(this.zziZ, zzg.zziZ);
                            b = false;
                            if (equals3) {
                                final boolean equals4 = zzss.equals(this.zzja, zzg.zzja);
                                b = false;
                                if (equals4) {
                                    final boolean equals5 = zzss.equals(this.zzjb, zzg.zzjb);
                                    b = false;
                                    if (equals5) {
                                        final boolean equals6 = zzss.equals(this.zzjc, zzg.zzjc);
                                        b = false;
                                        if (equals6) {
                                            final boolean equals7 = zzss.equals(this.zzjd, zzg.zzjd);
                                            b = false;
                                            if (equals7) {
                                                final boolean equals8 = zzss.equals(this.zzje, zzg.zzje);
                                                b = false;
                                                if (equals8) {
                                                    final boolean equals9 = zzss.equals(this.zzjf, zzg.zzjf);
                                                    b = false;
                                                    if (equals9) {
                                                        final boolean equals10 = zzss.equals(this.zzjg, zzg.zzjg);
                                                        b = false;
                                                        if (equals10) {
                                                            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                                                if (zzg.zzbuj != null) {
                                                                    final boolean empty = zzg.zzbuj.isEmpty();
                                                                    b = false;
                                                                    if (!empty) {
                                                                        return b;
                                                                    }
                                                                }
                                                                return true;
                                                            }
                                                            return this.zzbuj.equals(zzg.zzbuj);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zziX)) + zzss.hashCode(this.zziY)) + zzss.hashCode(this.zziZ)) + zzss.hashCode(this.zzja)) + zzss.hashCode(this.zzjb)) + zzss.hashCode(this.zzjc)) + zzss.hashCode(this.zzjd)) + zzss.hashCode(this.zzje)) + zzss.hashCode(this.zzjf)) + zzss.hashCode(this.zzjg));
            int hashCode;
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzbuj.hashCode();
            }
            return hashCode + n;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zziX != null && this.zziX.length > 0) {
                for (int i = 0; i < this.zziX.length; ++i) {
                    zzsn.zzA(1, this.zziX[i]);
                }
            }
            if (this.zziY != null && this.zziY.length > 0) {
                for (int j = 0; j < this.zziY.length; ++j) {
                    zzsn.zzA(2, this.zziY[j]);
                }
            }
            if (this.zziZ != null && this.zziZ.length > 0) {
                for (int k = 0; k < this.zziZ.length; ++k) {
                    zzsn.zzA(3, this.zziZ[k]);
                }
            }
            if (this.zzja != null && this.zzja.length > 0) {
                for (int l = 0; l < this.zzja.length; ++l) {
                    zzsn.zzA(4, this.zzja[l]);
                }
            }
            if (this.zzjb != null && this.zzjb.length > 0) {
                for (int n = 0; n < this.zzjb.length; ++n) {
                    zzsn.zzA(5, this.zzjb[n]);
                }
            }
            if (this.zzjc != null && this.zzjc.length > 0) {
                for (int n2 = 0; n2 < this.zzjc.length; ++n2) {
                    zzsn.zzA(6, this.zzjc[n2]);
                }
            }
            if (this.zzjd != null && this.zzjd.length > 0) {
                for (int n3 = 0; n3 < this.zzjd.length; ++n3) {
                    zzsn.zzA(7, this.zzjd[n3]);
                }
            }
            if (this.zzje != null && this.zzje.length > 0) {
                for (int n4 = 0; n4 < this.zzje.length; ++n4) {
                    zzsn.zzA(8, this.zzje[n4]);
                }
            }
            if (this.zzjf != null && this.zzjf.length > 0) {
                for (int n5 = 0; n5 < this.zzjf.length; ++n5) {
                    zzsn.zzA(9, this.zzjf[n5]);
                }
            }
            if (this.zzjg != null) {
                final int length = this.zzjg.length;
                int n6 = 0;
                if (length > 0) {
                    while (n6 < this.zzjg.length) {
                        zzsn.zzA(10, this.zzjg[n6]);
                        ++n6;
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zzg zzL() {
            this.zziX = zzsx.zzbuw;
            this.zziY = zzsx.zzbuw;
            this.zziZ = zzsx.zzbuw;
            this.zzja = zzsx.zzbuw;
            this.zzjb = zzsx.zzbuw;
            this.zzjc = zzsx.zzbuw;
            this.zzjd = zzsx.zzbuw;
            this.zzje = zzsx.zzbuw;
            this.zzjf = zzsx.zzbuw;
            this.zzjg = zzsx.zzbuw;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzg zzg(final zzsm zzsm) throws IOException {
        Label_0193:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0193;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0193;
                    }
                    case 8: {
                        final int zzc = zzsx.zzc(zzsm, 8);
                        int i;
                        if (this.zziX == null) {
                            i = 0;
                        }
                        else {
                            i = this.zziX.length;
                        }
                        final int[] zziX = new int[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zziX, 0, zziX, 0, i);
                        }
                        while (i < -1 + zziX.length) {
                            zziX[i] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++i;
                        }
                        zziX[i] = zzsm.zzJb();
                        this.zziX = zziX;
                        continue;
                    }
                    case 10: {
                        final int zzmq = zzsm.zzmq(zzsm.zzJf());
                        final int position = zzsm.getPosition();
                        int n = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n;
                        }
                        zzsm.zzms(position);
                        int j;
                        if (this.zziX == null) {
                            j = 0;
                        }
                        else {
                            j = this.zziX.length;
                        }
                        final int[] zziX2 = new int[n + j];
                        if (j != 0) {
                            System.arraycopy(this.zziX, 0, zziX2, 0, j);
                        }
                        while (j < zziX2.length) {
                            zziX2[j] = zzsm.zzJb();
                            ++j;
                        }
                        this.zziX = zziX2;
                        zzsm.zzmr(zzmq);
                        continue;
                    }
                    case 16: {
                        final int zzc2 = zzsx.zzc(zzsm, 16);
                        int k;
                        if (this.zziY == null) {
                            k = 0;
                        }
                        else {
                            k = this.zziY.length;
                        }
                        final int[] zziY = new int[zzc2 + k];
                        if (k != 0) {
                            System.arraycopy(this.zziY, 0, zziY, 0, k);
                        }
                        while (k < -1 + zziY.length) {
                            zziY[k] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++k;
                        }
                        zziY[k] = zzsm.zzJb();
                        this.zziY = zziY;
                        continue;
                    }
                    case 18: {
                        final int zzmq2 = zzsm.zzmq(zzsm.zzJf());
                        final int position2 = zzsm.getPosition();
                        int n2 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n2;
                        }
                        zzsm.zzms(position2);
                        int l;
                        if (this.zziY == null) {
                            l = 0;
                        }
                        else {
                            l = this.zziY.length;
                        }
                        final int[] zziY2 = new int[n2 + l];
                        if (l != 0) {
                            System.arraycopy(this.zziY, 0, zziY2, 0, l);
                        }
                        while (l < zziY2.length) {
                            zziY2[l] = zzsm.zzJb();
                            ++l;
                        }
                        this.zziY = zziY2;
                        zzsm.zzmr(zzmq2);
                        continue;
                    }
                    case 24: {
                        final int zzc3 = zzsx.zzc(zzsm, 24);
                        int length;
                        if (this.zziZ == null) {
                            length = 0;
                        }
                        else {
                            length = this.zziZ.length;
                        }
                        final int[] zziZ = new int[zzc3 + length];
                        if (length != 0) {
                            System.arraycopy(this.zziZ, 0, zziZ, 0, length);
                        }
                        while (length < -1 + zziZ.length) {
                            zziZ[length] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length;
                        }
                        zziZ[length] = zzsm.zzJb();
                        this.zziZ = zziZ;
                        continue;
                    }
                    case 26: {
                        final int zzmq3 = zzsm.zzmq(zzsm.zzJf());
                        final int position3 = zzsm.getPosition();
                        int n3 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n3;
                        }
                        zzsm.zzms(position3);
                        int length2;
                        if (this.zziZ == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.zziZ.length;
                        }
                        final int[] zziZ2 = new int[n3 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.zziZ, 0, zziZ2, 0, length2);
                        }
                        while (length2 < zziZ2.length) {
                            zziZ2[length2] = zzsm.zzJb();
                            ++length2;
                        }
                        this.zziZ = zziZ2;
                        zzsm.zzmr(zzmq3);
                        continue;
                    }
                    case 32: {
                        final int zzc4 = zzsx.zzc(zzsm, 32);
                        int length3;
                        if (this.zzja == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.zzja.length;
                        }
                        final int[] zzja = new int[zzc4 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.zzja, 0, zzja, 0, length3);
                        }
                        while (length3 < -1 + zzja.length) {
                            zzja[length3] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length3;
                        }
                        zzja[length3] = zzsm.zzJb();
                        this.zzja = zzja;
                        continue;
                    }
                    case 34: {
                        final int zzmq4 = zzsm.zzmq(zzsm.zzJf());
                        final int position4 = zzsm.getPosition();
                        int n4 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n4;
                        }
                        zzsm.zzms(position4);
                        int length4;
                        if (this.zzja == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.zzja.length;
                        }
                        final int[] zzja2 = new int[n4 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.zzja, 0, zzja2, 0, length4);
                        }
                        while (length4 < zzja2.length) {
                            zzja2[length4] = zzsm.zzJb();
                            ++length4;
                        }
                        this.zzja = zzja2;
                        zzsm.zzmr(zzmq4);
                        continue;
                    }
                    case 40: {
                        final int zzc5 = zzsx.zzc(zzsm, 40);
                        int length5;
                        if (this.zzjb == null) {
                            length5 = 0;
                        }
                        else {
                            length5 = this.zzjb.length;
                        }
                        final int[] zzjb = new int[zzc5 + length5];
                        if (length5 != 0) {
                            System.arraycopy(this.zzjb, 0, zzjb, 0, length5);
                        }
                        while (length5 < -1 + zzjb.length) {
                            zzjb[length5] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length5;
                        }
                        zzjb[length5] = zzsm.zzJb();
                        this.zzjb = zzjb;
                        continue;
                    }
                    case 42: {
                        final int zzmq5 = zzsm.zzmq(zzsm.zzJf());
                        final int position5 = zzsm.getPosition();
                        int n5 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n5;
                        }
                        zzsm.zzms(position5);
                        int length6;
                        if (this.zzjb == null) {
                            length6 = 0;
                        }
                        else {
                            length6 = this.zzjb.length;
                        }
                        final int[] zzjb2 = new int[n5 + length6];
                        if (length6 != 0) {
                            System.arraycopy(this.zzjb, 0, zzjb2, 0, length6);
                        }
                        while (length6 < zzjb2.length) {
                            zzjb2[length6] = zzsm.zzJb();
                            ++length6;
                        }
                        this.zzjb = zzjb2;
                        zzsm.zzmr(zzmq5);
                        continue;
                    }
                    case 48: {
                        final int zzc6 = zzsx.zzc(zzsm, 48);
                        int length7;
                        if (this.zzjc == null) {
                            length7 = 0;
                        }
                        else {
                            length7 = this.zzjc.length;
                        }
                        final int[] zzjc = new int[zzc6 + length7];
                        if (length7 != 0) {
                            System.arraycopy(this.zzjc, 0, zzjc, 0, length7);
                        }
                        while (length7 < -1 + zzjc.length) {
                            zzjc[length7] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length7;
                        }
                        zzjc[length7] = zzsm.zzJb();
                        this.zzjc = zzjc;
                        continue;
                    }
                    case 50: {
                        final int zzmq6 = zzsm.zzmq(zzsm.zzJf());
                        final int position6 = zzsm.getPosition();
                        int n6 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n6;
                        }
                        zzsm.zzms(position6);
                        int length8;
                        if (this.zzjc == null) {
                            length8 = 0;
                        }
                        else {
                            length8 = this.zzjc.length;
                        }
                        final int[] zzjc2 = new int[n6 + length8];
                        if (length8 != 0) {
                            System.arraycopy(this.zzjc, 0, zzjc2, 0, length8);
                        }
                        while (length8 < zzjc2.length) {
                            zzjc2[length8] = zzsm.zzJb();
                            ++length8;
                        }
                        this.zzjc = zzjc2;
                        zzsm.zzmr(zzmq6);
                        continue;
                    }
                    case 56: {
                        final int zzc7 = zzsx.zzc(zzsm, 56);
                        int length9;
                        if (this.zzjd == null) {
                            length9 = 0;
                        }
                        else {
                            length9 = this.zzjd.length;
                        }
                        final int[] zzjd = new int[zzc7 + length9];
                        if (length9 != 0) {
                            System.arraycopy(this.zzjd, 0, zzjd, 0, length9);
                        }
                        while (length9 < -1 + zzjd.length) {
                            zzjd[length9] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length9;
                        }
                        zzjd[length9] = zzsm.zzJb();
                        this.zzjd = zzjd;
                        continue;
                    }
                    case 58: {
                        final int zzmq7 = zzsm.zzmq(zzsm.zzJf());
                        final int position7 = zzsm.getPosition();
                        int n7 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n7;
                        }
                        zzsm.zzms(position7);
                        int length10;
                        if (this.zzjd == null) {
                            length10 = 0;
                        }
                        else {
                            length10 = this.zzjd.length;
                        }
                        final int[] zzjd2 = new int[n7 + length10];
                        if (length10 != 0) {
                            System.arraycopy(this.zzjd, 0, zzjd2, 0, length10);
                        }
                        while (length10 < zzjd2.length) {
                            zzjd2[length10] = zzsm.zzJb();
                            ++length10;
                        }
                        this.zzjd = zzjd2;
                        zzsm.zzmr(zzmq7);
                        continue;
                    }
                    case 64: {
                        final int zzc8 = zzsx.zzc(zzsm, 64);
                        int length11;
                        if (this.zzje == null) {
                            length11 = 0;
                        }
                        else {
                            length11 = this.zzje.length;
                        }
                        final int[] zzje = new int[zzc8 + length11];
                        if (length11 != 0) {
                            System.arraycopy(this.zzje, 0, zzje, 0, length11);
                        }
                        while (length11 < -1 + zzje.length) {
                            zzje[length11] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length11;
                        }
                        zzje[length11] = zzsm.zzJb();
                        this.zzje = zzje;
                        continue;
                    }
                    case 66: {
                        final int zzmq8 = zzsm.zzmq(zzsm.zzJf());
                        final int position8 = zzsm.getPosition();
                        int n8 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n8;
                        }
                        zzsm.zzms(position8);
                        int length12;
                        if (this.zzje == null) {
                            length12 = 0;
                        }
                        else {
                            length12 = this.zzje.length;
                        }
                        final int[] zzje2 = new int[n8 + length12];
                        if (length12 != 0) {
                            System.arraycopy(this.zzje, 0, zzje2, 0, length12);
                        }
                        while (length12 < zzje2.length) {
                            zzje2[length12] = zzsm.zzJb();
                            ++length12;
                        }
                        this.zzje = zzje2;
                        zzsm.zzmr(zzmq8);
                        continue;
                    }
                    case 72: {
                        final int zzc9 = zzsx.zzc(zzsm, 72);
                        int length13;
                        if (this.zzjf == null) {
                            length13 = 0;
                        }
                        else {
                            length13 = this.zzjf.length;
                        }
                        final int[] zzjf = new int[zzc9 + length13];
                        if (length13 != 0) {
                            System.arraycopy(this.zzjf, 0, zzjf, 0, length13);
                        }
                        while (length13 < -1 + zzjf.length) {
                            zzjf[length13] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length13;
                        }
                        zzjf[length13] = zzsm.zzJb();
                        this.zzjf = zzjf;
                        continue;
                    }
                    case 74: {
                        final int zzmq9 = zzsm.zzmq(zzsm.zzJf());
                        final int position9 = zzsm.getPosition();
                        int n9 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n9;
                        }
                        zzsm.zzms(position9);
                        int length14;
                        if (this.zzjf == null) {
                            length14 = 0;
                        }
                        else {
                            length14 = this.zzjf.length;
                        }
                        final int[] zzjf2 = new int[n9 + length14];
                        if (length14 != 0) {
                            System.arraycopy(this.zzjf, 0, zzjf2, 0, length14);
                        }
                        while (length14 < zzjf2.length) {
                            zzjf2[length14] = zzsm.zzJb();
                            ++length14;
                        }
                        this.zzjf = zzjf2;
                        zzsm.zzmr(zzmq9);
                        continue;
                    }
                    case 80: {
                        final int zzc10 = zzsx.zzc(zzsm, 80);
                        int length15;
                        if (this.zzjg == null) {
                            length15 = 0;
                        }
                        else {
                            length15 = this.zzjg.length;
                        }
                        final int[] zzjg = new int[zzc10 + length15];
                        if (length15 != 0) {
                            System.arraycopy(this.zzjg, 0, zzjg, 0, length15);
                        }
                        while (length15 < -1 + zzjg.length) {
                            zzjg[length15] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length15;
                        }
                        zzjg[length15] = zzsm.zzJb();
                        this.zzjg = zzjg;
                        continue;
                    }
                    case 82: {
                        final int zzmq10 = zzsm.zzmq(zzsm.zzJf());
                        final int position10 = zzsm.getPosition();
                        int n10 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n10;
                        }
                        zzsm.zzms(position10);
                        int length16;
                        if (this.zzjg == null) {
                            length16 = 0;
                        }
                        else {
                            length16 = this.zzjg.length;
                        }
                        final int[] zzjg2 = new int[n10 + length16];
                        if (length16 != 0) {
                            System.arraycopy(this.zzjg, 0, zzjg2, 0, length16);
                        }
                        while (length16 < zzjg2.length) {
                            zzjg2[length16] = zzsm.zzJb();
                            ++length16;
                        }
                        this.zzjg = zzjg2;
                        zzsm.zzmr(zzmq10);
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int i = 0;
            final int zzz = super.zzz();
            int n2;
            if (this.zziX != null && this.zziX.length > 0) {
                int j = 0;
                int n = 0;
                while (j < this.zziX.length) {
                    n += zzsn.zzmx(this.zziX[j]);
                    ++j;
                }
                n2 = zzz + n + 1 * this.zziX.length;
            }
            else {
                n2 = zzz;
            }
            if (this.zziY != null && this.zziY.length > 0) {
                int k = 0;
                int n3 = 0;
                while (k < this.zziY.length) {
                    n3 += zzsn.zzmx(this.zziY[k]);
                    ++k;
                }
                n2 = n2 + n3 + 1 * this.zziY.length;
            }
            if (this.zziZ != null && this.zziZ.length > 0) {
                int l = 0;
                int n4 = 0;
                while (l < this.zziZ.length) {
                    n4 += zzsn.zzmx(this.zziZ[l]);
                    ++l;
                }
                n2 = n2 + n4 + 1 * this.zziZ.length;
            }
            if (this.zzja != null && this.zzja.length > 0) {
                int n5 = 0;
                int n6 = 0;
                while (n5 < this.zzja.length) {
                    n6 += zzsn.zzmx(this.zzja[n5]);
                    ++n5;
                }
                n2 = n2 + n6 + 1 * this.zzja.length;
            }
            if (this.zzjb != null && this.zzjb.length > 0) {
                int n7 = 0;
                int n8 = 0;
                while (n7 < this.zzjb.length) {
                    n8 += zzsn.zzmx(this.zzjb[n7]);
                    ++n7;
                }
                n2 = n2 + n8 + 1 * this.zzjb.length;
            }
            if (this.zzjc != null && this.zzjc.length > 0) {
                int n9 = 0;
                int n10 = 0;
                while (n9 < this.zzjc.length) {
                    n10 += zzsn.zzmx(this.zzjc[n9]);
                    ++n9;
                }
                n2 = n2 + n10 + 1 * this.zzjc.length;
            }
            if (this.zzjd != null && this.zzjd.length > 0) {
                int n11 = 0;
                int n12 = 0;
                while (n11 < this.zzjd.length) {
                    n12 += zzsn.zzmx(this.zzjd[n11]);
                    ++n11;
                }
                n2 = n2 + n12 + 1 * this.zzjd.length;
            }
            if (this.zzje != null && this.zzje.length > 0) {
                int n13 = 0;
                int n14 = 0;
                while (n13 < this.zzje.length) {
                    n14 += zzsn.zzmx(this.zzje[n13]);
                    ++n13;
                }
                n2 = n2 + n14 + 1 * this.zzje.length;
            }
            if (this.zzjf != null && this.zzjf.length > 0) {
                int n15 = 0;
                int n16 = 0;
                while (n15 < this.zzjf.length) {
                    n16 += zzsn.zzmx(this.zzjf[n15]);
                    ++n15;
                }
                n2 = n2 + n16 + 1 * this.zzjf.length;
            }
            if (this.zzjg != null && this.zzjg.length > 0) {
                int n17 = 0;
                while (i < this.zzjg.length) {
                    n17 += zzsn.zzmx(this.zzjg[i]);
                    ++i;
                }
                n2 = n2 + n17 + 1 * this.zzjg.length;
            }
            return n2;
        }
    }
    
    public static final class zzh extends zzso<zzh>
    {
        public static final zzsp<zzag.zza, zzh> zzjh;
        private static final zzh[] zzji;
        public int[] zzjj;
        public int[] zzjk;
        public int[] zzjl;
        public int zzjm;
        public int[] zzjn;
        public int zzjo;
        public int zzjp;
        
        static {
            zzjh = zzsp.zza(11, zzh.class, 810L);
            zzji = new zzh[0];
        }
        
        public zzh() {
            this.zzM();
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzh;
                b = false;
                if (b2) {
                    final zzh zzh = (zzh)o;
                    final boolean equals = zzss.equals(this.zzjj, zzh.zzjj);
                    b = false;
                    if (equals) {
                        final boolean equals2 = zzss.equals(this.zzjk, zzh.zzjk);
                        b = false;
                        if (equals2) {
                            final boolean equals3 = zzss.equals(this.zzjl, zzh.zzjl);
                            b = false;
                            if (equals3) {
                                final int zzjm = this.zzjm;
                                final int zzjm2 = zzh.zzjm;
                                b = false;
                                if (zzjm == zzjm2) {
                                    final boolean equals4 = zzss.equals(this.zzjn, zzh.zzjn);
                                    b = false;
                                    if (equals4) {
                                        final int zzjo = this.zzjo;
                                        final int zzjo2 = zzh.zzjo;
                                        b = false;
                                        if (zzjo == zzjo2) {
                                            final int zzjp = this.zzjp;
                                            final int zzjp2 = zzh.zzjp;
                                            b = false;
                                            if (zzjp == zzjp2) {
                                                if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                                    if (zzh.zzbuj != null) {
                                                        final boolean empty = zzh.zzbuj.isEmpty();
                                                        b = false;
                                                        if (!empty) {
                                                            return b;
                                                        }
                                                    }
                                                    return true;
                                                }
                                                return this.zzbuj.equals(zzh.zzbuj);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzjj)) + zzss.hashCode(this.zzjk)) + zzss.hashCode(this.zzjl)) + this.zzjm) + zzss.hashCode(this.zzjn)) + this.zzjo) + this.zzjp);
            int hashCode;
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzbuj.hashCode();
            }
            return hashCode + n;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzjj != null && this.zzjj.length > 0) {
                for (int i = 0; i < this.zzjj.length; ++i) {
                    zzsn.zzA(1, this.zzjj[i]);
                }
            }
            if (this.zzjk != null && this.zzjk.length > 0) {
                for (int j = 0; j < this.zzjk.length; ++j) {
                    zzsn.zzA(2, this.zzjk[j]);
                }
            }
            if (this.zzjl != null && this.zzjl.length > 0) {
                for (int k = 0; k < this.zzjl.length; ++k) {
                    zzsn.zzA(3, this.zzjl[k]);
                }
            }
            if (this.zzjm != 0) {
                zzsn.zzA(4, this.zzjm);
            }
            if (this.zzjn != null) {
                final int length = this.zzjn.length;
                int l = 0;
                if (length > 0) {
                    while (l < this.zzjn.length) {
                        zzsn.zzA(5, this.zzjn[l]);
                        ++l;
                    }
                }
            }
            if (this.zzjo != 0) {
                zzsn.zzA(6, this.zzjo);
            }
            if (this.zzjp != 0) {
                zzsn.zzA(7, this.zzjp);
            }
            super.writeTo(zzsn);
        }
        
        public zzh zzM() {
            this.zzjj = zzsx.zzbuw;
            this.zzjk = zzsx.zzbuw;
            this.zzjl = zzsx.zzbuw;
            this.zzjm = 0;
            this.zzjn = zzsx.zzbuw;
            this.zzjo = 0;
            this.zzjp = 0;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzh zzh(final zzsm zzsm) throws IOException {
        Label_0121:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0121;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0121;
                    }
                    case 8: {
                        final int zzc = zzsx.zzc(zzsm, 8);
                        int i;
                        if (this.zzjj == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzjj.length;
                        }
                        final int[] zzjj = new int[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzjj, 0, zzjj, 0, i);
                        }
                        while (i < -1 + zzjj.length) {
                            zzjj[i] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++i;
                        }
                        zzjj[i] = zzsm.zzJb();
                        this.zzjj = zzjj;
                        continue;
                    }
                    case 10: {
                        final int zzmq = zzsm.zzmq(zzsm.zzJf());
                        final int position = zzsm.getPosition();
                        int n = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n;
                        }
                        zzsm.zzms(position);
                        int j;
                        if (this.zzjj == null) {
                            j = 0;
                        }
                        else {
                            j = this.zzjj.length;
                        }
                        final int[] zzjj2 = new int[n + j];
                        if (j != 0) {
                            System.arraycopy(this.zzjj, 0, zzjj2, 0, j);
                        }
                        while (j < zzjj2.length) {
                            zzjj2[j] = zzsm.zzJb();
                            ++j;
                        }
                        this.zzjj = zzjj2;
                        zzsm.zzmr(zzmq);
                        continue;
                    }
                    case 16: {
                        final int zzc2 = zzsx.zzc(zzsm, 16);
                        int k;
                        if (this.zzjk == null) {
                            k = 0;
                        }
                        else {
                            k = this.zzjk.length;
                        }
                        final int[] zzjk = new int[zzc2 + k];
                        if (k != 0) {
                            System.arraycopy(this.zzjk, 0, zzjk, 0, k);
                        }
                        while (k < -1 + zzjk.length) {
                            zzjk[k] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++k;
                        }
                        zzjk[k] = zzsm.zzJb();
                        this.zzjk = zzjk;
                        continue;
                    }
                    case 18: {
                        final int zzmq2 = zzsm.zzmq(zzsm.zzJf());
                        final int position2 = zzsm.getPosition();
                        int n2 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n2;
                        }
                        zzsm.zzms(position2);
                        int l;
                        if (this.zzjk == null) {
                            l = 0;
                        }
                        else {
                            l = this.zzjk.length;
                        }
                        final int[] zzjk2 = new int[n2 + l];
                        if (l != 0) {
                            System.arraycopy(this.zzjk, 0, zzjk2, 0, l);
                        }
                        while (l < zzjk2.length) {
                            zzjk2[l] = zzsm.zzJb();
                            ++l;
                        }
                        this.zzjk = zzjk2;
                        zzsm.zzmr(zzmq2);
                        continue;
                    }
                    case 24: {
                        final int zzc3 = zzsx.zzc(zzsm, 24);
                        int length;
                        if (this.zzjl == null) {
                            length = 0;
                        }
                        else {
                            length = this.zzjl.length;
                        }
                        final int[] zzjl = new int[zzc3 + length];
                        if (length != 0) {
                            System.arraycopy(this.zzjl, 0, zzjl, 0, length);
                        }
                        while (length < -1 + zzjl.length) {
                            zzjl[length] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length;
                        }
                        zzjl[length] = zzsm.zzJb();
                        this.zzjl = zzjl;
                        continue;
                    }
                    case 26: {
                        final int zzmq3 = zzsm.zzmq(zzsm.zzJf());
                        final int position3 = zzsm.getPosition();
                        int n3 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n3;
                        }
                        zzsm.zzms(position3);
                        int length2;
                        if (this.zzjl == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.zzjl.length;
                        }
                        final int[] zzjl2 = new int[n3 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.zzjl, 0, zzjl2, 0, length2);
                        }
                        while (length2 < zzjl2.length) {
                            zzjl2[length2] = zzsm.zzJb();
                            ++length2;
                        }
                        this.zzjl = zzjl2;
                        zzsm.zzmr(zzmq3);
                        continue;
                    }
                    case 32: {
                        this.zzjm = zzsm.zzJb();
                        continue;
                    }
                    case 40: {
                        final int zzc4 = zzsx.zzc(zzsm, 40);
                        int length3;
                        if (this.zzjn == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.zzjn.length;
                        }
                        final int[] zzjn = new int[zzc4 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.zzjn, 0, zzjn, 0, length3);
                        }
                        while (length3 < -1 + zzjn.length) {
                            zzjn[length3] = zzsm.zzJb();
                            zzsm.zzIX();
                            ++length3;
                        }
                        zzjn[length3] = zzsm.zzJb();
                        this.zzjn = zzjn;
                        continue;
                    }
                    case 42: {
                        final int zzmq4 = zzsm.zzmq(zzsm.zzJf());
                        final int position4 = zzsm.getPosition();
                        int n4 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzJb();
                            ++n4;
                        }
                        zzsm.zzms(position4);
                        int length4;
                        if (this.zzjn == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.zzjn.length;
                        }
                        final int[] zzjn2 = new int[n4 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.zzjn, 0, zzjn2, 0, length4);
                        }
                        while (length4 < zzjn2.length) {
                            zzjn2[length4] = zzsm.zzJb();
                            ++length4;
                        }
                        this.zzjn = zzjn2;
                        zzsm.zzmr(zzmq4);
                        continue;
                    }
                    case 48: {
                        this.zzjo = zzsm.zzJb();
                        continue;
                    }
                    case 56: {
                        this.zzjp = zzsm.zzJb();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int i = 0;
            final int zzz = super.zzz();
            int n2;
            if (this.zzjj != null && this.zzjj.length > 0) {
                int j = 0;
                int n = 0;
                while (j < this.zzjj.length) {
                    n += zzsn.zzmx(this.zzjj[j]);
                    ++j;
                }
                n2 = zzz + n + 1 * this.zzjj.length;
            }
            else {
                n2 = zzz;
            }
            if (this.zzjk != null && this.zzjk.length > 0) {
                int k = 0;
                int n3 = 0;
                while (k < this.zzjk.length) {
                    n3 += zzsn.zzmx(this.zzjk[k]);
                    ++k;
                }
                n2 = n2 + n3 + 1 * this.zzjk.length;
            }
            if (this.zzjl != null && this.zzjl.length > 0) {
                int l = 0;
                int n4 = 0;
                while (l < this.zzjl.length) {
                    n4 += zzsn.zzmx(this.zzjl[l]);
                    ++l;
                }
                n2 = n2 + n4 + 1 * this.zzjl.length;
            }
            if (this.zzjm != 0) {
                n2 += zzsn.zzC(4, this.zzjm);
            }
            if (this.zzjn != null && this.zzjn.length > 0) {
                int n5 = 0;
                while (i < this.zzjn.length) {
                    n5 += zzsn.zzmx(this.zzjn[i]);
                    ++i;
                }
                n2 = n2 + n5 + 1 * this.zzjn.length;
            }
            if (this.zzjo != 0) {
                n2 += zzsn.zzC(6, this.zzjo);
            }
            if (this.zzjp != 0) {
                n2 += zzsn.zzC(7, this.zzjp);
            }
            return n2;
        }
    }
    
    public static final class zzi extends zzso<zzi>
    {
        private static volatile zzi[] zzjq;
        public String name;
        public zzag.zza zzjr;
        public zzd zzjs;
        
        public zzi() {
            this.zzO();
        }
        
        public static zzi[] zzN() {
            Label_0027: {
                if (zzi.zzjq != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzi.zzjq == null) {
                        zzi.zzjq = new zzi[0];
                    }
                    return zzi.zzjq;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzi;
                b = false;
                if (b2) {
                    final zzi zzi = (zzi)o;
                    if (this.name == null) {
                        final String name = zzi.name;
                        b = false;
                        if (name != null) {
                            return b;
                        }
                    }
                    else if (!this.name.equals(zzi.name)) {
                        return false;
                    }
                    if (this.zzjr == null) {
                        final zzag.zza zzjr = zzi.zzjr;
                        b = false;
                        if (zzjr != null) {
                            return b;
                        }
                    }
                    else if (!this.zzjr.equals(zzi.zzjr)) {
                        return false;
                    }
                    if (this.zzjs == null) {
                        final zzd zzjs = zzi.zzjs;
                        b = false;
                        if (zzjs != null) {
                            return b;
                        }
                    }
                    else if (!this.zzjs.equals(zzi.zzjs)) {
                        return false;
                    }
                    if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                        if (zzi.zzbuj != null) {
                            final boolean empty = zzi.zzbuj.isEmpty();
                            b = false;
                            if (!empty) {
                                return b;
                            }
                        }
                        return true;
                    }
                    return this.zzbuj.equals(zzi.zzbuj);
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.name == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.name.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzjr == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzjr.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            int hashCode3;
            if (this.zzjs == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzjs.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            final zzsq zzbuj = this.zzbuj;
            int hashCode4 = 0;
            if (zzbuj != null) {
                final boolean empty = this.zzbuj.isEmpty();
                hashCode4 = 0;
                if (!empty) {
                    hashCode4 = this.zzbuj.hashCode();
                }
            }
            return n4 + hashCode4;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (!this.name.equals("")) {
                zzsn.zzn(1, this.name);
            }
            if (this.zzjr != null) {
                zzsn.zza(2, this.zzjr);
            }
            if (this.zzjs != null) {
                zzsn.zza(3, this.zzjs);
            }
            super.writeTo(zzsn);
        }
        
        public zzi zzO() {
            this.name = "";
            this.zzjr = null;
            this.zzjs = null;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzi zzi(final zzsm zzsm) throws IOException {
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
                    case 10: {
                        this.name = zzsm.readString();
                        continue;
                    }
                    case 18: {
                        if (this.zzjr == null) {
                            this.zzjr = new zzag.zza();
                        }
                        zzsm.zza(this.zzjr);
                        continue;
                    }
                    case 26: {
                        if (this.zzjs == null) {
                            this.zzjs = new zzd();
                        }
                        zzsm.zza(this.zzjs);
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (!this.name.equals("")) {
                zzz += zzsn.zzo(1, this.name);
            }
            if (this.zzjr != null) {
                zzz += zzsn.zzc(2, this.zzjr);
            }
            if (this.zzjs != null) {
                zzz += zzsn.zzc(3, this.zzjs);
            }
            return zzz;
        }
    }
    
    public static final class zzj extends zzso<zzj>
    {
        public zzi[] zzjt;
        public zzf zzju;
        public String zzjv;
        
        public zzj() {
            this.zzP();
        }
        
        public static zzj zzd(final byte[] array) throws zzst {
            return zzsu.mergeFrom(new zzj(), array);
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b;
            if (o == this) {
                b = true;
            }
            else {
                final boolean b2 = o instanceof zzj;
                b = false;
                if (b2) {
                    final zzj zzj = (zzj)o;
                    final boolean equals = zzss.equals(this.zzjt, zzj.zzjt);
                    b = false;
                    if (equals) {
                        if (this.zzju == null) {
                            final zzf zzju = zzj.zzju;
                            b = false;
                            if (zzju != null) {
                                return b;
                            }
                        }
                        else if (!this.zzju.equals(zzj.zzju)) {
                            return false;
                        }
                        if (this.zzjv == null) {
                            final String zzjv = zzj.zzjv;
                            b = false;
                            if (zzjv != null) {
                                return b;
                            }
                        }
                        else if (!this.zzjv.equals(zzj.zzjv)) {
                            return false;
                        }
                        if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                            if (zzj.zzbuj != null) {
                                final boolean empty = zzj.zzbuj.isEmpty();
                                b = false;
                                if (!empty) {
                                    return b;
                                }
                            }
                            return true;
                        }
                        return this.zzbuj.equals(zzj.zzbuj);
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzjt));
            int hashCode;
            if (this.zzju == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzju.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzjv == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzjv.hashCode();
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
            if (this.zzjt != null && this.zzjt.length > 0) {
                for (int i = 0; i < this.zzjt.length; ++i) {
                    final zzi zzi = this.zzjt[i];
                    if (zzi != null) {
                        zzsn.zza(1, zzi);
                    }
                }
            }
            if (this.zzju != null) {
                zzsn.zza(2, this.zzju);
            }
            if (!this.zzjv.equals("")) {
                zzsn.zzn(3, this.zzjv);
            }
            super.writeTo(zzsn);
        }
        
        public zzj zzP() {
            this.zzjt = zzi.zzN();
            this.zzju = null;
            this.zzjv = "";
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzj zzj(final zzsm zzsm) throws IOException {
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
                    case 10: {
                        final int zzc = zzsx.zzc(zzsm, 10);
                        int i;
                        if (this.zzjt == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzjt.length;
                        }
                        final zzi[] zzjt = new zzi[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzjt, 0, zzjt, 0, i);
                        }
                        while (i < -1 + zzjt.length) {
                            zzsm.zza(zzjt[i] = new zzi());
                            zzsm.zzIX();
                            ++i;
                        }
                        zzsm.zza(zzjt[i] = new zzi());
                        this.zzjt = zzjt;
                        continue;
                    }
                    case 18: {
                        if (this.zzju == null) {
                            this.zzju = new zzf();
                        }
                        zzsm.zza(this.zzju);
                        continue;
                    }
                    case 26: {
                        this.zzjv = zzsm.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzjt != null && this.zzjt.length > 0) {
                for (int i = 0; i < this.zzjt.length; ++i) {
                    final zzi zzi = this.zzjt[i];
                    if (zzi != null) {
                        zzz += zzsn.zzc(1, zzi);
                    }
                }
            }
            if (this.zzju != null) {
                zzz += zzsn.zzc(2, this.zzju);
            }
            if (!this.zzjv.equals("")) {
                zzz += zzsn.zzo(3, this.zzjv);
            }
            return zzz;
        }
    }
}
