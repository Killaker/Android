package android.support.v4.net;

import android.net.*;

static class HoneycombMR2ConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl
{
    @Override
    public boolean isActiveNetworkMetered(final ConnectivityManager connectivityManager) {
        return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(connectivityManager);
    }
}
