package com.facebook.login;

import android.os.*;
import com.facebook.internal.*;
import android.content.*;
import java.util.*;
import android.app.*;
import com.facebook.*;

public class LoginManager
{
    private static final String MANAGE_PERMISSION_PREFIX = "manage";
    private static final Set<String> OTHER_PUBLISH_PERMISSIONS;
    private static final String PUBLISH_PERMISSION_PREFIX = "publish";
    private static volatile LoginManager instance;
    private DefaultAudience defaultAudience;
    private LoginBehavior loginBehavior;
    
    static {
        OTHER_PUBLISH_PERMISSIONS = getOtherPublishPermissions();
    }
    
    LoginManager() {
        this.loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
        this.defaultAudience = DefaultAudience.FRIENDS;
        Validate.sdkInitialized();
    }
    
    static LoginResult computeLoginResult(final LoginClient.Request request, final AccessToken accessToken) {
        final Set<String> permissions = request.getPermissions();
        final HashSet set = new HashSet<String>(accessToken.getPermissions());
        if (request.isRerequest()) {
            set.retainAll(permissions);
        }
        final HashSet set2 = new HashSet<String>(permissions);
        set2.removeAll(set);
        return new LoginResult(accessToken, set, (Set<String>)set2);
    }
    
    private LoginClient.Request createLoginRequest(final Collection<String> collection) {
        final LoginBehavior loginBehavior = this.loginBehavior;
        HashSet<String> set;
        if (collection != null) {
            set = new HashSet<String>(collection);
        }
        else {
            set = new HashSet<String>();
        }
        final LoginClient.Request request = new LoginClient.Request(loginBehavior, Collections.unmodifiableSet((Set<? extends String>)set), this.defaultAudience, FacebookSdk.getApplicationId(), UUID.randomUUID().toString());
        request.setRerequest(AccessToken.getCurrentAccessToken() != null);
        return request;
    }
    
    private LoginClient.Request createLoginRequestFromResponse(final GraphResponse graphResponse) {
        Validate.notNull(graphResponse, "response");
        final AccessToken accessToken = graphResponse.getRequest().getAccessToken();
        Set<String> permissions;
        if (accessToken != null) {
            permissions = accessToken.getPermissions();
        }
        else {
            permissions = null;
        }
        return this.createLoginRequest(permissions);
    }
    
    private void finishLogin(final AccessToken currentAccessToken, final LoginClient.Request request, final FacebookException ex, final boolean b, final FacebookCallback<LoginResult> facebookCallback) {
        if (currentAccessToken != null) {
            AccessToken.setCurrentAccessToken(currentAccessToken);
            Profile.fetchProfileForCurrentAccessToken();
        }
        if (facebookCallback != null) {
            LoginResult computeLoginResult;
            if (currentAccessToken != null) {
                computeLoginResult = computeLoginResult(request, currentAccessToken);
            }
            else {
                computeLoginResult = null;
            }
            if (b || (computeLoginResult != null && computeLoginResult.getRecentlyGrantedPermissions().size() == 0)) {
                facebookCallback.onCancel();
            }
            else {
                if (ex != null) {
                    facebookCallback.onError(ex);
                    return;
                }
                if (currentAccessToken != null) {
                    facebookCallback.onSuccess(computeLoginResult);
                }
            }
        }
    }
    
    private Intent getFacebookActivityIntent(final LoginClient.Request request) {
        final Intent intent = new Intent();
        intent.setClass(FacebookSdk.getApplicationContext(), (Class)FacebookActivity.class);
        intent.setAction(request.getLoginBehavior().toString());
        final Bundle bundle = new Bundle();
        bundle.putParcelable("request", (Parcelable)request);
        intent.putExtras(bundle);
        return intent;
    }
    
    public static LoginManager getInstance() {
        Label_0028: {
            if (LoginManager.instance != null) {
                break Label_0028;
            }
            synchronized (LoginManager.class) {
                if (LoginManager.instance == null) {
                    LoginManager.instance = new LoginManager();
                }
                return LoginManager.instance;
            }
        }
    }
    
    private static Set<String> getOtherPublishPermissions() {
        return Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>() {
            {
                this.add("ads_management");
                this.add("create_event");
                this.add("rsvp_event");
            }
        });
    }
    
    static boolean isPublishPermission(final String s) {
        return s != null && (s.startsWith("publish") || s.startsWith("manage") || LoginManager.OTHER_PUBLISH_PERMISSIONS.contains(s));
    }
    
    private void logCompleteLogin(final Context context, final LoginClient.Result.Code code, final Map<String, String> map, final Exception ex, final boolean b, final LoginClient.Request request) {
        final LoginLogger access$000 = getLogger(context);
        if (access$000 == null) {
            return;
        }
        if (request == null) {
            access$000.logUnexpectedError("fb_mobile_login_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.");
            return;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s;
        if (b) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("try_login_activity", s);
        access$000.logCompleteLogin(request.getAuthId(), hashMap, code, map, ex);
    }
    
    private void logInWithPublishPermissions(final FragmentWrapper fragmentWrapper, final Collection<String> collection) {
        this.validatePublishPermissions(collection);
        this.startLogin(new FragmentStartActivityDelegate(fragmentWrapper), this.createLoginRequest(collection));
    }
    
    private void logInWithReadPermissions(final FragmentWrapper fragmentWrapper, final Collection<String> collection) {
        this.validateReadPermissions(collection);
        this.startLogin(new FragmentStartActivityDelegate(fragmentWrapper), this.createLoginRequest(collection));
    }
    
    private void logStartLogin(final Context context, final LoginClient.Request request) {
        final LoginLogger access$000 = getLogger(context);
        if (access$000 != null && request != null) {
            access$000.logStartLogin(request);
        }
    }
    
    private void resolveError(final FragmentWrapper fragmentWrapper, final GraphResponse graphResponse) {
        this.startLogin(new FragmentStartActivityDelegate(fragmentWrapper), this.createLoginRequestFromResponse(graphResponse));
    }
    
    private boolean resolveIntent(final Intent intent) {
        return FacebookSdk.getApplicationContext().getPackageManager().resolveActivity(intent, 0) != null;
    }
    
    private void startLogin(final StartActivityDelegate startActivityDelegate, final LoginClient.Request request) throws FacebookException {
        this.logStartLogin((Context)startActivityDelegate.getActivityContext(), request);
        CallbackManagerImpl.registerStaticCallback(CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return LoginManager.this.onActivityResult(n, intent);
            }
        });
        if (!this.tryFacebookActivity(startActivityDelegate, request)) {
            final FacebookException ex = new FacebookException("Log in attempt failed: FacebookActivity could not be started. Please make sure you added FacebookActivity to the AndroidManifest.");
            this.logCompleteLogin((Context)startActivityDelegate.getActivityContext(), LoginClient.Result.Code.ERROR, null, ex, false, request);
            throw ex;
        }
    }
    
    private boolean tryFacebookActivity(final StartActivityDelegate startActivityDelegate, final LoginClient.Request request) {
        final Intent facebookActivityIntent = this.getFacebookActivityIntent(request);
        if (!this.resolveIntent(facebookActivityIntent)) {
            return false;
        }
        try {
            startActivityDelegate.startActivityForResult(facebookActivityIntent, LoginClient.getLoginRequestCode());
            return true;
        }
        catch (ActivityNotFoundException ex) {
            return false;
        }
    }
    
    private void validatePublishPermissions(final Collection<String> collection) {
        if (collection != null) {
            for (final String s : collection) {
                if (!isPublishPermission(s)) {
                    throw new FacebookException(String.format("Cannot pass a read permission (%s) to a request for publish authorization", s));
                }
            }
        }
    }
    
    private void validateReadPermissions(final Collection<String> collection) {
        if (collection != null) {
            for (final String s : collection) {
                if (isPublishPermission(s)) {
                    throw new FacebookException(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", s));
                }
            }
        }
    }
    
    public DefaultAudience getDefaultAudience() {
        return this.defaultAudience;
    }
    
    public LoginBehavior getLoginBehavior() {
        return this.loginBehavior;
    }
    
    public void logInWithPublishPermissions(final Activity activity, final Collection<String> collection) {
        this.validatePublishPermissions(collection);
        this.startLogin(new ActivityStartActivityDelegate(activity), this.createLoginRequest(collection));
    }
    
    public void logInWithPublishPermissions(final Fragment fragment, final Collection<String> collection) {
        this.logInWithPublishPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logInWithPublishPermissions(final android.support.v4.app.Fragment fragment, final Collection<String> collection) {
        this.logInWithPublishPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logInWithReadPermissions(final Activity activity, final Collection<String> collection) {
        this.validateReadPermissions(collection);
        this.startLogin(new ActivityStartActivityDelegate(activity), this.createLoginRequest(collection));
    }
    
    public void logInWithReadPermissions(final Fragment fragment, final Collection<String> collection) {
        this.logInWithReadPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logInWithReadPermissions(final android.support.v4.app.Fragment fragment, final Collection<String> collection) {
        this.logInWithReadPermissions(new FragmentWrapper(fragment), collection);
    }
    
    public void logOut() {
        AccessToken.setCurrentAccessToken(null);
        Profile.setCurrentProfile(null);
    }
    
    boolean onActivityResult(final int n, final Intent intent) {
        return this.onActivityResult(n, intent, null);
    }
    
    boolean onActivityResult(final int n, final Intent intent, final FacebookCallback<LoginResult> facebookCallback) {
        LoginClient.Result.Code code = LoginClient.Result.Code.ERROR;
        Map<String, String> loggingExtras;
        FacebookException ex;
        LoginClient.Request request;
        AccessToken token;
        boolean b;
        if (intent != null) {
            final LoginClient.Result result = (LoginClient.Result)intent.getParcelableExtra("com.facebook.LoginFragment:Result");
            loggingExtras = null;
            ex = null;
            request = null;
            token = null;
            b = false;
            if (result != null) {
                request = result.request;
                code = result.code;
                if (n == -1) {
                    if (result.code == LoginClient.Result.Code.SUCCESS) {
                        token = result.token;
                    }
                    else {
                        ex = new FacebookAuthorizationException(result.errorMessage);
                        token = null;
                        b = false;
                    }
                }
                else {
                    ex = null;
                    token = null;
                    b = false;
                    if (n == 0) {
                        b = true;
                        ex = null;
                        token = null;
                    }
                }
                loggingExtras = result.loggingExtras;
            }
        }
        else {
            loggingExtras = null;
            ex = null;
            request = null;
            token = null;
            b = false;
            if (n == 0) {
                b = true;
                code = LoginClient.Result.Code.CANCEL;
                loggingExtras = null;
                ex = null;
                request = null;
                token = null;
            }
        }
        if (ex == null && token == null && !b) {
            ex = new FacebookException("Unexpected call to LoginManager.onActivityResult");
        }
        this.logCompleteLogin(null, code, loggingExtras, ex, true, request);
        this.finishLogin(token, request, ex, b, facebookCallback);
        return true;
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<LoginResult> facebookCallback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl)callbackManager).registerCallback(CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return LoginManager.this.onActivityResult(n, intent, facebookCallback);
            }
        });
    }
    
    public void resolveError(final Activity activity, final GraphResponse graphResponse) {
        this.startLogin(new ActivityStartActivityDelegate(activity), this.createLoginRequestFromResponse(graphResponse));
    }
    
    public void resolveError(final Fragment fragment, final GraphResponse graphResponse) {
        this.resolveError(new FragmentWrapper(fragment), graphResponse);
    }
    
    public void resolveError(final android.support.v4.app.Fragment fragment, final GraphResponse graphResponse) {
        this.resolveError(new FragmentWrapper(fragment), graphResponse);
    }
    
    public LoginManager setDefaultAudience(final DefaultAudience defaultAudience) {
        this.defaultAudience = defaultAudience;
        return this;
    }
    
    public LoginManager setLoginBehavior(final LoginBehavior loginBehavior) {
        this.loginBehavior = loginBehavior;
        return this;
    }
    
    private static class ActivityStartActivityDelegate implements StartActivityDelegate
    {
        private final Activity activity;
        
        ActivityStartActivityDelegate(final Activity activity) {
            Validate.notNull(activity, "activity");
            this.activity = activity;
        }
        
        @Override
        public Activity getActivityContext() {
            return this.activity;
        }
        
        @Override
        public void startActivityForResult(final Intent intent, final int n) {
            this.activity.startActivityForResult(intent, n);
        }
    }
    
    private static class FragmentStartActivityDelegate implements StartActivityDelegate
    {
        private final FragmentWrapper fragment;
        
        FragmentStartActivityDelegate(final FragmentWrapper fragment) {
            Validate.notNull(fragment, "fragment");
            this.fragment = fragment;
        }
        
        @Override
        public Activity getActivityContext() {
            return this.fragment.getActivity();
        }
        
        @Override
        public void startActivityForResult(final Intent intent, final int n) {
            this.fragment.startActivityForResult(intent, n);
        }
    }
    
    private static class LoginLoggerHolder
    {
        private static volatile LoginLogger logger;
        
        private static LoginLogger getLogger(Context applicationContext) {
            // monitorenter(LoginLoggerHolder.class)
            Label_0018: {
                if (applicationContext == null) {
                    break Label_0018;
                }
            Label_0013_Outer:
                while (true) {
                    Label_0025: {
                        if (applicationContext != null) {
                            break Label_0025;
                        }
                        LoginLogger logger = null;
                    Block_3_Outer:
                        while (true) {
                            return logger;
                            try {
                                applicationContext = FacebookSdk.getApplicationContext();
                                continue Label_0013_Outer;
                                // iftrue(Label_0045:, LoginLoggerHolder.logger != null)
                            Label_0045:
                                while (true) {
                                    LoginLoggerHolder.logger = new LoginLogger(applicationContext, FacebookSdk.getApplicationId());
                                    break Label_0045;
                                    continue;
                                }
                                logger = LoginLoggerHolder.logger;
                                continue Block_3_Outer;
                            }
                            finally {
                            }
                            // monitorexit(LoginLoggerHolder.class)
                            break;
                        }
                    }
                    break;
                }
            }
        }
        // monitorexit(LoginLoggerHolder.class)
    }
}
