package bf.io.openshop.ux.fragments;

import android.view.*;
import android.os.*;

class DrawerFragment$2 implements View$OnClickListener {
    private long mLastClickTime = 0L;
    
    public void onClick(final View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000L) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        DrawerFragment.access$200(DrawerFragment.this);
    }
}