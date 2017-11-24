package okhttp3;

import okhttp3.internal.*;
import javax.net.ssl.*;
import java.util.*;

public final class ConnectionSpec
{
    private static final CipherSuite[] APPROVED_CIPHER_SUITES;
    public static final ConnectionSpec CLEARTEXT;
    public static final ConnectionSpec COMPATIBLE_TLS;
    public static final ConnectionSpec MODERN_TLS;
    private final String[] cipherSuites;
    private final boolean supportsTlsExtensions;
    private final boolean tls;
    private final String[] tlsVersions;
    
    static {
        APPROVED_CIPHER_SUITES = new CipherSuite[] { CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA };
        MODERN_TLS = new Builder(true).cipherSuites(ConnectionSpec.APPROVED_CIPHER_SUITES).tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
        COMPATIBLE_TLS = new Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
        CLEARTEXT = new Builder(false).build();
    }
    
    private ConnectionSpec(final Builder builder) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }
    
    private static boolean nonEmptyIntersection(final String[] array, final String[] array2) {
        if (array != null && array2 != null && array.length != 0 && array2.length != 0) {
            for (int length = array.length, i = 0; i < length; ++i) {
                if (Util.contains(array2, array[i])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private ConnectionSpec supportedSpec(final SSLSocket sslSocket, final boolean b) {
        String[] array;
        if (this.cipherSuites != null) {
            array = Util.intersect(String.class, this.cipherSuites, sslSocket.getEnabledCipherSuites());
        }
        else {
            array = sslSocket.getEnabledCipherSuites();
        }
        String[] enabledProtocols;
        if (this.tlsVersions != null) {
            enabledProtocols = Util.intersect(String.class, this.tlsVersions, sslSocket.getEnabledProtocols());
        }
        else {
            enabledProtocols = sslSocket.getEnabledProtocols();
        }
        if (b && Util.contains(sslSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV")) {
            array = Util.concat(array, "TLS_FALLBACK_SCSV");
        }
        return new Builder(this).cipherSuites(array).tlsVersions(enabledProtocols).build();
    }
    
    void apply(final SSLSocket sslSocket, final boolean b) {
        final ConnectionSpec supportedSpec = this.supportedSpec(sslSocket, b);
        if (supportedSpec.tlsVersions != null) {
            sslSocket.setEnabledProtocols(supportedSpec.tlsVersions);
        }
        if (supportedSpec.cipherSuites != null) {
            sslSocket.setEnabledCipherSuites(supportedSpec.cipherSuites);
        }
    }
    
    public List<CipherSuite> cipherSuites() {
        if (this.cipherSuites == null) {
            return null;
        }
        final CipherSuite[] array = new CipherSuite[this.cipherSuites.length];
        for (int i = 0; i < this.cipherSuites.length; ++i) {
            array[i] = CipherSuite.forJavaName(this.cipherSuites[i]);
        }
        return Util.immutableList(array);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof ConnectionSpec) {
            if (o == this) {
                return true;
            }
            final ConnectionSpec connectionSpec = (ConnectionSpec)o;
            if (this.tls == connectionSpec.tls && (!this.tls || (Arrays.equals(this.cipherSuites, connectionSpec.cipherSuites) && Arrays.equals(this.tlsVersions, connectionSpec.tlsVersions) && this.supportsTlsExtensions == connectionSpec.supportsTlsExtensions))) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int n = 17;
        if (this.tls) {
            final int n2 = 31 * (31 * (527 + Arrays.hashCode(this.cipherSuites)) + Arrays.hashCode(this.tlsVersions));
            int n3;
            if (this.supportsTlsExtensions) {
                n3 = 0;
            }
            else {
                n3 = 1;
            }
            n = n2 + n3;
        }
        return n;
    }
    
    public boolean isCompatible(final SSLSocket sslSocket) {
        return this.tls && (this.tlsVersions == null || nonEmptyIntersection(this.tlsVersions, sslSocket.getEnabledProtocols())) && (this.cipherSuites == null || nonEmptyIntersection(this.cipherSuites, sslSocket.getEnabledCipherSuites()));
    }
    
    public boolean isTls() {
        return this.tls;
    }
    
    public boolean supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }
    
    public List<TlsVersion> tlsVersions() {
        if (this.tlsVersions == null) {
            return null;
        }
        final TlsVersion[] array = new TlsVersion[this.tlsVersions.length];
        for (int i = 0; i < this.tlsVersions.length; ++i) {
            array[i] = TlsVersion.forJavaName(this.tlsVersions[i]);
        }
        return Util.immutableList(array);
    }
    
    @Override
    public String toString() {
        if (!this.tls) {
            return "ConnectionSpec()";
        }
        String string;
        if (this.cipherSuites != null) {
            string = this.cipherSuites().toString();
        }
        else {
            string = "[all enabled]";
        }
        String string2;
        if (this.tlsVersions != null) {
            string2 = this.tlsVersions().toString();
        }
        else {
            string2 = "[all enabled]";
        }
        return "ConnectionSpec(cipherSuites=" + string + ", tlsVersions=" + string2 + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ")";
    }
    
    public static final class Builder
    {
        private String[] cipherSuites;
        private boolean supportsTlsExtensions;
        private boolean tls;
        private String[] tlsVersions;
        
        public Builder(final ConnectionSpec connectionSpec) {
            this.tls = connectionSpec.tls;
            this.cipherSuites = connectionSpec.cipherSuites;
            this.tlsVersions = connectionSpec.tlsVersions;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions;
        }
        
        Builder(final boolean tls) {
            this.tls = tls;
        }
        
        public Builder allEnabledCipherSuites() {
            if (!this.tls) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            this.cipherSuites = null;
            return this;
        }
        
        public Builder allEnabledTlsVersions() {
            if (!this.tls) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            this.tlsVersions = null;
            return this;
        }
        
        public ConnectionSpec build() {
            return new ConnectionSpec(this, null);
        }
        
        public Builder cipherSuites(final String... array) {
            if (!this.tls) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            if (array.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            this.cipherSuites = array.clone();
            return this;
        }
        
        public Builder cipherSuites(final CipherSuite... array) {
            if (!this.tls) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            final String[] array2 = new String[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = array[i].javaName;
            }
            return this.cipherSuites(array2);
        }
        
        public Builder supportsTlsExtensions(final boolean supportsTlsExtensions) {
            if (!this.tls) {
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            this.supportsTlsExtensions = supportsTlsExtensions;
            return this;
        }
        
        public Builder tlsVersions(final String... array) {
            if (!this.tls) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (array.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.tlsVersions = array.clone();
            return this;
        }
        
        public Builder tlsVersions(final TlsVersion... array) {
            if (!this.tls) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            final String[] array2 = new String[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = array[i].javaName;
            }
            return this.tlsVersions(array2);
        }
    }
}
