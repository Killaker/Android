package android.support.v4.view;

import android.view.*;

static class OnActionExpandListenerWrapper implements MenuItem$OnActionExpandListener
{
    private SupportActionExpandProxy mWrapped;
    
    public OnActionExpandListenerWrapper(final SupportActionExpandProxy mWrapped) {
        this.mWrapped = mWrapped;
    }
    
    public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
        return this.mWrapped.onMenuItemActionCollapse(menuItem);
    }
    
    public boolean onMenuItemActionExpand(final MenuItem menuItem) {
        return this.mWrapped.onMenuItemActionExpand(menuItem);
    }
}
