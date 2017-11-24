package com.facebook.share.widget;

import com.facebook.share.internal.*;
import com.facebook.*;
import com.facebook.internal.*;
import android.os.*;

class JoinAppGroupDialog$1 extends ResultProcessor {
    final /* synthetic */ FacebookCallback val$callback;
    
    @Override
    public void onSuccess(final AppCall appCall, final Bundle bundle) {
        this.val$callback.onSuccess(new Result(bundle));
    }
}