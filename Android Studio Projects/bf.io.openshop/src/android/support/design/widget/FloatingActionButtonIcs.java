package android.support.design.widget;

import android.support.annotation.*;
import android.support.v4.view.*;
import android.view.*;
import android.animation.*;

class FloatingActionButtonIcs extends FloatingActionButtonEclairMr1
{
    private boolean mIsHiding;
    
    FloatingActionButtonIcs(final VisibilityAwareImageButton visibilityAwareImageButton, final ShadowViewDelegate shadowViewDelegate) {
        super(visibilityAwareImageButton, shadowViewDelegate);
    }
    
    private void updateFromViewRotation(final float n) {
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setRotation(-n);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setRotation(-n);
        }
    }
    
    @Override
    void hide(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean b) {
        if (this.mIsHiding || this.mView.getVisibility() != 0) {
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
        }
        else {
            if (ViewCompat.isLaidOut((View)this.mView) && !this.mView.isInEditMode()) {
                this.mView.animate().cancel();
                this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
                    private boolean mCancelled;
                    
                    public void onAnimationCancel(final Animator animator) {
                        FloatingActionButtonIcs.this.mIsHiding = false;
                        this.mCancelled = true;
                    }
                    
                    public void onAnimationEnd(final Animator animator) {
                        FloatingActionButtonIcs.this.mIsHiding = false;
                        if (!this.mCancelled) {
                            FloatingActionButtonIcs.this.mView.internalSetVisibility(8, b);
                            if (internalVisibilityChangedListener != null) {
                                internalVisibilityChangedListener.onHidden();
                            }
                        }
                    }
                    
                    public void onAnimationStart(final Animator animator) {
                        FloatingActionButtonIcs.this.mIsHiding = true;
                        this.mCancelled = false;
                        FloatingActionButtonIcs.this.mView.internalSetVisibility(0, b);
                    }
                });
                return;
            }
            this.mView.internalSetVisibility(8, b);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
        }
    }
    
    @Override
    void onPreDraw() {
        this.updateFromViewRotation(this.mView.getRotation());
    }
    
    @Override
    boolean requirePreDrawListener() {
        return true;
    }
    
    @Override
    void show(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean b) {
        if (this.mIsHiding || this.mView.getVisibility() != 0) {
            if (ViewCompat.isLaidOut((View)this.mView) && !this.mView.isInEditMode()) {
                this.mView.animate().cancel();
                if (this.mView.getVisibility() != 0) {
                    this.mView.setAlpha(0.0f);
                    this.mView.setScaleY(0.0f);
                    this.mView.setScaleX(0.0f);
                }
                this.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
                    public void onAnimationEnd(final Animator animator) {
                        if (internalVisibilityChangedListener != null) {
                            internalVisibilityChangedListener.onShown();
                        }
                    }
                    
                    public void onAnimationStart(final Animator animator) {
                        FloatingActionButtonIcs.this.mView.internalSetVisibility(0, b);
                    }
                });
            }
            else {
                this.mView.internalSetVisibility(0, b);
                this.mView.setAlpha(1.0f);
                this.mView.setScaleY(1.0f);
                this.mView.setScaleX(1.0f);
                if (internalVisibilityChangedListener != null) {
                    internalVisibilityChangedListener.onShown();
                }
            }
        }
    }
}
