package android.support.v4.net;

import android.net.*;

static class JellyBeanConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl
{
    @Override
    public boolean isActiveNetworkMetered(final ConnectivityManager connectivityManager) {
        return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(connectivityManager);
    }
}
