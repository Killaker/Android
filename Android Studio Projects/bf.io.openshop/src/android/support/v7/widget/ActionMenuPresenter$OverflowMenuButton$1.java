package android.support.v7.widget;

import android.view.*;

class ActionMenuPresenter$OverflowMenuButton$1 extends ForwardingListener {
    @Override
    public ListPopupWindow getPopup() {
        if (ActionMenuPresenter.access$200(OverflowMenuButton.this.this$0) == null) {
            return null;
        }
        return ActionMenuPresenter.access$200(OverflowMenuButton.this.this$0).getPopup();
    }
    
    public boolean onForwardingStarted() {
        OverflowMenuButton.this.this$0.showOverflowMenu();
        return true;
    }
    
    public boolean onForwardingStopped() {
        if (ActionMenuPresenter.access$300(OverflowMenuButton.this.this$0) != null) {
            return false;
        }
        OverflowMenuButton.this.this$0.hideOverflowMenu();
        return true;
    }
}