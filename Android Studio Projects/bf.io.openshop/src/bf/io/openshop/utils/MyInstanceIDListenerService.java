package bf.io.openshop.utils;

import com.google.android.gms.iid.*;
import bf.io.openshop.*;
import android.content.*;

public class MyInstanceIDListenerService extends InstanceIDListenerService
{
    @Override
    public void onTokenRefresh() {
        SettingsMy.setTokenSentToServer(false);
        this.startService(new Intent((Context)this, (Class)MyRegistrationIntentService.class));
    }
}
