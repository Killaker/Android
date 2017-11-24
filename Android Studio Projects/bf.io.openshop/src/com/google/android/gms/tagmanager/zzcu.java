package com.google.android.gms.tagmanager;

import android.content.*;
import android.os.*;

class zzcu extends zzct
{
    private static final Object zzbkP;
    private static zzcu zzbla;
    private boolean connected;
    private Handler handler;
    private Context zzbkQ;
    private zzau zzbkR;
    private volatile zzas zzbkS;
    private int zzbkT;
    private boolean zzbkU;
    private boolean zzbkV;
    private boolean zzbkW;
    private zzav zzbkX;
    private zzbl zzbkY;
    private boolean zzbkZ;
    
    static {
        zzbkP = new Object();
    }
    
    private zzcu() {
        this.zzbkT = 1800000;
        this.zzbkU = true;
        this.zzbkV = false;
        this.connected = true;
        this.zzbkW = true;
        this.zzbkX = new zzav() {
            @Override
            public void zzax(final boolean b) {
                zzcu.this.zzd(b, zzcu.this.connected);
            }
        };
        this.zzbkZ = false;
    }
    
    public static zzcu zzHo() {
        if (zzcu.zzbla == null) {
            zzcu.zzbla = new zzcu();
        }
        return zzcu.zzbla;
    }
    
    private void zzHp() {
        (this.zzbkY = new zzbl(this)).zzba(this.zzbkQ);
    }
    
    private void zzHq() {
        this.handler = new Handler(this.zzbkQ.getMainLooper(), (Handler$Callback)new Handler$Callback() {
            public boolean handleMessage(final Message message) {
                if (1 == message.what && zzcu.zzbkP.equals(message.obj)) {
                    zzcu.this.dispatch();
                    if (zzcu.this.zzbkT > 0 && !zzcu.this.zzbkZ) {
                        zzcu.this.handler.sendMessageDelayed(zzcu.this.handler.obtainMessage(1, zzcu.zzbkP), (long)zzcu.this.zzbkT);
                    }
                }
                return true;
            }
        });
        if (this.zzbkT > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1, zzcu.zzbkP), (long)this.zzbkT);
        }
    }
    
    @Override
    public void dispatch() {
        synchronized (this) {
            if (!this.zzbkV) {
                zzbg.v("Dispatch call queued. Dispatch will run once initialization is complete.");
                this.zzbkU = true;
            }
            else {
                this.zzbkS.zzj(new Runnable() {
                    @Override
                    public void run() {
                        zzcu.this.zzbkR.dispatch();
                    }
                });
            }
        }
    }
    
    zzau zzHr() {
        Label_0050: {
            synchronized (this) {
                if (this.zzbkR != null) {
                    break Label_0050;
                }
                if (this.zzbkQ == null) {
                    throw new IllegalStateException("Cant get a store unless we have a context");
                }
            }
            this.zzbkR = new zzby(this.zzbkX, this.zzbkQ);
        }
        if (this.handler == null) {
            this.zzHq();
        }
        this.zzbkV = true;
        if (this.zzbkU) {
            this.dispatch();
            this.zzbkU = false;
        }
        if (this.zzbkY == null && this.zzbkW) {
            this.zzHp();
        }
        // monitorexit(this)
        return this.zzbkR;
    }
    
    void zza(final Context context, final zzas zzbkS) {
        synchronized (this) {
            if (this.zzbkQ == null) {
                this.zzbkQ = context.getApplicationContext();
                if (this.zzbkS == null) {
                    this.zzbkS = zzbkS;
                }
            }
        }
    }
    
    @Override
    public void zzay(final boolean b) {
        synchronized (this) {
            this.zzd(this.zzbkZ, b);
        }
    }
    
    void zzd(final boolean zzbkZ, final boolean connected) {
        while (true) {
            while (true) {
                Label_0153: {
                    Label_0146: {
                        synchronized (this) {
                            if (this.zzbkZ != zzbkZ || this.connected != connected) {
                                if ((zzbkZ || !connected) && this.zzbkT > 0) {
                                    this.handler.removeMessages(1, zzcu.zzbkP);
                                }
                                if (!zzbkZ && connected && this.zzbkT > 0) {
                                    this.handler.sendMessageDelayed(this.handler.obtainMessage(1, zzcu.zzbkP), (long)this.zzbkT);
                                }
                                final StringBuilder append = new StringBuilder().append("PowerSaveMode ");
                                if (!zzbkZ && connected) {
                                    break Label_0146;
                                }
                                break Label_0153;
                            }
                            return;
                            final StringBuilder append;
                            final String s;
                            zzbg.v(append.append(s).toString());
                            this.zzbkZ = zzbkZ;
                            this.connected = connected;
                            return;
                        }
                    }
                    final String s = "terminated.";
                    continue;
                }
                final String s = "initiated.";
                continue;
            }
        }
    }
    
    @Override
    public void zzjg() {
        synchronized (this) {
            if (!this.zzbkZ && this.connected && this.zzbkT > 0) {
                this.handler.removeMessages(1, zzcu.zzbkP);
                this.handler.sendMessage(this.handler.obtainMessage(1, zzcu.zzbkP));
            }
        }
    }
}
