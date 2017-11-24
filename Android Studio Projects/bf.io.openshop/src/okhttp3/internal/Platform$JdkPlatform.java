package okhttp3.internal;

import javax.net.ssl.*;

private static class JdkPlatform extends Platform
{
    private final Class<?> sslContextClass;
    
    public JdkPlatform(final Class<?> sslContextClass) {
        this.sslContextClass = sslContextClass;
    }
    
    @Override
    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
        final Object fieldOrNull = Platform.readFieldOrNull(sslSocketFactory, this.sslContextClass, "context");
        if (fieldOrNull == null) {
            return null;
        }
        return Platform.readFieldOrNull(fieldOrNull, X509TrustManager.class, "trustManager");
    }
}
