package android.support.v4.app;

import android.os.*;

public static final class WearableExtender implements Extender
{
    private static final int DEFAULT_FLAGS = 1;
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    private static final int FLAG_AVAILABLE_OFFLINE = 1;
    private static final String KEY_CANCEL_LABEL = "cancelLabel";
    private static final String KEY_CONFIRM_LABEL = "confirmLabel";
    private static final String KEY_FLAGS = "flags";
    private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    private CharSequence mCancelLabel;
    private CharSequence mConfirmLabel;
    private int mFlags;
    private CharSequence mInProgressLabel;
    
    public WearableExtender() {
        this.mFlags = 1;
    }
    
    public WearableExtender(final Action action) {
        this.mFlags = 1;
        final Bundle bundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
        if (bundle != null) {
            this.mFlags = bundle.getInt("flags", 1);
            this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
            this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
            this.mCancelLabel = bundle.getCharSequence("cancelLabel");
        }
    }
    
    private void setFlag(final int n, final boolean b) {
        if (b) {
            this.mFlags |= n;
            return;
        }
        this.mFlags &= ~n;
    }
    
    public WearableExtender clone() {
        final WearableExtender wearableExtender = new WearableExtender();
        wearableExtender.mFlags = this.mFlags;
        wearableExtender.mInProgressLabel = this.mInProgressLabel;
        wearableExtender.mConfirmLabel = this.mConfirmLabel;
        wearableExtender.mCancelLabel = this.mCancelLabel;
        return wearableExtender;
    }
    
    @Override
    public Builder extend(final Builder builder) {
        final Bundle bundle = new Bundle();
        if (this.mFlags != 1) {
            bundle.putInt("flags", this.mFlags);
        }
        if (this.mInProgressLabel != null) {
            bundle.putCharSequence("inProgressLabel", this.mInProgressLabel);
        }
        if (this.mConfirmLabel != null) {
            bundle.putCharSequence("confirmLabel", this.mConfirmLabel);
        }
        if (this.mCancelLabel != null) {
            bundle.putCharSequence("cancelLabel", this.mCancelLabel);
        }
        builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
        return builder;
    }
    
    public CharSequence getCancelLabel() {
        return this.mCancelLabel;
    }
    
    public CharSequence getConfirmLabel() {
        return this.mConfirmLabel;
    }
    
    public CharSequence getInProgressLabel() {
        return this.mInProgressLabel;
    }
    
    public boolean isAvailableOffline() {
        return (0x1 & this.mFlags) != 0x0;
    }
    
    public WearableExtender setAvailableOffline(final boolean b) {
        this.setFlag(1, b);
        return this;
    }
    
    public WearableExtender setCancelLabel(final CharSequence mCancelLabel) {
        this.mCancelLabel = mCancelLabel;
        return this;
    }
    
    public WearableExtender setConfirmLabel(final CharSequence mConfirmLabel) {
        this.mConfirmLabel = mConfirmLabel;
        return this;
    }
    
    public WearableExtender setInProgressLabel(final CharSequence mInProgressLabel) {
        this.mInProgressLabel = mInProgressLabel;
        return this;
    }
}
