package android.support.v7.app;

import android.support.v7.view.*;
import android.view.*;

private class ToolbarCallbackWrapper extends WindowCallbackWrapper
{
    public ToolbarCallbackWrapper(final Window$Callback window$Callback) {
        super(window$Callback);
    }
    
    @Override
    public View onCreatePanelView(final int n) {
        switch (n) {
            case 0: {
                final Menu menu = ToolbarActionBar.access$300(ToolbarActionBar.this).getMenu();
                if (this.onPreparePanel(n, null, menu) && this.onMenuOpened(n, menu)) {
                    return ToolbarActionBar.access$400(ToolbarActionBar.this, menu);
                }
                break;
            }
        }
        return super.onCreatePanelView(n);
    }
    
    @Override
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        final boolean onPreparePanel = super.onPreparePanel(n, view, menu);
        if (onPreparePanel && !ToolbarActionBar.access$200(ToolbarActionBar.this)) {
            ToolbarActionBar.access$300(ToolbarActionBar.this).setMenuPrepared();
            ToolbarActionBar.access$202(ToolbarActionBar.this, true);
        }
        return onPreparePanel;
    }
}
