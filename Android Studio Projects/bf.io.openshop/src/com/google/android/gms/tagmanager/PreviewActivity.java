package com.google.android.gms.tagmanager;

import android.app.*;
import android.os.*;
import android.net.*;
import android.content.*;

public class PreviewActivity extends Activity
{
    private void zzm(final String title, final String message, final String s) {
        final AlertDialog create = new AlertDialog$Builder((Context)this).create();
        create.setTitle((CharSequence)title);
        create.setMessage((CharSequence)message);
        create.setButton(-1, (CharSequence)s, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        create.show();
    }
    
    public void onCreate(final Bundle bundle) {
        try {
            super.onCreate(bundle);
            zzbg.zzaJ("Preview activity");
            final Uri data = this.getIntent().getData();
            if (!TagManager.getInstance((Context)this).zzp(data)) {
                final String string = "Cannot preview the app with the uri: " + data + ". Launching current version instead.";
                zzbg.zzaK(string);
                this.zzm("Preview failure", string, "Continue");
            }
            final Intent launchIntentForPackage = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
            if (launchIntentForPackage != null) {
                zzbg.zzaJ("Invoke the launch activity for package name: " + this.getPackageName());
                this.startActivity(launchIntentForPackage);
                return;
            }
            zzbg.zzaJ("No launch activity found for package name: " + this.getPackageName());
        }
        catch (Exception ex) {
            zzbg.e("Calling preview threw an exception: " + ex.getMessage());
        }
    }
}
