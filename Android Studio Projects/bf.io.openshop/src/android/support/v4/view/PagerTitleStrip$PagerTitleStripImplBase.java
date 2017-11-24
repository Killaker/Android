package android.support.v4.view;

import android.widget.*;

static class PagerTitleStripImplBase implements PagerTitleStripImpl
{
    @Override
    public void setSingleLineAllCaps(final TextView textView) {
        textView.setSingleLine();
    }
}
