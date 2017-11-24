package com.google.android.gms.analytics;

import java.util.*;

public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder>
{
    public ScreenViewBuilder() {
        this.set("&t", "screenview");
    }
}
