package android.net.http;

import java.security.cert.*;
import javax.net.ssl.*;

public static class CertificateWrap extends DelegatingSSLSession
{
    public CertificateWrap(final Certificate certificate) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
        throw new RuntimeException("Stub!");
    }
}
