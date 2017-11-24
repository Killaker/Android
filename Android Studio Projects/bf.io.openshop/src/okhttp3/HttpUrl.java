package okhttp3;

import okio.*;
import okhttp3.internal.*;
import java.net.*;
import java.util.*;

public final class HttpUrl
{
    static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    static final String FRAGMENT_ENCODE_SET = "";
    static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    private static final char[] HEX_DIGITS;
    static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    static final String QUERY_COMPONENT_ENCODE_SET = " \"'<>#&=";
    static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    static final String QUERY_ENCODE_SET = " \"'<>#";
    static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    private final String fragment;
    private final String host;
    private final String password;
    private final List<String> pathSegments;
    private final int port;
    private final List<String> queryNamesAndValues;
    private final String scheme;
    private final String url;
    private final String username;
    
    static {
        HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    private HttpUrl(final Builder builder) {
        this.scheme = builder.scheme;
        this.username = percentDecode(builder.encodedUsername, false);
        this.password = percentDecode(builder.encodedPassword, false);
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.pathSegments = this.percentDecode(builder.encodedPathSegments, false);
        List<String> percentDecode;
        if (builder.encodedQueryNamesAndValues != null) {
            percentDecode = this.percentDecode(builder.encodedQueryNamesAndValues, true);
        }
        else {
            percentDecode = null;
        }
        this.queryNamesAndValues = percentDecode;
        final String encodedFragment = builder.encodedFragment;
        String percentDecode2 = null;
        if (encodedFragment != null) {
            percentDecode2 = percentDecode(builder.encodedFragment, false);
        }
        this.fragment = percentDecode2;
        this.url = builder.toString();
    }
    
    static String canonicalize(final String s, final int n, final int n2, final String s2, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        int codePoint;
        for (int i = n; i < n2; i += Character.charCount(codePoint)) {
            codePoint = s.codePointAt(i);
            if (codePoint < 32 || codePoint == 127 || (codePoint >= 128 && b4) || s2.indexOf(codePoint) != -1 || (codePoint == 37 && (!b || (b2 && !percentEncoded(s, i, n2)))) || (codePoint == 43 && b3)) {
                final Buffer buffer = new Buffer();
                buffer.writeUtf8(s, n, i);
                canonicalize(buffer, s, i, n2, s2, b, b2, b3, b4);
                return buffer.readUtf8();
            }
        }
        return s.substring(n, n2);
    }
    
    static String canonicalize(final String s, final String s2, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        return canonicalize(s, 0, s.length(), s2, b, b2, b3, b4);
    }
    
    static void canonicalize(final Buffer buffer, final String s, final int n, final int n2, final String s2, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        Buffer buffer2 = null;
        int codePoint;
        for (int i = n; i < n2; i += Character.charCount(codePoint)) {
            codePoint = s.codePointAt(i);
            if (!b || (codePoint != 9 && codePoint != 10 && codePoint != 12 && codePoint != 13)) {
                if (codePoint == 43 && b3) {
                    String s3;
                    if (b) {
                        s3 = "+";
                    }
                    else {
                        s3 = "%2B";
                    }
                    buffer.writeUtf8(s3);
                }
                else if (codePoint < 32 || codePoint == 127 || (codePoint >= 128 && b4) || s2.indexOf(codePoint) != -1 || (codePoint == 37 && (!b || (b2 && !percentEncoded(s, i, n2))))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(codePoint);
                    while (!buffer2.exhausted()) {
                        final int n3 = 0xFF & buffer2.readByte();
                        buffer.writeByte(37);
                        buffer.writeByte((int)HttpUrl.HEX_DIGITS[0xF & n3 >> 4]);
                        buffer.writeByte((int)HttpUrl.HEX_DIGITS[n3 & 0xF]);
                    }
                }
                else {
                    buffer.writeUtf8CodePoint(codePoint);
                }
            }
        }
    }
    
    static int decodeHexDigit(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return '\n' + (c - 'a');
        }
        if (c >= 'A' && c <= 'F') {
            return '\n' + (c - 'A');
        }
        return -1;
    }
    
    public static int defaultPort(final String s) {
        if (s.equals("http")) {
            return 80;
        }
        if (s.equals("https")) {
            return 443;
        }
        return -1;
    }
    
    public static HttpUrl get(final URI uri) {
        return parse(uri.toString());
    }
    
    public static HttpUrl get(final URL url) {
        return parse(url.toString());
    }
    
    static HttpUrl getChecked(final String s) throws MalformedURLException, UnknownHostException {
        final Builder builder = new Builder();
        final ParseResult parse = builder.parse(null, s);
        switch (parse) {
            default: {
                throw new MalformedURLException("Invalid URL: " + parse + " for " + s);
            }
            case SUCCESS: {
                return builder.build();
            }
            case INVALID_HOST: {
                throw new UnknownHostException("Invalid host: " + s);
            }
        }
    }
    
    static void namesAndValuesToQueryString(final StringBuilder sb, final List<String> list) {
        for (int i = 0; i < list.size(); i += 2) {
            final String s = list.get(i);
            final String s2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(s);
            if (s2 != null) {
                sb.append('=');
                sb.append(s2);
            }
        }
    }
    
    public static HttpUrl parse(final String s) {
        final Builder builder = new Builder();
        final ParseResult parse = builder.parse(null, s);
        final ParseResult success = ParseResult.SUCCESS;
        HttpUrl build = null;
        if (parse == success) {
            build = builder.build();
        }
        return build;
    }
    
    static void pathSegmentsToString(final StringBuilder sb, final List<String> list) {
        for (int i = 0; i < list.size(); ++i) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }
    
    static String percentDecode(final String s, final int n, final int n2, final boolean b) {
        for (int i = n; i < n2; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '%' || (char1 == '+' && b)) {
                final Buffer buffer = new Buffer();
                buffer.writeUtf8(s, n, i);
                percentDecode(buffer, s, i, n2, b);
                return buffer.readUtf8();
            }
        }
        return s.substring(n, n2);
    }
    
    static String percentDecode(final String s, final boolean b) {
        return percentDecode(s, 0, s.length(), b);
    }
    
    private List<String> percentDecode(final List<String> list, final boolean b) {
        final ArrayList<String> list2 = new ArrayList<String>(list.size());
        for (final String s : list) {
            String percentDecode;
            if (s != null) {
                percentDecode = percentDecode(s, b);
            }
            else {
                percentDecode = null;
            }
            list2.add(percentDecode);
        }
        return (List<String>)Collections.unmodifiableList((List<?>)list2);
    }
    
    static void percentDecode(final Buffer buffer, final String s, final int n, final int n2, final boolean b) {
        int i = n;
    Label_0085_Outer:
        while (i < n2) {
            final int codePoint = s.codePointAt(i);
            while (true) {
                Label_0120: {
                    if (codePoint == 37 && i + 2 < n2) {
                        final int decodeHexDigit = decodeHexDigit(s.charAt(i + 1));
                        final int decodeHexDigit2 = decodeHexDigit(s.charAt(i + 2));
                        if (decodeHexDigit == -1 || decodeHexDigit2 == -1) {
                            break Label_0120;
                        }
                        buffer.writeByte(decodeHexDigit2 + (decodeHexDigit << 4));
                        i += 2;
                    }
                    else {
                        if (codePoint != 43 || !b) {
                            break Label_0120;
                        }
                        buffer.writeByte(32);
                    }
                    i += Character.charCount(codePoint);
                    continue Label_0085_Outer;
                }
                buffer.writeUtf8CodePoint(codePoint);
                continue;
            }
        }
    }
    
    static boolean percentEncoded(final String s, final int n, final int n2) {
        return n + 2 < n2 && s.charAt(n) == '%' && decodeHexDigit(s.charAt(n + 1)) != -1 && decodeHexDigit(s.charAt(n + 2)) != -1;
    }
    
    static List<String> queryStringToNamesAndValues(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        int n;
        for (int i = 0; i <= s.length(); i = n + 1) {
            n = s.indexOf(38, i);
            if (n == -1) {
                n = s.length();
            }
            final int index = s.indexOf(61, i);
            if (index == -1 || index > n) {
                list.add(s.substring(i, n));
                list.add(null);
            }
            else {
                list.add(s.substring(i, index));
                list.add(s.substring(index + 1, n));
            }
        }
        return list;
    }
    
    public String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        return this.url.substring(1 + this.url.indexOf(35));
    }
    
    public String encodedPassword() {
        if (this.password.isEmpty()) {
            return "";
        }
        return this.url.substring(1 + this.url.indexOf(58, 3 + this.scheme.length()), this.url.indexOf(64));
    }
    
    public String encodedPath() {
        final int index = this.url.indexOf(47, 3 + this.scheme.length());
        return this.url.substring(index, Util.delimiterOffset(this.url, index, this.url.length(), "?#"));
    }
    
    public List<String> encodedPathSegments() {
        final int index = this.url.indexOf(47, 3 + this.scheme.length());
        final int delimiterOffset = Util.delimiterOffset(this.url, index, this.url.length(), "?#");
        final ArrayList<String> list = new ArrayList<String>();
        int delimiterOffset2;
        for (int i = index; i < delimiterOffset; i = delimiterOffset2) {
            final int n = i + 1;
            delimiterOffset2 = Util.delimiterOffset(this.url, n, delimiterOffset, '/');
            list.add(this.url.substring(n, delimiterOffset2));
        }
        return list;
    }
    
    public String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        final int n = 1 + this.url.indexOf(63);
        return this.url.substring(n, Util.delimiterOffset(this.url, n + 1, this.url.length(), '#'));
    }
    
    public String encodedUsername() {
        if (this.username.isEmpty()) {
            return "";
        }
        final int n = 3 + this.scheme.length();
        return this.url.substring(n, Util.delimiterOffset(this.url, n, this.url.length(), ":@"));
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof HttpUrl && ((HttpUrl)o).url.equals(this.url);
    }
    
    public String fragment() {
        return this.fragment;
    }
    
    @Override
    public int hashCode() {
        return this.url.hashCode();
    }
    
    public String host() {
        return this.host;
    }
    
    public boolean isHttps() {
        return this.scheme.equals("https");
    }
    
    public Builder newBuilder() {
        final Builder builder = new Builder();
        builder.scheme = this.scheme;
        builder.encodedUsername = this.encodedUsername();
        builder.encodedPassword = this.encodedPassword();
        builder.host = this.host;
        int port;
        if (this.port != defaultPort(this.scheme)) {
            port = this.port;
        }
        else {
            port = -1;
        }
        builder.port = port;
        builder.encodedPathSegments.clear();
        builder.encodedPathSegments.addAll(this.encodedPathSegments());
        builder.encodedQuery(this.encodedQuery());
        builder.encodedFragment = this.encodedFragment();
        return builder;
    }
    
    public Builder newBuilder(final String s) {
        final Builder builder = new Builder();
        if (builder.parse(this, s) == ParseResult.SUCCESS) {
            return builder;
        }
        return null;
    }
    
    public String password() {
        return this.password;
    }
    
    public List<String> pathSegments() {
        return this.pathSegments;
    }
    
    public int pathSize() {
        return this.pathSegments.size();
    }
    
    public int port() {
        return this.port;
    }
    
    public String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        namesAndValuesToQueryString(sb, this.queryNamesAndValues);
        return sb.toString();
    }
    
    public String queryParameter(final String s) {
        if (this.queryNamesAndValues != null) {
            for (int i = 0; i < this.queryNamesAndValues.size(); i += 2) {
                if (s.equals(this.queryNamesAndValues.get(i))) {
                    return this.queryNamesAndValues.get(i + 1);
                }
            }
        }
        return null;
    }
    
    public String queryParameterName(final int n) {
        return this.queryNamesAndValues.get(n * 2);
    }
    
    public Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return Collections.emptySet();
        }
        final LinkedHashSet<String> set = new LinkedHashSet<String>();
        for (int i = 0; i < this.queryNamesAndValues.size(); i += 2) {
            set.add(this.queryNamesAndValues.get(i));
        }
        return (Set<String>)Collections.unmodifiableSet((Set<?>)set);
    }
    
    public String queryParameterValue(final int n) {
        return this.queryNamesAndValues.get(1 + n * 2);
    }
    
    public List<String> queryParameterValues(final String s) {
        if (this.queryNamesAndValues == null) {
            return Collections.emptyList();
        }
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < this.queryNamesAndValues.size(); i += 2) {
            if (s.equals(this.queryNamesAndValues.get(i))) {
                list.add(this.queryNamesAndValues.get(i + 1));
            }
        }
        return (List<String>)Collections.unmodifiableList((List<?>)list);
    }
    
    public int querySize() {
        if (this.queryNamesAndValues != null) {
            return this.queryNamesAndValues.size() / 2;
        }
        return 0;
    }
    
    public HttpUrl resolve(final String s) {
        final Builder builder = this.newBuilder(s);
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    public String scheme() {
        return this.scheme;
    }
    
    @Override
    public String toString() {
        return this.url;
    }
    
    public URI uri() {
        final String string = this.newBuilder().reencodeForUri().toString();
        try {
            return new URI(string);
        }
        catch (URISyntaxException ex) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            }
            catch (Exception ex2) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    public URL url() {
        try {
            return new URL(this.url);
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public String username() {
        return this.username;
    }
    
    public static final class Builder
    {
        String encodedFragment;
        String encodedPassword;
        final List<String> encodedPathSegments;
        List<String> encodedQueryNamesAndValues;
        String encodedUsername;
        String host;
        int port;
        String scheme;
        
        public Builder() {
            this.encodedUsername = "";
            this.encodedPassword = "";
            this.port = -1;
            (this.encodedPathSegments = new ArrayList<String>()).add("");
        }
        
        private static String canonicalizeHost(final String s, final int n, final int n2) {
            final String percentDecode = HttpUrl.percentDecode(s, n, n2, false);
            if (!percentDecode.startsWith("[") || !percentDecode.endsWith("]")) {
                return Util.domainToAscii(percentDecode);
            }
            final InetAddress decodeIpv6 = decodeIpv6(percentDecode, 1, -1 + percentDecode.length());
            if (decodeIpv6 == null) {
                return null;
            }
            final byte[] address = decodeIpv6.getAddress();
            if (address.length == 16) {
                return inet6AddressToAscii(address);
            }
            throw new AssertionError();
        }
        
        private static boolean decodeIpv4Suffix(final String s, final int n, final int n2, final byte[] array, final int n3) {
            int i = n;
            int n4 = n3;
            while (i < n2) {
                if (n4 != array.length) {
                    if (n4 != n3) {
                        if (s.charAt(i) != '.') {
                            return false;
                        }
                        ++i;
                    }
                    int n5 = 0;
                    final int n6 = i;
                    while (i < n2) {
                        final char char1 = s.charAt(i);
                        if (char1 < '0' || char1 > '9') {
                            break;
                        }
                        if (n5 == 0 && n6 != i) {
                            return false;
                        }
                        n5 = -48 + (char1 + n5 * 10);
                        if (n5 > 255) {
                            return false;
                        }
                        ++i;
                    }
                    if (i - n6 != 0) {
                        final int n7 = n4 + 1;
                        array[n4] = (byte)n5;
                        n4 = n7;
                        continue;
                    }
                }
                return false;
            }
            if (n4 == n3 + 4) {
                return true;
            }
            return false;
        }
        
        private static InetAddress decodeIpv6(final String s, final int n, final int n2) {
            final byte[] array = new byte[16];
            int n3 = 0;
            int n4 = -1;
            int n5 = -1;
            int i = n;
            while (i < n2) {
                if (n3 == array.length) {
                    return null;
                }
                if (i + 2 <= n2 && s.regionMatches(i, "::", 0, 2)) {
                    if (n4 != -1) {
                        return null;
                    }
                    i += 2;
                    n3 += 2;
                    n4 = n3;
                    if (i == n2) {
                        break;
                    }
                }
                else if (n3 != 0) {
                    if (s.regionMatches(i, ":", 0, 1)) {
                        ++i;
                    }
                    else {
                        if (!s.regionMatches(i, ".", 0, 1)) {
                            return null;
                        }
                        if (!decodeIpv4Suffix(s, n5, n2, array, n3 - 2)) {
                            return null;
                        }
                        n3 += 2;
                        break;
                    }
                }
                int n6 = 0;
                n5 = i;
                while (i < n2) {
                    final int decodeHexDigit = HttpUrl.decodeHexDigit(s.charAt(i));
                    if (decodeHexDigit == -1) {
                        break;
                    }
                    n6 = decodeHexDigit + (n6 << 4);
                    ++i;
                }
                final int n7 = i - n5;
                if (n7 == 0 || n7 > 4) {
                    return null;
                }
                final int n8 = n3 + 1;
                array[n3] = (byte)(0xFF & n6 >>> 8);
                n3 = n8 + 1;
                array[n8] = (byte)(n6 & 0xFF);
            }
            if (n3 != array.length) {
                if (n4 == -1) {
                    return null;
                }
                System.arraycopy(array, n4, array, array.length - (n3 - n4), n3 - n4);
                Arrays.fill(array, n4, n4 + (array.length - n3), (byte)0);
            }
            try {
                return InetAddress.getByAddress(array);
            }
            catch (UnknownHostException ex) {
                throw new AssertionError();
            }
        }
        
        private static String inet6AddressToAscii(final byte[] array) {
            int n = -1;
            int n2 = 0;
            for (int i = 0; i < array.length; i += 2) {
                final int n3 = i;
                while (i < 16 && array[i] == 0 && array[i + 1] == 0) {
                    i += 2;
                }
                final int n4 = i - n3;
                if (n4 > n2) {
                    n = n3;
                    n2 = n4;
                }
            }
            final Buffer buffer = new Buffer();
            int j = 0;
            while (j < array.length) {
                if (j == n) {
                    buffer.writeByte(58);
                    j += n2;
                    if (j != 16) {
                        continue;
                    }
                    buffer.writeByte(58);
                }
                else {
                    if (j > 0) {
                        buffer.writeByte(58);
                    }
                    buffer.writeHexadecimalUnsignedLong((long)((0xFF & array[j]) << 8 | (0xFF & array[j + 1])));
                    j += 2;
                }
            }
            return buffer.readUtf8();
        }
        
        private boolean isDot(final String s) {
            return s.equals(".") || s.equalsIgnoreCase("%2e");
        }
        
        private boolean isDotDot(final String s) {
            return s.equals("..") || s.equalsIgnoreCase("%2e.") || s.equalsIgnoreCase(".%2e") || s.equalsIgnoreCase("%2e%2e");
        }
        
        private static int parsePort(final String s, final int n, final int n2) {
            try {
                final int int1 = Integer.parseInt(HttpUrl.canonicalize(s, n, n2, "", false, false, false, true));
                if (int1 > 0 && int1 <= 65535) {
                    return int1;
                }
                return -1;
            }
            catch (NumberFormatException ex) {
                return -1;
            }
        }
        
        private void pop() {
            if (this.encodedPathSegments.remove(-1 + this.encodedPathSegments.size()).isEmpty() && !this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.set(-1 + this.encodedPathSegments.size(), "");
                return;
            }
            this.encodedPathSegments.add("");
        }
        
        private static int portColonOffset(final String s, final int n, final int n2) {
            for (int i = n; i < n2; ++i) {
                switch (s.charAt(i)) {
                    case '[': {
                        while (++i < n2) {
                            if (s.charAt(i) == ']') {
                                break;
                            }
                        }
                        break;
                    }
                    case ':': {
                        return i;
                    }
                }
            }
            return n2;
        }
        
        private void push(final String s, final int n, final int n2, final boolean b, final boolean b2) {
            final String canonicalize = HttpUrl.canonicalize(s, n, n2, " \"<>^`{}|/\\?#", b2, false, false, true);
            if (!this.isDot(canonicalize)) {
                if (this.isDotDot(canonicalize)) {
                    this.pop();
                    return;
                }
                if (this.encodedPathSegments.get(-1 + this.encodedPathSegments.size()).isEmpty()) {
                    this.encodedPathSegments.set(-1 + this.encodedPathSegments.size(), canonicalize);
                }
                else {
                    this.encodedPathSegments.add(canonicalize);
                }
                if (b) {
                    this.encodedPathSegments.add("");
                }
            }
        }
        
        private void removeAllCanonicalQueryParameters(final String s) {
            for (int i = -2 + this.encodedQueryNamesAndValues.size(); i >= 0; i -= 2) {
                if (s.equals(this.encodedQueryNamesAndValues.get(i))) {
                    this.encodedQueryNamesAndValues.remove(i + 1);
                    this.encodedQueryNamesAndValues.remove(i);
                    if (this.encodedQueryNamesAndValues.isEmpty()) {
                        this.encodedQueryNamesAndValues = null;
                        break;
                    }
                }
            }
        }
        
        private void resolvePath(final String s, int n, final int n2) {
            if (n != n2) {
                final char char1 = s.charAt(n);
                if (char1 == '/' || char1 == '\\') {
                    this.encodedPathSegments.clear();
                    this.encodedPathSegments.add("");
                    ++n;
                }
                else {
                    this.encodedPathSegments.set(-1 + this.encodedPathSegments.size(), "");
                }
                for (int i = n; i < n2; ++i) {
                    final int delimiterOffset = Util.delimiterOffset(s, i, n2, "/\\");
                    final boolean b = delimiterOffset < n2;
                    this.push(s, i, delimiterOffset, b, true);
                    i = delimiterOffset;
                    if (b) {}
                }
            }
        }
        
        private static int schemeDelimiterOffset(final String s, final int n, final int n2) {
            int i;
            if (n2 - n < 2) {
                i = -1;
            }
            else {
                final char char1 = s.charAt(n);
                if ((char1 < 'a' || char1 > 'z') && (char1 < 'A' || char1 > 'Z')) {
                    return -1;
                }
                i = n + 1;
                while (i < n2) {
                    final char char2 = s.charAt(i);
                    if ((char2 >= 'a' && char2 <= 'z') || (char2 >= 'A' && char2 <= 'Z') || (char2 >= '0' && char2 <= '9') || char2 == '+' || char2 == '-' || char2 == '.') {
                        ++i;
                    }
                    else {
                        if (char2 != ':') {
                            return -1;
                        }
                        return i;
                    }
                }
                return -1;
            }
            return i;
        }
        
        private static int slashCount(final String s, int i, final int n) {
            int n2 = 0;
            while (i < n) {
                final char char1 = s.charAt(i);
                if (char1 != '\\' && char1 != '/') {
                    break;
                }
                ++n2;
                ++i;
            }
            return n2;
        }
        
        public Builder addEncodedPathSegment(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("encodedPathSegment == null");
            }
            this.push(s, 0, s.length(), false, true);
            return this;
        }
        
        public Builder addEncodedQueryParameter(final String s, final String s2) {
            if (s == null) {
                throw new IllegalArgumentException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList<String>();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(s, " \"'<>#&=", true, false, true, true));
            final List<String> encodedQueryNamesAndValues = this.encodedQueryNamesAndValues;
            String canonicalize;
            if (s2 != null) {
                canonicalize = HttpUrl.canonicalize(s2, " \"'<>#&=", true, false, true, true);
            }
            else {
                canonicalize = null;
            }
            encodedQueryNamesAndValues.add(canonicalize);
            return this;
        }
        
        public Builder addPathSegment(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("pathSegment == null");
            }
            this.push(s, 0, s.length(), false, false);
            return this;
        }
        
        public Builder addQueryParameter(final String s, final String s2) {
            if (s == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList<String>();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(s, " \"'<>#&=", false, false, true, true));
            final List<String> encodedQueryNamesAndValues = this.encodedQueryNamesAndValues;
            String canonicalize;
            if (s2 != null) {
                canonicalize = HttpUrl.canonicalize(s2, " \"'<>#&=", false, false, true, true);
            }
            else {
                canonicalize = null;
            }
            encodedQueryNamesAndValues.add(canonicalize);
            return this;
        }
        
        public HttpUrl build() {
            if (this.scheme == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.host == null) {
                throw new IllegalStateException("host == null");
            }
            return new HttpUrl(this, null);
        }
        
        int effectivePort() {
            if (this.port != -1) {
                return this.port;
            }
            return HttpUrl.defaultPort(this.scheme);
        }
        
        public Builder encodedFragment(final String s) {
            String canonicalize;
            if (s != null) {
                canonicalize = HttpUrl.canonicalize(s, "", true, false, false, false);
            }
            else {
                canonicalize = null;
            }
            this.encodedFragment = canonicalize;
            return this;
        }
        
        public Builder encodedPassword(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("encodedPassword == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(s, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }
        
        public Builder encodedPath(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("encodedPath == null");
            }
            if (!s.startsWith("/")) {
                throw new IllegalArgumentException("unexpected encodedPath: " + s);
            }
            this.resolvePath(s, 0, s.length());
            return this;
        }
        
        public Builder encodedQuery(final String s) {
            List<String> queryStringToNamesAndValues;
            if (s != null) {
                queryStringToNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(s, " \"'<>#", true, false, true, true));
            }
            else {
                queryStringToNamesAndValues = null;
            }
            this.encodedQueryNamesAndValues = queryStringToNamesAndValues;
            return this;
        }
        
        public Builder encodedUsername(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("encodedUsername == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(s, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }
        
        public Builder fragment(final String s) {
            String canonicalize;
            if (s != null) {
                canonicalize = HttpUrl.canonicalize(s, "", false, false, false, false);
            }
            else {
                canonicalize = null;
            }
            this.encodedFragment = canonicalize;
            return this;
        }
        
        public Builder host(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("host == null");
            }
            final String canonicalizeHost = canonicalizeHost(s, 0, s.length());
            if (canonicalizeHost == null) {
                throw new IllegalArgumentException("unexpected host: " + s);
            }
            this.host = canonicalizeHost;
            return this;
        }
        
        ParseResult parse(final HttpUrl httpUrl, final String s) {
            int skipLeadingAsciiWhitespace = Util.skipLeadingAsciiWhitespace(s, 0, s.length());
            final int skipTrailingAsciiWhitespace = Util.skipTrailingAsciiWhitespace(s, skipLeadingAsciiWhitespace, s.length());
            if (schemeDelimiterOffset(s, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace) != -1) {
                if (s.regionMatches(true, skipLeadingAsciiWhitespace, "https:", 0, 6)) {
                    this.scheme = "https";
                    skipLeadingAsciiWhitespace += "https:".length();
                }
                else {
                    if (!s.regionMatches(true, skipLeadingAsciiWhitespace, "http:", 0, 5)) {
                        return ParseResult.UNSUPPORTED_SCHEME;
                    }
                    this.scheme = "http";
                    skipLeadingAsciiWhitespace += "http:".length();
                }
            }
            else {
                if (httpUrl == null) {
                    return ParseResult.MISSING_SCHEME;
                }
                this.scheme = httpUrl.scheme;
            }
            int n = 0;
            int n2 = 0;
            final int slashCount = slashCount(s, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace);
            if (slashCount >= 2 || httpUrl == null || !httpUrl.scheme.equals(this.scheme)) {
                int n3 = skipLeadingAsciiWhitespace + slashCount;
                int delimiterOffset = 0;
            Label_0199:
                while (true) {
                    delimiterOffset = Util.delimiterOffset(s, n3, skipTrailingAsciiWhitespace, "@/\\?#");
                    int char1;
                    if (delimiterOffset != skipTrailingAsciiWhitespace) {
                        char1 = s.charAt(delimiterOffset);
                    }
                    else {
                        char1 = -1;
                    }
                    switch (char1) {
                        default: {
                            continue;
                        }
                        case -1:
                        case 35:
                        case 47:
                        case 63:
                        case 92: {
                            break Label_0199;
                        }
                        case 64: {
                            if (n2 == 0) {
                                final int delimiterOffset2 = Util.delimiterOffset(s, n3, delimiterOffset, ':');
                                String encodedUsername = HttpUrl.canonicalize(s, n3, delimiterOffset2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                if (n != 0) {
                                    encodedUsername = this.encodedUsername + "%40" + encodedUsername;
                                }
                                this.encodedUsername = encodedUsername;
                                if (delimiterOffset2 != delimiterOffset) {
                                    n2 = 1;
                                    this.encodedPassword = HttpUrl.canonicalize(s, delimiterOffset2 + 1, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                }
                                n = 1;
                            }
                            else {
                                this.encodedPassword = this.encodedPassword + "%40" + HttpUrl.canonicalize(s, n3, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            }
                            n3 = delimiterOffset + 1;
                            continue;
                        }
                    }
                }
                final int portColonOffset = portColonOffset(s, n3, delimiterOffset);
                if (portColonOffset + 1 < delimiterOffset) {
                    this.host = canonicalizeHost(s, n3, portColonOffset);
                    this.port = parsePort(s, portColonOffset + 1, delimiterOffset);
                    if (this.port == -1) {
                        return ParseResult.INVALID_PORT;
                    }
                }
                else {
                    this.host = canonicalizeHost(s, n3, portColonOffset);
                    this.port = HttpUrl.defaultPort(this.scheme);
                }
                if (this.host == null) {
                    return ParseResult.INVALID_HOST;
                }
                skipLeadingAsciiWhitespace = delimiterOffset;
            }
            else {
                this.encodedUsername = httpUrl.encodedUsername();
                this.encodedPassword = httpUrl.encodedPassword();
                this.host = httpUrl.host;
                this.port = httpUrl.port;
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(httpUrl.encodedPathSegments());
                if (skipLeadingAsciiWhitespace == skipTrailingAsciiWhitespace || s.charAt(skipLeadingAsciiWhitespace) == '#') {
                    this.encodedQuery(httpUrl.encodedQuery());
                }
            }
            final int delimiterOffset3 = Util.delimiterOffset(s, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace, "?#");
            this.resolvePath(s, skipLeadingAsciiWhitespace, delimiterOffset3);
            int n4 = delimiterOffset3;
            if (n4 < skipTrailingAsciiWhitespace && s.charAt(n4) == '?') {
                final int delimiterOffset4 = Util.delimiterOffset(s, n4, skipTrailingAsciiWhitespace, '#');
                this.encodedQueryNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(s, n4 + 1, delimiterOffset4, " \"'<>#", true, false, true, true));
                n4 = delimiterOffset4;
            }
            if (n4 < skipTrailingAsciiWhitespace && s.charAt(n4) == '#') {
                this.encodedFragment = HttpUrl.canonicalize(s, n4 + 1, skipTrailingAsciiWhitespace, "", true, false, false, false);
            }
            return ParseResult.SUCCESS;
        }
        
        public Builder password(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("password == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(s, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }
        
        public Builder port(final int port) {
            if (port <= 0 || port > 65535) {
                throw new IllegalArgumentException("unexpected port: " + port);
            }
            this.port = port;
            return this;
        }
        
        public Builder query(final String s) {
            List<String> queryStringToNamesAndValues;
            if (s != null) {
                queryStringToNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(s, " \"'<>#", false, false, true, true));
            }
            else {
                queryStringToNamesAndValues = null;
            }
            this.encodedQueryNamesAndValues = queryStringToNamesAndValues;
            return this;
        }
        
        Builder reencodeForUri() {
            for (int i = 0; i < this.encodedPathSegments.size(); ++i) {
                this.encodedPathSegments.set(i, HttpUrl.canonicalize(this.encodedPathSegments.get(i), "[]", true, true, false, true));
            }
            if (this.encodedQueryNamesAndValues != null) {
                for (int j = 0; j < this.encodedQueryNamesAndValues.size(); ++j) {
                    final String s = this.encodedQueryNamesAndValues.get(j);
                    if (s != null) {
                        this.encodedQueryNamesAndValues.set(j, HttpUrl.canonicalize(s, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            if (this.encodedFragment != null) {
                this.encodedFragment = HttpUrl.canonicalize(this.encodedFragment, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }
        
        public Builder removeAllEncodedQueryParameters(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            this.removeAllCanonicalQueryParameters(HttpUrl.canonicalize(s, " \"'<>#&=", true, false, true, true));
            return this;
        }
        
        public Builder removeAllQueryParameters(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            this.removeAllCanonicalQueryParameters(HttpUrl.canonicalize(s, " \"'<>#&=", false, false, true, true));
            return this;
        }
        
        public Builder removePathSegment(final int n) {
            this.encodedPathSegments.remove(n);
            if (this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            }
            return this;
        }
        
        public Builder scheme(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("scheme == null");
            }
            if (s.equalsIgnoreCase("http")) {
                this.scheme = "http";
                return this;
            }
            if (s.equalsIgnoreCase("https")) {
                this.scheme = "https";
                return this;
            }
            throw new IllegalArgumentException("unexpected scheme: " + s);
        }
        
        public Builder setEncodedPathSegment(final int n, final String s) {
            if (s == null) {
                throw new IllegalArgumentException("encodedPathSegment == null");
            }
            final String canonicalize = HttpUrl.canonicalize(s, 0, s.length(), " \"<>^`{}|/\\?#", true, false, false, true);
            this.encodedPathSegments.set(n, canonicalize);
            if (this.isDot(canonicalize) || this.isDotDot(canonicalize)) {
                throw new IllegalArgumentException("unexpected path segment: " + s);
            }
            return this;
        }
        
        public Builder setEncodedQueryParameter(final String s, final String s2) {
            this.removeAllEncodedQueryParameters(s);
            this.addEncodedQueryParameter(s, s2);
            return this;
        }
        
        public Builder setPathSegment(final int n, final String s) {
            if (s == null) {
                throw new IllegalArgumentException("pathSegment == null");
            }
            final String canonicalize = HttpUrl.canonicalize(s, 0, s.length(), " \"<>^`{}|/\\?#", false, false, false, true);
            if (this.isDot(canonicalize) || this.isDotDot(canonicalize)) {
                throw new IllegalArgumentException("unexpected path segment: " + s);
            }
            this.encodedPathSegments.set(n, canonicalize);
            return this;
        }
        
        public Builder setQueryParameter(final String s, final String s2) {
            this.removeAllQueryParameters(s);
            this.addQueryParameter(s, s2);
            return this;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.scheme);
            sb.append("://");
            if (!this.encodedUsername.isEmpty() || !this.encodedPassword.isEmpty()) {
                sb.append(this.encodedUsername);
                if (!this.encodedPassword.isEmpty()) {
                    sb.append(':');
                    sb.append(this.encodedPassword);
                }
                sb.append('@');
            }
            if (this.host.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.host);
                sb.append(']');
            }
            else {
                sb.append(this.host);
            }
            final int effectivePort = this.effectivePort();
            if (effectivePort != HttpUrl.defaultPort(this.scheme)) {
                sb.append(':');
                sb.append(effectivePort);
            }
            HttpUrl.pathSegmentsToString(sb, this.encodedPathSegments);
            if (this.encodedQueryNamesAndValues != null) {
                sb.append('?');
                HttpUrl.namesAndValuesToQueryString(sb, this.encodedQueryNamesAndValues);
            }
            if (this.encodedFragment != null) {
                sb.append('#');
                sb.append(this.encodedFragment);
            }
            return sb.toString();
        }
        
        public Builder username(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("username == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(s, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }
        
        enum ParseResult
        {
            INVALID_HOST, 
            INVALID_PORT, 
            MISSING_SCHEME, 
            SUCCESS, 
            UNSUPPORTED_SCHEME;
        }
    }
}
