package com.facebook.internal;

import java.util.*;
import android.content.*;
import android.util.*;
import android.content.pm.*;
import android.os.*;
import com.facebook.*;

public final class Validate
{
    private static final String CONTENT_PROVIDER_BASE = "com.facebook.app.FacebookContentProvider";
    private static final String CONTENT_PROVIDER_NOT_FOUND_REASON = "A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.";
    private static final String FACEBOOK_ACTIVITY_NOT_FOUND_REASON = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
    private static final String NO_INTERNET_PERMISSION_REASON = "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.";
    private static final String TAG;
    
    static {
        TAG = Validate.class.getName();
    }
    
    public static void containsNoNullOrEmpty(final Collection<String> collection, final String s) {
        notNull(collection, s);
        for (final String s2 : collection) {
            if (s2 == null) {
                throw new NullPointerException("Container '" + s + "' cannot contain null values");
            }
            if (s2.length() == 0) {
                throw new IllegalArgumentException("Container '" + s + "' cannot contain empty values");
            }
        }
    }
    
    public static <T> void containsNoNulls(final Collection<T> collection, final String s) {
        notNull(collection, s);
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                throw new NullPointerException("Container '" + s + "' cannot contain null values");
            }
        }
    }
    
    public static String hasAppID() {
        final String applicationId = FacebookSdk.getApplicationId();
        if (applicationId == null) {
            throw new IllegalStateException("No App ID found, please set the App ID.");
        }
        return applicationId;
    }
    
    public static void hasContentProvider(final Context context) {
        notNull(context, "context");
        final String hasAppID = hasAppID();
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            final String string = "com.facebook.app.FacebookContentProvider" + hasAppID;
            if (packageManager.resolveContentProvider(string, 0) == null) {
                throw new IllegalStateException(String.format("A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.", string));
            }
        }
    }
    
    public static void hasFacebookActivity(final Context context) {
        hasFacebookActivity(context, true);
    }
    
    public static void hasFacebookActivity(final Context context, final boolean b) {
        notNull(context, "context");
        final PackageManager packageManager = context.getPackageManager();
        ActivityInfo activityInfo = null;
        while (true) {
            if (packageManager == null) {
                break Label_0041;
            }
            final ComponentName componentName = new ComponentName(context, (Class)FacebookActivity.class);
            try {
                activityInfo = packageManager.getActivityInfo(componentName, 1);
                if (activityInfo == null) {
                    if (b) {
                        throw new IllegalStateException("FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
                    }
                    Log.w(Validate.TAG, "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                activityInfo = null;
                continue;
            }
            break;
        }
    }
    
    public static void hasInternetPermissions(final Context context) {
        hasInternetPermissions(context, true);
    }
    
    public static void hasInternetPermissions(final Context context, final boolean b) {
        notNull(context, "context");
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
            if (b) {
                throw new IllegalStateException("No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
            }
            Log.w(Validate.TAG, "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
        }
    }
    
    public static <T> void notEmpty(final Collection<T> collection, final String s) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Container '" + s + "' cannot be empty");
        }
    }
    
    public static <T> void notEmptyAndContainsNoNulls(final Collection<T> collection, final String s) {
        containsNoNulls((Collection<Object>)collection, s);
        notEmpty((Collection<Object>)collection, s);
    }
    
    public static void notNull(final Object o, final String s) {
        if (o == null) {
            throw new NullPointerException("Argument '" + s + "' cannot be null");
        }
    }
    
    public static void notNullOrEmpty(final String s, final String s2) {
        if (Utility.isNullOrEmpty(s)) {
            throw new IllegalArgumentException("Argument '" + s2 + "' cannot be null or empty");
        }
    }
    
    public static void oneOf(final Object o, final String s, final Object... array) {
        for (final Object o2 : array) {
            if (o2 != null) {
                if (o2.equals(o)) {
                    return;
                }
            }
            else if (o == null) {
                return;
            }
        }
        throw new IllegalArgumentException("Argument '" + s + "' was not one of the allowed values");
    }
    
    public static void runningOnUiThread() {
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new FacebookException("This method should be called from the UI thread");
        }
    }
    
    public static void sdkInitialized() {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookSdkNotInitializedException("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
        }
    }
}
