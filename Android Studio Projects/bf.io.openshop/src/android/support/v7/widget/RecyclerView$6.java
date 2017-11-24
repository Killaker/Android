package android.support.v7.widget;

import android.util.*;

class RecyclerView$6 implements Callback {
    void dispatchUpdate(final UpdateOp updateOp) {
        switch (updateOp.cmd) {
            default: {}
            case 1: {
                RecyclerView.this.mLayout.onItemsAdded(RecyclerView.this, updateOp.positionStart, updateOp.itemCount);
            }
            case 2: {
                RecyclerView.this.mLayout.onItemsRemoved(RecyclerView.this, updateOp.positionStart, updateOp.itemCount);
            }
            case 4: {
                RecyclerView.this.mLayout.onItemsUpdated(RecyclerView.this, updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            }
            case 8: {
                RecyclerView.this.mLayout.onItemsMoved(RecyclerView.this, updateOp.positionStart, updateOp.itemCount, 1);
            }
        }
    }
    
    @Override
    public ViewHolder findViewHolder(final int n) {
        Object viewHolderForPosition = RecyclerView.this.findViewHolderForPosition(n, true);
        if (viewHolderForPosition == null) {
            viewHolderForPosition = null;
        }
        else if (RecyclerView.this.mChildHelper.isHidden(((ViewHolder)viewHolderForPosition).itemView)) {
            return null;
        }
        return (ViewHolder)viewHolderForPosition;
    }
    
    @Override
    public void markViewHoldersUpdated(final int n, final int n2, final Object o) {
        RecyclerView.this.viewRangeUpdate(n, n2, o);
        RecyclerView.this.mItemsChanged = true;
    }
    
    @Override
    public void offsetPositionsForAdd(final int n, final int n2) {
        RecyclerView.this.offsetPositionRecordsForInsert(n, n2);
        RecyclerView.this.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void offsetPositionsForMove(final int n, final int n2) {
        RecyclerView.this.offsetPositionRecordsForMove(n, n2);
        RecyclerView.this.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void offsetPositionsForRemovingInvisible(final int n, final int n2) {
        RecyclerView.this.offsetPositionRecordsForRemove(n, n2, true);
        RecyclerView.this.mItemsAddedOrRemoved = true;
        State.access$1712(RecyclerView.this.mState, n2);
    }
    
    @Override
    public void offsetPositionsForRemovingLaidOutOrNewView(final int n, final int n2) {
        RecyclerView.this.offsetPositionRecordsForRemove(n, n2, false);
        RecyclerView.this.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void onDispatchFirstPass(final UpdateOp updateOp) {
        this.dispatchUpdate(updateOp);
    }
    
    @Override
    public void onDispatchSecondPass(final UpdateOp updateOp) {
        this.dispatchUpdate(updateOp);
    }
}