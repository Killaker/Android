package com.facebook.share.internal;

import java.util.*;
import android.os.*;
import com.facebook.*;
import java.io.*;
import com.facebook.internal.*;
import org.json.*;

private static class TransferChunkWorkItem extends UploadWorkItemBase
{
    static final Set<Integer> transientErrorCodes;
    private String chunkEnd;
    private String chunkStart;
    
    static {
        transientErrorCodes = new HashSet<Integer>() {
            {
                this.add(1363019);
                this.add(1363021);
                this.add(1363030);
                this.add(1363033);
                this.add(1363041);
            }
        };
    }
    
    public TransferChunkWorkItem(final UploadContext uploadContext, final String chunkStart, final String chunkEnd, final int n) {
        super(uploadContext, n);
        this.chunkStart = chunkStart;
        this.chunkEnd = chunkEnd;
    }
    
    @Override
    protected void enqueueRetry(final int n) {
        VideoUploader.access$300(this.uploadContext, this.chunkStart, this.chunkEnd, n);
    }
    
    public Bundle getParameters() throws IOException {
        final Bundle bundle = new Bundle();
        bundle.putString("upload_phase", "transfer");
        bundle.putString("upload_session_id", this.uploadContext.sessionId);
        bundle.putString("start_offset", this.chunkStart);
        final byte[] access$600 = VideoUploader.access$600(this.uploadContext, this.chunkStart, this.chunkEnd);
        if (access$600 != null) {
            bundle.putByteArray("video_file_chunk", access$600);
            return bundle;
        }
        throw new FacebookException("Error reading video");
    }
    
    @Override
    protected Set<Integer> getTransientErrorCodes() {
        return TransferChunkWorkItem.transientErrorCodes;
    }
    
    @Override
    protected void handleError(final FacebookException ex) {
        VideoUploader.access$400(ex, "Error uploading video '%s'", new Object[] { this.uploadContext.videoId });
        ((UploadWorkItemBase)this).endUploadWithFailure(ex);
    }
    
    @Override
    protected void handleSuccess(final JSONObject jsonObject) throws JSONException {
        final String string = jsonObject.getString("start_offset");
        final String string2 = jsonObject.getString("end_offset");
        if (Utility.areObjectsEqual(string, string2)) {
            VideoUploader.access$700(this.uploadContext, 0);
            return;
        }
        VideoUploader.access$300(this.uploadContext, string, string2, 0);
    }
}
