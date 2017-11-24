package com.android.volley;

public class NoConnectionError extends NetworkError
{
    public NoConnectionError() {
    }
    
    public NoConnectionError(final Throwable t) {
        super(t);
    }
}
