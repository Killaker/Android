package com.facebook.login;

import com.facebook.internal.*;
import android.content.*;
import android.os.*;

final class GetTokenClient extends PlatformServiceClient
{
    GetTokenClient(final Context context, final String s) {
        super(context, 65536, 65537, 20121101, s);
    }
    
    @Override
    protected void populateRequestBundle(final Bundle bundle) {
    }
}
