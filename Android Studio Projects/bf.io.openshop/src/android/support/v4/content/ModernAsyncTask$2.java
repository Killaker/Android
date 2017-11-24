package android.support.v4.content;

import android.os.*;

class ModernAsyncTask$2 extends WorkerRunnable<Params, Result> {
    @Override
    public Result call() throws Exception {
        ModernAsyncTask.access$100(ModernAsyncTask.this).set(true);
        Process.setThreadPriority(10);
        return (Result)ModernAsyncTask.access$200(ModernAsyncTask.this, ModernAsyncTask.this.doInBackground(this.mParams));
    }
}