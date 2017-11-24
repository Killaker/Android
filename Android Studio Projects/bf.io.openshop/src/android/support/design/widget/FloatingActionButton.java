package android.support.design.widget;

import android.graphics.*;
import android.content.*;
import android.support.design.*;
import android.support.v7.widget.*;
import android.widget.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.os.*;
import android.support.v4.view.*;
import android.annotation.*;
import android.util.*;
import android.support.annotation.*;
import java.util.*;
import android.view.*;

@CoordinatorLayout.DefaultBehavior("Landroid/support/design/widget/FloatingActionButton$Behavior;")
public class FloatingActionButton extends VisibilityAwareImageButton
{
    private static final String LOG_TAG = "FloatingActionButton";
    private static final int SIZE_MINI = 1;
    private static final int SIZE_NORMAL;
    private ColorStateList mBackgroundTint;
    private PorterDuff$Mode mBackgroundTintMode;
    private int mBorderWidth;
    private boolean mCompatPadding;
    private AppCompatImageHelper mImageHelper;
    private int mImagePadding;
    private FloatingActionButtonImpl mImpl;
    private int mRippleColor;
    private final Rect mShadowPadding;
    private int mSize;
    
    public FloatingActionButton(final Context context) {
        this(context, null);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mShadowPadding = new Rect();
        ThemeUtils.checkAppCompatTheme(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.FloatingActionButton, n, R.style.Widget_Design_FloatingActionButton);
        this.mBackgroundTint = obtainStyledAttributes.getColorStateList(R.styleable.FloatingActionButton_backgroundTint);
        this.mBackgroundTintMode = parseTintMode(obtainStyledAttributes.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.mRippleColor = obtainStyledAttributes.getColor(R.styleable.FloatingActionButton_rippleColor, 0);
        this.mSize = obtainStyledAttributes.getInt(R.styleable.FloatingActionButton_fabSize, 0);
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
        final float dimension = obtainStyledAttributes.getDimension(R.styleable.FloatingActionButton_elevation, 0.0f);
        final float dimension2 = obtainStyledAttributes.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
        obtainStyledAttributes.recycle();
        (this.mImageHelper = new AppCompatImageHelper((ImageView)this, AppCompatDrawableManager.get())).loadFromAttributes(set, n);
        this.mImagePadding = (this.getSizeDimension() - (int)this.getResources().getDimension(R.dimen.design_fab_image_size)) / 2;
        this.getImpl().setBackgroundDrawable(this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
        this.getImpl().setElevation(dimension);
        this.getImpl().setPressedTranslationZ(dimension2);
        this.getImpl().updatePadding();
    }
    
    static /* synthetic */ void access$501(final FloatingActionButton floatingActionButton, final Drawable backgroundDrawable) {
        ((View)floatingActionButton).setBackgroundDrawable(backgroundDrawable);
    }
    
    private FloatingActionButtonImpl createImpl() {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            return new FloatingActionButtonLollipop(this, new ShadowDelegateImpl());
        }
        if (sdk_INT >= 14) {
            return new FloatingActionButtonIcs(this, new ShadowDelegateImpl());
        }
        return new FloatingActionButtonEclairMr1(this, new ShadowDelegateImpl());
    }
    
    private FloatingActionButtonImpl getImpl() {
        if (this.mImpl == null) {
            this.mImpl = this.createImpl();
        }
        return this.mImpl;
    }
    
    private void hide(@Nullable final OnVisibilityChangedListener onVisibilityChangedListener, final boolean b) {
        this.getImpl().hide(this.wrapOnVisibilityChangedListener(onVisibilityChangedListener), b);
    }
    
    static PorterDuff$Mode parseTintMode(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            default: {
                return porterDuff$Mode;
            }
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
        }
    }
    
    private static int resolveAdjustedSize(final int n, final int n2) {
        final int mode = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n2);
        switch (mode) {
            default: {
                return n;
            }
            case 0: {
                return n;
            }
            case Integer.MIN_VALUE: {
                return Math.min(n, size);
            }
            case 1073741824: {
                return size;
            }
        }
    }
    
    private void show(final OnVisibilityChangedListener onVisibilityChangedListener, final boolean b) {
        this.getImpl().show(this.wrapOnVisibilityChangedListener(onVisibilityChangedListener), b);
    }
    
    @Nullable
    private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(@Nullable final OnVisibilityChangedListener onVisibilityChangedListener) {
        if (onVisibilityChangedListener == null) {
            return null;
        }
        return new FloatingActionButtonImpl.InternalVisibilityChangedListener() {
            @Override
            public void onHidden() {
                onVisibilityChangedListener.onHidden(FloatingActionButton.this);
            }
            
            @Override
            public void onShown() {
                onVisibilityChangedListener.onShown(FloatingActionButton.this);
            }
        };
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.getImpl().onDrawableStateChanged(this.getDrawableState());
    }
    
    @Nullable
    public ColorStateList getBackgroundTintList() {
        return this.mBackgroundTint;
    }
    
    @Nullable
    public PorterDuff$Mode getBackgroundTintMode() {
        return this.mBackgroundTintMode;
    }
    
    public float getCompatElevation() {
        return this.getImpl().getElevation();
    }
    
    @NonNull
    public Drawable getContentBackground() {
        return this.getImpl().getContentBackground();
    }
    
    public boolean getContentRect(@NonNull final Rect rect) {
        final boolean laidOut = ViewCompat.isLaidOut((View)this);
        boolean b = false;
        if (laidOut) {
            rect.set(0, 0, this.getWidth(), this.getHeight());
            rect.left += this.mShadowPadding.left;
            rect.top += this.mShadowPadding.top;
            rect.right -= this.mShadowPadding.right;
            rect.bottom -= this.mShadowPadding.bottom;
            b = true;
        }
        return b;
    }
    
    final int getSizeDimension() {
        switch (this.mSize) {
            default: {
                return this.getResources().getDimensionPixelSize(R.dimen.design_fab_size_normal);
            }
            case 1: {
                return this.getResources().getDimensionPixelSize(R.dimen.design_fab_size_mini);
            }
        }
    }
    
    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }
    
    public void hide() {
        this.hide(null);
    }
    
    public void hide(@Nullable final OnVisibilityChangedListener onVisibilityChangedListener) {
        this.hide(onVisibilityChangedListener, true);
    }
    
    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.getImpl().jumpDrawableToCurrentState();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getImpl().onAttachedToWindow();
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.getImpl().onDetachedFromWindow();
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int sizeDimension = this.getSizeDimension();
        final int min = Math.min(resolveAdjustedSize(sizeDimension, n), resolveAdjustedSize(sizeDimension, n2));
        this.setMeasuredDimension(min + this.mShadowPadding.left + this.mShadowPadding.right, min + this.mShadowPadding.top + this.mShadowPadding.bottom);
    }
    
    public void setBackgroundColor(final int n) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }
    
    public void setBackgroundDrawable(final Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }
    
    public void setBackgroundResource(final int n) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }
    
    public void setBackgroundTintList(@Nullable final ColorStateList list) {
        if (this.mBackgroundTint != list) {
            this.mBackgroundTint = list;
            this.getImpl().setBackgroundTintList(list);
        }
    }
    
    public void setBackgroundTintMode(@Nullable final PorterDuff$Mode porterDuff$Mode) {
        if (this.mBackgroundTintMode != porterDuff$Mode) {
            this.mBackgroundTintMode = porterDuff$Mode;
            this.getImpl().setBackgroundTintMode(porterDuff$Mode);
        }
    }
    
    public void setCompatElevation(final float elevation) {
        this.getImpl().setElevation(elevation);
    }
    
    public void setImageResource(@DrawableRes final int imageResource) {
        this.mImageHelper.setImageResource(imageResource);
    }
    
    public void setRippleColor(@ColorInt final int n) {
        if (this.mRippleColor != n) {
            this.mRippleColor = n;
            this.getImpl().setRippleColor(n);
        }
    }
    
    public void setUseCompatPadding(final boolean mCompatPadding) {
        if (this.mCompatPadding != mCompatPadding) {
            this.mCompatPadding = mCompatPadding;
            this.getImpl().onCompatShadowChanged();
        }
    }
    
    public void show() {
        this.show(null);
    }
    
    public void show(@Nullable final OnVisibilityChangedListener onVisibilityChangedListener) {
        this.show(onVisibilityChangedListener, true);
    }
    
    public static class Behavior extends CoordinatorLayout.Behavior<FloatingActionButton>
    {
        private static final boolean SNACKBAR_BEHAVIOR_ENABLED;
        private float mFabTranslationY;
        private ValueAnimatorCompat mFabTranslationYAnimator;
        private Rect mTmpRect;
        
        static {
            SNACKBAR_BEHAVIOR_ENABLED = (Build$VERSION.SDK_INT >= 11);
        }
        
        private float getFabTranslationYForSnackbar(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton) {
            float min = 0.0f;
            final List<View> dependencies = coordinatorLayout.getDependencies((View)floatingActionButton);
            for (int i = 0; i < dependencies.size(); ++i) {
                final View view = dependencies.get(i);
                if (view instanceof Snackbar.SnackbarLayout && coordinatorLayout.doViewsOverlap((View)floatingActionButton, view)) {
                    min = Math.min(min, ViewCompat.getTranslationY(view) - view.getHeight());
                }
            }
            return min;
        }
        
        private void offsetIfNeeded(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton) {
            final Rect access$200 = floatingActionButton.mShadowPadding;
            if (access$200 != null && access$200.centerX() > 0 && access$200.centerY() > 0) {
                final LayoutParams layoutParams = (LayoutParams)floatingActionButton.getLayoutParams();
                int right;
                if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - layoutParams.rightMargin) {
                    right = access$200.right;
                }
                else {
                    final int left = floatingActionButton.getLeft();
                    final int leftMargin = layoutParams.leftMargin;
                    right = 0;
                    if (left <= leftMargin) {
                        right = -access$200.left;
                    }
                }
                int bottom;
                if (floatingActionButton.getBottom() >= coordinatorLayout.getBottom() - layoutParams.bottomMargin) {
                    bottom = access$200.bottom;
                }
                else {
                    final int top = floatingActionButton.getTop();
                    final int topMargin = layoutParams.topMargin;
                    bottom = 0;
                    if (top <= topMargin) {
                        bottom = -access$200.top;
                    }
                }
                floatingActionButton.offsetTopAndBottom(bottom);
                floatingActionButton.offsetLeftAndRight(right);
            }
        }
        
        private void updateFabTranslationForSnackbar(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
            final float fabTranslationYForSnackbar = this.getFabTranslationYForSnackbar(coordinatorLayout, floatingActionButton);
            if (this.mFabTranslationY == fabTranslationYForSnackbar) {
                return;
            }
            final float translationY = ViewCompat.getTranslationY((View)floatingActionButton);
            if (this.mFabTranslationYAnimator != null && this.mFabTranslationYAnimator.isRunning()) {
                this.mFabTranslationYAnimator.cancel();
            }
            if (floatingActionButton.isShown() && Math.abs(translationY - fabTranslationYForSnackbar) > 0.667f * floatingActionButton.getHeight()) {
                if (this.mFabTranslationYAnimator == null) {
                    (this.mFabTranslationYAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                    this.mFabTranslationYAnimator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                            ViewCompat.setTranslationY((View)floatingActionButton, valueAnimatorCompat.getAnimatedFloatValue());
                        }
                    });
                }
                this.mFabTranslationYAnimator.setFloatValues(translationY, fabTranslationYForSnackbar);
                this.mFabTranslationYAnimator.start();
            }
            else {
                ViewCompat.setTranslationY((View)floatingActionButton, fabTranslationYForSnackbar);
            }
            this.mFabTranslationY = fabTranslationYForSnackbar;
        }
        
        private boolean updateFabVisibility(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final FloatingActionButton floatingActionButton) {
            if (((LayoutParams)floatingActionButton.getLayoutParams()).getAnchorId() == appBarLayout.getId() && floatingActionButton.getUserSetVisibility() == 0) {
                if (this.mTmpRect == null) {
                    this.mTmpRect = new Rect();
                }
                final Rect mTmpRect = this.mTmpRect;
                ViewGroupUtils.getDescendantRect(coordinatorLayout, (View)appBarLayout, mTmpRect);
                if (mTmpRect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                    floatingActionButton.hide(null, false);
                }
                else {
                    floatingActionButton.show(null, false);
                }
                return true;
            }
            return false;
        }
        
        public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
            return Behavior.SNACKBAR_BEHAVIOR_ENABLED && view instanceof Snackbar.SnackbarLayout;
        }
        
        public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
            if (view instanceof Snackbar.SnackbarLayout) {
                this.updateFabTranslationForSnackbar(coordinatorLayout, floatingActionButton, view);
            }
            else if (view instanceof AppBarLayout) {
                this.updateFabVisibility(coordinatorLayout, (AppBarLayout)view, floatingActionButton);
            }
            return false;
        }
        
        public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final int n) {
            final List<View> dependencies = coordinatorLayout.getDependencies((View)floatingActionButton);
            for (int i = 0; i < dependencies.size(); ++i) {
                final View view = dependencies.get(i);
                if (view instanceof AppBarLayout && this.updateFabVisibility(coordinatorLayout, (AppBarLayout)view, floatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild((View)floatingActionButton, n);
            this.offsetIfNeeded(coordinatorLayout, floatingActionButton);
            return true;
        }
    }
    
    public abstract static class OnVisibilityChangedListener
    {
        public void onHidden(final FloatingActionButton floatingActionButton) {
        }
        
        public void onShown(final FloatingActionButton floatingActionButton) {
        }
    }
    
    private class ShadowDelegateImpl implements ShadowViewDelegate
    {
        @Override
        public float getRadius() {
            return FloatingActionButton.this.getSizeDimension() / 2.0f;
        }
        
        @Override
        public boolean isCompatPaddingEnabled() {
            return FloatingActionButton.this.mCompatPadding;
        }
        
        @Override
        public void setBackgroundDrawable(final Drawable drawable) {
            FloatingActionButton.access$501(FloatingActionButton.this, drawable);
        }
        
        @Override
        public void setShadowPadding(final int n, final int n2, final int n3, final int n4) {
            FloatingActionButton.this.mShadowPadding.set(n, n2, n3, n4);
            FloatingActionButton.this.setPadding(n + FloatingActionButton.this.mImagePadding, n2 + FloatingActionButton.this.mImagePadding, n3 + FloatingActionButton.this.mImagePadding, n4 + FloatingActionButton.this.mImagePadding);
        }
    }
}
