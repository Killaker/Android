package org.apache.http.message;

import org.apache.http.*;
import java.util.*;

@Deprecated
public class BasicHeaderIterator implements HeaderIterator
{
    protected final Header[] allHeaders;
    protected int currentIndex;
    protected String headerName;
    
    public BasicHeaderIterator(final Header[] array, final String s) {
        this.allHeaders = null;
        throw new RuntimeException("Stub!");
    }
    
    protected boolean filterHeader(final int n) {
        throw new RuntimeException("Stub!");
    }
    
    protected int findNext(final int n) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public boolean hasNext() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public final Object next() throws NoSuchElementException {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public Header nextHeader() throws NoSuchElementException {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void remove() throws UnsupportedOperationException {
        throw new RuntimeException("Stub!");
    }
}
