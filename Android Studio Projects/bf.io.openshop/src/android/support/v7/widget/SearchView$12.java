package android.support.v7.widget;

import android.text.*;

class SearchView$12 implements TextWatcher {
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        SearchView.access$2000(SearchView.this, charSequence);
    }
}