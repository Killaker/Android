package android.support.design.widget;

import android.view.animation.*;

class FloatingActionButtonEclairMr1$1 extends AnimationListenerAdapter {
    final /* synthetic */ boolean val$fromUser;
    final /* synthetic */ InternalVisibilityChangedListener val$listener;
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        FloatingActionButtonEclairMr1.access$202(FloatingActionButtonEclairMr1.this, false);
        FloatingActionButtonEclairMr1.this.mView.internalSetVisibility(8, this.val$fromUser);
        if (this.val$listener != null) {
            this.val$listener.onHidden();
        }
    }
    
    @Override
    public void onAnimationStart(final Animation animation) {
        FloatingActionButtonEclairMr1.access$202(FloatingActionButtonEclairMr1.this, true);
    }
}