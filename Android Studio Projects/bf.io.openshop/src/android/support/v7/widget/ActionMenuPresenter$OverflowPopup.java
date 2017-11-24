package android.support.v7.widget;

import android.content.*;
import android.view.*;
import android.support.v7.appcompat.*;
import android.support.v7.view.menu.*;

private class OverflowPopup extends MenuPopupHelper
{
    public OverflowPopup(final Context context, final MenuBuilder menuBuilder, final View view, final boolean b) {
        super(context, menuBuilder, view, b, R.attr.actionOverflowMenuStyle);
        this.setGravity(8388613);
        this.setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    @Override
    public void onDismiss() {
        super.onDismiss();
        if (ActionMenuPresenter.access$400(ActionMenuPresenter.this) != null) {
            ActionMenuPresenter.access$500(ActionMenuPresenter.this).close();
        }
        ActionMenuPresenter.access$202(ActionMenuPresenter.this, null);
    }
}
