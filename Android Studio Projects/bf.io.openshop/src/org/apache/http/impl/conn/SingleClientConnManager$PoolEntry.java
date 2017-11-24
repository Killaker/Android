package org.apache.http.impl.conn;

import org.apache.http.conn.*;
import org.apache.http.conn.routing.*;
import java.io.*;

protected class PoolEntry extends AbstractPoolEntry
{
    protected PoolEntry() {
        super(null, null);
        throw new RuntimeException("Stub!");
    }
    
    protected void close() throws IOException {
        throw new RuntimeException("Stub!");
    }
    
    protected void shutdown() throws IOException {
        throw new RuntimeException("Stub!");
    }
}
