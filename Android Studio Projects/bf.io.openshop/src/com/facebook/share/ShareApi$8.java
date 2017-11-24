package com.facebook.share;

import android.os.*;
import java.util.*;
import com.facebook.internal.*;
import com.facebook.*;

class ShareApi$8 implements Collection<String> {
    final /* synthetic */ Bundle val$parameters;
    
    public Object get(final String s) {
        return this.val$parameters.get(s);
    }
    
    @Override
    public Iterator<String> keyIterator() {
        return this.val$parameters.keySet().iterator();
    }
    
    public void set(final String s, final Object o, final OnErrorListener onErrorListener) {
        if (!Utility.putJSONValueInBundle(this.val$parameters, s, o)) {
            onErrorListener.onError(new FacebookException("Unexpected value: " + o.toString()));
        }
    }
}