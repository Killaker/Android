package android.support.v7.widget;

import android.support.v4.view.*;
import android.view.*;

private class ResizePopupRunnable implements Runnable
{
    @Override
    public void run() {
        if (ListPopupWindow.access$600(ListPopupWindow.this) != null && ViewCompat.isAttachedToWindow((View)ListPopupWindow.access$600(ListPopupWindow.this)) && ListPopupWindow.access$600(ListPopupWindow.this).getCount() > ListPopupWindow.access$600(ListPopupWindow.this).getChildCount() && ListPopupWindow.access$600(ListPopupWindow.this).getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
            ListPopupWindow.access$1100(ListPopupWindow.this).setInputMethodMode(2);
            ListPopupWindow.this.show();
        }
    }
}
