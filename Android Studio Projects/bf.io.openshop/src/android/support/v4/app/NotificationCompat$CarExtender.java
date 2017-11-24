package android.support.v4.app;

import android.graphics.*;
import android.os.*;
import android.support.annotation.*;
import android.app.*;
import java.util.*;

public static final class CarExtender implements NotificationCompat.Extender
{
    private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
    private static final String EXTRA_COLOR = "app_color";
    private static final String EXTRA_CONVERSATION = "car_conversation";
    private static final String EXTRA_LARGE_ICON = "large_icon";
    private static final String TAG = "CarExtender";
    private int mColor;
    private Bitmap mLargeIcon;
    private UnreadConversation mUnreadConversation;
    
    public CarExtender() {
        this.mColor = 0;
    }
    
    public CarExtender(final Notification notification) {
        this.mColor = 0;
        if (Build$VERSION.SDK_INT >= 21) {
            Bundle bundle;
            if (NotificationCompat.getExtras(notification) == null) {
                bundle = null;
            }
            else {
                bundle = NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
            }
            if (bundle != null) {
                this.mLargeIcon = (Bitmap)bundle.getParcelable("large_icon");
                this.mColor = bundle.getInt("app_color", 0);
                this.mUnreadConversation = (UnreadConversation)NotificationCompat.access$200().getUnreadConversationFromBundle(bundle.getBundle("car_conversation"), UnreadConversation.FACTORY, RemoteInput.FACTORY);
            }
        }
    }
    
    @Override
    public NotificationCompat.Builder extend(final NotificationCompat.Builder builder) {
        if (Build$VERSION.SDK_INT < 21) {
            return builder;
        }
        final Bundle bundle = new Bundle();
        if (this.mLargeIcon != null) {
            bundle.putParcelable("large_icon", (Parcelable)this.mLargeIcon);
        }
        if (this.mColor != 0) {
            bundle.putInt("app_color", this.mColor);
        }
        if (this.mUnreadConversation != null) {
            bundle.putBundle("car_conversation", NotificationCompat.access$200().getBundleForUnreadConversation(this.mUnreadConversation));
        }
        builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
        return builder;
    }
    
    @ColorInt
    public int getColor() {
        return this.mColor;
    }
    
    public Bitmap getLargeIcon() {
        return this.mLargeIcon;
    }
    
    public UnreadConversation getUnreadConversation() {
        return this.mUnreadConversation;
    }
    
    public CarExtender setColor(@ColorInt final int mColor) {
        this.mColor = mColor;
        return this;
    }
    
    public CarExtender setLargeIcon(final Bitmap mLargeIcon) {
        this.mLargeIcon = mLargeIcon;
        return this;
    }
    
    public CarExtender setUnreadConversation(final UnreadConversation mUnreadConversation) {
        this.mUnreadConversation = mUnreadConversation;
        return this;
    }
    
    public static class UnreadConversation extends NotificationCompatBase.UnreadConversation
    {
        static final Factory FACTORY;
        private final long mLatestTimestamp;
        private final String[] mMessages;
        private final String[] mParticipants;
        private final PendingIntent mReadPendingIntent;
        private final RemoteInput mRemoteInput;
        private final PendingIntent mReplyPendingIntent;
        
        static {
            FACTORY = new Factory() {
                public CarExtender.UnreadConversation build(final String[] array, final RemoteInputCompatBase.RemoteInput remoteInput, final PendingIntent pendingIntent, final PendingIntent pendingIntent2, final String[] array2, final long n) {
                    return new CarExtender.UnreadConversation(array, (RemoteInput)remoteInput, pendingIntent, pendingIntent2, array2, n);
                }
            };
        }
        
        UnreadConversation(final String[] mMessages, final RemoteInput mRemoteInput, final PendingIntent mReplyPendingIntent, final PendingIntent mReadPendingIntent, final String[] mParticipants, final long mLatestTimestamp) {
            this.mMessages = mMessages;
            this.mRemoteInput = mRemoteInput;
            this.mReadPendingIntent = mReadPendingIntent;
            this.mReplyPendingIntent = mReplyPendingIntent;
            this.mParticipants = mParticipants;
            this.mLatestTimestamp = mLatestTimestamp;
        }
        
        public long getLatestTimestamp() {
            return this.mLatestTimestamp;
        }
        
        public String[] getMessages() {
            return this.mMessages;
        }
        
        public String getParticipant() {
            if (this.mParticipants.length > 0) {
                return this.mParticipants[0];
            }
            return null;
        }
        
        public String[] getParticipants() {
            return this.mParticipants;
        }
        
        public PendingIntent getReadPendingIntent() {
            return this.mReadPendingIntent;
        }
        
        public RemoteInput getRemoteInput() {
            return this.mRemoteInput;
        }
        
        public PendingIntent getReplyPendingIntent() {
            return this.mReplyPendingIntent;
        }
        
        public static class Builder
        {
            private long mLatestTimestamp;
            private final List<String> mMessages;
            private final String mParticipant;
            private PendingIntent mReadPendingIntent;
            private RemoteInput mRemoteInput;
            private PendingIntent mReplyPendingIntent;
            
            public Builder(final String mParticipant) {
                this.mMessages = new ArrayList<String>();
                this.mParticipant = mParticipant;
            }
            
            public Builder addMessage(final String s) {
                this.mMessages.add(s);
                return this;
            }
            
            public UnreadConversation build() {
                return new UnreadConversation(this.mMessages.toArray(new String[this.mMessages.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[] { this.mParticipant }, this.mLatestTimestamp);
            }
            
            public Builder setLatestTimestamp(final long mLatestTimestamp) {
                this.mLatestTimestamp = mLatestTimestamp;
                return this;
            }
            
            public Builder setReadPendingIntent(final PendingIntent mReadPendingIntent) {
                this.mReadPendingIntent = mReadPendingIntent;
                return this;
            }
            
            public Builder setReplyAction(final PendingIntent mReplyPendingIntent, final RemoteInput mRemoteInput) {
                this.mRemoteInput = mRemoteInput;
                this.mReplyPendingIntent = mReplyPendingIntent;
                return this;
            }
        }
    }
}
