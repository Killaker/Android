package android.support.v4.widget;

import android.view.*;

static class KitKatListPopupWindowImpl extends BaseListPopupWindowImpl
{
    @Override
    public View$OnTouchListener createDragToOpenListener(final Object o, final View view) {
        return ListPopupWindowCompatKitKat.createDragToOpenListener(o, view);
    }
}
