package com.facebook.applinks;

import android.net.*;
import bolts.*;
import com.facebook.*;
import java.util.*;
import org.json.*;

class FacebookAppLinkResolver$2 implements Callback {
    final /* synthetic */ Map val$appLinkResults;
    final /* synthetic */ Task.TaskCompletionSource val$taskCompletionSource;
    final /* synthetic */ HashSet val$urisToRequest;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            this.val$taskCompletionSource.setError(error.getException());
            return;
        }
        final JSONObject jsonObject = graphResponse.getJSONObject();
        if (jsonObject == null) {
            this.val$taskCompletionSource.setResult((TResult)this.val$appLinkResults);
            return;
        }
    Label_0248:
        for (final Uri uri : this.val$urisToRequest) {
            if (jsonObject.has(uri.toString())) {
                while (true) {
                    while (true) {
                        int n = 0;
                        Label_0260: {
                            try {
                                final JSONObject jsonObject2 = jsonObject.getJSONObject(uri.toString()).getJSONObject("app_links");
                                final JSONArray jsonArray = jsonObject2.getJSONArray("android");
                                final int length = jsonArray.length();
                                final ArrayList list = new ArrayList<AppLink.Target>(length);
                                n = 0;
                                if (n < length) {
                                    final AppLink.Target access$000 = FacebookAppLinkResolver.access$000(jsonArray.getJSONObject(n));
                                    if (access$000 != null) {
                                        list.add(access$000);
                                    }
                                    break Label_0260;
                                }
                                else {
                                    final AppLink appLink = new AppLink(uri, (List<AppLink.Target>)list, FacebookAppLinkResolver.access$100(uri, jsonObject2));
                                    this.val$appLinkResults.put(uri, appLink);
                                    synchronized (FacebookAppLinkResolver.access$200(FacebookAppLinkResolver.this)) {
                                        FacebookAppLinkResolver.access$200(FacebookAppLinkResolver.this).put(uri, appLink);
                                    }
                                }
                            }
                            catch (JSONException ex) {
                                break;
                            }
                            break Label_0248;
                        }
                        ++n;
                        continue;
                    }
                }
            }
        }
        this.val$taskCompletionSource.setResult((TResult)this.val$appLinkResults);
    }
}