package com.google.android.gms.common.internal;

protected abstract class zzc<TListener>
{
    private TListener mListener;
    private boolean zzalM;
    
    public zzc(final TListener mListener) {
        this.mListener = mListener;
        this.zzalM = false;
    }
    
    public void unregister() {
        this.zzqO();
        synchronized (zzj.zzd(zzj.this)) {
            zzj.zzd(zzj.this).remove(this);
        }
    }
    
    protected abstract void zzqM();
    
    public void zzqN() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/common/internal/zzj$zzc.mListener:Ljava/lang/Object;
        //     6: astore_2       
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/internal/zzj$zzc.zzalM:Z
        //    11: ifeq            44
        //    14: ldc             "GmsClient"
        //    16: new             Ljava/lang/StringBuilder;
        //    19: dup            
        //    20: invokespecial   java/lang/StringBuilder.<init>:()V
        //    23: ldc             "Callback proxy "
        //    25: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    28: aload_0        
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    32: ldc             " being reused. This is not safe."
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    40: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    43: pop            
        //    44: aload_0        
        //    45: monitorexit    
        //    46: aload_2        
        //    47: ifnull          83
        //    50: aload_0        
        //    51: aload_2        
        //    52: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.zzw:(Ljava/lang/Object;)V
        //    55: aload_0        
        //    56: monitorenter   
        //    57: aload_0        
        //    58: iconst_1       
        //    59: putfield        com/google/android/gms/common/internal/zzj$zzc.zzalM:Z
        //    62: aload_0        
        //    63: monitorexit    
        //    64: aload_0        
        //    65: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.unregister:()V
        //    68: return         
        //    69: astore_1       
        //    70: aload_0        
        //    71: monitorexit    
        //    72: aload_1        
        //    73: athrow         
        //    74: astore          4
        //    76: aload_0        
        //    77: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.zzqM:()V
        //    80: aload           4
        //    82: athrow         
        //    83: aload_0        
        //    84: invokevirtual   com/google/android/gms/common/internal/zzj$zzc.zzqM:()V
        //    87: goto            55
        //    90: astore_3       
        //    91: aload_0        
        //    92: monitorexit    
        //    93: aload_3        
        //    94: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  2      44     69     74     Any
        //  44     46     69     74     Any
        //  50     55     74     83     Ljava/lang/RuntimeException;
        //  57     64     90     95     Any
        //  70     72     69     74     Any
        //  91     93     90     95     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void zzqO() {
        synchronized (this) {
            this.mListener = null;
        }
    }
    
    protected abstract void zzw(final TListener p0);
}
