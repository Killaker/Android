package okhttp3;

public static final class Builder
{
    private String[] cipherSuites;
    private boolean supportsTlsExtensions;
    private boolean tls;
    private String[] tlsVersions;
    
    public Builder(final ConnectionSpec connectionSpec) {
        this.tls = ConnectionSpec.access$400(connectionSpec);
        this.cipherSuites = ConnectionSpec.access$500(connectionSpec);
        this.tlsVersions = ConnectionSpec.access$600(connectionSpec);
        this.supportsTlsExtensions = ConnectionSpec.access$700(connectionSpec);
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
