package bf.io.openshop.views;

import android.view.*;
import timber.log.*;

private class ScaleListener extends ScaleGestureDetector$SimpleOnScaleGestureListener
{
    public boolean onScale(final ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        final float saveScale = TouchImageView.this.saveScale;
        final TouchImageView this$0 = TouchImageView.this;
        this$0.saveScale *= scaleFactor;
        if (TouchImageView.this.saveScale > TouchImageView.this.maxScale) {
            TouchImageView.this.saveScale = TouchImageView.this.maxScale;
            scaleFactor = TouchImageView.this.maxScale / saveScale;
        }
        else if (TouchImageView.this.saveScale < TouchImageView.this.minScale) {
            TouchImageView.this.saveScale = TouchImageView.this.minScale;
            scaleFactor = TouchImageView.this.minScale / saveScale;
        }
        if (TouchImageView.this.origWidth * TouchImageView.this.saveScale <= TouchImageView.this.viewWidth || TouchImageView.this.origHeight * TouchImageView.this.saveScale <= TouchImageView.this.viewHeight) {
            TouchImageView.this.matrix.postScale(scaleFactor, scaleFactor, (float)(TouchImageView.this.viewWidth / 2), (float)(TouchImageView.this.viewHeight / 2));
        }
        else {
            TouchImageView.this.matrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        }
        TouchImageView.this.fixTrans();
        Timber.d("onScale SimpleOnScaleGestureListener", new Object[0]);
        return true;
    }
    
    public boolean onScaleBegin(final ScaleGestureDetector scaleGestureDetector) {
        TouchImageView.this.mode = 2;
        Timber.d("onScaleBegin mode = ZOOM", new Object[0]);
        return true;
    }
}
