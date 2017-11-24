package com.facebook.internal;

import java.net.*;
import java.io.*;

private static class BufferedHttpInputStream extends BufferedInputStream
{
    HttpURLConnection connection;
    
    BufferedHttpInputStream(final InputStream inputStream, final HttpURLConnection connection) {
        super(inputStream, 8192);
        this.connection = connection;
    }
    
    @Override
    public void close() throws IOException {
        super.close();
        Utility.disconnectQuietly(this.connection);
    }
}
