package android.support.v4.media;

import android.os.*;
import android.support.annotation.*;
import android.content.*;
import android.support.v4.media.session.*;

interface MediaBrowserImpl
{
    void connect();
    
    void disconnect();
    
    @Nullable
    Bundle getExtras();
    
    void getItem(@NonNull final String p0, @NonNull final ItemCallback p1);
    
    @NonNull
    String getRoot();
    
    ComponentName getServiceComponent();
    
    @NonNull
    MediaSessionCompat.Token getSessionToken();
    
    boolean isConnected();
    
    void subscribe(@NonNull final String p0, final Bundle p1, @NonNull final SubscriptionCallback p2);
    
    void unsubscribe(@NonNull final String p0, final Bundle p1);
}
