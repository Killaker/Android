package android.support.v7.app;

import android.support.v7.view.*;
import android.support.v7.view.menu.*;
import android.view.*;

class AppCompatWindowCallbackBase extends WindowCallbackWrapper
{
    AppCompatWindowCallbackBase(final Window$Callback window$Callback) {
        super(window$Callback);
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return AppCompatDelegateImplBase.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }
    
    @Override
    public boolean dispatchKeyShortcutEvent(final KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImplBase.this.onKeyShortcut(keyEvent.getKeyCode(), keyEvent);
    }
    
    @Override
    public void onContentChanged() {
    }
    
    @Override
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        return (n != 0 || menu instanceof MenuBuilder) && super.onCreatePanelMenu(n, menu);
    }
    
    @Override
    public boolean onMenuOpened(final int n, final Menu menu) {
        super.onMenuOpened(n, menu);
        AppCompatDelegateImplBase.this.onMenuOpened(n, menu);
        return true;
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        super.onPanelClosed(n, menu);
        AppCompatDelegateImplBase.this.onPanelClosed(n, menu);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        MenuBuilder menuBuilder;
        if (menu instanceof MenuBuilder) {
            menuBuilder = (MenuBuilder)menu;
        }
        else {
            menuBuilder = null;
        }
        boolean onPreparePanel;
        if (n == 0 && menuBuilder == null) {
            onPreparePanel = false;
        }
        else {
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            onPreparePanel = super.onPreparePanel(n, view, menu);
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
                return onPreparePanel;
            }
        }
        return onPreparePanel;
    }
}
