package com.google.android.gms.common;

import android.support.annotation.*;
import android.widget.*;
import android.util.*;
import android.view.*;
import com.google.android.gms.*;
import android.app.*;
import android.content.*;

public class GoogleApiAvailability extends zzc
{
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailability zzafE;
    
    static {
        zzafE = new GoogleApiAvailability();
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
    
    public static GoogleApiAvailability getInstance() {
        return GoogleApiAvailability.zzafE;
    }
    
    public Dialog getErrorDialog(final Activity activity, final int n, final int n2) {
        return GooglePlayServicesUtil.getErrorDialog(n, activity, n2);
    }
    
    public Dialog getErrorDialog(final Activity activity, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return GooglePlayServicesUtil.getErrorDialog(n, activity, n2, dialogInterface$OnCancelListener);
    }
    
    @Nullable
    @Override
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final int n, final int n2) {
        return super.getErrorResolutionPendingIntent(context, n, n2);
    }
    
    @Override
    public final String getErrorString(final int n) {
        return super.getErrorString(n);
    }
    
    @Nullable
    @Override
    public String getOpenSourceSoftwareLicenseInfo(final Context context) {
        return super.getOpenSourceSoftwareLicenseInfo(context);
    }
    
    @Override
    public int isGooglePlayServicesAvailable(final Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }
    
    @Override
    public final boolean isUserResolvableError(final int n) {
        return super.isUserResolvableError(n);
    }
    
    public boolean showErrorDialogFragment(final Activity activity, final int n, final int n2) {
        return GooglePlayServicesUtil.showErrorDialogFragment(n, activity, n2);
    }
    
    public boolean showErrorDialogFragment(final Activity activity, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return GooglePlayServicesUtil.showErrorDialogFragment(n, activity, n2, dialogInterface$OnCancelListener);
    }
    
    public void showErrorNotification(final Context context, final int n) {
        GooglePlayServicesUtil.showErrorNotification(n, context);
    }
    
    public Dialog zza(final Activity activity, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final ProgressBar view = new ProgressBar((Context)activity, (AttributeSet)null, 16842874);
        view.setIndeterminate(true);
        view.setVisibility(0);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)activity);
        alertDialog$Builder.setView((View)view);
        alertDialog$Builder.setMessage((CharSequence)activity.getResources().getString(R.string.common_google_play_services_updating_text, new Object[] { zze.zzao((Context)activity) }));
        alertDialog$Builder.setTitle(R.string.common_google_play_services_updating_title);
        alertDialog$Builder.setPositiveButton((CharSequence)"", (DialogInterface$OnClickListener)null);
        final AlertDialog create = alertDialog$Builder.create();
        GooglePlayServicesUtil.zza(activity, dialogInterface$OnCancelListener, "GooglePlayServicesUpdatingDialog", (Dialog)create);
        return (Dialog)create;
    }
    
    @Nullable
    @Override
    public PendingIntent zza(final Context context, final int n, final int n2, @Nullable final String s) {
        return super.zza(context, n, n2, s);
    }
    
    @Nullable
    @Override
    public Intent zza(final Context context, final int n, @Nullable final String s) {
        return super.zza(context, n, s);
    }
    
    @Override
    public int zzaj(final Context context) {
        return super.zzaj(context);
    }
    
    @Deprecated
    @Nullable
    @Override
    public Intent zzbu(final int n) {
        return super.zzbu(n);
    }
    
    @Override
    public boolean zzd(final Context context, final int n) {
        return super.zzd(context, n);
    }
}
