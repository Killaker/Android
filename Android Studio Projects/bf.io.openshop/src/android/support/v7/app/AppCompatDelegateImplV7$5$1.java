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

class AppCompatDelegateImplV7$5$1 extends ViewPropertyAnimatorListenerAdapter {
    @Override
    public void onAnimationEnd(final View view) {
        ViewCompat.setAlpha((View)Runnable.this.this$0.mActionModeView, 1.0f);
        Runnable.this.this$0.mFadeAnim.setListener(null);
        Runnable.this.this$0.mFadeAnim = null;
    }
    
    @Override
    public void onAnimationStart(final View view) {
        Runnable.this.this$0.mActionModeView.setVisibility(0);
    }
}