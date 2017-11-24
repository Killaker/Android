package com.google.android.gms.iid;

import android.app.*;
import java.security.*;
import android.content.*;
import android.util.*;
import android.content.pm.*;
import java.io.*;
import java.util.*;
import com.google.android.gms.gcm.*;
import android.os.*;

public class zzc
{
    static String zzaNg;
    static int zzaNh;
    static int zzaNi;
    static int zzaNj;
    Context context;
    PendingIntent zzaLQ;
    Messenger zzaLU;
    Map<String, Object> zzaNk;
    Messenger zzaNl;
    MessengerCompat zzaNm;
    long zzaNn;
    long zzaNo;
    int zzaNp;
    int zzaNq;
    long zzaNr;
    
    static {
        zzc.zzaNg = null;
        zzc.zzaNh = 0;
        zzc.zzaNi = 0;
        zzc.zzaNj = 0;
    }
    
    public zzc(final Context context) {
        this.zzaNk = new HashMap<String, Object>();
        this.context = context;
    }
    
    private void zzE(final Object o) {
        synchronized (this.getClass()) {
            for (final String s : this.zzaNk.keySet()) {
                final Object value = this.zzaNk.get(s);
                this.zzaNk.put(s, o);
                this.zzg(value, o);
            }
        }
    }
    // monitorexit(clazz)
    
    static String zza(final KeyPair p0, final String... p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "\n"
        //     2: aload_1        
        //     3: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;
        //     6: ldc             "UTF-8"
        //     8: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    11: astore          4
        //    13: aload_0        
        //    14: invokevirtual   java/security/KeyPair.getPrivate:()Ljava/security/PrivateKey;
        //    17: astore          7
        //    19: aload           7
        //    21: instanceof      Ljava/security/interfaces/RSAPrivateKey;
        //    24: ifeq            77
        //    27: ldc             "SHA256withRSA"
        //    29: astore          8
        //    31: aload           8
        //    33: invokestatic    java/security/Signature.getInstance:(Ljava/lang/String;)Ljava/security/Signature;
        //    36: astore          9
        //    38: aload           9
        //    40: aload           7
        //    42: invokevirtual   java/security/Signature.initSign:(Ljava/security/PrivateKey;)V
        //    45: aload           9
        //    47: aload           4
        //    49: invokevirtual   java/security/Signature.update:([B)V
        //    52: aload           9
        //    54: invokevirtual   java/security/Signature.sign:()[B
        //    57: invokestatic    com/google/android/gms/iid/InstanceID.zzn:([B)Ljava/lang/String;
        //    60: astore          10
        //    62: aload           10
        //    64: areturn        
        //    65: astore_2       
        //    66: ldc             "InstanceID/Rpc"
        //    68: ldc             "Unable to encode string"
        //    70: aload_2        
        //    71: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    74: pop            
        //    75: aconst_null    
        //    76: areturn        
        //    77: ldc             "SHA256withECDSA"
        //    79: astore          8
        //    81: goto            31
        //    84: astore          5
        //    86: ldc             "InstanceID/Rpc"
        //    88: ldc             "Unable to sign registration request"
        //    90: aload           5
        //    92: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    95: pop            
        //    96: aconst_null    
        //    97: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  0      13     65     77     Ljava/io/UnsupportedEncodingException;
        //  13     27     84     98     Ljava/security/GeneralSecurityException;
        //  31     62     84     98     Ljava/security/GeneralSecurityException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String zzaN(final Context context) {
        if (zzc.zzaNg != null) {
            return zzc.zzaNg;
        }
        zzc.zzaNh = Process.myUid();
        final PackageManager packageManager = context.getPackageManager();
        final Iterator iterator = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
        while (true) {
            Label_0199: {
                if (!iterator.hasNext()) {
                    break Label_0199;
                }
                final ResolveInfo resolveInfo = iterator.next();
                Label_0149: {
                    if (packageManager.checkPermission("com.google.android.c2dm.permission.RECEIVE", resolveInfo.serviceInfo.packageName) != 0) {
                        break Label_0149;
                    }
                    try {
                        final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 0);
                        Log.w("InstanceID/Rpc", "Found " + applicationInfo.uid);
                        zzc.zzaNi = applicationInfo.uid;
                        return zzc.zzaNg = resolveInfo.serviceInfo.packageName;
                        Log.w("InstanceID/Rpc", "Failed to resolve REGISTER intent, falling back");
                        try {
                            final ApplicationInfo applicationInfo2 = packageManager.getApplicationInfo("com.google.android.gms", 0);
                            zzc.zzaNg = applicationInfo2.packageName;
                            zzc.zzaNi = applicationInfo2.uid;
                            return zzc.zzaNg;
                        }
                        catch (PackageManager$NameNotFoundException ex) {
                            try {
                                final ApplicationInfo applicationInfo3 = packageManager.getApplicationInfo("com.google.android.gsf", 0);
                                zzc.zzaNg = applicationInfo3.packageName;
                                zzc.zzaNi = applicationInfo3.uid;
                                return zzc.zzaNg;
                            }
                            catch (PackageManager$NameNotFoundException ex2) {
                                Log.w("InstanceID/Rpc", "Both Google Play Services and legacy GSF package are missing");
                                return null;
                            }
                        }
                        Log.w("InstanceID/Rpc", "Possible malicious package " + resolveInfo.serviceInfo.packageName + " declares " + "com.google.android.c2dm.intent.REGISTER" + " without permission");
                    }
                    catch (PackageManager$NameNotFoundException ex3) {}
                }
            }
        }
    }
    
    private Intent zzb(final Bundle bundle, final KeyPair keyPair) throws IOException {
        final ConditionVariable conditionVariable = new ConditionVariable();
        final String zzyF = zzyF();
        final Class<? extends zzc> class1 = this.getClass();
        Object o2 = null;
        Label_0105: {
            synchronized (class1) {
                this.zzaNk.put(zzyF, conditionVariable);
                // monitorexit(class1)
                this.zza(bundle, keyPair, zzyF);
                conditionVariable.block(30000L);
                final Class<? extends zzc> class2 = this.getClass();
                // monitorenter(class2)
                final zzc zzc = this;
                final Map<String, Object> map = zzc.zzaNk;
                final String s = zzyF;
                final Object o = map.remove(s);
                final Object o3;
                o2 = (o3 = o);
                final boolean b = o3 instanceof Intent;
                if (b) {
                    final Object o4 = o2;
                    final Intent intent = (Intent)o4;
                    return intent;
                }
                break Label_0105;
            }
            try {
                final zzc zzc = this;
                final Map<String, Object> map = zzc.zzaNk;
                final String s = zzyF;
                final Object o = map.remove(s);
                final Object o3;
                o2 = (o3 = o);
                final boolean b = o3 instanceof Intent;
                if (b) {
                    final Object o4 = o2;
                    final Intent intent2;
                    final Intent intent = intent2 = (Intent)o4;
                    return intent2;
                }
                if (o2 instanceof String) {
                    throw new IOException((String)o2);
                }
            }
            finally {
            }
            // monitorexit(class2)
        }
        Log.w("InstanceID/Rpc", "No response " + o2);
        throw new IOException("TIMEOUT");
    }
    
    private void zzea(final String s) {
        if ("com.google.android.gsf".equals(zzc.zzaNg)) {
            ++this.zzaNp;
            if (this.zzaNp >= 3) {
                if (this.zzaNp == 3) {
                    this.zzaNq = 1000 + new Random().nextInt(1000);
                }
                this.zzaNq *= 2;
                this.zzaNr = SystemClock.elapsedRealtime() + this.zzaNq;
                Log.w("InstanceID/Rpc", "Backoff due to " + s + " for " + this.zzaNq);
            }
        }
    }
    
    private void zzg(final Object o, final Object obj) {
        if (o instanceof ConditionVariable) {
            ((ConditionVariable)o).open();
        }
        if (!(o instanceof Messenger)) {
            return;
        }
        final Messenger messenger = (Messenger)o;
        final Message obtain = Message.obtain();
        obtain.obj = obj;
        try {
            messenger.send(obtain);
        }
        catch (RemoteException ex) {
            Log.w("InstanceID/Rpc", "Failed to send response " + ex);
        }
    }
    
    private void zzi(final String s, final Object o) {
        synchronized (this.getClass()) {
            final Object value = this.zzaNk.get(s);
            this.zzaNk.put(s, o);
            this.zzg(value, o);
        }
    }
    
    public static String zzyF() {
        synchronized (zzc.class) {
            final int zzaNj = zzc.zzaNj;
            zzc.zzaNj = zzaNj + 1;
            return Integer.toString(zzaNj);
        }
    }
    
    Intent zza(final Bundle bundle, final KeyPair keyPair) throws IOException {
        Intent intent = this.zzb(bundle, keyPair);
        if (intent != null && intent.hasExtra("google.messenger")) {
            intent = this.zzb(bundle, keyPair);
        }
        return intent;
    }
    
    void zza(final Bundle bundle, final KeyPair keyPair, final String s) throws IOException {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzaNr != 0L && elapsedRealtime <= this.zzaNr) {
            Log.w("InstanceID/Rpc", "Backoff mode, next request attempt: " + (this.zzaNr - elapsedRealtime) + " interval: " + this.zzaNq);
            throw new IOException("RETRY_LATER");
        }
        this.zzyE();
        if (zzc.zzaNg == null) {
            throw new IOException("MISSING_INSTANCEID_SERVICE");
        }
        this.zzaNn = SystemClock.elapsedRealtime();
        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage(zzc.zzaNg);
        bundle.putString("gmsv", Integer.toString(GoogleCloudMessaging.zzaK(this.context)));
        bundle.putString("osv", Integer.toString(Build$VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(InstanceID.zzaL(this.context)));
        bundle.putString("cliv", "1");
        bundle.putString("appid", InstanceID.zza(keyPair));
        final String zzn = InstanceID.zzn(keyPair.getPublic().getEncoded());
        bundle.putString("pub2", zzn);
        bundle.putString("sig", zza(keyPair, this.context.getPackageName(), zzn));
        intent.putExtras(bundle);
        this.zzt(intent);
        this.zzb(intent, s);
    }
    
    protected void zzb(final Intent intent, final String s) {
        this.zzaNn = SystemClock.elapsedRealtime();
        intent.putExtra("kid", "|ID|" + s + "|");
        intent.putExtra("X-kid", "|ID|" + s + "|");
        boolean b = "com.google.android.gsf".equals(zzc.zzaNg);
        final String stringExtra = intent.getStringExtra("useGsf");
        if (stringExtra != null) {
            b = "1".equals(stringExtra);
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Sending " + intent.getExtras());
        }
        if (this.zzaNl != null) {
            intent.putExtra("google.messenger", (Parcelable)this.zzaLU);
            final Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                this.zzaNl.send(obtain);
                return;
            }
            catch (RemoteException ex) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        if (b) {
            final Intent intent2 = new Intent("com.google.android.gms.iid.InstanceID");
            intent2.setPackage(this.context.getPackageName());
            intent2.putExtra("GSF", (Parcelable)intent);
            this.context.startService(intent2);
            return;
        }
        intent.putExtra("google.messenger", (Parcelable)this.zzaLU);
        intent.putExtra("messenger2", "1");
        if (this.zzaNm != null) {
            final Message obtain2 = Message.obtain();
            obtain2.obj = intent;
            try {
                this.zzaNm.send(obtain2);
                return;
            }
            catch (RemoteException ex2) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        this.context.startService(intent);
    }
    
    public void zze(final Message message) {
        if (message == null) {
            return;
        }
        if (message.obj instanceof Intent) {
            final Intent intent = (Intent)message.obj;
            intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
            if (intent.hasExtra("google.messenger")) {
                final Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof MessengerCompat) {
                    this.zzaNm = (MessengerCompat)parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzaNl = (Messenger)parcelableExtra;
                }
            }
            this.zzw((Intent)message.obj);
            return;
        }
        Log.w("InstanceID/Rpc", "Dropping invalid message");
    }
    
    void zzt(final Intent intent) {
        synchronized (this) {
            if (this.zzaLQ == null) {
                final Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                this.zzaLQ = PendingIntent.getBroadcast(this.context, 0, intent2, 0);
            }
            intent.putExtra("app", (Parcelable)this.zzaLQ);
        }
    }
    
    String zzu(final Intent intent) throws IOException {
        if (intent == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String s = intent.getStringExtra("registration_id");
        if (s == null) {
            s = intent.getStringExtra("unregistered");
        }
        intent.getLongExtra("Retry-After", 0L);
        if (s != null) {}
        if (s != null) {
            return s;
        }
        final String stringExtra = intent.getStringExtra("error");
        if (stringExtra != null) {
            throw new IOException(stringExtra);
        }
        Log.w("InstanceID/Rpc", "Unexpected response from GCM " + intent.getExtras(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    
    void zzv(final Intent intent) {
        String s = intent.getStringExtra("error");
        if (s == null) {
            Log.w("InstanceID/Rpc", "Unexpected response, no error or registration id " + intent.getExtras());
        }
        else {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                Log.d("InstanceID/Rpc", "Received InstanceID error " + s);
            }
            final boolean startsWith = s.startsWith("|");
            String s2 = null;
            if (startsWith) {
                final String[] split = s.split("\\|");
                if (!"ID".equals(split[1])) {
                    Log.w("InstanceID/Rpc", "Unexpected structured response " + s);
                }
                if (split.length > 2) {
                    s2 = split[2];
                    s = split[3];
                    if (s.startsWith(":")) {
                        s = s.substring(1);
                    }
                }
                else {
                    s = "UNKNOWN";
                    s2 = null;
                }
                intent.putExtra("error", s);
            }
            if (s2 == null) {
                this.zzE(s);
            }
            else {
                this.zzi(s2, s);
            }
            final long longExtra = intent.getLongExtra("Retry-After", 0L);
            if (longExtra > 0L) {
                this.zzaNo = SystemClock.elapsedRealtime();
                this.zzaNq = 1000 * (int)longExtra;
                this.zzaNr = SystemClock.elapsedRealtime() + this.zzaNq;
                Log.w("InstanceID/Rpc", "Explicit request from server to backoff: " + this.zzaNq);
                return;
            }
            if ("SERVICE_NOT_AVAILABLE".equals(s) || "AUTHENTICATION_FAILED".equals(s)) {
                this.zzea(s);
            }
        }
    }
    
    void zzw(final Intent intent) {
        if (intent == null) {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                Log.d("InstanceID/Rpc", "Unexpected response: null");
            }
        }
        else {
            final String action = intent.getAction();
            if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action) && !"com.google.android.gms.iid.InstanceID".equals(action)) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Unexpected response " + intent.getAction());
                }
            }
            else {
                final String stringExtra = intent.getStringExtra("registration_id");
                String stringExtra2;
                if (stringExtra == null) {
                    stringExtra2 = intent.getStringExtra("unregistered");
                }
                else {
                    stringExtra2 = stringExtra;
                }
                if (stringExtra2 == null) {
                    this.zzv(intent);
                    return;
                }
                this.zzaNn = SystemClock.elapsedRealtime();
                this.zzaNr = 0L;
                this.zzaNp = 0;
                this.zzaNq = 0;
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "AppIDResponse: " + stringExtra2 + " " + intent.getExtras());
                }
                final boolean startsWith = stringExtra2.startsWith("|");
                String s = null;
                if (startsWith) {
                    final String[] split = stringExtra2.split("\\|");
                    if (!"ID".equals(split[1])) {
                        Log.w("InstanceID/Rpc", "Unexpected structured response " + stringExtra2);
                    }
                    final String s2 = split[2];
                    if (split.length > 4) {
                        if ("SYNC".equals(split[3])) {
                            InstanceIDListenerService.zzaM(this.context);
                        }
                        else if ("RST".equals(split[3])) {
                            InstanceIDListenerService.zza(this.context, InstanceID.getInstance(this.context).zzyB());
                            intent.removeExtra("registration_id");
                            this.zzi(s2, intent);
                            return;
                        }
                    }
                    String substring = split[-1 + split.length];
                    if (substring.startsWith(":")) {
                        substring = substring.substring(1);
                    }
                    intent.putExtra("registration_id", substring);
                    s = s2;
                }
                if (s == null) {
                    this.zzE(intent);
                    return;
                }
                this.zzi(s, intent);
            }
        }
    }
    
    void zzyE() {
        if (this.zzaLU != null) {
            return;
        }
        zzaN(this.context);
        this.zzaLU = new Messenger((Handler)new Handler(Looper.getMainLooper()) {
            public void handleMessage(final Message message) {
                zzc.this.zze(message);
            }
        });
    }
}
