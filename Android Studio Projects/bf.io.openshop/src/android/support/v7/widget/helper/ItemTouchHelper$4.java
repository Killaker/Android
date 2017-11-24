package android.support.v7.widget.helper;

import android.support.v7.widget.*;

class ItemTouchHelper$4 implements Runnable {
    final /* synthetic */ RecoverAnimation val$anim;
    final /* synthetic */ int val$swipeDir;
    
    @Override
    public void run() {
        if (ItemTouchHelper.access$300(ItemTouchHelper.this) != null && ItemTouchHelper.access$300(ItemTouchHelper.this).isAttachedToWindow() && !this.val$anim.mOverridden && this.val$anim.mViewHolder.getAdapterPosition() != -1) {
            final RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.access$300(ItemTouchHelper.this).getItemAnimator();
            if ((itemAnimator != null && itemAnimator.isRunning(null)) || ItemTouchHelper.access$1800(ItemTouchHelper.this)) {
                ItemTouchHelper.access$300(ItemTouchHelper.this).post((Runnable)this);
                return;
            }
            ItemTouchHelper.this.mCallback.onSwiped(this.val$anim.mViewHolder, this.val$swipeDir);
        }
    }
}