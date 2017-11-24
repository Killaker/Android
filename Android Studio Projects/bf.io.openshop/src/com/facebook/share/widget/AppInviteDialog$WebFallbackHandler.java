package com.facebook.share.widget;

import com.facebook.share.model.*;
import com.facebook.internal.*;

private class WebFallbackHandler extends ModeHandler
{
    public boolean canShow(final AppInviteContent appInviteContent) {
        return AppInviteDialog.access$500();
    }
    
    public AppCall createAppCall(final AppInviteContent appInviteContent) {
        final AppCall baseAppCall = AppInviteDialog.this.createBaseAppCall();
        DialogPresenter.setupAppCallForWebFallbackDialog(baseAppCall, AppInviteDialog.access$300(appInviteContent), AppInviteDialog.access$400());
        return baseAppCall;
    }
}
