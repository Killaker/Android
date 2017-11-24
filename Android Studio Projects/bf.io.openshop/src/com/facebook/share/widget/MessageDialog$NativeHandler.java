package com.facebook.share.widget;

import com.facebook.share.model.*;
import com.facebook.internal.*;
import android.os.*;
import com.facebook.share.internal.*;

private class NativeHandler extends ModeHandler
{
    public boolean canShow(final ShareContent shareContent) {
        return shareContent != null && MessageDialog.canShow(shareContent.getClass());
    }
    
    public AppCall createAppCall(final ShareContent shareContent) {
        ShareContentValidation.validateForMessage(shareContent);
        final AppCall baseAppCall = MessageDialog.this.createBaseAppCall();
        final boolean shouldFailOnDataError = MessageDialog.this.getShouldFailOnDataError();
        MessageDialog.access$100(MessageDialog.this);
        DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
            @Override
            public Bundle getLegacyParameters() {
                return LegacyNativeDialogParameters.create(baseAppCall.getCallId(), shareContent, shouldFailOnDataError);
            }
            
            @Override
            public Bundle getParameters() {
                return NativeDialogParameters.create(baseAppCall.getCallId(), shareContent, shouldFailOnDataError);
            }
        }, MessageDialog.access$200(shareContent.getClass()));
        return baseAppCall;
    }
}
