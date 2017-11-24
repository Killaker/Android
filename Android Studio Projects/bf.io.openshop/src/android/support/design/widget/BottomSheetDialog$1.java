package android.support.design.widget;

import android.view.*;

class BottomSheetDialog$1 implements View$OnClickListener {
    public void onClick(final View view) {
        if (BottomSheetDialog.this.isShowing()) {
            BottomSheetDialog.this.cancel();
        }
    }
}