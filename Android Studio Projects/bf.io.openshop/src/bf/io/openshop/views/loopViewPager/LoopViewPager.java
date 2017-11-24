package bf.io.openshop.views.loopViewPager;

import android.content.*;
import android.util.*;
import android.support.v4.view.*;

public class LoopViewPager extends ViewPager
{
    private static final boolean DEFAULT_BOUNDARY_CASHING;
    private LoopPagerAdapterWrapper mAdapter;
    private boolean mBoundaryCaching;
    OnPageChangeListener mOuterPageChangeListener;
    private OnPageChangeListener onPageChangeListener;
    
    public LoopViewPager(final Context context) {
        super(context);
        this.mBoundaryCaching = false;
        this.onPageChangeListener = new OnPageChangeListener() {
            private float mPreviousOffset;
            private float mPreviousPosition;
            
            {
                this.mPreviousOffset = -1.0f;
                this.mPreviousPosition = -1.0f;
            }
            
            @Override
            public void onPageScrollStateChanged(final int n) {
                if (LoopViewPager.this.mAdapter != null) {
                    final int access$101 = LoopViewPager.this.getCurrentItem();
                    final int realPosition = LoopViewPager.this.mAdapter.toRealPosition(access$101);
                    if (n == 0 && (access$101 == 0 || access$101 == -1 + LoopViewPager.this.mAdapter.getCount())) {
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
                if (LoopViewPager.this.mAdapter != null) {
                    realPosition = LoopViewPager.this.mAdapter.toRealPosition(n);
                    if (mPreviousOffset == 0.0f && this.mPreviousOffset == 0.0f && (n == 0 || n == -1 + LoopViewPager.this.mAdapter.getCount())) {
                        LoopViewPager.this.setCurrentItem(realPosition, false);
                    }
                }
                this.mPreviousOffset = mPreviousOffset;
                if (LoopViewPager.this.mOuterPageChangeListener != null) {
                    if (realPosition != -1 + LoopViewPager.this.mAdapter.getRealCount()) {
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
                final int realPosition = LoopViewPager.this.mAdapter.toRealPosition(n);
                if (this.mPreviousPosition != realPosition) {
                    this.mPreviousPosition = realPosition;
                    if (LoopViewPager.this.mOuterPageChangeListener != null) {
                        LoopViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                    }
                }
            }
        };
        this.init();
    }
    
    public LoopViewPager(final Context context, final AttributeSet set) {
        super(context, set);
        this.mBoundaryCaching = false;
        this.onPageChangeListener = new OnPageChangeListener() {
            private float mPreviousOffset = -1.0f;
            private float mPreviousPosition = -1.0f;
            
            @Override
            public void onPageScrollStateChanged(final int n) {
                if (LoopViewPager.this.mAdapter != null) {
                    final int access$101 = LoopViewPager.this.getCurrentItem();
                    final int realPosition = LoopViewPager.this.mAdapter.toRealPosition(access$101);
                    if (n == 0 && (access$101 == 0 || access$101 == -1 + LoopViewPager.this.mAdapter.getCount())) {
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
                if (LoopViewPager.this.mAdapter != null) {
                    realPosition = LoopViewPager.this.mAdapter.toRealPosition(n);
                    if (mPreviousOffset == 0.0f && this.mPreviousOffset == 0.0f && (n == 0 || n == -1 + LoopViewPager.this.mAdapter.getCount())) {
                        LoopViewPager.this.setCurrentItem(realPosition, false);
                    }
                }
                this.mPreviousOffset = mPreviousOffset;
                if (LoopViewPager.this.mOuterPageChangeListener != null) {
                    if (realPosition != -1 + LoopViewPager.this.mAdapter.getRealCount()) {
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
                final int realPosition = LoopViewPager.this.mAdapter.toRealPosition(n);
                if (this.mPreviousPosition != realPosition) {
                    this.mPreviousPosition = realPosition;
                    if (LoopViewPager.this.mOuterPageChangeListener != null) {
                        LoopViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                    }
                }
            }
        };
        this.init();
    }
    
    private void init() {
        super.setOnPageChangeListener(this.onPageChangeListener);
    }
    
    public static int toRealPosition(final int n, final int n2) {
        final int n3 = n - 1;
        if (n3 < 0) {
            return n3 + n2;
        }
        return n3 % n2;
    }
    
    @Override
    public PagerAdapter getAdapter() {
        if (this.mAdapter != null) {
            return this.mAdapter.getRealAdapter();
        }
        return this.mAdapter;
    }
    
    @Override
    public int getCurrentItem() {
        if (this.mAdapter != null) {
            return this.mAdapter.toRealPosition(super.getCurrentItem());
        }
        return 0;
    }
    
    @Override
    public void setAdapter(final PagerAdapter pagerAdapter) {
        (this.mAdapter = new LoopPagerAdapterWrapper(pagerAdapter)).setBoundaryCaching(this.mBoundaryCaching);
        super.setAdapter(this.mAdapter);
        this.setCurrentItem(0, false);
    }
    
    public void setBoundaryCaching(final boolean b) {
        this.mBoundaryCaching = b;
        if (this.mAdapter != null) {
            this.mAdapter.setBoundaryCaching(b);
        }
    }
    
    @Override
    public void setCurrentItem(final int n) {
        if (this.getCurrentItem() != n) {
            this.setCurrentItem(n, true);
        }
    }
    
    @Override
    public void setCurrentItem(final int n, final boolean b) {
        super.setCurrentItem(this.mAdapter.toInnerPosition(n), b);
    }
    
    @Override
    public void setOnPageChangeListener(final OnPageChangeListener mOuterPageChangeListener) {
        this.mOuterPageChangeListener = mOuterPageChangeListener;
    }
}
