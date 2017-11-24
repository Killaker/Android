package com.facebook.internal;

import java.io.*;

static final class FileLruCache$BufferFile$2 implements FilenameFilter {
    @Override
    public boolean accept(final File file, final String s) {
        return s.startsWith("buffer");
    }
}