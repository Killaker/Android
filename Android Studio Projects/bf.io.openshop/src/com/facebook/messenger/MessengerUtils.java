package com.facebook.messenger;

import android.app.*;
import bolts.*;
import com.facebook.*;
import android.os.*;
import android.net.*;
import android.database.*;
import android.content.pm.*;
import android.content.*;
import java.util.*;

public class MessengerUtils
{
    public static final String EXTRA_APP_ID = "com.facebook.orca.extra.APPLICATION_ID";
    public static final String EXTRA_EXTERNAL_URI = "com.facebook.orca.extra.EXTERNAL_URI";
    public static final String EXTRA_IS_COMPOSE = "com.facebook.orca.extra.IS_COMPOSE";
    public static final String EXTRA_IS_REPLY = "com.facebook.orca.extra.IS_REPLY";
    public static final String EXTRA_METADATA = "com.facebook.orca.extra.METADATA";
    public static final String EXTRA_PARTICIPANTS = "com.facebook.orca.extra.PARTICIPANTS";
    public static final String EXTRA_PROTOCOL_VERSION = "com.facebook.orca.extra.PROTOCOL_VERSION";
    public static final String EXTRA_REPLY_TOKEN_KEY = "com.facebook.orca.extra.REPLY_TOKEN";
    public static final String EXTRA_THREAD_TOKEN_KEY = "com.facebook.orca.extra.THREAD_TOKEN";
    public static final String ORCA_THREAD_CATEGORY_20150314 = "com.facebook.orca.category.PLATFORM_THREAD_20150314";
    public static final String PACKAGE_NAME = "com.facebook.orca";
    public static final int PROTOCOL_VERSION_20150314 = 20150314;
    private static final String TAG = "MessengerUtils";
    
    public static void finishShareToMessenger(final Activity activity, final ShareToMessengerParams shareToMessengerParams) {
        final Intent intent = activity.getIntent();
        final Set categories = intent.getCategories();
        if (categories == null) {
            activity.setResult(0, (Intent)null);
            activity.finish();
            return;
        }
        if (!categories.contains("com.facebook.orca.category.PLATFORM_THREAD_20150314")) {
            activity.setResult(0, (Intent)null);
            activity.finish();
            return;
        }
        final Bundle appLinkExtras = AppLinks.getAppLinkExtras(intent);
        final Intent intent2 = new Intent();
        if (categories.contains("com.facebook.orca.category.PLATFORM_THREAD_20150314")) {
            intent2.putExtra("com.facebook.orca.extra.PROTOCOL_VERSION", 20150314);
            intent2.putExtra("com.facebook.orca.extra.THREAD_TOKEN", appLinkExtras.getString("com.facebook.orca.extra.THREAD_TOKEN"));
            intent2.setDataAndType(shareToMessengerParams.uri, shareToMessengerParams.mimeType);
            intent2.setFlags(1);
            intent2.putExtra("com.facebook.orca.extra.APPLICATION_ID", FacebookSdk.getApplicationId());
            intent2.putExtra("com.facebook.orca.extra.METADATA", shareToMessengerParams.metaData);
            intent2.putExtra("com.facebook.orca.extra.EXTERNAL_URI", (Parcelable)shareToMessengerParams.externalUri);
            activity.setResult(-1, intent2);
            activity.finish();
            return;
        }
        throw new RuntimeException();
    }
    
    private static Set<Integer> getAllAvailableProtocolVersions(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        final HashSet<Integer> set = new HashSet<Integer>();
        final Cursor query = contentResolver.query(Uri.parse("content://com.facebook.orca.provider.MessengerPlatformProvider/versions"), new String[] { "version" }, (String)null, (String[])null, (String)null);
        if (query != null) {
            try {
                final int columnIndex = query.getColumnIndex("version");
                while (query.moveToNext()) {
                    set.add(query.getInt(columnIndex));
                }
            }
            finally {
                query.close();
            }
            query.close();
        }
        return set;
    }
    
    public static MessengerThreadParams getMessengerThreadParamsForIntent(final Intent intent) {
        final Set categories = intent.getCategories();
        if (categories != null && categories.contains("com.facebook.orca.category.PLATFORM_THREAD_20150314")) {
            final Bundle appLinkExtras = AppLinks.getAppLinkExtras(intent);
            final String string = appLinkExtras.getString("com.facebook.orca.extra.THREAD_TOKEN");
            final String string2 = appLinkExtras.getString("com.facebook.orca.extra.METADATA");
            final String string3 = appLinkExtras.getString("com.facebook.orca.extra.PARTICIPANTS");
            final boolean boolean1 = appLinkExtras.getBoolean("com.facebook.orca.extra.IS_REPLY");
            final boolean boolean2 = appLinkExtras.getBoolean("com.facebook.orca.extra.IS_COMPOSE");
            MessengerThreadParams.Origin origin = MessengerThreadParams.Origin.UNKNOWN;
            if (boolean1) {
                origin = MessengerThreadParams.Origin.REPLY_FLOW;
            }
            else if (boolean2) {
                origin = MessengerThreadParams.Origin.COMPOSE_FLOW;
            }
            return new MessengerThreadParams(origin, string, string2, parseParticipants(string3));
        }
        return null;
    }
    
    public static boolean hasMessengerInstalled(final Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.orca", 0);
            return true;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static void openMessengerInPlayStore(final Context context) {
        try {
            startViewUri(context, "market://details?id=com.facebook.orca");
        }
        catch (ActivityNotFoundException ex) {
            startViewUri(context, "http://play.google.com/store/apps/details?id=com.facebook.orca");
        }
    }
    
    private static List<String> parseParticipants(final String s) {
        List<String> emptyList;
        if (s == null || s.length() == 0) {
            emptyList = Collections.emptyList();
        }
        else {
            final String[] split = s.split(",");
            emptyList = new ArrayList<String>();
            for (int length = split.length, i = 0; i < length; ++i) {
                emptyList.add(split[i].trim());
            }
        }
        return emptyList;
    }
    
    public static void shareToMessenger(final Activity activity, final int n, final ShareToMessengerParams shareToMessengerParams) {
        if (!hasMessengerInstalled((Context)activity)) {
            openMessengerInPlayStore((Context)activity);
            return;
        }
        if (getAllAvailableProtocolVersions((Context)activity).contains(20150314)) {
            shareToMessenger20150314(activity, n, shareToMessengerParams);
            return;
        }
        openMessengerInPlayStore((Context)activity);
    }
    
    private static void shareToMessenger20150314(final Activity activity, final int n, final ShareToMessengerParams shareToMessengerParams) {
        try {
            final Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(1);
            intent.setPackage("com.facebook.orca");
            intent.putExtra("android.intent.extra.STREAM", (Parcelable)shareToMessengerParams.uri);
            intent.setType(shareToMessengerParams.mimeType);
            final String applicationId = FacebookSdk.getApplicationId();
            if (applicationId != null) {
                intent.putExtra("com.facebook.orca.extra.PROTOCOL_VERSION", 20150314);
                intent.putExtra("com.facebook.orca.extra.APPLICATION_ID", applicationId);
                intent.putExtra("com.facebook.orca.extra.METADATA", shareToMessengerParams.metaData);
                intent.putExtra("com.facebook.orca.extra.EXTERNAL_URI", (Parcelable)shareToMessengerParams.externalUri);
            }
            activity.startActivityForResult(intent, n);
        }
        catch (ActivityNotFoundException ex) {
            activity.startActivity(activity.getPackageManager().getLaunchIntentForPackage("com.facebook.orca"));
        }
    }
    
    private static void startViewUri(final Context context, final String s) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
    }
}
