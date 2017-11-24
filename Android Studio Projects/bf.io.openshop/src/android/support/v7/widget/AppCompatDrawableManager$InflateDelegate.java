package android.support.v7.widget;

import android.content.*;
import org.xmlpull.v1.*;
import android.util.*;
import android.content.res.*;
import android.support.annotation.*;
import android.graphics.drawable.*;

private interface InflateDelegate
{
    Drawable createFromXmlInner(@NonNull final Context p0, @NonNull final XmlPullParser p1, @NonNull final AttributeSet p2, @Nullable final Resources$Theme p3);
}
