package android.support.v4.media;

import android.service.media.*;
import android.media.browse.*;
import android.os.*;

private static class ServiceBinderProxyApi23 extends ServiceBinderProxyApi21
{
    ServiceImplApi23 mServiceImpl;
    
    ServiceBinderProxyApi23(final ServiceImplApi23 mServiceImpl) {
        super(mServiceImpl);
        this.mServiceImpl = mServiceImpl;
    }
    
    @Override
    public void getMediaItem(final String s, final ResultReceiver resultReceiver) {
        try {
            this.mServiceImpl.getMediaItem(s, new ItemCallback() {
                final /* synthetic */ String val$KEY_MEDIA_ITEM = (String)MediaBrowserService.class.getDeclaredField("KEY_MEDIA_ITEM").get(null);
                
                @Override
                public void onItemLoaded(final int n, final Bundle bundle, final Parcel parcel) {
                    if (parcel != null) {
                        parcel.setDataPosition(0);
                        bundle.putParcelable(this.val$KEY_MEDIA_ITEM, (Parcelable)MediaBrowser$MediaItem.CREATOR.createFromParcel(parcel));
                        parcel.recycle();
                    }
                    resultReceiver.send(n, bundle);
                }
            });
        }
        catch (NoSuchFieldException ex) {}
        catch (IllegalAccessException ex2) {
            goto Label_0039;
        }
    }
}
