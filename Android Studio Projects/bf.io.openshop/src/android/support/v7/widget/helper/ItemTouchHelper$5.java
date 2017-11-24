package android.support.v7.widget.helper;

import android.support.v7.widget.*;

class ItemTouchHelper$5 implements ChildDrawingOrderCallback {
    @Override
    public int onGetChildDrawingOrder(final int n, final int n2) {
        if (ItemTouchHelper.access$1600(ItemTouchHelper.this) != null) {
            int n3 = ItemTouchHelper.access$2300(ItemTouchHelper.this);
            if (n3 == -1) {
                n3 = ItemTouchHelper.access$300(ItemTouchHelper.this).indexOfChild(ItemTouchHelper.access$1600(ItemTouchHelper.this));
                ItemTouchHelper.access$2302(ItemTouchHelper.this, n3);
            }
            if (n2 == n - 1) {
                return n3;
            }
            if (n2 >= n3) {
                return n2 + 1;
            }
        }
        return n2;
    }
}