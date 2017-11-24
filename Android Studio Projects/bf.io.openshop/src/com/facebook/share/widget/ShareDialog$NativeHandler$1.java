package com.facebook.share.widget;

import com.facebook.internal.*;
import com.facebook.share.model.*;
import android.os.*;
import com.facebook.share.internal.*;

class ShareDialog$NativeHandler$1 implements ParameterProvider {
    final /* synthetic */ AppCall val$appCall;
    final /* synthetic */ ShareContent val$content;
    final /* synthetic */ boolean val$shouldFailOnDataError;
    
    @Override
    public Bundle getLegacyParameters() {
        return LegacyNativeDialogParameters.create(this.val$appCall.getCallId(), this.val$content, this.val$shouldFailOnDataError);
    }
    
    @Override
    public Bundle getParameters() {
        return NativeDialogParameters.create(this.val$appCall.getCallId(), this.val$content, this.val$shouldFailOnDataError);
    }
}