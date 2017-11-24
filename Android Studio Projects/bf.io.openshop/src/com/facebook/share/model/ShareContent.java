package com.facebook.share.model;

import android.net.*;
import java.util.*;
import android.support.annotation.*;
import android.os.*;

public abstract class ShareContent<P extends ShareContent, E extends Builder> implements ShareModel
{
    private final Uri contentUrl;
    private final List<String> peopleIds;
    private final String placeId;
    private final String ref;
    
    protected ShareContent(final Parcel parcel) {
        this.contentUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.peopleIds = this.readUnmodifiableStringList(parcel);
        this.placeId = parcel.readString();
        this.ref = parcel.readString();
    }
    
    protected ShareContent(final Builder builder) {
        this.contentUrl = builder.contentUrl;
        this.peopleIds = builder.peopleIds;
        this.placeId = builder.placeId;
        this.ref = builder.ref;
    }
    
    private List<String> readUnmodifiableStringList(final Parcel parcel) {
        final ArrayList list = new ArrayList<String>();
        parcel.readStringList((List)list);
        if (list.size() == 0) {
            return null;
        }
        return Collections.unmodifiableList((List<? extends String>)list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Uri getContentUrl() {
        return this.contentUrl;
    }
    
    @Nullable
    public List<String> getPeopleIds() {
        return this.peopleIds;
    }
    
    @Nullable
    public String getPlaceId() {
        return this.placeId;
    }
    
    @Nullable
    public String getRef() {
        return this.ref;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable((Parcelable)this.contentUrl, 0);
        parcel.writeStringList((List)this.peopleIds);
        parcel.writeString(this.placeId);
        parcel.writeString(this.ref);
    }
    
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
}
