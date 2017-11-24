package com.android.volley.toolbox;

import java.util.*;

public class ByteArrayPool
{
    protected static final Comparator<byte[]> BUF_COMPARATOR;
    private List<byte[]> mBuffersByLastUse;
    private List<byte[]> mBuffersBySize;
    private int mCurrentSize;
    private final int mSizeLimit;
    
    static {
        BUF_COMPARATOR = new Comparator<byte[]>() {
            @Override
            public int compare(final byte[] array, final byte[] array2) {
                return array.length - array2.length;
            }
        };
    }
    
    public ByteArrayPool(final int mSizeLimit) {
        this.mBuffersByLastUse = new LinkedList<byte[]>();
        this.mBuffersBySize = new ArrayList<byte[]>(64);
        this.mCurrentSize = 0;
        this.mSizeLimit = mSizeLimit;
    }
    
    private void trim() {
        synchronized (this) {
            while (this.mCurrentSize > this.mSizeLimit) {
                final byte[] array = this.mBuffersByLastUse.remove(0);
                this.mBuffersBySize.remove(array);
                this.mCurrentSize -= array.length;
            }
        }
    }
    // monitorexit(this)
    
    public byte[] getBuf(final int n) {
        // monitorenter(this)
        int i = 0;
        try {
            while (i < this.mBuffersBySize.size()) {
                final byte[] array = this.mBuffersBySize.get(i);
                if (array.length >= n) {
                    this.mCurrentSize -= array.length;
                    this.mBuffersBySize.remove(i);
                    this.mBuffersByLastUse.remove(array);
                    return array;
                }
                ++i;
            }
            return new byte[n];
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public void returnBuf(final byte[] array) {
        // monitorenter(this)
        if (array == null) {
            return;
        }
        try {
            if (array.length <= this.mSizeLimit) {
                this.mBuffersByLastUse.add(array);
                int binarySearch = Collections.binarySearch(this.mBuffersBySize, array, ByteArrayPool.BUF_COMPARATOR);
                if (binarySearch < 0) {
                    binarySearch = -1 + -binarySearch;
                }
                this.mBuffersBySize.add(binarySearch, array);
                this.mCurrentSize += array.length;
                this.trim();
            }
        }
        finally {
        }
        // monitorexit(this)
    }
}
