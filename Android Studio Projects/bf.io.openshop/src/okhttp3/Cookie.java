package okhttp3;

import okhttp3.internal.*;
import java.util.regex.*;
import java.util.*;
import okhttp3.internal.http.*;

public final class Cookie
{
    private static final Pattern DAY_OF_MONTH_PATTERN;
    private static final Pattern MONTH_PATTERN;
    private static final Pattern TIME_PATTERN;
    private static final Pattern YEAR_PATTERN;
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;
    
    static {
        YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
        MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
        DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
        TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    }
    
    private Cookie(final String name, final String value, final long expiresAt, final String domain, final String path, final boolean secure, final boolean httpOnly, final boolean hostOnly, final boolean persistent) {
        this.name = name;
        this.value = value;
        this.expiresAt = expiresAt;
        this.domain = domain;
        this.path = path;
        this.secure = secure;
        this.httpOnly = httpOnly;
        this.hostOnly = hostOnly;
        this.persistent = persistent;
    }
    
    private Cookie(final Builder builder) {
        if (builder.name == null) {
            throw new IllegalArgumentException("builder.name == null");
        }
        if (builder.value == null) {
            throw new IllegalArgumentException("builder.value == null");
        }
        if (builder.domain == null) {
            throw new IllegalArgumentException("builder.domain == null");
        }
        this.name = builder.name;
        this.value = builder.value;
        this.expiresAt = builder.expiresAt;
        this.domain = builder.domain;
        this.path = builder.path;
        this.secure = builder.secure;
        this.httpOnly = builder.httpOnly;
        this.persistent = builder.persistent;
        this.hostOnly = builder.hostOnly;
    }
    
    private static int dateCharacterOffset(final String s, final int n, final int n2, final boolean b) {
        for (int i = n; i < n2; ++i) {
            final char char1 = s.charAt(i);
            int n3;
            if ((char1 < ' ' && char1 != '\t') || char1 >= '\u007f' || (char1 >= '0' && char1 <= '9') || (char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || char1 == ':') {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            int n4;
            if (!b) {
                n4 = 1;
            }
            else {
                n4 = 0;
            }
            if (n3 == n4) {
                return i;
            }
        }
        return n2;
    }
    
    private static boolean domainMatch(final HttpUrl httpUrl, final String s) {
        final String host = httpUrl.host();
        return host.equals(s) || (host.endsWith(s) && host.charAt(-1 + (host.length() - s.length())) == '.' && !Util.verifyAsIpAddress(host));
    }
    
    static Cookie parse(final long p0, final HttpUrl p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: invokevirtual   java/lang/String.length:()I
        //     4: istore          4
        //     6: aload_3        
        //     7: iconst_0       
        //     8: iload           4
        //    10: bipush          59
        //    12: invokestatic    okhttp3/internal/Util.delimiterOffset:(Ljava/lang/String;IIC)I
        //    15: istore          5
        //    17: aload_3        
        //    18: iconst_0       
        //    19: iload           5
        //    21: bipush          61
        //    23: invokestatic    okhttp3/internal/Util.delimiterOffset:(Ljava/lang/String;IIC)I
        //    26: istore          6
        //    28: iload           6
        //    30: iload           5
        //    32: if_icmpne       37
        //    35: aconst_null    
        //    36: areturn        
        //    37: aload_3        
        //    38: iconst_0       
        //    39: iload           6
        //    41: invokestatic    okhttp3/internal/Util.trimSubstring:(Ljava/lang/String;II)Ljava/lang/String;
        //    44: astore          7
        //    46: aload           7
        //    48: invokevirtual   java/lang/String.isEmpty:()Z
        //    51: ifeq            56
        //    54: aconst_null    
        //    55: areturn        
        //    56: aload_3        
        //    57: iload           6
        //    59: iconst_1       
        //    60: iadd           
        //    61: iload           5
        //    63: invokestatic    okhttp3/internal/Util.trimSubstring:(Ljava/lang/String;II)Ljava/lang/String;
        //    66: astore          8
        //    68: ldc2_w          253402300799999
        //    71: lstore          9
        //    73: ldc2_w          -1
        //    76: lstore          11
        //    78: aconst_null    
        //    79: astore          13
        //    81: aconst_null    
        //    82: astore          14
        //    84: iconst_0       
        //    85: istore          15
        //    87: iconst_0       
        //    88: istore          16
        //    90: iconst_1       
        //    91: istore          17
        //    93: iconst_0       
        //    94: istore          18
        //    96: iload           5
        //    98: iconst_1       
        //    99: iadd           
        //   100: istore          19
        //   102: iload           19
        //   104: iload           4
        //   106: if_icmpge       315
        //   109: aload_3        
        //   110: iload           19
        //   112: iload           4
        //   114: bipush          59
        //   116: invokestatic    okhttp3/internal/Util.delimiterOffset:(Ljava/lang/String;IIC)I
        //   119: istore          24
        //   121: aload_3        
        //   122: iload           19
        //   124: iload           24
        //   126: bipush          61
        //   128: invokestatic    okhttp3/internal/Util.delimiterOffset:(Ljava/lang/String;IIC)I
        //   131: istore          25
        //   133: aload_3        
        //   134: iload           19
        //   136: iload           25
        //   138: invokestatic    okhttp3/internal/Util.trimSubstring:(Ljava/lang/String;II)Ljava/lang/String;
        //   141: astore          26
        //   143: iload           25
        //   145: iload           24
        //   147: if_icmpge       205
        //   150: aload_3        
        //   151: iload           25
        //   153: iconst_1       
        //   154: iadd           
        //   155: iload           24
        //   157: invokestatic    okhttp3/internal/Util.trimSubstring:(Ljava/lang/String;II)Ljava/lang/String;
        //   160: astore          27
        //   162: aload           26
        //   164: ldc             "expires"
        //   166: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   169: ifeq            212
        //   172: aload           27
        //   174: invokevirtual   java/lang/String.length:()I
        //   177: istore          34
        //   179: aload           27
        //   181: iconst_0       
        //   182: iload           34
        //   184: invokestatic    okhttp3/Cookie.parseExpires:(Ljava/lang/String;II)J
        //   187: lstore          35
        //   189: lload           35
        //   191: lstore          9
        //   193: iconst_1       
        //   194: istore          18
        //   196: iload           24
        //   198: iconst_1       
        //   199: iadd           
        //   200: istore          19
        //   202: goto            102
        //   205: ldc             ""
        //   207: astore          27
        //   209: goto            162
        //   212: aload           26
        //   214: ldc             "max-age"
        //   216: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   219: ifeq            239
        //   222: aload           27
        //   224: invokestatic    okhttp3/Cookie.parseMaxAge:(Ljava/lang/String;)J
        //   227: lstore          31
        //   229: lload           31
        //   231: lstore          11
        //   233: iconst_1       
        //   234: istore          18
        //   236: goto            196
        //   239: aload           26
        //   241: ldc             "domain"
        //   243: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   246: ifeq            266
        //   249: aload           27
        //   251: invokestatic    okhttp3/Cookie.parseDomain:(Ljava/lang/String;)Ljava/lang/String;
        //   254: astore          29
        //   256: aload           29
        //   258: astore          13
        //   260: iconst_0       
        //   261: istore          17
        //   263: goto            196
        //   266: aload           26
        //   268: ldc             "path"
        //   270: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   273: ifeq            283
        //   276: aload           27
        //   278: astore          14
        //   280: goto            196
        //   283: aload           26
        //   285: ldc             "secure"
        //   287: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   290: ifeq            299
        //   293: iconst_1       
        //   294: istore          15
        //   296: goto            196
        //   299: aload           26
        //   301: ldc             "httponly"
        //   303: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   306: ifeq            196
        //   309: iconst_1       
        //   310: istore          16
        //   312: goto            196
        //   315: lload           11
        //   317: ldc2_w          -9223372036854775808
        //   320: lcmp           
        //   321: ifne            411
        //   324: ldc2_w          -9223372036854775808
        //   327: lstore          9
        //   329: aload           13
        //   331: ifnonnull       475
        //   334: aload_2        
        //   335: invokevirtual   okhttp3/HttpUrl.host:()Ljava/lang/String;
        //   338: astore          13
        //   340: aload           14
        //   342: ifnull          355
        //   345: aload           14
        //   347: ldc             "/"
        //   349: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   352: ifne            385
        //   355: aload_2        
        //   356: invokevirtual   okhttp3/HttpUrl.encodedPath:()Ljava/lang/String;
        //   359: astore          22
        //   361: aload           22
        //   363: bipush          47
        //   365: invokevirtual   java/lang/String.lastIndexOf:(I)I
        //   368: istore          23
        //   370: iload           23
        //   372: ifeq            486
        //   375: aload           22
        //   377: iconst_0       
        //   378: iload           23
        //   380: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   383: astore          14
        //   385: new             Lokhttp3/Cookie;
        //   388: dup            
        //   389: aload           7
        //   391: aload           8
        //   393: lload           9
        //   395: aload           13
        //   397: aload           14
        //   399: iload           15
        //   401: iload           16
        //   403: iload           17
        //   405: iload           18
        //   407: invokespecial   okhttp3/Cookie.<init>:(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZ)V
        //   410: areturn        
        //   411: lload           11
        //   413: ldc2_w          -1
        //   416: lcmp           
        //   417: ifeq            329
        //   420: lload           11
        //   422: ldc2_w          9223372036854775
        //   425: lcmp           
        //   426: ifgt            467
        //   429: lload           11
        //   431: ldc2_w          1000
        //   434: lmul           
        //   435: lstore          20
        //   437: lload_0        
        //   438: lload           20
        //   440: ladd           
        //   441: lstore          9
        //   443: lload           9
        //   445: lload_0        
        //   446: lcmp           
        //   447: iflt            459
        //   450: lload           9
        //   452: ldc2_w          253402300799999
        //   455: lcmp           
        //   456: ifle            329
        //   459: ldc2_w          253402300799999
        //   462: lstore          9
        //   464: goto            329
        //   467: ldc2_w          9223372036854775807
        //   470: lstore          20
        //   472: goto            437
        //   475: aload_2        
        //   476: aload           13
        //   478: invokestatic    okhttp3/Cookie.domainMatch:(Lokhttp3/HttpUrl;Ljava/lang/String;)Z
        //   481: ifne            340
        //   484: aconst_null    
        //   485: areturn        
        //   486: ldc             "/"
        //   488: astore          14
        //   490: goto            385
        //   493: astore          30
        //   495: goto            196
        //   498: astore          28
        //   500: goto            196
        //   503: astore          33
        //   505: goto            196
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  172    189    503    508    Ljava/lang/IllegalArgumentException;
        //  222    229    493    498    Ljava/lang/NumberFormatException;
        //  249    256    498    503    Ljava/lang/IllegalArgumentException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Cookie parse(final HttpUrl httpUrl, final String s) {
        return parse(System.currentTimeMillis(), httpUrl, s);
    }
    
    public static List<Cookie> parseAll(final HttpUrl httpUrl, final Headers headers) {
        final List<String> values = headers.values("Set-Cookie");
        List<? extends Cookie> list = null;
        for (int i = 0; i < values.size(); ++i) {
            final Cookie parse = parse(httpUrl, values.get(i));
            if (parse != null) {
                if (list == null) {
                    list = new ArrayList<Cookie>();
                }
                list.add(parse);
            }
        }
        if (list != null) {
            return (List<Cookie>)Collections.unmodifiableList((List<?>)list);
        }
        return Collections.emptyList();
    }
    
    private static String parseDomain(String substring) {
        if (substring.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (substring.startsWith(".")) {
            substring = substring.substring(1);
        }
        final String domainToAscii = Util.domainToAscii(substring);
        if (domainToAscii == null) {
            throw new IllegalArgumentException();
        }
        return domainToAscii;
    }
    
    private static long parseExpires(final String s, final int n, final int n2) {
        int i = dateCharacterOffset(s, n, n2, false);
        int int1 = -1;
        int int2 = -1;
        int int3 = -1;
        int int4 = -1;
        int n3 = -1;
        int int5 = -1;
        final Matcher matcher = Cookie.TIME_PATTERN.matcher(s);
        while (i < n2) {
            final int dateCharacterOffset = dateCharacterOffset(s, i + 1, n2, true);
            matcher.region(i, dateCharacterOffset);
            if (int1 == -1 && matcher.usePattern(Cookie.TIME_PATTERN).matches()) {
                int1 = Integer.parseInt(matcher.group(1));
                int2 = Integer.parseInt(matcher.group(2));
                int3 = Integer.parseInt(matcher.group(3));
            }
            else if (int4 == -1 && matcher.usePattern(Cookie.DAY_OF_MONTH_PATTERN).matches()) {
                int4 = Integer.parseInt(matcher.group(1));
            }
            else if (n3 == -1 && matcher.usePattern(Cookie.MONTH_PATTERN).matches()) {
                n3 = Cookie.MONTH_PATTERN.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            }
            else if (int5 == -1 && matcher.usePattern(Cookie.YEAR_PATTERN).matches()) {
                int5 = Integer.parseInt(matcher.group(1));
            }
            i = dateCharacterOffset(s, dateCharacterOffset + 1, n2, false);
        }
        if (int5 >= 70 && int5 <= 99) {
            int5 += 1900;
        }
        if (int5 >= 0 && int5 <= 69) {
            int5 += 2000;
        }
        if (int5 < 1601) {
            throw new IllegalArgumentException();
        }
        if (n3 == -1) {
            throw new IllegalArgumentException();
        }
        if (int4 < 1 || int4 > 31) {
            throw new IllegalArgumentException();
        }
        if (int1 < 0 || int1 > 23) {
            throw new IllegalArgumentException();
        }
        if (int2 < 0 || int2 > 59) {
            throw new IllegalArgumentException();
        }
        if (int3 < 0 || int3 > 59) {
            throw new IllegalArgumentException();
        }
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(1, int5);
        gregorianCalendar.set(2, n3 - 1);
        gregorianCalendar.set(5, int4);
        gregorianCalendar.set(11, int1);
        gregorianCalendar.set(12, int2);
        gregorianCalendar.set(13, int3);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }
    
    private static long parseMaxAge(final String s) {
        long n = Long.MIN_VALUE;
        try {
            long long1 = Long.parseLong(s);
            if (long1 <= 0L) {
                long1 = n;
            }
            return long1;
        }
        catch (NumberFormatException ex) {
            if (s.matches("-?\\d+")) {
                if (!s.startsWith("-")) {
                    n = Long.MAX_VALUE;
                }
                return n;
            }
            throw ex;
        }
    }
    
    private static boolean pathMatch(final HttpUrl httpUrl, final String s) {
        final String encodedPath = httpUrl.encodedPath();
        return encodedPath.equals(s) || (encodedPath.startsWith(s) && (s.endsWith("/") || encodedPath.charAt(s.length()) == '/'));
    }
    
    public String domain() {
        return this.domain;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Cookie) {
            final Cookie cookie = (Cookie)o;
            if (cookie.name.equals(this.name) && cookie.value.equals(this.value) && cookie.domain.equals(this.domain) && cookie.path.equals(this.path) && cookie.expiresAt == this.expiresAt && cookie.secure == this.secure && cookie.httpOnly == this.httpOnly && cookie.persistent == this.persistent && cookie.hostOnly == this.hostOnly) {
                return true;
            }
        }
        return false;
    }
    
    public long expiresAt() {
        return this.expiresAt;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (31 * (31 * (527 + this.name.hashCode()) + this.value.hashCode()) + this.domain.hashCode()) + this.path.hashCode()) + (int)(this.expiresAt ^ this.expiresAt >>> 32));
        int n2;
        if (this.secure) {
            n2 = 0;
        }
        else {
            n2 = 1;
        }
        final int n3 = 31 * (n + n2);
        int n4;
        if (this.httpOnly) {
            n4 = 0;
        }
        else {
            n4 = 1;
        }
        final int n5 = 31 * (n3 + n4);
        int n6;
        if (this.persistent) {
            n6 = 0;
        }
        else {
            n6 = 1;
        }
        final int n7 = 31 * (n5 + n6);
        final boolean hostOnly = this.hostOnly;
        int n8 = 0;
        if (!hostOnly) {
            n8 = 1;
        }
        return n7 + n8;
    }
    
    public boolean hostOnly() {
        return this.hostOnly;
    }
    
    public boolean httpOnly() {
        return this.httpOnly;
    }
    
    public boolean matches(final HttpUrl httpUrl) {
        boolean b;
        if (this.hostOnly) {
            b = httpUrl.host().equals(this.domain);
        }
        else {
            b = domainMatch(httpUrl, this.domain);
        }
        return b && pathMatch(httpUrl, this.path) && (!this.secure || httpUrl.isHttps());
    }
    
    public String name() {
        return this.name;
    }
    
    public String path() {
        return this.path;
    }
    
    public boolean persistent() {
        return this.persistent;
    }
    
    public boolean secure() {
        return this.secure;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('=');
        sb.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            }
            else {
                sb.append("; expires=").append(HttpDate.format(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            sb.append("; domain=").append(this.domain);
        }
        sb.append("; path=").append(this.path);
        if (this.secure) {
            sb.append("; secure");
        }
        if (this.httpOnly) {
            sb.append("; httponly");
        }
        return sb.toString();
    }
    
    public String value() {
        return this.value;
    }
    
    public static final class Builder
    {
        String domain;
        long expiresAt;
        boolean hostOnly;
        boolean httpOnly;
        String name;
        String path;
        boolean persistent;
        boolean secure;
        String value;
        
        public Builder() {
            this.expiresAt = 253402300799999L;
            this.path = "/";
        }
        
        private Builder domain(final String s, final boolean hostOnly) {
            if (s == null) {
                throw new IllegalArgumentException("domain == null");
            }
            final String domainToAscii = Util.domainToAscii(s);
            if (domainToAscii == null) {
                throw new IllegalArgumentException("unexpected domain: " + s);
            }
            this.domain = domainToAscii;
            this.hostOnly = hostOnly;
            return this;
        }
        
        public Cookie build() {
            return new Cookie(this, null);
        }
        
        public Builder domain(final String s) {
            return this.domain(s, false);
        }
        
        public Builder expiresAt(long expiresAt) {
            if (expiresAt <= 0L) {
                expiresAt = Long.MIN_VALUE;
            }
            if (expiresAt > 253402300799999L) {
                expiresAt = 253402300799999L;
            }
            this.expiresAt = expiresAt;
            this.persistent = true;
            return this;
        }
        
        public Builder hostOnlyDomain(final String s) {
            return this.domain(s, true);
        }
        
        public Builder httpOnly() {
            this.httpOnly = true;
            return this;
        }
        
        public Builder name(final String name) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (!name.trim().equals(name)) {
                throw new IllegalArgumentException("name is not trimmed");
            }
            this.name = name;
            return this;
        }
        
        public Builder path(final String path) {
            if (!path.startsWith("/")) {
                throw new IllegalArgumentException("path must start with '/'");
            }
            this.path = path;
            return this;
        }
        
        public Builder secure() {
            this.secure = true;
            return this;
        }
        
        public Builder value(final String value) {
            if (value == null) {
                throw new NullPointerException("value == null");
            }
            if (!value.trim().equals(value)) {
                throw new IllegalArgumentException("value is not trimmed");
            }
            this.value = value;
            return this;
        }
    }
}
