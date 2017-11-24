package android.support.v4.os;

import android.os.*;

class MyResultReceiver extends Stub
{
    public void send(final int n, final Bundle bundle) {
        if (ResultReceiver.this.mHandler != null) {
            ResultReceiver.this.mHandler.post((Runnable)new MyRunnable(n, bundle));
            return;
        }
        ResultReceiver.this.onReceiveResult(n, bundle);
    }
}
