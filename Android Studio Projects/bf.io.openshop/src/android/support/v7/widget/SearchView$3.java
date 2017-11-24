package android.support.v7.widget;

import android.database.*;

class SearchView$3 implements Runnable {
    @Override
    public void run() {
        if (SearchView.access$100(SearchView.this) != null && SearchView.access$100(SearchView.this) instanceof SuggestionsAdapter) {
            SearchView.access$100(SearchView.this).changeCursor(null);
        }
    }
}