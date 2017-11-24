package com.facebook.share;

import java.util.*;
import com.facebook.internal.*;
import com.facebook.*;
import org.json.*;

class ShareApi$5 implements Collection<Integer> {
    final /* synthetic */ ArrayList val$arrayList;
    final /* synthetic */ JSONArray val$stagedObject;
    
    public Object get(final Integer n) {
        return this.val$arrayList.get(n);
    }
    
    @Override
    public Iterator<Integer> keyIterator() {
        return new Iterator<Integer>() {
            final /* synthetic */ Mutable val$current = new Mutable((T)0);
            final /* synthetic */ int val$size = ShareApi$5.this.val$arrayList.size();
            
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
        };
    }
    
    public void set(final Integer n, final Object o, final OnErrorListener onErrorListener) {
        try {
            this.val$stagedObject.put((int)n, o);
        }
        catch (JSONException ex) {
            String localizedMessage = ex.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "Error staging object.";
            }
            onErrorListener.onError(new FacebookException(localizedMessage));
        }
    }
}