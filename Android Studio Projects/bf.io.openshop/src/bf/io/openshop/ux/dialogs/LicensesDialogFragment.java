package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import android.webkit.*;
import android.widget.*;
import timber.log.*;
import java.io.*;
import android.os.*;
import android.app.*;
import android.view.*;
import android.support.annotation.*;

public class LicensesDialogFragment extends DialogFragment
{
    private AsyncTask<Void, Void, String> licenseAsyncTask;
    private WebView licenseWebView;
    private ProgressBar progressBar;
    
    private void loadLicenses() {
        this.licenseAsyncTask = (AsyncTask<Void, Void, String>)new AsyncTask<Void, Void, String>() {
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
                LicensesDialogFragment.this.progressBar.setVisibility(4);
                LicensesDialogFragment.this.licenseWebView.setVisibility(0);
                LicensesDialogFragment.this.licenseWebView.loadDataWithBaseURL((String)null, s, "text/html", "utf-8", (String)null);
                LicensesDialogFragment.this.licenseAsyncTask = null;
            }
        }.execute((Object[])new Void[0]);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setStyle(1, 2131427708);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " - onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968634, viewGroup, false);
        this.licenseWebView = (WebView)inflate.findViewById(2131624229);
        this.progressBar = (ProgressBar)inflate.findViewById(2131624230);
        return inflate;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.licenseAsyncTask != null) {
            this.licenseAsyncTask.cancel(true);
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final Window window = dialog.getWindow();
            if (window == null) {
                Timber.e("Cannot set dialog animation", new Object[0]);
                return;
            }
            window.setWindowAnimations(2131427706);
        }
    }
    
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.loadLicenses();
    }
}
