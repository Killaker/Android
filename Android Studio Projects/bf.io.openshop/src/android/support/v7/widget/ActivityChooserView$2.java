package android.support.v7.widget;

import android.view.*;

class ActivityChooserView$2 implements ViewTreeObserver$OnGlobalLayoutListener {
    public void onGlobalLayout() {
        if (ActivityChooserView.this.isShowingPopup()) {
            if (!ActivityChooserView.this.isShown()) {
                ActivityChooserView.access$100(ActivityChooserView.this).dismiss();
            }
            else {
                ActivityChooserView.access$100(ActivityChooserView.this).show();
                if (ActivityChooserView.this.mProvider != null) {
                    ActivityChooserView.this.mProvider.subUiVisibilityChanged(true);
                }
            }
        }
    }
}