package com.android.internal.http.multipart;

import java.io.*;

public class FilePart extends PartBase
{
    public static final String DEFAULT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    protected static final String FILE_NAME = "; filename=";
    
    public FilePart(final String s, final PartSource partSource) {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }
    
    public FilePart(final String s, final PartSource partSource, final String s2, final String s3) {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }
    
    public FilePart(final String s, final File file) throws FileNotFoundException {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }
    
    public FilePart(final String s, final File file, final String s2, final String s3) throws FileNotFoundException {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }
    
    public FilePart(final String s, final String s2, final File file) throws FileNotFoundException {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }
    
    public FilePart(final String s, final String s2, final File file, final String s3, final String s4) throws FileNotFoundException {
        super(null, null, null, null);
        throw new RuntimeException("Stub!");
    }
    
    protected PartSource getSource() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    protected long lengthOfData() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    protected void sendData(final OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    protected void sendDispositionHeader(final OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }
}
