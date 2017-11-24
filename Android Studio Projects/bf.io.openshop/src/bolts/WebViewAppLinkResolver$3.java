package bolts;

import java.util.concurrent.*;
import android.net.*;
import java.net.*;

class WebViewAppLinkResolver$3 implements Callable<Void> {
    final /* synthetic */ Capture val$content;
    final /* synthetic */ Capture val$contentType;
    final /* synthetic */ Uri val$url;
    
    @Override
    public Void call() throws Exception {
        URL url = new URL(this.val$url.toString());
        URLConnection openConnection = null;
        while (url != null) {
            openConnection = url.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                ((HttpURLConnection)openConnection).setInstanceFollowRedirects(true);
            }
            openConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
            openConnection.connect();
            if (openConnection instanceof HttpURLConnection) {
                final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
                if (httpURLConnection.getResponseCode() >= 300 && httpURLConnection.getResponseCode() < 400) {
                    url = new URL(httpURLConnection.getHeaderField("Location"));
                    httpURLConnection.disconnect();
                }
                else {
                    url = null;
                }
            }
            else {
                url = null;
            }
        }
        try {
            this.val$content.set(WebViewAppLinkResolver.access$300(openConnection));
            this.val$contentType.set(openConnection.getContentType());
            return null;
        }
        finally {
            if (openConnection instanceof HttpURLConnection) {
                ((HttpURLConnection)openConnection).disconnect();
            }
        }
    }
}