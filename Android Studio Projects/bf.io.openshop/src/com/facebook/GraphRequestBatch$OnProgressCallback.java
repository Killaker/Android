package com.facebook;

public interface OnProgressCallback extends Callback
{
    void onBatchProgress(final GraphRequestBatch p0, final long p1, final long p2);
}
