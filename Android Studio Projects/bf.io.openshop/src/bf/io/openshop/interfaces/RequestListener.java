package bf.io.openshop.interfaces;

import com.android.volley.*;

public interface RequestListener
{
    void requestFailed(final VolleyError p0);
    
    void requestSuccess(final long p0);
}
