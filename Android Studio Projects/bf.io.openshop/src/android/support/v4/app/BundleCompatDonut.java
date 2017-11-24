package android.support.v4.app;

import android.os.*;
import android.util.*;
import java.lang.reflect.*;

class BundleCompatDonut
{
    private static final String TAG = "BundleCompatDonut";
    private static Method sGetIBinderMethod;
    private static boolean sGetIBinderMethodFetched;
    private static Method sPutIBinderMethod;
    private static boolean sPutIBinderMethodFetched;
    
    public static IBinder getBinder(final Bundle bundle, final String s) {
        Label_0036: {
            if (BundleCompatDonut.sGetIBinderMethodFetched) {
                break Label_0036;
            }
            while (true) {
                try {
                    (BundleCompatDonut.sGetIBinderMethod = Bundle.class.getMethod("getIBinder", String.class)).setAccessible(true);
                    BundleCompatDonut.sGetIBinderMethodFetched = true;
                    if (BundleCompatDonut.sGetIBinderMethod != null) {
                        final Method method = BundleCompatDonut.sGetIBinderMethod;
                        final Bundle bundle2 = bundle;
                        final int n = 1;
                        final Object[] array = new Object[n];
                        final int n2 = 0;
                        final String s2 = s;
                        array[n2] = s2;
                        final Object o = method.invoke(bundle2, array);
                        final IBinder binder = (IBinder)o;
                        return binder;
                    }
                    goto Label_0094;
                }
                catch (NoSuchMethodException ex) {
                    Log.i("BundleCompatDonut", "Failed to retrieve getIBinder method", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        try {
            final Method method = BundleCompatDonut.sGetIBinderMethod;
            final Bundle bundle2 = bundle;
            final int n = 1;
            final Object[] array = new Object[n];
            final int n2 = 0;
            final String s2 = s;
            array[n2] = s2;
            final Object o = method.invoke(bundle2, array);
            final IBinder binder2;
            final IBinder binder = binder2 = (IBinder)o;
            return binder2;
        }
        catch (IllegalAccessException ex2) {}
        catch (IllegalArgumentException ex3) {
            goto Label_0081;
        }
        catch (InvocationTargetException ex3) {
            goto Label_0081;
        }
    }
    
    public static void putBinder(final Bundle p0, final String p1, final IBinder p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/app/BundleCompatDonut.sPutIBinderMethodFetched:Z
        //     3: ifne            41
        //     6: ldc             Landroid/os/Bundle;.class
        //     8: ldc             "putIBinder"
        //    10: iconst_2       
        //    11: anewarray       Ljava/lang/Class;
        //    14: dup            
        //    15: iconst_0       
        //    16: ldc             Ljava/lang/String;.class
        //    18: aastore        
        //    19: dup            
        //    20: iconst_1       
        //    21: ldc             Landroid/os/IBinder;.class
        //    23: aastore        
        //    24: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    27: putstatic       android/support/v4/app/BundleCompatDonut.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    30: getstatic       android/support/v4/app/BundleCompatDonut.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    33: iconst_1       
        //    34: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    37: iconst_1       
        //    38: putstatic       android/support/v4/app/BundleCompatDonut.sPutIBinderMethodFetched:Z
        //    41: getstatic       android/support/v4/app/BundleCompatDonut.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    44: ifnull          67
        //    47: getstatic       android/support/v4/app/BundleCompatDonut.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    50: aload_0        
        //    51: iconst_2       
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: iconst_0       
        //    57: aload_1        
        //    58: aastore        
        //    59: dup            
        //    60: iconst_1       
        //    61: aload_2        
        //    62: aastore        
        //    63: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    66: pop            
        //    67: return         
        //    68: astore          6
        //    70: ldc             "BundleCompatDonut"
        //    72: ldc             "Failed to retrieve putIBinder method"
        //    74: aload           6
        //    76: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: goto            37
        //    83: astore_3       
        //    84: ldc             "BundleCompatDonut"
        //    86: ldc             "Failed to invoke putIBinder via reflection"
        //    88: aload_3        
        //    89: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    92: pop            
        //    93: aconst_null    
        //    94: putstatic       android/support/v4/app/BundleCompatDonut.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    97: return         
        //    98: astore_3       
        //    99: goto            84
        //   102: astore_3       
        //   103: goto            84
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  6      37     68     83     Ljava/lang/NoSuchMethodException;
        //  47     67     102    106    Ljava/lang/reflect/InvocationTargetException;
        //  47     67     83     84     Ljava/lang/IllegalAccessException;
        //  47     67     98     102    Ljava/lang/IllegalArgumentException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
