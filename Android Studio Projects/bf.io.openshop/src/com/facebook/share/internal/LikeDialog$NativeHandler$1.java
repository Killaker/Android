package com.facebook.share.internal;

import com.facebook.internal.*;
import android.os.*;
import android.util.*;

class LikeDialog$NativeHandler$1 implements ParameterProvider {
    final /* synthetic */ LikeContent val$content;
    
    @Override
    public Bundle getLegacyParameters() {
        Log.e("LikeDialog", "Attempting to present the Like Dialog with an outdated Facebook app on the device");
        return new Bundle();
    }
    
    @Override
    public Bundle getParameters() {
        return LikeDialog.access$200(this.val$content);
    }
}