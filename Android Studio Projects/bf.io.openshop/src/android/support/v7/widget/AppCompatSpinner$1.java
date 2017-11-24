package android.support.v7.widget;

import android.view.*;

class AppCompatSpinner$1 extends ForwardingListener {
    final /* synthetic */ DropdownPopup val$popup;
    
    @Override
    public ListPopupWindow getPopup() {
        return this.val$popup;
    }
    
    public boolean onForwardingStarted() {
        if (!AppCompatSpinner.access$000(AppCompatSpinner.this).isShowing()) {
            AppCompatSpinner.access$000(AppCompatSpinner.this).show();
        }
        return true;
    }
}