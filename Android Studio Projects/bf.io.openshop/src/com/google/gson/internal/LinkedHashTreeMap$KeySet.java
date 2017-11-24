package com.google.gson.internal;

import java.util.*;

final class KeySet extends AbstractSet<K>
{
    @Override
    public void clear() {
        LinkedHashTreeMap.this.clear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return LinkedHashTreeMap.this.containsKey(o);
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
        return LinkedHashTreeMap.this.removeInternalByKey(o) != null;
    }
    
    @Override
    public int size() {
        return LinkedHashTreeMap.this.size;
    }
}
