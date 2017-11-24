package com.google.android.gms.maps;

import android.content.*;
import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.*;
import android.os.*;

public final class MapsInitializer
{
    private static boolean zznY;
    
    static {
        MapsInitializer.zznY = false;
    }
    
    public static int initialize(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             Lcom/google/android/gms/maps/MapsInitializer;.class
        //     2: monitorenter   
        //     3: aload_0        
        //     4: ldc             "Context is null"
        //     6: invokestatic    com/google/android/gms/common/internal/zzx.zzb:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //     9: pop            
        //    10: getstatic       com/google/android/gms/maps/MapsInitializer.zznY:Z
        //    13: istore_3       
        //    14: iconst_0       
        //    15: istore          4
        //    17: iload_3        
        //    18: ifeq            27
        //    21: ldc             Lcom/google/android/gms/maps/MapsInitializer;.class
        //    23: monitorexit    
        //    24: iload           4
        //    26: ireturn        
        //    27: aload_0        
        //    28: invokestatic    com/google/android/gms/maps/internal/zzad.zzaO:(Landroid/content/Context;)Lcom/google/android/gms/maps/internal/zzc;
        //    31: astore          6
        //    33: aload           6
        //    35: invokestatic    com/google/android/gms/maps/MapsInitializer.zza:(Lcom/google/android/gms/maps/internal/zzc;)V
        //    38: iconst_1       
        //    39: putstatic       com/google/android/gms/maps/MapsInitializer.zznY:Z
        //    42: iconst_0       
        //    43: istore          4
        //    45: goto            21
        //    48: astore_1       
        //    49: ldc             Lcom/google/android/gms/maps/MapsInitializer;.class
        //    51: monitorexit    
        //    52: aload_1        
        //    53: athrow         
        //    54: astore          5
        //    56: aload           5
        //    58: getfield        com/google/android/gms/common/GooglePlayServicesNotAvailableException.errorCode:I
        //    61: istore          4
        //    63: goto            21
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                   
        //  -----  -----  -----  -----  -----------------------------------------------------------------------
        //  3      14     48     54     Any
        //  27     33     54     66     Lcom/google/android/gms/common/GooglePlayServicesNotAvailableException;
        //  27     33     48     54     Any
        //  33     42     48     54     Any
        //  56     63     48     54     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void zza(final zzc zzc) {
        try {
            CameraUpdateFactory.zza(zzc.zzAe());
            BitmapDescriptorFactory.zza(zzc.zzAf());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
