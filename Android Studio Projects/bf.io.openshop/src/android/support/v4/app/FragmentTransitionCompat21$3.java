package android.support.v4.app;

import android.graphics.*;
import android.transition.*;

static final class FragmentTransitionCompat21$3 extends Transition$EpicenterCallback {
    private Rect mEpicenter;
    final /* synthetic */ EpicenterView val$epicenterView;
    
    public Rect onGetEpicenter(final Transition transition) {
        if (this.mEpicenter == null && this.val$epicenterView.epicenter != null) {
            this.mEpicenter = FragmentTransitionCompat21.access$100(this.val$epicenterView.epicenter);
        }
        return this.mEpicenter;
    }
}