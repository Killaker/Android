package com.android.volley.toolbox;

import java.util.*;
import org.apache.http.*;
import java.io.*;
import com.android.volley.*;

public interface HttpStack
{
    HttpResponse performRequest(final Request<?> p0, final Map<String, String> p1) throws IOException, AuthFailureError;
}
