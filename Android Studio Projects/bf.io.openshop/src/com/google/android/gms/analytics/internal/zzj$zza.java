package com.google.android.gms.analytics.internal;

import android.content.*;
import android.database.sqlite.*;
import java.util.*;
import android.database.*;
import android.os.*;

class zza extends SQLiteOpenHelper
{
    zza(final Context context, final String s) {
        super(context, s, (SQLiteDatabase$CursorFactory)null, 1);
    }
    
    private void zza(final SQLiteDatabase sqLiteDatabase) {
        int n = 1;
        final Set<String> zzb = this.zzb(sqLiteDatabase, "hits2");
        final String[] array = new String[4];
        array[0] = "hit_id";
        array[n] = "hit_string";
        array[2] = "hit_time";
        array[3] = "hit_url";
        for (final String s : array) {
            if (!zzb.remove(s)) {
                throw new SQLiteException("Database hits2 is missing required column: " + s);
            }
        }
        if (zzb.remove("hit_app_id")) {
            n = 0;
        }
        if (!zzb.isEmpty()) {
            throw new SQLiteException("Database hits2 has extra columns");
        }
        if (n != 0) {
            sqLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
        }
    }
    
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
        //    66: getfield        com/google/android/gms/analytics/internal/zzj$zza.zzQW:Lcom/google/android/gms/analytics/internal/zzj;
        //    69: ldc             "Error querying for table"
        //    71: aload_2        
        //    72: aload           5
        //    74: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzc:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    77: aload           6
        //    79: ifnull          89
        //    82: aload           6
        //    84: invokeinterface android/database/Cursor.close:()V
        //    89: iconst_0       
        //    90: ireturn        
        //    91: astore          4
        //    93: aload_3        
        //    94: ifnull          103
        //    97: aload_3        
        //    98: invokeinterface android/database/Cursor.close:()V
        //   103: aload           4
        //   105: athrow         
        //   106: astore          4
        //   108: aload           6
        //   110: astore_3       
        //   111: goto            93
        //   114: astore          5
        //   116: goto            65
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  2      32     60     65     Landroid/database/sqlite/SQLiteException;
        //  2      32     91     93     Any
        //  36     45     114    119    Landroid/database/sqlite/SQLiteException;
        //  36     45     106    114    Any
        //  65     77     106    114    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private Set<String> zzb(final SQLiteDatabase sqLiteDatabase, final String s) {
        final HashSet<String> set = new HashSet<String>();
        final Cursor rawQuery = sqLiteDatabase.rawQuery("SELECT * FROM " + s + " LIMIT 0", (String[])null);
        try {
            final String[] columnNames = rawQuery.getColumnNames();
            for (int i = 0; i < columnNames.length; ++i) {
                set.add(columnNames[i]);
            }
            return set;
        }
        finally {
            rawQuery.close();
        }
    }
    
    private void zzb(final SQLiteDatabase sqLiteDatabase) {
        int i = 0;
        final Set<String> zzb = this.zzb(sqLiteDatabase, "properties");
        for (String[] array = { "app_uid", "cid", "tid", "params", "adid", "hits_count" }; i < array.length; ++i) {
            final String s = array[i];
            if (!zzb.remove(s)) {
                throw new SQLiteException("Database properties is missing required column: " + s);
            }
        }
        if (!zzb.isEmpty()) {
            throw new SQLiteException("Database properties table has extra columns");
        }
    }
    
    public SQLiteDatabase getWritableDatabase() {
        if (!zzj.zza(zzj.this).zzv(3600000L)) {
            throw new SQLiteException("Database open failed");
        }
        try {
            return super.getWritableDatabase();
        }
        catch (SQLiteException ex2) {
            zzj.zza(zzj.this).start();
            zzj.this.zzbh("Opening the database failed, dropping the table and recreating it");
            zzj.this.getContext().getDatabasePath(zzj.zzb(zzj.this)).delete();
            try {
                final SQLiteDatabase writableDatabase = super.getWritableDatabase();
                zzj.zza(zzj.this).clear();
                return writableDatabase;
            }
            catch (SQLiteException ex) {
                zzj.this.zze("Failed to open freshly created database", ex);
                throw ex;
            }
        }
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        zzx.zzbo(sqLiteDatabase.getPath());
    }
    
    public void onOpen(final SQLiteDatabase sqLiteDatabase) {
        while (true) {
            if (Build$VERSION.SDK_INT < 15) {
                while (true) {
                    final Cursor rawQuery = sqLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[])null);
                    while (true) {
                        try {
                            rawQuery.moveToFirst();
                            rawQuery.close();
                            if (!this.zza(sqLiteDatabase, "hits2")) {
                                sqLiteDatabase.execSQL(zzj.zzjR());
                                if (!this.zza(sqLiteDatabase, "properties")) {
                                    sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
                                    return;
                                }
                                break;
                            }
                        }
                        finally {
                            rawQuery.close();
                        }
                        this.zza(sqLiteDatabase);
                        continue;
                    }
                }
                this.zzb(sqLiteDatabase);
                return;
            }
            continue;
        }
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
    }
}
