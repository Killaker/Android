package com.facebook.internal;

import org.json.*;
import android.content.*;

static final class Utility$1 implements Runnable {
    final /* synthetic */ String val$applicationId;
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$settingsKey;
    
    @Override
    public void run() {
        final SharedPreferences sharedPreferences = this.val$context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
        final String string = sharedPreferences.getString(this.val$settingsKey, (String)null);
        Label_0059: {
            if (Utility.isNullOrEmpty(string)) {
                break Label_0059;
            }
            while (true) {
                try {
                    final JSONObject jsonObject = new JSONObject(string);
                    if (jsonObject != null) {
                        Utility.access$000(this.val$applicationId, jsonObject);
                    }
                    final JSONObject access$100 = Utility.access$100(this.val$applicationId);
                    if (access$100 != null) {
                        Utility.access$000(this.val$applicationId, access$100);
                        sharedPreferences.edit().putString(this.val$settingsKey, access$100.toString()).apply();
                    }
                    Utility.access$200().set(false);
                }
                catch (JSONException ex) {
                    Utility.logd("FacebookSDK", (Exception)ex);
                    final JSONObject jsonObject = null;
                    continue;
                }
                break;
            }
        }
    }
}