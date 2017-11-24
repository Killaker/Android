package bf.io.openshop.ux.fragments;

import android.view.animation.*;
import android.support.v7.widget.*;

class CategoryFragment$7 implements Animation$AnimationListener {
    final /* synthetic */ int val$layoutSpanCount;
    
    public void onAnimationEnd(final Animation animation) {
        CategoryFragment.access$1000(CategoryFragment.this).setSpanCount(this.val$layoutSpanCount);
        ((RecyclerView.LayoutManager)CategoryFragment.access$1000(CategoryFragment.this)).requestLayout();
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        ((Animation)alphaAnimation).setInterpolator((Interpolator)new AccelerateInterpolator());
        ((Animation)alphaAnimation).setDuration(400L);
        CategoryFragment.access$1100(CategoryFragment.this).startAnimation((Animation)alphaAnimation);
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}