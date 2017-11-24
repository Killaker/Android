package android.support.v4.widget;

import android.database.*;

interface CursorFilterClient
{
    void changeCursor(final Cursor p0);
    
    CharSequence convertToString(final Cursor p0);
    
    Cursor getCursor();
    
    Cursor runQueryOnBackgroundThread(final CharSequence p0);
}
