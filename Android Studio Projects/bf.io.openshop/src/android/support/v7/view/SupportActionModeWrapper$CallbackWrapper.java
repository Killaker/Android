package android.support.v7.view;

import java.util.*;
import android.content.*;
import android.support.v4.util.*;
import android.support.v7.view.menu.*;
import android.view.*;
import android.support.v4.internal.view.*;

public static class CallbackWrapper implements Callback
{
    final ArrayList<SupportActionModeWrapper> mActionModes;
    final Context mContext;
    final SimpleArrayMap<Menu, Menu> mMenus;
    final ActionMode$Callback mWrappedCallback;
    
    public CallbackWrapper(final Context mContext, final ActionMode$Callback mWrappedCallback) {
        this.mContext = mContext;
        this.mWrappedCallback = mWrappedCallback;
        this.mActionModes = new ArrayList<SupportActionModeWrapper>();
        this.mMenus = new SimpleArrayMap<Menu, Menu>();
    }
    
    private Menu getMenuWrapper(final Menu menu) {
        Menu wrapSupportMenu = this.mMenus.get(menu);
        if (wrapSupportMenu == null) {
            wrapSupportMenu = MenuWrapperFactory.wrapSupportMenu(this.mContext, (SupportMenu)menu);
            this.mMenus.put(menu, wrapSupportMenu);
        }
        return wrapSupportMenu;
    }
    
    public android.view.ActionMode getActionModeWrapper(final ActionMode actionMode) {
        for (int i = 0; i < this.mActionModes.size(); ++i) {
            final SupportActionModeWrapper supportActionModeWrapper = this.mActionModes.get(i);
            if (supportActionModeWrapper != null && supportActionModeWrapper.mWrappedObject == actionMode) {
                return supportActionModeWrapper;
            }
        }
        final SupportActionModeWrapper supportActionModeWrapper2 = new SupportActionModeWrapper(this.mContext, actionMode);
        this.mActionModes.add(supportActionModeWrapper2);
        return supportActionModeWrapper2;
    }
    
    @Override
    public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
        return this.mWrappedCallback.onActionItemClicked(this.getActionModeWrapper(actionMode), MenuWrapperFactory.wrapSupportMenuItem(this.mContext, (SupportMenuItem)menuItem));
    }
    
    @Override
    public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrappedCallback.onCreateActionMode(this.getActionModeWrapper(actionMode), this.getMenuWrapper(menu));
    }
    
    @Override
    public void onDestroyActionMode(final ActionMode actionMode) {
        this.mWrappedCallback.onDestroyActionMode(this.getActionModeWrapper(actionMode));
    }
    
    @Override
    public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrappedCallback.onPrepareActionMode(this.getActionModeWrapper(actionMode), this.getMenuWrapper(menu));
    }
}
