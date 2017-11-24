package android.support.v7.util;

import android.util.*;

class AsyncListUtil$2 implements BackgroundCallback<T> {
    private int mFirstRequiredTileStart;
    private int mGeneration;
    private int mItemCount;
    private int mLastRequiredTileStart;
    final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
    private TileList.Tile<T> mRecycledRoot;
    
    private TileList.Tile<T> acquireTile() {
        if (this.mRecycledRoot != null) {
            final TileList.Tile<T> mRecycledRoot = this.mRecycledRoot;
            this.mRecycledRoot = this.mRecycledRoot.mNext;
            return mRecycledRoot;
        }
        return (TileList.Tile<T>)new TileList.Tile((Class<Object>)AsyncListUtil.this.mTClass, AsyncListUtil.this.mTileSize);
    }
    
    private void addTile(final TileList.Tile<T> tile) {
        this.mLoadedTiles.put(tile.mStartPosition, true);
        AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, tile);
    }
    
    private void flushTileCache(final int n) {
        while (this.mLoadedTiles.size() >= AsyncListUtil.this.mDataCallback.getMaxCachedTiles()) {
            final int key = this.mLoadedTiles.keyAt(0);
            final int key2 = this.mLoadedTiles.keyAt(-1 + this.mLoadedTiles.size());
            final int n2 = this.mFirstRequiredTileStart - key;
            final int n3 = key2 - this.mLastRequiredTileStart;
            if (n2 > 0 && (n2 >= n3 || n == 2)) {
                this.removeTile(key);
            }
            else {
                if (n3 <= 0 || (n2 >= n3 && n != 1)) {
                    break;
                }
                this.removeTile(key2);
            }
        }
    }
    
    private int getTileStart(final int n) {
        return n - n % AsyncListUtil.this.mTileSize;
    }
    
    private boolean isTileLoaded(final int n) {
        return this.mLoadedTiles.get(n);
    }
    
    private void log(final String s, final Object... array) {
        Log.d("AsyncListUtil", "[BKGR] " + String.format(s, array));
    }
    
    private void removeTile(final int n) {
        this.mLoadedTiles.delete(n);
        AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, n);
    }
    
    private void requestTiles(final int n, final int n2, final int n3, final boolean b) {
        for (int i = n; i <= n2; i += AsyncListUtil.this.mTileSize) {
            int n4;
            if (b) {
                n4 = n2 + n - i;
            }
            else {
                n4 = i;
            }
            AsyncListUtil.this.mBackgroundProxy.loadTile(n4, n3);
        }
    }
    
    @Override
    public void loadTile(final int mStartPosition, final int n) {
        if (this.isTileLoaded(mStartPosition)) {
            return;
        }
        final TileList.Tile<T> acquireTile = this.acquireTile();
        acquireTile.mStartPosition = mStartPosition;
        acquireTile.mItemCount = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - acquireTile.mStartPosition);
        AsyncListUtil.this.mDataCallback.fillData((T[])acquireTile.mItems, acquireTile.mStartPosition, acquireTile.mItemCount);
        this.flushTileCache(n);
        this.addTile(acquireTile);
    }
    
    @Override
    public void recycleTile(final TileList.Tile<T> mRecycledRoot) {
        AsyncListUtil.this.mDataCallback.recycleData((T[])mRecycledRoot.mItems, mRecycledRoot.mItemCount);
        mRecycledRoot.mNext = this.mRecycledRoot;
        this.mRecycledRoot = mRecycledRoot;
    }
    
    @Override
    public void refresh(final int mGeneration) {
        this.mGeneration = mGeneration;
        this.mLoadedTiles.clear();
        this.mItemCount = AsyncListUtil.this.mDataCallback.refreshData();
        AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
    }
    
    @Override
    public void updateRange(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n > n2) {
            return;
        }
        final int tileStart = this.getTileStart(n);
        final int tileStart2 = this.getTileStart(n2);
        this.mFirstRequiredTileStart = this.getTileStart(n3);
        this.mLastRequiredTileStart = this.getTileStart(n4);
        if (n5 == 1) {
            this.requestTiles(this.mFirstRequiredTileStart, tileStart2, n5, true);
            this.requestTiles(tileStart2 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, n5, false);
            return;
        }
        this.requestTiles(tileStart, this.mLastRequiredTileStart, n5, false);
        this.requestTiles(this.mFirstRequiredTileStart, tileStart - AsyncListUtil.this.mTileSize, n5, true);
    }
}