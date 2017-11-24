package android.support.v4.app;

import android.view.*;
import java.util.*;
import android.support.v4.util.*;

class BackStackRecord$2 implements ViewTreeObserver$OnPreDrawListener {
    final /* synthetic */ Fragment val$inFragment;
    final /* synthetic */ boolean val$isBack;
    final /* synthetic */ Fragment val$outFragment;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementTargets;
    final /* synthetic */ Object val$sharedElementTransition;
    final /* synthetic */ TransitionState val$state;
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        if (this.val$sharedElementTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
            this.val$sharedElementTargets.clear();
            final ArrayMap access$000 = BackStackRecord.access$000(BackStackRecord.this, this.val$state, this.val$isBack, this.val$inFragment);
            FragmentTransitionCompat21.setSharedElementTargets(this.val$sharedElementTransition, this.val$state.nonExistentView, access$000, this.val$sharedElementTargets);
            BackStackRecord.access$100(BackStackRecord.this, access$000, this.val$state);
            BackStackRecord.access$200(BackStackRecord.this, this.val$state, this.val$inFragment, this.val$outFragment, this.val$isBack, access$000);
        }
        return true;
    }
}