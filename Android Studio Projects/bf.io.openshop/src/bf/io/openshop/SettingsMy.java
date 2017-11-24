package bf.io.openshop;

import bf.io.openshop.entities.*;
import timber.log.*;
import android.app.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.ux.*;
import android.support.annotation.*;
import android.content.pm.*;
import android.content.*;

public class SettingsMy
{
    public static final String PREF_ACTIVE_USER = "pref_active_user";
    public static final String PREF_ACTUAL_SHOP = "pref_actual_shop";
    public static final String PREF_USER_EMAIL = "pref_user_email";
    public static final String PREF_VERSION_CODE = "version_code";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_REG_ID = "registration_id";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String SHOW_HINT = "show_hint";
    private static final String TAG;
    private static User activeUser;
    private static Shop actualShop;
    private static SharedPreferences sharedPref;
    
    static {
        TAG = SettingsMy.class.getSimpleName();
    }
    
    public static User getActiveUser() {
        if (SettingsMy.activeUser != null) {
            Timber.d(SettingsMy.TAG + " - Returned active user", new Object[0]);
            return SettingsMy.activeUser;
        }
        final String string = getSettings().getString("pref_active_user", "");
        if (string.isEmpty() || "null".equals(string)) {
            Timber.d(SettingsMy.TAG + " - Returned null", new Object[0]);
            return null;
        }
        SettingsMy.activeUser = Utils.getGsonParser().fromJson(string, User.class);
        Timber.d(SettingsMy.TAG + " - Returned active user from memory:" + SettingsMy.activeUser.toString(), new Object[0]);
        return SettingsMy.activeUser;
    }
    
    @NonNull
    public static Shop getActualNonNullShop(final Activity activity) {
        Shop actualShop = getActualShop();
        if (actualShop == null) {
            if (activity != null) {
                MsgUtils.showToast(activity, 2, null, MsgUtils.ToastLength.LONG);
                final Intent intent = new Intent(activity.getApplicationContext(), (Class)SplashActivity.class);
                intent.setFlags(268468224);
                activity.startActivity(intent);
            }
            else {
                Timber.e("Null shop detected also with null activity parameter.", new Object[0]);
            }
            actualShop = new Shop();
        }
        return actualShop;
    }
    
    public static Shop getActualShop() {
        if (SettingsMy.actualShop != null) {
            Timber.d(SettingsMy.TAG + " - Returned actual shop", new Object[0]);
            return SettingsMy.actualShop;
        }
        final String string = getSettings().getString("pref_actual_shop", "");
        if (string.isEmpty() || "null".equals(string)) {
            Timber.e(SettingsMy.TAG + " - Returned null shop", new Object[0]);
            return null;
        }
        SettingsMy.actualShop = Utils.getGsonParser().fromJson(string, Shop.class);
        Timber.d(SettingsMy.TAG + " - Returned shop from memory:" + SettingsMy.actualShop.toString(), new Object[0]);
        return SettingsMy.actualShop;
    }
    
    private static int getAppVersion(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new RuntimeException("Could not get package name: " + ex);
        }
    }
    
    public static SharedPreferences getSettings() {
        if (SettingsMy.sharedPref == null) {
            SettingsMy.sharedPref = MyApplication.getInstance().getSharedPreferences(MyApplication.PACKAGE_NAME, 0);
        }
        return SettingsMy.sharedPref;
    }
    
    public static Boolean getTokenSentToServer() {
        final boolean boolean1 = getSettings().getBoolean("sentTokenToServer", false);
        Timber.d(SettingsMy.TAG + " - Obtained token sent to server:" + boolean1, new Object[0]);
        return boolean1;
    }
    
    public static String getUserEmailHint() {
        final String string = getSettings().getString("pref_user_email", "");
        Timber.d(SettingsMy.TAG + " - Obtained user email:" + string, new Object[0]);
        return string;
    }
    
    public static boolean isHintRequired() {
        return getSettings().getBoolean("show_hint", true);
    }
    
    private static boolean putParam(final String s, final int n) {
        final SharedPreferences$Editor edit = getSettings().edit();
        edit.putInt(s, n);
        return edit.commit();
    }
    
    private static boolean putParam(final String s, final long n) {
        final SharedPreferences$Editor edit = getSettings().edit();
        edit.putLong(s, n);
        return edit.commit();
    }
    
    private static boolean putParam(final String s, final String s2) {
        final SharedPreferences$Editor edit = getSettings().edit();
        edit.putString(s, s2);
        return edit.commit();
    }
    
    private static boolean putParam(final String s, final boolean b) {
        final SharedPreferences$Editor edit = getSettings().edit();
        edit.putBoolean(s, b);
        return edit.commit();
    }
    
    public static void setActiveUser(final User activeUser) {
        if (activeUser != null) {
            Timber.d(SettingsMy.TAG + " - Set active user with name: " + activeUser.toString(), new Object[0]);
        }
        else {
            Timber.d(SettingsMy.TAG + " - Deleting active user", new Object[0]);
        }
        SettingsMy.activeUser = activeUser;
        final String json = Utils.getGsonParser().toJson(SettingsMy.activeUser);
        final SharedPreferences$Editor edit = getSettings().edit();
        edit.putString("pref_active_user", json);
        edit.apply();
    }
    
    public static void setActualShop(final Shop actualShop) {
        if (actualShop != null) {
            Timber.d(SettingsMy.TAG + " - Set selected shop: " + actualShop.toString(), new Object[0]);
        }
        else {
            Timber.d(SettingsMy.TAG + " - Disable selected shop", new Object[0]);
        }
        SettingsMy.actualShop = actualShop;
        final String json = Utils.getGsonParser().toJson(SettingsMy.actualShop);
        final SharedPreferences$Editor edit = getSettings().edit();
        edit.putString("pref_actual_shop", json);
        edit.apply();
    }
    
    public static void setHintRequired(final boolean b) {
        putParam("show_hint", b);
    }
    
    public static void setTokenSentToServer(final boolean b) {
        putParam("sentTokenToServer", b);
    }
    
    public static void setUserEmailHint(final String s) {
        Timber.d(SettingsMy.TAG + " - Set user email: " + s, new Object[0]);
        putParam("pref_user_email", s);
    }
}
