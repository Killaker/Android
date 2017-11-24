package bf.io.openshop.ux.fragments;

import android.view.*;
import timber.log.*;

class ProductFragment$6 implements ViewTreeObserver$OnScrollChangedListener {
    private boolean alphaFull = false;
    final /* synthetic */ View val$productBackground;
    
    public void onScrollChanged() {
        final int scrollY = ProductFragment.access$500(ProductFragment.this).getScrollY();
        if (ProductFragment.access$600(ProductFragment.this) == null) {
            Timber.e("Null productImagesScroll", new Object[0]);
            return;
        }
        if (2 * ProductFragment.access$700(ProductFragment.this).getWidth() > scrollY) {
            ProductFragment.access$700(ProductFragment.this).setTranslationX((float)(scrollY / 4));
        }
        else {
            ProductFragment.access$700(ProductFragment.this).setTranslationX((float)(ProductFragment.access$700(ProductFragment.this).getWidth() / 2));
        }
        float alpha;
        if (ProductFragment.access$600(ProductFragment.this).getHeight() > scrollY) {
            ProductFragment.access$600(ProductFragment.this).setTranslationY((float)(scrollY / 2));
            alpha = scrollY / ProductFragment.access$600(ProductFragment.this).getHeight();
        }
        else {
            alpha = 1.0f;
        }
        if (this.alphaFull) {
            if (alpha <= 0.99) {
                this.alphaFull = false;
            }
            return;
        }
        if (alpha >= 0.9) {
            this.alphaFull = true;
        }
        this.val$productBackground.setAlpha(alpha);
    }
}