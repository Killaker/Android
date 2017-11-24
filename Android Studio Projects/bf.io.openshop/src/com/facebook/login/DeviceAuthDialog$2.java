package com.facebook.login;

import com.facebook.*;
import org.json.*;

class DeviceAuthDialog$2 implements Callback {
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        if (graphResponse.getError() != null) {
            DeviceAuthDialog.access$100(DeviceAuthDialog.this, graphResponse.getError().getException());
            return;
        }
        final JSONObject jsonObject = graphResponse.getJSONObject();
        final RequestState requestState = new RequestState();
        try {
            requestState.setUserCode(jsonObject.getString("user_code"));
            requestState.setRequestCode(jsonObject.getString("code"));
            requestState.setInterval(jsonObject.getLong("interval"));
            DeviceAuthDialog.access$200(DeviceAuthDialog.this, requestState);
        }
        catch (JSONException ex) {
            DeviceAuthDialog.access$100(DeviceAuthDialog.this, new FacebookException((Throwable)ex));
        }
    }
}