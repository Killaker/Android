package android.support.v4.app;

import android.graphics.drawable.*;
import android.support.annotation.*;

public interface Delegate
{
    @Nullable
    Drawable getThemeUpIndicator();
    
    void setActionBarDescription(@StringRes final int p0);
    
    void setActionBarUpIndicator(final Drawable p0, @StringRes final int p1);
}
