package com.facebook.share;

import com.facebook.internal.*;
import com.facebook.share.model.*;
import java.util.*;
import com.facebook.*;
import org.json.*;

class ShareApi$9 implements Collection<String> {
    final /* synthetic */ ShareOpenGraphObject val$object;
    final /* synthetic */ JSONObject val$stagedObject;
    
    public Object get(final String s) {
        return this.val$object.get(s);
    }
    
    @Override
    public Iterator<String> keyIterator() {
        return this.val$object.keySet().iterator();
    }
    
    public void set(final String s, final Object o, final OnErrorListener onErrorListener) {
        try {
            this.val$stagedObject.put(s, o);
        }
        catch (JSONException ex) {
            String localizedMessage = ex.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "Error staging object.";
            }
            onErrorListener.onError(new FacebookException(localizedMessage));
        }
    }
}