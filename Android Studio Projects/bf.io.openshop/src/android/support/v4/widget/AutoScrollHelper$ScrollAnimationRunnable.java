package android.support.v4.widget;

import android.support.v4.view.*;

private class ScrollAnimationRunnable implements Runnable
{
    @Override
    public void run() {
        if (!AutoScrollHelper.access$100(AutoScrollHelper.this)) {
            return;
        }
        if (AutoScrollHelper.access$200(AutoScrollHelper.this)) {
            AutoScrollHelper.access$202(AutoScrollHelper.this, false);
            AutoScrollHelper.access$300(AutoScrollHelper.this).start();
        }
        final ClampedScroller access$300 = AutoScrollHelper.access$300(AutoScrollHelper.this);
        if (access$300.isFinished() || !AutoScrollHelper.access$400(AutoScrollHelper.this)) {
            AutoScrollHelper.access$102(AutoScrollHelper.this, false);
            return;
        }
        if (AutoScrollHelper.access$500(AutoScrollHelper.this)) {
            AutoScrollHelper.access$502(AutoScrollHelper.this, false);
            AutoScrollHelper.access$600(AutoScrollHelper.this);
        }
        access$300.computeScrollDelta();
        AutoScrollHelper.this.scrollTargetBy(access$300.getDeltaX(), access$300.getDeltaY());
        ViewCompat.postOnAnimation(AutoScrollHelper.access$700(AutoScrollHelper.this), this);
    }
}
