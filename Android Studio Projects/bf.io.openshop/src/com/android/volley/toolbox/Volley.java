package com.android.volley.toolbox;

import android.content.*;
import java.io.*;
import android.os.*;
import android.net.http.*;
import org.apache.http.client.*;
import com.android.volley.*;
import android.content.pm.*;

public class Volley
{
    private static final String DEFAULT_CACHE_DIR = "volley";
    
    public static RequestQueue newRequestQueue(final Context context) {
        return newRequestQueue(context, null);
    }
    
    public static RequestQueue newRequestQueue(final Context context, HttpStack httpStack) {
        final File file = new File(context.getCacheDir(), "volley");
        String string = "volley/0";
        while (true) {
            try {
                final String packageName = context.getPackageName();
                string = packageName + "/" + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
                if (httpStack == null) {
                    if (Build$VERSION.SDK_INT >= 9) {
                        httpStack = new HurlStack();
                    }
                    else {
                        httpStack = new HttpClientStack(AndroidHttpClient.newInstance(string));
                    }
                }
                final RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file), new BasicNetwork(httpStack));
                requestQueue.start();
                return requestQueue;
            }
            catch (PackageManager$NameNotFoundException ex) {
                continue;
            }
            break;
        }
    }
}
