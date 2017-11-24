package android.support.v4.widget;

import android.view.*;
import android.content.*;
import android.graphics.drawable.*;

interface DrawerLayoutCompatImpl
{
    void applyMarginInsets(final ViewGroup$MarginLayoutParams p0, final Object p1, final int p2);
    
    void configureApplyInsets(final View p0);
    
    void dispatchChildInsets(final View p0, final Object p1, final int p2);
    
    Drawable getDefaultStatusBarBackground(final Context p0);
    
    int getTopInset(final Object p0);
}
