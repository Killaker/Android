package android.support.v4.app;

import android.view.*;
import android.graphics.*;
import android.os.*;
import android.content.*;
import java.util.*;

public abstract static class SharedElementCallback21
{
    public abstract Parcelable onCaptureSharedElementSnapshot(final View p0, final Matrix p1, final RectF p2);
    
    public abstract View onCreateSnapshotView(final Context p0, final Parcelable p1);
    
    public abstract void onMapSharedElements(final List<String> p0, final Map<String, View> p1);
    
    public abstract void onRejectSharedElements(final List<View> p0);
    
    public abstract void onSharedElementEnd(final List<String> p0, final List<View> p1, final List<View> p2);
    
    public abstract void onSharedElementStart(final List<String> p0, final List<View> p1, final List<View> p2);
}
