package okhttp3.internal.tls;

import javax.security.auth.x500.*;
import java.security.cert.*;
import java.util.*;
import java.security.*;

public final class RealTrustRootIndex implements TrustRootIndex
{
    private final Map<X500Principal, List<X509Certificate>> subjectToCaCerts;
    
    public RealTrustRootIndex(final X509Certificate... array) {
        this.subjectToCaCerts = new LinkedHashMap<X500Principal, List<X509Certificate>>();
        for (final X509Certificate x509Certificate : array) {
            final X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            List<X509Certificate> list = this.subjectToCaCerts.get(subjectX500Principal);
            if (list == null) {
                list = new ArrayList<X509Certificate>(1);
                this.subjectToCaCerts.put(subjectX500Principal, list);
            }
            list.add(x509Certificate);
        }
    }
    
    @Override
    public X509Certificate findByIssuerAndSignature(final X509Certificate x509Certificate) {
        final List<X509Certificate> list = this.subjectToCaCerts.get(x509Certificate.getIssuerX500Principal());
        if (list == null) {
            return null;
        }
        for (final X509Certificate x509Certificate2 : list) {
            final PublicKey publicKey = x509Certificate2.getPublicKey();
            try {
                x509Certificate.verify(publicKey);
                return x509Certificate2;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
        return null;
    }
}
