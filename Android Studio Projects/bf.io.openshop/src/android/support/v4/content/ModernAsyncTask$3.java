package android.support.v4.content;

import android.util.*;
import java.util.concurrent.*;

class ModernAsyncTask$3 extends FutureTask<Result> {
    @Override
    protected void done() {
        try {
            ModernAsyncTask.access$300(ModernAsyncTask.this, ((FutureTask<Object>)this).get());
        }
        catch (InterruptedException ex) {
            Log.w("AsyncTask", (Throwable)ex);
        }
        catch (ExecutionException ex2) {
            throw new RuntimeException("An error occurred while executing doInBackground()", ex2.getCause());
        }
        catch (CancellationException ex3) {
            ModernAsyncTask.access$300(ModernAsyncTask.this, null);
        }
        catch (Throwable t) {
            throw new RuntimeException("An error occurred while executing doInBackground()", t);
        }
    }
}