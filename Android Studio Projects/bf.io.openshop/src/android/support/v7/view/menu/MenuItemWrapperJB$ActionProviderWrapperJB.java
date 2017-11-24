package android.support.v7.view.menu;

import android.support.v4.view.*;
import android.content.*;
import android.view.*;

class ActionProviderWrapperJB extends ActionProviderWrapper implements ActionProvider$VisibilityListener
{
    VisibilityListener mListener;
    
    public ActionProviderWrapperJB(final Context context, final android.view.ActionProvider actionProvider) {
        super(context, actionProvider);
    }
    
    public boolean isVisible() {
        return this.mInner.isVisible();
    }
    
    public void onActionProviderVisibilityChanged(final boolean b) {
        if (this.mListener != null) {
            this.mListener.onActionProviderVisibilityChanged(b);
        }
    }
    
    public View onCreateActionView(final MenuItem menuItem) {
        return this.mInner.onCreateActionView(menuItem);
    }
    
    public boolean overridesItemVisibility() {
        return this.mInner.overridesItemVisibility();
    }
    
    public void refreshVisibility() {
        this.mInner.refreshVisibility();
    }
    
    public void setVisibilityListener(final VisibilityListener mListener) {
        this.mListener = mListener;
        final android.view.ActionProvider mInner = this.mInner;
        final ActionProvider$VisibilityListener visibilityListener;
        if (mListener == null) {
            visibilityListener = null;
        }
        mInner.setVisibilityListener(visibilityListener);
    }
}
