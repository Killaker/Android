package com.facebook.internal;

import java.util.*;
import android.annotation.*;

private static final class JSONObjectEntry implements Entry<String, Object>
{
    private final String key;
    private final Object value;
    
    JSONObjectEntry(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }
    
    @SuppressLint({ "FieldGetter" })
    public String getKey() {
        return this.key;
    }
    
    @Override
    public Object getValue() {
        return this.value;
    }
    
    @Override
    public Object setValue(final Object o) {
        throw new UnsupportedOperationException("JSONObjectEntry is immutable");
    }
}
