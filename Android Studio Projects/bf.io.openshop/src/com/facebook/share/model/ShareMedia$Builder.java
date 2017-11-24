package com.facebook.share.model;

import android.os.*;

public abstract static class Builder<M extends ShareMedia, B extends Builder> implements ShareModelBuilder<M, B>
{
    private Bundle params;
    
    public Builder() {
        this.params = new Bundle();
    }
    
    @Override
    public B readFrom(final M m) {
        if (m == null) {
            return (B)this;
        }
        return this.setParameters(m.getParameters());
    }
    
    @Deprecated
    public B setParameter(final String s, final String s2) {
        this.params.putString(s, s2);
        return (B)this;
    }
    
    @Deprecated
    public B setParameters(final Bundle bundle) {
        this.params.putAll(bundle);
        return (B)this;
    }
}
