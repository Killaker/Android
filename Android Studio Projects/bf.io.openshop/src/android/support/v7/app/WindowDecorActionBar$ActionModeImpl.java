package android.support.v7.app;

import android.content.*;
import java.lang.ref.*;
import android.support.v7.view.*;
import android.view.*;
import android.support.v7.view.menu.*;

public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback
{
    private final Context mActionModeContext;
    private ActionMode.Callback mCallback;
    private WeakReference<View> mCustomView;
    private final MenuBuilder mMenu;
    
    public ActionModeImpl(final Context mActionModeContext, final ActionMode.Callback mCallback) {
        this.mActionModeContext = mActionModeContext;
        this.mCallback = mCallback;
        (this.mMenu = new MenuBuilder(mActionModeContext).setDefaultShowAsAction(1)).setCallback((MenuBuilder.Callback)this);
    }
    
    public boolean dispatchOnCreate() {
        this.mMenu.stopDispatchingItemsChanged();
        try {
            return this.mCallback.onCreateActionMode(this, (Menu)this.mMenu);
        }
        finally {
            this.mMenu.startDispatchingItemsChanged();
        }
    }
    
    @Override
    public void finish() {
        if (WindowDecorActionBar.this.mActionMode != this) {
            return;
        }
        if (!WindowDecorActionBar.access$700(WindowDecorActionBar.access$500(WindowDecorActionBar.this), WindowDecorActionBar.access$600(WindowDecorActionBar.this), false)) {
            WindowDecorActionBar.this.mDeferredDestroyActionMode = this;
            WindowDecorActionBar.this.mDeferredModeDestroyCallback = this.mCallback;
        }
        else {
            this.mCallback.onDestroyActionMode(this);
        }
        this.mCallback = null;
        WindowDecorActionBar.this.animateToMode(false);
        WindowDecorActionBar.access$800(WindowDecorActionBar.this).closeMode();
        WindowDecorActionBar.access$900(WindowDecorActionBar.this).getViewGroup().sendAccessibilityEvent(32);
        WindowDecorActionBar.access$400(WindowDecorActionBar.this).setHideOnContentScrollEnabled(WindowDecorActionBar.this.mHideOnContentScroll);
        WindowDecorActionBar.this.mActionMode = null;
    }
    
    @Override
    public View getCustomView() {
        if (this.mCustomView != null) {
            return this.mCustomView.get();
        }
        return null;
    }
    
    @Override
    public Menu getMenu() {
        return (Menu)this.mMenu;
    }
    
    @Override
    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mActionModeContext);
    }
    
    @Override
    public CharSequence getSubtitle() {
        return WindowDecorActionBar.access$800(WindowDecorActionBar.this).getSubtitle();
    }
    
    @Override
    public CharSequence getTitle() {
        return WindowDecorActionBar.access$800(WindowDecorActionBar.this).getTitle();
    }
    
    @Override
    public void invalidate() {
        if (WindowDecorActionBar.this.mActionMode != this) {
            return;
        }
        this.mMenu.stopDispatchingItemsChanged();
        try {
            this.mCallback.onPrepareActionMode(this, (Menu)this.mMenu);
        }
        finally {
            this.mMenu.startDispatchingItemsChanged();
        }
    }
    
    @Override
    public boolean isTitleOptional() {
        return WindowDecorActionBar.access$800(WindowDecorActionBar.this).isTitleOptional();
    }
    
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
    }
    
    public void onCloseSubMenu(final SubMenuBuilder subMenuBuilder) {
    }
    
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        return this.mCallback != null && this.mCallback.onActionItemClicked(this, menuItem);
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
        if (this.mCallback == null) {
            return;
        }
        this.invalidate();
        WindowDecorActionBar.access$800(WindowDecorActionBar.this).showOverflowMenu();
    }
    
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        boolean b = true;
        if (this.mCallback == null) {
            b = false;
        }
        else if (subMenuBuilder.hasVisibleItems()) {
            new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenuBuilder).show();
            return b;
        }
        return b;
    }
    
    @Override
    public void setCustomView(final View customView) {
        WindowDecorActionBar.access$800(WindowDecorActionBar.this).setCustomView(customView);
        this.mCustomView = new WeakReference<View>(customView);
    }
    
    @Override
    public void setSubtitle(final int n) {
        this.setSubtitle(WindowDecorActionBar.access$1000(WindowDecorActionBar.this).getResources().getString(n));
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        WindowDecorActionBar.access$800(WindowDecorActionBar.this).setSubtitle(subtitle);
    }
    
    @Override
    public void setTitle(final int n) {
        this.setTitle(WindowDecorActionBar.access$1000(WindowDecorActionBar.this).getResources().getString(n));
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        WindowDecorActionBar.access$800(WindowDecorActionBar.this).setTitle(title);
    }
    
    @Override
    public void setTitleOptionalHint(final boolean b) {
        super.setTitleOptionalHint(b);
        WindowDecorActionBar.access$800(WindowDecorActionBar.this).setTitleOptional(b);
    }
}
