package com.facebook.share.widget;

import com.facebook.share.internal.*;
import com.facebook.*;
import com.facebook.internal.*;
import android.os.*;

class GameRequestDialog$1 extends ResultProcessor {
    final /* synthetic */ FacebookCallback val$callback;
    
    @Override
    public void onSuccess(final AppCall appCall, final Bundle bundle) {
        if (bundle != null) {
            this.val$callback.onSuccess(new Result(bundle));
            return;
        }
        this.onCancel(appCall);
    }
}