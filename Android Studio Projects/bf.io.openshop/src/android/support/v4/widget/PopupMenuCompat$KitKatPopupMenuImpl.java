package android.support.v4.widget;

import android.view.*;

static class KitKatPopupMenuImpl extends BasePopupMenuImpl
{
    @Override
    public View$OnTouchListener getDragToOpenListener(final Object o) {
        return PopupMenuCompatKitKat.getDragToOpenListener(o);
    }
}
