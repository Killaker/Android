package android.support.v4.content;

private static class AsyncTaskResult<Data>
{
    final Data[] mData;
    final ModernAsyncTask mTask;
    
    AsyncTaskResult(final ModernAsyncTask mTask, final Data... mData) {
        this.mTask = mTask;
        this.mData = mData;
    }
}
