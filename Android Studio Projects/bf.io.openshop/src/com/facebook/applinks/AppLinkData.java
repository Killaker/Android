package com.facebook.applinks;

import android.net.*;
import android.app.*;
import android.content.*;
import android.util.*;
import com.facebook.internal.*;
import com.facebook.*;
import org.json.*;
import android.os.*;
import java.util.*;

public class AppLinkData
{
    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
    static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
    private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
    private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
    private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
    private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
    private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String REFERER_DATA_REF_KEY = "fb_ref";
    private static final String TAG;
    private Bundle argumentBundle;
    private JSONObject arguments;
    private String ref;
    private Uri targetUri;
    
    static {
        TAG = AppLinkData.class.getCanonicalName();
    }
    
    public static AppLinkData createFromActivity(final Activity activity) {
        Validate.notNull(activity, "activity");
        final Intent intent = activity.getIntent();
        AppLinkData appLinkData;
        if (intent == null) {
            appLinkData = null;
        }
        else {
            appLinkData = createFromAlApplinkData(intent);
            if (appLinkData == null) {
                appLinkData = createFromJson(intent.getStringExtra("com.facebook.platform.APPLINK_ARGS"));
            }
            if (appLinkData == null) {
                return createFromUri(intent.getData());
            }
        }
        return appLinkData;
    }
    
    public static AppLinkData createFromAlApplinkData(final Intent intent) {
        AppLinkData appLinkData;
        if (intent == null) {
            appLinkData = null;
        }
        else {
            final Bundle bundleExtra = intent.getBundleExtra("al_applink_data");
            if (bundleExtra == null) {
                return null;
            }
            appLinkData = new AppLinkData();
            appLinkData.targetUri = intent.getData();
            if (appLinkData.targetUri == null) {
                final String string = bundleExtra.getString("target_url");
                if (string != null) {
                    appLinkData.targetUri = Uri.parse(string);
                }
            }
            appLinkData.argumentBundle = bundleExtra;
            appLinkData.arguments = null;
            final Bundle bundle = bundleExtra.getBundle("referer_data");
            if (bundle != null) {
                appLinkData.ref = bundle.getString("fb_ref");
                return appLinkData;
            }
        }
        return appLinkData;
    }
    
    private static AppLinkData createFromJson(final String s) {
        if (s == null) {
            return null;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final String string = jsonObject.getString("version");
            if (!jsonObject.getJSONObject("bridge_args").getString("method").equals("applink") || !string.equals("2")) {
                goto Label_0160;
            }
            final AppLinkData appLinkData = new AppLinkData();
            appLinkData.arguments = jsonObject.getJSONObject("method_args");
            if (appLinkData.arguments.has("ref")) {
                appLinkData.ref = appLinkData.arguments.getString("ref");
                if (appLinkData.arguments.has("target_url")) {
                    appLinkData.targetUri = Uri.parse(appLinkData.arguments.getString("target_url"));
                }
                appLinkData.argumentBundle = toBundle(appLinkData.arguments);
                return appLinkData;
            }
            goto Label_0162;
        }
        catch (JSONException ex) {
            Log.d(AppLinkData.TAG, "Unable to parse AppLink JSON", (Throwable)ex);
        }
        catch (FacebookException ex2) {
            Log.d(AppLinkData.TAG, "Unable to parse AppLink JSON", (Throwable)ex2);
            goto Label_0160;
        }
    }
    
    private static AppLinkData createFromUri(final Uri targetUri) {
        if (targetUri == null) {
            return null;
        }
        final AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = targetUri;
        return appLinkData;
    }
    
    public static void fetchDeferredAppLinkData(final Context context, final CompletionHandler completionHandler) {
        fetchDeferredAppLinkData(context, null, completionHandler);
    }
    
    public static void fetchDeferredAppLinkData(final Context context, String metadataApplicationId, final CompletionHandler completionHandler) {
        Validate.notNull(context, "context");
        Validate.notNull(completionHandler, "completionHandler");
        if (metadataApplicationId == null) {
            metadataApplicationId = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(metadataApplicationId, "applicationId");
        FacebookSdk.getExecutor().execute(new Runnable() {
            final /* synthetic */ Context val$applicationContext = context.getApplicationContext();
            
            @Override
            public void run() {
                fetchDeferredAppLinkFromServer(this.val$applicationContext, metadataApplicationId, completionHandler);
            }
        });
    }
    
    private static void fetchDeferredAppLinkFromServer(final Context p0, final String p1, final CompletionHandler p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_3       
        //     8: aload_3        
        //     9: ldc             "event"
        //    11: ldc             "DEFERRED_APP_LINK"
        //    13: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    16: pop            
        //    17: aload_3        
        //    18: aload_0        
        //    19: invokestatic    com/facebook/internal/AttributionIdentifiers.getAttributionIdentifiers:(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
        //    22: aload_0        
        //    23: invokestatic    com/facebook/appevents/AppEventsLogger.getAnonymousAppDeviceGUID:(Landroid/content/Context;)Ljava/lang/String;
        //    26: aload_0        
        //    27: invokestatic    com/facebook/FacebookSdk.getLimitEventAndDataUsage:(Landroid/content/Context;)Z
        //    30: invokestatic    com/facebook/internal/Utility.setAppEventAttributionParameters:(Lorg/json/JSONObject;Lcom/facebook/internal/AttributionIdentifiers;Ljava/lang/String;Z)V
        //    33: aload_3        
        //    34: ldc_w           "application_package_name"
        //    37: aload_0        
        //    38: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    41: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    44: pop            
        //    45: ldc             "%s/activities"
        //    47: iconst_1       
        //    48: anewarray       Ljava/lang/Object;
        //    51: dup            
        //    52: iconst_0       
        //    53: aload_1        
        //    54: aastore        
        //    55: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    58: astore          7
        //    60: aconst_null    
        //    61: astore          8
        //    63: aconst_null    
        //    64: aload           7
        //    66: aload_3        
        //    67: aconst_null    
        //    68: invokestatic    com/facebook/GraphRequest.newPostRequest:(Lcom/facebook/AccessToken;Ljava/lang/String;Lorg/json/JSONObject;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
        //    71: invokevirtual   com/facebook/GraphRequest.executeAndWait:()Lcom/facebook/GraphResponse;
        //    74: invokevirtual   com/facebook/GraphResponse.getJSONObject:()Lorg/json/JSONObject;
        //    77: astore          10
        //    79: aconst_null    
        //    80: astore          8
        //    82: aload           10
        //    84: ifnull          297
        //    87: aload           10
        //    89: ldc             "applink_args"
        //    91: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    94: astore          11
        //    96: aload           10
        //    98: ldc             "click_time"
        //   100: ldc2_w          -1
        //   103: invokevirtual   org/json/JSONObject.optLong:(Ljava/lang/String;J)J
        //   106: lstore          12
        //   108: aload           10
        //   110: ldc             "applink_class"
        //   112: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   115: astore          14
        //   117: aload           10
        //   119: ldc             "applink_url"
        //   121: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   124: astore          15
        //   126: aload           11
        //   128: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   131: istore          16
        //   133: aconst_null    
        //   134: astore          8
        //   136: iload           16
        //   138: ifne            297
        //   141: aload           11
        //   143: invokestatic    com/facebook/applinks/AppLinkData.createFromJson:(Ljava/lang/String;)Lcom/facebook/applinks/AppLinkData;
        //   146: astore          17
        //   148: aload           17
        //   150: astore          8
        //   152: lload           12
        //   154: ldc2_w          -1
        //   157: lcmp           
        //   158: ifeq            205
        //   161: aload           8
        //   163: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   166: ifnull          182
        //   169: aload           8
        //   171: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   174: ldc             "com.facebook.platform.APPLINK_TAP_TIME_UTC"
        //   176: lload           12
        //   178: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   181: pop            
        //   182: aload           8
        //   184: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   187: ifnull          205
        //   190: aload           8
        //   192: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   195: ldc             "com.facebook.platform.APPLINK_TAP_TIME_UTC"
        //   197: lload           12
        //   199: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //   202: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   205: aload           14
        //   207: ifnull          251
        //   210: aload           8
        //   212: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   215: ifnull          231
        //   218: aload           8
        //   220: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   223: ldc             "com.facebook.platform.APPLINK_NATIVE_CLASS"
        //   225: aload           14
        //   227: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   230: pop            
        //   231: aload           8
        //   233: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   236: ifnull          251
        //   239: aload           8
        //   241: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   244: ldc             "com.facebook.platform.APPLINK_NATIVE_CLASS"
        //   246: aload           14
        //   248: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   251: aload           15
        //   253: ifnull          297
        //   256: aload           8
        //   258: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   261: ifnull          277
        //   264: aload           8
        //   266: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   269: ldc             "com.facebook.platform.APPLINK_NATIVE_URL"
        //   271: aload           15
        //   273: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   276: pop            
        //   277: aload           8
        //   279: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   282: ifnull          297
        //   285: aload           8
        //   287: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   290: ldc             "com.facebook.platform.APPLINK_NATIVE_URL"
        //   292: aload           15
        //   294: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   297: aload_2        
        //   298: aload           8
        //   300: invokeinterface com/facebook/applinks/AppLinkData$CompletionHandler.onDeferredAppLinkDataFetched:(Lcom/facebook/applinks/AppLinkData;)V
        //   305: return         
        //   306: astore          4
        //   308: new             Lcom/facebook/FacebookException;
        //   311: dup            
        //   312: ldc_w           "An error occurred while preparing deferred app link"
        //   315: aload           4
        //   317: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   320: athrow         
        //   321: astore          24
        //   323: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   326: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   329: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   332: pop            
        //   333: goto            205
        //   336: astore          9
        //   338: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   341: ldc_w           "Unable to fetch deferred applink from server"
        //   344: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;)V
        //   347: goto            297
        //   350: astore          21
        //   352: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   355: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   358: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   361: pop            
        //   362: goto            251
        //   365: astore          18
        //   367: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   370: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   373: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   376: pop            
        //   377: goto            297
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  8      45     306    321    Lorg/json/JSONException;
        //  63     79     336    350    Ljava/lang/Exception;
        //  87     133    336    350    Ljava/lang/Exception;
        //  141    148    336    350    Ljava/lang/Exception;
        //  161    182    321    336    Lorg/json/JSONException;
        //  161    182    336    350    Ljava/lang/Exception;
        //  182    205    321    336    Lorg/json/JSONException;
        //  182    205    336    350    Ljava/lang/Exception;
        //  210    231    350    365    Lorg/json/JSONException;
        //  210    231    336    350    Ljava/lang/Exception;
        //  231    251    350    365    Lorg/json/JSONException;
        //  231    251    336    350    Ljava/lang/Exception;
        //  256    277    365    380    Lorg/json/JSONException;
        //  256    277    336    350    Ljava/lang/Exception;
        //  277    297    365    380    Lorg/json/JSONException;
        //  277    297    336    350    Ljava/lang/Exception;
        //  323    333    336    350    Ljava/lang/Exception;
        //  352    362    336    350    Ljava/lang/Exception;
        //  367    377    336    350    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static Bundle toBundle(final JSONObject jsonObject) throws JSONException {
        final Bundle bundle = new Bundle();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            final Object value = jsonObject.get(s);
            if (value instanceof JSONObject) {
                bundle.putBundle(s, toBundle((JSONObject)value));
            }
            else if (value instanceof JSONArray) {
                final JSONArray jsonArray = (JSONArray)value;
                if (jsonArray.length() == 0) {
                    bundle.putStringArray(s, new String[0]);
                }
                else {
                    final Object value2 = jsonArray.get(0);
                    if (value2 instanceof JSONObject) {
                        final Bundle[] array = new Bundle[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); ++i) {
                            array[i] = toBundle(jsonArray.getJSONObject(i));
                        }
                        bundle.putParcelableArray(s, (Parcelable[])array);
                    }
                    else {
                        if (value2 instanceof JSONArray) {
                            throw new FacebookException("Nested arrays are not supported.");
                        }
                        final String[] array2 = new String[jsonArray.length()];
                        for (int j = 0; j < jsonArray.length(); ++j) {
                            array2[j] = jsonArray.get(j).toString();
                        }
                        bundle.putStringArray(s, array2);
                    }
                }
            }
            else {
                bundle.putString(s, value.toString());
            }
        }
        return bundle;
    }
    
    public Bundle getArgumentBundle() {
        return this.argumentBundle;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public Bundle getRefererData() {
        if (this.argumentBundle != null) {
            return this.argumentBundle.getBundle("referer_data");
        }
        return null;
    }
    
    public Uri getTargetUri() {
        return this.targetUri;
    }
    
    public interface CompletionHandler
    {
        void onDeferredAppLinkDataFetched(final AppLinkData p0);
    }
}
