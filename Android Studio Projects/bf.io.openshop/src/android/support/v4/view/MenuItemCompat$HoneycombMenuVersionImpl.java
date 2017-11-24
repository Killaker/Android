package android.support.v4.view;

import android.view.*;

static class HoneycombMenuVersionImpl implements MenuVersionImpl
{
    @Override
    public boolean collapseActionView(final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public boolean expandActionView(final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public View getActionView(final MenuItem menuItem) {
        return MenuItemCompatHoneycomb.getActionView(menuItem);
    }
    
    @Override
    public boolean isActionViewExpanded(final MenuItem menuItem) {
        return false;
    }
    
    @Override
    public MenuItem setActionView(final MenuItem menuItem, final int n) {
        return MenuItemCompatHoneycomb.setActionView(menuItem, n);
    }
    
    @Override
    public MenuItem setActionView(final MenuItem menuItem, final View view) {
        return MenuItemCompatHoneycomb.setActionView(menuItem, view);
    }
    
    @Override
    public MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
        return menuItem;
    }
    
    @Override
    public void setShowAsAction(final MenuItem menuItem, final int n) {
        MenuItemCompatHoneycomb.setShowAsAction(menuItem, n);
    }
}
