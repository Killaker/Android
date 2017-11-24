package com.facebook.share.internal;

import java.util.*;
import android.os.*;
import com.facebook.*;
import org.json.*;

private static class StartUploadWorkItem extends UploadWorkItemBase
{
    static final Set<Integer> transientErrorCodes;
    
    static {
        transientErrorCodes = new HashSet<Integer>() {
            {
                this.add(6000);
            }
        };
    }
    
    public StartUploadWorkItem(final UploadContext uploadContext, final int n) {
        super(uploadContext, n);
    }
    
    @Override
    protected void enqueueRetry(final int n) {
        VideoUploader.access$500(this.uploadContext, n);
    }
    
    public Bundle getParameters() {
        final Bundle bundle = new Bundle();
        bundle.putString("upload_phase", "start");
        bundle.putLong("file_size", this.uploadContext.videoSize);
        return bundle;
    }
    
    @Override
    protected Set<Integer> getTransientErrorCodes() {
        return StartUploadWorkItem.transientErrorCodes;
    }
    
    @Override
    protected void handleError(final FacebookException ex) {
        VideoUploader.access$400(ex, "Error starting video upload", new Object[0]);
        ((UploadWorkItemBase)this).endUploadWithFailure(ex);
    }
    
    @Override
    protected void handleSuccess(final JSONObject jsonObject) throws JSONException {
        this.uploadContext.sessionId = jsonObject.getString("upload_session_id");
        this.uploadContext.videoId = jsonObject.getString("video_id");
        VideoUploader.access$300(this.uploadContext, jsonObject.getString("start_offset"), jsonObject.getString("end_offset"), 0);
    }
}
