package android.support.v4.widget;

import android.widget.*;

static class Api21PopupWindowImpl extends KitKatPopupWindowImpl
{
    @Override
    public boolean getOverlapAnchor(final PopupWindow popupWindow) {
        return PopupWindowCompatApi21.getOverlapAnchor(popupWindow);
    }
    
    @Override
    public void setOverlapAnchor(final PopupWindow popupWindow, final boolean b) {
        PopupWindowCompatApi21.setOverlapAnchor(popupWindow, b);
    }
}
