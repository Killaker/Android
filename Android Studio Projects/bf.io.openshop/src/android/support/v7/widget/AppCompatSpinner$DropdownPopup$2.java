package android.support.v7.widget;

import android.view.*;

class AppCompatSpinner$DropdownPopup$2 implements ViewTreeObserver$OnGlobalLayoutListener {
    public void onGlobalLayout() {
        if (!DropdownPopup.access$600(DropdownPopup.this, (View)DropdownPopup.this.this$0)) {
            DropdownPopup.this.dismiss();
            return;
        }
        DropdownPopup.this.computeContentWidth();
        DropdownPopup.access$701(DropdownPopup.this);
    }
}