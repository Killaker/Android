package android.support.multidex;

import java.io.*;

static final class MultiDexExtractor$1 implements FileFilter {
    final /* synthetic */ String val$extractedFilePrefix;
    
    @Override
    public boolean accept(final File file) {
        return !file.getName().startsWith(this.val$extractedFilePrefix);
    }
}