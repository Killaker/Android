package bf.io.openshop.utils;

import android.app.*;
import java.io.*;
import com.google.android.gms.iid.*;
import android.content.*;
import android.os.*;
import timber.log.*;
import bf.io.openshop.*;
import android.support.v4.content.*;

public class MyRegistrationIntentService extends IntentService
{
    private static final String TAG = "GcmRegistrationService";
    private static final String[] TOPICS;
    
    static {
        TOPICS = new String[] { "global" };
    }
    
    public MyRegistrationIntentService() {
        super("GcmRegistrationService");
    }
    
    private boolean sendRegistrationToServer(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/android/volley/toolbox/RequestFuture.newFuture:()Lcom/android/volley/toolbox/RequestFuture;
        //     3: astore_2       
        //     4: new             Lbf/io/openshop/utils/MyRegistrationIntentService$1;
        //     7: dup            
        //     8: aload_0        
        //     9: invokespecial   bf/io/openshop/utils/MyRegistrationIntentService$1.<init>:(Lbf/io/openshop/utils/MyRegistrationIntentService;)V
        //    12: astore_3       
        //    13: new             Lorg/json/JSONObject;
        //    16: dup            
        //    17: invokespecial   org/json/JSONObject.<init>:()V
        //    20: astore          4
        //    22: aload           4
        //    24: ldc             "platform"
        //    26: ldc             "android"
        //    28: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    31: pop            
        //    32: aload           4
        //    34: ldc             "device_token"
        //    36: aload_1        
        //    37: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    40: pop            
        //    41: invokestatic    bf/io/openshop/SettingsMy.getActualShop:()Lbf/io/openshop/entities/Shop;
        //    44: astore          8
        //    46: aload           8
        //    48: ifnull          225
        //    51: getstatic       bf/io/openshop/api/EndPoints.REGISTER_NOTIFICATION:Ljava/lang/String;
        //    54: astore          9
        //    56: iconst_1       
        //    57: anewarray       Ljava/lang/Object;
        //    60: astore          10
        //    62: aload           10
        //    64: iconst_0       
        //    65: aload           8
        //    67: invokevirtual   bf/io/openshop/entities/Shop.getId:()J
        //    70: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    73: aastore        
        //    74: aload           9
        //    76: aload           10
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: astore          11
        //    83: invokestatic    bf/io/openshop/SettingsMy.getActiveUser:()Lbf/io/openshop/entities/User;
        //    86: astore          12
        //    88: aload           12
        //    90: ifnull          197
        //    93: ldc             "GCM registration send: authorized"
        //    95: iconst_0       
        //    96: anewarray       Ljava/lang/Object;
        //    99: invokestatic    timber/log/Timber.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   102: new             Lbf/io/openshop/api/JsonRequest;
        //   105: dup            
        //   106: iconst_1       
        //   107: aload           11
        //   109: aload           4
        //   111: aload_2        
        //   112: aload_3        
        //   113: aconst_null    
        //   114: aload           12
        //   116: invokevirtual   bf/io/openshop/entities/User.getAccessToken:()Ljava/lang/String;
        //   119: invokespecial   bf/io/openshop/api/JsonRequest.<init>:(ILjava/lang/String;Lorg/json/JSONObject;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;Landroid/support/v4/app/FragmentManager;Ljava/lang/String;)V
        //   122: astore          13
        //   124: aload           13
        //   126: invokestatic    bf/io/openshop/MyApplication.getDefaultRetryPolice:()Lcom/android/volley/DefaultRetryPolicy;
        //   129: invokevirtual   bf/io/openshop/api/JsonRequest.setRetryPolicy:(Lcom/android/volley/RetryPolicy;)Lcom/android/volley/Request;
        //   132: pop            
        //   133: aload           13
        //   135: iconst_0       
        //   136: invokevirtual   bf/io/openshop/api/JsonRequest.setShouldCache:(Z)Lcom/android/volley/Request;
        //   139: pop            
        //   140: invokestatic    bf/io/openshop/MyApplication.getInstance:()Lbf/io/openshop/MyApplication;
        //   143: aload           13
        //   145: ldc             "no_cancel_tag"
        //   147: invokevirtual   bf/io/openshop/MyApplication.addToRequestQueue:(Lcom/android/volley/Request;Ljava/lang/String;)V
        //   150: aload_2        
        //   151: ldc2_w          30
        //   154: getstatic       java/util/concurrent/TimeUnit.SECONDS:Ljava/util/concurrent/TimeUnit;
        //   157: invokevirtual   com/android/volley/toolbox/RequestFuture.get:(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
        //   160: checkcast       Lorg/json/JSONObject;
        //   163: astore          19
        //   165: new             Ljava/lang/StringBuilder;
        //   168: dup            
        //   169: invokespecial   java/lang/StringBuilder.<init>:()V
        //   172: ldc             "GCM registration success: "
        //   174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: aload           19
        //   179: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   188: iconst_0       
        //   189: anewarray       Ljava/lang/Object;
        //   192: invokestatic    timber/log/Timber.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   195: iconst_1       
        //   196: ireturn        
        //   197: ldc             "GCM registration send: non-authorized"
        //   199: iconst_0       
        //   200: anewarray       Ljava/lang/Object;
        //   203: invokestatic    timber/log/Timber.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   206: new             Lbf/io/openshop/api/JsonRequest;
        //   209: dup            
        //   210: iconst_1       
        //   211: aload           11
        //   213: aload           4
        //   215: aload_2        
        //   216: aload_3        
        //   217: invokespecial   bf/io/openshop/api/JsonRequest.<init>:(ILjava/lang/String;Lorg/json/JSONObject;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;)V
        //   220: astore          13
        //   222: goto            124
        //   225: ldc             "Register notification failed - null actual shop."
        //   227: iconst_0       
        //   228: anewarray       Ljava/lang/Object;
        //   231: invokestatic    timber/log/Timber.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   234: iconst_0       
        //   235: ireturn        
        //   236: astore          5
        //   238: aload           5
        //   240: ldc             "Register notification failed."
        //   242: iconst_0       
        //   243: anewarray       Ljava/lang/Object;
        //   246: invokestatic    timber/log/Timber.d:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //   249: iconst_0       
        //   250: ireturn        
        //   251: astore          18
        //   253: aload           18
        //   255: ldc             "Register device api call interrupted."
        //   257: iconst_0       
        //   258: anewarray       Ljava/lang/Object;
        //   261: invokestatic    timber/log/Timber.e:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //   264: aload_3        
        //   265: new             Lcom/android/volley/VolleyError;
        //   268: dup            
        //   269: aload           18
        //   271: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/Throwable;)V
        //   274: invokeinterface com/android/volley/Response$ErrorListener.onErrorResponse:(Lcom/android/volley/VolleyError;)V
        //   279: iconst_0       
        //   280: ireturn        
        //   281: astore          17
        //   283: aload           17
        //   285: ldc             "Register device api call failed."
        //   287: iconst_0       
        //   288: anewarray       Ljava/lang/Object;
        //   291: invokestatic    timber/log/Timber.e:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //   294: aload_3        
        //   295: new             Lcom/android/volley/VolleyError;
        //   298: dup            
        //   299: aload           17
        //   301: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/Throwable;)V
        //   304: invokeinterface com/android/volley/Response$ErrorListener.onErrorResponse:(Lcom/android/volley/VolleyError;)V
        //   309: goto            279
        //   312: astore          16
        //   314: aload           16
        //   316: ldc             "Register device api call timed out."
        //   318: iconst_0       
        //   319: anewarray       Ljava/lang/Object;
        //   322: invokestatic    timber/log/Timber.e:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //   325: aload_3        
        //   326: new             Lcom/android/volley/VolleyError;
        //   329: dup            
        //   330: aload           16
        //   332: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/Throwable;)V
        //   335: invokeinterface com/android/volley/Response$ErrorListener.onErrorResponse:(Lcom/android/volley/VolleyError;)V
        //   340: goto            279
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  22     46     236    251    Ljava/lang/Exception;
        //  51     88     236    251    Ljava/lang/Exception;
        //  93     124    236    251    Ljava/lang/Exception;
        //  124    150    236    251    Ljava/lang/Exception;
        //  150    195    251    279    Ljava/lang/InterruptedException;
        //  150    195    281    312    Ljava/util/concurrent/ExecutionException;
        //  150    195    312    343    Ljava/util/concurrent/TimeoutException;
        //  197    222    236    251    Ljava/lang/Exception;
        //  225    234    236    251    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void subscribeTopics(final String s) throws IOException {
    }
    
    protected void onHandleIntent(final Intent intent) {
        while (true) {
            try {
                final String token = InstanceID.getInstance((Context)this).getToken(this.getString(2131230958), "GCM", null);
                Timber.d("GCM token obtained: " + token, new Object[0]);
                if (!SettingsMy.getTokenSentToServer()) {
                    final boolean sendRegistrationToServer = this.sendRegistrationToServer(token);
                    this.subscribeTopics(token);
                    SettingsMy.setTokenSentToServer(sendRegistrationToServer);
                }
                LocalBroadcastManager.getInstance((Context)this).sendBroadcast(new Intent("registrationComplete"));
            }
            catch (Exception ex) {
                Timber.e("Failed to complete token refresh", ex);
                SettingsMy.setTokenSentToServer(false);
                continue;
            }
            break;
        }
    }
}
