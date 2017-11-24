package com.facebook.share.internal;

import com.facebook.share.widget.*;
import com.facebook.internal.*;
import com.facebook.*;

private abstract class AbstractRequestWrapper implements RequestWrapper
{
    protected FacebookRequestError error;
    protected String objectId;
    protected LikeView.ObjectType objectType;
    private GraphRequest request;
    
    protected AbstractRequestWrapper(final String objectId, final LikeView.ObjectType objectType) {
        this.objectId = objectId;
        this.objectType = objectType;
    }
    
    @Override
    public void addToBatch(final GraphRequestBatch graphRequestBatch) {
        graphRequestBatch.add(this.request);
    }
    
    @Override
    public FacebookRequestError getError() {
        return this.error;
    }
    
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Error running request for object '%s' with type '%s' : %s", this.objectId, this.objectType, facebookRequestError);
    }
    
    protected abstract void processSuccess(final GraphResponse p0);
    
    protected void setRequest(final GraphRequest request) {
        (this.request = request).setVersion("v2.5");
        request.setCallback((GraphRequest.Callback)new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                AbstractRequestWrapper.this.error = graphResponse.getError();
                if (AbstractRequestWrapper.this.error != null) {
                    AbstractRequestWrapper.this.processError(AbstractRequestWrapper.this.error);
                    return;
                }
                AbstractRequestWrapper.this.processSuccess(graphResponse);
            }
        });
    }
}
