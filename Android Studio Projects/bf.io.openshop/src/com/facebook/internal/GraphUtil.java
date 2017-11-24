package com.facebook.internal;

import com.facebook.*;
import org.json.*;

public class GraphUtil
{
    private static final String[] dateFormats;
    
    static {
        dateFormats = new String[] { "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
    }
    
    public static JSONObject createOpenGraphActionForPost(final String s) {
        final JSONObject jsonObject = new JSONObject();
        if (s == null) {
            return jsonObject;
        }
        try {
            jsonObject.put("type", (Object)s);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new FacebookException("An error occurred while setting up the open graph action", (Throwable)ex);
        }
    }
    
    public static JSONObject createOpenGraphObjectForPost(final String s) {
        return createOpenGraphObjectForPost(s, null, null, null, null, null, null);
    }
    
    public static JSONObject createOpenGraphObjectForPost(final String s, final String s2, final String s3, final String s4, final String s5, final JSONObject jsonObject, final String s6) {
        final JSONObject jsonObject2 = new JSONObject();
        Label_0022: {
            if (s == null) {
                break Label_0022;
            }
            try {
                jsonObject2.put("type", (Object)s);
                jsonObject2.put("title", (Object)s2);
                if (s3 != null) {
                    final JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("url", (Object)s3);
                    final JSONArray jsonArray = new JSONArray();
                    jsonArray.put((Object)jsonObject3);
                    jsonObject2.put("image", (Object)jsonArray);
                }
                jsonObject2.put("url", (Object)s4);
                jsonObject2.put("description", (Object)s5);
                jsonObject2.put("fbsdk:create_object", true);
                if (jsonObject != null) {
                    jsonObject2.put("data", (Object)jsonObject);
                }
                if (s6 != null) {
                    jsonObject2.put("id", (Object)s6);
                }
                return jsonObject2;
            }
            catch (JSONException ex) {
                throw new FacebookException("An error occurred while setting up the graph object", (Throwable)ex);
            }
        }
    }
    
    public static boolean isOpenGraphObjectForPost(final JSONObject jsonObject) {
        return jsonObject != null && jsonObject.optBoolean("fbsdk:create_object");
    }
}
