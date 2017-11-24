package android.support.v4.os;

import android.os.*;

public final class CancellationSignal
{
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;
    
    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        android/support/v4/os/CancellationSignal.mIsCanceled:Z
        //     6: ifeq            12
        //     9: aload_0        
        //    10: monitorexit    
        //    11: return         
        //    12: aload_0        
        //    13: iconst_1       
        //    14: putfield        android/support/v4/os/CancellationSignal.mIsCanceled:Z
        //    17: aload_0        
        //    18: iconst_1       
        //    19: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    22: aload_0        
        //    23: getfield        android/support/v4/os/CancellationSignal.mOnCancelListener:Landroid/support/v4/os/CancellationSignal$OnCancelListener;
        //    26: astore_2       
        //    27: aload_0        
        //    28: getfield        android/support/v4/os/CancellationSignal.mCancellationSignalObj:Ljava/lang/Object;
        //    31: astore_3       
        //    32: aload_0        
        //    33: monitorexit    
        //    34: aload_2        
        //    35: ifnull          44
        //    38: aload_2        
        //    39: invokeinterface android/support/v4/os/CancellationSignal$OnCancelListener.onCancel:()V
        //    44: aload_3        
        //    45: ifnull          52
        //    48: aload_3        
        //    49: invokestatic    android/support/v4/os/CancellationSignalCompatJellybean.cancel:(Ljava/lang/Object;)V
        //    52: aload_0        
        //    53: monitorenter   
        //    54: aload_0        
        //    55: iconst_0       
        //    56: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    59: aload_0        
        //    60: invokevirtual   java/lang/Object.notifyAll:()V
        //    63: aload_0        
        //    64: monitorexit    
        //    65: return         
        //    66: astore          6
        //    68: aload_0        
        //    69: monitorexit    
        //    70: aload           6
        //    72: athrow         
        //    73: astore_1       
        //    74: aload_0        
        //    75: monitorexit    
        //    76: aload_1        
        //    77: athrow         
        //    78: astore          4
        //    80: aload_0        
        //    81: monitorenter   
        //    82: aload_0        
        //    83: iconst_0       
        //    84: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    87: aload_0        
        //    88: invokevirtual   java/lang/Object.notifyAll:()V
        //    91: aload_0        
        //    92: monitorexit    
        //    93: aload           4
        //    95: athrow         
        //    96: astore          5
        //    98: aload_0        
        //    99: monitorexit    
        //   100: aload           5
        //   102: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      11     73     78     Any
        //  12     34     73     78     Any
        //  38     44     78     103    Any
        //  48     52     78     103    Any
        //  54     65     66     73     Any
        //  68     70     66     73     Any
        //  74     76     73     78     Any
        //  82     93     96     103    Any
        //  98     100    96     103    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object getCancellationSignalObject() {
        if (Build$VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            if (this.mCancellationSignalObj == null) {
                this.mCancellationSignalObj = CancellationSignalCompatJellybean.create();
                if (this.mIsCanceled) {
                    CancellationSignalCompatJellybean.cancel(this.mCancellationSignalObj);
                }
            }
            return this.mCancellationSignalObj;
        }
    }
    
    public boolean isCanceled() {
        synchronized (this) {
            return this.mIsCanceled;
        }
    }
    
    public void setOnCancelListener(final OnCancelListener mOnCancelListener) {
        synchronized (this) {
            this.waitForCancelFinishedLocked();
            if (this.mOnCancelListener == mOnCancelListener) {
                return;
            }
            this.mOnCancelListener = mOnCancelListener;
            if (!this.mIsCanceled || mOnCancelListener == null) {
                return;
            }
        }
        // monitorexit(this)
        mOnCancelListener.onCancel();
    }
    
    public void throwIfCanceled() {
        if (this.isCanceled()) {
            throw new OperationCanceledException();
        }
    }
    
    public interface OnCancelListener
    {
        void onCancel();
    }
}
