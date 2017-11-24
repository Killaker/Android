package com.facebook.share.internal;

import com.facebook.*;

private interface RequestWrapper
{
    void addToBatch(final GraphRequestBatch p0);
    
    FacebookRequestError getError();
}
