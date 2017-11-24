package android.support.v7.util;

import android.util.*;

class AsyncListUtil$1 implements MainThreadCallback<T> {
    private boolean isRequestedGeneration(final int n) {
        return n == AsyncListUtil.this.mRequestedGeneration;
    }
    
    private void recycleAllTiles() {
        for (int i = 0; i < AsyncListUtil.this.mTileList.size(); ++i) {
            AsyncListUtil.this.mBackgroundProxy.recycleTile(AsyncListUtil.this.mTileList.getAtIndex(i));
        }
        AsyncListUtil.this.mTileList.clear();
    }
    
    @Override
    public void addTile(final int n, final TileList.Tile<T> tile) {
        if (!this.isRequestedGeneration(n)) {
            AsyncListUtil.this.mBackgroundProxy.recycleTile(tile);
        }
        else {
            final TileList.Tile<T> addOrReplace = AsyncListUtil.this.mTileList.addOrReplace(tile);
            if (addOrReplace != null) {
                Log.e("AsyncListUtil", "duplicate tile @" + addOrReplace.mStartPosition);
                AsyncListUtil.this.mBackgroundProxy.recycleTile(addOrReplace);
            }
            final int n2 = tile.mStartPosition + tile.mItemCount;
            int i = 0;
            while (i < AsyncListUtil.access$300(AsyncListUtil.this).size()) {
                final int key = AsyncListUtil.access$300(AsyncListUtil.this).keyAt(i);
                if (tile.mStartPosition <= key && key < n2) {
                    AsyncListUtil.access$300(AsyncListUtil.this).removeAt(i);
                    AsyncListUtil.this.mViewCallback.onItemLoaded(key);
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    @Override
    public void removeTile(final int n, final int n2) {
        if (!this.isRequestedGeneration(n)) {
            return;
        }
        final TileList.Tile<T> removeAtPos = AsyncListUtil.this.mTileList.removeAtPos(n2);
        if (removeAtPos == null) {
            Log.e("AsyncListUtil", "tile not found @" + n2);
            return;
        }
        AsyncListUtil.this.mBackgroundProxy.recycleTile(removeAtPos);
    }
    
    @Override
    public void updateItemCount(final int n, final int n2) {
        if (!this.isRequestedGeneration(n)) {
            return;
        }
        AsyncListUtil.access$002(AsyncListUtil.this, n2);
        AsyncListUtil.this.mViewCallback.onDataRefresh();
        AsyncListUtil.this.mDisplayedGeneration = AsyncListUtil.this.mRequestedGeneration;
        this.recycleAllTiles();
        AsyncListUtil.access$102(AsyncListUtil.this, false);
        AsyncListUtil.access$200(AsyncListUtil.this);
    }
}