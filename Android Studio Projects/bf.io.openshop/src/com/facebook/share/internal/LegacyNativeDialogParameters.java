package com.facebook.share.internal;

import android.os.*;
import com.facebook.share.model.*;
import java.util.*;
import com.facebook.internal.*;
import com.facebook.*;
import org.json.*;

public class LegacyNativeDialogParameters
{
    private static Bundle create(final ShareLinkContent shareLinkContent, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareLinkContent, b);
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.TITLE", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.DESCRIPTION", shareLinkContent.getContentDescription());
        Utility.putUri(baseParameters, "com.facebook.platform.extra.IMAGE", shareLinkContent.getImageUrl());
        return baseParameters;
    }
    
    private static Bundle create(final ShareOpenGraphContent shareOpenGraphContent, final JSONObject jsonObject, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareOpenGraphContent, b);
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", shareOpenGraphContent.getPreviewPropertyName());
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.ACTION_TYPE", shareOpenGraphContent.getAction().getActionType());
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.ACTION", jsonObject.toString());
        return baseParameters;
    }
    
    private static Bundle create(final SharePhotoContent sharePhotoContent, final List<String> list, final boolean b) {
        final Bundle baseParameters = createBaseParameters(sharePhotoContent, b);
        baseParameters.putStringArrayList("com.facebook.platform.extra.PHOTOS", new ArrayList((Collection<? extends E>)list));
        return baseParameters;
    }
    
    private static Bundle create(final ShareVideoContent shareVideoContent, final boolean b) {
        return null;
    }
    
    public static Bundle create(final UUID uuid, final ShareContent shareContent, final boolean b) {
        Validate.notNull(shareContent, "shareContent");
        Validate.notNull(uuid, "callId");
        Bundle create;
        if (shareContent instanceof ShareLinkContent) {
            create = create((ShareLinkContent)shareContent, b);
        }
        else {
            if (shareContent instanceof SharePhotoContent) {
                final SharePhotoContent sharePhotoContent = (SharePhotoContent)shareContent;
                return create(sharePhotoContent, ShareInternalUtility.getPhotoUrls(sharePhotoContent, uuid), b);
            }
            if (shareContent instanceof ShareVideoContent) {
                return create((ShareVideoContent)shareContent, b);
            }
            final boolean b2 = shareContent instanceof ShareOpenGraphContent;
            create = null;
            if (b2) {
                final ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent)shareContent;
                try {
                    return create(shareOpenGraphContent, ShareInternalUtility.toJSONObjectForCall(uuid, shareOpenGraphContent), b);
                }
                catch (JSONException ex) {
                    throw new FacebookException("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + ex.getMessage());
                }
            }
        }
        return create;
    }
    
    private static Bundle createBaseParameters(final ShareContent shareContent, final boolean b) {
        final Bundle bundle = new Bundle();
        Utility.putUri(bundle, "com.facebook.platform.extra.LINK", shareContent.getContentUrl());
        Utility.putNonEmptyString(bundle, "com.facebook.platform.extra.PLACE", shareContent.getPlaceId());
        Utility.putNonEmptyString(bundle, "com.facebook.platform.extra.REF", shareContent.getRef());
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", b);
        final List peopleIds = shareContent.getPeopleIds();
        if (!Utility.isNullOrEmpty((Collection<Object>)peopleIds)) {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", new ArrayList(peopleIds));
        }
        return bundle;
    }
}
