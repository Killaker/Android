package android.support.v7.widget;

import android.support.v4.view.*;
import android.view.*;
import android.util.*;

private class RecyclerViewDataObserver extends AdapterDataObserver
{
    @Override
    public void onChanged() {
        RecyclerView.this.assertNotInLayoutOrScroll(null);
        if (RecyclerView.access$3100(RecyclerView.this).hasStableIds()) {
            RecyclerView.this.mState.mStructureChanged = true;
            RecyclerView.access$4000(RecyclerView.this);
        }
        else {
            RecyclerView.this.mState.mStructureChanged = true;
            RecyclerView.access$4000(RecyclerView.this);
        }
        if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
            RecyclerView.this.requestLayout();
        }
    }
    
    @Override
    public void onItemRangeChanged(final int n, final int n2, final Object o) {
        RecyclerView.this.assertNotInLayoutOrScroll(null);
        if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(n, n2, o)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeInserted(final int n, final int n2) {
        RecyclerView.this.assertNotInLayoutOrScroll(null);
        if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(n, n2)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeMoved(final int n, final int n2, final int n3) {
        RecyclerView.this.assertNotInLayoutOrScroll(null);
        if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(n, n2, n3)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeRemoved(final int n, final int n2) {
        RecyclerView.this.assertNotInLayoutOrScroll(null);
        if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(n, n2)) {
            this.triggerUpdateProcessor();
        }
    }
    
    void triggerUpdateProcessor() {
        if (RecyclerView.access$4100(RecyclerView.this) && RecyclerView.access$4200(RecyclerView.this) && RecyclerView.access$4300(RecyclerView.this)) {
            ViewCompat.postOnAnimation((View)RecyclerView.this, RecyclerView.access$4400(RecyclerView.this));
            return;
        }
        RecyclerView.access$4502(RecyclerView.this, true);
        RecyclerView.this.requestLayout();
    }
}
