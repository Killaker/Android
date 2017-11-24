package android.support.v4.widget;

import android.widget.*;
import android.content.*;

public static class MySearchView extends SearchView
{
    public MySearchView(final Context context) {
        super(context);
    }
    
    public void onActionViewCollapsed() {
        this.setQuery((CharSequence)"", false);
        super.onActionViewCollapsed();
    }
}
