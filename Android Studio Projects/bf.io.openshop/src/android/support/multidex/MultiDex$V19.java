package android.support.multidex;

import java.io.*;
import android.util.*;
import java.util.*;
import java.lang.reflect.*;

private static final class V19
{
    private static void install(final ClassLoader classLoader, final List<File> list, final File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
        final Object value = MultiDex.access$300(classLoader, "pathList").get(classLoader);
        final ArrayList<IOException> list2 = new ArrayList<IOException>();
        MultiDex.access$400(value, "dexElements", makeDexElements(value, new ArrayList<File>(list), file, list2));
        if (list2.size() > 0) {
            final Iterator<IOException> iterator = list2.iterator();
            while (iterator.hasNext()) {
                Log.w("MultiDex", "Exception in makeDexElement", (Throwable)iterator.next());
            }
            final Field access$300 = MultiDex.access$300(classLoader, "dexElementsSuppressedExceptions");
            final IOException[] array = (IOException[])access$300.get(classLoader);
            IOException[] array2;
            if (array == null) {
                array2 = list2.toArray(new IOException[list2.size()]);
            }
            else {
                final IOException[] array3 = new IOException[list2.size() + array.length];
                list2.toArray(array3);
                System.arraycopy(array, 0, array3, list2.size(), array.length);
                array2 = array3;
            }
            access$300.set(classLoader, array2);
        }
    }
    
    private static Object[] makeDexElements(final Object o, final ArrayList<File> list, final File file, final ArrayList<IOException> list2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (Object[])MultiDex.access$500(o, "makeDexElements", new Class[] { ArrayList.class, File.class, ArrayList.class }).invoke(o, list, file, list2);
    }
}
