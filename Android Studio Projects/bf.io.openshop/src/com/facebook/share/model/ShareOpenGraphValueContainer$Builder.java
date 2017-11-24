package com.facebook.share.model;

import android.support.annotation.*;
import android.os.*;
import java.util.*;

public abstract static class Builder<P extends ShareOpenGraphValueContainer, E extends Builder> implements ShareModelBuilder<P, E>
{
    private Bundle bundle;
    
    public Builder() {
        this.bundle = new Bundle();
    }
    
    public E putBoolean(final String s, final boolean b) {
        this.bundle.putBoolean(s, b);
        return (E)this;
    }
    
    public E putBooleanArray(final String s, @Nullable final boolean[] array) {
        this.bundle.putBooleanArray(s, array);
        return (E)this;
    }
    
    public E putDouble(final String s, final double n) {
        this.bundle.putDouble(s, n);
        return (E)this;
    }
    
    public E putDoubleArray(final String s, @Nullable final double[] array) {
        this.bundle.putDoubleArray(s, array);
        return (E)this;
    }
    
    public E putInt(final String s, final int n) {
        this.bundle.putInt(s, n);
        return (E)this;
    }
    
    public E putIntArray(final String s, @Nullable final int[] array) {
        this.bundle.putIntArray(s, array);
        return (E)this;
    }
    
    public E putLong(final String s, final long n) {
        this.bundle.putLong(s, n);
        return (E)this;
    }
    
    public E putLongArray(final String s, @Nullable final long[] array) {
        this.bundle.putLongArray(s, array);
        return (E)this;
    }
    
    public E putObject(final String s, @Nullable final ShareOpenGraphObject shareOpenGraphObject) {
        this.bundle.putParcelable(s, (Parcelable)shareOpenGraphObject);
        return (E)this;
    }
    
    public E putObjectArrayList(final String s, @Nullable final ArrayList<ShareOpenGraphObject> list) {
        this.bundle.putParcelableArrayList(s, (ArrayList)list);
        return (E)this;
    }
    
    public E putPhoto(final String s, @Nullable final SharePhoto sharePhoto) {
        this.bundle.putParcelable(s, (Parcelable)sharePhoto);
        return (E)this;
    }
    
    public E putPhotoArrayList(final String s, @Nullable final ArrayList<SharePhoto> list) {
        this.bundle.putParcelableArrayList(s, (ArrayList)list);
        return (E)this;
    }
    
    public E putString(final String s, @Nullable final String s2) {
        this.bundle.putString(s, s2);
        return (E)this;
    }
    
    public E putStringArrayList(final String s, @Nullable final ArrayList<String> list) {
        this.bundle.putStringArrayList(s, (ArrayList)list);
        return (E)this;
    }
    
    @Override
    public E readFrom(final P p) {
        if (p != null) {
            this.bundle.putAll(p.getBundle());
        }
        return (E)this;
    }
}
