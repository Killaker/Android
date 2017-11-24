package com.facebook.internal;

import org.json.*;
import java.util.*;
import com.facebook.*;

public final class FacebookRequestErrorClassification
{
    public static final int EC_APP_TOO_MANY_CALLS = 4;
    public static final int EC_INVALID_SESSION = 102;
    public static final int EC_INVALID_TOKEN = 190;
    public static final int EC_RATE = 9;
    public static final int EC_SERVICE_UNAVAILABLE = 2;
    public static final int EC_TOO_MANY_USER_ACTION_CALLS = 341;
    public static final int EC_USER_TOO_MANY_CALLS = 17;
    public static final String KEY_LOGIN_RECOVERABLE = "login_recoverable";
    public static final String KEY_NAME = "name";
    public static final String KEY_OTHER = "other";
    public static final String KEY_RECOVERY_MESSAGE = "recovery_message";
    public static final String KEY_TRANSIENT = "transient";
    private static FacebookRequestErrorClassification defaultInstance;
    private final Map<Integer, Set<Integer>> loginRecoverableErrors;
    private final String loginRecoverableRecoveryMessage;
    private final Map<Integer, Set<Integer>> otherErrors;
    private final String otherRecoveryMessage;
    private final Map<Integer, Set<Integer>> transientErrors;
    private final String transientRecoveryMessage;
    
    FacebookRequestErrorClassification(final Map<Integer, Set<Integer>> otherErrors, final Map<Integer, Set<Integer>> transientErrors, final Map<Integer, Set<Integer>> loginRecoverableErrors, final String otherRecoveryMessage, final String transientRecoveryMessage, final String loginRecoverableRecoveryMessage) {
        this.otherErrors = otherErrors;
        this.transientErrors = transientErrors;
        this.loginRecoverableErrors = loginRecoverableErrors;
        this.otherRecoveryMessage = otherRecoveryMessage;
        this.transientRecoveryMessage = transientRecoveryMessage;
        this.loginRecoverableRecoveryMessage = loginRecoverableRecoveryMessage;
    }
    
    public static FacebookRequestErrorClassification createFromJSON(final JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }
        Map<Integer, Set<Integer>> jsonDefinition = null;
        Map<Integer, Set<Integer>> jsonDefinition2 = null;
        Map<Integer, Set<Integer>> jsonDefinition3 = null;
        String optString = null;
        String optString2 = null;
        String optString3 = null;
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject optJSONObject = jsonArray.optJSONObject(i);
            if (optJSONObject != null) {
                final String optString4 = optJSONObject.optString("name");
                if (optString4 != null) {
                    if (optString4.equalsIgnoreCase("other")) {
                        optString = optJSONObject.optString("recovery_message", (String)null);
                        jsonDefinition = parseJSONDefinition(optJSONObject);
                    }
                    else if (optString4.equalsIgnoreCase("transient")) {
                        optString2 = optJSONObject.optString("recovery_message", (String)null);
                        jsonDefinition2 = parseJSONDefinition(optJSONObject);
                    }
                    else if (optString4.equalsIgnoreCase("login_recoverable")) {
                        optString3 = optJSONObject.optString("recovery_message", (String)null);
                        jsonDefinition3 = parseJSONDefinition(optJSONObject);
                    }
                }
            }
        }
        return new FacebookRequestErrorClassification(jsonDefinition, jsonDefinition2, jsonDefinition3, optString, optString2, optString3);
    }
    
    public static FacebookRequestErrorClassification getDefaultErrorClassification() {
        synchronized (FacebookRequestErrorClassification.class) {
            if (FacebookRequestErrorClassification.defaultInstance == null) {
                FacebookRequestErrorClassification.defaultInstance = getDefaultErrorClassificationImpl();
            }
            return FacebookRequestErrorClassification.defaultInstance;
        }
    }
    
    private static FacebookRequestErrorClassification getDefaultErrorClassificationImpl() {
        return new FacebookRequestErrorClassification(null, new HashMap<Integer, Set<Integer>>() {
            {
                this.put(2, null);
                this.put(4, null);
                this.put(9, null);
                this.put(17, null);
                this.put(341, null);
            }
        }, new HashMap<Integer, Set<Integer>>() {
            {
                this.put(102, null);
                this.put(190, null);
            }
        }, null, null, null);
    }
    
    private static Map<Integer, Set<Integer>> parseJSONDefinition(final JSONObject jsonObject) {
        final JSONArray optJSONArray = jsonObject.optJSONArray("items");
        Map<Integer, Set<Integer>> map;
        if (optJSONArray.length() == 0) {
            map = null;
        }
        else {
            map = new HashMap<Integer, Set<Integer>>();
            for (int i = 0; i < optJSONArray.length(); ++i) {
                final JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    final int optInt = optJSONObject.optInt("code");
                    if (optInt != 0) {
                        final JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                        Set<Integer> set = null;
                        if (optJSONArray2 != null) {
                            final int length = optJSONArray2.length();
                            set = null;
                            if (length > 0) {
                                set = new HashSet<Integer>();
                                for (int j = 0; j < optJSONArray2.length(); ++j) {
                                    final int optInt2 = optJSONArray2.optInt(j);
                                    if (optInt2 != 0) {
                                        set.add(optInt2);
                                    }
                                }
                            }
                        }
                        map.put(optInt, set);
                    }
                }
            }
        }
        return map;
    }
    
    public FacebookRequestError.Category classify(final int n, final int n2, final boolean b) {
        if (b) {
            return FacebookRequestError.Category.TRANSIENT;
        }
        if (this.otherErrors != null && this.otherErrors.containsKey(n)) {
            final Set<Integer> set = this.otherErrors.get(n);
            if (set == null || set.contains(n2)) {
                return FacebookRequestError.Category.OTHER;
            }
        }
        if (this.loginRecoverableErrors != null && this.loginRecoverableErrors.containsKey(n)) {
            final Set<Integer> set2 = this.loginRecoverableErrors.get(n);
            if (set2 == null || set2.contains(n2)) {
                return FacebookRequestError.Category.LOGIN_RECOVERABLE;
            }
        }
        if (this.transientErrors != null && this.transientErrors.containsKey(n)) {
            final Set<Integer> set3 = this.transientErrors.get(n);
            if (set3 == null || set3.contains(n2)) {
                return FacebookRequestError.Category.TRANSIENT;
            }
        }
        return FacebookRequestError.Category.OTHER;
    }
    
    public Map<Integer, Set<Integer>> getLoginRecoverableErrors() {
        return this.loginRecoverableErrors;
    }
    
    public Map<Integer, Set<Integer>> getOtherErrors() {
        return this.otherErrors;
    }
    
    public String getRecoveryMessage(final FacebookRequestError.Category category) {
        switch (category) {
            default: {
                return null;
            }
            case OTHER: {
                return this.otherRecoveryMessage;
            }
            case LOGIN_RECOVERABLE: {
                return this.loginRecoverableRecoveryMessage;
            }
            case TRANSIENT: {
                return this.transientRecoveryMessage;
            }
        }
    }
    
    public Map<Integer, Set<Integer>> getTransientErrors() {
        return this.transientErrors;
    }
}
