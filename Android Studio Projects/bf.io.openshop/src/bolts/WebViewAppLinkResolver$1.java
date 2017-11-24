package bolts;

import org.json.*;
import android.net.*;

class WebViewAppLinkResolver$1 implements Continuation<JSONArray, AppLink> {
    final /* synthetic */ Uri val$url;
    
    @Override
    public AppLink then(final Task<JSONArray> task) throws Exception {
        return WebViewAppLinkResolver.access$100(WebViewAppLinkResolver.access$000(task.getResult()), this.val$url);
    }
}