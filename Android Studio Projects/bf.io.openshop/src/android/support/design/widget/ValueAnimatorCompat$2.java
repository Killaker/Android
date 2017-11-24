package android.support.design.widget;

class ValueAnimatorCompat$2 implements AnimatorListenerProxy {
    final /* synthetic */ AnimatorListener val$listener;
    
    @Override
    public void onAnimationCancel() {
        this.val$listener.onAnimationCancel(ValueAnimatorCompat.this);
    }
    
    @Override
    public void onAnimationEnd() {
        this.val$listener.onAnimationEnd(ValueAnimatorCompat.this);
    }
    
    @Override
    public void onAnimationStart() {
        this.val$listener.onAnimationStart(ValueAnimatorCompat.this);
    }
}