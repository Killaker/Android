package com.google.android.gms.tagmanager;

import android.os.*;
import android.content.*;
import android.annotation.*;

class zzcv
{
    static void zza(final SharedPreferences$Editor sharedPreferences$Editor) {
        if (Build$VERSION.SDK_INT >= 9) {
            sharedPreferences$Editor.apply();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                sharedPreferences$Editor.commit();
            }
        }).start();
    }
    
    @SuppressLint({ "CommitPrefEdits" })
    static void zzb(final Context context, final String s, final String s2, final String s3) {
        final SharedPreferences$Editor edit = context.getSharedPreferences(s, 0).edit();
        edit.putString(s2, s3);
        zza(edit);
    }
}
