package mbanje.kurt.fabbutton;

import android.view.*;
import android.graphics.*;

private static class ViewGroupUtilsImplHoneycomb implements ViewGroupUtilsImpl
{
    @Override
    public void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        ViewGroupUtilsHoneycomb.offsetDescendantRect(viewGroup, view, rect);
    }
}
