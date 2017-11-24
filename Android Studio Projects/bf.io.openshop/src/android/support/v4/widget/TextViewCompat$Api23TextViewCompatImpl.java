package android.support.v4.widget;

import android.widget.*;
import android.support.annotation.*;

static class Api23TextViewCompatImpl extends JbMr2TextViewCompatImpl
{
    @Override
    public void setTextAppearance(@NonNull final TextView textView, @StyleRes final int n) {
        TextViewCompatApi23.setTextAppearance(textView, n);
    }
}
