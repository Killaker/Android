package com.facebook.login.widget;

import android.util.*;
import android.content.res.*;
import android.view.*;
import android.graphics.*;
import com.facebook.login.*;
import com.facebook.internal.*;
import java.util.*;
import com.facebook.*;
import android.app.*;
import android.content.*;
import com.facebook.appevents.*;
import android.os.*;

public class LoginButton extends FacebookButtonBase
{
    private static final String TAG;
    private AccessTokenTracker accessTokenTracker;
    private boolean confirmLogout;
    private String loginLogoutEventName;
    private LoginManager loginManager;
    private String loginText;
    private String logoutText;
    private LoginButtonProperties properties;
    private boolean toolTipChecked;
    private long toolTipDisplayTime;
    private ToolTipMode toolTipMode;
    private ToolTipPopup toolTipPopup;
    private ToolTipPopup.Style toolTipStyle;
    
    static {
        TAG = LoginButton.class.getName();
    }
    
    public LoginButton(final Context context) {
        super(context, null, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = "fb_login_view_usage";
        this.toolTipStyle = ToolTipPopup.Style.BLUE;
        this.toolTipDisplayTime = 6000L;
    }
    
    public LoginButton(final Context context, final AttributeSet set) {
        super(context, set, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = "fb_login_view_usage";
        this.toolTipStyle = ToolTipPopup.Style.BLUE;
        this.toolTipDisplayTime = 6000L;
    }
    
    public LoginButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, 0, "fb_login_button_create", "fb_login_button_did_tap");
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = "fb_login_view_usage";
        this.toolTipStyle = ToolTipPopup.Style.BLUE;
        this.toolTipDisplayTime = 6000L;
    }
    
    private void checkToolTipSettings() {
        switch (this.toolTipMode) {
            default: {}
            case AUTOMATIC: {
                FacebookSdk.getExecutor().execute(new Runnable() {
                    final /* synthetic */ String val$appId = Utility.getMetadataApplicationId(LoginButton.this.getContext());
                    
                    @Override
                    public void run() {
                        LoginButton.this.getActivity().runOnUiThread((Runnable)new Runnable() {
                            final /* synthetic */ Utility.FetchedAppSettings val$settings = Utility.queryAppSettings(LoginButton$1.this.val$appId, false);
                            
                            @Override
                            public void run() {
                                LoginButton.this.showToolTipPerSettings(this.val$settings);
                            }
                        });
                    }
                });
            }
            case DISPLAY_ALWAYS: {
                this.displayToolTip(this.getResources().getString(R.string.com_facebook_tooltip_default));
            }
        }
    }
    
    private void displayToolTip(final String s) {
        (this.toolTipPopup = new ToolTipPopup(s, (View)this)).setStyle(this.toolTipStyle);
        this.toolTipPopup.setNuxDisplayTime(this.toolTipDisplayTime);
        this.toolTipPopup.show();
    }
    
    private int measureButtonWidth(final String s) {
        return this.measureTextWidth(s) + (this.getCompoundPaddingLeft() + this.getCompoundDrawablePadding()) + this.getCompoundPaddingRight();
    }
    
    private void parseLoginButtonAttributes(final Context context, final AttributeSet set, final int n, final int n2) {
        this.toolTipMode = ToolTipMode.DEFAULT;
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, R.styleable.com_facebook_login_view, n, n2);
        try {
            this.confirmLogout = obtainStyledAttributes.getBoolean(R.styleable.com_facebook_login_view_com_facebook_confirm_logout, true);
            this.loginText = obtainStyledAttributes.getString(R.styleable.com_facebook_login_view_com_facebook_login_text);
            this.logoutText = obtainStyledAttributes.getString(R.styleable.com_facebook_login_view_com_facebook_logout_text);
            this.toolTipMode = ToolTipMode.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_login_view_com_facebook_tooltip_mode, ToolTipMode.DEFAULT.getValue()));
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    private void setButtonText() {
        final Resources resources = this.getResources();
        if (!this.isInEditMode() && AccessToken.getCurrentAccessToken() != null) {
            String text;
            if (this.logoutText != null) {
                text = this.logoutText;
            }
            else {
                text = resources.getString(R.string.com_facebook_loginview_log_out_button);
            }
            this.setText((CharSequence)text);
            return;
        }
        if (this.loginText != null) {
            this.setText((CharSequence)this.loginText);
            return;
        }
        String text2 = resources.getString(R.string.com_facebook_loginview_log_in_button_long);
        final int width = this.getWidth();
        if (width != 0 && this.measureButtonWidth(text2) > width) {
            text2 = resources.getString(R.string.com_facebook_loginview_log_in_button);
        }
        this.setText((CharSequence)text2);
    }
    
    private void showToolTipPerSettings(final Utility.FetchedAppSettings fetchedAppSettings) {
        if (fetchedAppSettings != null && fetchedAppSettings.getNuxEnabled() && this.getVisibility() == 0) {
            this.displayToolTip(fetchedAppSettings.getNuxContent());
        }
    }
    
    public void clearPermissions() {
        this.properties.clearPermissions();
    }
    
    @Override
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        super.configureButton(context, set, n, n2);
        this.setInternalOnClickListener((View$OnClickListener)new LoginClickListener());
        this.parseLoginButtonAttributes(context, set, n, n2);
        if (this.isInEditMode()) {
            this.setBackgroundColor(this.getResources().getColor(R.color.com_facebook_blue));
            this.loginText = "Log in with Facebook";
        }
        else {
            this.accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(final AccessToken accessToken, final AccessToken accessToken2) {
                    LoginButton.this.setButtonText();
                }
            };
        }
        this.setButtonText();
    }
    
    public void dismissToolTip() {
        if (this.toolTipPopup != null) {
            this.toolTipPopup.dismiss();
            this.toolTipPopup = null;
        }
    }
    
    public DefaultAudience getDefaultAudience() {
        return this.properties.getDefaultAudience();
    }
    
    @Override
    protected int getDefaultRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode();
    }
    
    @Override
    protected int getDefaultStyleResource() {
        return R.style.com_facebook_loginview_default_style;
    }
    
    public LoginBehavior getLoginBehavior() {
        return this.properties.getLoginBehavior();
    }
    
    LoginManager getLoginManager() {
        if (this.loginManager == null) {
            this.loginManager = LoginManager.getInstance();
        }
        return this.loginManager;
    }
    
    List<String> getPermissions() {
        return this.properties.getPermissions();
    }
    
    public long getToolTipDisplayTime() {
        return this.toolTipDisplayTime;
    }
    
    public ToolTipMode getToolTipMode() {
        return this.toolTipMode;
    }
    
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.accessTokenTracker != null && !this.accessTokenTracker.isTracking()) {
            this.accessTokenTracker.startTracking();
            this.setButtonText();
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.accessTokenTracker != null) {
            this.accessTokenTracker.stopTracking();
        }
        this.dismissToolTip();
    }
    
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (!this.toolTipChecked && !this.isInEditMode()) {
            this.toolTipChecked = true;
            this.checkToolTipSettings();
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.setButtonText();
    }
    
    protected void onMeasure(final int n, final int n2) {
        final Paint$FontMetrics fontMetrics = this.getPaint().getFontMetrics();
        final int n3 = this.getCompoundPaddingTop() + (int)Math.ceil(Math.abs(fontMetrics.top) + Math.abs(fontMetrics.bottom)) + this.getCompoundPaddingBottom();
        final Resources resources = this.getResources();
        String s = this.loginText;
        if (s == null) {
            s = resources.getString(R.string.com_facebook_loginview_log_in_button_long);
            final int measureButtonWidth = this.measureButtonWidth(s);
            if (resolveSize(measureButtonWidth, n) < measureButtonWidth) {
                s = resources.getString(R.string.com_facebook_loginview_log_in_button);
            }
        }
        final int measureButtonWidth2 = this.measureButtonWidth(s);
        String s2 = this.logoutText;
        if (s2 == null) {
            s2 = resources.getString(R.string.com_facebook_loginview_log_out_button);
        }
        this.setMeasuredDimension(resolveSize(Math.max(measureButtonWidth2, this.measureButtonWidth(s2)), n), n3);
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        if (n != 0) {
            this.dismissToolTip();
        }
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<LoginResult> facebookCallback) {
        this.getLoginManager().registerCallback(callbackManager, facebookCallback);
    }
    
    public void setDefaultAudience(final DefaultAudience defaultAudience) {
        this.properties.setDefaultAudience(defaultAudience);
    }
    
    public void setLoginBehavior(final LoginBehavior loginBehavior) {
        this.properties.setLoginBehavior(loginBehavior);
    }
    
    void setLoginManager(final LoginManager loginManager) {
        this.loginManager = loginManager;
    }
    
    void setProperties(final LoginButtonProperties properties) {
        this.properties = properties;
    }
    
    public void setPublishPermissions(final List<String> publishPermissions) {
        this.properties.setPublishPermissions(publishPermissions);
    }
    
    public void setPublishPermissions(final String... array) {
        this.properties.setPublishPermissions(Arrays.asList(array));
    }
    
    public void setReadPermissions(final List<String> readPermissions) {
        this.properties.setReadPermissions(readPermissions);
    }
    
    public void setReadPermissions(final String... array) {
        this.properties.setReadPermissions(Arrays.asList(array));
    }
    
    public void setToolTipDisplayTime(final long toolTipDisplayTime) {
        this.toolTipDisplayTime = toolTipDisplayTime;
    }
    
    public void setToolTipMode(final ToolTipMode toolTipMode) {
        this.toolTipMode = toolTipMode;
    }
    
    public void setToolTipStyle(final ToolTipPopup.Style toolTipStyle) {
        this.toolTipStyle = toolTipStyle;
    }
    
    static class LoginButtonProperties
    {
        private LoginAuthorizationType authorizationType;
        private DefaultAudience defaultAudience;
        private LoginBehavior loginBehavior;
        private List<String> permissions;
        
        LoginButtonProperties() {
            this.defaultAudience = DefaultAudience.FRIENDS;
            this.permissions = Collections.emptyList();
            this.authorizationType = null;
            this.loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
        }
        
        public void clearPermissions() {
            this.permissions = null;
            this.authorizationType = null;
        }
        
        public DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }
        
        public LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }
        
        List<String> getPermissions() {
            return this.permissions;
        }
        
        public void setDefaultAudience(final DefaultAudience defaultAudience) {
            this.defaultAudience = defaultAudience;
        }
        
        public void setLoginBehavior(final LoginBehavior loginBehavior) {
            this.loginBehavior = loginBehavior;
        }
        
        public void setPublishPermissions(final List<String> permissions) {
            if (LoginAuthorizationType.READ.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            }
            if (Utility.isNullOrEmpty((Collection<Object>)permissions)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            }
            this.permissions = permissions;
            this.authorizationType = LoginAuthorizationType.PUBLISH;
        }
        
        public void setReadPermissions(final List<String> permissions) {
            if (LoginAuthorizationType.PUBLISH.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            this.permissions = permissions;
            this.authorizationType = LoginAuthorizationType.READ;
        }
    }
    
    private class LoginClickListener implements View$OnClickListener
    {
        public void onClick(final View view) {
            LoginButton.this.callExternalOnClickListener(view);
            final Context context = LoginButton.this.getContext();
            final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (currentAccessToken != null) {
                if (LoginButton.this.confirmLogout) {
                    final String string = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_log_out_action);
                    final String string2 = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_cancel_action);
                    final Profile currentProfile = Profile.getCurrentProfile();
                    String message;
                    if (currentProfile != null && currentProfile.getName() != null) {
                        message = String.format(LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_as), currentProfile.getName());
                    }
                    else {
                        message = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_using_facebook);
                    }
                    final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
                    alertDialog$Builder.setMessage((CharSequence)message).setCancelable(true).setPositiveButton((CharSequence)string, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            LoginButton.this.getLoginManager().logOut();
                        }
                    }).setNegativeButton((CharSequence)string2, (DialogInterface$OnClickListener)null);
                    alertDialog$Builder.create().show();
                }
                else {
                    LoginButton.this.getLoginManager().logOut();
                }
            }
            else {
                final LoginManager loginManager = LoginButton.this.getLoginManager();
                loginManager.setDefaultAudience(LoginButton.this.getDefaultAudience());
                loginManager.setLoginBehavior(LoginButton.this.getLoginBehavior());
                if (LoginAuthorizationType.PUBLISH.equals(LoginButton.this.properties.authorizationType)) {
                    if (LoginButton.this.getFragment() != null) {
                        loginManager.logInWithPublishPermissions(LoginButton.this.getFragment(), LoginButton.this.properties.permissions);
                    }
                    else if (LoginButton.this.getNativeFragment() != null) {
                        loginManager.logInWithPublishPermissions(LoginButton.this.getNativeFragment(), LoginButton.this.properties.permissions);
                    }
                    else {
                        loginManager.logInWithPublishPermissions(LoginButton.this.getActivity(), LoginButton.this.properties.permissions);
                    }
                }
                else if (LoginButton.this.getFragment() != null) {
                    loginManager.logInWithReadPermissions(LoginButton.this.getFragment(), LoginButton.this.properties.permissions);
                }
                else if (LoginButton.this.getNativeFragment() != null) {
                    loginManager.logInWithReadPermissions(LoginButton.this.getNativeFragment(), LoginButton.this.properties.permissions);
                }
                else {
                    loginManager.logInWithReadPermissions(LoginButton.this.getActivity(), LoginButton.this.properties.permissions);
                }
            }
            final AppEventsLogger logger = AppEventsLogger.newLogger(LoginButton.this.getContext());
            final Bundle bundle = new Bundle();
            int n;
            if (currentAccessToken != null) {
                n = 0;
            }
            else {
                n = 1;
            }
            bundle.putInt("logging_in", n);
            logger.logSdkEvent(LoginButton.this.loginLogoutEventName, null, bundle);
        }
    }
    
    public enum ToolTipMode
    {
        AUTOMATIC("automatic", 0);
        
        public static ToolTipMode DEFAULT;
        
        DISPLAY_ALWAYS("display_always", 1), 
        NEVER_DISPLAY("never_display", 2);
        
        private int intValue;
        private String stringValue;
        
        static {
            ToolTipMode.DEFAULT = ToolTipMode.AUTOMATIC;
        }
        
        private ToolTipMode(final String stringValue, final int intValue) {
            this.stringValue = stringValue;
            this.intValue = intValue;
        }
        
        public static ToolTipMode fromInt(final int n) {
            for (final ToolTipMode toolTipMode : values()) {
                if (toolTipMode.getValue() == n) {
                    return toolTipMode;
                }
            }
            return null;
        }
        
        public int getValue() {
            return this.intValue;
        }
        
        @Override
        public String toString() {
            return this.stringValue;
        }
    }
}
