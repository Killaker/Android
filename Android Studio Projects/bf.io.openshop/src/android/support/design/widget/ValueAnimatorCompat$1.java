package android.support.design.widget;

class ValueAnimatorCompat$1 implements AnimatorUpdateListenerProxy {
    final /* synthetic */ AnimatorUpdateListener val$updateListener;
    
    @Override
    public void onAnimationUpdate() {
        this.val$updateListener.onAnimationUpdate(ValueAnimatorCompat.this);
    }
}