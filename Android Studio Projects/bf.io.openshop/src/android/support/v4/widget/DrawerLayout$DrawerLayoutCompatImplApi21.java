package android.support.v4.widget;

import android.view.*;
import android.content.*;
import android.graphics.drawable.*;

static class DrawerLayoutCompatImplApi21 implements DrawerLayoutCompatImpl
{
    @Override
    public void applyMarginInsets(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final Object o, final int n) {
        DrawerLayoutCompatApi21.applyMarginInsets(viewGroup$MarginLayoutParams, o, n);
    }
    
    @Override
    public void configureApplyInsets(final View view) {
        DrawerLayoutCompatApi21.configureApplyInsets(view);
    }
    
    @Override
    public void dispatchChildInsets(final View view, final Object o, final int n) {
        DrawerLayoutCompatApi21.dispatchChildInsets(view, o, n);
    }
    
    @Override
    public Drawable getDefaultStatusBarBackground(final Context context) {
        return DrawerLayoutCompatApi21.getDefaultStatusBarBackground(context);
    }
    
    @Override
    public int getTopInset(final Object o) {
        return DrawerLayoutCompatApi21.getTopInset(o);
    }
}
