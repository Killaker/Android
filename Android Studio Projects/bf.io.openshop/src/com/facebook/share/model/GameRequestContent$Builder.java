package com.facebook.share.model;

import android.os.*;
import java.util.*;

public static class Builder implements ShareModelBuilder<GameRequestContent, Builder>
{
    private ActionType actionType;
    private String data;
    private Filters filters;
    private String message;
    private String objectId;
    private List<String> recipients;
    private List<String> suggestions;
    private String title;
    
    @Override
    public GameRequestContent build() {
        return new GameRequestContent(this, null);
    }
    
    @Override
    public Builder readFrom(final Parcel parcel) {
        return this.readFrom((GameRequestContent)parcel.readParcelable(GameRequestContent.class.getClassLoader()));
    }
    
    @Override
    public Builder readFrom(final GameRequestContent gameRequestContent) {
        if (gameRequestContent == null) {
            return this;
        }
        return this.setMessage(gameRequestContent.getMessage()).setRecipients(gameRequestContent.getRecipients()).setTitle(gameRequestContent.getTitle()).setData(gameRequestContent.getData()).setActionType(gameRequestContent.getActionType()).setObjectId(gameRequestContent.getObjectId()).setFilters(gameRequestContent.getFilters()).setSuggestions(gameRequestContent.getSuggestions());
    }
    
    public Builder setActionType(final ActionType actionType) {
        this.actionType = actionType;
        return this;
    }
    
    public Builder setData(final String data) {
        this.data = data;
        return this;
    }
    
    public Builder setFilters(final Filters filters) {
        this.filters = filters;
        return this;
    }
    
    public Builder setMessage(final String message) {
        this.message = message;
        return this;
    }
    
    public Builder setObjectId(final String objectId) {
        this.objectId = objectId;
        return this;
    }
    
    public Builder setRecipients(final List<String> recipients) {
        this.recipients = recipients;
        return this;
    }
    
    public Builder setSuggestions(final List<String> suggestions) {
        this.suggestions = suggestions;
        return this;
    }
    
    public Builder setTitle(final String title) {
        this.title = title;
        return this;
    }
    
    public Builder setTo(final String s) {
        if (s != null) {
            this.recipients = Arrays.asList(s.split(","));
        }
        return this;
    }
}
