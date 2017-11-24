package android.support.v7.widget.helper;

import android.support.v4.view.*;
import android.view.*;

class ItemTouchHelper$1 implements Runnable {
    @Override
    public void run() {
        if (ItemTouchHelper.this.mSelected != null && ItemTouchHelper.access$000(ItemTouchHelper.this)) {
            if (ItemTouchHelper.this.mSelected != null) {
                ItemTouchHelper.access$100(ItemTouchHelper.this, ItemTouchHelper.this.mSelected);
            }
            ItemTouchHelper.access$300(ItemTouchHelper.this).removeCallbacks(ItemTouchHelper.access$200(ItemTouchHelper.this));
            ViewCompat.postOnAnimation((View)ItemTouchHelper.access$300(ItemTouchHelper.this), this);
        }
    }
}