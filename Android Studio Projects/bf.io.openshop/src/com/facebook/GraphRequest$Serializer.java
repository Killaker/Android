package com.facebook;

import java.net.*;
import android.graphics.*;
import android.net.*;
import com.facebook.internal.*;
import java.io.*;
import android.os.*;
import java.util.*;
import org.json.*;

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
        if (GraphRequest.access$000(o)) {
            this.writeString(s, GraphRequest.access$100(o));
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
