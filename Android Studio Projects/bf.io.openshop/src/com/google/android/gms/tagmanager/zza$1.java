package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.*;
import java.io.*;
import com.google.android.gms.common.*;

class zza$1 implements zza {
    @Override
    public AdvertisingIdClient.Info zzFV() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(zza.zza(zza.this));
        }
        catch (IllegalStateException ex) {
            zzbg.zzd("IllegalStateException getting Advertising Id Info", ex);
            return null;
        }
        catch (GooglePlayServicesRepairableException ex2) {
            zzbg.zzd("GooglePlayServicesRepairableException getting Advertising Id Info", ex2);
            return null;
        }
        catch (IOException ex3) {
            zzbg.zzd("IOException getting Ad Id Info", ex3);
            return null;
        }
        catch (GooglePlayServicesNotAvailableException ex4) {
            zzbg.zzd("GooglePlayServicesNotAvailableException getting Advertising Id Info", ex4);
            return null;
        }
        catch (Exception ex5) {
            zzbg.zzd("Unknown exception. Could not get the Advertising Id Info.", ex5);
            return null;
        }
    }
}