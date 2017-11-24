package android.support.v7.view.menu;

import android.widget.*;
import android.support.v7.view.*;
import android.view.*;

static class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView
{
    final android.view.CollapsibleActionView mWrappedView;
    
    CollapsibleActionViewWrapper(final View view) {
        super(view.getContext());
        this.mWrappedView = (android.view.CollapsibleActionView)view;
        this.addView(view);
    }
    
    View getWrappedView() {
        return (View)this.mWrappedView;
    }
    
    public void onActionViewCollapsed() {
        this.mWrappedView.onActionViewCollapsed();
    }
    
    public void onActionViewExpanded() {
        this.mWrappedView.onActionViewExpanded();
    }
}
