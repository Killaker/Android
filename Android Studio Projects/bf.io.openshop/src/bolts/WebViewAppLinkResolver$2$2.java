package bolts;

import org.json.*;
import android.webkit.*;

class WebViewAppLinkResolver$2$2 {
    final /* synthetic */ TaskCompletionSource val$tcs;
    
    @JavascriptInterface
    public void setValue(final String s) {
        try {
            this.val$tcs.trySetResult(new JSONArray(s));
        }
        catch (JSONException ex) {
            this.val$tcs.trySetError((Exception)ex);
        }
    }
}