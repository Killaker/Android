package com.facebook.share;

import java.util.*;
import com.facebook.internal.*;

class ShareApi$5$1 implements Iterator<Integer> {
    final /* synthetic */ Mutable val$current;
    final /* synthetic */ int val$size;
    
    @Override
    public boolean hasNext() {
        return (int)this.val$current.value < this.val$size;
    }
    
    @Override
    public Integer next() {
        final Integer n = (Integer)this.val$current.value;
        final Mutable val$current = this.val$current;
        val$current.value = (T)(1 + (int)val$current.value);
        return n;
    }
    
    @Override
    public void remove() {
    }
}