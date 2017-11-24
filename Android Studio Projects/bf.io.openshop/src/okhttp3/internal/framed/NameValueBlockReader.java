package okhttp3.internal.framed;

import java.io.*;
import java.util.zip.*;
import okio.*;
import java.util.*;

class NameValueBlockReader
{
    private int compressedLimit;
    private final InflaterSource inflaterSource;
    private final BufferedSource source;
    
    public NameValueBlockReader(final BufferedSource bufferedSource) {
        this.inflaterSource = new InflaterSource(new ForwardingSource(bufferedSource) {
            @Override
            public long read(final Buffer buffer, final long n) throws IOException {
                if (NameValueBlockReader.this.compressedLimit == 0) {
                    return -1L;
                }
                final long read = super.read(buffer, Math.min(n, NameValueBlockReader.this.compressedLimit));
                if (read == -1L) {
                    return -1L;
                }
                NameValueBlockReader.access$022(NameValueBlockReader.this, read);
                return read;
            }
        }, new Inflater() {
            @Override
            public int inflate(final byte[] array, final int n, final int n2) throws DataFormatException {
                int n3 = super.inflate(array, n, n2);
                if (n3 == 0 && this.needsDictionary()) {
                    this.setDictionary(Spdy3.DICTIONARY);
                    n3 = super.inflate(array, n, n2);
                }
                return n3;
            }
        });
        this.source = Okio.buffer(this.inflaterSource);
    }
    
    static /* synthetic */ int access$022(final NameValueBlockReader nameValueBlockReader, final long n) {
        return nameValueBlockReader.compressedLimit -= (int)n;
    }
    
    private void doneReading() throws IOException {
        if (this.compressedLimit > 0) {
            this.inflaterSource.refill();
            if (this.compressedLimit != 0) {
                throw new IOException("compressedLimit > 0: " + this.compressedLimit);
            }
        }
    }
    
    private ByteString readByteString() throws IOException {
        return this.source.readByteString(this.source.readInt());
    }
    
    public void close() throws IOException {
        this.source.close();
    }
    
    public List<Header> readNameValueBlock(final int n) throws IOException {
        this.compressedLimit += n;
        final int int1 = this.source.readInt();
        if (int1 < 0) {
            throw new IOException("numberOfPairs < 0: " + int1);
        }
        if (int1 > 1024) {
            throw new IOException("numberOfPairs > 1024: " + int1);
        }
        final ArrayList list = new ArrayList<Header>(int1);
        for (int i = 0; i < int1; ++i) {
            final ByteString asciiLowercase = this.readByteString().toAsciiLowercase();
            final ByteString byteString = this.readByteString();
            if (asciiLowercase.size() == 0) {
                throw new IOException("name.size == 0");
            }
            list.add(new Header(asciiLowercase, byteString));
        }
        this.doneReading();
        return (List<Header>)list;
    }
}
