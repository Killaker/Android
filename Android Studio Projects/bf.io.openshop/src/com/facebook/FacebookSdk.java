package com.facebook;

import android.content.*;
import java.io.*;
import java.util.concurrent.atomic.*;
import java.security.*;
import android.os.*;
import java.util.*;
import android.content.pm.*;
import com.facebook.internal.*;
import java.util.concurrent.*;
import android.util.*;

public final class FacebookSdk
{
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized.";
    static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY;
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
    private static final String FACEBOOK_COM = "facebook.com";
    private static final Object LOCK;
    private static final int MAX_REQUEST_CODE_RANGE = 100;
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    private static final String TAG;
    public static final String WEB_DIALOG_THEME = "com.facebook.sdk.WebDialogTheme";
    private static volatile String appClientToken;
    private static Context applicationContext;
    private static volatile String applicationId;
    private static volatile String applicationName;
    private static LockOnGetVariable<File> cacheDir;
    private static int callbackRequestCodeOffset;
    private static volatile Executor executor;
    private static volatile String facebookDomain;
    private static volatile boolean isDebugEnabled;
    private static boolean isLegacyTokenUpgradeSupported;
    private static final HashSet<LoggingBehavior> loggingBehaviors;
    private static AtomicLong onProgressThreshold;
    private static Boolean sdkInitialized;
    private static volatile int webDialogTheme;
    
    static {
        TAG = FacebookSdk.class.getCanonicalName();
        loggingBehaviors = new HashSet<LoggingBehavior>(Arrays.asList(LoggingBehavior.DEVELOPER_ERRORS));
        FacebookSdk.facebookDomain = "facebook.com";
        FacebookSdk.onProgressThreshold = new AtomicLong(65536L);
        FacebookSdk.isDebugEnabled = false;
        FacebookSdk.isLegacyTokenUpgradeSupported = false;
        FacebookSdk.callbackRequestCodeOffset = 64206;
        LOCK = new Object();
        DEFAULT_WORK_QUEUE = new LinkedBlockingQueue<Runnable>(10);
        DEFAULT_THREAD_FACTORY = new ThreadFactory() {
            private final AtomicInteger counter = new AtomicInteger(0);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "FacebookSdk #" + this.counter.incrementAndGet());
            }
        };
        FacebookSdk.sdkInitialized = false;
    }
    
    public static void addLoggingBehavior(final LoggingBehavior loggingBehavior) {
        synchronized (FacebookSdk.loggingBehaviors) {
            FacebookSdk.loggingBehaviors.add(loggingBehavior);
            updateGraphDebugBehavior();
        }
    }
    
    public static void clearLoggingBehaviors() {
        synchronized (FacebookSdk.loggingBehaviors) {
            FacebookSdk.loggingBehaviors.clear();
        }
    }
    
    public static Context getApplicationContext() {
        Validate.sdkInitialized();
        return FacebookSdk.applicationContext;
    }
    
    public static String getApplicationId() {
        Validate.sdkInitialized();
        return FacebookSdk.applicationId;
    }
    
    public static String getApplicationName() {
        Validate.sdkInitialized();
        return FacebookSdk.applicationName;
    }
    
    public static String getApplicationSignature(final Context context) {
        Validate.sdkInitialized();
        if (context != null) {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                final String packageName = context.getPackageName();
                PackageInfo packageInfo;
                try {
                    packageInfo = packageManager.getPackageInfo(packageName, 64);
                    final Signature[] signatures = packageInfo.signatures;
                    if (signatures != null && signatures.length != 0) {
                        final String s = "SHA-1";
                        final MessageDigest messageDigest = MessageDigest.getInstance(s);
                        final MessageDigest messageDigest3;
                        final MessageDigest messageDigest2 = messageDigest3 = messageDigest;
                        final PackageInfo packageInfo2 = packageInfo;
                        final Signature[] array = packageInfo2.signatures;
                        final int n = 0;
                        final Signature signature = array[n];
                        final byte[] array2 = signature.toByteArray();
                        messageDigest3.update(array2);
                        final MessageDigest messageDigest4 = messageDigest2;
                        final byte[] array3 = messageDigest4.digest();
                        final int n2 = 9;
                        return Base64.encodeToString(array3, n2);
                    }
                    return null;
                }
                catch (PackageManager$NameNotFoundException ex) {
                    return null;
                }
                try {
                    final String s = "SHA-1";
                    final MessageDigest messageDigest = MessageDigest.getInstance(s);
                    final MessageDigest messageDigest3;
                    final MessageDigest messageDigest2 = messageDigest3 = messageDigest;
                    final PackageInfo packageInfo2 = packageInfo;
                    final Signature[] array = packageInfo2.signatures;
                    final int n = 0;
                    final Signature signature = array[n];
                    final byte[] array2 = signature.toByteArray();
                    messageDigest3.update(array2);
                    final MessageDigest messageDigest4 = messageDigest2;
                    final byte[] array3 = messageDigest4.digest();
                    final int n2 = 9;
                    return Base64.encodeToString(array3, n2);
                }
                catch (NoSuchAlgorithmException ex2) {
                    return null;
                }
            }
        }
        return null;
    }
    
    public static File getCacheDir() {
        Validate.sdkInitialized();
        return FacebookSdk.cacheDir.getValue();
    }
    
    public static int getCallbackRequestCodeOffset() {
        Validate.sdkInitialized();
        return FacebookSdk.callbackRequestCodeOffset;
    }
    
    public static String getClientToken() {
        Validate.sdkInitialized();
        return FacebookSdk.appClientToken;
    }
    
    public static Executor getExecutor() {
        synchronized (FacebookSdk.LOCK) {
            if (FacebookSdk.executor == null) {
                FacebookSdk.executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
            return FacebookSdk.executor;
        }
    }
    
    public static String getFacebookDomain() {
        return FacebookSdk.facebookDomain;
    }
    
    public static boolean getLimitEventAndDataUsage(final Context context) {
        Validate.sdkInitialized();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }
    
    public static Set<LoggingBehavior> getLoggingBehaviors() {
        synchronized (FacebookSdk.loggingBehaviors) {
            return Collections.unmodifiableSet((Set<? extends LoggingBehavior>)new HashSet<LoggingBehavior>(FacebookSdk.loggingBehaviors));
        }
    }
    
    public static long getOnProgressThreshold() {
        Validate.sdkInitialized();
        return FacebookSdk.onProgressThreshold.get();
    }
    
    public static String getSdkVersion() {
        return "4.9.0";
    }
    
    public static int getWebDialogTheme() {
        Validate.sdkInitialized();
        return FacebookSdk.webDialogTheme;
    }
    
    public static boolean isDebugEnabled() {
        return FacebookSdk.isDebugEnabled;
    }
    
    public static boolean isFacebookRequestCode(final int n) {
        return n >= FacebookSdk.callbackRequestCodeOffset && n < 100 + FacebookSdk.callbackRequestCodeOffset;
    }
    
    public static boolean isInitialized() {
        synchronized (FacebookSdk.class) {
            return FacebookSdk.sdkInitialized;
        }
    }
    
    public static boolean isLegacyTokenUpgradeSupported() {
        return FacebookSdk.isLegacyTokenUpgradeSupported;
    }
    
    public static boolean isLoggingBehaviorEnabled(final LoggingBehavior loggingBehavior) {
        while (true) {
            synchronized (FacebookSdk.loggingBehaviors) {
                if (isDebugEnabled() && FacebookSdk.loggingBehaviors.contains(loggingBehavior)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    static void loadDefaultsFromMetadata(final Context context) {
        if (context != null) {
            while (true) {
                while (true) {
                    Object value = null;
                    Label_0151: {
                        String applicationId = null;
                        Label_0143: {
                            try {
                                final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                                if (applicationInfo == null || applicationInfo.metaData == null) {
                                    return;
                                }
                                if (FacebookSdk.applicationId == null) {
                                    value = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                                    if (!(value instanceof String)) {
                                        break Label_0151;
                                    }
                                    applicationId = (String)value;
                                    if (!applicationId.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                        break Label_0143;
                                    }
                                    FacebookSdk.applicationId = applicationId.substring(2);
                                }
                                if (FacebookSdk.applicationName == null) {
                                    FacebookSdk.applicationName = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                                }
                                if (FacebookSdk.appClientToken == null) {
                                    FacebookSdk.appClientToken = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                                }
                                if (FacebookSdk.webDialogTheme == 0) {
                                    setWebDialogTheme(applicationInfo.metaData.getInt("com.facebook.sdk.WebDialogTheme"));
                                }
                                return;
                            }
                            catch (PackageManager$NameNotFoundException ex) {
                                return;
                            }
                        }
                        FacebookSdk.applicationId = applicationId;
                        continue;
                    }
                    if (value instanceof Integer) {
                        break;
                    }
                    continue;
                }
            }
            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
        }
    }
    
    static GraphResponse publishInstallAndWaitForResponse(final Context p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          8
        //     4: aload_1        
        //     5: ifnonnull       48
        //     8: new             Ljava/lang/IllegalArgumentException;
        //    11: dup            
        //    12: ldc_w           "Both context and applicationId must be non-null"
        //    15: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    18: athrow         
        //    19: astore_2       
        //    20: ldc_w           "Facebook-publish"
        //    23: aload_2        
        //    24: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/Exception;)V
        //    27: new             Lcom/facebook/FacebookRequestError;
        //    30: dup            
        //    31: aconst_null    
        //    32: aload_2        
        //    33: invokespecial   com/facebook/FacebookRequestError.<init>:(Ljava/net/HttpURLConnection;Ljava/lang/Exception;)V
        //    36: astore_3       
        //    37: new             Lcom/facebook/GraphResponse;
        //    40: dup            
        //    41: aconst_null    
        //    42: aconst_null    
        //    43: aload_3        
        //    44: invokespecial   com/facebook/GraphResponse.<init>:(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookRequestError;)V
        //    47: areturn        
        //    48: aload_0        
        //    49: invokestatic    com/facebook/internal/AttributionIdentifiers.getAttributionIdentifiers:(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
        //    52: astore          4
        //    54: aload_0        
        //    55: ldc             "com.facebook.sdk.attributionTracking"
        //    57: iconst_0       
        //    58: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //    61: astore          5
        //    63: new             Ljava/lang/StringBuilder;
        //    66: dup            
        //    67: invokespecial   java/lang/StringBuilder.<init>:()V
        //    70: aload_1        
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: ldc_w           "ping"
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    83: astore          6
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: aload_1        
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: ldc_w           "json"
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   105: astore          7
        //   107: aload           5
        //   109: aload           6
        //   111: lconst_0       
        //   112: invokeinterface android/content/SharedPreferences.getLong:(Ljava/lang/String;J)J
        //   117: lstore          8
        //   119: aload           5
        //   121: aload           7
        //   123: aconst_null    
        //   124: invokeinterface android/content/SharedPreferences.getString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   129: astore          10
        //   131: getstatic       com/facebook/internal/AppEventsLoggerUtility$GraphAPIActivityType.MOBILE_INSTALL_EVENT:Lcom/facebook/internal/AppEventsLoggerUtility$GraphAPIActivityType;
        //   134: aload           4
        //   136: aload_0        
        //   137: invokestatic    com/facebook/appevents/AppEventsLogger.getAnonymousAppDeviceGUID:(Landroid/content/Context;)Ljava/lang/String;
        //   140: aload_0        
        //   141: invokestatic    com/facebook/FacebookSdk.getLimitEventAndDataUsage:(Landroid/content/Context;)Z
        //   144: aload_0        
        //   145: invokestatic    com/facebook/internal/AppEventsLoggerUtility.getJSONObjectForGraphAPICall:(Lcom/facebook/internal/AppEventsLoggerUtility$GraphAPIActivityType;Lcom/facebook/internal/AttributionIdentifiers;Ljava/lang/String;ZLandroid/content/Context;)Lorg/json/JSONObject;
        //   148: astore          13
        //   150: aconst_null    
        //   151: ldc             "%s/activities"
        //   153: iconst_1       
        //   154: anewarray       Ljava/lang/Object;
        //   157: dup            
        //   158: iconst_0       
        //   159: aload_1        
        //   160: aastore        
        //   161: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   164: aload           13
        //   166: aconst_null    
        //   167: invokestatic    com/facebook/GraphRequest.newPostRequest:(Lcom/facebook/AccessToken;Ljava/lang/String;Lorg/json/JSONObject;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
        //   170: astore          14
        //   172: lload           8
        //   174: lconst_0       
        //   175: lcmp           
        //   176: ifeq            276
        //   179: aconst_null    
        //   180: astore          15
        //   182: aload           10
        //   184: ifnull          202
        //   187: new             Lorg/json/JSONObject;
        //   190: dup            
        //   191: aload           10
        //   193: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   196: astore          17
        //   198: aload           17
        //   200: astore          15
        //   202: aload           15
        //   204: ifnonnull       259
        //   207: ldc_w           "true"
        //   210: aconst_null    
        //   211: new             Lcom/facebook/GraphRequestBatch;
        //   214: dup            
        //   215: iconst_1       
        //   216: anewarray       Lcom/facebook/GraphRequest;
        //   219: dup            
        //   220: iconst_0       
        //   221: aload           14
        //   223: aastore        
        //   224: invokespecial   com/facebook/GraphRequestBatch.<init>:([Lcom/facebook/GraphRequest;)V
        //   227: invokestatic    com/facebook/GraphResponse.createResponsesFromString:(Ljava/lang/String;Ljava/net/HttpURLConnection;Lcom/facebook/GraphRequestBatch;)Ljava/util/List;
        //   230: iconst_0       
        //   231: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   236: checkcast       Lcom/facebook/GraphResponse;
        //   239: areturn        
        //   240: astore          11
        //   242: new             Lcom/facebook/FacebookException;
        //   245: dup            
        //   246: ldc_w           "An error occurred while publishing install."
        //   249: aload           11
        //   251: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   254: astore          12
        //   256: aload           12
        //   258: athrow         
        //   259: new             Lcom/facebook/GraphResponse;
        //   262: dup            
        //   263: aconst_null    
        //   264: aconst_null    
        //   265: aconst_null    
        //   266: aload           15
        //   268: invokespecial   com/facebook/GraphResponse.<init>:(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Ljava/lang/String;Lorg/json/JSONObject;)V
        //   271: astore          16
        //   273: aload           16
        //   275: areturn        
        //   276: aload           14
        //   278: invokevirtual   com/facebook/GraphRequest.executeAndWait:()Lcom/facebook/GraphResponse;
        //   281: astore          19
        //   283: aload           5
        //   285: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   290: astore          20
        //   292: aload           20
        //   294: aload           6
        //   296: invokestatic    java/lang/System.currentTimeMillis:()J
        //   299: invokeinterface android/content/SharedPreferences$Editor.putLong:(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;
        //   304: pop            
        //   305: aload           19
        //   307: invokevirtual   com/facebook/GraphResponse.getJSONObject:()Lorg/json/JSONObject;
        //   310: ifnull          331
        //   313: aload           20
        //   315: aload           7
        //   317: aload           19
        //   319: invokevirtual   com/facebook/GraphResponse.getJSONObject:()Lorg/json/JSONObject;
        //   322: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   325: invokeinterface android/content/SharedPreferences$Editor.putString:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
        //   330: pop            
        //   331: aload           20
        //   333: invokeinterface android/content/SharedPreferences$Editor.apply:()V
        //   338: aload           19
        //   340: areturn        
        //   341: astore          18
        //   343: aconst_null    
        //   344: astore          15
        //   346: goto            202
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  8      19     19     48     Ljava/lang/Exception;
        //  48     131    19     48     Ljava/lang/Exception;
        //  131    150    240    259    Lorg/json/JSONException;
        //  131    150    19     48     Ljava/lang/Exception;
        //  150    172    19     48     Ljava/lang/Exception;
        //  187    198    341    349    Lorg/json/JSONException;
        //  187    198    19     48     Ljava/lang/Exception;
        //  207    240    19     48     Ljava/lang/Exception;
        //  242    259    19     48     Ljava/lang/Exception;
        //  259    273    19     48     Ljava/lang/Exception;
        //  276    331    19     48     Ljava/lang/Exception;
        //  331    338    19     48     Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void publishInstallAsync(final Context context, final String s) {
        getExecutor().execute(new Runnable() {
            final /* synthetic */ Context val$applicationContext = context.getApplicationContext();
            
            @Override
            public void run() {
                FacebookSdk.publishInstallAndWaitForResponse(this.val$applicationContext, s);
            }
        });
    }
    
    public static void removeLoggingBehavior(final LoggingBehavior loggingBehavior) {
        synchronized (FacebookSdk.loggingBehaviors) {
            FacebookSdk.loggingBehaviors.remove(loggingBehavior);
        }
    }
    
    public static void sdkInitialize(final Context context) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, null);
        }
    }
    
    public static void sdkInitialize(final Context context, final int n) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, n, null);
        }
    }
    
    public static void sdkInitialize(final Context context, final int callbackRequestCodeOffset, final InitializeCallback initializeCallback) {
        synchronized (FacebookSdk.class) {
            if (FacebookSdk.sdkInitialized && callbackRequestCodeOffset != FacebookSdk.callbackRequestCodeOffset) {
                throw new FacebookException("The callback request code offset can't be updated once the SDK is initialized.");
            }
        }
        if (callbackRequestCodeOffset < 0) {
            throw new FacebookException("The callback request code offset can't be negative.");
        }
        FacebookSdk.callbackRequestCodeOffset = callbackRequestCodeOffset;
        sdkInitialize(context, initializeCallback);
    }
    // monitorexit(FacebookSdk.class)
    
    public static void sdkInitialize(final Context context, final InitializeCallback initializeCallback) {
        synchronized (FacebookSdk.class) {
            if (FacebookSdk.sdkInitialized) {
                if (initializeCallback != null) {
                    initializeCallback.onInitialized();
                }
            }
            else {
                Validate.notNull(context, "applicationContext");
                Validate.hasFacebookActivity(context, false);
                Validate.hasInternetPermissions(context, false);
                loadDefaultsFromMetadata(FacebookSdk.applicationContext = context.getApplicationContext());
                FacebookSdk.sdkInitialized = true;
                Utility.loadAppSettingsAsync(FacebookSdk.applicationContext, FacebookSdk.applicationId);
                NativeProtocol.updateAllAvailableProtocolVersionsAsync();
                BoltsMeasurementEventListener.getInstance(FacebookSdk.applicationContext);
                FacebookSdk.cacheDir = new LockOnGetVariable<File>(new Callable<File>() {
                    @Override
                    public File call() throws Exception {
                        return FacebookSdk.applicationContext.getCacheDir();
                    }
                });
                getExecutor().execute(new FutureTask<Object>(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        AccessTokenManager.getInstance().loadCurrentAccessToken();
                        ProfileManager.getInstance().loadCurrentProfile();
                        if (AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() == null) {
                            Profile.fetchProfileForCurrentAccessToken();
                        }
                        if (initializeCallback != null) {
                            initializeCallback.onInitialized();
                        }
                        return null;
                    }
                }));
            }
        }
    }
    
    public static void setApplicationId(final String applicationId) {
        FacebookSdk.applicationId = applicationId;
    }
    
    public static void setApplicationName(final String applicationName) {
        FacebookSdk.applicationName = applicationName;
    }
    
    public static void setCacheDir(final File file) {
        FacebookSdk.cacheDir = new LockOnGetVariable<File>(file);
    }
    
    public static void setClientToken(final String appClientToken) {
        FacebookSdk.appClientToken = appClientToken;
    }
    
    public static void setExecutor(final Executor executor) {
        Validate.notNull(executor, "executor");
        synchronized (FacebookSdk.LOCK) {
            FacebookSdk.executor = executor;
        }
    }
    
    public static void setFacebookDomain(final String facebookDomain) {
        Log.w(FacebookSdk.TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        FacebookSdk.facebookDomain = facebookDomain;
    }
    
    public static void setIsDebugEnabled(final boolean isDebugEnabled) {
        FacebookSdk.isDebugEnabled = isDebugEnabled;
    }
    
    public static void setLegacyTokenUpgradeSupported(final boolean isLegacyTokenUpgradeSupported) {
        FacebookSdk.isLegacyTokenUpgradeSupported = isLegacyTokenUpgradeSupported;
    }
    
    public static void setLimitEventAndDataUsage(final Context context, final boolean b) {
        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", b).apply();
    }
    
    public static void setOnProgressThreshold(final long n) {
        FacebookSdk.onProgressThreshold.set(n);
    }
    
    public static void setWebDialogTheme(int webDialogTheme) {
        if (webDialogTheme == 0) {
            webDialogTheme = 16973840;
        }
        FacebookSdk.webDialogTheme = webDialogTheme;
    }
    
    private static void updateGraphDebugBehavior() {
        if (FacebookSdk.loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO) && !FacebookSdk.loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            FacebookSdk.loggingBehaviors.add(LoggingBehavior.GRAPH_API_DEBUG_WARNING);
        }
    }
    
    public interface InitializeCallback
    {
        void onInitialized();
    }
}
