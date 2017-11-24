package android.support.v7.widget;

import android.view.*;

class Toolbar$1 implements ActionMenuView.OnMenuItemClickListener {
    @Override
    public boolean onMenuItemClick(final MenuItem menuItem) {
        return Toolbar.access$000(Toolbar.this) != null && Toolbar.access$000(Toolbar.this).onMenuItemClick(menuItem);
    }
}