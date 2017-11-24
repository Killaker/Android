package bf.io.openshop.ux.dialogs;

import android.widget.*;
import android.view.*;

class MapDialogFragment$3 implements View$OnTouchListener {
    final /* synthetic */ ScrollView val$parentScrollView;
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            default: {
                return true;
            }
            case 0: {
                this.val$parentScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
            case 1: {
                this.val$parentScrollView.requestDisallowInterceptTouchEvent(false);
                return true;
            }
            case 2: {
                this.val$parentScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        }
    }
}