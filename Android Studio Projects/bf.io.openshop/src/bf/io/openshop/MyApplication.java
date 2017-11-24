package bf.io.openshop;

import android.app.*;
import java.util.*;
import android.util.*;
import android.content.res.*;
import com.android.volley.*;
import android.text.*;
import bf.io.openshop.api.*;
import android.content.*;
import com.android.volley.toolbox.*;
import android.net.*;

public class MyApplication extends Application
{
    public static String ANDROID_ID;
    public static String APP_VERSION;
    public static final String PACKAGE_NAME;
    private static final String TAG;
    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;
    
    static {
        PACKAGE_NAME = MyApplication.class.getPackage().getName();
        TAG = MyApplication.class.getSimpleName();
        MyApplication.APP_VERSION = "0.0.0";
        MyApplication.ANDROID_ID = "0000000000000000";
    }
    
    public static DefaultRetryPolicy getDefaultRetryPolice() {
        return new DefaultRetryPolicy(14000, 2, 1.0f);
    }
    
    public static MyApplication getInstance() {
        synchronized (MyApplication.class) {
            return MyApplication.mInstance;
        }
    }
    
    public static void setAppLocale(final String s) {
        final Resources resources = MyApplication.mInstance.getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        final Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(s);
        resources.updateConfiguration(configuration, displayMetrics);
    }
    
    public <T> void addToRequestQueue(final Request<T> request, String tag) {
        if (TextUtils.isEmpty((CharSequence)tag)) {
            tag = MyApplication.TAG;
        }
        request.setTag(tag);
        this.getRequestQueue().add(request);
    }
    
    public void cancelPendingRequests(final Object o) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.cancelAll(o);
        }
    }
    
    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue((Context)this, new OkHttpStack());
        }
        return this.mRequestQueue;
    }
    
    public boolean isDataConnected() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
    
    public boolean isWiFiConnection() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }
    
    public void onCreate() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   android/app/Application.onCreate:()V
        //     4: aload_0        
        //     5: putstatic       bf/io/openshop/MyApplication.mInstance:Lbf/io/openshop/MyApplication;
        //     8: aload_0        
        //     9: invokestatic    com/facebook/FacebookSdk.sdkInitialize:(Landroid/content/Context;)V
        //    12: aload_0        
        //    13: invokevirtual   bf/io/openshop/MyApplication.getContentResolver:()Landroid/content/ContentResolver;
        //    16: ldc             "android_id"
        //    18: invokestatic    android/provider/Settings$Secure.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //    21: putstatic       bf/io/openshop/MyApplication.ANDROID_ID:Ljava/lang/String;
        //    24: getstatic       bf/io/openshop/MyApplication.ANDROID_ID:Ljava/lang/String;
        //    27: ifnull          39
        //    30: getstatic       bf/io/openshop/MyApplication.ANDROID_ID:Ljava/lang/String;
        //    33: invokevirtual   java/lang/String.isEmpty:()Z
        //    36: ifeq            44
        //    39: ldc             "0000000000000000"
        //    41: putstatic       bf/io/openshop/MyApplication.ANDROID_ID:Ljava/lang/String;
        //    44: aload_0        
        //    45: invokevirtual   bf/io/openshop/MyApplication.getPackageManager:()Landroid/content/pm/PackageManager;
        //    48: aload_0        
        //    49: invokevirtual   bf/io/openshop/MyApplication.getPackageName:()Ljava/lang/String;
        //    52: iconst_0       
        //    53: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    56: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    59: putstatic       bf/io/openshop/MyApplication.APP_VERSION:Ljava/lang/String;
        //    62: return         
        //    63: astore_1       
        //    64: ldc             "0000000000000000"
        //    66: putstatic       bf/io/openshop/MyApplication.ANDROID_ID:Ljava/lang/String;
        //    69: goto            44
        //    72: astore_2       
        //    73: aload_2        
        //    74: ldc             "App versionName not found. WTF?. This should never happen."
        //    76: iconst_0       
        //    77: anewarray       Ljava/lang/Object;
        //    80: invokestatic    timber/log/Timber.e:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //    83: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  12     39     63     72     Ljava/lang/Exception;
        //  39     44     63     72     Ljava/lang/Exception;
        //  44     62     72     84     Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
