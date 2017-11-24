package com.android.volley;

public interface Network
{
    NetworkResponse performRequest(final Request<?> p0) throws VolleyError;
}
