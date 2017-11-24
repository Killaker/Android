package okhttp3;

import java.util.*;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;
import okhttp3.internal.*;

public final class Address
{
    final CertificatePinner certificatePinner;
    final List<ConnectionSpec> connectionSpecs;
    final Dns dns;
    final HostnameVerifier hostnameVerifier;
    final List<Protocol> protocols;
    final Proxy proxy;
    final Authenticator proxyAuthenticator;
    final ProxySelector proxySelector;
    final SocketFactory socketFactory;
    final SSLSocketFactory sslSocketFactory;
    final HttpUrl url;
    
    public Address(final String s, final int n, final Dns dns, final SocketFactory socketFactory, final SSLSocketFactory sslSocketFactory, final HostnameVerifier hostnameVerifier, final CertificatePinner certificatePinner, final Authenticator proxyAuthenticator, final Proxy proxy, final List<Protocol> list, final List<ConnectionSpec> list2, final ProxySelector proxySelector) {
        final HttpUrl.Builder builder = new HttpUrl.Builder();
        String s2;
        if (sslSocketFactory != null) {
            s2 = "https";
        }
        else {
            s2 = "http";
        }
        this.url = builder.scheme(s2).host(s).port(n).build();
        if (dns == null) {
            throw new IllegalArgumentException("dns == null");
        }
        this.dns = dns;
        if (socketFactory == null) {
            throw new IllegalArgumentException("socketFactory == null");
        }
        this.socketFactory = socketFactory;
        if (proxyAuthenticator == null) {
            throw new IllegalArgumentException("proxyAuthenticator == null");
        }
        this.proxyAuthenticator = proxyAuthenticator;
        if (list == null) {
            throw new IllegalArgumentException("protocols == null");
        }
        this.protocols = Util.immutableList(list);
        if (list2 == null) {
            throw new IllegalArgumentException("connectionSpecs == null");
        }
        this.connectionSpecs = Util.immutableList(list2);
        if (proxySelector == null) {
            throw new IllegalArgumentException("proxySelector == null");
        }
        this.proxySelector = proxySelector;
        this.proxy = proxy;
        this.sslSocketFactory = sslSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
        this.certificatePinner = certificatePinner;
    }
    
    public CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }
    
    public List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }
    
    public Dns dns() {
        return this.dns;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof Address;
        boolean b2 = false;
        if (b) {
            final Address address = (Address)o;
            final boolean equals = this.url.equals(address.url);
            b2 = false;
            if (equals) {
                final boolean equals2 = this.dns.equals(address.dns);
                b2 = false;
                if (equals2) {
                    final boolean equals3 = this.proxyAuthenticator.equals(address.proxyAuthenticator);
                    b2 = false;
                    if (equals3) {
                        final boolean equals4 = this.protocols.equals(address.protocols);
                        b2 = false;
                        if (equals4) {
                            final boolean equals5 = this.connectionSpecs.equals(address.connectionSpecs);
                            b2 = false;
                            if (equals5) {
                                final boolean equals6 = this.proxySelector.equals(address.proxySelector);
                                b2 = false;
                                if (equals6) {
                                    final boolean equal = Util.equal(this.proxy, address.proxy);
                                    b2 = false;
                                    if (equal) {
                                        final boolean equal2 = Util.equal(this.sslSocketFactory, address.sslSocketFactory);
                                        b2 = false;
                                        if (equal2) {
                                            final boolean equal3 = Util.equal(this.hostnameVerifier, address.hostnameVerifier);
                                            b2 = false;
                                            if (equal3) {
                                                final boolean equal4 = Util.equal(this.certificatePinner, address.certificatePinner);
                                                b2 = false;
                                                if (equal4) {
                                                    b2 = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (31 * (31 * (31 * (527 + this.url.hashCode()) + this.dns.hashCode()) + this.proxyAuthenticator.hashCode()) + this.protocols.hashCode()) + this.connectionSpecs.hashCode()) + this.proxySelector.hashCode());
        int hashCode;
        if (this.proxy != null) {
            hashCode = this.proxy.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.sslSocketFactory != null) {
            hashCode2 = this.sslSocketFactory.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        int hashCode3;
        if (this.hostnameVerifier != null) {
            hashCode3 = this.hostnameVerifier.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n4 = 31 * (n3 + hashCode3);
        final CertificatePinner certificatePinner = this.certificatePinner;
        int hashCode4 = 0;
        if (certificatePinner != null) {
            hashCode4 = this.certificatePinner.hashCode();
        }
        return n4 + hashCode4;
    }
    
    public HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }
    
    public List<Protocol> protocols() {
        return this.protocols;
    }
    
    public Proxy proxy() {
        return this.proxy;
    }
    
    public Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }
    
    public ProxySelector proxySelector() {
        return this.proxySelector;
    }
    
    public SocketFactory socketFactory() {
        return this.socketFactory;
    }
    
    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }
    
    public HttpUrl url() {
        return this.url;
    }
}
