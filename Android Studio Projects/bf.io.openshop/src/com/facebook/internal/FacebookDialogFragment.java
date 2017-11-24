package com.facebook.internal;

import android.app.*;
import android.support.v4.app.*;
import android.content.res.*;
import android.content.*;
import com.facebook.*;
import android.support.annotation.*;
import android.os.*;

public class FacebookDialogFragment extends DialogFragment
{
    public static final String TAG = "FacebookDialogFragment";
    private Dialog dialog;
    
    private void onCompleteWebDialog(final Bundle bundle, final FacebookException ex) {
        final FragmentActivity activity = this.getActivity();
        final Intent protocolResultIntent = NativeProtocol.createProtocolResultIntent(activity.getIntent(), bundle, ex);
        int n;
        if (ex == null) {
            n = -1;
        }
        else {
            n = 0;
        }
        activity.setResult(n, protocolResultIntent);
        activity.finish();
    }
    
    private void onCompleteWebFallbackDialog(Bundle bundle) {
        final FragmentActivity activity = this.getActivity();
        final Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        activity.setResult(-1, intent);
        activity.finish();
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.dialog instanceof WebDialog) {
            ((WebDialog)this.dialog).resize();
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (this.dialog == null) {
            final FragmentActivity activity = this.getActivity();
            final Bundle methodArgumentsFromIntent = NativeProtocol.getMethodArgumentsFromIntent(activity.getIntent());
            WebDialog build;
            if (!methodArgumentsFromIntent.getBoolean("is_fallback", false)) {
                final String string = methodArgumentsFromIntent.getString("action");
                final Bundle bundle2 = methodArgumentsFromIntent.getBundle("params");
                if (Utility.isNullOrEmpty(string)) {
                    Utility.logd("FacebookDialogFragment", "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    return;
                }
                build = new WebDialog.Builder((Context)activity, string, bundle2).setOnCompleteListener(new WebDialog.OnCompleteListener() {
                    @Override
                    public void onComplete(final Bundle bundle, final FacebookException ex) {
                        FacebookDialogFragment.this.onCompleteWebDialog(bundle, ex);
                    }
                }).build();
            }
            else {
                final String string2 = methodArgumentsFromIntent.getString("url");
                if (Utility.isNullOrEmpty(string2)) {
                    Utility.logd("FacebookDialogFragment", "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    return;
                }
                build = new FacebookWebFallbackDialog((Context)activity, string2, String.format("fb%s://bridge/", FacebookSdk.getApplicationId()));
                build.setOnCompleteListener((WebDialog.OnCompleteListener)new WebDialog.OnCompleteListener() {
                    @Override
                    public void onComplete(final Bundle bundle, final FacebookException ex) {
                        FacebookDialogFragment.this.onCompleteWebFallbackDialog(bundle);
                    }
                });
            }
            this.dialog = build;
        }
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.dialog == null) {
            this.onCompleteWebDialog(null, null);
            this.setShowsDialog(false);
        }
        return this.dialog;
    }
    
    @Override
    public void onDestroyView() {
        if (this.getDialog() != null && this.getRetainInstance()) {
            this.getDialog().setDismissMessage((Message)null);
        }
        super.onDestroyView();
    }
    
    public void setDialog(final Dialog dialog) {
        this.dialog = dialog;
    }
}
