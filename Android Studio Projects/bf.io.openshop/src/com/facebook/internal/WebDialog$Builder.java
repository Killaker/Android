package com.facebook.internal;

import android.content.*;
import android.os.*;
import com.facebook.*;

public static class Builder
{
    private AccessToken accessToken;
    private String action;
    private String applicationId;
    private Context context;
    private OnCompleteListener listener;
    private Bundle parameters;
    private int theme;
    
    public Builder(final Context context, final String s, final Bundle bundle) {
        this.accessToken = AccessToken.getCurrentAccessToken();
        if (this.accessToken == null) {
            final String metadataApplicationId = Utility.getMetadataApplicationId(context);
            if (metadataApplicationId == null) {
                throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
            }
            this.applicationId = metadataApplicationId;
        }
        this.finishInit(context, s, bundle);
    }
    
    public Builder(final Context context, String metadataApplicationId, final String s, final Bundle bundle) {
        if (metadataApplicationId == null) {
            metadataApplicationId = Utility.getMetadataApplicationId(context);
        }
        Validate.notNullOrEmpty(metadataApplicationId, "applicationId");
        this.applicationId = metadataApplicationId;
        this.finishInit(context, s, bundle);
    }
    
    private void finishInit(final Context context, final String action, final Bundle parameters) {
        this.context = context;
        this.action = action;
        if (parameters != null) {
            this.parameters = parameters;
            return;
        }
        this.parameters = new Bundle();
    }
    
    public WebDialog build() {
        if (this.accessToken != null) {
            this.parameters.putString("app_id", this.accessToken.getApplicationId());
            this.parameters.putString("access_token", this.accessToken.getToken());
        }
        else {
            this.parameters.putString("app_id", this.applicationId);
        }
        return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
    }
    
    public String getApplicationId() {
        return this.applicationId;
    }
    
    public Context getContext() {
        return this.context;
    }
    
    public OnCompleteListener getListener() {
        return this.listener;
    }
    
    public Bundle getParameters() {
        return this.parameters;
    }
    
    public int getTheme() {
        return this.theme;
    }
    
    public Builder setOnCompleteListener(final OnCompleteListener listener) {
        this.listener = listener;
        return this;
    }
    
    public Builder setTheme(final int theme) {
        this.theme = theme;
        return this;
    }
}
