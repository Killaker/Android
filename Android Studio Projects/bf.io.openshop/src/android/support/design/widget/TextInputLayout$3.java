package android.support.design.widget;

import android.support.v4.view.*;
import android.view.*;

class TextInputLayout$3 extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ CharSequence val$error;
    
    @Override
    public void onAnimationEnd(final View view) {
        TextInputLayout.access$400(TextInputLayout.this).setText(this.val$error);
        view.setVisibility(4);
    }
}