package com.facebook.share;

import android.os.*;
import android.text.*;
import java.net.*;
import org.json.*;
import com.facebook.share.model.*;
import java.io.*;
import com.facebook.internal.*;
import com.facebook.*;
import android.graphics.*;
import android.net.*;
import android.util.*;
import java.util.*;
import com.facebook.share.internal.*;

public final class ShareApi
{
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_GRAPH_NODE = "me";
    private static final String GRAPH_PATH_FORMAT = "%s/%s";
    private static final String PHOTOS_EDGE = "photos";
    private static final String TAG = "ShareApi";
    private String graphNode;
    private String message;
    private final ShareContent shareContent;
    
    public ShareApi(final ShareContent shareContent) {
        this.shareContent = shareContent;
        this.graphNode = "me";
    }
    
    private void addCommonParameters(final Bundle bundle, final ShareContent shareContent) {
        final List peopleIds = shareContent.getPeopleIds();
        if (!Utility.isNullOrEmpty((Collection<Object>)peopleIds)) {
            bundle.putString("tags", TextUtils.join((CharSequence)", ", (Iterable)peopleIds));
        }
        if (!Utility.isNullOrEmpty(shareContent.getPlaceId())) {
            bundle.putString("place", shareContent.getPlaceId());
        }
        if (!Utility.isNullOrEmpty(shareContent.getRef())) {
            bundle.putString("ref", shareContent.getRef());
        }
    }
    
    private String getGraphPath(final String s) {
        try {
            return String.format(Locale.ROOT, "%s/%s", URLEncoder.encode(this.getGraphNode(), "UTF-8"), s);
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    private Bundle getSharePhotoCommonParameters(final SharePhoto sharePhoto, final SharePhotoContent sharePhotoContent) throws JSONException {
        final Bundle parameters = sharePhoto.getParameters();
        if (!parameters.containsKey("place") && !Utility.isNullOrEmpty(sharePhotoContent.getPlaceId())) {
            parameters.putString("place", sharePhotoContent.getPlaceId());
        }
        if (!parameters.containsKey("tags") && !Utility.isNullOrEmpty(sharePhotoContent.getPeopleIds())) {
            final List<String> peopleIds = sharePhotoContent.getPeopleIds();
            if (!Utility.isNullOrEmpty((Collection<Object>)peopleIds)) {
                final JSONArray jsonArray = new JSONArray();
                for (final String s : peopleIds) {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tag_uid", (Object)s);
                    jsonArray.put((Object)jsonObject);
                }
                parameters.putString("tags", jsonArray.toString());
            }
        }
        if (!parameters.containsKey("ref") && !Utility.isNullOrEmpty(sharePhotoContent.getRef())) {
            parameters.putString("ref", sharePhotoContent.getRef());
        }
        return parameters;
    }
    
    private static void handleImagesOnAction(final Bundle bundle) {
        final String string = bundle.getString("image");
        if (string == null) {
            return;
        }
        while (true) {
            while (true) {
                int n = 0;
                Label_0118: {
                    try {
                        final JSONArray jsonArray = new JSONArray(string);
                        n = 0;
                        if (n >= jsonArray.length()) {
                            break Label_0118;
                        }
                        final JSONObject optJSONObject = jsonArray.optJSONObject(n);
                        if (optJSONObject != null) {
                            putImageInBundleWithArrayFormat(bundle, n, optJSONObject);
                            break Label_0118;
                        }
                        bundle.putString(String.format(Locale.ROOT, "image[%d][url]", n), jsonArray.getString(n));
                        break Label_0118;
                    }
                    catch (JSONException ex) {
                        final Bundle bundle2 = bundle;
                        final int n2 = 0;
                        final String s = string;
                        final JSONObject jsonObject = new JSONObject(s);
                        putImageInBundleWithArrayFormat(bundle2, n2, jsonObject);
                        final Bundle bundle3 = bundle;
                        final String s2 = "image";
                        bundle3.remove(s2);
                    }
                    try {
                        final Bundle bundle2 = bundle;
                        final int n2 = 0;
                        final String s = string;
                        final JSONObject jsonObject = new JSONObject(s);
                        putImageInBundleWithArrayFormat(bundle2, n2, jsonObject);
                        final Bundle bundle3 = bundle;
                        final String s2 = "image";
                        bundle3.remove(s2);
                        return;
                        bundle.remove("image");
                        return;
                    }
                    catch (JSONException ex2) {
                        return;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    private static void putImageInBundleWithArrayFormat(final Bundle bundle, final int n, final JSONObject jsonObject) throws JSONException {
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            bundle.putString(String.format(Locale.ROOT, "image[%d][%s]", n, s), jsonObject.get(s).toString());
        }
    }
    
    public static void share(final ShareContent shareContent, final FacebookCallback<Sharer.Result> facebookCallback) {
        new ShareApi(shareContent).share(facebookCallback);
    }
    
    private void shareLinkContent(final ShareLinkContent shareLinkContent, final FacebookCallback<Sharer.Result> facebookCallback) {
        final GraphRequest.Callback callback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                final JSONObject jsonObject = graphResponse.getJSONObject();
                String optString;
                if (jsonObject == null) {
                    optString = null;
                }
                else {
                    optString = jsonObject.optString("id");
                }
                ShareInternalUtility.invokeCallbackWithResults(facebookCallback, optString, graphResponse);
            }
        };
        final Bundle bundle = new Bundle();
        this.addCommonParameters(bundle, shareLinkContent);
        bundle.putString("message", this.getMessage());
        bundle.putString("link", Utility.getUriString(shareLinkContent.getContentUrl()));
        bundle.putString("picture", Utility.getUriString(shareLinkContent.getImageUrl()));
        bundle.putString("name", shareLinkContent.getContentTitle());
        bundle.putString("description", shareLinkContent.getContentDescription());
        bundle.putString("ref", shareLinkContent.getRef());
        new GraphRequest(AccessToken.getCurrentAccessToken(), this.getGraphPath("feed"), bundle, HttpMethod.POST, (GraphRequest.Callback)callback).executeAsync();
    }
    
    private void shareOpenGraphContent(final ShareOpenGraphContent shareOpenGraphContent, final FacebookCallback<Sharer.Result> facebookCallback) {
        final GraphRequest.Callback callback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                final JSONObject jsonObject = graphResponse.getJSONObject();
                String optString;
                if (jsonObject == null) {
                    optString = null;
                }
                else {
                    optString = jsonObject.optString("id");
                }
                ShareInternalUtility.invokeCallbackWithResults(facebookCallback, optString, graphResponse);
            }
        };
        final ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        final Bundle bundle = action.getBundle();
        this.addCommonParameters(bundle, shareOpenGraphContent);
        if (!Utility.isNullOrEmpty(this.getMessage())) {
            bundle.putString("message", this.getMessage());
        }
        this.stageOpenGraphAction(bundle, new CollectionMapper.OnMapperCompleteListener() {
            @Override
            public void onComplete() {
                try {
                    handleImagesOnAction(bundle);
                    new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.this.getGraphPath(URLEncoder.encode(action.getActionType(), "UTF-8")), bundle, HttpMethod.POST, callback).executeAsync();
                }
                catch (UnsupportedEncodingException ex) {
                    ShareInternalUtility.invokeCallbackWithException(facebookCallback, ex);
                }
            }
            
            @Override
            public void onError(final FacebookException ex) {
                ShareInternalUtility.invokeCallbackWithException(facebookCallback, ex);
            }
        });
    }
    
    private void sharePhotoContent(final SharePhotoContent p0, final FacebookCallback<Sharer.Result> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/facebook/internal/Mutable;
        //     3: dup            
        //     4: iconst_0       
        //     5: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //     8: invokespecial   com/facebook/internal/Mutable.<init>:(Ljava/lang/Object;)V
        //    11: astore_3       
        //    12: invokestatic    com/facebook/AccessToken.getCurrentAccessToken:()Lcom/facebook/AccessToken;
        //    15: astore          4
        //    17: new             Ljava/util/ArrayList;
        //    20: dup            
        //    21: invokespecial   java/util/ArrayList.<init>:()V
        //    24: astore          5
        //    26: new             Lcom/facebook/share/ShareApi$3;
        //    29: dup            
        //    30: aload_0        
        //    31: new             Ljava/util/ArrayList;
        //    34: dup            
        //    35: invokespecial   java/util/ArrayList.<init>:()V
        //    38: new             Ljava/util/ArrayList;
        //    41: dup            
        //    42: invokespecial   java/util/ArrayList.<init>:()V
        //    45: aload_3        
        //    46: aload_2        
        //    47: invokespecial   com/facebook/share/ShareApi$3.<init>:(Lcom/facebook/share/ShareApi;Ljava/util/ArrayList;Ljava/util/ArrayList;Lcom/facebook/internal/Mutable;Lcom/facebook/FacebookCallback;)V
        //    50: astore          6
        //    52: aload_1        
        //    53: invokevirtual   com/facebook/share/model/SharePhotoContent.getPhotos:()Ljava/util/List;
        //    56: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    61: astore          8
        //    63: aload           8
        //    65: invokeinterface java/util/Iterator.hasNext:()Z
        //    70: ifeq            210
        //    73: aload           8
        //    75: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    80: checkcast       Lcom/facebook/share/model/SharePhoto;
        //    83: astore          11
        //    85: aload_0        
        //    86: aload           11
        //    88: aload_1        
        //    89: invokespecial   com/facebook/share/ShareApi.getSharePhotoCommonParameters:(Lcom/facebook/share/model/SharePhoto;Lcom/facebook/share/model/SharePhotoContent;)Landroid/os/Bundle;
        //    92: astore          13
        //    94: aload           11
        //    96: invokevirtual   com/facebook/share/model/SharePhoto.getBitmap:()Landroid/graphics/Bitmap;
        //    99: astore          14
        //   101: aload           11
        //   103: invokevirtual   com/facebook/share/model/SharePhoto.getImageUrl:()Landroid/net/Uri;
        //   106: astore          15
        //   108: aload           11
        //   110: invokevirtual   com/facebook/share/model/SharePhoto.getCaption:()Ljava/lang/String;
        //   113: astore          16
        //   115: aload           16
        //   117: ifnonnull       126
        //   120: aload_0        
        //   121: invokevirtual   com/facebook/share/ShareApi.getMessage:()Ljava/lang/String;
        //   124: astore          16
        //   126: aload           14
        //   128: ifnull          177
        //   131: aload           5
        //   133: aload           4
        //   135: aload_0        
        //   136: ldc             "photos"
        //   138: invokespecial   com/facebook/share/ShareApi.getGraphPath:(Ljava/lang/String;)Ljava/lang/String;
        //   141: aload           14
        //   143: aload           16
        //   145: aload           13
        //   147: aload           6
        //   149: invokestatic    com/facebook/GraphRequest.newUploadPhotoRequest:(Lcom/facebook/AccessToken;Ljava/lang/String;Landroid/graphics/Bitmap;Ljava/lang/String;Landroid/os/Bundle;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
        //   152: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   155: pop            
        //   156: goto            63
        //   159: astore          7
        //   161: aload_2        
        //   162: aload           7
        //   164: invokestatic    com/facebook/share/internal/ShareInternalUtility.invokeCallbackWithException:(Lcom/facebook/FacebookCallback;Ljava/lang/Exception;)V
        //   167: return         
        //   168: astore          12
        //   170: aload_2        
        //   171: aload           12
        //   173: invokestatic    com/facebook/share/internal/ShareInternalUtility.invokeCallbackWithException:(Lcom/facebook/FacebookCallback;Ljava/lang/Exception;)V
        //   176: return         
        //   177: aload           15
        //   179: ifnull          63
        //   182: aload           5
        //   184: aload           4
        //   186: aload_0        
        //   187: ldc             "photos"
        //   189: invokespecial   com/facebook/share/ShareApi.getGraphPath:(Ljava/lang/String;)Ljava/lang/String;
        //   192: aload           15
        //   194: aload           16
        //   196: aload           13
        //   198: aload           6
        //   200: invokestatic    com/facebook/GraphRequest.newUploadPhotoRequest:(Lcom/facebook/AccessToken;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;Landroid/os/Bundle;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
        //   203: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   206: pop            
        //   207: goto            63
        //   210: aload_3        
        //   211: aload_3        
        //   212: getfield        com/facebook/internal/Mutable.value:Ljava/lang/Object;
        //   215: checkcast       Ljava/lang/Integer;
        //   218: invokevirtual   java/lang/Integer.intValue:()I
        //   221: aload           5
        //   223: invokevirtual   java/util/ArrayList.size:()I
        //   226: iadd           
        //   227: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   230: putfield        com/facebook/internal/Mutable.value:Ljava/lang/Object;
        //   233: aload           5
        //   235: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   238: astore          9
        //   240: aload           9
        //   242: invokeinterface java/util/Iterator.hasNext:()Z
        //   247: ifeq            167
        //   250: aload           9
        //   252: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   257: checkcast       Lcom/facebook/GraphRequest;
        //   260: invokevirtual   com/facebook/GraphRequest.executeAsync:()Lcom/facebook/GraphRequestAsyncTask;
        //   263: pop            
        //   264: goto            240
        //    Signature:
        //  (Lcom/facebook/share/model/SharePhotoContent;Lcom/facebook/FacebookCallback<Lcom/facebook/share/Sharer$Result;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  52     63     159    167    Ljava/io/FileNotFoundException;
        //  63     85     159    167    Ljava/io/FileNotFoundException;
        //  85     94     168    177    Lorg/json/JSONException;
        //  85     94     159    167    Ljava/io/FileNotFoundException;
        //  94     115    159    167    Ljava/io/FileNotFoundException;
        //  120    126    159    167    Ljava/io/FileNotFoundException;
        //  131    156    159    167    Ljava/io/FileNotFoundException;
        //  170    176    159    167    Ljava/io/FileNotFoundException;
        //  182    207    159    167    Ljava/io/FileNotFoundException;
        //  210    240    159    167    Ljava/io/FileNotFoundException;
        //  240    264    159    167    Ljava/io/FileNotFoundException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void shareVideoContent(final ShareVideoContent shareVideoContent, final FacebookCallback<Sharer.Result> facebookCallback) {
        try {
            VideoUploader.uploadAsync(shareVideoContent, this.getGraphNode(), facebookCallback);
        }
        catch (FileNotFoundException ex) {
            ShareInternalUtility.invokeCallbackWithException(facebookCallback, ex);
        }
    }
    
    private void stageArrayList(final ArrayList list, final CollectionMapper.OnMapValueCompleteListener onMapValueCompleteListener) {
        final JSONArray jsonArray = new JSONArray();
        this.stageCollectionValues((CollectionMapper.Collection<Object>)new CollectionMapper.Collection<Integer>() {
            public Object get(final Integer n) {
                return list.get(n);
            }
            
            @Override
            public Iterator<Integer> keyIterator() {
                return new Iterator<Integer>() {
                    final /* synthetic */ Mutable val$current = new Mutable((T)0);
                    final /* synthetic */ int val$size = list.size();
                    
                    @Override
                    public boolean hasNext() {
                        return (int)this.val$current.value < this.val$size;
                    }
                    
                    @Override
                    public Integer next() {
                        final Integer n = (Integer)this.val$current.value;
                        final Mutable val$current = this.val$current;
                        val$current.value = (T)(1 + (int)val$current.value);
                        return n;
                    }
                    
                    @Override
                    public void remove() {
                    }
                };
            }
            
            public void set(final Integer n, final Object o, final OnErrorListener onErrorListener) {
                try {
                    jsonArray.put((int)n, o);
                }
                catch (JSONException ex) {
                    String localizedMessage = ex.getLocalizedMessage();
                    if (localizedMessage == null) {
                        localizedMessage = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(localizedMessage));
                }
            }
        }, new CollectionMapper.OnMapperCompleteListener() {
            @Override
            public void onComplete() {
                onMapValueCompleteListener.onComplete(jsonArray);
            }
            
            @Override
            public void onError(final FacebookException ex) {
                ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(ex);
            }
        });
    }
    
    private <T> void stageCollectionValues(final CollectionMapper.Collection<T> collection, final CollectionMapper.OnMapperCompleteListener onMapperCompleteListener) {
        CollectionMapper.iterate((CollectionMapper.Collection<Object>)collection, (CollectionMapper.ValueMapper)new CollectionMapper.ValueMapper() {
            @Override
            public void mapValue(final Object o, final OnMapValueCompleteListener onMapValueCompleteListener) {
                if (o instanceof ArrayList) {
                    ShareApi.this.stageArrayList((ArrayList)o, onMapValueCompleteListener);
                    return;
                }
                if (o instanceof ShareOpenGraphObject) {
                    ShareApi.this.stageOpenGraphObject((ShareOpenGraphObject)o, onMapValueCompleteListener);
                    return;
                }
                if (o instanceof SharePhoto) {
                    ShareApi.this.stagePhoto((SharePhoto)o, onMapValueCompleteListener);
                    return;
                }
                onMapValueCompleteListener.onComplete(o);
            }
        }, onMapperCompleteListener);
    }
    
    private void stageOpenGraphAction(final Bundle bundle, final CollectionMapper.OnMapperCompleteListener onMapperCompleteListener) {
        this.stageCollectionValues((CollectionMapper.Collection<Object>)new CollectionMapper.Collection<String>() {
            public Object get(final String s) {
                return bundle.get(s);
            }
            
            @Override
            public Iterator<String> keyIterator() {
                return bundle.keySet().iterator();
            }
            
            public void set(final String s, final Object o, final OnErrorListener onErrorListener) {
                if (!Utility.putJSONValueInBundle(bundle, s, o)) {
                    onErrorListener.onError(new FacebookException("Unexpected value: " + o.toString()));
                }
            }
        }, onMapperCompleteListener);
    }
    
    private void stageOpenGraphObject(final ShareOpenGraphObject shareOpenGraphObject, final CollectionMapper.OnMapValueCompleteListener onMapValueCompleteListener) {
        String s = shareOpenGraphObject.getString("type");
        if (s == null) {
            s = shareOpenGraphObject.getString("og:type");
        }
        if (s == null) {
            ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Open Graph objects must contain a type value."));
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        this.stageCollectionValues((CollectionMapper.Collection<Object>)new CollectionMapper.Collection<String>() {
            public Object get(final String s) {
                return shareOpenGraphObject.get(s);
            }
            
            @Override
            public Iterator<String> keyIterator() {
                return shareOpenGraphObject.keySet().iterator();
            }
            
            public void set(final String s, final Object o, final OnErrorListener onErrorListener) {
                try {
                    jsonObject.put(s, o);
                }
                catch (JSONException ex) {
                    String localizedMessage = ex.getLocalizedMessage();
                    if (localizedMessage == null) {
                        localizedMessage = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(localizedMessage));
                }
            }
        }, new CollectionMapper.OnMapperCompleteListener() {
            final /* synthetic */ GraphRequest.Callback val$requestCallback = new GraphRequest.Callback(this, onMapValueCompleteListener) {
                final /* synthetic */ OnMapValueCompleteListener val$onOpenGraphObjectStagedListener;
                
                @Override
                public void onCompleted(final GraphResponse graphResponse) {
                    final FacebookRequestError error = graphResponse.getError();
                    if (error != null) {
                        String errorMessage = error.getErrorMessage();
                        if (errorMessage == null) {
                            errorMessage = "Error staging Open Graph object.";
                        }
                        ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(new FacebookGraphResponseException(graphResponse, errorMessage));
                        return;
                    }
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    if (jsonObject == null) {
                        ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(new FacebookGraphResponseException(graphResponse, "Error staging Open Graph object."));
                        return;
                    }
                    final String optString = jsonObject.optString("id");
                    if (optString == null) {
                        ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError(new FacebookGraphResponseException(graphResponse, "Error staging Open Graph object."));
                        return;
                    }
                    this.val$onOpenGraphObjectStagedListener.onComplete(optString);
                }
            };
            
            @Override
            public void onComplete() {
                final String string = jsonObject.toString();
                final Bundle bundle = new Bundle();
                bundle.putString("object", string);
                try {
                    new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.this.getGraphPath("objects/" + URLEncoder.encode(s, "UTF-8")), bundle, HttpMethod.POST, this.val$requestCallback).executeAsync();
                }
                catch (UnsupportedEncodingException ex) {
                    String localizedMessage = ex.getLocalizedMessage();
                    if (localizedMessage == null) {
                        localizedMessage = "Error staging Open Graph object.";
                    }
                    ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException(localizedMessage));
                }
            }
            
            @Override
            public void onError(final FacebookException ex) {
                ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(ex);
            }
        });
    }
    
    private void stagePhoto(final SharePhoto sharePhoto, final CollectionMapper.OnMapValueCompleteListener onMapValueCompleteListener) {
        final Bitmap bitmap = sharePhoto.getBitmap();
        final Uri imageUrl = sharePhoto.getImageUrl();
        if (bitmap != null || imageUrl != null) {
            final GraphRequest.Callback callback = new GraphRequest.Callback() {
                @Override
                public void onCompleted(final GraphResponse graphResponse) {
                    final FacebookRequestError error = graphResponse.getError();
                    if (error != null) {
                        String errorMessage = error.getErrorMessage();
                        if (errorMessage == null) {
                            errorMessage = "Error staging photo.";
                        }
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookGraphResponseException(graphResponse, errorMessage));
                        return;
                    }
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    if (jsonObject == null) {
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Error staging photo."));
                        return;
                    }
                    final String optString = jsonObject.optString("uri");
                    if (optString == null) {
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Error staging photo."));
                        return;
                    }
                    final JSONObject jsonObject2 = new JSONObject();
                    try {
                        jsonObject2.put("url", (Object)optString);
                        jsonObject2.put("user_generated", sharePhoto.getUserGenerated());
                        onMapValueCompleteListener.onComplete(jsonObject2);
                    }
                    catch (JSONException ex) {
                        String localizedMessage = ex.getLocalizedMessage();
                        if (localizedMessage == null) {
                            localizedMessage = "Error staging photo.";
                        }
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException(localizedMessage));
                    }
                }
            };
            if (bitmap != null) {
                ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), bitmap, callback).executeAsync();
                return;
            }
            try {
                ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), imageUrl, callback).executeAsync();
                return;
            }
            catch (FileNotFoundException ex) {
                String localizedMessage = ex.getLocalizedMessage();
                if (localizedMessage == null) {
                    localizedMessage = "Error staging photo.";
                }
                ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException(localizedMessage));
                return;
            }
        }
        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Photos must have an imageURL or bitmap."));
    }
    
    public boolean canShare() {
        if (this.getShareContent() != null) {
            final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (currentAccessToken != null) {
                final Set<String> permissions = currentAccessToken.getPermissions();
                if (permissions == null || !permissions.contains("publish_actions")) {
                    Log.w("ShareApi", "The publish_actions permissions are missing, the share will fail unless this app was authorized to publish in another installation.");
                }
                return true;
            }
        }
        return false;
    }
    
    public String getGraphNode() {
        return this.graphNode;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public ShareContent getShareContent() {
        return this.shareContent;
    }
    
    public void setGraphNode(final String graphNode) {
        this.graphNode = graphNode;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public void share(final FacebookCallback<Sharer.Result> facebookCallback) {
        if (!this.canShare()) {
            ShareInternalUtility.invokeCallbackWithError(facebookCallback, "Insufficient permissions for sharing content via Api.");
        }
        else {
            final ShareContent shareContent = this.getShareContent();
            try {
                ShareContentValidation.validateForApiShare(shareContent);
                if (shareContent instanceof ShareLinkContent) {
                    this.shareLinkContent((ShareLinkContent)shareContent, facebookCallback);
                    return;
                }
            }
            catch (FacebookException ex) {
                ShareInternalUtility.invokeCallbackWithException(facebookCallback, ex);
                return;
            }
            if (shareContent instanceof SharePhotoContent) {
                this.sharePhotoContent((SharePhotoContent)shareContent, facebookCallback);
                return;
            }
            if (shareContent instanceof ShareVideoContent) {
                this.shareVideoContent((ShareVideoContent)shareContent, facebookCallback);
                return;
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                this.shareOpenGraphContent((ShareOpenGraphContent)shareContent, facebookCallback);
            }
        }
    }
}
