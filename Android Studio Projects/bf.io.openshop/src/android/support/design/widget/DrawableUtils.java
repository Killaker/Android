package android.support.design.widget;

import java.lang.reflect.*;
import android.os.*;
import android.util.*;
import android.graphics.drawable.*;

class DrawableUtils
{
    private static final String LOG_TAG = "DrawableUtils";
    private static Field sDrawableContainerStateField;
    private static boolean sDrawableContainerStateFieldFetched;
    private static Method sSetConstantStateMethod;
    private static boolean sSetConstantStateMethodFetched;
    
    static boolean setContainerConstantState(final DrawableContainer drawableContainer, final Drawable$ConstantState drawable$ConstantState) {
        if (Build$VERSION.SDK_INT >= 9) {
            return setContainerConstantStateV9(drawableContainer, drawable$ConstantState);
        }
        return setContainerConstantStateV7(drawableContainer, drawable$ConstantState);
    }
    
    private static boolean setContainerConstantStateV7(final DrawableContainer drawableContainer, final Drawable$ConstantState drawable$ConstantState) {
        while (true) {
            if (!DrawableUtils.sDrawableContainerStateFieldFetched) {
                while (true) {
                    try {
                        (DrawableUtils.sDrawableContainerStateField = DrawableContainer.class.getDeclaredField("mDrawableContainerStateField")).setAccessible(true);
                        DrawableUtils.sDrawableContainerStateFieldFetched = true;
                        if (DrawableUtils.sDrawableContainerStateField != null) {
                            final Field field = DrawableUtils.sDrawableContainerStateField;
                            final DrawableContainer drawableContainer2 = drawableContainer;
                            final Drawable$ConstantState drawable$ConstantState2 = drawable$ConstantState;
                            field.set(drawableContainer2, drawable$ConstantState2);
                            return true;
                        }
                        return false;
                    }
                    catch (NoSuchFieldException ex) {
                        Log.e("DrawableUtils", "Could not fetch mDrawableContainerStateField. Oh well.");
                        continue;
                    }
                    break;
                }
                try {
                    final Field field = DrawableUtils.sDrawableContainerStateField;
                    final DrawableContainer drawableContainer2 = drawableContainer;
                    final Drawable$ConstantState drawable$ConstantState2 = drawable$ConstantState;
                    field.set(drawableContainer2, drawable$ConstantState2);
                    return true;
                }
                catch (Exception ex2) {
                    Log.e("DrawableUtils", "Could not set mDrawableContainerStateField. Oh well.");
                }
                return false;
            }
            continue;
        }
    }
    
    private static boolean setContainerConstantStateV9(final DrawableContainer drawableContainer, final Drawable$ConstantState drawable$ConstantState) {
        while (true) {
            if (!DrawableUtils.sSetConstantStateMethodFetched) {
                while (true) {
                    try {
                        (DrawableUtils.sSetConstantStateMethod = DrawableContainer.class.getDeclaredMethod("setConstantState", DrawableContainer$DrawableContainerState.class)).setAccessible(true);
                        DrawableUtils.sSetConstantStateMethodFetched = true;
                        if (DrawableUtils.sSetConstantStateMethod != null) {
                            final Method method = DrawableUtils.sSetConstantStateMethod;
                            final DrawableContainer drawableContainer2 = drawableContainer;
                            final int n = 1;
                            final Object[] array = new Object[n];
                            final int n2 = 0;
                            final Drawable$ConstantState drawable$ConstantState2 = drawable$ConstantState;
                            array[n2] = drawable$ConstantState2;
                            method.invoke(drawableContainer2, array);
                            return true;
                        }
                        return false;
                    }
                    catch (NoSuchMethodException ex) {
                        Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
                        continue;
                    }
                    break;
                }
                try {
                    final Method method = DrawableUtils.sSetConstantStateMethod;
                    final DrawableContainer drawableContainer2 = drawableContainer;
                    final int n = 1;
                    final Object[] array = new Object[n];
                    final int n2 = 0;
                    final Drawable$ConstantState drawable$ConstantState2 = drawable$ConstantState;
                    array[n2] = drawable$ConstantState2;
                    method.invoke(drawableContainer2, array);
                    return true;
                }
                catch (Exception ex2) {
                    Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
                }
                return false;
            }
            continue;
        }
    }
}
