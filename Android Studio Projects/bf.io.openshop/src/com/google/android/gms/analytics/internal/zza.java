package com.google.android.gms.analytics.internal;

import com.google.android.gms.ads.identifier.*;
import android.text.*;
import java.util.*;
import java.math.*;
import java.security.*;
import java.io.*;

public class zza extends zzd
{
    public static boolean zzPV;
    private AdvertisingIdClient.Info zzPW;
    private final zzaj zzPX;
    private String zzPY;
    private boolean zzPZ;
    private Object zzQa;
    
    zza(final zzf zzf) {
        super(zzf);
        this.zzPZ = false;
        this.zzQa = new Object();
        this.zzPX = new zzaj(zzf.zzjl());
    }
    
    private boolean zza(final AdvertisingIdClient.Info info, final AdvertisingIdClient.Info info2) {
        String id;
        if (info2 == null) {
            id = null;
        }
        else {
            id = info2.getId();
        }
        if (TextUtils.isEmpty((CharSequence)id)) {
            return true;
        }
        while (true) {
            final String zzkk = this.zzjr().zzkk();
            String zzbb = null;
        Label_0198:
            while (true) {
                synchronized (this.zzQa) {
                    if (!this.zzPZ) {
                        this.zzPY = this.zzjb();
                        this.zzPZ = true;
                        zzbb = zzbb(id + zzkk);
                        if (TextUtils.isEmpty((CharSequence)zzbb)) {
                            return false;
                        }
                        break Label_0198;
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)this.zzPY)) {
                    continue;
                }
                String id2 = null;
                if (info != null) {
                    id2 = info.getId();
                }
                if (id2 == null) {
                    // monitorexit(o)
                    return this.zzbc(id + zzkk);
                }
                this.zzPY = zzbb(id2 + zzkk);
                continue;
            }
            if (zzbb.equals(this.zzPY)) {
                // monitorexit(o)
                return true;
            }
            String zzkl;
            if (!TextUtils.isEmpty((CharSequence)this.zzPY)) {
                this.zzbd("Resetting the client id because Advertising Id changed.");
                zzkl = this.zzjr().zzkl();
                this.zza("New client Id", zzkl);
            }
            else {
                zzkl = zzkk;
            }
            // monitorexit(o)
            return this.zzbc(id + zzkl);
        }
    }
    
    private static String zzbb(final String s) {
        final MessageDigest zzbv = zzam.zzbv("MD5");
        if (zzbv == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, zzbv.digest(s.getBytes())));
    }
    
    private boolean zzbc(final String s) {
        try {
            final String zzbb = zzbb(s);
            this.zzbd("Storing hashed adid.");
            final FileOutputStream openFileOutput = this.getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzbb.getBytes());
            openFileOutput.close();
            this.zzPY = zzbb;
            return true;
        }
        catch (IOException ex) {
            this.zze("Error creating hash file", ex);
            return false;
        }
    }
    
    private AdvertisingIdClient.Info zziZ() {
        synchronized (this) {
            if (this.zzPX.zzv(1000L)) {
                this.zzPX.start();
                final AdvertisingIdClient.Info zzja = this.zzja();
                if (this.zza(this.zzPW, zzja)) {
                    this.zzPW = zzja;
                }
                else {
                    this.zzbh("Failed to reset client id on adid change. Not using adid");
                    this.zzPW = new AdvertisingIdClient.Info("", false);
                }
            }
            return this.zzPW;
        }
    }
    
    @Override
    protected void zziJ() {
    }
    
    public boolean zziU() {
        this.zzjv();
        final AdvertisingIdClient.Info zziZ = this.zziZ();
        boolean b = false;
        if (zziZ != null) {
            final boolean limitAdTrackingEnabled = zziZ.isLimitAdTrackingEnabled();
            b = false;
            if (!limitAdTrackingEnabled) {
                b = true;
            }
        }
        return b;
    }
    
    public String zziY() {
        this.zzjv();
        final AdvertisingIdClient.Info zziZ = this.zziZ();
        String id;
        if (zziZ != null) {
            id = zziZ.getId();
        }
        else {
            id = null;
        }
        if (TextUtils.isEmpty((CharSequence)id)) {
            return null;
        }
        return id;
    }
    
    protected AdvertisingIdClient.Info zzja() {
        try {
            final Object advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.getContext());
            return (AdvertisingIdClient.Info)advertisingIdInfo;
        }
        catch (IllegalStateException ex) {
            this.zzbg("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        }
        catch (Throwable t) {
            final boolean zzPV = zza.zzPV;
            final Object advertisingIdInfo = null;
            if (!zzPV) {
                zza.zzPV = true;
                this.zzd("Error getting advertiser id", t);
                return null;
            }
            return (AdvertisingIdClient.Info)advertisingIdInfo;
        }
    }
    
    protected String zzjb() {
        FileInputStream openFileInput = null;
        String s = null;
        try {
            openFileInput = this.getContext().openFileInput("gaClientIdData");
            final byte[] array = new byte[128];
            final int read = openFileInput.read(array, 0, 128);
            if (openFileInput.available() > 0) {
                this.zzbg("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                this.getContext().deleteFile("gaClientIdData");
                return null;
            }
            if (read <= 0) {
                this.zzbd("Hash file is empty.");
                openFileInput.close();
                return null;
            }
            s = new String(array, 0, read);
            final FileInputStream fileInputStream = openFileInput;
            fileInputStream.close();
            return s;
        }
        catch (IOException ex) {}
        catch (FileNotFoundException ex2) {
            return null;
        }
        try {
            final FileInputStream fileInputStream = openFileInput;
            fileInputStream.close();
            return s;
        }
        catch (IOException ex3) {}
        catch (FileNotFoundException ex4) {}
    }
}
