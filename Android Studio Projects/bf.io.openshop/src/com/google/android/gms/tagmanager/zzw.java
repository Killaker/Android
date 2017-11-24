package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.concurrent.*;
import android.database.*;
import android.content.*;
import android.text.*;
import android.database.sqlite.*;
import java.util.*;
import android.os.*;

class zzw implements zzc
{
    private static final String zzbiB;
    private final Context mContext;
    private final Executor zzbiC;
    private zza zzbiD;
    private int zzbiE;
    private zzmq zzqW;
    
    static {
        zzbiB = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", "key", "value", "expires");
    }
    
    public zzw(final Context context) {
        this(context, zzmt.zzsc(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }
    
    zzw(final Context mContext, final zzmq zzqW, final String s, final int zzbiE, final Executor zzbiC) {
        this.mContext = mContext;
        this.zzqW = zzqW;
        this.zzbiE = zzbiE;
        this.zzbiC = zzbiC;
        this.zzbiD = new zza(this.mContext, s);
    }
    
    private List<DataLayer.zza> zzC(final List<zzb> list) {
        final ArrayList<DataLayer.zza> list2 = new ArrayList<DataLayer.zza>();
        for (final zzb zzb : list) {
            list2.add(new DataLayer.zza(zzb.zzvs, this.zzw(zzb.zzbiK)));
        }
        return list2;
    }
    
    private List<zzb> zzD(final List<DataLayer.zza> list) {
        final ArrayList<zzb> list2 = new ArrayList<zzb>();
        for (final DataLayer.zza zza : list) {
            list2.add(new zzb(zza.zzvs, this.zzJ(zza.zzNc)));
        }
        return list2;
    }
    
    private List<DataLayer.zza> zzGr() {
        try {
            this.zzal(this.zzqW.currentTimeMillis());
            return this.zzC(this.zzGs());
        }
        finally {
            this.zzGu();
        }
    }
    
    private List<zzb> zzGs() {
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for loadSerialized.");
        final ArrayList<zzb> list = new ArrayList<zzb>();
        if (zzgb == null) {
            return list;
        }
        final Cursor query = zzgb.query("datalayer", new String[] { "key", "value" }, (String)null, (String[])null, (String)null, (String)null, "ID", (String)null);
        try {
            while (query.moveToNext()) {
                list.add(new zzb(query.getString(0), query.getBlob(1)));
            }
        }
        finally {
            query.close();
        }
        query.close();
        return list;
    }
    
    private int zzGt() {
        Cursor rawQuery = null;
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for getNumStoredEntries.");
        int n = 0;
        if (zzgb == null) {
            return n;
        }
        try {
            rawQuery = zzgb.rawQuery("SELECT COUNT(*) from datalayer", (String[])null);
            final boolean moveToFirst = rawQuery.moveToFirst();
            n = 0;
            if (moveToFirst) {
                n = (int)rawQuery.getLong(0);
            }
            return n;
        }
        catch (SQLiteException ex) {
            zzbg.zzaK("Error getting numStoredEntries");
            n = 0;
            return 0;
        }
        finally {
            if (rawQuery != null) {
                rawQuery.close();
            }
        }
    }
    
    private void zzGu() {
        try {
            this.zzbiD.close();
        }
        catch (SQLiteException ex) {}
    }
    
    private byte[] zzJ(final Object p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/ByteArrayOutputStream;
        //     3: dup            
        //     4: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //     7: astore_2       
        //     8: new             Ljava/io/ObjectOutputStream;
        //    11: dup            
        //    12: aload_2        
        //    13: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    16: astore_3       
        //    17: aload_3        
        //    18: aload_1        
        //    19: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    22: aload_2        
        //    23: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    26: astore          8
        //    28: aload_3        
        //    29: ifnull          36
        //    32: aload_3        
        //    33: invokevirtual   java/io/ObjectOutputStream.close:()V
        //    36: aload_2        
        //    37: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    40: aload           8
        //    42: areturn        
        //    43: astore          11
        //    45: aconst_null    
        //    46: astore_3       
        //    47: aload_3        
        //    48: ifnull          55
        //    51: aload_3        
        //    52: invokevirtual   java/io/ObjectOutputStream.close:()V
        //    55: aload_2        
        //    56: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    59: aconst_null    
        //    60: areturn        
        //    61: astore          5
        //    63: aconst_null    
        //    64: areturn        
        //    65: astore          10
        //    67: aconst_null    
        //    68: astore_3       
        //    69: aload           10
        //    71: astore          6
        //    73: aload_3        
        //    74: ifnull          81
        //    77: aload_3        
        //    78: invokevirtual   java/io/ObjectOutputStream.close:()V
        //    81: aload_2        
        //    82: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    85: aload           6
        //    87: athrow         
        //    88: astore          7
        //    90: goto            85
        //    93: astore          6
        //    95: goto            73
        //    98: astore          4
        //   100: goto            47
        //   103: astore          9
        //   105: aload           8
        //   107: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      17     43     47     Ljava/io/IOException;
        //  8      17     65     73     Any
        //  17     28     98     103    Ljava/io/IOException;
        //  17     28     93     98     Any
        //  32     36     103    108    Ljava/io/IOException;
        //  36     40     103    108    Ljava/io/IOException;
        //  51     55     61     65     Ljava/io/IOException;
        //  55     59     61     65     Ljava/io/IOException;
        //  77     81     88     93     Ljava/io/IOException;
        //  81     85     88     93     Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void zzal(final long n) {
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for deleteOlderThan.");
        if (zzgb == null) {
            return;
        }
        try {
            zzbg.v("Deleted " + zzgb.delete("datalayer", "expires <= ?", new String[] { Long.toString(n) }) + " expired items");
        }
        catch (SQLiteException ex) {
            zzbg.zzaK("Error deleting old entries.");
        }
    }
    
    private void zzb(final List<zzb> list, final long n) {
        // monitorenter(this)
        try {
            final long currentTimeMillis = this.zzqW.currentTimeMillis();
            this.zzal(currentTimeMillis);
            this.zzkf(list.size());
            this.zzc(list, currentTimeMillis + n);
            return;
        }
        finally {
            this.zzGu();
        }
        try {}
        finally {
        }
        // monitorexit(this)
    }
    
    private void zzc(final List<zzb> list, final long n) {
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for writeEntryToDatabase.");
        if (zzgb != null) {
            for (final zzb zzb : list) {
                final ContentValues contentValues = new ContentValues();
                contentValues.put("expires", n);
                contentValues.put("key", zzb.zzvs);
                contentValues.put("value", zzb.zzbiK);
                zzgb.insert("datalayer", (String)null, contentValues);
            }
        }
    }
    
    private void zze(final String[] array) {
        if (array != null && array.length != 0) {
            final SQLiteDatabase zzgb = this.zzgb("Error opening database for deleteEntries.");
            if (zzgb != null) {
                final String format = String.format("%s in (%s)", "ID", TextUtils.join((CharSequence)",", (Iterable)Collections.nCopies(array.length, "?")));
                try {
                    zzgb.delete("datalayer", format, array);
                }
                catch (SQLiteException ex) {
                    zzbg.zzaK("Error deleting entries " + Arrays.toString(array));
                }
            }
        }
    }
    
    private void zzga(final String s) {
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for clearKeysWithPrefix.");
        if (zzgb == null) {
            return;
        }
        try {
            zzbg.v("Cleared " + zzgb.delete("datalayer", "key = ? OR key LIKE ?", new String[] { s, s + ".%" }) + " items");
        }
        catch (SQLiteException ex) {
            zzbg.zzaK("Error deleting entries with key prefix: " + s + " (" + ex + ").");
        }
        finally {
            this.zzGu();
        }
    }
    
    private SQLiteDatabase zzgb(final String s) {
        try {
            return this.zzbiD.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            zzbg.zzaK(s);
            return null;
        }
    }
    
    private void zzkf(final int n) {
        final int n2 = n + (this.zzGt() - this.zzbiE);
        if (n2 > 0) {
            final List<String> zzkg = this.zzkg(n2);
            zzbg.zzaJ("DataLayer store full, deleting " + zzkg.size() + " entries to make room.");
            this.zze(zzkg.toArray(new String[0]));
        }
    }
    
    private List<String> zzkg(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2       
        //     8: iload_1        
        //     9: ifgt            20
        //    12: ldc_w           "Invalid maxEntries specified. Skipping."
        //    15: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //    18: aload_2        
        //    19: areturn        
        //    20: aload_0        
        //    21: ldc_w           "Error opening database for peekEntryIds."
        //    24: invokespecial   com/google/android/gms/tagmanager/zzw.zzgb:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //    27: astore_3       
        //    28: aload_3        
        //    29: ifnonnull       34
        //    32: aload_2        
        //    33: areturn        
        //    34: aload_3        
        //    35: ldc             "datalayer"
        //    37: iconst_1       
        //    38: anewarray       Ljava/lang/String;
        //    41: dup            
        //    42: iconst_0       
        //    43: ldc             "ID"
        //    45: aastore        
        //    46: aconst_null    
        //    47: aconst_null    
        //    48: aconst_null    
        //    49: aconst_null    
        //    50: ldc_w           "%s ASC"
        //    53: iconst_1       
        //    54: anewarray       Ljava/lang/Object;
        //    57: dup            
        //    58: iconst_0       
        //    59: ldc             "ID"
        //    61: aastore        
        //    62: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    65: iload_1        
        //    66: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //    69: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    72: astore          7
        //    74: aload           7
        //    76: astore          5
        //    78: aload           5
        //    80: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    85: ifeq            120
        //    88: aload_2        
        //    89: aload           5
        //    91: iconst_0       
        //    92: invokeinterface android/database/Cursor.getLong:(I)J
        //    97: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   100: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   105: pop            
        //   106: aload           5
        //   108: invokeinterface android/database/Cursor.moveToNext:()Z
        //   113: istore          9
        //   115: iload           9
        //   117: ifne            88
        //   120: aload           5
        //   122: ifnull          132
        //   125: aload           5
        //   127: invokeinterface android/database/Cursor.close:()V
        //   132: aload_2        
        //   133: areturn        
        //   134: astore          6
        //   136: aconst_null    
        //   137: astore          5
        //   139: new             Ljava/lang/StringBuilder;
        //   142: dup            
        //   143: invokespecial   java/lang/StringBuilder.<init>:()V
        //   146: ldc_w           "Error in peekEntries fetching entryIds: "
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: aload           6
        //   154: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   163: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   166: aload           5
        //   168: ifnull          132
        //   171: aload           5
        //   173: invokeinterface android/database/Cursor.close:()V
        //   178: goto            132
        //   181: astore          4
        //   183: aconst_null    
        //   184: astore          5
        //   186: aload           5
        //   188: ifnull          198
        //   191: aload           5
        //   193: invokeinterface android/database/Cursor.close:()V
        //   198: aload           4
        //   200: athrow         
        //   201: astore          4
        //   203: goto            186
        //   206: astore          6
        //   208: goto            139
        //    Signature:
        //  (I)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  34     74     134    139    Landroid/database/sqlite/SQLiteException;
        //  34     74     181    186    Any
        //  78     88     206    211    Landroid/database/sqlite/SQLiteException;
        //  78     88     201    206    Any
        //  88     115    206    211    Landroid/database/sqlite/SQLiteException;
        //  88     115    201    206    Any
        //  139    166    201    206    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private Object zzw(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/ByteArrayInputStream;
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //     8: astore_2       
        //     9: new             Ljava/io/ObjectInputStream;
        //    12: dup            
        //    13: aload_2        
        //    14: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    17: astore_3       
        //    18: aload_3        
        //    19: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //    22: astore          10
        //    24: aload_3        
        //    25: ifnull          32
        //    28: aload_3        
        //    29: invokevirtual   java/io/ObjectInputStream.close:()V
        //    32: aload_2        
        //    33: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //    36: aload           10
        //    38: areturn        
        //    39: astore          14
        //    41: aconst_null    
        //    42: astore_3       
        //    43: aload_3        
        //    44: ifnull          51
        //    47: aload_3        
        //    48: invokevirtual   java/io/ObjectInputStream.close:()V
        //    51: aload_2        
        //    52: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //    55: aconst_null    
        //    56: areturn        
        //    57: astore          5
        //    59: aconst_null    
        //    60: areturn        
        //    61: astore          13
        //    63: aconst_null    
        //    64: astore_3       
        //    65: aload_3        
        //    66: ifnull          73
        //    69: aload_3        
        //    70: invokevirtual   java/io/ObjectInputStream.close:()V
        //    73: aload_2        
        //    74: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //    77: aconst_null    
        //    78: areturn        
        //    79: astore          7
        //    81: aconst_null    
        //    82: areturn        
        //    83: astore          12
        //    85: aconst_null    
        //    86: astore_3       
        //    87: aload           12
        //    89: astore          8
        //    91: aload_3        
        //    92: ifnull          99
        //    95: aload_3        
        //    96: invokevirtual   java/io/ObjectInputStream.close:()V
        //    99: aload_2        
        //   100: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   103: aload           8
        //   105: athrow         
        //   106: astore          9
        //   108: goto            103
        //   111: astore          8
        //   113: goto            91
        //   116: astore          6
        //   118: goto            65
        //   121: astore          4
        //   123: goto            43
        //   126: astore          11
        //   128: aload           10
        //   130: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  9      18     39     43     Ljava/io/IOException;
        //  9      18     61     65     Ljava/lang/ClassNotFoundException;
        //  9      18     83     91     Any
        //  18     24     121    126    Ljava/io/IOException;
        //  18     24     116    121    Ljava/lang/ClassNotFoundException;
        //  18     24     111    116    Any
        //  28     32     126    131    Ljava/io/IOException;
        //  32     36     126    131    Ljava/io/IOException;
        //  47     51     57     61     Ljava/io/IOException;
        //  51     55     57     61     Ljava/io/IOException;
        //  69     73     79     83     Ljava/io/IOException;
        //  73     77     79     83     Ljava/io/IOException;
        //  95     99     106    111    Ljava/io/IOException;
        //  99     103    106    111    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void zza(final zzc.zza zza) {
        this.zzbiC.execute(new Runnable() {
            @Override
            public void run() {
                zza.zzB(zzw.this.zzGr());
            }
        });
    }
    
    @Override
    public void zza(final List<DataLayer.zza> list, final long n) {
        this.zzbiC.execute(new Runnable() {
            final /* synthetic */ List zzbiF = zzw.this.zzD(list);
            
            @Override
            public void run() {
                zzw.this.zzb(this.zzbiF, n);
            }
        });
    }
    
    @Override
    public void zzfZ(final String s) {
        this.zzbiC.execute(new Runnable() {
            @Override
            public void run() {
                zzw.this.zzga(s);
            }
        });
    }
    
    class zza extends SQLiteOpenHelper
    {
        zza(final Context context, final String s) {
            super(context, s, (SQLiteDatabase$CursorFactory)null, 1);
        }
        
        private boolean zza(final String p0, final SQLiteDatabase p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aconst_null    
            //     1: astore_3       
            //     2: aload_2        
            //     3: ldc             "SQLITE_MASTER"
            //     5: iconst_1       
            //     6: anewarray       Ljava/lang/String;
            //     9: dup            
            //    10: iconst_0       
            //    11: ldc             "name"
            //    13: aastore        
            //    14: ldc             "name=?"
            //    16: iconst_1       
            //    17: anewarray       Ljava/lang/String;
            //    20: dup            
            //    21: iconst_0       
            //    22: aload_1        
            //    23: aastore        
            //    24: aconst_null    
            //    25: aconst_null    
            //    26: aconst_null    
            //    27: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
            //    30: astore          8
            //    32: aload           8
            //    34: invokeinterface android/database/Cursor.moveToFirst:()Z
            //    39: istore          10
            //    41: aload           8
            //    43: ifnull          53
            //    46: aload           8
            //    48: invokeinterface android/database/Cursor.close:()V
            //    53: iload           10
            //    55: ireturn        
            //    56: astore          5
            //    58: aconst_null    
            //    59: astore          6
            //    61: new             Ljava/lang/StringBuilder;
            //    64: dup            
            //    65: invokespecial   java/lang/StringBuilder.<init>:()V
            //    68: ldc             "Error querying for table "
            //    70: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    73: aload_1        
            //    74: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    77: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    80: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
            //    83: aload           6
            //    85: ifnull          95
            //    88: aload           6
            //    90: invokeinterface android/database/Cursor.close:()V
            //    95: iconst_0       
            //    96: ireturn        
            //    97: astore          4
            //    99: aload_3        
            //   100: ifnull          109
            //   103: aload_3        
            //   104: invokeinterface android/database/Cursor.close:()V
            //   109: aload           4
            //   111: athrow         
            //   112: astore          4
            //   114: aload           8
            //   116: astore_3       
            //   117: goto            99
            //   120: astore          7
            //   122: aload           6
            //   124: astore_3       
            //   125: aload           7
            //   127: astore          4
            //   129: goto            99
            //   132: astore          9
            //   134: aload           8
            //   136: astore          6
            //   138: goto            61
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                     
            //  -----  -----  -----  -----  -----------------------------------------
            //  2      32     56     61     Landroid/database/sqlite/SQLiteException;
            //  2      32     97     99     Any
            //  32     41     132    141    Landroid/database/sqlite/SQLiteException;
            //  32     41     112    120    Any
            //  61     83     120    132    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        private void zzc(final SQLiteDatabase sqLiteDatabase) {
            final Cursor rawQuery = sqLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", (String[])null);
            final HashSet<String> set = new HashSet<String>();
            try {
                final String[] columnNames = rawQuery.getColumnNames();
                for (int i = 0; i < columnNames.length; ++i) {
                    set.add(columnNames[i]);
                }
                rawQuery.close();
                if (!set.remove("key") || !set.remove("value") || !set.remove("ID") || !set.remove("expires")) {
                    throw new SQLiteException("Database column missing");
                }
            }
            finally {
                rawQuery.close();
            }
            if (!set.isEmpty()) {
                throw new SQLiteException("Database has extra columns");
            }
        }
        
        public SQLiteDatabase getWritableDatabase() {
            while (true) {
                try {
                    SQLiteDatabase sqLiteDatabase = super.getWritableDatabase();
                    if (sqLiteDatabase == null) {
                        sqLiteDatabase = super.getWritableDatabase();
                    }
                    return sqLiteDatabase;
                }
                catch (SQLiteException ex) {
                    zzw.this.mContext.getDatabasePath("google_tagmanager.db").delete();
                    final SQLiteDatabase sqLiteDatabase = null;
                    continue;
                }
                break;
            }
        }
        
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            zzal.zzbo(sqLiteDatabase.getPath());
        }
        
        public void onOpen(final SQLiteDatabase sqLiteDatabase) {
            while (true) {
                if (Build$VERSION.SDK_INT < 15) {
                    final Cursor rawQuery = sqLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[])null);
                    try {
                        rawQuery.moveToFirst();
                        rawQuery.close();
                        if (!this.zza("datalayer", sqLiteDatabase)) {
                            sqLiteDatabase.execSQL(zzw.zzbiB);
                            return;
                        }
                    }
                    finally {
                        rawQuery.close();
                    }
                    this.zzc(sqLiteDatabase);
                    return;
                }
                continue;
            }
        }
        
        public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        }
    }
    
    private static class zzb
    {
        final byte[] zzbiK;
        final String zzvs;
        
        zzb(final String zzvs, final byte[] zzbiK) {
            this.zzvs = zzvs;
            this.zzbiK = zzbiK;
        }
        
        @Override
        public String toString() {
            return "KeyAndSerialized: key = " + this.zzvs + " serialized hash = " + Arrays.hashCode(this.zzbiK);
        }
    }
}
