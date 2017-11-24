package okhttp3.internal.framed;

import okhttp3.*;
import java.io.*;
import java.net.*;
import okio.*;

public static class Builder
{
    private boolean client;
    private String hostname;
    private Listener listener;
    private Protocol protocol;
    private PushObserver pushObserver;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    
    public Builder(final boolean client) throws IOException {
        this.listener = Listener.REFUSE_INCOMING_STREAMS;
        this.protocol = Protocol.SPDY_3;
        this.pushObserver = PushObserver.CANCEL;
        this.client = client;
    }
    
    public FramedConnection build() throws IOException {
        return new FramedConnection(this, null);
    }
    
    public Builder listener(final Listener listener) {
        this.listener = listener;
        return this;
    }
    
    public Builder protocol(final Protocol protocol) {
        this.protocol = protocol;
        return this;
    }
    
    public Builder pushObserver(final PushObserver pushObserver) {
        this.pushObserver = pushObserver;
        return this;
    }
    
    public Builder socket(final Socket socket) throws IOException {
        return this.socket(socket, ((InetSocketAddress)socket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket)), Okio.buffer(Okio.sink(socket)));
    }
    
    public Builder socket(final Socket socket, final String hostname, final BufferedSource source, final BufferedSink sink) {
        this.socket = socket;
        this.hostname = hostname;
        this.source = source;
        this.sink = sink;
        return this;
    }
}
