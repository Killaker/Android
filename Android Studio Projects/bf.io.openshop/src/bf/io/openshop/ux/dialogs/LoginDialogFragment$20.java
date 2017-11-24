package bf.io.openshop.ux.dialogs;

import com.facebook.login.*;
import org.json.*;
import com.facebook.*;
import timber.log.*;

class LoginDialogFragment$20 implements GraphJSONObjectCallback {
    final /* synthetic */ LoginResult val$loginResult;
    
    @Override
    public void onCompleted(final JSONObject jsonObject, final GraphResponse graphResponse) {
        if (graphResponse != null && graphResponse.getError() == null) {
            LoginDialogFragment.this.verifyUserOnApi(jsonObject, this.val$loginResult.getAccessToken());
            return;
        }
        Timber.e("Error on receiving user profile information.", new Object[0]);
        if (graphResponse != null && graphResponse.getError() != null) {
            Timber.e(new RuntimeException(), "Error:" + graphResponse.getError().toString(), new Object[0]);
        }
        LoginDialogFragment.access$900(LoginDialogFragment.this, LoginDialogFragment.this.getString(2131230877), true);
    }
}