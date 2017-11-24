package android.support.v7.app;

import android.support.v7.view.menu.*;
import android.view.*;

private final class MenuBuilderCallback implements Callback
{
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
        if (ToolbarActionBar.access$000(ToolbarActionBar.this) != null) {
            if (ToolbarActionBar.access$300(ToolbarActionBar.this).isOverflowMenuShowing()) {
                ToolbarActionBar.access$000(ToolbarActionBar.this).onPanelClosed(108, (Menu)menuBuilder);
            }
            else if (ToolbarActionBar.access$000(ToolbarActionBar.this).onPreparePanel(0, (View)null, (Menu)menuBuilder)) {
                ToolbarActionBar.access$000(ToolbarActionBar.this).onMenuOpened(108, (Menu)menuBuilder);
            }
        }
    }
}
