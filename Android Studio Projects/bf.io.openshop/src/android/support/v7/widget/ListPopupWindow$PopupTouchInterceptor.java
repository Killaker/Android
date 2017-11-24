package android.support.v7.widget;

import android.view.*;

private class PopupTouchInterceptor implements View$OnTouchListener
{
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        final int n = (int)motionEvent.getX();
        final int n2 = (int)motionEvent.getY();
        if (action == 0 && ListPopupWindow.access$1100(ListPopupWindow.this) != null && ListPopupWindow.access$1100(ListPopupWindow.this).isShowing() && n >= 0 && n < ListPopupWindow.access$1100(ListPopupWindow.this).getWidth() && n2 >= 0 && n2 < ListPopupWindow.access$1100(ListPopupWindow.this).getHeight()) {
            ListPopupWindow.access$1300(ListPopupWindow.this).postDelayed((Runnable)ListPopupWindow.access$1200(ListPopupWindow.this), 250L);
        }
        else if (action == 1) {
            ListPopupWindow.access$1300(ListPopupWindow.this).removeCallbacks((Runnable)ListPopupWindow.access$1200(ListPopupWindow.this));
        }
        return false;
    }
}
