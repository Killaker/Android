package com.facebook.internal;

import java.io.*;

private static class BufferFile
{
    private static final String FILE_NAME_PREFIX = "buffer";
    private static final FilenameFilter filterExcludeBufferFiles;
    private static final FilenameFilter filterExcludeNonBufferFiles;
    
    static {
        filterExcludeBufferFiles = new FilenameFilter() {
            @Override
            public boolean accept(final File file, final String s) {
                return !s.startsWith("buffer");
            }
        };
        filterExcludeNonBufferFiles = new FilenameFilter() {
            @Override
            public boolean accept(final File file, final String s) {
                return s.startsWith("buffer");
            }
        };
    }
    
    static void deleteAll(final File file) {
        final File[] listFiles = file.listFiles(excludeNonBufferFiles());
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                listFiles[i].delete();
            }
        }
    }
    
    static FilenameFilter excludeBufferFiles() {
        return BufferFile.filterExcludeBufferFiles;
    }
    
    static FilenameFilter excludeNonBufferFiles() {
        return BufferFile.filterExcludeNonBufferFiles;
    }
    
    static File newFile(final File file) {
        return new File(file, "buffer" + Long.valueOf(FileLruCache.access$300().incrementAndGet()).toString());
    }
}
