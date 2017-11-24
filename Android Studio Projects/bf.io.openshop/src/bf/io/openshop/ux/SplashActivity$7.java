package bf.io.openshop.ux;

import com.android.volley.*;
import bf.io.openshop.utils.*;

class SplashActivity$7 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (SplashActivity.access$000(SplashActivity.this) != null) {
            SplashActivity.access$000(SplashActivity.this).cancel();
        }
        MsgUtils.logAndShowErrorMessage(SplashActivity.access$500(SplashActivity.this), volleyError);
        SplashActivity.this.finish();
    }
}