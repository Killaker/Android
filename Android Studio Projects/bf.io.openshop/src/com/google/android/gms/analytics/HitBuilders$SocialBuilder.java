package com.google.android.gms.analytics;

import java.util.*;

public static class SocialBuilder extends HitBuilder<SocialBuilder>
{
    public SocialBuilder() {
        this.set("&t", "social");
    }
    
    public SocialBuilder setAction(final String s) {
        this.set("&sa", s);
        return this;
    }
    
    public SocialBuilder setNetwork(final String s) {
        this.set("&sn", s);
        return this;
    }
    
    public SocialBuilder setTarget(final String s) {
        this.set("&st", s);
        return this;
    }
}
