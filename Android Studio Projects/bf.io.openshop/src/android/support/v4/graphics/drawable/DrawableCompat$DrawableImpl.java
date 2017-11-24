package android.support.v4.graphics.drawable;

import android.graphics.drawable.*;
import android.util.*;
import java.io.*;
import org.xmlpull.v1.*;
import android.content.res.*;
import android.graphics.*;

interface DrawableImpl
{
    void applyTheme(final Drawable p0, final Resources$Theme p1);
    
    boolean canApplyTheme(final Drawable p0);
    
    int getAlpha(final Drawable p0);
    
    ColorFilter getColorFilter(final Drawable p0);
    
    int getLayoutDirection(final Drawable p0);
    
    void inflate(final Drawable p0, final Resources p1, final XmlPullParser p2, final AttributeSet p3, final Resources$Theme p4) throws IOException, XmlPullParserException;
    
    boolean isAutoMirrored(final Drawable p0);
    
    void jumpToCurrentState(final Drawable p0);
    
    void setAutoMirrored(final Drawable p0, final boolean p1);
    
    void setHotspot(final Drawable p0, final float p1, final float p2);
    
    void setHotspotBounds(final Drawable p0, final int p1, final int p2, final int p3, final int p4);
    
    void setLayoutDirection(final Drawable p0, final int p1);
    
    void setTint(final Drawable p0, final int p1);
    
    void setTintList(final Drawable p0, final ColorStateList p1);
    
    void setTintMode(final Drawable p0, final PorterDuff$Mode p1);
    
    Drawable wrap(final Drawable p0);
}
