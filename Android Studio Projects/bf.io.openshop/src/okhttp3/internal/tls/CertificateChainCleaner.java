package okhttp3.internal.tls;

import java.security.*;
import java.security.cert.*;
import javax.net.ssl.*;
import java.util.*;

public final class CertificateChainCleaner
{
    private final TrustRootIndex trustRootIndex;
    
    public CertificateChainCleaner(final TrustRootIndex trustRootIndex) {
        this.trustRootIndex = trustRootIndex;
    }
    
    private boolean verifySignature(final X509Certificate x509Certificate, final X509Certificate x509Certificate2) {
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        }
        catch (GeneralSecurityException ex) {
            return false;
        }
    }
    
    public List<Certificate> clean(final List<Certificate> list) throws SSLPeerUnverifiedException {
        final ArrayDeque<X509Certificate> arrayDeque = (ArrayDeque<X509Certificate>)new ArrayDeque<Certificate>(list);
        final ArrayList<X509Certificate> list2 = (ArrayList<X509Certificate>)new ArrayList<Certificate>();
        list2.add(arrayDeque.removeFirst());
    Label_0030:
        while (true) {
            final X509Certificate x509Certificate = list2.get(-1 + list2.size());
            final X509Certificate byIssuerAndSignature = this.trustRootIndex.findByIssuerAndSignature(x509Certificate);
            if (byIssuerAndSignature != null) {
                if (list2.size() > 1 || !x509Certificate.equals(byIssuerAndSignature)) {
                    list2.add(byIssuerAndSignature);
                }
                return (List<Certificate>)list2;
            }
            final Iterator<X509Certificate> iterator = (Iterator<X509Certificate>)arrayDeque.iterator();
            while (iterator.hasNext()) {
                final X509Certificate x509Certificate2 = iterator.next();
                if (x509Certificate.getIssuerDN().equals(x509Certificate2.getSubjectDN()) && this.verifySignature(x509Certificate, x509Certificate2)) {
                    iterator.remove();
                    list2.add(x509Certificate2);
                    continue Label_0030;
                }
            }
            throw new SSLPeerUnverifiedException("Failed to find a cert that signed " + x509Certificate);
        }
    }
}
