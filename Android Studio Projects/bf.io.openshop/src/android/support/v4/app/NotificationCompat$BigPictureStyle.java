package android.support.v4.app;

import android.graphics.*;

public static class BigPictureStyle extends Style
{
    Bitmap mBigLargeIcon;
    boolean mBigLargeIconSet;
    Bitmap mPicture;
    
    public BigPictureStyle() {
    }
    
    public BigPictureStyle(final NotificationCompat.Builder builder) {
        ((Style)this).setBuilder(builder);
    }
    
    public BigPictureStyle bigLargeIcon(final Bitmap mBigLargeIcon) {
        this.mBigLargeIcon = mBigLargeIcon;
        this.mBigLargeIconSet = true;
        return this;
    }
    
    public BigPictureStyle bigPicture(final Bitmap mPicture) {
        this.mPicture = mPicture;
        return this;
    }
    
    public BigPictureStyle setBigContentTitle(final CharSequence charSequence) {
        this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
        return this;
    }
    
    public BigPictureStyle setSummaryText(final CharSequence charSequence) {
        this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }
}
