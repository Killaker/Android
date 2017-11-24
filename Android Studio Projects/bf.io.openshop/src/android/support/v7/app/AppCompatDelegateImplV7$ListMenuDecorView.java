package android.support.v7.app;

import android.content.*;
import android.view.*;
import android.support.v7.widget.*;

private class ListMenuDecorView extends ContentFrameLayout
{
    public ListMenuDecorView(final Context context) {
        super(context);
    }
    
    private boolean isOutOfBounds(final int n, final int n2) {
        return n < -5 || n2 < -5 || n > 5 + this.getWidth() || n2 > 5 + this.getHeight();
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return AppCompatDelegateImplV7.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && this.isOutOfBounds((int)motionEvent.getX(), (int)motionEvent.getY())) {
            AppCompatDelegateImplV7.access$1400(AppCompatDelegateImplV7.this, 0);
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
    
    public void setBackgroundResource(final int n) {
        this.setBackgroundDrawable(AppCompatDrawableManager.get().getDrawable(this.getContext(), n));
    }
}
