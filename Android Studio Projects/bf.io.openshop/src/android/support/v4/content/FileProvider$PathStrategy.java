package android.support.v4.content;

import android.net.*;
import java.io.*;

interface PathStrategy
{
    File getFileForUri(final Uri p0);
    
    Uri getUriForFile(final File p0);
}
