package com.facebook.share.internal;

import java.util.*;
import org.json.*;
import android.support.annotation.*;
import com.facebook.share.model.*;

public final class OpenGraphJSONUtility
{
    private static JSONArray toJSONArray(final List list, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put(toJSONValue(iterator.next(), photoJSONProcessor));
        }
        return jsonArray;
    }
    
    public static JSONObject toJSONObject(final ShareOpenGraphAction shareOpenGraphAction, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : shareOpenGraphAction.keySet()) {
            jsonObject.put(s, toJSONValue(shareOpenGraphAction.get(s), photoJSONProcessor));
        }
        return jsonObject;
    }
    
    private static JSONObject toJSONObject(final ShareOpenGraphObject shareOpenGraphObject, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : shareOpenGraphObject.keySet()) {
            jsonObject.put(s, toJSONValue(shareOpenGraphObject.get(s), photoJSONProcessor));
        }
        return jsonObject;
    }
    
    public static Object toJSONValue(@Nullable Object null, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        if (null == null) {
            null = JSONObject.NULL;
        }
        else if (!(null instanceof String) && !(null instanceof Boolean) && !(null instanceof Double) && !(null instanceof Float) && !(null instanceof Integer) && !(null instanceof Long)) {
            if (null instanceof SharePhoto) {
                if (photoJSONProcessor != null) {
                    return photoJSONProcessor.toJSONObject((SharePhoto)null);
                }
                return null;
            }
            else {
                if (null instanceof ShareOpenGraphObject) {
                    return toJSONObject((ShareOpenGraphObject)null, photoJSONProcessor);
                }
                if (null instanceof List) {
                    return toJSONArray((List)null, photoJSONProcessor);
                }
                throw new IllegalArgumentException("Invalid object found for JSON serialization: " + null.toString());
            }
        }
        return null;
    }
    
    public interface PhotoJSONProcessor
    {
        JSONObject toJSONObject(final SharePhoto p0);
    }
}
