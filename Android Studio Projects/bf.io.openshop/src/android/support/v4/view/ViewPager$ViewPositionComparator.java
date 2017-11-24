package android.support.v4.view;

import java.util.*;
import android.view.*;

static class ViewPositionComparator implements Comparator<View>
{
    @Override
    public int compare(final View view, final View view2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final LayoutParams layoutParams2 = (LayoutParams)view2.getLayoutParams();
        if (layoutParams.isDecor == layoutParams2.isDecor) {
            return layoutParams.position - layoutParams2.position;
        }
        if (layoutParams.isDecor) {
            return 1;
        }
        return -1;
    }
}
