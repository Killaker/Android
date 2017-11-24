package android.support.v4.widget;

import android.widget.*;
import android.graphics.drawable.*;

static class Api23CompoundButtonImpl extends LollipopCompoundButtonImpl
{
    @Override
    public Drawable getButtonDrawable(final CompoundButton compoundButton) {
        return CompoundButtonCompatApi23.getButtonDrawable(compoundButton);
    }
}
