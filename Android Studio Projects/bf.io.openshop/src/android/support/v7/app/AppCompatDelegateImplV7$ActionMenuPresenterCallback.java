package android.support.v7.app;

import android.support.v7.view.menu.*;
import android.view.*;

private final class ActionMenuPresenterCallback implements MenuPresenter.Callback
{
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        AppCompatDelegateImplV7.access$1100(AppCompatDelegateImplV7.this, menuBuilder);
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        final Window$Callback windowCallback = AppCompatDelegateImplV7.this.getWindowCallback();
        if (windowCallback != null) {
            windowCallback.onMenuOpened(108, (Menu)menuBuilder);
        }
        return true;
    }
}
