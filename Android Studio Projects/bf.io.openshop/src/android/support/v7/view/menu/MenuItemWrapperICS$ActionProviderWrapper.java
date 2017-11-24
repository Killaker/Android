package android.support.v7.view.menu;

import android.support.v4.view.*;
import android.content.*;
import android.view.*;

class ActionProviderWrapper extends ActionProvider
{
    final android.view.ActionProvider mInner;
    
    public ActionProviderWrapper(final Context context, final android.view.ActionProvider mInner) {
        super(context);
        this.mInner = mInner;
    }
    
    @Override
    public boolean hasSubMenu() {
        return this.mInner.hasSubMenu();
    }
    
    @Override
    public View onCreateActionView() {
        return this.mInner.onCreateActionView();
    }
    
    @Override
    public boolean onPerformDefaultAction() {
        return this.mInner.onPerformDefaultAction();
    }
    
    @Override
    public void onPrepareSubMenu(final SubMenu subMenu) {
        this.mInner.onPrepareSubMenu(MenuItemWrapperICS.this.getSubMenuWrapper(subMenu));
    }
}
