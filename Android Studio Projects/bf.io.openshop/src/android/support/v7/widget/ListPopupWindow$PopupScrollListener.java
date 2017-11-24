package android.support.v7.widget;

import android.widget.*;

private class PopupScrollListener implements AbsListView$OnScrollListener
{
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
        if (n == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.access$1100(ListPopupWindow.this).getContentView() != null) {
            ListPopupWindow.access$1300(ListPopupWindow.this).removeCallbacks((Runnable)ListPopupWindow.access$1200(ListPopupWindow.this));
            ListPopupWindow.access$1200(ListPopupWindow.this).run();
        }
    }
}
