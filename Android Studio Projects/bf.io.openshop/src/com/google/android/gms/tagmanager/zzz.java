package com.google.android.gms.tagmanager;

import android.content.*;
import java.net.*;
import java.io.*;

public class zzz implements zzar
{
    private static final Object zzbhz;
    private static zzz zzbiM;
    private String zzbiN;
    private String zzbiO;
    private zzas zzbiP;
    private zzcd zzbic;
    
    static {
        zzbhz = new Object();
    }
    
    private zzz(final Context context) {
        this(zzat.zzaZ(context), new zzcs());
    }
    
    zzz(final zzas zzbiP, final zzcd zzbic) {
        this.zzbiP = zzbiP;
        this.zzbic = zzbic;
    }
    
    public static zzar zzaX(final Context context) {
        synchronized (zzz.zzbhz) {
            if (zzz.zzbiM == null) {
                zzz.zzbiM = new zzz(context);
            }
            return zzz.zzbiM;
        }
    }
    
    @Override
    public boolean zzgc(String string) {
        if (!this.zzbic.zzlw()) {
            zzbg.zzaK("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        Label_0099: {
            if (this.zzbiN == null || this.zzbiO == null) {
                break Label_0099;
            }
            try {
                string = this.zzbiN + "?" + this.zzbiO + "=" + URLEncoder.encode(string, "UTF-8");
                zzbg.v("Sending wrapped url hit: " + string);
                this.zzbiP.zzgg(string);
                return true;
            }
            catch (UnsupportedEncodingException ex) {
                zzbg.zzd("Error wrapping URL for testing.", ex);
                return false;
            }
        }
    }
}
