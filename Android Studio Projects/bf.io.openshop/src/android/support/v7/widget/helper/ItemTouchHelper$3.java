package android.support.v7.widget.helper;

import android.support.v7.widget.*;
import android.support.v4.animation.*;

class ItemTouchHelper$3 extends RecoverAnimation {
    final /* synthetic */ ViewHolder val$prevSelected;
    final /* synthetic */ int val$swipeDir;
    
    @Override
    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        super.onAnimationEnd(valueAnimatorCompat);
        if (!this.mOverridden) {
            if (this.val$swipeDir <= 0) {
                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.access$300(ItemTouchHelper.this), this.val$prevSelected);
            }
            else {
                ItemTouchHelper.this.mPendingCleanup.add(this.val$prevSelected.itemView);
                this.mIsPendingCleanup = true;
                if (this.val$swipeDir > 0) {
                    ItemTouchHelper.access$1500(ItemTouchHelper.this, (RecoverAnimation)this, this.val$swipeDir);
                }
            }
            if (ItemTouchHelper.access$1600(ItemTouchHelper.this) == this.val$prevSelected.itemView) {
                ItemTouchHelper.access$1700(ItemTouchHelper.this, this.val$prevSelected.itemView);
            }
        }
    }
}