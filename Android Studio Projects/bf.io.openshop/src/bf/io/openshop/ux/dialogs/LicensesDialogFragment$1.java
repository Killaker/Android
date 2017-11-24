package bf.io.openshop.ux.dialogs;

import android.os.*;
import timber.log.*;
import java.io.*;

class LicensesDialogFragment$1 extends AsyncTask<Void, Void, String> {
    protected String doInBackground(final Void... array) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(LicensesDialogFragment.this.getActivity().getResources().openRawResource(2131165185)));
        final StringBuilder sb = new StringBuilder();
        Label_0084: {
            try {
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break Label_0084;
                    }
                    sb.append(line);
                    sb.append("\n");
                }
            }
            catch (IOException ex) {
                Timber.e(ex, "Load licenses failed.", new Object[0]);
            }
            return sb.toString();
        }
        bufferedReader.close();
        return sb.toString();
    }
    
    protected void onPostExecute(final String s) {
        super.onPostExecute((Object)s);
        if (LicensesDialogFragment.this.getActivity() == null || this.isCancelled()) {
            return;
        }
        LicensesDialogFragment.access$000(LicensesDialogFragment.this).setVisibility(4);
        LicensesDialogFragment.access$100(LicensesDialogFragment.this).setVisibility(0);
        LicensesDialogFragment.access$100(LicensesDialogFragment.this).loadDataWithBaseURL((String)null, s, "text/html", "utf-8", (String)null);
        LicensesDialogFragment.access$202(LicensesDialogFragment.this, null);
    }
}