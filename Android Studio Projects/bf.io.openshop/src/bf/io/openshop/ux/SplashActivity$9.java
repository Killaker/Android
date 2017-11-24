package bf.io.openshop.ux;

import android.os.*;
import timber.log.*;
import android.view.*;
import android.view.animation.*;
import android.animation.*;

class SplashActivity$9 implements Runnable {
    @Override
    public void run() {
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                if (Build$VERSION.SDK_INT > 21) {
                    Timber.d("Circular animation.", new Object[0]);
                    final int n = (SplashActivity.access$700(SplashActivity.this).getLeft() + SplashActivity.access$700(SplashActivity.this).getRight()) / 2;
                    final int n2 = (SplashActivity.access$700(SplashActivity.this).getTop() + SplashActivity.access$700(SplashActivity.this).getBottom()) / 2;
                    final Animator circularReveal = ViewAnimationUtils.createCircularReveal(SplashActivity.access$700(SplashActivity.this), n, n2, 0.0f, (float)Math.hypot(Math.max(n, SplashActivity.access$700(SplashActivity.this).getWidth() - n), Math.max(n2, SplashActivity.access$700(SplashActivity.this).getHeight() - n2)));
                    circularReveal.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
                    circularReveal.setDuration(1250L);
                    SplashActivity.access$700(SplashActivity.this).setVisibility(0);
                    circularReveal.start();
                    return;
                }
                Timber.d("Alpha animation.", new Object[0]);
                SplashActivity.access$700(SplashActivity.this).setAlpha(0.0f);
                SplashActivity.access$700(SplashActivity.this).setVisibility(0);
                SplashActivity.access$700(SplashActivity.this).animate().alpha(1.0f).setDuration(1000L).setListener((Animator$AnimatorListener)null);
            }
        }, 330L);
    }
}