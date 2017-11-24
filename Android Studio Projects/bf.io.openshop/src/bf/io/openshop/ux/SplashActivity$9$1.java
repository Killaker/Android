package bf.io.openshop.ux;

import android.os.*;
import timber.log.*;
import android.view.*;
import android.view.animation.*;
import android.animation.*;

class SplashActivity$9$1 implements Runnable {
    @Override
    public void run() {
        if (Build$VERSION.SDK_INT > 21) {
            Timber.d("Circular animation.", new Object[0]);
            final int n = (SplashActivity.access$700(Runnable.this.this$0).getLeft() + SplashActivity.access$700(Runnable.this.this$0).getRight()) / 2;
            final int n2 = (SplashActivity.access$700(Runnable.this.this$0).getTop() + SplashActivity.access$700(Runnable.this.this$0).getBottom()) / 2;
            final Animator circularReveal = ViewAnimationUtils.createCircularReveal(SplashActivity.access$700(Runnable.this.this$0), n, n2, 0.0f, (float)Math.hypot(Math.max(n, SplashActivity.access$700(Runnable.this.this$0).getWidth() - n), Math.max(n2, SplashActivity.access$700(Runnable.this.this$0).getHeight() - n2)));
            circularReveal.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
            circularReveal.setDuration(1250L);
            SplashActivity.access$700(Runnable.this.this$0).setVisibility(0);
            circularReveal.start();
            return;
        }
        Timber.d("Alpha animation.", new Object[0]);
        SplashActivity.access$700(Runnable.this.this$0).setAlpha(0.0f);
        SplashActivity.access$700(Runnable.this.this$0).setVisibility(0);
        SplashActivity.access$700(Runnable.this.this$0).animate().alpha(1.0f).setDuration(1000L).setListener((Animator$AnimatorListener)null);
    }
}