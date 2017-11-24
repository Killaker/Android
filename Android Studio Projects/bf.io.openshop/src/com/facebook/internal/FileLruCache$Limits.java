package com.facebook.internal;

import java.security.*;

public static final class Limits
{
    private int byteCount;
    private int fileCount;
    
    public Limits() {
        this.fileCount = 1024;
        this.byteCount = 1048576;
    }
    
    int getByteCount() {
        return this.byteCount;
    }
    
    int getFileCount() {
        return this.fileCount;
    }
    
    void setByteCount(final int byteCount) {
        if (byteCount < 0) {
            throw new InvalidParameterException("Cache byte-count limit must be >= 0");
        }
        this.byteCount = byteCount;
    }
    
    void setFileCount(final int fileCount) {
        if (fileCount < 0) {
            throw new InvalidParameterException("Cache file count limit must be >= 0");
        }
        this.fileCount = fileCount;
    }
}
