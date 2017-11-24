package android.support.v4.util;

import java.util.*;

final class MapIterator implements Iterator<Entry<K, V>>, Entry<K, V>
{
    int mEnd;
    boolean mEntryValid;
    int mIndex;
    
    MapIterator() {
        this.mEntryValid = false;
        this.mEnd = -1 + MapCollections.this.colGetSize();
        this.mIndex = -1;
    }
    
    @Override
    public final boolean equals(final Object o) {
        int n = 1;
        if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(o instanceof Entry)) {
            return false;
        }
        final Entry entry = (Entry)o;
        if (!ContainerHelpers.equal(entry.getKey(), MapCollections.this.colGetEntry(this.mIndex, 0)) || !ContainerHelpers.equal(entry.getValue(), MapCollections.this.colGetEntry(this.mIndex, n))) {
            n = 0;
        }
        return n != 0;
    }
    
    @Override
    public K getKey() {
        if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (K)MapCollections.this.colGetEntry(this.mIndex, 0);
    }
    
    @Override
    public V getValue() {
        if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (V)MapCollections.this.colGetEntry(this.mIndex, 1);
    }
    
    @Override
    public boolean hasNext() {
        return this.mIndex < this.mEnd;
    }
    
    @Override
    public final int hashCode() {
        if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        final Object colGetEntry = MapCollections.this.colGetEntry(this.mIndex, 0);
        final Object colGetEntry2 = MapCollections.this.colGetEntry(this.mIndex, 1);
        int hashCode;
        if (colGetEntry == null) {
            hashCode = 0;
        }
        else {
            hashCode = colGetEntry.hashCode();
        }
        int hashCode2 = 0;
        if (colGetEntry2 != null) {
            hashCode2 = colGetEntry2.hashCode();
        }
        return hashCode2 ^ hashCode;
    }
    
    @Override
    public Entry<K, V> next() {
        ++this.mIndex;
        this.mEntryValid = true;
        return this;
    }
    
    @Override
    public void remove() {
        if (!this.mEntryValid) {
            throw new IllegalStateException();
        }
        MapCollections.this.colRemoveAt(this.mIndex);
        --this.mIndex;
        --this.mEnd;
        this.mEntryValid = false;
    }
    
    @Override
    public V setValue(final V v) {
        if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return MapCollections.this.colSetValue(this.mIndex, v);
    }
    
    @Override
    public final String toString() {
        return this.getKey() + "=" + this.getValue();
    }
}
