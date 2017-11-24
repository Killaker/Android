package android.support.v4.media;

import android.os.*;
import android.support.annotation.*;

public static final class BrowserRoot
{
    public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
    public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
    public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
    private final Bundle mExtras;
    private final String mRootId;
    
    public BrowserRoot(@NonNull final String mRootId, @Nullable final Bundle mExtras) {
        if (mRootId == null) {
            throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
        }
        this.mRootId = mRootId;
        this.mExtras = mExtras;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public String getRootId() {
        return this.mRootId;
    }
}
