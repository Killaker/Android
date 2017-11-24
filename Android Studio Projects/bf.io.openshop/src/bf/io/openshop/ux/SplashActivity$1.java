package bf.io.openshop.ux;

import com.facebook.applinks.*;
import timber.log.*;
import bf.io.openshop.utils.*;

class SplashActivity$1 implements CompletionHandler {
    @Override
    public void onDeferredAppLinkDataFetched(final AppLinkData appLinkData) {
        if (appLinkData == null) {
            return;
        }
        try {
            final String string = appLinkData.getTargetUri().toString();
            if (string != null && !string.isEmpty()) {
                Timber.e("TargetUrl: " + string, new Object[0]);
                Analytics.setCampaignUriString(string);
            }
        }
        catch (Exception ex) {
            Timber.e(ex, "AppLinkData exception", new Object[0]);
        }
    }
}