package com.facebook.share.widget;

import com.facebook.internal.*;
import com.facebook.share.model.*;
import android.os.*;
import android.util.*;

class AppInviteDialog$NativeHandler$1 implements ParameterProvider {
    final /* synthetic */ AppInviteContent val$content;
    
    @Override
    public Bundle getLegacyParameters() {
        Log.e("AppInviteDialog", "Attempting to present the AppInviteDialog with an outdated Facebook app on the device");
        return new Bundle();
    }
    
    @Override
    public Bundle getParameters() {
        return AppInviteDialog.access$300(this.val$content);
    }
}