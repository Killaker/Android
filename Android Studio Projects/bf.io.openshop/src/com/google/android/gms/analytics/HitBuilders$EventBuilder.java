package com.google.android.gms.analytics;

import java.util.*;

public static class EventBuilder extends HitBuilder<EventBuilder>
{
    public EventBuilder() {
        this.set("&t", "event");
    }
    
    public EventBuilder(final String category, final String action) {
        this();
        this.setCategory(category);
        this.setAction(action);
    }
    
    public EventBuilder setAction(final String s) {
        this.set("&ea", s);
        return this;
    }
    
    public EventBuilder setCategory(final String s) {
        this.set("&ec", s);
        return this;
    }
    
    public EventBuilder setLabel(final String s) {
        this.set("&el", s);
        return this;
    }
    
    public EventBuilder setValue(final long n) {
        this.set("&ev", Long.toString(n));
        return this;
    }
}
