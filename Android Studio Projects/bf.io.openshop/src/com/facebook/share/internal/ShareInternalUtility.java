package com.facebook.share.internal;

import android.content.*;
import android.graphics.*;
import android.net.*;
import android.util.*;
import com.facebook.share.widget.*;
import android.support.annotation.*;
import com.facebook.share.*;
import com.facebook.appevents.*;
import java.io.*;
import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;
import org.json.*;
import com.facebook.share.model.*;
import java.util.*;

public final class ShareInternalUtility
{
    public static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String STAGING_PARAM = "file";
    
    private static AppCall getAppCallFromActivityResult(final int n, final int n2, final Intent intent) {
        final UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
        if (callIdFromIntent == null) {
            return null;
        }
        return AppCall.finishPendingCall(callIdFromIntent, n);
    }
    
    private static NativeAppCallAttachmentStore.Attachment getAttachment(final UUID uuid, final SharePhoto sharePhoto) {
        final Bitmap bitmap = sharePhoto.getBitmap();
        final Uri imageUrl = sharePhoto.getImageUrl();
        Object attachment;
        if (bitmap != null) {
            attachment = NativeAppCallAttachmentStore.createAttachment(uuid, bitmap);
        }
        else {
            attachment = null;
            if (imageUrl != null) {
                return NativeAppCallAttachmentStore.createAttachment(uuid, imageUrl);
            }
        }
        return (NativeAppCallAttachmentStore.Attachment)attachment;
    }
    
    public static Pair<String, String> getFieldNameAndNamespaceFromFullName(final String s) {
        final int index = s.indexOf(58);
        String substring;
        String substring2;
        if (index != -1 && s.length() > index + 1) {
            substring = s.substring(0, index);
            substring2 = s.substring(index + 1);
        }
        else {
            substring2 = s;
            substring = null;
        }
        return (Pair<String, String>)new Pair((Object)substring, (Object)substring2);
    }
    
    @Nullable
    public static LikeView.ObjectType getMostSpecificObjectType(final LikeView.ObjectType objectType, final LikeView.ObjectType objectType2) {
        if (objectType != objectType2) {
            if (objectType == LikeView.ObjectType.UNKNOWN) {
                return objectType2;
            }
            if (objectType2 != LikeView.ObjectType.UNKNOWN) {
                return null;
            }
        }
        return objectType;
    }
    
    public static String getNativeDialogCompletionGesture(final Bundle bundle) {
        if (bundle.containsKey("completionGesture")) {
            return bundle.getString("completionGesture");
        }
        return bundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
    }
    
    public static List<String> getPhotoUrls(final SharePhotoContent sharePhotoContent, final UUID uuid) {
        if (sharePhotoContent != null) {
            final List<SharePhoto> photos = sharePhotoContent.getPhotos();
            if (photos != null) {
                final List<Object> map = Utility.map((List<Object>)photos, (Utility.Mapper<Object, Object>)new Utility.Mapper<SharePhoto, NativeAppCallAttachmentStore.Attachment>() {
                    public NativeAppCallAttachmentStore.Attachment apply(final SharePhoto sharePhoto) {
                        return getAttachment(uuid, sharePhoto);
                    }
                });
                final List<String> map2 = Utility.map(map, (Utility.Mapper<Object, String>)new Utility.Mapper<NativeAppCallAttachmentStore.Attachment, String>() {
                    public String apply(final NativeAppCallAttachmentStore.Attachment attachment) {
                        return attachment.getAttachmentUrl();
                    }
                });
                NativeAppCallAttachmentStore.addAttachments((Collection<NativeAppCallAttachmentStore.Attachment>)map);
                return map2;
            }
        }
        return null;
    }
    
    public static String getShareDialogPostId(final Bundle bundle) {
        if (bundle.containsKey("postId")) {
            return bundle.getString("postId");
        }
        if (bundle.containsKey("com.facebook.platform.extra.POST_ID")) {
            return bundle.getString("com.facebook.platform.extra.POST_ID");
        }
        return bundle.getString("post_id");
    }
    
    public static ResultProcessor getShareResultProcessor(final FacebookCallback<Sharer.Result> facebookCallback) {
        return new ResultProcessor(facebookCallback) {
            @Override
            public void onCancel(final AppCall appCall) {
                ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
            }
            
            @Override
            public void onError(final AppCall appCall, final FacebookException ex) {
                ShareInternalUtility.invokeOnErrorCallback(facebookCallback, ex);
            }
            
            @Override
            public void onSuccess(final AppCall appCall, final Bundle bundle) {
                if (bundle != null) {
                    final String nativeDialogCompletionGesture = ShareInternalUtility.getNativeDialogCompletionGesture(bundle);
                    if (nativeDialogCompletionGesture == null || "post".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                        ShareInternalUtility.invokeOnSuccessCallback(facebookCallback, ShareInternalUtility.getShareDialogPostId(bundle));
                    }
                    else {
                        if ("cancel".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                            ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
                            return;
                        }
                        ShareInternalUtility.invokeOnErrorCallback(facebookCallback, new FacebookException("UnknownError"));
                    }
                }
            }
        };
    }
    
    public static String getVideoUrl(final ShareVideoContent shareVideoContent, final UUID uuid) {
        if (shareVideoContent == null || shareVideoContent.getVideo() == null) {
            return null;
        }
        final NativeAppCallAttachmentStore.Attachment attachment = NativeAppCallAttachmentStore.createAttachment(uuid, shareVideoContent.getVideo().getLocalUrl());
        final ArrayList<NativeAppCallAttachmentStore.Attachment> list = new ArrayList<NativeAppCallAttachmentStore.Attachment>(1);
        list.add(attachment);
        NativeAppCallAttachmentStore.addAttachments(list);
        return attachment.getAttachmentUrl();
    }
    
    public static boolean handleActivityResult(final int n, final int n2, final Intent intent, final ResultProcessor resultProcessor) {
        boolean b = true;
        final AppCall appCallFromActivityResult = getAppCallFromActivityResult(n, n2, intent);
        if (appCallFromActivityResult == null) {
            b = false;
        }
        else {
            NativeAppCallAttachmentStore.cleanupAttachmentsForCall(appCallFromActivityResult.getCallId());
            if (resultProcessor != null) {
                final FacebookException exceptionFromErrorData = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getErrorDataFromResultIntent(intent));
                if (exceptionFromErrorData == null) {
                    resultProcessor.onSuccess(appCallFromActivityResult, NativeProtocol.getSuccessResultsFromIntent(intent));
                    return b;
                }
                if (exceptionFromErrorData instanceof FacebookOperationCanceledException) {
                    resultProcessor.onCancel(appCallFromActivityResult);
                    return b;
                }
                resultProcessor.onError(appCallFromActivityResult, exceptionFromErrorData);
                return b;
            }
        }
        return b;
    }
    
    public static void invokeCallbackWithError(final FacebookCallback<Sharer.Result> facebookCallback, final String s) {
        invokeOnErrorCallback(facebookCallback, s);
    }
    
    public static void invokeCallbackWithException(final FacebookCallback<Sharer.Result> facebookCallback, final Exception ex) {
        if (ex instanceof FacebookException) {
            invokeOnErrorCallback(facebookCallback, (FacebookException)ex);
            return;
        }
        invokeCallbackWithError(facebookCallback, "Error preparing share content: " + ex.getLocalizedMessage());
    }
    
    public static void invokeCallbackWithResults(final FacebookCallback<Sharer.Result> facebookCallback, final String s, final GraphResponse graphResponse) {
        final FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            String errorMessage = error.getErrorMessage();
            if (Utility.isNullOrEmpty(errorMessage)) {
                errorMessage = "Unexpected error sharing.";
            }
            invokeOnErrorCallback(facebookCallback, graphResponse, errorMessage);
            return;
        }
        invokeOnSuccessCallback(facebookCallback, s);
    }
    
    static void invokeOnCancelCallback(final FacebookCallback<Sharer.Result> facebookCallback) {
        logShareResult("cancelled", null);
        if (facebookCallback != null) {
            facebookCallback.onCancel();
        }
    }
    
    static void invokeOnErrorCallback(final FacebookCallback<Sharer.Result> facebookCallback, final FacebookException ex) {
        logShareResult("error", ex.getMessage());
        if (facebookCallback != null) {
            facebookCallback.onError(ex);
        }
    }
    
    static void invokeOnErrorCallback(final FacebookCallback<Sharer.Result> facebookCallback, final GraphResponse graphResponse, final String s) {
        logShareResult("error", s);
        if (facebookCallback != null) {
            facebookCallback.onError(new FacebookGraphResponseException(graphResponse, s));
        }
    }
    
    static void invokeOnErrorCallback(final FacebookCallback<Sharer.Result> facebookCallback, final String s) {
        logShareResult("error", s);
        if (facebookCallback != null) {
            facebookCallback.onError(new FacebookException(s));
        }
    }
    
    static void invokeOnSuccessCallback(final FacebookCallback<Sharer.Result> facebookCallback, final String s) {
        logShareResult("succeeded", null);
        if (facebookCallback != null) {
            facebookCallback.onSuccess(new Sharer.Result(s));
        }
    }
    
    private static void logShareResult(final String s, final String s2) {
        final AppEventsLogger logger = AppEventsLogger.newLogger(FacebookSdk.getApplicationContext());
        final Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_outcome", s);
        if (s2 != null) {
            bundle.putString("error_message", s2);
        }
        logger.logSdkEvent("fb_share_dialog_result", null, bundle);
    }
    
    public static GraphRequest newUploadStagingResourceWithImageRequest(final AccessToken accessToken, final Bitmap bitmap, final GraphRequest.Callback callback) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", (Parcelable)bitmap);
        return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, callback);
    }
    
    public static GraphRequest newUploadStagingResourceWithImageRequest(final AccessToken accessToken, final Uri uri, final GraphRequest.Callback callback) throws FileNotFoundException {
        if (Utility.isFileUri(uri)) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(uri.getPath()), callback);
        }
        if (!Utility.isContentUri(uri)) {
            throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
        }
        final GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType((Parcelable)uri, "image/png");
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", (Parcelable)parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, callback);
    }
    
    public static GraphRequest newUploadStagingResourceWithImageRequest(final AccessToken accessToken, final File file, final GraphRequest.Callback callback) throws FileNotFoundException {
        final GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType((Parcelable)ParcelFileDescriptor.open(file, 268435456), "image/png");
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", (Parcelable)parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, callback);
    }
    
    public static void registerSharerCallback(final int n, final CallbackManager callbackManager, final FacebookCallback<Sharer.Result> facebookCallback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl)callbackManager).registerCallback(n, (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(n, n, intent, ShareInternalUtility.getShareResultProcessor(facebookCallback));
            }
        });
    }
    
    public static void registerStaticShareCallback(final int n) {
        CallbackManagerImpl.registerStaticCallback(n, (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(n, n, intent, ShareInternalUtility.getShareResultProcessor(null));
            }
        });
    }
    
    public static JSONArray removeNamespacesFromOGJsonArray(final JSONArray jsonArray, final boolean b) throws JSONException {
        final JSONArray jsonArray2 = new JSONArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            Object o = jsonArray.get(i);
            if (o instanceof JSONArray) {
                o = removeNamespacesFromOGJsonArray((JSONArray)o, b);
            }
            else if (o instanceof JSONObject) {
                o = removeNamespacesFromOGJsonObject((JSONObject)o, b);
            }
            jsonArray2.put(o);
        }
        return jsonArray2;
    }
    
    public static JSONObject removeNamespacesFromOGJsonObject(final JSONObject jsonObject, final boolean b) {
        JSONObject jsonObject2 = null;
        if (jsonObject == null) {
            jsonObject2 = null;
        }
        else {
            JSONObject jsonObject3;
            while (true) {
                while (true) {
                    int n = 0;
                    Label_0271: {
                        String string = null;
                        Object o = null;
                        String s = null;
                        String s2 = null;
                        Label_0213: {
                            try {
                                jsonObject2 = new JSONObject();
                                jsonObject3 = new JSONObject();
                                final JSONArray names = jsonObject.names();
                                n = 0;
                                if (n >= names.length()) {
                                    break;
                                }
                                string = names.getString(n);
                                o = jsonObject.get(string);
                                if (o instanceof JSONObject) {
                                    o = removeNamespacesFromOGJsonObject((JSONObject)o, true);
                                }
                                else if (o instanceof JSONArray) {
                                    o = removeNamespacesFromOGJsonArray((JSONArray)o, true);
                                }
                                final Pair<String, String> fieldNameAndNamespaceFromFullName = getFieldNameAndNamespaceFromFullName(string);
                                s = (String)fieldNameAndNamespaceFromFullName.first;
                                s2 = (String)fieldNameAndNamespaceFromFullName.second;
                                if (!b) {
                                    break Label_0213;
                                }
                                if (s != null && s.equals("fbsdk")) {
                                    jsonObject2.put(string, o);
                                    break Label_0271;
                                }
                                if (s == null || s.equals("og")) {
                                    jsonObject2.put(s2, o);
                                    break Label_0271;
                                }
                            }
                            catch (JSONException ex) {
                                throw new FacebookException("Failed to create json object from share content");
                            }
                            jsonObject3.put(s2, o);
                            break Label_0271;
                        }
                        if (s != null && s.equals("fb")) {
                            jsonObject2.put(string, o);
                        }
                        else {
                            jsonObject2.put(s2, o);
                        }
                    }
                    ++n;
                    continue;
                }
            }
            if (jsonObject3.length() > 0) {
                jsonObject2.put("data", (Object)jsonObject3);
                return jsonObject2;
            }
        }
        return jsonObject2;
    }
    
    public static JSONObject toJSONObjectForCall(final UUID uuid, final ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        final ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        final ArrayList<NativeAppCallAttachmentStore.Attachment> list = new ArrayList<NativeAppCallAttachmentStore.Attachment>();
        final JSONObject jsonObject = OpenGraphJSONUtility.toJSONObject(action, (OpenGraphJSONUtility.PhotoJSONProcessor)new OpenGraphJSONUtility.PhotoJSONProcessor() {
            @Override
            public JSONObject toJSONObject(final SharePhoto sharePhoto) {
                final NativeAppCallAttachmentStore.Attachment access$000 = getAttachment(uuid, sharePhoto);
                JSONObject jsonObject;
                if (access$000 == null) {
                    jsonObject = null;
                }
                else {
                    list.add(access$000);
                    jsonObject = new JSONObject();
                    try {
                        jsonObject.put("url", (Object)access$000.getAttachmentUrl());
                        if (sharePhoto.getUserGenerated()) {
                            jsonObject.put("user_generated", true);
                            return jsonObject;
                        }
                    }
                    catch (JSONException ex) {
                        throw new FacebookException("Unable to attach images", (Throwable)ex);
                    }
                }
                return jsonObject;
            }
        });
        NativeAppCallAttachmentStore.addAttachments(list);
        if (shareOpenGraphContent.getPlaceId() != null && Utility.isNullOrEmpty(jsonObject.optString("place"))) {
            jsonObject.put("place", (Object)shareOpenGraphContent.getPlaceId());
        }
        if (shareOpenGraphContent.getPeopleIds() != null) {
            final JSONArray optJSONArray = jsonObject.optJSONArray("tags");
            Set<String> jsonArrayToSet;
            if (optJSONArray == null) {
                jsonArrayToSet = new HashSet<String>();
            }
            else {
                jsonArrayToSet = Utility.jsonArrayToSet(optJSONArray);
            }
            final Iterator<String> iterator = shareOpenGraphContent.getPeopleIds().iterator();
            while (iterator.hasNext()) {
                jsonArrayToSet.add(iterator.next());
            }
            jsonObject.put("tags", (Object)new ArrayList(jsonArrayToSet));
        }
        return jsonObject;
    }
    
    public static JSONObject toJSONObjectForWeb(final ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return OpenGraphJSONUtility.toJSONObject(shareOpenGraphContent.getAction(), (OpenGraphJSONUtility.PhotoJSONProcessor)new OpenGraphJSONUtility.PhotoJSONProcessor() {
            @Override
            public JSONObject toJSONObject(final SharePhoto sharePhoto) {
                final Uri imageUrl = sharePhoto.getImageUrl();
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("url", (Object)imageUrl.toString());
                    return jsonObject;
                }
                catch (JSONException ex) {
                    throw new FacebookException("Unable to attach images", (Throwable)ex);
                }
            }
        });
    }
}
