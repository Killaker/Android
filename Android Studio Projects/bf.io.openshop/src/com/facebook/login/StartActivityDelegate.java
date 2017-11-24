package com.facebook.login;

import android.app.*;
import android.content.*;

interface StartActivityDelegate
{
    Activity getActivityContext();
    
    void startActivityForResult(final Intent p0, final int p1);
}
