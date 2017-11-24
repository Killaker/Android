package android.support.design.widget;

class TabLayout$1 implements AnimatorUpdateListener {
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        TabLayout.this.scrollTo(valueAnimatorCompat.getAnimatedIntValue(), 0);
    }
}