package com.facebook.internal;

import android.app.*;
import android.os.*;
import java.util.*;
import android.widget.*;
import android.annotation.*;
import android.net.*;
import android.util.*;
import android.view.*;
import android.graphics.*;
import android.webkit.*;
import android.net.http.*;
import com.facebook.*;
import android.content.*;

public class WebDialog extends Dialog
{
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int BACKGROUND_GRAY = -872415232;
    static final String CANCEL_URI = "fbconnect://cancel";
    public static final int DEFAULT_THEME = 16973840;
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    private static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
    private static final int MAX_PADDING_SCREEN_WIDTH = 800;
    private static final double MIN_SCALE_FACTOR = 0.5;
    private static final int NO_PADDING_SCREEN_HEIGHT = 800;
    private static final int NO_PADDING_SCREEN_WIDTH = 480;
    static final String REDIRECT_URI = "fbconnect://success";
    private FrameLayout contentFrameLayout;
    private ImageView crossImageView;
    private String expectedRedirectUrl;
    private boolean isDetached;
    private boolean isPageFinished;
    private boolean listenerCalled;
    private OnCompleteListener onCompleteListener;
    private ProgressDialog spinner;
    private String url;
    private WebView webView;
    
    public WebDialog(final Context context, final String s) {
        this(context, s, FacebookSdk.getWebDialogTheme());
    }
    
    public WebDialog(final Context context, final String url, int webDialogTheme) {
        if (webDialogTheme == 0) {
            webDialogTheme = FacebookSdk.getWebDialogTheme();
        }
        super(context, webDialogTheme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        this.url = url;
    }
    
    public WebDialog(final Context context, final String s, Bundle bundle, int webDialogTheme, final OnCompleteListener onCompleteListener) {
        if (webDialogTheme == 0) {
            webDialogTheme = FacebookSdk.getWebDialogTheme();
        }
        super(context, webDialogTheme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("redirect_uri", "fbconnect://success");
        bundle.putString("display", "touch");
        bundle.putString("sdk", String.format(Locale.ROOT, "android-%s", FacebookSdk.getSdkVersion()));
        this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), ServerProtocol.getAPIVersion() + "/" + "dialog/" + s, bundle).toString();
        this.onCompleteListener = onCompleteListener;
    }
    
    private void createCrossImage() {
        (this.crossImageView = new ImageView(this.getContext())).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                WebDialog.this.cancel();
            }
        });
        this.crossImageView.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }
    
    private int getScaledSize(final int n, final float n2, final int n3, final int n4) {
        final int n5 = (int)(n / n2);
        double n6;
        if (n5 <= n3) {
            n6 = 1.0;
        }
        else if (n5 >= n4) {
            n6 = 0.5;
        }
        else {
            n6 = 0.5 + 0.5 * ((n4 - n5) / (n4 - n3));
        }
        return (int)(n6 * n);
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    private void setUpWebView(final int n) {
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        (this.webView = new WebView(this.getContext().getApplicationContext()) {
            public void onWindowFocusChanged(final boolean b) {
                try {
                    super.onWindowFocusChanged(b);
                }
                catch (NullPointerException ex) {}
            }
        }).setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient((WebViewClient)new DialogWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        this.webView.setFocusable(true);
        this.webView.setFocusableInTouchMode(true);
        this.webView.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (!view.hasFocus()) {
                    view.requestFocus();
                }
                return false;
            }
        });
        linearLayout.setPadding(n, n, n, n);
        linearLayout.addView((View)this.webView);
        linearLayout.setBackgroundColor(-872415232);
        this.contentFrameLayout.addView((View)linearLayout);
    }
    
    public void cancel() {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.sendErrorToListener(new FacebookOperationCanceledException());
        }
    }
    
    public void dismiss() {
        if (this.webView != null) {
            this.webView.stopLoading();
        }
        if (!this.isDetached && this.spinner != null && this.spinner.isShowing()) {
            this.spinner.dismiss();
        }
        super.dismiss();
    }
    
    public OnCompleteListener getOnCompleteListener() {
        return this.onCompleteListener;
    }
    
    protected WebView getWebView() {
        return this.webView;
    }
    
    protected boolean isListenerCalled() {
        return this.listenerCalled;
    }
    
    protected boolean isPageFinished() {
        return this.isPageFinished;
    }
    
    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        (this.spinner = new ProgressDialog(this.getContext())).requestWindowFeature(1);
        this.spinner.setMessage((CharSequence)this.getContext().getString(R.string.com_facebook_loading));
        this.spinner.setCanceledOnTouchOutside(false);
        this.spinner.setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                WebDialog.this.cancel();
            }
        });
        this.requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(this.getContext());
        this.resize();
        this.getWindow().setGravity(17);
        this.getWindow().setSoftInputMode(16);
        this.createCrossImage();
        this.setUpWebView(1 + this.crossImageView.getDrawable().getIntrinsicWidth() / 2);
        this.contentFrameLayout.addView((View)this.crossImageView, new ViewGroup$LayoutParams(-2, -2));
        this.setContentView((View)this.contentFrameLayout);
    }
    
    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.cancel();
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    protected void onStart() {
        super.onStart();
        this.resize();
    }
    
    protected Bundle parseResponseUri(final String s) {
        final Uri parse = Uri.parse(s);
        final Bundle urlQueryString = Utility.parseUrlQueryString(parse.getQuery());
        urlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
        return urlQueryString;
    }
    
    public void resize() {
        final Display defaultDisplay = ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int n;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n = displayMetrics.widthPixels;
        }
        else {
            n = displayMetrics.heightPixels;
        }
        int n2;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n2 = displayMetrics.heightPixels;
        }
        else {
            n2 = displayMetrics.widthPixels;
        }
        this.getWindow().setLayout(Math.min(this.getScaledSize(n, displayMetrics.density, 480, 800), displayMetrics.widthPixels), Math.min(this.getScaledSize(n2, displayMetrics.density, 800, 1280), displayMetrics.heightPixels));
    }
    
    protected void sendErrorToListener(final Throwable t) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            FacebookException ex;
            if (t instanceof FacebookException) {
                ex = (FacebookException)t;
            }
            else {
                ex = new FacebookException(t);
            }
            this.onCompleteListener.onComplete(null, ex);
            this.dismiss();
        }
    }
    
    protected void sendSuccessToListener(final Bundle bundle) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.onComplete(bundle, null);
            this.dismiss();
        }
    }
    
    protected void setExpectedRedirectUrl(final String expectedRedirectUrl) {
        this.expectedRedirectUrl = expectedRedirectUrl;
    }
    
    public void setOnCompleteListener(final OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }
    
    public static class Builder
    {
        private AccessToken accessToken;
        private String action;
        private String applicationId;
        private Context context;
        private OnCompleteListener listener;
        private Bundle parameters;
        private int theme;
        
        public Builder(final Context context, final String s, final Bundle bundle) {
            this.accessToken = AccessToken.getCurrentAccessToken();
            if (this.accessToken == null) {
                final String metadataApplicationId = Utility.getMetadataApplicationId(context);
                if (metadataApplicationId == null) {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
                this.applicationId = metadataApplicationId;
            }
            this.finishInit(context, s, bundle);
        }
        
        public Builder(final Context context, String metadataApplicationId, final String s, final Bundle bundle) {
            if (metadataApplicationId == null) {
                metadataApplicationId = Utility.getMetadataApplicationId(context);
            }
            Validate.notNullOrEmpty(metadataApplicationId, "applicationId");
            this.applicationId = metadataApplicationId;
            this.finishInit(context, s, bundle);
        }
        
        private void finishInit(final Context context, final String action, final Bundle parameters) {
            this.context = context;
            this.action = action;
            if (parameters != null) {
                this.parameters = parameters;
                return;
            }
            this.parameters = new Bundle();
        }
        
        public WebDialog build() {
            if (this.accessToken != null) {
                this.parameters.putString("app_id", this.accessToken.getApplicationId());
                this.parameters.putString("access_token", this.accessToken.getToken());
            }
            else {
                this.parameters.putString("app_id", this.applicationId);
            }
            return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
        }
        
        public String getApplicationId() {
            return this.applicationId;
        }
        
        public Context getContext() {
            return this.context;
        }
        
        public OnCompleteListener getListener() {
            return this.listener;
        }
        
        public Bundle getParameters() {
            return this.parameters;
        }
        
        public int getTheme() {
            return this.theme;
        }
        
        public Builder setOnCompleteListener(final OnCompleteListener listener) {
            this.listener = listener;
            return this;
        }
        
        public Builder setTheme(final int theme) {
            this.theme = theme;
            return this;
        }
    }
    
    private class DialogWebViewClient extends WebViewClient
    {
        public void onPageFinished(final WebView webView, final String s) {
            super.onPageFinished(webView, s);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.dismiss();
            }
            WebDialog.this.contentFrameLayout.setBackgroundColor(0);
            WebDialog.this.webView.setVisibility(0);
            WebDialog.this.crossImageView.setVisibility(0);
            WebDialog.this.isPageFinished = true;
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            Utility.logd("FacebookSDK.WebDialog", "Webview loading URL: " + s);
            super.onPageStarted(webView, s, bitmap);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.show();
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
            if (s.startsWith(WebDialog.this.expectedRedirectUrl)) {
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
    
    public interface OnCompleteListener
    {
        void onComplete(final Bundle p0, final FacebookException p1);
    }
}
