package android.support.v4.widget;

import android.widget.*;

static final class SearchViewCompatHoneycomb$1 implements SearchView$OnQueryTextListener {
    final /* synthetic */ OnQueryTextListenerCompatBridge val$listener;
    
    public boolean onQueryTextChange(final String s) {
        return this.val$listener.onQueryTextChange(s);
    }
    
    public boolean onQueryTextSubmit(final String s) {
        return this.val$listener.onQueryTextSubmit(s);
    }
}