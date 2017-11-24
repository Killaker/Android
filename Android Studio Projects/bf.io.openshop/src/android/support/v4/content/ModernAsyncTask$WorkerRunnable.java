package android.support.v4.content;

import java.util.concurrent.*;

private abstract static class WorkerRunnable<Params, Result> implements Callable<Result>
{
    Params[] mParams;
}
