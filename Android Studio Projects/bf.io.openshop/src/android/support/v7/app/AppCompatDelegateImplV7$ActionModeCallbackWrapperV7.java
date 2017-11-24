package android.support.v7.app;

import android.support.v7.view.*;
import android.widget.*;
import android.content.*;
import android.util.*;
import android.support.v7.appcompat.*;
import android.text.*;
import android.support.v7.view.menu.*;
import android.graphics.drawable.*;
import android.view.accessibility.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.support.v4.view.*;

class ActionModeCallbackWrapperV7 implements ActionMode.Callback
{
    private ActionMode.Callback mWrapped;
    
    public ActionModeCallbackWrapperV7(final ActionMode.Callback mWrapped) {
        this.mWrapped = mWrapped;
    }
    
    @Override
    public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
        return this.mWrapped.onActionItemClicked(actionMode, menuItem);
    }
    
    @Override
    public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrapped.onCreateActionMode(actionMode, menu);
    }
    
    @Override
    public void onDestroyActionMode(final ActionMode actionMode) {
        this.mWrapped.onDestroyActionMode(actionMode);
        if (AppCompatDelegateImplV7.this.mActionModePopup != null) {
            AppCompatDelegateImplV7.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV7.this.mShowActionModePopup);
        }
        if (AppCompatDelegateImplV7.this.mActionModeView != null) {
            AppCompatDelegateImplV7.access$500(AppCompatDelegateImplV7.this);
            (AppCompatDelegateImplV7.this.mFadeAnim = ViewCompat.animate((View)AppCompatDelegateImplV7.this.mActionModeView).alpha(0.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final View view) {
                    AppCompatDelegateImplV7.this.mActionModeView.setVisibility(8);
                    if (AppCompatDelegateImplV7.this.mActionModePopup != null) {
                        AppCompatDelegateImplV7.this.mActionModePopup.dismiss();
                    }
                    else if (AppCompatDelegateImplV7.this.mActionModeView.getParent() instanceof View) {
                        ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV7.this.mActionModeView.getParent());
                    }
                    AppCompatDelegateImplV7.this.mActionModeView.removeAllViews();
                    AppCompatDelegateImplV7.this.mFadeAnim.setListener(null);
                    AppCompatDelegateImplV7.this.mFadeAnim = null;
                }
            });
        }
        if (AppCompatDelegateImplV7.this.mAppCompatCallback != null) {
            AppCompatDelegateImplV7.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImplV7.this.mActionMode);
        }
        AppCompatDelegateImplV7.this.mActionMode = null;
    }
    
    @Override
    public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrapped.onPrepareActionMode(actionMode, menu);
    }
}
