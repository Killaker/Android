package android.support.v4.widget;

import java.lang.reflect.*;
import android.view.*;
import android.util.*;

static class SlidingPanelLayoutImplJB extends SlidingPanelLayoutImplBase
{
    private Method mGetDisplayList;
    private Field mRecreateDisplayList;
    
    SlidingPanelLayoutImplJB() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   android/support/v4/widget/SlidingPaneLayout$SlidingPanelLayoutImplBase.<init>:()V
        //     4: aload_0        
        //     5: ldc             Landroid/view/View;.class
        //     7: ldc             "getDisplayList"
        //     9: aconst_null    
        //    10: checkcast       [Ljava/lang/Class;
        //    13: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    16: putfield        android/support/v4/widget/SlidingPaneLayout$SlidingPanelLayoutImplJB.mGetDisplayList:Ljava/lang/reflect/Method;
        //    19: aload_0        
        //    20: ldc             Landroid/view/View;.class
        //    22: ldc             "mRecreateDisplayList"
        //    24: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    27: putfield        android/support/v4/widget/SlidingPaneLayout$SlidingPanelLayoutImplJB.mRecreateDisplayList:Ljava/lang/reflect/Field;
        //    30: aload_0        
        //    31: getfield        android/support/v4/widget/SlidingPaneLayout$SlidingPanelLayoutImplJB.mRecreateDisplayList:Ljava/lang/reflect/Field;
        //    34: iconst_1       
        //    35: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    38: return         
        //    39: astore_1       
        //    40: ldc             "SlidingPaneLayout"
        //    42: ldc             "Couldn't fetch getDisplayList method; dimming won't work right."
        //    44: aload_1        
        //    45: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    48: pop            
        //    49: goto            19
        //    52: astore_3       
        //    53: ldc             "SlidingPaneLayout"
        //    55: ldc             "Couldn't fetch mRecreateDisplayList field; dimming will be slow."
        //    57: aload_3        
        //    58: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    61: pop            
        //    62: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  4      19     39     52     Ljava/lang/NoSuchMethodException;
        //  19     38     52     63     Ljava/lang/NoSuchFieldException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void invalidateChildRegion(final SlidingPaneLayout slidingPaneLayout, final View view) {
        if (this.mGetDisplayList != null && this.mRecreateDisplayList != null) {
            while (true) {
                try {
                    this.mRecreateDisplayList.setBoolean(view, true);
                    this.mGetDisplayList.invoke(view, (Object[])null);
                    super.invalidateChildRegion(slidingPaneLayout, view);
                    return;
                }
                catch (Exception ex) {
                    Log.e("SlidingPaneLayout", "Error refreshing display list state", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        view.invalidate();
    }
}
