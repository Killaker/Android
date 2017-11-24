package bolts;

import android.net.*;

public interface AppLinkResolver
{
    Task<AppLink> getAppLinkFromUrlInBackground(final Uri p0);
}
