package android.support.v7.widget;

import android.os.*;
import android.support.annotation.*;
import android.graphics.drawable.*;
import android.support.v4.graphics.drawable.*;
import android.util.*;
import java.lang.reflect.*;
import android.graphics.*;

class DrawableUtils
{
    public static final Rect INSETS_NONE;
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class<?> sInsetsClazz;
    
    static {
        INSETS_NONE = new Rect();
        if (Build$VERSION.SDK_INT < 18) {
            return;
        }
        try {
            DrawableUtils.sInsetsClazz = Class.forName("android.graphics.Insets");
        }
        catch (ClassNotFoundException ex) {}
    }
    
    static boolean canSafelyMutateDrawable(@NonNull final Drawable drawable) {
        if (drawable instanceof LayerDrawable) {
            if (Build$VERSION.SDK_INT < 16) {
                return false;
            }
        }
        else if (drawable instanceof InsetDrawable) {
            if (Build$VERSION.SDK_INT < 14) {
                return false;
            }
        }
        else if (drawable instanceof StateListDrawable) {
            if (Build$VERSION.SDK_INT < 8) {
                return false;
            }
        }
        else if (drawable instanceof GradientDrawable) {
            if (Build$VERSION.SDK_INT < 14) {
                return false;
            }
        }
        else if (drawable instanceof DrawableContainer) {
            final Drawable$ConstantState constantState = drawable.getConstantState();
            if (constantState instanceof DrawableContainer$DrawableContainerState) {
                final Drawable[] children = ((DrawableContainer$DrawableContainerState)constantState).getChildren();
                for (int length = children.length, i = 0; i < length; ++i) {
                    if (!canSafelyMutateDrawable(children[i])) {
                        return false;
                    }
                }
            }
        }
        else {
            if (drawable instanceof DrawableWrapper) {
                return canSafelyMutateDrawable(((DrawableWrapper)drawable).getWrappedDrawable());
            }
            if (drawable instanceof android.support.v7.graphics.drawable.DrawableWrapper) {
                return canSafelyMutateDrawable(((android.support.v7.graphics.drawable.DrawableWrapper)drawable).getWrappedDrawable());
            }
        }
        return true;
    }
    
    static void fixDrawable(@NonNull final Drawable drawable) {
        if (Build$VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }
    
    private static void fixVectorDrawableTinting(final Drawable drawable) {
        final int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(ThemeUtils.CHECKED_STATE_SET);
        }
        else {
            drawable.setState(ThemeUtils.EMPTY_STATE_SET);
        }
        drawable.setState(state);
    }
    
    public static Rect getOpticalBounds(final Drawable drawable) {
        if (DrawableUtils.sInsetsClazz != null) {
            while (true) {
            Label_0200_Outer:
                while (true) {
                    int n = 0;
                Label_0304:
                    while (true) {
                        Object invoke = null;
                        Rect insets_NONE = null;
                        Field field = null;
                        int n2 = 0;
                        Label_0271: {
                            try {
                                final Drawable unwrap = DrawableCompat.unwrap(drawable);
                                invoke = unwrap.getClass().getMethod("getOpticalInsets", (Class<?>[])new Class[0]).invoke(unwrap, new Object[0]);
                                if (invoke == null) {
                                    break;
                                }
                                insets_NONE = new Rect();
                                final Field[] fields = DrawableUtils.sInsetsClazz.getFields();
                                final int length = fields.length;
                                n = 0;
                                if (n >= length) {
                                    return insets_NONE;
                                }
                                field = fields[n];
                                final String name = field.getName();
                                n2 = -1;
                                switch (name.hashCode()) {
                                    case 3317767: {
                                        if (name.equals("left")) {
                                            n2 = 0;
                                        }
                                        break Label_0271;
                                    }
                                    case 115029: {
                                        if (name.equals("top")) {
                                            n2 = 1;
                                        }
                                        break Label_0271;
                                    }
                                    case 108511772: {
                                        if (name.equals("right")) {
                                            n2 = 2;
                                        }
                                        break Label_0271;
                                    }
                                    case -1383228885: {
                                        if (name.equals("bottom")) {
                                            n2 = 3;
                                        }
                                        break Label_0271;
                                    }
                                    default: {
                                        break Label_0271;
                                    }
                                }
                                insets_NONE.left = field.getInt(invoke);
                                break Label_0304;
                            }
                            catch (Exception ex) {
                                Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
                            }
                            break;
                        }
                        switch (n2) {
                            case 0: {
                                continue;
                            }
                            case 1: {
                                insets_NONE.top = field.getInt(invoke);
                                break;
                            }
                            case 2: {
                                insets_NONE.right = field.getInt(invoke);
                                break;
                            }
                            case 3: {
                                insets_NONE.bottom = field.getInt(invoke);
                                break;
                            }
                        }
                        break;
                    }
                    ++n;
                    continue Label_0200_Outer;
                }
            }
        }
        return DrawableUtils.INSETS_NONE;
    }
    
    static PorterDuff$Mode parseTintMode(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
            case 16: {
                if (Build$VERSION.SDK_INT >= 11) {
                    return PorterDuff$Mode.valueOf("ADD");
                }
                break;
            }
        }
        return porterDuff$Mode;
    }
}
