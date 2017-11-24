package com.facebook;

import android.support.v4.content.*;
import java.util.concurrent.atomic.*;
import com.facebook.internal.*;
import java.util.*;
import android.util.*;
import org.json.*;
import android.content.*;
import android.os.*;

final class AccessTokenManager
{
    static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    static final String TAG = "AccessTokenManager";
    private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate;
    private final LocalBroadcastManager localBroadcastManager;
    private AtomicBoolean tokenRefreshInProgress;
    
    AccessTokenManager(final LocalBroadcastManager localBroadcastManager, final AccessTokenCache accessTokenCache) {
        this.tokenRefreshInProgress = new AtomicBoolean(false);
        this.lastAttemptedTokenExtendDate = new Date(0L);
        Validate.notNull(localBroadcastManager, "localBroadcastManager");
        Validate.notNull(accessTokenCache, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager;
        this.accessTokenCache = accessTokenCache;
    }
    
    private static GraphRequest createExtendAccessTokenRequest(final AccessToken accessToken, final GraphRequest.Callback callback) {
        final Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, "oauth/access_token", bundle, HttpMethod.GET, callback);
    }
    
    private static GraphRequest createGrantedPermissionsRequest(final AccessToken accessToken, final GraphRequest.Callback callback) {
        return new GraphRequest(accessToken, "me/permissions", new Bundle(), HttpMethod.GET, callback);
    }
    
    static AccessTokenManager getInstance() {
        Label_0041: {
            if (AccessTokenManager.instance != null) {
                break Label_0041;
            }
            synchronized (AccessTokenManager.class) {
                if (AccessTokenManager.instance == null) {
                    AccessTokenManager.instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
                return AccessTokenManager.instance;
            }
        }
    }
    
    private void refreshCurrentAccessTokenImpl(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        final AccessToken currentAccessToken = this.currentAccessToken;
        if (currentAccessToken == null) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        }
        else {
            if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
                this.lastAttemptedTokenExtendDate = new Date();
                final HashSet set = new HashSet();
                final HashSet set2 = new HashSet();
                final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                final RefreshResult refreshResult = new RefreshResult();
                final GraphRequestBatch graphRequestBatch = new GraphRequestBatch(new GraphRequest[] { createGrantedPermissionsRequest(currentAccessToken, new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(final GraphResponse graphResponse) {
                            final JSONObject jsonObject = graphResponse.getJSONObject();
                            if (jsonObject != null) {
                                final JSONArray optJSONArray = jsonObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    atomicBoolean.set(true);
                                    for (int i = 0; i < optJSONArray.length(); ++i) {
                                        final JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                        if (optJSONObject != null) {
                                            final String optString = optJSONObject.optString("permission");
                                            final String optString2 = optJSONObject.optString("status");
                                            if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                                                final String lowerCase = optString2.toLowerCase(Locale.US);
                                                if (lowerCase.equals("granted")) {
                                                    set.add(optString);
                                                }
                                                else if (lowerCase.equals("declined")) {
                                                    set2.add(optString);
                                                }
                                                else {
                                                    Log.w("AccessTokenManager", "Unexpected status: " + lowerCase);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }), createExtendAccessTokenRequest(currentAccessToken, new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(final GraphResponse graphResponse) {
                            final JSONObject jsonObject = graphResponse.getJSONObject();
                            if (jsonObject == null) {
                                return;
                            }
                            refreshResult.accessToken = jsonObject.optString("access_token");
                            refreshResult.expiresAt = jsonObject.optInt("expires_at");
                        }
                    }) });
                graphRequestBatch.addCallback((GraphRequestBatch.Callback)new GraphRequestBatch.Callback() {
                    @Override
                    public void onBatchCompleted(final GraphRequestBatch p0) {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     0: invokestatic    com/facebook/AccessTokenManager.getInstance:()Lcom/facebook/AccessTokenManager;
                        //     3: invokevirtual   com/facebook/AccessTokenManager.getCurrentAccessToken:()Lcom/facebook/AccessToken;
                        //     6: ifnull          28
                        //     9: invokestatic    com/facebook/AccessTokenManager.getInstance:()Lcom/facebook/AccessTokenManager;
                        //    12: invokevirtual   com/facebook/AccessTokenManager.getCurrentAccessToken:()Lcom/facebook/AccessToken;
                        //    15: invokevirtual   com/facebook/AccessToken.getUserId:()Ljava/lang/String;
                        //    18: aload_0        
                        //    19: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //    22: invokevirtual   com/facebook/AccessToken.getUserId:()Ljava/lang/String;
                        //    25: if_acmpeq       86
                        //    28: aload_0        
                        //    29: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    32: ifnull          53
                        //    35: aload_0        
                        //    36: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    39: new             Lcom/facebook/FacebookException;
                        //    42: dup            
                        //    43: ldc             "No current access token to refresh"
                        //    45: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
                        //    48: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshFailed:(Lcom/facebook/FacebookException;)V
                        //    53: aload_0        
                        //    54: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //    57: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //    60: iconst_0       
                        //    61: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //    64: aload_0        
                        //    65: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    68: ifnull          85
                        //    71: iconst_0       
                        //    72: ifeq            85
                        //    75: aload_0        
                        //    76: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    79: aconst_null    
                        //    80: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //    85: return         
                        //    86: aload_0        
                        //    87: getfield        com/facebook/AccessTokenManager$4.val$permissionsCallSucceeded:Ljava/util/concurrent/atomic/AtomicBoolean;
                        //    90: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
                        //    93: ifne            174
                        //    96: aload_0        
                        //    97: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   100: getfield        com/facebook/AccessTokenManager$RefreshResult.accessToken:Ljava/lang/String;
                        //   103: ifnonnull       174
                        //   106: aload_0        
                        //   107: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   110: getfield        com/facebook/AccessTokenManager$RefreshResult.expiresAt:I
                        //   113: ifne            174
                        //   116: aload_0        
                        //   117: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   120: ifnull          141
                        //   123: aload_0        
                        //   124: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   127: new             Lcom/facebook/FacebookException;
                        //   130: dup            
                        //   131: ldc             "Failed to refresh access token"
                        //   133: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
                        //   136: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshFailed:(Lcom/facebook/FacebookException;)V
                        //   141: aload_0        
                        //   142: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //   145: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   148: iconst_0       
                        //   149: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //   152: aload_0        
                        //   153: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   156: ifnull          173
                        //   159: iconst_0       
                        //   160: ifeq            173
                        //   163: aload_0        
                        //   164: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   167: aconst_null    
                        //   168: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //   173: return         
                        //   174: aload_0        
                        //   175: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   178: getfield        com/facebook/AccessTokenManager$RefreshResult.accessToken:Ljava/lang/String;
                        //   181: ifnull          352
                        //   184: aload_0        
                        //   185: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   188: getfield        com/facebook/AccessTokenManager$RefreshResult.accessToken:Ljava/lang/String;
                        //   191: astore          4
                        //   193: aload_0        
                        //   194: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   197: invokevirtual   com/facebook/AccessToken.getApplicationId:()Ljava/lang/String;
                        //   200: astore          5
                        //   202: aload_0        
                        //   203: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   206: invokevirtual   com/facebook/AccessToken.getUserId:()Ljava/lang/String;
                        //   209: astore          6
                        //   211: aload_0        
                        //   212: getfield        com/facebook/AccessTokenManager$4.val$permissionsCallSucceeded:Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   215: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
                        //   218: ifeq            364
                        //   221: aload_0        
                        //   222: getfield        com/facebook/AccessTokenManager$4.val$permissions:Ljava/util/Set;
                        //   225: astore          7
                        //   227: aload_0        
                        //   228: getfield        com/facebook/AccessTokenManager$4.val$permissionsCallSucceeded:Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   231: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
                        //   234: ifeq            376
                        //   237: aload_0        
                        //   238: getfield        com/facebook/AccessTokenManager$4.val$declinedPermissions:Ljava/util/Set;
                        //   241: astore          8
                        //   243: aload_0        
                        //   244: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   247: invokevirtual   com/facebook/AccessToken.getSource:()Lcom/facebook/AccessTokenSource;
                        //   250: astore          9
                        //   252: aload_0        
                        //   253: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   256: getfield        com/facebook/AccessTokenManager$RefreshResult.expiresAt:I
                        //   259: ifeq            388
                        //   262: new             Ljava/util/Date;
                        //   265: dup            
                        //   266: ldc2_w          1000
                        //   269: aload_0        
                        //   270: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   273: getfield        com/facebook/AccessTokenManager$RefreshResult.expiresAt:I
                        //   276: i2l            
                        //   277: lmul           
                        //   278: invokespecial   java/util/Date.<init>:(J)V
                        //   281: astore          10
                        //   283: new             Lcom/facebook/AccessToken;
                        //   286: dup            
                        //   287: aload           4
                        //   289: aload           5
                        //   291: aload           6
                        //   293: aload           7
                        //   295: aload           8
                        //   297: aload           9
                        //   299: aload           10
                        //   301: new             Ljava/util/Date;
                        //   304: dup            
                        //   305: invokespecial   java/util/Date.<init>:()V
                        //   308: invokespecial   com/facebook/AccessToken.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Collection;Ljava/util/Collection;Lcom/facebook/AccessTokenSource;Ljava/util/Date;Ljava/util/Date;)V
                        //   311: astore_3       
                        //   312: invokestatic    com/facebook/AccessTokenManager.getInstance:()Lcom/facebook/AccessTokenManager;
                        //   315: aload_3        
                        //   316: invokevirtual   com/facebook/AccessTokenManager.setCurrentAccessToken:(Lcom/facebook/AccessToken;)V
                        //   319: aload_0        
                        //   320: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //   323: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   326: iconst_0       
                        //   327: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //   330: aload_0        
                        //   331: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   334: ifnull          85
                        //   337: aload_3        
                        //   338: ifnull          85
                        //   341: aload_0        
                        //   342: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   345: aload_3        
                        //   346: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //   351: return         
                        //   352: aload_0        
                        //   353: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   356: invokevirtual   com/facebook/AccessToken.getToken:()Ljava/lang/String;
                        //   359: astore          4
                        //   361: goto            193
                        //   364: aload_0        
                        //   365: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   368: invokevirtual   com/facebook/AccessToken.getPermissions:()Ljava/util/Set;
                        //   371: astore          7
                        //   373: goto            227
                        //   376: aload_0        
                        //   377: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   380: invokevirtual   com/facebook/AccessToken.getDeclinedPermissions:()Ljava/util/Set;
                        //   383: astore          8
                        //   385: goto            243
                        //   388: aload_0        
                        //   389: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   392: invokevirtual   com/facebook/AccessToken.getExpires:()Ljava/util/Date;
                        //   395: astore          11
                        //   397: aload           11
                        //   399: astore          10
                        //   401: goto            283
                        //   404: astore_2       
                        //   405: aconst_null    
                        //   406: astore_3       
                        //   407: aload_0        
                        //   408: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //   411: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   414: iconst_0       
                        //   415: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //   418: aload_0        
                        //   419: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   422: ifnull          439
                        //   425: aload_3        
                        //   426: ifnull          439
                        //   429: aload_0        
                        //   430: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   433: aload_3        
                        //   434: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //   439: aload_2        
                        //   440: athrow         
                        //   441: astore_2       
                        //   442: goto            407
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type
                        //  -----  -----  -----  -----  ----
                        //  0      28     404    407    Any
                        //  28     53     404    407    Any
                        //  86     141    404    407    Any
                        //  174    193    404    407    Any
                        //  193    227    404    407    Any
                        //  227    243    404    407    Any
                        //  243    283    404    407    Any
                        //  283    312    404    407    Any
                        //  312    319    441    445    Any
                        //  352    361    404    407    Any
                        //  364    373    404    407    Any
                        //  376    385    404    407    Any
                        //  388    397    404    407    Any
                        // 
                        throw new IllegalStateException("An error occurred while decompiling this method.");
                    }
                });
                graphRequestBatch.executeAsync();
                return;
            }
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
            }
        }
    }
    
    private void sendCurrentAccessTokenChangedBroadcast(final AccessToken accessToken, final AccessToken accessToken2) {
        final Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", (Parcelable)accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", (Parcelable)accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }
    
    private void setCurrentAccessToken(final AccessToken currentAccessToken, final boolean b) {
        final AccessToken currentAccessToken2 = this.currentAccessToken;
        this.currentAccessToken = currentAccessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0L);
        if (b) {
            if (currentAccessToken != null) {
                this.accessTokenCache.save(currentAccessToken);
            }
            else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(currentAccessToken2, currentAccessToken)) {
            this.sendCurrentAccessTokenChangedBroadcast(currentAccessToken2, currentAccessToken);
        }
    }
    
    private boolean shouldExtendAccessToken() {
        if (this.currentAccessToken != null) {
            final Long value = new Date().getTime();
            if (this.currentAccessToken.getSource().canExtendToken() && value - this.lastAttemptedTokenExtendDate.getTime() > 3600000L && value - this.currentAccessToken.getLastRefresh().getTime() > 86400000L) {
                return true;
            }
        }
        return false;
    }
    
    void extendAccessTokenIfNeeded() {
        if (!this.shouldExtendAccessToken()) {
            return;
        }
        this.refreshCurrentAccessToken(null);
    }
    
    AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }
    
    boolean loadCurrentAccessToken() {
        final AccessToken load = this.accessTokenCache.load();
        boolean b = false;
        if (load != null) {
            this.setCurrentAccessToken(load, false);
            b = true;
        }
        return b;
    }
    
    void refreshCurrentAccessToken(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
            }
        });
    }
    
    void setCurrentAccessToken(final AccessToken accessToken) {
        this.setCurrentAccessToken(accessToken, true);
    }
    
    private static class RefreshResult
    {
        public String accessToken;
        public int expiresAt;
    }
}
