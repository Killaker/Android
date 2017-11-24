package android.support.v7.widget;

import android.view.*;

class ActivityChooserView$3 extends ForwardingListener {
    @Override
    public ListPopupWindow getPopup() {
        return ActivityChooserView.access$100(ActivityChooserView.this);
    }
    
    @Override
    protected boolean onForwardingStarted() {
        ActivityChooserView.this.showPopup();
        return true;
    }
    
    @Override
    protected boolean onForwardingStopped() {
        ActivityChooserView.this.dismissPopup();
        return true;
    }
}