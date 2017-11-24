package com.facebook.share.widget;

import android.content.*;
import android.util.*;
import com.facebook.*;
import com.facebook.internal.*;
import com.facebook.share.model.*;
import com.facebook.share.*;

public final class SendButton extends ShareButtonBase
{
    public SendButton(final Context context) {
        super(context, null, 0, "fb_send_button_create", "fb_send_button_did_tap");
    }
    
    public SendButton(final Context context, final AttributeSet set) {
        super(context, set, 0, "fb_send_button_create", "fb_send_button_did_tap");
    }
    
    public SendButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, "fb_send_button_create", "fb_send_button_did_tap");
    }
    
    @Override
    protected int getDefaultRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Message.toRequestCode();
    }
    
    @Override
    protected int getDefaultStyleResource() {
        return R.style.com_facebook_button_send;
    }
    
    @Override
    protected FacebookDialogBase<ShareContent, Sharer.Result> getDialog() {
        if (this.getFragment() != null) {
            return new MessageDialog(this.getFragment(), this.getRequestCode());
        }
        if (this.getNativeFragment() != null) {
            return new MessageDialog(this.getNativeFragment(), this.getRequestCode());
        }
        return new MessageDialog(this.getActivity(), this.getRequestCode());
    }
}
