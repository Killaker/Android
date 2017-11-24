package okhttp3;

import okio.*;
import okhttp3.internal.*;
import java.security.cert.*;
import okhttp3.internal.tls.*;
import javax.net.ssl.*;
import java.util.*;

public final class CertificatePinner
{
    public static final CertificatePinner DEFAULT;
    private final Map<String, Set<ByteString>> hostnameToPins;
    private final TrustRootIndex trustRootIndex;
    
    static {
        DEFAULT = new Builder().build();
    }
    
    private CertificatePinner(final Builder builder) {
        this.hostnameToPins = Util.immutableMap(builder.hostnameToPins);
        this.trustRootIndex = builder.trustRootIndex;
    }
    
    public static String pin(final Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha1/" + sha1((X509Certificate)certificate).base64();
    }
    
    private static ByteString sha1(final X509Certificate x509Certificate) {
        return Util.sha1(ByteString.of(x509Certificate.getPublicKey().getEncoded()));
    }
    
    public void check(final String s, List<Certificate> clean) throws SSLPeerUnverifiedException {
        if (this.trustRootIndex != null) {
            clean = new CertificateChainCleaner(this.trustRootIndex).clean(clean);
        }
        final Set<ByteString> matchingPins = this.findMatchingPins(s);
        if (matchingPins != null) {
            for (int i = 0; i < clean.size(); ++i) {
                if (matchingPins.contains(sha1(clean.get(i)))) {
                    return;
                }
            }
            final StringBuilder append = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
            for (int j = 0; j < clean.size(); ++j) {
                final X509Certificate x509Certificate = clean.get(j);
                append.append("\n    ").append(pin(x509Certificate)).append(": ").append(x509Certificate.getSubjectDN().getName());
            }
            append.append("\n  Pinned certificates for ").append(s).append(":");
            final Iterator<ByteString> iterator = matchingPins.iterator();
            while (iterator.hasNext()) {
                append.append("\n    sha1/").append(iterator.next().base64());
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }
    
    public void check(final String s, final Certificate... array) throws SSLPeerUnverifiedException {
        this.check(s, Arrays.asList(array));
    }
    
    Set<ByteString> findMatchingPins(final String s) {
        final Set<ByteString> set = this.hostnameToPins.get(s);
        final int index = s.indexOf(46);
        final int lastIndex = s.lastIndexOf(46);
        Collection<ByteString> collection = null;
        if (index != lastIndex) {
            collection = this.hostnameToPins.get("*." + s.substring(index + 1));
        }
        if (set == null && collection == null) {
            return null;
        }
        if (set != null && collection != null) {
            final LinkedHashSet set2 = new LinkedHashSet<Object>();
            set2.addAll(set);
            set2.addAll(collection);
            return (Set<ByteString>)set2;
        }
        if (set != null) {
            return set;
        }
        return (Set<ByteString>)collection;
    }
    
    Builder newBuilder() {
        return new Builder(this);
    }
    
    public static final class Builder
    {
        private final Map<String, Set<ByteString>> hostnameToPins;
        private TrustRootIndex trustRootIndex;
        
        public Builder() {
            this.hostnameToPins = new LinkedHashMap<String, Set<ByteString>>();
        }
        
        Builder(final CertificatePinner certificatePinner) {
            (this.hostnameToPins = new LinkedHashMap<String, Set<ByteString>>()).putAll(certificatePinner.hostnameToPins);
            this.trustRootIndex = certificatePinner.trustRootIndex;
        }
        
        public Builder add(final String s, final String... array) {
            if (s == null) {
                throw new IllegalArgumentException("hostname == null");
            }
            final LinkedHashSet<ByteString> set = new LinkedHashSet<ByteString>();
            final Set<ByteString> set2 = this.hostnameToPins.put(s, Collections.unmodifiableSet((Set<? extends ByteString>)set));
            if (set2 != null) {
                set.addAll((Collection<?>)set2);
            }
            for (final String s2 : array) {
                if (!s2.startsWith("sha1/")) {
                    throw new IllegalArgumentException("pins must start with 'sha1/': " + s2);
                }
                final ByteString decodeBase64 = ByteString.decodeBase64(s2.substring("sha1/".length()));
                if (decodeBase64 == null) {
                    throw new IllegalArgumentException("pins must be base64: " + s2);
                }
                set.add(decodeBase64);
            }
            return this;
        }
        
        public CertificatePinner build() {
            return new CertificatePinner(this, null);
        }
        
        public Builder trustRootIndex(final TrustRootIndex trustRootIndex) {
            this.trustRootIndex = trustRootIndex;
            return this;
        }
    }
}
