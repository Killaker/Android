package com.google.android.gms.analytics.internal;

import android.os.*;
import com.google.android.gms.common.internal.*;
import java.util.zip.*;
import java.util.*;
import java.net.*;
import android.net.*;
import java.io.*;

class zzah extends zzd
{
    private static final byte[] zzTd;
    private final zzaj zzTc;
    private final String zzzN;
    
    static {
        zzTd = "\n".getBytes();
    }
    
    zzah(final zzf zzf) {
        super(zzf);
        this.zzzN = zza("GoogleAnalytics", zze.VERSION, Build$VERSION.RELEASE, zzam.zza(Locale.getDefault()), Build.MODEL, Build.ID);
        this.zzTc = new zzaj(zzf.zzjl());
    }
    
    private int zza(final URL p0, final byte[] p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_1        
        //     3: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //     6: pop            
        //     7: aload_2        
        //     8: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //    11: pop            
        //    12: aload_0        
        //    13: ldc             "POST bytes, url"
        //    15: aload_2        
        //    16: arraylength    
        //    17: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    20: aload_1        
        //    21: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    24: aload_0        
        //    25: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzhp:()Z
        //    28: ifeq            45
        //    31: aload_0        
        //    32: ldc             "Post payload\n"
        //    34: new             Ljava/lang/String;
        //    37: dup            
        //    38: aload_2        
        //    39: invokespecial   java/lang/String.<init>:([B)V
        //    42: invokevirtual   com/google/android/gms/analytics/internal/zzah.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //    45: aload_0        
        //    46: aload_1        
        //    47: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzc:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //    50: astore          12
        //    52: aload           12
        //    54: astore          7
        //    56: aload           7
        //    58: iconst_1       
        //    59: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //    62: aload           7
        //    64: aload_2        
        //    65: arraylength    
        //    66: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //    69: aload           7
        //    71: invokevirtual   java/net/HttpURLConnection.connect:()V
        //    74: aload           7
        //    76: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //    79: astore_3       
        //    80: aload_3        
        //    81: aload_2        
        //    82: invokevirtual   java/io/OutputStream.write:([B)V
        //    85: aload_0        
        //    86: aload           7
        //    88: invokespecial   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/net/HttpURLConnection;)V
        //    91: aload           7
        //    93: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    96: istore          10
        //    98: iload           10
        //   100: sipush          200
        //   103: if_icmpne       113
        //   106: aload_0        
        //   107: invokevirtual   com/google/android/gms/analytics/internal/zzah.zziH:()Lcom/google/android/gms/analytics/internal/zzb;
        //   110: invokevirtual   com/google/android/gms/analytics/internal/zzb.zzjh:()V
        //   113: aload_0        
        //   114: ldc             "POST status"
        //   116: iload           10
        //   118: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   121: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/lang/String;Ljava/lang/Object;)V
        //   124: aload_3        
        //   125: ifnull          132
        //   128: aload_3        
        //   129: invokevirtual   java/io/OutputStream.close:()V
        //   132: aload           7
        //   134: ifnull          142
        //   137: aload           7
        //   139: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   142: iload           10
        //   144: ireturn        
        //   145: astore          13
        //   147: aload_0        
        //   148: ldc             "Error closing http post connection output stream"
        //   150: aload           13
        //   152: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   155: goto            132
        //   158: astore          9
        //   160: aconst_null    
        //   161: astore          7
        //   163: aload_0        
        //   164: ldc             "Network POST connection error"
        //   166: aload           9
        //   168: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzd:(Ljava/lang/String;Ljava/lang/Object;)V
        //   171: aload_3        
        //   172: ifnull          179
        //   175: aload_3        
        //   176: invokevirtual   java/io/OutputStream.close:()V
        //   179: iconst_0       
        //   180: istore          10
        //   182: aload           7
        //   184: ifnull          142
        //   187: aload           7
        //   189: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   192: iconst_0       
        //   193: ireturn        
        //   194: astore          11
        //   196: aload_0        
        //   197: ldc             "Error closing http post connection output stream"
        //   199: aload           11
        //   201: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   204: goto            179
        //   207: astore          6
        //   209: aconst_null    
        //   210: astore          7
        //   212: aload_3        
        //   213: ifnull          220
        //   216: aload_3        
        //   217: invokevirtual   java/io/OutputStream.close:()V
        //   220: aload           7
        //   222: ifnull          230
        //   225: aload           7
        //   227: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   230: aload           6
        //   232: athrow         
        //   233: astore          8
        //   235: aload_0        
        //   236: ldc             "Error closing http post connection output stream"
        //   238: aload           8
        //   240: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   243: goto            220
        //   246: astore          6
        //   248: goto            212
        //   251: astore          9
        //   253: goto            163
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  45     52     158    163    Ljava/io/IOException;
        //  45     52     207    212    Any
        //  56     98     251    256    Ljava/io/IOException;
        //  56     98     246    251    Any
        //  106    113    251    256    Ljava/io/IOException;
        //  106    113    246    251    Any
        //  113    124    251    256    Ljava/io/IOException;
        //  113    124    246    251    Any
        //  128    132    145    158    Ljava/io/IOException;
        //  163    171    246    251    Any
        //  175    179    194    207    Ljava/io/IOException;
        //  216    220    233    246    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static String zza(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", s, s2, s3, s4, s5, s6);
    }
    
    private void zza(final StringBuilder sb, final String s, final String s2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(s, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(s2, "UTF-8"));
    }
    
    private int zzb(final URL url) {
        zzx.zzz(url);
        this.zzb("GET request", url);
        HttpURLConnection zzc = null;
        try {
            zzc = this.zzc(url);
            zzc.connect();
            this.zzb(zzc);
            final int responseCode = zzc.getResponseCode();
            if (responseCode == 200) {
                this.zziH().zzjh();
            }
            this.zzb("GET status", responseCode);
            return responseCode;
        }
        catch (IOException ex) {
            this.zzd("Network GET connection error", ex);
            final int responseCode = 0;
            return 0;
        }
        finally {
            if (zzc != null) {
                zzc.disconnect();
            }
        }
    }
    
    private int zzb(final URL p0, final byte[] p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_1        
        //     3: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //     6: pop            
        //     7: aload_2        
        //     8: invokestatic    com/google/android/gms/common/internal/zzx.zzz:(Ljava/lang/Object;)Ljava/lang/Object;
        //    11: pop            
        //    12: aload_2        
        //    13: invokestatic    com/google/android/gms/analytics/internal/zzah.zzg:([B)[B
        //    16: astore          13
        //    18: aload_0        
        //    19: ldc             "POST compressed size, ratio %, url"
        //    21: aload           13
        //    23: arraylength    
        //    24: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    27: ldc2_w          100
        //    30: aload           13
        //    32: arraylength    
        //    33: i2l            
        //    34: lmul           
        //    35: aload_2        
        //    36: arraylength    
        //    37: i2l            
        //    38: ldiv           
        //    39: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    42: aload_1        
        //    43: invokevirtual   com/google/android/gms/analytics/internal/zzah.zza:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
        //    46: aload           13
        //    48: arraylength    
        //    49: aload_2        
        //    50: arraylength    
        //    51: if_icmple       71
        //    54: aload_0        
        //    55: ldc             "Compressed payload is larger then uncompressed. compressed, uncompressed"
        //    57: aload           13
        //    59: arraylength    
        //    60: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    63: aload_2        
        //    64: arraylength    
        //    65: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    68: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzc:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    71: aload_0        
        //    72: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzhp:()Z
        //    75: ifeq            110
        //    78: aload_0        
        //    79: ldc             "Post payload"
        //    81: new             Ljava/lang/StringBuilder;
        //    84: dup            
        //    85: invokespecial   java/lang/StringBuilder.<init>:()V
        //    88: ldc             "\n"
        //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    93: new             Ljava/lang/String;
        //    96: dup            
        //    97: aload_2        
        //    98: invokespecial   java/lang/String.<init>:([B)V
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   107: invokevirtual   com/google/android/gms/analytics/internal/zzah.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //   110: aload_0        
        //   111: aload_1        
        //   112: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzc:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //   115: astore          14
        //   117: aload           14
        //   119: astore          7
        //   121: aload           7
        //   123: iconst_1       
        //   124: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   127: aload           7
        //   129: ldc             "Content-Encoding"
        //   131: ldc             "gzip"
        //   133: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   136: aload           7
        //   138: aload           13
        //   140: arraylength    
        //   141: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   144: aload           7
        //   146: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   149: aload           7
        //   151: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   154: astore          15
        //   156: aload           15
        //   158: aload           13
        //   160: invokevirtual   java/io/OutputStream.write:([B)V
        //   163: aload           15
        //   165: invokevirtual   java/io/OutputStream.close:()V
        //   168: aload_0        
        //   169: aload           7
        //   171: invokespecial   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/net/HttpURLConnection;)V
        //   174: aload           7
        //   176: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   179: istore          11
        //   181: iload           11
        //   183: sipush          200
        //   186: if_icmpne       196
        //   189: aload_0        
        //   190: invokevirtual   com/google/android/gms/analytics/internal/zzah.zziH:()Lcom/google/android/gms/analytics/internal/zzb;
        //   193: invokevirtual   com/google/android/gms/analytics/internal/zzb.zzjh:()V
        //   196: aload_0        
        //   197: ldc             "POST status"
        //   199: iload           11
        //   201: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   204: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzb:(Ljava/lang/String;Ljava/lang/Object;)V
        //   207: iconst_0       
        //   208: ifeq            215
        //   211: aconst_null    
        //   212: invokevirtual   java/io/OutputStream.close:()V
        //   215: aload           7
        //   217: ifnull          225
        //   220: aload           7
        //   222: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   225: iload           11
        //   227: ireturn        
        //   228: astore          16
        //   230: aload_0        
        //   231: ldc             "Error closing http compressed post connection output stream"
        //   233: aload           16
        //   235: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   238: goto            215
        //   241: astore          9
        //   243: aconst_null    
        //   244: astore          10
        //   246: aload_0        
        //   247: ldc             "Network compressed POST connection error"
        //   249: aload           9
        //   251: invokevirtual   com/google/android/gms/analytics/internal/zzah.zzd:(Ljava/lang/String;Ljava/lang/Object;)V
        //   254: aload_3        
        //   255: ifnull          262
        //   258: aload_3        
        //   259: invokevirtual   java/io/OutputStream.close:()V
        //   262: iconst_0       
        //   263: istore          11
        //   265: aload           10
        //   267: ifnull          225
        //   270: aload           10
        //   272: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   275: iconst_0       
        //   276: ireturn        
        //   277: astore          12
        //   279: aload_0        
        //   280: ldc             "Error closing http compressed post connection output stream"
        //   282: aload           12
        //   284: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   287: goto            262
        //   290: astore          6
        //   292: aconst_null    
        //   293: astore          7
        //   295: aload_3        
        //   296: ifnull          303
        //   299: aload_3        
        //   300: invokevirtual   java/io/OutputStream.close:()V
        //   303: aload           7
        //   305: ifnull          313
        //   308: aload           7
        //   310: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   313: aload           6
        //   315: athrow         
        //   316: astore          8
        //   318: aload_0        
        //   319: ldc             "Error closing http compressed post connection output stream"
        //   321: aload           8
        //   323: invokevirtual   com/google/android/gms/analytics/internal/zzah.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   326: goto            303
        //   329: astore          6
        //   331: aconst_null    
        //   332: astore_3       
        //   333: goto            295
        //   336: astore          6
        //   338: aload           15
        //   340: astore_3       
        //   341: goto            295
        //   344: astore          6
        //   346: aload           10
        //   348: astore          7
        //   350: goto            295
        //   353: astore          9
        //   355: aload           7
        //   357: astore          10
        //   359: aconst_null    
        //   360: astore_3       
        //   361: goto            246
        //   364: astore          9
        //   366: aload           15
        //   368: astore_3       
        //   369: aload           7
        //   371: astore          10
        //   373: goto            246
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     71     241    246    Ljava/io/IOException;
        //  12     71     290    295    Any
        //  71     110    241    246    Ljava/io/IOException;
        //  71     110    290    295    Any
        //  110    117    241    246    Ljava/io/IOException;
        //  110    117    290    295    Any
        //  121    156    353    364    Ljava/io/IOException;
        //  121    156    329    336    Any
        //  156    168    364    376    Ljava/io/IOException;
        //  156    168    336    344    Any
        //  168    181    353    364    Ljava/io/IOException;
        //  168    181    329    336    Any
        //  189    196    353    364    Ljava/io/IOException;
        //  189    196    329    336    Any
        //  196    207    353    364    Ljava/io/IOException;
        //  196    207    329    336    Any
        //  211    215    228    241    Ljava/io/IOException;
        //  246    254    344    353    Any
        //  258    262    277    290    Ljava/io/IOException;
        //  299    303    316    329    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private URL zzb(final zzab zzab, final String s) {
        Label_0061: {
            if (!zzab.zzlt()) {
                break Label_0061;
            }
            String s2 = this.zzjn().zzkF() + this.zzjn().zzkH() + "?" + s;
            try {
                return new URL(s2);
                s2 = this.zzjn().zzkG() + this.zzjn().zzkH() + "?" + s;
                return new URL(s2);
            }
            catch (MalformedURLException ex) {
                this.zze("Error trying to parse the hardcoded host url", ex);
                return null;
            }
        }
    }
    
    private void zzb(final HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            while (inputStream.read(new byte[1024]) > 0) {}
            if (inputStream == null) {
                return;
            }
            try {
                inputStream.close();
            }
            catch (IOException ex) {
                this.zze("Error closing http connection input stream", ex);
            }
        }
        finally {
            Label_0057: {
                if (inputStream == null) {
                    break Label_0057;
                }
                try {
                    inputStream.close();
                }
                catch (IOException ex2) {
                    this.zze("Error closing http connection input stream", ex2);
                }
            }
        }
    }
    
    private boolean zzg(final zzab zzab) {
        zzx.zzz(zzab);
        final String zza = this.zza(zzab, !zzab.zzlt());
        if (zza == null) {
            this.zzjm().zza(zzab, "Error formatting hit for upload");
        }
        else if (zza.length() <= this.zzjn().zzku()) {
            final URL zzb = this.zzb(zzab, zza);
            if (zzb == null) {
                this.zzbh("Failed to build collect GET endpoint url");
                return false;
            }
            if (this.zzb(zzb) != 200) {
                return false;
            }
        }
        else {
            final String zza2 = this.zza(zzab, false);
            if (zza2 == null) {
                this.zzjm().zza(zzab, "Error formatting hit for POST upload");
                return true;
            }
            final byte[] bytes = zza2.getBytes();
            if (bytes.length > this.zzjn().zzkw()) {
                this.zzjm().zza(zzab, "Hit payload exceeds size limit");
                return true;
            }
            final URL zzh = this.zzh(zzab);
            if (zzh == null) {
                this.zzbh("Failed to build collect POST endpoint url");
                return false;
            }
            if (this.zza(zzh, bytes) != 200) {
                return false;
            }
        }
        return true;
    }
    
    private static byte[] zzg(final byte[] array) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(array);
        gzipOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    private URL zzh(final zzab zzab) {
        Label_0049: {
            if (!zzab.zzlt()) {
                break Label_0049;
            }
            String s = this.zzjn().zzkF() + this.zzjn().zzkH();
            try {
                return new URL(s);
                s = this.zzjn().zzkG() + this.zzjn().zzkH();
                return new URL(s);
            }
            catch (MalformedURLException ex) {
                this.zze("Error trying to parse the hardcoded host url", ex);
                return null;
            }
        }
    }
    
    private String zzi(final zzab zzab) {
        return String.valueOf(zzab.zzlq());
    }
    
    private URL zzlC() {
        final String string = this.zzjn().zzkF() + this.zzjn().zzkI();
        try {
            return new URL(string);
        }
        catch (MalformedURLException ex) {
            this.zze("Error trying to parse the hardcoded host url", ex);
            return null;
        }
    }
    
    String zza(final zzab zzab, final boolean b) {
        zzx.zzz(zzab);
        final StringBuilder sb = new StringBuilder();
        try {
            for (final Map.Entry<String, String> entry : zzab.zzn().entrySet()) {
                final String s = entry.getKey();
                if (!"ht".equals(s) && !"qt".equals(s) && !"AppUID".equals(s) && !"z".equals(s) && !"_gmsv".equals(s)) {
                    this.zza(sb, s, entry.getValue());
                }
            }
        }
        catch (UnsupportedEncodingException ex) {
            this.zze("Failed to encode name or value", ex);
            return null;
        }
        this.zza(sb, "ht", String.valueOf(zzab.zzlr()));
        this.zza(sb, "qt", String.valueOf(this.zzjl().currentTimeMillis() - zzab.zzlr()));
        if (this.zzjn().zzkr()) {
            this.zza(sb, "_gmsv", zze.VERSION);
        }
        if (b) {
            final long zzlu = zzab.zzlu();
            String s2;
            if (zzlu != 0L) {
                s2 = String.valueOf(zzlu);
            }
            else {
                s2 = this.zzi(zzab);
            }
            this.zza(sb, "z", s2);
        }
        return sb.toString();
    }
    
    List<Long> zza(final List<zzab> list, final boolean b) {
        zzx.zzac(!list.isEmpty());
        this.zza("Uploading batched hits. compression, count", b, list.size());
        final zza zza = new zza();
        final ArrayList<Long> list2 = new ArrayList<Long>();
        for (final zzab zzab : list) {
            if (!zza.zzj(zzab)) {
                break;
            }
            list2.add(zzab.zzlq());
        }
        if (zza.zzlE() == 0) {
            return list2;
        }
        final URL zzlC = this.zzlC();
        if (zzlC == null) {
            this.zzbh("Failed to build batching endpoint url");
            return Collections.emptyList();
        }
        int n;
        if (b) {
            n = this.zzb(zzlC, zza.getPayload());
        }
        else {
            n = this.zza(zzlC, zza.getPayload());
        }
        if (200 == n) {
            this.zza("Batched upload completed. Hits batched", zza.zzlE());
            return list2;
        }
        this.zza("Network error uploading hits. status code", n);
        if (this.zzjn().zzkL().contains(n)) {
            this.zzbg("Server instructed the client to stop batching");
            this.zzTc.start();
        }
        return Collections.emptyList();
    }
    
    HttpURLConnection zzc(final URL url) throws IOException {
        final URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(this.zzjn().zzkU());
        httpURLConnection.setReadTimeout(this.zzjn().zzkV());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzzN);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }
    
    @Override
    protected void zziJ() {
        this.zza("Network initialized. User agent", this.zzzN);
    }
    
    public boolean zzlB() {
        this.zzjk();
        this.zzjv();
        final ConnectivityManager connectivityManager = (ConnectivityManager)this.getContext().getSystemService("connectivity");
        while (true) {
            try {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    this.zzbd("No network connectivity");
                    return false;
                }
            }
            catch (SecurityException ex) {
                final NetworkInfo activeNetworkInfo = null;
                continue;
            }
            break;
        }
        return true;
    }
    
    public List<Long> zzq(final List<zzab> list) {
        boolean b = true;
        this.zzjk();
        this.zzjv();
        zzx.zzz(list);
        int n;
        if (this.zzjn().zzkL().isEmpty() || !this.zzTc.zzv(1000L * this.zzjn().zzkE())) {
            b = false;
            n = 0;
        }
        else {
            n = ((this.zzjn().zzkJ() != zzm.zzRk && b) ? 1 : 0);
            if (this.zzjn().zzkK() != zzo.zzRv) {
                b = false;
            }
        }
        if (n != 0) {
            return this.zza(list, b);
        }
        return this.zzr(list);
    }
    
    List<Long> zzr(final List<zzab> list) {
        final ArrayList<Long> list2 = new ArrayList<Long>(list.size());
        for (final zzab zzab : list) {
            if (!this.zzg(zzab)) {
                break;
            }
            list2.add(zzab.zzlq());
            if (list2.size() >= this.zzjn().zzkC()) {
                return list2;
            }
        }
        return list2;
    }
    
    private class zza
    {
        private int zzTe;
        private ByteArrayOutputStream zzTf;
        
        public zza() {
            this.zzTf = new ByteArrayOutputStream();
        }
        
        public byte[] getPayload() {
            return this.zzTf.toByteArray();
        }
        
        public boolean zzj(final zzab zzab) {
            zzx.zzz(zzab);
            if (1 + this.zzTe > zzah.this.zzjn().zzkD()) {
                return false;
            }
            final String zza = zzah.this.zza(zzab, false);
            if (zza == null) {
                zzah.this.zzjm().zza(zzab, "Error formatting hit");
                return true;
            }
            final byte[] bytes = zza.getBytes();
            int length = bytes.length;
            if (length > zzah.this.zzjn().zzkv()) {
                zzah.this.zzjm().zza(zzab, "Hit size exceeds the maximum size limit");
                return true;
            }
            if (this.zzTf.size() > 0) {
                ++length;
            }
            if (length + this.zzTf.size() > zzah.this.zzjn().zzkx()) {
                return false;
            }
            try {
                if (this.zzTf.size() > 0) {
                    this.zzTf.write(zzah.zzTd);
                }
                this.zzTf.write(bytes);
                ++this.zzTe;
                return true;
            }
            catch (IOException ex) {
                zzah.this.zze("Failed to write payload when batching hits", ex);
                return true;
            }
        }
        
        public int zzlE() {
            return this.zzTe;
        }
    }
}
