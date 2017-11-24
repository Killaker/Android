package android.support.v4.app;

import android.view.*;

class ListFragment$1 implements Runnable {
    @Override
    public void run() {
        ListFragment.this.mList.focusableViewAvailable((View)ListFragment.this.mList);
    }
}