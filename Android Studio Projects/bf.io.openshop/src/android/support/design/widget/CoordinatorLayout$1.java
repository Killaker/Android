package android.support.design.widget;

import java.util.*;
import android.view.*;

class CoordinatorLayout$1 implements Comparator<View> {
    @Override
    public int compare(final View view, final View view2) {
        if (view == view2) {
            return 0;
        }
        if (((LayoutParams)view.getLayoutParams()).dependsOn(CoordinatorLayout.this, view, view2)) {
            return 1;
        }
        if (((LayoutParams)view2.getLayoutParams()).dependsOn(CoordinatorLayout.this, view2, view)) {
            return -1;
        }
        return 0;
    }
}