package android.support.v7.widget;

import android.content.*;
import android.graphics.*;

class StaggeredGridLayoutManager$2 extends LinearSmoothScroller {
    @Override
    public PointF computeScrollVectorForPosition(final int n) {
        final int access$400 = StaggeredGridLayoutManager.access$400(StaggeredGridLayoutManager.this, n);
        if (access$400 == 0) {
            return null;
        }
        if (StaggeredGridLayoutManager.access$500(StaggeredGridLayoutManager.this) == 0) {
            return new PointF((float)access$400, 0.0f);
        }
        return new PointF(0.0f, (float)access$400);
    }
}