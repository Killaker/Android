package bf.io.openshop.listeners;

import android.view.*;
import android.os.*;

public abstract class OnSingleClickListener implements View$OnClickListener
{
    private static final long MIN_CLICK_INTERVAL = 800L;
    private long mLastClickTime;
    
    public OnSingleClickListener() {
        this.mLastClickTime = 0L;
    }
    
    public final void onClick(final View view) {
        if (SystemClock.uptimeMillis() - this.mLastClickTime < 800L) {
            return;
        }
        this.mLastClickTime = SystemClock.uptimeMillis();
        this.onSingleClick(view);
    }
    
    public abstract void onSingleClick(final View p0);
}
