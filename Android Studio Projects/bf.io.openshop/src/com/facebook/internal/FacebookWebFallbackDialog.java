package com.facebook.internal;

import android.content.*;
import android.webkit.*;
import android.os.*;

public class FacebookWebFallbackDialog extends WebDialog
{
    private static final int OS_BACK_BUTTON_RESPONSE_TIMEOUT_MILLISECONDS = 1500;
    private static final String TAG;
    private boolean waitingForDialogToClose;
    
    static {
        TAG = FacebookWebFallbackDialog.class.getName();
    }
    
    public FacebookWebFallbackDialog(final Context context, final String s, final String expectedRedirectUrl) {
        super(context, s);
        this.setExpectedRedirectUrl(expectedRedirectUrl);
    }
    
    @Override
    public void cancel() {
        final WebView webView = this.getWebView();
        if (!this.isPageFinished() || this.isListenerCalled() || webView == null || !webView.isShown()) {
            super.cancel();
        }
        else if (!this.waitingForDialogToClose) {
            this.waitingForDialogToClose = true;
            webView.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            new Handler(Looper.getMainLooper()).postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    FacebookWebFallbackDialog.this.cancel();
                }
            }, 1500L);
        }
    }
    
    @Override
    protected Bundle parseResponseUri(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //     4: invokevirtual   android/net/Uri.getQuery:()Ljava/lang/String;
        //     7: invokestatic    com/facebook/internal/Utility.parseUrlQueryString:(Ljava/lang/String;)Landroid/os/Bundle;
        //    10: astore_2       
        //    11: aload_2        
        //    12: ldc             "bridge_args"
        //    14: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    17: astore_3       
        //    18: aload_2        
        //    19: ldc             "bridge_args"
        //    21: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //    24: aload_3        
        //    25: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    28: ifne            48
        //    31: aload_2        
        //    32: ldc             "com.facebook.platform.protocol.BRIDGE_ARGS"
        //    34: new             Lorg/json/JSONObject;
        //    37: dup            
        //    38: aload_3        
        //    39: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    42: invokestatic    com/facebook/internal/BundleJSONConverter.convertToBundle:(Lorg/json/JSONObject;)Landroid/os/Bundle;
        //    45: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //    48: aload_2        
        //    49: ldc             "method_results"
        //    51: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    54: astore          4
        //    56: aload_2        
        //    57: ldc             "method_results"
        //    59: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //    62: aload           4
        //    64: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    67: ifne            100
        //    70: aload           4
        //    72: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    75: ifeq            82
        //    78: ldc             "{}"
        //    80: astore          4
        //    82: aload_2        
        //    83: ldc             "com.facebook.platform.protocol.RESULT_ARGS"
        //    85: new             Lorg/json/JSONObject;
        //    88: dup            
        //    89: aload           4
        //    91: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    94: invokestatic    com/facebook/internal/BundleJSONConverter.convertToBundle:(Lorg/json/JSONObject;)Landroid/os/Bundle;
        //    97: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //   100: aload_2        
        //   101: ldc             "version"
        //   103: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //   106: aload_2        
        //   107: ldc             "com.facebook.platform.protocol.PROTOCOL_VERSION"
        //   109: invokestatic    com/facebook/internal/NativeProtocol.getLatestKnownVersion:()I
        //   112: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   115: aload_2        
        //   116: areturn        
        //   117: astore          6
        //   119: getstatic       com/facebook/internal/FacebookWebFallbackDialog.TAG:Ljava/lang/String;
        //   122: ldc             "Unable to parse bridge_args JSON"
        //   124: aload           6
        //   126: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   129: goto            48
        //   132: astore          5
        //   134: getstatic       com/facebook/internal/FacebookWebFallbackDialog.TAG:Ljava/lang/String;
        //   137: ldc             "Unable to parse bridge_args JSON"
        //   139: aload           5
        //   141: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   144: goto            100
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  31     48     117    132    Lorg/json/JSONException;
        //  82     100    132    147    Lorg/json/JSONException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
