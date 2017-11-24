package com.android.volley.toolbox;

import java.util.*;
import java.io.*;
import com.android.volley.*;

static class CacheHeader
{
    public String etag;
    public String key;
    public long lastModified;
    public Map<String, String> responseHeaders;
    public long serverDate;
    public long size;
    public long softTtl;
    public long ttl;
    
    private CacheHeader() {
    }
    
    public CacheHeader(final String key, final Entry entry) {
        this.key = key;
        this.size = entry.data.length;
        this.etag = entry.etag;
        this.serverDate = entry.serverDate;
        this.lastModified = entry.lastModified;
        this.ttl = entry.ttl;
        this.softTtl = entry.softTtl;
        this.responseHeaders = entry.responseHeaders;
    }
    
    public static CacheHeader readHeader(final InputStream inputStream) throws IOException {
        final CacheHeader cacheHeader = new CacheHeader();
        if (DiskBasedCache.readInt(inputStream) != 538247942) {
            throw new IOException();
        }
        cacheHeader.key = DiskBasedCache.readString(inputStream);
        cacheHeader.etag = DiskBasedCache.readString(inputStream);
        if (cacheHeader.etag.equals("")) {
            cacheHeader.etag = null;
        }
        cacheHeader.serverDate = DiskBasedCache.readLong(inputStream);
        cacheHeader.lastModified = DiskBasedCache.readLong(inputStream);
        cacheHeader.ttl = DiskBasedCache.readLong(inputStream);
        cacheHeader.softTtl = DiskBasedCache.readLong(inputStream);
        cacheHeader.responseHeaders = DiskBasedCache.readStringStringMap(inputStream);
        return cacheHeader;
    }
    
    public Entry toCacheEntry(final byte[] data) {
        final Entry entry = new Entry();
        entry.data = data;
        entry.etag = this.etag;
        entry.serverDate = this.serverDate;
        entry.lastModified = this.lastModified;
        entry.ttl = this.ttl;
        entry.softTtl = this.softTtl;
        entry.responseHeaders = this.responseHeaders;
        return entry;
    }
    
    public boolean writeHeader(final OutputStream outputStream) {
        try {
            DiskBasedCache.writeInt(outputStream, 538247942);
            DiskBasedCache.writeString(outputStream, this.key);
            String etag;
            if (this.etag == null) {
                etag = "";
            }
            else {
                etag = this.etag;
            }
            DiskBasedCache.writeString(outputStream, etag);
            DiskBasedCache.writeLong(outputStream, this.serverDate);
            DiskBasedCache.writeLong(outputStream, this.lastModified);
            DiskBasedCache.writeLong(outputStream, this.ttl);
            DiskBasedCache.writeLong(outputStream, this.softTtl);
            DiskBasedCache.writeStringStringMap(this.responseHeaders, outputStream);
            outputStream.flush();
            return true;
        }
        catch (IOException ex) {
            VolleyLog.d("%s", ex.toString());
            return false;
        }
    }
}
