package android.support.v4.widget;

import android.widget.*;
import android.view.*;

static class BasePopupWindowImpl implements PopupWindowImpl
{
    @Override
    public boolean getOverlapAnchor(final PopupWindow popupWindow) {
        return false;
    }
    
    @Override
    public int getWindowLayoutType(final PopupWindow popupWindow) {
        return 0;
    }
    
    @Override
    public void setOverlapAnchor(final PopupWindow popupWindow, final boolean b) {
    }
    
    @Override
    public void setWindowLayoutType(final PopupWindow popupWindow, final int n) {
    }
    
    @Override
    public void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        popupWindow.showAsDropDown(view, n, n2);
    }
}
