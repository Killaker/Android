package com.google.android.gms.tagmanager;

import android.content.*;
import com.google.android.gms.internal.*;
import android.net.*;

class zzcl implements Runnable
{
    private final Context mContext;
    private final String zzbhM;
    private volatile String zzbij;
    private final zzrw zzbke;
    private final String zzbkf;
    private zzbf<zzaf.zzj> zzbkg;
    private volatile zzs zzbkh;
    private volatile String zzbki;
    
    zzcl(final Context mContext, final String zzbhM, final zzrw zzbke, final zzs zzbkh) {
        this.mContext = mContext;
        this.zzbke = zzbke;
        this.zzbhM = zzbhM;
        this.zzbkh = zzbkh;
        this.zzbkf = "/r?id=" + zzbhM;
        this.zzbij = this.zzbkf;
        this.zzbki = null;
    }
    
    public zzcl(final Context context, final String s, final zzs zzs) {
        this(context, s, new zzrw(), zzs);
    }
    
    private boolean zzGX() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzbg.v("...no network connectivity");
            return false;
        }
        return true;
    }
    
    private void zzGY() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/google/android/gms/tagmanager/zzcl.zzGX:()Z
        //     4: ifne            20
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/tagmanager/zzcl.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //    11: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbju:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //    14: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //    19: return         
        //    20: ldc             "Start loading resource from network ..."
        //    22: invokestatic    com/google/android/gms/tagmanager/zzbg.v:(Ljava/lang/String;)V
        //    25: aload_0        
        //    26: invokevirtual   com/google/android/gms/tagmanager/zzcl.zzGZ:()Ljava/lang/String;
        //    29: astore_1       
        //    30: aload_0        
        //    31: getfield        com/google/android/gms/tagmanager/zzcl.zzbke:Lcom/google/android/gms/internal/zzrw;
        //    34: invokevirtual   com/google/android/gms/internal/zzrw.zzIa:()Lcom/google/android/gms/internal/zzrv;
        //    37: astore_2       
        //    38: aload_2        
        //    39: aload_1        
        //    40: invokeinterface com/google/android/gms/internal/zzrv.zzgI:(Ljava/lang/String;)Ljava/io/InputStream;
        //    45: astore          6
        //    47: new             Ljava/io/ByteArrayOutputStream;
        //    50: dup            
        //    51: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    54: astore          7
        //    56: aload           6
        //    58: aload           7
        //    60: invokestatic    com/google/android/gms/internal/zzrs.zzb:(Ljava/io/InputStream;Ljava/io/OutputStream;)V
        //    63: aload           7
        //    65: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    68: invokestatic    com/google/android/gms/internal/zzaf$zzj.zzd:([B)Lcom/google/android/gms/internal/zzaf$zzj;
        //    71: astore          9
        //    73: new             Ljava/lang/StringBuilder;
        //    76: dup            
        //    77: invokespecial   java/lang/StringBuilder.<init>:()V
        //    80: ldc             "Successfully loaded supplemented resource: "
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: aload           9
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokestatic    com/google/android/gms/tagmanager/zzbg.v:(Ljava/lang/String;)V
        //    96: aload           9
        //    98: getfield        com/google/android/gms/internal/zzaf$zzj.zzju:Lcom/google/android/gms/internal/zzaf$zzf;
        //   101: ifnonnull       138
        //   104: aload           9
        //   106: getfield        com/google/android/gms/internal/zzaf$zzj.zzjt:[Lcom/google/android/gms/internal/zzaf$zzi;
        //   109: arraylength    
        //   110: ifne            138
        //   113: new             Ljava/lang/StringBuilder;
        //   116: dup            
        //   117: invokespecial   java/lang/StringBuilder.<init>:()V
        //   120: ldc             "No change for container: "
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: aload_0        
        //   126: getfield        com/google/android/gms/tagmanager/zzcl.zzbhM:Ljava/lang/String;
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   135: invokestatic    com/google/android/gms/tagmanager/zzbg.v:(Ljava/lang/String;)V
        //   138: aload_0        
        //   139: getfield        com/google/android/gms/tagmanager/zzcl.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   142: aload           9
        //   144: invokeinterface com/google/android/gms/tagmanager/zzbf.zzI:(Ljava/lang/Object;)V
        //   149: aload_2        
        //   150: invokeinterface com/google/android/gms/internal/zzrv.close:()V
        //   155: ldc             "Load resource from network finished."
        //   157: invokestatic    com/google/android/gms/tagmanager/zzbg.v:(Ljava/lang/String;)V
        //   160: return         
        //   161: astore          5
        //   163: new             Ljava/lang/StringBuilder;
        //   166: dup            
        //   167: invokespecial   java/lang/StringBuilder.<init>:()V
        //   170: ldc             "No data is retrieved from the given url: "
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: aload_1        
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: ldc             ". Make sure container_id: "
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: aload_0        
        //   185: getfield        com/google/android/gms/tagmanager/zzcl.zzbhM:Ljava/lang/String;
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: ldc             " is correct."
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   199: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   202: aload_0        
        //   203: getfield        com/google/android/gms/tagmanager/zzcl.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   206: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbjw:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //   209: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //   214: aload_2        
        //   215: invokeinterface com/google/android/gms/internal/zzrv.close:()V
        //   220: return         
        //   221: astore          4
        //   223: new             Ljava/lang/StringBuilder;
        //   226: dup            
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: ldc             "Error when loading resources from url: "
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: aload_1        
        //   236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   239: ldc             " "
        //   241: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: aload           4
        //   246: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   255: aload           4
        //   257: invokestatic    com/google/android/gms/tagmanager/zzbg.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   260: aload_0        
        //   261: getfield        com/google/android/gms/tagmanager/zzcl.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   264: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbjv:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //   267: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //   272: aload_2        
        //   273: invokeinterface com/google/android/gms/internal/zzrv.close:()V
        //   278: return         
        //   279: astore          8
        //   281: new             Ljava/lang/StringBuilder;
        //   284: dup            
        //   285: invokespecial   java/lang/StringBuilder.<init>:()V
        //   288: ldc             "Error when parsing downloaded resources from url: "
        //   290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   293: aload_1        
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: ldc             " "
        //   299: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   302: aload           8
        //   304: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   313: aload           8
        //   315: invokestatic    com/google/android/gms/tagmanager/zzbg.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   318: aload_0        
        //   319: getfield        com/google/android/gms/tagmanager/zzcl.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   322: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbjw:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //   325: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //   330: aload_2        
        //   331: invokeinterface com/google/android/gms/internal/zzrv.close:()V
        //   336: return         
        //   337: astore_3       
        //   338: aload_2        
        //   339: invokeinterface com/google/android/gms/internal/zzrv.close:()V
        //   344: aload_3        
        //   345: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  38     47     161    221    Ljava/io/FileNotFoundException;
        //  38     47     221    279    Ljava/io/IOException;
        //  38     47     337    346    Any
        //  47     138    279    337    Ljava/io/IOException;
        //  47     138    337    346    Any
        //  138    149    279    337    Ljava/io/IOException;
        //  138    149    337    346    Any
        //  163    214    337    346    Any
        //  223    272    337    346    Any
        //  281    330    337    346    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void run() {
        if (this.zzbkg == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.zzbkg.zzGk();
        this.zzGY();
    }
    
    String zzGZ() {
        String s = this.zzbkh.zzGm() + this.zzbij + "&v=a65833898";
        if (this.zzbki != null && !this.zzbki.trim().equals("")) {
            s = s + "&pv=" + this.zzbki;
        }
        if (zzcb.zzGU().zzGV().equals(zzcb.zza.zzbjW)) {
            s += "&gtm_debug=x";
        }
        return s;
    }
    
    void zza(final zzbf<zzaf.zzj> zzbkg) {
        this.zzbkg = zzbkg;
    }
    
    void zzfW(final String zzbij) {
        if (zzbij == null) {
            this.zzbij = this.zzbkf;
            return;
        }
        zzbg.zzaI("Setting CTFE URL path: " + zzbij);
        this.zzbij = zzbij;
    }
    
    void zzgl(final String zzbki) {
        zzbg.zzaI("Setting previous container version: " + zzbki);
        this.zzbki = zzbki;
    }
}
