package android.support.v4.widget;

import android.view.animation.*;

class MaterialProgressDrawable$1 extends Animation {
    final /* synthetic */ Ring val$ring;
    
    public void applyTransformation(final float n, final Transformation transformation) {
        if (MaterialProgressDrawable.this.mFinishing) {
            MaterialProgressDrawable.access$000(MaterialProgressDrawable.this, n, this.val$ring);
            return;
        }
        final float access$100 = MaterialProgressDrawable.access$100(MaterialProgressDrawable.this, this.val$ring);
        final float startingEndTrim = this.val$ring.getStartingEndTrim();
        final float startingStartTrim = this.val$ring.getStartingStartTrim();
        final float startingRotation = this.val$ring.getStartingRotation();
        MaterialProgressDrawable.access$200(MaterialProgressDrawable.this, n, this.val$ring);
        if (n <= 0.5f) {
            this.val$ring.setStartTrim(startingStartTrim + (0.8f - access$100) * MaterialProgressDrawable.access$300().getInterpolation(n / 0.5f));
        }
        if (n > 0.5f) {
            this.val$ring.setEndTrim(startingEndTrim + (0.8f - access$100) * MaterialProgressDrawable.access$300().getInterpolation((n - 0.5f) / 0.5f));
        }
        this.val$ring.setRotation(startingRotation + 0.25f * n);
        MaterialProgressDrawable.this.setRotation(216.0f * n + 1080.0f * (MaterialProgressDrawable.access$400(MaterialProgressDrawable.this) / 5.0f));
    }
}