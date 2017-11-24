package com.google.android.gms.common;

import android.annotation.*;
import com.google.android.gms.common.internal.*;
import android.content.*;
import android.os.*;
import android.app.*;

@TargetApi(11)
public class ErrorDialogFragment extends DialogFragment
{
    private Dialog mDialog;
    private DialogInterface$OnCancelListener zzafD;
    
    public ErrorDialogFragment() {
        this.mDialog = null;
        this.zzafD = null;
    }
    
    public static ErrorDialogFragment newInstance(final Dialog dialog) {
        return newInstance(dialog, null);
    }
    
    public static ErrorDialogFragment newInstance(final Dialog dialog, final DialogInterface$OnCancelListener zzafD) {
        final ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        final Dialog mDialog = zzx.zzb(dialog, "Cannot display null dialog");
        mDialog.setOnCancelListener((DialogInterface$OnCancelListener)null);
        mDialog.setOnDismissListener((DialogInterface$OnDismissListener)null);
        errorDialogFragment.mDialog = mDialog;
        if (zzafD != null) {
            errorDialogFragment.zzafD = zzafD;
        }
        return errorDialogFragment;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        if (this.zzafD != null) {
            this.zzafD.onCancel(dialogInterface);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.mDialog == null) {
            this.setShowsDialog(false);
        }
        return this.mDialog;
    }
    
    public void show(final FragmentManager fragmentManager, final String s) {
        super.show(fragmentManager, s);
    }
}
