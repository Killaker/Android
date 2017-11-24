package com.facebook.share.internal;

import com.facebook.*;

class LikeActionController$AbstractRequestWrapper$1 implements Callback {
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        AbstractRequestWrapper.this.error = graphResponse.getError();
        if (AbstractRequestWrapper.this.error != null) {
            AbstractRequestWrapper.this.processError(AbstractRequestWrapper.this.error);
            return;
        }
        AbstractRequestWrapper.this.processSuccess(graphResponse);
    }
}