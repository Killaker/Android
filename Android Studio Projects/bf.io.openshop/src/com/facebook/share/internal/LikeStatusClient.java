package com.facebook.share.internal;

import com.facebook.internal.*;
import android.content.*;
import android.os.*;

final class LikeStatusClient extends PlatformServiceClient
{
    private String objectId;
    
    LikeStatusClient(final Context context, final String s, final String objectId) {
        super(context, 65542, 65543, 20141001, s);
        this.objectId = objectId;
    }
    
    @Override
    protected void populateRequestBundle(final Bundle bundle) {
        bundle.putString("com.facebook.platform.extra.OBJECT_ID", this.objectId);
    }
}
