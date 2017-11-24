package org.apache.http.message;

import java.util.*;
import org.apache.http.*;

@Deprecated
public class BasicHeaderElementIterator implements HeaderElementIterator
{
    public BasicHeaderElementIterator(final HeaderIterator headerIterator) {
        throw new RuntimeException("Stub!");
    }
    
    public BasicHeaderElementIterator(final HeaderIterator headerIterator, final HeaderValueParser headerValueParser) {
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
    public HeaderElement nextElement() throws NoSuchElementException {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void remove() throws UnsupportedOperationException {
        throw new RuntimeException("Stub!");
    }
}
