package com.facebook.share.internal;

import com.facebook.*;
import com.facebook.share.model.*;

private static class WebShareValidator extends Validator
{
    @Override
    public void validate(final SharePhoto sharePhoto) {
        ShareContentValidation.access$300(sharePhoto, (Validator)this);
    }
    
    @Override
    public void validate(final SharePhotoContent sharePhotoContent) {
        throw new FacebookException("Cannot share SharePhotoContent via web sharing dialogs");
    }
    
    @Override
    public void validate(final ShareVideoContent shareVideoContent) {
        throw new FacebookException("Cannot share ShareVideoContent via web sharing dialogs");
    }
}
