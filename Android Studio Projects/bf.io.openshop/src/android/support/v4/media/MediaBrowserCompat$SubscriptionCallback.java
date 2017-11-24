package android.support.v4.media;

import android.support.annotation.*;
import java.util.*;
import android.os.*;

public abstract static class SubscriptionCallback
{
    public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list) {
    }
    
    public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list, @NonNull final Bundle bundle) {
    }
    
    public void onError(@NonNull final String s) {
    }
    
    public void onError(@NonNull final String s, @NonNull final Bundle bundle) {
    }
}
