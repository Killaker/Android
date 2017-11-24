package com.squareup.picasso;

import android.annotation.*;
import android.app.*;

@TargetApi(11)
private static class ActivityManagerHoneycomb
{
    static int getLargeMemoryClass(final ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }
}
