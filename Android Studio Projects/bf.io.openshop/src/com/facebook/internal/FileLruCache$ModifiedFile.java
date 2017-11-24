package com.facebook.internal;

import java.io.*;

private static final class ModifiedFile implements Comparable<ModifiedFile>
{
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    private final File file;
    private final long modified;
    
    ModifiedFile(final File file) {
        this.file = file;
        this.modified = file.lastModified();
    }
    
    @Override
    public int compareTo(final ModifiedFile modifiedFile) {
        if (this.getModified() < modifiedFile.getModified()) {
            return -1;
        }
        if (this.getModified() > modifiedFile.getModified()) {
            return 1;
        }
        return this.getFile().compareTo(modifiedFile.getFile());
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof ModifiedFile && this.compareTo((ModifiedFile)o) == 0;
    }
    
    File getFile() {
        return this.file;
    }
    
    long getModified() {
        return this.modified;
    }
    
    @Override
    public int hashCode() {
        return 37 * (1073 + this.file.hashCode()) + (int)(this.modified % 2147483647L);
    }
}
