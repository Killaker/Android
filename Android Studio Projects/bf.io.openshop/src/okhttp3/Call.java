package okhttp3;

import java.io.*;

public interface Call
{
    void cancel();
    
    void enqueue(final Callback p0);
    
    Response execute() throws IOException;
    
    boolean isCanceled();
    
    boolean isExecuted();
    
    Request request();
    
    public interface Factory
    {
        Call newCall(final Request p0);
    }
}
