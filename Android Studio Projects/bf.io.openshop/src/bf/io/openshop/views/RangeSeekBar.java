package bf.io.openshop.views;

import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.util.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.*;
import android.graphics.drawable.*;
import android.graphics.*;
import android.view.*;
import android.os.*;
import android.support.annotation.*;
import java.math.*;

public class RangeSeekBar<T extends Number> extends ImageView
{
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int ACTIVE_COLOR = 0;
    public static final Integer DEFAULT_MAXIMUM;
    public static final Integer DEFAULT_MINIMUM;
    private static final int DEFAULT_TEXT_DISTANCE_TO_BUTTON_IN_DP = 8;
    private static final int DEFAULT_TEXT_DISTANCE_TO_TOP_IN_DP = 8;
    private static final int DEFAULT_TEXT_SIZE_IN_DP = 14;
    public static final int HEIGHT_IN_DP = 30;
    private static final int INITIAL_PADDING_IN_DP = 8;
    public static final int INVALID_POINTER_ID = 255;
    private static final int LINE_HEIGHT_IN_DP = 1;
    public static final int TEXT_LATERAL_PADDING_IN_DP = 3;
    private T absoluteMaxValue;
    private double absoluteMaxValuePrim;
    private T absoluteMinValue;
    private double absoluteMinValuePrim;
    private OnRangeSeekBarChangeListener<T> listener;
    private boolean mActivateOnDefaultValues;
    private int mActiveColor;
    private int mActivePointerId;
    private boolean mAlwaysActive;
    private int mDefaultColor;
    private int mDistanceToTop;
    private float mDownMotionX;
    private float mInternalPad;
    private boolean mIsDragging;
    private RectF mRect;
    private int mScaledTouchSlop;
    private boolean mShowLabels;
    private boolean mShowTextAboveThumbs;
    private boolean mSingleThumb;
    private int mTextAboveThumbsColor;
    private int mTextOffset;
    private int mTextSize;
    private float mThumbHalfHeight;
    private float mThumbHalfWidth;
    private boolean mThumbShadow;
    private int mThumbShadowBlur;
    private Matrix mThumbShadowMatrix;
    private Path mThumbShadowPath;
    private int mThumbShadowXOffset;
    private int mThumbShadowYOffset;
    private Path mTranslatedThumbShadowPath;
    private double normalizedMaxValue;
    private double normalizedMinValue;
    private boolean notifyWhileDragging;
    private NumberType numberType;
    private float padding;
    private final Paint paint;
    private Thumb pressedThumb;
    private final Paint shadowPaint;
    private Bitmap thumbDisabledImage;
    private Bitmap thumbImage;
    private Bitmap thumbPressedImage;
    
    static {
        DEFAULT_MINIMUM = 0;
        DEFAULT_MAXIMUM = 100;
    }
    
    public RangeSeekBar(final Context context) {
        super(context);
        this.paint = new Paint(1);
        this.shadowPaint = new Paint();
        this.normalizedMinValue = 0.0;
        this.normalizedMaxValue = 1.0;
        this.pressedThumb = null;
        this.notifyWhileDragging = false;
        this.mActivePointerId = 255;
        this.mTranslatedThumbShadowPath = new Path();
        this.mThumbShadowMatrix = new Matrix();
        this.init(context, null);
    }
    
    public RangeSeekBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.paint = new Paint(1);
        this.shadowPaint = new Paint();
        this.normalizedMinValue = 0.0;
        this.normalizedMaxValue = 1.0;
        this.pressedThumb = null;
        this.notifyWhileDragging = false;
        this.mActivePointerId = 255;
        this.mTranslatedThumbShadowPath = new Path();
        this.mThumbShadowMatrix = new Matrix();
        this.init(context, set);
    }
    
    public RangeSeekBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.paint = new Paint(1);
        this.shadowPaint = new Paint();
        this.normalizedMinValue = 0.0;
        this.normalizedMaxValue = 1.0;
        this.pressedThumb = null;
        this.notifyWhileDragging = false;
        this.mActivePointerId = 255;
        this.mTranslatedThumbShadowPath = new Path();
        this.mThumbShadowMatrix = new Matrix();
        this.init(context, set);
    }
    
    private void attemptClaimDrag() {
        if (this.getParent() != null) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
    
    private void drawThumb(final float n, final boolean b, final Canvas canvas, final boolean b2) {
        Bitmap bitmap;
        if (!this.mActivateOnDefaultValues && b2) {
            bitmap = this.thumbDisabledImage;
        }
        else if (b) {
            bitmap = this.thumbPressedImage;
        }
        else {
            bitmap = this.thumbImage;
        }
        canvas.drawBitmap(bitmap, n - this.mThumbHalfWidth, (float)this.mTextOffset, this.paint);
    }
    
    private void drawThumbShadow(final float n, final Canvas canvas) {
        this.mThumbShadowMatrix.setTranslate(n + this.mThumbShadowXOffset, this.mTextOffset + this.mThumbHalfHeight + this.mThumbShadowYOffset);
        this.mTranslatedThumbShadowPath.set(this.mThumbShadowPath);
        this.mTranslatedThumbShadowPath.transform(this.mThumbShadowMatrix);
        canvas.drawPath(this.mTranslatedThumbShadowPath, this.shadowPaint);
    }
    
    private Thumb evalPressedThumb(final float n) {
        final boolean inThumbRange = this.isInThumbRange(n, this.normalizedMinValue);
        final boolean inThumbRange2 = this.isInThumbRange(n, this.normalizedMaxValue);
        Thumb min;
        if (inThumbRange && inThumbRange2) {
            if (n / this.getWidth() <= 0.5f) {
                return Thumb.MAX;
            }
            min = Thumb.MIN;
        }
        else {
            if (inThumbRange) {
                return Thumb.MIN;
            }
            min = null;
            if (inThumbRange2) {
                return Thumb.MAX;
            }
        }
        return min;
    }
    
    private T extractNumericValueFromAttributes(final TypedArray typedArray, final int n, final int n2) {
        final TypedValue peekValue = typedArray.peekValue(n);
        if (peekValue == null) {
            return (T)n2;
        }
        if (peekValue.type == 4) {
            return (T)typedArray.getFloat(n, (float)n2);
        }
        return (T)typedArray.getInteger(n, n2);
    }
    
    private void init(final Context context, final AttributeSet set) {
        final int argb = Color.argb(75, 0, 0, 0);
        final int dpToPx = Utils.dpToPx(context, 2);
        final int dpToPx2 = Utils.dpToPx(context, 0);
        final int dpToPx3 = Utils.dpToPx(context, 2);
        while (true) {
            Label_0711: {
                float n = 0.0f;
                int color = 0;
                Label_0112: {
                    if (set != null) {
                        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R.styleable.RangeSeekBar, 0, 0);
                        try {
                            this.setRangeValues(this.extractNumericValueFromAttributes(obtainStyledAttributes, 0, RangeSeekBar.DEFAULT_MINIMUM), this.extractNumericValueFromAttributes(obtainStyledAttributes, 1, RangeSeekBar.DEFAULT_MAXIMUM));
                            this.mShowTextAboveThumbs = obtainStyledAttributes.getBoolean(5, true);
                            this.mTextAboveThumbsColor = obtainStyledAttributes.getColor(10, -1);
                            this.mSingleThumb = obtainStyledAttributes.getBoolean(2, false);
                            this.mShowLabels = obtainStyledAttributes.getBoolean(3, true);
                            this.mInternalPad = obtainStyledAttributes.getDimensionPixelSize(6, 8);
                            n = obtainStyledAttributes.getDimensionPixelSize(7, 1);
                            this.mActiveColor = obtainStyledAttributes.getColor(9, RangeSeekBar.ACTIVE_COLOR);
                            this.mDefaultColor = obtainStyledAttributes.getColor(8, -7829368);
                            this.mAlwaysActive = obtainStyledAttributes.getBoolean(4, false);
                            final Drawable drawable = obtainStyledAttributes.getDrawable(11);
                            if (drawable != null) {
                                this.thumbImage = Utils.drawableToBitmap(drawable);
                            }
                            final Drawable drawable2 = obtainStyledAttributes.getDrawable(13);
                            if (drawable2 != null) {
                                this.thumbDisabledImage = Utils.drawableToBitmap(drawable2);
                            }
                            final Drawable drawable3 = obtainStyledAttributes.getDrawable(12);
                            if (drawable3 != null) {
                                this.thumbPressedImage = Utils.drawableToBitmap(drawable3);
                            }
                            this.mThumbShadow = obtainStyledAttributes.getBoolean(14, false);
                            color = obtainStyledAttributes.getColor(15, argb);
                            this.mThumbShadowXOffset = obtainStyledAttributes.getDimensionPixelSize(16, dpToPx2);
                            this.mThumbShadowYOffset = obtainStyledAttributes.getDimensionPixelSize(17, dpToPx);
                            this.mThumbShadowBlur = obtainStyledAttributes.getDimensionPixelSize(18, dpToPx3);
                            this.mActivateOnDefaultValues = obtainStyledAttributes.getBoolean(19, false);
                            break Label_0112;
                        }
                        finally {
                            obtainStyledAttributes.recycle();
                        }
                        break Label_0711;
                    }
                    this.setRangeToDefaultValues();
                    this.mInternalPad = Utils.dpToPx(context, 8);
                    n = Utils.dpToPx(context, 1);
                    this.mActiveColor = RangeSeekBar.ACTIVE_COLOR;
                    this.mDefaultColor = -7829368;
                    this.mAlwaysActive = false;
                    this.mShowTextAboveThumbs = true;
                    this.mTextAboveThumbsColor = -1;
                    color = argb;
                    this.mThumbShadowXOffset = dpToPx2;
                    this.mThumbShadowYOffset = dpToPx;
                    this.mThumbShadowBlur = dpToPx3;
                    this.mActivateOnDefaultValues = false;
                }
                if (this.thumbImage == null) {
                    this.thumbImage = BitmapFactory.decodeResource(this.getResources(), 2130837581);
                }
                if (this.thumbPressedImage == null) {
                    this.thumbPressedImage = BitmapFactory.decodeResource(this.getResources(), 2130837582);
                }
                if (this.thumbDisabledImage == null) {
                    this.thumbDisabledImage = BitmapFactory.decodeResource(this.getResources(), 2130837580);
                }
                this.mThumbHalfWidth = 0.5f * this.thumbImage.getWidth();
                this.mThumbHalfHeight = 0.5f * this.thumbImage.getHeight();
                this.setValuePrimAndNumberType();
                this.mTextSize = Utils.dpToPx(context, 14);
                this.mDistanceToTop = Utils.dpToPx(context, 8);
                if (this.mShowTextAboveThumbs) {
                    break Label_0711;
                }
                final int mTextOffset = 0;
                this.mTextOffset = mTextOffset;
                this.mRect = new RectF(this.padding, this.mTextOffset + this.mThumbHalfHeight - n / 2.0f, this.getWidth() - this.padding, this.mTextOffset + this.mThumbHalfHeight + n / 2.0f);
                this.setFocusable(true);
                this.setFocusableInTouchMode(true);
                this.mScaledTouchSlop = ViewConfiguration.get(this.getContext()).getScaledTouchSlop();
                if (this.mThumbShadow) {
                    this.setLayerType(1, (Paint)null);
                    this.shadowPaint.setColor(color);
                    this.shadowPaint.setMaskFilter((MaskFilter)new BlurMaskFilter((float)this.mThumbShadowBlur, BlurMaskFilter$Blur.NORMAL));
                    (this.mThumbShadowPath = new Path()).addCircle(0.0f, 0.0f, this.mThumbHalfHeight, Path$Direction.CW);
                }
                return;
            }
            final int mTextOffset = this.mTextSize + Utils.dpToPx(context, 8) + this.mDistanceToTop;
            continue;
        }
    }
    
    private boolean isInThumbRange(final float n, final double n2) {
        return Math.abs(n - this.normalizedToScreen(n2)) <= this.mThumbHalfWidth;
    }
    
    private float normalizedToScreen(final double n) {
        return (float)(this.padding + n * (this.getWidth() - 2.0f * this.padding));
    }
    
    private T normalizedToValue(final double n) {
        return (T)this.numberType.toNumber(Math.round((this.absoluteMinValuePrim + n * (this.absoluteMaxValuePrim - this.absoluteMinValuePrim)) * 100.0) / 100.0);
    }
    
    private void onSecondaryPointerUp(final MotionEvent motionEvent) {
        final int n = (0xFF00 & motionEvent.getAction()) >> 8;
        if (motionEvent.getPointerId(n) == this.mActivePointerId) {
            int n2;
            if (n == 0) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            this.mDownMotionX = motionEvent.getX(n2);
            this.mActivePointerId = motionEvent.getPointerId(n2);
        }
    }
    
    private double screenToNormalized(final float n) {
        final int width = this.getWidth();
        if (width <= 2.0f * this.padding) {
            return 0.0;
        }
        return Math.min(1.0, Math.max(0.0, (n - this.padding) / (width - 2.0f * this.padding)));
    }
    
    private void setNormalizedMaxValue(final double n) {
        this.normalizedMaxValue = Math.max(0.0, Math.min(1.0, Math.max(n, this.normalizedMinValue)));
        this.invalidate();
    }
    
    private void setNormalizedMinValue(final double n) {
        this.normalizedMinValue = Math.max(0.0, Math.min(1.0, Math.min(n, this.normalizedMaxValue)));
        this.invalidate();
    }
    
    private void setRangeToDefaultValues() {
        this.absoluteMinValue = (T)RangeSeekBar.DEFAULT_MINIMUM;
        this.absoluteMaxValue = (T)RangeSeekBar.DEFAULT_MAXIMUM;
        this.setValuePrimAndNumberType();
    }
    
    private void setValuePrimAndNumberType() {
        this.absoluteMinValuePrim = this.absoluteMinValue.doubleValue();
        this.absoluteMaxValuePrim = this.absoluteMaxValue.doubleValue();
        this.numberType = NumberType.fromNumber(this.absoluteMinValue);
    }
    
    private void trackTouchEvent(final MotionEvent motionEvent) {
        final float x = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
        if (Thumb.MIN.equals(this.pressedThumb) && !this.mSingleThumb) {
            this.setNormalizedMinValue(this.screenToNormalized(x));
        }
        else if (Thumb.MAX.equals(this.pressedThumb)) {
            this.setNormalizedMaxValue(this.screenToNormalized(x));
        }
    }
    
    private double valueToNormalized(final T t) {
        if (0.0 == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            return 0.0;
        }
        return (t.doubleValue() - this.absoluteMinValuePrim) / (this.absoluteMaxValuePrim - this.absoluteMinValuePrim);
    }
    
    public T getAbsoluteMaxValue() {
        return this.absoluteMaxValue;
    }
    
    public T getAbsoluteMinValue() {
        return this.absoluteMinValue;
    }
    
    public T getSelectedMaxValue() {
        return this.normalizedToValue(this.normalizedMaxValue);
    }
    
    public T getSelectedMinValue() {
        return this.normalizedToValue(this.normalizedMinValue);
    }
    
    public boolean isNotifyWhileDragging() {
        return this.notifyWhileDragging;
    }
    
    protected void onDraw(@NonNull final Canvas canvas) {
        synchronized (this) {
            super.onDraw(canvas);
            this.paint.setTextSize((float)this.mTextSize);
            this.paint.setStyle(Paint$Style.FILL);
            this.paint.setColor(this.mDefaultColor);
            this.paint.setAntiAlias(true);
            final boolean mShowLabels = this.mShowLabels;
            float max = 0.0f;
            if (mShowLabels) {
                final String string = this.getContext().getString(2131230847);
                final String string2 = this.getContext().getString(2131230846);
                max = Math.max(this.paint.measureText(string), this.paint.measureText(string2));
                final float n = this.mTextOffset + this.mThumbHalfHeight + this.mTextSize / 3;
                canvas.drawText(string, 0.0f, n, this.paint);
                canvas.drawText(string2, this.getWidth() - max, n, this.paint);
            }
            this.padding = max + this.mInternalPad + this.mThumbHalfWidth;
            this.mRect.left = this.padding;
            this.mRect.right = this.getWidth() - this.padding;
            canvas.drawRect(this.mRect, this.paint);
            final boolean b = this.getSelectedMinValue().equals(this.getAbsoluteMinValue()) && this.getSelectedMaxValue().equals(this.getAbsoluteMaxValue());
            int color;
            if (!this.mAlwaysActive && !this.mActivateOnDefaultValues && b) {
                color = this.mDefaultColor;
            }
            else {
                color = this.mActiveColor;
            }
            this.mRect.left = this.normalizedToScreen(this.normalizedMinValue);
            this.mRect.right = this.normalizedToScreen(this.normalizedMaxValue);
            this.paint.setColor(color);
            canvas.drawRect(this.mRect, this.paint);
            if (!this.mSingleThumb) {
                if (this.mThumbShadow) {
                    this.drawThumbShadow(this.normalizedToScreen(this.normalizedMinValue), canvas);
                }
                this.drawThumb(this.normalizedToScreen(this.normalizedMinValue), Thumb.MIN.equals(this.pressedThumb), canvas, b);
            }
            if (this.mThumbShadow) {
                this.drawThumbShadow(this.normalizedToScreen(this.normalizedMaxValue), canvas);
            }
            this.drawThumb(this.normalizedToScreen(this.normalizedMaxValue), Thumb.MAX.equals(this.pressedThumb), canvas, b);
            if (this.mShowTextAboveThumbs && (this.mActivateOnDefaultValues || !b)) {
                this.paint.setTextSize((float)this.mTextSize);
                this.paint.setColor(this.mTextAboveThumbsColor);
                final int dpToPx = Utils.dpToPx(this.getContext(), 3);
                final String value = String.valueOf(this.getSelectedMinValue());
                final String value2 = String.valueOf(this.getSelectedMaxValue());
                final float n2 = this.paint.measureText(value) + dpToPx;
                final float n3 = this.paint.measureText(value2) + dpToPx;
                if (!this.mSingleThumb) {
                    canvas.drawText(value, this.normalizedToScreen(this.normalizedMinValue) - 0.5f * n2, (float)(this.mDistanceToTop + this.mTextSize), this.paint);
                }
                canvas.drawText(value2, this.normalizedToScreen(this.normalizedMaxValue) - 0.5f * n3, (float)(this.mDistanceToTop + this.mTextSize), this.paint);
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        // monitorenter(this)
        int size = 200;
        try {
            if (View$MeasureSpec.getMode(n) != 0) {
                size = View$MeasureSpec.getSize(n);
            }
            final int height = this.thumbImage.getHeight();
            int dpToPx;
            if (!this.mShowTextAboveThumbs) {
                dpToPx = 0;
            }
            else {
                dpToPx = Utils.dpToPx(this.getContext(), 30);
            }
            final int n3 = dpToPx + height;
            final boolean mThumbShadow = this.mThumbShadow;
            int n4 = 0;
            if (mThumbShadow) {
                n4 = this.mThumbShadowYOffset + this.mThumbShadowBlur;
            }
            int min = n3 + n4;
            if (View$MeasureSpec.getMode(n2) != 0) {
                min = Math.min(min, View$MeasureSpec.getSize(n2));
            }
            this.setMeasuredDimension(size, min);
        }
        finally {
        }
        // monitorexit(this)
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final Bundle bundle = (Bundle)parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("SUPER"));
        this.normalizedMinValue = bundle.getDouble("MIN");
        this.normalizedMaxValue = bundle.getDouble("MAX");
    }
    
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("SUPER", super.onSaveInstanceState());
        bundle.putDouble("MIN", this.normalizedMinValue);
        bundle.putDouble("MAX", this.normalizedMaxValue);
        return (Parcelable)bundle;
    }
    
    void onStartTrackingTouch() {
        this.mIsDragging = true;
    }
    
    void onStopTrackingTouch() {
        this.mIsDragging = false;
    }
    
    public boolean onTouchEvent(@NonNull final MotionEvent motionEvent) {
        if (!this.isEnabled()) {
            return false;
        }
        switch (0xFF & motionEvent.getAction()) {
            case 0: {
                this.mActivePointerId = motionEvent.getPointerId(-1 + motionEvent.getPointerCount());
                this.mDownMotionX = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
                this.pressedThumb = this.evalPressedThumb(this.mDownMotionX);
                if (this.pressedThumb == null) {
                    return super.onTouchEvent(motionEvent);
                }
                this.setPressed(true);
                this.invalidate();
                this.onStartTrackingTouch();
                this.trackTouchEvent(motionEvent);
                this.attemptClaimDrag();
                break;
            }
            case 2: {
                if (this.pressedThumb == null) {
                    break;
                }
                if (this.mIsDragging) {
                    this.trackTouchEvent(motionEvent);
                }
                else if (Math.abs(motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)) - this.mDownMotionX) > this.mScaledTouchSlop) {
                    this.setPressed(true);
                    this.invalidate();
                    this.onStartTrackingTouch();
                    this.trackTouchEvent(motionEvent);
                    this.attemptClaimDrag();
                }
                if (this.notifyWhileDragging && this.listener != null) {
                    this.listener.onRangeSeekBarValuesChanged(this, this.getSelectedMinValue(), this.getSelectedMaxValue());
                    break;
                }
                break;
            }
            case 1: {
                if (this.mIsDragging) {
                    this.trackTouchEvent(motionEvent);
                    this.onStopTrackingTouch();
                    this.setPressed(false);
                }
                else {
                    this.onStartTrackingTouch();
                    this.trackTouchEvent(motionEvent);
                    this.onStopTrackingTouch();
                }
                this.pressedThumb = null;
                this.invalidate();
                if (this.listener != null) {
                    this.listener.onRangeSeekBarValuesChanged(this, this.getSelectedMinValue(), this.getSelectedMaxValue());
                    break;
                }
                break;
            }
            case 5: {
                final int n = -1 + motionEvent.getPointerCount();
                this.mDownMotionX = motionEvent.getX(n);
                this.mActivePointerId = motionEvent.getPointerId(n);
                this.invalidate();
                break;
            }
            case 6: {
                this.onSecondaryPointerUp(motionEvent);
                this.invalidate();
                break;
            }
            case 3: {
                if (this.mIsDragging) {
                    this.onStopTrackingTouch();
                    this.setPressed(false);
                }
                this.invalidate();
                break;
            }
        }
        return true;
    }
    
    public void resetSelectedValues() {
        this.setSelectedMinValue(this.absoluteMinValue);
        this.setSelectedMaxValue(this.absoluteMaxValue);
    }
    
    public void setNotifyWhileDragging(final boolean notifyWhileDragging) {
        this.notifyWhileDragging = notifyWhileDragging;
    }
    
    public void setOnRangeSeekBarChangeListener(final OnRangeSeekBarChangeListener<T> listener) {
        this.listener = listener;
    }
    
    public void setRangeValues(final T absoluteMinValue, final T absoluteMaxValue) {
        this.absoluteMinValue = absoluteMinValue;
        this.absoluteMaxValue = absoluteMaxValue;
        this.setValuePrimAndNumberType();
    }
    
    public void setSelectedMaxValue(final T t) {
        if (0.0 == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            this.setNormalizedMaxValue(1.0);
            return;
        }
        this.setNormalizedMaxValue(this.valueToNormalized(t));
    }
    
    public void setSelectedMinValue(final T t) {
        if (0.0 == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            this.setNormalizedMinValue(0.0);
            return;
        }
        this.setNormalizedMinValue(this.valueToNormalized(t));
    }
    
    public void setTextAboveThumbsColor(final int mTextAboveThumbsColor) {
        this.mTextAboveThumbsColor = mTextAboveThumbsColor;
        this.invalidate();
    }
    
    public void setTextAboveThumbsColorResource(@ColorRes final int n) {
        this.setTextAboveThumbsColor(this.getResources().getColor(n));
    }
    
    public void setThumbShadowPath(final Path mThumbShadowPath) {
        this.mThumbShadowPath = mThumbShadowPath;
    }
    
    private enum NumberType
    {
        BIG_DECIMAL, 
        BYTE, 
        DOUBLE, 
        FLOAT, 
        INTEGER, 
        LONG, 
        SHORT;
        
        public static <E extends Number> NumberType fromNumber(final E e) throws IllegalArgumentException {
            if (e instanceof Long) {
                return NumberType.LONG;
            }
            if (e instanceof Double) {
                return NumberType.DOUBLE;
            }
            if (e instanceof Integer) {
                return NumberType.INTEGER;
            }
            if (e instanceof Float) {
                return NumberType.FLOAT;
            }
            if (e instanceof Short) {
                return NumberType.SHORT;
            }
            if (e instanceof Byte) {
                return NumberType.BYTE;
            }
            if (e instanceof BigDecimal) {
                return NumberType.BIG_DECIMAL;
            }
            throw new IllegalArgumentException("Number class '" + e.getClass().getName() + "' is not supported");
        }
        
        public Number toNumber(final double n) {
            switch (this) {
                default: {
                    throw new InstantiationError("can't convert " + this + " to a Number object");
                }
                case LONG: {
                    return (long)n;
                }
                case DOUBLE: {
                    return n;
                }
                case INTEGER: {
                    return (int)n;
                }
                case FLOAT: {
                    return (float)n;
                }
                case SHORT: {
                    return (short)n;
                }
                case BYTE: {
                    return (byte)n;
                }
                case BIG_DECIMAL: {
                    return BigDecimal.valueOf(n);
                }
            }
        }
    }
    
    public interface OnRangeSeekBarChangeListener<T>
    {
        void onRangeSeekBarValuesChanged(final RangeSeekBar<?> p0, final T p1, final T p2);
    }
    
    private enum Thumb
    {
        MAX, 
        MIN;
    }
}
