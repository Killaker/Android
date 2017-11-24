package android.support.v7.widget;

import android.support.v7.view.menu.*;

private class ActionMenuPopupCallback extends PopupCallback
{
    @Override
    public ListPopupWindow getPopup() {
        if (ActionMenuPresenter.access$800(ActionMenuPresenter.this) != null) {
            return ActionMenuPresenter.access$800(ActionMenuPresenter.this).getPopup();
        }
        return null;
    }
}
