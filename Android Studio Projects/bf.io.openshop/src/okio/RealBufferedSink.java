package okio;

import java.io.*;
import java.nio.charset.*;

final class RealBufferedSink implements BufferedSink
{
    public final Buffer buffer;
    private boolean closed;
    public final Sink sink;
    
    public RealBufferedSink(final Sink sink) {
        this(sink, new Buffer());
    }
    
    public RealBufferedSink(final Sink sink, final Buffer buffer) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.buffer = buffer;
        this.sink = sink;
    }
    
    @Override
    public Buffer buffer() {
        return this.buffer;
    }
    
    @Override
    public void close() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        okio/RealBufferedSink.closed:Z
        //     4: ifeq            8
        //     7: return         
        //     8: aload_0        
        //     9: getfield        okio/RealBufferedSink.buffer:Lokio/Buffer;
        //    12: getfield        okio/Buffer.size:J
        //    15: lconst_0       
        //    16: lcmp           
        //    17: istore          4
        //    19: aconst_null    
        //    20: astore_2       
        //    21: iload           4
        //    23: ifle            46
        //    26: aload_0        
        //    27: getfield        okio/RealBufferedSink.sink:Lokio/Sink;
        //    30: aload_0        
        //    31: getfield        okio/RealBufferedSink.buffer:Lokio/Buffer;
        //    34: aload_0        
        //    35: getfield        okio/RealBufferedSink.buffer:Lokio/Buffer;
        //    38: getfield        okio/Buffer.size:J
        //    41: invokeinterface okio/Sink.write:(Lokio/Buffer;J)V
        //    46: aload_0        
        //    47: getfield        okio/RealBufferedSink.sink:Lokio/Sink;
        //    50: invokeinterface okio/Sink.close:()V
        //    55: aload_0        
        //    56: iconst_1       
        //    57: putfield        okio/RealBufferedSink.closed:Z
        //    60: aload_2        
        //    61: ifnull          7
        //    64: aload_2        
        //    65: invokestatic    okio/Util.sneakyRethrow:(Ljava/lang/Throwable;)V
        //    68: return         
        //    69: astore_1       
        //    70: aload_1        
        //    71: astore_2       
        //    72: goto            46
        //    75: astore_3       
        //    76: aload_2        
        //    77: ifnonnull       55
        //    80: aload_3        
        //    81: astore_2       
        //    82: goto            55
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      19     69     75     Ljava/lang/Throwable;
        //  26     46     69     75     Ljava/lang/Throwable;
        //  46     55     75     85     Ljava/lang/Throwable;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public BufferedSink emit() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        final long size = this.buffer.size();
        if (size > 0L) {
            this.sink.write(this.buffer, size);
        }
        return this;
    }
    
    @Override
    public BufferedSink emitCompleteSegments() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        final long completeSegmentByteCount = this.buffer.completeSegmentByteCount();
        if (completeSegmentByteCount > 0L) {
            this.sink.write(this.buffer, completeSegmentByteCount);
        }
        return this;
    }
    
    @Override
    public void flush() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.buffer.size > 0L) {
            this.sink.write(this.buffer, this.buffer.size);
        }
        this.sink.flush();
    }
    
    @Override
    public OutputStream outputStream() {
        return new OutputStream() {
            @Override
            public void close() throws IOException {
                RealBufferedSink.this.close();
            }
            
            @Override
            public void flush() throws IOException {
                if (!RealBufferedSink.this.closed) {
                    RealBufferedSink.this.flush();
                }
            }
            
            @Override
            public String toString() {
                return RealBufferedSink.this + ".outputStream()";
            }
            
            @Override
            public void write(final int n) throws IOException {
                if (RealBufferedSink.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSink.this.buffer.writeByte((int)(byte)n);
                RealBufferedSink.this.emitCompleteSegments();
            }
            
            @Override
            public void write(final byte[] array, final int n, final int n2) throws IOException {
                if (RealBufferedSink.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSink.this.buffer.write(array, n, n2);
                RealBufferedSink.this.emitCompleteSegments();
            }
        };
    }
    
    @Override
    public Timeout timeout() {
        return this.sink.timeout();
    }
    
    @Override
    public String toString() {
        return "buffer(" + this.sink + ")";
    }
    
    @Override
    public BufferedSink write(final ByteString byteString) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(byteString);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink write(final Source source, long n) throws IOException {
        while (n > 0L) {
            final long read = source.read(this.buffer, n);
            if (read == -1L) {
                throw new EOFException();
            }
            n -= read;
            this.emitCompleteSegments();
        }
        return this;
    }
    
    @Override
    public BufferedSink write(final byte[] array) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(array);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink write(final byte[] array, final int n, final int n2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(array, n, n2);
        return this.emitCompleteSegments();
    }
    
    @Override
    public void write(final Buffer buffer, final long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(buffer, n);
        this.emitCompleteSegments();
    }
    
    @Override
    public long writeAll(final Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long n = 0L;
        while (true) {
            final long read = source.read(this.buffer, 2048L);
            if (read == -1L) {
                break;
            }
            n += read;
            this.emitCompleteSegments();
        }
        return n;
    }
    
    @Override
    public BufferedSink writeByte(final int n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeByte(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeDecimalLong(final long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeDecimalLong(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeHexadecimalUnsignedLong(final long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeHexadecimalUnsignedLong(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeInt(final int n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeInt(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeIntLe(final int n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeIntLe(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeLong(final long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeLong(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeLongLe(final long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeLongLe(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeShort(final int n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeShort(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeShortLe(final int n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeShortLe(n);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeString(final String s, final int n, final int n2, final Charset charset) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeString(s, n, n2, charset);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeString(final String s, final Charset charset) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeString(s, charset);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeUtf8(final String s) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8(s);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeUtf8(final String s, final int n, final int n2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8(s, n, n2);
        return this.emitCompleteSegments();
    }
    
    @Override
    public BufferedSink writeUtf8CodePoint(final int n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8CodePoint(n);
        return this.emitCompleteSegments();
    }
}
