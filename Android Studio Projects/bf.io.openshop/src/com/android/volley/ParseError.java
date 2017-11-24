package com.android.volley;

public class ParseError extends VolleyError
{
    public ParseError() {
    }
    
    public ParseError(final NetworkResponse networkResponse) {
        super(networkResponse);
    }
    
    public ParseError(final Throwable t) {
        super(t);
    }
}
