package android.support.v7.widget;

import android.view.*;
import java.util.*;
import android.support.v4.view.*;
import android.util.*;

public abstract static class ViewHolder
{
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    static final int FLAG_BOUND = 1;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_MOVED = 2048;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List<Object> FULLUPDATE_PAYLOADS;
    public final View itemView;
    private int mFlags;
    private boolean mInChangeScrap;
    private int mIsRecyclableCount;
    long mItemId;
    int mItemViewType;
    int mOldPosition;
    RecyclerView mOwnerRecyclerView;
    List<Object> mPayloads;
    int mPosition;
    int mPreLayoutPosition;
    private Recycler mScrapContainer;
    ViewHolder mShadowedHolder;
    ViewHolder mShadowingHolder;
    List<Object> mUnmodifiedPayloads;
    private int mWasImportantForAccessibilityBeforeHidden;
    
    static {
        FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
    }
    
    public ViewHolder(final View itemView) {
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mItemViewType = -1;
        this.mPreLayoutPosition = -1;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        this.mPayloads = null;
        this.mUnmodifiedPayloads = null;
        this.mIsRecyclableCount = 0;
        this.mScrapContainer = null;
        this.mInChangeScrap = false;
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = itemView;
    }
    
    private void createPayloadsIfNeeded() {
        if (this.mPayloads == null) {
            this.mPayloads = new ArrayList<Object>();
            this.mUnmodifiedPayloads = Collections.unmodifiableList((List<?>)this.mPayloads);
        }
    }
    
    private boolean doesTransientStatePreventRecycling() {
        return (0x10 & this.mFlags) == 0x0 && ViewCompat.hasTransientState(this.itemView);
    }
    
    private void onEnteredHiddenState() {
        this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
        ViewCompat.setImportantForAccessibility(this.itemView, 4);
    }
    
    private void onLeftHiddenState() {
        ViewCompat.setImportantForAccessibility(this.itemView, this.mWasImportantForAccessibilityBeforeHidden);
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }
    
    private boolean shouldBeKeptAsChild() {
        return (0x10 & this.mFlags) != 0x0;
    }
    
    void addChangePayload(final Object o) {
        if (o == null) {
            this.addFlags(1024);
        }
        else if ((0x400 & this.mFlags) == 0x0) {
            this.createPayloadsIfNeeded();
            this.mPayloads.add(o);
        }
    }
    
    void addFlags(final int n) {
        this.mFlags |= n;
    }
    
    void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }
    
    void clearPayload() {
        if (this.mPayloads != null) {
            this.mPayloads.clear();
        }
        this.mFlags &= 0xFFFFFBFF;
    }
    
    void clearReturnedFromScrapFlag() {
        this.mFlags &= 0xFFFFFFDF;
    }
    
    void clearTmpDetachFlag() {
        this.mFlags &= 0xFFFFFEFF;
    }
    
    void flagRemovedAndOffsetPosition(final int mPosition, final int n, final boolean b) {
        this.addFlags(8);
        this.offsetPosition(n, b);
        this.mPosition = mPosition;
    }
    
    public final int getAdapterPosition() {
        if (this.mOwnerRecyclerView == null) {
            return -1;
        }
        return RecyclerView.access$5700(this.mOwnerRecyclerView, this);
    }
    
    public final long getItemId() {
        return this.mItemId;
    }
    
    public final int getItemViewType() {
        return this.mItemViewType;
    }
    
    public final int getLayoutPosition() {
        if (this.mPreLayoutPosition == -1) {
            return this.mPosition;
        }
        return this.mPreLayoutPosition;
    }
    
    public final int getOldPosition() {
        return this.mOldPosition;
    }
    
    @Deprecated
    public final int getPosition() {
        if (this.mPreLayoutPosition == -1) {
            return this.mPosition;
        }
        return this.mPreLayoutPosition;
    }
    
    List<Object> getUnmodifiedPayloads() {
        if ((0x400 & this.mFlags) != 0x0) {
            return ViewHolder.FULLUPDATE_PAYLOADS;
        }
        if (this.mPayloads == null || this.mPayloads.size() == 0) {
            return ViewHolder.FULLUPDATE_PAYLOADS;
        }
        return this.mUnmodifiedPayloads;
    }
    
    boolean hasAnyOfTheFlags(final int n) {
        return (n & this.mFlags) != 0x0;
    }
    
    boolean isAdapterPositionUnknown() {
        return (0x200 & this.mFlags) != 0x0 || this.isInvalid();
    }
    
    boolean isBound() {
        return (0x1 & this.mFlags) != 0x0;
    }
    
    boolean isInvalid() {
        return (0x4 & this.mFlags) != 0x0;
    }
    
    public final boolean isRecyclable() {
        return (0x10 & this.mFlags) == 0x0 && !ViewCompat.hasTransientState(this.itemView);
    }
    
    boolean isRemoved() {
        return (0x8 & this.mFlags) != 0x0;
    }
    
    boolean isScrap() {
        return this.mScrapContainer != null;
    }
    
    boolean isTmpDetached() {
        return (0x100 & this.mFlags) != 0x0;
    }
    
    boolean isUpdated() {
        return (0x2 & this.mFlags) != 0x0;
    }
    
    boolean needsUpdate() {
        return (0x2 & this.mFlags) != 0x0;
    }
    
    void offsetPosition(final int n, final boolean b) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (b) {
            this.mPreLayoutPosition += n;
        }
        this.mPosition += n;
        if (this.itemView.getLayoutParams() != null) {
            ((LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true;
        }
    }
    
    void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        this.clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }
    
    void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }
    
    void setFlags(final int n, final int n2) {
        this.mFlags = ((this.mFlags & ~n2) | (n & n2));
    }
    
    public final void setIsRecyclable(final boolean b) {
        int mIsRecyclableCount;
        if (b) {
            mIsRecyclableCount = -1 + this.mIsRecyclableCount;
        }
        else {
            mIsRecyclableCount = 1 + this.mIsRecyclableCount;
        }
        this.mIsRecyclableCount = mIsRecyclableCount;
        if (this.mIsRecyclableCount < 0) {
            this.mIsRecyclableCount = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
        }
        else {
            if (!b && this.mIsRecyclableCount == 1) {
                this.mFlags |= 0x10;
                return;
            }
            if (b && this.mIsRecyclableCount == 0) {
                this.mFlags &= 0xFFFFFFEF;
            }
        }
    }
    
    void setScrapContainer(final Recycler mScrapContainer, final boolean mInChangeScrap) {
        this.mScrapContainer = mScrapContainer;
        this.mInChangeScrap = mInChangeScrap;
    }
    
    boolean shouldIgnore() {
        return (0x80 & this.mFlags) != 0x0;
    }
    
    void stopIgnoring() {
        this.mFlags &= 0xFFFFFF7F;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(this.hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
        if (this.isScrap()) {
            final StringBuilder append = sb.append(" scrap ");
            String s;
            if (this.mInChangeScrap) {
                s = "[changeScrap]";
            }
            else {
                s = "[attachedScrap]";
            }
            append.append(s);
        }
        if (this.isInvalid()) {
            sb.append(" invalid");
        }
        if (!this.isBound()) {
            sb.append(" unbound");
        }
        if (this.needsUpdate()) {
            sb.append(" update");
        }
        if (this.isRemoved()) {
            sb.append(" removed");
        }
        if (this.shouldIgnore()) {
            sb.append(" ignored");
        }
        if (this.isTmpDetached()) {
            sb.append(" tmpDetached");
        }
        if (!this.isRecyclable()) {
            sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
        }
        if (this.isAdapterPositionUnknown()) {
            sb.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }
    
    void unScrap() {
        this.mScrapContainer.unscrapView(this);
    }
    
    boolean wasReturnedFromScrap() {
        return (0x20 & this.mFlags) != 0x0;
    }
}
