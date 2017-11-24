package com.google.android.gms.tagmanager;

import android.net.*;
import java.net.*;
import java.io.*;

class zzcb
{
    private static zzcb zzbjQ;
    private volatile String zzbhM;
    private volatile zza zzbjR;
    private volatile String zzbjS;
    private volatile String zzbjT;
    
    zzcb() {
        this.clear();
    }
    
    static zzcb zzGU() {
        synchronized (zzcb.class) {
            if (zzcb.zzbjQ == null) {
                zzcb.zzbjQ = new zzcb();
            }
            return zzcb.zzbjQ;
        }
    }
    
    private String zzgk(final String s) {
        return s.split("&")[0].split("=")[1];
    }
    
    private String zzq(final Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }
    
    void clear() {
        this.zzbjR = zza.zzbjU;
        this.zzbjS = null;
        this.zzbhM = null;
        this.zzbjT = null;
    }
    
    String getContainerId() {
        return this.zzbhM;
    }
    
    zza zzGV() {
        return this.zzbjR;
    }
    
    String zzGW() {
        return this.zzbjS;
    }
    
    boolean zzp(final Uri uri) {
        while (true) {
            boolean b = true;
            String decode;
            synchronized (this) {
                while (true) {
                    while (true) {
                        try {
                            decode = URLDecoder.decode(uri.toString(), "UTF-8");
                            if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                                break;
                            }
                            zzbg.v("Container preview url: " + decode);
                            if (decode.matches(".*?&gtm_debug=x$")) {
                                this.zzbjR = zza.zzbjW;
                                this.zzbjT = this.zzq(uri);
                                if (this.zzbjR == zza.zzbjV || this.zzbjR == zza.zzbjW) {
                                    this.zzbjS = "/r?" + this.zzbjT;
                                }
                                this.zzbhM = this.zzgk(this.zzbjT);
                                return b;
                            }
                        }
                        catch (UnsupportedEncodingException ex) {
                            b = false;
                            return b;
                        }
                        this.zzbjR = zza.zzbjV;
                        continue;
                    }
                }
            }
            if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                zzbg.zzaK("Invalid preview uri: " + decode);
                b = false;
                return b;
            }
            if (this.zzgk(uri.getQuery()).equals(this.zzbhM)) {
                zzbg.v("Exit preview mode for container: " + this.zzbhM);
                this.zzbjR = zza.zzbjU;
                this.zzbjS = null;
                return b;
            }
            b = false;
            return b;
        }
    }
    
    enum zza
    {
        zzbjU, 
        zzbjV, 
        zzbjW;
    }
}
