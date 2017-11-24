package com.facebook;

import android.os.*;
import java.io.*;
import org.json.*;
import android.content.*;
import java.util.*;
import com.facebook.internal.*;

final class LegacyTokenHelper
{
    public static final String APPLICATION_ID_KEY = "com.facebook.TokenCachingStrategy.ApplicationId";
    public static final String DECLINED_PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.DeclinedPermissions";
    public static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    public static final String EXPIRATION_DATE_KEY = "com.facebook.TokenCachingStrategy.ExpirationDate";
    private static final long INVALID_BUNDLE_MILLISECONDS = Long.MIN_VALUE;
    private static final String IS_SSO_KEY = "com.facebook.TokenCachingStrategy.IsSSO";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    public static final String LAST_REFRESH_DATE_KEY = "com.facebook.TokenCachingStrategy.LastRefreshDate";
    public static final String PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.Permissions";
    private static final String TAG;
    public static final String TOKEN_KEY = "com.facebook.TokenCachingStrategy.Token";
    public static final String TOKEN_SOURCE_KEY = "com.facebook.TokenCachingStrategy.AccessTokenSource";
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;
    
    static {
        TAG = LegacyTokenHelper.class.getSimpleName();
    }
    
    public LegacyTokenHelper(final Context context) {
        this(context, null);
    }
    
    public LegacyTokenHelper(Context context, String cacheKey) {
        Validate.notNull(context, "context");
        if (Utility.isNullOrEmpty(cacheKey)) {
            cacheKey = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        }
        this.cacheKey = cacheKey;
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.cache = context.getSharedPreferences(this.cacheKey, 0);
    }
    
    private void deserializeKey(final String s, final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject(this.cache.getString(s, "{}"));
        final String string = jsonObject.getString("valueType");
        if (string.equals("bool")) {
            bundle.putBoolean(s, jsonObject.getBoolean("value"));
        }
        else {
            if (string.equals("bool[]")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("value");
                final boolean[] array = new boolean[jsonArray.length()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = jsonArray.getBoolean(i);
                }
                bundle.putBooleanArray(s, array);
                return;
            }
            if (string.equals("byte")) {
                bundle.putByte(s, (byte)jsonObject.getInt("value"));
                return;
            }
            if (string.equals("byte[]")) {
                final JSONArray jsonArray2 = jsonObject.getJSONArray("value");
                final byte[] array2 = new byte[jsonArray2.length()];
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = (byte)jsonArray2.getInt(j);
                }
                bundle.putByteArray(s, array2);
                return;
            }
            if (string.equals("short")) {
                bundle.putShort(s, (short)jsonObject.getInt("value"));
                return;
            }
            if (string.equals("short[]")) {
                final JSONArray jsonArray3 = jsonObject.getJSONArray("value");
                final short[] array3 = new short[jsonArray3.length()];
                for (int k = 0; k < array3.length; ++k) {
                    array3[k] = (short)jsonArray3.getInt(k);
                }
                bundle.putShortArray(s, array3);
                return;
            }
            if (string.equals("int")) {
                bundle.putInt(s, jsonObject.getInt("value"));
                return;
            }
            if (string.equals("int[]")) {
                final JSONArray jsonArray4 = jsonObject.getJSONArray("value");
                final int[] array4 = new int[jsonArray4.length()];
                for (int l = 0; l < array4.length; ++l) {
                    array4[l] = jsonArray4.getInt(l);
                }
                bundle.putIntArray(s, array4);
                return;
            }
            if (string.equals("long")) {
                bundle.putLong(s, jsonObject.getLong("value"));
                return;
            }
            if (string.equals("long[]")) {
                final JSONArray jsonArray5 = jsonObject.getJSONArray("value");
                final long[] array5 = new long[jsonArray5.length()];
                for (int n = 0; n < array5.length; ++n) {
                    array5[n] = jsonArray5.getLong(n);
                }
                bundle.putLongArray(s, array5);
                return;
            }
            if (string.equals("float")) {
                bundle.putFloat(s, (float)jsonObject.getDouble("value"));
                return;
            }
            if (string.equals("float[]")) {
                final JSONArray jsonArray6 = jsonObject.getJSONArray("value");
                final float[] array6 = new float[jsonArray6.length()];
                for (int n2 = 0; n2 < array6.length; ++n2) {
                    array6[n2] = (float)jsonArray6.getDouble(n2);
                }
                bundle.putFloatArray(s, array6);
                return;
            }
            if (string.equals("double")) {
                bundle.putDouble(s, jsonObject.getDouble("value"));
                return;
            }
            if (string.equals("double[]")) {
                final JSONArray jsonArray7 = jsonObject.getJSONArray("value");
                final double[] array7 = new double[jsonArray7.length()];
                for (int n3 = 0; n3 < array7.length; ++n3) {
                    array7[n3] = jsonArray7.getDouble(n3);
                }
                bundle.putDoubleArray(s, array7);
                return;
            }
            if (string.equals("char")) {
                final String string2 = jsonObject.getString("value");
                if (string2 != null && string2.length() == 1) {
                    bundle.putChar(s, string2.charAt(0));
                }
            }
            else {
                if (string.equals("char[]")) {
                    final JSONArray jsonArray8 = jsonObject.getJSONArray("value");
                    final char[] array8 = new char[jsonArray8.length()];
                    for (int n4 = 0; n4 < array8.length; ++n4) {
                        final String string3 = jsonArray8.getString(n4);
                        if (string3 != null && string3.length() == 1) {
                            array8[n4] = string3.charAt(0);
                        }
                    }
                    bundle.putCharArray(s, array8);
                    return;
                }
                if (string.equals("string")) {
                    bundle.putString(s, jsonObject.getString("value"));
                    return;
                }
                if (string.equals("stringList")) {
                    final JSONArray jsonArray9 = jsonObject.getJSONArray("value");
                    final int length = jsonArray9.length();
                    final ArrayList list = new ArrayList<String>(length);
                    for (int n5 = 0; n5 < length; ++n5) {
                        final Object value = jsonArray9.get(n5);
                        String s2;
                        if (value == JSONObject.NULL) {
                            s2 = null;
                        }
                        else {
                            s2 = (String)value;
                        }
                        list.add(n5, s2);
                    }
                    bundle.putStringArrayList(s, list);
                    return;
                }
                if (string.equals("enum")) {
                    try {
                        bundle.putSerializable(s, Enum.valueOf(Class.forName(jsonObject.getString("enumType")), jsonObject.getString("value")));
                    }
                    catch (ClassNotFoundException ex) {}
                    catch (IllegalArgumentException ex2) {}
                }
            }
        }
    }
    
    public static String getApplicationId(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.ApplicationId");
    }
    
    static Date getDate(final Bundle bundle, final String s) {
        if (bundle != null) {
            final long long1 = bundle.getLong(s, Long.MIN_VALUE);
            if (long1 != Long.MIN_VALUE) {
                return new Date(long1);
            }
        }
        return null;
    }
    
    public static Date getExpirationDate(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate");
    }
    
    public static long getExpirationMilliseconds(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate");
    }
    
    public static Date getLastRefreshDate(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate");
    }
    
    public static long getLastRefreshMilliseconds(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong("com.facebook.TokenCachingStrategy.LastRefreshDate");
    }
    
    public static Set<String> getPermissions(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        final ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.TokenCachingStrategy.Permissions");
        if (stringArrayList == null) {
            return null;
        }
        return new HashSet<String>(stringArrayList);
    }
    
    public static AccessTokenSource getSource(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        if (bundle.containsKey("com.facebook.TokenCachingStrategy.AccessTokenSource")) {
            return (AccessTokenSource)bundle.getSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource");
        }
        if (bundle.getBoolean("com.facebook.TokenCachingStrategy.IsSSO")) {
            return AccessTokenSource.FACEBOOK_APPLICATION_WEB;
        }
        return AccessTokenSource.WEB_VIEW;
    }
    
    public static String getToken(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.Token");
    }
    
    public static boolean hasTokenInformation(final Bundle bundle) {
        if (bundle != null) {
            final String string = bundle.getString("com.facebook.TokenCachingStrategy.Token");
            if (string != null && string.length() != 0 && bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate", 0L) != 0L) {
                return true;
            }
        }
        return false;
    }
    
    public static void putApplicationId(final Bundle bundle, final String s) {
        Validate.notNull(bundle, "bundle");
        bundle.putString("com.facebook.TokenCachingStrategy.ApplicationId", s);
    }
    
    static void putDate(final Bundle bundle, final String s, final Date date) {
        bundle.putLong(s, date.getTime());
    }
    
    public static void putDeclinedPermissions(final Bundle bundle, final Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList("com.facebook.TokenCachingStrategy.DeclinedPermissions", new ArrayList((Collection<? extends E>)collection));
    }
    
    public static void putExpirationDate(final Bundle bundle, final Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate", date);
    }
    
    public static void putExpirationMilliseconds(final Bundle bundle, final long n) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong("com.facebook.TokenCachingStrategy.ExpirationDate", n);
    }
    
    public static void putLastRefreshDate(final Bundle bundle, final Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate", date);
    }
    
    public static void putLastRefreshMilliseconds(final Bundle bundle, final long n) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong("com.facebook.TokenCachingStrategy.LastRefreshDate", n);
    }
    
    public static void putPermissions(final Bundle bundle, final Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList("com.facebook.TokenCachingStrategy.Permissions", new ArrayList((Collection<? extends E>)collection));
    }
    
    public static void putSource(final Bundle bundle, final AccessTokenSource accessTokenSource) {
        Validate.notNull(bundle, "bundle");
        bundle.putSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource", (Serializable)accessTokenSource);
    }
    
    public static void putToken(final Bundle bundle, final String s) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(s, "value");
        bundle.putString("com.facebook.TokenCachingStrategy.Token", s);
    }
    
    private void serializeKey(final String s, final Bundle bundle, final SharedPreferences$Editor sharedPreferences$Editor) throws JSONException {
        int i = 0;
        final Object value = bundle.get(s);
        if (value != null) {
            JSONArray jsonArray = null;
            final JSONObject jsonObject = new JSONObject();
            String s2;
            if (value instanceof Byte) {
                s2 = "byte";
                jsonObject.put("value", (int)value);
            }
            else if (value instanceof Short) {
                s2 = "short";
                jsonObject.put("value", (int)value);
                jsonArray = null;
            }
            else if (value instanceof Integer) {
                s2 = "int";
                jsonObject.put("value", (int)value);
                jsonArray = null;
            }
            else if (value instanceof Long) {
                s2 = "long";
                jsonObject.put("value", (long)value);
                jsonArray = null;
            }
            else if (value instanceof Float) {
                s2 = "float";
                jsonObject.put("value", (double)value);
                jsonArray = null;
            }
            else if (value instanceof Double) {
                s2 = "double";
                jsonObject.put("value", (double)value);
                jsonArray = null;
            }
            else if (value instanceof Boolean) {
                s2 = "bool";
                jsonObject.put("value", (boolean)value);
                jsonArray = null;
            }
            else if (value instanceof Character) {
                s2 = "char";
                jsonObject.put("value", (Object)value.toString());
                jsonArray = null;
            }
            else if (value instanceof String) {
                s2 = "string";
                jsonObject.put("value", (Object)value);
                jsonArray = null;
            }
            else if (value instanceof Enum) {
                s2 = "enum";
                jsonObject.put("value", (Object)value.toString());
                jsonObject.put("enumType", (Object)((List<String>)value).getClass().getName());
                jsonArray = null;
            }
            else {
                jsonArray = new JSONArray();
                if (value instanceof byte[]) {
                    s2 = "byte[]";
                    for (byte[] array = (byte[])value; i < array.length; ++i) {
                        jsonArray.put((int)array[i]);
                    }
                }
                else if (value instanceof short[]) {
                    s2 = "short[]";
                    for (short[] array2 = (short[])value; i < array2.length; ++i) {
                        jsonArray.put((int)array2[i]);
                    }
                }
                else if (value instanceof int[]) {
                    s2 = "int[]";
                    for (int[] array3 = (int[])value; i < array3.length; ++i) {
                        jsonArray.put(array3[i]);
                    }
                }
                else if (value instanceof long[]) {
                    s2 = "long[]";
                    for (long[] array4 = (long[])value; i < array4.length; ++i) {
                        jsonArray.put(array4[i]);
                    }
                }
                else if (value instanceof float[]) {
                    s2 = "float[]";
                    for (float[] array5 = (float[])value; i < array5.length; ++i) {
                        jsonArray.put((double)array5[i]);
                    }
                }
                else if (value instanceof double[]) {
                    s2 = "double[]";
                    for (double[] array6 = (double[])value; i < array6.length; ++i) {
                        jsonArray.put(array6[i]);
                    }
                }
                else if (value instanceof boolean[]) {
                    s2 = "bool[]";
                    for (boolean[] array7 = (boolean[])value; i < array7.length; ++i) {
                        jsonArray.put(array7[i]);
                    }
                }
                else if (value instanceof char[]) {
                    s2 = "char[]";
                    for (char[] array8 = (char[])value; i < array8.length; ++i) {
                        jsonArray.put((Object)String.valueOf(array8[i]));
                    }
                }
                else if (value instanceof List) {
                    s2 = "stringList";
                    for (Object null : (List<String>)value) {
                        if (null == null) {
                            null = JSONObject.NULL;
                        }
                        jsonArray.put(null);
                    }
                }
                else {
                    jsonArray = null;
                    s2 = null;
                }
            }
            if (s2 != null) {
                jsonObject.put("valueType", (Object)s2);
                if (jsonArray != null) {
                    jsonObject.putOpt("value", (Object)jsonArray);
                }
                sharedPreferences$Editor.putString(s, jsonObject.toString());
            }
        }
    }
    
    public void clear() {
        this.cache.edit().clear().apply();
    }
    
    public Bundle load() {
        Bundle bundle = new Bundle();
        for (final String s : this.cache.getAll().keySet()) {
            try {
                this.deserializeKey(s, bundle);
                continue;
            }
            catch (JSONException ex) {
                Logger.log(LoggingBehavior.CACHE, 5, LegacyTokenHelper.TAG, "Error reading cached value for key: '" + s + "' -- " + ex);
                bundle = null;
            }
            break;
        }
        return bundle;
    }
    
    public void save(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        final SharedPreferences$Editor edit = this.cache.edit();
        for (final String s : bundle.keySet()) {
            try {
                this.serializeKey(s, bundle, edit);
                continue;
            }
            catch (JSONException ex) {
                Logger.log(LoggingBehavior.CACHE, 5, LegacyTokenHelper.TAG, "Error processing value for key: '" + s + "' -- " + ex);
                return;
            }
            break;
        }
        edit.apply();
    }
}
