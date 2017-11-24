package bf.io.openshop.views.loopViewPager;

import android.support.v4.view.*;

class LoopViewPager$1 implements OnPageChangeListener {
    private float mPreviousOffset = -1.0f;
    private float mPreviousPosition = -1.0f;
    
    @Override
    public void onPageScrollStateChanged(final int n) {
        if (LoopViewPager.access$000(LoopViewPager.this) != null) {
            final int access$101 = LoopViewPager.access$101(LoopViewPager.this);
            final int realPosition = LoopViewPager.access$000(LoopViewPager.this).toRealPosition(access$101);
            if (n == 0 && (access$101 == 0 || access$101 == -1 + LoopViewPager.access$000(LoopViewPager.this).getCount())) {
                LoopViewPager.this.setCurrentItem(realPosition, false);
            }
        }
        if (LoopViewPager.this.mOuterPageChangeListener != null) {
            LoopViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(n);
        }
    }
    
    @Override
    public void onPageScrolled(final int n, final float mPreviousOffset, final int n2) {
        int realPosition = n;
        if (LoopViewPager.access$000(LoopViewPager.this) != null) {
            realPosition = LoopViewPager.access$000(LoopViewPager.this).toRealPosition(n);
            if (mPreviousOffset == 0.0f && this.mPreviousOffset == 0.0f && (n == 0 || n == -1 + LoopViewPager.access$000(LoopViewPager.this).getCount())) {
                LoopViewPager.this.setCurrentItem(realPosition, false);
            }
        }
        this.mPreviousOffset = mPreviousOffset;
        if (LoopViewPager.this.mOuterPageChangeListener != null) {
            if (realPosition != -1 + LoopViewPager.access$000(LoopViewPager.this).getRealCount()) {
                LoopViewPager.this.mOuterPageChangeListener.onPageScrolled(realPosition, mPreviousOffset, n2);
            }
            else {
                if (mPreviousOffset > 0.5) {
                    LoopViewPager.this.mOuterPageChangeListener.onPageScrolled(0, 0.0f, 0);
                    return;
                }
                LoopViewPager.this.mOuterPageChangeListener.onPageScrolled(realPosition, 0.0f, 0);
            }
        }
    }
    
    @Override
    public void onPageSelected(final int n) {
        final int realPosition = LoopViewPager.access$000(LoopViewPager.this).toRealPosition(n);
        if (this.mPreviousPosition != realPosition) {
            this.mPreviousPosition = realPosition;
            if (LoopViewPager.this.mOuterPageChangeListener != null) {
                LoopViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
            }
        }
    }
}