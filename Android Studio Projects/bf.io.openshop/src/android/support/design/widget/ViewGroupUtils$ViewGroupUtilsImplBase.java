package android.support.design.widget;

import android.view.*;
import android.graphics.*;

private static class ViewGroupUtilsImplBase implements ViewGroupUtilsImpl
{
    @Override
    public void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        viewGroup.offsetDescendantRectToMyCoords(view, rect);
        rect.offset(view.getScrollX(), view.getScrollY());
    }
}
