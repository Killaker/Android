package bf.io.openshop.ux;

import android.widget.*;
import android.view.*;
import bf.io.openshop.entities.*;
import timber.log.*;

class SplashActivity$8 implements AdapterView$OnItemSelectedListener {
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (((Shop)adapterView.getItemAtPosition(n)).getId() == -131L) {
            SplashActivity.access$600(SplashActivity.this).setVisibility(4);
            return;
        }
        SplashActivity.access$600(SplashActivity.this).setVisibility(0);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Timber.d("No shop selected.", new Object[0]);
    }
}