package android.support.v4.app;

import android.app.*;
import android.os.*;
import java.util.*;

public static class Action extends NotificationCompatBase.Action
{
    public static final Factory FACTORY;
    public PendingIntent actionIntent;
    public int icon;
    private final Bundle mExtras;
    private final RemoteInput[] mRemoteInputs;
    public CharSequence title;
    
    static {
        FACTORY = new Factory() {
            public NotificationCompat.Action build(final int n, final CharSequence charSequence, final PendingIntent pendingIntent, final Bundle bundle, final RemoteInputCompatBase.RemoteInput[] array) {
                return new NotificationCompat.Action(n, charSequence, pendingIntent, bundle, (RemoteInput[])array);
            }
            
            public NotificationCompat.Action[] newArray(final int n) {
                return new NotificationCompat.Action[n];
            }
        };
    }
    
    public Action(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
        this(n, charSequence, pendingIntent, new Bundle(), null);
    }
    
    private Action(final int icon, final CharSequence charSequence, final PendingIntent actionIntent, Bundle mExtras, final RemoteInput[] mRemoteInputs) {
        this.icon = icon;
        this.title = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
        this.actionIntent = actionIntent;
        if (mExtras == null) {
            mExtras = new Bundle();
        }
        this.mExtras = mExtras;
        this.mRemoteInputs = mRemoteInputs;
    }
    
    @Override
    public PendingIntent getActionIntent() {
        return this.actionIntent;
    }
    
    @Override
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    @Override
    public int getIcon() {
        return this.icon;
    }
    
    public RemoteInput[] getRemoteInputs() {
        return this.mRemoteInputs;
    }
    
    @Override
    public CharSequence getTitle() {
        return this.title;
    }
    
    public static final class Builder
    {
        private final Bundle mExtras;
        private final int mIcon;
        private final PendingIntent mIntent;
        private ArrayList<RemoteInput> mRemoteInputs;
        private final CharSequence mTitle;
        
        public Builder(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
            this(n, charSequence, pendingIntent, new Bundle());
        }
        
        private Builder(final int mIcon, final CharSequence charSequence, final PendingIntent mIntent, final Bundle mExtras) {
            this.mIcon = mIcon;
            this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.mIntent = mIntent;
            this.mExtras = mExtras;
        }
        
        public Builder(final Action action) {
            this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras));
        }
        
        public Builder addExtras(final Bundle bundle) {
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            return this;
        }
        
        public Builder addRemoteInput(final RemoteInput remoteInput) {
            if (this.mRemoteInputs == null) {
                this.mRemoteInputs = new ArrayList<RemoteInput>();
            }
            this.mRemoteInputs.add(remoteInput);
            return this;
        }
        
        public Action build() {
            RemoteInput[] array;
            if (this.mRemoteInputs != null) {
                array = this.mRemoteInputs.toArray(new RemoteInput[this.mRemoteInputs.size()]);
            }
            else {
                array = null;
            }
            return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, array);
        }
        
        public Builder extend(final Extender extender) {
            extender.extend(this);
            return this;
        }
        
        public Bundle getExtras() {
            return this.mExtras;
        }
    }
    
    public interface Extender
    {
        Builder extend(final Builder p0);
    }
    
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
}
