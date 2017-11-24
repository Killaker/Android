package android.support.v4.widget;

import android.widget.*;

static class GingerbreadPopupWindowImpl extends BasePopupWindowImpl
{
    @Override
    public int getWindowLayoutType(final PopupWindow popupWindow) {
        return PopupWindowCompatGingerbread.getWindowLayoutType(popupWindow);
    }
    
    @Override
    public void setWindowLayoutType(final PopupWindow popupWindow, final int n) {
        PopupWindowCompatGingerbread.setWindowLayoutType(popupWindow, n);
    }
}
