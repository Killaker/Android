package com.google.android.gms.measurement.internal;

import android.support.v4.util.*;
import android.os.*;
import android.database.*;
import android.annotation.*;
import android.support.annotation.*;
import com.google.android.gms.common.internal.*;
import android.text.*;
import com.google.android.gms.internal.*;
import android.util.*;
import android.content.*;
import android.database.sqlite.*;
import java.util.*;
import java.io.*;

class zze extends zzz
{
    private static final Map<String, String> zzaVB;
    private final zzc zzaVC;
    private final zzaf zzaVD;
    
    static {
        (zzaVB = new ArrayMap<String, String>(13)).put("app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;");
        zze.zzaVB.put("app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;");
        zze.zzaVB.put("gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;");
        zze.zzaVB.put("dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;");
        zze.zzaVB.put("measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;");
        zze.zzaVB.put("last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;");
        zze.zzaVB.put("day", "ALTER TABLE apps ADD COLUMN day INTEGER;");
        zze.zzaVB.put("daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;");
        zze.zzaVB.put("daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;");
        zze.zzaVB.put("daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;");
        zze.zzaVB.put("remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;");
        zze.zzaVB.put("config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;");
        zze.zzaVB.put("failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;");
    }
    
    zze(final zzw zzw) {
        super(zzw);
        this.zzaVD = new zzaf(this.zzjl());
        this.zzaVC = new zzc(this.getContext(), this.zzjQ());
    }
    
    private boolean zzCw() {
        return this.getContext().getDatabasePath(this.zzjQ()).exists();
    }
    
    @TargetApi(11)
    @WorkerThread
    static int zza(final Cursor cursor, final int n) {
        if (Build$VERSION.SDK_INT >= 11) {
            return cursor.getType(n);
        }
        final CursorWindow window = ((SQLiteCursor)cursor).getWindow();
        final int position = cursor.getPosition();
        if (window.isNull(position, n)) {
            return 0;
        }
        if (window.isLong(position, n)) {
            return 1;
        }
        if (window.isFloat(position, n)) {
            return 2;
        }
        if (window.isString(position, n)) {
            return 3;
        }
        if (window.isBlob(position, n)) {
            return 4;
        }
        return -1;
    }
    
    @WorkerThread
    private long zza(final String s, final String[] array, long long1) {
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor rawQuery = null;
        try {
            rawQuery = writableDatabase.rawQuery(s, array);
            if (rawQuery.moveToFirst()) {
                long1 = rawQuery.getLong(0);
                return long1;
            }
            return long1;
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zze("Database error", s, ex);
            throw ex;
        }
        finally {
            if (rawQuery != null) {
                rawQuery.close();
            }
        }
    }
    
    @WorkerThread
    private void zza(final String s, final zzpz.zza zza) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        zzx.zzz(zza);
        zzx.zzz(zza.zzaZt);
        zzx.zzz(zza.zzaZs);
        if (zza.zzaZr != null) {
            final int intValue = zza.zzaZr;
            final zzpz.zzb[] zzaZt = zza.zzaZt;
            for (int length = zzaZt.length, i = 0; i < length; ++i) {
                if (zzaZt[i].zzaZv == null) {
                    this.zzAo().zzCF().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", s, zza.zzaZr);
                    return;
                }
            }
            final zzpz.zze[] zzaZs = zza.zzaZs;
            for (int length2 = zzaZs.length, j = 0; j < length2; ++j) {
                if (zzaZs[j].zzaZv == null) {
                    this.zzAo().zzCF().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", s, zza.zzaZr);
                    return;
                }
            }
            boolean b = true;
            final zzpz.zzb[] zzaZt2 = zza.zzaZt;
            for (int length3 = zzaZt2.length, k = 0; k < length3; ++k) {
                if (!this.zza(s, intValue, zzaZt2[k])) {
                    b = false;
                    break;
                }
            }
        Label_0267:
            while (true) {
                if (b) {
                    final zzpz.zze[] zzaZs2 = zza.zzaZs;
                    for (int length4 = zzaZs2.length, l = 0; l < length4; ++l) {
                        final boolean zza2 = this.zza(s, intValue, zzaZs2[l]);
                        final boolean b2 = false;
                        if (!zza2) {
                            break Label_0267;
                        }
                    }
                }
                Label_0292: {
                    break Label_0292;
                    final boolean b2;
                    if (!b2) {
                        this.zzB(s, intValue);
                        return;
                    }
                    return;
                }
                final boolean b2 = b;
                continue Label_0267;
            }
        }
        this.zzAo().zzCF().zzfg("Audience with no ID");
    }
    
    @WorkerThread
    private boolean zza(final String p0, final int p1, final zzpz.zzb p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    12: pop            
        //    13: aload_3        
        //    14: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //    17: pop            
        //    18: aload_3        
        //    19: getfield        com/google/android/gms/internal/zzpz$zzb.zzaZw:Ljava/lang/String;
        //    22: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    25: ifeq            54
        //    28: aload_0        
        //    29: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //    32: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //    35: ldc_w           "Event filter had no event name. Audience definition ignored. audienceId, filterId"
        //    38: iload_2        
        //    39: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    42: aload_3        
        //    43: getfield        com/google/android/gms/internal/zzpz$zzb.zzaZv:Ljava/lang/Integer;
        //    46: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    49: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    52: iconst_0       
        //    53: ireturn        
        //    54: aload_3        
        //    55: invokevirtual   com/google/android/gms/internal/zzpz$zzb.getSerializedSize:()I
        //    58: newarray        B
        //    60: astore          7
        //    62: aload           7
        //    64: invokestatic    com/google/android/gms/internal/zzsn.zzE:([B)Lcom/google/android/gms/internal/zzsn;
        //    67: astore          8
        //    69: aload_3        
        //    70: aload           8
        //    72: invokevirtual   com/google/android/gms/internal/zzpz$zzb.writeTo:(Lcom/google/android/gms/internal/zzsn;)V
        //    75: aload           8
        //    77: invokevirtual   com/google/android/gms/internal/zzsn.zzJo:()V
        //    80: new             Landroid/content/ContentValues;
        //    83: dup            
        //    84: invokespecial   android/content/ContentValues.<init>:()V
        //    87: astore          9
        //    89: aload           9
        //    91: ldc_w           "app_id"
        //    94: aload_1        
        //    95: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //    98: aload           9
        //   100: ldc_w           "audience_id"
        //   103: iload_2        
        //   104: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   107: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   110: aload           9
        //   112: ldc_w           "filter_id"
        //   115: aload_3        
        //   116: getfield        com/google/android/gms/internal/zzpz$zzb.zzaZv:Ljava/lang/Integer;
        //   119: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   122: aload           9
        //   124: ldc_w           "event_name"
        //   127: aload_3        
        //   128: getfield        com/google/android/gms/internal/zzpz$zzb.zzaZw:Ljava/lang/String;
        //   131: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   134: aload           9
        //   136: ldc_w           "data"
        //   139: aload           7
        //   141: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;[B)V
        //   144: aload_0        
        //   145: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //   148: ldc_w           "event_filters"
        //   151: aconst_null    
        //   152: aload           9
        //   154: iconst_5       
        //   155: invokevirtual   android/database/sqlite/SQLiteDatabase.insertWithOnConflict:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
        //   158: ldc2_w          -1
        //   161: lcmp           
        //   162: ifne            178
        //   165: aload_0        
        //   166: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   169: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   172: ldc_w           "Failed to insert event filter (got -1)"
        //   175: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   178: iconst_1       
        //   179: ireturn        
        //   180: astore          6
        //   182: aload_0        
        //   183: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   186: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   189: ldc_w           "Configuration loss. Failed to serialize event filter"
        //   192: aload           6
        //   194: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   197: iconst_0       
        //   198: ireturn        
        //   199: astore          10
        //   201: aload_0        
        //   202: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   205: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   208: ldc_w           "Error storing event filter"
        //   211: aload           10
        //   213: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   216: iconst_0       
        //   217: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  54     80     180    199    Ljava/io/IOException;
        //  144    178    199    218    Landroid/database/sqlite/SQLiteException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    private boolean zza(final String s, final int n, final zzpz.zze zze) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        zzx.zzz(zze);
        if (TextUtils.isEmpty((CharSequence)zze.zzaZL)) {
            this.zzAo().zzCF().zze("Property filter had no property name. Audience definition ignored. audienceId, filterId", n, String.valueOf(zze.zzaZv));
            return false;
        }
        try {
            final byte[] array = new byte[zze.getSerializedSize()];
            final zzsn zzE = zzsn.zzE(array);
            zze.writeTo(zzE);
            zzE.zzJo();
            final ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", s);
            contentValues.put("audience_id", n);
            contentValues.put("filter_id", zze.zzaZv);
            contentValues.put("property_name", zze.zzaZL);
            contentValues.put("data", array);
            try {
                if (this.getWritableDatabase().insertWithOnConflict("property_filters", (String)null, contentValues, 5) == -1L) {
                    this.zzAo().zzCE().zzfg("Failed to insert property filter (got -1)");
                    return false;
                }
                return true;
            }
            catch (SQLiteException ex) {
                this.zzAo().zzCE().zzj("Error storing property filter", ex);
                return false;
            }
        }
        catch (IOException ex2) {
            this.zzAo().zzCE().zzj("Configuration loss. Failed to serialize property filter", ex2);
            return false;
        }
        return true;
    }
    
    @WorkerThread
    private long zzb(final String s, final String[] array) {
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor rawQuery = null;
        try {
            rawQuery = writableDatabase.rawQuery(s, array);
            if (rawQuery.moveToFirst()) {
                return rawQuery.getLong(0);
            }
            throw new SQLiteException("Database returned empty set");
        }
        catch (SQLiteException ex) {
            try {
                this.zzAo().zzCE().zze("Database error", s, ex);
                throw ex;
            }
            finally {
                if (rawQuery != null) {
                    rawQuery.close();
                }
            }
        }
    }
    
    private String zzjQ() {
        if (!this.zzCp().zzkr()) {
            return this.zzCp().zzkR();
        }
        if (this.zzCp().zzks()) {
            return this.zzCp().zzkR();
        }
        this.zzAo().zzCG().zzfg("Using secondary database");
        return this.zzCp().zzkS();
    }
    
    @WorkerThread
    public void beginTransaction() {
        this.zzjv();
        this.getWritableDatabase().beginTransaction();
    }
    
    @WorkerThread
    public void endTransaction() {
        this.zzjv();
        this.getWritableDatabase().endTransaction();
    }
    
    @WorkerThread
    SQLiteDatabase getWritableDatabase() {
        this.zzjk();
        try {
            return this.zzaVC.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCF().zzj("Error opening database", ex);
            throw ex;
        }
    }
    
    @WorkerThread
    public void setTransactionSuccessful() {
        this.zzjv();
        this.getWritableDatabase().setTransactionSuccessful();
    }
    
    @WorkerThread
    public void zzA(final String s, final int n) {
        zzx.zzcM(s);
        this.zzjk();
        this.zzjv();
        try {
            this.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", (Object[])new String[] { s, s, String.valueOf(n) });
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zze("Error pruning currencies", s, ex);
        }
    }
    
    void zzB(final String s, final int n) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=? and audience_id=?", new String[] { s, String.valueOf(n) });
        writableDatabase.delete("event_filters", "app_id=? and audience_id=?", new String[] { s, String.valueOf(n) });
    }
    
    zzqb.zzf zzC(final String p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    12: pop            
        //    13: aload_0        
        //    14: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    17: astore          4
        //    19: iconst_1       
        //    20: anewarray       Ljava/lang/String;
        //    23: dup            
        //    24: iconst_0       
        //    25: ldc_w           "current_results"
        //    28: aastore        
        //    29: astore          5
        //    31: iconst_2       
        //    32: anewarray       Ljava/lang/String;
        //    35: astore          9
        //    37: aload           9
        //    39: iconst_0       
        //    40: aload_1        
        //    41: aastore        
        //    42: aload           9
        //    44: iconst_1       
        //    45: iload_2        
        //    46: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //    49: aastore        
        //    50: aload           4
        //    52: ldc_w           "audience_filter_values"
        //    55: aload           5
        //    57: ldc_w           "app_id=? AND audience_id=?"
        //    60: aload           9
        //    62: aconst_null    
        //    63: aconst_null    
        //    64: aconst_null    
        //    65: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    68: astore          10
        //    70: aload           10
        //    72: astore          7
        //    74: aload           7
        //    76: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    81: istore          11
        //    83: iload           11
        //    85: ifne            106
        //    88: aload           7
        //    90: ifnull          100
        //    93: aload           7
        //    95: invokeinterface android/database/Cursor.close:()V
        //   100: aconst_null    
        //   101: astore          13
        //   103: aload           13
        //   105: areturn        
        //   106: aload           7
        //   108: iconst_0       
        //   109: invokeinterface android/database/Cursor.getBlob:(I)[B
        //   114: invokestatic    com/google/android/gms/internal/zzsm.zzD:([B)Lcom/google/android/gms/internal/zzsm;
        //   117: astore          12
        //   119: new             Lcom/google/android/gms/internal/zzqb$zzf;
        //   122: dup            
        //   123: invokespecial   com/google/android/gms/internal/zzqb$zzf.<init>:()V
        //   126: astore          13
        //   128: aload           13
        //   130: aload           12
        //   132: invokevirtual   com/google/android/gms/internal/zzqb$zzf.zzH:(Lcom/google/android/gms/internal/zzsm;)Lcom/google/android/gms/internal/zzqb$zzf;
        //   135: pop            
        //   136: aload           7
        //   138: ifnull          103
        //   141: aload           7
        //   143: invokeinterface android/database/Cursor.close:()V
        //   148: aload           13
        //   150: areturn        
        //   151: astore          14
        //   153: aload_0        
        //   154: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   157: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   160: ldc_w           "Failed to merge filter results"
        //   163: aload_1        
        //   164: aload           14
        //   166: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   169: goto            136
        //   172: astore          6
        //   174: aload_0        
        //   175: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   178: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   181: ldc_w           "Database error querying filter results"
        //   184: aload           6
        //   186: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   189: aload           7
        //   191: ifnull          201
        //   194: aload           7
        //   196: invokeinterface android/database/Cursor.close:()V
        //   201: aconst_null    
        //   202: areturn        
        //   203: astore          8
        //   205: aconst_null    
        //   206: astore          7
        //   208: aload           7
        //   210: ifnull          220
        //   213: aload           7
        //   215: invokeinterface android/database/Cursor.close:()V
        //   220: aload           8
        //   222: athrow         
        //   223: astore          8
        //   225: goto            208
        //   228: astore          6
        //   230: aconst_null    
        //   231: astore          7
        //   233: goto            174
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  19     70     228    236    Landroid/database/sqlite/SQLiteException;
        //  19     70     203    208    Any
        //  74     83     172    174    Landroid/database/sqlite/SQLiteException;
        //  74     83     223    228    Any
        //  106    128    172    174    Landroid/database/sqlite/SQLiteException;
        //  106    128    223    228    Any
        //  128    136    151    172    Ljava/io/IOException;
        //  128    136    172    174    Landroid/database/sqlite/SQLiteException;
        //  128    136    223    228    Any
        //  153    169    172    174    Landroid/database/sqlite/SQLiteException;
        //  153    169    223    228    Any
        //  174    189    223    228    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    public String zzCq() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //     4: astore_1       
        //     5: aload_1        
        //     6: ldc_w           "select app_id from queue where app_id not in (select app_id from apps where measurement_enabled=0) order by rowid limit 1;"
        //     9: aconst_null    
        //    10: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //    13: astore          7
        //    15: aload           7
        //    17: astore_3       
        //    18: aload_3        
        //    19: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    24: ifeq            53
        //    27: aload_3        
        //    28: iconst_0       
        //    29: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    34: astore          8
        //    36: aload           8
        //    38: astore          6
        //    40: aload_3        
        //    41: ifnull          50
        //    44: aload_3        
        //    45: invokeinterface android/database/Cursor.close:()V
        //    50: aload           6
        //    52: areturn        
        //    53: aconst_null    
        //    54: astore          6
        //    56: aload_3        
        //    57: ifnull          50
        //    60: aload_3        
        //    61: invokeinterface android/database/Cursor.close:()V
        //    66: aconst_null    
        //    67: areturn        
        //    68: astore          5
        //    70: aconst_null    
        //    71: astore_3       
        //    72: aload_0        
        //    73: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //    76: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //    79: ldc_w           "Database error getting next bundle app id"
        //    82: aload           5
        //    84: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //    87: aconst_null    
        //    88: astore          6
        //    90: aload_3        
        //    91: ifnull          50
        //    94: aload_3        
        //    95: invokeinterface android/database/Cursor.close:()V
        //   100: aconst_null    
        //   101: areturn        
        //   102: astore_2       
        //   103: aconst_null    
        //   104: astore_3       
        //   105: aload_2        
        //   106: astore          4
        //   108: aload_3        
        //   109: ifnull          118
        //   112: aload_3        
        //   113: invokeinterface android/database/Cursor.close:()V
        //   118: aload           4
        //   120: athrow         
        //   121: astore          4
        //   123: goto            108
        //   126: astore          5
        //   128: goto            72
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  5      15     68     72     Landroid/database/sqlite/SQLiteException;
        //  5      15     102    108    Any
        //  18     36     126    131    Landroid/database/sqlite/SQLiteException;
        //  18     36     121    126    Any
        //  72     87     121    126    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    void zzCr() {
        this.zzjk();
        this.zzjv();
        if (this.zzCw()) {
            final long value = this.zzCo().zzaXm.get();
            final long elapsedRealtime = this.zzjl().elapsedRealtime();
            if (Math.abs(elapsedRealtime - value) > this.zzCp().zzBR()) {
                this.zzCo().zzaXm.set(elapsedRealtime);
                this.zzCs();
            }
        }
    }
    
    @WorkerThread
    void zzCs() {
        this.zzjk();
        this.zzjv();
        if (this.zzCw()) {
            final int delete = this.getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[] { String.valueOf(this.zzjl().currentTimeMillis()), String.valueOf(this.zzCp().zzBQ()) });
            if (delete > 0) {
                this.zzAo().zzCK().zzj("Deleted stale rows. rowsDeleted", delete);
            }
        }
    }
    
    @WorkerThread
    public long zzCt() {
        return this.zza("select max(bundle_end_timestamp) from queue", null, 0L);
    }
    
    @WorkerThread
    public long zzCu() {
        return this.zza("select max(timestamp) from raw_events", null, 0L);
    }
    
    public boolean zzCv() {
        return this.zzb("select count(1) > 0 from raw_events", (String[])null) != 0L;
    }
    
    @WorkerThread
    public zzi zzI(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_1        
        //     3: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //     6: pop            
        //     7: aload_2        
        //     8: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    11: pop            
        //    12: aload_0        
        //    13: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //    16: aload_0        
        //    17: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    20: aload_0        
        //    21: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    24: ldc_w           "events"
        //    27: iconst_3       
        //    28: anewarray       Ljava/lang/String;
        //    31: dup            
        //    32: iconst_0       
        //    33: ldc_w           "lifetime_count"
        //    36: aastore        
        //    37: dup            
        //    38: iconst_1       
        //    39: ldc_w           "current_bundle_count"
        //    42: aastore        
        //    43: dup            
        //    44: iconst_2       
        //    45: ldc_w           "last_fire_timestamp"
        //    48: aastore        
        //    49: ldc_w           "app_id=? and name=?"
        //    52: iconst_2       
        //    53: anewarray       Ljava/lang/String;
        //    56: dup            
        //    57: iconst_0       
        //    58: aload_1        
        //    59: aastore        
        //    60: dup            
        //    61: iconst_1       
        //    62: aload_2        
        //    63: aastore        
        //    64: aconst_null    
        //    65: aconst_null    
        //    66: aconst_null    
        //    67: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    70: astore          9
        //    72: aload           9
        //    74: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    79: istore          10
        //    81: iload           10
        //    83: ifne            104
        //    86: aload           9
        //    88: ifnull          98
        //    91: aload           9
        //    93: invokeinterface android/database/Cursor.close:()V
        //    98: aconst_null    
        //    99: astore          11
        //   101: aload           11
        //   103: areturn        
        //   104: new             Lcom/google/android/gms/measurement/internal/zzi;
        //   107: dup            
        //   108: aload_1        
        //   109: aload_2        
        //   110: aload           9
        //   112: iconst_0       
        //   113: invokeinterface android/database/Cursor.getLong:(I)J
        //   118: aload           9
        //   120: iconst_1       
        //   121: invokeinterface android/database/Cursor.getLong:(I)J
        //   126: aload           9
        //   128: iconst_2       
        //   129: invokeinterface android/database/Cursor.getLong:(I)J
        //   134: invokespecial   com/google/android/gms/measurement/internal/zzi.<init>:(Ljava/lang/String;Ljava/lang/String;JJJ)V
        //   137: astore          11
        //   139: aload           9
        //   141: invokeinterface android/database/Cursor.moveToNext:()Z
        //   146: ifeq            162
        //   149: aload_0        
        //   150: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   153: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   156: ldc_w           "Got multiple records for event aggregates, expected one"
        //   159: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   162: aload           9
        //   164: ifnull          101
        //   167: aload           9
        //   169: invokeinterface android/database/Cursor.close:()V
        //   174: aload           11
        //   176: areturn        
        //   177: astore          7
        //   179: aconst_null    
        //   180: astore          8
        //   182: aload_0        
        //   183: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   186: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   189: ldc_w           "Error querying events"
        //   192: aload_1        
        //   193: aload_2        
        //   194: aload           7
        //   196: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzd:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
        //   199: aload           8
        //   201: ifnull          211
        //   204: aload           8
        //   206: invokeinterface android/database/Cursor.close:()V
        //   211: aconst_null    
        //   212: areturn        
        //   213: astore          6
        //   215: aload_3        
        //   216: ifnull          225
        //   219: aload_3        
        //   220: invokeinterface android/database/Cursor.close:()V
        //   225: aload           6
        //   227: athrow         
        //   228: astore          6
        //   230: aload           9
        //   232: astore_3       
        //   233: goto            215
        //   236: astore          6
        //   238: aload           8
        //   240: astore_3       
        //   241: goto            215
        //   244: astore          7
        //   246: aload           9
        //   248: astore          8
        //   250: goto            182
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  20     72     177    182    Landroid/database/sqlite/SQLiteException;
        //  20     72     213    215    Any
        //  72     81     244    253    Landroid/database/sqlite/SQLiteException;
        //  72     81     228    236    Any
        //  104    162    244    253    Landroid/database/sqlite/SQLiteException;
        //  104    162    228    236    Any
        //  182    199    236    244    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    public void zzJ(final String s, final String s2) {
        zzx.zzcM(s);
        zzx.zzcM(s2);
        this.zzjk();
        this.zzjv();
        try {
            this.zzAo().zzCK().zzj("Deleted user attribute rows:", this.getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[] { s, s2 }));
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zzd("Error deleting user attribute", s, s2, ex);
        }
    }
    
    @WorkerThread
    public zzai zzK(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_1        
        //     3: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //     6: pop            
        //     7: aload_2        
        //     8: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    11: pop            
        //    12: aload_0        
        //    13: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //    16: aload_0        
        //    17: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    20: aload_0        
        //    21: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    24: ldc_w           "user_attributes"
        //    27: iconst_2       
        //    28: anewarray       Ljava/lang/String;
        //    31: dup            
        //    32: iconst_0       
        //    33: ldc_w           "set_timestamp"
        //    36: aastore        
        //    37: dup            
        //    38: iconst_1       
        //    39: ldc_w           "value"
        //    42: aastore        
        //    43: ldc_w           "app_id=? and name=?"
        //    46: iconst_2       
        //    47: anewarray       Ljava/lang/String;
        //    50: dup            
        //    51: iconst_0       
        //    52: aload_1        
        //    53: aastore        
        //    54: dup            
        //    55: iconst_1       
        //    56: aload_2        
        //    57: aastore        
        //    58: aconst_null    
        //    59: aconst_null    
        //    60: aconst_null    
        //    61: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    64: astore          9
        //    66: aload           9
        //    68: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    73: istore          10
        //    75: iload           10
        //    77: ifne            98
        //    80: aload           9
        //    82: ifnull          92
        //    85: aload           9
        //    87: invokeinterface android/database/Cursor.close:()V
        //    92: aconst_null    
        //    93: astore          11
        //    95: aload           11
        //    97: areturn        
        //    98: new             Lcom/google/android/gms/measurement/internal/zzai;
        //   101: dup            
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload           9
        //   106: iconst_0       
        //   107: invokeinterface android/database/Cursor.getLong:(I)J
        //   112: aload_0        
        //   113: aload           9
        //   115: iconst_1       
        //   116: invokevirtual   com/google/android/gms/measurement/internal/zze.zzb:(Landroid/database/Cursor;I)Ljava/lang/Object;
        //   119: invokespecial   com/google/android/gms/measurement/internal/zzai.<init>:(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
        //   122: astore          11
        //   124: aload           9
        //   126: invokeinterface android/database/Cursor.moveToNext:()Z
        //   131: ifeq            147
        //   134: aload_0        
        //   135: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   138: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   141: ldc_w           "Got multiple records for user property, expected one"
        //   144: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   147: aload           9
        //   149: ifnull          95
        //   152: aload           9
        //   154: invokeinterface android/database/Cursor.close:()V
        //   159: aload           11
        //   161: areturn        
        //   162: astore          7
        //   164: aconst_null    
        //   165: astore          8
        //   167: aload_0        
        //   168: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   171: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   174: ldc_w           "Error querying user property"
        //   177: aload_1        
        //   178: aload_2        
        //   179: aload           7
        //   181: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzd:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
        //   184: aload           8
        //   186: ifnull          196
        //   189: aload           8
        //   191: invokeinterface android/database/Cursor.close:()V
        //   196: aconst_null    
        //   197: areturn        
        //   198: astore          6
        //   200: aload_3        
        //   201: ifnull          210
        //   204: aload_3        
        //   205: invokeinterface android/database/Cursor.close:()V
        //   210: aload           6
        //   212: athrow         
        //   213: astore          6
        //   215: aload           9
        //   217: astore_3       
        //   218: goto            200
        //   221: astore          6
        //   223: aload           8
        //   225: astore_3       
        //   226: goto            200
        //   229: astore          7
        //   231: aload           9
        //   233: astore          8
        //   235: goto            167
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  20     66     162    167    Landroid/database/sqlite/SQLiteException;
        //  20     66     198    200    Any
        //  66     75     229    238    Landroid/database/sqlite/SQLiteException;
        //  66     75     213    221    Any
        //  98     147    229    238    Landroid/database/sqlite/SQLiteException;
        //  98     147    213    221    Any
        //  167    184    221    229    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    Map<Integer, List<zzpz.zzb>> zzL(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    12: pop            
        //    13: aload_2        
        //    14: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    17: pop            
        //    18: new             Landroid/support/v4/util/ArrayMap;
        //    21: dup            
        //    22: invokespecial   android/support/v4/util/ArrayMap.<init>:()V
        //    25: astore          5
        //    27: aload_0        
        //    28: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    31: astore          6
        //    33: aload           6
        //    35: ldc_w           "event_filters"
        //    38: iconst_2       
        //    39: anewarray       Ljava/lang/String;
        //    42: dup            
        //    43: iconst_0       
        //    44: ldc_w           "audience_id"
        //    47: aastore        
        //    48: dup            
        //    49: iconst_1       
        //    50: ldc_w           "data"
        //    53: aastore        
        //    54: ldc_w           "app_id=? AND event_name=?"
        //    57: iconst_2       
        //    58: anewarray       Ljava/lang/String;
        //    61: dup            
        //    62: iconst_0       
        //    63: aload_1        
        //    64: aastore        
        //    65: dup            
        //    66: iconst_1       
        //    67: aload_2        
        //    68: aastore        
        //    69: aconst_null    
        //    70: aconst_null    
        //    71: aconst_null    
        //    72: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    75: astore          10
        //    77: aload           10
        //    79: astore          8
        //    81: aload           8
        //    83: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    88: ifne            111
        //    91: invokestatic    java/util/Collections.emptyMap:()Ljava/util/Map;
        //    94: astore          20
        //    96: aload           8
        //    98: ifnull          108
        //   101: aload           8
        //   103: invokeinterface android/database/Cursor.close:()V
        //   108: aload           20
        //   110: areturn        
        //   111: aload           8
        //   113: iconst_1       
        //   114: invokeinterface android/database/Cursor.getBlob:(I)[B
        //   119: invokestatic    com/google/android/gms/internal/zzsm.zzD:([B)Lcom/google/android/gms/internal/zzsm;
        //   122: astore          11
        //   124: new             Lcom/google/android/gms/internal/zzpz$zzb;
        //   127: dup            
        //   128: invokespecial   com/google/android/gms/internal/zzpz$zzb.<init>:()V
        //   131: astore          12
        //   133: aload           12
        //   135: aload           11
        //   137: invokevirtual   com/google/android/gms/internal/zzpz$zzb.zzu:(Lcom/google/android/gms/internal/zzsm;)Lcom/google/android/gms/internal/zzpz$zzb;
        //   140: pop            
        //   141: aload           8
        //   143: iconst_0       
        //   144: invokeinterface android/database/Cursor.getInt:(I)I
        //   149: istore          16
        //   151: aload           5
        //   153: iload           16
        //   155: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   158: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   163: checkcast       Ljava/util/List;
        //   166: astore          17
        //   168: aload           17
        //   170: ifnonnull       197
        //   173: new             Ljava/util/ArrayList;
        //   176: dup            
        //   177: invokespecial   java/util/ArrayList.<init>:()V
        //   180: astore          17
        //   182: aload           5
        //   184: iload           16
        //   186: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   189: aload           17
        //   191: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   196: pop            
        //   197: aload           17
        //   199: aload           12
        //   201: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   206: pop            
        //   207: aload           8
        //   209: invokeinterface android/database/Cursor.moveToNext:()Z
        //   214: istore          14
        //   216: iload           14
        //   218: ifne            111
        //   221: aload           8
        //   223: ifnull          233
        //   226: aload           8
        //   228: invokeinterface android/database/Cursor.close:()V
        //   233: aload           5
        //   235: areturn        
        //   236: astore          13
        //   238: aload_0        
        //   239: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   242: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   245: ldc_w           "Failed to merge filter"
        //   248: aload_1        
        //   249: aload           13
        //   251: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   254: goto            207
        //   257: astore          7
        //   259: aload_0        
        //   260: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   263: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   266: ldc_w           "Database error querying filters"
        //   269: aload           7
        //   271: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   274: aload           8
        //   276: ifnull          286
        //   279: aload           8
        //   281: invokeinterface android/database/Cursor.close:()V
        //   286: aconst_null    
        //   287: areturn        
        //   288: astore          9
        //   290: aconst_null    
        //   291: astore          8
        //   293: aload           8
        //   295: ifnull          305
        //   298: aload           8
        //   300: invokeinterface android/database/Cursor.close:()V
        //   305: aload           9
        //   307: athrow         
        //   308: astore          9
        //   310: goto            293
        //   313: astore          7
        //   315: aconst_null    
        //   316: astore          8
        //   318: goto            259
        //    Signature:
        //  (Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map<Ljava/lang/Integer;Ljava/util/List<Lcom/google/android/gms/internal/zzpz$zzb;>;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  33     77     313    321    Landroid/database/sqlite/SQLiteException;
        //  33     77     288    293    Any
        //  81     96     257    259    Landroid/database/sqlite/SQLiteException;
        //  81     96     308    313    Any
        //  111    133    257    259    Landroid/database/sqlite/SQLiteException;
        //  111    133    308    313    Any
        //  133    141    236    257    Ljava/io/IOException;
        //  133    141    257    259    Landroid/database/sqlite/SQLiteException;
        //  133    141    308    313    Any
        //  141    168    257    259    Landroid/database/sqlite/SQLiteException;
        //  141    168    308    313    Any
        //  173    197    257    259    Landroid/database/sqlite/SQLiteException;
        //  173    197    308    313    Any
        //  197    207    257    259    Landroid/database/sqlite/SQLiteException;
        //  197    207    308    313    Any
        //  207    216    257    259    Landroid/database/sqlite/SQLiteException;
        //  207    216    308    313    Any
        //  238    254    257    259    Landroid/database/sqlite/SQLiteException;
        //  238    254    308    313    Any
        //  259    274    308    313    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    Map<Integer, List<zzpz.zze>> zzM(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    12: pop            
        //    13: aload_2        
        //    14: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    17: pop            
        //    18: new             Landroid/support/v4/util/ArrayMap;
        //    21: dup            
        //    22: invokespecial   android/support/v4/util/ArrayMap.<init>:()V
        //    25: astore          5
        //    27: aload_0        
        //    28: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    31: astore          6
        //    33: aload           6
        //    35: ldc_w           "property_filters"
        //    38: iconst_2       
        //    39: anewarray       Ljava/lang/String;
        //    42: dup            
        //    43: iconst_0       
        //    44: ldc_w           "audience_id"
        //    47: aastore        
        //    48: dup            
        //    49: iconst_1       
        //    50: ldc_w           "data"
        //    53: aastore        
        //    54: ldc_w           "app_id=? AND property_name=?"
        //    57: iconst_2       
        //    58: anewarray       Ljava/lang/String;
        //    61: dup            
        //    62: iconst_0       
        //    63: aload_1        
        //    64: aastore        
        //    65: dup            
        //    66: iconst_1       
        //    67: aload_2        
        //    68: aastore        
        //    69: aconst_null    
        //    70: aconst_null    
        //    71: aconst_null    
        //    72: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    75: astore          10
        //    77: aload           10
        //    79: astore          8
        //    81: aload           8
        //    83: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    88: ifne            111
        //    91: invokestatic    java/util/Collections.emptyMap:()Ljava/util/Map;
        //    94: astore          20
        //    96: aload           8
        //    98: ifnull          108
        //   101: aload           8
        //   103: invokeinterface android/database/Cursor.close:()V
        //   108: aload           20
        //   110: areturn        
        //   111: aload           8
        //   113: iconst_1       
        //   114: invokeinterface android/database/Cursor.getBlob:(I)[B
        //   119: invokestatic    com/google/android/gms/internal/zzsm.zzD:([B)Lcom/google/android/gms/internal/zzsm;
        //   122: astore          11
        //   124: new             Lcom/google/android/gms/internal/zzpz$zze;
        //   127: dup            
        //   128: invokespecial   com/google/android/gms/internal/zzpz$zze.<init>:()V
        //   131: astore          12
        //   133: aload           12
        //   135: aload           11
        //   137: invokevirtual   com/google/android/gms/internal/zzpz$zze.zzx:(Lcom/google/android/gms/internal/zzsm;)Lcom/google/android/gms/internal/zzpz$zze;
        //   140: pop            
        //   141: aload           8
        //   143: iconst_0       
        //   144: invokeinterface android/database/Cursor.getInt:(I)I
        //   149: istore          16
        //   151: aload           5
        //   153: iload           16
        //   155: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   158: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   163: checkcast       Ljava/util/List;
        //   166: astore          17
        //   168: aload           17
        //   170: ifnonnull       197
        //   173: new             Ljava/util/ArrayList;
        //   176: dup            
        //   177: invokespecial   java/util/ArrayList.<init>:()V
        //   180: astore          17
        //   182: aload           5
        //   184: iload           16
        //   186: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   189: aload           17
        //   191: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   196: pop            
        //   197: aload           17
        //   199: aload           12
        //   201: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   206: pop            
        //   207: aload           8
        //   209: invokeinterface android/database/Cursor.moveToNext:()Z
        //   214: istore          14
        //   216: iload           14
        //   218: ifne            111
        //   221: aload           8
        //   223: ifnull          233
        //   226: aload           8
        //   228: invokeinterface android/database/Cursor.close:()V
        //   233: aload           5
        //   235: areturn        
        //   236: astore          13
        //   238: aload_0        
        //   239: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   242: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   245: ldc_w           "Failed to merge filter"
        //   248: aload_1        
        //   249: aload           13
        //   251: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   254: goto            207
        //   257: astore          7
        //   259: aload_0        
        //   260: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   263: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   266: ldc_w           "Database error querying filters"
        //   269: aload           7
        //   271: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   274: aload           8
        //   276: ifnull          286
        //   279: aload           8
        //   281: invokeinterface android/database/Cursor.close:()V
        //   286: aconst_null    
        //   287: areturn        
        //   288: astore          9
        //   290: aconst_null    
        //   291: astore          8
        //   293: aload           8
        //   295: ifnull          305
        //   298: aload           8
        //   300: invokeinterface android/database/Cursor.close:()V
        //   305: aload           9
        //   307: athrow         
        //   308: astore          9
        //   310: goto            293
        //   313: astore          7
        //   315: aconst_null    
        //   316: astore          8
        //   318: goto            259
        //    Signature:
        //  (Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map<Ljava/lang/Integer;Ljava/util/List<Lcom/google/android/gms/internal/zzpz$zze;>;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  33     77     313    321    Landroid/database/sqlite/SQLiteException;
        //  33     77     288    293    Any
        //  81     96     257    259    Landroid/database/sqlite/SQLiteException;
        //  81     96     308    313    Any
        //  111    133    257    259    Landroid/database/sqlite/SQLiteException;
        //  111    133    308    313    Any
        //  133    141    236    257    Ljava/io/IOException;
        //  133    141    257    259    Landroid/database/sqlite/SQLiteException;
        //  133    141    308    313    Any
        //  141    168    257    259    Landroid/database/sqlite/SQLiteException;
        //  141    168    308    313    Any
        //  173    197    257    259    Landroid/database/sqlite/SQLiteException;
        //  173    197    308    313    Any
        //  197    207    257    259    Landroid/database/sqlite/SQLiteException;
        //  197    207    308    313    Any
        //  207    216    257    259    Landroid/database/sqlite/SQLiteException;
        //  207    216    308    313    Any
        //  238    254    257    259    Landroid/database/sqlite/SQLiteException;
        //  238    254    308    313    Any
        //  259    274    308    313    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    public void zzZ(final long n) {
        this.zzjk();
        this.zzjv();
        if (this.getWritableDatabase().delete("queue", "rowid=?", new String[] { String.valueOf(n) }) != 1) {
            this.zzAo().zzCE().zzfg("Deleted fewer rows from queue than expected");
        }
    }
    
    @WorkerThread
    public zza zza(final long p0, final String p1, final boolean p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //     4: pop            
        //     5: aload_0        
        //     6: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     9: aload_0        
        //    10: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    13: iconst_1       
        //    14: anewarray       Ljava/lang/String;
        //    17: dup            
        //    18: iconst_0       
        //    19: aload_3        
        //    20: aastore        
        //    21: astore          7
        //    23: new             Lcom/google/android/gms/measurement/internal/zze$zza;
        //    26: dup            
        //    27: invokespecial   com/google/android/gms/measurement/internal/zze$zza.<init>:()V
        //    30: astore          8
        //    32: aload_0        
        //    33: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    36: astore          12
        //    38: aload           12
        //    40: ldc_w           "apps"
        //    43: iconst_4       
        //    44: anewarray       Ljava/lang/String;
        //    47: dup            
        //    48: iconst_0       
        //    49: ldc             "day"
        //    51: aastore        
        //    52: dup            
        //    53: iconst_1       
        //    54: ldc             "daily_events_count"
        //    56: aastore        
        //    57: dup            
        //    58: iconst_2       
        //    59: ldc             "daily_public_events_count"
        //    61: aastore        
        //    62: dup            
        //    63: iconst_3       
        //    64: ldc             "daily_conversions_count"
        //    66: aastore        
        //    67: ldc_w           "app_id=?"
        //    70: iconst_1       
        //    71: anewarray       Ljava/lang/String;
        //    74: dup            
        //    75: iconst_0       
        //    76: aload_3        
        //    77: aastore        
        //    78: aconst_null    
        //    79: aconst_null    
        //    80: aconst_null    
        //    81: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    84: astore          13
        //    86: aload           13
        //    88: astore          10
        //    90: aload           10
        //    92: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    97: ifne            129
        //   100: aload_0        
        //   101: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   104: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   107: ldc_w           "Not updating daily counts, app is not known"
        //   110: aload_3        
        //   111: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   114: aload           10
        //   116: ifnull          126
        //   119: aload           10
        //   121: invokeinterface android/database/Cursor.close:()V
        //   126: aload           8
        //   128: areturn        
        //   129: aload           10
        //   131: iconst_0       
        //   132: invokeinterface android/database/Cursor.getLong:(I)J
        //   137: lload_1        
        //   138: lcmp           
        //   139: ifne            181
        //   142: aload           8
        //   144: aload           10
        //   146: iconst_1       
        //   147: invokeinterface android/database/Cursor.getLong:(I)J
        //   152: putfield        com/google/android/gms/measurement/internal/zze$zza.zzaVF:J
        //   155: aload           8
        //   157: aload           10
        //   159: iconst_2       
        //   160: invokeinterface android/database/Cursor.getLong:(I)J
        //   165: putfield        com/google/android/gms/measurement/internal/zze$zza.zzaVE:J
        //   168: aload           8
        //   170: aload           10
        //   172: iconst_3       
        //   173: invokeinterface android/database/Cursor.getLong:(I)J
        //   178: putfield        com/google/android/gms/measurement/internal/zze$zza.zzaVG:J
        //   181: aload           8
        //   183: lconst_1       
        //   184: aload           8
        //   186: getfield        com/google/android/gms/measurement/internal/zze$zza.zzaVF:J
        //   189: ladd           
        //   190: putfield        com/google/android/gms/measurement/internal/zze$zza.zzaVF:J
        //   193: iload           4
        //   195: ifeq            210
        //   198: aload           8
        //   200: lconst_1       
        //   201: aload           8
        //   203: getfield        com/google/android/gms/measurement/internal/zze$zza.zzaVE:J
        //   206: ladd           
        //   207: putfield        com/google/android/gms/measurement/internal/zze$zza.zzaVE:J
        //   210: iload           5
        //   212: ifeq            227
        //   215: aload           8
        //   217: lconst_1       
        //   218: aload           8
        //   220: getfield        com/google/android/gms/measurement/internal/zze$zza.zzaVG:J
        //   223: ladd           
        //   224: putfield        com/google/android/gms/measurement/internal/zze$zza.zzaVG:J
        //   227: new             Landroid/content/ContentValues;
        //   230: dup            
        //   231: invokespecial   android/content/ContentValues.<init>:()V
        //   234: astore          14
        //   236: aload           14
        //   238: ldc             "day"
        //   240: lload_1        
        //   241: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   244: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   247: aload           14
        //   249: ldc             "daily_public_events_count"
        //   251: aload           8
        //   253: getfield        com/google/android/gms/measurement/internal/zze$zza.zzaVE:J
        //   256: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   259: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   262: aload           14
        //   264: ldc             "daily_events_count"
        //   266: aload           8
        //   268: getfield        com/google/android/gms/measurement/internal/zze$zza.zzaVF:J
        //   271: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   274: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   277: aload           14
        //   279: ldc             "daily_conversions_count"
        //   281: aload           8
        //   283: getfield        com/google/android/gms/measurement/internal/zze$zza.zzaVG:J
        //   286: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   289: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   292: aload           12
        //   294: ldc_w           "apps"
        //   297: aload           14
        //   299: ldc_w           "app_id=?"
        //   302: aload           7
        //   304: invokevirtual   android/database/sqlite/SQLiteDatabase.update:(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   307: pop            
        //   308: aload           10
        //   310: ifnull          320
        //   313: aload           10
        //   315: invokeinterface android/database/Cursor.close:()V
        //   320: aload           8
        //   322: areturn        
        //   323: astore          11
        //   325: aconst_null    
        //   326: astore          10
        //   328: aload_0        
        //   329: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   332: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   335: ldc_w           "Error updating daily counts"
        //   338: aload           11
        //   340: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   343: aload           10
        //   345: ifnull          355
        //   348: aload           10
        //   350: invokeinterface android/database/Cursor.close:()V
        //   355: aload           8
        //   357: areturn        
        //   358: astore          9
        //   360: aconst_null    
        //   361: astore          10
        //   363: aload           10
        //   365: ifnull          375
        //   368: aload           10
        //   370: invokeinterface android/database/Cursor.close:()V
        //   375: aload           9
        //   377: athrow         
        //   378: astore          9
        //   380: goto            363
        //   383: astore          11
        //   385: goto            328
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  32     86     323    328    Landroid/database/sqlite/SQLiteException;
        //  32     86     358    363    Any
        //  90     114    383    388    Landroid/database/sqlite/SQLiteException;
        //  90     114    378    383    Any
        //  129    181    383    388    Landroid/database/sqlite/SQLiteException;
        //  129    181    378    383    Any
        //  181    193    383    388    Landroid/database/sqlite/SQLiteException;
        //  181    193    378    383    Any
        //  198    210    383    388    Landroid/database/sqlite/SQLiteException;
        //  198    210    378    383    Any
        //  215    227    383    388    Landroid/database/sqlite/SQLiteException;
        //  215    227    378    383    Any
        //  227    308    383    388    Landroid/database/sqlite/SQLiteException;
        //  227    308    378    383    Any
        //  328    343    378    383    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    void zza(final ContentValues contentValues, final String s, final Object o) {
        zzx.zzcM(s);
        zzx.zzz(o);
        if (o instanceof String) {
            contentValues.put(s, (String)o);
            return;
        }
        if (o instanceof Long) {
            contentValues.put(s, (Long)o);
            return;
        }
        if (o instanceof Float) {
            contentValues.put(s, (Float)o);
            return;
        }
        throw new IllegalArgumentException("Invalid value type");
    }
    
    @WorkerThread
    public void zza(final zzqb.zze p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: pop            
        //    13: aload_1        
        //    14: getfield        com/google/android/gms/internal/zzqb$zze.appId:Ljava/lang/String;
        //    17: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    20: pop            
        //    21: aload_1        
        //    22: getfield        com/google/android/gms/internal/zzqb$zze.zzbaq:Ljava/lang/Long;
        //    25: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //    28: pop            
        //    29: aload_0        
        //    30: invokevirtual   com/google/android/gms/measurement/internal/zze.zzCr:()V
        //    33: aload_0        
        //    34: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjl:()Lcom/google/android/gms/internal/zzmq;
        //    37: invokeinterface com/google/android/gms/internal/zzmq.currentTimeMillis:()J
        //    42: lstore          5
        //    44: aload_1        
        //    45: getfield        com/google/android/gms/internal/zzqb$zze.zzbaq:Ljava/lang/Long;
        //    48: invokevirtual   java/lang/Long.longValue:()J
        //    51: lload           5
        //    53: aload_0        
        //    54: invokevirtual   com/google/android/gms/measurement/internal/zze.zzCp:()Lcom/google/android/gms/measurement/internal/zzd;
        //    57: invokevirtual   com/google/android/gms/measurement/internal/zzd.zzBQ:()J
        //    60: lsub           
        //    61: lcmp           
        //    62: iflt            86
        //    65: aload_1        
        //    66: getfield        com/google/android/gms/internal/zzqb$zze.zzbaq:Ljava/lang/Long;
        //    69: invokevirtual   java/lang/Long.longValue:()J
        //    72: lload           5
        //    74: aload_0        
        //    75: invokevirtual   com/google/android/gms/measurement/internal/zze.zzCp:()Lcom/google/android/gms/measurement/internal/zzd;
        //    78: invokevirtual   com/google/android/gms/measurement/internal/zzd.zzBQ:()J
        //    81: ladd           
        //    82: lcmp           
        //    83: ifle            108
        //    86: aload_0        
        //    87: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //    90: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //    93: ldc_w           "Storing bundle outside of the max uploading time span. now, timestamp"
        //    96: lload           5
        //    98: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   101: aload_1        
        //   102: getfield        com/google/android/gms/internal/zzqb$zze.zzbaq:Ljava/lang/Long;
        //   105: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   108: aload_1        
        //   109: invokevirtual   com/google/android/gms/internal/zzqb$zze.getSerializedSize:()I
        //   112: newarray        B
        //   114: astore          8
        //   116: aload           8
        //   118: invokestatic    com/google/android/gms/internal/zzsn.zzE:([B)Lcom/google/android/gms/internal/zzsn;
        //   121: astore          9
        //   123: aload_1        
        //   124: aload           9
        //   126: invokevirtual   com/google/android/gms/internal/zzqb$zze.writeTo:(Lcom/google/android/gms/internal/zzsn;)V
        //   129: aload           9
        //   131: invokevirtual   com/google/android/gms/internal/zzsn.zzJo:()V
        //   134: aload_0        
        //   135: invokevirtual   com/google/android/gms/measurement/internal/zze.zzCk:()Lcom/google/android/gms/measurement/internal/zzaj;
        //   138: aload           8
        //   140: invokevirtual   com/google/android/gms/measurement/internal/zzaj.zzg:([B)[B
        //   143: astore          10
        //   145: aload_0        
        //   146: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   149: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   152: ldc_w           "Saving bundle, size"
        //   155: aload           10
        //   157: arraylength    
        //   158: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   161: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   164: new             Landroid/content/ContentValues;
        //   167: dup            
        //   168: invokespecial   android/content/ContentValues.<init>:()V
        //   171: astore          11
        //   173: aload           11
        //   175: ldc_w           "app_id"
        //   178: aload_1        
        //   179: getfield        com/google/android/gms/internal/zzqb$zze.appId:Ljava/lang/String;
        //   182: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   185: aload           11
        //   187: ldc_w           "bundle_end_timestamp"
        //   190: aload_1        
        //   191: getfield        com/google/android/gms/internal/zzqb$zze.zzbaq:Ljava/lang/Long;
        //   194: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   197: aload           11
        //   199: ldc_w           "data"
        //   202: aload           10
        //   204: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;[B)V
        //   207: aload_0        
        //   208: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //   211: ldc_w           "queue"
        //   214: aconst_null    
        //   215: aload           11
        //   217: invokevirtual   android/database/sqlite/SQLiteDatabase.insert:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   220: ldc2_w          -1
        //   223: lcmp           
        //   224: ifne            240
        //   227: aload_0        
        //   228: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   231: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   234: ldc_w           "Failed to insert bundle (got -1)"
        //   237: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   240: return         
        //   241: astore          7
        //   243: aload_0        
        //   244: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   247: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   250: ldc_w           "Data loss. Failed to serialize bundle"
        //   253: aload           7
        //   255: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   258: return         
        //   259: astore          12
        //   261: aload_0        
        //   262: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   265: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   268: ldc_w           "Error storing bundle"
        //   271: aload           12
        //   273: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   276: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  108    145    241    259    Ljava/io/IOException;
        //  207    240    259    277    Landroid/database/sqlite/SQLiteException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    public void zza(final com.google.android.gms.measurement.internal.zza zza) {
        zzx.zzz(zza);
        this.zzjk();
        this.zzjv();
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zza.zzwK());
        contentValues.put("app_instance_id", zza.zzBj());
        contentValues.put("gmp_app_id", zza.zzBk());
        contentValues.put("resettable_device_id_hash", zza.zzBl());
        contentValues.put("last_bundle_index", zza.zzBr());
        contentValues.put("last_bundle_start_timestamp", zza.zzBm());
        contentValues.put("last_bundle_end_timestamp", zza.zzBn());
        contentValues.put("app_version", zza.zzli());
        contentValues.put("app_store", zza.zzBo());
        contentValues.put("gmp_version", zza.zzBp());
        contentValues.put("dev_cert_hash", zza.zzBq());
        contentValues.put("measurement_enabled", zza.zzAr());
        contentValues.put("day", zza.zzBv());
        contentValues.put("daily_public_events_count", zza.zzBw());
        contentValues.put("daily_events_count", zza.zzBx());
        contentValues.put("daily_conversions_count", zza.zzBy());
        contentValues.put("config_fetched_time", zza.zzBs());
        contentValues.put("failed_config_fetch_time", zza.zzBt());
        try {
            if (this.getWritableDatabase().insertWithOnConflict("apps", (String)null, contentValues, 5) == -1L) {
                this.zzAo().zzCE().zzfg("Failed to insert/update app (got -1)");
            }
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zzj("Error storing app", ex);
        }
    }
    
    public void zza(final zzh p0, final long p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: pop            
        //    13: aload_1        
        //    14: getfield        com/google/android/gms/measurement/internal/zzh.zzaUa:Ljava/lang/String;
        //    17: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    20: pop            
        //    21: new             Lcom/google/android/gms/internal/zzqb$zzb;
        //    24: dup            
        //    25: invokespecial   com/google/android/gms/internal/zzqb$zzb.<init>:()V
        //    28: astore          6
        //    30: aload           6
        //    32: aload_1        
        //    33: getfield        com/google/android/gms/measurement/internal/zzh.zzaVN:J
        //    36: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    39: putfield        com/google/android/gms/internal/zzqb$zzb.zzbag:Ljava/lang/Long;
        //    42: aload           6
        //    44: aload_1        
        //    45: getfield        com/google/android/gms/measurement/internal/zzh.zzaVO:Lcom/google/android/gms/measurement/internal/EventParams;
        //    48: invokevirtual   com/google/android/gms/measurement/internal/EventParams.size:()I
        //    51: anewarray       Lcom/google/android/gms/internal/zzqb$zzc;
        //    54: putfield        com/google/android/gms/internal/zzqb$zzb.zzbae:[Lcom/google/android/gms/internal/zzqb$zzc;
        //    57: aload_1        
        //    58: getfield        com/google/android/gms/measurement/internal/zzh.zzaVO:Lcom/google/android/gms/measurement/internal/EventParams;
        //    61: invokevirtual   com/google/android/gms/measurement/internal/EventParams.iterator:()Ljava/util/Iterator;
        //    64: astore          7
        //    66: iconst_0       
        //    67: istore          8
        //    69: aload           7
        //    71: invokeinterface java/util/Iterator.hasNext:()Z
        //    76: ifeq            156
        //    79: aload           7
        //    81: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    86: checkcast       Ljava/lang/String;
        //    89: astore          14
        //    91: new             Lcom/google/android/gms/internal/zzqb$zzc;
        //    94: dup            
        //    95: invokespecial   com/google/android/gms/internal/zzqb$zzc.<init>:()V
        //    98: astore          15
        //   100: aload           6
        //   102: getfield        com/google/android/gms/internal/zzqb$zzb.zzbae:[Lcom/google/android/gms/internal/zzqb$zzc;
        //   105: astore          16
        //   107: iload           8
        //   109: iconst_1       
        //   110: iadd           
        //   111: istore          17
        //   113: aload           16
        //   115: iload           8
        //   117: aload           15
        //   119: aastore        
        //   120: aload           15
        //   122: aload           14
        //   124: putfield        com/google/android/gms/internal/zzqb$zzc.name:Ljava/lang/String;
        //   127: aload_1        
        //   128: getfield        com/google/android/gms/measurement/internal/zzh.zzaVO:Lcom/google/android/gms/measurement/internal/EventParams;
        //   131: aload           14
        //   133: invokevirtual   com/google/android/gms/measurement/internal/EventParams.get:(Ljava/lang/String;)Ljava/lang/Object;
        //   136: astore          18
        //   138: aload_0        
        //   139: invokevirtual   com/google/android/gms/measurement/internal/zze.zzCk:()Lcom/google/android/gms/measurement/internal/zzaj;
        //   142: aload           15
        //   144: aload           18
        //   146: invokevirtual   com/google/android/gms/measurement/internal/zzaj.zza:(Lcom/google/android/gms/internal/zzqb$zzc;Ljava/lang/Object;)V
        //   149: iload           17
        //   151: istore          8
        //   153: goto            69
        //   156: aload           6
        //   158: invokevirtual   com/google/android/gms/internal/zzqb$zzb.getSerializedSize:()I
        //   161: newarray        B
        //   163: astore          10
        //   165: aload           10
        //   167: invokestatic    com/google/android/gms/internal/zzsn.zzE:([B)Lcom/google/android/gms/internal/zzsn;
        //   170: astore          11
        //   172: aload           6
        //   174: aload           11
        //   176: invokevirtual   com/google/android/gms/internal/zzqb$zzb.writeTo:(Lcom/google/android/gms/internal/zzsn;)V
        //   179: aload           11
        //   181: invokevirtual   com/google/android/gms/internal/zzsn.zzJo:()V
        //   184: aload_0        
        //   185: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   188: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   191: ldc_w           "Saving event, name, data size"
        //   194: aload_1        
        //   195: getfield        com/google/android/gms/measurement/internal/zzh.mName:Ljava/lang/String;
        //   198: aload           10
        //   200: arraylength    
        //   201: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   204: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   207: new             Landroid/content/ContentValues;
        //   210: dup            
        //   211: invokespecial   android/content/ContentValues.<init>:()V
        //   214: astore          12
        //   216: aload           12
        //   218: ldc_w           "app_id"
        //   221: aload_1        
        //   222: getfield        com/google/android/gms/measurement/internal/zzh.zzaUa:Ljava/lang/String;
        //   225: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   228: aload           12
        //   230: ldc_w           "name"
        //   233: aload_1        
        //   234: getfield        com/google/android/gms/measurement/internal/zzh.mName:Ljava/lang/String;
        //   237: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   240: aload           12
        //   242: ldc_w           "timestamp"
        //   245: aload_1        
        //   246: getfield        com/google/android/gms/measurement/internal/zzh.zzaez:J
        //   249: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   252: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   255: aload           12
        //   257: ldc_w           "metadata_fingerprint"
        //   260: lload_2        
        //   261: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   264: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   267: aload           12
        //   269: ldc_w           "data"
        //   272: aload           10
        //   274: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;[B)V
        //   277: aload_0        
        //   278: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //   281: ldc_w           "raw_events"
        //   284: aconst_null    
        //   285: aload           12
        //   287: invokevirtual   android/database/sqlite/SQLiteDatabase.insert:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   290: ldc2_w          -1
        //   293: lcmp           
        //   294: ifne            310
        //   297: aload_0        
        //   298: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   301: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   304: ldc_w           "Failed to insert raw event (got -1)"
        //   307: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   310: return         
        //   311: astore          9
        //   313: aload_0        
        //   314: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   317: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   320: ldc_w           "Data loss. Failed to serialize event params/data"
        //   323: aload           9
        //   325: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   328: return         
        //   329: astore          13
        //   331: aload_0        
        //   332: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   335: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   338: ldc_w           "Error storing raw event"
        //   341: aload           13
        //   343: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   346: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  156    184    311    329    Ljava/io/IOException;
        //  277    310    329    347    Landroid/database/sqlite/SQLiteException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    public void zza(final zzi zzi) {
        zzx.zzz(zzi);
        this.zzjk();
        this.zzjv();
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzi.zzaUa);
        contentValues.put("name", zzi.mName);
        contentValues.put("lifetime_count", zzi.zzaVP);
        contentValues.put("current_bundle_count", zzi.zzaVQ);
        contentValues.put("last_fire_timestamp", zzi.zzaVR);
        try {
            if (this.getWritableDatabase().insertWithOnConflict("events", (String)null, contentValues, 5) == -1L) {
                this.zzAo().zzCE().zzfg("Failed to insert/update event aggregates (got -1)");
            }
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zzj("Error storing event aggregates", ex);
        }
    }
    
    void zza(final String p0, final int p1, final zzqb.zzf p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     8: aload_1        
        //     9: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    12: pop            
        //    13: aload_3        
        //    14: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //    17: pop            
        //    18: aload_3        
        //    19: invokevirtual   com/google/android/gms/internal/zzqb$zzf.getSerializedSize:()I
        //    22: newarray        B
        //    24: astore          7
        //    26: aload           7
        //    28: invokestatic    com/google/android/gms/internal/zzsn.zzE:([B)Lcom/google/android/gms/internal/zzsn;
        //    31: astore          8
        //    33: aload_3        
        //    34: aload           8
        //    36: invokevirtual   com/google/android/gms/internal/zzqb$zzf.writeTo:(Lcom/google/android/gms/internal/zzsn;)V
        //    39: aload           8
        //    41: invokevirtual   com/google/android/gms/internal/zzsn.zzJo:()V
        //    44: new             Landroid/content/ContentValues;
        //    47: dup            
        //    48: invokespecial   android/content/ContentValues.<init>:()V
        //    51: astore          9
        //    53: aload           9
        //    55: ldc_w           "app_id"
        //    58: aload_1        
        //    59: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //    62: aload           9
        //    64: ldc_w           "audience_id"
        //    67: iload_2        
        //    68: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    71: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //    74: aload           9
        //    76: ldc_w           "current_results"
        //    79: aload           7
        //    81: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;[B)V
        //    84: aload_0        
        //    85: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    88: ldc_w           "audience_filter_values"
        //    91: aconst_null    
        //    92: aload           9
        //    94: iconst_5       
        //    95: invokevirtual   android/database/sqlite/SQLiteDatabase.insertWithOnConflict:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
        //    98: ldc2_w          -1
        //   101: lcmp           
        //   102: ifne            118
        //   105: aload_0        
        //   106: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   109: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   112: ldc_w           "Failed to insert filter results (got -1)"
        //   115: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   118: return         
        //   119: astore          6
        //   121: aload_0        
        //   122: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   125: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   128: ldc_w           "Configuration loss. Failed to serialize filter results"
        //   131: aload           6
        //   133: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   136: return         
        //   137: astore          10
        //   139: aload_0        
        //   140: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   143: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   146: ldc_w           "Error storing filter results"
        //   149: aload           10
        //   151: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   154: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  18     44     119    137    Ljava/io/IOException;
        //  84     118    137    155    Landroid/database/sqlite/SQLiteException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void zza(final String p0, final long p1, final zzb p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload           4
        //     5: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //     8: pop            
        //     9: aload_0        
        //    10: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //    13: aload_0        
        //    14: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    17: aload_0        
        //    18: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    21: astore          10
        //    23: aload_1        
        //    24: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    27: ifeq            201
        //    30: iconst_1       
        //    31: anewarray       Ljava/lang/String;
        //    34: astore          28
        //    36: aload           28
        //    38: iconst_0       
        //    39: lload_2        
        //    40: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    43: aastore        
        //    44: aload           10
        //    46: ldc_w           "select app_id, metadata_fingerprint from raw_events where app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
        //    49: aload           28
        //    51: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //    54: astore          29
        //    56: aload           29
        //    58: astore          5
        //    60: aload           5
        //    62: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    67: istore          30
        //    69: iload           30
        //    71: ifne            87
        //    74: aload           5
        //    76: ifnull          86
        //    79: aload           5
        //    81: invokeinterface android/database/Cursor.close:()V
        //    86: return         
        //    87: aload           5
        //    89: iconst_0       
        //    90: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    95: astore_1       
        //    96: aload           5
        //    98: iconst_1       
        //    99: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   104: astore          31
        //   106: aload           5
        //   108: invokeinterface android/database/Cursor.close:()V
        //   113: aload           31
        //   115: astore          14
        //   117: aload           5
        //   119: astore          8
        //   121: aload           10
        //   123: ldc_w           "raw_events_metadata"
        //   126: iconst_1       
        //   127: anewarray       Ljava/lang/String;
        //   130: dup            
        //   131: iconst_0       
        //   132: ldc_w           "metadata"
        //   135: aastore        
        //   136: ldc_w           "app_id=? and metadata_fingerprint=?"
        //   139: iconst_2       
        //   140: anewarray       Ljava/lang/String;
        //   143: dup            
        //   144: iconst_0       
        //   145: aload_1        
        //   146: aastore        
        //   147: dup            
        //   148: iconst_1       
        //   149: aload           14
        //   151: aastore        
        //   152: aconst_null    
        //   153: aconst_null    
        //   154: ldc_w           "rowid"
        //   157: ldc_w           "2"
        //   160: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   163: astore          8
        //   165: aload           8
        //   167: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   172: ifne            278
        //   175: aload_0        
        //   176: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   179: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   182: ldc_w           "Raw event metadata record is missing"
        //   185: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   188: aload           8
        //   190: ifnull          86
        //   193: aload           8
        //   195: invokeinterface android/database/Cursor.close:()V
        //   200: return         
        //   201: aload           10
        //   203: ldc_w           "select metadata_fingerprint from raw_events where app_id = ? order by rowid limit 1;"
        //   206: iconst_1       
        //   207: anewarray       Ljava/lang/String;
        //   210: dup            
        //   211: iconst_0       
        //   212: aload_1        
        //   213: aastore        
        //   214: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   217: astore          11
        //   219: aload           11
        //   221: astore          5
        //   223: aload           5
        //   225: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   230: istore          12
        //   232: iload           12
        //   234: ifne            250
        //   237: aload           5
        //   239: ifnull          86
        //   242: aload           5
        //   244: invokeinterface android/database/Cursor.close:()V
        //   249: return         
        //   250: aload           5
        //   252: iconst_0       
        //   253: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   258: astore          13
        //   260: aload           5
        //   262: invokeinterface android/database/Cursor.close:()V
        //   267: aload           13
        //   269: astore          14
        //   271: aload           5
        //   273: astore          8
        //   275: goto            121
        //   278: aload           8
        //   280: iconst_0       
        //   281: invokeinterface android/database/Cursor.getBlob:(I)[B
        //   286: invokestatic    com/google/android/gms/internal/zzsm.zzD:([B)Lcom/google/android/gms/internal/zzsm;
        //   289: astore          15
        //   291: new             Lcom/google/android/gms/internal/zzqb$zze;
        //   294: dup            
        //   295: invokespecial   com/google/android/gms/internal/zzqb$zze.<init>:()V
        //   298: astore          16
        //   300: aload           16
        //   302: aload           15
        //   304: invokevirtual   com/google/android/gms/internal/zzqb$zze.zzG:(Lcom/google/android/gms/internal/zzsm;)Lcom/google/android/gms/internal/zzqb$zze;
        //   307: pop            
        //   308: aload           8
        //   310: invokeinterface android/database/Cursor.moveToNext:()Z
        //   315: ifeq            331
        //   318: aload_0        
        //   319: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   322: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   325: ldc_w           "Get multiple raw event metadata records, expected one"
        //   328: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   331: aload           8
        //   333: invokeinterface android/database/Cursor.close:()V
        //   338: aload           4
        //   340: aload           16
        //   342: invokeinterface com/google/android/gms/measurement/internal/zze$zzb.zzc:(Lcom/google/android/gms/internal/zzqb$zze;)V
        //   347: aload           10
        //   349: ldc_w           "raw_events"
        //   352: iconst_4       
        //   353: anewarray       Ljava/lang/String;
        //   356: dup            
        //   357: iconst_0       
        //   358: ldc_w           "rowid"
        //   361: aastore        
        //   362: dup            
        //   363: iconst_1       
        //   364: ldc_w           "name"
        //   367: aastore        
        //   368: dup            
        //   369: iconst_2       
        //   370: ldc_w           "timestamp"
        //   373: aastore        
        //   374: dup            
        //   375: iconst_3       
        //   376: ldc_w           "data"
        //   379: aastore        
        //   380: ldc_w           "app_id=? and metadata_fingerprint=?"
        //   383: iconst_2       
        //   384: anewarray       Ljava/lang/String;
        //   387: dup            
        //   388: iconst_0       
        //   389: aload_1        
        //   390: aastore        
        //   391: dup            
        //   392: iconst_1       
        //   393: aload           14
        //   395: aastore        
        //   396: aconst_null    
        //   397: aconst_null    
        //   398: ldc_w           "rowid"
        //   401: aconst_null    
        //   402: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   405: astore          19
        //   407: aload           19
        //   409: astore          5
        //   411: aload           5
        //   413: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   418: ifne            478
        //   421: aload_0        
        //   422: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   425: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   428: ldc_w           "Raw event data disappeared while in transaction"
        //   431: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   434: aload           5
        //   436: ifnull          86
        //   439: aload           5
        //   441: invokeinterface android/database/Cursor.close:()V
        //   446: return         
        //   447: astore          17
        //   449: aload_0        
        //   450: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   453: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   456: ldc_w           "Data loss. Failed to merge raw event metadata"
        //   459: aload_1        
        //   460: aload           17
        //   462: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   465: aload           8
        //   467: ifnull          86
        //   470: aload           8
        //   472: invokeinterface android/database/Cursor.close:()V
        //   477: return         
        //   478: aload           5
        //   480: iconst_0       
        //   481: invokeinterface android/database/Cursor.getLong:(I)J
        //   486: lstore          20
        //   488: aload           5
        //   490: iconst_3       
        //   491: invokeinterface android/database/Cursor.getBlob:(I)[B
        //   496: invokestatic    com/google/android/gms/internal/zzsm.zzD:([B)Lcom/google/android/gms/internal/zzsm;
        //   499: astore          22
        //   501: new             Lcom/google/android/gms/internal/zzqb$zzb;
        //   504: dup            
        //   505: invokespecial   com/google/android/gms/internal/zzqb$zzb.<init>:()V
        //   508: astore          23
        //   510: aload           23
        //   512: aload           22
        //   514: invokevirtual   com/google/android/gms/internal/zzqb$zzb.zzD:(Lcom/google/android/gms/internal/zzsm;)Lcom/google/android/gms/internal/zzqb$zzb;
        //   517: pop            
        //   518: aload           23
        //   520: aload           5
        //   522: iconst_1       
        //   523: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   528: putfield        com/google/android/gms/internal/zzqb$zzb.name:Ljava/lang/String;
        //   531: aload           23
        //   533: aload           5
        //   535: iconst_2       
        //   536: invokeinterface android/database/Cursor.getLong:(I)J
        //   541: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   544: putfield        com/google/android/gms/internal/zzqb$zzb.zzbaf:Ljava/lang/Long;
        //   547: aload           4
        //   549: lload           20
        //   551: aload           23
        //   553: invokeinterface com/google/android/gms/measurement/internal/zze$zzb.zza:(JLcom/google/android/gms/internal/zzqb$zzb;)Z
        //   558: istore          27
        //   560: iload           27
        //   562: ifne            596
        //   565: aload           5
        //   567: ifnull          86
        //   570: aload           5
        //   572: invokeinterface android/database/Cursor.close:()V
        //   577: return         
        //   578: astore          24
        //   580: aload_0        
        //   581: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   584: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   587: ldc_w           "Data loss. Failed to merge raw event"
        //   590: aload_1        
        //   591: aload           24
        //   593: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   596: aload           5
        //   598: invokeinterface android/database/Cursor.moveToNext:()Z
        //   603: istore          25
        //   605: iload           25
        //   607: ifne            478
        //   610: aload           5
        //   612: ifnull          86
        //   615: aload           5
        //   617: invokeinterface android/database/Cursor.close:()V
        //   622: return         
        //   623: astore          9
        //   625: aload_0        
        //   626: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   629: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   632: ldc_w           "Data loss. Error selecting raw event"
        //   635: aload           9
        //   637: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   640: aload           5
        //   642: ifnull          86
        //   645: aload           5
        //   647: invokeinterface android/database/Cursor.close:()V
        //   652: return         
        //   653: astore          7
        //   655: aconst_null    
        //   656: astore          8
        //   658: aload           8
        //   660: ifnull          670
        //   663: aload           8
        //   665: invokeinterface android/database/Cursor.close:()V
        //   670: aload           7
        //   672: athrow         
        //   673: astore          7
        //   675: aload           5
        //   677: astore          8
        //   679: goto            658
        //   682: astore          7
        //   684: goto            658
        //   687: astore          9
        //   689: aload           8
        //   691: astore          5
        //   693: goto            625
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  17     56     623    625    Landroid/database/sqlite/SQLiteException;
        //  17     56     653    658    Any
        //  60     69     623    625    Landroid/database/sqlite/SQLiteException;
        //  60     69     673    682    Any
        //  87     113    623    625    Landroid/database/sqlite/SQLiteException;
        //  87     113    673    682    Any
        //  121    188    687    696    Landroid/database/sqlite/SQLiteException;
        //  121    188    682    687    Any
        //  201    219    623    625    Landroid/database/sqlite/SQLiteException;
        //  201    219    653    658    Any
        //  223    232    623    625    Landroid/database/sqlite/SQLiteException;
        //  223    232    673    682    Any
        //  250    267    623    625    Landroid/database/sqlite/SQLiteException;
        //  250    267    673    682    Any
        //  278    300    687    696    Landroid/database/sqlite/SQLiteException;
        //  278    300    682    687    Any
        //  300    308    447    478    Ljava/io/IOException;
        //  300    308    687    696    Landroid/database/sqlite/SQLiteException;
        //  300    308    682    687    Any
        //  308    331    687    696    Landroid/database/sqlite/SQLiteException;
        //  308    331    682    687    Any
        //  331    407    687    696    Landroid/database/sqlite/SQLiteException;
        //  331    407    682    687    Any
        //  411    434    623    625    Landroid/database/sqlite/SQLiteException;
        //  411    434    673    682    Any
        //  449    465    687    696    Landroid/database/sqlite/SQLiteException;
        //  449    465    682    687    Any
        //  478    510    623    625    Landroid/database/sqlite/SQLiteException;
        //  478    510    673    682    Any
        //  510    518    578    596    Ljava/io/IOException;
        //  510    518    623    625    Landroid/database/sqlite/SQLiteException;
        //  510    518    673    682    Any
        //  518    560    623    625    Landroid/database/sqlite/SQLiteException;
        //  518    560    673    682    Any
        //  580    596    623    625    Landroid/database/sqlite/SQLiteException;
        //  580    596    673    682    Any
        //  596    605    623    625    Landroid/database/sqlite/SQLiteException;
        //  596    605    673    682    Any
        //  625    640    673    682    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    public boolean zza(final zzai zzai) {
        zzx.zzz(zzai);
        this.zzjk();
        this.zzjv();
        Label_0110: {
            if (this.zzK(zzai.zzaUa, zzai.mName) == null) {
                if (zzaj.zzfq(zzai.mName)) {
                    if (this.zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[] { zzai.zzaUa }) < this.zzCp().zzBL()) {
                        break Label_0110;
                    }
                }
                else if (this.zzb("select count(1) from user_attributes where app_id=?", new String[] { zzai.zzaUa }) < this.zzCp().zzBM()) {
                    break Label_0110;
                }
                return false;
            }
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzai.zzaUa);
        contentValues.put("name", zzai.mName);
        contentValues.put("set_timestamp", zzai.zzaZp);
        this.zza(contentValues, "value", zzai.zzNc);
        try {
            if (this.getWritableDatabase().insertWithOnConflict("user_attributes", (String)null, contentValues, 5) == -1L) {
                this.zzAo().zzCE().zzfg("Failed to insert/update user property (got -1)");
            }
            return true;
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zzj("Error storing user property", ex);
            return true;
        }
    }
    
    public String zzaa(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     4: aload_0        
        //     5: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //     8: aload_0        
        //     9: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    12: astore          8
        //    14: iconst_1       
        //    15: anewarray       Ljava/lang/String;
        //    18: astore          9
        //    20: aload           9
        //    22: iconst_0       
        //    23: lload_1        
        //    24: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    27: aastore        
        //    28: aload           8
        //    30: ldc_w           "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
        //    33: aload           9
        //    35: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //    38: astore          10
        //    40: aload           10
        //    42: astore          4
        //    44: aload           4
        //    46: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    51: ifne            85
        //    54: aload_0        
        //    55: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //    58: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //    61: ldc_w           "No expired configs for apps with pending events"
        //    64: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //    67: aconst_null    
        //    68: astore          7
        //    70: aload           4
        //    72: ifnull          82
        //    75: aload           4
        //    77: invokeinterface android/database/Cursor.close:()V
        //    82: aload           7
        //    84: areturn        
        //    85: aload           4
        //    87: iconst_0       
        //    88: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    93: astore          11
        //    95: aload           11
        //    97: astore          7
        //    99: aload           4
        //   101: ifnull          82
        //   104: aload           4
        //   106: invokeinterface android/database/Cursor.close:()V
        //   111: aload           7
        //   113: areturn        
        //   114: astore          6
        //   116: aconst_null    
        //   117: astore          4
        //   119: aload_0        
        //   120: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   123: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   126: ldc_w           "Error selecting expired configs"
        //   129: aload           6
        //   131: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   134: aconst_null    
        //   135: astore          7
        //   137: aload           4
        //   139: ifnull          82
        //   142: aload           4
        //   144: invokeinterface android/database/Cursor.close:()V
        //   149: aconst_null    
        //   150: areturn        
        //   151: astore_3       
        //   152: aconst_null    
        //   153: astore          4
        //   155: aload_3        
        //   156: astore          5
        //   158: aload           4
        //   160: ifnull          170
        //   163: aload           4
        //   165: invokeinterface android/database/Cursor.close:()V
        //   170: aload           5
        //   172: athrow         
        //   173: astore          5
        //   175: goto            158
        //   178: astore          6
        //   180: goto            119
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  8      40     114    119    Landroid/database/sqlite/SQLiteException;
        //  8      40     151    158    Any
        //  44     67     178    183    Landroid/database/sqlite/SQLiteException;
        //  44     67     173    178    Any
        //  85     95     178    183    Landroid/database/sqlite/SQLiteException;
        //  85     95     173    178    Any
        //  119    134    173    178    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public long zzb(final zzqb.zze zze) throws IOException {
        this.zzjk();
        this.zzjv();
        zzx.zzz(zze);
        zzx.zzcM(zze.appId);
        long zzr;
        ContentValues contentValues;
        try {
            final byte[] array = new byte[zze.getSerializedSize()];
            final zzsn zzE = zzsn.zzE(array);
            zze.writeTo(zzE);
            zzE.zzJo();
            zzr = this.zzCk().zzr(array);
            contentValues = new ContentValues();
            contentValues.put("app_id", zze.appId);
            contentValues.put("metadata_fingerprint", zzr);
            contentValues.put("metadata", array);
            final zze zze2 = this;
            final SQLiteDatabase sqLiteDatabase = zze2.getWritableDatabase();
            final String s = "raw_events_metadata";
            final String s2 = null;
            final ContentValues contentValues2 = contentValues;
            final int n = 4;
            sqLiteDatabase.insertWithOnConflict(s, s2, contentValues2, n);
            return zzr;
        }
        catch (IOException ex) {
            this.zzAo().zzCE().zzj("Data loss. Failed to serialize event metadata", ex);
            throw ex;
        }
        try {
            final zze zze2 = this;
            final SQLiteDatabase sqLiteDatabase = zze2.getWritableDatabase();
            final String s = "raw_events_metadata";
            final String s2 = null;
            final ContentValues contentValues2 = contentValues;
            final int n = 4;
            sqLiteDatabase.insertWithOnConflict(s, s2, contentValues2, n);
            return zzr;
        }
        catch (SQLiteException ex2) {
            this.zzAo().zzCE().zzj("Error storing raw event metadata", ex2);
            throw ex2;
        }
    }
    
    @WorkerThread
    Object zzb(final Cursor cursor, final int n) {
        final int zza = zza(cursor, n);
        switch (zza) {
            default: {
                this.zzAo().zzCE().zzj("Loaded invalid unknown value type, ignoring it", zza);
                return null;
            }
            case 0: {
                this.zzAo().zzCE().zzfg("Loaded invalid null value from database");
                return null;
            }
            case 1: {
                return cursor.getLong(n);
            }
            case 2: {
                return cursor.getFloat(n);
            }
            case 3: {
                return cursor.getString(n);
            }
            case 4: {
                this.zzAo().zzCE().zzfg("Loaded invalid blob type value, ignoring it");
                return null;
            }
        }
    }
    
    @WorkerThread
    void zzb(final String s, final zzpz.zza[] array) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        zzx.zzz(array);
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            this.zzfb(s);
            for (int length = array.length, i = 0; i < length; ++i) {
                this.zza(s, array[i]);
            }
            writableDatabase.setTransactionSuccessful();
        }
        finally {
            writableDatabase.endTransaction();
        }
    }
    
    @WorkerThread
    public void zzd(final String s, final byte[] array) {
        zzx.zzcM(s);
        this.zzjk();
        this.zzjv();
        final ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", array);
        try {
            if (this.getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[] { s }) == 0L) {
                this.zzAo().zzCE().zzfg("Failed to update remote config (got 0)");
            }
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zzj("Error storing remote config", ex);
        }
    }
    
    @WorkerThread
    public List<zzai> zzeX(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aload_1        
        //     3: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //     6: pop            
        //     7: aload_0        
        //     8: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //    11: aload_0        
        //    12: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    15: new             Ljava/util/ArrayList;
        //    18: dup            
        //    19: invokespecial   java/util/ArrayList.<init>:()V
        //    22: astore          4
        //    24: aload_0        
        //    25: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    28: ldc_w           "user_attributes"
        //    31: iconst_3       
        //    32: anewarray       Ljava/lang/String;
        //    35: dup            
        //    36: iconst_0       
        //    37: ldc_w           "name"
        //    40: aastore        
        //    41: dup            
        //    42: iconst_1       
        //    43: ldc_w           "set_timestamp"
        //    46: aastore        
        //    47: dup            
        //    48: iconst_2       
        //    49: ldc_w           "value"
        //    52: aastore        
        //    53: ldc_w           "app_id=?"
        //    56: iconst_1       
        //    57: anewarray       Ljava/lang/String;
        //    60: dup            
        //    61: iconst_0       
        //    62: aload_1        
        //    63: aastore        
        //    64: aconst_null    
        //    65: aconst_null    
        //    66: ldc_w           "rowid"
        //    69: aload_0        
        //    70: invokevirtual   com/google/android/gms/measurement/internal/zze.zzCp:()Lcom/google/android/gms/measurement/internal/zzd;
        //    73: invokevirtual   com/google/android/gms/measurement/internal/zzd.zzBM:()I
        //    76: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //    79: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    82: astore          8
        //    84: aload           8
        //    86: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    91: istore          9
        //    93: iload           9
        //    95: ifne            113
        //    98: aload           8
        //   100: ifnull          110
        //   103: aload           8
        //   105: invokeinterface android/database/Cursor.close:()V
        //   110: aload           4
        //   112: areturn        
        //   113: aload           8
        //   115: iconst_0       
        //   116: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   121: astore          10
        //   123: aload           8
        //   125: iconst_1       
        //   126: invokeinterface android/database/Cursor.getLong:(I)J
        //   131: lstore          11
        //   133: aload_0        
        //   134: aload           8
        //   136: iconst_2       
        //   137: invokevirtual   com/google/android/gms/measurement/internal/zze.zzb:(Landroid/database/Cursor;I)Ljava/lang/Object;
        //   140: astore          13
        //   142: aload           13
        //   144: ifnonnull       189
        //   147: aload_0        
        //   148: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   151: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   154: ldc_w           "Read invalid user property value, ignoring it"
        //   157: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   160: aload           8
        //   162: invokeinterface android/database/Cursor.moveToNext:()Z
        //   167: istore          14
        //   169: iload           14
        //   171: ifne            113
        //   174: aload           8
        //   176: ifnull          186
        //   179: aload           8
        //   181: invokeinterface android/database/Cursor.close:()V
        //   186: aload           4
        //   188: areturn        
        //   189: aload           4
        //   191: new             Lcom/google/android/gms/measurement/internal/zzai;
        //   194: dup            
        //   195: aload_1        
        //   196: aload           10
        //   198: lload           11
        //   200: aload           13
        //   202: invokespecial   com/google/android/gms/measurement/internal/zzai.<init>:(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
        //   205: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   210: pop            
        //   211: goto            160
        //   214: astore          5
        //   216: aload           8
        //   218: astore          6
        //   220: aload_0        
        //   221: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   224: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   227: ldc_w           "Error querying user properties"
        //   230: aload_1        
        //   231: aload           5
        //   233: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   236: aload           6
        //   238: ifnull          248
        //   241: aload           6
        //   243: invokeinterface android/database/Cursor.close:()V
        //   248: aconst_null    
        //   249: areturn        
        //   250: astore          7
        //   252: aload_2        
        //   253: ifnull          262
        //   256: aload_2        
        //   257: invokeinterface android/database/Cursor.close:()V
        //   262: aload           7
        //   264: athrow         
        //   265: astore          7
        //   267: aload           8
        //   269: astore_2       
        //   270: goto            252
        //   273: astore          7
        //   275: aload           6
        //   277: astore_2       
        //   278: goto            252
        //   281: astore          5
        //   283: aconst_null    
        //   284: astore          6
        //   286: goto            220
        //    Signature:
        //  (Ljava/lang/String;)Ljava/util/List<Lcom/google/android/gms/measurement/internal/zzai;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  24     84     281    289    Landroid/database/sqlite/SQLiteException;
        //  24     84     250    252    Any
        //  84     93     214    220    Landroid/database/sqlite/SQLiteException;
        //  84     93     265    273    Any
        //  113    142    214    220    Landroid/database/sqlite/SQLiteException;
        //  113    142    265    273    Any
        //  147    160    214    220    Landroid/database/sqlite/SQLiteException;
        //  147    160    265    273    Any
        //  160    169    214    220    Landroid/database/sqlite/SQLiteException;
        //  160    169    265    273    Any
        //  189    211    214    220    Landroid/database/sqlite/SQLiteException;
        //  189    211    265    273    Any
        //  220    236    273    281    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    public com.google.android.gms.measurement.internal.zza zzeY(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //     4: pop            
        //     5: aload_0        
        //     6: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     9: aload_0        
        //    10: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    13: aload_0        
        //    14: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    17: ldc_w           "apps"
        //    20: bipush          17
        //    22: anewarray       Ljava/lang/String;
        //    25: dup            
        //    26: iconst_0       
        //    27: ldc_w           "app_instance_id"
        //    30: aastore        
        //    31: dup            
        //    32: iconst_1       
        //    33: ldc_w           "gmp_app_id"
        //    36: aastore        
        //    37: dup            
        //    38: iconst_2       
        //    39: ldc_w           "resettable_device_id_hash"
        //    42: aastore        
        //    43: dup            
        //    44: iconst_3       
        //    45: ldc_w           "last_bundle_index"
        //    48: aastore        
        //    49: dup            
        //    50: iconst_4       
        //    51: ldc             "last_bundle_start_timestamp"
        //    53: aastore        
        //    54: dup            
        //    55: iconst_5       
        //    56: ldc_w           "last_bundle_end_timestamp"
        //    59: aastore        
        //    60: dup            
        //    61: bipush          6
        //    63: ldc             "app_version"
        //    65: aastore        
        //    66: dup            
        //    67: bipush          7
        //    69: ldc             "app_store"
        //    71: aastore        
        //    72: dup            
        //    73: bipush          8
        //    75: ldc             "gmp_version"
        //    77: aastore        
        //    78: dup            
        //    79: bipush          9
        //    81: ldc             "dev_cert_hash"
        //    83: aastore        
        //    84: dup            
        //    85: bipush          10
        //    87: ldc             "measurement_enabled"
        //    89: aastore        
        //    90: dup            
        //    91: bipush          11
        //    93: ldc             "day"
        //    95: aastore        
        //    96: dup            
        //    97: bipush          12
        //    99: ldc             "daily_public_events_count"
        //   101: aastore        
        //   102: dup            
        //   103: bipush          13
        //   105: ldc             "daily_events_count"
        //   107: aastore        
        //   108: dup            
        //   109: bipush          14
        //   111: ldc             "daily_conversions_count"
        //   113: aastore        
        //   114: dup            
        //   115: bipush          15
        //   117: ldc             "config_fetched_time"
        //   119: aastore        
        //   120: dup            
        //   121: bipush          16
        //   123: ldc             "failed_config_fetch_time"
        //   125: aastore        
        //   126: ldc_w           "app_id=?"
        //   129: iconst_1       
        //   130: anewarray       Ljava/lang/String;
        //   133: dup            
        //   134: iconst_0       
        //   135: aload_1        
        //   136: aastore        
        //   137: aconst_null    
        //   138: aconst_null    
        //   139: aconst_null    
        //   140: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   143: astore          6
        //   145: aload           6
        //   147: astore          4
        //   149: aload           4
        //   151: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   156: istore          7
        //   158: iload           7
        //   160: ifne            181
        //   163: aload           4
        //   165: ifnull          175
        //   168: aload           4
        //   170: invokeinterface android/database/Cursor.close:()V
        //   175: aconst_null    
        //   176: astore          8
        //   178: aload           8
        //   180: areturn        
        //   181: new             Lcom/google/android/gms/measurement/internal/zza;
        //   184: dup            
        //   185: aload_0        
        //   186: getfield        com/google/android/gms/measurement/internal/zze.zzaTV:Lcom/google/android/gms/measurement/internal/zzw;
        //   189: aload_1        
        //   190: invokespecial   com/google/android/gms/measurement/internal/zza.<init>:(Lcom/google/android/gms/measurement/internal/zzw;Ljava/lang/String;)V
        //   193: astore          8
        //   195: aload           8
        //   197: aload           4
        //   199: iconst_0       
        //   200: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   205: invokevirtual   com/google/android/gms/measurement/internal/zza.zzeM:(Ljava/lang/String;)V
        //   208: aload           8
        //   210: aload           4
        //   212: iconst_1       
        //   213: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   218: invokevirtual   com/google/android/gms/measurement/internal/zza.zzeN:(Ljava/lang/String;)V
        //   221: aload           8
        //   223: aload           4
        //   225: iconst_2       
        //   226: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   231: invokevirtual   com/google/android/gms/measurement/internal/zza.zzeO:(Ljava/lang/String;)V
        //   234: aload           8
        //   236: aload           4
        //   238: iconst_3       
        //   239: invokeinterface android/database/Cursor.getLong:(I)J
        //   244: invokevirtual   com/google/android/gms/measurement/internal/zza.zzS:(J)V
        //   247: aload           8
        //   249: aload           4
        //   251: iconst_4       
        //   252: invokeinterface android/database/Cursor.getLong:(I)J
        //   257: invokevirtual   com/google/android/gms/measurement/internal/zza.zzO:(J)V
        //   260: aload           8
        //   262: aload           4
        //   264: iconst_5       
        //   265: invokeinterface android/database/Cursor.getLong:(I)J
        //   270: invokevirtual   com/google/android/gms/measurement/internal/zza.zzP:(J)V
        //   273: aload           8
        //   275: aload           4
        //   277: bipush          6
        //   279: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   284: invokevirtual   com/google/android/gms/measurement/internal/zza.setAppVersion:(Ljava/lang/String;)V
        //   287: aload           8
        //   289: aload           4
        //   291: bipush          7
        //   293: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   298: invokevirtual   com/google/android/gms/measurement/internal/zza.zzeP:(Ljava/lang/String;)V
        //   301: aload           8
        //   303: aload           4
        //   305: bipush          8
        //   307: invokeinterface android/database/Cursor.getLong:(I)J
        //   312: invokevirtual   com/google/android/gms/measurement/internal/zza.zzQ:(J)V
        //   315: aload           8
        //   317: aload           4
        //   319: bipush          9
        //   321: invokeinterface android/database/Cursor.getLong:(I)J
        //   326: invokevirtual   com/google/android/gms/measurement/internal/zza.zzR:(J)V
        //   329: aload           4
        //   331: bipush          10
        //   333: invokeinterface android/database/Cursor.isNull:(I)Z
        //   338: ifeq            481
        //   341: iconst_1       
        //   342: istore          9
        //   344: goto            567
        //   347: aload           8
        //   349: iload           10
        //   351: invokevirtual   com/google/android/gms/measurement/internal/zza.setMeasurementEnabled:(Z)V
        //   354: aload           8
        //   356: aload           4
        //   358: bipush          11
        //   360: invokeinterface android/database/Cursor.getLong:(I)J
        //   365: invokevirtual   com/google/android/gms/measurement/internal/zza.zzV:(J)V
        //   368: aload           8
        //   370: aload           4
        //   372: bipush          12
        //   374: invokeinterface android/database/Cursor.getLong:(I)J
        //   379: invokevirtual   com/google/android/gms/measurement/internal/zza.zzW:(J)V
        //   382: aload           8
        //   384: aload           4
        //   386: bipush          13
        //   388: invokeinterface android/database/Cursor.getLong:(I)J
        //   393: invokevirtual   com/google/android/gms/measurement/internal/zza.zzX:(J)V
        //   396: aload           8
        //   398: aload           4
        //   400: bipush          14
        //   402: invokeinterface android/database/Cursor.getLong:(I)J
        //   407: invokevirtual   com/google/android/gms/measurement/internal/zza.zzY:(J)V
        //   410: aload           8
        //   412: aload           4
        //   414: bipush          15
        //   416: invokeinterface android/database/Cursor.getLong:(I)J
        //   421: invokevirtual   com/google/android/gms/measurement/internal/zza.zzT:(J)V
        //   424: aload           8
        //   426: aload           4
        //   428: bipush          16
        //   430: invokeinterface android/database/Cursor.getLong:(I)J
        //   435: invokevirtual   com/google/android/gms/measurement/internal/zza.zzU:(J)V
        //   438: aload           8
        //   440: invokevirtual   com/google/android/gms/measurement/internal/zza.zzBi:()V
        //   443: aload           4
        //   445: invokeinterface android/database/Cursor.moveToNext:()Z
        //   450: ifeq            466
        //   453: aload_0        
        //   454: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   457: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   460: ldc_w           "Got multiple records for app, expected one"
        //   463: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   466: aload           4
        //   468: ifnull          178
        //   471: aload           4
        //   473: invokeinterface android/database/Cursor.close:()V
        //   478: aload           8
        //   480: areturn        
        //   481: aload           4
        //   483: bipush          10
        //   485: invokeinterface android/database/Cursor.getInt:(I)I
        //   490: istore          11
        //   492: iload           11
        //   494: istore          9
        //   496: goto            567
        //   499: iconst_0       
        //   500: istore          10
        //   502: goto            347
        //   505: astore          5
        //   507: aconst_null    
        //   508: astore          4
        //   510: aload_0        
        //   511: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   514: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   517: ldc_w           "Error querying app"
        //   520: aload_1        
        //   521: aload           5
        //   523: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   526: aload           4
        //   528: ifnull          538
        //   531: aload           4
        //   533: invokeinterface android/database/Cursor.close:()V
        //   538: aconst_null    
        //   539: areturn        
        //   540: astore_3       
        //   541: aconst_null    
        //   542: astore          4
        //   544: aload           4
        //   546: ifnull          556
        //   549: aload           4
        //   551: invokeinterface android/database/Cursor.close:()V
        //   556: aload_3        
        //   557: athrow         
        //   558: astore_3       
        //   559: goto            544
        //   562: astore          5
        //   564: goto            510
        //   567: iload           9
        //   569: ifeq            499
        //   572: iconst_1       
        //   573: istore          10
        //   575: goto            347
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  13     145    505    510    Landroid/database/sqlite/SQLiteException;
        //  13     145    540    544    Any
        //  149    158    562    567    Landroid/database/sqlite/SQLiteException;
        //  149    158    558    562    Any
        //  181    341    562    567    Landroid/database/sqlite/SQLiteException;
        //  181    341    558    562    Any
        //  347    466    562    567    Landroid/database/sqlite/SQLiteException;
        //  347    466    558    562    Any
        //  481    492    562    567    Landroid/database/sqlite/SQLiteException;
        //  481    492    558    562    Any
        //  510    526    558    562    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public long zzeZ(final String s) {
        zzx.zzcM(s);
        this.zzjk();
        this.zzjv();
        try {
            return this.getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[] { s, String.valueOf(this.zzCp().zzeW(s)) });
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zzj("Error deleting over the limit events", ex);
            return 0L;
        }
    }
    
    @WorkerThread
    public byte[] zzfa(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //     4: pop            
        //     5: aload_0        
        //     6: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     9: aload_0        
        //    10: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    13: aload_0        
        //    14: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    17: ldc_w           "apps"
        //    20: iconst_1       
        //    21: anewarray       Ljava/lang/String;
        //    24: dup            
        //    25: iconst_0       
        //    26: ldc             "remote_config"
        //    28: aastore        
        //    29: ldc_w           "app_id=?"
        //    32: iconst_1       
        //    33: anewarray       Ljava/lang/String;
        //    36: dup            
        //    37: iconst_0       
        //    38: aload_1        
        //    39: aastore        
        //    40: aconst_null    
        //    41: aconst_null    
        //    42: aconst_null    
        //    43: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    46: astore          6
        //    48: aload           6
        //    50: astore          4
        //    52: aload           4
        //    54: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    59: istore          7
        //    61: iload           7
        //    63: ifne            84
        //    66: aload           4
        //    68: ifnull          78
        //    71: aload           4
        //    73: invokeinterface android/database/Cursor.close:()V
        //    78: aconst_null    
        //    79: astore          8
        //    81: aload           8
        //    83: areturn        
        //    84: aload           4
        //    86: iconst_0       
        //    87: invokeinterface android/database/Cursor.getBlob:(I)[B
        //    92: astore          8
        //    94: aload           4
        //    96: invokeinterface android/database/Cursor.moveToNext:()Z
        //   101: ifeq            117
        //   104: aload_0        
        //   105: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   108: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   111: ldc_w           "Got multiple records for app config, expected one"
        //   114: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   117: aload           4
        //   119: ifnull          81
        //   122: aload           4
        //   124: invokeinterface android/database/Cursor.close:()V
        //   129: aload           8
        //   131: areturn        
        //   132: astore          5
        //   134: aconst_null    
        //   135: astore          4
        //   137: aload_0        
        //   138: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   141: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   144: ldc_w           "Error querying remote config"
        //   147: aload_1        
        //   148: aload           5
        //   150: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   153: aload           4
        //   155: ifnull          165
        //   158: aload           4
        //   160: invokeinterface android/database/Cursor.close:()V
        //   165: aconst_null    
        //   166: areturn        
        //   167: astore_3       
        //   168: aconst_null    
        //   169: astore          4
        //   171: aload           4
        //   173: ifnull          183
        //   176: aload           4
        //   178: invokeinterface android/database/Cursor.close:()V
        //   183: aload_3        
        //   184: athrow         
        //   185: astore_3       
        //   186: goto            171
        //   189: astore          5
        //   191: goto            137
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  13     48     132    137    Landroid/database/sqlite/SQLiteException;
        //  13     48     167    171    Any
        //  52     61     189    194    Landroid/database/sqlite/SQLiteException;
        //  52     61     185    189    Any
        //  84     117    189    194    Landroid/database/sqlite/SQLiteException;
        //  84     117    185    189    Any
        //  137    153    185    189    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @WorkerThread
    void zzfb(final String s) {
        this.zzjv();
        this.zzjk();
        zzx.zzcM(s);
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=?", new String[] { s });
        writableDatabase.delete("event_filters", "app_id=?", new String[] { s });
    }
    
    public void zzfc(final String s) {
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        try {
            writableDatabase.execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", (Object[])new String[] { s, s });
        }
        catch (SQLiteException ex) {
            this.zzAo().zzCE().zzj("Failed to remove unused event metadata", ex);
        }
    }
    
    public long zzfd(final String s) {
        zzx.zzcM(s);
        return this.zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[] { s }, 0L);
    }
    
    @Override
    protected void zziJ() {
    }
    
    @WorkerThread
    public List<Pair<zzqb.zze, Long>> zzn(final String p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore          4
        //     3: aload_0        
        //     4: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjk:()V
        //     7: aload_0        
        //     8: invokevirtual   com/google/android/gms/measurement/internal/zze.zzjv:()V
        //    11: iload_2        
        //    12: ifle            124
        //    15: iload           4
        //    17: istore          5
        //    19: iload           5
        //    21: invokestatic    com/google/android/gms/common/internal/zzx.zzac:(Z)V
        //    24: iload_3        
        //    25: ifle            130
        //    28: iload           4
        //    30: invokestatic    com/google/android/gms/common/internal/zzx.zzac:(Z)V
        //    33: aload_1        
        //    34: invokestatic    com/google/android/gms/common/internal/zzx.zzcM:(Ljava/lang/String;)Ljava/lang/String;
        //    37: pop            
        //    38: aload_0        
        //    39: invokevirtual   com/google/android/gms/measurement/internal/zze.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    42: ldc_w           "queue"
        //    45: iconst_2       
        //    46: anewarray       Ljava/lang/String;
        //    49: dup            
        //    50: iconst_0       
        //    51: ldc_w           "rowid"
        //    54: aastore        
        //    55: dup            
        //    56: iconst_1       
        //    57: ldc_w           "data"
        //    60: aastore        
        //    61: ldc_w           "app_id=?"
        //    64: iconst_1       
        //    65: anewarray       Ljava/lang/String;
        //    68: dup            
        //    69: iconst_0       
        //    70: aload_1        
        //    71: aastore        
        //    72: aconst_null    
        //    73: aconst_null    
        //    74: ldc_w           "rowid"
        //    77: iload_2        
        //    78: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //    81: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    84: astore          13
        //    86: aload           13
        //    88: astore          10
        //    90: aload           10
        //    92: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    97: ifne            136
        //   100: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   103: astore          27
        //   105: aload           27
        //   107: astore          12
        //   109: aload           10
        //   111: ifnull          121
        //   114: aload           10
        //   116: invokeinterface android/database/Cursor.close:()V
        //   121: aload           12
        //   123: areturn        
        //   124: iconst_0       
        //   125: istore          5
        //   127: goto            19
        //   130: iconst_0       
        //   131: istore          4
        //   133: goto            28
        //   136: new             Ljava/util/ArrayList;
        //   139: dup            
        //   140: invokespecial   java/util/ArrayList.<init>:()V
        //   143: astore          12
        //   145: iconst_0       
        //   146: istore          14
        //   148: aload           10
        //   150: iconst_0       
        //   151: invokeinterface android/database/Cursor.getLong:(I)J
        //   156: lstore          15
        //   158: aload           10
        //   160: iconst_1       
        //   161: invokeinterface android/database/Cursor.getBlob:(I)[B
        //   166: astore          19
        //   168: aload_0        
        //   169: invokevirtual   com/google/android/gms/measurement/internal/zze.zzCk:()Lcom/google/android/gms/measurement/internal/zzaj;
        //   172: aload           19
        //   174: invokevirtual   com/google/android/gms/measurement/internal/zzaj.zzp:([B)[B
        //   177: astore          20
        //   179: aload           12
        //   181: invokeinterface java/util/List.isEmpty:()Z
        //   186: ifne            263
        //   189: aload           20
        //   191: arraylength    
        //   192: istore          26
        //   194: iload           26
        //   196: iload           14
        //   198: iadd           
        //   199: iload_3        
        //   200: if_icmple       263
        //   203: aload           10
        //   205: ifnull          121
        //   208: aload           10
        //   210: invokeinterface android/database/Cursor.close:()V
        //   215: aload           12
        //   217: areturn        
        //   218: astore          17
        //   220: aload_0        
        //   221: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   224: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   227: ldc_w           "Failed to unzip queued bundle"
        //   230: aload_1        
        //   231: aload           17
        //   233: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   236: iload           14
        //   238: istore          18
        //   240: aload           10
        //   242: invokeinterface android/database/Cursor.moveToNext:()Z
        //   247: ifeq            203
        //   250: iload           18
        //   252: iload_3        
        //   253: if_icmpgt       203
        //   256: iload           18
        //   258: istore          14
        //   260: goto            148
        //   263: aload           20
        //   265: invokestatic    com/google/android/gms/internal/zzsm.zzD:([B)Lcom/google/android/gms/internal/zzsm;
        //   268: astore          21
        //   270: new             Lcom/google/android/gms/internal/zzqb$zze;
        //   273: dup            
        //   274: invokespecial   com/google/android/gms/internal/zzqb$zze.<init>:()V
        //   277: astore          22
        //   279: aload           22
        //   281: aload           21
        //   283: invokevirtual   com/google/android/gms/internal/zzqb$zze.zzG:(Lcom/google/android/gms/internal/zzsm;)Lcom/google/android/gms/internal/zzqb$zze;
        //   286: pop            
        //   287: iload           14
        //   289: aload           20
        //   291: arraylength    
        //   292: iadd           
        //   293: istore          18
        //   295: aload           12
        //   297: aload           22
        //   299: lload           15
        //   301: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   304: invokestatic    android/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Landroid/util/Pair;
        //   307: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   312: pop            
        //   313: goto            240
        //   316: astore          7
        //   318: aload           10
        //   320: astore          8
        //   322: aload_0        
        //   323: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   326: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   329: ldc_w           "Error querying bundles"
        //   332: aload_1        
        //   333: aload           7
        //   335: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   338: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   341: astore          11
        //   343: aload           11
        //   345: astore          12
        //   347: aload           8
        //   349: ifnull          121
        //   352: aload           8
        //   354: invokeinterface android/database/Cursor.close:()V
        //   359: aload           12
        //   361: areturn        
        //   362: astore          23
        //   364: aload_0        
        //   365: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   368: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   371: ldc_w           "Failed to merge queued bundle"
        //   374: aload_1        
        //   375: aload           23
        //   377: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   380: iload           14
        //   382: istore          18
        //   384: goto            240
        //   387: astore          9
        //   389: aconst_null    
        //   390: astore          10
        //   392: aload           10
        //   394: ifnull          404
        //   397: aload           10
        //   399: invokeinterface android/database/Cursor.close:()V
        //   404: aload           9
        //   406: athrow         
        //   407: astore          9
        //   409: goto            392
        //   412: astore          9
        //   414: aload           8
        //   416: astore          10
        //   418: goto            392
        //   421: astore          7
        //   423: aconst_null    
        //   424: astore          8
        //   426: goto            322
        //    Signature:
        //  (Ljava/lang/String;II)Ljava/util/List<Landroid/util/Pair<Lcom/google/android/gms/internal/zzqb$zze;Ljava/lang/Long;>;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  38     86     421    429    Landroid/database/sqlite/SQLiteException;
        //  38     86     387    392    Any
        //  90     105    316    322    Landroid/database/sqlite/SQLiteException;
        //  90     105    407    412    Any
        //  136    145    316    322    Landroid/database/sqlite/SQLiteException;
        //  136    145    407    412    Any
        //  148    158    316    322    Landroid/database/sqlite/SQLiteException;
        //  148    158    407    412    Any
        //  158    179    218    240    Ljava/io/IOException;
        //  158    179    316    322    Landroid/database/sqlite/SQLiteException;
        //  158    179    407    412    Any
        //  179    194    316    322    Landroid/database/sqlite/SQLiteException;
        //  179    194    407    412    Any
        //  220    236    316    322    Landroid/database/sqlite/SQLiteException;
        //  220    236    407    412    Any
        //  240    250    316    322    Landroid/database/sqlite/SQLiteException;
        //  240    250    407    412    Any
        //  263    279    316    322    Landroid/database/sqlite/SQLiteException;
        //  263    279    407    412    Any
        //  279    287    362    387    Ljava/io/IOException;
        //  279    287    316    322    Landroid/database/sqlite/SQLiteException;
        //  279    287    407    412    Any
        //  287    313    316    322    Landroid/database/sqlite/SQLiteException;
        //  287    313    407    412    Any
        //  322    343    412    421    Any
        //  364    380    316    322    Landroid/database/sqlite/SQLiteException;
        //  364    380    407    412    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void zzz(final List<Long> list) {
        zzx.zzz(list);
        this.zzjk();
        this.zzjv();
        final StringBuilder sb = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append((long)list.get(i));
        }
        sb.append(")");
        final int delete = this.getWritableDatabase().delete("raw_events", sb.toString(), (String[])null);
        if (delete != list.size()) {
            this.zzAo().zzCE().zze("Deleted fewer rows from raw events table than expected", delete, list.size());
        }
    }
    
    public static class zza
    {
        long zzaVE;
        long zzaVF;
        long zzaVG;
    }
    
    interface zzb
    {
        boolean zza(final long p0, final zzqb.zzb p1);
        
        void zzc(final zzqb.zze p0);
    }
    
    private class zzc extends SQLiteOpenHelper
    {
        zzc(final Context context, final String s) {
            super(context, s, (SQLiteDatabase$CursorFactory)null, 1);
        }
        
        @WorkerThread
        private void zza(final SQLiteDatabase sqLiteDatabase, final String s, final String s2, final String s3, final Map<String, String> map) throws SQLiteException {
            if (!this.zza(sqLiteDatabase, s)) {
                sqLiteDatabase.execSQL(s2);
            }
            try {
                this.zza(sqLiteDatabase, s, s3, map);
            }
            catch (SQLiteException ex) {
                zze.this.zzAo().zzCE().zzj("Failed to verify columns on table that was just created", s);
                throw ex;
            }
        }
        
        @WorkerThread
        private void zza(final SQLiteDatabase sqLiteDatabase, final String s, final String s2, final Map<String, String> map) throws SQLiteException {
            final Set<String> zzb = this.zzb(sqLiteDatabase, s);
            for (final String s3 : s2.split(",")) {
                if (!zzb.remove(s3)) {
                    throw new SQLiteException("Table " + s + " is missing required column: " + s3);
                }
            }
            if (map != null) {
                for (final Map.Entry<String, String> entry : map.entrySet()) {
                    if (!zzb.remove(entry.getKey())) {
                        sqLiteDatabase.execSQL((String)entry.getValue());
                    }
                }
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Table " + s + " table has extra columns");
            }
        }
        
        @WorkerThread
        private boolean zza(final SQLiteDatabase p0, final String p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aconst_null    
            //     1: astore_3       
            //     2: aload_1        
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
            //    22: aload_2        
            //    23: aastore        
            //    24: aconst_null    
            //    25: aconst_null    
            //    26: aconst_null    
            //    27: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
            //    30: astore          7
            //    32: aload           7
            //    34: astore          6
            //    36: aload           6
            //    38: invokeinterface android/database/Cursor.moveToFirst:()Z
            //    43: istore          8
            //    45: aload           6
            //    47: ifnull          57
            //    50: aload           6
            //    52: invokeinterface android/database/Cursor.close:()V
            //    57: iload           8
            //    59: ireturn        
            //    60: astore          5
            //    62: aconst_null    
            //    63: astore          6
            //    65: aload_0        
            //    66: getfield        com/google/android/gms/measurement/internal/zze$zzc.zzaVH:Lcom/google/android/gms/measurement/internal/zze;
            //    69: invokevirtual   com/google/android/gms/measurement/internal/zze.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
            //    72: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
            //    75: ldc             "Error querying for table"
            //    77: aload_2        
            //    78: aload           5
            //    80: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
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
            //   114: aload           6
            //   116: astore_3       
            //   117: goto            99
            //   120: astore          5
            //   122: goto            65
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                     
            //  -----  -----  -----  -----  -----------------------------------------
            //  2      32     60     65     Landroid/database/sqlite/SQLiteException;
            //  2      32     97     99     Any
            //  36     45     120    125    Landroid/database/sqlite/SQLiteException;
            //  36     45     112    120    Any
            //  65     83     112    120    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        @WorkerThread
        private Set<String> zzb(final SQLiteDatabase sqLiteDatabase, final String s) {
            final HashSet<Object> set = new HashSet<Object>();
            final Cursor rawQuery = sqLiteDatabase.rawQuery("SELECT * FROM " + s + " LIMIT 0", (String[])null);
            try {
                Collections.addAll(set, rawQuery.getColumnNames());
                return (Set<String>)set;
            }
            finally {
                rawQuery.close();
            }
        }
        
        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            if (!zze.this.zzaVD.zzv(zze.this.zzCp().zzBN())) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            }
            catch (SQLiteException ex2) {
                zze.this.zzaVD.start();
                zze.this.zzAo().zzCE().zzfg("Opening the database failed, dropping and recreating it");
                zze.this.getContext().getDatabasePath(zze.this.zzjQ()).delete();
                try {
                    final SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zze.this.zzaVD.clear();
                    return writableDatabase;
                }
                catch (SQLiteException ex) {
                    zze.this.zzAo().zzCE().zzj("Failed to open freshly created database", ex);
                    throw ex;
                }
            }
        }
        
        @WorkerThread
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            if (Build$VERSION.SDK_INT >= 9) {
                final File file = new File(sqLiteDatabase.getPath());
                file.setReadable(false, false);
                file.setWritable(false, false);
                file.setReadable(true, true);
                file.setWritable(true, true);
            }
        }
        
        @WorkerThread
        public void onOpen(final SQLiteDatabase sqLiteDatabase) {
            Label_0029: {
                if (Build$VERSION.SDK_INT >= 15) {
                    break Label_0029;
                }
                final Cursor rawQuery = sqLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[])null);
                try {
                    rawQuery.moveToFirst();
                    rawQuery.close();
                    this.zza(sqLiteDatabase, "events", "CREATE TABLE IF NOT EXISTS events ( app_id TEXT NOT NULL, name TEXT NOT NULL, lifetime_count INTEGER NOT NULL, current_bundle_count INTEGER NOT NULL, last_fire_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,lifetime_count,current_bundle_count,last_fire_timestamp", null);
                    this.zza(sqLiteDatabase, "user_attributes", "CREATE TABLE IF NOT EXISTS user_attributes ( app_id TEXT NOT NULL, name TEXT NOT NULL, set_timestamp INTEGER NOT NULL, value BLOB NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,set_timestamp,value", null);
                    this.zza(sqLiteDatabase, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zze.zzaVB);
                    this.zza(sqLiteDatabase, "queue", "CREATE TABLE IF NOT EXISTS queue ( app_id TEXT NOT NULL, bundle_end_timestamp INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,bundle_end_timestamp,data", null);
                    this.zza(sqLiteDatabase, "raw_events_metadata", "CREATE TABLE IF NOT EXISTS raw_events_metadata ( app_id TEXT NOT NULL, metadata_fingerprint INTEGER NOT NULL, metadata BLOB NOT NULL, PRIMARY KEY (app_id, metadata_fingerprint));", "app_id,metadata_fingerprint,metadata", null);
                    this.zza(sqLiteDatabase, "raw_events", "CREATE TABLE IF NOT EXISTS raw_events ( app_id TEXT NOT NULL, name TEXT NOT NULL, timestamp INTEGER NOT NULL, metadata_fingerprint INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,name,timestamp,metadata_fingerprint,data", null);
                    this.zza(sqLiteDatabase, "event_filters", "CREATE TABLE IF NOT EXISTS event_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, event_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, event_name, audience_id, filter_id));", "app_id,audience_id,filter_id,event_name,data", null);
                    this.zza(sqLiteDatabase, "property_filters", "CREATE TABLE IF NOT EXISTS property_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, property_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, property_name, audience_id, filter_id));", "app_id,audience_id,filter_id,property_name,data", null);
                    this.zza(sqLiteDatabase, "audience_filter_values", "CREATE TABLE IF NOT EXISTS audience_filter_values ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, current_results BLOB, PRIMARY KEY (app_id, audience_id));", "app_id,audience_id,current_results", null);
                }
                finally {
                    rawQuery.close();
                }
            }
        }
        
        @WorkerThread
        public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        }
    }
}
