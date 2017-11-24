package com.google.android.gms.measurement;

import com.google.android.gms.common.api.*;
import android.content.*;
import com.google.android.gms.*;
import android.text.*;
import android.content.res.*;
import com.google.android.gms.common.internal.*;

public final class zza
{
    private static volatile zza zzaUe;
    private final String zzaUa;
    private final Status zzaUb;
    private final boolean zzaUc;
    private final boolean zzaUd;
    
    zza(final Context context) {
        boolean b = true;
        final Resources resources = context.getResources();
        final String resourcePackageName = resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
        final int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resourcePackageName);
        if (identifier != 0) {
            final boolean b2 = resources.getInteger(identifier) != 0 && b;
            if (b2) {
                b = false;
            }
            this.zzaUd = b;
            b = b2;
        }
        else {
            this.zzaUd = false;
        }
        this.zzaUc = b;
        final int identifier2 = resources.getIdentifier("google_app_id", "string", resourcePackageName);
        if (identifier2 == 0) {
            if (this.zzaUc) {
                this.zzaUb = new Status(10, "Missing an expected resource: 'R.string.google_app_id' for initializing Google services.  Possible causes are missing google-services.json or com.google.gms.google-services gradle plugin.");
            }
            else {
                this.zzaUb = Status.zzagC;
            }
            this.zzaUa = null;
            return;
        }
        final String string = resources.getString(identifier2);
        if (TextUtils.isEmpty((CharSequence)string)) {
            if (this.zzaUc) {
                this.zzaUb = new Status(10, "The resource 'R.string.google_app_id' is invalid, expected an app  identifier and found: '" + string + "'.");
            }
            else {
                this.zzaUb = Status.zzagC;
            }
            this.zzaUa = null;
            return;
        }
        this.zzaUa = string;
        this.zzaUb = Status.zzagC;
    }
    
    zza(final Context context, final String zzaUa, final boolean zzaUc) {
        this.zzaUa = zzaUa;
        this.zzaUb = Status.zzagC;
        this.zzaUc = zzaUc;
        this.zzaUd = !zzaUc;
    }
    
    public static String zzAp() {
        if (zza.zzaUe == null) {
            synchronized (zza.class) {
                if (zza.zzaUe == null) {
                    throw new IllegalStateException("Initialize must be called before getGoogleAppId.");
                }
            }
        }
        // monitorexit(zza.class)
        return zza.zzaUe.zzAq();
    }
    
    public static boolean zzAr() {
        if (zza.zzaUe == null) {
            synchronized (zza.class) {
                if (zza.zzaUe == null) {
                    throw new IllegalStateException("Initialize must be called before isMeasurementEnabled.");
                }
            }
        }
        // monitorexit(zza.class)
        return zza.zzaUe.zzAt();
    }
    
    public static boolean zzAs() {
        if (zza.zzaUe == null) {
            synchronized (zza.class) {
                if (zza.zzaUe == null) {
                    throw new IllegalStateException("Initialize must be called before isMeasurementExplicitlyDisabled.");
                }
            }
        }
        // monitorexit(zza.class)
        return zza.zzaUe.zzaUd;
    }
    
    public static Status zzaR(final Context context) {
        zzx.zzb(context, "Context must not be null.");
        Label_0036: {
            if (zza.zzaUe != null) {
                break Label_0036;
            }
            synchronized (zza.class) {
                if (zza.zzaUe == null) {
                    zza.zzaUe = new zza(context);
                }
                // monitorexit(zza.class)
                return zza.zzaUe.zzaUb;
            }
        }
    }
    
    public static Status zzb(final Context context, final String s, final boolean b) {
        zzx.zzb(context, "Context must not be null.");
        zzx.zzh(s, "App ID must be nonempty.");
        synchronized (zza.class) {
            if (zza.zzaUe != null) {
                return zza.zzaUe.zzeu(s);
            }
            zza.zzaUe = new zza(context, s, b);
            // monitorexit(zza.class)
            return zza.zzaUe.zzaUb;
        }
    }
    
    String zzAq() {
        return this.zzaUa;
    }
    
    boolean zzAt() {
        return this.zzaUb.isSuccess() && this.zzaUc;
    }
    
    Status zzeu(final String s) {
        if (this.zzaUa != null && !this.zzaUa.equals(s)) {
            return new Status(10, "Initialize was called with two different Google App IDs.  Only the first app ID will be used: '" + this.zzaUa + "'.");
        }
        return Status.zzagC;
    }
}
