package com.facebook.share.widget;

import com.facebook.share.model.*;
import com.facebook.share.internal.*;
import com.facebook.internal.*;

private class WebHandler extends ModeHandler
{
    public boolean canShow(final AppGroupCreationContent appGroupCreationContent) {
        return true;
    }
    
    public AppCall createAppCall(final AppGroupCreationContent appGroupCreationContent) {
        final AppCall baseAppCall = CreateAppGroupDialog.this.createBaseAppCall();
        DialogPresenter.setupAppCallForWebDialog(baseAppCall, "game_group_create", WebDialogParameters.create(appGroupCreationContent));
        return baseAppCall;
    }
}
