package com.google.android.gms.analytics;

import java.util.*;

@Deprecated
public static class ItemBuilder extends HitBuilder<ItemBuilder>
{
    public ItemBuilder() {
        this.set("&t", "item");
    }
    
    public ItemBuilder setCategory(final String s) {
        this.set("&iv", s);
        return this;
    }
    
    public ItemBuilder setCurrencyCode(final String s) {
        this.set("&cu", s);
        return this;
    }
    
    public ItemBuilder setName(final String s) {
        this.set("&in", s);
        return this;
    }
    
    public ItemBuilder setPrice(final double n) {
        this.set("&ip", Double.toString(n));
        return this;
    }
    
    public ItemBuilder setQuantity(final long n) {
        this.set("&iq", Long.toString(n));
        return this;
    }
    
    public ItemBuilder setSku(final String s) {
        this.set("&ic", s);
        return this;
    }
    
    public ItemBuilder setTransactionId(final String s) {
        this.set("&ti", s);
        return this;
    }
}
