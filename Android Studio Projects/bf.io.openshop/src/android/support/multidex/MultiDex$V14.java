package android.support.multidex;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

private static final class V14
{
    private static void install(final ClassLoader classLoader, final List<File> list, final File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
        final Object value = MultiDex.access$300(classLoader, "pathList").get(classLoader);
        MultiDex.access$400(value, "dexElements", makeDexElements(value, new ArrayList<File>(list), file));
    }
    
    private static Object[] makeDexElements(final Object o, final ArrayList<File> list, final File file) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (Object[])MultiDex.access$500(o, "makeDexElements", new Class[] { ArrayList.class, File.class }).invoke(o, list, file);
    }
}
