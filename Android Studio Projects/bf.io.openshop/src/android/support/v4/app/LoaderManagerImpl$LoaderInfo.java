package android.support.v4.app;

import android.support.v4.content.*;
import android.os.*;
import android.util.*;
import java.io.*;
import java.lang.reflect.*;
import android.support.v4.util.*;

final class LoaderInfo implements OnLoadCompleteListener<Object>, OnLoadCanceledListener<Object>
{
    final Bundle mArgs;
    LoaderCallbacks<Object> mCallbacks;
    Object mData;
    boolean mDeliveredData;
    boolean mDestroyed;
    boolean mHaveData;
    final int mId;
    boolean mListenerRegistered;
    Loader<Object> mLoader;
    LoaderInfo mPendingLoader;
    boolean mReportNextStart;
    boolean mRetaining;
    boolean mRetainingStarted;
    boolean mStarted;
    
    public LoaderInfo(final int mId, final Bundle mArgs, final LoaderCallbacks<Object> mCallbacks) {
        this.mId = mId;
        this.mArgs = mArgs;
        this.mCallbacks = mCallbacks;
    }
    
    void callOnLoadFinished(final Loader<Object> loader, final Object o) {
        if (this.mCallbacks == null) {
            return;
        }
        final FragmentHostCallback access$000 = LoaderManagerImpl.access$000(LoaderManagerImpl.this);
        String mNoTransactionsBecause = null;
        if (access$000 != null) {
            mNoTransactionsBecause = LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause;
            LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = "onLoadFinished";
        }
        try {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  onLoadFinished in " + loader + ": " + loader.dataToString(o));
            }
            this.mCallbacks.onLoadFinished(loader, o);
            if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null) {
                LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
            }
            this.mDeliveredData = true;
        }
        finally {
            if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null) {
                LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
            }
        }
    }
    
    void cancel() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Canceling: " + this);
        }
        if (this.mStarted && this.mLoader != null && this.mListenerRegistered && !this.mLoader.cancelLoad()) {
            this.onLoadCanceled(this.mLoader);
        }
    }
    
    void destroy() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Destroying: " + this);
        }
        this.mDestroyed = true;
        final boolean mDeliveredData = this.mDeliveredData;
        this.mDeliveredData = false;
        Label_0182: {
            if (this.mCallbacks == null || this.mLoader == null || !this.mHaveData || !mDeliveredData) {
                break Label_0182;
            }
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Reseting: " + this);
            }
            final FragmentHostCallback access$000 = LoaderManagerImpl.access$000(LoaderManagerImpl.this);
            String mNoTransactionsBecause = null;
            if (access$000 != null) {
                mNoTransactionsBecause = LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause;
                LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = "onLoaderReset";
            }
            try {
                this.mCallbacks.onLoaderReset(this.mLoader);
                if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null) {
                    LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
                }
                this.mCallbacks = null;
                this.mData = null;
                this.mHaveData = false;
                if (this.mLoader != null) {
                    if (this.mListenerRegistered) {
                        this.mListenerRegistered = false;
                        this.mLoader.unregisterListener((Loader.OnLoadCompleteListener<Object>)this);
                        this.mLoader.unregisterOnLoadCanceledListener((Loader.OnLoadCanceledListener<Object>)this);
                    }
                    this.mLoader.reset();
                }
                if (this.mPendingLoader != null) {
                    this.mPendingLoader.destroy();
                }
            }
            finally {
                if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null) {
                    LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.mNoTransactionsBecause = mNoTransactionsBecause;
                }
            }
        }
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mArgs=");
        printWriter.println(this.mArgs);
        printWriter.print(s);
        printWriter.print("mCallbacks=");
        printWriter.println(this.mCallbacks);
        printWriter.print(s);
        printWriter.print("mLoader=");
        printWriter.println(this.mLoader);
        if (this.mLoader != null) {
            this.mLoader.dump(s + "  ", fileDescriptor, printWriter, array);
        }
        if (this.mHaveData || this.mDeliveredData) {
            printWriter.print(s);
            printWriter.print("mHaveData=");
            printWriter.print(this.mHaveData);
            printWriter.print("  mDeliveredData=");
            printWriter.println(this.mDeliveredData);
            printWriter.print(s);
            printWriter.print("mData=");
            printWriter.println(this.mData);
        }
        printWriter.print(s);
        printWriter.print("mStarted=");
        printWriter.print(this.mStarted);
        printWriter.print(" mReportNextStart=");
        printWriter.print(this.mReportNextStart);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        printWriter.print(s);
        printWriter.print("mRetaining=");
        printWriter.print(this.mRetaining);
        printWriter.print(" mRetainingStarted=");
        printWriter.print(this.mRetainingStarted);
        printWriter.print(" mListenerRegistered=");
        printWriter.println(this.mListenerRegistered);
        if (this.mPendingLoader != null) {
            printWriter.print(s);
            printWriter.println("Pending Loader ");
            printWriter.print(this.mPendingLoader);
            printWriter.println(":");
            this.mPendingLoader.dump(s + "  ", fileDescriptor, printWriter, array);
        }
    }
    
    void finishRetain() {
        if (this.mRetaining) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Finished Retaining: " + this);
            }
            this.mRetaining = false;
            if (this.mStarted != this.mRetainingStarted && !this.mStarted) {
                this.stop();
            }
        }
        if (this.mStarted && this.mHaveData && !this.mReportNextStart) {
            this.callOnLoadFinished(this.mLoader, this.mData);
        }
    }
    
    @Override
    public void onLoadCanceled(final Loader<Object> loader) {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "onLoadCanceled: " + this);
        }
        if (this.mDestroyed) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load canceled -- destroyed");
            }
        }
        else if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load canceled -- not active");
            }
        }
        else {
            final LoaderInfo mPendingLoader = this.mPendingLoader;
            if (mPendingLoader != null) {
                if (LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Switching to pending loader: " + mPendingLoader);
                }
                this.mPendingLoader = null;
                LoaderManagerImpl.this.mLoaders.put(this.mId, null);
                this.destroy();
                LoaderManagerImpl.this.installLoader(mPendingLoader);
            }
        }
    }
    
    @Override
    public void onLoadComplete(final Loader<Object> loader, final Object mData) {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "onLoadComplete: " + this);
        }
        if (this.mDestroyed) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
            }
        }
        else if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Ignoring load complete -- not active");
            }
        }
        else {
            final LoaderInfo mPendingLoader = this.mPendingLoader;
            if (mPendingLoader != null) {
                if (LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Switching to pending loader: " + mPendingLoader);
                }
                this.mPendingLoader = null;
                LoaderManagerImpl.this.mLoaders.put(this.mId, null);
                this.destroy();
                LoaderManagerImpl.this.installLoader(mPendingLoader);
                return;
            }
            if (this.mData != mData || !this.mHaveData) {
                this.mData = mData;
                this.mHaveData = true;
                if (this.mStarted) {
                    this.callOnLoadFinished(loader, mData);
                }
            }
            final LoaderInfo loaderInfo = LoaderManagerImpl.this.mInactiveLoaders.get(this.mId);
            if (loaderInfo != null && loaderInfo != this) {
                loaderInfo.mDeliveredData = false;
                loaderInfo.destroy();
                LoaderManagerImpl.this.mInactiveLoaders.remove(this.mId);
            }
            if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null && !LoaderManagerImpl.this.hasRunningLoaders()) {
                LoaderManagerImpl.access$000(LoaderManagerImpl.this).mFragmentManager.startPendingDeferredFragments();
            }
        }
    }
    
    void reportStart() {
        if (this.mStarted && this.mReportNextStart) {
            this.mReportNextStart = false;
            if (this.mHaveData) {
                this.callOnLoadFinished(this.mLoader, this.mData);
            }
        }
    }
    
    void retain() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Retaining: " + this);
        }
        this.mRetaining = true;
        this.mRetainingStarted = this.mStarted;
        this.mStarted = false;
        this.mCallbacks = null;
    }
    
    void start() {
        if (this.mRetaining && this.mRetainingStarted) {
            this.mStarted = true;
        }
        else if (!this.mStarted) {
            this.mStarted = true;
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            if (this.mLoader == null && this.mCallbacks != null) {
                this.mLoader = this.mCallbacks.onCreateLoader(this.mId, this.mArgs);
            }
            if (this.mLoader != null) {
                if (this.mLoader.getClass().isMemberClass() && !Modifier.isStatic(this.mLoader.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.mLoader);
                }
                if (!this.mListenerRegistered) {
                    this.mLoader.registerListener(this.mId, (Loader.OnLoadCompleteListener<Object>)this);
                    this.mLoader.registerOnLoadCanceledListener((Loader.OnLoadCanceledListener<Object>)this);
                    this.mListenerRegistered = true;
                }
                this.mLoader.startLoading();
            }
        }
    }
    
    void stop() {
        if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Stopping: " + this);
        }
        this.mStarted = false;
        if (!this.mRetaining && this.mLoader != null && this.mListenerRegistered) {
            this.mListenerRegistered = false;
            this.mLoader.unregisterListener((Loader.OnLoadCompleteListener<Object>)this);
            this.mLoader.unregisterOnLoadCanceledListener((Loader.OnLoadCanceledListener<Object>)this);
            this.mLoader.stopLoading();
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        sb.append("LoaderInfo{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" #");
        sb.append(this.mId);
        sb.append(" : ");
        DebugUtils.buildShortClassTag(this.mLoader, sb);
        sb.append("}}");
        return sb.toString();
    }
}
