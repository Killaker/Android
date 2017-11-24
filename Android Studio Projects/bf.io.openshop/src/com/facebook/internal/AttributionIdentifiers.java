package com.facebook.internal;

import com.facebook.*;
import java.lang.reflect.*;
import android.net.*;
import android.database.*;
import android.util.*;
import android.content.pm.*;
import android.support.annotation.*;
import android.os.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
import android.content.*;

public class AttributionIdentifiers
{
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000L;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG;
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;
    
    static {
        TAG = AttributionIdentifiers.class.getCanonicalName();
    }
    
    private static AttributionIdentifiers cacheAndReturnIdentifiers(final AttributionIdentifiers recentlyFetchedIdentifiers) {
        recentlyFetchedIdentifiers.fetchTime = System.currentTimeMillis();
        return AttributionIdentifiers.recentlyFetchedIdentifiers = recentlyFetchedIdentifiers;
    }
    
    private static AttributionIdentifiers getAndroidId(final Context context) {
        AttributionIdentifiers attributionIdentifiers = getAndroidIdViaReflection(context);
        if (attributionIdentifiers == null) {
            attributionIdentifiers = getAndroidIdViaService(context);
            if (attributionIdentifiers == null) {
                attributionIdentifiers = new AttributionIdentifiers();
            }
        }
        return attributionIdentifiers;
    }
    
    private static AttributionIdentifiers getAndroidIdViaReflection(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
        }
        catch (Exception ex) {
            Utility.logd("android_id", ex);
            return null;
        }
        final Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
        if (methodQuietly == null) {
            return null;
        }
        final Object invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly, context);
        if (!(invokeMethodQuietly instanceof Integer) || (int)invokeMethodQuietly != 0) {
            return null;
        }
        final Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
        if (methodQuietly2 == null) {
            return null;
        }
        final Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly2, context);
        if (invokeMethodQuietly2 == null) {
            return null;
        }
        final Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "getId", (Class<?>[])new Class[0]);
        final Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "isLimitAdTrackingEnabled", (Class<?>[])new Class[0]);
        if (methodQuietly3 != null && methodQuietly4 != null) {
            final AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
            attributionIdentifiers.androidAdvertiserId = (String)Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly3, new Object[0]);
            attributionIdentifiers.limitTracking = (boolean)Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly4, new Object[0]);
            return attributionIdentifiers;
        }
        return null;
    }
    
    private static AttributionIdentifiers getAndroidIdViaService(final Context context) {
        final GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        final Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        Label_0100: {
            if (!context.bindService(intent, (ServiceConnection)googleAdServiceConnection, 1)) {
                break Label_0100;
            }
            try {
                try {
                    final GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                    final AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                    attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                    attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                    return attributionIdentifiers;
                }
                catch (Exception ex) {
                    Utility.logd("android_id", ex);
                }
                return null;
            }
            finally {
                context.unbindService((ServiceConnection)googleAdServiceConnection);
            }
        }
    }
    
    public static AttributionIdentifiers getAttributionIdentifiers(final Context context) {
        AttributionIdentifiers attributionIdentifiers;
        if (AttributionIdentifiers.recentlyFetchedIdentifiers != null && System.currentTimeMillis() - AttributionIdentifiers.recentlyFetchedIdentifiers.fetchTime < 3600000L) {
            attributionIdentifiers = AttributionIdentifiers.recentlyFetchedIdentifiers;
        }
        else {
            final AttributionIdentifiers androidId = getAndroidId(context);
            Cursor query = null;
            try {
                final String[] array = { "aid", "androidid", "limit_tracking" };
                Uri uri;
                if (context.getPackageManager().resolveContentProvider("com.facebook.katana.provider.AttributionIdProvider", 0) != null) {
                    uri = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
                }
                else {
                    final ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.facebook.wakizashi.provider.AttributionIdProvider", 0);
                    uri = null;
                    if (resolveContentProvider != null) {
                        uri = Uri.parse("content://com.facebook.wakizashi.provider.AttributionIdProvider");
                    }
                }
                final String installerPackageName = getInstallerPackageName(context);
                query = null;
                if (installerPackageName != null) {
                    androidId.androidInstallerPackage = installerPackageName;
                }
                query = null;
                if (uri == null) {
                    attributionIdentifiers = cacheAndReturnIdentifiers(androidId);
                    if (false) {
                        null.close();
                        return attributionIdentifiers;
                    }
                }
                else {
                    query = context.getContentResolver().query(uri, array, (String)null, (String[])null, (String)null);
                    if (query == null || !query.moveToFirst()) {
                        attributionIdentifiers = cacheAndReturnIdentifiers(androidId);
                        return attributionIdentifiers;
                    }
                    final int columnIndex = query.getColumnIndex("aid");
                    final int columnIndex2 = query.getColumnIndex("androidid");
                    final int columnIndex3 = query.getColumnIndex("limit_tracking");
                    androidId.attributionId = query.getString(columnIndex);
                    if (columnIndex2 > 0 && columnIndex3 > 0 && androidId.getAndroidAdvertiserId() == null) {
                        androidId.androidAdvertiserId = query.getString(columnIndex2);
                        androidId.limitTracking = Boolean.parseBoolean(query.getString(columnIndex3));
                    }
                    if (query != null) {
                        query.close();
                    }
                    return cacheAndReturnIdentifiers(androidId);
                }
            }
            catch (Exception ex) {
                Log.d(AttributionIdentifiers.TAG, "Caught unexpected exception in getAttributionId(): " + ex.toString());
                attributionIdentifiers = null;
                return null;
            }
            finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        return attributionIdentifiers;
    }
    
    @Nullable
    private static String getInstallerPackageName(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
    
    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }
    
    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }
    
    public String getAttributionId() {
        return this.attributionId;
    }
    
    public boolean isTrackingLimited() {
        return this.limitTracking;
    }
    
    private static final class GoogleAdInfo implements IInterface
    {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;
        
        GoogleAdInfo(final IBinder binder) {
            this.binder = binder;
        }
        
        public IBinder asBinder() {
            return this.binder;
        }
        
        public String getAdvertiserId() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        public boolean isTrackingLimited() throws RemoteException {
            boolean b = true;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    b = false;
                }
                return b;
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
    
    private static final class GoogleAdServiceConnection implements ServiceConnection
    {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;
        
        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque<IBinder>();
        }
        
        public IBinder getBinder() throws InterruptedException {
            if (this.consumed.compareAndSet(true, true)) {
                throw new IllegalStateException("Binder already consumed");
            }
            return this.queue.take();
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            try {
                this.queue.put(binder);
            }
            catch (InterruptedException ex) {}
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
        }
    }
}
