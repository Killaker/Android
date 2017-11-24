package com.facebook.internal;

import android.graphics.*;
import java.util.*;
import android.net.*;
import com.facebook.*;

public static final class Attachment
{
    private final String attachmentName;
    private final String attachmentUrl;
    private Bitmap bitmap;
    private final UUID callId;
    private boolean isContentUri;
    private Uri originalUri;
    private boolean shouldCreateFile;
    
    private Attachment(final UUID callId, final Bitmap bitmap, final Uri originalUri) {
        boolean b = true;
        this.callId = callId;
        this.bitmap = bitmap;
        this.originalUri = originalUri;
        if (originalUri != null) {
            final String scheme = originalUri.getScheme();
            if ("content".equalsIgnoreCase(scheme)) {
                this.isContentUri = b;
                if (originalUri.getAuthority() == null || originalUri.getAuthority().startsWith("media")) {
                    b = false;
                }
                this.shouldCreateFile = b;
            }
            else if ("file".equalsIgnoreCase(originalUri.getScheme())) {
                this.shouldCreateFile = b;
            }
            else if (!Utility.isWebUri(originalUri)) {
                throw new FacebookException("Unsupported scheme for media Uri : " + scheme);
            }
        }
        else {
            if (bitmap == null) {
                throw new FacebookException("Cannot share media without a bitmap or Uri set");
            }
            this.shouldCreateFile = b;
        }
        String string;
        if (!this.shouldCreateFile) {
            string = null;
        }
        else {
            string = UUID.randomUUID().toString();
        }
        this.attachmentName = string;
        String attachmentUrl;
        if (!this.shouldCreateFile) {
            attachmentUrl = this.originalUri.toString();
        }
        else {
            attachmentUrl = FacebookContentProvider.getAttachmentUrl(FacebookSdk.getApplicationId(), callId, this.attachmentName);
        }
        this.attachmentUrl = attachmentUrl;
    }
    
    public String getAttachmentUrl() {
        return this.attachmentUrl;
    }
}
