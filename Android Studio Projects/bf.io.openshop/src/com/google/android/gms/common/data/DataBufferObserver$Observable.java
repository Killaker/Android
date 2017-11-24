package com.google.android.gms.common.data;

public interface Observable
{
    void addObserver(final DataBufferObserver p0);
    
    void removeObserver(final DataBufferObserver p0);
}
