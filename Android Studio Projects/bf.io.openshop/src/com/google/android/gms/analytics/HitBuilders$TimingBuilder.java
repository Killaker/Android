package com.google.android.gms.analytics;

import java.util.*;

public static class TimingBuilder extends HitBuilder<TimingBuilder>
{
    public TimingBuilder() {
        this.set("&t", "timing");
    }
    
    public TimingBuilder(final String category, final String variable, final long value) {
        this();
        this.setVariable(variable);
        this.setValue(value);
        this.setCategory(category);
    }
    
    public TimingBuilder setCategory(final String s) {
        this.set("&utc", s);
        return this;
    }
    
    public TimingBuilder setLabel(final String s) {
        this.set("&utl", s);
        return this;
    }
    
    public TimingBuilder setValue(final long n) {
        this.set("&utt", Long.toString(n));
        return this;
    }
    
    public TimingBuilder setVariable(final String s) {
        this.set("&utv", s);
        return this;
    }
}
