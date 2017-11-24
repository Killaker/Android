package com.facebook.login;

import android.app.*;
import com.facebook.internal.*;
import android.content.*;

private static class ActivityStartActivityDelegate implements StartActivityDelegate
{
    private final Activity activity;
    
    ActivityStartActivityDelegate(final Activity activity) {
        Validate.notNull(activity, "activity");
        this.activity = activity;
    }
    
    @Override
    public Activity getActivityContext() {
        return this.activity;
    }
    
    @Override
    public void startActivityForResult(final Intent intent, final int n) {
        this.activity.startActivityForResult(intent, n);
    }
}
