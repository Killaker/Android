package android.support.v4.media;

import android.content.*;
import android.view.*;
import android.util.*;

class TransportMediatorJellybeanMR2$3 extends BroadcastReceiver {
    public void onReceive(final Context context, final Intent intent) {
        try {
            TransportMediatorJellybeanMR2.this.mTransportCallback.handleKey((KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
        }
        catch (ClassCastException ex) {
            Log.w("TransportController", (Throwable)ex);
        }
    }
}