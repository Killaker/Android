package android.support.v7.widget;

import android.support.v4.util.*;
import android.support.annotation.*;

static class InfoRecord
{
    static final int FLAG_APPEAR = 2;
    static final int FLAG_APPEAR_AND_DISAPPEAR = 3;
    static final int FLAG_APPEAR_PRE_AND_POST = 14;
    static final int FLAG_DISAPPEARED = 1;
    static final int FLAG_POST = 8;
    static final int FLAG_PRE = 4;
    static final int FLAG_PRE_AND_POST = 12;
    static Pools.Pool<InfoRecord> sPool;
    int flags;
    @Nullable
    RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
    @Nullable
    RecyclerView.ItemAnimator.ItemHolderInfo preInfo;
    
    static {
        InfoRecord.sPool = new Pools.SimplePool<InfoRecord>(20);
    }
    
    static void drainCache() {
        while (InfoRecord.sPool.acquire() != null) {}
    }
    
    static InfoRecord obtain() {
        InfoRecord infoRecord = InfoRecord.sPool.acquire();
        if (infoRecord == null) {
            infoRecord = new InfoRecord();
        }
        return infoRecord;
    }
    
    static void recycle(final InfoRecord infoRecord) {
        infoRecord.flags = 0;
        infoRecord.preInfo = null;
        infoRecord.postInfo = null;
        InfoRecord.sPool.release(infoRecord);
    }
}
