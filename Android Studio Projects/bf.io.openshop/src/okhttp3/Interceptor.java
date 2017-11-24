package okhttp3;

import java.io.*;

public interface Interceptor
{
    Response intercept(final Chain p0) throws IOException;
    
    public interface Chain
    {
        Connection connection();
        
        Response proceed(final Request p0) throws IOException;
        
        Request request();
    }
}
