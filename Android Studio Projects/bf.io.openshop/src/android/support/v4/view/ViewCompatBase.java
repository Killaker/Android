package android.support.v4.view;

import java.lang.reflect.*;
import android.content.res.*;
import android.graphics.*;
import android.view.*;

class ViewCompatBase
{
    private static final String TAG = "ViewCompatBase";
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;
    
    static ColorStateList getBackgroundTintList(final View view) {
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView)view).getSupportBackgroundTintList();
        }
        return null;
    }
    
    static PorterDuff$Mode getBackgroundTintMode(final View view) {
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView)view).getSupportBackgroundTintMode();
        }
        return null;
    }
    
    static int getMinimumHeight(final View view) {
        Label_0027: {
            if (ViewCompatBase.sMinHeightFieldFetched) {
                break Label_0027;
            }
            while (true) {
                try {
                    (ViewCompatBase.sMinHeightField = View.class.getDeclaredField("mMinHeight")).setAccessible(true);
                    ViewCompatBase.sMinHeightFieldFetched = true;
                    if (ViewCompatBase.sMinHeightField != null) {
                        try {
                            return (int)ViewCompatBase.sMinHeightField.get(view);
                        }
                        catch (Exception ex) {}
                    }
                    return 0;
                }
                catch (NoSuchFieldException ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    static int getMinimumWidth(final View view) {
        Label_0027: {
            if (ViewCompatBase.sMinWidthFieldFetched) {
                break Label_0027;
            }
            while (true) {
                try {
                    (ViewCompatBase.sMinWidthField = View.class.getDeclaredField("mMinWidth")).setAccessible(true);
                    ViewCompatBase.sMinWidthFieldFetched = true;
                    if (ViewCompatBase.sMinWidthField != null) {
                        try {
                            return (int)ViewCompatBase.sMinWidthField.get(view);
                        }
                        catch (Exception ex) {}
                    }
                    return 0;
                }
                catch (NoSuchFieldException ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    static boolean isAttachedToWindow(final View view) {
        return view.getWindowToken() != null;
    }
    
    static boolean isLaidOut(final View view) {
        return view.getWidth() > 0 && view.getHeight() > 0;
    }
    
    static void offsetLeftAndRight(final View view, final int n) {
        final int left = view.getLeft();
        view.offsetLeftAndRight(n);
        if (n != 0) {
            final ViewParent parent = view.getParent();
            if (!(parent instanceof View)) {
                view.invalidate();
                return;
            }
            final int abs = Math.abs(n);
            ((View)parent).invalidate(left - abs, view.getTop(), abs + (left + view.getWidth()), view.getBottom());
        }
    }
    
    static void offsetTopAndBottom(final View view, final int n) {
        final int top = view.getTop();
        view.offsetTopAndBottom(n);
        if (n != 0) {
            final ViewParent parent = view.getParent();
            if (!(parent instanceof View)) {
                view.invalidate();
                return;
            }
            final int abs = Math.abs(n);
            ((View)parent).invalidate(view.getLeft(), top - abs, view.getRight(), abs + (top + view.getHeight()));
        }
    }
    
    static void setBackgroundTintList(final View view, final ColorStateList supportBackgroundTintList) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view).setSupportBackgroundTintList(supportBackgroundTintList);
        }
    }
    
    static void setBackgroundTintMode(final View view, final PorterDuff$Mode supportBackgroundTintMode) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view).setSupportBackgroundTintMode(supportBackgroundTintMode);
        }
    }
}
