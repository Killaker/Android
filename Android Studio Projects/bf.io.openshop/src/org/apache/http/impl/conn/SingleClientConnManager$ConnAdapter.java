package org.apache.http.impl.conn;

import org.apache.http.conn.routing.*;
import org.apache.http.conn.*;

protected class ConnAdapter extends AbstractPooledConnAdapter
{
    protected ConnAdapter(final PoolEntry poolEntry, final HttpRoute httpRoute) {
        super(null, (AbstractPoolEntry)null);
        throw new RuntimeException("Stub!");
    }
}
