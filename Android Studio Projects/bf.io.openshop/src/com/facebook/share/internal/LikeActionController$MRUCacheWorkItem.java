package com.facebook.share.internal;

import java.util.*;

private static class MRUCacheWorkItem implements Runnable
{
    private static ArrayList<String> mruCachedItems;
    private String cacheItem;
    private boolean shouldTrim;
    
    static {
        MRUCacheWorkItem.mruCachedItems = new ArrayList<String>();
    }
    
    MRUCacheWorkItem(final String cacheItem, final boolean shouldTrim) {
        this.cacheItem = cacheItem;
        this.shouldTrim = shouldTrim;
    }
    
    @Override
    public void run() {
        if (this.cacheItem != null) {
            MRUCacheWorkItem.mruCachedItems.remove(this.cacheItem);
            MRUCacheWorkItem.mruCachedItems.add(0, this.cacheItem);
        }
        if (this.shouldTrim && MRUCacheWorkItem.mruCachedItems.size() >= 128) {
            while (64 < MRUCacheWorkItem.mruCachedItems.size()) {
                LikeActionController.access$400().remove(MRUCacheWorkItem.mruCachedItems.remove(-1 + MRUCacheWorkItem.mruCachedItems.size()));
            }
        }
    }
}
