package android.support.v7.widget;

import android.support.annotation.*;

class RecyclerView$4 implements ProcessCallback {
    @Override
    public void processAppeared(final ViewHolder viewHolder, final ItemHolderInfo itemHolderInfo, final ItemHolderInfo itemHolderInfo2) {
        RecyclerView.access$800(RecyclerView.this, viewHolder, itemHolderInfo, itemHolderInfo2);
    }
    
    @Override
    public void processDisappeared(final ViewHolder viewHolder, @NonNull final ItemHolderInfo itemHolderInfo, @Nullable final ItemHolderInfo itemHolderInfo2) {
        RecyclerView.this.mRecycler.unscrapView(viewHolder);
        RecyclerView.access$700(RecyclerView.this, viewHolder, itemHolderInfo, itemHolderInfo2);
    }
    
    @Override
    public void processPersistent(final ViewHolder viewHolder, @NonNull final ItemHolderInfo itemHolderInfo, @NonNull final ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (RecyclerView.access$900(RecyclerView.this)) {
            if (RecyclerView.this.mItemAnimator.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                RecyclerView.access$1000(RecyclerView.this);
            }
        }
        else if (RecyclerView.this.mItemAnimator.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            RecyclerView.access$1000(RecyclerView.this);
        }
    }
    
    @Override
    public void unused(final ViewHolder viewHolder) {
        RecyclerView.this.mLayout.removeAndRecycleView(viewHolder.itemView, RecyclerView.this.mRecycler);
    }
}