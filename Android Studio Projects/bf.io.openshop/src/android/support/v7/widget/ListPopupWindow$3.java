package android.support.v7.widget;

import android.support.v4.widget.*;
import android.content.*;
import android.support.v7.appcompat.*;
import android.util.*;
import android.os.*;
import android.view.*;
import android.support.v4.view.*;
import android.widget.*;

class ListPopupWindow$3 implements AdapterView$OnItemSelectedListener {
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (n != -1) {
            final DropDownListView access$600 = ListPopupWindow.access$600(ListPopupWindow.this);
            if (access$600 != null) {
                access$600.mListSelectionHidden = false;
            }
        }
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
    }
}