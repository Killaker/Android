package com.facebook.share.internal;

import com.facebook.share.model.*;
import com.facebook.internal.*;
import com.facebook.*;
import java.util.*;

private static class ApiValidator extends Validator
{
    @Override
    public void validate(final SharePhoto sharePhoto) {
        ShareContentValidation.access$400(sharePhoto, (Validator)this);
    }
    
    @Override
    public void validate(final ShareVideoContent shareVideoContent) {
        if (!Utility.isNullOrEmpty(shareVideoContent.getPlaceId())) {
            throw new FacebookException("Cannot share video content with place IDs using the share api");
        }
        if (!Utility.isNullOrEmpty(shareVideoContent.getPeopleIds())) {
            throw new FacebookException("Cannot share video content with people IDs using the share api");
        }
        if (!Utility.isNullOrEmpty(shareVideoContent.getRef())) {
            throw new FacebookException("Cannot share video content with referrer URL using the share api");
        }
    }
}
