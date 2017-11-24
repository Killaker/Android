package android.support.v7.widget;

import android.view.*;

class ScrollingTabContainerView$1 implements Runnable {
    final /* synthetic */ View val$tabView;
    
    @Override
    public void run() {
        ScrollingTabContainerView.this.smoothScrollTo(this.val$tabView.getLeft() - (ScrollingTabContainerView.this.getWidth() - this.val$tabView.getWidth()) / 2, 0);
        ScrollingTabContainerView.this.mTabSelector = null;
    }
}