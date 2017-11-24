package com.facebook.internal;

import java.util.*;
import android.content.*;
import com.facebook.*;
import org.json.*;

public class AppEventsLoggerUtility
{
    private static final Map<GraphAPIActivityType, String> API_ACTIVITY_TYPE_TO_STRING;
    
    static {
        API_ACTIVITY_TYPE_TO_STRING = new HashMap<GraphAPIActivityType, String>() {
            {
                this.put(GraphAPIActivityType.MOBILE_INSTALL_EVENT, "MOBILE_APP_INSTALL");
                this.put(GraphAPIActivityType.CUSTOM_APP_EVENTS, "CUSTOM_APP_EVENTS");
            }
        };
    }
    
    public static JSONObject getJSONObjectForGraphAPICall(final GraphAPIActivityType graphAPIActivityType, final AttributionIdentifiers attributionIdentifiers, final String s, final boolean b, final Context context) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("event", (Object)AppEventsLoggerUtility.API_ACTIVITY_TYPE_TO_STRING.get(graphAPIActivityType));
        Utility.setAppEventAttributionParameters(jsonObject, attributionIdentifiers, s, b);
        while (true) {
            try {
                Utility.setAppEventExtendedDeviceInfoParameters(jsonObject, context);
                jsonObject.put("application_package_name", (Object)context.getPackageName());
                return jsonObject;
            }
            catch (Exception ex) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Fetching extended device info parameters failed: '%s'", ex.toString());
                continue;
            }
            break;
        }
    }
    
    public enum GraphAPIActivityType
    {
        CUSTOM_APP_EVENTS, 
        MOBILE_INSTALL_EVENT;
    }
}
