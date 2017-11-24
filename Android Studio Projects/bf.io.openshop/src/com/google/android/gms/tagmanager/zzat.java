package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.concurrent.*;
import java.io.*;

class zzat extends Thread implements zzas
{
    private static zzat zzbjb;
    private volatile boolean mClosed;
    private final Context mContext;
    private volatile boolean zzRE;
    private final LinkedBlockingQueue<Runnable> zzbja;
    private volatile zzau zzbjc;
    
    private zzat(final Context mContext) {
        super("GAThread");
        this.zzbja = new LinkedBlockingQueue<Runnable>();
        this.zzRE = false;
        this.mClosed = false;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        this.start();
    }
    
    static zzat zzaZ(final Context context) {
        if (zzat.zzbjb == null) {
            zzat.zzbjb = new zzat(context);
        }
        return zzat.zzbjb;
    }
    
    private String zzd(final Throwable t) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        t.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    public void run() {
        while (!this.mClosed) {
            try {
                try {
                    final Runnable runnable = this.zzbja.take();
                    if (!this.zzRE) {
                        runnable.run();
                        continue;
                    }
                    continue;
                }
                catch (InterruptedException ex) {
                    zzbg.zzaJ(ex.toString());
                }
            }
            catch (Throwable t) {
                zzbg.e("Error on Google TagManager Thread: " + this.zzd(t));
                zzbg.e("Google TagManager is shutting down.");
                this.zzRE = true;
                continue;
            }
            break;
        }
    }
    
    @Override
    public void zzgg(final String s) {
        this.zzk(s, System.currentTimeMillis());
    }
    
    @Override
    public void zzj(final Runnable runnable) {
        this.zzbja.add(runnable);
    }
    
    void zzk(final String s, final long n) {
        this.zzj(new Runnable() {
            final /* synthetic */ zzas zzbjd;
            
            @Override
            public void run() {
                if (zzat.this.zzbjc == null) {
                    final zzcu zzHo = zzcu.zzHo();
                    zzHo.zza(zzat.this.mContext, this.zzbjd);
                    zzat.this.zzbjc = zzHo.zzHr();
                }
                zzat.this.zzbjc.zzg(n, s);
            }
        });
    }
}
