package android.support.v4.view;

import android.view.*;
import android.content.*;
import android.util.*;

static class FactoryWrapperHC extends FactoryWrapper implements LayoutInflater$Factory2
{
    FactoryWrapperHC(final LayoutInflaterFactory layoutInflaterFactory) {
        super(layoutInflaterFactory);
    }
    
    public View onCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        return this.mDelegateFactory.onCreateView(view, s, context, set);
    }
}
