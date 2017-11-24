package android.support.v4.net;

import android.net.*;

static class GingerbreadConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl
{
    @Override
    public boolean isActiveNetworkMetered(final ConnectivityManager connectivityManager) {
        return ConnectivityManagerCompatGingerbread.isActiveNetworkMetered(connectivityManager);
    }
}
