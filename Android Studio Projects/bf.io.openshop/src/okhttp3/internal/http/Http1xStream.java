package okhttp3.internal.http;

import okhttp3.internal.io.*;
import okhttp3.*;
import java.io.*;
import okio.*;
import java.net.*;
import java.util.concurrent.*;
import okhttp3.internal.*;

public final class Http1xStream implements HttpStream
{
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private HttpEngine httpEngine;
    private final BufferedSink sink;
    private final BufferedSource source;
    private int state;
    private final StreamAllocation streamAllocation;
    
    public Http1xStream(final StreamAllocation streamAllocation, final BufferedSource source, final BufferedSink sink) {
        this.state = 0;
        this.streamAllocation = streamAllocation;
        this.source = source;
        this.sink = sink;
    }
    
    private void detachTimeout(final ForwardingTimeout forwardingTimeout) {
        final Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }
    
    private Source getTransferStream(final Response response) throws IOException {
        if (!HttpEngine.hasBody(response)) {
            return this.newFixedLengthSource(0L);
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return this.newChunkedSource(this.httpEngine);
        }
        final long contentLength = OkHeaders.contentLength(response);
        if (contentLength != -1L) {
            return this.newFixedLengthSource(contentLength);
        }
        return this.newUnknownLengthSource();
    }
    
    @Override
    public void cancel() {
        final RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }
    
    @Override
    public Sink createRequestBody(final Request request, final long n) throws IOException {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return this.newChunkedSink();
        }
        if (n != -1L) {
            return this.newFixedLengthSink(n);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }
    
    @Override
    public void finishRequest() throws IOException {
        this.sink.flush();
    }
    
    public boolean isClosed() {
        return this.state == 6;
    }
    
    public Sink newChunkedSink() {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return new ChunkedSink();
    }
    
    public Source newChunkedSource(final HttpEngine httpEngine) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return new ChunkedSource(httpEngine);
    }
    
    public Sink newFixedLengthSink(final long n) {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return new FixedLengthSink(n);
    }
    
    public Source newFixedLengthSource(final long n) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return new FixedLengthSource(n);
    }
    
    public Source newUnknownLengthSource() throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        if (this.streamAllocation == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.state = 5;
        this.streamAllocation.noNewStreams();
        return new UnknownLengthSource();
    }
    
    @Override
    public ResponseBody openResponseBody(final Response response) throws IOException {
        return new RealResponseBody(response.headers(), Okio.buffer(this.getTransferStream(response)));
    }
    
    public Headers readHeaders() throws IOException {
        final Headers.Builder builder = new Headers.Builder();
        while (true) {
            final String utf8LineStrict = this.source.readUtf8LineStrict();
            if (utf8LineStrict.length() == 0) {
                break;
            }
            Internal.instance.addLenient(builder, utf8LineStrict);
        }
        return builder.build();
    }
    
    public Response.Builder readResponse() throws IOException {
        if (this.state != 1 && this.state != 3) {
            throw new IllegalStateException("state: " + this.state);
        }
        Label_0046: {
            break Label_0046;
            try {
                StatusLine parse;
                Response.Builder headers;
                do {
                    parse = StatusLine.parse(this.source.readUtf8LineStrict());
                    headers = new Response.Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(this.readHeaders());
                } while (parse.code == 100);
                this.state = 4;
                return headers;
            }
            catch (EOFException ex2) {
                final IOException ex = new IOException("unexpected end of stream on " + this.streamAllocation);
                ex.initCause(ex2);
                throw ex;
            }
        }
    }
    
    @Override
    public Response.Builder readResponseHeaders() throws IOException {
        return this.readResponse();
    }
    
    @Override
    public void setHttpEngine(final HttpEngine httpEngine) {
        this.httpEngine = httpEngine;
    }
    
    public void writeRequest(final Headers headers, final String s) throws IOException {
        if (this.state != 0) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.sink.writeUtf8(s).writeUtf8("\r\n");
        for (int i = 0; i < headers.size(); ++i) {
            this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");
        }
        this.sink.writeUtf8("\r\n");
        this.state = 1;
    }
    
    @Override
    public void writeRequestBody(final RetryableSink retryableSink) throws IOException {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 3;
        retryableSink.writeToSocket(this.sink);
    }
    
    @Override
    public void writeRequestHeaders(final Request request) throws IOException {
        this.httpEngine.writingRequestHeaders();
        this.writeRequest(request.headers(), RequestLine.get(request, this.httpEngine.getConnection().route().proxy().type()));
    }
    
    private abstract class AbstractSource implements Source
    {
        protected boolean closed;
        protected final ForwardingTimeout timeout;
        
        private AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1xStream.this.source.timeout());
        }
        
        protected final void endOfInput(final boolean b) throws IOException {
            if (Http1xStream.this.state != 6) {
                if (Http1xStream.this.state != 5) {
                    throw new IllegalStateException("state: " + Http1xStream.this.state);
                }
                Http1xStream.this.detachTimeout(this.timeout);
                Http1xStream.this.state = 6;
                if (Http1xStream.this.streamAllocation != null) {
                    Http1xStream.this.streamAllocation.streamFinished(!b, Http1xStream.this);
                }
            }
        }
        
        @Override
        public Timeout timeout() {
            return this.timeout;
        }
    }
    
    private final class ChunkedSink implements Sink
    {
        private boolean closed;
        private final ForwardingTimeout timeout;
        
        private ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1xStream.this.sink.timeout());
        }
        
        @Override
        public void close() throws IOException {
            synchronized (this) {
                if (!this.closed) {
                    this.closed = true;
                    Http1xStream.this.sink.writeUtf8("0\r\n\r\n");
                    Http1xStream.this.detachTimeout(this.timeout);
                    Http1xStream.this.state = 3;
                }
            }
        }
        
        @Override
        public void flush() throws IOException {
            synchronized (this) {
                if (!this.closed) {
                    Http1xStream.this.sink.flush();
                }
            }
        }
        
        @Override
        public Timeout timeout() {
            return this.timeout;
        }
        
        @Override
        public void write(final Buffer buffer, final long n) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (n == 0L) {
                return;
            }
            Http1xStream.this.sink.writeHexadecimalUnsignedLong(n);
            Http1xStream.this.sink.writeUtf8("\r\n");
            Http1xStream.this.sink.write(buffer, n);
            Http1xStream.this.sink.writeUtf8("\r\n");
        }
    }
    
    private class ChunkedSource extends AbstractSource
    {
        private static final long NO_CHUNK_YET = -1L;
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;
        private final HttpEngine httpEngine;
        
        ChunkedSource(final HttpEngine httpEngine) throws IOException {
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
            this.httpEngine = httpEngine;
        }
        
        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1L) {
                Http1xStream.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1xStream.this.source.readHexadecimalUnsignedLong();
                final String trim = Http1xStream.this.source.readUtf8LineStrict().trim();
                if (this.bytesRemainingInChunk < 0L || (!trim.isEmpty() && !trim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + trim + "\"");
                }
            }
            catch (NumberFormatException ex) {
                throw new ProtocolException(ex.getMessage());
            }
            if (this.bytesRemainingInChunk == 0L) {
                this.hasMoreChunks = false;
                this.httpEngine.receiveHeaders(Http1xStream.this.readHeaders());
                ((AbstractSource)this).endOfInput(true);
            }
        }
        
        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                ((AbstractSource)this).endOfInput(false);
            }
            this.closed = true;
        }
        
        @Override
        public long read(final Buffer buffer, final long n) throws IOException {
            if (n < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + n);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (!this.hasMoreChunks) {
                return -1L;
            }
            if (this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) {
                this.readChunkSize();
                if (!this.hasMoreChunks) {
                    return -1L;
                }
            }
            final long read = Http1xStream.this.source.read(buffer, Math.min(n, this.bytesRemainingInChunk));
            if (read == -1L) {
                ((AbstractSource)this).endOfInput(false);
                throw new ProtocolException("unexpected end of stream");
            }
            this.bytesRemainingInChunk -= read;
            return read;
        }
    }
    
    private final class FixedLengthSink implements Sink
    {
        private long bytesRemaining;
        private boolean closed;
        private final ForwardingTimeout timeout;
        
        private FixedLengthSink(final long bytesRemaining) {
            this.timeout = new ForwardingTimeout(Http1xStream.this.sink.timeout());
            this.bytesRemaining = bytesRemaining;
        }
        
        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.bytesRemaining > 0L) {
                throw new ProtocolException("unexpected end of stream");
            }
            Http1xStream.this.detachTimeout(this.timeout);
            Http1xStream.this.state = 3;
        }
        
        @Override
        public void flush() throws IOException {
            if (this.closed) {
                return;
            }
            Http1xStream.this.sink.flush();
        }
        
        @Override
        public Timeout timeout() {
            return this.timeout;
        }
        
        @Override
        public void write(final Buffer buffer, final long n) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(buffer.size(), 0L, n);
            if (n > this.bytesRemaining) {
                throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + n);
            }
            Http1xStream.this.sink.write(buffer, n);
            this.bytesRemaining -= n;
        }
    }
    
    private class FixedLengthSource extends AbstractSource
    {
        private long bytesRemaining;
        
        public FixedLengthSource(final long bytesRemaining) throws IOException {
            this.bytesRemaining = bytesRemaining;
            if (this.bytesRemaining == 0L) {
                ((AbstractSource)this).endOfInput(true);
            }
        }
        
        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.bytesRemaining != 0L && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                ((AbstractSource)this).endOfInput(false);
            }
            this.closed = true;
        }
        
        @Override
        public long read(final Buffer buffer, final long n) throws IOException {
            if (n < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + n);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            long read;
            if (this.bytesRemaining == 0L) {
                read = -1L;
            }
            else {
                read = Http1xStream.this.source.read(buffer, Math.min(this.bytesRemaining, n));
                if (read == -1L) {
                    ((AbstractSource)this).endOfInput(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.bytesRemaining -= read;
                if (this.bytesRemaining == 0L) {
                    ((AbstractSource)this).endOfInput(true);
                    return read;
                }
            }
            return read;
        }
    }
    
    private class UnknownLengthSource extends AbstractSource
    {
        private boolean inputExhausted;
        
        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (!this.inputExhausted) {
                ((AbstractSource)this).endOfInput(false);
            }
            this.closed = true;
        }
        
        @Override
        public long read(final Buffer buffer, final long n) throws IOException {
            if (n < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + n);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            long read;
            if (this.inputExhausted) {
                read = -1L;
            }
            else {
                read = Http1xStream.this.source.read(buffer, n);
                if (read == -1L) {
                    ((AbstractSource)this).endOfInput(this.inputExhausted = true);
                    return -1L;
                }
            }
            return read;
        }
    }
}
