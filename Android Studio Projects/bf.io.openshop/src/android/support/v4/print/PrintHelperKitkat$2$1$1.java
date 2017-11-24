package android.support.v4.print;

import android.os.*;

class PrintHelperKitkat$2$1$1 implements CancellationSignal$OnCancelListener {
    public void onCancel() {
        PrintHelperKitkat$2.access$200(AsyncTask.this.this$1);
        AsyncTask.this.cancel(false);
    }
}