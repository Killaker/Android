package com.google.android.gms.iid;

import android.support.v4.content.*;
import java.io.*;
import android.content.*;
import java.util.*;
import android.util.*;
import java.security.spec.*;
import java.security.*;

public class zzd
{
    Context context;
    SharedPreferences zzaNt;
    
    public zzd(final Context context) {
        this(context, "com.google.android.gms.appid");
    }
    
    public zzd(final Context context, final String s) {
        this.context = context;
        this.zzaNt = context.getSharedPreferences(s, 4);
        this.zzeb(s + "-no-backup");
    }
    
    private void zzeb(final String s) {
        final File file = new File(new ContextCompat().getNoBackupFilesDir(this.context), s);
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !this.isEmpty()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    InstanceIDListenerService.zza(this.context, this);
                }
            }
            catch (IOException ex) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    Log.d("InstanceID/Store", "Error creating file in no backup dir: " + ex.getMessage());
                }
            }
        }
    }
    
    private String zzh(final String s, final String s2, final String s3) {
        return s + "|T|" + s2 + "|" + s3;
    }
    
    String get(final String s) {
        synchronized (this) {
            return this.zzaNt.getString(s, (String)null);
        }
    }
    
    String get(final String s, final String s2) {
        synchronized (this) {
            return this.zzaNt.getString(s + "|S|" + s2, (String)null);
        }
    }
    
    boolean isEmpty() {
        return this.zzaNt.getAll().isEmpty();
    }
    
    void zza(final SharedPreferences$Editor sharedPreferences$Editor, final String s, final String s2, final String s3) {
        synchronized (this) {
            sharedPreferences$Editor.putString(s + "|S|" + s2, s3);
        }
    }
    
    public void zza(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this) {
            final String zzh = this.zzh(s, s2, s3);
            final SharedPreferences$Editor edit = this.zzaNt.edit();
            edit.putString(zzh, s4);
            edit.putString("appVersion", s5);
            edit.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000L));
            edit.commit();
        }
    }
    
    KeyPair zzd(final String s, final long n) {
        synchronized (this) {
            final KeyPair zzyy = zza.zzyy();
            final SharedPreferences$Editor edit = this.zzaNt.edit();
            this.zza(edit, s, "|P|", InstanceID.zzn(zzyy.getPublic().getEncoded()));
            this.zza(edit, s, "|K|", InstanceID.zzn(zzyy.getPrivate().getEncoded()));
            this.zza(edit, s, "cre", Long.toString(n));
            edit.commit();
            return zzyy;
        }
    }
    
    public void zzec(final String s) {
        final SharedPreferences$Editor edit;
        synchronized (this) {
            edit = this.zzaNt.edit();
            for (final String s2 : this.zzaNt.getAll().keySet()) {
                if (s2.startsWith(s)) {
                    edit.remove(s2);
                }
            }
        }
        edit.commit();
    }
    // monitorexit(this)
    
    public KeyPair zzed(final String s) {
        return this.zzeg(s);
    }
    
    void zzee(final String s) {
        this.zzec(s + "|");
    }
    
    public void zzef(final String s) {
        this.zzec(s + "|T|");
    }
    
    KeyPair zzeg(final String s) {
        final String value = this.get(s, "|P|");
        final String value2 = this.get(s, "|K|");
        if (value2 == null) {
            return null;
        }
        try {
            final byte[] decode = Base64.decode(value, 8);
            final byte[] decode2 = Base64.decode(value2, 8);
            final KeyFactory instance = KeyFactory.getInstance("RSA");
            return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
        }
        catch (InvalidKeySpecException ex) {}
        catch (NoSuchAlgorithmException ex2) {
            goto Label_0087;
        }
    }
    
    public String zzi(final String s, final String s2, final String s3) {
        synchronized (this) {
            return this.zzaNt.getString(this.zzh(s, s2, s3), (String)null);
        }
    }
    
    public void zzj(final String s, final String s2, final String s3) {
        synchronized (this) {
            final String zzh = this.zzh(s, s2, s3);
            final SharedPreferences$Editor edit = this.zzaNt.edit();
            edit.remove(zzh);
            edit.commit();
        }
    }
    
    public void zzyG() {
        synchronized (this) {
            this.zzaNt.edit().clear().commit();
        }
    }
}
