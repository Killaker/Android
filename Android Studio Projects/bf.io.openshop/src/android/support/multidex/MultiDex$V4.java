package android.support.multidex;

import java.io.*;
import java.util.zip.*;
import dalvik.system.*;
import java.lang.reflect.*;
import java.util.*;

private static final class V4
{
    private static void install(final ClassLoader classLoader, final List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
        final int size = list.size();
        final Field access$300 = MultiDex.access$300(classLoader, "path");
        final StringBuilder sb = new StringBuilder((String)access$300.get(classLoader));
        final String[] array = new String[size];
        final File[] array2 = new File[size];
        final ZipFile[] array3 = new ZipFile[size];
        final DexFile[] array4 = new DexFile[size];
        final ListIterator<File> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            final File file = listIterator.next();
            final String absolutePath = file.getAbsolutePath();
            sb.append(':').append(absolutePath);
            final int previousIndex = listIterator.previousIndex();
            array[previousIndex] = absolutePath;
            array2[previousIndex] = file;
            array3[previousIndex] = new ZipFile(file);
            array4[previousIndex] = DexFile.loadDex(absolutePath, absolutePath + ".dex", 0);
        }
        access$300.set(classLoader, sb.toString());
        MultiDex.access$400(classLoader, "mPaths", array);
        MultiDex.access$400(classLoader, "mFiles", array2);
        MultiDex.access$400(classLoader, "mZips", array3);
        MultiDex.access$400(classLoader, "mDexs", array4);
    }
}
