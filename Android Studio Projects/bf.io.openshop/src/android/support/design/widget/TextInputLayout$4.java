package android.support.design.widget;

class TextInputLayout$4 implements AnimatorUpdateListener {
    @Override
    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
        TextInputLayout.access$500(TextInputLayout.this).setExpansionFraction(valueAnimatorCompat.getAnimatedFloatValue());
    }
}