package android.support.design.widget;

import android.view.animation.*;

class FloatingActionButtonEclairMr1$2 extends AnimationListenerAdapter {
    final /* synthetic */ InternalVisibilityChangedListener val$listener;
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        if (this.val$listener != null) {
            this.val$listener.onShown();
        }
    }
}