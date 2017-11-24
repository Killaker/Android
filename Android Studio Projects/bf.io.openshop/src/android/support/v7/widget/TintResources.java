package android.support.v7.widget;

import java.lang.ref.*;
import android.content.*;
import android.support.annotation.*;
import android.graphics.drawable.*;
import android.content.res.*;

public class TintResources extends Resources
{
    private final WeakReference<Context> mContextRef;
    
    public TintResources(@NonNull final Context context, @NonNull final Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mContextRef = new WeakReference<Context>(context);
    }
    
    public Drawable getDrawable(final int n) throws Resources$NotFoundException {
        final Context context = this.mContextRef.get();
        if (context != null) {
            return AppCompatDrawableManager.get().onDrawableLoadedFromResources(context, this, n);
        }
        return super.getDrawable(n);
    }
    
    final Drawable superGetDrawable(final int n) {
        return super.getDrawable(n);
    }
}
