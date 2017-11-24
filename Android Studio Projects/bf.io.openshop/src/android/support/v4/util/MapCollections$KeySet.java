package android.support.v4.util;

import java.util.*;

final class KeySet implements Set<K>
{
    @Override
    public boolean add(final K k) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(final Collection<? extends K> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        MapCollections.this.colClear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return MapCollections.this.colIndexOfKey(o) >= 0;
    }
    
    @Override
    public boolean containsAll(final Collection<?> collection) {
        return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), collection);
    }
    
    @Override
    public boolean equals(final Object o) {
        return MapCollections.equalsSetHelper((Set<Object>)this, o);
    }
    
    @Override
    public int hashCode() {
        int n = 0;
        for (int i = -1 + MapCollections.this.colGetSize(); i >= 0; --i) {
            final Object colGetEntry = MapCollections.this.colGetEntry(i, 0);
            int hashCode;
            if (colGetEntry == null) {
                hashCode = 0;
            }
            else {
                hashCode = colGetEntry.hashCode();
            }
            n += hashCode;
        }
        return n;
    }
    
    @Override
    public boolean isEmpty() {
        return MapCollections.this.colGetSize() == 0;
    }
    
    @Override
    public Iterator<K> iterator() {
        return new ArrayIterator<K>(0);
    }
    
    @Override
    public boolean remove(final Object o) {
        final int colIndexOfKey = MapCollections.this.colIndexOfKey(o);
        if (colIndexOfKey >= 0) {
            MapCollections.this.colRemoveAt(colIndexOfKey);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), collection);
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), collection);
    }
    
    @Override
    public int size() {
        return MapCollections.this.colGetSize();
    }
    
    @Override
    public Object[] toArray() {
        return MapCollections.this.toArrayHelper(0);
    }
    
    @Override
    public <T> T[] toArray(final T[] array) {
        return MapCollections.this.toArrayHelper(array, 0);
    }
}
