package android.support.v4.view;

import java.lang.reflect.*;
import android.view.*;

class ViewCompatEclairMr1
{
    public static final String TAG = "ViewCompat";
    private static Method sChildrenDrawingOrderMethod;
    
    public static boolean isOpaque(final View view) {
        return view.isOpaque();
    }
    
    public static void setChildrenDrawingOrderEnabled(final ViewGroup p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/view/ViewCompatEclairMr1.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //     3: ifnonnull       38
        //     6: iconst_1       
        //     7: anewarray       Ljava/lang/Class;
        //    10: astore          13
        //    12: aload           13
        //    14: iconst_0       
        //    15: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    18: aastore        
        //    19: ldc             Landroid/view/ViewGroup;.class
        //    21: ldc             "setChildrenDrawingOrderEnabled"
        //    23: aload           13
        //    25: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    28: putstatic       android/support/v4/view/ViewCompatEclairMr1.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //    31: getstatic       android/support/v4/view/ViewCompatEclairMr1.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //    34: iconst_1       
        //    35: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    38: getstatic       android/support/v4/view/ViewCompatEclairMr1.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //    41: astore          8
        //    43: iconst_1       
        //    44: anewarray       Ljava/lang/Object;
        //    47: astore          9
        //    49: aload           9
        //    51: iconst_0       
        //    52: iload_1        
        //    53: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    56: aastore        
        //    57: aload           8
        //    59: aload_0        
        //    60: aload           9
        //    62: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    65: pop            
        //    66: return         
        //    67: astore          11
        //    69: ldc             "ViewCompat"
        //    71: ldc             "Unable to find childrenDrawingOrderEnabled"
        //    73: aload           11
        //    75: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    78: pop            
        //    79: goto            31
        //    82: astore          6
        //    84: ldc             "ViewCompat"
        //    86: ldc             "Unable to invoke childrenDrawingOrderEnabled"
        //    88: aload           6
        //    90: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    93: pop            
        //    94: return         
        //    95: astore          4
        //    97: ldc             "ViewCompat"
        //    99: ldc             "Unable to invoke childrenDrawingOrderEnabled"
        //   101: aload           4
        //   103: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   106: pop            
        //   107: return         
        //   108: astore_2       
        //   109: ldc             "ViewCompat"
        //   111: ldc             "Unable to invoke childrenDrawingOrderEnabled"
        //   113: aload_2        
        //   114: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   117: pop            
        //   118: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  6      31     67     82     Ljava/lang/NoSuchMethodException;
        //  38     66     82     95     Ljava/lang/IllegalAccessException;
        //  38     66     95     108    Ljava/lang/IllegalArgumentException;
        //  38     66     108    119    Ljava/lang/reflect/InvocationTargetException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
