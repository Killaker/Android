package com.android.volley;

private class ResponseDeliveryRunnable implements Runnable
{
    private final Request mRequest;
    private final Response mResponse;
    private final Runnable mRunnable;
    
    public ResponseDeliveryRunnable(final Request mRequest, final Response mResponse, final Runnable mRunnable) {
        this.mRequest = mRequest;
        this.mResponse = mResponse;
        this.mRunnable = mRunnable;
    }
    
    @Override
    public void run() {
        if (this.mRequest.isCanceled()) {
            this.mRequest.finish("canceled-at-delivery");
        }
        else {
            if (this.mResponse.isSuccess()) {
                this.mRequest.deliverResponse(this.mResponse.result);
            }
            else {
                this.mRequest.deliverError(this.mResponse.error);
            }
            if (this.mResponse.intermediate) {
                this.mRequest.addMarker("intermediate-response");
            }
            else {
                this.mRequest.finish("done");
            }
            if (this.mRunnable != null) {
                this.mRunnable.run();
            }
        }
    }
}
