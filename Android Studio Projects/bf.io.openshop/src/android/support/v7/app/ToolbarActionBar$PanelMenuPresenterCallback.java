package android.support.v7.app;

import android.support.v7.view.menu.*;
import android.view.*;

private final class PanelMenuPresenterCallback implements Callback
{
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (ToolbarActionBar.access$000(ToolbarActionBar.this) != null) {
            ToolbarActionBar.access$000(ToolbarActionBar.this).onPanelClosed(0, (Menu)menuBuilder);
        }
    }
    
    @Override
    public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
        if (menuBuilder == null && ToolbarActionBar.access$000(ToolbarActionBar.this) != null) {
            ToolbarActionBar.access$000(ToolbarActionBar.this).onMenuOpened(0, (Menu)menuBuilder);
        }
        return true;
    }
}
