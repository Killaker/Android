package bf.io.openshop.views.loopViewPager;

import android.support.v4.view.*;
import android.util.*;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;

public class LoopPagerAdapterWrapper extends PagerAdapter
{
    private PagerAdapter mAdapter;
    private boolean mBoundaryCaching;
    private SparseArray<ToDestroy> mToDestroy;
    
    LoopPagerAdapterWrapper(final PagerAdapter mAdapter) {
        this.mToDestroy = (SparseArray<ToDestroy>)new SparseArray();
        this.mAdapter = mAdapter;
    }
    
    private int getRealFirstPosition() {
        return 1;
    }
    
    private int getRealLastPosition() {
        return -1 + (this.getRealFirstPosition() + this.getRealCount());
    }
    
    @Override
    public void destroyItem(final ViewGroup viewGroup, final int n, final Object o) {
        final int realFirstPosition = this.getRealFirstPosition();
        final int realLastPosition = this.getRealLastPosition();
        int realPosition;
        if (this.mAdapter instanceof FragmentPagerAdapter || this.mAdapter instanceof FragmentStatePagerAdapter) {
            realPosition = n;
        }
        else {
            realPosition = this.toRealPosition(n);
        }
        if (this.mBoundaryCaching && (n == realFirstPosition || n == realLastPosition)) {
            this.mToDestroy.put(n, (Object)new ToDestroy(viewGroup, realPosition, o));
            return;
        }
        this.mAdapter.destroyItem(viewGroup, realPosition, o);
    }
    
    @Override
    public void finishUpdate(final ViewGroup viewGroup) {
        this.mAdapter.finishUpdate(viewGroup);
    }
    
    @Override
    public int getCount() {
        return 2 + this.mAdapter.getCount();
    }
    
    public PagerAdapter getRealAdapter() {
        return this.mAdapter;
    }
    
    public int getRealCount() {
        return this.mAdapter.getCount();
    }
    
    @Override
    public Object instantiateItem(final ViewGroup viewGroup, final int n) {
        int realPosition;
        if (this.mAdapter instanceof FragmentPagerAdapter || this.mAdapter instanceof FragmentStatePagerAdapter) {
            realPosition = n;
        }
        else {
            realPosition = this.toRealPosition(n);
        }
        if (this.mBoundaryCaching) {
            final ToDestroy toDestroy = (ToDestroy)this.mToDestroy.get(n);
            if (toDestroy != null) {
                this.mToDestroy.remove(n);
                return toDestroy.object;
            }
        }
        return this.mAdapter.instantiateItem(viewGroup, realPosition);
    }
    
    @Override
    public boolean isViewFromObject(final View view, final Object o) {
        return this.mAdapter.isViewFromObject(view, o);
    }
    
    @Override
    public void notifyDataSetChanged() {
        this.mToDestroy = (SparseArray<ToDestroy>)new SparseArray();
        super.notifyDataSetChanged();
    }
    
    @Override
    public void restoreState(final Parcelable parcelable, final ClassLoader classLoader) {
        this.mAdapter.restoreState(parcelable, classLoader);
    }
    
    @Override
    public Parcelable saveState() {
        return this.mAdapter.saveState();
    }
    
    void setBoundaryCaching(final boolean mBoundaryCaching) {
        this.mBoundaryCaching = mBoundaryCaching;
    }
    
    @Override
    public void setPrimaryItem(final ViewGroup viewGroup, final int n, final Object o) {
        this.mAdapter.setPrimaryItem(viewGroup, n, o);
    }
    
    @Override
    public void startUpdate(final ViewGroup viewGroup) {
        this.mAdapter.startUpdate(viewGroup);
    }
    
    public int toInnerPosition(final int n) {
        return n + 1;
    }
    
    int toRealPosition(final int n) {
        final int realCount = this.getRealCount();
        int n2;
        if (realCount == 0) {
            n2 = 0;
        }
        else {
            n2 = (n - 1) % realCount;
            if (n2 < 0) {
                return n2 + realCount;
            }
        }
        return n2;
    }
    
    static class ToDestroy
    {
        ViewGroup container;
        Object object;
        int position;
        
        public ToDestroy(final ViewGroup container, final int position, final Object object) {
            this.container = container;
            this.position = position;
            this.object = object;
        }
    }
}
