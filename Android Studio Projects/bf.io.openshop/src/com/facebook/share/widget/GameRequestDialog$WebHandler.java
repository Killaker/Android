package com.facebook.share.widget;

import com.facebook.share.model.*;
import com.facebook.share.internal.*;
import com.facebook.internal.*;

private class WebHandler extends ModeHandler
{
    public boolean canShow(final GameRequestContent gameRequestContent) {
        return true;
    }
    
    public AppCall createAppCall(final GameRequestContent gameRequestContent) {
        GameRequestValidation.validate(gameRequestContent);
        final AppCall baseAppCall = GameRequestDialog.this.createBaseAppCall();
        DialogPresenter.setupAppCallForWebDialog(baseAppCall, "apprequests", WebDialogParameters.create(gameRequestContent));
        return baseAppCall;
    }
}
