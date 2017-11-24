package android.support.v7.app;

import android.support.v7.view.menu.*;
import android.view.*;

private final class ActionMenuPresenterCallback implements Callback
{
    private boolean mClosingActionMenu;
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        ToolbarActionBar.access$300(ToolbarActionBar.this).dismissPopupMenus();
        if (ToolbarActionBar.access$000(ToolbarActionBar.this) != null) {
            ToolbarActionBar.access$000(ToolbarActionBar.this).onPanelClosed(108, (Menu)menuBuilder);
        }
        this.mClosingActionMenu = false;
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        if (ToolbarActionBar.access$000(ToolbarActionBar.this) != null) {
            ToolbarActionBar.access$000(ToolbarActionBar.this).onMenuOpened(108, (Menu)menuBuilder);
            return true;
        }
        return false;
    }
}
