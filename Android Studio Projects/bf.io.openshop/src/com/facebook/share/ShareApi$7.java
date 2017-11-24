package com.facebook.share;

import com.facebook.internal.*;
import java.util.*;
import com.facebook.share.model.*;

class ShareApi$7 implements ValueMapper {
    @Override
    public void mapValue(final Object o, final OnMapValueCompleteListener onMapValueCompleteListener) {
        if (o instanceof ArrayList) {
            ShareApi.access$200(ShareApi.this, (ArrayList)o, onMapValueCompleteListener);
            return;
        }
        if (o instanceof ShareOpenGraphObject) {
            ShareApi.access$300(ShareApi.this, (ShareOpenGraphObject)o, onMapValueCompleteListener);
            return;
        }
        if (o instanceof SharePhoto) {
            ShareApi.access$400(ShareApi.this, (SharePhoto)o, onMapValueCompleteListener);
            return;
        }
        onMapValueCompleteListener.onComplete(o);
    }
}