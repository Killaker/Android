package com.google.gson.internal;

static final class AvlBuilder<K, V>
{
    private int leavesSkipped;
    private int leavesToSkip;
    private int size;
    private Node<K, V> stack;
    
    void add(final Node<K, V> stack) {
        stack.right = null;
        stack.parent = null;
        stack.left = null;
        stack.height = 1;
        if (this.leavesToSkip > 0 && (0x1 & this.size) == 0x0) {
            ++this.size;
            --this.leavesToSkip;
            ++this.leavesSkipped;
        }
        stack.parent = this.stack;
        this.stack = stack;
        ++this.size;
        if (this.leavesToSkip > 0 && (0x1 & this.size) == 0x0) {
            ++this.size;
            --this.leavesToSkip;
            ++this.leavesSkipped;
        }
        for (int n = 4; (this.size & n - 1) == n - 1; n *= 2) {
            if (this.leavesSkipped == 0) {
                final Node<K, V> stack2 = this.stack;
                final Node<K, V> parent = stack2.parent;
                final Node<K, V> parent2 = (Node<K, V>)parent.parent;
                parent.parent = (Node<K, V>)parent2.parent;
                this.stack = parent;
                parent.left = (Node<K, V>)parent2;
                parent.right = stack2;
                parent.height = 1 + stack2.height;
                parent2.parent = (Node<K, V>)parent;
                stack2.parent = parent;
            }
            else if (this.leavesSkipped == 1) {
                final Node<K, V> stack3 = this.stack;
                final Node<K, V> parent3 = stack3.parent;
                this.stack = parent3;
                parent3.right = stack3;
                parent3.height = 1 + stack3.height;
                stack3.parent = parent3;
                this.leavesSkipped = 0;
            }
            else if (this.leavesSkipped == 2) {
                this.leavesSkipped = 0;
            }
        }
    }
    
    void reset(final int n) {
        this.leavesToSkip = -1 + 2 * Integer.highestOneBit(n) - n;
        this.size = 0;
        this.leavesSkipped = 0;
        this.stack = null;
    }
    
    Node<K, V> root() {
        final Node<K, V> stack = this.stack;
        if (stack.parent != null) {
            throw new IllegalStateException();
        }
        return stack;
    }
}
