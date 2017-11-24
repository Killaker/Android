package bf.io.openshop.ux;

import android.view.*;
import android.os.*;

class SplashActivity$5 implements View$OnClickListener {
    public void onClick(final View view) {
        SplashActivity.access$000(SplashActivity.this).show();
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                SplashActivity.access$300(SplashActivity.this);
            }
        }, 1000L);
    }
}