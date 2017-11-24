package android.support.v4.media;

import android.support.annotation.*;
import android.os.*;

interface ItemCallback
{
    void onError(@NonNull final String p0);
    
    void onItemLoaded(final Parcel p0);
}
