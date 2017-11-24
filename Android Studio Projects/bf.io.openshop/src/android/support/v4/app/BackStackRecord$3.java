package android.support.v4.app;

import android.view.*;

class BackStackRecord$3 implements ViewTreeObserver$OnPreDrawListener {
    final /* synthetic */ int val$containerId;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ TransitionState val$state;
    final /* synthetic */ Object val$transition;
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        BackStackRecord.access$300(BackStackRecord.this, this.val$state, this.val$containerId, this.val$transition);
        return true;
    }
}