package android.support.v4.view;

import android.os.*;

private class GestureHandler extends Handler
{
    GestureHandler() {
    }
    
    GestureHandler(final Handler handler) {
        super(handler.getLooper());
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                throw new RuntimeException("Unknown message " + message);
            }
            case 1: {
                GestureDetectorCompatImplBase.access$100(GestureDetectorCompatImplBase.this).onShowPress(GestureDetectorCompatImplBase.access$000(GestureDetectorCompatImplBase.this));
                break;
            }
            case 2: {
                GestureDetectorCompatImplBase.access$200(GestureDetectorCompatImplBase.this);
            }
            case 3: {
                if (GestureDetectorCompatImplBase.access$300(GestureDetectorCompatImplBase.this) == null) {
                    break;
                }
                if (!GestureDetectorCompatImplBase.access$400(GestureDetectorCompatImplBase.this)) {
                    GestureDetectorCompatImplBase.access$300(GestureDetectorCompatImplBase.this).onSingleTapConfirmed(GestureDetectorCompatImplBase.access$000(GestureDetectorCompatImplBase.this));
                    return;
                }
                GestureDetectorCompatImplBase.access$502(GestureDetectorCompatImplBase.this, true);
            }
        }
    }
}
