package com.google.android.gms.internal;

import java.io.*;
import java.net.*;

class zzru implements zzrv
{
    private HttpURLConnection zzbmy;
    
    private InputStream zzd(final HttpURLConnection httpURLConnection) throws IOException {
        final int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            return httpURLConnection.getInputStream();
        }
        final String string = "Bad response: " + responseCode;
        if (responseCode == 404) {
            throw new FileNotFoundException(string);
        }
        throw new IOException(string);
    }
    
    private void zze(final HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
    
    @Override
    public void close() {
        this.zze(this.zzbmy);
    }
    
    @Override
    public InputStream zzgI(final String s) throws IOException {
        this.zzbmy = this.zzgJ(s);
        return this.zzd(this.zzbmy);
    }
    
    HttpURLConnection zzgJ(final String s) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        return httpURLConnection;
    }
}
