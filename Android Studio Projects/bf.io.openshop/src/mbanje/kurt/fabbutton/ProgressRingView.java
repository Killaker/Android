package mbanje.kurt.fabbutton;

import android.view.*;
import android.content.*;
import android.util.*;
import android.content.res.*;
import android.graphics.*;
import android.animation.*;

public class ProgressRingView extends View implements OnFabValueCallback
{
    String TAG;
    private float actualProgress;
    private int animDuration;
    private boolean autostartanim;
    private RectF bounds;
    private float boundsPadding;
    private CircleImageView.OnFabViewListener fabViewListener;
    private boolean indeterminate;
    private AnimatorSet indeterminateAnimator;
    private float indeterminateRotateOffset;
    private float indeterminateSweep;
    private float maxProgress;
    private int midRingWidth;
    private float progress;
    private ValueAnimator progressAnimator;
    private int progressColor;
    private Paint progressPaint;
    private int ringWidth;
    private float ringWidthRatio;
    private int size;
    private float startAngle;
    private ValueAnimator startAngleRotate;
    private int viewRadius;
    
    public ProgressRingView(final Context context) {
        super(context);
        this.TAG = ProgressRingView.class.getSimpleName();
        this.size = 0;
        this.boundsPadding = 0.14f;
        this.ringWidthRatio = 0.14f;
        this.progressColor = -16777216;
        this.init(null, 0);
    }
    
    public ProgressRingView(final Context context, final AttributeSet set) {
        super(context, set);
        this.TAG = ProgressRingView.class.getSimpleName();
        this.size = 0;
        this.boundsPadding = 0.14f;
        this.ringWidthRatio = 0.14f;
        this.progressColor = -16777216;
        this.init(set, 0);
    }
    
    public ProgressRingView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.TAG = ProgressRingView.class.getSimpleName();
        this.size = 0;
        this.boundsPadding = 0.14f;
        this.ringWidthRatio = 0.14f;
        this.progressColor = -16777216;
        this.init(set, n);
    }
    
    private void updateBounds() {
        this.bounds = new RectF((float)this.midRingWidth, (float)this.midRingWidth, (float)(this.size - this.midRingWidth), (float)(this.size - this.midRingWidth));
    }
    
    protected void init(final AttributeSet set, final int n) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R.styleable.CircleImageView, n, 0);
        this.progress = obtainStyledAttributes.getFloat(R.styleable.CircleImageView_android_progress, 0.0f);
        this.progressColor = obtainStyledAttributes.getColor(R.styleable.CircleImageView_fbb_progressColor, this.progressColor);
        this.maxProgress = obtainStyledAttributes.getFloat(R.styleable.CircleImageView_android_max, 100.0f);
        this.indeterminate = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_android_indeterminate, false);
        this.autostartanim = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_fbb_autoStart, true);
        this.animDuration = obtainStyledAttributes.getInteger(R.styleable.CircleImageView_android_indeterminateDuration, 4000);
        this.ringWidthRatio = obtainStyledAttributes.getFloat(R.styleable.CircleImageView_fbb_progressWidthRatio, this.ringWidthRatio);
        obtainStyledAttributes.recycle();
        (this.progressPaint = new Paint(1)).setColor(this.progressColor);
        this.progressPaint.setStyle(Paint$Style.STROKE);
        this.progressPaint.setStrokeCap(Paint$Cap.BUTT);
        if (this.autostartanim) {
            this.startAnimation();
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        float n;
        if (this.isInEditMode()) {
            n = 360.0f * (this.progress / this.maxProgress);
        }
        else {
            n = 360.0f * (this.actualProgress / this.maxProgress);
        }
        if (!this.indeterminate) {
            canvas.drawArc(this.bounds, this.startAngle, n, false, this.progressPaint);
            return;
        }
        canvas.drawArc(this.bounds, this.startAngle + this.indeterminateRotateOffset, this.indeterminateSweep, false, this.progressPaint);
    }
    
    public void onIndeterminateValuesChanged(final float indeterminateSweep, final float indeterminateRotateOffset, final float startAngle, final float actualProgress) {
        if (indeterminateSweep != -1.0f) {
            this.indeterminateSweep = indeterminateSweep;
        }
        if (indeterminateRotateOffset != -1.0f) {
            this.indeterminateRotateOffset = indeterminateRotateOffset;
        }
        if (startAngle != -1.0f) {
            this.startAngle = startAngle;
        }
        if (actualProgress != -1.0f) {
            this.actualProgress = actualProgress;
            if (Math.round(this.actualProgress) == 100 && this.fabViewListener != null) {
                this.fabViewListener.onProgressCompleted();
            }
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.size = Math.min(n, n2);
        this.viewRadius = this.size / 2;
        this.setRingWidth(-1, true);
    }
    
    public void resetAnimation() {
        this.stopAnimation(false);
        if (!this.indeterminate) {
            this.startAngle = -90.0f;
            (this.startAngleRotate = FabUtil.createStartAngleAnimator(this, -90.0f, 270.0f, (FabUtil.OnFabValueCallback)this)).start();
            this.actualProgress = 0.0f;
            (this.progressAnimator = FabUtil.createProgressAnimator(this, this.actualProgress, this.progress, (FabUtil.OnFabValueCallback)this)).start();
            return;
        }
        this.startAngle = -90.0f;
        this.indeterminateSweep = 15.0f;
        this.indeterminateAnimator = new AnimatorSet();
        Object o = null;
        for (int i = 0; i < 4; ++i) {
            final AnimatorSet indeterminateAnimator = FabUtil.createIndeterminateAnimator(this, i, this.animDuration, (FabUtil.OnFabValueCallback)this);
            final AnimatorSet$Builder play = this.indeterminateAnimator.play((Animator)indeterminateAnimator);
            if (o != null) {
                play.after((Animator)o);
            }
            o = indeterminateAnimator;
        }
        this.indeterminateAnimator.addListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
            boolean wasCancelled = false;
            
            public void onAnimationCancel(final Animator animator) {
                this.wasCancelled = true;
            }
            
            public void onAnimationEnd(final Animator animator) {
                if (!this.wasCancelled) {
                    ProgressRingView.this.resetAnimation();
                }
            }
        });
        this.indeterminateAnimator.start();
    }
    
    public void setAnimDuration(final int animDuration) {
        this.animDuration = animDuration;
    }
    
    public void setAutostartanim(final boolean autostartanim) {
        this.autostartanim = autostartanim;
    }
    
    public void setFabViewListener(final CircleImageView.OnFabViewListener fabViewListener) {
        this.fabViewListener = fabViewListener;
    }
    
    public void setIndeterminate(final boolean indeterminate) {
        this.indeterminate = indeterminate;
    }
    
    public void setMaxProgress(final float maxProgress) {
        this.maxProgress = maxProgress;
    }
    
    public void setProgress(final float progress) {
        this.progress = progress;
        if (!this.indeterminate) {
            if (this.progressAnimator != null && this.progressAnimator.isRunning()) {
                this.progressAnimator.cancel();
            }
            (this.progressAnimator = FabUtil.createProgressAnimator(this, this.actualProgress, progress, (FabUtil.OnFabValueCallback)this)).start();
        }
        this.invalidate();
    }
    
    public void setProgressColor(final int n) {
        this.progressColor = n;
        this.progressPaint.setColor(n);
    }
    
    public void setRingWidth(final int ringWidth, final boolean b) {
        if (b) {
            this.ringWidth = Math.round(this.viewRadius * this.ringWidthRatio);
        }
        else {
            this.ringWidth = ringWidth;
        }
        this.midRingWidth = this.ringWidth / 2;
        this.progressPaint.setStrokeWidth((float)this.ringWidth);
        this.updateBounds();
    }
    
    public void setRingWidthRatio(final float ringWidthRatio) {
        this.ringWidthRatio = ringWidthRatio;
    }
    
    public void startAnimation() {
        this.resetAnimation();
    }
    
    public void stopAnimation(final boolean b) {
        if (this.startAngleRotate != null && this.startAngleRotate.isRunning()) {
            this.startAngleRotate.cancel();
        }
        if (this.progressAnimator != null && this.progressAnimator.isRunning()) {
            this.progressAnimator.cancel();
        }
        if (this.indeterminateAnimator != null && this.indeterminateAnimator.isRunning()) {
            this.indeterminateAnimator.cancel();
        }
        if (b) {
            this.setRingWidth(0, false);
        }
        else {
            this.setRingWidth(0, true);
        }
        this.invalidate();
    }
}
