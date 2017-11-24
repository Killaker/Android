package bf.io.openshop.utils;

import android.app.*;
import com.android.volley.*;
import timber.log.*;
import org.json.*;
import android.widget.*;
import android.content.*;
import android.view.*;

public class MsgUtils
{
    public static final int TOAST_TYPE_INTERNAL_ERROR = 2;
    public static final int TOAST_TYPE_MESSAGE = 1;
    public static final int TOAST_TYPE_NO_NETWORK = 3;
    public static final int TOAST_TYPE_NO_SIZE_SELECTED = 5;
    
    public static void logAndShowErrorMessage(final Activity activity, final VolleyError volleyError) {
        try {
            showMessage(activity, new JSONObject(new String(volleyError.networkResponse.data)));
        }
        catch (Exception ex) {
            if (volleyError.getMessage() != null && !volleyError.getMessage().isEmpty()) {
                Timber.e(ex, volleyError.getMessage(), new Object[0]);
            }
            else {
                Timber.e(ex, "Cannot parse error message", new Object[0]);
            }
            showToast(activity, 2, null, ToastLength.SHORT);
        }
    }
    
    public static void logErrorMessage(final VolleyError volleyError) {
        try {
            showMessage(null, new JSONObject(new String(volleyError.networkResponse.data)));
        }
        catch (Exception ex) {
            if (volleyError.getMessage() != null && !volleyError.getMessage().isEmpty()) {
                Timber.e(ex, volleyError.getMessage(), new Object[0]);
                return;
            }
            Timber.e(ex, "Cannot parse error message", new Object[0]);
        }
    }
    
    public static void showMessage(final Activity activity, final JSONObject jsonObject) {
        try {
            final JSONArray jsonArray = jsonObject.getJSONArray("body");
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jsonArray.length(); ++i) {
                sb.append(jsonArray.get(i));
                sb.append("\n");
            }
            final String string = sb.toString();
            Timber.e("Error message:" + string, new Object[0]);
            if (activity != null) {
                showToast(activity, 1, string, ToastLength.LONG);
            }
        }
        catch (Exception ex) {
            Timber.e(ex, "ShowMessage exception", new Object[0]);
            if (activity != null) {
                showToast(activity, 2, null, ToastLength.SHORT);
            }
        }
    }
    
    public static void showToast(final Activity activity, final int n, final String s, final ToastLength toastLength) {
        if (activity == null) {
            Timber.e(new RuntimeException(), "Called showToast with null activity.", new Object[0]);
            return;
        }
        final View inflate = activity.getLayoutInflater().inflate(2130968689, (ViewGroup)activity.findViewById(2131624395));
        final TextView textView = (TextView)inflate.findViewById(2131624397);
        final ImageView imageView = (ImageView)inflate.findViewById(2131624396);
        String text = "";
        final Toast toast = new Toast((Context)activity);
        switch (toastLength) {
            default: {
                Timber.e("Not implemented", new Object[0]);
                break;
            }
            case SHORT: {
                toast.setDuration(0);
                break;
            }
            case LONG: {
                toast.setDuration(1);
                break;
            }
        }
        switch (n) {
            case 1: {
                text = s;
                break;
            }
            case 2: {
                text = activity.getString(2131230833);
                break;
            }
            case 3: {
                text = activity.getString(2131230852);
                break;
            }
            case 5: {
                text = activity.getString(2131230868);
                break;
            }
        }
        textView.setText((CharSequence)text);
        if (false) {
            imageView.setImageResource(0);
            imageView.setVisibility(0);
        }
        else {
            imageView.setVisibility(8);
        }
        toast.setView(inflate);
        toast.show();
    }
    
    public enum ToastLength
    {
        LONG, 
        SHORT;
    }
}
