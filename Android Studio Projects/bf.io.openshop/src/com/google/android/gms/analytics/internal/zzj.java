package com.google.android.gms.analytics.internal;

import java.io.*;
import com.google.android.gms.common.internal.*;
import android.net.*;
import android.database.*;
import android.text.*;
import com.google.android.gms.internal.*;
import java.net.*;
import android.content.*;
import android.database.sqlite.*;
import java.util.*;
import android.os.*;

class zzj extends zzd implements Closeable
{
    private static final String zzQR;
    private static final String zzQS;
    private final zza zzQT;
    private final zzaj zzQU;
    private final zzaj zzQV;
    
    static {
        zzQR = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
        zzQS = String.format("SELECT MAX(%s) FROM %s WHERE 1;", "hit_time", "hits2");
    }
    
    zzj(final zzf zzf) {
        super(zzf);
        this.zzQU = new zzaj(this.zzjl());
        this.zzQV = new zzaj(this.zzjl());
        this.zzQT = new zza(zzf.getContext(), this.zzjQ());
    }
    
    private static String zzI(final Map<String, String> map) {
        zzx.zzz(map);
        final Uri$Builder uri$Builder = new Uri$Builder();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            uri$Builder.appendQueryParameter((String)entry.getKey(), (String)entry.getValue());
        }
        String encodedQuery = uri$Builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        return encodedQuery;
    }
    
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
            this.zzd("Database error", s, ex);
            throw ex;
        }
        finally {
            if (rawQuery != null) {
                rawQuery.close();
            }
        }
    }
    
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
                this.zzd("Database error", s, ex);
                throw ex;
            }
            finally {
                if (rawQuery != null) {
                    rawQuery.close();
                }
            }
        }
    }
    
    private String zzd(final zzab zzab) {
        if (zzab.zzlt()) {
            return this.zzjn().zzkF();
        }
        return this.zzjn().zzkG();
    }
    
    private static String zze(final zzab zzab) {
        zzx.zzz(zzab);
        final Uri$Builder uri$Builder = new Uri$Builder();
        for (final Map.Entry<String, String> entry : zzab.zzn().entrySet()) {
            final String s = entry.getKey();
            if (!"ht".equals(s) && !"qt".equals(s) && !"AppUID".equals(s)) {
                uri$Builder.appendQueryParameter(s, (String)entry.getValue());
            }
        }
        String encodedQuery = uri$Builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        return encodedQuery;
    }
    
    private void zzjP() {
        final int zzkP = this.zzjn().zzkP();
        final long zzjG = this.zzjG();
        if (zzjG > zzkP - 1) {
            final List<Long> zzo = this.zzo(1L + (zzjG - zzkP));
            this.zzd("Store full, deleting hits to make room, count", zzo.size());
            this.zzo(zzo);
        }
    }
    
    private String zzjQ() {
        if (!this.zzjn().zzkr()) {
            return this.zzjn().zzkR();
        }
        if (this.zzjn().zzks()) {
            return this.zzjn().zzkR();
        }
        return this.zzjn().zzkS();
    }
    
    public void beginTransaction() {
        this.zzjv();
        this.getWritableDatabase().beginTransaction();
    }
    
    @Override
    public void close() {
        try {
            this.zzQT.close();
        }
        catch (SQLiteException ex) {
            this.zze("Sql error closing database", ex);
        }
        catch (IllegalStateException ex2) {
            this.zze("Error closing database", ex2);
        }
    }
    
    public void endTransaction() {
        this.zzjv();
        this.getWritableDatabase().endTransaction();
    }
    
    SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzQT.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            this.zzd("Error opening database", ex);
            throw ex;
        }
    }
    
    boolean isEmpty() {
        return this.zzjG() == 0L;
    }
    
    public void setTransactionSuccessful() {
        this.zzjv();
        this.getWritableDatabase().setTransactionSuccessful();
    }
    
    public long zza(final long n, final String s, final String s2) {
        zzx.zzcM(s);
        zzx.zzcM(s2);
        this.zzjv();
        this.zzjk();
        return this.zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[] { String.valueOf(n), s, s2 }, 0L);
    }
    
    public void zza(final long n, final String s) {
        zzx.zzcM(s);
        this.zzjv();
        this.zzjk();
        final int delete = this.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[] { String.valueOf(n), s });
        if (delete > 0) {
            this.zza("Deleted property records", delete);
        }
    }
    
    public void zzb(final zzh zzh) {
        zzx.zzz(zzh);
        this.zzjv();
        this.zzjk();
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        final String zzI = zzI(zzh.zzn());
        final ContentValues contentValues = new ContentValues();
        contentValues.put("app_uid", zzh.zzjD());
        contentValues.put("cid", zzh.getClientId());
        contentValues.put("tid", zzh.zzjE());
        Label_0149: {
            if (!zzh.zzjF()) {
                break Label_0149;
            }
            int n = 1;
            while (true) {
                contentValues.put("adid", n);
                contentValues.put("hits_count", zzh.zzjG());
                contentValues.put("params", zzI);
                try {
                    if (writableDatabase.insertWithOnConflict("properties", (String)null, contentValues, 5) == -1L) {
                        this.zzbh("Failed to insert/update a property (got -1)");
                    }
                    return;
                    n = 0;
                }
                catch (SQLiteException ex) {
                    this.zze("Error storing a property", ex);
                }
            }
        }
    }
    
    Map<String, String> zzbi(String string) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            return new HashMap<String, String>(0);
        }
        try {
            if (!string.startsWith("?")) {
                string = "?" + string;
            }
            return zzmz.zza(new URI(string), "UTF-8");
        }
        catch (URISyntaxException ex) {
            this.zze("Error parsing hit parameters", ex);
            return new HashMap<String, String>(0);
        }
    }
    
    Map<String, String> zzbj(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return new HashMap<String, String>(0);
        }
        try {
            return zzmz.zza(new URI("?" + s), "UTF-8");
        }
        catch (URISyntaxException ex) {
            this.zze("Error parsing property parameters", ex);
            return new HashMap<String, String>(0);
        }
    }
    
    public void zzc(final zzab zzab) {
        zzx.zzz(zzab);
        this.zzjk();
        this.zzjv();
        final String zze = zze(zzab);
        if (zze.length() > 8192) {
            this.zzjm().zza(zzab, "Hit length exceeds the maximum allowed size");
            return;
        }
        this.zzjP();
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", zze);
        contentValues.put("hit_time", zzab.zzlr());
        contentValues.put("hit_app_id", zzab.zzlp());
        contentValues.put("hit_url", this.zzd(zzab));
        long insert;
        try {
            insert = writableDatabase.insert("hits2", (String)null, contentValues);
            if (insert == -1L) {
                this.zzbh("Failed to insert a hit (got -1)");
                return;
            }
        }
        catch (SQLiteException ex) {
            this.zze("Error storing a hit", ex);
            return;
        }
        this.zzb("Hit saved to database. db-id, hit", insert, zzab);
    }
    
    @Override
    protected void zziJ() {
    }
    
    public long zzjG() {
        this.zzjk();
        this.zzjv();
        return this.zzb("SELECT COUNT(*) FROM hits2", null);
    }
    
    public void zzjL() {
        this.zzjk();
        this.zzjv();
        this.getWritableDatabase().delete("hits2", (String)null, (String[])null);
    }
    
    public void zzjM() {
        this.zzjk();
        this.zzjv();
        this.getWritableDatabase().delete("properties", (String)null, (String[])null);
    }
    
    public int zzjN() {
        this.zzjk();
        this.zzjv();
        if (!this.zzQU.zzv(86400000L)) {
            return 0;
        }
        this.zzQU.start();
        this.zzbd("Deleting stale hits (if any)");
        final int delete = this.getWritableDatabase().delete("hits2", "hit_time < ?", new String[] { Long.toString(this.zzjl().currentTimeMillis() - 2592000000L) });
        this.zza("Deleted stale hits, count", delete);
        return delete;
    }
    
    public long zzjO() {
        this.zzjk();
        this.zzjv();
        return this.zza(zzj.zzQS, null, 0L);
    }
    
    public List<Long> zzo(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_0        
        //     3: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzjk:()V
        //     6: aload_0        
        //     7: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzjv:()V
        //    10: lload_1        
        //    11: lconst_0       
        //    12: lcmp           
        //    13: ifgt            20
        //    16: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    19: areturn        
        //    20: aload_0        
        //    21: invokevirtual   com/google/android/gms/analytics/internal/zzj.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    24: astore          4
        //    26: new             Ljava/util/ArrayList;
        //    29: dup            
        //    30: invokespecial   java/util/ArrayList.<init>:()V
        //    33: astore          5
        //    35: aload           4
        //    37: ldc             "hits2"
        //    39: iconst_1       
        //    40: anewarray       Ljava/lang/String;
        //    43: dup            
        //    44: iconst_0       
        //    45: ldc             "hit_id"
        //    47: aastore        
        //    48: aconst_null    
        //    49: aconst_null    
        //    50: aconst_null    
        //    51: aconst_null    
        //    52: ldc_w           "%s ASC"
        //    55: iconst_1       
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: iconst_0       
        //    61: ldc             "hit_id"
        //    63: aastore        
        //    64: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    67: lload_1        
        //    68: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //    71: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    74: astore          9
        //    76: aload           9
        //    78: astore          8
        //    80: aload           8
        //    82: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    87: ifeq            123
        //    90: aload           5
        //    92: aload           8
        //    94: iconst_0       
        //    95: invokeinterface android/database/Cursor.getLong:(I)J
        //   100: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   103: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   108: pop            
        //   109: aload           8
        //   111: invokeinterface android/database/Cursor.moveToNext:()Z
        //   116: istore          11
        //   118: iload           11
        //   120: ifne            90
        //   123: aload           8
        //   125: ifnull          135
        //   128: aload           8
        //   130: invokeinterface android/database/Cursor.close:()V
        //   135: aload           5
        //   137: areturn        
        //   138: astore          7
        //   140: aconst_null    
        //   141: astore          8
        //   143: aload_0        
        //   144: ldc_w           "Error selecting hit ids"
        //   147: aload           7
        //   149: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzd:(Ljava/lang/String;Ljava/lang/Object;)V
        //   152: aload           8
        //   154: ifnull          135
        //   157: aload           8
        //   159: invokeinterface android/database/Cursor.close:()V
        //   164: goto            135
        //   167: astore          6
        //   169: aload_3        
        //   170: ifnull          179
        //   173: aload_3        
        //   174: invokeinterface android/database/Cursor.close:()V
        //   179: aload           6
        //   181: athrow         
        //   182: astore          6
        //   184: aload           8
        //   186: astore_3       
        //   187: goto            169
        //   190: astore          7
        //   192: goto            143
        //    Signature:
        //  (J)Ljava/util/List<Ljava/lang/Long;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  35     76     138    143    Landroid/database/sqlite/SQLiteException;
        //  35     76     167    169    Any
        //  80     90     190    195    Landroid/database/sqlite/SQLiteException;
        //  80     90     182    190    Any
        //  90     118    190    195    Landroid/database/sqlite/SQLiteException;
        //  90     118    182    190    Any
        //  143    152    182    190    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void zzo(final List<Long> list) {
        zzx.zzz(list);
        this.zzjk();
        this.zzjv();
        if (!list.isEmpty()) {
            final StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            for (int i = 0; i < list.size(); ++i) {
                final Long n = list.get(i);
                if (n == null || n == 0L) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(n);
            }
            sb.append(")");
            final String string = sb.toString();
            try {
                final SQLiteDatabase writableDatabase = this.getWritableDatabase();
                this.zza("Deleting dispatched hits. count", list.size());
                final int delete = writableDatabase.delete("hits2", string, (String[])null);
                if (delete != list.size()) {
                    this.zzb("Deleted fewer hits then expected", list.size(), delete, string);
                }
            }
            catch (SQLiteException ex) {
                this.zze("Error deleting hits", ex);
                throw ex;
            }
        }
    }
    
    public List<zzab> zzp(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_3       
        //     2: aconst_null    
        //     3: astore          4
        //     5: lload_1        
        //     6: lconst_0       
        //     7: lcmp           
        //     8: iflt            225
        //    11: iload_3        
        //    12: invokestatic    com/google/android/gms/common/internal/zzx.zzac:(Z)V
        //    15: aload_0        
        //    16: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzjk:()V
        //    19: aload_0        
        //    20: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzjv:()V
        //    23: aload_0        
        //    24: invokevirtual   com/google/android/gms/analytics/internal/zzj.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    27: astore          5
        //    29: aload           5
        //    31: ldc             "hits2"
        //    33: iconst_5       
        //    34: anewarray       Ljava/lang/String;
        //    37: dup            
        //    38: iconst_0       
        //    39: ldc             "hit_id"
        //    41: aastore        
        //    42: dup            
        //    43: iconst_1       
        //    44: ldc             "hit_time"
        //    46: aastore        
        //    47: dup            
        //    48: iconst_2       
        //    49: ldc             "hit_string"
        //    51: aastore        
        //    52: dup            
        //    53: iconst_3       
        //    54: ldc             "hit_url"
        //    56: aastore        
        //    57: dup            
        //    58: iconst_4       
        //    59: ldc             "hit_app_id"
        //    61: aastore        
        //    62: aconst_null    
        //    63: aconst_null    
        //    64: aconst_null    
        //    65: aconst_null    
        //    66: ldc_w           "%s ASC"
        //    69: iconst_1       
        //    70: anewarray       Ljava/lang/Object;
        //    73: dup            
        //    74: iconst_0       
        //    75: ldc             "hit_id"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: lload_1        
        //    82: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //    85: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    88: astore          9
        //    90: aload           9
        //    92: astore          4
        //    94: new             Ljava/util/ArrayList;
        //    97: dup            
        //    98: invokespecial   java/util/ArrayList.<init>:()V
        //   101: astore          10
        //   103: aload           4
        //   105: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   110: ifeq            210
        //   113: aload           4
        //   115: iconst_0       
        //   116: invokeinterface android/database/Cursor.getLong:(I)J
        //   121: lstore          11
        //   123: aload           4
        //   125: iconst_1       
        //   126: invokeinterface android/database/Cursor.getLong:(I)J
        //   131: lstore          13
        //   133: aload           4
        //   135: iconst_2       
        //   136: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   141: astore          15
        //   143: aload           4
        //   145: iconst_3       
        //   146: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   151: astore          16
        //   153: aload           4
        //   155: iconst_4       
        //   156: invokeinterface android/database/Cursor.getInt:(I)I
        //   161: istore          17
        //   163: aload           10
        //   165: new             Lcom/google/android/gms/analytics/internal/zzab;
        //   168: dup            
        //   169: aload_0        
        //   170: aload_0        
        //   171: aload           15
        //   173: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzbi:(Ljava/lang/String;)Ljava/util/Map;
        //   176: lload           13
        //   178: aload           16
        //   180: invokestatic    com/google/android/gms/analytics/internal/zzam.zzbx:(Ljava/lang/String;)Z
        //   183: lload           11
        //   185: iload           17
        //   187: invokespecial   com/google/android/gms/analytics/internal/zzab.<init>:(Lcom/google/android/gms/analytics/internal/zzc;Ljava/util/Map;JZJI)V
        //   190: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   195: pop            
        //   196: aload           4
        //   198: invokeinterface android/database/Cursor.moveToNext:()Z
        //   203: istore          19
        //   205: iload           19
        //   207: ifne            113
        //   210: aload           4
        //   212: ifnull          222
        //   215: aload           4
        //   217: invokeinterface android/database/Cursor.close:()V
        //   222: aload           10
        //   224: areturn        
        //   225: iconst_0       
        //   226: istore_3       
        //   227: goto            11
        //   230: astore          7
        //   232: aconst_null    
        //   233: astore          8
        //   235: aload_0        
        //   236: ldc_w           "Error loading hits from the database"
        //   239: aload           7
        //   241: invokevirtual   com/google/android/gms/analytics/internal/zzj.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   244: aload           7
        //   246: athrow         
        //   247: astore          6
        //   249: aload           8
        //   251: astore          4
        //   253: aload           4
        //   255: ifnull          265
        //   258: aload           4
        //   260: invokeinterface android/database/Cursor.close:()V
        //   265: aload           6
        //   267: athrow         
        //   268: astore          6
        //   270: goto            253
        //   273: astore          7
        //   275: aload           4
        //   277: astore          8
        //   279: goto            235
        //    Signature:
        //  (J)Ljava/util/List<Lcom/google/android/gms/analytics/internal/zzab;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  29     90     230    235    Landroid/database/sqlite/SQLiteException;
        //  29     90     268    273    Any
        //  94     113    273    282    Landroid/database/sqlite/SQLiteException;
        //  94     113    268    273    Any
        //  113    205    273    282    Landroid/database/sqlite/SQLiteException;
        //  113    205    268    273    Any
        //  235    247    247    253    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void zzq(final long n) {
        this.zzjk();
        this.zzjv();
        final ArrayList<Long> list = new ArrayList<Long>(1);
        list.add(n);
        this.zza("Deleting hit, id", n);
        this.zzo(list);
    }
    
    public List<zzh> zzr(final long n) {
        this.zzjv();
        this.zzjk();
        final SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor query = null;
        while (true) {
            try {
                try {
                    final String[] array = { "cid", "tid", "adid", "hits_count", "params" };
                    final int zzkQ = this.zzjn().zzkQ();
                    query = writableDatabase.query("properties", array, "app_uid=?", new String[] { String.valueOf(n) }, (String)null, (String)null, (String)null, String.valueOf(zzkQ));
                    Cursor cursor;
                    try {
                        final ArrayList<zzh> list = new ArrayList<zzh>();
                        if (query.moveToFirst()) {
                            do {
                                final String string = query.getString(0);
                                final String string2 = query.getString(1);
                                final boolean b = query.getInt(2) != 0;
                                final long n2 = query.getInt(3);
                                final Map<String, String> zzbj = this.zzbj(query.getString(4));
                                if (TextUtils.isEmpty((CharSequence)string) || TextUtils.isEmpty((CharSequence)string2)) {
                                    this.zzc("Read property with empty client id or tracker id", string, string2);
                                }
                                else {
                                    list.add(new zzh(n, string, string2, b, n2, zzbj));
                                }
                            } while (query.moveToNext());
                        }
                        if (list.size() >= zzkQ) {
                            this.zzbg("Sending hits to too many properties. Campaign report might be incorrect");
                        }
                        if (query != null) {
                            query.close();
                        }
                        return list;
                    }
                    catch (SQLiteException ex) {
                        cursor = query;
                    }
                    try {
                        final SQLiteException ex;
                        this.zze("Error loading hits from the database", ex);
                        throw ex;
                    }
                    finally {
                        query = cursor;
                    }
                    if (query != null) {
                        query.close();
                    }
                    throw;
                }
                finally {}
            }
            catch (SQLiteException ex) {
                final Cursor cursor = null;
                continue;
            }
            break;
        }
    }
    
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
            if (!zzj.this.zzQV.zzv(3600000L)) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            }
            catch (SQLiteException ex2) {
                zzj.this.zzQV.start();
                zzj.this.zzbh("Opening the database failed, dropping the table and recreating it");
                zzj.this.getContext().getDatabasePath(zzj.this.zzjQ()).delete();
                try {
                    final SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zzj.this.zzQV.clear();
                    return writableDatabase;
                }
                catch (SQLiteException ex) {
                    zzj.this.zze("Failed to open freshly created database", ex);
                    throw ex;
                }
            }
        }
        
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            com.google.android.gms.analytics.internal.zzx.zzbo(sqLiteDatabase.getPath());
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
                                    sqLiteDatabase.execSQL(zzj.zzQR);
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
}
