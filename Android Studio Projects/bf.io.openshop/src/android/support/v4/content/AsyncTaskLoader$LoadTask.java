package android.support.v4.content;

import java.util.concurrent.*;
import android.support.v4.os.*;

final class LoadTask extends ModernAsyncTask<Void, Void, D> implements Runnable
{
    private final CountDownLatch mDone;
    boolean waiting;
    
    LoadTask() {
        this.mDone = new CountDownLatch(1);
    }
    
    @Override
    protected D doInBackground(final Void... array) {
        try {
            return AsyncTaskLoader.this.onLoadInBackground();
        }
        catch (OperationCanceledException ex) {
            if (!this.isCancelled()) {
                throw ex;
            }
            return null;
        }
    }
    
    @Override
    protected void onCancelled(final D n) {
        try {
            AsyncTaskLoader.this.dispatchOnCancelled(this, n);
        }
        finally {
            this.mDone.countDown();
        }
    }
    
    @Override
    protected void onPostExecute(final D n) {
        try {
            AsyncTaskLoader.this.dispatchOnLoadComplete(this, n);
        }
        finally {
            this.mDone.countDown();
        }
    }
    
    @Override
    public void run() {
        this.waiting = false;
        AsyncTaskLoader.this.executePendingTask();
    }
    
    public void waitForLoader() {
        try {
            this.mDone.await();
        }
        catch (InterruptedException ex) {}
    }
}
