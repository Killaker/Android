package com.google.android.gms.measurement.internal;

import android.content.*;
import android.database.sqlite.*;
import android.support.annotation.*;
import java.util.*;
import android.database.*;
import android.os.*;
import java.io.*;

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
        if (!zze.zza(zze.this).zzv(zze.this.zzCp().zzBN())) {
            throw new SQLiteException("Database open failed");
        }
        try {
            return super.getWritableDatabase();
        }
        catch (SQLiteException ex2) {
            zze.zza(zze.this).start();
            zze.this.zzAo().zzCE().zzfg("Opening the database failed, dropping and recreating it");
            zze.this.getContext().getDatabasePath(zze.zzb(zze.this)).delete();
            try {
                final SQLiteDatabase writableDatabase = super.getWritableDatabase();
                zze.zza(zze.this).clear();
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
                this.zza(sqLiteDatabase, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zze.zzCx());
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
