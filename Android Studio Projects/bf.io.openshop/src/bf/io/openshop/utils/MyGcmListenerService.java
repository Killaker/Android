package bf.io.openshop.utils;

import com.google.android.gms.gcm.*;
import android.os.*;
import android.support.v4.app.*;
import android.media.*;
import android.net.*;
import android.content.*;
import android.app.*;
import bf.io.openshop.ux.*;
import android.graphics.*;
import java.net.*;
import timber.log.*;

public class MyGcmListenerService extends GcmListenerService
{
    public static final int NOTIFICATION_ID = 6342806;
    private static final String TAG = "GcmListener";
    
    private void sendNotification(final Bundle bundle) {
        final String string = bundle.getString("title", this.getString(2131230950));
        final String string2 = bundle.getString("message");
        final NotificationCompat.Builder setSound = new NotificationCompat.Builder((Context)this).setSmallIcon(2130837680).setLargeIcon(BitmapFactory.decodeResource(this.getResources(), 2130837679)).setContentTitle(string).setStyle(new NotificationCompat.BigTextStyle().bigText(string2)).setContentText(string2).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2));
        final String string3 = bundle.getString("image_url");
        if (string3 != null && !string3.isEmpty()) {
            final Bitmap bitmapFromURL = this.getBitmapFromURL(string3);
            if (bitmapFromURL != null) {
                setSound.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmapFromURL).setBigContentTitle(string).setSummaryText(string2));
            }
        }
        final String string4 = bundle.getString("link");
        while (true) {
            Label_0289: {
                if (string4 == null || !string4.contains("http")) {
                    break Label_0289;
                }
                try {
                    final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(string4));
                    intent.putExtra("utm", "utm_source=API" + "&" + "utm_medium=notification" + "&" + ("utm_campaign=" + string));
                    setSound.setContentIntent(PendingIntent.getActivity((Context)this, 0, intent, 134217728));
                    ((NotificationManager)this.getSystemService("notification")).notify(6342806, setSound.build());
                    return;
                }
                catch (Exception ex) {
                    return;
                }
            }
            final Intent intent = new Intent((Context)this, (Class)SplashActivity.class);
            intent.addFlags(268468224);
            intent.putExtra("link", string4);
            intent.putExtra("title", string);
            continue;
        }
    }
    
    public Bitmap getBitmapFromURL(final String s) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void onMessageReceived(final String s, final Bundle bundle) {
        final String string = bundle.getString("message");
        Timber.d("From: " + s, new Object[0]);
        Timber.d("Message: " + string, new Object[0]);
        Timber.d("Bundle:" + bundle.toString(), new Object[0]);
        this.sendNotification(bundle);
    }
}
