package android.support.v7.widget;

import android.view.*;

class SearchView$4 implements View$OnFocusChangeListener {
    public void onFocusChange(final View view, final boolean b) {
        if (SearchView.access$200(SearchView.this) != null) {
            SearchView.access$200(SearchView.this).onFocusChange((View)SearchView.this, b);
        }
    }
}