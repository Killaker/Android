package bf.io.openshop.ux;

import com.android.volley.*;
import bf.io.openshop.utils.*;

class MainActivity$4 implements ErrorListener {
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        MsgUtils.logErrorMessage(volleyError);
        MainActivity.access$000(MainActivity.this, 0);
    }
}