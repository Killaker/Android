package android.support.v4.app;

import java.util.*;
import android.content.*;
import android.os.*;
import android.graphics.*;
import android.app.*;
import android.widget.*;
import android.support.annotation.*;
import android.net.*;

public static class Builder
{
    private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
    public ArrayList<Action> mActions;
    String mCategory;
    int mColor;
    public CharSequence mContentInfo;
    PendingIntent mContentIntent;
    public CharSequence mContentText;
    public CharSequence mContentTitle;
    public Context mContext;
    Bundle mExtras;
    PendingIntent mFullScreenIntent;
    String mGroupKey;
    boolean mGroupSummary;
    public Bitmap mLargeIcon;
    boolean mLocalOnly;
    public Notification mNotification;
    public int mNumber;
    public ArrayList<String> mPeople;
    int mPriority;
    int mProgress;
    boolean mProgressIndeterminate;
    int mProgressMax;
    Notification mPublicVersion;
    boolean mShowWhen;
    String mSortKey;
    public Style mStyle;
    public CharSequence mSubText;
    RemoteViews mTickerView;
    public boolean mUseChronometer;
    int mVisibility;
    
    public Builder(final Context mContext) {
        this.mShowWhen = true;
        this.mActions = new ArrayList<Action>();
        this.mLocalOnly = false;
        this.mColor = 0;
        this.mVisibility = 0;
        this.mNotification = new Notification();
        this.mContext = mContext;
        this.mNotification.when = System.currentTimeMillis();
        this.mNotification.audioStreamType = -1;
        this.mPriority = 0;
        this.mPeople = new ArrayList<String>();
    }
    
    protected static CharSequence limitCharSequenceLength(final CharSequence charSequence) {
        if (charSequence != null && charSequence.length() > 5120) {
            return charSequence.subSequence(0, 5120);
        }
        return charSequence;
    }
    
    private void setFlag(final int n, final boolean b) {
        if (b) {
            final Notification mNotification = this.mNotification;
            mNotification.flags |= n;
            return;
        }
        final Notification mNotification2 = this.mNotification;
        mNotification2.flags &= ~n;
    }
    
    public Builder addAction(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
        this.mActions.add(new Action(n, charSequence, pendingIntent));
        return this;
    }
    
    public Builder addAction(final Action action) {
        this.mActions.add(action);
        return this;
    }
    
    public Builder addExtras(final Bundle bundle) {
        if (bundle != null) {
            if (this.mExtras != null) {
                this.mExtras.putAll(bundle);
                return this;
            }
            this.mExtras = new Bundle(bundle);
        }
        return this;
    }
    
    public Builder addPerson(final String s) {
        this.mPeople.add(s);
        return this;
    }
    
    public Notification build() {
        return NotificationCompat.access$200().build(this, this.getExtender());
    }
    
    public Builder extend(final NotificationCompat.Extender extender) {
        extender.extend(this);
        return this;
    }
    
    protected BuilderExtender getExtender() {
        return new BuilderExtender();
    }
    
    public Bundle getExtras() {
        if (this.mExtras == null) {
            this.mExtras = new Bundle();
        }
        return this.mExtras;
    }
    
    @Deprecated
    public Notification getNotification() {
        return this.build();
    }
    
    public Builder setAutoCancel(final boolean b) {
        this.setFlag(16, b);
        return this;
    }
    
    public Builder setCategory(final String mCategory) {
        this.mCategory = mCategory;
        return this;
    }
    
    public Builder setColor(@ColorInt final int mColor) {
        this.mColor = mColor;
        return this;
    }
    
    public Builder setContent(final RemoteViews contentView) {
        this.mNotification.contentView = contentView;
        return this;
    }
    
    public Builder setContentInfo(final CharSequence charSequence) {
        this.mContentInfo = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public Builder setContentIntent(final PendingIntent mContentIntent) {
        this.mContentIntent = mContentIntent;
        return this;
    }
    
    public Builder setContentText(final CharSequence charSequence) {
        this.mContentText = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public Builder setContentTitle(final CharSequence charSequence) {
        this.mContentTitle = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public Builder setDefaults(final int defaults) {
        this.mNotification.defaults = defaults;
        if ((defaults & 0x4) != 0x0) {
            final Notification mNotification = this.mNotification;
            mNotification.flags |= 0x1;
        }
        return this;
    }
    
    public Builder setDeleteIntent(final PendingIntent deleteIntent) {
        this.mNotification.deleteIntent = deleteIntent;
        return this;
    }
    
    public Builder setExtras(final Bundle mExtras) {
        this.mExtras = mExtras;
        return this;
    }
    
    public Builder setFullScreenIntent(final PendingIntent mFullScreenIntent, final boolean b) {
        this.mFullScreenIntent = mFullScreenIntent;
        this.setFlag(128, b);
        return this;
    }
    
    public Builder setGroup(final String mGroupKey) {
        this.mGroupKey = mGroupKey;
        return this;
    }
    
    public Builder setGroupSummary(final boolean mGroupSummary) {
        this.mGroupSummary = mGroupSummary;
        return this;
    }
    
    public Builder setLargeIcon(final Bitmap mLargeIcon) {
        this.mLargeIcon = mLargeIcon;
        return this;
    }
    
    public Builder setLights(@ColorInt final int ledARGB, final int ledOnMS, final int ledOffMS) {
        int n = 1;
        this.mNotification.ledARGB = ledARGB;
        this.mNotification.ledOnMS = ledOnMS;
        this.mNotification.ledOffMS = ledOffMS;
        int n2;
        if (this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0) {
            n2 = n;
        }
        else {
            n2 = 0;
        }
        final Notification mNotification = this.mNotification;
        final int n3 = 0xFFFFFFFE & this.mNotification.flags;
        if (n2 == 0) {
            n = 0;
        }
        mNotification.flags = (n | n3);
        return this;
    }
    
    public Builder setLocalOnly(final boolean mLocalOnly) {
        this.mLocalOnly = mLocalOnly;
        return this;
    }
    
    public Builder setNumber(final int mNumber) {
        this.mNumber = mNumber;
        return this;
    }
    
    public Builder setOngoing(final boolean b) {
        this.setFlag(2, b);
        return this;
    }
    
    public Builder setOnlyAlertOnce(final boolean b) {
        this.setFlag(8, b);
        return this;
    }
    
    public Builder setPriority(final int mPriority) {
        this.mPriority = mPriority;
        return this;
    }
    
    public Builder setProgress(final int mProgressMax, final int mProgress, final boolean mProgressIndeterminate) {
        this.mProgressMax = mProgressMax;
        this.mProgress = mProgress;
        this.mProgressIndeterminate = mProgressIndeterminate;
        return this;
    }
    
    public Builder setPublicVersion(final Notification mPublicVersion) {
        this.mPublicVersion = mPublicVersion;
        return this;
    }
    
    public Builder setShowWhen(final boolean mShowWhen) {
        this.mShowWhen = mShowWhen;
        return this;
    }
    
    public Builder setSmallIcon(final int icon) {
        this.mNotification.icon = icon;
        return this;
    }
    
    public Builder setSmallIcon(final int icon, final int iconLevel) {
        this.mNotification.icon = icon;
        this.mNotification.iconLevel = iconLevel;
        return this;
    }
    
    public Builder setSortKey(final String mSortKey) {
        this.mSortKey = mSortKey;
        return this;
    }
    
    public Builder setSound(final Uri sound) {
        this.mNotification.sound = sound;
        this.mNotification.audioStreamType = -1;
        return this;
    }
    
    public Builder setSound(final Uri sound, final int audioStreamType) {
        this.mNotification.sound = sound;
        this.mNotification.audioStreamType = audioStreamType;
        return this;
    }
    
    public Builder setStyle(final Style mStyle) {
        if (this.mStyle != mStyle) {
            this.mStyle = mStyle;
            if (this.mStyle != null) {
                this.mStyle.setBuilder(this);
            }
        }
        return this;
    }
    
    public Builder setSubText(final CharSequence charSequence) {
        this.mSubText = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public Builder setTicker(final CharSequence charSequence) {
        this.mNotification.tickerText = limitCharSequenceLength(charSequence);
        return this;
    }
    
    public Builder setTicker(final CharSequence charSequence, final RemoteViews mTickerView) {
        this.mNotification.tickerText = limitCharSequenceLength(charSequence);
        this.mTickerView = mTickerView;
        return this;
    }
    
    public Builder setUsesChronometer(final boolean mUseChronometer) {
        this.mUseChronometer = mUseChronometer;
        return this;
    }
    
    public Builder setVibrate(final long[] vibrate) {
        this.mNotification.vibrate = vibrate;
        return this;
    }
    
    public Builder setVisibility(final int mVisibility) {
        this.mVisibility = mVisibility;
        return this;
    }
    
    public Builder setWhen(final long when) {
        this.mNotification.when = when;
        return this;
    }
}
