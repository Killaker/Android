package com.facebook.share.internal;

import android.os.*;
import com.facebook.internal.*;
import java.util.*;
import com.facebook.share.model.*;
import com.facebook.*;
import org.json.*;

public class WebDialogParameters
{
    public static Bundle create(final AppGroupCreationContent appGroupCreationContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "name", appGroupCreationContent.getName());
        Utility.putNonEmptyString(bundle, "description", appGroupCreationContent.getDescription());
        final AppGroupCreationContent.AppGroupPrivacy appGroupPrivacy = appGroupCreationContent.getAppGroupPrivacy();
        if (appGroupPrivacy != null) {
            Utility.putNonEmptyString(bundle, "privacy", appGroupPrivacy.toString().toLowerCase(Locale.ENGLISH));
        }
        return bundle;
    }
    
    public static Bundle create(final GameRequestContent gameRequestContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "message", gameRequestContent.getMessage());
        Utility.putCommaSeparatedStringList(bundle, "to", gameRequestContent.getRecipients());
        Utility.putNonEmptyString(bundle, "title", gameRequestContent.getTitle());
        Utility.putNonEmptyString(bundle, "data", gameRequestContent.getData());
        if (gameRequestContent.getActionType() != null) {
            Utility.putNonEmptyString(bundle, "action_type", gameRequestContent.getActionType().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putNonEmptyString(bundle, "object_id", gameRequestContent.getObjectId());
        if (gameRequestContent.getFilters() != null) {
            Utility.putNonEmptyString(bundle, "filters", gameRequestContent.getFilters().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putCommaSeparatedStringList(bundle, "suggestions", gameRequestContent.getSuggestions());
        return bundle;
    }
    
    public static Bundle create(final ShareLinkContent shareLinkContent) {
        final Bundle bundle = new Bundle();
        Utility.putUri(bundle, "href", shareLinkContent.getContentUrl());
        return bundle;
    }
    
    public static Bundle create(final ShareOpenGraphContent shareOpenGraphContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "action_type", shareOpenGraphContent.getAction().getActionType());
        try {
            final JSONObject removeNamespacesFromOGJsonObject = ShareInternalUtility.removeNamespacesFromOGJsonObject(ShareInternalUtility.toJSONObjectForWeb(shareOpenGraphContent), false);
            if (removeNamespacesFromOGJsonObject != null) {
                Utility.putNonEmptyString(bundle, "action_properties", removeNamespacesFromOGJsonObject.toString());
            }
            return bundle;
        }
        catch (JSONException ex) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", (Throwable)ex);
        }
    }
    
    public static Bundle createForFeed(final ShareFeedContent shareFeedContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "to", shareFeedContent.getToId());
        Utility.putNonEmptyString(bundle, "link", shareFeedContent.getLink());
        Utility.putNonEmptyString(bundle, "picture", shareFeedContent.getPicture());
        Utility.putNonEmptyString(bundle, "source", shareFeedContent.getMediaSource());
        Utility.putNonEmptyString(bundle, "name", shareFeedContent.getLinkName());
        Utility.putNonEmptyString(bundle, "caption", shareFeedContent.getLinkCaption());
        Utility.putNonEmptyString(bundle, "description", shareFeedContent.getLinkDescription());
        return bundle;
    }
    
    public static Bundle createForFeed(final ShareLinkContent shareLinkContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "name", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(bundle, "description", shareLinkContent.getContentDescription());
        Utility.putNonEmptyString(bundle, "link", Utility.getUriString(shareLinkContent.getContentUrl()));
        Utility.putNonEmptyString(bundle, "picture", Utility.getUriString(shareLinkContent.getImageUrl()));
        return bundle;
    }
}
