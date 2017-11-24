package bf.io.openshop.utils;

import com.google.android.gms.common.*;
import timber.log.*;
import android.support.design.widget.*;
import android.graphics.drawable.*;
import android.graphics.*;
import android.app.*;
import com.google.gson.*;
import bf.io.openshop.entities.filtr.*;
import java.lang.reflect.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.net.*;
import android.content.*;

public class Utils
{
    private static Gson gson;
    
    public static String calculateDiscountPercent(final Context context, final double n, final double n2) {
        return String.format(context.getString(2131230938), (int)(100.0 - 100.0 * (n2 / n)));
    }
    
    public static boolean checkPlayServices(final Activity activity) {
        final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        final int googlePlayServicesAvailable = instance.isGooglePlayServicesAvailable((Context)activity);
        if (googlePlayServicesAvailable == 0) {
            return true;
        }
        Timber.e("Google play services don't working.", new Object[0]);
        if (instance.isUserResolvableError(googlePlayServicesAvailable)) {
            instance.getErrorDialog(activity, googlePlayServicesAvailable, 9000).show();
            return false;
        }
        Timber.e("GCM - This device is not supported.", new Object[0]);
        return false;
    }
    
    public static boolean checkTextInputLayoutValueRequirement(final TextInputLayout textInputLayout, final String error) {
        if (textInputLayout == null || textInputLayout.getEditText() == null) {
            Timber.e(new RuntimeException(), "Checking null input field during order send.", new Object[0]);
            return false;
        }
        final String textFromInputLayout = getTextFromInputLayout(textInputLayout);
        if (textFromInputLayout == null || textFromInputLayout.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(error);
            Timber.d("Input field: " + (Object)textInputLayout.getHint() + " missing text.", new Object[0]);
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        Timber.d("Input field: " + (Object)textInputLayout.getHint() + " OK.", new Object[0]);
        return true;
    }
    
    public static int dpToPx(final Context context, final int n) {
        return Math.round(n * getPixelScaleFactor(context));
    }
    
    public static Bitmap drawableToBitmap(final Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        int n;
        if (!drawable.getBounds().isEmpty()) {
            n = drawable.getBounds().width();
        }
        else {
            n = drawable.getIntrinsicWidth();
        }
        int n2;
        if (!drawable.getBounds().isEmpty()) {
            n2 = drawable.getBounds().height();
        }
        else {
            n2 = drawable.getIntrinsicHeight();
        }
        if (n <= 0) {
            n = 1;
        }
        if (n2 <= 0) {
            n2 = 1;
        }
        final Bitmap bitmap = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    
    public static ProgressDialog generateProgressDialog(final Context context, final boolean cancelable) {
        final ProgressDialog progressDialog = new ProgressDialog(context, 2131427455);
        progressDialog.setMessage((CharSequence)context.getString(2131230838));
        progressDialog.setCancelable(cancelable);
        return progressDialog;
    }
    
    public static Gson getGsonParser() {
        if (Utils.gson == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Filters.class, new DeserializerFilters());
            Utils.gson = gsonBuilder.create();
        }
        return Utils.gson;
    }
    
    private static float getPixelScaleFactor(final Context context) {
        return context.getResources().getDisplayMetrics().xdpi / 160.0f;
    }
    
    public static String getTextFromInputLayout(final TextInputLayout textInputLayout) {
        if (textInputLayout != null && textInputLayout.getEditText() != null) {
            return textInputLayout.getEditText().getText().toString();
        }
        return null;
    }
    
    public static String parseDate(final String s) {
        try {
            final String[] split = s.split("-");
            return split[2].split(" ")[0] + "." + split[1] + "." + split[0];
        }
        catch (Exception ex) {
            Timber.e(ex, "Parsing order date created failed.", new Object[0]);
            return s;
        }
    }
    
    public static int pxToDp(final Context context, final int n) {
        return Math.round(n / getPixelScaleFactor(context));
    }
    
    public static SpannableString safeURLSpanLinks(final Spanned spanned, final Activity activity) {
        final SpannableString spannableString = new SpannableString((CharSequence)spanned);
        for (final URLSpan urlSpan : (URLSpan[])spannableString.getSpans(0, spannableString.length(), (Class)URLSpan.class)) {
            final int spanStart = spannableString.getSpanStart((Object)urlSpan);
            final int spanEnd = spannableString.getSpanEnd((Object)urlSpan);
            spannableString.removeSpan((Object)urlSpan);
            spannableString.setSpan((Object)new DefensiveURLSpan(urlSpan.getURL(), activity), spanStart, spanEnd, 0);
        }
        return spannableString;
    }
    
    public static void setTextToInputLayout(final TextInputLayout textInputLayout, final String text) {
        if (textInputLayout != null && textInputLayout.getEditText() != null) {
            textInputLayout.getEditText().setText((CharSequence)text);
            return;
        }
        Timber.e("Setting text to null input wrapper, or without editText", new Object[0]);
    }
    
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
}
