package android.support.graphics.drawable;

import java.util.*;
import android.animation.*;
import android.support.v4.util.*;
import android.content.*;
import android.content.res.*;
import android.graphics.drawable.*;

private static class AnimatedVectorDrawableCompatState extends Drawable$ConstantState
{
    ArrayList<Animator> mAnimators;
    int mChangingConfigurations;
    ArrayMap<Animator, String> mTargetNameMap;
    VectorDrawableCompat mVectorDrawable;
    
    public AnimatedVectorDrawableCompatState(final Context context, final AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, final Drawable$Callback callback, final Resources resources) {
        if (animatedVectorDrawableCompatState != null) {
            this.mChangingConfigurations = animatedVectorDrawableCompatState.mChangingConfigurations;
            if (animatedVectorDrawableCompatState.mVectorDrawable != null) {
                final Drawable$ConstantState constantState = animatedVectorDrawableCompatState.mVectorDrawable.getConstantState();
                if (resources != null) {
                    this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable(resources);
                }
                else {
                    this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable();
                }
                (this.mVectorDrawable = (VectorDrawableCompat)this.mVectorDrawable.mutate()).setCallback(callback);
                this.mVectorDrawable.setBounds(animatedVectorDrawableCompatState.mVectorDrawable.getBounds());
                this.mVectorDrawable.setAllowCaching(false);
            }
            if (animatedVectorDrawableCompatState.mAnimators != null) {
                final int size = animatedVectorDrawableCompatState.mAnimators.size();
                this.mAnimators = new ArrayList<Animator>(size);
                this.mTargetNameMap = new ArrayMap<Animator, String>(size);
                for (int i = 0; i < size; ++i) {
                    final Animator animator = animatedVectorDrawableCompatState.mAnimators.get(i);
                    final Animator clone = animator.clone();
                    final String s = animatedVectorDrawableCompatState.mTargetNameMap.get(animator);
                    clone.setTarget(this.mVectorDrawable.getTargetByName(s));
                    this.mAnimators.add(clone);
                    this.mTargetNameMap.put(clone, s);
                }
            }
        }
    }
    
    public int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }
    
    public Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 21.");
    }
    
    public Drawable newDrawable(final Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 21.");
    }
}
