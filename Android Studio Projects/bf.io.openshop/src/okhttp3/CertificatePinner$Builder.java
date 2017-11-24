package okhttp3;

import okio.*;
import okhttp3.internal.tls.*;
import java.util.*;

public static final class Builder
{
    private final Map<String, Set<ByteString>> hostnameToPins;
    private TrustRootIndex trustRootIndex;
    
    public Builder() {
        this.hostnameToPins = new LinkedHashMap<String, Set<ByteString>>();
    }
    
    Builder(final CertificatePinner certificatePinner) {
        (this.hostnameToPins = new LinkedHashMap<String, Set<ByteString>>()).putAll(CertificatePinner.access$200(certificatePinner));
        this.trustRootIndex = CertificatePinner.access$300(certificatePinner);
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
