package bf.io.openshop.views;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.widget.*;
import android.graphics.drawable.*;
import android.view.*;
import timber.log.*;

public class TouchImageView extends ImageView
{
    static final int CLICK = 3;
    static final int DRAG = 1;
    static final int NONE = 0;
    static final int ZOOM = 2;
    Context context;
    PointF last;
    float[] m;
    ScaleGestureDetector mScaleDetector;
    Matrix matrix;
    float maxScale;
    float minScale;
    int mode;
    int oldMeasuredHeight;
    int oldMeasuredWidth;
    protected float origHeight;
    protected float origWidth;
    float saveScale;
    PointF start;
    int viewHeight;
    int viewWidth;
    
    public TouchImageView(final Context context) {
        super(context);
        this.mode = 0;
        this.last = new PointF();
        this.start = new PointF();
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.saveScale = 1.0f;
        this.sharedConstructing(context);
    }
    
    public TouchImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mode = 0;
        this.last = new PointF();
        this.start = new PointF();
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.saveScale = 1.0f;
        this.sharedConstructing(context);
    }
    
    private void sharedConstructing(final Context context) {
        super.setClickable(true);
        this.context = context;
        this.mScaleDetector = new ScaleGestureDetector(context, (ScaleGestureDetector$OnScaleGestureListener)new ScaleListener());
        this.matrix = new Matrix();
        this.m = new float[9];
        this.setImageMatrix(this.matrix);
        this.setScaleType(ImageView$ScaleType.MATRIX);
        this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
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
        });
    }
    
    void fixTrans() {
        this.matrix.getValues(this.m);
        final float n = this.m[2];
        final float n2 = this.m[5];
        final float fixTrans = this.getFixTrans(n, this.viewWidth, this.origWidth * this.saveScale);
        final float fixTrans2 = this.getFixTrans(n2, this.viewHeight, this.origHeight * this.saveScale);
        if (fixTrans != 0.0f || fixTrans2 != 0.0f) {
            this.matrix.postTranslate(fixTrans, fixTrans2);
        }
    }
    
    float getFixDragTrans(float n, final float n2, final float n3) {
        if (n3 <= n2) {
            n = 0.0f;
        }
        return n;
    }
    
    float getFixTrans(final float n, final float n2, final float n3) {
        float n4;
        float n5;
        if (n3 <= n2) {
            n4 = 0.0f;
            n5 = n2 - n3;
        }
        else {
            n4 = n2 - n3;
            n5 = 0.0f;
        }
        if (n < n4) {
            return n4 + -n;
        }
        if (n > n5) {
            return n5 + -n;
        }
        return 0.0f;
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.viewWidth = View$MeasureSpec.getSize(n);
        this.viewHeight = View$MeasureSpec.getSize(n2);
        if ((this.oldMeasuredHeight != this.viewWidth || this.oldMeasuredHeight != this.viewHeight) && this.viewWidth != 0 && this.viewHeight != 0) {
            this.oldMeasuredHeight = this.viewHeight;
            this.oldMeasuredWidth = this.viewWidth;
            if (this.saveScale == 1.0f) {
                final Drawable drawable = this.getDrawable();
                if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
                    return;
                }
                final int intrinsicWidth = drawable.getIntrinsicWidth();
                final int intrinsicHeight = drawable.getIntrinsicHeight();
                final float min = Math.min(this.viewWidth / intrinsicWidth, this.viewHeight / intrinsicHeight);
                this.matrix.setScale(min, min);
                final float n3 = this.viewHeight - min * intrinsicHeight;
                final float n4 = this.viewWidth - min * intrinsicWidth;
                final float n5 = n3 / 2.0f;
                final float n6 = n4 / 2.0f;
                this.matrix.postTranslate(n6, n5);
                this.origWidth = this.viewWidth - 2.0f * n6;
                this.origHeight = this.viewHeight - 2.0f * n5;
                this.setImageMatrix(this.matrix);
            }
            this.fixTrans();
        }
    }
    
    public void setMaxZoom(final float maxScale) {
        this.maxScale = maxScale;
    }
    
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
}
