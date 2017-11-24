package bf.io.openshop.ux;

import android.support.v7.app.*;
import android.app.*;
import android.os.*;
import timber.log.*;
import android.view.animation.*;
import android.animation.*;
import com.facebook.applinks.*;
import bf.io.openshop.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.api.*;
import com.android.volley.*;
import android.content.*;
import android.net.*;
import android.view.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import bf.io.openshop.utils.*;
import com.facebook.appevents.*;
import bf.io.openshop.ux.adapters.*;
import android.widget.*;
import java.util.*;

public class SplashActivity extends AppCompatActivity
{
    private static final String TAG;
    private Activity activity;
    private Button continueToShopBtn;
    private View layoutContent;
    private View layoutContentNoConnection;
    private View layoutContentSelectShop;
    private boolean layoutCreated;
    private View layoutIntroScreen;
    private ProgressDialog progressDialog;
    private Spinner shopSelectionSpinner;
    
    static {
        TAG = SplashActivity.class.getSimpleName();
    }
    
    public SplashActivity() {
        this.layoutCreated = false;
    }
    
    private void animateContentVisible() {
        if (this.layoutIntroScreen != null && this.layoutContent != null && this.layoutIntroScreen.getVisibility() == 0) {
            this.runOnUiThread((Runnable)new Runnable() {
                @Override
                public void run() {
                    new Handler().postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (Build$VERSION.SDK_INT > 21) {
                                Timber.d("Circular animation.", new Object[0]);
                                final int n = (SplashActivity.this.layoutContent.getLeft() + SplashActivity.this.layoutContent.getRight()) / 2;
                                final int n2 = (SplashActivity.this.layoutContent.getTop() + SplashActivity.this.layoutContent.getBottom()) / 2;
                                final Animator circularReveal = ViewAnimationUtils.createCircularReveal(SplashActivity.this.layoutContent, n, n2, 0.0f, (float)Math.hypot(Math.max(n, SplashActivity.this.layoutContent.getWidth() - n), Math.max(n2, SplashActivity.this.layoutContent.getHeight() - n2)));
                                circularReveal.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
                                circularReveal.setDuration(1250L);
                                SplashActivity.this.layoutContent.setVisibility(0);
                                circularReveal.start();
                                return;
                            }
                            Timber.d("Alpha animation.", new Object[0]);
                            SplashActivity.this.layoutContent.setAlpha(0.0f);
                            SplashActivity.this.layoutContent.setVisibility(0);
                            SplashActivity.this.layoutContent.animate().alpha(1.0f).setDuration(1000L).setListener((Animator$AnimatorListener)null);
                        }
                    }, 330L);
                }
            });
        }
    }
    
    private void init() {
        if (!MyApplication.getInstance().isDataConnected()) {
            this.progressDialog.hide();
            Timber.d("No network connection.", new Object[0]);
            this.initSplashLayout();
            this.layoutContent.setVisibility(0);
            this.layoutIntroScreen.setVisibility(8);
            this.layoutContentNoConnection.setVisibility(0);
            this.layoutContentSelectShop.setVisibility(8);
            return;
        }
        this.progressDialog.hide();
        final Intent intent = this.getIntent();
        while (true) {
            final Uri data;
            Label_0296: {
                if (intent != null) {
                    data = intent.getData();
                    if (data != null && (data.getQueryParameter("utm_source") != null || data.getQueryParameter("referrer") != null)) {
                        Timber.d("UTM source detected. - General Campaign & Traffic Source Attribution.", new Object[0]);
                        if (data.getQueryParameter("utm_source") == null) {
                            break Label_0296;
                        }
                        Analytics.setCampaignUriString(data.toString());
                    }
                    else if (intent.getExtras() != null) {
                        Timber.d("Extra bundle detected.", new Object[0]);
                        try {
                            final Bundle extras = this.getIntent().getExtras();
                            if (extras != null) {
                                final Bundle bundle = extras.getBundle("al_applink_data");
                                if (bundle != null) {
                                    final String string = bundle.getString("target_url");
                                    if (string != null && !string.isEmpty()) {
                                        Timber.d("TargetUrl: " + string, new Object[0]);
                                        Analytics.setCampaignUriString(string);
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {
                            Timber.e(ex, "Parsing FB deepLink exception", new Object[0]);
                        }
                    }
                    else {
                        try {
                            AppLinkData.fetchDeferredAppLinkData((Context)this, (AppLinkData.CompletionHandler)new AppLinkData.CompletionHandler() {
                                @Override
                                public void onDeferredAppLinkDataFetched(final AppLinkData appLinkData) {
                                    if (appLinkData == null) {
                                        return;
                                    }
                                    try {
                                        final String string = appLinkData.getTargetUri().toString();
                                        if (string != null && !string.isEmpty()) {
                                            Timber.e("TargetUrl: " + string, new Object[0]);
                                            Analytics.setCampaignUriString(string);
                                        }
                                    }
                                    catch (Exception ex) {
                                        Timber.e(ex, "AppLinkData exception", new Object[0]);
                                    }
                                }
                            });
                        }
                        catch (Exception ex2) {
                            Timber.e(ex2, "Fetch deferredAppLinkData  exception", new Object[0]);
                        }
                    }
                }
                if (this.getIntent() != null && this.getIntent().getExtras() != null && this.getIntent().getExtras().getString("link") != null) {
                    Timber.d("Run by notification.", new Object[0]);
                    final String string2 = this.getIntent().getExtras().getString("link", "");
                    final String string3 = this.getIntent().getExtras().getString("title", "");
                    String[] split = null;
                    Label_0467: {
                        try {
                            split = string2.split(":");
                            if (split.length != 3) {
                                Timber.e("Bad notification format. NotifyType:" + string2, new Object[0]);
                                throw new Exception("Bad notification format. NotifyType:" + string2);
                            }
                            break Label_0467;
                        }
                        catch (Exception ex3) {
                            Timber.e(ex3, "Skip Splash activity after notification error.", new Object[0]);
                            this.startMainActivity(null);
                            return;
                        }
                        break Label_0296;
                    }
                    final String string4 = split[1] + ":" + split[2];
                    final String format = String.format(EndPoints.SHOPS_SINGLE, Integer.parseInt(split[0]));
                    Analytics.setCampaignUriString(this.getIntent().getExtras().getString("utm", ""));
                    this.progressDialog.show();
                    final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, (Class<Object>)Shop.class, (Response.Listener<Object>)new Response.Listener<Shop>() {
                        public void onResponse(final Shop shop) {
                            SplashActivity.this.progressDialog.cancel();
                            final Bundle bundle = new Bundle();
                            bundle.putString("target", string4);
                            bundle.putString("title", string3);
                            final Shop actualShop = SettingsMy.getActualShop();
                            if (actualShop != null && shop.getId() != actualShop.getId()) {
                                LoginDialogFragment.logoutUser();
                            }
                            SplashActivity.this.setShopInformationAndStartMainActivity(shop, bundle);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError volleyError) {
                            SplashActivity.this.progressDialog.cancel();
                            MsgUtils.logErrorMessage(volleyError);
                            SplashActivity.this.startMainActivity(null);
                        }
                    });
                    gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                    gsonRequest.setShouldCache(false);
                    MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "splash_requests");
                    return;
                }
                Timber.d("Nothing special.", new Object[0]);
                this.startMainActivity(null);
                return;
            }
            if (data.getQueryParameter("referrer") != null) {
                Analytics.setCampaignUriString(data.getQueryParameter("referrer"));
            }
            continue;
        }
    }
    
    private void initSplashLayout() {
        if (!this.layoutCreated) {
            this.setContentView(2130968603);
            this.layoutContent = this.findViewById(2131624071);
            this.layoutIntroScreen = this.findViewById(2131624070);
            this.layoutContentNoConnection = this.findViewById(2131624072);
            this.layoutContentSelectShop = this.findViewById(2131624074);
            this.shopSelectionSpinner = (Spinner)this.findViewById(2131624075);
            (this.continueToShopBtn = (Button)this.findViewById(2131624076)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    final Shop shop = (Shop)SplashActivity.this.shopSelectionSpinner.getSelectedItem();
                    if (shop != null && shop.getId() != -131L) {
                        SplashActivity.this.setShopInformationAndStartMainActivity(shop, null);
                        return;
                    }
                    Timber.e("Cannot continue. Shop is not selected or is null.", new Object[0]);
                }
            });
            ((Button)this.findViewById(2131624073)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    SplashActivity.this.progressDialog.show();
                    new Handler().postDelayed((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            SplashActivity.this.init();
                        }
                    }, 1000L);
                }
            });
            this.layoutCreated = true;
            return;
        }
        Timber.d(this.getClass().getSimpleName() + " screen is already created.", new Object[0]);
    }
    
    private void requestShops() {
        if (this.layoutIntroScreen.getVisibility() != 0) {
            this.progressDialog.show();
        }
        final GsonRequest<Object> gsonRequest = new GsonRequest<Object>(0, EndPoints.SHOPS, null, (Class<Object>)ShopResponse.class, (Response.Listener<Object>)new Response.Listener<ShopResponse>() {
            public void onResponse(@NonNull final ShopResponse shopResponse) {
                Timber.d("Get shops response: " + shopResponse.toString(), new Object[0]);
                SplashActivity.this.setSpinShops(shopResponse.getShopList());
                if (SplashActivity.this.progressDialog != null) {
                    SplashActivity.this.progressDialog.cancel();
                }
                SplashActivity.this.animateContentVisible();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (SplashActivity.this.progressDialog != null) {
                    SplashActivity.this.progressDialog.cancel();
                }
                MsgUtils.logAndShowErrorMessage(SplashActivity.this.activity, volleyError);
                SplashActivity.this.finish();
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue(gsonRequest, "splash_requests");
    }
    
    private void setShopInformationAndStartMainActivity(final Shop actualShop, final Bundle bundle) {
        SettingsMy.setActualShop(actualShop);
        this.startMainActivity(bundle);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        Timber.tag(SplashActivity.TAG);
        this.activity = this;
        this.progressDialog = Utils.generateProgressDialog((Context)this, false);
        this.init();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp((Context)this);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp((Context)this);
    }
    
    @Override
    protected void onStop() {
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        if (this.layoutIntroScreen != null) {
            this.layoutIntroScreen.setVisibility(8);
        }
        if (this.layoutContent != null) {
            this.layoutContent.setVisibility(0);
        }
        MyApplication.getInstance().cancelPendingRequests("splash_requests");
        super.onStop();
    }
    
    public void setSpinShops(final List<Shop> list) {
        if (list != null && list.size() > 0) {
            final Shop shop = new Shop();
            shop.setId(-131L);
            shop.setName(this.getString(2131230890));
            list.add(0, shop);
            this.shopSelectionSpinner.setAdapter((SpinnerAdapter)new ShopSpinnerAdapter(this, list));
            this.shopSelectionSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    if (((Shop)adapterView.getItemAtPosition(n)).getId() == -131L) {
                        SplashActivity.this.continueToShopBtn.setVisibility(4);
                        return;
                    }
                    SplashActivity.this.continueToShopBtn.setVisibility(0);
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                    Timber.d("No shop selected.", new Object[0]);
                }
            });
            if (list.size() == 2) {
                this.shopSelectionSpinner.setSelection(1);
                Timber.d("Only one shop exist.", new Object[0]);
            }
            else {
                final String language = Locale.getDefault().getLanguage();
                Timber.d("Default language: " + language, new Object[0]);
                long id = 0L;
                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).getLanguage() != null && list.get(i).getLanguage().equalsIgnoreCase(language)) {
                        id = list.get(i).getId();
                        break;
                    }
                }
                for (int j = 0; j < list.size(); ++j) {
                    if (list.get(j).getId() == id) {
                        Timber.d("Preselect language position:" + j, new Object[0]);
                        this.shopSelectionSpinner.setSelection(j);
                        return;
                    }
                }
            }
            return;
        }
        Timber.e(new RuntimeException(), "Trying to set empty shops array.", new Object[0]);
    }
    
    public void startMainActivity(final Bundle bundle) {
        if (SettingsMy.getActualShop() == null) {
            Timber.d("Missing active shop. Show shop selection.", new Object[0]);
            this.initSplashLayout();
            this.layoutContentNoConnection.setVisibility(8);
            this.layoutContentSelectShop.setVisibility(0);
            this.requestShops();
            return;
        }
        final Intent intent = new Intent((Context)this, (Class)MainActivity.class);
        if (bundle != null) {
            Timber.d("Pass bundle to main activity", new Object[0]);
            intent.putExtras(bundle);
        }
        this.startActivity(intent);
        this.finish();
    }
}
