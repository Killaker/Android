package android.support.v4.app;

import android.app.*;
import java.util.*;

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
