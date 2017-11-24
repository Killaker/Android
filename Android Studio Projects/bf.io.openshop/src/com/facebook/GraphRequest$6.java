package com.facebook;

import java.util.*;
import java.net.*;
import java.io.*;

class GraphRequest$6 implements KeyValueSerializer {
    final /* synthetic */ ArrayList val$keysAndValues;
    
    @Override
    public void writeString(final String s, final String s2) throws IOException {
        this.val$keysAndValues.add(String.format(Locale.US, "%s=%s", s, URLEncoder.encode(s2, "UTF-8")));
    }
}