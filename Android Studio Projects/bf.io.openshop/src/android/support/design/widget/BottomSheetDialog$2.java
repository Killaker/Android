package android.support.design.widget;

import android.view.*;
import android.support.annotation.*;

class BottomSheetDialog$2 extends BottomSheetCallback {
    @Override
    public void onSlide(@NonNull final View view, final float n) {
    }
    
    @Override
    public void onStateChanged(@NonNull final View view, final int n) {
        if (n == 5) {
            BottomSheetDialog.this.dismiss();
        }
    }
}