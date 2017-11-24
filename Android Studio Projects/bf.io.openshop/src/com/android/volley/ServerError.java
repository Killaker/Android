package com.android.volley;

public class ServerError extends VolleyError
{
    public ServerError() {
    }
    
    public ServerError(final NetworkResponse networkResponse) {
        super(networkResponse);
    }
}
