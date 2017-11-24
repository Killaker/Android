package android.support.v4.widget;

import android.widget.*;
import android.graphics.drawable.*;
import android.support.annotation.*;

interface TextViewCompatImpl
{
    int getMaxLines(final TextView p0);
    
    int getMinLines(final TextView p0);
    
    void setCompoundDrawablesRelative(@NonNull final TextView p0, @Nullable final Drawable p1, @Nullable final Drawable p2, @Nullable final Drawable p3, @Nullable final Drawable p4);
    
    void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView p0, @DrawableRes final int p1, @DrawableRes final int p2, @DrawableRes final int p3, @DrawableRes final int p4);
    
    void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView p0, @Nullable final Drawable p1, @Nullable final Drawable p2, @Nullable final Drawable p3, @Nullable final Drawable p4);
    
    void setTextAppearance(@NonNull final TextView p0, @StyleRes final int p1);
}
