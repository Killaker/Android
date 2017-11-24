package okio;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.security.*;

public final class Buffer implements BufferedSource, BufferedSink, Cloneable
{
    private static final byte[] DIGITS;
    static final int REPLACEMENT_CHARACTER = 65533;
    Segment head;
    long size;
    
    static {
        DIGITS = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    }
    
    private void readFrom(final InputStream inputStream, long n, final boolean b) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (n > 0L || b) {
            final Segment writableSegment = this.writableSegment(1);
            final int read = inputStream.read(writableSegment.data, writableSegment.limit, (int)Math.min(n, 2048 - writableSegment.limit));
            if (read == -1) {
                if (b) {
                    break;
                }
                throw new EOFException();
            }
            else {
                writableSegment.limit += read;
                this.size += read;
                n -= read;
            }
        }
    }
    
    @Override
    public Buffer buffer() {
        return this;
    }
    
    public void clear() {
        try {
            this.skip(this.size);
        }
        catch (EOFException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public Buffer clone() {
        final Buffer buffer = new Buffer();
        if (this.size == 0L) {
            return buffer;
        }
        buffer.head = new Segment(this.head);
        final Segment head = buffer.head;
        final Segment head2 = buffer.head;
        final Segment head3 = buffer.head;
        head2.prev = head3;
        head.next = head3;
        for (Segment segment = this.head.next; segment != this.head; segment = segment.next) {
            buffer.head.prev.push(new Segment(segment));
        }
        buffer.size = this.size;
        return buffer;
    }
    
    @Override
    public void close() {
    }
    
    public long completeSegmentByteCount() {
        long size = this.size;
        if (size == 0L) {
            return 0L;
        }
        final Segment prev = this.head.prev;
        if (prev.limit < 2048 && prev.owner) {
            size -= prev.limit - prev.pos;
        }
        return size;
    }
    
    public Buffer copyTo(final OutputStream outputStream) throws IOException {
        return this.copyTo(outputStream, 0L, this.size);
    }
    
    public Buffer copyTo(final OutputStream outputStream, long n, long n2) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, n, n2);
        if (n2 != 0L) {
            Segment segment;
            for (segment = this.head; n >= segment.limit - segment.pos; n -= segment.limit - segment.pos, segment = segment.next) {}
            while (n2 > 0L) {
                final int n3 = (int)(n + segment.pos);
                final int n4 = (int)Math.min(segment.limit - n3, n2);
                outputStream.write(segment.data, n3, n4);
                n2 -= n4;
                n = 0L;
                segment = segment.next;
            }
        }
        return this;
    }
    
    public Buffer copyTo(final Buffer buffer, long n, long n2) {
        if (buffer == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, n, n2);
        if (n2 != 0L) {
            buffer.size += n2;
            Segment segment;
            for (segment = this.head; n >= segment.limit - segment.pos; n -= segment.limit - segment.pos, segment = segment.next) {}
            while (n2 > 0L) {
                final Segment head = new Segment(segment);
                head.pos += (int)n;
                head.limit = Math.min(head.pos + (int)n2, head.limit);
                if (buffer.head == null) {
                    head.prev = head;
                    head.next = head;
                    buffer.head = head;
                }
                else {
                    buffer.head.prev.push(head);
                }
                n2 -= head.limit - head.pos;
                n = 0L;
                segment = segment.next;
            }
        }
        return this;
    }
    
    @Override
    public BufferedSink emit() {
        return this;
    }
    
    @Override
    public Buffer emitCompleteSegments() {
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buffer)) {
            return false;
        }
        final Buffer buffer = (Buffer)o;
        if (this.size != buffer.size) {
            return false;
        }
        if (this.size == 0L) {
            return true;
        }
        Segment segment = this.head;
        Segment segment2 = buffer.head;
        int n = segment.pos;
        int n2 = segment2.pos;
        long n4;
        for (long n3 = 0L; n3 < this.size; n3 += n4) {
            n4 = Math.min(segment.limit - n, segment2.limit - n2);
            int n5 = 0;
            int n6 = n2;
            int n7 = n;
            while (n5 < n4) {
                final byte[] data = segment.data;
                final int n8 = n7 + 1;
                final byte b = data[n7];
                final byte[] data2 = segment2.data;
                final int n9 = n6 + 1;
                if (b != data2[n6]) {
                    return false;
                }
                ++n5;
                n6 = n9;
                n7 = n8;
            }
            if (n7 == segment.limit) {
                segment = segment.next;
                n = segment.pos;
            }
            else {
                n = n7;
            }
            if (n6 == segment2.limit) {
                segment2 = segment2.next;
                n2 = segment2.pos;
            }
            else {
                n2 = n6;
            }
        }
        return true;
    }
    
    @Override
    public boolean exhausted() {
        return this.size == 0L;
    }
    
    @Override
    public void flush() {
    }
    
    public byte getByte(long n) {
        Util.checkOffsetAndCount(this.size, n, 1L);
        Segment segment = this.head;
        while (true) {
            final int n2 = segment.limit - segment.pos;
            if (n < n2) {
                break;
            }
            n -= n2;
            segment = segment.next;
        }
        return segment.data[segment.pos + (int)n];
    }
    
    @Override
    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int n = 1;
        do {
            for (int i = segment.pos; i < segment.limit; ++i) {
                n = n * 31 + segment.data[i];
            }
            segment = segment.next;
        } while (segment != this.head);
        return n;
    }
    
    @Override
    public long indexOf(final byte b) {
        return this.indexOf(b, 0L);
    }
    
    @Override
    public long indexOf(final byte b, long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        long n2 = 0L;
        while (true) {
            final int n3 = segment.limit - segment.pos;
            if (n >= n3) {
                n -= n3;
            }
            else {
                final byte[] data = segment.data;
                for (int i = (int)(n + segment.pos); i < segment.limit; ++i) {
                    if (data[i] == b) {
                        return n2 + i - segment.pos;
                    }
                }
                n = 0L;
            }
            n2 += n3;
            segment = segment.next;
            if (segment == this.head) {
                return -1L;
            }
        }
    }
    
    @Override
    public long indexOf(final ByteString byteString) throws IOException {
        return this.indexOf(byteString, 0L);
    }
    
    @Override
    public long indexOf(final ByteString byteString, long n) throws IOException {
        if (byteString.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long index = 0L;
        Label_0022: {
            break Label_0022;
            do {
                n = index + 1L;
                index = this.indexOf(byteString.getByte(0), n);
                if (index == -1L) {
                    return -1L;
                }
            } while (!this.rangeEquals(index, byteString));
        }
        return index;
    }
    
    @Override
    public long indexOfElement(final ByteString byteString) {
        return this.indexOfElement(byteString, 0L);
    }
    
    @Override
    public long indexOfElement(final ByteString byteString, long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        long n2 = 0L;
        final byte[] byteArray = byteString.toByteArray();
        while (true) {
            final int n3 = segment.limit - segment.pos;
            if (n >= n3) {
                n -= n3;
            }
            else {
                final byte[] data = segment.data;
                for (long n4 = n + segment.pos; n4 < segment.limit; ++n4) {
                    final byte b = data[(int)n4];
                    for (int length = byteArray.length, i = 0; i < length; ++i) {
                        if (b == byteArray[i]) {
                            return n2 + n4 - segment.pos;
                        }
                    }
                }
                n = 0L;
            }
            n2 += n3;
            segment = segment.next;
            if (segment == this.head) {
                return -1L;
            }
        }
    }
    
    @Override
    public InputStream inputStream() {
        return new InputStream() {
            @Override
            public int available() {
                return (int)Math.min(Buffer.this.size, 2147483647L);
            }
            
            @Override
            public void close() {
            }
            
            @Override
            public int read() {
                if (Buffer.this.size > 0L) {
                    return 0xFF & Buffer.this.readByte();
                }
                return -1;
            }
            
            @Override
            public int read(final byte[] array, final int n, final int n2) {
                return Buffer.this.read(array, n, n2);
            }
            
            @Override
            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }
    
    @Override
    public OutputStream outputStream() {
        return new OutputStream() {
            @Override
            public void close() {
            }
            
            @Override
            public void flush() {
            }
            
            @Override
            public String toString() {
                return this + ".outputStream()";
            }
            
            @Override
            public void write(final int n) {
                Buffer.this.writeByte((int)(byte)n);
            }
            
            @Override
            public void write(final byte[] array, final int n, final int n2) {
                Buffer.this.write(array, n, n2);
            }
        };
    }
    
    boolean rangeEquals(final long n, final ByteString byteString) {
        final int size = byteString.size();
        if (this.size - n >= size) {
            for (int i = 0; i < size; ++i) {
                if (this.getByte(n + i) != byteString.getByte(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        Util.checkOffsetAndCount(array.length, n, n2);
        final Segment head = this.head;
        int min;
        if (head == null) {
            min = -1;
        }
        else {
            min = Math.min(n2, head.limit - head.pos);
            System.arraycopy(head.data, head.pos, array, n, min);
            head.pos += min;
            this.size -= min;
            if (head.pos == head.limit) {
                this.head = head.pop();
                SegmentPool.recycle(head);
                return min;
            }
        }
        return min;
    }
    
    @Override
    public long read(final Buffer buffer, long size) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (size < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + size);
        }
        if (this.size == 0L) {
            return -1L;
        }
        if (size > this.size) {
            size = this.size;
        }
        buffer.write(this, size);
        return size;
    }
    
    @Override
    public long readAll(final Sink sink) throws IOException {
        final long size = this.size;
        if (size > 0L) {
            sink.write(this, size);
        }
        return size;
    }
    
    @Override
    public byte readByte() {
        if (this.size == 0L) {
            throw new IllegalStateException("size == 0");
        }
        final Segment head = this.head;
        final int pos = head.pos;
        final int limit = head.limit;
        final byte[] data = head.data;
        final int pos2 = pos + 1;
        final byte b = data[pos];
        --this.size;
        if (pos2 == limit) {
            this.head = head.pop();
            SegmentPool.recycle(head);
            return b;
        }
        head.pos = pos2;
        return b;
    }
    
    @Override
    public byte[] readByteArray() {
        try {
            return this.readByteArray(this.size);
        }
        catch (EOFException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public byte[] readByteArray(final long n) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, n);
        if (n > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + n);
        }
        final byte[] array = new byte[(int)n];
        this.readFully(array);
        return array;
    }
    
    @Override
    public ByteString readByteString() {
        return new ByteString(this.readByteArray());
    }
    
    @Override
    public ByteString readByteString(final long n) throws EOFException {
        return new ByteString(this.readByteArray(n));
    }
    
    @Override
    public long readDecimalLong() {
        if (this.size == 0L) {
            throw new IllegalStateException("size == 0");
        }
        long n = 0L;
        int n2 = 0;
        int n3 = 0;
        boolean b = false;
        long n4 = -7L;
        while (true) {
            final Segment head = this.head;
            final byte[] data = head.data;
            int i;
            int limit;
            for (i = head.pos, limit = head.limit; i < limit; ++i, ++n2) {
                final byte b2 = data[i];
                if (b2 >= 48 && b2 <= 57) {
                    final byte b3 = (byte)(48 - b2);
                    if (n < -922337203685477580L || (n == -922337203685477580L && b3 < n4)) {
                        final Buffer writeByte = new Buffer().writeDecimalLong(n).writeByte((int)b2);
                        if (n3 == 0) {
                            writeByte.readByte();
                        }
                        throw new NumberFormatException("Number too large: " + writeByte.readUtf8());
                    }
                    n = n * 10L + b3;
                }
                else if (b2 == 45 && n2 == 0) {
                    n3 = 1;
                    --n4;
                }
                else {
                    if (n2 == 0) {
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(b2));
                    }
                    b = true;
                    break;
                }
            }
            if (i == limit) {
                this.head = head.pop();
                SegmentPool.recycle(head);
            }
            else {
                head.pos = i;
            }
            if (b || this.head == null) {
                this.size -= n2;
                if (n3 != 0) {
                    return n;
                }
                return -n;
            }
        }
    }
    
    public Buffer readFrom(final InputStream inputStream) throws IOException {
        this.readFrom(inputStream, Long.MAX_VALUE, true);
        return this;
    }
    
    public Buffer readFrom(final InputStream inputStream, final long n) throws IOException {
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        this.readFrom(inputStream, n, false);
        return this;
    }
    
    @Override
    public void readFully(final Buffer buffer, final long n) throws EOFException {
        if (this.size < n) {
            buffer.write(this, this.size);
            throw new EOFException();
        }
        buffer.write(this, n);
    }
    
    @Override
    public void readFully(final byte[] array) throws EOFException {
        int read;
        for (int i = 0; i < array.length; i += read) {
            read = this.read(array, i, array.length - i);
            if (read == -1) {
                throw new EOFException();
            }
        }
    }
    
    @Override
    public long readHexadecimalUnsignedLong() {
        if (this.size == 0L) {
            throw new IllegalStateException("size == 0");
        }
        long n = 0L;
        int n2 = 0;
        boolean b = false;
        while (true) {
            final Segment head = this.head;
            final byte[] data = head.data;
            int i;
            int limit;
            for (i = head.pos, limit = head.limit; i < limit; ++i, ++n2) {
                final byte b2 = data[i];
                byte b3;
                if (b2 >= 48 && b2 <= 57) {
                    b3 = (byte)(b2 - 48);
                }
                else if (b2 >= 97 && b2 <= 102) {
                    b3 = (byte)(10 + (b2 - 97));
                }
                else if (b2 >= 65 && b2 <= 70) {
                    b3 = (byte)(10 + (b2 - 65));
                }
                else {
                    if (n2 == 0) {
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b2));
                    }
                    b = true;
                    break;
                }
                if ((0xF000000000000000L & n) != 0x0L) {
                    throw new NumberFormatException("Number too large: " + new Buffer().writeHexadecimalUnsignedLong(n).writeByte((int)b2).readUtf8());
                }
                n = (n << 4 | b3);
            }
            if (i == limit) {
                this.head = head.pop();
                SegmentPool.recycle(head);
            }
            else {
                head.pos = i;
            }
            if (b || this.head == null) {
                this.size -= n2;
                return n;
            }
        }
    }
    
    @Override
    public int readInt() {
        if (this.size < 4L) {
            throw new IllegalStateException("size < 4: " + this.size);
        }
        final Segment head = this.head;
        final int pos = head.pos;
        final int limit = head.limit;
        if (limit - pos < 4) {
            return (0xFF & this.readByte()) << 24 | (0xFF & this.readByte()) << 16 | (0xFF & this.readByte()) << 8 | (0xFF & this.readByte());
        }
        final byte[] data = head.data;
        final int n = pos + 1;
        final int n2 = (0xFF & data[pos]) << 24;
        final int n3 = n + 1;
        final int n4 = n2 | (0xFF & data[n]) << 16;
        final int n5 = n3 + 1;
        final int n6 = n4 | (0xFF & data[n3]) << 8;
        final int pos2 = n5 + 1;
        final int n7 = n6 | (0xFF & data[n5]);
        this.size -= 4L;
        if (pos2 == limit) {
            this.head = head.pop();
            SegmentPool.recycle(head);
            return n7;
        }
        head.pos = pos2;
        return n7;
    }
    
    @Override
    public int readIntLe() {
        return Util.reverseBytesInt(this.readInt());
    }
    
    @Override
    public long readLong() {
        if (this.size < 8L) {
            throw new IllegalStateException("size < 8: " + this.size);
        }
        final Segment head = this.head;
        final int pos = head.pos;
        final int limit = head.limit;
        if (limit - pos < 8) {
            return (0xFFFFFFFFL & this.readInt()) << 32 | (0xFFFFFFFFL & this.readInt());
        }
        final byte[] data = head.data;
        final int n = pos + 1;
        final long n2 = (0xFFL & data[pos]) << 56;
        final int n3 = n + 1;
        final long n4 = n2 | (0xFFL & data[n]) << 48;
        final int n5 = n3 + 1;
        final long n6 = n4 | (0xFFL & data[n3]) << 40;
        final int n7 = n5 + 1;
        final long n8 = n6 | (0xFFL & data[n5]) << 32;
        final int n9 = n7 + 1;
        final long n10 = n8 | (0xFFL & data[n7]) << 24;
        final int n11 = n9 + 1;
        final long n12 = n10 | (0xFFL & data[n9]) << 16;
        final int n13 = n11 + 1;
        final long n14 = n12 | (0xFFL & data[n11]) << 8;
        final int pos2 = n13 + 1;
        final long n15 = n14 | (0xFFL & data[n13]);
        this.size -= 8L;
        if (pos2 == limit) {
            this.head = head.pop();
            SegmentPool.recycle(head);
            return n15;
        }
        head.pos = pos2;
        return n15;
    }
    
    @Override
    public long readLongLe() {
        return Util.reverseBytesLong(this.readLong());
    }
    
    @Override
    public short readShort() {
        if (this.size < 2L) {
            throw new IllegalStateException("size < 2: " + this.size);
        }
        final Segment head = this.head;
        final int pos = head.pos;
        final int limit = head.limit;
        if (limit - pos < 2) {
            return (short)((0xFF & this.readByte()) << 8 | (0xFF & this.readByte()));
        }
        final byte[] data = head.data;
        final int n = pos + 1;
        final int n2 = (0xFF & data[pos]) << 8;
        final int pos2 = n + 1;
        final int n3 = n2 | (0xFF & data[n]);
        this.size -= 2L;
        if (pos2 == limit) {
            this.head = head.pop();
            SegmentPool.recycle(head);
        }
        else {
            head.pos = pos2;
        }
        return (short)n3;
    }
    
    @Override
    public short readShortLe() {
        return Util.reverseBytesShort(this.readShort());
    }
    
    @Override
    public String readString(final long n, final Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, n);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (n > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + n);
        }
        String s;
        if (n == 0L) {
            s = "";
        }
        else {
            final Segment head = this.head;
            if (n + head.pos > head.limit) {
                return new String(this.readByteArray(n), charset);
            }
            s = new String(head.data, head.pos, (int)n, charset);
            head.pos += (int)n;
            this.size -= n;
            if (head.pos == head.limit) {
                this.head = head.pop();
                SegmentPool.recycle(head);
                return s;
            }
        }
        return s;
    }
    
    @Override
    public String readString(final Charset charset) {
        try {
            return this.readString(this.size, charset);
        }
        catch (EOFException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public String readUtf8() {
        try {
            return this.readString(this.size, Util.UTF_8);
        }
        catch (EOFException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public String readUtf8(final long n) throws EOFException {
        return this.readString(n, Util.UTF_8);
    }
    
    @Override
    public int readUtf8CodePoint() throws EOFException {
        if (this.size == 0L) {
            throw new EOFException();
        }
        final byte byte1 = this.getByte(0L);
        int n;
        int n2;
        int n3;
        if ((byte1 & 0x80) == 0x0) {
            n = (byte1 & 0x7F);
            n2 = 1;
            n3 = 0;
        }
        else if ((byte1 & 0xE0) == 0xC0) {
            n = (byte1 & 0x1F);
            n2 = 2;
            n3 = 128;
        }
        else if ((byte1 & 0xF0) == 0xE0) {
            n = (byte1 & 0xF);
            n2 = 3;
            n3 = 2048;
        }
        else {
            if ((byte1 & 0xF8) != 0xF0) {
                this.skip(1L);
                n = 65533;
                return n;
            }
            n = (byte1 & 0x7);
            n2 = 4;
            n3 = 65536;
        }
        if (this.size < n2) {
            throw new EOFException("size < " + n2 + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(byte1) + ")");
        }
        for (int i = 1; i < n2; ++i) {
            final byte byte2 = this.getByte(i);
            if ((byte2 & 0xC0) != 0x80) {
                this.skip(i);
                return 65533;
            }
            n = (n << 6 | (byte2 & 0x3F));
        }
        this.skip(n2);
        if (n > 1114111) {
            return 65533;
        }
        if (n >= 55296 && n <= 57343) {
            return 65533;
        }
        if (n < n3) {
            return 65533;
        }
        return n;
    }
    
    @Override
    public String readUtf8Line() throws EOFException {
        final long index = this.indexOf((byte)10);
        if (index != -1L) {
            return this.readUtf8Line(index);
        }
        if (this.size != 0L) {
            return this.readUtf8(this.size);
        }
        return null;
    }
    
    String readUtf8Line(final long n) throws EOFException {
        if (n > 0L && this.getByte(n - 1L) == 13) {
            final String utf8 = this.readUtf8(n - 1L);
            this.skip(2L);
            return utf8;
        }
        final String utf9 = this.readUtf8(n);
        this.skip(1L);
        return utf9;
    }
    
    @Override
    public String readUtf8LineStrict() throws EOFException {
        final long index = this.indexOf((byte)10);
        if (index == -1L) {
            final Buffer buffer = new Buffer();
            this.copyTo(buffer, 0L, Math.min(32L, this.size));
            throw new EOFException("\\n not found: size=" + this.size() + " content=" + buffer.readByteString().hex() + "...");
        }
        return this.readUtf8Line(index);
    }
    
    @Override
    public boolean request(final long n) {
        return this.size >= n;
    }
    
    @Override
    public void require(final long n) throws EOFException {
        if (this.size < n) {
            throw new EOFException();
        }
    }
    
    List<Integer> segmentSizes() {
        List<Integer> emptyList;
        if (this.head == null) {
            emptyList = Collections.emptyList();
        }
        else {
            emptyList = new ArrayList<Integer>();
            emptyList.add(this.head.limit - this.head.pos);
            for (Segment segment = this.head.next; segment != this.head; segment = segment.next) {
                emptyList.add(segment.limit - segment.pos);
            }
        }
        return emptyList;
    }
    
    public long size() {
        return this.size;
    }
    
    @Override
    public void skip(long n) throws EOFException {
        while (n > 0L) {
            if (this.head == null) {
                throw new EOFException();
            }
            final int n2 = (int)Math.min(n, this.head.limit - this.head.pos);
            this.size -= n2;
            n -= n2;
            final Segment head = this.head;
            head.pos += n2;
            if (this.head.pos != this.head.limit) {
                continue;
            }
            final Segment head2 = this.head;
            this.head = head2.pop();
            SegmentPool.recycle(head2);
        }
    }
    
    public ByteString snapshot() {
        if (this.size > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
        }
        return this.snapshot((int)this.size);
    }
    
    public ByteString snapshot(final int n) {
        if (n == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, n);
    }
    
    @Override
    public Timeout timeout() {
        return Timeout.NONE;
    }
    
    @Override
    public String toString() {
        if (this.size == 0L) {
            return "Buffer[size=0]";
        }
        if (this.size <= 16L) {
            return String.format("Buffer[size=%s data=%s]", this.size, this.clone().readByteString().hex());
        }
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
            for (Segment segment = this.head.next; segment != this.head; segment = segment.next) {
                instance.update(segment.data, segment.pos, segment.limit - segment.pos);
            }
            return String.format("Buffer[size=%s md5=%s]", this.size, ByteString.of(instance.digest()).hex());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new AssertionError();
        }
    }
    
    Segment writableSegment(final int n) {
        if (n < 1 || n > 2048) {
            throw new IllegalArgumentException();
        }
        Segment segment;
        if (this.head == null) {
            this.head = SegmentPool.take();
            final Segment head = this.head;
            final Segment head2 = this.head;
            segment = this.head;
            head2.prev = segment;
            head.next = segment;
        }
        else {
            segment = this.head.prev;
            if (n + segment.limit > 2048 || !segment.owner) {
                return segment.push(SegmentPool.take());
            }
        }
        return segment;
    }
    
    @Override
    public Buffer write(final ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }
    
    @Override
    public Buffer write(final byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("source == null");
        }
        return this.write(array, 0, array.length);
    }
    
    @Override
    public Buffer write(final byte[] array, int i, final int n) {
        if (array == null) {
            throw new IllegalArgumentException("source == null");
        }
        Util.checkOffsetAndCount(array.length, i, n);
        Segment writableSegment;
        int min;
        for (int n2 = i + n; i < n2; i += min, writableSegment.limit += min) {
            writableSegment = this.writableSegment(1);
            min = Math.min(n2 - i, 2048 - writableSegment.limit);
            System.arraycopy(array, i, writableSegment.data, writableSegment.limit, min);
        }
        this.size += n;
        return this;
    }
    
    @Override
    public BufferedSink write(final Source source, long n) throws IOException {
        while (n > 0L) {
            final long read = source.read(this, n);
            if (read == -1L) {
                throw new EOFException();
            }
            n -= read;
        }
        return this;
    }
    
    @Override
    public void write(final Buffer buffer, long n) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (buffer == this) {
            throw new IllegalArgumentException("source == this");
        }
        Util.checkOffsetAndCount(buffer.size, 0L, n);
        while (n > 0L) {
            if (n < buffer.head.limit - buffer.head.pos) {
                Segment prev;
                if (this.head != null) {
                    prev = this.head.prev;
                }
                else {
                    prev = null;
                }
                if (prev != null && prev.owner) {
                    final long n2 = n + prev.limit;
                    int pos;
                    if (prev.shared) {
                        pos = 0;
                    }
                    else {
                        pos = prev.pos;
                    }
                    if (n2 - pos <= 2048L) {
                        buffer.head.writeTo(prev, (int)n);
                        buffer.size -= n;
                        this.size += n;
                        break;
                    }
                }
                buffer.head = buffer.head.split((int)n);
            }
            final Segment head = buffer.head;
            final long n3 = head.limit - head.pos;
            buffer.head = head.pop();
            if (this.head == null) {
                this.head = head;
                final Segment head2 = this.head;
                final Segment head3 = this.head;
                final Segment head4 = this.head;
                head3.prev = head4;
                head2.next = head4;
            }
            else {
                this.head.prev.push(head).compact();
            }
            buffer.size -= n3;
            this.size += n3;
            n -= n3;
        }
    }
    
    @Override
    public long writeAll(final Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long n = 0L;
        while (true) {
            final long read = source.read(this, 2048L);
            if (read == -1L) {
                break;
            }
            n += read;
        }
        return n;
    }
    
    @Override
    public Buffer writeByte(final int n) {
        final Segment writableSegment = this.writableSegment(1);
        writableSegment.data[writableSegment.limit++] = (byte)n;
        ++this.size;
        return this;
    }
    
    @Override
    public Buffer writeDecimalLong(long n) {
        if (n == 0L) {
            return this.writeByte(48);
        }
        final long n2 = lcmp(n, 0L);
        boolean b = false;
        if (n2 < 0) {
            n = -n;
            if (n < 0L) {
                return this.writeUtf8("-9223372036854775808");
            }
            b = true;
        }
        int n3;
        if (n < 100000000L) {
            if (n < 10000L) {
                if (n < 100L) {
                    if (n < 10L) {
                        n3 = 1;
                    }
                    else {
                        n3 = 2;
                    }
                }
                else if (n < 1000L) {
                    n3 = 3;
                }
                else {
                    n3 = 4;
                }
            }
            else if (n < 1000000L) {
                if (n < 100000L) {
                    n3 = 5;
                }
                else {
                    n3 = 6;
                }
            }
            else if (n < 10000000L) {
                n3 = 7;
            }
            else {
                n3 = 8;
            }
        }
        else if (n < 1000000000000L) {
            if (n < 10000000000L) {
                if (n < 1000000000L) {
                    n3 = 9;
                }
                else {
                    n3 = 10;
                }
            }
            else if (n < 100000000000L) {
                n3 = 11;
            }
            else {
                n3 = 12;
            }
        }
        else if (n < 1000000000000000L) {
            if (n < 10000000000000L) {
                n3 = 13;
            }
            else if (n < 100000000000000L) {
                n3 = 14;
            }
            else {
                n3 = 15;
            }
        }
        else if (n < 100000000000000000L) {
            if (n < 10000000000000000L) {
                n3 = 16;
            }
            else {
                n3 = 17;
            }
        }
        else if (n < 1000000000000000000L) {
            n3 = 18;
        }
        else {
            n3 = 19;
        }
        if (b) {
            ++n3;
        }
        final Segment writableSegment = this.writableSegment(n3);
        final byte[] data = writableSegment.data;
        int n4 = n3 + writableSegment.limit;
        while (n != 0L) {
            final int n5 = (int)(n % 10L);
            --n4;
            data[n4] = Buffer.DIGITS[n5];
            n /= 10L;
        }
        if (b) {
            data[n4 - 1] = 45;
        }
        writableSegment.limit += n3;
        this.size += n3;
        return this;
    }
    
    @Override
    public Buffer writeHexadecimalUnsignedLong(long n) {
        if (n == 0L) {
            return this.writeByte(48);
        }
        final int n2 = 1 + Long.numberOfTrailingZeros(Long.highestOneBit(n)) / 4;
        final Segment writableSegment = this.writableSegment(n2);
        final byte[] data = writableSegment.data;
        for (int i = -1 + (n2 + writableSegment.limit); i >= writableSegment.limit; --i) {
            data[i] = Buffer.DIGITS[(int)(0xFL & n)];
            n >>>= 4;
        }
        writableSegment.limit += n2;
        this.size += n2;
        return this;
    }
    
    @Override
    public Buffer writeInt(final int n) {
        final Segment writableSegment = this.writableSegment(4);
        final byte[] data = writableSegment.data;
        final int limit = writableSegment.limit;
        final int n2 = limit + 1;
        data[limit] = (byte)(0xFF & n >>> 24);
        final int n3 = n2 + 1;
        data[n2] = (byte)(0xFF & n >>> 16);
        final int n4 = n3 + 1;
        data[n3] = (byte)(0xFF & n >>> 8);
        final int limit2 = n4 + 1;
        data[n4] = (byte)(n & 0xFF);
        writableSegment.limit = limit2;
        this.size += 4L;
        return this;
    }
    
    @Override
    public Buffer writeIntLe(final int n) {
        return this.writeInt(Util.reverseBytesInt(n));
    }
    
    @Override
    public Buffer writeLong(final long n) {
        final Segment writableSegment = this.writableSegment(8);
        final byte[] data = writableSegment.data;
        final int limit = writableSegment.limit;
        final int n2 = limit + 1;
        data[limit] = (byte)(0xFFL & n >>> 56);
        final int n3 = n2 + 1;
        data[n2] = (byte)(0xFFL & n >>> 48);
        final int n4 = n3 + 1;
        data[n3] = (byte)(0xFFL & n >>> 40);
        final int n5 = n4 + 1;
        data[n4] = (byte)(0xFFL & n >>> 32);
        final int n6 = n5 + 1;
        data[n5] = (byte)(0xFFL & n >>> 24);
        final int n7 = n6 + 1;
        data[n6] = (byte)(0xFFL & n >>> 16);
        final int n8 = n7 + 1;
        data[n7] = (byte)(0xFFL & n >>> 8);
        final int limit2 = n8 + 1;
        data[n8] = (byte)(n & 0xFFL);
        writableSegment.limit = limit2;
        this.size += 8L;
        return this;
    }
    
    @Override
    public Buffer writeLongLe(final long n) {
        return this.writeLong(Util.reverseBytesLong(n));
    }
    
    @Override
    public Buffer writeShort(final int n) {
        final Segment writableSegment = this.writableSegment(2);
        final byte[] data = writableSegment.data;
        final int limit = writableSegment.limit;
        final int n2 = limit + 1;
        data[limit] = (byte)(0xFF & n >>> 8);
        final int limit2 = n2 + 1;
        data[n2] = (byte)(n & 0xFF);
        writableSegment.limit = limit2;
        this.size += 2L;
        return this;
    }
    
    @Override
    public Buffer writeShortLe(final int n) {
        return this.writeShort((int)Util.reverseBytesShort((short)n));
    }
    
    @Override
    public Buffer writeString(final String s, final int n, final int n2, final Charset charset) {
        if (s == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (n < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + n);
        }
        if (n2 < n) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + n2 + " < " + n);
        }
        if (n2 > s.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + n2 + " > " + s.length());
        }
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (charset.equals(Util.UTF_8)) {
            return this.writeUtf8(s);
        }
        final byte[] bytes = s.substring(n, n2).getBytes(charset);
        return this.write(bytes, 0, bytes.length);
    }
    
    @Override
    public Buffer writeString(final String s, final Charset charset) {
        return this.writeString(s, 0, s.length(), charset);
    }
    
    public Buffer writeTo(final OutputStream outputStream) throws IOException {
        return this.writeTo(outputStream, this.size);
    }
    
    public Buffer writeTo(final OutputStream outputStream, long n) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, 0L, n);
        Segment head = this.head;
        while (n > 0L) {
            final int n2 = (int)Math.min(n, head.limit - head.pos);
            outputStream.write(head.data, head.pos, n2);
            head.pos += n2;
            this.size -= n2;
            n -= n2;
            if (head.pos == head.limit) {
                final Segment segment = head;
                head = segment.pop();
                this.head = head;
                SegmentPool.recycle(segment);
            }
        }
        return this;
    }
    
    @Override
    public Buffer writeUtf8(final String s) {
        return this.writeUtf8(s, 0, s.length());
    }
    
    @Override
    public Buffer writeUtf8(final String s, final int n, final int n2) {
        if (s == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (n < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + n);
        }
        if (n2 < n) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + n2 + " < " + n);
        }
        if (n2 > s.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + n2 + " > " + s.length());
        }
        int i = n;
        while (i < n2) {
            final char char1 = s.charAt(i);
            int n7;
            if (char1 < '\u0080') {
                final Segment writableSegment = this.writableSegment(1);
                final byte[] data = writableSegment.data;
                final int n3 = writableSegment.limit - i;
                final int min = Math.min(n2, 2048 - n3);
                final int n4 = i + 1;
                data[n3 + i] = (byte)char1;
                int j;
                int n5;
                for (j = n4; j < min; j = n5) {
                    final char char2 = s.charAt(j);
                    if (char2 >= '\u0080') {
                        break;
                    }
                    n5 = j + 1;
                    data[n3 + j] = (byte)char2;
                }
                final int n6 = j + n3 - writableSegment.limit;
                writableSegment.limit += n6;
                this.size += n6;
                n7 = j;
            }
            else if (char1 < '\u0800') {
                this.writeByte('\u00c0' | char1 >> 6);
                this.writeByte('\u0080' | (char1 & '?'));
                n7 = i + 1;
            }
            else if (char1 < '\ud800' || char1 > '\udfff') {
                this.writeByte('\u00e0' | char1 >> 12);
                this.writeByte('\u0080' | ('?' & char1 >> 6));
                this.writeByte('\u0080' | (char1 & '?'));
                n7 = i + 1;
            }
            else {
                char char3;
                if (i + 1 < n2) {
                    char3 = s.charAt(i + 1);
                }
                else {
                    char3 = '\0';
                }
                if (char1 > '\udbff' || char3 < '\udc00' || char3 > '\udfff') {
                    this.writeByte(63);
                    ++i;
                    continue;
                }
                final int n8 = 65536 + ((0xFFFF27FF & char1) << 10 | (0xFFFF23FF & char3));
                this.writeByte(0xF0 | n8 >> 18);
                this.writeByte(0x80 | (0x3F & n8 >> 12));
                this.writeByte(0x80 | (0x3F & n8 >> 6));
                this.writeByte(0x80 | (n8 & 0x3F));
                n7 = i + 2;
            }
            i = n7;
        }
        return this;
    }
    
    @Override
    public Buffer writeUtf8CodePoint(final int n) {
        if (n < 128) {
            this.writeByte(n);
            return this;
        }
        if (n < 2048) {
            this.writeByte(0xC0 | n >> 6);
            this.writeByte(0x80 | (n & 0x3F));
            return this;
        }
        if (n < 65536) {
            if (n >= 55296 && n <= 57343) {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(n));
            }
            this.writeByte(0xE0 | n >> 12);
            this.writeByte(0x80 | (0x3F & n >> 6));
            this.writeByte(0x80 | (n & 0x3F));
            return this;
        }
        else {
            if (n <= 1114111) {
                this.writeByte(0xF0 | n >> 18);
                this.writeByte(0x80 | (0x3F & n >> 12));
                this.writeByte(0x80 | (0x3F & n >> 6));
                this.writeByte(0x80 | (n & 0x3F));
                return this;
            }
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(n));
        }
    }
}
