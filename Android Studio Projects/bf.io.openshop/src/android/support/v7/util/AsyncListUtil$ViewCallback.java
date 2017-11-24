package android.support.v7.util;

import android.support.annotation.*;

public abstract static class ViewCallback
{
    public static final int HINT_SCROLL_ASC = 2;
    public static final int HINT_SCROLL_DESC = 1;
    public static final int HINT_SCROLL_NONE;
    
    @UiThread
    public void extendRangeInto(final int[] array, final int[] array2, final int n) {
        int n2 = 1 + (array[1] - array[0]);
        final int n3 = n2 / 2;
        final int n4 = array[0];
        int n5;
        if (n == 1) {
            n5 = n2;
        }
        else {
            n5 = n3;
        }
        array2[0] = n4 - n5;
        final int n6 = array[1];
        if (n != 2) {
            n2 = n3;
        }
        array2[1] = n6 + n2;
    }
    
    @UiThread
    public abstract void getItemRangeInto(final int[] p0);
    
    @UiThread
    public abstract void onDataRefresh();
    
    @UiThread
    public abstract void onItemLoaded(final int p0);
}
