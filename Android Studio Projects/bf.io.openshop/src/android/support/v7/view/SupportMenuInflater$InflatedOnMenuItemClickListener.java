package android.support.v7.view;

import java.lang.reflect.*;
import android.view.*;

private static class InflatedOnMenuItemClickListener implements MenuItem$OnMenuItemClickListener
{
    private static final Class<?>[] PARAM_TYPES;
    private Method mMethod;
    private Object mRealOwner;
    
    static {
        PARAM_TYPES = new Class[] { MenuItem.class };
    }
    
    public InflatedOnMenuItemClickListener(final Object mRealOwner, final String s) {
        this.mRealOwner = mRealOwner;
        final Class<?> class1 = mRealOwner.getClass();
        try {
            this.mMethod = class1.getMethod(s, InflatedOnMenuItemClickListener.PARAM_TYPES);
        }
        catch (Exception ex2) {
            final InflateException ex = new InflateException("Couldn't resolve menu item onClick handler " + s + " in class " + class1.getName());
            ex.initCause((Throwable)ex2);
            throw ex;
        }
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        try {
            if (this.mMethod.getReturnType() == Boolean.TYPE) {
                return (boolean)this.mMethod.invoke(this.mRealOwner, menuItem);
            }
            this.mMethod.invoke(this.mRealOwner, menuItem);
            return true;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
