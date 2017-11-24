package com.facebook.internal;

import android.graphics.*;
import android.webkit.*;
import android.net.http.*;
import com.facebook.*;
import android.net.*;
import android.content.*;
import android.os.*;

private class DialogWebViewClient extends WebViewClient
{
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        if (!WebDialog.access$200(WebDialog.this)) {
            WebDialog.access$300(WebDialog.this).dismiss();
        }
        WebDialog.access$400(WebDialog.this).setBackgroundColor(0);
        WebDialog.access$500(WebDialog.this).setVisibility(0);
        WebDialog.access$600(WebDialog.this).setVisibility(0);
        WebDialog.access$702(WebDialog.this, true);
    }
    
    public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
        Utility.logd("FacebookSDK.WebDialog", "Webview loading URL: " + s);
        super.onPageStarted(webView, s, bitmap);
        if (!WebDialog.access$200(WebDialog.this)) {
            WebDialog.access$300(WebDialog.this).show();
        }
    }
    
    public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        super.onReceivedError(webView, n, s, s2);
        WebDialog.this.sendErrorToListener(new FacebookDialogException(s, n, s2));
    }
    
    public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        sslErrorHandler.cancel();
        WebDialog.this.sendErrorToListener(new FacebookDialogException(null, -11, null));
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        Utility.logd("FacebookSDK.WebDialog", "Redirect URL: " + s);
        if (s.startsWith(WebDialog.access$100(WebDialog.this))) {
            final Bundle responseUri = WebDialog.this.parseResponseUri(s);
            String s2 = responseUri.getString("error");
            if (s2 == null) {
                s2 = responseUri.getString("error_type");
            }
            String s3 = responseUri.getString("error_msg");
            if (s3 == null) {
                s3 = responseUri.getString("error_message");
            }
            if (s3 == null) {
                s3 = responseUri.getString("error_description");
            }
            final String string = responseUri.getString("error_code");
            int int1 = -1;
        Label_0139:
            while (true) {
                if (Utility.isNullOrEmpty(string)) {
                    break Label_0139;
                }
                while (true) {
                    try {
                        int1 = Integer.parseInt(string);
                        if (Utility.isNullOrEmpty(s2) && Utility.isNullOrEmpty(s3) && int1 == -1) {
                            WebDialog.this.sendSuccessToListener(responseUri);
                            return true;
                        }
                    }
                    catch (NumberFormatException ex) {
                        int1 = -1;
                        continue Label_0139;
                    }
                    if (s2 != null && (s2.equals("access_denied") || s2.equals("OAuthAccessDeniedException"))) {
                        WebDialog.this.cancel();
                        return true;
                    }
                    if (int1 == 4201) {
                        WebDialog.this.cancel();
                        return true;
                    }
                    WebDialog.this.sendErrorToListener(new FacebookServiceException(new FacebookRequestError(int1, s2, s3), s3));
                    return true;
                }
                break;
            }
        }
        else {
            if (s.startsWith("fbconnect://cancel")) {
                WebDialog.this.cancel();
                return true;
            }
            if (s.contains("touch")) {
                return false;
            }
            try {
                WebDialog.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                return true;
            }
            catch (ActivityNotFoundException ex2) {
                return false;
            }
        }
    }
}
