package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import android.content.*;
import android.database.*;
import android.text.*;
import android.database.sqlite.*;
import java.util.*;
import android.os.*;

class zzby implements zzau
{
    private static final String zzQR;
    private final Context mContext;
    private final zzb zzbjE;
    private volatile zzac zzbjF;
    private final zzav zzbjG;
    private final String zzbjH;
    private long zzbjI;
    private final int zzbjJ;
    private zzmq zzqW;
    
    static {
        zzQR = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    }
    
    zzby(final zzav zzav, final Context context) {
        this(zzav, context, "gtm_urls.db", 2000);
    }
    
    zzby(final zzav zzbjG, final Context context, final String zzbjH, final int zzbjJ) {
        this.mContext = context.getApplicationContext();
        this.zzbjH = zzbjH;
        this.zzbjG = zzbjG;
        this.zzqW = zzmt.zzsc();
        this.zzbjE = new zzb(this.mContext, this.zzbjH);
        this.zzbjF = new zzcx(this.mContext, (zzcx.zza)new zza());
        this.zzbjI = 0L;
        this.zzbjJ = zzbjJ;
    }
    
    private void zzGQ() {
        final int n = 1 + (this.zzGR() - this.zzbjJ);
        if (n > 0) {
            final List<String> zzkl = this.zzkl(n);
            zzbg.v("Store full, deleting " + zzkl.size() + " hits to make room.");
            this.zzf(zzkl.toArray(new String[0]));
        }
    }
    
    private void zzd(final long n, final long n2) {
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for getNumStoredHits.");
        if (zzgb == null) {
            return;
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("hit_first_send_time", n2);
        try {
            zzgb.update("gtm_hits", contentValues, "hit_id=?", new String[] { String.valueOf(n) });
        }
        catch (SQLiteException ex) {
            zzbg.zzaK("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + n);
            this.zzq(n);
        }
    }
    
    private SQLiteDatabase zzgb(final String s) {
        try {
            return this.zzbjE.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            zzbg.zzaK(s);
            return null;
        }
    }
    
    private void zzh(final long n, final String s) {
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for putHit");
        if (zzgb == null) {
            return;
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("hit_time", n);
        contentValues.put("hit_url", s);
        contentValues.put("hit_first_send_time", 0);
        try {
            zzgb.insert("gtm_hits", (String)null, contentValues);
            this.zzbjG.zzax(false);
        }
        catch (SQLiteException ex) {
            zzbg.zzaK("Error storing hit");
        }
    }
    
    private void zzq(final long n) {
        this.zzf(new String[] { String.valueOf(n) });
    }
    
    @Override
    public void dispatch() {
        zzbg.v("GTM Dispatch running...");
        if (this.zzbjF.zzGw()) {
            final List<zzaq> zzkm = this.zzkm(40);
            if (zzkm.isEmpty()) {
                zzbg.v("...nothing to dispatch");
                this.zzbjG.zzax(true);
                return;
            }
            this.zzbjF.zzE(zzkm);
            if (this.zzGS() > 0) {
                zzcu.zzHo().dispatch();
            }
        }
    }
    
    int zzGR() {
        Cursor rawQuery = null;
        final SQLiteDatabase zzgb = this.zzgb("Error opening database for getNumStoredHits.");
        int n = 0;
        if (zzgb == null) {
            return n;
        }
        try {
            rawQuery = zzgb.rawQuery("SELECT COUNT(*) from gtm_hits", (String[])null);
            final boolean moveToFirst = rawQuery.moveToFirst();
            n = 0;
            if (moveToFirst) {
                n = (int)rawQuery.getLong(0);
            }
            return n;
        }
        catch (SQLiteException ex) {
            zzbg.zzaK("Error getting numStoredHits");
            n = 0;
            return 0;
        }
        finally {
            if (rawQuery != null) {
                rawQuery.close();
            }
        }
    }
    
    int zzGS() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: aload_0        
        //     3: ldc             "Error opening database for getNumStoredHits."
        //     5: invokespecial   com/google/android/gms/tagmanager/zzby.zzgb:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //     8: astore_2       
        //     9: aload_2        
        //    10: ifnonnull       15
        //    13: iconst_0       
        //    14: ireturn        
        //    15: aload_2        
        //    16: ldc             "gtm_hits"
        //    18: iconst_2       
        //    19: anewarray       Ljava/lang/String;
        //    22: dup            
        //    23: iconst_0       
        //    24: ldc             "hit_id"
        //    26: aastore        
        //    27: dup            
        //    28: iconst_1       
        //    29: ldc             "hit_first_send_time"
        //    31: aastore        
        //    32: ldc_w           "hit_first_send_time=0"
        //    35: aconst_null    
        //    36: aconst_null    
        //    37: aconst_null    
        //    38: aconst_null    
        //    39: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    42: astore          8
        //    44: aload           8
        //    46: invokeinterface android/database/Cursor.getCount:()I
        //    51: istore          10
        //    53: iload           10
        //    55: istore          7
        //    57: aload           8
        //    59: ifnull          69
        //    62: aload           8
        //    64: invokeinterface android/database/Cursor.close:()V
        //    69: iload           7
        //    71: ireturn        
        //    72: astore          4
        //    74: aconst_null    
        //    75: astore          5
        //    77: ldc_w           "Error getting num untried hits"
        //    80: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //    83: aload           5
        //    85: ifnull          141
        //    88: aload           5
        //    90: invokeinterface android/database/Cursor.close:()V
        //    95: iconst_0       
        //    96: istore          7
        //    98: goto            69
        //   101: astore_3       
        //   102: aload_1        
        //   103: ifnull          112
        //   106: aload_1        
        //   107: invokeinterface android/database/Cursor.close:()V
        //   112: aload_3        
        //   113: athrow         
        //   114: astore_3       
        //   115: aload           8
        //   117: astore_1       
        //   118: goto            102
        //   121: astore          6
        //   123: aload           5
        //   125: astore_1       
        //   126: aload           6
        //   128: astore_3       
        //   129: goto            102
        //   132: astore          9
        //   134: aload           8
        //   136: astore          5
        //   138: goto            77
        //   141: iconst_0       
        //   142: istore          7
        //   144: goto            69
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  15     44     72     77     Landroid/database/sqlite/SQLiteException;
        //  15     44     101    102    Any
        //  44     53     132    141    Landroid/database/sqlite/SQLiteException;
        //  44     53     114    121    Any
        //  77     83     121    132    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void zzf(final String[] array) {
        int n = 1;
        if (array != null && array.length != 0) {
            final SQLiteDatabase zzgb = this.zzgb("Error opening database for deleteHits.");
            if (zzgb != null) {
                while (true) {
                    final Object[] array2 = new Object[n];
                    array2[0] = TextUtils.join((CharSequence)",", (Iterable)Collections.nCopies(array.length, "?"));
                    final String format = String.format("HIT_ID in (%s)", array2);
                    while (true) {
                        try {
                            zzgb.delete("gtm_hits", format, array);
                            final zzav zzbjG = this.zzbjG;
                            if (this.zzGR() == 0) {
                                zzbjG.zzax(n != 0);
                                return;
                            }
                        }
                        catch (SQLiteException ex) {
                            zzbg.zzaK("Error deleting hits");
                            return;
                        }
                        n = 0;
                        continue;
                    }
                }
            }
        }
    }
    
    @Override
    public void zzg(final long n, final String s) {
        this.zzjN();
        this.zzGQ();
        this.zzh(n, s);
    }
    
    int zzjN() {
        int n = 1;
        final long currentTimeMillis = this.zzqW.currentTimeMillis();
        if (currentTimeMillis > 86400000L + this.zzbjI) {
            this.zzbjI = currentTimeMillis;
            final SQLiteDatabase zzgb = this.zzgb("Error opening database for deleteStaleHits.");
            if (zzgb != null) {
                final long n2 = this.zzqW.currentTimeMillis() - 2592000000L;
                final String[] array = new String[n];
                array[0] = Long.toString(n2);
                final int delete = zzgb.delete("gtm_hits", "HIT_TIME < ?", array);
                final zzav zzbjG = this.zzbjG;
                if (this.zzGR() != 0) {
                    n = 0;
                }
                zzbjG.zzax(n != 0);
                return delete;
            }
        }
        return 0;
    }
    
    List<String> zzkl(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2       
        //     8: iload_1        
        //     9: ifgt            20
        //    12: ldc_w           "Invalid maxHits specified. Skipping"
        //    15: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //    18: aload_2        
        //    19: areturn        
        //    20: aload_0        
        //    21: ldc_w           "Error opening database for peekHitIds."
        //    24: invokespecial   com/google/android/gms/tagmanager/zzby.zzgb:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //    27: astore_3       
        //    28: aload_3        
        //    29: ifnonnull       34
        //    32: aload_2        
        //    33: areturn        
        //    34: aload_3        
        //    35: ldc             "gtm_hits"
        //    37: iconst_1       
        //    38: anewarray       Ljava/lang/String;
        //    41: dup            
        //    42: iconst_0       
        //    43: ldc             "hit_id"
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
        //    59: ldc             "hit_id"
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
        //   146: ldc_w           "Error in peekHits fetching hitIds: "
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
    
    public List<zzaq> zzkm(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2       
        //     8: aload_0        
        //     9: ldc_w           "Error opening database for peekHits"
        //    12: invokespecial   com/google/android/gms/tagmanager/zzby.zzgb:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //    15: astore_3       
        //    16: aload_3        
        //    17: ifnonnull       26
        //    20: aload_2        
        //    21: astore          9
        //    23: aload           9
        //    25: areturn        
        //    26: aconst_null    
        //    27: astore          4
        //    29: aload_3        
        //    30: ldc             "gtm_hits"
        //    32: iconst_3       
        //    33: anewarray       Ljava/lang/String;
        //    36: dup            
        //    37: iconst_0       
        //    38: ldc             "hit_id"
        //    40: aastore        
        //    41: dup            
        //    42: iconst_1       
        //    43: ldc             "hit_time"
        //    45: aastore        
        //    46: dup            
        //    47: iconst_2       
        //    48: ldc             "hit_first_send_time"
        //    50: aastore        
        //    51: aconst_null    
        //    52: aconst_null    
        //    53: aconst_null    
        //    54: aconst_null    
        //    55: ldc_w           "%s ASC"
        //    58: iconst_1       
        //    59: anewarray       Ljava/lang/Object;
        //    62: dup            
        //    63: iconst_0       
        //    64: ldc             "hit_id"
        //    66: aastore        
        //    67: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    70: iload_1        
        //    71: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //    74: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    77: astore          10
        //    79: aload           10
        //    81: astore          11
        //    83: new             Ljava/util/ArrayList;
        //    86: dup            
        //    87: invokespecial   java/util/ArrayList.<init>:()V
        //    90: astore          12
        //    92: aload           11
        //    94: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    99: ifeq            155
        //   102: aload           12
        //   104: new             Lcom/google/android/gms/tagmanager/zzaq;
        //   107: dup            
        //   108: aload           11
        //   110: iconst_0       
        //   111: invokeinterface android/database/Cursor.getLong:(I)J
        //   116: aload           11
        //   118: iconst_1       
        //   119: invokeinterface android/database/Cursor.getLong:(I)J
        //   124: aload           11
        //   126: iconst_2       
        //   127: invokeinterface android/database/Cursor.getLong:(I)J
        //   132: invokespecial   com/google/android/gms/tagmanager/zzaq.<init>:(JJJ)V
        //   135: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   140: pop            
        //   141: aload           11
        //   143: invokeinterface android/database/Cursor.moveToNext:()Z
        //   148: istore          15
        //   150: iload           15
        //   152: ifne            102
        //   155: aload           11
        //   157: ifnull          167
        //   160: aload           11
        //   162: invokeinterface android/database/Cursor.close:()V
        //   167: aload_3        
        //   168: ldc             "gtm_hits"
        //   170: iconst_2       
        //   171: anewarray       Ljava/lang/String;
        //   174: dup            
        //   175: iconst_0       
        //   176: ldc             "hit_id"
        //   178: aastore        
        //   179: dup            
        //   180: iconst_1       
        //   181: ldc             "hit_url"
        //   183: aastore        
        //   184: aconst_null    
        //   185: aconst_null    
        //   186: aconst_null    
        //   187: aconst_null    
        //   188: ldc_w           "%s ASC"
        //   191: iconst_1       
        //   192: anewarray       Ljava/lang/Object;
        //   195: dup            
        //   196: iconst_0       
        //   197: ldc             "hit_id"
        //   199: aastore        
        //   200: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   203: iload_1        
        //   204: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   207: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   210: astore          24
        //   212: aload           24
        //   214: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   219: ifeq            282
        //   222: iconst_0       
        //   223: istore          25
        //   225: aload           24
        //   227: checkcast       Landroid/database/sqlite/SQLiteCursor;
        //   230: invokevirtual   android/database/sqlite/SQLiteCursor.getWindow:()Landroid/database/CursorWindow;
        //   233: invokevirtual   android/database/CursorWindow.getNumRows:()I
        //   236: ifle            368
        //   239: aload           12
        //   241: iload           25
        //   243: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   248: checkcast       Lcom/google/android/gms/tagmanager/zzaq;
        //   251: aload           24
        //   253: iconst_1       
        //   254: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   259: invokevirtual   com/google/android/gms/tagmanager/zzaq.zzgf:(Ljava/lang/String;)V
        //   262: iload           25
        //   264: iconst_1       
        //   265: iadd           
        //   266: istore          27
        //   268: aload           24
        //   270: invokeinterface android/database/Cursor.moveToNext:()Z
        //   275: istore          28
        //   277: iload           28
        //   279: ifne            619
        //   282: aload           24
        //   284: ifnull          294
        //   287: aload           24
        //   289: invokeinterface android/database/Cursor.close:()V
        //   294: aload           12
        //   296: areturn        
        //   297: astore          6
        //   299: aload           6
        //   301: astore          7
        //   303: aconst_null    
        //   304: astore          8
        //   306: aload_2        
        //   307: astore          9
        //   309: new             Ljava/lang/StringBuilder;
        //   312: dup            
        //   313: invokespecial   java/lang/StringBuilder.<init>:()V
        //   316: ldc_w           "Error in peekHits fetching hitIds: "
        //   319: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: aload           7
        //   324: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   333: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   336: aload           8
        //   338: ifnull          23
        //   341: aload           8
        //   343: invokeinterface android/database/Cursor.close:()V
        //   348: aload           9
        //   350: areturn        
        //   351: astore          5
        //   353: aload           4
        //   355: ifnull          365
        //   358: aload           4
        //   360: invokeinterface android/database/Cursor.close:()V
        //   365: aload           5
        //   367: athrow         
        //   368: iconst_1       
        //   369: anewarray       Ljava/lang/Object;
        //   372: astore          26
        //   374: aload           26
        //   376: iconst_0       
        //   377: aload           12
        //   379: iload           25
        //   381: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   386: checkcast       Lcom/google/android/gms/tagmanager/zzaq;
        //   389: invokevirtual   com/google/android/gms/tagmanager/zzaq.zzGD:()J
        //   392: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   395: aastore        
        //   396: ldc_w           "HitString for hitId %d too large.  Hit will be deleted."
        //   399: aload           26
        //   401: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   404: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   407: goto            262
        //   410: astore          16
        //   412: aload           24
        //   414: astore          11
        //   416: new             Ljava/lang/StringBuilder;
        //   419: dup            
        //   420: invokespecial   java/lang/StringBuilder.<init>:()V
        //   423: ldc_w           "Error in peekHits fetching hit url: "
        //   426: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   429: aload           16
        //   431: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   434: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   437: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   440: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   443: new             Ljava/util/ArrayList;
        //   446: dup            
        //   447: invokespecial   java/util/ArrayList.<init>:()V
        //   450: astore          18
        //   452: iconst_0       
        //   453: istore          19
        //   455: aload           12
        //   457: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   462: astore          20
        //   464: aload           20
        //   466: invokeinterface java/util/Iterator.hasNext:()Z
        //   471: ifeq            506
        //   474: aload           20
        //   476: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   481: checkcast       Lcom/google/android/gms/tagmanager/zzaq;
        //   484: astore          21
        //   486: aload           21
        //   488: invokevirtual   com/google/android/gms/tagmanager/zzaq.zzGF:()Ljava/lang/String;
        //   491: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   494: istore          22
        //   496: iload           22
        //   498: ifeq            524
        //   501: iload           19
        //   503: ifeq            521
        //   506: aload           11
        //   508: ifnull          518
        //   511: aload           11
        //   513: invokeinterface android/database/Cursor.close:()V
        //   518: aload           18
        //   520: areturn        
        //   521: iconst_1       
        //   522: istore          19
        //   524: aload           18
        //   526: aload           21
        //   528: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   533: pop            
        //   534: goto            464
        //   537: astore          17
        //   539: aload           11
        //   541: ifnull          551
        //   544: aload           11
        //   546: invokeinterface android/database/Cursor.close:()V
        //   551: aload           17
        //   553: athrow         
        //   554: astore          17
        //   556: aload           24
        //   558: astore          11
        //   560: goto            539
        //   563: astore          16
        //   565: goto            416
        //   568: astore          5
        //   570: aload           11
        //   572: astore          4
        //   574: goto            353
        //   577: astore          5
        //   579: aload           8
        //   581: astore          4
        //   583: goto            353
        //   586: astore          29
        //   588: aload           29
        //   590: astore          7
        //   592: aload           11
        //   594: astore          8
        //   596: aload_2        
        //   597: astore          9
        //   599: goto            309
        //   602: astore          13
        //   604: aload           13
        //   606: astore          7
        //   608: aload           11
        //   610: astore          8
        //   612: aload           12
        //   614: astore          9
        //   616: goto            309
        //   619: iload           27
        //   621: istore          25
        //   623: goto            225
        //    Signature:
        //  (I)Ljava/util/List<Lcom/google/android/gms/tagmanager/zzaq;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  29     79     297    309    Landroid/database/sqlite/SQLiteException;
        //  29     79     351    353    Any
        //  83     92     586    602    Landroid/database/sqlite/SQLiteException;
        //  83     92     568    577    Any
        //  92     102    602    619    Landroid/database/sqlite/SQLiteException;
        //  92     102    568    577    Any
        //  102    150    602    619    Landroid/database/sqlite/SQLiteException;
        //  102    150    568    577    Any
        //  167    212    563    568    Landroid/database/sqlite/SQLiteException;
        //  167    212    537    539    Any
        //  212    222    410    416    Landroid/database/sqlite/SQLiteException;
        //  212    222    554    563    Any
        //  225    262    410    416    Landroid/database/sqlite/SQLiteException;
        //  225    262    554    563    Any
        //  268    277    410    416    Landroid/database/sqlite/SQLiteException;
        //  268    277    554    563    Any
        //  309    336    577    586    Any
        //  368    407    410    416    Landroid/database/sqlite/SQLiteException;
        //  368    407    554    563    Any
        //  416    452    537    539    Any
        //  455    464    537    539    Any
        //  464    496    537    539    Any
        //  524    534    537    539    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    class zza implements zzcx.zza
    {
        @Override
        public void zza(final zzaq zzaq) {
            zzby.this.zzq(zzaq.zzGD());
        }
        
        @Override
        public void zzb(final zzaq zzaq) {
            zzby.this.zzq(zzaq.zzGD());
            zzbg.v("Permanent failure dispatching hitId: " + zzaq.zzGD());
        }
        
        @Override
        public void zzc(final zzaq zzaq) {
            final long zzGE = zzaq.zzGE();
            if (zzGE == 0L) {
                zzby.this.zzd(zzaq.zzGD(), zzby.this.zzqW.currentTimeMillis());
            }
            else if (zzGE + 14400000L < zzby.this.zzqW.currentTimeMillis()) {
                zzby.this.zzq(zzaq.zzGD());
                zzbg.v("Giving up on failed hitId: " + zzaq.zzGD());
            }
        }
    }
    
    class zzb extends SQLiteOpenHelper
    {
        private boolean zzbjL;
        private long zzbjM;
        
        zzb(final Context context, final String s) {
            super(context, s, (SQLiteDatabase$CursorFactory)null, 1);
            this.zzbjM = 0L;
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
            final Cursor rawQuery = sqLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", (String[])null);
            final HashSet<String> set = new HashSet<String>();
            try {
                final String[] columnNames = rawQuery.getColumnNames();
                for (int i = 0; i < columnNames.length; ++i) {
                    set.add(columnNames[i]);
                }
                rawQuery.close();
                if (!set.remove("hit_id") || !set.remove("hit_url") || !set.remove("hit_time") || !set.remove("hit_first_send_time")) {
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
            if (this.zzbjL && 3600000L + this.zzbjM > zzby.this.zzqW.currentTimeMillis()) {
                throw new SQLiteException("Database creation failed");
            }
            this.zzbjL = true;
            this.zzbjM = zzby.this.zzqW.currentTimeMillis();
            while (true) {
                try {
                    SQLiteDatabase sqLiteDatabase = super.getWritableDatabase();
                    if (sqLiteDatabase == null) {
                        sqLiteDatabase = super.getWritableDatabase();
                    }
                    this.zzbjL = false;
                    return sqLiteDatabase;
                }
                catch (SQLiteException ex) {
                    zzby.this.mContext.getDatabasePath(zzby.this.zzbjH).delete();
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
                        if (!this.zza("gtm_hits", sqLiteDatabase)) {
                            sqLiteDatabase.execSQL(zzby.zzQR);
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
}
