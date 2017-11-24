package android.support.v7.widget;

import android.view.*;

class ListPopupWindow$2 implements Runnable {
    @Override
    public void run() {
        final View anchorView = ListPopupWindow.this.getAnchorView();
        if (anchorView != null && anchorView.getWindowToken() != null) {
            ListPopupWindow.this.show();
        }
    }
}