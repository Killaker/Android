package android.support.v4.provider;

import android.net.*;
import android.text.*;
import android.provider.*;
import android.util.*;
import android.content.*;
import android.database.*;

class DocumentsContractApi19
{
    private static final String TAG = "DocumentFile";
    
    public static boolean canRead(final Context context, final Uri uri) {
        return context.checkCallingOrSelfUriPermission(uri, 1) == 0 && !TextUtils.isEmpty((CharSequence)getRawType(context, uri));
    }
    
    public static boolean canWrite(final Context context, final Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 2) == 0) {
            final String rawType = getRawType(context, uri);
            final int queryForInt = queryForInt(context, uri, "flags", 0);
            if (!TextUtils.isEmpty((CharSequence)rawType)) {
                if ((queryForInt & 0x4) != 0x0) {
                    return true;
                }
                if ("vnd.android.document/directory".equals(rawType) && (queryForInt & 0x8) != 0x0) {
                    return true;
                }
                if (!TextUtils.isEmpty((CharSequence)rawType) && (queryForInt & 0x2) != 0x0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void closeQuietly(final AutoCloseable autoCloseable) {
        if (autoCloseable == null) {
            return;
        }
        try {
            autoCloseable.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static boolean delete(final Context context, final Uri uri) {
        return DocumentsContract.deleteDocument(context.getContentResolver(), uri);
    }
    
    public static boolean exists(final Context context, final Uri uri) {
        final ContentResolver contentResolver = context.getContentResolver();
        Cursor query = null;
        try {
            query = contentResolver.query(uri, new String[] { "document_id" }, (String)null, (String[])null, (String)null);
            return query.getCount() > 0;
        }
        catch (Exception ex) {
            Log.w("DocumentFile", "Failed query: " + ex);
            return false;
        }
        finally {
            closeQuietly((AutoCloseable)query);
        }
    }
    
    public static String getName(final Context context, final Uri uri) {
        return queryForString(context, uri, "_display_name", null);
    }
    
    private static String getRawType(final Context context, final Uri uri) {
        return queryForString(context, uri, "mime_type", null);
    }
    
    public static String getType(final Context context, final Uri uri) {
        String rawType = getRawType(context, uri);
        if ("vnd.android.document/directory".equals(rawType)) {
            rawType = null;
        }
        return rawType;
    }
    
    public static boolean isDirectory(final Context context, final Uri uri) {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }
    
    public static boolean isDocumentUri(final Context context, final Uri uri) {
        return DocumentsContract.isDocumentUri(context, uri);
    }
    
    public static boolean isFile(final Context context, final Uri uri) {
        final String rawType = getRawType(context, uri);
        return !"vnd.android.document/directory".equals(rawType) && !TextUtils.isEmpty((CharSequence)rawType);
    }
    
    public static long lastModified(final Context context, final Uri uri) {
        return queryForLong(context, uri, "last_modified", 0L);
    }
    
    public static long length(final Context context, final Uri uri) {
        return queryForLong(context, uri, "_size", 0L);
    }
    
    private static int queryForInt(final Context context, final Uri uri, final String s, final int n) {
        return (int)queryForLong(context, uri, s, n);
    }
    
    private static long queryForLong(final Context context, final Uri uri, final String s, final long n) {
        final ContentResolver contentResolver = context.getContentResolver();
        Cursor query = null;
        try {
            query = contentResolver.query(uri, new String[] { s }, (String)null, (String[])null, (String)null);
            if (query.moveToFirst() && !query.isNull(0)) {
                return query.getLong(0);
            }
            return n;
        }
        catch (Exception ex) {
            Log.w("DocumentFile", "Failed query: " + ex);
            return n;
        }
        finally {
            closeQuietly((AutoCloseable)query);
        }
    }
    
    private static String queryForString(final Context context, final Uri uri, final String s, final String s2) {
        final ContentResolver contentResolver = context.getContentResolver();
        Cursor query = null;
        try {
            query = contentResolver.query(uri, new String[] { s }, (String)null, (String[])null, (String)null);
            if (query.moveToFirst() && !query.isNull(0)) {
                return query.getString(0);
            }
            return s2;
        }
        catch (Exception ex) {
            Log.w("DocumentFile", "Failed query: " + ex);
            return s2;
        }
        finally {
            closeQuietly((AutoCloseable)query);
        }
    }
}
