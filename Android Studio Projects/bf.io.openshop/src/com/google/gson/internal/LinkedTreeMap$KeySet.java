package com.google.gson.internal;

import java.util.*;

final class KeySet extends AbstractSet<K>
{
    @Override
    public void clear() {
        LinkedTreeMap.this.clear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return LinkedTreeMap.this.containsKey(o);
    }
    
    @Override
    public Iterator<K> iterator() {
        return new LinkedTreeMapIterator<K>() {
            @Override
            public K next() {
                return ((LinkedTreeMapIterator)this).nextNode().key;
            }
        };
    }
    
    @Override
    public boolean remove(final Object o) {
        return LinkedTreeMap.this.removeInternalByKey(o) != null;
    }
    
    @Override
    public int size() {
        return LinkedTreeMap.this.size;
    }
}
