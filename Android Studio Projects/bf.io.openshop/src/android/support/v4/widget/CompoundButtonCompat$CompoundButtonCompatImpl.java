package android.support.v4.widget;

import android.widget.*;
import android.graphics.drawable.*;
import android.content.res.*;
import android.graphics.*;

interface CompoundButtonCompatImpl
{
    Drawable getButtonDrawable(final CompoundButton p0);
    
    ColorStateList getButtonTintList(final CompoundButton p0);
    
    PorterDuff$Mode getButtonTintMode(final CompoundButton p0);
    
    void setButtonTintList(final CompoundButton p0, final ColorStateList p1);
    
    void setButtonTintMode(final CompoundButton p0, final PorterDuff$Mode p1);
}
