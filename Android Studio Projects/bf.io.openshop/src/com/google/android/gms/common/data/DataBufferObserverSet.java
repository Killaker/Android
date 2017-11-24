package com.google.android.gms.common.data;

import java.util.*;

public final class DataBufferObserverSet implements DataBufferObserver, Observable
{
    private HashSet<DataBufferObserver> zzajd;
    
    public DataBufferObserverSet() {
        this.zzajd = new HashSet<DataBufferObserver>();
    }
    
    @Override
    public void addObserver(final DataBufferObserver dataBufferObserver) {
        this.zzajd.add(dataBufferObserver);
    }
    
    public void clear() {
        this.zzajd.clear();
    }
    
    public boolean hasObservers() {
        return !this.zzajd.isEmpty();
    }
    
    @Override
    public void onDataChanged() {
        final Iterator<DataBufferObserver> iterator = this.zzajd.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataChanged();
        }
    }
    
    @Override
    public void onDataRangeChanged(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zzajd.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeChanged(n, n2);
        }
    }
    
    @Override
    public void onDataRangeInserted(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zzajd.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeInserted(n, n2);
        }
    }
    
    @Override
    public void onDataRangeMoved(final int n, final int n2, final int n3) {
        final Iterator<DataBufferObserver> iterator = this.zzajd.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeMoved(n, n2, n3);
        }
    }
    
    @Override
    public void onDataRangeRemoved(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zzajd.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeRemoved(n, n2);
        }
    }
    
    @Override
    public void removeObserver(final DataBufferObserver dataBufferObserver) {
        this.zzajd.remove(dataBufferObserver);
    }
}
