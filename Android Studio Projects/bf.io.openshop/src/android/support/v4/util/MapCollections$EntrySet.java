package android.support.v4.util;

import java.util.*;

final class EntrySet implements Set<Map.Entry<K, V>>
{
    @Override
    public boolean add(final Map.Entry<K, V> entry) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(final Collection<? extends Map.Entry<K, V>> collection) {
        final int colGetSize = MapCollections.this.colGetSize();
        for (final Map.Entry<K, V> entry : collection) {
            MapCollections.this.colPut(entry.getKey(), entry.getValue());
        }
        return colGetSize != MapCollections.this.colGetSize();
    }
    
    @Override
    public void clear() {
        MapCollections.this.colClear();
    }
    
    @Override
    public boolean contains(final Object o) {
        if (o instanceof Map.Entry) {
            final Map.Entry entry = (Map.Entry)o;
            final int colIndexOfKey = MapCollections.this.colIndexOfKey(entry.getKey());
            if (colIndexOfKey >= 0) {
                return ContainerHelpers.equal(MapCollections.this.colGetEntry(colIndexOfKey, 1), entry.getValue());
            }
        }
        return false;
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
    public boolean equals(final Object o) {
        return MapCollections.equalsSetHelper((Set<Object>)this, o);
    }
    
    @Override
    public int hashCode() {
        int n = 0;
        for (int i = -1 + MapCollections.this.colGetSize(); i >= 0; --i) {
            final Object colGetEntry = MapCollections.this.colGetEntry(i, 0);
            final Object colGetEntry2 = MapCollections.this.colGetEntry(i, 1);
            int hashCode;
            if (colGetEntry == null) {
                hashCode = 0;
            }
            else {
                hashCode = colGetEntry.hashCode();
            }
            int hashCode2;
            if (colGetEntry2 == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = colGetEntry2.hashCode();
            }
            n += (hashCode2 ^ hashCode);
        }
        return n;
    }
    
    @Override
    public boolean isEmpty() {
        return MapCollections.this.colGetSize() == 0;
    }
    
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MapIterator();
    }
    
    @Override
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int size() {
        return MapCollections.this.colGetSize();
    }
    
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public <T> T[] toArray(final T[] array) {
        throw new UnsupportedOperationException();
    }
}
