package com.google.android.gms.gcm;

import android.content.*;
import android.util.*;
import android.app.*;
import android.annotation.*;
import android.support.v4.content.*;
import java.util.*;
import android.os.*;

public abstract class GcmListenerService extends Service
{
    private int zzaLy;
    private int zzaLz;
    private final Object zzpV;
    
    public GcmListenerService() {
        this.zzpV = new Object();
        this.zzaLz = 0;
    }
    
    private void zzm(final Intent intent) {
        final PendingIntent pendingIntent = (PendingIntent)intent.getParcelableExtra("com.google.android.gms.gcm.PENDING_INTENT");
        while (true) {
            if (pendingIntent == null) {
                break Label_0018;
            }
            try {
                pendingIntent.send();
                if (zzx(intent.getExtras())) {
                    zza.zzf((Context)this, intent);
                }
            }
            catch (PendingIntent$CanceledException ex) {
                Log.e("GcmListenerService", "Notification pending intent canceled");
                continue;
            }
            break;
        }
    }
    
    @TargetApi(11)
    private void zzn(final Intent intent) {
        if (Build$VERSION.SDK_INT >= 11) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    GcmListenerService.this.zzo(intent);
                }
            });
            return;
        }
        new AsyncTask<Void, Void, Void>() {
            protected Void zzb(final Void... array) {
                GcmListenerService.this.zzo(intent);
                return null;
            }
        }.execute((Object[])new Void[0]);
    }
    
    private void zzo(final Intent intent) {
    Label_0110_Outer:
        while (true) {
        Label_0126_Outer:
            while (true) {
                while (true) {
                    int n = 0;
                    Label_0144: {
                        while (true) {
                            try {
                                final String action = intent.getAction();
                                n = -1;
                                switch (action.hashCode()) {
                                    case 366519424: {
                                        if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
                                            n = 0;
                                        }
                                        break Label_0144;
                                    }
                                    case 214175003: {
                                        if (action.equals("com.google.android.gms.gcm.NOTIFICATION_DISMISS")) {
                                            n = 1;
                                        }
                                        break Label_0144;
                                    }
                                    default: {
                                        break Label_0144;
                                    }
                                }
                                Log.d("GcmListenerService", "Unknown intent action: " + intent.getAction());
                                break Label_0068;
                                this.zzp(intent);
                                this.zzyh();
                                return;
                            }
                            finally {
                                WakefulBroadcastReceiver.completeWakefulIntent(intent);
                            }
                            if (zzx(intent.getExtras())) {
                                zza.zzg((Context)this, intent);
                                continue Label_0110_Outer;
                            }
                            continue Label_0110_Outer;
                        }
                    }
                    switch (n) {
                        case 0: {
                            continue Label_0126_Outer;
                        }
                        case 1: {
                            continue;
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    private void zzp(final Intent intent) {
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        switch (stringExtra) {
            default: {
                Log.w("GcmListenerService", "Received message with unknown type: " + stringExtra);
            }
            case "gcm": {
                if (zzx(intent.getExtras())) {
                    zza.zze((Context)this, intent);
                }
                this.zzq(intent);
            }
            case "deleted_messages": {
                this.onDeletedMessages();
            }
            case "send_event": {
                this.onMessageSent(intent.getStringExtra("google.message_id"));
            }
            case "send_error": {
                this.onSendError(intent.getStringExtra("google.message_id"), intent.getStringExtra("error"));
            }
        }
    }
    
    private void zzq(final Intent intent) {
        final Bundle extras = intent.getExtras();
        extras.remove("message_type");
        extras.remove("android.support.content.wakelockid");
        if (zzb.zzy(extras)) {
            if (!zzb.zzaI((Context)this)) {
                zzb.zzc((Context)this, this.getClass()).zzA(extras);
                return;
            }
            if (zzx(intent.getExtras())) {
                zza.zzh((Context)this, intent);
            }
            zzb.zzz(extras);
        }
        final String string = extras.getString("from");
        extras.remove("from");
        zzw(extras);
        this.onMessageReceived(string, extras);
    }
    
    static void zzw(final Bundle bundle) {
        final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (s != null && s.startsWith("google.c.")) {
                iterator.remove();
            }
        }
    }
    
    static boolean zzx(final Bundle bundle) {
        return "1".equals(bundle.getString("google.c.a.e"));
    }
    
    private void zzyh() {
        synchronized (this.zzpV) {
            --this.zzaLz;
            if (this.zzaLz == 0) {
                this.zzhd(this.zzaLy);
            }
        }
    }
    
    public final IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onDeletedMessages() {
    }
    
    public void onMessageReceived(final String s, final Bundle bundle) {
    }
    
    public void onMessageSent(final String s) {
    }
    
    public void onSendError(final String s, final String s2) {
    }
    
    public final int onStartCommand(final Intent intent, final int n, final int zzaLy) {
        synchronized (this.zzpV) {
            this.zzaLy = zzaLy;
            ++this.zzaLz;
            // monitorexit(this.zzpV)
            if (intent == null) {
                this.zzyh();
                return 2;
            }
        }
        if ("com.google.android.gms.gcm.NOTIFICATION_OPEN".equals(intent.getAction())) {
            this.zzm(intent);
            this.zzyh();
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        else {
            this.zzn(intent);
        }
        return 3;
    }
    
    boolean zzhd(final int n) {
        return this.stopSelfResult(n);
    }
}
