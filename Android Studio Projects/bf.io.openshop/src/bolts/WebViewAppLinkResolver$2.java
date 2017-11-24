package bolts;

import android.net.*;
import org.json.*;
import android.webkit.*;

class WebViewAppLinkResolver$2 implements Continuation<Void, Task<JSONArray>> {
    final /* synthetic */ Capture val$content;
    final /* synthetic */ Capture val$contentType;
    final /* synthetic */ Uri val$url;
    
    @Override
    public Task<JSONArray> then(final Task<Void> task) throws Exception {
        final TaskCompletionSource<JSONArray> taskCompletionSource = new TaskCompletionSource<JSONArray>();
        final WebView webView = new WebView(WebViewAppLinkResolver.access$200(WebViewAppLinkResolver.this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setNetworkAvailable(false);
        webView.setWebViewClient((WebViewClient)new WebViewClient() {
            private boolean loaded = false;
            
            private void runJavaScript(final WebView webView) {
                if (!this.loaded) {
                    this.loaded = true;
                    webView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
                }
            }
            
            public void onLoadResource(final WebView webView, final String s) {
                super.onLoadResource(webView, s);
                this.runJavaScript(webView);
            }
            
            public void onPageFinished(final WebView webView, final String s) {
                super.onPageFinished(webView, s);
                this.runJavaScript(webView);
            }
        });
        webView.addJavascriptInterface((Object)new Object() {
            @JavascriptInterface
            public void setValue(final String s) {
                try {
                    taskCompletionSource.trySetResult(new JSONArray(s));
                }
                catch (JSONException ex) {
                    taskCompletionSource.trySetError((Exception)ex);
                }
            }
        }, "boltsWebViewAppLinkResolverResult");
        final Object value = this.val$contentType.get();
        String s = null;
        if (value != null) {
            s = this.val$contentType.get().split(";")[0];
        }
        webView.loadDataWithBaseURL(this.val$url.toString(), (String)this.val$content.get(), s, (String)null, (String)null);
        return taskCompletionSource.getTask();
    }
}