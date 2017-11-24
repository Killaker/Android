package com.squareup.picasso;

import android.annotation.*;
import android.content.*;
import android.net.*;
import java.io.*;
import android.provider.*;

@TargetApi(14)
private static class ContactPhotoStreamIcs
{
    static InputStream get(final ContentResolver contentResolver, final Uri uri) {
        return ContactsContract$Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }
}
