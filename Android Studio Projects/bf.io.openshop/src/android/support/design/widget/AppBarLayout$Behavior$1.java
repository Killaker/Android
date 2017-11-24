package android.support.design.widget;

class AppBarLayout$Behavior$1 implements AnimatorUpdateListener {
    final /* synthetic */ AppBarLayout val$child;
    final /* synthetic */ CoordinatorLayout val$coordinatorLayout;
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        Behavior.this.setHeaderTopBottomOffset(this.val$coordinatorLayout, this.val$child, valueAnimatorCompat.getAnimatedIntValue());
    }
}