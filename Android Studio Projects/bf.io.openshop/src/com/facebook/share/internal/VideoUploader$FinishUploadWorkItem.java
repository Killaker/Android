package com.facebook.share.internal;

import java.util.*;
import android.os.*;
import com.facebook.internal.*;
import com.facebook.*;
import org.json.*;

private static class FinishUploadWorkItem extends UploadWorkItemBase
{
    static final Set<Integer> transientErrorCodes;
    
    static {
        transientErrorCodes = new HashSet<Integer>() {
            {
                this.add(1363011);
            }
        };
    }
    
    public FinishUploadWorkItem(final UploadContext uploadContext, final int n) {
        super(uploadContext, n);
    }
    
    @Override
    protected void enqueueRetry(final int n) {
        VideoUploader.access$700(this.uploadContext, n);
    }
    
    public Bundle getParameters() {
        final Bundle bundle = new Bundle();
        if (this.uploadContext.params != null) {
            bundle.putAll(this.uploadContext.params);
        }
        bundle.putString("upload_phase", "finish");
        bundle.putString("upload_session_id", this.uploadContext.sessionId);
        Utility.putNonEmptyString(bundle, "title", this.uploadContext.title);
        Utility.putNonEmptyString(bundle, "description", this.uploadContext.description);
        Utility.putNonEmptyString(bundle, "ref", this.uploadContext.ref);
        return bundle;
    }
    
    @Override
    protected Set<Integer> getTransientErrorCodes() {
        return FinishUploadWorkItem.transientErrorCodes;
    }
    
    @Override
    protected void handleError(final FacebookException ex) {
        VideoUploader.access$400(ex, "Video '%s' failed to finish uploading", new Object[] { this.uploadContext.videoId });
        ((UploadWorkItemBase)this).endUploadWithFailure(ex);
    }
    
    @Override
    protected void handleSuccess(final JSONObject jsonObject) throws JSONException {
        if (jsonObject.getBoolean("success")) {
            ((UploadWorkItemBase)this).issueResponseOnMainThread(null, this.uploadContext.videoId);
            return;
        }
        this.handleError(new FacebookException("Unexpected error in server response"));
    }
}
