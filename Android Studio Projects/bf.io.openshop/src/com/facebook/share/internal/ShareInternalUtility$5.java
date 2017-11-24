package com.facebook.share.internal;

import com.facebook.internal.*;

static final class ShareInternalUtility$5 implements Mapper<NativeAppCallAttachmentStore.Attachment, String> {
    public String apply(final NativeAppCallAttachmentStore.Attachment attachment) {
        return attachment.getAttachmentUrl();
    }
}