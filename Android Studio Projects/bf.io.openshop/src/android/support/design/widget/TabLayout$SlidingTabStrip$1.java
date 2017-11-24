package android.support.design.widget;

class TabLayout$SlidingTabStrip$1 implements AnimatorUpdateListener {
    final /* synthetic */ int val$startLeft;
    final /* synthetic */ int val$startRight;
    final /* synthetic */ int val$targetLeft;
    final /* synthetic */ int val$targetRight;
    
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        final float animatedFraction = valueAnimatorCompat.getAnimatedFraction();
        SlidingTabStrip.access$2400(SlidingTabStrip.this, AnimationUtils.lerp(this.val$startLeft, this.val$targetLeft, animatedFraction), AnimationUtils.lerp(this.val$startRight, this.val$targetRight, animatedFraction));
    }
}