package mbanje.kurt.fabbutton;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.view.*;
import android.graphics.*;
import android.os.*;
import android.support.v4.view.*;
import android.view.animation.*;
import java.util.*;
import android.support.design.widget.*;

@CoordinatorLayout.DefaultBehavior("Lmbanje/kurt/fabbutton/FabButton$Behavior;")
public class FabButton extends FrameLayout implements OnFabViewListener
{
    private boolean autostartanim;
    private CircleImageView circle;
    private int endBitmapResource;
    private boolean hideProgressOnComplete;
    private boolean indeterminate;
    private ProgressRingView ring;
    private float ringWidthRatio;
    private boolean showEndBitmap;
    
    public FabButton(final Context context) {
        super(context);
        this.ringWidthRatio = 0.14f;
        this.init(context, null, 0);
    }
    
    public FabButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.ringWidthRatio = 0.14f;
        this.init(context, set, 0);
    }
    
    public FabButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ringWidthRatio = 0.14f;
        this.init(context, set, n);
    }
    
    public void hideProgressOnComplete(final boolean hideProgressOnComplete) {
        this.hideProgressOnComplete = hideProgressOnComplete;
    }
    
    protected void init(final Context context, final AttributeSet set, final int n) {
        final View inflate = View.inflate(context, R.layout.widget_fab_button, (ViewGroup)this);
        this.setClipChildren(false);
        this.circle = (CircleImageView)inflate.findViewById(R.id.fabbutton_circle);
        this.ring = (ProgressRingView)inflate.findViewById(R.id.fabbutton_ring);
        this.circle.setFabViewListener((CircleImageView.OnFabViewListener)this);
        this.ring.setFabViewListener(this);
        int color = -16777216;
        int color2 = -16777216;
        int integer = 4000;
        int resourceId = -1;
        float float1 = 0.0f;
        float float2 = 0.0f;
        if (set != null) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CircleImageView);
            color = obtainStyledAttributes.getColor(R.styleable.CircleImageView_android_color, -16777216);
            color2 = obtainStyledAttributes.getColor(R.styleable.CircleImageView_fbb_progressColor, -16777216);
            float2 = obtainStyledAttributes.getFloat(R.styleable.CircleImageView_android_progress, 0.0f);
            float1 = obtainStyledAttributes.getFloat(R.styleable.CircleImageView_android_max, 100.0f);
            this.indeterminate = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_android_indeterminate, false);
            this.autostartanim = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_fbb_autoStart, true);
            integer = obtainStyledAttributes.getInteger(R.styleable.CircleImageView_android_indeterminateDuration, integer);
            resourceId = obtainStyledAttributes.getResourceId(R.styleable.CircleImageView_android_src, resourceId);
            this.ringWidthRatio = obtainStyledAttributes.getFloat(R.styleable.CircleImageView_fbb_progressWidthRatio, this.ringWidthRatio);
            this.endBitmapResource = obtainStyledAttributes.getResourceId(R.styleable.CircleImageView_fbb_endBitmap, R.drawable.ic_fab_complete);
            this.showEndBitmap = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_fbb_showEndBitmap, false);
            this.hideProgressOnComplete = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_fbb_hideProgressOnComplete, false);
            this.circle.setShowShadow(obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_fbb_showShadow, true));
            obtainStyledAttributes.recycle();
        }
        this.circle.setColor(color);
        this.circle.setShowEndBitmap(this.showEndBitmap);
        this.circle.setRingWidthRatio(this.ringWidthRatio);
        this.ring.setProgressColor(color2);
        this.ring.setProgress(float2);
        this.ring.setMaxProgress(float1);
        this.ring.setAutostartanim(this.autostartanim);
        this.ring.setAnimDuration(integer);
        this.ring.setRingWidthRatio(this.ringWidthRatio);
        this.ring.setIndeterminate(this.indeterminate);
        if (resourceId != -1) {
            this.circle.setIcon(resourceId, this.endBitmapResource);
        }
    }
    
    public void onProgressCompleted() {
        this.circle.showCompleted(this.showEndBitmap, this.hideProgressOnComplete);
        if (this.hideProgressOnComplete) {
            this.ring.setVisibility(8);
        }
    }
    
    public void onProgressVisibilityChanged(final boolean b) {
        if (b) {
            this.ring.setVisibility(0);
            this.ring.startAnimation();
            return;
        }
        this.ring.stopAnimation(true);
        this.ring.setVisibility(8);
    }
    
    public void resetIcon() {
        this.circle.resetIcon();
    }
    
    public void setColor(final int color) {
        this.circle.setColor(color);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.circle.setEnabled(enabled);
        this.ring.setEnabled(enabled);
    }
    
    public void setIcon(final int n, final int n2) {
        this.circle.setIcon(n, n2);
    }
    
    public void setIcon(final Drawable drawable, final Drawable drawable2) {
        this.circle.setIcon(drawable, drawable2);
    }
    
    public void setIndeterminate(final boolean b) {
        this.indeterminate = b;
        this.ring.setIndeterminate(b);
    }
    
    public void setOnClickListener(final View$OnClickListener view$OnClickListener) {
        this.ring.setOnClickListener(view$OnClickListener);
        this.circle.setOnClickListener(view$OnClickListener);
    }
    
    public void setProgress(final float progress) {
        this.ring.setProgress(progress);
    }
    
    public void showProgress(final boolean b) {
        this.circle.showRing(b);
    }
    
    public static class Behavior extends CoordinatorLayout.Behavior<FabButton>
    {
        private static final boolean SNACKBAR_BEHAVIOR_ENABLED;
        private boolean mIsAnimatingOut;
        private Rect mTmpRect;
        private float mTranslationY;
        
        static {
            SNACKBAR_BEHAVIOR_ENABLED = (Build$VERSION.SDK_INT >= 11);
        }
        
        private void animateIn(final FabButton fabButton) {
            fabButton.setVisibility(0);
            if (Build$VERSION.SDK_INT >= 14) {
                ViewCompat.animate((View)fabButton).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).withLayer().setListener(null).start();
                return;
            }
            final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(fabButton.getContext(), R.anim.design_fab_in);
            loadAnimation.setDuration(200L);
            loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            fabButton.startAnimation(loadAnimation);
        }
        
        private void animateOut(final FabButton fabButton) {
            if (Build$VERSION.SDK_INT >= 14) {
                ViewCompat.animate((View)fabButton).scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).withLayer().setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationCancel(final View view) {
                        Behavior.this.mIsAnimatingOut = false;
                    }
                    
                    @Override
                    public void onAnimationEnd(final View view) {
                        Behavior.this.mIsAnimatingOut = false;
                        view.setVisibility(8);
                    }
                    
                    @Override
                    public void onAnimationStart(final View view) {
                        Behavior.this.mIsAnimatingOut = true;
                    }
                }).start();
                return;
            }
            final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(fabButton.getContext(), R.anim.design_fab_out);
            loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            loadAnimation.setDuration(200L);
            loadAnimation.setAnimationListener((Animation$AnimationListener)new AnimationUtils.AnimationListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animation animation) {
                    Behavior.this.mIsAnimatingOut = false;
                    fabButton.setVisibility(8);
                }
                
                @Override
                public void onAnimationStart(final Animation animation) {
                    Behavior.this.mIsAnimatingOut = true;
                }
            });
            fabButton.startAnimation(loadAnimation);
        }
        
        private float getFabTranslationYForSnackbar(final CoordinatorLayout coordinatorLayout, final FabButton fabButton) {
            float min = 0.0f;
            final List<View> dependencies = coordinatorLayout.getDependencies((View)fabButton);
            for (int i = 0; i < dependencies.size(); ++i) {
                final View view = dependencies.get(i);
                if (view instanceof Snackbar.SnackbarLayout && coordinatorLayout.doViewsOverlap((View)fabButton, view)) {
                    min = Math.min(min, ViewCompat.getTranslationY(view) - view.getHeight());
                }
            }
            return min;
        }
        
        private void updateFabTranslationForSnackbar(final CoordinatorLayout coordinatorLayout, final FabButton fabButton, final View view) {
            final float fabTranslationYForSnackbar = this.getFabTranslationYForSnackbar(coordinatorLayout, fabButton);
            if (fabTranslationYForSnackbar != this.mTranslationY) {
                ViewCompat.animate((View)fabButton).cancel();
                if (Math.abs(fabTranslationYForSnackbar - this.mTranslationY) == view.getHeight()) {
                    ViewCompat.animate((View)fabButton).translationY(fabTranslationYForSnackbar).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(null);
                }
                else {
                    ViewCompat.setTranslationY((View)fabButton, fabTranslationYForSnackbar);
                }
                this.mTranslationY = fabTranslationYForSnackbar;
            }
        }
        
        final int getMinimumHeightForVisibleOverlappingContent(final AppBarLayout appBarLayout) {
            final int minimumHeight = ViewCompat.getMinimumHeight((View)appBarLayout);
            if (minimumHeight != 0) {
                return 0 + minimumHeight * 2;
            }
            final int childCount = appBarLayout.getChildCount();
            if (childCount >= 1) {
                return 0 + 2 * ViewCompat.getMinimumHeight(appBarLayout.getChildAt(childCount - 1));
            }
            return 0;
        }
        
        public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final FabButton fabButton, final View view) {
            return Behavior.SNACKBAR_BEHAVIOR_ENABLED && view instanceof Snackbar.SnackbarLayout;
        }
        
        public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final FabButton fabButton, final View view) {
            if (view instanceof Snackbar.SnackbarLayout) {
                this.updateFabTranslationForSnackbar(coordinatorLayout, fabButton, view);
            }
            else if (view instanceof AppBarLayout) {
                final AppBarLayout appBarLayout = (AppBarLayout)view;
                if (this.mTmpRect == null) {
                    this.mTmpRect = new Rect();
                }
                final Rect mTmpRect = this.mTmpRect;
                ViewGroupUtils.getDescendantRect(coordinatorLayout, view, mTmpRect);
                if (mTmpRect.bottom <= this.getMinimumHeightForVisibleOverlappingContent(appBarLayout)) {
                    if (!this.mIsAnimatingOut && fabButton.getVisibility() == 0) {
                        this.animateOut(fabButton);
                    }
                }
                else if (fabButton.getVisibility() != 0) {
                    this.animateIn(fabButton);
                }
            }
            return false;
        }
    }
}
