package android.support.v4.widget;

import android.widget.*;

static class JbTextViewCompatImpl extends BaseTextViewCompatImpl
{
    @Override
    public int getMaxLines(final TextView textView) {
        return TextViewCompatJb.getMaxLines(textView);
    }
    
    @Override
    public int getMinLines(final TextView textView) {
        return TextViewCompatJb.getMinLines(textView);
    }
}
