package com.facebook.share.widget;

import com.facebook.share.model.*;
import android.content.*;
import com.facebook.share.internal.*;
import com.facebook.internal.*;
import android.os.*;

private class WebShareHandler extends ModeHandler
{
    private String getActionName(final ShareContent shareContent) {
        if (shareContent instanceof ShareLinkContent) {
            return "share";
        }
        if (shareContent instanceof ShareOpenGraphContent) {
            return "share_open_graph";
        }
        return null;
    }
    
    public boolean canShow(final ShareContent shareContent) {
        return shareContent != null && ShareDialog.access$700(shareContent.getClass());
    }
    
    public AppCall createAppCall(final ShareContent shareContent) {
        ShareDialog.access$500(ShareDialog.this, (Context)ShareDialog.access$800(ShareDialog.this), shareContent, Mode.WEB);
        final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
        ShareContentValidation.validateForWebShare(shareContent);
        Bundle bundle;
        if (shareContent instanceof ShareLinkContent) {
            bundle = WebDialogParameters.create((ShareLinkContent)shareContent);
        }
        else {
            bundle = WebDialogParameters.create((ShareOpenGraphContent)shareContent);
        }
        DialogPresenter.setupAppCallForWebDialog(baseAppCall, this.getActionName(shareContent), bundle);
        return baseAppCall;
    }
    
    @Override
    public Object getMode() {
        return Mode.WEB;
    }
}
