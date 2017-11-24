package android.support.v4.media;

import android.media.browse.*;
import android.os.*;

class MediaBrowserServiceCompatApi23$MediaBrowserServiceAdaptorApi23$ServiceBinderProxyApi23$1 implements ItemCallback {
    final /* synthetic */ String val$KEY_MEDIA_ITEM;
    final /* synthetic */ ResultReceiver val$receiver;
    
    @Override
    public void onItemLoaded(final int n, final Bundle bundle, final Parcel parcel) {
        if (parcel != null) {
            parcel.setDataPosition(0);
            bundle.putParcelable(this.val$KEY_MEDIA_ITEM, (Parcelable)MediaBrowser$MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
        this.val$receiver.send(n, bundle);
    }
}