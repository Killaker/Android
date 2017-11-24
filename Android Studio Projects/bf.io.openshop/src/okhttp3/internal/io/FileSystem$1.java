package okhttp3.internal.io;

import java.io.*;
import okio.*;

static final class FileSystem$1 implements FileSystem {
    @Override
    public Sink appendingSink(final File file) throws FileNotFoundException {
        try {
            return Okio.appendingSink(file);
        }
        catch (FileNotFoundException ex) {
            file.getParentFile().mkdirs();
            return Okio.appendingSink(file);
        }
    }
    
    @Override
    public void delete(final File file) throws IOException {
        if (!file.delete() && file.exists()) {
            throw new IOException("failed to delete " + file);
        }
    }
    
    @Override
    public void deleteContents(final File file) throws IOException {
        final File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (final File file2 : listFiles) {
            if (file2.isDirectory()) {
                this.deleteContents(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete " + file2);
            }
        }
    }
    
    @Override
    public boolean exists(final File file) throws IOException {
        return file.exists();
    }
    
    @Override
    public void rename(final File file, final File file2) throws IOException {
        this.delete(file2);
        if (!file.renameTo(file2)) {
            throw new IOException("failed to rename " + file + " to " + file2);
        }
    }
    
    @Override
    public Sink sink(final File file) throws FileNotFoundException {
        try {
            return Okio.sink(file);
        }
        catch (FileNotFoundException ex) {
            file.getParentFile().mkdirs();
            return Okio.sink(file);
        }
    }
    
    @Override
    public long size(final File file) {
        return file.length();
    }
    
    @Override
    public Source source(final File file) throws FileNotFoundException {
        return Okio.source(file);
    }
}