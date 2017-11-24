package com.google.gson.internal;

import java.util.*;

static class AvlIterator<K, V>
{
    private Node<K, V> stackTop;
    
    public Node<K, V> next() {
        final Node<K, V> stackTop = this.stackTop;
        if (stackTop == null) {
            return null;
        }
        Node<K, V> parent = stackTop.parent;
        stackTop.parent = null;
        for (Node<K, V> node = stackTop.right; node != null; node = node.left) {
            node.parent = parent;
            parent = node;
        }
        this.stackTop = parent;
        return stackTop;
    }
    
    void reset(final Node<K, V> node) {
        Node<K, V> node2 = null;
        for (Entry<K, V> left = (Entry<K, V>)node; left != null; left = ((Node)left).left) {
            ((Node)left).parent = (Node<K, V>)node2;
            node2 = (Node<K, V>)left;
        }
        this.stackTop = node2;
    }
}
