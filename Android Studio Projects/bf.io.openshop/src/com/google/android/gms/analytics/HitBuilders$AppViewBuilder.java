package com.google.android.gms.analytics;

import java.util.*;

@Deprecated
public static class AppViewBuilder extends HitBuilder<AppViewBuilder>
{
    public AppViewBuilder() {
        this.set("&t", "screenview");
    }
}
