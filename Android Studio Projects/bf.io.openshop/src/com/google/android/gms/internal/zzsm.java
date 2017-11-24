package com.google.android.gms.internal;

import java.io.*;

public final class zzsm
{
    private final byte[] buffer;
    private int zzbtZ;
    private int zzbua;
    private int zzbub;
    private int zzbuc;
    private int zzbud;
    private int zzbue;
    private int zzbuf;
    private int zzbug;
    private int zzbuh;
    
    private zzsm(final byte[] buffer, final int n, final int n2) {
        this.zzbue = Integer.MAX_VALUE;
        this.zzbug = 64;
        this.zzbuh = 67108864;
        this.buffer = buffer;
        this.zzbtZ = n;
        this.zzbua = n + n2;
        this.zzbuc = n;
    }
    
    public static zzsm zzD(final byte[] array) {
        return zza(array, 0, array.length);
    }
    
    private void zzJj() {
        this.zzbua += this.zzbub;
        final int zzbua = this.zzbua;
        if (zzbua > this.zzbue) {
            this.zzbub = zzbua - this.zzbue;
            this.zzbua -= this.zzbub;
            return;
        }
        this.zzbub = 0;
    }
    
    public static zzsm zza(final byte[] array, final int n, final int n2) {
        return new zzsm(array, n, n2);
    }
    
    public static long zzan(final long n) {
        return n >>> 1 ^ -(0x1L & n);
    }
    
    public static int zzmp(final int n) {
        return n >>> 1 ^ -(n & 0x1);
    }
    
    public int getPosition() {
        return this.zzbuc - this.zzbtZ;
    }
    
    public byte[] readBytes() throws IOException {
        final int zzJf = this.zzJf();
        if (zzJf <= this.zzbua - this.zzbuc && zzJf > 0) {
            final byte[] array = new byte[zzJf];
            System.arraycopy(this.buffer, this.zzbuc, array, 0, zzJf);
            this.zzbuc += zzJf;
            return array;
        }
        if (zzJf == 0) {
            return zzsx.zzbuD;
        }
        return this.zzmt(zzJf);
    }
    
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.zzJi());
    }
    
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.zzJh());
    }
    
    public String readString() throws IOException {
        final int zzJf = this.zzJf();
        if (zzJf <= this.zzbua - this.zzbuc && zzJf > 0) {
            final String s = new String(this.buffer, this.zzbuc, zzJf, "UTF-8");
            this.zzbuc += zzJf;
            return s;
        }
        return new String(this.zzmt(zzJf), "UTF-8");
    }
    
    public int zzIX() throws IOException {
        if (this.zzJl()) {
            return this.zzbud = 0;
        }
        this.zzbud = this.zzJf();
        if (this.zzbud == 0) {
            throw zzst.zzJv();
        }
        return this.zzbud;
    }
    
    public void zzIY() throws IOException {
        int zzIX;
        do {
            zzIX = this.zzIX();
        } while (zzIX != 0 && this.zzmo(zzIX));
    }
    
    public long zzIZ() throws IOException {
        return this.zzJg();
    }
    
    public long zzJa() throws IOException {
        return this.zzJg();
    }
    
    public int zzJb() throws IOException {
        return this.zzJf();
    }
    
    public boolean zzJc() throws IOException {
        return this.zzJf() != 0;
    }
    
    public int zzJd() throws IOException {
        return zzmp(this.zzJf());
    }
    
    public long zzJe() throws IOException {
        return zzan(this.zzJg());
    }
    
    public int zzJf() throws IOException {
        int zzJm = this.zzJm();
        if (zzJm < 0) {
            final int n = zzJm & 0x7F;
            final byte zzJm2 = this.zzJm();
            if (zzJm2 >= 0) {
                return n | zzJm2 << 7;
            }
            final int n2 = n | (zzJm2 & 0x7F) << 7;
            final byte zzJm3 = this.zzJm();
            if (zzJm3 >= 0) {
                return n2 | zzJm3 << 14;
            }
            final int n3 = n2 | (zzJm3 & 0x7F) << 14;
            final byte zzJm4 = this.zzJm();
            if (zzJm4 >= 0) {
                return n3 | zzJm4 << 21;
            }
            final int n4 = n3 | (zzJm4 & 0x7F) << 21;
            final byte zzJm5 = this.zzJm();
            zzJm = (n4 | zzJm5 << 28);
            if (zzJm5 < 0) {
                for (int i = 0; i < 5; ++i) {
                    if (this.zzJm() >= 0) {
                        return zzJm;
                    }
                }
                throw zzst.zzJu();
            }
        }
        return zzJm;
    }
    
    public long zzJg() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte zzJm = this.zzJm();
            n |= (zzJm & 0x7F) << i;
            if ((zzJm & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw zzst.zzJu();
    }
    
    public int zzJh() throws IOException {
        return (this.zzJm() & 0xFF) | (this.zzJm() & 0xFF) << 8 | (this.zzJm() & 0xFF) << 16 | (this.zzJm() & 0xFF) << 24;
    }
    
    public long zzJi() throws IOException {
        return (0xFFL & this.zzJm()) | (0xFFL & this.zzJm()) << 8 | (0xFFL & this.zzJm()) << 16 | (0xFFL & this.zzJm()) << 24 | (0xFFL & this.zzJm()) << 32 | (0xFFL & this.zzJm()) << 40 | (0xFFL & this.zzJm()) << 48 | (0xFFL & this.zzJm()) << 56;
    }
    
    public int zzJk() {
        if (this.zzbue == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzbue - this.zzbuc;
    }
    
    public boolean zzJl() {
        return this.zzbuc == this.zzbua;
    }
    
    public byte zzJm() throws IOException {
        if (this.zzbuc == this.zzbua) {
            throw zzst.zzJs();
        }
        return this.buffer[this.zzbuc++];
    }
    
    public void zza(final zzsu zzsu) throws IOException {
        final int zzJf = this.zzJf();
        if (this.zzbuf >= this.zzbug) {
            throw zzst.zzJy();
        }
        final int zzmq = this.zzmq(zzJf);
        ++this.zzbuf;
        zzsu.mergeFrom(this);
        this.zzmn(0);
        --this.zzbuf;
        this.zzmr(zzmq);
    }
    
    public void zza(final zzsu zzsu, final int n) throws IOException {
        if (this.zzbuf >= this.zzbug) {
            throw zzst.zzJy();
        }
        ++this.zzbuf;
        zzsu.mergeFrom(this);
        this.zzmn(zzsx.zzF(n, 4));
        --this.zzbuf;
    }
    
    public void zzmn(final int n) throws zzst {
        if (this.zzbud != n) {
            throw zzst.zzJw();
        }
    }
    
    public boolean zzmo(final int n) throws IOException {
        switch (zzsx.zzmI(n)) {
            default: {
                throw zzst.zzJx();
            }
            case 0: {
                this.zzJb();
                return true;
            }
            case 1: {
                this.zzJi();
                return true;
            }
            case 2: {
                this.zzmu(this.zzJf());
                return true;
            }
            case 3: {
                this.zzIY();
                this.zzmn(zzsx.zzF(zzsx.zzmJ(n), 4));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.zzJh();
                return true;
            }
        }
    }
    
    public int zzmq(final int n) throws zzst {
        if (n < 0) {
            throw zzst.zzJt();
        }
        final int zzbue = n + this.zzbuc;
        final int zzbue2 = this.zzbue;
        if (zzbue > zzbue2) {
            throw zzst.zzJs();
        }
        this.zzbue = zzbue;
        this.zzJj();
        return zzbue2;
    }
    
    public void zzmr(final int zzbue) {
        this.zzbue = zzbue;
        this.zzJj();
    }
    
    public void zzms(final int n) {
        if (n > this.zzbuc - this.zzbtZ) {
            throw new IllegalArgumentException("Position " + n + " is beyond current " + (this.zzbuc - this.zzbtZ));
        }
        if (n < 0) {
            throw new IllegalArgumentException("Bad position " + n);
        }
        this.zzbuc = n + this.zzbtZ;
    }
    
    public byte[] zzmt(final int n) throws IOException {
        if (n < 0) {
            throw zzst.zzJt();
        }
        if (n + this.zzbuc > this.zzbue) {
            this.zzmu(this.zzbue - this.zzbuc);
            throw zzst.zzJs();
        }
        if (n <= this.zzbua - this.zzbuc) {
            final byte[] array = new byte[n];
            System.arraycopy(this.buffer, this.zzbuc, array, 0, n);
            this.zzbuc += n;
            return array;
        }
        throw zzst.zzJs();
    }
    
    public void zzmu(final int n) throws IOException {
        if (n < 0) {
            throw zzst.zzJt();
        }
        if (n + this.zzbuc > this.zzbue) {
            this.zzmu(this.zzbue - this.zzbuc);
            throw zzst.zzJs();
        }
        if (n <= this.zzbua - this.zzbuc) {
            this.zzbuc += n;
            return;
        }
        throw zzst.zzJs();
    }
    
    public byte[] zzz(final int n, final int n2) {
        if (n2 == 0) {
            return zzsx.zzbuD;
        }
        final byte[] array = new byte[n2];
        System.arraycopy(this.buffer, n + this.zzbtZ, array, 0, n2);
        return array;
    }
}
