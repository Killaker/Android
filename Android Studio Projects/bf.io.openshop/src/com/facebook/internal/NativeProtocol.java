package com.facebook.internal;

import java.util.concurrent.atomic.*;
import android.net.*;
import com.facebook.login.*;
import android.text.*;
import com.facebook.*;
import android.content.*;
import android.database.*;
import java.util.*;
import android.os.*;
import android.content.pm.*;

public final class NativeProtocol
{
    public static final String ACTION_APPINVITE_DIALOG = "com.facebook.platform.action.request.APPINVITES_DIALOG";
    public static final String ACTION_FEED_DIALOG = "com.facebook.platform.action.request.FEED_DIALOG";
    public static final String ACTION_LIKE_DIALOG = "com.facebook.platform.action.request.LIKE_DIALOG";
    public static final String ACTION_MESSAGE_DIALOG = "com.facebook.platform.action.request.MESSAGE_DIALOG";
    public static final String ACTION_OGACTIONPUBLISH_DIALOG = "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    public static final String ACTION_OGMESSAGEPUBLISH_DIALOG = "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    public static final String AUDIENCE_EVERYONE = "everyone";
    public static final String AUDIENCE_FRIENDS = "friends";
    public static final String AUDIENCE_ME = "only_me";
    public static final String BRIDGE_ARG_ACTION_ID_STRING = "action_id";
    public static final String BRIDGE_ARG_APP_NAME_STRING = "app_name";
    public static final String BRIDGE_ARG_ERROR_BUNDLE = "error";
    public static final String BRIDGE_ARG_ERROR_CODE = "error_code";
    public static final String BRIDGE_ARG_ERROR_DESCRIPTION = "error_description";
    public static final String BRIDGE_ARG_ERROR_JSON = "error_json";
    public static final String BRIDGE_ARG_ERROR_SUBCODE = "error_subcode";
    public static final String BRIDGE_ARG_ERROR_TYPE = "error_type";
    private static final String CONTENT_SCHEME = "content://";
    public static final String ERROR_APPLICATION_ERROR = "ApplicationError";
    public static final String ERROR_NETWORK_ERROR = "NetworkError";
    public static final String ERROR_PERMISSION_DENIED = "PermissionDenied";
    public static final String ERROR_PROTOCOL_ERROR = "ProtocolError";
    public static final String ERROR_SERVICE_DISABLED = "ServiceDisabled";
    public static final String ERROR_UNKNOWN_ERROR = "UnknownError";
    public static final String ERROR_USER_CANCELED = "UserCanceled";
    public static final String EXTRA_ACCESS_TOKEN = "com.facebook.platform.extra.ACCESS_TOKEN";
    public static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
    public static final String EXTRA_APPLICATION_NAME = "com.facebook.platform.extra.APPLICATION_NAME";
    public static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    public static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    public static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    public static final String EXTRA_GET_INSTALL_DATA_PACKAGE = "com.facebook.platform.extra.INSTALLDATA_PACKAGE";
    public static final String EXTRA_PERMISSIONS = "com.facebook.platform.extra.PERMISSIONS";
    public static final String EXTRA_PROTOCOL_ACTION = "com.facebook.platform.protocol.PROTOCOL_ACTION";
    public static final String EXTRA_PROTOCOL_BRIDGE_ARGS = "com.facebook.platform.protocol.BRIDGE_ARGS";
    public static final String EXTRA_PROTOCOL_CALL_ID = "com.facebook.platform.protocol.CALL_ID";
    public static final String EXTRA_PROTOCOL_METHOD_ARGS = "com.facebook.platform.protocol.METHOD_ARGS";
    public static final String EXTRA_PROTOCOL_METHOD_RESULTS = "com.facebook.platform.protocol.RESULT_ARGS";
    public static final String EXTRA_PROTOCOL_VERSION = "com.facebook.platform.protocol.PROTOCOL_VERSION";
    static final String EXTRA_PROTOCOL_VERSIONS = "com.facebook.platform.extra.PROTOCOL_VERSIONS";
    public static final String EXTRA_USER_ID = "com.facebook.platform.extra.USER_ID";
    private static final NativeAppInfo FACEBOOK_APP_INFO;
    private static final String FACEBOOK_PROXY_AUTH_ACTIVITY = "com.facebook.katana.ProxyAuth";
    public static final String FACEBOOK_PROXY_AUTH_APP_ID_KEY = "client_id";
    public static final String FACEBOOK_PROXY_AUTH_E2E_KEY = "e2e";
    public static final String FACEBOOK_PROXY_AUTH_PERMISSIONS_KEY = "scope";
    private static final String FACEBOOK_TOKEN_REFRESH_ACTIVITY = "com.facebook.katana.platform.TokenRefreshService";
    public static final String IMAGE_URL_KEY = "url";
    public static final String IMAGE_USER_GENERATED_KEY = "user_generated";
    static final String INTENT_ACTION_PLATFORM_ACTIVITY = "com.facebook.platform.PLATFORM_ACTIVITY";
    static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
    private static final List<Integer> KNOWN_PROTOCOL_VERSIONS;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REPLY = 65537;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REQUEST = 65536;
    public static final int MESSAGE_GET_INSTALL_DATA_REPLY = 65541;
    public static final int MESSAGE_GET_INSTALL_DATA_REQUEST = 65540;
    public static final int MESSAGE_GET_LIKE_STATUS_REPLY = 65543;
    public static final int MESSAGE_GET_LIKE_STATUS_REQUEST = 65542;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REPLY = 65539;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REQUEST = 65538;
    public static final int NO_PROTOCOL_AVAILABLE = -1;
    public static final String OPEN_GRAPH_CREATE_OBJECT_KEY = "fbsdk:create_object";
    private static final String PLATFORM_PROVIDER = ".provider.PlatformProvider";
    private static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
    private static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
    public static final int PROTOCOL_VERSION_20121101 = 20121101;
    public static final int PROTOCOL_VERSION_20130502 = 20130502;
    public static final int PROTOCOL_VERSION_20130618 = 20130618;
    public static final int PROTOCOL_VERSION_20131107 = 20131107;
    public static final int PROTOCOL_VERSION_20140204 = 20140204;
    public static final int PROTOCOL_VERSION_20140324 = 20140324;
    public static final int PROTOCOL_VERSION_20140701 = 20140701;
    public static final int PROTOCOL_VERSION_20141001 = 20141001;
    public static final int PROTOCOL_VERSION_20141028 = 20141028;
    public static final int PROTOCOL_VERSION_20141107 = 20141107;
    public static final int PROTOCOL_VERSION_20141218 = 20141218;
    public static final String RESULT_ARGS_ACCESS_TOKEN = "access_token";
    public static final String RESULT_ARGS_DIALOG_COMPLETE_KEY = "didComplete";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY = "completionGesture";
    public static final String RESULT_ARGS_EXPIRES_SECONDS_SINCE_EPOCH = "expires_seconds_since_epoch";
    public static final String RESULT_ARGS_PERMISSIONS = "permissions";
    public static final String STATUS_ERROR_CODE = "com.facebook.platform.status.ERROR_CODE";
    public static final String STATUS_ERROR_DESCRIPTION = "com.facebook.platform.status.ERROR_DESCRIPTION";
    public static final String STATUS_ERROR_JSON = "com.facebook.platform.status.ERROR_JSON";
    public static final String STATUS_ERROR_SUBCODE = "com.facebook.platform.status.ERROR_SUBCODE";
    public static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
    public static final String WEB_DIALOG_ACTION = "action";
    public static final String WEB_DIALOG_IS_FALLBACK = "is_fallback";
    public static final String WEB_DIALOG_PARAMS = "params";
    public static final String WEB_DIALOG_URL = "url";
    private static Map<String, List<NativeAppInfo>> actionToAppInfoMap;
    private static List<NativeAppInfo> facebookAppInfoList;
    private static AtomicBoolean protocolVersionsAsyncUpdating;
    
    static {
        FACEBOOK_APP_INFO = (NativeAppInfo)new KatanaAppInfo();
        NativeProtocol.facebookAppInfoList = buildFacebookAppList();
        NativeProtocol.actionToAppInfoMap = buildActionToAppInfoMap();
        NativeProtocol.protocolVersionsAsyncUpdating = new AtomicBoolean(false);
        KNOWN_PROTOCOL_VERSIONS = Arrays.asList(20141218, 20141107, 20141028, 20141001, 20140701, 20140324, 20140204, 20131107, 20130618, 20130502, 20121101);
    }
    
    private static Map<String, List<NativeAppInfo>> buildActionToAppInfoMap() {
        final HashMap<String, ArrayList<MessengerAppInfo>> hashMap = (HashMap<String, ArrayList<MessengerAppInfo>>)new HashMap<String, List<MessengerAppInfo>>();
        final ArrayList<MessengerAppInfo> list = new ArrayList<MessengerAppInfo>();
        list.add(new MessengerAppInfo());
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", (List<MessengerAppInfo>)NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", list);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", list);
        return (Map<String, List<NativeAppInfo>>)hashMap;
    }
    
    private static List<NativeAppInfo> buildFacebookAppList() {
        final ArrayList<WakizashiAppInfo> list = new ArrayList<WakizashiAppInfo>();
        list.add(NativeProtocol.FACEBOOK_APP_INFO);
        list.add(new WakizashiAppInfo());
        return (List<NativeAppInfo>)list;
    }
    
    private static Uri buildPlatformProviderVersionURI(final NativeAppInfo nativeAppInfo) {
        return Uri.parse("content://" + nativeAppInfo.getPackage() + ".provider.PlatformProvider/versions");
    }
    
    public static int computeLatestAvailableVersionFromVersionSpec(final TreeSet<Integer> set, final int n, final int[] array) {
        int n2 = -1 + array.length;
        final Iterator<Integer> descendingIterator = set.descendingIterator();
        int max = -1;
        while (descendingIterator.hasNext()) {
            final int intValue = descendingIterator.next();
            max = Math.max(max, intValue);
            while (n2 >= 0 && array[n2] > intValue) {
                --n2;
            }
            if (n2 < 0) {
                break;
            }
            if (array[n2] == intValue) {
                int min;
                if (n2 % 2 == 0) {
                    min = Math.min(max, n);
                }
                else {
                    min = -1;
                }
                return min;
            }
        }
        return -1;
    }
    
    public static Bundle createBundleForException(final FacebookException ex) {
        Bundle bundle;
        if (ex == null) {
            bundle = null;
        }
        else {
            bundle = new Bundle();
            bundle.putString("error_description", ex.toString());
            if (ex instanceof FacebookOperationCanceledException) {
                bundle.putString("error_type", "UserCanceled");
                return bundle;
            }
        }
        return bundle;
    }
    
    public static Intent createPlatformActivityIntent(final Context context, final String s, final String s2, final int n, final Bundle bundle) {
        final Intent activityIntent = findActivityIntent(context, "com.facebook.platform.PLATFORM_ACTIVITY", s2);
        if (activityIntent == null) {
            return null;
        }
        setupProtocolRequestIntent(activityIntent, s, s2, n, bundle);
        return activityIntent;
    }
    
    public static Intent createPlatformServiceIntent(final Context context) {
        for (final NativeAppInfo nativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent validateServiceIntent = validateServiceIntent(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(nativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }
    
    public static Intent createProtocolResultIntent(final Intent intent, final Bundle bundle, final FacebookException ex) {
        final UUID callIdFromIntent = getCallIdFromIntent(intent);
        Intent intent2;
        if (callIdFromIntent == null) {
            intent2 = null;
        }
        else {
            intent2 = new Intent();
            intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", getProtocolVersionFromIntent(intent));
            final Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", callIdFromIntent.toString());
            if (ex != null) {
                bundle2.putBundle("error", createBundleForException(ex));
            }
            intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            if (bundle != null) {
                intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
                return intent2;
            }
        }
        return intent2;
    }
    
    public static Intent createProxyAuthIntent(final Context context, final String s, final Collection<String> collection, final String s2, final boolean b, final boolean b2, final DefaultAudience defaultAudience) {
        for (final NativeAppInfo nativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent putExtra = new Intent().setClassName(nativeAppInfo.getPackage(), "com.facebook.katana.ProxyAuth").putExtra("client_id", s);
            if (!Utility.isNullOrEmpty((Collection<Object>)collection)) {
                putExtra.putExtra("scope", TextUtils.join((CharSequence)",", (Iterable)collection));
            }
            if (!Utility.isNullOrEmpty(s2)) {
                putExtra.putExtra("e2e", s2);
            }
            putExtra.putExtra("response_type", "token,signed_request");
            putExtra.putExtra("return_scopes", "true");
            if (b2) {
                putExtra.putExtra("default_audience", defaultAudience.getNativeProtocolAudience());
            }
            putExtra.putExtra("legacy_override", "v2.5");
            if (b) {
                putExtra.putExtra("auth_type", "rerequest");
            }
            final Intent validateActivityIntent = validateActivityIntent(context, putExtra, nativeAppInfo);
            if (validateActivityIntent != null) {
                return validateActivityIntent;
            }
        }
        return null;
    }
    
    public static Intent createTokenRefreshIntent(final Context context) {
        for (final NativeAppInfo nativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent validateServiceIntent = validateServiceIntent(context, new Intent().setClassName(nativeAppInfo.getPackage(), "com.facebook.katana.platform.TokenRefreshService"), nativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }
    
    private static TreeSet<Integer> fetchAllAvailableProtocolVersionsForAppInfo(final NativeAppInfo nativeAppInfo) {
        final TreeSet<Integer> set = new TreeSet<Integer>();
        final ContentResolver contentResolver = FacebookSdk.getApplicationContext().getContentResolver();
        final String[] array = { "version" };
        final Uri buildPlatformProviderVersionURI = buildPlatformProviderVersionURI(nativeAppInfo);
        Cursor query = null;
        try {
            final ProviderInfo resolveContentProvider = FacebookSdk.getApplicationContext().getPackageManager().resolveContentProvider(nativeAppInfo.getPackage() + ".provider.PlatformProvider", 0);
            query = null;
            if (resolveContentProvider != null) {
                query = contentResolver.query(buildPlatformProviderVersionURI, array, (String)null, (String[])null, (String)null);
                if (query != null) {
                    while (query.moveToNext()) {
                        set.add(query.getInt(query.getColumnIndex("version")));
                    }
                }
            }
        }
        finally {
            if (query != null) {
                query.close();
            }
        }
        if (query != null) {
            query.close();
        }
        return set;
    }
    
    private static Intent findActivityIntent(final Context context, final String action, final String s) {
        final List<NativeAppInfo> list = NativeProtocol.actionToAppInfoMap.get(s);
        Intent validateActivityIntent;
        if (list == null) {
            validateActivityIntent = null;
        }
        else {
            validateActivityIntent = null;
            for (final NativeAppInfo nativeAppInfo : list) {
                validateActivityIntent = validateActivityIntent(context, new Intent().setAction(action).setPackage(nativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeAppInfo);
                if (validateActivityIntent != null) {
                    return validateActivityIntent;
                }
            }
        }
        return validateActivityIntent;
    }
    
    public static Bundle getBridgeArgumentsFromIntent(final Intent intent) {
        if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
            return null;
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
    }
    
    public static UUID getCallIdFromIntent(final Intent intent) {
        if (intent != null) {
            Label_0050: {
                if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
                    break Label_0050;
                }
                final Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
                String s = null;
                if (bundleExtra != null) {
                    s = bundleExtra.getString("action_id");
                }
                while (true) {
                    if (s == null) {
                        return null;
                    }
                    try {
                        return UUID.fromString(s);
                        s = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        return null;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    public static Bundle getErrorDataFromResultIntent(final Intent intent) {
        if (!isErrorResult(intent)) {
            return null;
        }
        final Bundle bridgeArgumentsFromIntent = getBridgeArgumentsFromIntent(intent);
        if (bridgeArgumentsFromIntent != null) {
            return bridgeArgumentsFromIntent.getBundle("error");
        }
        return intent.getExtras();
    }
    
    public static FacebookException getExceptionFromErrorData(final Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String s = bundle.getString("error_type");
        if (s == null) {
            s = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String s2 = bundle.getString("error_description");
        if (s2 == null) {
            s2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (s != null && s.equalsIgnoreCase("UserCanceled")) {
            return new FacebookOperationCanceledException(s2);
        }
        return new FacebookException(s2);
    }
    
    public static int getLatestAvailableProtocolVersionForAction(final String s, final int[] array) {
        return getLatestAvailableProtocolVersionForAppInfoList(NativeProtocol.actionToAppInfoMap.get(s), array);
    }
    
    private static int getLatestAvailableProtocolVersionForAppInfoList(final List<NativeAppInfo> list, final int[] array) {
        updateAllAvailableProtocolVersionsAsync();
        if (list == null) {
            return -1;
        }
        final Iterator<NativeAppInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            final int computeLatestAvailableVersionFromVersionSpec = computeLatestAvailableVersionFromVersionSpec(iterator.next().getAvailableVersions(), getLatestKnownVersion(), array);
            if (computeLatestAvailableVersionFromVersionSpec != -1) {
                return computeLatestAvailableVersionFromVersionSpec;
            }
        }
        return -1;
    }
    
    public static int getLatestAvailableProtocolVersionForService(final int n) {
        return getLatestAvailableProtocolVersionForAppInfoList(NativeProtocol.facebookAppInfoList, new int[] { n });
    }
    
    public static final int getLatestKnownVersion() {
        return NativeProtocol.KNOWN_PROTOCOL_VERSIONS.get(0);
    }
    
    public static Bundle getMethodArgumentsFromIntent(final Intent intent) {
        if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
            return intent.getExtras();
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
    }
    
    public static int getProtocolVersionFromIntent(final Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }
    
    public static Bundle getSuccessResultsFromIntent(final Intent intent) {
        final int protocolVersionFromIntent = getProtocolVersionFromIntent(intent);
        final Bundle extras = intent.getExtras();
        if (!isVersionCompatibleWithBucketedIntent(protocolVersionFromIntent) || extras == null) {
            return extras;
        }
        return extras.getBundle("com.facebook.platform.protocol.RESULT_ARGS");
    }
    
    public static boolean isErrorResult(final Intent intent) {
        final Bundle bridgeArgumentsFromIntent = getBridgeArgumentsFromIntent(intent);
        if (bridgeArgumentsFromIntent != null) {
            return bridgeArgumentsFromIntent.containsKey("error");
        }
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }
    
    public static boolean isVersionCompatibleWithBucketedIntent(final int n) {
        return NativeProtocol.KNOWN_PROTOCOL_VERSIONS.contains(n) && n >= 20140701;
    }
    
    public static void setupProtocolRequestIntent(final Intent intent, final String s, final String s2, final int n, final Bundle bundle) {
        final String applicationId = FacebookSdk.getApplicationId();
        final String applicationName = FacebookSdk.getApplicationName();
        intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", n).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", s2).putExtra("com.facebook.platform.extra.APPLICATION_ID", applicationId);
        if (isVersionCompatibleWithBucketedIntent(n)) {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", s);
            Utility.putNonEmptyString(bundle2, "app_name", applicationName);
            intent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            Bundle bundle3;
            if (bundle == null) {
                bundle3 = new Bundle();
            }
            else {
                bundle3 = bundle;
            }
            intent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle3);
            return;
        }
        intent.putExtra("com.facebook.platform.protocol.CALL_ID", s);
        if (!Utility.isNullOrEmpty(applicationName)) {
            intent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", applicationName);
        }
        intent.putExtras(bundle);
    }
    
    public static void updateAllAvailableProtocolVersionsAsync() {
        if (!NativeProtocol.protocolVersionsAsyncUpdating.compareAndSet(false, true)) {
            return;
        }
        FacebookSdk.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Iterator<NativeAppInfo> iterator = NativeProtocol.facebookAppInfoList.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().fetchAvailableVersions(true);
                    }
                }
                finally {
                    NativeProtocol.protocolVersionsAsyncUpdating.set(false);
                }
                NativeProtocol.protocolVersionsAsyncUpdating.set(false);
            }
        });
    }
    
    static Intent validateActivityIntent(final Context context, Intent intent, final NativeAppInfo nativeAppInfo) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                return null;
            }
            if (!nativeAppInfo.validateSignature(context, resolveActivity.activityInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    static Intent validateServiceIntent(final Context context, Intent intent, final NativeAppInfo nativeAppInfo) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null) {
                return null;
            }
            if (!nativeAppInfo.validateSignature(context, resolveService.serviceInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    private static class KatanaAppInfo extends NativeAppInfo
    {
        static final String KATANA_PACKAGE = "com.facebook.katana";
        
        @Override
        protected String getPackage() {
            return "com.facebook.katana";
        }
    }
    
    private static class MessengerAppInfo extends NativeAppInfo
    {
        static final String MESSENGER_PACKAGE = "com.facebook.orca";
        
        @Override
        protected String getPackage() {
            return "com.facebook.orca";
        }
    }
    
    private abstract static class NativeAppInfo
    {
        private static final String FBI_HASH = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
        private static final String FBL_HASH = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
        private static final String FBR_HASH = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
        private static final HashSet<String> validAppSignatureHashes;
        private TreeSet<Integer> availableVersions;
        
        static {
            validAppSignatureHashes = buildAppSignatureHashes();
        }
        
        private static HashSet<String> buildAppSignatureHashes() {
            final HashSet<String> set = new HashSet<String>();
            set.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
            set.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
            set.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
            return set;
        }
        
        private void fetchAvailableVersions(final boolean b) {
            // monitorenter(this)
            Label_0013: {
                if (b) {
                    break Label_0013;
                }
                try {
                    if (this.availableVersions == null) {
                        this.availableVersions = fetchAllAvailableProtocolVersionsForAppInfo(this);
                    }
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        public TreeSet<Integer> getAvailableVersions() {
            if (this.availableVersions == null) {
                this.fetchAvailableVersions(false);
            }
            return this.availableVersions;
        }
        
        protected abstract String getPackage();
        
        public boolean validateSignature(final Context context, final String s) {
            final String brand = Build.BRAND;
            final int flags = context.getApplicationInfo().flags;
            if (!brand.startsWith("generic") || (flags & 0x2) == 0x0) {
                try {
                    final Signature[] signatures = context.getPackageManager().getPackageInfo(s, 64).signatures;
                    for (int length = signatures.length, i = 0; i < length; ++i) {
                        if (NativeAppInfo.validAppSignatureHashes.contains(Utility.sha1hash(signatures[i].toByteArray()))) {
                            return true;
                        }
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    return false;
                }
                return false;
            }
            return true;
        }
    }
    
    private static class WakizashiAppInfo extends NativeAppInfo
    {
        static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";
        
        @Override
        protected String getPackage() {
            return "com.facebook.wakizashi";
        }
    }
}
