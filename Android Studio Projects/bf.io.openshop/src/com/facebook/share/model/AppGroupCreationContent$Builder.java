package com.facebook.share.model;

import android.os.*;

public static class Builder implements ShareModelBuilder<AppGroupCreationContent, Builder>
{
    private String description;
    private String name;
    private AppGroupPrivacy privacy;
    
    @Override
    public AppGroupCreationContent build() {
        return new AppGroupCreationContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((AppGroupCreationContent)parcel.readParcelable(AppGroupCreationContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final AppGroupCreationContent appGroupCreationContent) {
        if (appGroupCreationContent == null) {
            return this;
        }
        return this.setName(appGroupCreationContent.getName()).setDescription(appGroupCreationContent.getDescription()).setAppGroupPrivacy(appGroupCreationContent.getAppGroupPrivacy());
    }
    
    public Builder setAppGroupPrivacy(final AppGroupPrivacy privacy) {
        this.privacy = privacy;
        return this;
    }
    
    public Builder setDescription(final String description) {
        this.description = description;
        return this;
    }
    
    public Builder setName(final String name) {
        this.name = name;
        return this;
    }
}
