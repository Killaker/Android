package android.support.v7.widget;

import android.widget.*;
import android.view.*;

class AppCompatSpinner$DropdownPopup$3 implements PopupWindow$OnDismissListener {
    final /* synthetic */ ViewTreeObserver$OnGlobalLayoutListener val$layoutListener;
    
    public void onDismiss() {
        final ViewTreeObserver viewTreeObserver = DropdownPopup.this.this$0.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.val$layoutListener);
        }
    }
}