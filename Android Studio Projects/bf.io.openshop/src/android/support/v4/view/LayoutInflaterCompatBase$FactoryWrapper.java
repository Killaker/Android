package android.support.v4.view;

import android.content.*;
import android.util.*;
import android.view.*;

static class FactoryWrapper implements LayoutInflater$Factory
{
    final LayoutInflaterFactory mDelegateFactory;
    
    FactoryWrapper(final LayoutInflaterFactory mDelegateFactory) {
        this.mDelegateFactory = mDelegateFactory;
    }
    
    public View onCreateView(final String s, final Context context, final AttributeSet set) {
        return this.mDelegateFactory.onCreateView(null, s, context, set);
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + "{" + this.mDelegateFactory + "}";
    }
}
