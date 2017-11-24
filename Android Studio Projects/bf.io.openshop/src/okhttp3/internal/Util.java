package okhttp3.internal;

import java.nio.charset.*;
import java.util.regex.*;
import java.net.*;
import okhttp3.*;
import java.util.*;
import java.lang.reflect.*;
import java.security.*;
import okio.*;
import java.io.*;
import java.util.concurrent.*;

public final class Util
{
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final String[] EMPTY_STRING_ARRAY;
    public static final TimeZone UTC;
    public static final Charset UTF_8;
    private static final Pattern VERIFY_AS_IP_ADDRESS;
    
    static {
        EMPTY_BYTE_ARRAY = new byte[0];
        EMPTY_STRING_ARRAY = new String[0];
        UTF_8 = Charset.forName("UTF-8");
        UTC = TimeZone.getTimeZone("GMT");
        VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }
    
    public static void checkOffsetAndCount(final long n, final long n2, final long n3) {
        if ((n2 | n3) < 0L || n2 > n || n - n2 < n3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    public static void closeAll(final Closeable p0, final Closeable p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aload_0        
        //     3: invokeinterface java/io/Closeable.close:()V
        //     8: aload_1        
        //     9: invokeinterface java/io/Closeable.close:()V
        //    14: aload_2        
        //    15: ifnonnull       37
        //    18: return         
        //    19: astore_3       
        //    20: aload_3        
        //    21: astore_2       
        //    22: goto            8
        //    25: astore          4
        //    27: aload_2        
        //    28: ifnonnull       14
        //    31: aload           4
        //    33: astore_2       
        //    34: goto            14
        //    37: aload_2        
        //    38: instanceof      Ljava/io/IOException;
        //    41: ifeq            49
        //    44: aload_2        
        //    45: checkcast       Ljava/io/IOException;
        //    48: athrow         
        //    49: aload_2        
        //    50: instanceof      Ljava/lang/RuntimeException;
        //    53: ifeq            61
        //    56: aload_2        
        //    57: checkcast       Ljava/lang/RuntimeException;
        //    60: athrow         
        //    61: aload_2        
        //    62: instanceof      Ljava/lang/Error;
        //    65: ifeq            73
        //    68: aload_2        
        //    69: checkcast       Ljava/lang/Error;
        //    72: athrow         
        //    73: new             Ljava/lang/AssertionError;
        //    76: dup            
        //    77: aload_2        
        //    78: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //    81: athrow         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      8      19     25     Ljava/lang/Throwable;
        //  8      14     25     37     Ljava/lang/Throwable;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static void closeQuietly(final ServerSocket serverSocket) {
        if (serverSocket == null) {
            return;
        }
        try {
            serverSocket.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static void closeQuietly(final Socket socket) {
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        }
        catch (AssertionError assertionError) {
            if (!isAndroidGetsocknameError(assertionError)) {
                throw assertionError;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static String[] concat(final String[] array, final String s) {
        final String[] array2 = new String[1 + array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        array2[-1 + array2.length] = s;
        return array2;
    }
    
    public static boolean contains(final String[] array, final String s) {
        return Arrays.asList(array).contains(s);
    }
    
    private static boolean containsInvalidHostnameAsciiCodes(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 <= '\u001f' || char1 >= '\u007f' || " #%/:?@[\\]".indexOf(char1) != -1) {
                return true;
            }
        }
        return false;
    }
    
    public static int delimiterOffset(final String s, final int n, final int n2, final char c) {
        for (int i = n; i < n2; ++i) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return n2;
    }
    
    public static int delimiterOffset(final String s, final int n, final int n2, final String s2) {
        for (int i = n; i < n2; ++i) {
            if (s2.indexOf(s.charAt(i)) != -1) {
                return i;
            }
        }
        return n2;
    }
    
    public static boolean discard(final Source source, final int n, final TimeUnit timeUnit) {
        try {
            return skipAll(source, n, timeUnit);
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    public static String domainToAscii(final String s) {
        String lowerCase;
        try {
            lowerCase = IDN.toASCII(s).toLowerCase(Locale.US);
            if (lowerCase.isEmpty()) {
                return null;
            }
            if (containsInvalidHostnameAsciiCodes(lowerCase)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            lowerCase = null;
        }
        return lowerCase;
    }
    
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static String hostHeader(final HttpUrl httpUrl) {
        if (httpUrl.port() != HttpUrl.defaultPort(httpUrl.scheme())) {
            return httpUrl.host() + ":" + httpUrl.port();
        }
        return httpUrl.host();
    }
    
    public static <T> List<T> immutableList(final List<T> list) {
        return Collections.unmodifiableList((List<? extends T>)new ArrayList<T>((Collection<? extends T>)list));
    }
    
    public static <T> List<T> immutableList(final T... array) {
        return Collections.unmodifiableList((List<? extends T>)Arrays.asList((T[])array.clone()));
    }
    
    public static <K, V> Map<K, V> immutableMap(final Map<K, V> map) {
        return Collections.unmodifiableMap((Map<? extends K, ? extends V>)new LinkedHashMap<K, V>((Map<? extends K, ? extends V>)map));
    }
    
    private static <T> List<T> intersect(final T[] array, final T[] array2) {
        final ArrayList<T> list = new ArrayList<T>();
        for (final T t : array) {
            for (final T t2 : array2) {
                if (t.equals(t2)) {
                    list.add(t2);
                    break;
                }
            }
        }
        return list;
    }
    
    public static <T> T[] intersect(final Class<T> clazz, final T[] array, final T[] array2) {
        final List<T> intersect = intersect(array, array2);
        return intersect.toArray((T[])Array.newInstance(clazz, intersect.size()));
    }
    
    public static boolean isAndroidGetsocknameError(final AssertionError assertionError) {
        return assertionError.getCause() != null && assertionError.getMessage() != null && assertionError.getMessage().contains("getsockname failed");
    }
    
    public static String md5Hex(final String s) {
        try {
            return ByteString.of(MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"))).hex();
        }
        catch (NoSuchAlgorithmException ex) {}
        catch (UnsupportedEncodingException ex2) {
            goto Label_0025;
        }
    }
    
    public static ByteString sha1(final ByteString byteString) {
        try {
            return ByteString.of(MessageDigest.getInstance("SHA-1").digest(byteString.toByteArray()));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public static String shaBase64(final String s) {
        try {
            return ByteString.of(MessageDigest.getInstance("SHA-1").digest(s.getBytes("UTF-8"))).base64();
        }
        catch (NoSuchAlgorithmException ex) {}
        catch (UnsupportedEncodingException ex2) {
            goto Label_0025;
        }
    }
    
    public static boolean skipAll(final Source source, final int n, final TimeUnit timeUnit) throws IOException {
        final long nanoTime = System.nanoTime();
        Label_0110: {
            if (!source.timeout().hasDeadline()) {
                break Label_0110;
            }
            long n2 = source.timeout().deadlineNanoTime() - nanoTime;
            while (true) {
                source.timeout().deadlineNanoTime(nanoTime + Math.min(n2, timeUnit.toNanos(n)));
                Label_0118: {
                    try {
                        final Buffer buffer = new Buffer();
                        while (source.read(buffer, 2048L) != -1L) {
                            buffer.clear();
                        }
                        break Label_0118;
                    }
                    catch (InterruptedIOException ex) {
                        if (n2 == Long.MAX_VALUE) {
                            source.timeout().clearDeadline();
                        }
                        else {
                            source.timeout().deadlineNanoTime(nanoTime + n2);
                        }
                        return false;
                        n2 = Long.MAX_VALUE;
                        continue;
                        Label_0139: {
                            source.timeout().deadlineNanoTime(nanoTime + n2);
                        }
                        return true;
                        // iftrue(Label_0139:, n2 != 9223372036854775807L)
                        source.timeout().clearDeadline();
                        return true;
                    }
                    finally {
                        while (true) {
                            if (n2 == Long.MAX_VALUE) {
                                source.timeout().clearDeadline();
                                break Label_0194;
                            }
                            source.timeout().deadlineNanoTime(nanoTime + n2);
                            break Label_0194;
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    public static int skipLeadingAsciiWhitespace(final String s, final int n, final int n2) {
        int i = n;
        while (i < n2) {
            switch (s.charAt(i)) {
                default: {
                    return i;
                }
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ': {
                    ++i;
                    continue;
                }
            }
        }
        return n2;
    }
    
    public static int skipTrailingAsciiWhitespace(final String s, int n, final int n2) {
        int i = n2 - 1;
    Label_0068:
        while (i >= n) {
            switch (s.charAt(i)) {
                default: {
                    n = i + 1;
                    break Label_0068;
                }
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ': {
                    --i;
                    continue;
                }
            }
        }
        return n;
    }
    
    public static ThreadFactory threadFactory(final String s, final boolean b) {
        return new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable runnable) {
                final Thread thread = new Thread(runnable, s);
                thread.setDaemon(b);
                return thread;
            }
        };
    }
    
    public static String toHumanReadableAscii(String utf8) {
        int codePoint;
        for (int i = 0, length = utf8.length(); i < length; i += Character.charCount(codePoint)) {
            codePoint = utf8.codePointAt(i);
            if (codePoint <= 31 || codePoint >= 127) {
                final Buffer buffer = new Buffer();
                buffer.writeUtf8(utf8, 0, i);
                int codePoint2;
                for (int j = i; j < length; j += Character.charCount(codePoint2)) {
                    codePoint2 = utf8.codePointAt(j);
                    int n;
                    if (codePoint2 > 31 && codePoint2 < 127) {
                        n = codePoint2;
                    }
                    else {
                        n = 63;
                    }
                    buffer.writeUtf8CodePoint(n);
                }
                utf8 = buffer.readUtf8();
                break;
            }
        }
        return utf8;
    }
    
    public static String trimSubstring(final String s, final int n, final int n2) {
        final int skipLeadingAsciiWhitespace = skipLeadingAsciiWhitespace(s, n, n2);
        return s.substring(skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace(s, skipLeadingAsciiWhitespace, n2));
    }
    
    public static boolean verifyAsIpAddress(final String s) {
        return Util.VERIFY_AS_IP_ADDRESS.matcher(s).matches();
    }
}
