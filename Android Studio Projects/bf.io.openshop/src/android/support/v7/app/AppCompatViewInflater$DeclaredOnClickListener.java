package android.support.v7.app;

import android.view.*;
import android.support.annotation.*;
import android.content.*;
import java.lang.reflect.*;

private static class DeclaredOnClickListener implements View$OnClickListener
{
    private final View mHostView;
    private final String mMethodName;
    private Context mResolvedContext;
    private Method mResolvedMethod;
    
    public DeclaredOnClickListener(@NonNull final View mHostView, @NonNull final String mMethodName) {
        this.mHostView = mHostView;
        this.mMethodName = mMethodName;
    }
    
    @NonNull
    private void resolveMethod(@Nullable Context baseContext, @NonNull final String s) {
        while (baseContext != null) {
            try {
                if (!baseContext.isRestricted()) {
                    final Method method = baseContext.getClass().getMethod(this.mMethodName, View.class);
                    if (method != null) {
                        this.mResolvedMethod = method;
                        this.mResolvedContext = baseContext;
                        return;
                    }
                }
            }
            catch (NoSuchMethodException ex) {}
            if (baseContext instanceof ContextWrapper) {
                baseContext = ((ContextWrapper)baseContext).getBaseContext();
            }
            else {
                baseContext = null;
            }
        }
        final int id = this.mHostView.getId();
        String string;
        if (id == -1) {
            string = "";
        }
        else {
            string = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'";
        }
        throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.mHostView.getClass() + string);
    }
    
    public void onClick(@NonNull final View view) {
        if (this.mResolvedMethod == null) {
            this.resolveMethod(this.mHostView.getContext(), this.mMethodName);
        }
        try {
            this.mResolvedMethod.invoke(this.mResolvedContext, view);
        }
        catch (IllegalAccessException ex) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", ex);
        }
        catch (InvocationTargetException ex2) {
            throw new IllegalStateException("Could not execute method for android:onClick", ex2);
        }
    }
}
