package com.facebook;

import com.facebook.share.internal.*;
import com.facebook.share.model.*;
import android.net.*;
import java.util.regex.*;
import android.content.*;
import com.facebook.internal.*;
import org.json.*;
import android.location.*;
import java.text.*;
import java.util.*;
import android.util.*;
import android.text.*;
import java.net.*;
import android.graphics.*;
import java.io.*;
import android.os.*;

public class GraphRequest
{
    private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
    private static final String ACCESS_TOKEN_PARAM = "access_token";
    private static final String ATTACHED_FILES_PARAM = "attached_files";
    private static final String ATTACHMENT_FILENAME_PREFIX = "file";
    private static final String BATCH_APP_ID_PARAM = "batch_app_id";
    private static final String BATCH_BODY_PARAM = "body";
    private static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
    private static final String BATCH_ENTRY_NAME_PARAM = "name";
    private static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
    private static final String BATCH_METHOD_PARAM = "method";
    private static final String BATCH_PARAM = "batch";
    private static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
    private static final String CAPTION_PARAM = "caption";
    private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String DEBUG_KEY = "__debug__";
    private static final String DEBUG_MESSAGES_KEY = "messages";
    private static final String DEBUG_MESSAGE_KEY = "message";
    private static final String DEBUG_MESSAGE_LINK_KEY = "link";
    private static final String DEBUG_MESSAGE_TYPE_KEY = "type";
    private static final String DEBUG_PARAM = "debug";
    private static final String DEBUG_SEVERITY_INFO = "info";
    private static final String DEBUG_SEVERITY_WARNING = "warning";
    public static final String FIELDS_PARAM = "fields";
    private static final String FORMAT_JSON = "json";
    private static final String FORMAT_PARAM = "format";
    private static final String GRAPH_PATH_FORMAT = "%s/%s";
    private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final int MAXIMUM_BATCH_SIZE = 50;
    private static final String ME = "me";
    private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String MY_FRIENDS = "me/friends";
    private static final String MY_PHOTOS = "me/photos";
    private static final String PICTURE_PARAM = "picture";
    private static final String SDK_ANDROID = "android";
    private static final String SDK_PARAM = "sdk";
    private static final String SEARCH = "search";
    public static final String TAG;
    private static final String USER_AGENT_BASE = "FBAndroidSDK";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String VIDEOS_SUFFIX = "/videos";
    private static String defaultBatchApplicationId;
    private static volatile String userAgent;
    private static Pattern versionPattern;
    private AccessToken accessToken;
    private String batchEntryDependsOn;
    private String batchEntryName;
    private boolean batchEntryOmitResultOnSuccess;
    private Callback callback;
    private JSONObject graphObject;
    private String graphPath;
    private HttpMethod httpMethod;
    private String overriddenURL;
    private Bundle parameters;
    private boolean skipClientToken;
    private Object tag;
    private String version;
    
    static {
        TAG = GraphRequest.class.getSimpleName();
        GraphRequest.versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    }
    
    public GraphRequest() {
        this(null, null, null, null, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String s) {
        this(accessToken, s, null, null, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String s, final Bundle bundle, final HttpMethod httpMethod) {
        this(accessToken, s, bundle, httpMethod, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String s, final Bundle bundle, final HttpMethod httpMethod, final Callback callback) {
        this(accessToken, s, bundle, httpMethod, callback, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String graphPath, final Bundle bundle, final HttpMethod httpMethod, final Callback callback, final String version) {
        this.batchEntryOmitResultOnSuccess = true;
        this.skipClientToken = false;
        this.accessToken = accessToken;
        this.graphPath = graphPath;
        this.version = version;
        this.setCallback(callback);
        this.setHttpMethod(httpMethod);
        if (bundle != null) {
            this.parameters = new Bundle(bundle);
        }
        else {
            this.parameters = new Bundle();
        }
        if (this.version == null) {
            this.version = ServerProtocol.getAPIVersion();
        }
    }
    
    GraphRequest(final AccessToken accessToken, final URL url) {
        this.batchEntryOmitResultOnSuccess = true;
        this.skipClientToken = false;
        this.accessToken = accessToken;
        this.overriddenURL = url.toString();
        this.setHttpMethod(HttpMethod.GET);
        this.parameters = new Bundle();
    }
    
    private void addCommonParameters() {
        if (this.accessToken != null) {
            if (!this.parameters.containsKey("access_token")) {
                final String token = this.accessToken.getToken();
                Logger.registerAccessToken(token);
                this.parameters.putString("access_token", token);
            }
        }
        else if (!this.skipClientToken && !this.parameters.containsKey("access_token")) {
            final String applicationId = FacebookSdk.getApplicationId();
            final String clientToken = FacebookSdk.getClientToken();
            if (!Utility.isNullOrEmpty(applicationId) && !Utility.isNullOrEmpty(clientToken)) {
                this.parameters.putString("access_token", applicationId + "|" + clientToken);
            }
            else {
                Log.d(GraphRequest.TAG, "Warning: Request without access token missing application ID or client token.");
            }
        }
        this.parameters.putString("sdk", "android");
        this.parameters.putString("format", "json");
        this.parameters.putString("locale", Locale.getDefault().toString());
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO)) {
            this.parameters.putString("debug", "info");
        }
        else if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.parameters.putString("debug", "warning");
        }
    }
    
    private String appendParametersToBaseUrl(final String s) {
        final Uri$Builder encodedPath = new Uri$Builder().encodedPath(s);
        for (final String s2 : this.parameters.keySet()) {
            Object value = this.parameters.get(s2);
            if (value == null) {
                value = "";
            }
            if (isSupportedParameterType(value)) {
                encodedPath.appendQueryParameter(s2, parameterToString(value).toString());
            }
            else {
                if (this.httpMethod == HttpMethod.GET) {
                    throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", value.getClass().getSimpleName()));
                }
                continue;
            }
        }
        return encodedPath.toString();
    }
    
    private static HttpURLConnection createConnection(final URL url) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", getUserAgent());
        httpURLConnection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }
    
    public static GraphRequest createOpenGraphObject(final ShareOpenGraphObject shareOpenGraphObject) throws FacebookException {
        String s = shareOpenGraphObject.getString("type");
        if (s == null) {
            s = shareOpenGraphObject.getString("og:type");
        }
        if (s == null) {
            throw new FacebookException("Open graph object type cannot be null");
        }
        try {
            final JSONObject jsonObject = (JSONObject)OpenGraphJSONUtility.toJSONValue(shareOpenGraphObject, (OpenGraphJSONUtility.PhotoJSONProcessor)new OpenGraphJSONUtility.PhotoJSONProcessor() {
                @Override
                public JSONObject toJSONObject(final SharePhoto sharePhoto) {
                    final Uri imageUrl = sharePhoto.getImageUrl();
                    final JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("url", (Object)imageUrl.toString());
                        return jsonObject;
                    }
                    catch (Exception ex) {
                        throw new FacebookException("Unable to attach images", ex);
                    }
                }
            });
            final String s2 = s;
            final Bundle bundle = new Bundle();
            bundle.putString("object", jsonObject.toString());
            return new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.ROOT, "%s/%s", "me", "objects/" + s2), bundle, HttpMethod.POST);
        }
        catch (JSONException ex) {
            throw new FacebookException(ex.getMessage());
        }
    }
    
    public static GraphResponse executeAndWait(final GraphRequest graphRequest) {
        final List<GraphResponse> executeBatchAndWait = executeBatchAndWait(graphRequest);
        if (executeBatchAndWait == null || executeBatchAndWait.size() != 1) {
            throw new FacebookException("invalid state: expected a single response");
        }
        return executeBatchAndWait.get(0);
    }
    
    public static List<GraphResponse> executeBatchAndWait(final GraphRequestBatch graphRequestBatch) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)graphRequestBatch, "requests");
        HttpURLConnection httpConnection = null;
        try {
            httpConnection = toHttpConnection(graphRequestBatch);
            return executeConnectionAndWait(httpConnection, graphRequestBatch);
        }
        catch (Exception ex) {
            final List<GraphResponse> constructErrorResponses = GraphResponse.constructErrorResponses(graphRequestBatch.getRequests(), null, new FacebookException(ex));
            runCallbacks(graphRequestBatch, constructErrorResponses);
            Utility.disconnectQuietly(null);
            return constructErrorResponses;
        }
        finally {
            Utility.disconnectQuietly(httpConnection);
        }
    }
    
    public static List<GraphResponse> executeBatchAndWait(final Collection<GraphRequest> collection) {
        return executeBatchAndWait(new GraphRequestBatch(collection));
    }
    
    public static List<GraphResponse> executeBatchAndWait(final GraphRequest... array) {
        Validate.notNull(array, "requests");
        return executeBatchAndWait(Arrays.asList(array));
    }
    
    public static GraphRequestAsyncTask executeBatchAsync(final GraphRequestBatch graphRequestBatch) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)graphRequestBatch, "requests");
        final GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(graphRequestBatch);
        graphRequestAsyncTask.executeOnExecutor(FacebookSdk.getExecutor(), (Object[])null);
        return graphRequestAsyncTask;
    }
    
    public static GraphRequestAsyncTask executeBatchAsync(final Collection<GraphRequest> collection) {
        return executeBatchAsync(new GraphRequestBatch(collection));
    }
    
    public static GraphRequestAsyncTask executeBatchAsync(final GraphRequest... array) {
        Validate.notNull(array, "requests");
        return executeBatchAsync(Arrays.asList(array));
    }
    
    public static List<GraphResponse> executeConnectionAndWait(final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        final List<GraphResponse> fromHttpConnection = GraphResponse.fromHttpConnection(httpURLConnection, graphRequestBatch);
        Utility.disconnectQuietly(httpURLConnection);
        final int size = graphRequestBatch.size();
        if (size != fromHttpConnection.size()) {
            throw new FacebookException(String.format(Locale.US, "Received %d responses while expecting %d", fromHttpConnection.size(), size));
        }
        runCallbacks(graphRequestBatch, fromHttpConnection);
        AccessTokenManager.getInstance().extendAccessTokenIfNeeded();
        return fromHttpConnection;
    }
    
    public static List<GraphResponse> executeConnectionAndWait(final HttpURLConnection httpURLConnection, final Collection<GraphRequest> collection) {
        return executeConnectionAndWait(httpURLConnection, new GraphRequestBatch(collection));
    }
    
    public static GraphRequestAsyncTask executeConnectionAsync(final Handler callbackHandler, final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        Validate.notNull(httpURLConnection, "connection");
        final GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(httpURLConnection, graphRequestBatch);
        graphRequestBatch.setCallbackHandler(callbackHandler);
        graphRequestAsyncTask.executeOnExecutor(FacebookSdk.getExecutor(), (Object[])null);
        return graphRequestAsyncTask;
    }
    
    public static GraphRequestAsyncTask executeConnectionAsync(final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        return executeConnectionAsync(null, httpURLConnection, graphRequestBatch);
    }
    
    private static String getBatchAppId(final GraphRequestBatch graphRequestBatch) {
        if (!Utility.isNullOrEmpty(graphRequestBatch.getBatchApplicationId())) {
            return graphRequestBatch.getBatchApplicationId();
        }
        final Iterator<GraphRequest> iterator = graphRequestBatch.iterator();
        while (iterator.hasNext()) {
            final AccessToken accessToken = iterator.next().accessToken;
            if (accessToken != null) {
                final String applicationId = accessToken.getApplicationId();
                if (applicationId != null) {
                    return applicationId;
                }
                continue;
            }
        }
        if (!Utility.isNullOrEmpty(GraphRequest.defaultBatchApplicationId)) {
            return GraphRequest.defaultBatchApplicationId;
        }
        return FacebookSdk.getApplicationId();
    }
    
    public static final String getDefaultBatchApplicationId() {
        return GraphRequest.defaultBatchApplicationId;
    }
    
    private static String getDefaultPhotoPathIfNull(String s) {
        if (s == null) {
            s = "me/photos";
        }
        return s;
    }
    
    private String getGraphPathWithVersion() {
        if (GraphRequest.versionPattern.matcher(this.graphPath).matches()) {
            return this.graphPath;
        }
        return String.format("%s/%s", this.version, this.graphPath);
    }
    
    private static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
    }
    
    private static String getUserAgent() {
        if (GraphRequest.userAgent == null) {
            GraphRequest.userAgent = String.format("%s.%s", "FBAndroidSDK", "4.9.0");
            final String customUserAgent = InternalSettings.getCustomUserAgent();
            if (!Utility.isNullOrEmpty(customUserAgent)) {
                GraphRequest.userAgent = String.format(Locale.ROOT, "%s/%s", GraphRequest.userAgent, customUserAgent);
            }
        }
        return GraphRequest.userAgent;
    }
    
    private static boolean hasOnProgressCallbacks(final GraphRequestBatch graphRequestBatch) {
        final Iterator<GraphRequestBatch.Callback> iterator = graphRequestBatch.getCallbacks().iterator();
        while (iterator.hasNext()) {
            if (((GraphRequestBatch.Callback)iterator.next()) instanceof GraphRequestBatch.OnProgressCallback) {
                return true;
            }
        }
        final Iterator<GraphRequest> iterator2 = graphRequestBatch.iterator();
        while (iterator2.hasNext()) {
            if (iterator2.next().getCallback() instanceof OnProgressCallback) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isGzipCompressible(final GraphRequestBatch graphRequestBatch) {
        for (final GraphRequest graphRequest : graphRequestBatch) {
            final Iterator iterator2 = graphRequest.parameters.keySet().iterator();
            while (iterator2.hasNext()) {
                if (isSupportedAttachmentType(graphRequest.parameters.get((String)iterator2.next()))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean isMeRequest(String group) {
        final Matcher matcher = GraphRequest.versionPattern.matcher(group);
        if (matcher.matches()) {
            group = matcher.group(1);
        }
        return group.startsWith("me/") || group.startsWith("/me/");
    }
    
    private static boolean isSupportedAttachmentType(final Object o) {
        return o instanceof Bitmap || o instanceof byte[] || o instanceof Uri || o instanceof ParcelFileDescriptor || o instanceof ParcelableResourceWithMimeType;
    }
    
    private static boolean isSupportedParameterType(final Object o) {
        return o instanceof String || o instanceof Boolean || o instanceof Number || o instanceof Date;
    }
    
    public static GraphRequest newCustomAudienceThirdPartyIdRequest(final AccessToken accessToken, final Context context, final Callback callback) {
        return newCustomAudienceThirdPartyIdRequest(accessToken, context, null, callback);
    }
    
    public static GraphRequest newCustomAudienceThirdPartyIdRequest(final AccessToken accessToken, final Context context, String s, final Callback callback) {
        if (s == null && accessToken != null) {
            s = accessToken.getApplicationId();
        }
        if (s == null) {
            s = Utility.getMetadataApplicationId(context);
        }
        if (s == null) {
            throw new FacebookException("Facebook App ID cannot be determined");
        }
        final String string = s + "/custom_audience_third_party_id";
        final AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        final Bundle bundle = new Bundle();
        if (accessToken == null) {
            if (attributionIdentifiers == null) {
                throw new FacebookException("There is no access token and attribution identifiers could not be retrieved");
            }
            String s2;
            if (attributionIdentifiers.getAttributionId() != null) {
                s2 = attributionIdentifiers.getAttributionId();
            }
            else {
                s2 = attributionIdentifiers.getAndroidAdvertiserId();
            }
            if (attributionIdentifiers.getAttributionId() != null) {
                bundle.putString("udid", s2);
            }
        }
        if (FacebookSdk.getLimitEventAndDataUsage(context) || (attributionIdentifiers != null && attributionIdentifiers.isTrackingLimited())) {
            bundle.putString("limit_event_usage", "1");
        }
        return new GraphRequest(accessToken, string, bundle, HttpMethod.GET, callback);
    }
    
    public static GraphRequest newDeleteObjectRequest(final AccessToken accessToken, final String s, final Callback callback) {
        return new GraphRequest(accessToken, s, null, HttpMethod.DELETE, callback);
    }
    
    public static GraphRequest newGraphPathRequest(final AccessToken accessToken, final String s, final Callback callback) {
        return new GraphRequest(accessToken, s, null, null, callback);
    }
    
    public static GraphRequest newMeRequest(final AccessToken accessToken, final GraphJSONObjectCallback graphJSONObjectCallback) {
        return new GraphRequest(accessToken, "me", null, null, (Callback)new Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphJSONObjectCallback != null) {
                    graphJSONObjectCallback.onCompleted(graphResponse.getJSONObject(), graphResponse);
                }
            }
        });
    }
    
    public static GraphRequest newMyFriendsRequest(final AccessToken accessToken, final GraphJSONArrayCallback graphJSONArrayCallback) {
        return new GraphRequest(accessToken, "me/friends", null, null, (Callback)new Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphJSONArrayCallback != null) {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    JSONArray optJSONArray;
                    if (jsonObject != null) {
                        optJSONArray = jsonObject.optJSONArray("data");
                    }
                    else {
                        optJSONArray = null;
                    }
                    graphJSONArrayCallback.onCompleted(optJSONArray, graphResponse);
                }
            }
        });
    }
    
    public static GraphRequest newPlacesSearchRequest(final AccessToken accessToken, final Location location, final int n, final int n2, final String s, final GraphJSONArrayCallback graphJSONArrayCallback) {
        if (location == null && Utility.isNullOrEmpty(s)) {
            throw new FacebookException("Either location or searchText must be specified.");
        }
        final Bundle bundle = new Bundle(5);
        bundle.putString("type", "place");
        bundle.putInt("limit", n2);
        if (location != null) {
            bundle.putString("center", String.format(Locale.US, "%f,%f", location.getLatitude(), location.getLongitude()));
            bundle.putInt("distance", n);
        }
        if (!Utility.isNullOrEmpty(s)) {
            bundle.putString("q", s);
        }
        return new GraphRequest(accessToken, "search", bundle, HttpMethod.GET, (Callback)new Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphJSONArrayCallback != null) {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    JSONArray optJSONArray;
                    if (jsonObject != null) {
                        optJSONArray = jsonObject.optJSONArray("data");
                    }
                    else {
                        optJSONArray = null;
                    }
                    graphJSONArrayCallback.onCompleted(optJSONArray, graphResponse);
                }
            }
        });
    }
    
    public static GraphRequest newPostRequest(final AccessToken accessToken, final String s, final JSONObject graphObject, final Callback callback) {
        final GraphRequest graphRequest = new GraphRequest(accessToken, s, null, HttpMethod.POST, callback);
        graphRequest.setGraphObject(graphObject);
        return graphRequest;
    }
    
    public static GraphRequest newUploadPhotoRequest(final AccessToken accessToken, final String s, final Bitmap bitmap, final String s2, final Bundle bundle, final Callback callback) {
        final String defaultPhotoPathIfNull = getDefaultPhotoPathIfNull(s);
        final Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", (Parcelable)bitmap);
        if (s2 != null && !s2.isEmpty()) {
            bundle2.putString("caption", s2);
        }
        return new GraphRequest(accessToken, defaultPhotoPathIfNull, bundle2, HttpMethod.POST, callback);
    }
    
    public static GraphRequest newUploadPhotoRequest(final AccessToken accessToken, final String s, final Uri uri, final String s2, final Bundle bundle, final Callback callback) throws FileNotFoundException {
        final String defaultPhotoPathIfNull = getDefaultPhotoPathIfNull(s);
        if (Utility.isFileUri(uri)) {
            return newUploadPhotoRequest(accessToken, defaultPhotoPathIfNull, new File(uri.getPath()), s2, bundle, callback);
        }
        if (!Utility.isContentUri(uri)) {
            throw new FacebookException("The photo Uri must be either a file:// or content:// Uri");
        }
        final Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", (Parcelable)uri);
        return new GraphRequest(accessToken, defaultPhotoPathIfNull, bundle2, HttpMethod.POST, callback);
    }
    
    public static GraphRequest newUploadPhotoRequest(final AccessToken accessToken, final String s, final File file, final String s2, final Bundle bundle, final Callback callback) throws FileNotFoundException {
        final String defaultPhotoPathIfNull = getDefaultPhotoPathIfNull(s);
        final ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
        final Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", (Parcelable)open);
        if (s2 != null && !s2.isEmpty()) {
            bundle2.putString("caption", s2);
        }
        return new GraphRequest(accessToken, defaultPhotoPathIfNull, bundle2, HttpMethod.POST, callback);
    }
    
    private static String parameterToString(final Object o) {
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Boolean || o instanceof Number) {
            return o.toString();
        }
        if (o instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(o);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }
    
    private static void processGraphObject(final JSONObject jsonObject, final String s, final KeyValueSerializer keyValueSerializer) throws IOException {
        final boolean meRequest = isMeRequest(s);
        boolean b = false;
        if (meRequest) {
            final int index = s.indexOf(":");
            final int index2 = s.indexOf("?");
            if (index > 3 && (index2 == -1 || index < index2)) {
                b = true;
            }
            else {
                b = false;
            }
        }
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s2 = keys.next();
            processGraphObjectProperty(s2, jsonObject.opt(s2), keyValueSerializer, b && s2.equalsIgnoreCase("image"));
        }
    }
    
    private static void processGraphObjectProperty(final String s, final Object o, final KeyValueSerializer keyValueSerializer, final boolean b) throws IOException {
        final Class<?> class1 = o.getClass();
        if (JSONObject.class.isAssignableFrom(class1)) {
            final JSONObject jsonObject = (JSONObject)o;
            if (b) {
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    processGraphObjectProperty(String.format("%s[%s]", s, s2), jsonObject.opt(s2), keyValueSerializer, b);
                }
            }
            else if (jsonObject.has("id")) {
                processGraphObjectProperty(s, jsonObject.optString("id"), keyValueSerializer, b);
            }
            else {
                if (jsonObject.has("url")) {
                    processGraphObjectProperty(s, jsonObject.optString("url"), keyValueSerializer, b);
                    return;
                }
                if (jsonObject.has("fbsdk:create_object")) {
                    processGraphObjectProperty(s, jsonObject.toString(), keyValueSerializer, b);
                }
            }
        }
        else if (JSONArray.class.isAssignableFrom(class1)) {
            final JSONArray jsonArray = (JSONArray)o;
            for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                processGraphObjectProperty(String.format(Locale.ROOT, "%s[%d]", s, i), jsonArray.opt(i), keyValueSerializer, b);
            }
        }
        else {
            if (String.class.isAssignableFrom(class1) || Number.class.isAssignableFrom(class1) || Boolean.class.isAssignableFrom(class1)) {
                keyValueSerializer.writeString(s, o.toString());
                return;
            }
            if (Date.class.isAssignableFrom(class1)) {
                keyValueSerializer.writeString(s, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date)o));
            }
        }
    }
    
    private static void processRequest(final GraphRequestBatch graphRequestBatch, final Logger logger, final int n, final URL url, final OutputStream outputStream, final boolean b) throws IOException, JSONException {
        final Serializer serializer = new Serializer(outputStream, logger, b);
        if (n == 1) {
            final GraphRequest value = graphRequestBatch.get(0);
            final HashMap<String, Attachment> hashMap = new HashMap<String, Attachment>();
            for (final String s : value.parameters.keySet()) {
                final Object value2 = value.parameters.get(s);
                if (isSupportedAttachmentType(value2)) {
                    hashMap.put(s, new Attachment(value, value2));
                }
            }
            if (logger != null) {
                logger.append("  Parameters:\n");
            }
            serializeParameters(value.parameters, serializer, value);
            if (logger != null) {
                logger.append("  Attachments:\n");
            }
            serializeAttachments(hashMap, serializer);
            if (value.graphObject != null) {
                processGraphObject(value.graphObject, url.getPath(), (KeyValueSerializer)serializer);
            }
            return;
        }
        final String batchAppId = getBatchAppId(graphRequestBatch);
        if (Utility.isNullOrEmpty(batchAppId)) {
            throw new FacebookException("App ID was not specified at the request or Settings.");
        }
        serializer.writeString("batch_app_id", batchAppId);
        final HashMap<String, Attachment> hashMap2 = new HashMap<String, Attachment>();
        serializeRequestsAsJSON(serializer, graphRequestBatch, hashMap2);
        if (logger != null) {
            logger.append("  Attachments:\n");
        }
        serializeAttachments(hashMap2, serializer);
    }
    
    static void runCallbacks(final GraphRequestBatch graphRequestBatch, final List<GraphResponse> list) {
        final int size = graphRequestBatch.size();
        final ArrayList<Pair> list2 = new ArrayList<Pair>();
        for (int i = 0; i < size; ++i) {
            final GraphRequest value = graphRequestBatch.get(i);
            if (value.callback != null) {
                list2.add(new Pair((Object)value.callback, (Object)list.get(i)));
            }
        }
        if (list2.size() > 0) {
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (final Pair pair : list2) {
                        ((Callback)pair.first).onCompleted((GraphResponse)pair.second);
                    }
                    final Iterator<GraphRequestBatch.Callback> iterator2 = graphRequestBatch.getCallbacks().iterator();
                    while (iterator2.hasNext()) {
                        ((GraphRequestBatch.Callback)iterator2.next()).onBatchCompleted(graphRequestBatch);
                    }
                }
            };
            final Handler callbackHandler = graphRequestBatch.getCallbackHandler();
            if (callbackHandler != null) {
                callbackHandler.post((Runnable)runnable);
                return;
            }
            runnable.run();
        }
    }
    
    private static void serializeAttachments(final Map<String, Attachment> map, final Serializer serializer) throws IOException {
        for (final String s : map.keySet()) {
            final Attachment attachment = map.get(s);
            if (isSupportedAttachmentType(attachment.getValue())) {
                serializer.writeObject(s, attachment.getValue(), attachment.getRequest());
            }
        }
    }
    
    private static void serializeParameters(final Bundle bundle, final Serializer serializer, final GraphRequest graphRequest) throws IOException {
        for (final String s : bundle.keySet()) {
            final Object value = bundle.get(s);
            if (isSupportedParameterType(value)) {
                serializer.writeObject(s, value, graphRequest);
            }
        }
    }
    
    private static void serializeRequestsAsJSON(final Serializer serializer, final Collection<GraphRequest> collection, final Map<String, Attachment> map) throws JSONException, IOException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<GraphRequest> iterator = collection.iterator();
        while (iterator.hasNext()) {
            iterator.next().serializeToBatch(jsonArray, map);
        }
        serializer.writeRequestsAsJson("batch", jsonArray, collection);
    }
    
    private void serializeToBatch(final JSONArray jsonArray, final Map<String, Attachment> map) throws JSONException, IOException {
        final JSONObject jsonObject = new JSONObject();
        if (this.batchEntryName != null) {
            jsonObject.put("name", (Object)this.batchEntryName);
            jsonObject.put("omit_response_on_success", this.batchEntryOmitResultOnSuccess);
        }
        if (this.batchEntryDependsOn != null) {
            jsonObject.put("depends_on", (Object)this.batchEntryDependsOn);
        }
        final String urlForBatchedRequest = this.getUrlForBatchedRequest();
        jsonObject.put("relative_url", (Object)urlForBatchedRequest);
        jsonObject.put("method", (Object)this.httpMethod);
        if (this.accessToken != null) {
            Logger.registerAccessToken(this.accessToken.getToken());
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.parameters.keySet().iterator();
        while (iterator.hasNext()) {
            final Object value = this.parameters.get((String)iterator.next());
            if (isSupportedAttachmentType(value)) {
                final String format = String.format(Locale.ROOT, "%s%d", "file", map.size());
                list.add(format);
                map.put(format, new Attachment(this, value));
            }
        }
        if (!list.isEmpty()) {
            jsonObject.put("attached_files", (Object)TextUtils.join((CharSequence)",", (Iterable)list));
        }
        if (this.graphObject != null) {
            final ArrayList list2 = new ArrayList();
            processGraphObject(this.graphObject, urlForBatchedRequest, (KeyValueSerializer)new KeyValueSerializer() {
                @Override
                public void writeString(final String s, final String s2) throws IOException {
                    list2.add(String.format(Locale.US, "%s=%s", s, URLEncoder.encode(s2, "UTF-8")));
                }
            });
            jsonObject.put("body", (Object)TextUtils.join((CharSequence)"&", (Iterable)list2));
        }
        jsonArray.put((Object)jsonObject);
    }
    
    static final void serializeToUrlConnection(final GraphRequestBatch p0, final HttpURLConnection p1) throws IOException, JSONException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/facebook/internal/Logger;
        //     3: dup            
        //     4: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //     7: ldc_w           "Request"
        //    10: invokespecial   com/facebook/internal/Logger.<init>:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;)V
        //    13: astore_2       
        //    14: aload_0        
        //    15: invokevirtual   com/facebook/GraphRequestBatch.size:()I
        //    18: istore_3       
        //    19: aload_0        
        //    20: invokestatic    com/facebook/GraphRequest.isGzipCompressible:(Lcom/facebook/GraphRequestBatch;)Z
        //    23: istore          4
        //    25: iload_3        
        //    26: iconst_1       
        //    27: if_icmpne       164
        //    30: aload_0        
        //    31: iconst_0       
        //    32: invokevirtual   com/facebook/GraphRequestBatch.get:(I)Lcom/facebook/GraphRequest;
        //    35: getfield        com/facebook/GraphRequest.httpMethod:Lcom/facebook/HttpMethod;
        //    38: astore          5
        //    40: aload_1        
        //    41: aload           5
        //    43: invokevirtual   com/facebook/HttpMethod.name:()Ljava/lang/String;
        //    46: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    49: aload_1        
        //    50: iload           4
        //    52: invokestatic    com/facebook/GraphRequest.setConnectionContentType:(Ljava/net/HttpURLConnection;Z)V
        //    55: aload_1        
        //    56: invokevirtual   java/net/HttpURLConnection.getURL:()Ljava/net/URL;
        //    59: astore          6
        //    61: aload_2        
        //    62: ldc_w           "Request:\n"
        //    65: invokevirtual   com/facebook/internal/Logger.append:(Ljava/lang/String;)V
        //    68: aload_2        
        //    69: ldc_w           "Id"
        //    72: aload_0        
        //    73: invokevirtual   com/facebook/GraphRequestBatch.getId:()Ljava/lang/String;
        //    76: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    79: aload_2        
        //    80: ldc_w           "URL"
        //    83: aload           6
        //    85: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    88: aload_2        
        //    89: ldc_w           "Method"
        //    92: aload_1        
        //    93: invokevirtual   java/net/HttpURLConnection.getRequestMethod:()Ljava/lang/String;
        //    96: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    99: aload_2        
        //   100: ldc             "User-Agent"
        //   102: aload_1        
        //   103: ldc             "User-Agent"
        //   105: invokevirtual   java/net/HttpURLConnection.getRequestProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   108: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   111: aload_2        
        //   112: ldc             "Content-Type"
        //   114: aload_1        
        //   115: ldc             "Content-Type"
        //   117: invokevirtual   java/net/HttpURLConnection.getRequestProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   120: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   123: aload_1        
        //   124: aload_0        
        //   125: invokevirtual   com/facebook/GraphRequestBatch.getTimeout:()I
        //   128: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   131: aload_1        
        //   132: aload_0        
        //   133: invokevirtual   com/facebook/GraphRequestBatch.getTimeout:()I
        //   136: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   139: getstatic       com/facebook/HttpMethod.POST:Lcom/facebook/HttpMethod;
        //   142: astore          7
        //   144: aload           5
        //   146: aload           7
        //   148: if_acmpne       172
        //   151: iconst_1       
        //   152: istore          8
        //   154: iload           8
        //   156: ifne            178
        //   159: aload_2        
        //   160: invokevirtual   com/facebook/internal/Logger.log:()V
        //   163: return         
        //   164: getstatic       com/facebook/HttpMethod.POST:Lcom/facebook/HttpMethod;
        //   167: astore          5
        //   169: goto            40
        //   172: iconst_0       
        //   173: istore          8
        //   175: goto            154
        //   178: aload_1        
        //   179: iconst_1       
        //   180: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   183: aconst_null    
        //   184: astore          9
        //   186: new             Ljava/io/BufferedOutputStream;
        //   189: dup            
        //   190: aload_1        
        //   191: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   194: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   197: astore          10
        //   199: iload           4
        //   201: ifeq            215
        //   204: new             Ljava/util/zip/GZIPOutputStream;
        //   207: dup            
        //   208: aload           10
        //   210: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   213: astore          10
        //   215: aload_0        
        //   216: invokestatic    com/facebook/GraphRequest.hasOnProgressCallbacks:(Lcom/facebook/GraphRequestBatch;)Z
        //   219: ifeq            329
        //   222: new             Lcom/facebook/ProgressNoopOutputStream;
        //   225: dup            
        //   226: aload_0        
        //   227: invokevirtual   com/facebook/GraphRequestBatch.getCallbackHandler:()Landroid/os/Handler;
        //   230: invokespecial   com/facebook/ProgressNoopOutputStream.<init>:(Landroid/os/Handler;)V
        //   233: astore          12
        //   235: aload_0        
        //   236: aconst_null    
        //   237: iload_3        
        //   238: aload           6
        //   240: aload           12
        //   242: iload           4
        //   244: invokestatic    com/facebook/GraphRequest.processRequest:(Lcom/facebook/GraphRequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;Z)V
        //   247: aload           12
        //   249: invokevirtual   com/facebook/ProgressNoopOutputStream.getMaxProgress:()I
        //   252: istore          13
        //   254: new             Lcom/facebook/ProgressOutputStream;
        //   257: dup            
        //   258: aload           10
        //   260: aload_0        
        //   261: aload           12
        //   263: invokevirtual   com/facebook/ProgressNoopOutputStream.getProgressMap:()Ljava/util/Map;
        //   266: iload           13
        //   268: i2l            
        //   269: invokespecial   com/facebook/ProgressOutputStream.<init>:(Ljava/io/OutputStream;Lcom/facebook/GraphRequestBatch;Ljava/util/Map;J)V
        //   272: astore          9
        //   274: aload           9
        //   276: astore          14
        //   278: aload_0        
        //   279: aload_2        
        //   280: iload_3        
        //   281: aload           6
        //   283: aload           14
        //   285: iload           4
        //   287: invokestatic    com/facebook/GraphRequest.processRequest:(Lcom/facebook/GraphRequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;Z)V
        //   290: aload           9
        //   292: ifnull          300
        //   295: aload           9
        //   297: invokevirtual   java/io/OutputStream.close:()V
        //   300: aload_2        
        //   301: invokevirtual   com/facebook/internal/Logger.log:()V
        //   304: return         
        //   305: astore          11
        //   307: aload           9
        //   309: ifnull          317
        //   312: aload           9
        //   314: invokevirtual   java/io/OutputStream.close:()V
        //   317: aload           11
        //   319: athrow         
        //   320: astore          11
        //   322: aload           10
        //   324: astore          9
        //   326: goto            307
        //   329: aload           10
        //   331: astore          9
        //   333: goto            274
        //    Exceptions:
        //  throws java.io.IOException
        //  throws org.json.JSONException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  186    199    305    307    Any
        //  204    215    320    329    Any
        //  215    274    320    329    Any
        //  278    290    305    307    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void setConnectionContentType(final HttpURLConnection httpURLConnection, final boolean b) {
        if (b) {
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
            return;
        }
        httpURLConnection.setRequestProperty("Content-Type", getMimeContentType());
    }
    
    public static final void setDefaultBatchApplicationId(final String defaultBatchApplicationId) {
        GraphRequest.defaultBatchApplicationId = defaultBatchApplicationId;
    }
    
    static final boolean shouldWarnOnMissingFieldsParam(final GraphRequest graphRequest) {
        String s = graphRequest.getVersion();
        if (Utility.isNullOrEmpty(s)) {
            return true;
        }
        if (s.startsWith("v")) {
            s = s.substring(1);
        }
        final String[] split = s.split("\\.");
        if (split.length < 2 || Integer.parseInt(split[0]) <= 2) {
            final int int1 = Integer.parseInt(split[0]);
            boolean b = false;
            if (int1 < 2) {
                return b;
            }
            final int int2 = Integer.parseInt(split[1]);
            b = false;
            if (int2 < 4) {
                return b;
            }
        }
        return true;
    }
    
    public static HttpURLConnection toHttpConnection(final GraphRequestBatch p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/facebook/GraphRequest.validateFieldsParamForGetRequests:(Lcom/facebook/GraphRequestBatch;)V
        //     4: aload_0        
        //     5: invokevirtual   com/facebook/GraphRequestBatch.size:()I
        //     8: iconst_1       
        //     9: if_icmpne       42
        //    12: new             Ljava/net/URL;
        //    15: dup            
        //    16: aload_0        
        //    17: iconst_0       
        //    18: invokevirtual   com/facebook/GraphRequestBatch.get:(I)Lcom/facebook/GraphRequest;
        //    21: invokevirtual   com/facebook/GraphRequest.getUrlForSingleRequest:()Ljava/lang/String;
        //    24: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    27: astore_2       
        //    28: aconst_null    
        //    29: astore_3       
        //    30: aload_2        
        //    31: invokestatic    com/facebook/GraphRequest.createConnection:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //    34: astore_3       
        //    35: aload_0        
        //    36: aload_3        
        //    37: invokestatic    com/facebook/GraphRequest.serializeToUrlConnection:(Lcom/facebook/GraphRequestBatch;Ljava/net/HttpURLConnection;)V
        //    40: aload_3        
        //    41: areturn        
        //    42: new             Ljava/net/URL;
        //    45: dup            
        //    46: invokestatic    com/facebook/internal/ServerProtocol.getGraphUrlBase:()Ljava/lang/String;
        //    49: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    52: astore_2       
        //    53: goto            28
        //    56: astore_1       
        //    57: new             Lcom/facebook/FacebookException;
        //    60: dup            
        //    61: ldc_w           "could not construct URL for request"
        //    64: aload_1        
        //    65: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    68: athrow         
        //    69: astore          4
        //    71: aload_3        
        //    72: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //    75: new             Lcom/facebook/FacebookException;
        //    78: dup            
        //    79: ldc_w           "could not construct request body"
        //    82: aload           4
        //    84: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    87: athrow         
        //    88: astore          4
        //    90: goto            71
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  4      28     56     69     Ljava/net/MalformedURLException;
        //  30     40     88     93     Ljava/io/IOException;
        //  30     40     69     71     Lorg/json/JSONException;
        //  42     53     56     69     Ljava/net/MalformedURLException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static HttpURLConnection toHttpConnection(final Collection<GraphRequest> collection) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)collection, "requests");
        return toHttpConnection(new GraphRequestBatch(collection));
    }
    
    public static HttpURLConnection toHttpConnection(final GraphRequest... array) {
        return toHttpConnection(Arrays.asList(array));
    }
    
    static final void validateFieldsParamForGetRequests(final GraphRequestBatch graphRequestBatch) {
        for (final GraphRequest graphRequest : graphRequestBatch) {
            if (HttpMethod.GET.equals(graphRequest.getHttpMethod()) && shouldWarnOnMissingFieldsParam(graphRequest)) {
                final Bundle parameters = graphRequest.getParameters();
                if (parameters.containsKey("fields") && !Utility.isNullOrEmpty(parameters.getString("fields"))) {
                    continue;
                }
                Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 5, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.getGraphPath());
            }
        }
    }
    
    public final GraphResponse executeAndWait() {
        return executeAndWait(this);
    }
    
    public final GraphRequestAsyncTask executeAsync() {
        return executeBatchAsync(this);
    }
    
    public final AccessToken getAccessToken() {
        return this.accessToken;
    }
    
    public final String getBatchEntryDependsOn() {
        return this.batchEntryDependsOn;
    }
    
    public final String getBatchEntryName() {
        return this.batchEntryName;
    }
    
    public final boolean getBatchEntryOmitResultOnSuccess() {
        return this.batchEntryOmitResultOnSuccess;
    }
    
    public final Callback getCallback() {
        return this.callback;
    }
    
    public final JSONObject getGraphObject() {
        return this.graphObject;
    }
    
    public final String getGraphPath() {
        return this.graphPath;
    }
    
    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }
    
    public final Bundle getParameters() {
        return this.parameters;
    }
    
    public final Object getTag() {
        return this.tag;
    }
    
    final String getUrlForBatchedRequest() {
        if (this.overriddenURL != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        final String graphPathWithVersion = this.getGraphPathWithVersion();
        this.addCommonParameters();
        return this.appendParametersToBaseUrl(graphPathWithVersion);
    }
    
    final String getUrlForSingleRequest() {
        if (this.overriddenURL != null) {
            return this.overriddenURL.toString();
        }
        String s;
        if (this.getHttpMethod() == HttpMethod.POST && this.graphPath != null && this.graphPath.endsWith("/videos")) {
            s = ServerProtocol.getGraphVideoUrlBase();
        }
        else {
            s = ServerProtocol.getGraphUrlBase();
        }
        final String format = String.format("%s/%s", s, this.getGraphPathWithVersion());
        this.addCommonParameters();
        return this.appendParametersToBaseUrl(format);
    }
    
    public final String getVersion() {
        return this.version;
    }
    
    public final void setAccessToken(final AccessToken accessToken) {
        this.accessToken = accessToken;
    }
    
    public final void setBatchEntryDependsOn(final String batchEntryDependsOn) {
        this.batchEntryDependsOn = batchEntryDependsOn;
    }
    
    public final void setBatchEntryName(final String batchEntryName) {
        this.batchEntryName = batchEntryName;
    }
    
    public final void setBatchEntryOmitResultOnSuccess(final boolean batchEntryOmitResultOnSuccess) {
        this.batchEntryOmitResultOnSuccess = batchEntryOmitResultOnSuccess;
    }
    
    public final void setCallback(final Callback callback) {
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO) || FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.callback = (Callback)new Callback() {
                @Override
                public void onCompleted(final GraphResponse graphResponse) {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    JSONObject optJSONObject;
                    if (jsonObject != null) {
                        optJSONObject = jsonObject.optJSONObject("__debug__");
                    }
                    else {
                        optJSONObject = null;
                    }
                    JSONArray optJSONArray;
                    if (optJSONObject != null) {
                        optJSONArray = optJSONObject.optJSONArray("messages");
                    }
                    else {
                        optJSONArray = null;
                    }
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); ++i) {
                            final JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                            String s;
                            if (optJSONObject2 != null) {
                                s = optJSONObject2.optString("message");
                            }
                            else {
                                s = null;
                            }
                            String optString;
                            if (optJSONObject2 != null) {
                                optString = optJSONObject2.optString("type");
                            }
                            else {
                                optString = null;
                            }
                            String optString2;
                            if (optJSONObject2 != null) {
                                optString2 = optJSONObject2.optString("link");
                            }
                            else {
                                optString2 = null;
                            }
                            if (s != null && optString != null) {
                                LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                                if (optString.equals("warning")) {
                                    loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                                }
                                if (!Utility.isNullOrEmpty(optString2)) {
                                    s = s + " Link: " + optString2;
                                }
                                Logger.log(loggingBehavior, GraphRequest.TAG, s);
                            }
                        }
                    }
                    if (callback != null) {
                        callback.onCompleted(graphResponse);
                    }
                }
            };
            return;
        }
        this.callback = callback;
    }
    
    public final void setGraphObject(final JSONObject graphObject) {
        this.graphObject = graphObject;
    }
    
    public final void setGraphPath(final String graphPath) {
        this.graphPath = graphPath;
    }
    
    public final void setHttpMethod(HttpMethod get) {
        if (this.overriddenURL != null && get != HttpMethod.GET) {
            throw new FacebookException("Can't change HTTP method on request with overridden URL.");
        }
        if (get == null) {
            get = HttpMethod.GET;
        }
        this.httpMethod = get;
    }
    
    public final void setParameters(final Bundle parameters) {
        this.parameters = parameters;
    }
    
    public final void setSkipClientToken(final boolean skipClientToken) {
        this.skipClientToken = skipClientToken;
    }
    
    public final void setTag(final Object tag) {
        this.tag = tag;
    }
    
    public final void setVersion(final String version) {
        this.version = version;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("{Request: ").append(" accessToken: ");
        Object accessToken;
        if (this.accessToken == null) {
            accessToken = "null";
        }
        else {
            accessToken = this.accessToken;
        }
        return append.append(accessToken).append(", graphPath: ").append(this.graphPath).append(", graphObject: ").append(this.graphObject).append(", httpMethod: ").append(this.httpMethod).append(", parameters: ").append(this.parameters).append("}").toString();
    }
    
    private static class Attachment
    {
        private final GraphRequest request;
        private final Object value;
        
        public Attachment(final GraphRequest request, final Object value) {
            this.request = request;
            this.value = value;
        }
        
        public GraphRequest getRequest() {
            return this.request;
        }
        
        public Object getValue() {
            return this.value;
        }
    }
    
    public interface Callback
    {
        void onCompleted(final GraphResponse p0);
    }
    
    public interface GraphJSONArrayCallback
    {
        void onCompleted(final JSONArray p0, final GraphResponse p1);
    }
    
    public interface GraphJSONObjectCallback
    {
        void onCompleted(final JSONObject p0, final GraphResponse p1);
    }
    
    private interface KeyValueSerializer
    {
        void writeString(final String p0, final String p1) throws IOException;
    }
    
    public interface OnProgressCallback extends Callback
    {
        void onProgress(final long p0, final long p1);
    }
    
    public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable
    {
        public static final Parcelable$Creator<ParcelableResourceWithMimeType> CREATOR;
        private final String mimeType;
        private final RESOURCE resource;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<ParcelableResourceWithMimeType>() {
                public ParcelableResourceWithMimeType createFromParcel(final Parcel parcel) {
                    return new ParcelableResourceWithMimeType(parcel);
                }
                
                public ParcelableResourceWithMimeType[] newArray(final int n) {
                    return new ParcelableResourceWithMimeType[n];
                }
            };
        }
        
        private ParcelableResourceWithMimeType(final Parcel parcel) {
            this.mimeType = parcel.readString();
            this.resource = (RESOURCE)parcel.readParcelable(FacebookSdk.getApplicationContext().getClassLoader());
        }
        
        public ParcelableResourceWithMimeType(final RESOURCE resource, final String mimeType) {
            this.mimeType = mimeType;
            this.resource = resource;
        }
        
        public int describeContents() {
            return 1;
        }
        
        public String getMimeType() {
            return this.mimeType;
        }
        
        public RESOURCE getResource() {
            return this.resource;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeString(this.mimeType);
            parcel.writeParcelable((Parcelable)this.resource, n);
        }
    }
    
    private static class Serializer implements KeyValueSerializer
    {
        private boolean firstWrite;
        private final Logger logger;
        private final OutputStream outputStream;
        private boolean useUrlEncode;
        
        public Serializer(final OutputStream outputStream, final Logger logger, final boolean useUrlEncode) {
            this.firstWrite = true;
            this.useUrlEncode = false;
            this.outputStream = outputStream;
            this.logger = logger;
            this.useUrlEncode = useUrlEncode;
        }
        
        private RuntimeException getInvalidTypeError() {
            return new IllegalArgumentException("value is not a supported type.");
        }
        
        public void write(final String s, final Object... array) throws IOException {
            if (!this.useUrlEncode) {
                if (this.firstWrite) {
                    this.outputStream.write("--".getBytes());
                    this.outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
                    this.outputStream.write("\r\n".getBytes());
                    this.firstWrite = false;
                }
                this.outputStream.write(String.format(s, array).getBytes());
                return;
            }
            this.outputStream.write(URLEncoder.encode(String.format(Locale.US, s, array), "UTF-8").getBytes());
        }
        
        public void writeBitmap(final String s, final Bitmap bitmap) throws IOException {
            this.writeContentDisposition(s, s, "image/png");
            bitmap.compress(Bitmap$CompressFormat.PNG, 100, this.outputStream);
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, "<Image>");
            }
        }
        
        public void writeBytes(final String s, final byte[] array) throws IOException {
            this.writeContentDisposition(s, s, "content/unknown");
            this.outputStream.write(array);
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, String.format(Locale.ROOT, "<Data: %d>", array.length));
            }
        }
        
        public void writeContentDisposition(final String s, final String s2, final String s3) throws IOException {
            if (!this.useUrlEncode) {
                this.write("Content-Disposition: form-data; name=\"%s\"", s);
                if (s2 != null) {
                    this.write("; filename=\"%s\"", s2);
                }
                this.writeLine("", new Object[0]);
                if (s3 != null) {
                    this.writeLine("%s: %s", "Content-Type", s3);
                }
                this.writeLine("", new Object[0]);
                return;
            }
            this.outputStream.write(String.format("%s=", s).getBytes());
        }
        
        public void writeContentUri(final String s, final Uri uri, String s2) throws IOException {
            if (s2 == null) {
                s2 = "content/unknown";
            }
            this.writeContentDisposition(s, s, s2);
            final InputStream openInputStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(uri);
            int n = 0;
            if (this.outputStream instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream)this.outputStream).addProgress(Utility.getContentSize(uri));
            }
            else {
                n = 0 + Utility.copyAndCloseInputStream(openInputStream, this.outputStream);
            }
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, String.format(Locale.ROOT, "<Data: %d>", n));
            }
        }
        
        public void writeFile(final String s, final ParcelFileDescriptor parcelFileDescriptor, String s2) throws IOException {
            if (s2 == null) {
                s2 = "content/unknown";
            }
            this.writeContentDisposition(s, s, s2);
            int n = 0;
            if (this.outputStream instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream)this.outputStream).addProgress(parcelFileDescriptor.getStatSize());
            }
            else {
                n = 0 + Utility.copyAndCloseInputStream((InputStream)new ParcelFileDescriptor$AutoCloseInputStream(parcelFileDescriptor), this.outputStream);
            }
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, String.format(Locale.ROOT, "<Data: %d>", n));
            }
        }
        
        public void writeLine(final String s, final Object... array) throws IOException {
            this.write(s, array);
            if (!this.useUrlEncode) {
                this.write("\r\n", new Object[0]);
            }
        }
        
        public void writeObject(final String s, final Object o, final GraphRequest currentRequest) throws IOException {
            if (this.outputStream instanceof RequestOutputStream) {
                ((RequestOutputStream)this.outputStream).setCurrentRequest(currentRequest);
            }
            if (isSupportedParameterType(o)) {
                this.writeString(s, parameterToString(o));
                return;
            }
            if (o instanceof Bitmap) {
                this.writeBitmap(s, (Bitmap)o);
                return;
            }
            if (o instanceof byte[]) {
                this.writeBytes(s, (byte[])o);
                return;
            }
            if (o instanceof Uri) {
                this.writeContentUri(s, (Uri)o, null);
                return;
            }
            if (o instanceof ParcelFileDescriptor) {
                this.writeFile(s, (ParcelFileDescriptor)o, null);
                return;
            }
            if (!(o instanceof ParcelableResourceWithMimeType)) {
                throw this.getInvalidTypeError();
            }
            final ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType)o;
            final Parcelable resource = parcelableResourceWithMimeType.getResource();
            final String mimeType = parcelableResourceWithMimeType.getMimeType();
            if (resource instanceof ParcelFileDescriptor) {
                this.writeFile(s, (ParcelFileDescriptor)resource, mimeType);
                return;
            }
            if (resource instanceof Uri) {
                this.writeContentUri(s, (Uri)resource, mimeType);
                return;
            }
            throw this.getInvalidTypeError();
        }
        
        public void writeRecordBoundary() throws IOException {
            if (!this.useUrlEncode) {
                this.writeLine("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
                return;
            }
            this.outputStream.write("&".getBytes());
        }
        
        public void writeRequestsAsJson(final String s, final JSONArray jsonArray, final Collection<GraphRequest> collection) throws IOException, JSONException {
            if (!(this.outputStream instanceof RequestOutputStream)) {
                this.writeString(s, jsonArray.toString());
            }
            else {
                final RequestOutputStream requestOutputStream = (RequestOutputStream)this.outputStream;
                this.writeContentDisposition(s, null, null);
                this.write("[", new Object[0]);
                int n = 0;
                for (final GraphRequest currentRequest : collection) {
                    final JSONObject jsonObject = jsonArray.getJSONObject(n);
                    requestOutputStream.setCurrentRequest(currentRequest);
                    if (n > 0) {
                        this.write(",%s", jsonObject.toString());
                    }
                    else {
                        this.write("%s", jsonObject.toString());
                    }
                    ++n;
                }
                this.write("]", new Object[0]);
                if (this.logger != null) {
                    this.logger.appendKeyValue("    " + s, jsonArray.toString());
                }
            }
        }
        
        @Override
        public void writeString(final String s, final String s2) throws IOException {
            this.writeContentDisposition(s, null, null);
            this.writeLine("%s", s2);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, s2);
            }
        }
    }
}
