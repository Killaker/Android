package android.support.design.widget;

import android.view.*;
import android.support.annotation.*;

public abstract static class BottomSheetCallback
{
    public abstract void onSlide(@NonNull final View p0, final float p1);
    
    public abstract void onStateChanged(@NonNull final View p0, final int p1);
}
