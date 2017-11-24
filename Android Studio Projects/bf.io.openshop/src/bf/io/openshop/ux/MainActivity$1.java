package bf.io.openshop.ux;

import android.content.*;
import bf.io.openshop.*;
import timber.log.*;

class MainActivity$1 extends BroadcastReceiver {
    public void onReceive(final Context context, final Intent intent) {
        if (SettingsMy.getTokenSentToServer()) {
            Timber.d("Gcm registration success.", new Object[0]);
            return;
        }
        Timber.e("Gcm registration failed. Device isn't registered on server.", new Object[0]);
    }
}