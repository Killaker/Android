package com.google.android.gms.internal;

import java.io.*;

public final class zzsx
{
    public static final boolean[] zzbuA;
    public static final String[] zzbuB;
    public static final byte[][] zzbuC;
    public static final byte[] zzbuD;
    public static final int[] zzbuw;
    public static final long[] zzbux;
    public static final float[] zzbuy;
    public static final double[] zzbuz;
    
    static {
        zzbuw = new int[0];
        zzbux = new long[0];
        zzbuy = new float[0];
        zzbuz = new double[0];
        zzbuA = new boolean[0];
        zzbuB = new String[0];
        zzbuC = new byte[0][];
        zzbuD = new byte[0];
    }
    
    static int zzF(final int n, final int n2) {
        return n2 | n << 3;
    }
    
    public static boolean zzb(final zzsm zzsm, final int n) throws IOException {
        return zzsm.zzmo(n);
    }
    
    public static final int zzc(final zzsm zzsm, final int n) throws IOException {
        int n2 = 1;
        final int position = zzsm.getPosition();
        zzsm.zzmo(n);
        while (zzsm.zzIX() == n) {
            zzsm.zzmo(n);
            ++n2;
        }
        zzsm.zzms(position);
        return n2;
    }
    
    static int zzmI(final int n) {
        return n & 0x7;
    }
    
    public static int zzmJ(final int n) {
        return n >>> 3;
    }
}
