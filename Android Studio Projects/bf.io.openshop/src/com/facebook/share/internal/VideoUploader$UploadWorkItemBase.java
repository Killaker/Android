package com.facebook.share.internal;

import android.os.*;
import com.facebook.*;
import org.json.*;
import java.util.*;

private abstract static class UploadWorkItemBase implements Runnable
{
    protected int completedRetries;
    protected UploadContext uploadContext;
    
    protected UploadWorkItemBase(final UploadContext uploadContext, final int completedRetries) {
        this.uploadContext = uploadContext;
        this.completedRetries = completedRetries;
    }
    
    private boolean attemptRetry(final int n) {
        if (this.completedRetries < 2 && this.getTransientErrorCodes().contains(n)) {
            VideoUploader.access$800().postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    UploadWorkItemBase.this.enqueueRetry(1 + UploadWorkItemBase.this.completedRetries);
                }
            }, (long)(5000 * (int)Math.pow(3.0, this.completedRetries)));
            return true;
        }
        return false;
    }
    
    protected void endUploadWithFailure(final FacebookException ex) {
        this.issueResponseOnMainThread(ex, null);
    }
    
    protected abstract void enqueueRetry(final int p0);
    
    protected void executeGraphRequestSynchronously(final Bundle bundle) {
        final GraphResponse executeAndWait = new GraphRequest(this.uploadContext.accessToken, String.format(Locale.ROOT, "%s/videos", this.uploadContext.graphNode), bundle, HttpMethod.POST, null).executeAndWait();
        if (executeAndWait == null) {
            this.handleError(new FacebookException("Unexpected error in server response"));
            return;
        }
        final FacebookRequestError error = executeAndWait.getError();
        final JSONObject jsonObject = executeAndWait.getJSONObject();
        if (error != null) {
            if (!this.attemptRetry(error.getSubErrorCode())) {
                this.handleError(new FacebookGraphResponseException(executeAndWait, "Video upload failed"));
            }
            return;
        }
        if (jsonObject != null) {
            try {
                this.handleSuccess(jsonObject);
                return;
            }
            catch (JSONException ex) {
                this.endUploadWithFailure(new FacebookException("Unexpected error in server response", (Throwable)ex));
                return;
            }
        }
        this.handleError(new FacebookException("Unexpected error in server response"));
    }
    
    protected abstract Bundle getParameters() throws Exception;
    
    protected abstract Set<Integer> getTransientErrorCodes();
    
    protected abstract void handleError(final FacebookException p0);
    
    protected abstract void handleSuccess(final JSONObject p0) throws JSONException;
    
    protected void issueResponseOnMainThread(final FacebookException ex, final String s) {
        VideoUploader.access$800().post((Runnable)new Runnable() {
            @Override
            public void run() {
                VideoUploader.access$900(UploadWorkItemBase.this.uploadContext, ex, s);
            }
        });
    }
    
    @Override
    public void run() {
        if (!this.uploadContext.isCanceled) {
            try {
                this.executeGraphRequestSynchronously(this.getParameters());
                return;
            }
            catch (FacebookException ex) {
                this.endUploadWithFailure(ex);
                return;
            }
            catch (Exception ex2) {
                this.endUploadWithFailure(new FacebookException("Video upload failed", ex2));
                return;
            }
        }
        this.endUploadWithFailure(null);
    }
}
