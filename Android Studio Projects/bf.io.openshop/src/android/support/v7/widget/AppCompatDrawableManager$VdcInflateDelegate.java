package android.support.v7.widget;

import android.content.*;
import org.xmlpull.v1.*;
import android.content.res.*;
import android.support.annotation.*;
import android.graphics.drawable.*;
import android.support.graphics.drawable.*;
import android.util.*;

private static class VdcInflateDelegate implements InflateDelegate
{
    @Override
    public Drawable createFromXmlInner(@NonNull final Context context, @NonNull final XmlPullParser xmlPullParser, @NonNull final AttributeSet set, @Nullable final Resources$Theme resources$Theme) {
        try {
            return VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlPullParser, set, resources$Theme);
        }
        catch (Exception ex) {
            Log.e("VdcInflateDelegate", "Exception while inflating <vector>", (Throwable)ex);
            return null;
        }
    }
}
