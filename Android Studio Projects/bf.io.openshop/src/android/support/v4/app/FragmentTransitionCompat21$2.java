package android.support.v4.app;

import android.view.*;
import android.transition.*;
import java.util.*;

static final class FragmentTransitionCompat21$2 implements ViewTreeObserver$OnPreDrawListener {
    final /* synthetic */ View val$container;
    final /* synthetic */ Transition val$enterTransition;
    final /* synthetic */ ArrayList val$enteringViews;
    final /* synthetic */ ViewRetriever val$inFragment;
    final /* synthetic */ Map val$nameOverrides;
    final /* synthetic */ View val$nonExistentView;
    final /* synthetic */ Map val$renamedViews;
    
    public boolean onPreDraw() {
        this.val$container.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        if (this.val$enterTransition != null) {
            this.val$enterTransition.removeTarget(this.val$nonExistentView);
        }
        final View view = this.val$inFragment.getView();
        if (view != null) {
            if (!this.val$nameOverrides.isEmpty()) {
                FragmentTransitionCompat21.findNamedViews(this.val$renamedViews, view);
                this.val$renamedViews.keySet().retainAll(this.val$nameOverrides.values());
                for (final Map.Entry<K, String> entry : this.val$nameOverrides.entrySet()) {
                    final View view2 = this.val$renamedViews.get(entry.getValue());
                    if (view2 != null) {
                        view2.setTransitionName((String)entry.getKey());
                    }
                }
            }
            if (this.val$enterTransition != null) {
                FragmentTransitionCompat21.access$000(this.val$enteringViews, view);
                this.val$enteringViews.removeAll(this.val$renamedViews.values());
                this.val$enteringViews.add(this.val$nonExistentView);
                FragmentTransitionCompat21.addTargets(this.val$enterTransition, this.val$enteringViews);
            }
        }
        return true;
    }
}