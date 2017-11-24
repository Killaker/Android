package com.facebook.share.internal;

import android.os.*;
import com.facebook.share.model.*;
import java.util.*;
import com.facebook.internal.*;
import com.facebook.*;
import org.json.*;

public class NativeDialogParameters
{
    private static Bundle create(final ShareLinkContent shareLinkContent, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareLinkContent, b);
        Utility.putNonEmptyString(baseParameters, "TITLE", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(baseParameters, "DESCRIPTION", shareLinkContent.getContentDescription());
        Utility.putUri(baseParameters, "IMAGE", shareLinkContent.getImageUrl());
        return baseParameters;
    }
    
    private static Bundle create(final ShareOpenGraphContent shareOpenGraphContent, final JSONObject jsonObject, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareOpenGraphContent, b);
        Utility.putNonEmptyString(baseParameters, "PREVIEW_PROPERTY_NAME", (String)ShareInternalUtility.getFieldNameAndNamespaceFromFullName(shareOpenGraphContent.getPreviewPropertyName()).second);
        Utility.putNonEmptyString(baseParameters, "ACTION_TYPE", shareOpenGraphContent.getAction().getActionType());
        Utility.putNonEmptyString(baseParameters, "ACTION", jsonObject.toString());
        return baseParameters;
    }
    
    private static Bundle create(final SharePhotoContent sharePhotoContent, final List<String> list, final boolean b) {
        final Bundle baseParameters = createBaseParameters(sharePhotoContent, b);
        baseParameters.putStringArrayList("PHOTOS", new ArrayList((Collection<? extends E>)list));
        return baseParameters;
    }
    
    private static Bundle create(final ShareVideoContent shareVideoContent, final String s, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareVideoContent, b);
        Utility.putNonEmptyString(baseParameters, "TITLE", shareVideoContent.getContentTitle());
        Utility.putNonEmptyString(baseParameters, "DESCRIPTION", shareVideoContent.getContentDescription());
        Utility.putNonEmptyString(baseParameters, "VIDEO", s);
        return baseParameters;
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
                final ShareVideoContent shareVideoContent = (ShareVideoContent)shareContent;
                return create(shareVideoContent, ShareInternalUtility.getVideoUrl(shareVideoContent, uuid), b);
            }
            final boolean b2 = shareContent instanceof ShareOpenGraphContent;
            create = null;
            if (b2) {
                final ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent)shareContent;
                try {
                    return create(shareOpenGraphContent, ShareInternalUtility.removeNamespacesFromOGJsonObject(ShareInternalUtility.toJSONObjectForCall(uuid, shareOpenGraphContent), false), b);
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
        Utility.putUri(bundle, "LINK", shareContent.getContentUrl());
        Utility.putNonEmptyString(bundle, "PLACE", shareContent.getPlaceId());
        Utility.putNonEmptyString(bundle, "REF", shareContent.getRef());
        bundle.putBoolean("DATA_FAILURES_FATAL", b);
        final List peopleIds = shareContent.getPeopleIds();
        if (!Utility.isNullOrEmpty((Collection<Object>)peopleIds)) {
            bundle.putStringArrayList("FRIENDS", new ArrayList(peopleIds));
        }
        return bundle;
    }
}
