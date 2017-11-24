package android.support.v7.widget;

import android.view.*;

class PopupMenu$1 extends ForwardingListener {
    @Override
    public ListPopupWindow getPopup() {
        return PopupMenu.access$000(PopupMenu.this).getPopup();
    }
    
    @Override
    protected boolean onForwardingStarted() {
        PopupMenu.this.show();
        return true;
    }
    
    @Override
    protected boolean onForwardingStopped() {
        PopupMenu.this.dismiss();
        return true;
    }
}