package android.support.v7.widget;

import android.support.v7.view.menu.*;

private class PopupPresenterCallback implements Callback
{
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (menuBuilder instanceof SubMenuBuilder) {
            ((SubMenuBuilder)menuBuilder).getRootMenu().close(false);
        }
        final Callback callback = ActionMenuPresenter.this.getCallback();
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, b);
        }
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        if (menuBuilder == null) {
            return false;
        }
        ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)menuBuilder).getItem().getItemId();
        final Callback callback = ActionMenuPresenter.this.getCallback();
        return callback != null && callback.onOpenSubMenu(menuBuilder);
    }
}
