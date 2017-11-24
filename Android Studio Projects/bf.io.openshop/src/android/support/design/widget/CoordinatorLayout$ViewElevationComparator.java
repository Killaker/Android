package android.support.design.widget;

import java.util.*;
import android.view.*;
import android.support.v4.view.*;

static class ViewElevationComparator implements Comparator<View>
{
    @Override
    public int compare(final View view, final View view2) {
        final float z = ViewCompat.getZ(view);
        final float z2 = ViewCompat.getZ(view2);
        if (z > z2) {
            return -1;
        }
        if (z < z2) {
            return 1;
        }
        return 0;
    }
}
