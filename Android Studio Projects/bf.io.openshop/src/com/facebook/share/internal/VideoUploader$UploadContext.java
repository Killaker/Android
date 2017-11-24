package com.facebook.share.internal;

import com.facebook.share.*;
import android.net.*;
import com.facebook.share.model.*;
import com.facebook.internal.*;
import java.util.*;
import android.text.*;
import android.os.*;
import java.io.*;
import com.facebook.*;

private static class UploadContext
{
    public final AccessToken accessToken;
    public final FacebookCallback<Sharer.Result> callback;
    public String chunkStart;
    public final String description;
    public final String graphNode;
    public boolean isCanceled;
    public Bundle params;
    public final String ref;
    public String sessionId;
    public final String title;
    public String videoId;
    public long videoSize;
    public InputStream videoStream;
    public final Uri videoUri;
    public WorkQueue.WorkItem workItem;
    
    private UploadContext(final ShareVideoContent shareVideoContent, final String graphNode, final FacebookCallback<Sharer.Result> callback) {
        this.chunkStart = "0";
        this.accessToken = AccessToken.getCurrentAccessToken();
        this.videoUri = shareVideoContent.getVideo().getLocalUrl();
        this.title = shareVideoContent.getContentTitle();
        this.description = shareVideoContent.getContentDescription();
        this.ref = shareVideoContent.getRef();
        this.graphNode = graphNode;
        this.callback = callback;
        this.params = shareVideoContent.getVideo().getParameters();
        if (!Utility.isNullOrEmpty(shareVideoContent.getPeopleIds())) {
            this.params.putString("tags", TextUtils.join((CharSequence)", ", (Iterable)shareVideoContent.getPeopleIds()));
        }
        if (!Utility.isNullOrEmpty(shareVideoContent.getPlaceId())) {
            this.params.putString("place", shareVideoContent.getPlaceId());
        }
        if (!Utility.isNullOrEmpty(shareVideoContent.getRef())) {
            this.params.putString("ref", shareVideoContent.getRef());
        }
    }
    
    private void initialize() throws FileNotFoundException {
        try {
            if (Utility.isFileUri(this.videoUri)) {
                final ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(this.videoUri.getPath()), 268435456);
                this.videoSize = open.getStatSize();
                this.videoStream = (InputStream)new ParcelFileDescriptor$AutoCloseInputStream(open);
                return;
            }
            if (Utility.isContentUri(this.videoUri)) {
                this.videoSize = Utility.getContentSize(this.videoUri);
                this.videoStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(this.videoUri);
                return;
            }
        }
        catch (FileNotFoundException ex) {
            Utility.closeQuietly(this.videoStream);
            throw ex;
        }
        throw new FacebookException("Uri must be a content:// or file:// uri");
    }
}
