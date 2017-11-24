package com.google.gson.internal;

import java.util.*;

final class EntrySet extends AbstractSet<Entry<K, V>>
{
    @Override
    public void clear() {
        LinkedHashTreeMap.this.clear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return o instanceof Entry && LinkedHashTreeMap.this.findByEntry((Entry<?, ?>)o) != null;
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
            final Node<K, V> byEntry = LinkedHashTreeMap.this.findByEntry((Entry<?, ?>)o);
            if (byEntry != null) {
                LinkedHashTreeMap.this.removeInternal(byEntry, true);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int size() {
        return LinkedHashTreeMap.this.size;
    }
}
