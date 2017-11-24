package android.support.multidex;

import android.app.*;
import android.content.*;

public class MultiDexApplication extends Application
{
    protected void attachBaseContext(final Context context) {
        super.attachBaseContext(context);
        MultiDex.install((Context)this);
    }
}
