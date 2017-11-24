package com.facebook.internal;

import android.util.*;
import java.util.*;
import android.net.*;
import java.net.*;
import android.graphics.*;
import java.io.*;
import com.facebook.*;

public final class NativeAppCallAttachmentStore
{
    static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
    private static final String TAG;
    private static File attachmentsDirectory;
    
    static {
        TAG = NativeAppCallAttachmentStore.class.getName();
    }
    
    public static void addAttachments(final Collection<Attachment> collection) {
        if (collection != null && collection.size() != 0) {
            if (NativeAppCallAttachmentStore.attachmentsDirectory == null) {
                cleanupAllAttachments();
            }
            while (true) {
                ensureAttachmentsDirectoryExists();
                final ArrayList<File> list = new ArrayList<File>();
                while (true) {
                    Attachment attachment = null;
                    File attachmentFile = null;
                    Label_0190: {
                        try {
                            final Iterator<Attachment> iterator = collection.iterator();
                            while (iterator.hasNext()) {
                                attachment = iterator.next();
                                if (attachment.shouldCreateFile) {
                                    attachmentFile = getAttachmentFile(attachment.callId, attachment.attachmentName, true);
                                    list.add(attachmentFile);
                                    if (attachment.bitmap == null) {
                                        break Label_0190;
                                    }
                                    processAttachmentBitmap(attachment.bitmap, attachmentFile);
                                }
                            }
                            return;
                        }
                        catch (IOException ex) {
                            Log.e(NativeAppCallAttachmentStore.TAG, "Got unexpected exception:" + ex);
                            for (final File file : list) {
                                try {
                                    file.delete();
                                }
                                catch (Exception ex2) {}
                            }
                            break;
                        }
                    }
                    if (attachment.originalUri != null) {
                        processAttachmentFile(attachment.originalUri, attachment.isContentUri, attachmentFile);
                        continue;
                    }
                    continue;
                }
            }
            final IOException ex;
            throw new FacebookException(ex);
        }
    }
    
    public static void cleanupAllAttachments() {
        Utility.deleteDirectory(getAttachmentsDirectory());
    }
    
    public static void cleanupAttachmentsForCall(final UUID uuid) {
        final File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, false);
        if (attachmentsDirectoryForCall != null) {
            Utility.deleteDirectory(attachmentsDirectoryForCall);
        }
    }
    
    public static Attachment createAttachment(final UUID uuid, final Bitmap bitmap) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(bitmap, "attachmentBitmap");
        return new Attachment(uuid, bitmap, (Uri)null);
    }
    
    public static Attachment createAttachment(final UUID uuid, final Uri uri) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(uri, "attachmentUri");
        return new Attachment(uuid, (Bitmap)null, uri);
    }
    
    static File ensureAttachmentsDirectoryExists() {
        final File attachmentsDirectory = getAttachmentsDirectory();
        attachmentsDirectory.mkdirs();
        return attachmentsDirectory;
    }
    
    static File getAttachmentFile(final UUID uuid, final String s, final boolean b) throws IOException {
        final File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, b);
        if (attachmentsDirectoryForCall == null) {
            return null;
        }
        try {
            return new File(attachmentsDirectoryForCall, URLEncoder.encode(s, "UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    static File getAttachmentsDirectory() {
        synchronized (NativeAppCallAttachmentStore.class) {
            if (NativeAppCallAttachmentStore.attachmentsDirectory == null) {
                NativeAppCallAttachmentStore.attachmentsDirectory = new File(FacebookSdk.getApplicationContext().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            return NativeAppCallAttachmentStore.attachmentsDirectory;
        }
    }
    
    static File getAttachmentsDirectoryForCall(final UUID uuid, final boolean b) {
        File file;
        if (NativeAppCallAttachmentStore.attachmentsDirectory == null) {
            file = null;
        }
        else {
            file = new File(NativeAppCallAttachmentStore.attachmentsDirectory, uuid.toString());
            if (b && !file.exists()) {
                file.mkdirs();
                return file;
            }
        }
        return file;
    }
    
    public static File openAttachment(final UUID uuid, final String s) throws FileNotFoundException {
        if (Utility.isNullOrEmpty(s) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return getAttachmentFile(uuid, s, false);
        }
        catch (IOException ex) {
            throw new FileNotFoundException();
        }
    }
    
    private static void processAttachmentBitmap(final Bitmap bitmap, final File file) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)fileOutputStream);
        }
        finally {
            Utility.closeQuietly(fileOutputStream);
        }
    }
    
    private static void processAttachmentFile(final Uri uri, final boolean b, final File file) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        Label_0038: {
            if (b) {
                break Label_0038;
            }
            try {
                InputStream openInputStream = new FileInputStream(uri.getPath());
                while (true) {
                    Utility.copyAndCloseInputStream(openInputStream, fileOutputStream);
                    return;
                    openInputStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(uri);
                    continue;
                }
            }
            finally {
                Utility.closeQuietly(fileOutputStream);
            }
        }
    }
    
    public static final class Attachment
    {
        private final String attachmentName;
        private final String attachmentUrl;
        private Bitmap bitmap;
        private final UUID callId;
        private boolean isContentUri;
        private Uri originalUri;
        private boolean shouldCreateFile;
        
        private Attachment(final UUID callId, final Bitmap bitmap, final Uri originalUri) {
            boolean b = true;
            this.callId = callId;
            this.bitmap = bitmap;
            this.originalUri = originalUri;
            if (originalUri != null) {
                final String scheme = originalUri.getScheme();
                if ("content".equalsIgnoreCase(scheme)) {
                    this.isContentUri = b;
                    if (originalUri.getAuthority() == null || originalUri.getAuthority().startsWith("media")) {
                        b = false;
                    }
                    this.shouldCreateFile = b;
                }
                else if ("file".equalsIgnoreCase(originalUri.getScheme())) {
                    this.shouldCreateFile = b;
                }
                else if (!Utility.isWebUri(originalUri)) {
                    throw new FacebookException("Unsupported scheme for media Uri : " + scheme);
                }
            }
            else {
                if (bitmap == null) {
                    throw new FacebookException("Cannot share media without a bitmap or Uri set");
                }
                this.shouldCreateFile = b;
            }
            String string;
            if (!this.shouldCreateFile) {
                string = null;
            }
            else {
                string = UUID.randomUUID().toString();
            }
            this.attachmentName = string;
            String attachmentUrl;
            if (!this.shouldCreateFile) {
                attachmentUrl = this.originalUri.toString();
            }
            else {
                attachmentUrl = FacebookContentProvider.getAttachmentUrl(FacebookSdk.getApplicationId(), callId, this.attachmentName);
            }
            this.attachmentUrl = attachmentUrl;
        }
        
        public String getAttachmentUrl() {
            return this.attachmentUrl;
        }
    }
}
