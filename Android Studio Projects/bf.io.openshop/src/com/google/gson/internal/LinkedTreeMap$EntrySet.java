package com.google.gson.internal;

import java.util.*;

class EntrySet extends AbstractSet<Entry<K, V>>
{
    @Override
    public void clear() {
        LinkedTreeMap.this.clear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return o instanceof Entry && LinkedTreeMap.this.findByEntry((Entry<?, ?>)o) != null;
    }
    
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new LinkedTreeMapIterator<Entry<K, V>>() {
            @Override
            public Entry<K, V> next() {
                return ((LinkedTreeMapIterator)this).nextNode();
            }
        };
    }
    
    @Override
    public boolean remove(final Object o) {
        if (o instanceof Entry) {
            final Node<K, V> byEntry = LinkedTreeMap.this.findByEntry((Entry<?, ?>)o);
            if (byEntry != null) {
                LinkedTreeMap.this.removeInternal(byEntry, true);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int size() {
        return LinkedTreeMap.this.size;
    }
}
