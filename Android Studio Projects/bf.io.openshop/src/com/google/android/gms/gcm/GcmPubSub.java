package com.google.android.gms.gcm;

import java.util.regex.*;
import com.google.android.gms.iid.*;
import android.content.*;
import android.os.*;
import java.io.*;
import android.support.annotation.*;

public class GcmPubSub
{
    private static GcmPubSub zzaLE;
    private static final Pattern zzaLG;
    private InstanceID zzaLF;
    
    static {
        zzaLG = Pattern.compile("/topics/[a-zA-Z0-9-_.~%]{1,900}");
    }
    
    private GcmPubSub(final Context context) {
        this.zzaLF = InstanceID.getInstance(context);
    }
    
    public static GcmPubSub getInstance(final Context context) {
        synchronized (GcmPubSub.class) {
            if (GcmPubSub.zzaLE == null) {
                GcmPubSub.zzaLE = new GcmPubSub(context);
            }
            return GcmPubSub.zzaLE;
        }
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void subscribe(final String s, final String s2, Bundle bundle) throws IOException {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Invalid appInstanceToken: " + s);
        }
        if (s2 == null || !GcmPubSub.zzaLG.matcher(s2).matches()) {
            throw new IllegalArgumentException("Invalid topic name: " + s2);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("gcm.topic", s2);
        this.zzaLF.getToken(s, s2, bundle);
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void unsubscribe(final String s, final String s2) throws IOException {
        final Bundle bundle = new Bundle();
        bundle.putString("gcm.topic", s2);
        this.zzaLF.zzb(s, s2, bundle);
    }
}
