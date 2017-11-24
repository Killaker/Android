package okhttp3.internal;

import okhttp3.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.security.cert.*;
import javax.net.ssl.*;

public final class ConnectionSpecSelector
{
    private final List<ConnectionSpec> connectionSpecs;
    private boolean isFallback;
    private boolean isFallbackPossible;
    private int nextModeIndex;
    
    public ConnectionSpecSelector(final List<ConnectionSpec> connectionSpecs) {
        this.nextModeIndex = 0;
        this.connectionSpecs = connectionSpecs;
    }
    
    private boolean isFallbackPossible(final SSLSocket sslSocket) {
        for (int i = this.nextModeIndex; i < this.connectionSpecs.size(); ++i) {
            if (this.connectionSpecs.get(i).isCompatible(sslSocket)) {
                return true;
            }
        }
        return false;
    }
    
    public ConnectionSpec configureSecureSocket(final SSLSocket sslSocket) throws IOException {
        int nextModeIndex = this.nextModeIndex;
        final int size = this.connectionSpecs.size();
        ConnectionSpec connectionSpec;
        while (true) {
            connectionSpec = null;
            if (nextModeIndex >= size) {
                break;
            }
            final ConnectionSpec connectionSpec2 = this.connectionSpecs.get(nextModeIndex);
            if (connectionSpec2.isCompatible(sslSocket)) {
                connectionSpec = connectionSpec2;
                this.nextModeIndex = nextModeIndex + 1;
                break;
            }
            ++nextModeIndex;
        }
        if (connectionSpec == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.isFallback + ", modes=" + this.connectionSpecs + ", supported protocols=" + Arrays.toString(sslSocket.getEnabledProtocols()));
        }
        this.isFallbackPossible = this.isFallbackPossible(sslSocket);
        Internal.instance.apply(connectionSpec, sslSocket, this.isFallback);
        return connectionSpec;
    }
    
    public boolean connectionFailed(final IOException ex) {
        this.isFallback = true;
        return this.isFallbackPossible && !(ex instanceof ProtocolException) && !(ex instanceof InterruptedIOException) && (!(ex instanceof SSLHandshakeException) || !(ex.getCause() instanceof CertificateException)) && !(ex instanceof SSLPeerUnverifiedException) && (ex instanceof SSLHandshakeException || ex instanceof SSLProtocolException);
    }
}
