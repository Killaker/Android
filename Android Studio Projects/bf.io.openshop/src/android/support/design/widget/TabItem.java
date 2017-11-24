package android.support.design.widget;

import android.view.*;
import android.graphics.drawable.*;
import android.content.*;
import android.util.*;
import android.support.design.*;
import android.support.v7.widget.*;

public final class TabItem extends View
{
    final int mCustomLayout;
    final Drawable mIcon;
    final CharSequence mText;
    
    public TabItem(final Context context) {
        this(context, null);
    }
    
    public TabItem(final Context context, final AttributeSet set) {
        super(context, set);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.TabItem);
        this.mText = obtainStyledAttributes.getText(R.styleable.TabItem_android_text);
        this.mIcon = obtainStyledAttributes.getDrawable(R.styleable.TabItem_android_icon);
        this.mCustomLayout = obtainStyledAttributes.getResourceId(R.styleable.TabItem_android_layout, 0);
        obtainStyledAttributes.recycle();
    }
}
