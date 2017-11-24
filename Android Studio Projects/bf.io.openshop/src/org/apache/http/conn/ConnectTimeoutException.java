package org.apache.http.conn;

import java.io.*;

public class ConnectTimeoutException extends InterruptedIOException
{
    public ConnectTimeoutException() {
        throw new RuntimeException("Stub!");
    }
    
    public ConnectTimeoutException(final String s) {
        throw new RuntimeException("Stub!");
    }
}
