package com.facebook.share.internal;

import com.facebook.share.model.*;

private static class Validator
{
    private boolean isOpenGraphContent;
    
    private Validator() {
        this.isOpenGraphContent = false;
    }
    
    public boolean isOpenGraphContent() {
        return this.isOpenGraphContent;
    }
    
    public void validate(final ShareLinkContent shareLinkContent) {
        ShareContentValidation.access$500(shareLinkContent, this);
    }
    
    public void validate(final ShareOpenGraphAction shareOpenGraphAction) {
        ShareContentValidation.access$900(shareOpenGraphAction, this);
    }
    
    public void validate(final ShareOpenGraphContent shareOpenGraphContent) {
        this.isOpenGraphContent = true;
        ShareContentValidation.access$800(shareOpenGraphContent, this);
    }
    
    public void validate(final ShareOpenGraphObject shareOpenGraphObject) {
        ShareContentValidation.access$1000(shareOpenGraphObject, this);
    }
    
    public void validate(final ShareOpenGraphValueContainer shareOpenGraphValueContainer, final boolean b) {
        ShareContentValidation.access$1100(shareOpenGraphValueContainer, this, b);
    }
    
    public void validate(final SharePhoto sharePhoto) {
        ShareContentValidation.access$1200(sharePhoto, this);
    }
    
    public void validate(final SharePhotoContent sharePhotoContent) {
        ShareContentValidation.access$600(sharePhotoContent, this);
    }
    
    public void validate(final ShareVideo shareVideo) {
        ShareContentValidation.access$1300(shareVideo, this);
    }
    
    public void validate(final ShareVideoContent shareVideoContent) {
        ShareContentValidation.access$700(shareVideoContent, this);
    }
}
