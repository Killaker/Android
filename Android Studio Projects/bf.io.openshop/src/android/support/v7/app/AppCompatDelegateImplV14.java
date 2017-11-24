package android.support.v7.app;

import android.content.*;
import android.app.*;
import android.content.res.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.support.v7.view.*;

class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11
{
    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private static TwilightManager sTwilightManager;
    private boolean mApplyDayNightCalled;
    private boolean mHandleNativeActionModes;
    private int mLocalNightMode;
    
    AppCompatDelegateImplV14(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mLocalNightMode = -100;
        this.mHandleNativeActionModes = true;
    }
    
    private int getNightModeToApply() {
        int n;
        if (this.mLocalNightMode == -100) {
            n = AppCompatDelegate.getDefaultNightMode();
        }
        else {
            n = this.mLocalNightMode;
        }
        return this.mapNightModeToYesNo(n);
    }
    
    private TwilightManager getTwilightManager() {
        if (AppCompatDelegateImplV14.sTwilightManager == null) {
            AppCompatDelegateImplV14.sTwilightManager = new TwilightManager(this.mContext.getApplicationContext());
        }
        return AppCompatDelegateImplV14.sTwilightManager;
    }
    
    private int mapNightModeToYesNo(final int n) {
        int n2 = 2;
        switch (n) {
            default: {
                n2 = 1;
                return n2;
            }
            case 2: {
                return n2;
            }
            case 0: {
                if (!this.getTwilightManager().isNight()) {
                    return 1;
                }
                return n2;
            }
            case -1: {
                switch (((UiModeManager)this.mContext.getSystemService("uimode")).getNightMode()) {
                    case 2: {
                        return n2;
                    }
                    default: {
                        return 1;
                    }
                    case 0: {
                        return 0;
                    }
                }
                break;
            }
        }
    }
    
    private boolean updateConfigurationForNightMode(final int n) {
        final Resources resources = this.mContext.getResources();
        final Configuration configuration = resources.getConfiguration();
        final int n2 = 0x30 & configuration.uiMode;
        int n3 = 0;
        switch (n) {
            case 1: {
                n3 = 16;
                break;
            }
            case 2: {
                n3 = 32;
                break;
            }
        }
        if (n2 != n3) {
            configuration.uiMode = (n3 | (0xFFFFFFCF & configuration.uiMode));
            resources.updateConfiguration(configuration, (DisplayMetrics)null);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean applyDayNight() {
        this.mApplyDayNightCalled = true;
        return this.updateConfigurationForNightMode(this.getNightModeToApply());
    }
    
    @Override
    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && this.mLocalNightMode == -100) {
            this.mLocalNightMode = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mLocalNightMode != -100) {
            bundle.putInt("appcompat:local_night_mode", this.mLocalNightMode);
        }
    }
    
    @Override
    public void setHandleNativeActionModesEnabled(final boolean mHandleNativeActionModes) {
        this.mHandleNativeActionModes = mHandleNativeActionModes;
    }
    
    @Override
    public void setLocalNightMode(final int mLocalNightMode) {
        switch (mLocalNightMode) {
            default: {
                Log.d("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                break;
            }
            case -1:
            case 0:
            case 1:
            case 2: {
                if (this.mLocalNightMode == mLocalNightMode) {
                    break;
                }
                this.mLocalNightMode = mLocalNightMode;
                if (this.mApplyDayNightCalled) {
                    this.applyDayNight();
                    return;
                }
                break;
            }
        }
    }
    
    @Override
    Window$Callback wrapWindowCallback(final Window$Callback window$Callback) {
        return (Window$Callback)new AppCompatWindowCallbackV14(window$Callback);
    }
    
    class AppCompatWindowCallbackV14 extends AppCompatWindowCallbackBase
    {
        AppCompatWindowCallbackV14(final Window$Callback window$Callback) {
            super(window$Callback);
        }
        
        @Override
        public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
            if (AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled()) {
                return this.startAsSupportActionMode(actionMode$Callback);
            }
            return super.onWindowStartingActionMode(actionMode$Callback);
        }
        
        final ActionMode startAsSupportActionMode(final ActionMode$Callback actionMode$Callback) {
            final SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImplV14.this.mContext, actionMode$Callback);
            final android.support.v7.view.ActionMode startSupportActionMode = AppCompatDelegateImplV14.this.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }
    }
}
