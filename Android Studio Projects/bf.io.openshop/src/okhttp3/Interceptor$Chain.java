package okhttp3;

import java.io.*;

public interface Chain
{
    Connection connection();
    
    Response proceed(final Request p0) throws IOException;
    
    Request request();
}
