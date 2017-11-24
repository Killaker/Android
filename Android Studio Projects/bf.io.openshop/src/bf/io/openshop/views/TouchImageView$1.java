package bf.io.openshop.views;

import android.view.*;
import android.graphics.*;

class TouchImageView$1 implements View$OnTouchListener {
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        TouchImageView.this.mScaleDetector.onTouchEvent(motionEvent);
        final PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        switch (motionEvent.getAction()) {
            case 0: {
                TouchImageView.this.last.set(pointF);
                TouchImageView.this.start.set(TouchImageView.this.last);
                TouchImageView.this.mode = 1;
                break;
            }
            case 2: {
                if (TouchImageView.this.mode == 1) {
                    TouchImageView.this.matrix.postTranslate(TouchImageView.this.getFixDragTrans(pointF.x - TouchImageView.this.last.x, TouchImageView.this.viewWidth, TouchImageView.this.origWidth * TouchImageView.this.saveScale), TouchImageView.this.getFixDragTrans(pointF.y - TouchImageView.this.last.y, TouchImageView.this.viewHeight, TouchImageView.this.origHeight * TouchImageView.this.saveScale));
                    TouchImageView.this.fixTrans();
                    TouchImageView.this.last.set(pointF.x, pointF.y);
                    break;
                }
                break;
            }
            case 1: {
                TouchImageView.this.mode = 0;
                final int n = (int)Math.abs(pointF.x - TouchImageView.this.start.x);
                final int n2 = (int)Math.abs(pointF.y - TouchImageView.this.start.y);
                if (n < 3 && n2 < 3) {
                    TouchImageView.this.performClick();
                    break;
                }
                break;
            }
            case 6: {
                TouchImageView.this.mode = 0;
                break;
            }
        }
        TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
        TouchImageView.this.invalidate();
        return true;
    }
}