package bf.io.openshop.ux;

import android.app.*;
import timber.log.*;
import android.content.*;

public class RestartAppActivity extends Activity
{
    private static String TAG;
    
    static {
        RestartAppActivity.TAG = RestartAppActivity.class.getSimpleName();
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        Timber.tag(RestartAppActivity.TAG);
        Timber.d("---------- onShopChange - finish old instances -----------", new Object[0]);
        this.finish();
    }
    
    protected void onResume() {
        super.onResume();
        Timber.tag(RestartAppActivity.TAG);
        Timber.d("---------- onShopChange starting new instance. -----------", new Object[0]);
        this.startActivityForResult(new Intent((Context)this, (Class)SplashActivity.class), 0);
    }
}
