package android.support.v7.app;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.support.v7.appcompat.*;
import android.text.*;
import android.support.v7.view.*;
import android.support.v7.view.menu.*;
import android.graphics.drawable.*;
import android.view.accessibility.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.support.v4.view.*;

class AppCompatDelegateImplV7$ActionModeCallbackWrapperV7$1 extends ViewPropertyAnimatorListenerAdapter {
    @Override
    public void onAnimationEnd(final View view) {
        ActionModeCallbackWrapperV7.this.this$0.mActionModeView.setVisibility(8);
        if (ActionModeCallbackWrapperV7.this.this$0.mActionModePopup != null) {
            ActionModeCallbackWrapperV7.this.this$0.mActionModePopup.dismiss();
        }
        else if (ActionModeCallbackWrapperV7.this.this$0.mActionModeView.getParent() instanceof View) {
            ViewCompat.requestApplyInsets((View)ActionModeCallbackWrapperV7.this.this$0.mActionModeView.getParent());
        }
        ActionModeCallbackWrapperV7.this.this$0.mActionModeView.removeAllViews();
        ActionModeCallbackWrapperV7.this.this$0.mFadeAnim.setListener(null);
        ActionModeCallbackWrapperV7.this.this$0.mFadeAnim = null;
    }
}