package com.google.android.gms.internal;

import java.nio.*;
import java.io.*;

public final class zzsn
{
    private final ByteBuffer zzbui;
    
    private zzsn(final ByteBuffer zzbui) {
        (this.zzbui = zzbui).order(ByteOrder.LITTLE_ENDIAN);
    }
    
    private zzsn(final byte[] array, final int n, final int n2) {
        this(ByteBuffer.wrap(array, n, n2));
    }
    
    public static int zzC(final int n, final int n2) {
        return zzmA(n) + zzmx(n2);
    }
    
    public static int zzD(final int n, final int n2) {
        return zzmA(n) + zzmy(n2);
    }
    
    public static zzsn zzE(final byte[] array) {
        return zzb(array, 0, array.length);
    }
    
    public static int zzG(final byte[] array) {
        return zzmC(array.length) + array.length;
    }
    
    private static int zza(final CharSequence charSequence, final int n) {
        final int length = charSequence.length();
        char c = '\0';
        for (int i = n; i < length; ++i) {
            final char char1 = charSequence.charAt(i);
            if (char1 < '\u0800') {
                c += (char)('\u007f' - char1 >>> 31);
            }
            else {
                c += '\u0002';
                if ('\ud800' <= char1 && char1 <= '\udfff') {
                    if (Character.codePointAt(charSequence, i) < 65536) {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i);
                    }
                    ++i;
                }
            }
        }
        return c;
    }
    
    private static int zza(final CharSequence charSequence, final byte[] array, final int n, final int n2) {
        int length;
        int i;
        int n3;
        for (length = charSequence.length(), i = 0, n3 = n + n2; i < length && i + n < n3; ++i) {
            final char char1 = charSequence.charAt(i);
            if (char1 >= '\u0080') {
                break;
            }
            array[n + i] = (byte)char1;
        }
        if (i == length) {
            return n + length;
        }
        int n4 = n + i;
        while (i < length) {
            final char char2 = charSequence.charAt(i);
            int n5 = 0;
            Label_0128: {
                if (char2 < '\u0080' && n4 < n3) {
                    n5 = n4 + 1;
                    array[n4] = (byte)char2;
                }
                else if (char2 < '\u0800' && n4 <= n3 - 2) {
                    final int n6 = n4 + 1;
                    array[n4] = (byte)('\u03c0' | char2 >>> 6);
                    n5 = n6 + 1;
                    array[n6] = (byte)('\u0080' | (char2 & '?'));
                }
                else if ((char2 < '\ud800' || '\udfff' < char2) && n4 <= n3 - 3) {
                    final int n7 = n4 + 1;
                    array[n4] = (byte)('\u01e0' | char2 >>> 12);
                    final int n8 = n7 + 1;
                    array[n7] = (byte)('\u0080' | ('?' & char2 >>> 6));
                    n5 = n8 + 1;
                    array[n8] = (byte)('\u0080' | (char2 & '?'));
                }
                else {
                    if (n4 <= n3 - 4) {
                        if (i + 1 != charSequence.length()) {
                            ++i;
                            final char char3 = charSequence.charAt(i);
                            if (Character.isSurrogatePair(char2, char3)) {
                                final int codePoint = Character.toCodePoint(char2, char3);
                                final int n9 = n4 + 1;
                                array[n4] = (byte)(0xF0 | codePoint >>> 18);
                                final int n10 = n9 + 1;
                                array[n9] = (byte)(0x80 | (0x3F & codePoint >>> 12));
                                final int n11 = n10 + 1;
                                array[n10] = (byte)(0x80 | (0x3F & codePoint >>> 6));
                                n5 = n11 + 1;
                                array[n11] = (byte)(0x80 | (codePoint & 0x3F));
                                break Label_0128;
                            }
                        }
                        throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                    }
                    if ('\ud800' <= char2 && char2 <= '\udfff' && (i + 1 == charSequence.length() || !Character.isSurrogatePair(char2, charSequence.charAt(i + 1)))) {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i);
                    }
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + char2 + " at index " + n4);
                }
            }
            ++i;
            n4 = n5;
        }
        return n4;
    }
    
    private static void zza(final CharSequence charSequence, final ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
                return;
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                final BufferOverflowException ex = new BufferOverflowException();
                ex.initCause(ex2);
                throw ex;
            }
        }
        zzb(charSequence, byteBuffer);
    }
    
    public static int zzaA(final boolean b) {
        return 1;
    }
    
    public static int zzar(final long n) {
        return zzav(n);
    }
    
    public static int zzas(final long n) {
        return zzav(n);
    }
    
    public static int zzat(final long n) {
        return zzav(zzax(n));
    }
    
    public static int zzav(final long n) {
        if ((0xFFFFFFFFFFFFFF80L & n) == 0x0L) {
            return 1;
        }
        if ((0xFFFFFFFFFFFFC000L & n) == 0x0L) {
            return 2;
        }
        if ((0xFFFFFFFFFFE00000L & n) == 0x0L) {
            return 3;
        }
        if ((0xFFFFFFFFF0000000L & n) == 0x0L) {
            return 4;
        }
        if ((0xFFFFFFF800000000L & n) == 0x0L) {
            return 5;
        }
        if ((0xFFFFFC0000000000L & n) == 0x0L) {
            return 6;
        }
        if ((0xFFFE000000000000L & n) == 0x0L) {
            return 7;
        }
        if ((0xFF00000000000000L & n) == 0x0L) {
            return 8;
        }
        if ((Long.MIN_VALUE & n) == 0x0L) {
            return 9;
        }
        return 10;
    }
    
    public static long zzax(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public static int zzb(final int n, final double n2) {
        return zzmA(n) + zzl(n2);
    }
    
    public static int zzb(final int n, final zzsu zzsu) {
        return 2 * zzmA(n) + zzd(zzsu);
    }
    
    public static int zzb(final int n, final byte[] array) {
        return zzmA(n) + zzG(array);
    }
    
    public static zzsn zzb(final byte[] array, final int n, final int n2) {
        return new zzsn(array, n, n2);
    }
    
    private static void zzb(final CharSequence charSequence, final ByteBuffer byteBuffer) {
        for (int length = charSequence.length(), i = 0; i < length; ++i) {
            final char char1 = charSequence.charAt(i);
            if (char1 < '\u0080') {
                byteBuffer.put((byte)char1);
            }
            else if (char1 < '\u0800') {
                byteBuffer.put((byte)('\u03c0' | char1 >>> 6));
                byteBuffer.put((byte)('\u0080' | (char1 & '?')));
            }
            else {
                if (char1 >= '\ud800' && '\udfff' >= char1) {
                    if (i + 1 != charSequence.length()) {
                        ++i;
                        final char char2 = charSequence.charAt(i);
                        if (Character.isSurrogatePair(char1, char2)) {
                            final int codePoint = Character.toCodePoint(char1, char2);
                            byteBuffer.put((byte)(0xF0 | codePoint >>> 18));
                            byteBuffer.put((byte)(0x80 | (0x3F & codePoint >>> 12)));
                            byteBuffer.put((byte)(0x80 | (0x3F & codePoint >>> 6)));
                            byteBuffer.put((byte)(0x80 | (codePoint & 0x3F)));
                            continue;
                        }
                    }
                    throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                }
                byteBuffer.put((byte)('\u01e0' | char1 >>> 12));
                byteBuffer.put((byte)('\u0080' | ('?' & char1 >>> 6)));
                byteBuffer.put((byte)('\u0080' | (char1 & '?')));
            }
        }
    }
    
    public static int zzc(final int n, final float n2) {
        return zzmA(n) + zzk(n2);
    }
    
    public static int zzc(final int n, final zzsu zzsu) {
        return zzmA(n) + zze(zzsu);
    }
    
    private static int zzc(final CharSequence charSequence) {
        int length;
        char c;
        for (length = charSequence.length(), c = '\0'; c < length && charSequence.charAt(c) < '\u0080'; ++c) {}
        char c2 = c;
        int n = length;
        while (c2 < length) {
            final char char1 = charSequence.charAt(c2);
            if (char1 >= '\u0800') {
                n += zza(charSequence, c2);
                break;
            }
            final char c3 = (char)(n + ('\u007f' - char1 >>> 31));
            ++c2;
            n = c3;
        }
        if (n < length) {
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (4294967296L + n));
        }
        return n;
    }
    
    public static int zzd(final int n, final long n2) {
        return zzmA(n) + zzas(n2);
    }
    
    public static int zzd(final zzsu zzsu) {
        return zzsu.getSerializedSize();
    }
    
    public static int zze(final int n, final long n2) {
        return zzmA(n) + zzat(n2);
    }
    
    public static int zze(final zzsu zzsu) {
        final int serializedSize = zzsu.getSerializedSize();
        return serializedSize + zzmC(serializedSize);
    }
    
    public static int zzf(final int n, final boolean b) {
        return zzmA(n) + zzaA(b);
    }
    
    public static int zzgO(final String s) {
        final int zzc = zzc(s);
        return zzc + zzmC(zzc);
    }
    
    public static int zzk(final float n) {
        return 4;
    }
    
    public static int zzl(final double n) {
        return 8;
    }
    
    public static int zzmA(final int n) {
        return zzmC(zzsx.zzF(n, 0));
    }
    
    public static int zzmC(final int n) {
        if ((n & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((n & 0xFFFFC000) == 0x0) {
            return 2;
        }
        if ((0xFFE00000 & n) == 0x0) {
            return 3;
        }
        if ((0xF0000000 & n) == 0x0) {
            return 4;
        }
        return 5;
    }
    
    public static int zzmE(final int n) {
        return n << 1 ^ n >> 31;
    }
    
    public static int zzmx(final int n) {
        if (n >= 0) {
            return zzmC(n);
        }
        return 10;
    }
    
    public static int zzmy(final int n) {
        return zzmC(zzmE(n));
    }
    
    public static int zzo(final int n, final String s) {
        return zzmA(n) + zzgO(s);
    }
    
    public void zzA(final int n, final int n2) throws IOException {
        this.zzE(n, 0);
        this.zzmv(n2);
    }
    
    public void zzB(final int n, final int n2) throws IOException {
        this.zzE(n, 0);
        this.zzmw(n2);
    }
    
    public void zzE(final int n, final int n2) throws IOException {
        this.zzmB(zzsx.zzF(n, n2));
    }
    
    public void zzF(final byte[] array) throws IOException {
        this.zzmB(array.length);
        this.zzH(array);
    }
    
    public void zzH(final byte[] array) throws IOException {
        this.zzc(array, 0, array.length);
    }
    
    public int zzJn() {
        return this.zzbui.remaining();
    }
    
    public void zzJo() {
        if (this.zzJn() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
    
    public void zza(final int n, final double n2) throws IOException {
        this.zzE(n, 1);
        this.zzk(n2);
    }
    
    public void zza(final int n, final long n2) throws IOException {
        this.zzE(n, 0);
        this.zzao(n2);
    }
    
    public void zza(final int n, final zzsu zzsu) throws IOException {
        this.zzE(n, 2);
        this.zzc(zzsu);
    }
    
    public void zza(final int n, final byte[] array) throws IOException {
        this.zzE(n, 2);
        this.zzF(array);
    }
    
    public void zzao(final long n) throws IOException {
        this.zzau(n);
    }
    
    public void zzap(final long n) throws IOException {
        this.zzau(n);
    }
    
    public void zzaq(final long n) throws IOException {
        this.zzau(zzax(n));
    }
    
    public void zzau(long n) throws IOException {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.zzmz(0x80 | (0x7F & (int)n));
            n >>>= 7;
        }
        this.zzmz((int)n);
    }
    
    public void zzaw(final long n) throws IOException {
        if (this.zzbui.remaining() < 8) {
            throw new zza(this.zzbui.position(), this.zzbui.limit());
        }
        this.zzbui.putLong(n);
    }
    
    public void zzaz(final boolean b) throws IOException {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.zzmz(n);
    }
    
    public void zzb(final byte b) throws IOException {
        if (!this.zzbui.hasRemaining()) {
            throw new zza(this.zzbui.position(), this.zzbui.limit());
        }
        this.zzbui.put(b);
    }
    
    public void zzb(final int n, final float n2) throws IOException {
        this.zzE(n, 5);
        this.zzj(n2);
    }
    
    public void zzb(final int n, final long n2) throws IOException {
        this.zzE(n, 0);
        this.zzap(n2);
    }
    
    public void zzb(final zzsu zzsu) throws IOException {
        zzsu.writeTo(this);
    }
    
    public void zzc(final int n, final long n2) throws IOException {
        this.zzE(n, 0);
        this.zzaq(n2);
    }
    
    public void zzc(final zzsu zzsu) throws IOException {
        this.zzmB(zzsu.getCachedSize());
        zzsu.writeTo(this);
    }
    
    public void zzc(final byte[] array, final int n, final int n2) throws IOException {
        if (this.zzbui.remaining() >= n2) {
            this.zzbui.put(array, n, n2);
            return;
        }
        throw new zza(this.zzbui.position(), this.zzbui.limit());
    }
    
    public void zze(final int n, final boolean b) throws IOException {
        this.zzE(n, 0);
        this.zzaz(b);
    }
    
    public void zzgN(final String s) throws IOException {
        Label_0158: {
            int zzmC;
            int position;
            try {
                zzmC = zzmC(s.length());
                if (zzmC != zzmC(3 * s.length())) {
                    break Label_0158;
                }
                position = this.zzbui.position();
                if (this.zzbui.remaining() < zzmC) {
                    throw new zza(zzmC + position, this.zzbui.limit());
                }
            }
            catch (BufferOverflowException ex) {
                final zza zza = new zza(this.zzbui.position(), this.zzbui.limit());
                zza.initCause(ex);
                throw zza;
            }
            this.zzbui.position(position + zzmC);
            zza(s, this.zzbui);
            final int position2 = this.zzbui.position();
            this.zzbui.position(position);
            this.zzmB(position2 - position - zzmC);
            this.zzbui.position(position2);
            return;
        }
        this.zzmB(zzc(s));
        zza(s, this.zzbui);
    }
    
    public void zzj(final float n) throws IOException {
        this.zzmD(Float.floatToIntBits(n));
    }
    
    public void zzk(final double n) throws IOException {
        this.zzaw(Double.doubleToLongBits(n));
    }
    
    public void zzmB(int n) throws IOException {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.zzmz(0x80 | (n & 0x7F));
            n >>>= 7;
        }
        this.zzmz(n);
    }
    
    public void zzmD(final int n) throws IOException {
        if (this.zzbui.remaining() < 4) {
            throw new zza(this.zzbui.position(), this.zzbui.limit());
        }
        this.zzbui.putInt(n);
    }
    
    public void zzmv(final int n) throws IOException {
        if (n >= 0) {
            this.zzmB(n);
            return;
        }
        this.zzau(n);
    }
    
    public void zzmw(final int n) throws IOException {
        this.zzmB(zzmE(n));
    }
    
    public void zzmz(final int n) throws IOException {
        this.zzb((byte)n);
    }
    
    public void zzn(final int n, final String s) throws IOException {
        this.zzE(n, 2);
        this.zzgN(s);
    }
    
    public static class zza extends IOException
    {
        zza(final int n, final int n2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + n + " limit " + n2 + ").");
        }
    }
}
