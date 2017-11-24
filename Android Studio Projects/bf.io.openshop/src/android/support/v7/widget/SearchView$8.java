package android.support.v7.widget;

import android.support.v4.view.*;
import android.content.*;
import android.util.*;
import android.support.v7.appcompat.*;
import android.text.*;
import android.graphics.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;

class SearchView$8 implements View$OnKeyListener {
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (SearchView.access$1400(SearchView.this) != null) {
            if (SearchView.access$1200(SearchView.this).isPopupShowing() && SearchView.access$1200(SearchView.this).getListSelection() != -1) {
                return SearchView.access$1500(SearchView.this, view, n, keyEvent);
            }
            if (!SearchView.access$1200(SearchView.this).isEmpty() && KeyEventCompat.hasNoModifiers(keyEvent) && keyEvent.getAction() == 1 && n == 66) {
                view.cancelLongPress();
                SearchView.access$1700(SearchView.this, 0, null, SearchView.access$1200(SearchView.this).getText().toString());
                return true;
            }
        }
        return false;
    }
}