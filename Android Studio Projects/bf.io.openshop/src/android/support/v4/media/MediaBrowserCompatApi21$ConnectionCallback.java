package android.support.v4.media;

interface ConnectionCallback
{
    void onConnected();
    
    void onConnectionFailed();
    
    void onConnectionSuspended();
}
