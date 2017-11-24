package com.facebook.share.widget;

import android.content.*;
import android.util.*;
import com.facebook.*;
import com.facebook.internal.*;
import com.facebook.share.model.*;
import com.facebook.share.*;

public final class ShareButton extends ShareButtonBase
{
    public ShareButton(final Context context) {
        super(context, null, 0, "fb_share_button_create", "fb_share_button_did_tap");
    }
    
    public ShareButton(final Context context, final AttributeSet set) {
        super(context, set, 0, "fb_share_button_create", "fb_share_button_did_tap");
    }
    
    public ShareButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, "fb_share_button_create", "fb_share_button_did_tap");
    }
    
    @Override
    protected int getDefaultRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Share.toRequestCode();
    }
    
    @Override
    protected int getDefaultStyleResource() {
        return R.style.com_facebook_button_share;
    }
    
    @Override
    protected FacebookDialogBase<ShareContent, Sharer.Result> getDialog() {
        if (this.getFragment() != null) {
            return new ShareDialog(this.getFragment(), this.getRequestCode());
        }
        if (this.getNativeFragment() != null) {
            return new ShareDialog(this.getNativeFragment(), this.getRequestCode());
        }
        return new ShareDialog(this.getActivity(), this.getRequestCode());
    }
}
