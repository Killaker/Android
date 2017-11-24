package com.android.volley.toolbox;

import com.android.volley.*;

public class NoCache implements Cache
{
    @Override
    public void clear() {
    }
    
    @Override
    public Entry get(final String s) {
        return null;
    }
    
    @Override
    public void initialize() {
    }
    
    @Override
    public void invalidate(final String s, final boolean b) {
    }
    
    @Override
    public void put(final String s, final Entry entry) {
    }
    
    @Override
    public void remove(final String s) {
    }
}
