package android.support.v4.media.session;

import android.text.*;
import android.os.*;

public static final class CustomAction implements Parcelable
{
    public static final Parcelable$Creator<CustomAction> CREATOR;
    private final String mAction;
    private Object mCustomActionObj;
    private final Bundle mExtras;
    private final int mIcon;
    private final CharSequence mName;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<CustomAction>() {
            public CustomAction createFromParcel(final Parcel parcel) {
                return new CustomAction(parcel);
            }
            
            public CustomAction[] newArray(final int n) {
                return new CustomAction[n];
            }
        };
    }
    
    private CustomAction(final Parcel parcel) {
        this.mAction = parcel.readString();
        this.mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mIcon = parcel.readInt();
        this.mExtras = parcel.readBundle();
    }
    
    private CustomAction(final String mAction, final CharSequence mName, final int mIcon, final Bundle mExtras) {
        this.mAction = mAction;
        this.mName = mName;
        this.mIcon = mIcon;
        this.mExtras = mExtras;
    }
    
    public static CustomAction fromCustomAction(final Object mCustomActionObj) {
        if (mCustomActionObj == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        final CustomAction customAction = new CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(mCustomActionObj), PlaybackStateCompatApi21.CustomAction.getName(mCustomActionObj), PlaybackStateCompatApi21.CustomAction.getIcon(mCustomActionObj), PlaybackStateCompatApi21.CustomAction.getExtras(mCustomActionObj));
        customAction.mCustomActionObj = mCustomActionObj;
        return customAction;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAction() {
        return this.mAction;
    }
    
    public Object getCustomAction() {
        if (this.mCustomActionObj != null || Build$VERSION.SDK_INT < 21) {
            return this.mCustomActionObj;
        }
        return this.mCustomActionObj = PlaybackStateCompatApi21.CustomAction.newInstance(this.mAction, this.mName, this.mIcon, this.mExtras);
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public int getIcon() {
        return this.mIcon;
    }
    
    public CharSequence getName() {
        return this.mName;
    }
    
    @Override
    public String toString() {
        return "Action:mName='" + (Object)this.mName + ", mIcon=" + this.mIcon + ", mExtras=" + this.mExtras;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.mAction);
        TextUtils.writeToParcel(this.mName, parcel, n);
        parcel.writeInt(this.mIcon);
        parcel.writeBundle(this.mExtras);
    }
    
    public static final class Builder
    {
        private final String mAction;
        private Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;
        
        public Builder(final String mAction, final CharSequence mName, final int mIcon) {
            if (TextUtils.isEmpty((CharSequence)mAction)) {
                throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
            }
            if (TextUtils.isEmpty(mName)) {
                throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
            }
            if (mIcon == 0) {
                throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
            }
            this.mAction = mAction;
            this.mName = mName;
            this.mIcon = mIcon;
        }
        
        public CustomAction build() {
            return new CustomAction(this.mAction, this.mName, this.mIcon, this.mExtras);
        }
        
        public Builder setExtras(final Bundle mExtras) {
            this.mExtras = mExtras;
            return this;
        }
    }
}
