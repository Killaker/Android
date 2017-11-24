package com.facebook.share.internal;

import com.facebook.share.model.*;
import com.facebook.internal.*;
import java.util.*;

static final class ShareInternalUtility$4 implements Mapper<SharePhoto, NativeAppCallAttachmentStore.Attachment> {
    final /* synthetic */ UUID val$appCallId;
    
    public NativeAppCallAttachmentStore.Attachment apply(final SharePhoto sharePhoto) {
        return ShareInternalUtility.access$000(this.val$appCallId, sharePhoto);
    }
}