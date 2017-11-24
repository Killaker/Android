package com.facebook.share.widget;

import com.facebook.share.model.*;
import android.content.*;
import com.facebook.internal.*;
import android.os.*;
import com.facebook.share.internal.*;

private class NativeHandler extends ModeHandler
{
    public boolean canShow(final ShareContent shareContent) {
        return shareContent != null && ShareDialog.access$300(shareContent.getClass());
    }
    
    public AppCall createAppCall(final ShareContent shareContent) {
        ShareDialog.access$500(ShareDialog.this, (Context)ShareDialog.access$400(ShareDialog.this), shareContent, Mode.NATIVE);
        ShareContentValidation.validateForNativeShare(shareContent);
        final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
        DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
            final /* synthetic */ boolean val$shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            
            @Override
            public Bundle getLegacyParameters() {
                return LegacyNativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
            }
            
            @Override
            public Bundle getParameters() {
                return NativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
            }
        }, ShareDialog.access$600(shareContent.getClass()));
        return baseAppCall;
    }
    
    @Override
    public Object getMode() {
        return Mode.NATIVE;
    }
}
