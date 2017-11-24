package android.support.v7.widget;

import android.support.v7.view.menu.*;

private class ActionMenuPresenterCallback implements MenuPresenter.Callback
{
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        return false;
    }
}
