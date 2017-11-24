package android.support.v4.graphics.drawable;

import java.lang.reflect.*;
import android.graphics.drawable.*;
import android.util.*;

class DrawableCompatJellybeanMr1
{
    private static final String TAG = "DrawableCompatJellybeanMr1";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;
    
    public static int getLayoutDirection(final Drawable drawable) {
        while (true) {
            if (!DrawableCompatJellybeanMr1.sGetLayoutDirectionMethodFetched) {
                while (true) {
                    try {
                        (DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", (Class<?>[])new Class[0])).setAccessible(true);
                        DrawableCompatJellybeanMr1.sGetLayoutDirectionMethodFetched = true;
                        if (DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod != null) {
                            final Method method = DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod;
                            final Drawable drawable2 = drawable;
                            final int n = 0;
                            final Object[] array = new Object[n];
                            final Object o = method.invoke(drawable2, array);
                            final Integer n2 = (Integer)o;
                            final int intValue = n2;
                            return intValue;
                        }
                        return -1;
                    }
                    catch (NoSuchMethodException ex) {
                        Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve getLayoutDirection() method", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                try {
                    final Method method = DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod;
                    final Drawable drawable2 = drawable;
                    final int n = 0;
                    final Object[] array = new Object[n];
                    final Object o = method.invoke(drawable2, array);
                    final Integer n2 = (Integer)o;
                    final int intValue2;
                    final int intValue = intValue2 = n2;
                    return intValue2;
                }
                catch (Exception ex2) {
                    Log.i("DrawableCompatJellybeanMr1", "Failed to invoke getLayoutDirection() via reflection", (Throwable)ex2);
                    DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod = null;
                }
                return -1;
            }
            continue;
        }
    }
    
    public static void setLayoutDirection(final Drawable p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethodFetched:Z
        //     3: ifne            42
        //     6: iconst_1       
        //     7: anewarray       Ljava/lang/Class;
        //    10: astore          9
        //    12: aload           9
        //    14: iconst_0       
        //    15: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    18: aastore        
        //    19: ldc             Landroid/graphics/drawable/Drawable;.class
        //    21: ldc             "setLayoutDirection"
        //    23: aload           9
        //    25: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    28: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    31: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    34: iconst_1       
        //    35: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    38: iconst_1       
        //    39: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethodFetched:Z
        //    42: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    45: ifnull          76
        //    48: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    51: astore          4
        //    53: iconst_1       
        //    54: anewarray       Ljava/lang/Object;
        //    57: astore          5
        //    59: aload           5
        //    61: iconst_0       
        //    62: iload_1        
        //    63: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    66: aastore        
        //    67: aload           4
        //    69: aload_0        
        //    70: aload           5
        //    72: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    75: pop            
        //    76: return         
        //    77: astore          7
        //    79: ldc             "DrawableCompatJellybeanMr1"
        //    81: ldc             "Failed to retrieve setLayoutDirection(int) method"
        //    83: aload           7
        //    85: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    88: pop            
        //    89: goto            38
        //    92: astore_2       
        //    93: ldc             "DrawableCompatJellybeanMr1"
        //    95: ldc             "Failed to invoke setLayoutDirection(int) via reflection"
        //    97: aload_2        
        //    98: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   101: pop            
        //   102: aconst_null    
        //   103: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //   106: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  6      38     77     92     Ljava/lang/NoSuchMethodException;
        //  48     76     92     107    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
