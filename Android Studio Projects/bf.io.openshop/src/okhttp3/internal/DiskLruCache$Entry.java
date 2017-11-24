package okhttp3.internal;

import java.util.*;
import okio.*;
import java.io.*;

private final class Entry
{
    private final File[] cleanFiles;
    private Editor currentEditor;
    private final File[] dirtyFiles;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;
    
    private Entry(final String key) {
        this.key = key;
        this.lengths = new long[DiskLruCache.access$2300(DiskLruCache.this)];
        this.cleanFiles = new File[DiskLruCache.access$2300(DiskLruCache.this)];
        this.dirtyFiles = new File[DiskLruCache.access$2300(DiskLruCache.this)];
        final StringBuilder append = new StringBuilder(key).append('.');
        final int length = append.length();
        for (int i = 0; i < DiskLruCache.access$2300(DiskLruCache.this); ++i) {
            append.append(i);
            this.cleanFiles[i] = new File(DiskLruCache.access$2800(DiskLruCache.this), append.toString());
            append.append(".tmp");
            this.dirtyFiles[i] = new File(DiskLruCache.access$2800(DiskLruCache.this), append.toString());
            append.setLength(length);
        }
    }
    
    private IOException invalidLengths(final String[] array) throws IOException {
        throw new IOException("unexpected journal line: " + Arrays.toString(array));
    }
    
    private void setLengths(final String[] array) throws IOException {
        if (array.length != DiskLruCache.access$2300(DiskLruCache.this)) {
            throw this.invalidLengths(array);
        }
        int i = 0;
        try {
            while (i < array.length) {
                this.lengths[i] = Long.parseLong(array[i]);
                ++i;
            }
        }
        catch (NumberFormatException ex) {
            throw this.invalidLengths(array);
        }
    }
    
    Snapshot snapshot() {
        if (!Thread.holdsLock(DiskLruCache.this)) {
            throw new AssertionError();
        }
        final Source[] array = new Source[DiskLruCache.access$2300(DiskLruCache.this)];
        final long[] array2 = this.lengths.clone();
        int i = 0;
        try {
            while (i < DiskLruCache.access$2300(DiskLruCache.this)) {
                array[i] = DiskLruCache.access$2400(DiskLruCache.this).source(this.cleanFiles[i]);
                ++i;
            }
            return new Snapshot(this.key, this.sequenceNumber, array, array2);
        }
        catch (FileNotFoundException ex) {
            for (int n = 0; n < DiskLruCache.access$2300(DiskLruCache.this) && array[n] != null; ++n) {
                Util.closeQuietly(array[n]);
            }
            return null;
        }
    }
    
    void writeLengths(final BufferedSink bufferedSink) throws IOException {
        final long[] lengths = this.lengths;
        for (int length = lengths.length, i = 0; i < length; ++i) {
            bufferedSink.writeByte(32).writeDecimalLong(lengths[i]);
        }
    }
}
