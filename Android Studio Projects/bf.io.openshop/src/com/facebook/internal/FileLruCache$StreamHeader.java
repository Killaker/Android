package com.facebook.internal;

import com.facebook.*;
import org.json.*;
import java.io.*;

private static final class StreamHeader
{
    private static final int HEADER_VERSION;
    
    static JSONObject readHeader(final InputStream inputStream) throws IOException {
        if (inputStream.read() != 0) {
            return null;
        }
        int n = 0;
        for (int i = 0; i < 3; ++i) {
            final int read = inputStream.read();
            if (read == -1) {
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                return null;
            }
            n = (n << 8) + (read & 0xFF);
        }
        final byte[] array = new byte[n];
        int read2;
        for (int j = 0; j < array.length; j += read2) {
            read2 = inputStream.read(array, j, array.length - j);
            if (read2 < 1) {
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read stopped at " + (Object)j + " when expected " + array.length);
                return null;
            }
        }
        final JSONTokener jsonTokener = new JSONTokener(new String(array));
        try {
            final Object nextValue = jsonTokener.nextValue();
            if (!(nextValue instanceof JSONObject)) {
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: expected JSONObject, got " + ((JSONObject)nextValue).getClass().getCanonicalName());
                return null;
            }
            return (JSONObject)nextValue;
        }
        catch (JSONException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    static void writeHeader(final OutputStream outputStream, final JSONObject jsonObject) throws IOException {
        final byte[] bytes = jsonObject.toString().getBytes();
        outputStream.write(0);
        outputStream.write(0xFF & bytes.length >> 16);
        outputStream.write(0xFF & bytes.length >> 8);
        outputStream.write(0xFF & bytes.length >> 0);
        outputStream.write(bytes);
    }
}
