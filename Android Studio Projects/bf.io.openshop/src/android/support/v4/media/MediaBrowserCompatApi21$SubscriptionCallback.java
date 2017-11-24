package android.support.v4.media;

import android.support.annotation.*;
import java.util.*;
import android.os.*;

interface SubscriptionCallback
{
    void onChildrenLoaded(@NonNull final String p0, final List<Parcel> p1);
    
    void onError(@NonNull final String p0);
}
