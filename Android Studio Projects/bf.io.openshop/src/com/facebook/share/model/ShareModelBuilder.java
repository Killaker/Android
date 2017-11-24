package com.facebook.share.model;

import com.facebook.share.*;
import android.os.*;

public interface ShareModelBuilder<P extends ShareModel, E extends ShareModelBuilder> extends ShareBuilder<P, E>
{
    E readFrom(final Parcel p0);
    
    E readFrom(final P p0);
}
