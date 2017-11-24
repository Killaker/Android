package android.support.v4.os;

import android.os.*;

class MyRunnable implements Runnable
{
    final int mResultCode;
    final Bundle mResultData;
    
    MyRunnable(final int mResultCode, final Bundle mResultData) {
        this.mResultCode = mResultCode;
        this.mResultData = mResultData;
    }
    
    @Override
    public void run() {
        ResultReceiver.this.onReceiveResult(this.mResultCode, this.mResultData);
    }
}
