package bf.io.openshop.utils;

import android.text.style.*;
import android.app.*;
import android.view.*;
import android.net.*;
import timber.log.*;
import android.content.*;

private static class DefensiveURLSpan extends URLSpan
{
    Activity activity;
    
    public DefensiveURLSpan(final String s, final Activity activity) {
        super(s);
        this.activity = activity;
    }
    
    public void onClick(final View view) {
        final Uri parse = Uri.parse(this.getURL());
        final Context context = view.getContext();
        final Intent intent = new Intent("android.intent.action.VIEW", parse);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        try {
            context.startActivity(intent);
        }
        catch (ActivityNotFoundException ex) {
            if (this.activity != null && !this.activity.isFinishing()) {
                MsgUtils.showToast(this.activity, 1, this.activity.getString(2131230837), MsgUtils.ToastLength.SHORT);
                Timber.e((Throwable)ex, "Invoked invalid web link: " + parse, new Object[0]);
            }
        }
    }
}
