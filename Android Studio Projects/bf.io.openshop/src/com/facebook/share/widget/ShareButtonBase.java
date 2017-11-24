package com.facebook.share.widget;

import com.facebook.share.model.*;
import android.content.*;
import android.util.*;
import com.facebook.internal.*;
import com.facebook.share.*;
import android.view.*;
import com.facebook.share.internal.*;
import com.facebook.*;

public abstract class ShareButtonBase extends FacebookButtonBase
{
    private boolean enabledExplicitlySet;
    private int requestCode;
    private ShareContent shareContent;
    
    protected ShareButtonBase(final Context context, final AttributeSet set, final int n, final String s, final String s2) {
        super(context, set, n, 0, s, s2);
        this.requestCode = 0;
        this.enabledExplicitlySet = false;
        int defaultRequestCode;
        if (this.isInEditMode()) {
            defaultRequestCode = 0;
        }
        else {
            defaultRequestCode = this.getDefaultRequestCode();
        }
        this.requestCode = defaultRequestCode;
        this.internalSetEnabled(false);
    }
    
    private void internalSetEnabled(final boolean enabled) {
        this.setEnabled(enabled);
        this.enabledExplicitlySet = false;
    }
    
    protected boolean canShare() {
        return this.getDialog().canShow(this.getShareContent());
    }
    
    @Override
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        super.configureButton(context, set, n, n2);
        this.setInternalOnClickListener(this.getShareOnClickListener());
    }
    
    protected abstract FacebookDialogBase<ShareContent, Sharer.Result> getDialog();
    
    @Override
    public int getRequestCode() {
        return this.requestCode;
    }
    
    public ShareContent getShareContent() {
        return this.shareContent;
    }
    
    protected View$OnClickListener getShareOnClickListener() {
        return (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ShareButtonBase.this.callExternalOnClickListener(view);
                ShareButtonBase.this.getDialog().show(ShareButtonBase.this.getShareContent());
            }
        };
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<Sharer.Result> facebookCallback) {
        ShareInternalUtility.registerSharerCallback(this.getRequestCode(), callbackManager, facebookCallback);
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<Sharer.Result> facebookCallback, final int requestCode) {
        this.setRequestCode(requestCode);
        this.registerCallback(callbackManager, facebookCallback);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.enabledExplicitlySet = true;
    }
    
    protected void setRequestCode(final int requestCode) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            throw new IllegalArgumentException("Request code " + requestCode + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.requestCode = requestCode;
    }
    
    public void setShareContent(final ShareContent shareContent) {
        this.shareContent = shareContent;
        if (!this.enabledExplicitlySet) {
            this.internalSetEnabled(this.canShare());
        }
    }
}
