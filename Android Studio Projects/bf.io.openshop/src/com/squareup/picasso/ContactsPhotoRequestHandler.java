package com.squareup.picasso;

import android.provider.*;
import android.os.*;
import android.content.*;
import android.net.*;
import java.io.*;
import android.annotation.*;

class ContactsPhotoRequestHandler extends RequestHandler
{
    private static final int ID_CONTACT = 3;
    private static final int ID_DISPLAY_PHOTO = 4;
    private static final int ID_LOOKUP = 1;
    private static final int ID_THUMBNAIL = 2;
    private static final UriMatcher matcher;
    private final Context context;
    
    static {
        (matcher = new UriMatcher(-1)).addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        ContactsPhotoRequestHandler.matcher.addURI("com.android.contacts", "contacts/lookup/*", 1);
        ContactsPhotoRequestHandler.matcher.addURI("com.android.contacts", "contacts/#/photo", 2);
        ContactsPhotoRequestHandler.matcher.addURI("com.android.contacts", "contacts/#", 3);
        ContactsPhotoRequestHandler.matcher.addURI("com.android.contacts", "display_photo/#", 4);
    }
    
    ContactsPhotoRequestHandler(final Context context) {
        this.context = context;
    }
    
    private InputStream getInputStream(final Request request) throws IOException {
        final ContentResolver contentResolver = this.context.getContentResolver();
        Uri uri = request.uri;
        switch (ContactsPhotoRequestHandler.matcher.match(uri)) {
            default: {
                throw new IllegalStateException("Invalid uri: " + uri);
            }
            case 1: {
                uri = ContactsContract$Contacts.lookupContact(contentResolver, uri);
                if (uri == null) {
                    return null;
                }
            }
            case 3: {
                if (Build$VERSION.SDK_INT < 14) {
                    return ContactsContract$Contacts.openContactPhotoInputStream(contentResolver, uri);
                }
                return ContactPhotoStreamIcs.get(contentResolver, uri);
            }
            case 2:
            case 4: {
                return contentResolver.openInputStream(uri);
            }
        }
    }
    
    @Override
    public boolean canHandleRequest(final Request request) {
        final Uri uri = request.uri;
        return "content".equals(uri.getScheme()) && ContactsContract$Contacts.CONTENT_URI.getHost().equals(uri.getHost()) && ContactsPhotoRequestHandler.matcher.match(request.uri) != -1;
    }
    
    @Override
    public Result load(final Request request, final int n) throws IOException {
        final InputStream inputStream = this.getInputStream(request);
        if (inputStream != null) {
            return new Result(inputStream, Picasso.LoadedFrom.DISK);
        }
        return null;
    }
    
    @TargetApi(14)
    private static class ContactPhotoStreamIcs
    {
        static InputStream get(final ContentResolver contentResolver, final Uri uri) {
            return ContactsContract$Contacts.openContactPhotoInputStream(contentResolver, uri, true);
        }
    }
}
