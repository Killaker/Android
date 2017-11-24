package android.support.v7.view.menu;

import android.support.v7.widget.*;
import android.view.*;

private class ActionMenuItemForwardingListener extends ForwardingListener
{
    public ActionMenuItemForwardingListener() {
        super((View)ActionMenuItemView.this);
    }
    
    @Override
    public ListPopupWindow getPopup() {
        if (ActionMenuItemView.access$000(ActionMenuItemView.this) != null) {
            return ActionMenuItemView.access$000(ActionMenuItemView.this).getPopup();
        }
        return null;
    }
    
    @Override
    protected boolean onForwardingStarted() {
        final ItemInvoker access$100 = ActionMenuItemView.access$100(ActionMenuItemView.this);
        boolean b = false;
        if (access$100 != null) {
            final boolean invokeItem = ActionMenuItemView.access$100(ActionMenuItemView.this).invokeItem(ActionMenuItemView.access$200(ActionMenuItemView.this));
            b = false;
            if (invokeItem) {
                final ListPopupWindow popup = this.getPopup();
                b = false;
                if (popup != null) {
                    final boolean showing = popup.isShowing();
                    b = false;
                    if (showing) {
                        b = true;
                    }
                }
            }
        }
        return b;
    }
}
