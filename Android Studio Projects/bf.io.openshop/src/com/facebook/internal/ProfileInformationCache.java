package com.facebook.internal;

import java.util.concurrent.*;
import org.json.*;

class ProfileInformationCache
{
    private static final ConcurrentHashMap<String, JSONObject> infoCache;
    
    static {
        infoCache = new ConcurrentHashMap<String, JSONObject>();
    }
    
    public static JSONObject getProfileInformation(final String s) {
        return ProfileInformationCache.infoCache.get(s);
    }
    
    public static void putProfileInformation(final String s, final JSONObject jsonObject) {
        ProfileInformationCache.infoCache.put(s, jsonObject);
    }
}
