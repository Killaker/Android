package com.facebook;

static final class GraphRequest$1 implements Callback {
    final /* synthetic */ GraphJSONObjectCallback val$callback;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        if (this.val$callback != null) {
            this.val$callback.onCompleted(graphResponse.getJSONObject(), graphResponse);
        }
    }
}