package com.facebook.appevents;

import android.app.*;
import android.util.*;
import android.os.*;
import org.json.*;
import java.util.concurrent.*;
import bolts.*;
import android.content.*;
import java.math.*;
import com.facebook.*;
import java.util.*;
import java.io.*;
import com.facebook.internal.*;

public class AppEventsLogger
{
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
    private static final int FLUSH_PERIOD_IN_SECONDS = 15;
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 100;
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private static final String TAG;
    private static String anonymousAppDeviceGUID;
    private static Context applicationContext;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static FlushBehavior flushBehavior;
    private static boolean isActivateAppEventRequested;
    private static boolean isOpenedByApplink;
    private static boolean requestInFlight;
    private static String sourceApplication;
    private static Map<AccessTokenAppIdPair, SessionEventsState> stateMap;
    private static Object staticLock;
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;
    
    static {
        TAG = AppEventsLogger.class.getCanonicalName();
        AppEventsLogger.stateMap = new ConcurrentHashMap<AccessTokenAppIdPair, SessionEventsState>();
        AppEventsLogger.flushBehavior = FlushBehavior.AUTO;
        AppEventsLogger.staticLock = new Object();
    }
    
    private AppEventsLogger(final Context context, String metadataApplicationId, AccessToken currentAccessToken) {
        Validate.notNull(context, "context");
        this.contextName = Utility.getActivityName(context);
        if (currentAccessToken == null) {
            currentAccessToken = AccessToken.getCurrentAccessToken();
        }
        Label_0085: {
            if (currentAccessToken == null || (metadataApplicationId != null && !metadataApplicationId.equals(currentAccessToken.getApplicationId()))) {
                break Label_0085;
            }
            this.accessTokenAppId = new AccessTokenAppIdPair(currentAccessToken);
        Label_0094_Outer:
            while (true) {
                synchronized (AppEventsLogger.staticLock) {
                    if (AppEventsLogger.applicationContext == null) {
                        AppEventsLogger.applicationContext = context.getApplicationContext();
                    }
                    initializeTimersIfNeeded();
                    return;
                    while (true) {
                        this.accessTokenAppId = new AccessTokenAppIdPair(null, metadataApplicationId);
                        continue Label_0094_Outer;
                        metadataApplicationId = Utility.getMetadataApplicationId(context);
                        continue;
                    }
                }
                // iftrue(Label_0094:, metadataApplicationId != null)
            }
        }
    }
    
    private static int accumulatePersistedEvents() {
        final PersistedEvents andClearStore = PersistedEvents.readAndClearStore(AppEventsLogger.applicationContext);
        int n = 0;
        for (final AccessTokenAppIdPair accessTokenAppIdPair : andClearStore.keySet()) {
            final SessionEventsState sessionEventsState = getSessionEventsState(AppEventsLogger.applicationContext, accessTokenAppIdPair);
            final List<AppEvent> events = andClearStore.getEvents(accessTokenAppIdPair);
            sessionEventsState.accumulatePersistedEvents(events);
            n += events.size();
        }
        return n;
    }
    
    public static void activateApp(final Context context) {
        FacebookSdk.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }
    
    public static void activateApp(final Context context, final String s) {
        if (context == null || s == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        if (context instanceof Activity) {
            setSourceApplication((Activity)context);
        }
        else {
            resetSourceApplication();
            Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
        }
        FacebookSdk.publishInstallAsync(context, s);
        AppEventsLogger.backgroundExecutor.execute(new Runnable() {
            final /* synthetic */ long val$eventTime = System.currentTimeMillis();
            final /* synthetic */ AppEventsLogger val$logger = new AppEventsLogger(context, s, null);
            final /* synthetic */ String val$sourceApplicationInfo = getSourceApplication();
            
            @Override
            public void run() {
                this.val$logger.logAppSessionResumeEvent(this.val$eventTime, this.val$sourceApplicationInfo);
            }
        });
    }
    
    private static FlushStatistics buildAndExecuteRequests(final FlushReason flushReason, final Set<AccessTokenAppIdPair> set) {
        FlushStatistics flushStatistics = new FlushStatistics();
        final boolean limitEventAndDataUsage = FacebookSdk.getLimitEventAndDataUsage(AppEventsLogger.applicationContext);
        final ArrayList<GraphRequest> list = new ArrayList<GraphRequest>();
        for (final AccessTokenAppIdPair accessTokenAppIdPair : set) {
            final SessionEventsState sessionEventsState = getSessionEventsState(accessTokenAppIdPair);
            if (sessionEventsState != null) {
                final GraphRequest buildRequestForSession = buildRequestForSession(accessTokenAppIdPair, sessionEventsState, limitEventAndDataUsage, flushStatistics);
                if (buildRequestForSession == null) {
                    continue;
                }
                list.add(buildRequestForSession);
            }
        }
        if (list.size() > 0) {
            Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "Flushing %d events due to %s.", flushStatistics.numEvents, flushReason.toString());
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().executeAndWait();
            }
        }
        else {
            flushStatistics = null;
        }
        return flushStatistics;
    }
    
    private static GraphRequest buildRequestForSession(final AccessTokenAppIdPair accessTokenAppIdPair, final SessionEventsState sessionEventsState, final boolean b, final FlushStatistics flushStatistics) {
        final String applicationId = accessTokenAppIdPair.getApplicationId();
        final Utility.FetchedAppSettings queryAppSettings = Utility.queryAppSettings(applicationId, false);
        final GraphRequest postRequest = GraphRequest.newPostRequest(null, String.format("%s/activities", applicationId), null, null);
        Bundle parameters = postRequest.getParameters();
        if (parameters == null) {
            parameters = new Bundle();
        }
        parameters.putString("access_token", accessTokenAppIdPair.getAccessTokenString());
        postRequest.setParameters(parameters);
        if (queryAppSettings == null) {
            return null;
        }
        final int populateRequest = sessionEventsState.populateRequest(postRequest, queryAppSettings.supportsImplicitLogging(), b);
        if (populateRequest == 0) {
            return null;
        }
        flushStatistics.numEvents += populateRequest;
        postRequest.setCallback((GraphRequest.Callback)new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                handleResponse(accessTokenAppIdPair, postRequest, graphResponse, sessionEventsState, flushStatistics);
            }
        });
        return postRequest;
    }
    
    public static void deactivateApp(final Context context) {
        deactivateApp(context, Utility.getMetadataApplicationId(context));
    }
    
    public static void deactivateApp(final Context context, final String s) {
        if (context == null || s == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        resetSourceApplication();
        AppEventsLogger.backgroundExecutor.execute(new Runnable() {
            final /* synthetic */ long val$eventTime = System.currentTimeMillis();
            final /* synthetic */ AppEventsLogger val$logger = new AppEventsLogger(context, s, null);
            
            @Override
            public void run() {
                this.val$logger.logAppSessionSuspendEvent(this.val$eventTime);
            }
        });
    }
    
    static void eagerFlush() {
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }
    
    private static void flush(final FlushReason flushReason) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                flushAndWait(flushReason);
            }
        });
    }
    
    private static void flushAndWait(final FlushReason p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/facebook/appevents/AppEventsLogger.staticLock:Ljava/lang/Object;
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: getstatic       com/facebook/appevents/AppEventsLogger.requestInFlight:Z
        //     9: ifeq            15
        //    12: aload_1        
        //    13: monitorexit    
        //    14: return         
        //    15: iconst_1       
        //    16: putstatic       com/facebook/appevents/AppEventsLogger.requestInFlight:Z
        //    19: new             Ljava/util/HashSet;
        //    22: dup            
        //    23: getstatic       com/facebook/appevents/AppEventsLogger.stateMap:Ljava/util/Map;
        //    26: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //    31: invokespecial   java/util/HashSet.<init>:(Ljava/util/Collection;)V
        //    34: astore_3       
        //    35: aload_1        
        //    36: monitorexit    
        //    37: invokestatic    com/facebook/appevents/AppEventsLogger.accumulatePersistedEvents:()I
        //    40: pop            
        //    41: aload_0        
        //    42: aload_3        
        //    43: invokestatic    com/facebook/appevents/AppEventsLogger.buildAndExecuteRequests:(Lcom/facebook/appevents/AppEventsLogger$FlushReason;Ljava/util/Set;)Lcom/facebook/appevents/AppEventsLogger$FlushStatistics;
        //    46: astore          13
        //    48: aload           13
        //    50: astore          6
        //    52: getstatic       com/facebook/appevents/AppEventsLogger.staticLock:Ljava/lang/Object;
        //    55: astore          7
        //    57: aload           7
        //    59: monitorenter   
        //    60: iconst_0       
        //    61: putstatic       com/facebook/appevents/AppEventsLogger.requestInFlight:Z
        //    64: aload           7
        //    66: monitorexit    
        //    67: aload           6
        //    69: ifnull          154
        //    72: new             Landroid/content/Intent;
        //    75: dup            
        //    76: ldc             "com.facebook.sdk.APP_EVENTS_FLUSHED"
        //    78: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
        //    81: astore          9
        //    83: aload           9
        //    85: ldc             "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED"
        //    87: aload           6
        //    89: getfield        com/facebook/appevents/AppEventsLogger$FlushStatistics.numEvents:I
        //    92: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;I)Landroid/content/Intent;
        //    95: pop            
        //    96: aload           9
        //    98: ldc             "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT"
        //   100: aload           6
        //   102: getfield        com/facebook/appevents/AppEventsLogger$FlushStatistics.result:Lcom/facebook/appevents/AppEventsLogger$FlushResult;
        //   105: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //   108: pop            
        //   109: getstatic       com/facebook/appevents/AppEventsLogger.applicationContext:Landroid/content/Context;
        //   112: invokestatic    android/support/v4/content/LocalBroadcastManager.getInstance:(Landroid/content/Context;)Landroid/support/v4/content/LocalBroadcastManager;
        //   115: aload           9
        //   117: invokevirtual   android/support/v4/content/LocalBroadcastManager.sendBroadcast:(Landroid/content/Intent;)Z
        //   120: pop            
        //   121: return         
        //   122: astore_2       
        //   123: aload_1        
        //   124: monitorexit    
        //   125: aload_2        
        //   126: athrow         
        //   127: astore          5
        //   129: getstatic       com/facebook/appevents/AppEventsLogger.TAG:Ljava/lang/String;
        //   132: ldc_w           "Caught unexpected exception while flushing: "
        //   135: aload           5
        //   137: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   140: aconst_null    
        //   141: astore          6
        //   143: goto            52
        //   146: astore          8
        //   148: aload           7
        //   150: monitorexit    
        //   151: aload           8
        //   153: athrow         
        //   154: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      14     122    127    Any
        //  15     37     122    127    Any
        //  41     48     127    146    Ljava/lang/Exception;
        //  60     67     146    154    Any
        //  123    125    122    127    Any
        //  148    151    146    154    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void flushIfNecessary() {
        synchronized (AppEventsLogger.staticLock) {
            if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY && getAccumulatedEventCount() > 100) {
                flush(FlushReason.EVENT_THRESHOLD);
            }
        }
    }
    
    private static int getAccumulatedEventCount() {
        final Object staticLock = AppEventsLogger.staticLock;
        // monitorenter(staticLock)
        int n = 0;
        try {
            final Iterator<SessionEventsState> iterator = AppEventsLogger.stateMap.values().iterator();
            while (iterator.hasNext()) {
                n += iterator.next().getAccumulatedEventCount();
            }
            return n;
        }
        finally {
        }
        // monitorexit(staticLock)
    }
    
    public static String getAnonymousAppDeviceGUID(final Context context) {
        Label_0101: {
            if (AppEventsLogger.anonymousAppDeviceGUID != null) {
                break Label_0101;
            }
            synchronized (AppEventsLogger.staticLock) {
                if (AppEventsLogger.anonymousAppDeviceGUID == null) {
                    AppEventsLogger.anonymousAppDeviceGUID = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", (String)null);
                    if (AppEventsLogger.anonymousAppDeviceGUID == null) {
                        AppEventsLogger.anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", AppEventsLogger.anonymousAppDeviceGUID).apply();
                    }
                }
                return AppEventsLogger.anonymousAppDeviceGUID;
            }
        }
    }
    
    public static FlushBehavior getFlushBehavior() {
        synchronized (AppEventsLogger.staticLock) {
            return AppEventsLogger.flushBehavior;
        }
    }
    
    private static SessionEventsState getSessionEventsState(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair) {
        final SessionEventsState sessionEventsState = AppEventsLogger.stateMap.get(accessTokenAppIdPair);
        AttributionIdentifiers attributionIdentifiers = null;
        if (sessionEventsState == null) {
            attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        }
        final Object staticLock = AppEventsLogger.staticLock;
        // monitorenter(staticLock)
        while (true) {
            try {
                SessionEventsState sessionEventsState2 = AppEventsLogger.stateMap.get(accessTokenAppIdPair);
                Label_0085: {
                    if (sessionEventsState2 != null) {
                        break Label_0085;
                    }
                    final SessionEventsState sessionEventsState3 = new SessionEventsState(attributionIdentifiers, context.getPackageName(), getAnonymousAppDeviceGUID(context));
                    try {
                        AppEventsLogger.stateMap.put(accessTokenAppIdPair, sessionEventsState3);
                        sessionEventsState2 = sessionEventsState3;
                        // monitorexit(staticLock)
                        return sessionEventsState2;
                        // monitorexit(staticLock)
                        throw;
                    }
                    finally {}
                }
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    private static SessionEventsState getSessionEventsState(final AccessTokenAppIdPair accessTokenAppIdPair) {
        synchronized (AppEventsLogger.staticLock) {
            return AppEventsLogger.stateMap.get(accessTokenAppIdPair);
        }
    }
    
    static String getSourceApplication() {
        String string = "Unclassified";
        if (AppEventsLogger.isOpenedByApplink) {
            string = "Applink";
        }
        if (AppEventsLogger.sourceApplication != null) {
            string = string + "(" + AppEventsLogger.sourceApplication + ")";
        }
        return string;
    }
    
    private static void handleResponse(final AccessTokenAppIdPair accessTokenAppIdPair, final GraphRequest graphRequest, final GraphResponse graphResponse, final SessionEventsState sessionEventsState, final FlushStatistics flushStatistics) {
        final FacebookRequestError error = graphResponse.getError();
        String format = "Success";
        FlushResult result = FlushResult.SUCCESS;
        while (true) {
            while (true) {
                Label_0040: {
                    if (error == null) {
                        break Label_0040;
                    }
                    if (error.getErrorCode() == -1) {
                        format = "Failed: No Connectivity";
                        result = FlushResult.NO_CONNECTIVITY;
                        break Label_0040;
                    }
                    Label_0185: {
                        break Label_0185;
                    Label_0136_Outer:
                        while (true) {
                            final String s = (String)graphRequest.getTag();
                            while (true) {
                            Label_0236:
                                while (true) {
                                    try {
                                        final String string = new JSONArray(s).toString(2);
                                        Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", graphRequest.getGraphObject().toString(), format, string);
                                        if (error != null) {
                                            final boolean b = true;
                                            sessionEventsState.clearInFlightAndStats(b);
                                            if (result == FlushResult.NO_CONNECTIVITY) {
                                                PersistedEvents.persistEvents(AppEventsLogger.applicationContext, accessTokenAppIdPair, sessionEventsState);
                                            }
                                            if (result != FlushResult.SUCCESS && flushStatistics.result != FlushResult.NO_CONNECTIVITY) {
                                                flushStatistics.result = result;
                                            }
                                            return;
                                        }
                                        break Label_0236;
                                        format = String.format("Failed:\n  Response: %s\n  Error %s", graphResponse.toString(), error.toString());
                                        result = FlushResult.SERVER_ERROR;
                                        break;
                                    }
                                    catch (JSONException ex) {
                                        final String string = "<Can't encode events for debug logging>";
                                        continue Label_0136_Outer;
                                    }
                                    break;
                                }
                                final boolean b = false;
                                continue;
                            }
                        }
                    }
                }
                if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.APP_EVENTS)) {
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private static void initializeTimersIfNeeded() {
        synchronized (AppEventsLogger.staticLock) {
            if (AppEventsLogger.backgroundExecutor != null) {
                return;
            }
            AppEventsLogger.backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            // monitorexit(AppEventsLogger.staticLock)
            AppEventsLogger.backgroundExecutor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                        flushAndWait(FlushReason.TIMER);
                    }
                }
            }, 0L, 15L, TimeUnit.SECONDS);
            AppEventsLogger.backgroundExecutor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    final HashSet<String> set = new HashSet<String>();
                    synchronized (AppEventsLogger.staticLock) {
                        final Iterator<AccessTokenAppIdPair> iterator = AppEventsLogger.stateMap.keySet().iterator();
                        while (iterator.hasNext()) {
                            set.add(iterator.next().getApplicationId());
                        }
                    }
                    // monitorexit(o)
                    final Iterator<Object> iterator2 = set.iterator();
                    while (iterator2.hasNext()) {
                        Utility.queryAppSettings(iterator2.next(), true);
                    }
                }
            }, 0L, 86400L, TimeUnit.SECONDS);
        }
    }
    
    private void logAppSessionResumeEvent(final long n, final String s) {
        PersistedAppSessionInfo.onResume(AppEventsLogger.applicationContext, this.accessTokenAppId, this, n, s);
    }
    
    private void logAppSessionSuspendEvent(final long n) {
        PersistedAppSessionInfo.onSuspend(AppEventsLogger.applicationContext, this.accessTokenAppId, this, n);
    }
    
    private static void logEvent(final Context context, final AppEvent appEvent, final AccessTokenAppIdPair accessTokenAppIdPair) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                getSessionEventsState(context, accessTokenAppIdPair).addEvent(appEvent);
                flushIfNecessary();
            }
        });
        if (!appEvent.isImplicit && !AppEventsLogger.isActivateAppEventRequested) {
            if (appEvent.getName() != "fb_mobile_activate_app") {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
                return;
            }
            AppEventsLogger.isActivateAppEventRequested = true;
        }
    }
    
    private void logEvent(final String s, final Double n, final Bundle bundle, final boolean b) {
        logEvent(AppEventsLogger.applicationContext, new AppEvent(this.contextName, s, n, bundle, b), this.accessTokenAppId);
    }
    
    public static AppEventsLogger newLogger(final Context context) {
        return new AppEventsLogger(context, null, null);
    }
    
    public static AppEventsLogger newLogger(final Context context, final AccessToken accessToken) {
        return new AppEventsLogger(context, null, accessToken);
    }
    
    public static AppEventsLogger newLogger(final Context context, final String s) {
        return new AppEventsLogger(context, s, null);
    }
    
    public static AppEventsLogger newLogger(final Context context, final String s, final AccessToken accessToken) {
        return new AppEventsLogger(context, s, accessToken);
    }
    
    private static void notifyDeveloperError(final String s) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", s);
    }
    
    public static void onContextStop() {
        PersistedEvents.persistEvents(AppEventsLogger.applicationContext, AppEventsLogger.stateMap);
    }
    
    static void resetSourceApplication() {
        AppEventsLogger.sourceApplication = null;
        AppEventsLogger.isOpenedByApplink = false;
    }
    
    public static void setFlushBehavior(final FlushBehavior flushBehavior) {
        synchronized (AppEventsLogger.staticLock) {
            AppEventsLogger.flushBehavior = flushBehavior;
        }
    }
    
    private static void setSourceApplication(final Activity activity) {
        final ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            final String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            AppEventsLogger.sourceApplication = packageName;
        }
        final Intent intent = activity.getIntent();
        if (intent == null || intent.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)) {
            resetSourceApplication();
            return;
        }
        final Bundle appLinkData = AppLinks.getAppLinkData(intent);
        if (appLinkData == null) {
            resetSourceApplication();
            return;
        }
        AppEventsLogger.isOpenedByApplink = true;
        final Bundle bundle = appLinkData.getBundle("referer_app_link");
        if (bundle == null) {
            AppEventsLogger.sourceApplication = null;
            return;
        }
        AppEventsLogger.sourceApplication = bundle.getString("package");
        intent.putExtra("_fbSourceApplicationHasBeenSet", true);
    }
    
    static void setSourceApplication(final String sourceApplication, final boolean isOpenedByApplink) {
        AppEventsLogger.sourceApplication = sourceApplication;
        AppEventsLogger.isOpenedByApplink = isOpenedByApplink;
    }
    
    public void flush() {
        flush(FlushReason.EXPLICIT);
    }
    
    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }
    
    public boolean isValidForAccessToken(final AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }
    
    public void logEvent(final String s) {
        this.logEvent(s, null);
    }
    
    public void logEvent(final String s, final double n) {
        this.logEvent(s, n, null);
    }
    
    public void logEvent(final String s, final double n, final Bundle bundle) {
        this.logEvent(s, n, bundle, false);
    }
    
    public void logEvent(final String s, final Bundle bundle) {
        this.logEvent(s, null, bundle, false);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency) {
        this.logPurchase(bigDecimal, currency, null);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency, Bundle bundle) {
        if (bigDecimal == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
            return;
        }
        if (currency == null) {
            notifyDeveloperError("currency cannot be null");
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("fb_currency", currency.getCurrencyCode());
        this.logEvent("fb_mobile_purchase", bigDecimal.doubleValue(), bundle);
        eagerFlush();
    }
    
    public void logSdkEvent(final String s, final Double n, final Bundle bundle) {
        this.logEvent(s, n, bundle, true);
    }
    
    private static class AccessTokenAppIdPair implements Serializable
    {
        private static final long serialVersionUID = 1L;
        private final String accessTokenString;
        private final String applicationId;
        
        AccessTokenAppIdPair(final AccessToken accessToken) {
            this(accessToken.getToken(), FacebookSdk.getApplicationId());
        }
        
        AccessTokenAppIdPair(String accessTokenString, final String applicationId) {
            if (Utility.isNullOrEmpty(accessTokenString)) {
                accessTokenString = null;
            }
            this.accessTokenString = accessTokenString;
            this.applicationId = applicationId;
        }
        
        private Object writeReplace() {
            return new SerializationProxyV1(this.accessTokenString, this.applicationId);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof AccessTokenAppIdPair) {
                final AccessTokenAppIdPair accessTokenAppIdPair = (AccessTokenAppIdPair)o;
                if (Utility.areObjectsEqual(accessTokenAppIdPair.accessTokenString, this.accessTokenString) && Utility.areObjectsEqual(accessTokenAppIdPair.applicationId, this.applicationId)) {
                    return true;
                }
            }
            return false;
        }
        
        String getAccessTokenString() {
            return this.accessTokenString;
        }
        
        String getApplicationId() {
            return this.applicationId;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.accessTokenString == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.accessTokenString.hashCode();
            }
            final String applicationId = this.applicationId;
            int hashCode2 = 0;
            if (applicationId != null) {
                hashCode2 = this.applicationId.hashCode();
            }
            return hashCode ^ hashCode2;
        }
        
        private static class SerializationProxyV1 implements Serializable
        {
            private static final long serialVersionUID = -2488473066578201069L;
            private final String accessTokenString;
            private final String appId;
            
            private SerializationProxyV1(final String accessTokenString, final String appId) {
                this.accessTokenString = accessTokenString;
                this.appId = appId;
            }
            
            private Object readResolve() {
                return new AccessTokenAppIdPair(this.accessTokenString, this.appId);
            }
        }
    }
    
    static class AppEvent implements Serializable
    {
        private static final long serialVersionUID = 1L;
        private static final HashSet<String> validatedIdentifiers;
        private boolean isImplicit;
        private JSONObject jsonObject;
        private String name;
        
        static {
            validatedIdentifiers = new HashSet<String>();
        }
        
        public AppEvent(final String s, final String name, final Double n, final Bundle bundle, final boolean isImplicit) {
            try {
                this.validateIdentifier(name);
                this.name = name;
                this.isImplicit = isImplicit;
                (this.jsonObject = new JSONObject()).put("_eventName", (Object)name);
                this.jsonObject.put("_logTime", System.currentTimeMillis() / 1000L);
                this.jsonObject.put("_ui", (Object)s);
                if (n != null) {
                    this.jsonObject.put("_valueToSum", (double)n);
                }
                if (this.isImplicit) {
                    this.jsonObject.put("_implicitlyLogged", (Object)"1");
                }
                if (bundle != null) {
                    final Iterator<String> iterator = bundle.keySet().iterator();
                    if (iterator.hasNext()) {
                        final String s2 = iterator.next();
                        this.validateIdentifier(s2);
                        final Object value = bundle.get(s2);
                        if (!(value instanceof String) && !(value instanceof Number)) {
                            throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", value, s2));
                        }
                        goto Label_0243;
                    }
                }
            }
            catch (JSONException ex) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", ex.toString());
                this.jsonObject = null;
            }
            catch (FacebookException ex2) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", ex2.toString());
                this.jsonObject = null;
                return;
            }
            if (!this.isImplicit) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", this.jsonObject.toString());
                return;
            }
            goto Label_0242;
        }
        
        private AppEvent(final String s, final boolean isImplicit) throws JSONException {
            this.jsonObject = new JSONObject(s);
            this.isImplicit = isImplicit;
        }
        
        private void validateIdentifier(final String p0) throws FacebookException {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnull          20
            //     4: aload_1        
            //     5: invokevirtual   java/lang/String.length:()I
            //     8: ifeq            20
            //    11: aload_1        
            //    12: invokevirtual   java/lang/String.length:()I
            //    15: bipush          40
            //    17: if_icmple       63
            //    20: aload_1        
            //    21: ifnonnull       27
            //    24: ldc             "<None Provided>"
            //    26: astore_1       
            //    27: getstatic       java/util/Locale.ROOT:Ljava/util/Locale;
            //    30: astore_2       
            //    31: iconst_2       
            //    32: anewarray       Ljava/lang/Object;
            //    35: astore_3       
            //    36: aload_3        
            //    37: iconst_0       
            //    38: aload_1        
            //    39: aastore        
            //    40: aload_3        
            //    41: iconst_1       
            //    42: bipush          40
            //    44: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
            //    47: aastore        
            //    48: new             Lcom/facebook/FacebookException;
            //    51: dup            
            //    52: aload_2        
            //    53: ldc             "Identifier '%s' must be less than %d characters"
            //    55: aload_3        
            //    56: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    59: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
            //    62: athrow         
            //    63: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
            //    66: astore          4
            //    68: aload           4
            //    70: monitorenter   
            //    71: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
            //    74: aload_1        
            //    75: invokevirtual   java/util/HashSet.contains:(Ljava/lang/Object;)Z
            //    78: istore          6
            //    80: aload           4
            //    82: monitorexit    
            //    83: iload           6
            //    85: ifne            116
            //    88: aload_1        
            //    89: ldc             "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$"
            //    91: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
            //    94: ifeq            133
            //    97: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
            //   100: astore          7
            //   102: aload           7
            //   104: monitorenter   
            //   105: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
            //   108: aload_1        
            //   109: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
            //   112: pop            
            //   113: aload           7
            //   115: monitorexit    
            //   116: return         
            //   117: astore          5
            //   119: aload           4
            //   121: monitorexit    
            //   122: aload           5
            //   124: athrow         
            //   125: astore          8
            //   127: aload           7
            //   129: monitorexit    
            //   130: aload           8
            //   132: athrow         
            //   133: new             Lcom/facebook/FacebookException;
            //   136: dup            
            //   137: ldc             "Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen."
            //   139: iconst_1       
            //   140: anewarray       Ljava/lang/Object;
            //   143: dup            
            //   144: iconst_0       
            //   145: aload_1        
            //   146: aastore        
            //   147: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   150: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
            //   153: athrow         
            //    Exceptions:
            //  throws com.facebook.FacebookException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  71     83     117    125    Any
            //  105    116    125    133    Any
            //  119    122    117    125    Any
            //  127    130    125    133    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        private Object writeReplace() {
            return new SerializationProxyV1(this.jsonObject.toString(), this.isImplicit);
        }
        
        public boolean getIsImplicit() {
            return this.isImplicit;
        }
        
        public JSONObject getJSONObject() {
            return this.jsonObject;
        }
        
        public String getName() {
            return this.name;
        }
        
        @Override
        public String toString() {
            return String.format("\"%s\", implicit: %b, json: %s", this.jsonObject.optString("_eventName"), this.isImplicit, this.jsonObject.toString());
        }
        
        private static class SerializationProxyV1 implements Serializable
        {
            private static final long serialVersionUID = -2488473066578201069L;
            private final boolean isImplicit;
            private final String jsonString;
            
            private SerializationProxyV1(final String jsonString, final boolean isImplicit) {
                this.jsonString = jsonString;
                this.isImplicit = isImplicit;
            }
            
            private Object readResolve() throws JSONException {
                return new AppEvent(this.jsonString, this.isImplicit);
            }
        }
    }
    
    public enum FlushBehavior
    {
        AUTO, 
        EXPLICIT_ONLY;
    }
    
    private enum FlushReason
    {
        EAGER_FLUSHING_EVENT, 
        EVENT_THRESHOLD, 
        EXPLICIT, 
        PERSISTED_EVENTS, 
        SESSION_CHANGE, 
        TIMER;
    }
    
    private enum FlushResult
    {
        NO_CONNECTIVITY, 
        SERVER_ERROR, 
        SUCCESS, 
        UNKNOWN_ERROR;
    }
    
    private static class FlushStatistics
    {
        public int numEvents;
        public FlushResult result;
        
        private FlushStatistics() {
            this.numEvents = 0;
            this.result = FlushResult.SUCCESS;
        }
    }
    
    static class PersistedAppSessionInfo
    {
        private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
        private static final Runnable appSessionInfoFlushRunnable;
        private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
        private static boolean hasChanges;
        private static boolean isLoaded;
        private static final Object staticLock;
        
        static {
            staticLock = new Object();
            PersistedAppSessionInfo.hasChanges = false;
            PersistedAppSessionInfo.isLoaded = false;
            appSessionInfoFlushRunnable = new Runnable() {
                @Override
                public void run() {
                    PersistedAppSessionInfo.saveAppSessionInformation(AppEventsLogger.applicationContext);
                }
            };
        }
        
        private static FacebookTimeSpentData getTimeSpentData(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair) {
            restoreAppSessionInformation(context);
            FacebookTimeSpentData facebookTimeSpentData = PersistedAppSessionInfo.appSessionInfoMap.get(accessTokenAppIdPair);
            if (facebookTimeSpentData == null) {
                facebookTimeSpentData = new FacebookTimeSpentData();
                PersistedAppSessionInfo.appSessionInfoMap.put(accessTokenAppIdPair, facebookTimeSpentData);
            }
            return facebookTimeSpentData;
        }
        
        static void onResume(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final AppEventsLogger appEventsLogger, final long n, final String s) {
            synchronized (PersistedAppSessionInfo.staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onResume(appEventsLogger, n, s);
                onTimeSpentDataUpdate();
            }
        }
        
        static void onSuspend(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final AppEventsLogger appEventsLogger, final long n) {
            synchronized (PersistedAppSessionInfo.staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onSuspend(appEventsLogger, n);
                onTimeSpentDataUpdate();
            }
        }
        
        private static void onTimeSpentDataUpdate() {
            if (!PersistedAppSessionInfo.hasChanges) {
                PersistedAppSessionInfo.hasChanges = true;
                AppEventsLogger.backgroundExecutor.schedule(PersistedAppSessionInfo.appSessionInfoFlushRunnable, 30L, TimeUnit.SECONDS);
            }
        }
        
        private static void restoreAppSessionInformation(final Context p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aconst_null    
            //     1: astore_1       
            //     2: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
            //     5: astore_2       
            //     6: aload_2        
            //     7: monitorenter   
            //     8: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //    11: istore          4
            //    13: iload           4
            //    15: ifne            90
            //    18: new             Ljava/io/ObjectInputStream;
            //    21: dup            
            //    22: aload_0        
            //    23: ldc             "AppEventsLogger.persistedsessioninfo"
            //    25: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
            //    28: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
            //    31: astore          5
            //    33: aload           5
            //    35: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
            //    38: checkcast       Ljava/util/HashMap;
            //    41: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    44: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
            //    47: ldc             "AppEvents"
            //    49: ldc             "App session info loaded"
            //    51: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
            //    54: aload           5
            //    56: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    59: aload_0        
            //    60: ldc             "AppEventsLogger.persistedsessioninfo"
            //    62: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //    65: pop            
            //    66: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    69: ifnonnull       82
            //    72: new             Ljava/util/HashMap;
            //    75: dup            
            //    76: invokespecial   java/util/HashMap.<init>:()V
            //    79: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    82: iconst_1       
            //    83: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //    86: iconst_0       
            //    87: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //    90: aload_2        
            //    91: monitorexit    
            //    92: return         
            //    93: aload           7
            //    95: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    98: aload_0        
            //    99: ldc             "AppEventsLogger.persistedsessioninfo"
            //   101: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //   104: pop            
            //   105: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   108: ifnonnull       121
            //   111: new             Ljava/util/HashMap;
            //   114: dup            
            //   115: invokespecial   java/util/HashMap.<init>:()V
            //   118: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   121: iconst_1       
            //   122: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //   125: iconst_0       
            //   126: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //   129: goto            90
            //   132: aload_2        
            //   133: monitorexit    
            //   134: aload_3        
            //   135: athrow         
            //   136: astore          9
            //   138: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
            //   141: new             Ljava/lang/StringBuilder;
            //   144: dup            
            //   145: invokespecial   java/lang/StringBuilder.<init>:()V
            //   148: ldc             "Got unexpected exception: "
            //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   153: aload           9
            //   155: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   161: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   164: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   167: pop            
            //   168: aload_1        
            //   169: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   172: aload_0        
            //   173: ldc             "AppEventsLogger.persistedsessioninfo"
            //   175: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //   178: pop            
            //   179: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   182: ifnonnull       195
            //   185: new             Ljava/util/HashMap;
            //   188: dup            
            //   189: invokespecial   java/util/HashMap.<init>:()V
            //   192: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   195: iconst_1       
            //   196: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //   199: iconst_0       
            //   200: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //   203: goto            90
            //   206: aload_1        
            //   207: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   210: aload_0        
            //   211: ldc             "AppEventsLogger.persistedsessioninfo"
            //   213: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //   216: pop            
            //   217: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   220: ifnonnull       233
            //   223: new             Ljava/util/HashMap;
            //   226: dup            
            //   227: invokespecial   java/util/HashMap.<init>:()V
            //   230: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   233: iconst_1       
            //   234: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //   237: iconst_0       
            //   238: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //   241: aload           10
            //   243: athrow         
            //   244: astore_3       
            //   245: goto            132
            //   248: astore          10
            //   250: aload           5
            //   252: astore_1       
            //   253: goto            206
            //   256: astore          9
            //   258: aload           5
            //   260: astore_1       
            //   261: goto            138
            //   264: astore          6
            //   266: aload           5
            //   268: astore          7
            //   270: goto            93
            //   273: astore          15
            //   275: aconst_null    
            //   276: astore          7
            //   278: goto            93
            //   281: astore_3       
            //   282: goto            132
            //   285: astore          10
            //   287: goto            206
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                           
            //  -----  -----  -----  -----  -------------------------------
            //  8      13     281    285    Any
            //  18     33     273    281    Ljava/io/FileNotFoundException;
            //  18     33     136    138    Ljava/lang/Exception;
            //  18     33     285    290    Any
            //  33     54     264    273    Ljava/io/FileNotFoundException;
            //  33     54     256    264    Ljava/lang/Exception;
            //  33     54     248    256    Any
            //  54     82     244    248    Any
            //  82     90     244    248    Any
            //  90     92     281    285    Any
            //  93     121    281    285    Any
            //  121    129    281    285    Any
            //  132    134    281    285    Any
            //  138    168    285    290    Any
            //  168    195    281    285    Any
            //  195    203    281    285    Any
            //  206    233    281    285    Any
            //  233    244    281    285    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        static void saveAppSessionInformation(final Context p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aconst_null    
            //     1: astore_1       
            //     2: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
            //     5: astore_2       
            //     6: aload_2        
            //     7: monitorenter   
            //     8: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //    11: istore          4
            //    13: iload           4
            //    15: ifeq            68
            //    18: new             Ljava/io/ObjectOutputStream;
            //    21: dup            
            //    22: new             Ljava/io/BufferedOutputStream;
            //    25: dup            
            //    26: aload_0        
            //    27: ldc             "AppEventsLogger.persistedsessioninfo"
            //    29: iconst_0       
            //    30: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
            //    33: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
            //    36: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
            //    39: astore          5
            //    41: aload           5
            //    43: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    46: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
            //    49: iconst_0       
            //    50: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //    53: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
            //    56: ldc             "AppEvents"
            //    58: ldc             "App session info saved"
            //    60: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
            //    63: aload           5
            //    65: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    68: aload_2        
            //    69: monitorexit    
            //    70: return         
            //    71: astore          6
            //    73: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
            //    76: new             Ljava/lang/StringBuilder;
            //    79: dup            
            //    80: invokespecial   java/lang/StringBuilder.<init>:()V
            //    83: ldc             "Got unexpected exception: "
            //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    88: aload           6
            //    90: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    96: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    99: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //   102: pop            
            //   103: aload_1        
            //   104: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   107: goto            68
            //   110: aload_2        
            //   111: monitorexit    
            //   112: aload_3        
            //   113: athrow         
            //   114: astore          7
            //   116: aload_1        
            //   117: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   120: aload           7
            //   122: athrow         
            //   123: astore_3       
            //   124: goto            110
            //   127: astore          7
            //   129: aload           5
            //   131: astore_1       
            //   132: goto            116
            //   135: astore          6
            //   137: aload           5
            //   139: astore_1       
            //   140: goto            73
            //   143: astore_3       
            //   144: goto            110
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  8      13     143    147    Any
            //  18     41     71     73     Ljava/lang/Exception;
            //  18     41     114    116    Any
            //  41     63     135    143    Ljava/lang/Exception;
            //  41     63     127    135    Any
            //  63     68     123    127    Any
            //  68     70     143    147    Any
            //  73     103    114    116    Any
            //  103    107    143    147    Any
            //  110    112    143    147    Any
            //  116    123    143    147    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
    
    static class PersistedEvents
    {
        static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
        private static Object staticLock;
        private Context context;
        private HashMap<AccessTokenAppIdPair, List<AppEvent>> persistedEvents;
        
        static {
            PersistedEvents.staticLock = new Object();
        }
        
        private PersistedEvents(final Context context) {
            this.persistedEvents = new HashMap<AccessTokenAppIdPair, List<AppEvent>>();
            this.context = context;
        }
        
        public static void persistEvents(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final SessionEventsState sessionEventsState) {
            final HashMap<AccessTokenAppIdPair, SessionEventsState> hashMap = new HashMap<AccessTokenAppIdPair, SessionEventsState>();
            hashMap.put(accessTokenAppIdPair, sessionEventsState);
            persistEvents(context, hashMap);
        }
        
        public static void persistEvents(final Context context, final Map<AccessTokenAppIdPair, SessionEventsState> map) {
            final PersistedEvents andClearStore;
            synchronized (PersistedEvents.staticLock) {
                andClearStore = readAndClearStore(context);
                for (final Map.Entry<AccessTokenAppIdPair, SessionEventsState> entry : map.entrySet()) {
                    final List<AppEvent> eventsToPersist = entry.getValue().getEventsToPersist();
                    if (eventsToPersist.size() != 0) {
                        andClearStore.addEvents(entry.getKey(), eventsToPersist);
                    }
                }
            }
            andClearStore.write();
        }
        // monitorexit(o)
        
        public static PersistedEvents readAndClearStore(final Context context) {
            synchronized (PersistedEvents.staticLock) {
                final PersistedEvents persistedEvents = new PersistedEvents(context);
                persistedEvents.readAndClearStore();
                return persistedEvents;
            }
        }
        
        private void readAndClearStore() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aconst_null    
            //     1: astore_1       
            //     2: new             Ljava/io/ObjectInputStream;
            //     5: dup            
            //     6: new             Ljava/io/BufferedInputStream;
            //     9: dup            
            //    10: aload_0        
            //    11: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
            //    14: ldc             "AppEventsLogger.persistedevents"
            //    16: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
            //    19: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
            //    22: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
            //    25: astore_2       
            //    26: aload_2        
            //    27: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
            //    30: checkcast       Ljava/util/HashMap;
            //    33: astore          7
            //    35: aload_0        
            //    36: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
            //    39: ldc             "AppEventsLogger.persistedevents"
            //    41: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
            //    44: invokevirtual   java/io/File.delete:()Z
            //    47: pop            
            //    48: aload_0        
            //    49: aload           7
            //    51: putfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.persistedEvents:Ljava/util/HashMap;
            //    54: aload_2        
            //    55: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    58: return         
            //    59: astore          9
            //    61: aload_1        
            //    62: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    65: return         
            //    66: astore          4
            //    68: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
            //    71: new             Ljava/lang/StringBuilder;
            //    74: dup            
            //    75: invokespecial   java/lang/StringBuilder.<init>:()V
            //    78: ldc             "Got unexpected exception: "
            //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    83: aload           4
            //    85: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    91: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    94: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //    97: pop            
            //    98: aload_1        
            //    99: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   102: return         
            //   103: astore          5
            //   105: aload_1        
            //   106: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   109: aload           5
            //   111: athrow         
            //   112: astore          5
            //   114: aload_2        
            //   115: astore_1       
            //   116: goto            105
            //   119: astore          4
            //   121: aload_2        
            //   122: astore_1       
            //   123: goto            68
            //   126: astore_3       
            //   127: aload_2        
            //   128: astore_1       
            //   129: goto            61
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                           
            //  -----  -----  -----  -----  -------------------------------
            //  2      26     59     61     Ljava/io/FileNotFoundException;
            //  2      26     66     68     Ljava/lang/Exception;
            //  2      26     103    105    Any
            //  26     54     126    132    Ljava/io/FileNotFoundException;
            //  26     54     119    126    Ljava/lang/Exception;
            //  26     54     112    119    Any
            //  68     98     103    105    Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        private void write() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aconst_null    
            //     1: astore_1       
            //     2: new             Ljava/io/ObjectOutputStream;
            //     5: dup            
            //     6: new             Ljava/io/BufferedOutputStream;
            //     9: dup            
            //    10: aload_0        
            //    11: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.context:Landroid/content/Context;
            //    14: ldc             "AppEventsLogger.persistedevents"
            //    16: iconst_0       
            //    17: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
            //    20: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
            //    23: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
            //    26: astore_2       
            //    27: aload_2        
            //    28: aload_0        
            //    29: getfield        com/facebook/appevents/AppEventsLogger$PersistedEvents.persistedEvents:Ljava/util/HashMap;
            //    32: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
            //    35: aload_2        
            //    36: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    39: return         
            //    40: astore_3       
            //    41: invokestatic    com/facebook/appevents/AppEventsLogger.access$1400:()Ljava/lang/String;
            //    44: new             Ljava/lang/StringBuilder;
            //    47: dup            
            //    48: invokespecial   java/lang/StringBuilder.<init>:()V
            //    51: ldc             "Got unexpected exception: "
            //    53: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    56: aload_3        
            //    57: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    63: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    66: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
            //    69: pop            
            //    70: aload_1        
            //    71: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    74: return         
            //    75: astore          4
            //    77: aload_1        
            //    78: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    81: aload           4
            //    83: athrow         
            //    84: astore          4
            //    86: aload_2        
            //    87: astore_1       
            //    88: goto            77
            //    91: astore_3       
            //    92: aload_2        
            //    93: astore_1       
            //    94: goto            41
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  2      27     40     41     Ljava/lang/Exception;
            //  2      27     75     77     Any
            //  27     35     91     97     Ljava/lang/Exception;
            //  27     35     84     91     Any
            //  41     70     75     77     Any
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public void addEvents(final AccessTokenAppIdPair accessTokenAppIdPair, final List<AppEvent> list) {
            if (!this.persistedEvents.containsKey(accessTokenAppIdPair)) {
                this.persistedEvents.put(accessTokenAppIdPair, new ArrayList<AppEvent>());
            }
            this.persistedEvents.get(accessTokenAppIdPair).addAll(list);
        }
        
        public List<AppEvent> getEvents(final AccessTokenAppIdPair accessTokenAppIdPair) {
            return this.persistedEvents.get(accessTokenAppIdPair);
        }
        
        public Set<AccessTokenAppIdPair> keySet() {
            return this.persistedEvents.keySet();
        }
    }
    
    static class SessionEventsState
    {
        public static final String ENCODED_EVENTS_KEY = "encoded_events";
        public static final String EVENT_COUNT_KEY = "event_count";
        public static final String NUM_SKIPPED_KEY = "num_skipped";
        private final int MAX_ACCUMULATED_LOG_EVENTS;
        private List<AppEvent> accumulatedEvents;
        private String anonymousAppDeviceGUID;
        private AttributionIdentifiers attributionIdentifiers;
        private List<AppEvent> inFlightEvents;
        private int numSkippedEventsDueToFullBuffer;
        private String packageName;
        
        public SessionEventsState(final AttributionIdentifiers attributionIdentifiers, final String packageName, final String anonymousAppDeviceGUID) {
            this.accumulatedEvents = new ArrayList<AppEvent>();
            this.inFlightEvents = new ArrayList<AppEvent>();
            this.MAX_ACCUMULATED_LOG_EVENTS = 1000;
            this.attributionIdentifiers = attributionIdentifiers;
            this.packageName = packageName;
            this.anonymousAppDeviceGUID = anonymousAppDeviceGUID;
        }
        
        private byte[] getStringAsByteArray(final String s) {
            try {
                return s.getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException ex) {
                Utility.logd("Encoding exception: ", ex);
                return null;
            }
        }
        
        private void populateRequest(final GraphRequest graphRequest, final int n, final JSONArray jsonArray, final boolean b) {
            while (true) {
                try {
                    final JSONObject jsonObjectForGraphAPICall = AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.CUSTOM_APP_EVENTS, this.attributionIdentifiers, this.anonymousAppDeviceGUID, b, AppEventsLogger.applicationContext);
                    if (this.numSkippedEventsDueToFullBuffer > 0) {
                        jsonObjectForGraphAPICall.put("num_skipped_events", n);
                    }
                    graphRequest.setGraphObject(jsonObjectForGraphAPICall);
                    Bundle parameters = graphRequest.getParameters();
                    if (parameters == null) {
                        parameters = new Bundle();
                    }
                    final String string = jsonArray.toString();
                    if (string != null) {
                        parameters.putByteArray("custom_events_file", this.getStringAsByteArray(string));
                        graphRequest.setTag(string);
                    }
                    graphRequest.setParameters(parameters);
                }
                catch (JSONException ex) {
                    final JSONObject jsonObjectForGraphAPICall = new JSONObject();
                    continue;
                }
                break;
            }
        }
        
        public void accumulatePersistedEvents(final List<AppEvent> list) {
            synchronized (this) {
                this.accumulatedEvents.addAll(list);
            }
        }
        
        public void addEvent(final AppEvent appEvent) {
            synchronized (this) {
                if (this.accumulatedEvents.size() + this.inFlightEvents.size() >= 1000) {
                    ++this.numSkippedEventsDueToFullBuffer;
                }
                else {
                    this.accumulatedEvents.add(appEvent);
                }
            }
        }
        
        public void clearInFlightAndStats(final boolean b) {
            // monitorenter(this)
            Label_0020: {
                if (!b) {
                    break Label_0020;
                }
                try {
                    this.accumulatedEvents.addAll(this.inFlightEvents);
                    this.inFlightEvents.clear();
                    this.numSkippedEventsDueToFullBuffer = 0;
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        public int getAccumulatedEventCount() {
            synchronized (this) {
                return this.accumulatedEvents.size();
            }
        }
        
        public List<AppEvent> getEventsToPersist() {
            synchronized (this) {
                final List<AppEvent> accumulatedEvents = this.accumulatedEvents;
                this.accumulatedEvents = new ArrayList<AppEvent>();
                return accumulatedEvents;
            }
        }
        
        public int populateRequest(final GraphRequest graphRequest, final boolean b, final boolean b2) {
            final int numSkippedEventsDueToFullBuffer;
            final JSONArray jsonArray;
            synchronized (this) {
                numSkippedEventsDueToFullBuffer = this.numSkippedEventsDueToFullBuffer;
                this.inFlightEvents.addAll(this.accumulatedEvents);
                this.accumulatedEvents.clear();
                jsonArray = new JSONArray();
                for (final AppEvent appEvent : this.inFlightEvents) {
                    if (b || !appEvent.getIsImplicit()) {
                        jsonArray.put((Object)appEvent.getJSONObject());
                    }
                }
            }
            if (jsonArray.length() == 0) {
                // monitorexit(this)
                return 0;
            }
            // monitorexit(this)
            this.populateRequest(graphRequest, numSkippedEventsDueToFullBuffer, jsonArray, b2);
            return jsonArray.length();
        }
    }
}
