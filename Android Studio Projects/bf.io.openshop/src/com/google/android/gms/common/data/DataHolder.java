package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.annotation.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import android.os.*;
import android.database.*;
import android.net.*;
import java.util.*;

@KeepName
public final class DataHolder implements SafeParcelable
{
    public static final zze CREATOR;
    private static final zza zzajq;
    boolean mClosed;
    private final int mVersionCode;
    private final int zzade;
    private final String[] zzaji;
    Bundle zzajj;
    private final CursorWindow[] zzajk;
    private final Bundle zzajl;
    int[] zzajm;
    int zzajn;
    private Object zzajo;
    private boolean zzajp;
    
    static {
        CREATOR = new zze();
        zzajq = (zza)new zza(new String[0], null) {};
    }
    
    DataHolder(final int mVersionCode, final String[] zzaji, final CursorWindow[] zzajk, final int zzade, final Bundle zzajl) {
        this.mClosed = false;
        this.zzajp = true;
        this.mVersionCode = mVersionCode;
        this.zzaji = zzaji;
        this.zzajk = zzajk;
        this.zzade = zzade;
        this.zzajl = zzajl;
    }
    
    private DataHolder(final zza zza, final int n, final Bundle bundle) {
        this(zza.zzaji, zza(zza, -1), n, bundle);
    }
    
    public DataHolder(final String[] array, final CursorWindow[] array2, final int zzade, final Bundle zzajl) {
        this.mClosed = false;
        this.zzajp = true;
        this.mVersionCode = 1;
        this.zzaji = zzx.zzz(array);
        this.zzajk = zzx.zzz(array2);
        this.zzade = zzade;
        this.zzajl = zzajl;
        this.zzqd();
    }
    
    public static DataHolder zza(final int n, final Bundle bundle) {
        return new DataHolder(DataHolder.zzajq, n, bundle);
    }
    
    private static CursorWindow[] zza(final zza zza, final int n) {
        int i = 0;
        if (zza.zzaji.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList<CursorWindow> list2 = null;
        while (true) {
            int n2 = 0;
            final int size;
            Label_0087: {
                List<Map<K, String>> list = null;
                Label_0035: {
                    if (n < 0 || n >= zza.zzajr.size()) {
                        list = (List<Map<K, String>>)zza.zzajr;
                        break Label_0035;
                    }
                    CursorWindow cursorWindow = null;
                    boolean b = false;
                    Label_0214: {
                        break Label_0214;
                    Label_0410_Outer:
                        while (true) {
                            while (true) {
                            Label_0754:
                                while (true) {
                                    int n3 = 0;
                                    Label_0748: {
                                        try {
                                            if (!cursorWindow.allocRow()) {
                                                Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + n2 + ")");
                                                cursorWindow = new CursorWindow(false);
                                                cursorWindow.setStartPosition(n2);
                                                cursorWindow.setNumColumns(zza.zzaji.length);
                                                list2.add(cursorWindow);
                                                if (!cursorWindow.allocRow()) {
                                                    Log.e("DataHolder", "Unable to allocate row to hold data.");
                                                    list2.remove(cursorWindow);
                                                    return list2.toArray(new CursorWindow[list2.size()]);
                                                }
                                            }
                                            final Map<K, String> map = list.get(n2);
                                            n3 = 0;
                                            b = true;
                                            if (n3 >= zza.zzaji.length || !b) {
                                                break;
                                            }
                                            final String s = zza.zzaji[n3];
                                            final String value = map.get(s);
                                            if (value == null) {
                                                b = cursorWindow.putNull(n2, n3);
                                                break Label_0748;
                                            }
                                            if (value instanceof String) {
                                                b = cursorWindow.putString((String)value, n2, n3);
                                                break Label_0748;
                                            }
                                            if (value instanceof Long) {
                                                b = cursorWindow.putLong((long)value, n2, n3);
                                                break Label_0748;
                                            }
                                            if (value instanceof Integer) {
                                                b = cursorWindow.putLong((long)(int)value, n2, n3);
                                                break Label_0748;
                                            }
                                            if (value instanceof Boolean) {
                                                if (value) {
                                                    final long n4 = 1L;
                                                    b = cursorWindow.putLong(n4, n2, n3);
                                                    break Label_0748;
                                                }
                                                break Label_0754;
                                            }
                                            else {
                                                if (value instanceof byte[]) {
                                                    b = cursorWindow.putBlob((byte[])(byte[])(Object)value, n2, n3);
                                                    break Label_0748;
                                                }
                                                if (value instanceof Double) {
                                                    b = cursorWindow.putDouble((double)value, n2, n3);
                                                    break Label_0748;
                                                }
                                                if (value instanceof Float) {
                                                    b = cursorWindow.putDouble((double)(float)value, n2, n3);
                                                    break Label_0748;
                                                }
                                                throw new IllegalArgumentException("Unsupported object for column " + s + ": " + (Object)value);
                                            }
                                            list = zza.zzajr.subList(0, n);
                                            break Label_0035;
                                        }
                                        catch (RuntimeException ex) {
                                            while (i < list2.size()) {
                                                list2.get(i).close();
                                                ++i;
                                            }
                                            throw ex;
                                        }
                                        break;
                                    }
                                    ++n3;
                                    continue Label_0410_Outer;
                                }
                                final long n4 = 0L;
                                continue;
                            }
                        }
                    }
                    int n6;
                    CursorWindow cursorWindow3;
                    int n7;
                    if (!b) {
                        final int n5;
                        if (n5 != 0) {
                            throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                        }
                        Log.d("DataHolder", "Couldn't populate window data for row " + n2 + " - allocating new window.");
                        cursorWindow.freeLastRow();
                        final CursorWindow cursorWindow2 = new CursorWindow(false);
                        cursorWindow2.setStartPosition(n2);
                        cursorWindow2.setNumColumns(zza.zzaji.length);
                        list2.add(cursorWindow2);
                        n6 = n2 - 1;
                        cursorWindow3 = cursorWindow2;
                        n7 = 1;
                    }
                    else {
                        n6 = n2;
                        cursorWindow3 = cursorWindow;
                        n7 = 0;
                    }
                    final int n8 = n6 + 1;
                    final int n5 = n7;
                    cursorWindow = cursorWindow3;
                    n2 = n8;
                    break Label_0087;
                }
                size = list.size();
                CursorWindow cursorWindow = new CursorWindow(false);
                list2 = new ArrayList<CursorWindow>();
                list2.add(cursorWindow);
                cursorWindow.setNumColumns(zza.zzaji.length);
                n2 = 0;
                final int n5 = 0;
            }
            if (n2 < size) {
                continue;
            }
            break;
        }
        return list2.toArray(new CursorWindow[list2.size()]);
    }
    
    public static DataHolder zzbI(final int n) {
        return zza(n, null);
    }
    
    private void zzg(final String s, final int n) {
        if (this.zzajj == null || !this.zzajj.containsKey(s)) {
            throw new IllegalArgumentException("No such column: " + s);
        }
        if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (n < 0 || n >= this.zzajn) {
            throw new CursorIndexOutOfBoundsException(n, this.zzajn);
        }
    }
    
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.zzajk.length; ++i) {
                    this.zzajk[i].close();
                }
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    protected void finalize() throws Throwable {
        try {
            if (this.zzajp && this.zzajk.length > 0 && !this.isClosed()) {
                String s;
                if (this.zzajo == null) {
                    s = "internal object: " + this.toString();
                }
                else {
                    s = this.zzajo.toString();
                }
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + s + ")");
                this.close();
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public int getCount() {
        return this.zzajn;
    }
    
    public int getStatusCode() {
        return this.zzade;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public boolean isClosed() {
        synchronized (this) {
            return this.mClosed;
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
    
    public void zza(final String s, final int n, final int n2, final CharArrayBuffer charArrayBuffer) {
        this.zzg(s, n);
        this.zzajk[n2].copyStringToBuffer(n, this.zzajj.getInt(s), charArrayBuffer);
    }
    
    public long zzb(final String s, final int n, final int n2) {
        this.zzg(s, n);
        return this.zzajk[n2].getLong(n, this.zzajj.getInt(s));
    }
    
    public int zzbH(final int n) {
        int i = 0;
        zzx.zzab(n >= 0 && n < this.zzajn);
        while (i < this.zzajm.length) {
            if (n < this.zzajm[i]) {
                --i;
                break;
            }
            ++i;
        }
        if (i == this.zzajm.length) {
            --i;
        }
        return i;
    }
    
    public int zzc(final String s, final int n, final int n2) {
        this.zzg(s, n);
        return this.zzajk[n2].getInt(n, this.zzajj.getInt(s));
    }
    
    public boolean zzcz(final String s) {
        return this.zzajj.containsKey(s);
    }
    
    public String zzd(final String s, final int n, final int n2) {
        this.zzg(s, n);
        return this.zzajk[n2].getString(n, this.zzajj.getInt(s));
    }
    
    public boolean zze(final String s, final int n, final int n2) {
        this.zzg(s, n);
        return Long.valueOf(this.zzajk[n2].getLong(n, this.zzajj.getInt(s))) == 1L;
    }
    
    public float zzf(final String s, final int n, final int n2) {
        this.zzg(s, n);
        return this.zzajk[n2].getFloat(n, this.zzajj.getInt(s));
    }
    
    public byte[] zzg(final String s, final int n, final int n2) {
        this.zzg(s, n);
        return this.zzajk[n2].getBlob(n, this.zzajj.getInt(s));
    }
    
    public Uri zzh(final String s, final int n, final int n2) {
        final String zzd = this.zzd(s, n, n2);
        if (zzd == null) {
            return null;
        }
        return Uri.parse(zzd);
    }
    
    public boolean zzi(final String s, final int n, final int n2) {
        this.zzg(s, n);
        return this.zzajk[n2].isNull(n, this.zzajj.getInt(s));
    }
    
    public Bundle zzpZ() {
        return this.zzajl;
    }
    
    public void zzqd() {
        int i = 0;
        this.zzajj = new Bundle();
        for (int j = 0; j < this.zzaji.length; ++j) {
            this.zzajj.putInt(this.zzaji[j], j);
        }
        this.zzajm = new int[this.zzajk.length];
        int zzajn = 0;
        while (i < this.zzajk.length) {
            this.zzajm[i] = zzajn;
            zzajn += this.zzajk[i].getNumRows() - (zzajn - this.zzajk[i].getStartPosition());
            ++i;
        }
        this.zzajn = zzajn;
    }
    
    String[] zzqe() {
        return this.zzaji;
    }
    
    CursorWindow[] zzqf() {
        return this.zzajk;
    }
    
    public void zzu(final Object zzajo) {
        this.zzajo = zzajo;
    }
    
    public static class zza
    {
        private final String[] zzaji;
        private final ArrayList<HashMap<String, Object>> zzajr;
        private final String zzajs;
        private final HashMap<Object, Integer> zzajt;
        private boolean zzaju;
        private String zzajv;
        
        private zza(final String[] array, final String zzajs) {
            this.zzaji = zzx.zzz(array);
            this.zzajr = new ArrayList<HashMap<String, Object>>();
            this.zzajs = zzajs;
            this.zzajt = new HashMap<Object, Integer>();
            this.zzaju = false;
            this.zzajv = null;
        }
    }
    
    public static class zzb extends RuntimeException
    {
        public zzb(final String s) {
            super(s);
        }
    }
}
