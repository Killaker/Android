package android.support.design.widget;

class TabLayout$SlidingTabStrip$2 extends AnimatorListenerAdapter {
    final /* synthetic */ int val$position;
    
    @Override
    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        SlidingTabStrip.access$2502(SlidingTabStrip.this, this.val$position);
        SlidingTabStrip.access$2602(SlidingTabStrip.this, 0.0f);
    }
}