package com.facebook.share.internal;

import java.util.*;
import com.facebook.share.model.*;
import com.facebook.*;
import org.json.*;
import com.facebook.internal.*;

static final class ShareInternalUtility$6 implements PhotoJSONProcessor {
    final /* synthetic */ ArrayList val$attachments;
    final /* synthetic */ UUID val$callId;
    
    @Override
    public JSONObject toJSONObject(final SharePhoto sharePhoto) {
        final NativeAppCallAttachmentStore.Attachment access$000 = ShareInternalUtility.access$000(this.val$callId, sharePhoto);
        JSONObject jsonObject;
        if (access$000 == null) {
            jsonObject = null;
        }
        else {
            this.val$attachments.add(access$000);
            jsonObject = new JSONObject();
            try {
                jsonObject.put("url", (Object)access$000.getAttachmentUrl());
                if (sharePhoto.getUserGenerated()) {
                    jsonObject.put("user_generated", true);
                    return jsonObject;
                }
            }
            catch (JSONException ex) {
                throw new FacebookException("Unable to attach images", (Throwable)ex);
            }
        }
        return jsonObject;
    }
}