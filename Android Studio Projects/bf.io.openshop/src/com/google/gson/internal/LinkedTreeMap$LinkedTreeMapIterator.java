package com.google.gson.internal;

import java.util.*;

private abstract class LinkedTreeMapIterator<T> implements Iterator<T>
{
    int expectedModCount;
    Node<K, V> lastReturned;
    Node<K, V> next;
    
    private LinkedTreeMapIterator() {
        this.next = LinkedTreeMap.this.header.next;
        this.lastReturned = null;
        this.expectedModCount = LinkedTreeMap.this.modCount;
    }
    
    @Override
    public final boolean hasNext() {
        return this.next != LinkedTreeMap.this.header;
    }
    
    final Node<K, V> nextNode() {
        final Node<K, V> next = this.next;
        if (next == LinkedTreeMap.this.header) {
            throw new NoSuchElementException();
        }
        if (LinkedTreeMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
        this.next = next.next;
        return this.lastReturned = next;
    }
    
    @Override
    public final void remove() {
        if (this.lastReturned == null) {
            throw new IllegalStateException();
        }
        LinkedTreeMap.this.removeInternal(this.lastReturned, true);
        this.lastReturned = null;
        this.expectedModCount = LinkedTreeMap.this.modCount;
    }
}
