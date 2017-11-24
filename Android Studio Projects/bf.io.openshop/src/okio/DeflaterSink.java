package okio;

import java.util.zip.*;
import java.io.*;
import org.codehaus.mojo.animal_sniffer.*;

public final class DeflaterSink implements Sink
{
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;
    
    DeflaterSink(final BufferedSink sink, final Deflater deflater) {
        if (sink == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.sink = sink;
        this.deflater = deflater;
    }
    
    public DeflaterSink(final Sink sink, final Deflater deflater) {
        this(Okio.buffer(sink), deflater);
    }
    
    @IgnoreJRERequirement
    private void deflate(final boolean b) throws IOException {
        final Buffer buffer = this.sink.buffer();
        Segment writableSegment;
        while (true) {
            writableSegment = buffer.writableSegment(1);
            int n;
            if (b) {
                n = this.deflater.deflate(writableSegment.data, writableSegment.limit, 2048 - writableSegment.limit, 2);
            }
            else {
                n = this.deflater.deflate(writableSegment.data, writableSegment.limit, 2048 - writableSegment.limit);
            }
            if (n > 0) {
                writableSegment.limit += n;
                buffer.size += n;
                this.sink.emitCompleteSegments();
            }
            else {
                if (this.deflater.needsInput()) {
                    break;
                }
                continue;
            }
        }
        if (writableSegment.pos == writableSegment.limit) {
            buffer.head = writableSegment.pop();
            SegmentPool.recycle(writableSegment);
        }
    }
    
    @Override
    public void close() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        okio/DeflaterSink.closed:Z
        //     4: ifeq            8
        //     7: return         
        //     8: aconst_null    
        //     9: astore_1       
        //    10: aload_0        
        //    11: invokevirtual   okio/DeflaterSink.finishDeflate:()V
        //    14: aload_0        
        //    15: getfield        okio/DeflaterSink.deflater:Ljava/util/zip/Deflater;
        //    18: invokevirtual   java/util/zip/Deflater.end:()V
        //    21: aload_0        
        //    22: getfield        okio/DeflaterSink.sink:Lokio/BufferedSink;
        //    25: invokeinterface okio/BufferedSink.close:()V
        //    30: aload_0        
        //    31: iconst_1       
        //    32: putfield        okio/DeflaterSink.closed:Z
        //    35: aload_1        
        //    36: ifnull          7
        //    39: aload_1        
        //    40: invokestatic    okio/Util.sneakyRethrow:(Ljava/lang/Throwable;)V
        //    43: return         
        //    44: astore_2       
        //    45: aload_2        
        //    46: astore_1       
        //    47: goto            14
        //    50: astore_3       
        //    51: aload_1        
        //    52: ifnonnull       21
        //    55: aload_3        
        //    56: astore_1       
        //    57: goto            21
        //    60: astore          4
        //    62: aload_1        
        //    63: ifnonnull       30
        //    66: aload           4
        //    68: astore_1       
        //    69: goto            30
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     14     44     50     Ljava/lang/Throwable;
        //  14     21     50     60     Ljava/lang/Throwable;
        //  21     30     60     72     Ljava/lang/Throwable;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void finishDeflate() throws IOException {
        this.deflater.finish();
        this.deflate(false);
    }
    
    @Override
    public void flush() throws IOException {
        this.deflate(true);
        this.sink.flush();
    }
    
    @Override
    public Timeout timeout() {
        return this.sink.timeout();
    }
    
    @Override
    public String toString() {
        return "DeflaterSink(" + this.sink + ")";
    }
    
    @Override
    public void write(final Buffer buffer, long n) throws IOException {
        Util.checkOffsetAndCount(buffer.size, 0L, n);
        while (n > 0L) {
            final Segment head = buffer.head;
            final int n2 = (int)Math.min(n, head.limit - head.pos);
            this.deflater.setInput(head.data, head.pos, n2);
            this.deflate(false);
            buffer.size -= n2;
            head.pos += n2;
            if (head.pos == head.limit) {
                buffer.head = head.pop();
                SegmentPool.recycle(head);
            }
            n -= n2;
        }
    }
}
