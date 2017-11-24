package com.facebook.internal;

import android.net.*;

private static class RequestKey
{
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    Object tag;
    Uri uri;
    
    RequestKey(final Uri uri, final Object tag) {
        this.uri = uri;
        this.tag = tag;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = false;
        if (o != null) {
            final boolean b2 = o instanceof RequestKey;
            b = false;
            if (b2) {
                final RequestKey requestKey = (RequestKey)o;
                if (requestKey.uri != this.uri || requestKey.tag != this.tag) {
                    return false;
                }
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        return 37 * (1073 + this.uri.hashCode()) + this.tag.hashCode();
    }
}
