package com.facebook.share.model;

import android.net.*;
import android.support.annotation.*;
import java.util.*;

public abstract static class Builder<P extends ShareContent, E extends Builder> implements ShareModelBuilder<P, E>
{
    private Uri contentUrl;
    private List<String> peopleIds;
    private String placeId;
    private String ref;
    
    @Override
    public E readFrom(final P p) {
        if (p == null) {
            return (E)this;
        }
        return (E)this.setContentUrl(p.getContentUrl()).setPeopleIds(p.getPeopleIds()).setPlaceId(p.getPlaceId()).setRef(p.getRef());
    }
    
    public E setContentUrl(@Nullable final Uri contentUrl) {
        this.contentUrl = contentUrl;
        return (E)this;
    }
    
    public E setPeopleIds(@Nullable final List<String> list) {
        List<String> unmodifiableList;
        if (list == null) {
            unmodifiableList = null;
        }
        else {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        this.peopleIds = unmodifiableList;
        return (E)this;
    }
    
    public E setPlaceId(@Nullable final String placeId) {
        this.placeId = placeId;
        return (E)this;
    }
    
    public E setRef(@Nullable final String ref) {
        this.ref = ref;
        return (E)this;
    }
}
