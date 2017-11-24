package android.support.v4.util;

import java.util.*;

class ArrayMap$1 extends MapCollections<K, V> {
    @Override
    protected void colClear() {
        ArrayMap.this.clear();
    }
    
    @Override
    protected Object colGetEntry(final int n, final int n2) {
        return ArrayMap.this.mArray[n2 + (n << 1)];
    }
    
    @Override
    protected Map<K, V> colGetMap() {
        return (Map<K, V>)ArrayMap.this;
    }
    
    @Override
    protected int colGetSize() {
        return ArrayMap.this.mSize;
    }
    
    @Override
    protected int colIndexOfKey(final Object o) {
        return ArrayMap.this.indexOfKey(o);
    }
    
    @Override
    protected int colIndexOfValue(final Object o) {
        return ArrayMap.this.indexOfValue(o);
    }
    
    @Override
    protected void colPut(final K k, final V v) {
        ArrayMap.this.put(k, v);
    }
    
    @Override
    protected void colRemoveAt(final int n) {
        ArrayMap.this.removeAt(n);
    }
    
    @Override
    protected V colSetValue(final int n, final V v) {
        return ArrayMap.this.setValueAt(n, v);
    }
}