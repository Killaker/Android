package android.support.v4.app;

import android.widget.*;
import android.content.*;
import android.view.*;

static class DummyTabFactory implements TabHost$TabContentFactory
{
    private final Context mContext;
    
    public DummyTabFactory(final Context mContext) {
        this.mContext = mContext;
    }
    
    public View createTabContent(final String s) {
        final View view = new View(this.mContext);
        view.setMinimumWidth(0);
        view.setMinimumHeight(0);
        return view;
    }
}
