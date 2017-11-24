package android.support.v7.widget;

import android.view.*;
import java.util.*;
import android.support.v4.view.*;
import android.util.*;

class RecyclerView$5 implements Callback {
    @Override
    public void addView(final View view, final int n) {
        RecyclerView.this.addView(view, n);
        RecyclerView.access$1100(RecyclerView.this, view);
    }
    
    @Override
    public void attachViewToParent(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt);
            }
            childViewHolderInt.clearTmpDetachFlag();
        }
        RecyclerView.access$1300(RecyclerView.this, view, n, viewGroup$LayoutParams);
    }
    
    @Override
    public void detachViewFromParent(final int n) {
        final View child = this.getChildAt(n);
        if (child != null) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(child);
            if (childViewHolderInt != null) {
                if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt);
                }
                childViewHolderInt.addFlags(256);
            }
        }
        RecyclerView.access$1400(RecyclerView.this, n);
    }
    
    @Override
    public View getChildAt(final int n) {
        return RecyclerView.this.getChildAt(n);
    }
    
    @Override
    public int getChildCount() {
        return RecyclerView.this.getChildCount();
    }
    
    @Override
    public ViewHolder getChildViewHolder(final View view) {
        return RecyclerView.getChildViewHolderInt(view);
    }
    
    @Override
    public int indexOfChild(final View view) {
        return RecyclerView.this.indexOfChild(view);
    }
    
    @Override
    public void onEnteredHiddenState(final View view) {
        final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onEnteredHiddenState();
        }
    }
    
    @Override
    public void onLeftHiddenState(final View view) {
        final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onLeftHiddenState();
        }
    }
    
    @Override
    public void removeAllViews() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            RecyclerView.access$1200(RecyclerView.this, this.getChildAt(i));
        }
        RecyclerView.this.removeAllViews();
    }
    
    @Override
    public void removeViewAt(final int n) {
        final View child = RecyclerView.this.getChildAt(n);
        if (child != null) {
            RecyclerView.access$1200(RecyclerView.this, child);
        }
        RecyclerView.this.removeViewAt(n);
    }
}