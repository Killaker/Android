package com.facebook.share.widget;

import com.facebook.share.model.*;
import android.content.*;
import com.facebook.share.internal.*;
import com.facebook.internal.*;
import android.os.*;

private class FeedHandler extends ModeHandler
{
    public boolean canShow(final ShareContent shareContent) {
        return shareContent instanceof ShareLinkContent || shareContent instanceof ShareFeedContent;
    }
    
    public AppCall createAppCall(final ShareContent shareContent) {
        ShareDialog.access$500(ShareDialog.this, (Context)ShareDialog.access$900(ShareDialog.this), shareContent, Mode.FEED);
        final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
        Bundle bundle;
        if (shareContent instanceof ShareLinkContent) {
            final ShareLinkContent shareLinkContent = (ShareLinkContent)shareContent;
            ShareContentValidation.validateForWebShare(shareLinkContent);
            bundle = WebDialogParameters.createForFeed(shareLinkContent);
        }
        else {
            bundle = WebDialogParameters.createForFeed((ShareFeedContent)shareContent);
        }
        DialogPresenter.setupAppCallForWebDialog(baseAppCall, "feed", bundle);
        return baseAppCall;
    }
    
    @Override
    public Object getMode() {
        return Mode.FEED;
    }
}
