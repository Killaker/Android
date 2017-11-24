package mbanje.kurt.fabbutton;

import android.graphics.*;
import android.os.*;
import android.support.v4.view.*;
import android.view.animation.*;
import java.util.*;
import android.support.design.widget.*;
import android.view.*;

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
