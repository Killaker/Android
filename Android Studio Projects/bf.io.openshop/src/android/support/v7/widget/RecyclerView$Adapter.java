package android.support.v7.widget;

import android.support.v4.os.*;
import android.view.*;
import java.util.*;

public abstract static class Adapter<VH extends ViewHolder>
{
    private boolean mHasStableIds;
    private final AdapterDataObservable mObservable;
    
    public Adapter() {
        this.mObservable = new AdapterDataObservable();
        this.mHasStableIds = false;
    }
    
    public final void bindViewHolder(final VH vh, final int mPosition) {
        vh.mPosition = mPosition;
        if (this.hasStableIds()) {
            vh.mItemId = this.getItemId(mPosition);
        }
        ((ViewHolder)vh).setFlags(1, 519);
        TraceCompat.beginSection("RV OnBindView");
        this.onBindViewHolder(vh, mPosition, ((ViewHolder)vh).getUnmodifiedPayloads());
        ((ViewHolder)vh).clearPayload();
        TraceCompat.endSection();
    }
    
    public final VH createViewHolder(final ViewGroup viewGroup, final int mItemViewType) {
        TraceCompat.beginSection("RV CreateView");
        final ViewHolder onCreateViewHolder = this.onCreateViewHolder(viewGroup, mItemViewType);
        onCreateViewHolder.mItemViewType = mItemViewType;
        TraceCompat.endSection();
        return (VH)onCreateViewHolder;
    }
    
    public abstract int getItemCount();
    
    public long getItemId(final int n) {
        return -1L;
    }
    
    public int getItemViewType(final int n) {
        return 0;
    }
    
    public final boolean hasObservers() {
        return this.mObservable.hasObservers();
    }
    
    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }
    
    public final void notifyDataSetChanged() {
        this.mObservable.notifyChanged();
    }
    
    public final void notifyItemChanged(final int n) {
        this.mObservable.notifyItemRangeChanged(n, 1);
    }
    
    public final void notifyItemChanged(final int n, final Object o) {
        this.mObservable.notifyItemRangeChanged(n, 1, o);
    }
    
    public final void notifyItemInserted(final int n) {
        this.mObservable.notifyItemRangeInserted(n, 1);
    }
    
    public final void notifyItemMoved(final int n, final int n2) {
        this.mObservable.notifyItemMoved(n, n2);
    }
    
    public final void notifyItemRangeChanged(final int n, final int n2) {
        this.mObservable.notifyItemRangeChanged(n, n2);
    }
    
    public final void notifyItemRangeChanged(final int n, final int n2, final Object o) {
        this.mObservable.notifyItemRangeChanged(n, n2, o);
    }
    
    public final void notifyItemRangeInserted(final int n, final int n2) {
        this.mObservable.notifyItemRangeInserted(n, n2);
    }
    
    public final void notifyItemRangeRemoved(final int n, final int n2) {
        this.mObservable.notifyItemRangeRemoved(n, n2);
    }
    
    public final void notifyItemRemoved(final int n) {
        this.mObservable.notifyItemRangeRemoved(n, 1);
    }
    
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
    }
    
    public abstract void onBindViewHolder(final VH p0, final int p1);
    
    public void onBindViewHolder(final VH vh, final int n, final List<Object> list) {
        this.onBindViewHolder(vh, n);
    }
    
    public abstract VH onCreateViewHolder(final ViewGroup p0, final int p1);
    
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
    }
    
    public boolean onFailedToRecycleView(final VH vh) {
        return false;
    }
    
    public void onViewAttachedToWindow(final VH vh) {
    }
    
    public void onViewDetachedFromWindow(final VH vh) {
    }
    
    public void onViewRecycled(final VH vh) {
    }
    
    public void registerAdapterDataObserver(final AdapterDataObserver adapterDataObserver) {
        this.mObservable.registerObserver((Object)adapterDataObserver);
    }
    
    public void setHasStableIds(final boolean mHasStableIds) {
        if (this.hasObservers()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.mHasStableIds = mHasStableIds;
    }
    
    public void unregisterAdapterDataObserver(final AdapterDataObserver adapterDataObserver) {
        this.mObservable.unregisterObserver((Object)adapterDataObserver);
    }
}
