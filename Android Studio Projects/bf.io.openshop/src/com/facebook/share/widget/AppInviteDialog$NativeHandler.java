package com.facebook.share.widget;

import com.facebook.share.model.*;
import com.facebook.internal.*;
import android.os.*;
import android.util.*;

private class NativeHandler extends ModeHandler
{
    public boolean canShow(final AppInviteContent appInviteContent) {
        return AppInviteDialog.access$200();
    }
    
    public AppCall createAppCall(final AppInviteContent appInviteContent) {
        final AppCall baseAppCall = AppInviteDialog.this.createBaseAppCall();
        DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
            @Override
            public Bundle getLegacyParameters() {
                Log.e("AppInviteDialog", "Attempting to present the AppInviteDialog with an outdated Facebook app on the device");
                return new Bundle();
            }
            
            @Override
            public Bundle getParameters() {
                return AppInviteDialog.access$300(appInviteContent);
            }
        }, AppInviteDialog.access$400());
        return baseAppCall;
    }
}
