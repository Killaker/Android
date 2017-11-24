package bolts;

import android.os.*;
import android.net.*;
import android.content.*;
import java.util.*;

public final class AppLinks
{
    static final String KEY_NAME_APPLINK_DATA = "al_applink_data";
    static final String KEY_NAME_EXTRAS = "extras";
    static final String KEY_NAME_TARGET = "target_url";
    
    public static Bundle getAppLinkData(final Intent intent) {
        return intent.getBundleExtra("al_applink_data");
    }
    
    public static Bundle getAppLinkExtras(final Intent intent) {
        final Bundle appLinkData = getAppLinkData(intent);
        if (appLinkData == null) {
            return null;
        }
        return appLinkData.getBundle("extras");
    }
    
    public static Uri getTargetUrl(final Intent intent) {
        final Bundle appLinkData = getAppLinkData(intent);
        if (appLinkData != null) {
            final String string = appLinkData.getString("target_url");
            if (string != null) {
                return Uri.parse(string);
            }
        }
        return intent.getData();
    }
    
    public static Uri getTargetUrlFromInboundIntent(final Context context, final Intent intent) {
        final Bundle appLinkData = getAppLinkData(intent);
        Uri parse = null;
        if (appLinkData != null) {
            final String string = appLinkData.getString("target_url");
            parse = null;
            if (string != null) {
                MeasurementEvent.sendBroadcastEvent(context, "al_nav_in", intent, null);
                parse = Uri.parse(string);
            }
        }
        return parse;
    }
}
