package com.facebook.share;

import com.facebook.internal.*;
import org.json.*;
import android.os.*;
import java.net.*;
import com.facebook.*;
import java.io.*;

class ShareApi$11 implements OnMapperCompleteListener {
    final /* synthetic */ String val$ogType;
    final /* synthetic */ OnMapValueCompleteListener val$onOpenGraphObjectStagedListener;
    final /* synthetic */ GraphRequest.Callback val$requestCallback;
    final /* synthetic */ JSONObject val$stagedObject;
    
    @Override
    public void onComplete() {
        final String string = this.val$stagedObject.toString();
        final Bundle bundle = new Bundle();
        bundle.putString("object", string);
        try {
            new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.access$100(ShareApi.this, "objects/" + URLEncoder.encode(this.val$ogType, "UTF-8")), bundle, HttpMethod.POST, this.val$requestCallback).executeAsync();
        }
        catch (UnsupportedEncodingException ex) {
            String localizedMessage = ex.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "Error staging Open Graph object.";
            }
            ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(new FacebookException(localizedMessage));
        }
    }
    
    @Override
    public void onError(final FacebookException ex) {
        ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(ex);
    }
}