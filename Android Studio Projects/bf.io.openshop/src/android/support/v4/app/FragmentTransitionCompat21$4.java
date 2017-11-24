package android.support.v4.app;

import android.transition.*;
import android.view.*;
import java.util.*;

static final class FragmentTransitionCompat21$4 implements ViewTreeObserver$OnPreDrawListener {
    final /* synthetic */ Transition val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ Transition val$exitTransition;
    final /* synthetic */ ArrayList val$exitingViews;
    final /* synthetic */ ArrayList val$hiddenViews;
    final /* synthetic */ View val$nonExistentView;
    final /* synthetic */ Transition val$overallTransition;
    final /* synthetic */ Map val$renamedViews;
    final /* synthetic */ View val$sceneRoot;
    final /* synthetic */ ArrayList val$sharedElementTargets;
    final /* synthetic */ Transition val$sharedElementTransition;
    
    public boolean onPreDraw() {
        this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        if (this.val$enterTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$enterTransition, this.val$enteringViews);
        }
        if (this.val$exitTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$exitTransition, this.val$exitingViews);
        }
        if (this.val$sharedElementTransition != null) {
            FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
        }
        for (final Map.Entry<K, View> entry : this.val$renamedViews.entrySet()) {
            entry.getValue().setTransitionName((String)entry.getKey());
        }
        for (int size = this.val$hiddenViews.size(), i = 0; i < size; ++i) {
            this.val$overallTransition.excludeTarget((View)this.val$hiddenViews.get(i), false);
        }
        this.val$overallTransition.excludeTarget(this.val$nonExistentView, false);
        return true;
    }
}