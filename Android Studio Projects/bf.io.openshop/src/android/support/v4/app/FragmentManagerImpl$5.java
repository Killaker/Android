package android.support.v4.app;

import android.view.*;
import android.view.animation.*;

class FragmentManagerImpl$5 extends AnimateOnHWLayerIfNeededListener {
    final /* synthetic */ Fragment val$fragment;
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        super.onAnimationEnd(animation);
        if (this.val$fragment.mAnimatingAway != null) {
            this.val$fragment.mAnimatingAway = null;
            FragmentManagerImpl.this.moveToState(this.val$fragment, this.val$fragment.mStateAfterAnimating, 0, 0, false);
        }
    }
}