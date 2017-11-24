package android.support.v4.util;

import java.util.*;

final class ArrayIterator<T> implements Iterator<T>
{
    boolean mCanRemove;
    int mIndex;
    final int mOffset;
    int mSize;
    
    ArrayIterator(final int mOffset) {
        this.mCanRemove = false;
        this.mOffset = mOffset;
        this.mSize = MapCollections.this.colGetSize();
    }
    
    @Override
    public boolean hasNext() {
        return this.mIndex < this.mSize;
    }
    
    @Override
    public T next() {
        final Object colGetEntry = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
        ++this.mIndex;
        this.mCanRemove = true;
        return (T)colGetEntry;
    }
    
    @Override
    public void remove() {
        if (!this.mCanRemove) {
            throw new IllegalStateException();
        }
        --this.mIndex;
        --this.mSize;
        this.mCanRemove = false;
        MapCollections.this.colRemoveAt(this.mIndex);
    }
}
