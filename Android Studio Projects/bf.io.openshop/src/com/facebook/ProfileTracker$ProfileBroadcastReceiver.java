package com.facebook;

import android.content.*;

private class ProfileBroadcastReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        if ("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED".equals(intent.getAction())) {
            ProfileTracker.this.onCurrentProfileChanged((Profile)intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_PROFILE"), (Profile)intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_PROFILE"));
        }
    }
}
