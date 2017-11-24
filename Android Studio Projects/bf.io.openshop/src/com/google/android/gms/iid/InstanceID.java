package com.google.android.gms.iid;

import android.content.*;
import java.util.*;
import android.util.*;
import java.security.*;
import android.content.pm.*;
import java.io.*;
import android.os.*;

public class InstanceID
{
    public static final String ERROR_BACKOFF = "RETRY_LATER";
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String ERROR_TIMEOUT = "TIMEOUT";
    static Map<String, InstanceID> zzaMP;
    private static zzd zzaMQ;
    private static zzc zzaMR;
    static String zzaMV;
    Context mContext;
    KeyPair zzaMS;
    String zzaMT;
    long zzaMU;
    
    static {
        InstanceID.zzaMP = new HashMap<String, InstanceID>();
    }
    
    protected InstanceID(final Context context, final String zzaMT, final Bundle bundle) {
        this.zzaMT = "";
        this.mContext = context.getApplicationContext();
        this.zzaMT = zzaMT;
    }
    
    public static InstanceID getInstance(final Context context) {
        return zza(context, null);
    }
    
    public static InstanceID zza(final Context context, final Bundle bundle) {
        // monitorenter(InstanceID.class)
        while (true) {
            while (true) {
                String string = null;
                Label_0140: {
                    if (bundle == null) {
                        string = "";
                        break Label_0140;
                    }
                    Label_0114: {
                        break Label_0114;
                        try {
                            final Context applicationContext = context.getApplicationContext();
                            if (InstanceID.zzaMQ == null) {
                                InstanceID.zzaMQ = new zzd(applicationContext);
                                InstanceID.zzaMR = new zzc(applicationContext);
                            }
                            InstanceID.zzaMV = Integer.toString(zzaL(applicationContext));
                            final String s;
                            InstanceID instanceID = InstanceID.zzaMP.get(s);
                            if (instanceID == null) {
                                instanceID = new InstanceID(applicationContext, s, bundle);
                                InstanceID.zzaMP.put(s, instanceID);
                            }
                            return instanceID;
                            string = bundle.getString("subtype");
                            break Label_0140;
                        }
                        finally {
                        }
                        // monitorexit(InstanceID.class)
                    }
                    final String s = string;
                    continue;
                }
                if (string == null) {
                    final String s = "";
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    static String zza(final KeyPair keyPair) {
        final byte[] encoded = keyPair.getPublic().getEncoded();
        try {
            final byte[] digest = MessageDigest.getInstance("SHA1").digest(encoded);
            digest[0] = (byte)(0xFF & 112 + (0xF & digest[0]));
            return Base64.encodeToString(digest, 0, 8, 11);
        }
        catch (NoSuchAlgorithmException ex) {
            Log.w("InstanceID", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }
    
    static int zzaL(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("InstanceID", "Never happens: can't find own package " + ex);
            return 0;
        }
    }
    
    static String zzn(final byte[] array) {
        return Base64.encodeToString(array, 11);
    }
    
    public void deleteInstanceID() throws IOException {
        this.zzb("*", "*", null);
        this.zzyA();
    }
    
    public void deleteToken(final String s, final String s2) throws IOException {
        this.zzb(s, s2, null);
    }
    
    public long getCreationTime() {
        if (this.zzaMU == 0L) {
            final String value = InstanceID.zzaMQ.get(this.zzaMT, "cre");
            if (value != null) {
                this.zzaMU = Long.parseLong(value);
            }
        }
        return this.zzaMU;
    }
    
    public String getId() {
        return zza(this.zzyz());
    }
    
    public String getToken(final String s, final String s2) throws IOException {
        return this.getToken(s, s2, null);
    }
    
    public String getToken(final String s, final String s2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        boolean b = true;
        String s3;
        if (this.zzyD()) {
            s3 = null;
        }
        else {
            s3 = InstanceID.zzaMQ.zzi(this.zzaMT, s, s2);
        }
        if (s3 == null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (bundle.getString("ttl") != null) {
                b = false;
            }
            final boolean equals = "jwt".equals(bundle.getString("type"));
            boolean b2 = false;
            if (!equals) {
                b2 = b;
            }
            s3 = this.zzc(s, s2, bundle);
            Log.w("InstanceID", "token: " + s3);
            if (s3 != null && b2) {
                InstanceID.zzaMQ.zza(this.zzaMT, s, s2, s3, InstanceID.zzaMV);
                return s3;
            }
        }
        return s3;
    }
    
    public void zzb(String zzaMT, final String s, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        InstanceID.zzaMQ.zzj(this.zzaMT, zzaMT, s);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("sender", zzaMT);
        if (s != null) {
            bundle.putString("scope", s);
        }
        bundle.putString("subscription", zzaMT);
        bundle.putString("delete", "1");
        bundle.putString("X-delete", "1");
        String zzaMT2;
        if ("".equals(this.zzaMT)) {
            zzaMT2 = zzaMT;
        }
        else {
            zzaMT2 = this.zzaMT;
        }
        bundle.putString("subtype", zzaMT2);
        if (!"".equals(this.zzaMT)) {
            zzaMT = this.zzaMT;
        }
        bundle.putString("X-subtype", zzaMT);
        InstanceID.zzaMR.zzu(InstanceID.zzaMR.zza(bundle, this.zzyz()));
    }
    
    public String zzc(final String s, final String s2, final Bundle bundle) throws IOException {
        if (s2 != null) {
            bundle.putString("scope", s2);
        }
        bundle.putString("sender", s);
        String zzaMT;
        if ("".equals(this.zzaMT)) {
            zzaMT = s;
        }
        else {
            zzaMT = this.zzaMT;
        }
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", s);
            bundle.putString("subtype", zzaMT);
            bundle.putString("X-subscription", s);
            bundle.putString("X-subtype", zzaMT);
        }
        return InstanceID.zzaMR.zzu(InstanceID.zzaMR.zza(bundle, this.zzyz()));
    }
    
    void zzyA() {
        this.zzaMU = 0L;
        InstanceID.zzaMQ.zzee(this.zzaMT);
        this.zzaMS = null;
    }
    
    public zzd zzyB() {
        return InstanceID.zzaMQ;
    }
    
    zzc zzyC() {
        return InstanceID.zzaMR;
    }
    
    boolean zzyD() {
        final String value = InstanceID.zzaMQ.get("appVersion");
        if (value != null && value.equals(InstanceID.zzaMV)) {
            final String value2 = InstanceID.zzaMQ.get("lastToken");
            if (value2 != null && System.currentTimeMillis() / 1000L - Long.valueOf(Long.parseLong(value2)) <= 604800L) {
                return false;
            }
        }
        return true;
    }
    
    KeyPair zzyz() {
        if (this.zzaMS == null) {
            this.zzaMS = InstanceID.zzaMQ.zzed(this.zzaMT);
        }
        if (this.zzaMS == null) {
            this.zzaMU = System.currentTimeMillis();
            this.zzaMS = InstanceID.zzaMQ.zzd(this.zzaMT, this.zzaMU);
        }
        return this.zzaMS;
    }
}
