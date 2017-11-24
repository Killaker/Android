package com.google.android.gms.analytics;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import android.content.*;
import android.text.*;
import com.google.android.gms.analytics.internal.*;
import android.support.annotation.*;

public class CampaignTrackingReceiver extends BroadcastReceiver
{
    static zzrp zzOM;
    static Boolean zzON;
    static Object zzqy;
    
    static {
        CampaignTrackingReceiver.zzqy = new Object();
    }
    
    public static boolean zzY(final Context context) {
        zzx.zzz(context);
        if (CampaignTrackingReceiver.zzON != null) {
            return CampaignTrackingReceiver.zzON;
        }
        final boolean zza = zzam.zza(context, CampaignTrackingReceiver.class, true);
        CampaignTrackingReceiver.zzON = zza;
        return zza;
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public void onReceive(final Context context, final Intent intent) {
        final zzf zzaa = zzf.zzaa(context);
        final zzaf zzjm = zzaa.zzjm();
        final String stringExtra = intent.getStringExtra("referrer");
        final String action = intent.getAction();
        zzjm.zza("CampaignTrackingReceiver received", action);
        if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty((CharSequence)stringExtra)) {
            zzjm.zzbg("CampaignTrackingReceiver received unexpected intent without referrer extra");
            return;
        }
        final boolean zzZ = CampaignTrackingService.zzZ(context);
        if (!zzZ) {
            zzjm.zzbg("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        this.zzaV(stringExtra);
        if (zzaa.zzjn().zzkr()) {
            zzjm.zzbh("Received unexpected installation campaign on package side");
            return;
        }
        final Class<? extends CampaignTrackingService> zziB = this.zziB();
        zzx.zzz(zziB);
        final Intent intent2 = new Intent(context, (Class)zziB);
        intent2.putExtra("referrer", stringExtra);
        synchronized (CampaignTrackingReceiver.zzqy) {
            context.startService(intent2);
            if (!zzZ) {
                return;
            }
        }
        while (true) {
            try {
                if (CampaignTrackingReceiver.zzOM == null) {
                    (CampaignTrackingReceiver.zzOM = new zzrp(context, 1, "Analytics campaign WakeLock")).setReferenceCounted(false);
                }
                CampaignTrackingReceiver.zzOM.acquire(1000L);
            }
            // monitorexit(o)
            catch (SecurityException ex) {
                zzjm.zzbg("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                continue;
            }
            break;
        }
    }
    
    protected void zzaV(final String s) {
    }
    
    protected Class<? extends CampaignTrackingService> zziB() {
        return CampaignTrackingService.class;
    }
}
