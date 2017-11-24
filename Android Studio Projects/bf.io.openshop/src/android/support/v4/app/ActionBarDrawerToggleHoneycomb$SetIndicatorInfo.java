package android.support.v4.app;

import java.lang.reflect.*;
import android.widget.*;
import android.app.*;
import android.graphics.drawable.*;
import android.view.*;

private static class SetIndicatorInfo
{
    public Method setHomeActionContentDescription;
    public Method setHomeAsUpIndicator;
    public ImageView upIndicatorView;
    
    SetIndicatorInfo(final Activity activity) {
        try {
            this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
            this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
        }
        catch (NoSuchMethodException ex) {
            final View viewById = activity.findViewById(16908332);
            if (viewById == null) {
                return;
            }
            final ViewGroup viewGroup = (ViewGroup)viewById.getParent();
            if (viewGroup.getChildCount() != 2) {
                return;
            }
            final View child = viewGroup.getChildAt(0);
            final View child2 = viewGroup.getChildAt(1);
            Object o;
            if (child.getId() == 16908332) {
                o = child2;
            }
            else {
                o = child;
            }
            if (o instanceof ImageView) {
                this.upIndicatorView = (ImageView)o;
            }
        }
    }
}
