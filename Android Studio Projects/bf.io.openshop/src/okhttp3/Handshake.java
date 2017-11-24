package okhttp3;

import okhttp3.internal.*;
import javax.net.ssl.*;
import java.util.*;
import java.security.*;
import java.security.cert.*;

public final class Handshake
{
    private final CipherSuite cipherSuite;
    private final List<Certificate> localCertificates;
    private final List<Certificate> peerCertificates;
    private final TlsVersion tlsVersion;
    
    private Handshake(final TlsVersion tlsVersion, final CipherSuite cipherSuite, final List<Certificate> peerCertificates, final List<Certificate> localCertificates) {
        this.tlsVersion = tlsVersion;
        this.cipherSuite = cipherSuite;
        this.peerCertificates = peerCertificates;
        this.localCertificates = localCertificates;
    }
    
    public static Handshake get(final SSLSession sslSession) {
        final String cipherSuite = sslSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        final CipherSuite forJavaName = CipherSuite.forJavaName(cipherSuite);
        final String protocol = sslSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        TlsVersion forJavaName2;
        Certificate[] peerCertificates;
        List<Certificate> list;
        Certificate[] localCertificates;
        List<Certificate> list2;
        Label_0077_Outer:Label_0097_Outer:
        while (true) {
            forJavaName2 = TlsVersion.forJavaName(protocol);
            while (true) {
            Label_0128:
                while (true) {
                    while (true) {
                        try {
                            peerCertificates = sslSession.getPeerCertificates();
                            if (peerCertificates != null) {
                                list = Util.immutableList(peerCertificates);
                                localCertificates = sslSession.getLocalCertificates();
                                if (localCertificates != null) {
                                    list2 = Util.immutableList(localCertificates);
                                    return new Handshake(forJavaName2, forJavaName, list, list2);
                                }
                                break Label_0128;
                            }
                        }
                        catch (SSLPeerUnverifiedException ex) {
                            peerCertificates = null;
                            continue Label_0077_Outer;
                        }
                        break;
                    }
                    list = Collections.emptyList();
                    continue Label_0097_Outer;
                }
                list2 = Collections.emptyList();
                continue;
            }
        }
    }
    
    public static Handshake get(final TlsVersion tlsVersion, final CipherSuite cipherSuite, final List<Certificate> list, final List<Certificate> list2) {
        if (cipherSuite == null) {
            throw new IllegalArgumentException("cipherSuite == null");
        }
        return new Handshake(tlsVersion, cipherSuite, Util.immutableList(list), Util.immutableList(list2));
    }
    
    public CipherSuite cipherSuite() {
        return this.cipherSuite;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Handshake) {
            final Handshake handshake = (Handshake)o;
            if (Util.equal(this.cipherSuite, handshake.cipherSuite) && this.cipherSuite.equals(handshake.cipherSuite) && this.peerCertificates.equals(handshake.peerCertificates) && this.localCertificates.equals(handshake.localCertificates)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.tlsVersion != null) {
            hashCode = this.tlsVersion.hashCode();
        }
        else {
            hashCode = 0;
        }
        return 31 * (31 * (31 * (hashCode + 527) + this.cipherSuite.hashCode()) + this.peerCertificates.hashCode()) + this.localCertificates.hashCode();
    }
    
    public List<Certificate> localCertificates() {
        return this.localCertificates;
    }
    
    public Principal localPrincipal() {
        if (!this.localCertificates.isEmpty()) {
            return this.localCertificates.get(0).getSubjectX500Principal();
        }
        return null;
    }
    
    public List<Certificate> peerCertificates() {
        return this.peerCertificates;
    }
    
    public Principal peerPrincipal() {
        if (!this.peerCertificates.isEmpty()) {
            return this.peerCertificates.get(0).getSubjectX500Principal();
        }
        return null;
    }
    
    public TlsVersion tlsVersion() {
        return this.tlsVersion;
    }
}
