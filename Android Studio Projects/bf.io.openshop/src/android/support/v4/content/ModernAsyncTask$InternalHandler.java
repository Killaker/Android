package android.support.v4.content;

import java.util.concurrent.atomic.*;
import android.os.*;
import android.util.*;
import java.util.concurrent.*;

private static class InternalHandler extends Handler
{
    public InternalHandler() {
        super(Looper.getMainLooper());
    }
    
    public void handleMessage(final Message message) {
        final AsyncTaskResult asyncTaskResult = (AsyncTaskResult)message.obj;
        switch (message.what) {
            default: {}
            case 1: {
                asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
            }
            case 2: {
                asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
            }
        }
    }
}
