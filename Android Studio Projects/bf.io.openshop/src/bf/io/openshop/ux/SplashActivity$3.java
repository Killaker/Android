package bf.io.openshop.ux;

import com.android.volley.*;
import bf.io.openshop.utils.*;
import android.os.*;

class SplashActivity$3 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        SplashActivity.access$000(SplashActivity.this).cancel();
        MsgUtils.logErrorMessage(volleyError);
        SplashActivity.this.startMainActivity(null);
    }
}