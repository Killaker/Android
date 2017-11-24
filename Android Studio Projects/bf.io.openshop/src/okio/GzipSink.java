package okio;

import java.util.zip.*;
import java.io.*;

public final class GzipSink implements Sink
{
    private boolean closed;
    private final CRC32 crc;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final BufferedSink sink;
    
    public GzipSink(final Sink sink) {
        this.crc = new CRC32();
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.deflater = new Deflater(-1, true);
        this.sink = Okio.buffer(sink);
        this.deflaterSink = new DeflaterSink(this.sink, this.deflater);
        this.writeHeader();
    }
    
    private void updateCrc(final Buffer buffer, long n) {
        int n2;
        for (Segment segment = buffer.head; n > 0L; n -= n2, segment = segment.next) {
            n2 = (int)Math.min(n, segment.limit - segment.pos);
            this.crc.update(segment.data, segment.pos, n2);
        }
    }
    
    private void writeFooter() throws IOException {
        this.sink.writeIntLe((int)this.crc.getValue());
        this.sink.writeIntLe(this.deflater.getTotalIn());
    }
    
    private void writeHeader() {
        final Buffer buffer = this.sink.buffer();
        buffer.writeShort(8075);
        buffer.writeByte(8);
        buffer.writeByte(0);
        buffer.writeInt(0);
        buffer.writeByte(0);
        buffer.writeByte(0);
    }
    
    @Override
    public void close() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        okio/GzipSink.closed:Z
        //     4: ifeq            8
        //     7: return         
        //     8: aconst_null    
        //     9: astore_1       
        //    10: aload_0        
        //    11: getfield        okio/GzipSink.deflaterSink:Lokio/DeflaterSink;
        //    14: invokevirtual   okio/DeflaterSink.finishDeflate:()V
        //    17: aload_0        
        //    18: invokespecial   okio/GzipSink.writeFooter:()V
        //    21: aload_0        
        //    22: getfield        okio/GzipSink.deflater:Ljava/util/zip/Deflater;
        //    25: invokevirtual   java/util/zip/Deflater.end:()V
        //    28: aload_0        
        //    29: getfield        okio/GzipSink.sink:Lokio/BufferedSink;
        //    32: invokeinterface okio/BufferedSink.close:()V
        //    37: aload_0        
        //    38: iconst_1       
        //    39: putfield        okio/GzipSink.closed:Z
        //    42: aload_1        
        //    43: ifnull          7
        //    46: aload_1        
        //    47: invokestatic    okio/Util.sneakyRethrow:(Ljava/lang/Throwable;)V
        //    50: return         
        //    51: astore_2       
        //    52: aload_2        
        //    53: astore_1       
        //    54: goto            21
        //    57: astore_3       
        //    58: aload_1        
        //    59: ifnonnull       28
        //    62: aload_3        
        //    63: astore_1       
        //    64: goto            28
        //    67: astore          4
        //    69: aload_1        
        //    70: ifnonnull       37
        //    73: aload           4
        //    75: astore_1       
        //    76: goto            37
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     21     51     57     Ljava/lang/Throwable;
        //  21     28     57     67     Ljava/lang/Throwable;
        //  28     37     67     79     Ljava/lang/Throwable;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void flush() throws IOException {
        this.deflaterSink.flush();
    }
    
    @Override
    public Timeout timeout() {
        return this.sink.timeout();
    }
    
    @Override
    public void write(final Buffer buffer, final long n) throws IOException {
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (n == 0L) {
            return;
        }
        this.updateCrc(buffer, n);
        this.deflaterSink.write(buffer, n);
    }
}
