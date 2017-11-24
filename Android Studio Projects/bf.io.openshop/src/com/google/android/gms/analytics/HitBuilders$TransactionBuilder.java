package com.google.android.gms.analytics;

import java.util.*;

@Deprecated
public static class TransactionBuilder extends HitBuilder<TransactionBuilder>
{
    public TransactionBuilder() {
        this.set("&t", "transaction");
    }
    
    public TransactionBuilder setAffiliation(final String s) {
        this.set("&ta", s);
        return this;
    }
    
    public TransactionBuilder setCurrencyCode(final String s) {
        this.set("&cu", s);
        return this;
    }
    
    public TransactionBuilder setRevenue(final double n) {
        this.set("&tr", Double.toString(n));
        return this;
    }
    
    public TransactionBuilder setShipping(final double n) {
        this.set("&ts", Double.toString(n));
        return this;
    }
    
    public TransactionBuilder setTax(final double n) {
        this.set("&tt", Double.toString(n));
        return this;
    }
    
    public TransactionBuilder setTransactionId(final String s) {
        this.set("&ti", s);
        return this;
    }
}
