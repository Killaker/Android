package android.support.v4.app;

import android.view.*;
import android.content.*;
import android.util.*;
import android.os.*;

abstract class BaseFragmentActivityHoneycomb extends BaseFragmentActivityDonut
{
    public View onCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        View view2 = this.dispatchFragmentsOnCreateView(view, s, context, set);
        if (view2 == null && Build$VERSION.SDK_INT >= 11) {
            view2 = super.onCreateView(view, s, context, set);
        }
        return view2;
    }
}
