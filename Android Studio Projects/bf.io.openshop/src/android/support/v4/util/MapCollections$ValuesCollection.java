package android.support.v4.util;

import java.util.*;

final class ValuesCollection implements Collection<V>
{
    @Override
    public boolean add(final V v) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(final Collection<? extends V> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        MapCollections.this.colClear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return MapCollections.this.colIndexOfValue(o) >= 0;
    }
    
    @Override
    public boolean containsAll(final Collection<?> collection) {
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!this.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean isEmpty() {
        return MapCollections.this.colGetSize() == 0;
    }
    
    @Override
    public Iterator<V> iterator() {
        return new ArrayIterator<V>(1);
    }
    
    @Override
    public boolean remove(final Object o) {
        final int colIndexOfValue = MapCollections.this.colIndexOfValue(o);
        if (colIndexOfValue >= 0) {
            MapCollections.this.colRemoveAt(colIndexOfValue);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        int colGetSize = MapCollections.this.colGetSize();
        boolean b = false;
        for (int i = 0; i < colGetSize; ++i) {
            if (collection.contains(MapCollections.this.colGetEntry(i, 1))) {
                MapCollections.this.colRemoveAt(i);
                --i;
                --colGetSize;
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        int colGetSize = MapCollections.this.colGetSize();
        boolean b = false;
        for (int i = 0; i < colGetSize; ++i) {
            if (!collection.contains(MapCollections.this.colGetEntry(i, 1))) {
                MapCollections.this.colRemoveAt(i);
                --i;
                --colGetSize;
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public int size() {
        return MapCollections.this.colGetSize();
    }
    
    @Override
    public Object[] toArray() {
        return MapCollections.this.toArrayHelper(1);
    }
    
    @Override
    public <T> T[] toArray(final T[] array) {
        return MapCollections.this.toArrayHelper(array, 1);
    }
}
