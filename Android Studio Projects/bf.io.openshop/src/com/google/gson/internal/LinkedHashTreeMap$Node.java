package com.google.gson.internal;

import java.util.*;

static final class Node<K, V> implements Entry<K, V>
{
    final int hash;
    int height;
    final K key;
    Node<K, V> left;
    Node<K, V> next;
    Node<K, V> parent;
    Node<K, V> prev;
    Node<K, V> right;
    V value;
    
    Node() {
        this.key = null;
        this.hash = -1;
        this.prev = this;
        this.next = this;
    }
    
    Node(final Node<K, V> parent, final K key, final int hash, final Node<K, V> next, final Node<K, V> prev) {
        this.parent = parent;
        this.key = key;
        this.hash = hash;
        this.height = 1;
        this.next = next;
        this.prev = prev;
        prev.next = this;
        next.prev = this;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof Entry;
        boolean b2 = false;
        if (b) {
            final Entry entry = (Entry)o;
            if (this.key == null) {
                final K key = (K)entry.getKey();
                b2 = false;
                if (key != null) {
                    return b2;
                }
            }
            else {
                final boolean equals = this.key.equals(entry.getKey());
                b2 = false;
                if (!equals) {
                    return b2;
                }
            }
            if (this.value == null) {
                final Object value = entry.getValue();
                b2 = false;
                if (value != null) {
                    return b2;
                }
            }
            else {
                final boolean equals2 = this.value.equals(entry.getValue());
                b2 = false;
                if (!equals2) {
                    return b2;
                }
            }
            b2 = true;
        }
        return b2;
    }
    
    public Node<K, V> first() {
        Node node = this;
        for (Node<K, V> node2 = node.left; node2 != null; node2 = node.left) {
            node = node2;
        }
        return (Node<K, V>)node;
    }
    
    @Override
    public K getKey() {
        return this.key;
    }
    
    @Override
    public V getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.key == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.key.hashCode();
        }
        final V value = this.value;
        int hashCode2 = 0;
        if (value != null) {
            hashCode2 = this.value.hashCode();
        }
        return hashCode ^ hashCode2;
    }
    
    public Node<K, V> last() {
        Node node = this;
        for (Node<K, V> node2 = node.right; node2 != null; node2 = node.right) {
            node = node2;
        }
        return (Node<K, V>)node;
    }
    
    @Override
    public V setValue(final V value) {
        final V value2 = this.value;
        this.value = value;
        return value2;
    }
    
    @Override
    public String toString() {
        return this.key + "=" + this.value;
    }
}
