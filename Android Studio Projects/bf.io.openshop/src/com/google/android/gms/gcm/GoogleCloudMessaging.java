package com.google.android.gms.gcm;

import java.util.concurrent.atomic.*;
import android.app.*;
import android.content.*;
import android.util.*;
import java.io.*;
import java.util.*;
import com.google.android.gms.iid.*;
import android.content.pm.*;
import android.support.annotation.*;
import android.os.*;
import java.util.concurrent.*;

public class GoogleCloudMessaging
{
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String INSTANCE_ID_SCOPE = "GCM";
    @Deprecated
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    @Deprecated
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
    public static int zzaLM;
    public static int zzaLN;
    public static int zzaLO;
    static GoogleCloudMessaging zzaLP;
    private static final AtomicInteger zzaLS;
    private Context context;
    private PendingIntent zzaLQ;
    private Map<String, Handler> zzaLR;
    private final BlockingQueue<Intent> zzaLT;
    final Messenger zzaLU;
    
    static {
        GoogleCloudMessaging.zzaLM = 5000000;
        GoogleCloudMessaging.zzaLN = 6500000;
        GoogleCloudMessaging.zzaLO = 7000000;
        zzaLS = new AtomicInteger(1);
    }
    
    public GoogleCloudMessaging() {
        this.zzaLT = new LinkedBlockingQueue<Intent>();
        this.zzaLR = Collections.synchronizedMap(new HashMap<String, Handler>());
        this.zzaLU = new Messenger((Handler)new Handler(Looper.getMainLooper()) {
            public void handleMessage(final Message message) {
                if (message == null || !(message.obj instanceof Intent)) {
                    Log.w("GCM", "Dropping invalid message");
                }
                final Intent intent = (Intent)message.obj;
                if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                    GoogleCloudMessaging.this.zzaLT.add(intent);
                }
                else if (!GoogleCloudMessaging.this.zzr(intent)) {
                    intent.setPackage(GoogleCloudMessaging.this.context.getPackageName());
                    GoogleCloudMessaging.this.context.sendBroadcast(intent);
                }
            }
        });
    }
    
    public static GoogleCloudMessaging getInstance(final Context context) {
        synchronized (GoogleCloudMessaging.class) {
            if (GoogleCloudMessaging.zzaLP == null) {
                GoogleCloudMessaging.zzaLP = new GoogleCloudMessaging();
                GoogleCloudMessaging.zzaLP.context = context.getApplicationContext();
            }
            return GoogleCloudMessaging.zzaLP;
        }
    }
    
    static String zza(final Intent intent, final String s) throws IOException {
        if (intent == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        final String stringExtra = intent.getStringExtra(s);
        if (stringExtra != null) {
            return stringExtra;
        }
        final String stringExtra2 = intent.getStringExtra("error");
        if (stringExtra2 != null) {
            throw new IOException(stringExtra2);
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    
    private void zza(final String s, final String s2, final long n, final int n2, final Bundle bundle) throws IOException {
        if (s == null) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        final Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.zzs(intent);
        intent.setPackage(zzaJ(this.context));
        intent.putExtra("google.to", s);
        intent.putExtra("google.message_id", s2);
        intent.putExtra("google.ttl", Long.toString(n));
        intent.putExtra("google.delay", Integer.toString(n2));
        intent.putExtra("google.from", this.zzdZ(s));
        if (zzaJ(this.context).contains(".gsf")) {
            final Bundle bundle2 = new Bundle();
            for (final String s3 : bundle.keySet()) {
                final Object value = bundle.get(s3);
                if (value instanceof String) {
                    bundle2.putString("gcm." + s3, (String)value);
                }
            }
            bundle2.putString("google.to", s);
            bundle2.putString("google.message_id", s2);
            InstanceID.getInstance(this.context).zzc("GCM", "upstream", bundle2);
            return;
        }
        this.context.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }
    
    public static String zzaJ(final Context context) {
        return zzc.zzaN(context);
    }
    
    public static int zzaK(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(zzaJ(context), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return -1;
        }
    }
    
    private String zzdZ(String substring) {
        final int index = substring.indexOf(64);
        if (index > 0) {
            substring = substring.substring(0, index);
        }
        return InstanceID.getInstance(this.context).zzyB().zzi("", substring, "GCM");
    }
    
    private boolean zzr(final Intent obj) {
        String s = obj.getStringExtra("In-Reply-To");
        if (s == null && obj.hasExtra("error")) {
            s = obj.getStringExtra("google.message_id");
        }
        if (s != null) {
            final Handler handler = this.zzaLR.remove(s);
            if (handler != null) {
                final Message obtain = Message.obtain();
                obtain.obj = obj;
                return handler.sendMessage(obtain);
            }
        }
        return false;
    }
    
    private String zzyk() {
        return "google.rpc" + String.valueOf(GoogleCloudMessaging.zzaLS.getAndIncrement());
    }
    
    public void close() {
        GoogleCloudMessaging.zzaLP = null;
        zzb.zzaLC = null;
        this.zzyl();
    }
    
    public String getMessageType(final Intent intent) {
        String stringExtra;
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            stringExtra = null;
        }
        else {
            stringExtra = intent.getStringExtra("message_type");
            if (stringExtra == null) {
                return "gcm";
            }
        }
        return stringExtra;
    }
    
    @Deprecated
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public String register(final String... array) throws IOException {
        synchronized (this) {
            final String zzc = this.zzc(array);
            final Bundle bundle = new Bundle();
            String s;
            if (zzaJ(this.context).contains(".gsf")) {
                bundle.putString("legacy.sender", zzc);
                s = InstanceID.getInstance(this.context).getToken(zzc, "GCM", bundle);
            }
            else {
                bundle.putString("sender", zzc);
                s = zza(this.zzE(bundle), "registration_id");
            }
            return s;
        }
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void send(final String s, final String s2, final long n, final Bundle bundle) throws IOException {
        this.zza(s, s2, n, -1, bundle);
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void send(final String s, final String s2, final Bundle bundle) throws IOException {
        this.send(s, s2, -1L, bundle);
    }
    
    @Deprecated
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void unregister() throws IOException {
        synchronized (this) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                throw new IOException("MAIN_THREAD");
            }
        }
        InstanceID.getInstance(this.context).deleteInstanceID();
    }
    // monitorexit(this)
    
    @Deprecated
    Intent zzE(Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        if (zzaK(this.context) < 0) {
            throw new IOException("Google Play Services missing");
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage(zzaJ(this.context));
        this.zzs(intent);
        intent.putExtra("google.message_id", this.zzyk());
        intent.putExtras(bundle);
        intent.putExtra("google.messenger", (Parcelable)this.zzaLU);
        this.context.startService(intent);
        try {
            return this.zzaLT.poll(30000L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    String zzc(final String... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        final StringBuilder sb = new StringBuilder(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(',').append(array[i]);
        }
        return sb.toString();
    }
    
    void zzs(final Intent intent) {
        synchronized (this) {
            if (this.zzaLQ == null) {
                final Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                this.zzaLQ = PendingIntent.getBroadcast(this.context, 0, intent2, 0);
            }
            intent.putExtra("app", (Parcelable)this.zzaLQ);
        }
    }
    
    void zzyl() {
        synchronized (this) {
            if (this.zzaLQ != null) {
                this.zzaLQ.cancel();
                this.zzaLQ = null;
            }
        }
    }
}
