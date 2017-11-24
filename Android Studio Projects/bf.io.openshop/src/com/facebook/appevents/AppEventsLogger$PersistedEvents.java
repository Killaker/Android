package com.facebook.appevents;

import android.content.*;
import java.util.*;

static class PersistedEvents
{
    static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    private static Object staticLock;
    private Context context;
    private HashMap<AccessTokenAppIdPair, List<AppEvent>> persistedEvents;
    
    static {
        PersistedEvents.staticLock = new Object();
    }
    
    private PersistedEvents(final Context context) {
        this.persistedEvents = new HashMap<AccessTokenAppIdPair, List<AppEvent>>();
        this.context = context;
    }
    
    public static void persistEvents(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final SessionEventsState sessionEventsState) {
        final HashMap<AccessTokenAppIdPair, SessionEventsState> hashMap = new HashMap<AccessTokenAppIdPair, SessionEventsState>();
        hashMap.put(accessTokenAppIdPair, sessionEventsState);
        persistEvents(context, hashMap);
    }
    
    public static void persistEvents(final Context context, final Map<AccessTokenAppIdPair, SessionEventsState> map) {
        final PersistedEvents andClearStore;
        synchronized (PersistedEvents.staticLock) {
            andClearStore = readAndClearStore(context);
            for (final Map.Entry<AccessTokenAppIdPair, SessionEventsState> entry : map.entrySet()) {
                final List<AppEvent> eventsToPersist = entry.getValue().getEventsToPersist();
                if (eventsToPersist.size() != 0) {
                    andClearStore.addEvents(entry.getKey(), eventsToPersist);
                }
            }
        }
        andClearStore.write();
    }
    // monitorexit(o)
    
    public static PersistedEvents readAndClearStore(final Context context) {
        synchronized (PersistedEvents.staticLock) {
            final PersistedEvents persistedEvents = new PersistedEvents(context);
            persistedEvents.readAndClearStore();
            return persistedEvents;
        }
    }
    
    private void readAndClearStore() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1       
        //     2: new             Ljava/io/ObjectInputStream;
        //     5: dup            
        //     6: new             Ljava/io/BufferedInputStream;
        //     9: dup            
        //    10: aload_0        
        //    11: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
        //    14: ldc             "AppEventsLogger.persistedevents"
        //    16: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    19: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    22: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    25: astore_2       
        //    26: aload_2        
        //    27: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //    30: checkcast       Ljava/util/HashMap;
        //    33: astore          7
        //    35: aload_0        
        //    36: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
        //    39: ldc             "AppEventsLogger.persistedevents"
        //    41: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
        //    44: invokevirtual   java/io/File.delete:()Z
        //    47: pop            
        //    48: aload_0        
        //    49: aload           7
        //    51: putfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.persistedEvents:Ljava/util/HashMap;
        //    54: aload_2        
        //    55: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    58: return         
        //    59: astore          9
        //    61: aload_1        
        //    62: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    65: return         
        //    66: astore          4
        //    68: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
        //    71: new             Ljava/lang/StringBuilder;
        //    74: dup            
        //    75: invokespecial   java/lang/StringBuilder.<init>:()V
        //    78: ldc             "Got unexpected exception: "
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload           4
        //    85: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    91: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    94: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    97: pop            
        //    98: aload_1        
        //    99: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   102: return         
        //   103: astore          5
        //   105: aload_1        
        //   106: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   109: aload           5
        //   111: athrow         
        //   112: astore          5
        //   114: aload_2        
        //   115: astore_1       
        //   116: goto            105
        //   119: astore          4
        //   121: aload_2        
        //   122: astore_1       
        //   123: goto            68
        //   126: astore_3       
        //   127: aload_2        
        //   128: astore_1       
        //   129: goto            61
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  2      26     59     61     Ljava/io/FileNotFoundException;
        //  2      26     66     68     Ljava/lang/Exception;
        //  2      26     103    105    Any
        //  26     54     126    132    Ljava/io/FileNotFoundException;
        //  26     54     119    126    Ljava/lang/Exception;
        //  26     54     112    119    Any
        //  68     98     103    105    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void write() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1       
        //     2: new             Ljava/io/ObjectOutputStream;
        //     5: dup            
        //     6: new             Ljava/io/BufferedOutputStream;
        //     9: dup            
        //    10: aload_0        
        //    11: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
        //    14: ldc             "AppEventsLogger.persistedevents"
        //    16: iconst_0       
        //    17: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    20: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    23: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    26: astore_2       
        //    27: aload_2        
        //    28: aload_0        
        //    29: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.persistedEvents:Ljava/util/HashMap;
        //    32: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    35: aload_2        
        //    36: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    39: return         
        //    40: astore_3       
        //    41: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
        //    44: new             Ljava/lang/StringBuilder;
        //    47: dup            
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: ldc             "Got unexpected exception: "
        //    53: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    56: aload_3        
        //    57: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    63: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    66: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    69: pop            
        //    70: aload_1        
        //    71: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    74: return         
        //    75: astore          4
        //    77: aload_1        
        //    78: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    81: aload           4
        //    83: athrow         
        //    84: astore          4
        //    86: aload_2        
        //    87: astore_1       
        //    88: goto            77
        //    91: astore_3       
        //    92: aload_2        
        //    93: astore_1       
        //    94: goto            41
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      27     40     41     Ljava/lang/Exception;
        //  2      27     75     77     Any
        //  27     35     91     97     Ljava/lang/Exception;
        //  27     35     84     91     Any
        //  41     70     75     77     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void addEvents(final AccessTokenAppIdPair accessTokenAppIdPair, final List<AppEvent> list) {
        if (!this.persistedEvents.containsKey(accessTokenAppIdPair)) {
            this.persistedEvents.put(accessTokenAppIdPair, new ArrayList<AppEvent>());
        }
        this.persistedEvents.get(accessTokenAppIdPair).addAll(list);
    }
    
    public List<AppEvent> getEvents(final AccessTokenAppIdPair accessTokenAppIdPair) {
        return this.persistedEvents.get(accessTokenAppIdPair);
    }
    
    public Set<AccessTokenAppIdPair> keySet() {
        return this.persistedEvents.keySet();
    }
}
