package okhttp3.internal.tls;

import javax.net.ssl.*;
import java.security.cert.*;
import java.lang.reflect.*;

public final class AndroidTrustRootIndex implements TrustRootIndex
{
    private final Method findByIssuerAndSignatureMethod;
    private final X509TrustManager trustManager;
    
    public AndroidTrustRootIndex(final X509TrustManager trustManager, final Method findByIssuerAndSignatureMethod) {
        this.findByIssuerAndSignatureMethod = findByIssuerAndSignatureMethod;
        this.trustManager = trustManager;
    }
    
    public static TrustRootIndex get(final X509TrustManager x509TrustManager) {
        try {
            final Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new AndroidTrustRootIndex(x509TrustManager, declaredMethod);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    @Override
    public X509Certificate findByIssuerAndSignature(final X509Certificate x509Certificate) {
        try {
            return ((TrustAnchor)this.findByIssuerAndSignatureMethod.invoke(this.trustManager, x509Certificate)).getTrustedCert();
        }
        catch (IllegalAccessException ex) {
            throw new AssertionError();
        }
        catch (InvocationTargetException ex2) {
            return null;
        }
    }
}
