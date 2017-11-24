package com.facebook.internal;

import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
import android.net.*;
import android.webkit.*;
import android.text.*;
import android.database.*;
import org.json.*;
import com.facebook.*;
import java.security.*;
import java.lang.reflect.*;
import android.content.*;
import android.util.*;
import java.net.*;
import android.os.*;
import java.util.regex.*;
import android.telephony.*;
import java.util.*;
import java.io.*;

public final class Utility
{
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String[] APP_SETTING_FIELDS;
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
    private static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "\\|";
    private static final String DIALOG_CONFIG_NAME_KEY = "name";
    private static final String DIALOG_CONFIG_URL_KEY = "url";
    private static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
    private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a2";
    private static final int GINGERBREAD_MR1 = 10;
    private static final String HASH_ALGORITHM_MD5 = "MD5";
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    static final String LOG_TAG = "FacebookSDK";
    private static final int REFRESH_TIME_FOR_EXTENDED_DEVICE_INFO_MILLIS = 1800000;
    private static final String URL_SCHEME = "https";
    private static final String UTF8 = "UTF-8";
    private static long availableExternalStorageGB = 0L;
    private static String carrierName;
    private static String deviceTimezone;
    private static Map<String, FetchedAppSettings> fetchedAppSettings;
    private static AtomicBoolean loadingSettings;
    private static final String noCarrierConstant = "NoCarrier";
    private static int numCPUCores;
    private static long timestampOfLastCheck;
    private static long totalExternalStorageGB;
    
    static {
        APP_SETTING_FIELDS = new String[] { "supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "android_dialog_configs", "android_sdk_error_categories" };
        Utility.fetchedAppSettings = new ConcurrentHashMap<String, FetchedAppSettings>();
        Utility.loadingSettings = new AtomicBoolean(false);
        Utility.numCPUCores = 0;
        Utility.timestampOfLastCheck = -1L;
        Utility.totalExternalStorageGB = -1L;
        Utility.availableExternalStorageGB = -1L;
        Utility.deviceTimezone = "";
        Utility.carrierName = "NoCarrier";
    }
    
    public static <T> boolean areObjectsEqual(final T t, final T t2) {
        if (t == null) {
            return t2 == null;
        }
        return t.equals(t2);
    }
    
    public static <T> ArrayList<T> arrayList(final T... array) {
        final ArrayList<T> list = new ArrayList<T>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        return list;
    }
    
    public static <T> List<T> asListNoNulls(final T... array) {
        final ArrayList<T> list = new ArrayList<T>();
        for (final T t : array) {
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }
    
    public static JSONObject awaitGetGraphMeRequestWithCache(final String s) {
        final JSONObject profileInformation = ProfileInformationCache.getProfileInformation(s);
        if (profileInformation != null) {
            return profileInformation;
        }
        final GraphResponse executeAndWait = getGraphMeRequestWithCache(s).executeAndWait();
        if (executeAndWait.getError() != null) {
            return null;
        }
        return executeAndWait.getJSONObject();
    }
    
    public static Uri buildUri(final String s, final String s2, final Bundle bundle) {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("https");
        uri$Builder.authority(s);
        uri$Builder.path(s2);
        if (bundle != null) {
            for (final String s3 : bundle.keySet()) {
                final Object value = bundle.get(s3);
                if (value instanceof String) {
                    uri$Builder.appendQueryParameter(s3, (String)value);
                }
            }
        }
        return uri$Builder.build();
    }
    
    public static void clearCaches(final Context context) {
        ImageDownloader.clearCache(context);
    }
    
    private static void clearCookiesForDomain(final Context context, final String s) {
        CookieSyncManager.createInstance(context).sync();
        final CookieManager instance = CookieManager.getInstance();
        final String cookie = instance.getCookie(s);
        if (cookie == null) {
            return;
        }
        final String[] split = cookie.split(";");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String[] split2 = split[i].split("=");
            if (split2.length > 0) {
                instance.setCookie(s, split2[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
            }
        }
        instance.removeExpiredCookie();
    }
    
    public static void clearFacebookCookies(final Context context) {
        clearCookiesForDomain(context, "facebook.com");
        clearCookiesForDomain(context, ".facebook.com");
        clearCookiesForDomain(context, "https://facebook.com");
        clearCookiesForDomain(context, "https://.facebook.com");
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static String coerceValueIfNullOrEmpty(final String s, final String s2) {
        if (isNullOrEmpty(s)) {
            return s2;
        }
        return s;
    }
    
    private static long convertBytesToGB(final double n) {
        return Math.round(n / 1.073741824E9);
    }
    
    static Map<String, Object> convertJSONObjectToHashMap(final JSONObject jsonObject) {
        final HashMap<String, JSONObject> hashMap = (HashMap<String, JSONObject>)new HashMap<String, Object>();
        final JSONArray names = jsonObject.names();
        int n = 0;
    Label_0065_Outer:
        while (true) {
            if (n >= names.length()) {
                return (Map<String, Object>)hashMap;
            }
            while (true) {
                try {
                    final String string = names.getString(n);
                    Object o = jsonObject.get(string);
                    if (o instanceof JSONObject) {
                        o = convertJSONObjectToHashMap((JSONObject)o);
                    }
                    hashMap.put(string, (JSONObject)o);
                    ++n;
                    continue Label_0065_Outer;
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public static int copyAndCloseInputStream(final InputStream p0, final OutputStream p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: iconst_0       
        //     3: istore_3       
        //     4: new             Ljava/io/BufferedInputStream;
        //     7: dup            
        //     8: aload_0        
        //     9: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    12: astore          4
        //    14: sipush          8192
        //    17: newarray        B
        //    19: astore          6
        //    21: aload           4
        //    23: aload           6
        //    25: invokevirtual   java/io/BufferedInputStream.read:([B)I
        //    28: istore          7
        //    30: iload           7
        //    32: iconst_m1      
        //    33: if_icmpeq       53
        //    36: aload_1        
        //    37: aload           6
        //    39: iconst_0       
        //    40: iload           7
        //    42: invokevirtual   java/io/OutputStream.write:([BII)V
        //    45: iload_3        
        //    46: iload           7
        //    48: iadd           
        //    49: istore_3       
        //    50: goto            21
        //    53: aload           4
        //    55: ifnull          63
        //    58: aload           4
        //    60: invokevirtual   java/io/BufferedInputStream.close:()V
        //    63: aload_0        
        //    64: ifnull          71
        //    67: aload_0        
        //    68: invokevirtual   java/io/InputStream.close:()V
        //    71: iload_3        
        //    72: ireturn        
        //    73: astore          5
        //    75: aload_2        
        //    76: ifnull          83
        //    79: aload_2        
        //    80: invokevirtual   java/io/BufferedInputStream.close:()V
        //    83: aload_0        
        //    84: ifnull          91
        //    87: aload_0        
        //    88: invokevirtual   java/io/InputStream.close:()V
        //    91: aload           5
        //    93: athrow         
        //    94: astore          5
        //    96: aload           4
        //    98: astore_2       
        //    99: goto            75
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  4      14     73     75     Any
        //  14     21     94     102    Any
        //  21     30     94     102    Any
        //  36     45     94     102    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void deleteDirectory(final File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    deleteDirectory(listFiles[i]);
                }
            }
        }
        file.delete();
    }
    
    public static void disconnectQuietly(final URLConnection urlConnection) {
        if (urlConnection != null && urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).disconnect();
        }
    }
    
    private static boolean externalStorageExists() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
    
    public static <T> List<T> filter(final List<T> list, final Predicate<T> predicate) {
        if (list == null) {
            return null;
        }
        ArrayList<T> list2 = new ArrayList<T>();
        for (final T next : list) {
            if (predicate.apply(next)) {
                list2.add(next);
            }
        }
        if (list2.size() == 0) {
            list2 = null;
        }
        return list2;
    }
    
    public static String getActivityName(final Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return "unknown";
        }
        return context.getClass().getSimpleName();
    }
    
    private static JSONObject getAppSettingsQueryResponse(final String s) {
        final Bundle parameters = new Bundle();
        parameters.putString("fields", TextUtils.join((CharSequence)",", (Object[])Utility.APP_SETTING_FIELDS));
        final GraphRequest graphPathRequest = GraphRequest.newGraphPathRequest(null, s, null);
        graphPathRequest.setSkipClientToken(true);
        graphPathRequest.setParameters(parameters);
        return graphPathRequest.executeAndWait().getJSONObject();
    }
    
    public static FetchedAppSettings getAppSettingsWithoutQuery(final String s) {
        if (s != null) {
            return Utility.fetchedAppSettings.get(s);
        }
        return null;
    }
    
    public static Date getBundleLongAsDate(final Bundle bundle, final String s, final Date date) {
        if (bundle != null) {
            final Object value = bundle.get(s);
            long n = 0L;
            Label_0028: {
                if (value instanceof Long) {
                    n = (long)value;
                }
                else {
                    if (value instanceof String) {
                        try {
                            n = Long.parseLong((String)value);
                            break Label_0028;
                        }
                        catch (NumberFormatException ex) {
                            return null;
                        }
                        return new Date(date.getTime() + 1000L * n);
                    }
                    return null;
                }
            }
            if (n == 0L) {
                return new Date(Long.MAX_VALUE);
            }
            return new Date(date.getTime() + 1000L * n);
        }
        return null;
    }
    
    public static long getContentSize(final Uri uri) {
        Cursor query = null;
        try {
            query = FacebookSdk.getApplicationContext().getContentResolver().query(uri, (String[])null, (String)null, (String[])null, (String)null);
            final int columnIndex = query.getColumnIndex("_size");
            query.moveToFirst();
            return query.getLong(columnIndex);
        }
        finally {
            if (query != null) {
                query.close();
            }
        }
    }
    
    public static DialogFeatureConfig getDialogFeatureConfig(final String s, final String s2, final String s3) {
        if (!isNullOrEmpty(s2) && !isNullOrEmpty(s3)) {
            final FetchedAppSettings fetchedAppSettings = Utility.fetchedAppSettings.get(s);
            if (fetchedAppSettings != null) {
                final Map<String, DialogFeatureConfig> map = fetchedAppSettings.getDialogConfigurations().get(s2);
                if (map != null) {
                    return (DialogFeatureConfig)map.get(s3);
                }
            }
        }
        return null;
    }
    
    private static GraphRequest getGraphMeRequestWithCache(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString("access_token", s);
        return new GraphRequest(null, "me", bundle, HttpMethod.GET, null);
    }
    
    public static void getGraphMeRequestWithCacheAsync(final String s, final GraphMeRequestWithCacheCallback graphMeRequestWithCacheCallback) {
        final JSONObject profileInformation = ProfileInformationCache.getProfileInformation(s);
        if (profileInformation != null) {
            graphMeRequestWithCacheCallback.onSuccess(profileInformation);
            return;
        }
        final GraphRequest.Callback callback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphResponse.getError() != null) {
                    graphMeRequestWithCacheCallback.onFailure(graphResponse.getError().getException());
                    return;
                }
                ProfileInformationCache.putProfileInformation(s, graphResponse.getJSONObject());
                graphMeRequestWithCacheCallback.onSuccess(graphResponse.getJSONObject());
            }
        };
        final GraphRequest graphMeRequestWithCache = getGraphMeRequestWithCache(s);
        graphMeRequestWithCache.setCallback((GraphRequest.Callback)callback);
        graphMeRequestWithCache.executeAsync();
    }
    
    public static String getMetadataApplicationId(final Context context) {
        Validate.notNull(context, "context");
        FacebookSdk.sdkInitialize(context);
        return FacebookSdk.getApplicationId();
    }
    
    public static Method getMethodQuietly(final Class<?> clazz, final String s, final Class<?>... array) {
        try {
            return clazz.getMethod(s, array);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    public static Method getMethodQuietly(final String s, final String s2, final Class<?>... array) {
        try {
            return getMethodQuietly(Class.forName(s), s2, array);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static Object getStringPropertyAsJSON(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        Object o = jsonObject.opt(s);
        if (o != null && o instanceof String) {
            o = new JSONTokener((String)o).nextValue();
        }
        if (o == null || o instanceof JSONObject || o instanceof JSONArray) {
            return o;
        }
        if (s2 != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.putOpt(s2, o);
            return jsonObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }
    
    public static String getUriString(final Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }
    
    public static PermissionsPair handlePermissionResponse(final JSONObject jsonObject) throws JSONException {
        final JSONArray jsonArray = jsonObject.getJSONObject("permissions").getJSONArray("data");
        final ArrayList list = new ArrayList<String>(jsonArray.length());
        final ArrayList list2 = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject optJSONObject = jsonArray.optJSONObject(i);
            final String optString = optJSONObject.optString("permission");
            if (optString != null && !optString.equals("installed")) {
                final String optString2 = optJSONObject.optString("status");
                if (optString2 != null) {
                    if (optString2.equals("granted")) {
                        list.add(optString);
                    }
                    else if (optString2.equals("declined")) {
                        list2.add(optString);
                    }
                }
            }
        }
        return new PermissionsPair((List<String>)list, (List<String>)list2);
    }
    
    public static boolean hasSameId(final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (jsonObject != null && jsonObject2 != null && jsonObject.has("id") && jsonObject2.has("id")) {
            if (jsonObject.equals(jsonObject2)) {
                return true;
            }
            final String optString = jsonObject.optString("id");
            final String optString2 = jsonObject2.optString("id");
            if (optString != null && optString2 != null) {
                return optString.equals(optString2);
            }
        }
        return false;
    }
    
    private static String hashBytes(final MessageDigest messageDigest, final byte[] array) {
        messageDigest.update(array);
        final byte[] digest = messageDigest.digest();
        final StringBuilder sb = new StringBuilder();
        for (final byte b : digest) {
            sb.append(Integer.toHexString(0xF & b >> 4));
            sb.append(Integer.toHexString(0xF & b >> 0));
        }
        return sb.toString();
    }
    
    public static <T> HashSet<T> hashSet(final T... array) {
        final HashSet<T> set = new HashSet<T>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add(array[i]);
        }
        return set;
    }
    
    private static String hashWithAlgorithm(final String s, final String s2) {
        return hashWithAlgorithm(s, s2.getBytes());
    }
    
    private static String hashWithAlgorithm(final String s, final byte[] array) {
        try {
            return hashBytes(MessageDigest.getInstance(s), array);
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    public static int[] intersectRanges(final int[] array, final int[] array2) {
        if (array == null) {
            return array2;
        }
        if (array2 == null) {
            return array;
        }
        final int[] array3 = new int[array.length + array2.length];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (n2 < array.length && n3 < array2.length) {
            int n4 = Integer.MIN_VALUE;
            int n5 = Integer.MAX_VALUE;
            final int n6 = array[n2];
            int n7 = Integer.MAX_VALUE;
            final int n8 = array2[n3];
            int n9 = Integer.MAX_VALUE;
            if (n2 < -1 + array.length) {
                n7 = array[n2 + 1];
            }
            if (n3 < -1 + array2.length) {
                n9 = array2[n3 + 1];
            }
            if (n6 < n8) {
                if (n7 > n8) {
                    n4 = n8;
                    if (n7 > n9) {
                        n5 = n9;
                        n3 += 2;
                    }
                    else {
                        n5 = n7;
                        n2 += 2;
                    }
                }
                else {
                    n2 += 2;
                }
            }
            else if (n9 > n6) {
                n4 = n6;
                if (n9 > n7) {
                    n5 = n7;
                    n2 += 2;
                }
                else {
                    n5 = n9;
                    n3 += 2;
                }
            }
            else {
                n3 += 2;
            }
            if (n4 != Integer.MIN_VALUE) {
                final int n10 = n + 1;
                array3[n] = n4;
                if (n5 == Integer.MAX_VALUE) {
                    n = n10;
                    break;
                }
                n = n10 + 1;
                array3[n10] = n5;
            }
        }
        return Arrays.copyOf(array3, n);
    }
    
    public static Object invokeMethodQuietly(final Object o, final Method method, final Object... array) {
        try {
            return method.invoke(o, array);
        }
        catch (IllegalAccessException ex) {
            return null;
        }
        catch (InvocationTargetException ex2) {
            return null;
        }
    }
    
    public static boolean isContentUri(final Uri uri) {
        return uri != null && "content".equalsIgnoreCase(uri.getScheme());
    }
    
    public static boolean isCurrentAccessToken(final AccessToken accessToken) {
        return accessToken != null && accessToken.equals(AccessToken.getCurrentAccessToken());
    }
    
    public static boolean isFileUri(final Uri uri) {
        return uri != null && "file".equalsIgnoreCase(uri.getScheme());
    }
    
    public static boolean isNullOrEmpty(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static <T> boolean isNullOrEmpty(final Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }
    
    public static <T> boolean isSubset(final Collection<T> collection, final Collection<T> collection2) {
        if (collection2 == null || collection2.size() == 0) {
            if (collection != null) {
                final int size = collection.size();
                final boolean b = false;
                if (size != 0) {
                    return b;
                }
            }
            return true;
        }
        final HashSet set = new HashSet((Collection<? extends E>)collection2);
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!set.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isWebUri(final Uri uri) {
        return uri != null && ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()));
    }
    
    public static Set<String> jsonArrayToSet(final JSONArray jsonArray) throws JSONException {
        final HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            set.add(jsonArray.getString(i));
        }
        return set;
    }
    
    public static List<String> jsonArrayToStringList(final JSONArray jsonArray) throws JSONException {
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
    
    public static void loadAppSettingsAsync(final Context context, final String s) {
        final boolean compareAndSet = Utility.loadingSettings.compareAndSet(false, true);
        if (isNullOrEmpty(s) || Utility.fetchedAppSettings.containsKey(s) || !compareAndSet) {
            return;
        }
        FacebookSdk.getExecutor().execute(new Runnable() {
            final /* synthetic */ String val$settingsKey = String.format("com.facebook.internal.APP_SETTINGS.%s", s);
            
            @Override
            public void run() {
                final SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
                final String string = sharedPreferences.getString(this.val$settingsKey, (String)null);
                Label_0059: {
                    if (Utility.isNullOrEmpty(string)) {
                        break Label_0059;
                    }
                    while (true) {
                        try {
                            final JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject != null) {
                                parseAppSettingsFromJSON(s, jsonObject);
                            }
                            final JSONObject access$100 = getAppSettingsQueryResponse(s);
                            if (access$100 != null) {
                                parseAppSettingsFromJSON(s, access$100);
                                sharedPreferences.edit().putString(this.val$settingsKey, access$100.toString()).apply();
                            }
                            Utility.loadingSettings.set(false);
                        }
                        catch (JSONException ex) {
                            Utility.logd("FacebookSDK", (Exception)ex);
                            final JSONObject jsonObject = null;
                            continue;
                        }
                        break;
                    }
                }
            }
        });
    }
    
    public static void logd(final String s, final Exception ex) {
        if (FacebookSdk.isDebugEnabled() && s != null && ex != null) {
            Log.d(s, ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
    
    public static void logd(final String s, final String s2) {
        if (FacebookSdk.isDebugEnabled() && s != null && s2 != null) {
            Log.d(s, s2);
        }
    }
    
    public static void logd(final String s, final String s2, final Throwable t) {
        if (FacebookSdk.isDebugEnabled() && !isNullOrEmpty(s)) {
            Log.d(s, s2, t);
        }
    }
    
    public static <T, K> List<K> map(final List<T> list, final Mapper<T, K> mapper) {
        if (list == null) {
            return null;
        }
        ArrayList<K> list2 = new ArrayList<K>();
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            final K apply = mapper.apply(iterator.next());
            if (apply != null) {
                list2.add(apply);
            }
        }
        if (list2.size() == 0) {
            list2 = null;
        }
        return list2;
    }
    
    public static String md5hash(final String s) {
        return hashWithAlgorithm("MD5", s);
    }
    
    private static FetchedAppSettings parseAppSettingsFromJSON(final String s, final JSONObject jsonObject) {
        final JSONArray optJSONArray = jsonObject.optJSONArray("android_sdk_error_categories");
        FacebookRequestErrorClassification facebookRequestErrorClassification;
        if (optJSONArray == null) {
            facebookRequestErrorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
        }
        else {
            facebookRequestErrorClassification = FacebookRequestErrorClassification.createFromJSON(optJSONArray);
        }
        final FetchedAppSettings fetchedAppSettings = new FetchedAppSettings(jsonObject.optBoolean("supports_implicit_sdk_logging", false), jsonObject.optString("gdpv4_nux_content", ""), jsonObject.optBoolean("gdpv4_nux_enabled", false), (Map)parseDialogConfigurations(jsonObject.optJSONObject("android_dialog_configs")), facebookRequestErrorClassification);
        Utility.fetchedAppSettings.put(s, fetchedAppSettings);
        return fetchedAppSettings;
    }
    
    private static Map<String, Map<String, DialogFeatureConfig>> parseDialogConfigurations(final JSONObject jsonObject) {
        final HashMap<Object, Map<String, DialogFeatureConfig>> hashMap = new HashMap<Object, Map<String, DialogFeatureConfig>>();
        if (jsonObject != null) {
            final JSONArray optJSONArray = jsonObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final DialogFeatureConfig access$400 = parseDialogConfig(optJSONArray.optJSONObject(i));
                    if (access$400 != null) {
                        final String dialogName = access$400.getDialogName();
                        Map<String, DialogFeatureConfig> map = hashMap.get(dialogName);
                        if (map == null) {
                            map = new HashMap<String, DialogFeatureConfig>();
                            hashMap.put(dialogName, map);
                        }
                        map.put(access$400.getFeatureName(), access$400);
                    }
                }
            }
        }
        return (Map<String, Map<String, DialogFeatureConfig>>)hashMap;
    }
    
    public static Bundle parseUrlQueryString(final String s) {
        int i = 0;
        final Bundle bundle = new Bundle();
        if (!isNullOrEmpty(s)) {
            for (String[] split = s.split("&"); i < split.length; ++i) {
                final String[] split2 = split[i].split("=");
                try {
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                    }
                    else if (split2.length == 1) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), "");
                    }
                }
                catch (UnsupportedEncodingException ex) {
                    logd("FacebookSDK", ex);
                }
            }
        }
        return bundle;
    }
    
    public static void putCommaSeparatedStringList(final Bundle bundle, final String s, final List<String> list) {
        if (list != null) {
            final StringBuilder sb = new StringBuilder();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(",");
            }
            String substring = "";
            if (sb.length() > 0) {
                substring = sb.substring(0, -1 + sb.length());
            }
            bundle.putString(s, substring);
        }
    }
    
    public static boolean putJSONValueInBundle(final Bundle bundle, final String s, final Object o) {
        if (o == null) {
            bundle.remove(s);
        }
        else if (o instanceof Boolean) {
            bundle.putBoolean(s, (boolean)o);
        }
        else if (o instanceof boolean[]) {
            bundle.putBooleanArray(s, (boolean[])o);
        }
        else if (o instanceof Double) {
            bundle.putDouble(s, (double)o);
        }
        else if (o instanceof double[]) {
            bundle.putDoubleArray(s, (double[])o);
        }
        else if (o instanceof Integer) {
            bundle.putInt(s, (int)o);
        }
        else if (o instanceof int[]) {
            bundle.putIntArray(s, (int[])o);
        }
        else if (o instanceof Long) {
            bundle.putLong(s, (long)o);
        }
        else if (o instanceof long[]) {
            bundle.putLongArray(s, (long[])o);
        }
        else if (o instanceof String) {
            bundle.putString(s, (String)o);
        }
        else if (o instanceof JSONArray) {
            bundle.putString(s, ((JSONArray)o).toString());
        }
        else {
            if (!(o instanceof JSONObject)) {
                return false;
            }
            bundle.putString(s, ((JSONObject)o).toString());
        }
        return true;
    }
    
    public static void putNonEmptyString(final Bundle bundle, final String s, final String s2) {
        if (!isNullOrEmpty(s2)) {
            bundle.putString(s, s2);
        }
    }
    
    public static void putUri(final Bundle bundle, final String s, final Uri uri) {
        if (uri != null) {
            putNonEmptyString(bundle, s, uri.toString());
        }
    }
    
    public static FetchedAppSettings queryAppSettings(final String s, final boolean b) {
        if (!b && Utility.fetchedAppSettings.containsKey(s)) {
            return Utility.fetchedAppSettings.get(s);
        }
        final JSONObject appSettingsQueryResponse = getAppSettingsQueryResponse(s);
        if (appSettingsQueryResponse == null) {
            return null;
        }
        return parseAppSettingsFromJSON(s, appSettingsQueryResponse);
    }
    
    public static String readStreamToString(final InputStream p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/BufferedInputStream;
        //     3: dup            
        //     4: aload_0        
        //     5: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //     8: astore_1       
        //     9: new             Ljava/io/InputStreamReader;
        //    12: dup            
        //    13: aload_1        
        //    14: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    17: astore_2       
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: astore_3       
        //    26: sipush          2048
        //    29: newarray        C
        //    31: astore          7
        //    33: aload_2        
        //    34: aload           7
        //    36: invokevirtual   java/io/InputStreamReader.read:([C)I
        //    39: istore          8
        //    41: iload           8
        //    43: iconst_m1      
        //    44: if_icmpeq       81
        //    47: aload_3        
        //    48: aload           7
        //    50: iconst_0       
        //    51: iload           8
        //    53: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //    56: pop            
        //    57: goto            33
        //    60: astore          4
        //    62: aload_2        
        //    63: astore          5
        //    65: aload_1        
        //    66: astore          6
        //    68: aload           6
        //    70: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    73: aload           5
        //    75: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    78: aload           4
        //    80: athrow         
        //    81: aload_3        
        //    82: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    85: astore          10
        //    87: aload_1        
        //    88: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    91: aload_2        
        //    92: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    95: aload           10
        //    97: areturn        
        //    98: astore          4
        //   100: aconst_null    
        //   101: astore          6
        //   103: aconst_null    
        //   104: astore          5
        //   106: goto            68
        //   109: astore          4
        //   111: aload_1        
        //   112: astore          6
        //   114: aconst_null    
        //   115: astore          5
        //   117: goto            68
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      9      98     109    Any
        //  9      18     109    120    Any
        //  18     33     60     68     Any
        //  33     41     60     68     Any
        //  47     57     60     68     Any
        //  81     87     60     68     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Map<String, String> readStringMapFromParcel(final Parcel parcel) {
        final int int1 = parcel.readInt();
        Map<String, String> map;
        if (int1 < 0) {
            map = null;
        }
        else {
            map = new HashMap<String, String>();
            for (int i = 0; i < int1; ++i) {
                map.put(parcel.readString(), parcel.readString());
            }
        }
        return map;
    }
    
    private static void refreshAvailableExternalStorage() {
        try {
            if (externalStorageExists()) {
                final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                Utility.availableExternalStorageGB = statFs.getAvailableBlocks() * statFs.getBlockSize();
            }
            Utility.availableExternalStorageGB = convertBytesToGB(Utility.availableExternalStorageGB);
        }
        catch (Exception ex) {}
    }
    
    private static int refreshBestGuessNumberOfCPUCores() {
        if (Utility.numCPUCores > 0) {
            return Utility.numCPUCores;
        }
        while (true) {
            try {
                final File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(final File file, final String s) {
                        return Pattern.matches("cpu[0-9]+", s);
                    }
                });
                if (listFiles != null) {
                    Utility.numCPUCores = listFiles.length;
                }
                if (Utility.numCPUCores <= 0) {
                    Utility.numCPUCores = Math.max(Runtime.getRuntime().availableProcessors(), 1);
                }
                return Utility.numCPUCores;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    private static void refreshCarrierName(final Context context) {
        if (!Utility.carrierName.equals("NoCarrier")) {
            return;
        }
        try {
            Utility.carrierName = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
        }
        catch (Exception ex) {}
    }
    
    private static void refreshPeriodicExtendedDeviceInfo(final Context context) {
        if (Utility.timestampOfLastCheck == -1L || System.currentTimeMillis() - Utility.timestampOfLastCheck >= 1800000L) {
            Utility.timestampOfLastCheck = System.currentTimeMillis();
            refreshTimezone();
            refreshCarrierName(context);
            refreshTotalExternalStorage();
            refreshAvailableExternalStorage();
        }
    }
    
    private static void refreshTimezone() {
        try {
            final TimeZone default1 = TimeZone.getDefault();
            Utility.deviceTimezone = default1.getDisplayName(default1.inDaylightTime(new Date()), 0);
        }
        catch (Exception ex) {}
    }
    
    private static void refreshTotalExternalStorage() {
        try {
            if (externalStorageExists()) {
                final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                Utility.totalExternalStorageGB = statFs.getBlockCount() * statFs.getBlockSize();
            }
            Utility.totalExternalStorageGB = convertBytesToGB(Utility.totalExternalStorageGB);
        }
        catch (Exception ex) {}
    }
    
    public static String safeGetStringFromResponse(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            return jsonObject.optString(s, "");
        }
        return "";
    }
    
    public static void setAppEventAttributionParameters(final JSONObject jsonObject, final AttributionIdentifiers attributionIdentifiers, final String s, final boolean b) throws JSONException {
        boolean b2 = true;
        if (attributionIdentifiers != null && attributionIdentifiers.getAttributionId() != null) {
            jsonObject.put("attribution", (Object)attributionIdentifiers.getAttributionId());
        }
        if (attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null) {
            jsonObject.put("advertiser_id", (Object)attributionIdentifiers.getAndroidAdvertiserId());
            jsonObject.put("advertiser_tracking_enabled", !attributionIdentifiers.isTrackingLimited() && b2);
        }
        if (attributionIdentifiers != null && attributionIdentifiers.getAndroidInstallerPackage() != null) {
            jsonObject.put("installer_package", (Object)attributionIdentifiers.getAndroidInstallerPackage());
        }
        jsonObject.put("anon_id", (Object)s);
        if (b) {
            b2 = false;
        }
        jsonObject.put("application_tracking_enabled", b2);
    }
    
    public static void setAppEventExtendedDeviceInfoParameters(final JSONObject p0, final Context p1) throws JSONException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONArray;
        //     3: dup            
        //     4: invokespecial   org/json/JSONArray.<init>:()V
        //     7: astore_2       
        //     8: aload_2        
        //     9: ldc             "a2"
        //    11: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    14: pop            
        //    15: aload_1        
        //    16: invokestatic    com/facebook/internal/Utility.refreshPeriodicExtendedDeviceInfo:(Landroid/content/Context;)V
        //    19: aload_1        
        //    20: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    23: astore          4
        //    25: iconst_m1      
        //    26: istore          5
        //    28: ldc             ""
        //    30: astore          6
        //    32: aload_1        
        //    33: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    36: aload           4
        //    38: iconst_0       
        //    39: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    42: astore          35
        //    44: aload           35
        //    46: getfield        android/content/pm/PackageInfo.versionCode:I
        //    49: istore          5
        //    51: aload           35
        //    53: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    56: astore          6
        //    58: aload_2        
        //    59: aload           4
        //    61: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    64: pop            
        //    65: aload_2        
        //    66: iload           5
        //    68: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //    71: pop            
        //    72: aload_2        
        //    73: aload           6
        //    75: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    78: pop            
        //    79: aload_2        
        //    80: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //    83: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    86: pop            
        //    87: aload_2        
        //    88: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //    91: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    94: pop            
        //    95: aload_1        
        //    96: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    99: invokevirtual   android/content/res/Resources.getConfiguration:()Landroid/content/res/Configuration;
        //   102: getfield        android/content/res/Configuration.locale:Ljava/util/Locale;
        //   105: astore          14
        //   107: aload_2        
        //   108: new             Ljava/lang/StringBuilder;
        //   111: dup            
        //   112: invokespecial   java/lang/StringBuilder.<init>:()V
        //   115: aload           14
        //   117: invokevirtual   java/util/Locale.getLanguage:()Ljava/lang/String;
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: ldc_w           "_"
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: aload           14
        //   131: invokevirtual   java/util/Locale.getCountry:()Ljava/lang/String;
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   140: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   143: pop            
        //   144: aload_2        
        //   145: getstatic       com/facebook/internal/Utility.deviceTimezone:Ljava/lang/String;
        //   148: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   151: pop            
        //   152: aload_2        
        //   153: getstatic       com/facebook/internal/Utility.carrierName:Ljava/lang/String;
        //   156: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   159: pop            
        //   160: iconst_0       
        //   161: istore          18
        //   163: iconst_0       
        //   164: istore          19
        //   166: dconst_0       
        //   167: dstore          20
        //   169: aload_1        
        //   170: ldc_w           "window"
        //   173: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   176: checkcast       Landroid/view/WindowManager;
        //   179: astore          31
        //   181: iconst_0       
        //   182: istore          19
        //   184: iconst_0       
        //   185: istore          18
        //   187: aload           31
        //   189: ifnull          243
        //   192: aload           31
        //   194: invokeinterface android/view/WindowManager.getDefaultDisplay:()Landroid/view/Display;
        //   199: astore          32
        //   201: new             Landroid/util/DisplayMetrics;
        //   204: dup            
        //   205: invokespecial   android/util/DisplayMetrics.<init>:()V
        //   208: astore          33
        //   210: aload           32
        //   212: aload           33
        //   214: invokevirtual   android/view/Display.getMetrics:(Landroid/util/DisplayMetrics;)V
        //   217: aload           33
        //   219: getfield        android/util/DisplayMetrics.widthPixels:I
        //   222: istore          18
        //   224: aload           33
        //   226: getfield        android/util/DisplayMetrics.heightPixels:I
        //   229: istore          19
        //   231: aload           33
        //   233: getfield        android/util/DisplayMetrics.density:F
        //   236: fstore          34
        //   238: fload           34
        //   240: f2d            
        //   241: dstore          20
        //   243: aload_2        
        //   244: iload           18
        //   246: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //   249: pop            
        //   250: aload_2        
        //   251: iload           19
        //   253: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //   256: pop            
        //   257: iconst_1       
        //   258: anewarray       Ljava/lang/Object;
        //   261: astore          25
        //   263: aload           25
        //   265: iconst_0       
        //   266: dload           20
        //   268: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   271: aastore        
        //   272: aload_2        
        //   273: ldc_w           "%.2f"
        //   276: aload           25
        //   278: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   281: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   284: pop            
        //   285: aload_2        
        //   286: invokestatic    com/facebook/internal/Utility.refreshBestGuessNumberOfCPUCores:()I
        //   289: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //   292: pop            
        //   293: aload_2        
        //   294: getstatic       com/facebook/internal/Utility.totalExternalStorageGB:J
        //   297: invokevirtual   org/json/JSONArray.put:(J)Lorg/json/JSONArray;
        //   300: pop            
        //   301: aload_2        
        //   302: getstatic       com/facebook/internal/Utility.availableExternalStorageGB:J
        //   305: invokevirtual   org/json/JSONArray.put:(J)Lorg/json/JSONArray;
        //   308: pop            
        //   309: aload_0        
        //   310: ldc_w           "extinfo"
        //   313: aload_2        
        //   314: invokevirtual   org/json/JSONArray.toString:()Ljava/lang/String;
        //   317: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   320: pop            
        //   321: return         
        //   322: astore          13
        //   324: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   327: astore          14
        //   329: goto            107
        //   332: astore          22
        //   334: goto            243
        //   337: astore          7
        //   339: goto            58
        //    Exceptions:
        //  throws org.json.JSONException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  32     58     337    342    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  95     107    322    332    Ljava/lang/Exception;
        //  169    181    332    337    Ljava/lang/Exception;
        //  192    238    332    337    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String sha1hash(final String s) {
        return hashWithAlgorithm("SHA-1", s);
    }
    
    public static String sha1hash(final byte[] array) {
        return hashWithAlgorithm("SHA-1", array);
    }
    
    public static boolean stringsEqualOrEmpty(final String s, final String s2) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        final boolean empty2 = TextUtils.isEmpty((CharSequence)s2);
        return (empty && empty2) || (!empty && !empty2 && s.equals(s2));
    }
    
    public static JSONArray tryGetJSONArrayFromResponse(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            return jsonObject.optJSONArray(s);
        }
        return null;
    }
    
    public static JSONObject tryGetJSONObjectFromResponse(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            return jsonObject.optJSONObject(s);
        }
        return null;
    }
    
    public static <T> Collection<T> unmodifiableCollection(final T... array) {
        return Collections.unmodifiableCollection((Collection<? extends T>)Arrays.asList(array));
    }
    
    public static void writeStringMapToParcel(final Parcel parcel, final Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
        }
        else {
            parcel.writeInt(map.size());
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                parcel.writeString((String)entry.getKey());
                parcel.writeString((String)entry.getValue());
            }
        }
    }
    
    public static class DialogFeatureConfig
    {
        private String dialogName;
        private Uri fallbackUrl;
        private String featureName;
        private int[] featureVersionSpec;
        
        private DialogFeatureConfig(final String dialogName, final String featureName, final Uri fallbackUrl, final int[] featureVersionSpec) {
            this.dialogName = dialogName;
            this.featureName = featureName;
            this.fallbackUrl = fallbackUrl;
            this.featureVersionSpec = featureVersionSpec;
        }
        
        private static DialogFeatureConfig parseDialogConfig(final JSONObject jsonObject) {
            final String optString = jsonObject.optString("name");
            if (!Utility.isNullOrEmpty(optString)) {
                final String[] split = optString.split("\\|");
                if (split.length == 2) {
                    final String s = split[0];
                    final String s2 = split[1];
                    if (!Utility.isNullOrEmpty(s) && !Utility.isNullOrEmpty(s2)) {
                        final String optString2 = jsonObject.optString("url");
                        final boolean nullOrEmpty = Utility.isNullOrEmpty(optString2);
                        Uri parse = null;
                        if (!nullOrEmpty) {
                            parse = Uri.parse(optString2);
                        }
                        return new DialogFeatureConfig(s, s2, parse, parseVersionSpec(jsonObject.optJSONArray("versions")));
                    }
                }
            }
            return null;
        }
        
        private static int[] parseVersionSpec(final JSONArray jsonArray) {
            int[] array = null;
            if (jsonArray != null) {
                final int length = jsonArray.length();
                array = new int[length];
                int i = 0;
            Label_0062_Outer:
                while (i < length) {
                    int n = jsonArray.optInt(i, -1);
                    while (true) {
                        if (n != -1) {
                            break Label_0062;
                        }
                        final String optString = jsonArray.optString(i);
                        if (Utility.isNullOrEmpty(optString)) {
                            break Label_0062;
                        }
                        try {
                            n = Integer.parseInt(optString);
                            array[i] = n;
                            ++i;
                            continue Label_0062_Outer;
                        }
                        catch (NumberFormatException ex) {
                            Utility.logd("FacebookSDK", ex);
                            n = -1;
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
            return array;
        }
        
        public String getDialogName() {
            return this.dialogName;
        }
        
        public Uri getFallbackUrl() {
            return this.fallbackUrl;
        }
        
        public String getFeatureName() {
            return this.featureName;
        }
        
        public int[] getVersionSpec() {
            return this.featureVersionSpec;
        }
    }
    
    public static class FetchedAppSettings
    {
        private Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap;
        private FacebookRequestErrorClassification errorClassification;
        private String nuxContent;
        private boolean nuxEnabled;
        private boolean supportsImplicitLogging;
        
        private FetchedAppSettings(final boolean supportsImplicitLogging, final String nuxContent, final boolean nuxEnabled, final Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap, final FacebookRequestErrorClassification errorClassification) {
            this.supportsImplicitLogging = supportsImplicitLogging;
            this.nuxContent = nuxContent;
            this.nuxEnabled = nuxEnabled;
            this.dialogConfigMap = dialogConfigMap;
            this.errorClassification = errorClassification;
        }
        
        public Map<String, Map<String, DialogFeatureConfig>> getDialogConfigurations() {
            return this.dialogConfigMap;
        }
        
        public FacebookRequestErrorClassification getErrorClassification() {
            return this.errorClassification;
        }
        
        public String getNuxContent() {
            return this.nuxContent;
        }
        
        public boolean getNuxEnabled() {
            return this.nuxEnabled;
        }
        
        public boolean supportsImplicitLogging() {
            return this.supportsImplicitLogging;
        }
    }
    
    public interface GraphMeRequestWithCacheCallback
    {
        void onFailure(final FacebookException p0);
        
        void onSuccess(final JSONObject p0);
    }
    
    public interface Mapper<T, K>
    {
        K apply(final T p0);
    }
    
    public static class PermissionsPair
    {
        List<String> declinedPermissions;
        List<String> grantedPermissions;
        
        public PermissionsPair(final List<String> grantedPermissions, final List<String> declinedPermissions) {
            this.grantedPermissions = grantedPermissions;
            this.declinedPermissions = declinedPermissions;
        }
        
        public List<String> getDeclinedPermissions() {
            return this.declinedPermissions;
        }
        
        public List<String> getGrantedPermissions() {
            return this.grantedPermissions;
        }
    }
    
    public interface Predicate<T>
    {
        boolean apply(final T p0);
    }
}
