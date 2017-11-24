package okio;

import java.util.zip.*;
import java.io.*;

public final class GzipSource implements Source
{
    private static final byte FCOMMENT = 4;
    private static final byte FEXTRA = 2;
    private static final byte FHCRC = 1;
    private static final byte FNAME = 3;
    private static final byte SECTION_BODY = 1;
    private static final byte SECTION_DONE = 3;
    private static final byte SECTION_HEADER = 0;
    private static final byte SECTION_TRAILER = 2;
    private final CRC32 crc;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private int section;
    private final BufferedSource source;
    
    public GzipSource(final Source source) {
        this.section = 0;
        this.crc = new CRC32();
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.inflater = new Inflater(true);
        this.source = Okio.buffer(source);
        this.inflaterSource = new InflaterSource(this.source, this.inflater);
    }
    
    private void checkEqual(final String s, final int n, final int n2) throws IOException {
        if (n2 != n) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", s, n2, n));
        }
    }
    
    private void consumeHeader() throws IOException {
        this.source.require(10L);
        final byte byte1 = this.source.buffer().getByte(3L);
        boolean b;
        if ((0x1 & byte1 >> 1) == 0x1) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.updateCrc(this.source.buffer(), 0L, 10L);
        }
        this.checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8L);
        if ((0x1 & byte1 >> 2) == 0x1) {
            this.source.require(2L);
            if (b) {
                this.updateCrc(this.source.buffer(), 0L, 2L);
            }
            final short shortLe = this.source.buffer().readShortLe();
            this.source.require(shortLe);
            if (b) {
                this.updateCrc(this.source.buffer(), 0L, shortLe);
            }
            this.source.skip(shortLe);
        }
        if ((0x1 & byte1 >> 3) == 0x1) {
            final long index = this.source.indexOf((byte)0);
            if (index == -1L) {
                throw new EOFException();
            }
            if (b) {
                this.updateCrc(this.source.buffer(), 0L, 1L + index);
            }
            this.source.skip(1L + index);
        }
        if ((0x1 & byte1 >> 4) == 0x1) {
            final long index2 = this.source.indexOf((byte)0);
            if (index2 == -1L) {
                throw new EOFException();
            }
            if (b) {
                this.updateCrc(this.source.buffer(), 0L, 1L + index2);
            }
            this.source.skip(1L + index2);
        }
        if (b) {
            this.checkEqual("FHCRC", this.source.readShortLe(), (short)this.crc.getValue());
            this.crc.reset();
        }
    }
    
    private void consumeTrailer() throws IOException {
        this.checkEqual("CRC", this.source.readIntLe(), (int)this.crc.getValue());
        this.checkEqual("ISIZE", this.source.readIntLe(), this.inflater.getTotalOut());
    }
    
    private void updateCrc(final Buffer buffer, long n, long n2) {
        Segment segment;
        for (segment = buffer.head; n >= segment.limit - segment.pos; n -= segment.limit - segment.pos, segment = segment.next) {}
        while (n2 > 0L) {
            final int n3 = (int)(n + segment.pos);
            final int n4 = (int)Math.min(segment.limit - n3, n2);
            this.crc.update(segment.data, n3, n4);
            n2 -= n4;
            n = 0L;
            segment = segment.next;
        }
    }
    
    @Override
    public void close() throws IOException {
        this.inflaterSource.close();
    }
    
    @Override
    public long read(final Buffer buffer, final long n) throws IOException {
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (n == 0L) {
            return 0L;
        }
        if (this.section == 0) {
            this.consumeHeader();
            this.section = 1;
        }
        if (this.section == 1) {
            final long size = buffer.size;
            final long read = this.inflaterSource.read(buffer, n);
            if (read != -1L) {
                this.updateCrc(buffer, size, read);
                return read;
            }
            this.section = 2;
        }
        if (this.section == 2) {
            this.consumeTrailer();
            this.section = 3;
            if (!this.source.exhausted()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }
    
    @Override
    public Timeout timeout() {
        return this.source.timeout();
    }
}
