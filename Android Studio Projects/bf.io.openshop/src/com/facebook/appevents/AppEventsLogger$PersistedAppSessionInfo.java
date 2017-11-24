package com.facebook.appevents;

import java.util.*;
import android.content.*;
import java.util.concurrent.*;

static class PersistedAppSessionInfo
{
    private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
    private static final Runnable appSessionInfoFlushRunnable;
    private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
    private static boolean hasChanges;
    private static boolean isLoaded;
    private static final Object staticLock;
    
    static {
        staticLock = new Object();
        PersistedAppSessionInfo.hasChanges = false;
        PersistedAppSessionInfo.isLoaded = false;
        appSessionInfoFlushRunnable = new Runnable() {
            @Override
            public void run() {
                PersistedAppSessionInfo.saveAppSessionInformation(AppEventsLogger.access$1100());
            }
        };
    }
    
    private static FacebookTimeSpentData getTimeSpentData(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair) {
        restoreAppSessionInformation(context);
        FacebookTimeSpentData facebookTimeSpentData = PersistedAppSessionInfo.appSessionInfoMap.get(accessTokenAppIdPair);
        if (facebookTimeSpentData == null) {
            facebookTimeSpentData = new FacebookTimeSpentData();
            PersistedAppSessionInfo.appSessionInfoMap.put(accessTokenAppIdPair, facebookTimeSpentData);
        }
        return facebookTimeSpentData;
    }
    
    static void onResume(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final AppEventsLogger appEventsLogger, final long n, final String s) {
        synchronized (PersistedAppSessionInfo.staticLock) {
            getTimeSpentData(context, accessTokenAppIdPair).onResume(appEventsLogger, n, s);
            onTimeSpentDataUpdate();
        }
    }
    
    static void onSuspend(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final AppEventsLogger appEventsLogger, final long n) {
        synchronized (PersistedAppSessionInfo.staticLock) {
            getTimeSpentData(context, accessTokenAppIdPair).onSuspend(appEventsLogger, n);
            onTimeSpentDataUpdate();
        }
    }
    
    private static void onTimeSpentDataUpdate() {
        if (!PersistedAppSessionInfo.hasChanges) {
            PersistedAppSessionInfo.hasChanges = true;
            AppEventsLogger.access$1500().schedule(PersistedAppSessionInfo.appSessionInfoFlushRunnable, 30L, TimeUnit.SECONDS);
        }
    }
    
    private static void restoreAppSessionInformation(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1       
        //     2: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
        //     5: astore_2       
        //     6: aload_2        
        //     7: monitorenter   
        //     8: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //    11: istore          4
        //    13: iload           4
        //    15: ifne            90
        //    18: new             Ljava/io/ObjectInputStream;
        //    21: dup            
        //    22: aload_0        
        //    23: ldc             "AppEventsLogger.persistedsessioninfo"
        //    25: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    28: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    31: astore          5
        //    33: aload           5
        //    35: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //    38: checkcast       Ljava/util/HashMap;
        //    41: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    44: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
        //    47: ldc             "AppEvents"
        //    49: ldc             "App session info loaded"
        //    51: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //    54: aload           5
        //    56: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    59: aload_0        
        //    60: ldc             "AppEventsLogger.persistedsessioninfo"
        //    62: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //    65: pop            
        //    66: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    69: ifnonnull       82
        //    72: new             Ljava/util/HashMap;
        //    75: dup            
        //    76: invokespecial   java/util/HashMap.<init>:()V
        //    79: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    82: iconst_1       
        //    83: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //    86: iconst_0       
        //    87: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //    90: aload_2        
        //    91: monitorexit    
        //    92: return         
        //    93: aload           7
        //    95: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    98: aload_0        
        //    99: ldc             "AppEventsLogger.persistedsessioninfo"
        //   101: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   104: pop            
        //   105: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   108: ifnonnull       121
        //   111: new             Ljava/util/HashMap;
        //   114: dup            
        //   115: invokespecial   java/util/HashMap.<init>:()V
        //   118: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   121: iconst_1       
        //   122: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //   125: iconst_0       
        //   126: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //   129: goto            90
        //   132: aload_2        
        //   133: monitorexit    
        //   134: aload_3        
        //   135: athrow         
        //   136: astore          9
        //   138: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
        //   141: new             Ljava/lang/StringBuilder;
        //   144: dup            
        //   145: invokespecial   java/lang/StringBuilder.<init>:()V
        //   148: ldc             "Got unexpected exception: "
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: aload           9
        //   155: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   164: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   167: pop            
        //   168: aload_1        
        //   169: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   172: aload_0        
        //   173: ldc             "AppEventsLogger.persistedsessioninfo"
        //   175: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   178: pop            
        //   179: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   182: ifnonnull       195
        //   185: new             Ljava/util/HashMap;
        //   188: dup            
        //   189: invokespecial   java/util/HashMap.<init>:()V
        //   192: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   195: iconst_1       
        //   196: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //   199: iconst_0       
        //   200: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //   203: goto            90
        //   206: aload_1        
        //   207: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   210: aload_0        
        //   211: ldc             "AppEventsLogger.persistedsessioninfo"
        //   213: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   216: pop            
        //   217: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   220: ifnonnull       233
        //   223: new             Ljava/util/HashMap;
        //   226: dup            
        //   227: invokespecial   java/util/HashMap.<init>:()V
        //   230: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //   233: iconst_1       
        //   234: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
        //   237: iconst_0       
        //   238: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //   241: aload           10
        //   243: athrow         
        //   244: astore_3       
        //   245: goto            132
        //   248: astore          10
        //   250: aload           5
        //   252: astore_1       
        //   253: goto            206
        //   256: astore          9
        //   258: aload           5
        //   260: astore_1       
        //   261: goto            138
        //   264: astore          6
        //   266: aload           5
        //   268: astore          7
        //   270: goto            93
        //   273: astore          15
        //   275: aconst_null    
        //   276: astore          7
        //   278: goto            93
        //   281: astore_3       
        //   282: goto            132
        //   285: astore          10
        //   287: goto            206
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  8      13     281    285    Any
        //  18     33     273    281    Ljava/io/FileNotFoundException;
        //  18     33     136    138    Ljava/lang/Exception;
        //  18     33     285    290    Any
        //  33     54     264    273    Ljava/io/FileNotFoundException;
        //  33     54     256    264    Ljava/lang/Exception;
        //  33     54     248    256    Any
        //  54     82     244    248    Any
        //  82     90     244    248    Any
        //  90     92     281    285    Any
        //  93     121    281    285    Any
        //  121    129    281    285    Any
        //  132    134    281    285    Any
        //  138    168    285    290    Any
        //  168    195    281    285    Any
        //  195    203    281    285    Any
        //  206    233    281    285    Any
        //  233    244    281    285    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void saveAppSessionInformation(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1       
        //     2: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
        //     5: astore_2       
        //     6: aload_2        
        //     7: monitorenter   
        //     8: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //    11: istore          4
        //    13: iload           4
        //    15: ifeq            68
        //    18: new             Ljava/io/ObjectOutputStream;
        //    21: dup            
        //    22: new             Ljava/io/BufferedOutputStream;
        //    25: dup            
        //    26: aload_0        
        //    27: ldc             "AppEventsLogger.persistedsessioninfo"
        //    29: iconst_0       
        //    30: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    33: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    36: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    39: astore          5
        //    41: aload           5
        //    43: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
        //    46: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    49: iconst_0       
        //    50: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
        //    53: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
        //    56: ldc             "AppEvents"
        //    58: ldc             "App session info saved"
        //    60: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //    63: aload           5
        //    65: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    68: aload_2        
        //    69: monitorexit    
        //    70: return         
        //    71: astore          6
        //    73: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
        //    76: new             Ljava/lang/StringBuilder;
        //    79: dup            
        //    80: invokespecial   java/lang/StringBuilder.<init>:()V
        //    83: ldc             "Got unexpected exception: "
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: aload           6
        //    90: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    99: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   102: pop            
        //   103: aload_1        
        //   104: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   107: goto            68
        //   110: aload_2        
        //   111: monitorexit    
        //   112: aload_3        
        //   113: athrow         
        //   114: astore          7
        //   116: aload_1        
        //   117: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   120: aload           7
        //   122: athrow         
        //   123: astore_3       
        //   124: goto            110
        //   127: astore          7
        //   129: aload           5
        //   131: astore_1       
        //   132: goto            116
        //   135: astore          6
        //   137: aload           5
        //   139: astore_1       
        //   140: goto            73
        //   143: astore_3       
        //   144: goto            110
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      13     143    147    Any
        //  18     41     71     73     Ljava/lang/Exception;
        //  18     41     114    116    Any
        //  41     63     135    143    Ljava/lang/Exception;
        //  41     63     127    135    Any
        //  63     68     123    127    Any
        //  68     70     143    147    Any
        //  73     103    114    116    Any
        //  103    107    143    147    Any
        //  110    112    143    147    Any
        //  116    123    143    147    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
