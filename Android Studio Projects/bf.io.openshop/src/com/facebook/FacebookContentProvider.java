package com.facebook;

import java.util.*;
import android.net.*;
import android.content.*;
import android.os.*;
import java.io.*;
import com.facebook.internal.*;
import android.util.*;
import android.database.*;

public class FacebookContentProvider extends ContentProvider
{
    private static final String ATTACHMENT_URL_BASE = "content://com.facebook.app.FacebookContentProvider";
    private static final String TAG;
    
    static {
        TAG = FacebookContentProvider.class.getName();
    }
    
    public static String getAttachmentUrl(final String s, final UUID uuid, final String s2) {
        return String.format("%s%s/%s/%s", "content://com.facebook.app.FacebookContentProvider", s, uuid.toString(), s2);
    }
    
    public int delete(final Uri uri, final String s, final String[] array) {
        return 0;
    }
    
    public String getType(final Uri uri) {
        return null;
    }
    
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        return null;
    }
    
    public boolean onCreate() {
        return true;
    }
    
    public ParcelFileDescriptor openFile(final Uri uri, final String s) throws FileNotFoundException {
        final Pair<UUID, String> callIdAndAttachmentName = this.parseCallIdAndAttachmentName(uri);
        if (callIdAndAttachmentName == null) {
            throw new FileNotFoundException();
        }
        try {
            return ParcelFileDescriptor.open(NativeAppCallAttachmentStore.openAttachment((UUID)callIdAndAttachmentName.first, (String)callIdAndAttachmentName.second), 268435456);
        }
        catch (FileNotFoundException ex) {
            Log.e(FacebookContentProvider.TAG, "Got unexpected exception:" + ex);
            throw ex;
        }
    }
    
    Pair<UUID, String> parseCallIdAndAttachmentName(final Uri uri) {
        try {
            final String[] split = uri.getPath().substring(1).split("/");
            return (Pair<UUID, String>)new Pair((Object)UUID.fromString(split[0]), (Object)split[1]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Cursor query(final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        return null;
    }
    
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] array) {
        return 0;
    }
}
