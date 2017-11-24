package android.support.v4.animation;

class DonutAnimatorCompatProvider$DonutFloatValueAnimator$1 implements Runnable {
    @Override
    public void run() {
        float n = 1.0f * (DonutFloatValueAnimator.access$000(DonutFloatValueAnimator.this) - DonutFloatValueAnimator.access$100(DonutFloatValueAnimator.this)) / DonutFloatValueAnimator.access$200(DonutFloatValueAnimator.this);
        if (n > 1.0f || DonutFloatValueAnimator.this.mTarget.getParent() == null) {
            n = 1.0f;
        }
        DonutFloatValueAnimator.access$302(DonutFloatValueAnimator.this, n);
        DonutFloatValueAnimator.access$400(DonutFloatValueAnimator.this);
        if (DonutFloatValueAnimator.access$300(DonutFloatValueAnimator.this) >= 1.0f) {
            DonutFloatValueAnimator.access$500(DonutFloatValueAnimator.this);
            return;
        }
        DonutFloatValueAnimator.this.mTarget.postDelayed(DonutFloatValueAnimator.access$600(DonutFloatValueAnimator.this), 16L);
    }
}