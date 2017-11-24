package mbanje.kurt.fabbutton;

import android.content.*;
import android.widget.*;
import android.animation.*;
import android.util.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.graphics.*;

public class CircleImageView extends ImageView
{
    private static final int animationDuration = 200;
    private int centerX;
    private int centerY;
    private Paint circlePaint;
    private int circleRadius;
    private TransitionDrawable crossfader;
    private float currentRingWidth;
    private Drawable[] drawables;
    private OnFabViewListener fabViewListener;
    private boolean progressVisible;
    float radiusX;
    float radiusY;
    private final int ringAlpha;
    private ObjectAnimator ringAnimator;
    private Paint ringPaint;
    private int ringRadius;
    private int ringWidth;
    private float ringWidthRatio;
    float shadowRadius;
    int shadowTransparency;
    private boolean showEndBitmap;
    private boolean showShadow;
    private int viewRadius;
    
    public CircleImageView(final Context context) {
        super(context);
        this.ringAlpha = 75;
        this.ringWidthRatio = 0.14f;
        this.drawables = new Drawable[2];
        this.radiusY = 3.5f;
        this.radiusX = 0.0f;
        this.shadowRadius = 10.0f;
        this.shadowTransparency = 100;
        this.showShadow = true;
        this.init(context, null);
    }
    
    public CircleImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.ringAlpha = 75;
        this.ringWidthRatio = 0.14f;
        this.drawables = new Drawable[2];
        this.radiusY = 3.5f;
        this.radiusX = 0.0f;
        this.shadowRadius = 10.0f;
        this.shadowTransparency = 100;
        this.showShadow = true;
        this.init(context, set);
    }
    
    public CircleImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ringAlpha = 75;
        this.ringWidthRatio = 0.14f;
        this.drawables = new Drawable[2];
        this.radiusY = 3.5f;
        this.radiusX = 0.0f;
        this.shadowRadius = 10.0f;
        this.shadowTransparency = 100;
        this.showShadow = true;
        this.init(context, set);
    }
    
    private void init(final Context context, final AttributeSet set) {
        this.setFocusable(false);
        this.setScaleType(ImageView$ScaleType.CENTER_INSIDE);
        this.setClickable(true);
        (this.circlePaint = new Paint(1)).setStyle(Paint$Style.FILL);
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        if (displayMetrics.densityDpi <= 240) {
            this.shadowRadius = 1.0f;
        }
        else if (displayMetrics.densityDpi <= 320) {
            this.shadowRadius = 3.0f;
        }
        else {
            this.shadowRadius = 10.0f;
        }
        (this.ringPaint = new Paint(1)).setStyle(Paint$Style.STROKE);
        this.setWillNotDraw(false);
        this.setLayerType(1, (Paint)null);
        int color = -16777216;
        if (set != null) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CircleImageView);
            color = obtainStyledAttributes.getColor(R.styleable.CircleImageView_android_color, -16777216);
            this.ringWidthRatio = obtainStyledAttributes.getFloat(R.styleable.CircleImageView_fbb_progressWidthRatio, this.ringWidthRatio);
            this.setShowShadow(obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_fbb_showShadow, true));
            obtainStyledAttributes.recycle();
        }
        this.setColor(color);
        (this.ringAnimator = ObjectAnimator.ofFloat((Object)this, "currentRingWidth", new float[] { 0.0f, 0.0f })).setDuration(200L);
        this.ringAnimator.addListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(final Animator animator) {
                if (CircleImageView.this.fabViewListener != null) {
                    CircleImageView.this.fabViewListener.onProgressVisibilityChanged(CircleImageView.this.progressVisible);
                }
            }
        });
    }
    
    private void setIconAnimation(final Drawable drawable, final Drawable drawable2) {
        this.drawables[0] = drawable;
        this.drawables[1] = drawable2;
        (this.crossfader = new TransitionDrawable(this.drawables)).setCrossFadeEnabled(true);
        this.setImageDrawable((Drawable)this.crossfader);
    }
    
    public float getCurrentRingWidth() {
        return this.currentRingWidth;
    }
    
    protected void onDraw(final Canvas canvas) {
        canvas.drawCircle((float)this.centerX, (float)this.centerY, this.ringRadius + this.currentRingWidth, this.ringPaint);
        canvas.drawCircle((float)this.centerX, (float)this.centerY, (float)this.circleRadius, this.circlePaint);
        super.onDraw(canvas);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.centerX = n / 2;
        this.centerY = n2 / 2;
        this.viewRadius = Math.min(n, n2) / 2;
        this.ringWidth = Math.round(this.viewRadius * this.ringWidthRatio);
        this.circleRadius = this.viewRadius - this.ringWidth;
        this.ringPaint.setStrokeWidth((float)this.ringWidth);
        this.ringPaint.setAlpha(75);
        this.ringRadius = this.circleRadius - this.ringWidth / 2;
    }
    
    public void resetIcon() {
        this.crossfader.resetTransition();
    }
    
    public void setColor(final int n) {
        this.circlePaint.setColor(n);
        this.ringPaint.setColor(n);
        this.ringPaint.setAlpha(75);
        this.invalidate();
    }
    
    public void setCurrentRingWidth(final float currentRingWidth) {
        this.currentRingWidth = currentRingWidth;
        this.invalidate();
    }
    
    public void setFabViewListener(final OnFabViewListener fabViewListener) {
        this.fabViewListener = fabViewListener;
    }
    
    public void setIcon(final int n, final int n2) {
        final Bitmap decodeResource = BitmapFactory.decodeResource(this.getResources(), n);
        if (this.showEndBitmap) {
            this.setIconAnimation((Drawable)new BitmapDrawable(this.getResources(), decodeResource), (Drawable)new BitmapDrawable(this.getResources(), BitmapFactory.decodeResource(this.getResources(), n2)));
            return;
        }
        this.setImageBitmap(decodeResource);
    }
    
    public void setIcon(final Drawable imageDrawable, final Drawable drawable) {
        if (this.showEndBitmap) {
            this.setIconAnimation(imageDrawable, drawable);
            return;
        }
        this.setImageDrawable(imageDrawable);
    }
    
    public void setRingWidthRatio(final float ringWidthRatio) {
        this.ringWidthRatio = ringWidthRatio;
    }
    
    public void setShowEndBitmap(final boolean showEndBitmap) {
        this.showEndBitmap = showEndBitmap;
    }
    
    public void setShowShadow(final boolean b) {
        if (b) {
            this.circlePaint.setShadowLayer(this.shadowRadius, this.radiusX, this.radiusY, Color.argb(this.shadowTransparency, 0, 0, 0));
            return;
        }
        this.circlePaint.clearShadowLayer();
    }
    
    public void showCompleted(final boolean b, final boolean b2) {
        if (b) {
            this.crossfader.startTransition(500);
        }
        if (b2) {
            final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "currentRingWidth", new float[] { 0.0f, 0.0f });
            ofFloat.setFloatValues(new float[] { 1.0f });
            ofFloat.setDuration(200L);
            ofFloat.start();
        }
    }
    
    public void showRing(final boolean progressVisible) {
        this.progressVisible = progressVisible;
        if (progressVisible) {
            this.ringAnimator.setFloatValues(new float[] { this.currentRingWidth, this.ringWidth });
        }
        else {
            this.ringAnimator.setFloatValues(new float[] { this.ringWidth, 0.0f });
        }
        this.ringAnimator.start();
    }
    
    public interface OnFabViewListener
    {
        void onProgressCompleted();
        
        void onProgressVisibilityChanged(final boolean p0);
    }
}
