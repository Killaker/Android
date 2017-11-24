package okhttp3.internal;

import okhttp3.*;
import okio.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import android.util.*;
import okhttp3.internal.tls.*;
import java.util.*;
import java.lang.reflect.*;
import java.util.logging.*;

public class Platform
{
    private static final Platform PLATFORM;
    
    static {
        PLATFORM = findPlatform();
    }
    
    static byte[] concatLengthPrefixed(final List<Protocol> list) {
        final Buffer buffer = new Buffer();
        for (int i = 0; i < list.size(); ++i) {
            final Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }
    
    private static Platform findPlatform() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "com.android.org.conscrypt.SSLParametersImpl"
        //     2: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //     5: astore          31
        //     7: aload           31
        //     9: astore          13
        //    11: iconst_1       
        //    12: anewarray       Ljava/lang/Class;
        //    15: astore          14
        //    17: aload           14
        //    19: iconst_0       
        //    20: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    23: aastore        
        //    24: new             Lokhttp3/internal/OptionalMethod;
        //    27: dup            
        //    28: aconst_null    
        //    29: ldc             "setUseSessionTickets"
        //    31: aload           14
        //    33: invokespecial   okhttp3/internal/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //    36: astore          15
        //    38: new             Lokhttp3/internal/OptionalMethod;
        //    41: dup            
        //    42: aconst_null    
        //    43: ldc             "setHostname"
        //    45: iconst_1       
        //    46: anewarray       Ljava/lang/Class;
        //    49: dup            
        //    50: iconst_0       
        //    51: ldc             Ljava/lang/String;.class
        //    53: aastore        
        //    54: invokespecial   okhttp3/internal/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //    57: astore          16
        //    59: aconst_null    
        //    60: astore          17
        //    62: aconst_null    
        //    63: astore          18
        //    65: ldc             "android.net.TrafficStats"
        //    67: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    70: astore          23
        //    72: aload           23
        //    74: ldc             "tagSocket"
        //    76: iconst_1       
        //    77: anewarray       Ljava/lang/Class;
        //    80: dup            
        //    81: iconst_0       
        //    82: ldc             Ljava/net/Socket;.class
        //    84: aastore        
        //    85: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    88: astore          17
        //    90: aload           23
        //    92: ldc             "untagSocket"
        //    94: iconst_1       
        //    95: anewarray       Ljava/lang/Class;
        //    98: dup            
        //    99: iconst_0       
        //   100: ldc             Ljava/net/Socket;.class
        //   102: aastore        
        //   103: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   106: astore          24
        //   108: aload           24
        //   110: astore          18
        //   112: ldc             "android.net.Network"
        //   114: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   117: pop            
        //   118: new             Lokhttp3/internal/OptionalMethod;
        //   121: dup            
        //   122: ldc             [B.class
        //   124: ldc             "getAlpnSelectedProtocol"
        //   126: iconst_0       
        //   127: anewarray       Ljava/lang/Class;
        //   130: invokespecial   okhttp3/internal/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //   133: astore          27
        //   135: new             Lokhttp3/internal/OptionalMethod;
        //   138: dup            
        //   139: aconst_null    
        //   140: ldc             "setAlpnProtocols"
        //   142: iconst_1       
        //   143: anewarray       Ljava/lang/Class;
        //   146: dup            
        //   147: iconst_0       
        //   148: ldc             [B.class
        //   150: aastore        
        //   151: invokespecial   okhttp3/internal/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //   154: astore          28
        //   156: aload           28
        //   158: astore          21
        //   160: aload           27
        //   162: astore          20
        //   164: new             Lokhttp3/internal/Platform$Android;
        //   167: dup            
        //   168: aload           13
        //   170: aload           15
        //   172: aload           16
        //   174: aload           17
        //   176: aload           18
        //   178: aload           20
        //   180: aload           21
        //   182: invokespecial   okhttp3/internal/Platform$Android.<init>:(Ljava/lang/Class;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;)V
        //   185: areturn        
        //   186: astore_0       
        //   187: ldc             "org.apache.harmony.xnet.provider.jsse.SSLParametersImpl"
        //   189: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   192: astore          12
        //   194: aload           12
        //   196: astore          13
        //   198: goto            11
        //   201: astore_1       
        //   202: ldc             "sun.security.ssl.SSLContextImpl"
        //   204: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   207: astore_3       
        //   208: ldc             "org.eclipse.jetty.alpn.ALPN"
        //   210: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   213: astore          7
        //   215: new             Ljava/lang/StringBuilder;
        //   218: dup            
        //   219: invokespecial   java/lang/StringBuilder.<init>:()V
        //   222: ldc             "org.eclipse.jetty.alpn.ALPN"
        //   224: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   227: ldc             "$Provider"
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   235: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   238: astore          8
        //   240: new             Ljava/lang/StringBuilder;
        //   243: dup            
        //   244: invokespecial   java/lang/StringBuilder.<init>:()V
        //   247: ldc             "org.eclipse.jetty.alpn.ALPN"
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: ldc             "$ClientProvider"
        //   254: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   257: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   260: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   263: astore          9
        //   265: new             Ljava/lang/StringBuilder;
        //   268: dup            
        //   269: invokespecial   java/lang/StringBuilder.<init>:()V
        //   272: ldc             "org.eclipse.jetty.alpn.ALPN"
        //   274: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   277: ldc             "$ServerProvider"
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   285: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   288: astore          10
        //   290: new             Lokhttp3/internal/Platform$JdkWithJettyBootPlatform;
        //   293: dup            
        //   294: aload_3        
        //   295: aload           7
        //   297: ldc             "put"
        //   299: iconst_2       
        //   300: anewarray       Ljava/lang/Class;
        //   303: dup            
        //   304: iconst_0       
        //   305: ldc             Ljavax/net/ssl/SSLSocket;.class
        //   307: aastore        
        //   308: dup            
        //   309: iconst_1       
        //   310: aload           8
        //   312: aastore        
        //   313: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   316: aload           7
        //   318: ldc             "get"
        //   320: iconst_1       
        //   321: anewarray       Ljava/lang/Class;
        //   324: dup            
        //   325: iconst_0       
        //   326: ldc             Ljavax/net/ssl/SSLSocket;.class
        //   328: aastore        
        //   329: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   332: aload           7
        //   334: ldc             "remove"
        //   336: iconst_1       
        //   337: anewarray       Ljava/lang/Class;
        //   340: dup            
        //   341: iconst_0       
        //   342: ldc             Ljavax/net/ssl/SSLSocket;.class
        //   344: aastore        
        //   345: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   348: aload           9
        //   350: aload           10
        //   352: invokespecial   okhttp3/internal/Platform$JdkWithJettyBootPlatform.<init>:(Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V
        //   355: astore          11
        //   357: aload           11
        //   359: areturn        
        //   360: astore          6
        //   362: new             Lokhttp3/internal/Platform$JdkPlatform;
        //   365: dup            
        //   366: aload_3        
        //   367: invokespecial   okhttp3/internal/Platform$JdkPlatform.<init>:(Ljava/lang/Class;)V
        //   370: astore          5
        //   372: aload           5
        //   374: areturn        
        //   375: astore_2       
        //   376: new             Lokhttp3/internal/Platform;
        //   379: dup            
        //   380: invokespecial   okhttp3/internal/Platform.<init>:()V
        //   383: areturn        
        //   384: astore          4
        //   386: goto            362
        //   389: astore          22
        //   391: aconst_null    
        //   392: astore          18
        //   394: aconst_null    
        //   395: astore          20
        //   397: aconst_null    
        //   398: astore          21
        //   400: goto            164
        //   403: astore          19
        //   405: aconst_null    
        //   406: astore          20
        //   408: aconst_null    
        //   409: astore          21
        //   411: goto            164
        //   414: astore          30
        //   416: aload           27
        //   418: astore          20
        //   420: aconst_null    
        //   421: astore          21
        //   423: goto            164
        //   426: astore          25
        //   428: aconst_null    
        //   429: astore          20
        //   431: aconst_null    
        //   432: astore          21
        //   434: goto            164
        //   437: astore          29
        //   439: aload           27
        //   441: astore          20
        //   443: aconst_null    
        //   444: astore          21
        //   446: goto            164
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  0      7      186    201    Ljava/lang/ClassNotFoundException;
        //  11     59     201    389    Ljava/lang/ClassNotFoundException;
        //  65     108    389    403    Ljava/lang/ClassNotFoundException;
        //  65     108    403    414    Ljava/lang/NoSuchMethodException;
        //  112    135    426    437    Ljava/lang/ClassNotFoundException;
        //  112    135    403    414    Ljava/lang/NoSuchMethodException;
        //  135    156    437    449    Ljava/lang/ClassNotFoundException;
        //  135    156    414    426    Ljava/lang/NoSuchMethodException;
        //  164    186    201    389    Ljava/lang/ClassNotFoundException;
        //  187    194    201    389    Ljava/lang/ClassNotFoundException;
        //  202    208    375    384    Ljava/lang/ClassNotFoundException;
        //  208    357    360    362    Ljava/lang/ClassNotFoundException;
        //  208    357    384    389    Ljava/lang/NoSuchMethodException;
        //  362    372    375    384    Ljava/lang/ClassNotFoundException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Platform get() {
        return Platform.PLATFORM;
    }
    
    static <T> T readFieldOrNull(final Object o, final Class<T> clazz, final String s) {
        Class<?> clazz2 = o.getClass();
        while (clazz2 != Object.class) {
            try {
                final Field declaredField = clazz2.getDeclaredField(s);
                declaredField.setAccessible(true);
                final Object value = declaredField.get(o);
                final Object fieldOrNull = null;
                if (value == null) {
                    return (T)fieldOrNull;
                }
                if (!clazz.isInstance(value)) {
                    return null;
                }
                return clazz.cast(value);
            }
            catch (IllegalAccessException ex) {
                throw new AssertionError();
            }
            catch (NoSuchFieldException ex2) {
                clazz2 = clazz2.getSuperclass();
                continue;
            }
            break;
        }
        final boolean equals = s.equals("delegate");
        Object fieldOrNull = null;
        if (equals) {
            return (T)fieldOrNull;
        }
        final Object fieldOrNull2 = readFieldOrNull(o, Object.class, "delegate");
        fieldOrNull = null;
        if (fieldOrNull2 != null) {
            fieldOrNull = readFieldOrNull(fieldOrNull2, (Class<Object>)clazz, s);
            return (T)fieldOrNull;
        }
        return (T)fieldOrNull;
    }
    
    public void afterHandshake(final SSLSocket sslSocket) {
    }
    
    public void configureTlsExtensions(final SSLSocket sslSocket, final String s, final List<Protocol> list) {
    }
    
    public void connectSocket(final Socket socket, final InetSocketAddress inetSocketAddress, final int n) throws IOException {
        socket.connect(inetSocketAddress, n);
    }
    
    public String getPrefix() {
        return "OkHttp";
    }
    
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        return null;
    }
    
    public void log(final String s) {
        System.out.println(s);
    }
    
    public void logW(final String s) {
        System.out.println(s);
    }
    
    public void tagSocket(final Socket socket) throws SocketException {
    }
    
    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
        return null;
    }
    
    public TrustRootIndex trustRootIndex(final X509TrustManager x509TrustManager) {
        return new RealTrustRootIndex(x509TrustManager.getAcceptedIssuers());
    }
    
    public void untagSocket(final Socket socket) throws SocketException {
    }
    
    private static class Android extends Platform
    {
        private static final int MAX_LOG_LENGTH = 4000;
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final Class<?> sslParametersClass;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;
        
        public Android(final Class<?> sslParametersClass, final OptionalMethod<Socket> setUseSessionTickets, final OptionalMethod<Socket> setHostname, final Method trafficStatsTagSocket, final Method trafficStatsUntagSocket, final OptionalMethod<Socket> getAlpnSelectedProtocol, final OptionalMethod<Socket> setAlpnProtocols) {
            this.sslParametersClass = sslParametersClass;
            this.setUseSessionTickets = setUseSessionTickets;
            this.setHostname = setHostname;
            this.trafficStatsTagSocket = trafficStatsTagSocket;
            this.trafficStatsUntagSocket = trafficStatsUntagSocket;
            this.getAlpnSelectedProtocol = getAlpnSelectedProtocol;
            this.setAlpnProtocols = setAlpnProtocols;
        }
        
        @Override
        public void configureTlsExtensions(final SSLSocket sslSocket, final String s, final List<Protocol> list) {
            if (s != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, s);
            }
            if (this.setAlpnProtocols != null && this.setAlpnProtocols.isSupported(sslSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, Platform.concatLengthPrefixed(list));
            }
        }
        
        @Override
        public void connectSocket(final Socket socket, final InetSocketAddress inetSocketAddress, final int n) throws IOException {
            try {
                socket.connect(inetSocketAddress, n);
            }
            catch (AssertionError assertionError) {
                if (Util.isAndroidGetsocknameError(assertionError)) {
                    throw new IOException(assertionError);
                }
                throw assertionError;
            }
            catch (SecurityException ex2) {
                final IOException ex = new IOException("Exception in connect");
                ex.initCause(ex2);
                throw ex;
            }
        }
        
        @Override
        public String getSelectedProtocol(final SSLSocket sslSocket) {
            if (this.getAlpnSelectedProtocol != null && this.getAlpnSelectedProtocol.isSupported(sslSocket)) {
                final byte[] array = (byte[])this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sslSocket, new Object[0]);
                String s;
                if (array != null) {
                    s = new String(array, Util.UTF_8);
                }
                else {
                    s = null;
                }
                return s;
            }
            return null;
        }
        
        @Override
        public void log(final String s) {
            for (int i = 0, length = s.length(); i < length; ++i) {
                int index = s.indexOf(10, i);
                if (index == -1) {
                    index = length;
                }
                do {
                    final int min = Math.min(index, i + 4000);
                    Log.d("OkHttp", s.substring(i, min));
                    i = min;
                } while (i < index);
            }
        }
        
        @Override
        public void tagSocket(final Socket socket) throws SocketException {
            if (this.trafficStatsTagSocket == null) {
                return;
            }
            try {
                this.trafficStatsTagSocket.invoke(null, socket);
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            catch (InvocationTargetException ex2) {
                throw new RuntimeException(ex2.getCause());
            }
        }
        
        @Override
        public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
            Object o = Platform.readFieldOrNull(sslSocketFactory, this.sslParametersClass, "sslParameters");
            while (true) {
                if (o == null) {
                    try {
                        o = Platform.readFieldOrNull(sslSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sslSocketFactory.getClass().getClassLoader()), "sslParameters");
                        final X509TrustManager x509TrustManager = Platform.readFieldOrNull(o, X509TrustManager.class, "x509TrustManager");
                        if (x509TrustManager != null) {
                            return x509TrustManager;
                        }
                    }
                    catch (ClassNotFoundException ex) {
                        return null;
                    }
                    return Platform.readFieldOrNull(o, X509TrustManager.class, "trustManager");
                }
                continue;
            }
        }
        
        @Override
        public TrustRootIndex trustRootIndex(final X509TrustManager x509TrustManager) {
            final TrustRootIndex value = AndroidTrustRootIndex.get(x509TrustManager);
            if (value != null) {
                return value;
            }
            return super.trustRootIndex(x509TrustManager);
        }
        
        @Override
        public void untagSocket(final Socket socket) throws SocketException {
            if (this.trafficStatsUntagSocket == null) {
                return;
            }
            try {
                this.trafficStatsUntagSocket.invoke(null, socket);
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            catch (InvocationTargetException ex2) {
                throw new RuntimeException(ex2.getCause());
            }
        }
    }
    
    private static class JdkPlatform extends Platform
    {
        private final Class<?> sslContextClass;
        
        public JdkPlatform(final Class<?> sslContextClass) {
            this.sslContextClass = sslContextClass;
        }
        
        @Override
        public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
            final Object fieldOrNull = Platform.readFieldOrNull(sslSocketFactory, this.sslContextClass, "context");
            if (fieldOrNull == null) {
                return null;
            }
            return Platform.readFieldOrNull(fieldOrNull, X509TrustManager.class, "trustManager");
        }
    }
    
    private static class JdkWithJettyBootPlatform extends JdkPlatform
    {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;
        
        public JdkWithJettyBootPlatform(final Class<?> clazz, final Method putMethod, final Method getMethod, final Method removeMethod, final Class<?> clientProviderClass, final Class<?> serverProviderClass) {
            super(clazz);
            this.putMethod = putMethod;
            this.getMethod = getMethod;
            this.removeMethod = removeMethod;
            this.clientProviderClass = clientProviderClass;
            this.serverProviderClass = serverProviderClass;
        }
        
        @Override
        public void afterHandshake(final SSLSocket sslSocket) {
            try {
                this.removeMethod.invoke(null, sslSocket);
            }
            catch (IllegalAccessException ex) {}
            catch (InvocationTargetException ex2) {
                goto Label_0019;
            }
        }
        
        @Override
        public void configureTlsExtensions(final SSLSocket sslSocket, final String s, final List<Protocol> list) {
            final ArrayList<String> list2 = new ArrayList<String>(list.size());
            for (int i = 0; i < list.size(); ++i) {
                final Protocol protocol = list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    list2.add(protocol.toString());
                }
            }
            try {
                this.putMethod.invoke(null, sslSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[] { this.clientProviderClass, this.serverProviderClass }, new JettyNegoProvider(list2)));
            }
            catch (IllegalAccessException ex) {}
            catch (InvocationTargetException ex2) {
                goto Label_0148;
            }
        }
        
        @Override
        public String getSelectedProtocol(final SSLSocket sslSocket) {
            try {
                final JettyNegoProvider jettyNegoProvider = (JettyNegoProvider)Proxy.getInvocationHandler(this.getMethod.invoke(null, sslSocket));
                if (!jettyNegoProvider.unsupported && jettyNegoProvider.selected == null) {
                    Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                }
                if (!jettyNegoProvider.unsupported) {
                    return jettyNegoProvider.selected;
                }
            }
            catch (IllegalAccessException ex) {}
            catch (InvocationTargetException ex2) {
                goto Label_0072;
            }
            return null;
        }
    }
    
    private static class JettyNegoProvider implements InvocationHandler
    {
        private final List<String> protocols;
        private String selected;
        private boolean unsupported;
        
        public JettyNegoProvider(final List<String> protocols) {
            this.protocols = protocols;
        }
        
        @Override
        public Object invoke(final Object o, final Method method, Object[] empty_STRING_ARRAY) throws Throwable {
            final String name = method.getName();
            final Class<?> returnType = method.getReturnType();
            if (empty_STRING_ARRAY == null) {
                empty_STRING_ARRAY = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            }
            if (name.equals("protocols") && empty_STRING_ARRAY.length == 0) {
                return this.protocols;
            }
            if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && empty_STRING_ARRAY.length == 1 && empty_STRING_ARRAY[0] instanceof List) {
                final List list = (List)empty_STRING_ARRAY[0];
                for (int i = 0; i < list.size(); ++i) {
                    if (this.protocols.contains(list.get(i))) {
                        return this.selected = list.get(i);
                    }
                }
                return this.selected = this.protocols.get(0);
            }
            if ((name.equals("protocolSelected") || name.equals("selected")) && empty_STRING_ARRAY.length == 1) {
                this.selected = (String)empty_STRING_ARRAY[0];
                return null;
            }
            return method.invoke(this, empty_STRING_ARRAY);
        }
    }
}
