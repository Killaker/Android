package android.support.v7.widget;

import android.view.inputmethod.*;
import android.view.*;

class SearchView$1 implements Runnable {
    @Override
    public void run() {
        final InputMethodManager inputMethodManager = (InputMethodManager)SearchView.this.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            SearchView.HIDDEN_METHOD_INVOKER.showSoftInputUnchecked(inputMethodManager, (View)SearchView.this, 0);
        }
    }
}