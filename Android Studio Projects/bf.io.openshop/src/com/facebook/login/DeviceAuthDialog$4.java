package com.facebook.login;

import org.json.*;
import com.facebook.*;

class DeviceAuthDialog$4 implements Callback {
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        if (DeviceAuthDialog.access$400(DeviceAuthDialog.this).get()) {
            return;
        }
        final FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            final String errorMessage = error.getErrorMessage();
            if (errorMessage.equals("authorization_pending") || errorMessage.equals("slow_down")) {
                DeviceAuthDialog.access$500(DeviceAuthDialog.this);
                return;
            }
            if (errorMessage.equals("authorization_declined") || errorMessage.equals("code_expired")) {
                DeviceAuthDialog.access$000(DeviceAuthDialog.this);
                return;
            }
            DeviceAuthDialog.access$100(DeviceAuthDialog.this, graphResponse.getError().getException());
        }
        else {
            try {
                DeviceAuthDialog.access$600(DeviceAuthDialog.this, graphResponse.getJSONObject().getString("access_token"));
            }
            catch (JSONException ex) {
                DeviceAuthDialog.access$100(DeviceAuthDialog.this, new FacebookException((Throwable)ex));
            }
        }
    }
}