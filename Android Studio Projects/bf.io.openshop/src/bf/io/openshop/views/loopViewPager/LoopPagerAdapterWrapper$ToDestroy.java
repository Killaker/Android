package bf.io.openshop.views.loopViewPager;

import android.view.*;

static class ToDestroy
{
    ViewGroup container;
    Object object;
    int position;
    
    public ToDestroy(final ViewGroup container, final int position, final Object object) {
        this.container = container;
        this.position = position;
        this.object = object;
    }
}
