package okhttp3.internal.tls;

import java.security.cert.*;
import java.util.*;
import okhttp3.internal.*;
import javax.net.ssl.*;

public final class OkHostnameVerifier implements HostnameVerifier
{
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE;
    
    static {
        INSTANCE = new OkHostnameVerifier();
    }
    
    public static List<String> allSubjectAltNames(final X509Certificate x509Certificate) {
        final List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 7);
        final List<String> subjectAltNames2 = getSubjectAltNames(x509Certificate, 2);
        final ArrayList list = new ArrayList<Object>(subjectAltNames.size() + subjectAltNames2.size());
        list.addAll((Collection<?>)subjectAltNames);
        list.addAll((Collection<?>)subjectAltNames2);
        return (List<String>)list;
    }
    
    private static List<String> getSubjectAltNames(final X509Certificate x509Certificate, final int n) {
        List<String> emptyList = new ArrayList<String>();
        try {
            final Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (final List<Integer> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2) {
                    final Integer n2 = list.get(0);
                    if (n2 == null || n2 != n) {
                        continue;
                    }
                    final String s = (String)list.get(1);
                    if (s == null) {
                        continue;
                    }
                    emptyList.add(s);
                }
            }
        }
        catch (CertificateParsingException ex) {
            emptyList = Collections.emptyList();
        }
        return emptyList;
    }
    
    private boolean verifyHostname(String string, String string2) {
        if (string != null && string.length() != 0 && !string.startsWith(".") && !string.endsWith("..") && string2 != null && string2.length() != 0 && !string2.startsWith(".") && !string2.endsWith("..")) {
            if (!string.endsWith(".")) {
                string += '.';
            }
            if (!string2.endsWith(".")) {
                string2 += '.';
            }
            final String lowerCase = string2.toLowerCase(Locale.US);
            if (!lowerCase.contains("*")) {
                return string.equals(lowerCase);
            }
            if (lowerCase.startsWith("*.") && lowerCase.indexOf(42, 1) == -1 && string.length() >= lowerCase.length() && !"*.".equals(lowerCase)) {
                final String substring = lowerCase.substring(1);
                if (string.endsWith(substring)) {
                    final int n = string.length() - substring.length();
                    if (n <= 0 || string.lastIndexOf(46, n - 1) == -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean verifyHostname(final String s, final X509Certificate x509Certificate) {
        final String lowerCase = s.toLowerCase(Locale.US);
        boolean b = false;
        final List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 2);
        for (int i = 0; i < subjectAltNames.size(); ++i) {
            b = true;
            if (this.verifyHostname(lowerCase, subjectAltNames.get(i))) {
                return true;
            }
        }
        if (!b) {
            final String mostSpecific = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn");
            if (mostSpecific != null) {
                return this.verifyHostname(lowerCase, mostSpecific);
            }
        }
        return false;
    }
    
    private boolean verifyIpAddress(final String s, final X509Certificate x509Certificate) {
        final List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 7);
        for (int i = 0; i < subjectAltNames.size(); ++i) {
            if (s.equalsIgnoreCase(subjectAltNames.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public boolean verify(final String s, final X509Certificate x509Certificate) {
        if (Util.verifyAsIpAddress(s)) {
            return this.verifyIpAddress(s, x509Certificate);
        }
        return this.verifyHostname(s, x509Certificate);
    }
    
    @Override
    public boolean verify(final String s, final SSLSession sslSession) {
        try {
            return this.verify(s, (X509Certificate)sslSession.getPeerCertificates()[0]);
        }
        catch (SSLException ex) {
            return false;
        }
    }
}
