package android.support.v7.widget;

import android.support.v7.view.menu.*;
import android.view.*;

private class MenuBuilderCallback implements Callback
{
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        return ActionMenuView.access$200(ActionMenuView.this) != null && ActionMenuView.access$200(ActionMenuView.this).onMenuItemClick(menuItem);
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
        if (ActionMenuView.access$300(ActionMenuView.this) != null) {
            ActionMenuView.access$300(ActionMenuView.this).onMenuModeChange(menuBuilder);
        }
    }
}
