package okhttp3.internal;

import java.util.*;
import okhttp3.*;
import java.io.*;
import android.util.*;
import java.lang.reflect.*;
import java.net.*;
import javax.net.ssl.*;
import okhttp3.internal.tls.*;

private static class Android extends Platform
{
    private static final int MAX_LOG_LENGTH = 4000;
    private final OptionalMethod<Socket> getAlpnSelectedProtocol;
    private final OptionalMethod<Socket> setAlpnProtocols;
    private final OptionalMethod<Socket> setHostname;
    private final OptionalMethod<Socket> setUseSessionTickets;
    private final Class<?> sslParametersClass;
    private final Method trafficStatsTagSocket;
    private final Method trafficStatsUntagSocket;
    
    public Android(final Class<?> sslParametersClass, final OptionalMethod<Socket> setUseSessionTickets, final OptionalMethod<Socket> setHostname, final Method trafficStatsTagSocket, final Method trafficStatsUntagSocket, final OptionalMethod<Socket> getAlpnSelectedProtocol, final OptionalMethod<Socket> setAlpnProtocols) {
        this.sslParametersClass = sslParametersClass;
        this.setUseSessionTickets = setUseSessionTickets;
        this.setHostname = setHostname;
        this.trafficStatsTagSocket = trafficStatsTagSocket;
        this.trafficStatsUntagSocket = trafficStatsUntagSocket;
        this.getAlpnSelectedProtocol = getAlpnSelectedProtocol;
        this.setAlpnProtocols = setAlpnProtocols;
    }
    
    @Override
    public void configureTlsExtensions(final SSLSocket sslSocket, final String s, final List<Protocol> list) {
        if (s != null) {
            this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, true);
            this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, s);
        }
        if (this.setAlpnProtocols != null && this.setAlpnProtocols.isSupported(sslSocket)) {
            this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, Platform.concatLengthPrefixed(list));
        }
    }
    
    @Override
    public void connectSocket(final Socket socket, final InetSocketAddress inetSocketAddress, final int n) throws IOException {
        try {
            socket.connect(inetSocketAddress, n);
        }
        catch (AssertionError assertionError) {
            if (Util.isAndroidGetsocknameError(assertionError)) {
                throw new IOException(assertionError);
            }
            throw assertionError;
        }
        catch (SecurityException ex2) {
            final IOException ex = new IOException("Exception in connect");
            ex.initCause(ex2);
            throw ex;
        }
    }
    
    @Override
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        if (this.getAlpnSelectedProtocol != null && this.getAlpnSelectedProtocol.isSupported(sslSocket)) {
            final byte[] array = (byte[])this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sslSocket, new Object[0]);
            String s;
            if (array != null) {
                s = new String(array, Util.UTF_8);
            }
            else {
                s = null;
            }
            return s;
        }
        return null;
    }
    
    @Override
    public void log(final String s) {
        for (int i = 0, length = s.length(); i < length; ++i) {
            int index = s.indexOf(10, i);
            if (index == -1) {
                index = length;
            }
            do {
                final int min = Math.min(index, i + 4000);
                Log.d("OkHttp", s.substring(i, min));
                i = min;
            } while (i < index);
        }
    }
    
    @Override
    public void tagSocket(final Socket socket) throws SocketException {
        if (this.trafficStatsTagSocket == null) {
            return;
        }
        try {
            this.trafficStatsTagSocket.invoke(null, socket);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new RuntimeException(ex2.getCause());
        }
    }
    
    @Override
    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
        Object o = Platform.readFieldOrNull(sslSocketFactory, this.sslParametersClass, "sslParameters");
        while (true) {
            if (o == null) {
                try {
                    o = Platform.readFieldOrNull(sslSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sslSocketFactory.getClass().getClassLoader()), "sslParameters");
                    final X509TrustManager x509TrustManager = Platform.readFieldOrNull(o, X509TrustManager.class, "x509TrustManager");
                    if (x509TrustManager != null) {
                        return x509TrustManager;
                    }
                }
                catch (ClassNotFoundException ex) {
                    return null;
                }
                return Platform.readFieldOrNull(o, X509TrustManager.class, "trustManager");
            }
            continue;
        }
    }
    
    @Override
    public TrustRootIndex trustRootIndex(final X509TrustManager x509TrustManager) {
        final TrustRootIndex value = AndroidTrustRootIndex.get(x509TrustManager);
        if (value != null) {
            return value;
        }
        return super.trustRootIndex(x509TrustManager);
    }
    
    @Override
    public void untagSocket(final Socket socket) throws SocketException {
        if (this.trafficStatsUntagSocket == null) {
            return;
        }
        try {
            this.trafficStatsUntagSocket.invoke(null, socket);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        catch (InvocationTargetException ex2) {
            throw new RuntimeException(ex2.getCause());
        }
    }
}
