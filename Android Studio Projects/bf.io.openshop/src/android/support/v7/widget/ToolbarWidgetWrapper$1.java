package android.support.v7.widget;

import android.support.v7.view.menu.*;
import android.view.*;

class ToolbarWidgetWrapper$1 implements View$OnClickListener {
    final ActionMenuItem mNavItem = new ActionMenuItem(ToolbarWidgetWrapper.access$000(ToolbarWidgetWrapper.this).getContext(), 0, 16908332, 0, 0, ToolbarWidgetWrapper.access$100(ToolbarWidgetWrapper.this));
    
    public void onClick(final View view) {
        if (ToolbarWidgetWrapper.access$200(ToolbarWidgetWrapper.this) != null && ToolbarWidgetWrapper.access$300(ToolbarWidgetWrapper.this)) {
            ToolbarWidgetWrapper.access$200(ToolbarWidgetWrapper.this).onMenuItemSelected(0, (MenuItem)this.mNavItem);
        }
    }
}