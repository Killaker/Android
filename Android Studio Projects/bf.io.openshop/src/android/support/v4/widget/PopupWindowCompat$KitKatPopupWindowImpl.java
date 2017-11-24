package android.support.v4.widget;

import android.widget.*;
import android.view.*;

static class KitKatPopupWindowImpl extends GingerbreadPopupWindowImpl
{
    @Override
    public void showAsDropDown(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        PopupWindowCompatKitKat.showAsDropDown(popupWindow, view, n, n2, n3);
    }
}
