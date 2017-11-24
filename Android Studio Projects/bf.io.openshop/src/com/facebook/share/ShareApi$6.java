package com.facebook.share;

import com.facebook.internal.*;
import org.json.*;
import com.facebook.*;

class ShareApi$6 implements OnMapperCompleteListener {
    final /* synthetic */ OnMapValueCompleteListener val$onArrayListStagedListener;
    final /* synthetic */ JSONArray val$stagedObject;
    
    @Override
    public void onComplete() {
        this.val$onArrayListStagedListener.onComplete(this.val$stagedObject);
    }
    
    @Override
    public void onError(final FacebookException ex) {
        ((CollectionMapper.OnErrorListener)this.val$onArrayListStagedListener).onError(ex);
    }
}