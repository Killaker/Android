package com.google.android.gms.tagmanager;

import android.content.*;
import java.io.*;
import android.os.*;
import java.util.*;
import android.net.*;
import java.net.*;

class zzcx implements zzac
{
    private final Context mContext;
    private final zzb zzblh;
    private final zza zzbli;
    private final String zzzN;
    
    zzcx(final Context context, final zza zza) {
        this((zzb)new zzb() {
            @Override
            public HttpURLConnection zzd(final URL url) throws IOException {
                return (HttpURLConnection)url.openConnection();
            }
        }, context, zza);
    }
    
    zzcx(final zzb zzblh, final Context context, final zza zzbli) {
        this.zzblh = zzblh;
        this.mContext = context.getApplicationContext();
        this.zzbli = zzbli;
        this.zzzN = this.zza("GoogleTagManager", "4.00", Build$VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
    }
    
    static String zzc(final Locale locale) {
        if (locale != null && locale.getLanguage() != null && locale.getLanguage().length() != 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (locale.getCountry() != null && locale.getCountry().length() != 0) {
                sb.append("-").append(locale.getCountry().toLowerCase());
            }
            return sb.toString();
        }
        return null;
    }
    
    @Override
    public void zzE(final List<zzaq> list) {
        final int min = Math.min(list.size(), 40);
        int n = 1;
        int n2 = 0;
    Label_0068_Outer:
        while (true) {
            if (n2 >= min) {
                return;
            }
            final zzaq zzaq = list.get(n2);
            final URL zzd = this.zzd(zzaq);
            Label_0077: {
                if (zzd != null) {
                    break Label_0077;
                }
                zzbg.zzaK("No destination: discarding hit.");
                this.zzbli.zzb(zzaq);
                int n3 = n;
                HttpURLConnection zzd2;
                int responseCode;
                int n4;
                IOException ex;
                Label_0103_Outer:Label_0210_Outer:
                while (true) {
                    ++n2;
                    n = n3;
                    continue Label_0068_Outer;
                    while (true) {
                        try {
                            zzd2 = this.zzblh.zzd(zzd);
                            while (true) {
                                if (n != 0) {
                                    try {
                                        zzbl.zzbb(this.mContext);
                                        n = 0;
                                        zzd2.setRequestProperty("User-Agent", this.zzzN);
                                        responseCode = zzd2.getResponseCode();
                                        if (responseCode != 200) {
                                            zzbg.zzaK("Bad response: " + responseCode);
                                            this.zzbli.zzc(zzaq);
                                        }
                                        else {
                                            this.zzbli.zza(zzaq);
                                        }
                                        zzd2.disconnect();
                                        n3 = n;
                                        continue Label_0103_Outer;
                                    }
                                    finally {
                                        n4 = n;
                                        try {
                                            zzd2.disconnect();
                                        }
                                        catch (IOException ex2) {
                                            n = n4;
                                            ex = ex2;
                                        }
                                    }
                                    zzbg.zzaK("Exception sending hit: " + ex.getClass().getSimpleName());
                                    zzbg.zzaK(ex.getMessage());
                                    this.zzbli.zzc(zzaq);
                                    n3 = n;
                                    continue Label_0103_Outer;
                                }
                                continue Label_0210_Outer;
                            }
                        }
                        catch (IOException ex) {
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public boolean zzGw() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzbg.v("...no network connectivity");
            return false;
        }
        return true;
    }
    
    String zza(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", s, s2, s3, s4, s5, s6);
    }
    
    URL zzd(final zzaq zzaq) {
        final String zzGF = zzaq.zzGF();
        try {
            return new URL(zzGF);
        }
        catch (MalformedURLException ex) {
            zzbg.e("Error trying to parse the GTM url.");
            return null;
        }
    }
    
    public interface zza
    {
        void zza(final zzaq p0);
        
        void zzb(final zzaq p0);
        
        void zzc(final zzaq p0);
    }
    
    interface zzb
    {
        HttpURLConnection zzd(final URL p0) throws IOException;
    }
}
