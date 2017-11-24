package android.support.multidex;

import java.lang.reflect.*;
import android.util.*;
import android.content.*;
import android.os.*;
import android.content.pm.*;
import java.util.*;
import java.io.*;
import java.util.zip.*;

final class MultiDexExtractor
{
    private static final int BUFFER_SIZE = 16384;
    private static final String DEX_PREFIX = "classes";
    private static final String DEX_SUFFIX = ".dex";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1L;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";
    private static Method sApplyMethod;
    
    static {
        try {
            MultiDexExtractor.sApplyMethod = SharedPreferences$Editor.class.getMethod("apply", (Class<?>[])new Class[0]);
        }
        catch (NoSuchMethodException ex) {
            MultiDexExtractor.sApplyMethod = null;
        }
    }
    
    private static void apply(final SharedPreferences$Editor sharedPreferences$Editor) {
        if (MultiDexExtractor.sApplyMethod == null) {
            goto Label_0020;
        }
        try {
            MultiDexExtractor.sApplyMethod.invoke(sharedPreferences$Editor, new Object[0]);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0020;
        }
    }
    
    private static void closeQuietly(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.w("MultiDex", "Failed to close resource", (Throwable)ex);
        }
    }
    
    private static void extract(final ZipFile zipFile, final ZipEntry zipEntry, final File file, final String s) throws IOException, FileNotFoundException {
        final InputStream inputStream = zipFile.getInputStream(zipEntry);
        final File tempFile = File.createTempFile(s, ".zip", file.getParentFile());
        Log.i("MultiDex", "Extracting " + tempFile.getPath());
        while (true) {
            try {
                final ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tempFile)));
                Label_0265: {
                    try {
                        final ZipEntry zipEntry2 = new ZipEntry("classes.dex");
                        zipEntry2.setTime(zipEntry.getTime());
                        zipOutputStream.putNextEntry(zipEntry2);
                        final byte[] array = new byte[16384];
                        for (int i = inputStream.read(array); i != -1; i = inputStream.read(array)) {
                            zipOutputStream.write(array, 0, i);
                        }
                        zipOutputStream.closeEntry();
                        try {
                            zipOutputStream.close();
                            Log.i("MultiDex", "Renaming to " + file.getPath());
                            if (!tempFile.renameTo(file)) {
                                throw new IOException("Failed to rename \"" + tempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
                            }
                            break Label_0265;
                        }
                        finally {}
                        closeQuietly(inputStream);
                        tempFile.delete();
                        throw;
                    }
                    finally {
                        zipOutputStream.close();
                    }
                }
                closeQuietly(inputStream);
                tempFile.delete();
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    private static SharedPreferences getMultiDexPreferences(final Context context) {
        int n;
        if (Build$VERSION.SDK_INT < 11) {
            n = 0;
        }
        else {
            n = 4;
        }
        return context.getSharedPreferences("multidex.version", n);
    }
    
    private static long getTimeStamp(final File file) {
        long lastModified = file.lastModified();
        if (lastModified == -1L) {
            --lastModified;
        }
        return lastModified;
    }
    
    private static long getZipCrc(final File file) throws IOException {
        long zipCrc = ZipUtil.getZipCrc(file);
        if (zipCrc == -1L) {
            --zipCrc;
        }
        return zipCrc;
    }
    
    private static boolean isModified(final Context context, final File file, final long n) {
        final SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        return multiDexPreferences.getLong("timestamp", -1L) != getTimeStamp(file) || multiDexPreferences.getLong("crc", -1L) != n;
    }
    
    static List<File> load(final Context context, final ApplicationInfo applicationInfo, final File file, final boolean b) throws IOException {
        Log.i("MultiDex", "MultiDexExtractor.load(" + applicationInfo.sourceDir + ", " + b + ")");
        final File file2 = new File(applicationInfo.sourceDir);
        final long zipCrc = getZipCrc(file2);
        while (true) {
            Label_0175: {
                if (b || isModified(context, file2, zipCrc)) {
                    break Label_0175;
                }
                try {
                    final List<File> list = loadExistingExtractions(context, file2, file);
                    Log.i("MultiDex", "load found " + list.size() + " secondary dex files");
                    return list;
                }
                catch (IOException ex) {
                    Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", (Throwable)ex);
                    final List<File> list = performExtractions(file2, file);
                    putStoredApkInfo(context, getTimeStamp(file2), zipCrc, 1 + list.size());
                    continue;
                }
            }
            Log.i("MultiDex", "Detected that extraction must be performed.");
            final List<File> list = performExtractions(file2, file);
            putStoredApkInfo(context, getTimeStamp(file2), zipCrc, 1 + list.size());
            continue;
        }
    }
    
    private static List<File> loadExistingExtractions(final Context context, final File file, final File file2) throws IOException {
        Log.i("MultiDex", "loading existing secondary dex files");
        final String string = file.getName() + ".classes";
        final int int1 = getMultiDexPreferences(context).getInt("dex.number", 1);
        final ArrayList list = new ArrayList<File>(int1);
        for (int i = 2; i <= int1; ++i) {
            final File file3 = new File(file2, string + i + ".zip");
            if (!file3.isFile()) {
                throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + "'");
            }
            list.add(file3);
            if (!verifyZipFile(file3)) {
                Log.i("MultiDex", "Invalid zip file: " + file3);
                throw new IOException("Invalid ZIP file.");
            }
        }
        return (List<File>)list;
    }
    
    private static void mkdirChecked(final File file) throws IOException {
        file.mkdir();
        if (!file.isDirectory()) {
            final File parentFile = file.getParentFile();
            if (parentFile == null) {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". Parent file is null.");
            }
            else {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite());
            }
            throw new IOException("Failed to create cache directory " + file.getPath());
        }
    }
    
    private static List<File> performExtractions(final File p0, final File p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: aload_0        
        //     8: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    11: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    14: ldc             ".classes"
        //    16: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    19: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    22: astore_2       
        //    23: aload_1        
        //    24: aload_2        
        //    25: invokestatic    android/support/multidex/MultiDexExtractor.prepareDexDir:(Ljava/io/File;Ljava/lang/String;)V
        //    28: new             Ljava/util/ArrayList;
        //    31: dup            
        //    32: invokespecial   java/util/ArrayList.<init>:()V
        //    35: astore_3       
        //    36: new             Ljava/util/zip/ZipFile;
        //    39: dup            
        //    40: aload_0        
        //    41: invokespecial   java/util/zip/ZipFile.<init>:(Ljava/io/File;)V
        //    44: astore          4
        //    46: iconst_2       
        //    47: istore          5
        //    49: aload           4
        //    51: new             Ljava/lang/StringBuilder;
        //    54: dup            
        //    55: invokespecial   java/lang/StringBuilder.<init>:()V
        //    58: ldc             "classes"
        //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    63: iload           5
        //    65: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    68: ldc             ".dex"
        //    70: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    73: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    76: invokevirtual   java/util/zip/ZipFile.getEntry:(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
        //    79: astore          9
        //    81: aload           9
        //    83: ifnull          433
        //    86: new             Ljava/io/File;
        //    89: dup            
        //    90: aload_1        
        //    91: new             Ljava/lang/StringBuilder;
        //    94: dup            
        //    95: invokespecial   java/lang/StringBuilder.<init>:()V
        //    98: aload_2        
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: iload           5
        //   104: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   107: ldc             ".zip"
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   115: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   118: astore          10
        //   120: aload_3        
        //   121: aload           10
        //   123: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   128: pop            
        //   129: ldc             "MultiDex"
        //   131: new             Ljava/lang/StringBuilder;
        //   134: dup            
        //   135: invokespecial   java/lang/StringBuilder.<init>:()V
        //   138: ldc_w           "Extraction is needed for file "
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: aload           10
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   149: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   152: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   155: pop            
        //   156: iconst_0       
        //   157: istore          13
        //   159: iconst_0       
        //   160: istore          14
        //   162: iload           13
        //   164: iconst_3       
        //   165: if_icmpge       338
        //   168: iload           14
        //   170: ifne            338
        //   173: iinc            13, 1
        //   176: aload           4
        //   178: aload           9
        //   180: aload           10
        //   182: aload_2        
        //   183: invokestatic    android/support/multidex/MultiDexExtractor.extract:(Ljava/util/zip/ZipFile;Ljava/util/zip/ZipEntry;Ljava/io/File;Ljava/lang/String;)V
        //   186: aload           10
        //   188: invokestatic    android/support/multidex/MultiDexExtractor.verifyZipFile:(Ljava/io/File;)Z
        //   191: istore          14
        //   193: new             Ljava/lang/StringBuilder;
        //   196: dup            
        //   197: invokespecial   java/lang/StringBuilder.<init>:()V
        //   200: ldc_w           "Extraction "
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: astore          16
        //   208: iload           14
        //   210: ifeq            330
        //   213: ldc_w           "success"
        //   216: astore          17
        //   218: ldc             "MultiDex"
        //   220: aload           16
        //   222: aload           17
        //   224: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   227: ldc_w           " - length "
        //   230: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: aload           10
        //   235: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   238: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: ldc_w           ": "
        //   244: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   247: aload           10
        //   249: invokevirtual   java/io/File.length:()J
        //   252: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   255: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   258: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   261: pop            
        //   262: iload           14
        //   264: ifne            162
        //   267: aload           10
        //   269: invokevirtual   java/io/File.delete:()Z
        //   272: pop            
        //   273: aload           10
        //   275: invokevirtual   java/io/File.exists:()Z
        //   278: ifeq            162
        //   281: ldc             "MultiDex"
        //   283: new             Ljava/lang/StringBuilder;
        //   286: dup            
        //   287: invokespecial   java/lang/StringBuilder.<init>:()V
        //   290: ldc_w           "Failed to delete corrupted secondary dex '"
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: aload           10
        //   298: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: ldc_w           "'"
        //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   313: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   316: pop            
        //   317: goto            162
        //   320: astore          6
        //   322: aload           4
        //   324: invokevirtual   java/util/zip/ZipFile.close:()V
        //   327: aload           6
        //   329: athrow         
        //   330: ldc_w           "failed"
        //   333: astore          17
        //   335: goto            218
        //   338: iload           14
        //   340: ifne            391
        //   343: new             Ljava/io/IOException;
        //   346: dup            
        //   347: new             Ljava/lang/StringBuilder;
        //   350: dup            
        //   351: invokespecial   java/lang/StringBuilder.<init>:()V
        //   354: ldc_w           "Could not create zip file "
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: aload           10
        //   362: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   365: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: ldc_w           " for secondary dex ("
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: iload           5
        //   376: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   379: ldc             ")"
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   387: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   390: athrow         
        //   391: iinc            5, 1
        //   394: aload           4
        //   396: new             Ljava/lang/StringBuilder;
        //   399: dup            
        //   400: invokespecial   java/lang/StringBuilder.<init>:()V
        //   403: ldc             "classes"
        //   405: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   408: iload           5
        //   410: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   413: ldc             ".dex"
        //   415: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   421: invokevirtual   java/util/zip/ZipFile.getEntry:(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
        //   424: astore          15
        //   426: aload           15
        //   428: astore          9
        //   430: goto            81
        //   433: aload           4
        //   435: invokevirtual   java/util/zip/ZipFile.close:()V
        //   438: aload_3        
        //   439: areturn        
        //   440: astore          21
        //   442: ldc             "MultiDex"
        //   444: ldc             "Failed to close resource"
        //   446: aload           21
        //   448: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   451: pop            
        //   452: aload_3        
        //   453: areturn        
        //   454: astore          7
        //   456: ldc             "MultiDex"
        //   458: ldc             "Failed to close resource"
        //   460: aload           7
        //   462: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   465: pop            
        //   466: goto            327
        //    Exceptions:
        //  throws java.io.IOException
        //    Signature:
        //  (Ljava/io/File;Ljava/io/File;)Ljava/util/List<Ljava/io/File;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  49     81     320    330    Any
        //  86     156    320    330    Any
        //  176    208    320    330    Any
        //  218    262    320    330    Any
        //  267    317    320    330    Any
        //  322    327    454    469    Ljava/io/IOException;
        //  343    391    320    330    Any
        //  394    426    320    330    Any
        //  433    438    440    454    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void prepareDexDir(final File file, final String s) throws IOException {
        mkdirChecked(file.getParentFile());
        mkdirChecked(file);
        final File[] listFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(final File file) {
                return !file.getName().startsWith(s);
            }
        });
        if (listFiles == null) {
            Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
        }
        else {
            for (final File file2 : listFiles) {
                Log.i("MultiDex", "Trying to delete old file " + file2.getPath() + " of size " + file2.length());
                if (!file2.delete()) {
                    Log.w("MultiDex", "Failed to delete old file " + file2.getPath());
                }
                else {
                    Log.i("MultiDex", "Deleted old file " + file2.getPath());
                }
            }
        }
    }
    
    private static void putStoredApkInfo(final Context context, final long n, final long n2, final int n3) {
        final SharedPreferences$Editor edit = getMultiDexPreferences(context).edit();
        edit.putLong("timestamp", n);
        edit.putLong("crc", n2);
        edit.putInt("dex.number", n3);
        apply(edit);
    }
    
    static boolean verifyZipFile(final File file) {
        try {
            final ZipFile zipFile = new ZipFile(file);
            try {
                zipFile.close();
                return true;
            }
            catch (IOException ex3) {
                Log.w("MultiDex", "Failed to close zip file: " + file.getAbsolutePath());
            }
            return false;
        }
        catch (ZipException ex) {
            Log.w("MultiDex", "File " + file.getAbsolutePath() + " is not a valid zip file.", (Throwable)ex);
            return false;
        }
        catch (IOException ex2) {
            Log.w("MultiDex", "Got an IOException trying to open zip file: " + file.getAbsolutePath(), (Throwable)ex2);
            return false;
        }
    }
}
