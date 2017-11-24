package android.support.v7.widget;

import android.database.*;

static class AdapterDataObservable extends Observable<AdapterDataObserver>
{
    public boolean hasObservers() {
        return !this.mObservers.isEmpty();
    }
    
    public void notifyChanged() {
        for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
            ((AdapterDataObserver)this.mObservers.get(i)).onChanged();
        }
    }
    
    public void notifyItemMoved(final int n, final int n2) {
        for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
            ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeMoved(n, n2, 1);
        }
    }
    
    public void notifyItemRangeChanged(final int n, final int n2) {
        this.notifyItemRangeChanged(n, n2, null);
    }
    
    public void notifyItemRangeChanged(final int n, final int n2, final Object o) {
        for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
            ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeChanged(n, n2, o);
        }
    }
    
    public void notifyItemRangeInserted(final int n, final int n2) {
        for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
            ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeInserted(n, n2);
        }
    }
    
    public void notifyItemRangeRemoved(final int n, final int n2) {
        for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
            ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeRemoved(n, n2);
        }
    }
}
