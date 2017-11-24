package bolts;

public interface Continuation<TTaskResult, TContinuationResult>
{
    TContinuationResult then(final Task<TTaskResult> p0) throws Exception;
}
