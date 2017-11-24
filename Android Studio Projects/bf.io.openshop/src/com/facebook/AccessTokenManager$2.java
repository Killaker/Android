package com.facebook;

import java.util.concurrent.atomic.*;
import com.facebook.internal.*;
import java.util.*;
import android.util.*;
import org.json.*;

class AccessTokenManager$2 implements Callback {
    final /* synthetic */ Set val$declinedPermissions;
    final /* synthetic */ Set val$permissions;
    final /* synthetic */ AtomicBoolean val$permissionsCallSucceeded;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final JSONObject jsonObject = graphResponse.getJSONObject();
        if (jsonObject != null) {
            final JSONArray optJSONArray = jsonObject.optJSONArray("data");
            if (optJSONArray != null) {
                this.val$permissionsCallSucceeded.set(true);
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        final String optString = optJSONObject.optString("permission");
                        final String optString2 = optJSONObject.optString("status");
                        if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                            final String lowerCase = optString2.toLowerCase(Locale.US);
                            if (lowerCase.equals("granted")) {
                                this.val$permissions.add(optString);
                            }
                            else if (lowerCase.equals("declined")) {
                                this.val$declinedPermissions.add(optString);
                            }
                            else {
                                Log.w("AccessTokenManager", "Unexpected status: " + lowerCase);
                            }
                        }
                    }
                }
            }
        }
    }
}