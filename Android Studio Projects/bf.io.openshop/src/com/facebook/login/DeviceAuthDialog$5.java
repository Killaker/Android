package com.facebook.login;

import com.facebook.internal.*;
import java.util.*;
import com.facebook.*;
import org.json.*;

class DeviceAuthDialog$5 implements Callback {
    final /* synthetic */ String val$accessToken;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        if (DeviceAuthDialog.access$400(DeviceAuthDialog.this).get()) {
            return;
        }
        if (graphResponse.getError() != null) {
            DeviceAuthDialog.access$100(DeviceAuthDialog.this, graphResponse.getError().getException());
            return;
        }
        try {
            final JSONObject jsonObject = graphResponse.getJSONObject();
            final String string = jsonObject.getString("id");
            final Utility.PermissionsPair handlePermissionResponse = Utility.handlePermissionResponse(jsonObject);
            DeviceAuthDialog.access$700(DeviceAuthDialog.this).onSuccess(this.val$accessToken, FacebookSdk.getApplicationId(), string, handlePermissionResponse.getGrantedPermissions(), handlePermissionResponse.getDeclinedPermissions(), AccessTokenSource.DEVICE_AUTH, null, null);
            DeviceAuthDialog.access$800(DeviceAuthDialog.this).dismiss();
        }
        catch (JSONException ex) {
            DeviceAuthDialog.access$100(DeviceAuthDialog.this, new FacebookException((Throwable)ex));
        }
    }
}