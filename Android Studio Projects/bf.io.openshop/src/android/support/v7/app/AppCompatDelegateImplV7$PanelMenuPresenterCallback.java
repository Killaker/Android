package android.support.v7.app;

import android.support.v7.view.menu.*;
import android.view.*;

private final class PanelMenuPresenterCallback implements MenuPresenter.Callback
{
    @Override
    public void onCloseMenu(MenuBuilder menuBuilder, final boolean b) {
        final Object rootMenu = menuBuilder.getRootMenu();
        boolean b2;
        if (rootMenu != menuBuilder) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final AppCompatDelegateImplV7 this$0 = AppCompatDelegateImplV7.this;
        if (b2) {
            menuBuilder = (MenuBuilder)rootMenu;
        }
        final PanelFeatureState access$800 = AppCompatDelegateImplV7.access$800(this$0, (Menu)menuBuilder);
        if (access$800 != null) {
            if (!b2) {
                AppCompatDelegateImplV7.access$1000(AppCompatDelegateImplV7.this, access$800, b);
                return;
            }
            AppCompatDelegateImplV7.access$900(AppCompatDelegateImplV7.this, access$800.featureId, access$800, (Menu)rootMenu);
            AppCompatDelegateImplV7.access$1000(AppCompatDelegateImplV7.this, access$800, true);
        }
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        if (menuBuilder == null && AppCompatDelegateImplV7.this.mHasActionBar) {
            final Window$Callback windowCallback = AppCompatDelegateImplV7.this.getWindowCallback();
            if (windowCallback != null && !AppCompatDelegateImplV7.this.isDestroyed()) {
                windowCallback.onMenuOpened(108, (Menu)menuBuilder);
            }
        }
        return true;
    }
}
