package android.support.v4.media.session;

import android.support.v4.media.*;
import android.os.*;

public static final class QueueItem implements Parcelable
{
    public static final Parcelable$Creator<QueueItem> CREATOR;
    public static final int UNKNOWN_ID = -1;
    private final MediaDescriptionCompat mDescription;
    private final long mId;
    private Object mItem;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<QueueItem>() {
            public QueueItem createFromParcel(final Parcel parcel) {
                return new QueueItem(parcel);
            }
            
            public QueueItem[] newArray(final int n) {
                return new QueueItem[n];
            }
        };
    }
    
    private QueueItem(final Parcel parcel) {
        this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.mId = parcel.readLong();
    }
    
    public QueueItem(final MediaDescriptionCompat mediaDescriptionCompat, final long n) {
        this(null, mediaDescriptionCompat, n);
    }
    
    private QueueItem(final Object mItem, final MediaDescriptionCompat mDescription, final long mId) {
        if (mDescription == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        if (mId == -1L) {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
        this.mDescription = mDescription;
        this.mId = mId;
        this.mItem = mItem;
    }
    
    public static QueueItem obtain(final Object o) {
        return new QueueItem(o, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(o)), MediaSessionCompatApi21.QueueItem.getQueueId(o));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MediaDescriptionCompat getDescription() {
        return this.mDescription;
    }
    
    public long getQueueId() {
        return this.mId;
    }
    
    public Object getQueueItem() {
        if (this.mItem != null || Build$VERSION.SDK_INT < 21) {
            return this.mItem;
        }
        return this.mItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
    }
    
    @Override
    public String toString() {
        return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        this.mDescription.writeToParcel(parcel, n);
        parcel.writeLong(this.mId);
    }
}
