package android.support.v7.widget;

import java.util.*;
import java.lang.ref.*;
import android.content.res.*;
import android.content.*;
import android.support.annotation.*;

public class TintContextWrapper extends ContextWrapper
{
    private static final ArrayList<WeakReference<TintContextWrapper>> sCache;
    private Resources mResources;
    private final Resources$Theme mTheme;
    
    static {
        sCache = new ArrayList<WeakReference<TintContextWrapper>>();
    }
    
    private TintContextWrapper(@NonNull final Context context) {
        super(context);
        (this.mTheme = this.getResources().newTheme()).setTo(context.getTheme());
    }
    
    private static boolean shouldWrap(@NonNull final Context context) {
        return !(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources);
    }
    
    public static Context wrap(@NonNull final Context context) {
        if (shouldWrap(context)) {
            for (int i = 0; i < TintContextWrapper.sCache.size(); ++i) {
                final WeakReference<TintContextWrapper> weakReference = TintContextWrapper.sCache.get(i);
                TintContextWrapper tintContextWrapper;
                if (weakReference != null) {
                    tintContextWrapper = weakReference.get();
                }
                else {
                    tintContextWrapper = null;
                }
                if (tintContextWrapper != null && tintContextWrapper.getBaseContext() == context) {
                    return (Context)tintContextWrapper;
                }
            }
            final TintContextWrapper tintContextWrapper2 = new TintContextWrapper(context);
            TintContextWrapper.sCache.add(new WeakReference<TintContextWrapper>(tintContextWrapper2));
            return (Context)tintContextWrapper2;
        }
        return context;
    }
    
    public Resources getResources() {
        if (this.mResources == null) {
            this.mResources = new TintResources((Context)this, super.getResources());
        }
        return this.mResources;
    }
    
    public Resources$Theme getTheme() {
        return this.mTheme;
    }
    
    public void setTheme(final int n) {
        this.mTheme.applyStyle(n, true);
    }
}
