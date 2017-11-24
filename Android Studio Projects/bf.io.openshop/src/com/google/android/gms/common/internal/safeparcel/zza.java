package com.google.android.gms.common.internal.safeparcel;

import java.math.*;
import java.util.*;
import android.os.*;

public class zza
{
    public static BigDecimal[] zzA(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final BigDecimal[] array = new BigDecimal[int1];
        for (int i = 0; i < int1; ++i) {
            array[i] = new BigDecimal(new BigInteger(parcel.createByteArray()), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + zza);
        return array;
    }
    
    public static String[] zzB(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final String[] stringArray = parcel.createStringArray();
        parcel.setDataPosition(zza + dataPosition);
        return stringArray;
    }
    
    public static ArrayList<Integer> zzC(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            list.add(parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + zza);
        return list;
    }
    
    public static ArrayList<String> zzD(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final ArrayList stringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(zza + dataPosition);
        return (ArrayList<String>)stringArrayList;
    }
    
    public static Parcel zzE(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, zza);
        parcel.setDataPosition(zza + dataPosition);
        return obtain;
    }
    
    public static Parcel[] zzF(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final Parcel[] array = new Parcel[int1];
        for (int i = 0; i < int1; ++i) {
            final int int2 = parcel.readInt();
            if (int2 != 0) {
                final int dataPosition2 = parcel.dataPosition();
                final Parcel obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, int2);
                array[i] = obtain;
                parcel.setDataPosition(int2 + dataPosition2);
            }
            else {
                array[i] = null;
            }
        }
        parcel.setDataPosition(dataPosition + zza);
        return array;
    }
    
    public static int zza(final Parcel parcel, final int n) {
        if ((n & 0xFFFF0000) != 0xFFFF0000) {
            return 0xFFFF & n >> 16;
        }
        return parcel.readInt();
    }
    
    public static <T extends Parcelable> T zza(final Parcel parcel, final int n, final Parcelable$Creator<T> parcelable$Creator) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final Parcelable parcelable = (Parcelable)parcelable$Creator.createFromParcel(parcel);
        parcel.setDataPosition(zza + dataPosition);
        return (T)parcelable;
    }
    
    private static void zza(final Parcel parcel, final int n, final int n2) {
        final int zza = zza(parcel, n);
        if (zza != n2) {
            throw new zza("Expected size " + n2 + " got " + zza + " (0x" + Integer.toHexString(zza) + ")", parcel);
        }
    }
    
    private static void zza(final Parcel parcel, final int n, final int n2, final int n3) {
        if (n2 != n3) {
            throw new zza("Expected size " + n3 + " got " + n2 + " (0x" + Integer.toHexString(n2) + ")", parcel);
        }
    }
    
    public static void zza(final Parcel parcel, final int n, final List list, final ClassLoader classLoader) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return;
        }
        parcel.readList(list, classLoader);
        parcel.setDataPosition(zza + dataPosition);
    }
    
    public static int zzat(final Parcel parcel) {
        return parcel.readInt();
    }
    
    public static int zzau(final Parcel parcel) {
        final int zzat = zzat(parcel);
        final int zza = zza(parcel, zzat);
        final int dataPosition = parcel.dataPosition();
        if (zzca(zzat) != 20293) {
            throw new zza("Expected object header. Got 0x" + Integer.toHexString(zzat), parcel);
        }
        final int n = dataPosition + zza;
        if (n < dataPosition || n > parcel.dataSize()) {
            throw new zza("Size read is invalid start=" + dataPosition + " end=" + n, parcel);
        }
        return n;
    }
    
    public static void zzb(final Parcel parcel, final int n) {
        parcel.setDataPosition(zza(parcel, n) + parcel.dataPosition());
    }
    
    public static <T> T[] zzb(final Parcel parcel, final int n, final Parcelable$Creator<T> parcelable$Creator) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final Object[] typedArray = parcel.createTypedArray((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(zza + dataPosition);
        return (T[])typedArray;
    }
    
    public static <T> ArrayList<T> zzc(final Parcel parcel, final int n, final Parcelable$Creator<T> parcelable$Creator) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(zza + dataPosition);
        return (ArrayList<T>)typedArrayList;
    }
    
    public static boolean zzc(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt() != 0;
    }
    
    public static int zzca(final int n) {
        return 0xFFFF & n;
    }
    
    public static Boolean zzd(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        if (zza == 0) {
            return null;
        }
        zza(parcel, n, zza, 4);
        return parcel.readInt() != 0;
    }
    
    public static byte zze(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return (byte)parcel.readInt();
    }
    
    public static short zzf(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return (short)parcel.readInt();
    }
    
    public static int zzg(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt();
    }
    
    public static Integer zzh(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        if (zza == 0) {
            return null;
        }
        zza(parcel, n, zza, 4);
        return parcel.readInt();
    }
    
    public static long zzi(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readLong();
    }
    
    public static Long zzj(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        if (zza == 0) {
            return null;
        }
        zza(parcel, n, zza, 8);
        return parcel.readLong();
    }
    
    public static BigInteger zzk(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        parcel.setDataPosition(zza + dataPosition);
        return new BigInteger(byteArray);
    }
    
    public static float zzl(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readFloat();
    }
    
    public static Float zzm(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        if (zza == 0) {
            return null;
        }
        zza(parcel, n, zza, 4);
        return parcel.readFloat();
    }
    
    public static double zzn(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readDouble();
    }
    
    public static BigDecimal zzo(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        final int int1 = parcel.readInt();
        parcel.setDataPosition(zza + dataPosition);
        return new BigDecimal(new BigInteger(byteArray), int1);
    }
    
    public static String zzp(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final String string = parcel.readString();
        parcel.setDataPosition(zza + dataPosition);
        return string;
    }
    
    public static IBinder zzq(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final IBinder strongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(zza + dataPosition);
        return strongBinder;
    }
    
    public static Bundle zzr(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final Bundle bundle = parcel.readBundle();
        parcel.setDataPosition(zza + dataPosition);
        return bundle;
    }
    
    public static byte[] zzs(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        parcel.setDataPosition(zza + dataPosition);
        return byteArray;
    }
    
    public static byte[][] zzt(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final byte[][] array = new byte[int1][];
        for (int i = 0; i < int1; ++i) {
            array[i] = parcel.createByteArray();
        }
        parcel.setDataPosition(dataPosition + zza);
        return array;
    }
    
    public static boolean[] zzu(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final boolean[] booleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(zza + dataPosition);
        return booleanArray;
    }
    
    public static int[] zzv(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final int[] intArray = parcel.createIntArray();
        parcel.setDataPosition(zza + dataPosition);
        return intArray;
    }
    
    public static long[] zzw(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final long[] longArray = parcel.createLongArray();
        parcel.setDataPosition(zza + dataPosition);
        return longArray;
    }
    
    public static BigInteger[] zzx(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final BigInteger[] array = new BigInteger[int1];
        for (int i = 0; i < int1; ++i) {
            array[i] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + zza);
        return array;
    }
    
    public static float[] zzy(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final float[] floatArray = parcel.createFloatArray();
        parcel.setDataPosition(zza + dataPosition);
        return floatArray;
    }
    
    public static double[] zzz(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final double[] doubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(zza + dataPosition);
        return doubleArray;
    }
    
    public static class zza extends RuntimeException
    {
        public zza(final String s, final Parcel parcel) {
            super(s + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }
}
